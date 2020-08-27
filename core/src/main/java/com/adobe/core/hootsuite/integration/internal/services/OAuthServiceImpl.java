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

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.framework.ServiceException;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.adobe.core.hootsuite.integration.external.constants.ErrorCodeEnum;
import com.adobe.core.hootsuite.integration.external.constants.GlobalConstants;
import com.adobe.core.hootsuite.integration.external.services.OAuthService;
import com.adobe.core.hootsuite.integration.external.services.beans.AuthTokenResponse;
import com.adobe.core.hootsuite.integration.external.services.exceptions.OAuthException;
import com.adobe.core.hootsuite.integration.exterrnal.exceptions.BaseException;
import com.adobe.granite.crypto.CryptoException;
import com.adobe.granite.crypto.CryptoSupport;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.webservicesupport.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component(immediate = true, service = OAuthService.class)
public class OAuthServiceImpl implements OAuthService {

	/** The crypto service. */
	@Reference
	private transient CryptoSupport cryptoService;

	/** The resolver factory. */
	@Reference
	private transient ResourceResolverFactory resolverFactory;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized AuthTokenResponse getRefreshToken(Configuration configuration) {
		try {
			if (null == configuration) {
				throw new BaseException("Unable to read the configuration", ErrorCodeEnum.ERR07.getErrorCode());
			}
			String clientID = this.cryptoService.unprotect(configuration.get("clientID", ""));
			String clientSecret = this.cryptoService.unprotect(configuration.get("clientSecret", ""));
			String oauthEndPoint = configuration.get("tokenEP", "");
			String grantType = configuration.get("grantType", "refresh_token");
			String refreshToken = configuration.get("refreshToken","");
			
			String response = Request.Post(oauthEndPoint)
					.setHeader(GlobalConstants.HEADER_CONTENT_TYPE, GlobalConstants.MIME_TYPE_URLENCODED)
					.setHeader(GlobalConstants.HEADER_ACCEPT, GlobalConstants.MIME_TYPE_JSON)
					.setHeader(GlobalConstants.HEADER_CACHE_CONTROL, "no-cache")
					.setHeader(GlobalConstants.HEADER_AUTHORIZATION,
							"Basic " + getAuthorizationHeader(clientID, clientSecret))
					.bodyString("grant_type="+grantType+"&refresh_token="+refreshToken,
							ContentType.APPLICATION_FORM_URLENCODED)
					.execute().returnContent().asString();

			ObjectMapper mapper = new ObjectMapper();
			AuthTokenResponse authTokenResponse = mapper.readValue(response,
					AuthTokenResponse.class);
			if(null == authTokenResponse || authTokenResponse.getAdditionalProperties().containsKey("error")) {
				throw new BaseException("Unable to get the access token", ErrorCodeEnum.ERR08.getErrorCode());
			}
			return authTokenResponse;
		} catch (BaseException e) {
			throw new OAuthException(e.getMessage(), e.getErrorCode());
		} catch (CryptoException e) {
			throw new OAuthException(e.getMessage(), ErrorCodeEnum.ERR04.getErrorCode());
		} catch (ClientProtocolException e) {
			throw new OAuthException(e.getMessage(), ErrorCodeEnum.ERR04.getErrorCode());
		} catch (IOException e) {
			throw new OAuthException(e.getMessage(), ErrorCodeEnum.ERR04.getErrorCode());
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized AuthTokenResponse getRefreshToken(Resource configuration) {
		try {
			if (null == configuration) {
				throw new BaseException("Unable to read the configuration", ErrorCodeEnum.ERR07.getErrorCode());
			}
			ValueMap valueMap = configuration.getValueMap();
			String clientID = valueMap.get("clientId", "");
			String clientSecret = valueMap.get("clientSecret", "");
			String oauthEndPoint = valueMap.get("endpointHost", "");
			String grantType = "refresh_token";
			String refreshToken = valueMap.get("refreshToken","");
			
			String response = Request.Post(oauthEndPoint)
					.setHeader(GlobalConstants.HEADER_CONTENT_TYPE, GlobalConstants.MIME_TYPE_URLENCODED)
					.setHeader(GlobalConstants.HEADER_ACCEPT, GlobalConstants.MIME_TYPE_JSON)
					.setHeader(GlobalConstants.HEADER_CACHE_CONTROL, "no-cache")
					.setHeader(GlobalConstants.HEADER_AUTHORIZATION,
							"Basic " + getAuthorizationHeader(clientID, clientSecret))
					.bodyString("grant_type="+grantType+"&refresh_token="+refreshToken,
							ContentType.APPLICATION_FORM_URLENCODED)
					.execute().returnContent().asString();

			ObjectMapper mapper = new ObjectMapper();
			AuthTokenResponse authTokenResponse = mapper.readValue(response,
					AuthTokenResponse.class);
			if(null == authTokenResponse || authTokenResponse.getAdditionalProperties().containsKey("error")) {
				throw new BaseException("Unable to get the access token", ErrorCodeEnum.ERR08.getErrorCode());
			}
			return authTokenResponse;
		} catch (BaseException e) {
			throw new OAuthException(e.getMessage(), e.getErrorCode());
		} catch (ClientProtocolException e) {
			throw new OAuthException(e.getMessage(), ErrorCodeEnum.ERR04.getErrorCode());
		} catch (IOException e) {
			throw new OAuthException(e.getMessage(), ErrorCodeEnum.ERR04.getErrorCode());
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuthTokenResponse getAccessToken(String authCode, Page page) {
		try {
			if (StringUtils.isEmpty(authCode) || null == page) {
				throw new ServiceException("authToken or configuration object is missing");
			}
			ValueMap valueMap = page.getContentResource().getValueMap();

			String response = Request.Post(valueMap.get("endpointHost", String.class))
					.setHeader(GlobalConstants.HEADER_CONTENT_TYPE, GlobalConstants.MIME_TYPE_URLENCODED)
					.setHeader(GlobalConstants.HEADER_ACCEPT, GlobalConstants.MIME_TYPE_JSON)
					.setHeader(GlobalConstants.HEADER_CACHE_CONTROL, "no-cache")
					.setHeader(GlobalConstants.HEADER_AUTHORIZATION,
							"Basic " + getAuthorizationHeader(valueMap.get("clientId", String.class),
									valueMap.get("clientSecret", String.class)))
					.bodyString("grant_type=authorization_code&scope=" + valueMap.get("scope", String.class)
							+ "&redirect_uri=" + valueMap.get("redirectUrl", String.class) + "&code=" + authCode,
							ContentType.APPLICATION_FORM_URLENCODED)
					.execute().returnContent().asString();

			ObjectMapper mapper = new ObjectMapper();
			AuthTokenResponse authTokenResponse = mapper.readValue(response, AuthTokenResponse.class);
			if (null == authTokenResponse || authTokenResponse.getAdditionalProperties().containsKey("error")) {
				throw new BaseException("Unable to get the access token", ErrorCodeEnum.ERR08.getErrorCode());
			}
			return authTokenResponse;
		} catch (Exception e) {
			throw new OAuthException(e.getMessage(), ErrorCodeEnum.ERR04.getErrorCode());
		}
	}
	
	/**
	 * Gets the authorization header.
	 *
	 * @param userID{@link String} the user ID
	 * @param password{@link String} the password
	 * @return the authorization header{@link String}
	 */
	private static String getAuthorizationHeader(String userID, String password) {
		StringBuilder authBuilder = new StringBuilder();
		authBuilder.append(userID);
		authBuilder.append(GlobalConstants.COLON);
		authBuilder.append(password);
		return new String(Base64.encodeBase64(authBuilder.toString().getBytes()));
	}
	
}
