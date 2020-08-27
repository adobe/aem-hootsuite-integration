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

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.framework.ServiceException;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.adobe.core.hootsuite.integration.external.constants.GlobalConstants;
import com.adobe.core.hootsuite.integration.external.services.MediaService;
import com.adobe.core.hootsuite.integration.external.services.OAuthService;
import com.adobe.core.hootsuite.integration.external.services.UtilityService;
import com.adobe.core.hootsuite.integration.external.services.beans.MediaUploadRequest;
import com.adobe.core.hootsuite.integration.external.services.beans.MediaUploadResponse;
import com.adobe.core.hootsuite.integration.external.services.exceptions.OAuthException;
import com.adobe.core.hootsuite.integration.exterrnal.exceptions.BaseException;
import com.day.cq.dam.api.Asset;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component(immediate = true, service = MediaService.class)
public class MediaServiceImpl implements MediaService {
	
	@Reference
	private transient UtilityService utilityService;
	
	@Reference
	private transient OAuthService oAuthService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MediaUploadResponse createAssetUploadUrl(Asset asset, ResourceResolver resolver, Resource config) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			MediaUploadRequest request = new MediaUploadRequest(Long.parseLong(asset.getMetadataValue("dam:size")), asset.getMimeType());
			String response = Request.Post("https://platform.hootsuite.com/v1/media")
					.setHeader(GlobalConstants.HEADER_CONTENT_TYPE,ContentType.APPLICATION_JSON.getMimeType())
					.setHeader(GlobalConstants.HEADER_ACCEPT, GlobalConstants.MIME_TYPE_JSON)
					.setHeader(GlobalConstants.HEADER_CACHE_CONTROL, "no-cache")
					.setHeader(GlobalConstants.HEADER_AUTHORIZATION,
							"Bearer " + config.getValueMap().get("accessToken", ""))
					.bodyString(mapper.writeValueAsString(request),
							ContentType.APPLICATION_JSON)
					.execute().returnContent().asString();
			MediaUploadResponse uploadResponse = mapper.readValue(response, MediaUploadResponse.class);
			if(uploadResponse.getAdditionalProperties().containsKey("error") && StringUtils.equalsIgnoreCase(uploadResponse.getAdditionalProperties().get("error").toString(), "request_forbidden")) {
				throw new OAuthException("request_forbidden", "ERR401");
			}else if(uploadResponse.getAdditionalProperties().containsKey("error")) {
				
			}
			return uploadResponse;
		} catch (OAuthException   | HttpResponseException e) {
			this.utilityService.updateConfiguration(config, resolver, this.oAuthService.getRefreshToken(config));
			return createAssetUploadUrl(asset, resolver, config);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		} 
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void uploadAsset(Asset asset, MediaUploadResponse mediaUploadResponse) {
		InputStream inputStream = null;
		try {
			inputStream = asset.getOriginal().getStream();
			HttpResponse response = Request.Put(mediaUploadResponse.getData().getUploadUrl())
					.setHeader(GlobalConstants.HEADER_CONTENT_TYPE,asset.getOriginal().getMimeType())
					.bodyByteArray(IOUtils.toByteArray(inputStream))
					.execute().returnResponse();
			if(response.getStatusLine().getStatusCode() != 200) {
				throw new BaseException("unable to upload the asset", "MEDIA102");
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}finally {
			IOUtils.closeQuietly(inputStream);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MediaUploadResponse checkAssetUploadStatus(MediaUploadResponse mediaUploadResponse, ResourceResolver resolver,Resource config) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String response = Request
					.Get("https://platform.hootsuite.com/v1/media/" + mediaUploadResponse.getData().getId())
					.setHeader(GlobalConstants.HEADER_CONTENT_TYPE,ContentType.APPLICATION_JSON.getMimeType())
					.setHeader(GlobalConstants.HEADER_ACCEPT, GlobalConstants.MIME_TYPE_JSON)
					.setHeader(GlobalConstants.HEADER_CACHE_CONTROL, "no-cache")
					.setHeader(GlobalConstants.HEADER_AUTHORIZATION,
							"Bearer " +config.getValueMap().get("accessToken", ""))
					.execute().returnContent().asString();
			MediaUploadResponse uploadResponse = mapper.readValue(response, MediaUploadResponse.class);
			if(uploadResponse.getAdditionalProperties().containsKey("error") && StringUtils.equalsIgnoreCase(uploadResponse.getAdditionalProperties().get("error").toString(), "request_forbidden")) {
				throw new OAuthException("request_forbidden", "ERR401");
			}else if(uploadResponse.getAdditionalProperties().containsKey("error")) {
				
			}
			return uploadResponse;
		} catch (OAuthException | HttpResponseException e) {
			this.utilityService.updateConfiguration(config, resolver, this.oAuthService.getRefreshToken(config));
			return checkAssetUploadStatus(mediaUploadResponse, resolver,config);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		} 

	}
	
}
