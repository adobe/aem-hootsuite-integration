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
package com.adobe.core.hootsuite.integration.external.services;

import org.apache.sling.api.resource.Resource;

import com.adobe.core.hootsuite.integration.external.services.beans.AuthTokenResponse;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.webservicesupport.Configuration;

/**
 * 
 * This service interface defines a contract for OAuth server integration. <br/>
 * Contract defines the following functions;
 * <ul>
 * 		<li>Generates the OAuth Refresh Token</li>
 * </ul>
 * 
 *
 * @since 2020.04.15
 * @version 1.0
 * 
 *******************************************************************************
 *          				Version History
 *******************************************************************************
 * 
 *          	1.0 							Initial Version 			
 ********************************************************************************
 */
public interface OAuthService {


	/**
	 * Generates the OAuth Access Token.
	 *
	 * @param configuration{@link Configuration} the configuration
	 * @return the access token{@link AuthTokenResponse}
	 */
	AuthTokenResponse getRefreshToken(Configuration configuration);

	/**
	 * Generates the OAuth Access Token.
	 *
	 * @param authCode{@link String} the auth code
	 * @param page{@link Page} the page
	 * @return the access token{@link AuthTokenResponse}
	 */
	AuthTokenResponse getAccessToken(String authCode, Page page);

	/**
	 * Generates the OAuth Access Token.
	 *
	 * @param configuration{@link Resource} the configuration
	 * @return the access token{@link AuthTokenResponse}
	 */
	AuthTokenResponse getRefreshToken(Resource configuration);
}
