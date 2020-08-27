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

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.framework.ServiceException;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.adobe.core.hootsuite.integration.external.constants.GlobalConstants;
import com.adobe.core.hootsuite.integration.external.services.OAuthService;
import com.adobe.core.hootsuite.integration.external.services.ProfileService;
import com.adobe.core.hootsuite.integration.external.services.UtilityService;
import com.adobe.core.hootsuite.integration.external.services.beans.Profiles;
import com.adobe.core.hootsuite.integration.external.services.exceptions.OAuthException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component(immediate = true, service = ProfileService.class)
public class ProfileServiceImpl implements ProfileService {

	@Reference
	private transient UtilityService utilityService;

	@Reference
	private transient OAuthService oauthService;

	@Reference
	private ResourceResolverFactory resolverFactory;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Profiles getSocialProfiles(Resource configuration, ResourceResolver resolver) {
		try {
			ObjectMapper mapper = new ObjectMapper();

			String response = Request.Get("https://platform.hootsuite.com/v1/socialProfiles")
					.setHeader(GlobalConstants.HEADER_CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
					.setHeader(GlobalConstants.HEADER_ACCEPT, GlobalConstants.MIME_TYPE_JSON)
					.setHeader(GlobalConstants.HEADER_CACHE_CONTROL, "no-cache")
					.setHeader(GlobalConstants.HEADER_AUTHORIZATION, "Bearer " + configuration.getValueMap().get("accessToken", ""))

					.execute().returnContent().asString();

			Profiles profileResponse = mapper.readValue(response, Profiles.class);

			if (profileResponse.getAdditionalProperties().containsKey("error") && StringUtils.equalsIgnoreCase(
					profileResponse.getAdditionalProperties().get("error").toString(), "request_forbidden")) {
				throw new OAuthException("request_forbidden", "ERR401");
			} else if (profileResponse.getAdditionalProperties().containsKey("error")) {

			}
			return profileResponse;
		} catch (OAuthException | HttpResponseException e) {
			this.utilityService.updateConfiguration(configuration, resolver,
					this.oauthService.getRefreshToken(configuration));
			return getSocialProfiles(configuration, resolver);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Profiles getSocialProfiles(String pagePath) {
		ResourceResolver resolver = null;
		Resource configResource = null;
		ValueMap valueMap = null;
		try {
			ObjectMapper mapper = new ObjectMapper();

			Map<String, Object> param = new HashMap<String, Object>();
			param.put(ResourceResolverFactory.SUBSERVICE, "write-service");
			resolver = this.resolverFactory.getServiceResourceResolver(param);

			configResource = this.utilityService.findConfiguration(
					resolver.getResource(pagePath), "hootsuite/components/admin/hootsuite");
			valueMap = configResource.getValueMap();
			String response = Request.Get("https://platform.hootsuite.com/v1/socialProfiles")
					.setHeader(GlobalConstants.HEADER_CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())
					.setHeader(GlobalConstants.HEADER_ACCEPT, GlobalConstants.MIME_TYPE_JSON)
					.setHeader(GlobalConstants.HEADER_CACHE_CONTROL, "no-cache")
					.setHeader(GlobalConstants.HEADER_AUTHORIZATION, "Bearer " + valueMap.get("accessToken", String.class))

					.execute().returnContent().asString();

			Profiles profileResponse = mapper.readValue(response, Profiles.class);

			if (profileResponse.getAdditionalProperties().containsKey("error") && StringUtils.equalsIgnoreCase(
					profileResponse.getAdditionalProperties().get("error").toString(), "request_forbidden")) {
				throw new OAuthException("request_forbidden", "ERR401");
			} else if (profileResponse.getAdditionalProperties().containsKey("error")) {

			}
			return profileResponse;
		} catch (OAuthException | HttpResponseException e) {
			this.utilityService.updateConfiguration(configResource, resolver,
					this.oauthService.getRefreshToken(configResource));
			return getSocialProfiles(pagePath);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		} finally {
			if (null != resolver) {
				resolver.close();
			}
		}
	}
}
