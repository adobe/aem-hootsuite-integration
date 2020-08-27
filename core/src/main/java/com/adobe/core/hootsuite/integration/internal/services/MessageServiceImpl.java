/*
 * #%L
 * Hootsuite Integration
 * %%
 * Copyright 2020 Adobe. All rights reserved.
 * %%
 * This file is licensed to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. You may obtain a copy
 * of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.adobe.core.hootsuite.integration.internal.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.framework.ServiceException;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.core.hootsuite.integration.external.constants.GlobalConstants;
import com.adobe.core.hootsuite.integration.external.services.MediaService;
import com.adobe.core.hootsuite.integration.external.services.MessageService;
import com.adobe.core.hootsuite.integration.external.services.OAuthService;
import com.adobe.core.hootsuite.integration.external.services.ProfileService;
import com.adobe.core.hootsuite.integration.external.services.UtilityService;
import com.adobe.core.hootsuite.integration.external.services.beans.Datum;
import com.adobe.core.hootsuite.integration.external.services.beans.MediaUploadResponse;
import com.adobe.core.hootsuite.integration.external.services.beans.MediaUrl;
import com.adobe.core.hootsuite.integration.external.services.beans.MessageCreationRequest;
import com.adobe.core.hootsuite.integration.external.services.beans.MessageCreationResponse;
import com.adobe.core.hootsuite.integration.external.services.exceptions.OAuthException;
import com.day.cq.dam.api.Asset;
import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component(immediate = true, service = MessageService.class)
@Designate(ocd = com.adobe.core.hootsuite.integration.internal.services.Configuration.class)
public class MessageServiceImpl implements MessageService {

	private static final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

	@Reference
	private transient UtilityService utilityService;

	@Reference
	private transient OAuthService oauthService;

	@Reference
	private transient ProfileService profileService;

	@Reference
	private transient MediaService mediaService;

	private com.adobe.core.hootsuite.integration.internal.services.Configuration config;

	@Activate
	public void activate(com.adobe.core.hootsuite.integration.internal.services.Configuration configuration) {
		this.config = configuration;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MessageCreationResponse createSocialPost(Page page, ResourceResolver resolver, String socialMediaID) {
		Resource config = null;
		try {
			config = this.utilityService.findConfiguration(page.adaptTo(Resource.class), "hootsuite/components/admin/hootsuite");

			ObjectMapper mapper = new ObjectMapper();

			MessageCreationRequest request = buildPostMessagePayload(page, resolver, config);

			// Add Social Pro
			request.setSocialProfileIds((StringUtils.isBlank(socialMediaID))
					? this.profileService.getSocialProfiles(config, resolver).getProfileList()
					: Arrays.asList(socialMediaID));

			String response = Request.Post("https://platform.hootsuite.com/v1/messages")
					.setHeader(GlobalConstants.HEADER_CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
					.setHeader(GlobalConstants.HEADER_ACCEPT, GlobalConstants.MIME_TYPE_JSON)
					.setHeader(GlobalConstants.HEADER_CACHE_CONTROL, "no-cache")
					.setHeader(GlobalConstants.HEADER_AUTHORIZATION, "Bearer " + config.getValueMap().get("accessToken", ""))
					.bodyString(mapper.writeValueAsString(request), ContentType.APPLICATION_JSON).execute()
					.returnContent().asString();
			MessageCreationResponse postResponse = mapper.readValue(response, MessageCreationResponse.class);

			if (postResponse.getAdditionalProperties().containsKey("error") && StringUtils.equalsIgnoreCase(
					postResponse.getAdditionalProperties().get("error").toString(), "request_forbidden")) {
				throw new OAuthException("request_forbidden", "ERR401");
			} else if (postResponse.getAdditionalProperties().containsKey("error")) {

			}

			// Update page properties.
			ModifiableValueMap valueMap = page.getContentResource().adaptTo(ModifiableValueMap.class);
			for (Datum data : postResponse.getData()) {
				valueMap.put(data.getSocialProfile().getId(), data.getId());
				valueMap.put(data.getSocialProfile().getId() + "_type", StringUtils.isNotEmpty(data.getSocialProfile().getType()) ? data.getSocialProfile().getType() : "");
				valueMap.put(data.getSocialProfile().getId() + "_url", StringUtils.isNotEmpty(data.getPostUrl()) ? data.getPostUrl() : "");
			}
			valueMap.put("publishedToHootsuite", true);
			resolver.commit();

			return postResponse;
		} catch (OAuthException | HttpResponseException e) {
			this.utilityService.updateConfiguration(config, resolver, this.oauthService.getRefreshToken(config));
			return createSocialPost(page, resolver, socialMediaID);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MessageCreationResponse createSocialPost(Asset asset, ResourceResolver resolver, String socialMediaID) {
		Resource config = null;
		try {
			
			config = this.utilityService.findConfigurationInHierarchy(asset.adaptTo(Resource.class), "hootsuite/components/admin/hootsuite");

			ObjectMapper mapper = new ObjectMapper();
			MessageCreationRequest request = buildPostMessagePayload(asset, resolver, config);

			// Add Social Pro
			request.setSocialProfileIds((StringUtils.isBlank(socialMediaID))
					? this.profileService.getSocialProfiles(config, resolver).getProfileList()
					: Arrays.asList(socialMediaID));

			String response = Request.Post("https://platform.hootsuite.com/v1/messages")
					.setHeader(GlobalConstants.HEADER_CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
					.setHeader(GlobalConstants.HEADER_ACCEPT, GlobalConstants.MIME_TYPE_JSON)
					.setHeader(GlobalConstants.HEADER_CACHE_CONTROL, "no-cache")
					.setHeader(GlobalConstants.HEADER_AUTHORIZATION, "Bearer " + config.getValueMap().get("accessToken", ""))
					.bodyString(mapper.writeValueAsString(request), ContentType.APPLICATION_JSON).execute()
					.returnContent().asString();
			MessageCreationResponse postResponse = mapper.readValue(response, MessageCreationResponse.class);

			if (postResponse.getAdditionalProperties().containsKey("error") && StringUtils.equalsIgnoreCase(
					postResponse.getAdditionalProperties().get("error").toString(), "request_forbidden")) {
				throw new OAuthException("request_forbidden", "ERR401");
			} else if (postResponse.getAdditionalProperties().containsKey("error")) {

			}
			
			// Update Metadata.
			Map<String, Object> metadataMap = asset.getMetadata();
			for (Datum data : postResponse.getData()) {
				metadataMap.put(data.getSocialProfile().getId(), data.getId());
				metadataMap.put(data.getSocialProfile().getId() + "_type", StringUtils.isNotEmpty(data.getSocialProfile().getType()) ? data.getSocialProfile().getType() : "");
				metadataMap.put(data.getSocialProfile().getId() + "_url", StringUtils.isNotEmpty(data.getPostUrl()) ? data.getPostUrl() : "");
			}
			metadataMap.put("publishedToHootsuite", true);
			resolver.commit();
			
			return postResponse;
		} catch (OAuthException | HttpResponseException e) {
			this.utilityService.updateConfiguration(config, resolver, this.oauthService.getRefreshToken(config));
			return createSocialPost(asset, resolver, socialMediaID);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/**
	 * Builds the post message payload.
	 *
	 * @param asset {@link Asset} the asset
	 * @param resolver {@link ResourceResolver} the resolver
	 * @param configuration {@link Resource} the configuration
	 * @return the message creation request {@link MessageCreationRequest}
	 */
	private MessageCreationRequest buildPostMessagePayload(Asset asset, ResourceResolver resolver,
			Resource configuration) {
		// Build Payload
		MessageCreationRequest request = new MessageCreationRequest();
		List<MediaUrl> assetList = new ArrayList<MediaUrl>();
		try {
			// Add Message Content.
			request.setText(asset.getMetadataValue("dc:description"));
			addAsset(resolver, configuration, assetList, asset);
			request.setMediaUrls(assetList);
			return request;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/**
	 * Builds the post message payload.
	 *
	 * @param page {@link Page} the page
	 * @param resolver {@link ResourceResolver} the resolver
	 * @param configuration {@link Resource} the configuration
	 * @return the message creation {@link Request} {@link MessageCreationRequest}
	 */
	private MessageCreationRequest buildPostMessagePayload(Page page, ResourceResolver resolver,
			Resource configuration) {
		// Build Payload
		MessageCreationRequest request = new MessageCreationRequest();
		List<MediaUrl> assetList = new ArrayList<MediaUrl>();
		try {
			// Add Tags.
			try {
				addTags(page, request);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			// Add Message Content.
			String[] supportedTypes = this.config.getWhitelistedResourceTypes();
			Resource containerResource = page.getContentResource().getChild("root");
			if (null != containerResource.getChild("responsivegrid")) {
				containerResource = containerResource.getChild("responsivegrid");
			}
			Iterator<Resource> resourceIterator = containerResource.listChildren();

			while (resourceIterator.hasNext()) {
				Resource resource = resourceIterator.next();
				String resourceType = resource.getResourceType();
				for (String supportedType : supportedTypes) {
					if (!StringUtils.startsWith(supportedType, resourceType)) {
						continue;
					} else if (StringUtils.startsWith(supportedType, resourceType)
							&& StringUtils.contains(supportedType, "text")) {
						request.setText(
								resource.getValueMap().get(StringUtils.split(supportedType, "|")[2], String.class)
										.replaceAll("\\<.*?\\>", ""));
					} else if (StringUtils.startsWith(supportedType, resourceType)
							&& StringUtils.contains(supportedType, "asset")) {
						try {
							Asset asset = resolver.getResource(
									resource.getValueMap().get(StringUtils.split(supportedType, "|")[2], String.class))
									.adaptTo(Asset.class);
							addAsset(resolver, configuration, assetList, asset);
						} catch (Exception e) {
							log.error(e.getMessage());
							continue;
						}
					}
				}
			}
			request.setMediaUrls(assetList);
			request.setEmailNotification(true);
			return request;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/**
	 * Adds the tags.
	 *
	 * @param page {@link Page} the page
	 * @param request {@link MessageCreationRequest} the request
	 */
	private void addTags(Page page, MessageCreationRequest request) {
		try {
			Tag[] tags = page.getTags();
			List<String> tagList = new ArrayList<String>();
			for (Tag tag : tags) {
				tagList.add(tag.getTitle());
			}
			request.setTags(tagList);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/**
	 * Adds the asset.
	 *
	 * @param resolver {@link ResourceResolver} the resolver
	 * @param configuration {@link Resource} the configuration
	 * @param assetList {@link List} the asset list
	 * @param asset {@link Asset} the asset
	 */
	private void addAsset(ResourceResolver resolver, Resource configuration, List<MediaUrl> assetList,
			Asset asset) {
		try {
			MediaUploadResponse mediaUploadResponse = this.mediaService.createAssetUploadUrl(asset, resolver,
					configuration);
			this.mediaService.uploadAsset(asset, mediaUploadResponse);

			MediaUrl url = new MediaUrl();
			url.setUrl(mediaUploadResponse.getData().getUploadUrl());

			// Check if the Asset is available.
			String status = "PENDING";
			while (!StringUtils.equalsIgnoreCase(status, "READY")) {
				MediaUploadResponse statusResponse = this.mediaService.checkAssetUploadStatus(mediaUploadResponse,
						resolver, configuration);
				status = statusResponse.getData().getAdditionalProperties().get("state").toString();
			}

			assetList.add(url);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
