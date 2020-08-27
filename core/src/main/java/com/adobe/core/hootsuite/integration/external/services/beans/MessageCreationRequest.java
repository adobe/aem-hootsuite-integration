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
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * A simple POJO for marshalling/unmarshalling Social Post Creation Request<br/>
 * for Hootsuite.
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
public class MessageCreationRequest {

	/** The text. */
	@JsonProperty("text")
	private String text;

	/** The social profile ids. */
	@JsonProperty("socialProfileIds")
	private List<String> socialProfileIds = null;

	/** The scheduled send time. */
	@JsonProperty("scheduledSendTime")
	private String scheduledSendTime;

	/** The tags. */
	@JsonProperty("tags")
	private List<String> tags = null;

	/** The email notification. */
	@JsonProperty("emailNotification")
	private Boolean emailNotification;

	/** The media urls. */
	@JsonProperty("mediaUrls")
	private List<MediaUrl> mediaUrls = null;

	/** The additional properties. */
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	@JsonProperty("text")
	public String getText() {
		return text;
	}

	/**
	 * Sets the text.
	 *
	 * @param text the new text
	 */
	@JsonProperty("text")
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Gets the social profile ids.
	 *
	 * @return the social profile ids
	 */
	@JsonProperty("socialProfileIds")
	public List<String> getSocialProfileIds() {
		return socialProfileIds;
	}

	/**
	 * Sets the social profile ids.
	 *
	 * @param socialProfileIds the new social profile ids
	 */
	@JsonProperty("socialProfileIds")
	public void setSocialProfileIds(List<String> socialProfileIds) {
		this.socialProfileIds = socialProfileIds;
	}

	/**
	 * Gets the scheduled send time.
	 *
	 * @return the scheduled send time
	 */
	@JsonProperty("scheduledSendTime")
	public String getScheduledSendTime() {
		return scheduledSendTime;
	}

	/**
	 * Sets the scheduled send time.
	 *
	 * @param scheduledSendTime the new scheduled send time
	 */
	@JsonProperty("scheduledSendTime")
	public void setScheduledSendTime(String scheduledSendTime) {
		this.scheduledSendTime = scheduledSendTime;
	}

	/**
	 * Gets the tags.
	 *
	 * @return the tags
	 */
	@JsonProperty("tags")
	public List<String> getTags() {
		return tags;
	}

	/**
	 * Sets the tags.
	 *
	 * @param tags the new tags
	 */
	@JsonProperty("tags")
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	/**
	 * Gets the email notification.
	 *
	 * @return the email notification
	 */
	@JsonProperty("emailNotification")
	public Boolean getEmailNotification() {
		return emailNotification;
	}

	/**
	 * Sets the email notification.
	 *
	 * @param emailNotification the new email notification
	 */
	@JsonProperty("emailNotification")
	public void setEmailNotification(Boolean emailNotification) {
		this.emailNotification = emailNotification;
	}

	/**
	 * Gets the media urls.
	 *
	 * @return the media urls
	 */
	@JsonProperty("mediaUrls")
	public List<MediaUrl> getMediaUrls() {
		return mediaUrls;
	}

	/**
	 * Sets the media urls.
	 *
	 * @param mediaUrls the new media urls
	 */
	@JsonProperty("mediaUrls")
	public void setMediaUrls(List<MediaUrl> mediaUrls) {
		this.mediaUrls = mediaUrls;
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
	 * @param name  the name
	 * @param value the value
	 */
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}