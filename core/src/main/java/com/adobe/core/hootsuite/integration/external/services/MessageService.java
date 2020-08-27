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

import org.apache.sling.api.resource.ResourceResolver;

import com.adobe.core.hootsuite.integration.external.services.beans.MessageCreationResponse;
import com.day.cq.dam.api.Asset;
import com.day.cq.wcm.api.Page;

/**
 * 
 * Interface is to be implemented to provide payload creation service <br/>
 * for posting social media content through Hoostsuite.
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
public interface MessageService {
	
	/**
	 * Creates the social post.
	 *
	 * @param page{@link Page} the page
	 * @param resolver{{@link ResourceResolver} the resolver
	 * @param socialMediaID{@link String}} the social media ID
	 * @return the message creation response{@link MessageCreationResponse}
	 */
	MessageCreationResponse createSocialPost(Page page, ResourceResolver resolver, String socialMediaID);
	
	/**
	 * Creates the social post.
	 *
	 * @param asset{@link Asset} the asset
	 * @param resolver{@link ResourceResolver} the resolver
	 * @param socialMediaID{@link String} the social media ID
	 * @return the message creation response{@link MessageCreationResponse}
	 */
	MessageCreationResponse createSocialPost(Asset asset, ResourceResolver resolver, String socialMediaID);
}
