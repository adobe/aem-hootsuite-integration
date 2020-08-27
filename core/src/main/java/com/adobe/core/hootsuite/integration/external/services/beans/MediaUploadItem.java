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

// TODO: Auto-generated Javadoc
/**
 * The Class MediaUploadItem.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MediaUploadItem {

	/** The upload url. */
	@JsonProperty("uploadUrl")
	private String uploadUrl;
	
	/** The upload url duration seconds. */
	@JsonProperty("uploadUrlDurationSeconds")
	private Integer uploadUrlDurationSeconds;
	
	/** The id. */
	@JsonProperty("id")
	private String id;
	
	/** The additional properties. */
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * Gets the upload url.
	 *
	 * @return the upload url
	 */
	@JsonProperty("uploadUrl")
	public String getUploadUrl() {
		return uploadUrl;
	}

	/**
	 * Sets the upload url.
	 *
	 * @param uploadUrl the new upload url
	 */
	@JsonProperty("uploadUrl")
	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}

	/**
	 * Gets the upload url duration seconds.
	 *
	 * @return the upload url duration seconds
	 */
	@JsonProperty("uploadUrlDurationSeconds")
	public Integer getUploadUrlDurationSeconds() {
		return uploadUrlDurationSeconds;
	}

	/**
	 * Sets the upload url duration seconds.
	 *
	 * @param uploadUrlDurationSeconds the new upload url duration seconds
	 */
	@JsonProperty("uploadUrlDurationSeconds")
	public void setUploadUrlDurationSeconds(Integer uploadUrlDurationSeconds) {
		this.uploadUrlDurationSeconds = uploadUrlDurationSeconds;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@JsonProperty("id")
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
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