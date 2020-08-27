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
package com.adobe.core.hootsuite.integration.external.services.beans;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * A simple POJO for marshalling/unmarshalling Access/Refresh Token Response<br/>
 * from Hootsuite.
 *
 * @version 1.0
 * @since 2020.04.15
 * 
 * ******************************************************************************
 *          				Version History
 * ******************************************************************************
 * 
 * 				1.0 						Initial Version 						
 * 
 * *******************************************************************************
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthTokenResponse {

	/** The access token. */
	@JsonProperty("access_token")
	private String accessToken;
	
	/** The expires in. */
	@JsonProperty("expires_in")
	private Integer expiresIn;
	
	/** The refresh token. */
	@JsonProperty("refresh_token")
	private String refreshToken;
	
	/** The scope. */
	@JsonProperty("scope")
	private String scope;
	
	/** The token type. */
	@JsonProperty("token_type")
	private String tokenType;
	
	/** The additional properties. */
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * Gets the access token.
	 *
	 * @return the access token
	 */
	@JsonProperty("access_token")
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * Sets the access token.
	 *
	 * @param accessToken the new access token
	 */
	@JsonProperty("access_token")
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * Gets the expires in.
	 *
	 * @return the expires in
	 */
	@JsonProperty("expires_in")
	public Integer getExpiresIn() {
		return expiresIn;
	}

	/**
	 * Sets the expires in.
	 *
	 * @param expiresIn the new expires in
	 */
	@JsonProperty("expires_in")
	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	/**
	 * Gets the refresh token.
	 *
	 * @return the refresh token
	 */
	@JsonProperty("refresh_token")
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * Sets the refresh token.
	 *
	 * @param refreshToken the new refresh token
	 */
	@JsonProperty("refresh_token")
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	/**
	 * Gets the scope.
	 *
	 * @return the scope
	 */
	@JsonProperty("scope")
	public String getScope() {
		return scope;
	}

	/**
	 * Sets the scope.
	 *
	 * @param scope the new scope
	 */
	@JsonProperty("scope")
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 * Gets the token type.
	 *
	 * @return the token type
	 */
	@JsonProperty("token_type")
	public String getTokenType() {
		return tokenType;
	}

	/**
	 * Sets the token type.
	 *
	 * @param tokenType the new token type
	 */
	@JsonProperty("token_type")
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	/**
	 * Gets the additional properties.
	 *
	 * @return the additional properties
	 */
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	/**
	 * Sets the additional property.
	 *
	 * @param name the name
	 * @param value the value
	 */
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}