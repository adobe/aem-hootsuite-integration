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
 * A simple POJO for marshalling/unmarshalling Social Post Response details<br/>
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
public class Datum {

	/** The id. */
	@JsonProperty("id")
	private String id;

	/** The state. */
	@JsonProperty("state")
	private String state;

	/** The text. */
	@JsonProperty("text")
	private String text;

	/** The scheduled send time. */
	@JsonProperty("scheduledSendTime")
	private Object scheduledSendTime;

	/** The social profile. */
	@JsonProperty("socialProfile")
	private SocialProfile socialProfile;

	/** The media urls. */
	@JsonProperty("mediaUrls")
	private List<Object> mediaUrls = null;

	/** The media. */
	@JsonProperty("media")
	private List<Object> media = null;

	/** The webhook urls. */
	@JsonProperty("webhookUrls")
	private Object webhookUrls;

	/** The tags. */
	@JsonProperty("tags")
	private Object tags;

	/** The targeting. */
	@JsonProperty("targeting")
	private Object targeting;

	/** The privacy. */
	@JsonProperty("privacy")
	private Object privacy;

	/** The location. */
	@JsonProperty("location")
	private Object location;

	/** The email notification. */
	@JsonProperty("emailNotification")
	private Boolean emailNotification;

	/** The post url. */
	@JsonProperty("postUrl")
	private String postUrl;

	/** The post id. */
	@JsonProperty("postId")
	private String postId;

	/** The reviewers. */
	@JsonProperty("reviewers")
	private Object reviewers;

	/** The created by member. */
	@JsonProperty("createdByMember")
	private Object createdByMember;

	/** The last updated by member. */
	@JsonProperty("lastUpdatedByMember")
	private Object lastUpdatedByMember;

	/** The extended info. */
	@JsonProperty("extendedInfo")
	private Object extendedInfo;

	/** The sequence number. */
	@JsonProperty("sequenceNumber")
	private Object sequenceNumber;

	/** The additional properties. */
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
	 * Gets the state.
	 *
	 * @return the state
	 */
	@JsonProperty("state")
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	@JsonProperty("state")
	public void setState(String state) {
		this.state = state;
	}

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
	 * Gets the scheduled send time.
	 *
	 * @return the scheduled send time
	 */
	@JsonProperty("scheduledSendTime")
	public Object getScheduledSendTime() {
		return scheduledSendTime;
	}

	/**
	 * Sets the scheduled send time.
	 *
	 * @param scheduledSendTime the new scheduled send time
	 */
	@JsonProperty("scheduledSendTime")
	public void setScheduledSendTime(Object scheduledSendTime) {
		this.scheduledSendTime = scheduledSendTime;
	}

	/**
	 * Gets the social profile.
	 *
	 * @return the social profile
	 */
	@JsonProperty("socialProfile")
	public SocialProfile getSocialProfile() {
		return socialProfile;
	}

	/**
	 * Sets the social profile.
	 *
	 * @param socialProfile the new social profile
	 */
	@JsonProperty("socialProfile")
	public void setSocialProfile(SocialProfile socialProfile) {
		this.socialProfile = socialProfile;
	}

	/**
	 * Gets the media urls.
	 *
	 * @return the media urls
	 */
	@JsonProperty("mediaUrls")
	public List<Object> getMediaUrls() {
		return mediaUrls;
	}

	/**
	 * Sets the media urls.
	 *
	 * @param mediaUrls the new media urls
	 */
	@JsonProperty("mediaUrls")
	public void setMediaUrls(List<Object> mediaUrls) {
		this.mediaUrls = mediaUrls;
	}

	/**
	 * Gets the media.
	 *
	 * @return the media
	 */
	@JsonProperty("media")
	public List<Object> getMedia() {
		return media;
	}

	/**
	 * Sets the media.
	 *
	 * @param media the new media
	 */
	@JsonProperty("media")
	public void setMedia(List<Object> media) {
		this.media = media;
	}

	/**
	 * Gets the webhook urls.
	 *
	 * @return the webhook urls
	 */
	@JsonProperty("webhookUrls")
	public Object getWebhookUrls() {
		return webhookUrls;
	}

	/**
	 * Sets the webhook urls.
	 *
	 * @param webhookUrls the new webhook urls
	 */
	@JsonProperty("webhookUrls")
	public void setWebhookUrls(Object webhookUrls) {
		this.webhookUrls = webhookUrls;
	}

	/**
	 * Gets the tags.
	 *
	 * @return the tags
	 */
	@JsonProperty("tags")
	public Object getTags() {
		return tags;
	}

	/**
	 * Sets the tags.
	 *
	 * @param tags the new tags
	 */
	@JsonProperty("tags")
	public void setTags(Object tags) {
		this.tags = tags;
	}

	/**
	 * Gets the targeting.
	 *
	 * @return the targeting
	 */
	@JsonProperty("targeting")
	public Object getTargeting() {
		return targeting;
	}

	/**
	 * Sets the targeting.
	 *
	 * @param targeting the new targeting
	 */
	@JsonProperty("targeting")
	public void setTargeting(Object targeting) {
		this.targeting = targeting;
	}

	/**
	 * Gets the privacy.
	 *
	 * @return the privacy
	 */
	@JsonProperty("privacy")
	public Object getPrivacy() {
		return privacy;
	}

	/**
	 * Sets the privacy.
	 *
	 * @param privacy the new privacy
	 */
	@JsonProperty("privacy")
	public void setPrivacy(Object privacy) {
		this.privacy = privacy;
	}

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	@JsonProperty("location")
	public Object getLocation() {
		return location;
	}

	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	@JsonProperty("location")
	public void setLocation(Object location) {
		this.location = location;
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
	 * Gets the post url.
	 *
	 * @return the post url
	 */
	@JsonProperty("postUrl")
	public String getPostUrl() {
		return postUrl;
	}

	/**
	 * Sets the post url.
	 *
	 * @param postUrl the new post url
	 */
	@JsonProperty("postUrl")
	public void setPostUrl(String postUrl) {
		this.postUrl = postUrl;
	}

	/**
	 * Gets the post id.
	 *
	 * @return the post id
	 */
	@JsonProperty("postId")
	public String getPostId() {
		return postId;
	}

	/**
	 * Sets the post id.
	 *
	 * @param postId the new post id
	 */
	@JsonProperty("postId")
	public void setPostId(String postId) {
		this.postId = postId;
	}

	/**
	 * Gets the reviewers.
	 *
	 * @return the reviewers
	 */
	@JsonProperty("reviewers")
	public Object getReviewers() {
		return reviewers;
	}

	/**
	 * Sets the reviewers.
	 *
	 * @param reviewers the new reviewers
	 */
	@JsonProperty("reviewers")
	public void setReviewers(Object reviewers) {
		this.reviewers = reviewers;
	}

	/**
	 * Gets the created by member.
	 *
	 * @return the created by member
	 */
	@JsonProperty("createdByMember")
	public Object getCreatedByMember() {
		return createdByMember;
	}

	/**
	 * Sets the created by member.
	 *
	 * @param createdByMember the new created by member
	 */
	@JsonProperty("createdByMember")
	public void setCreatedByMember(Object createdByMember) {
		this.createdByMember = createdByMember;
	}

	/**
	 * Gets the last updated by member.
	 *
	 * @return the last updated by member
	 */
	@JsonProperty("lastUpdatedByMember")
	public Object getLastUpdatedByMember() {
		return lastUpdatedByMember;
	}

	/**
	 * Sets the last updated by member.
	 *
	 * @param lastUpdatedByMember the new last updated by member
	 */
	@JsonProperty("lastUpdatedByMember")
	public void setLastUpdatedByMember(Object lastUpdatedByMember) {
		this.lastUpdatedByMember = lastUpdatedByMember;
	}

	/**
	 * Gets the extended info.
	 *
	 * @return the extended info
	 */
	@JsonProperty("extendedInfo")
	public Object getExtendedInfo() {
		return extendedInfo;
	}

	/**
	 * Sets the extended info.
	 *
	 * @param extendedInfo the new extended info
	 */
	@JsonProperty("extendedInfo")
	public void setExtendedInfo(Object extendedInfo) {
		this.extendedInfo = extendedInfo;
	}

	/**
	 * Gets the sequence number.
	 *
	 * @return the sequence number
	 */
	@JsonProperty("sequenceNumber")
	public Object getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * Sets the sequence number.
	 *
	 * @param sequenceNumber the new sequence number
	 */
	@JsonProperty("sequenceNumber")
	public void setSequenceNumber(Object sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
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