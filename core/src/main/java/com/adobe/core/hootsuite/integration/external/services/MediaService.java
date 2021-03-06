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
import org.apache.sling.api.resource.ResourceResolver;

import com.adobe.core.hootsuite.integration.external.services.beans.MediaUploadResponse;
import com.day.cq.dam.api.Asset;

/**
 * 
 * Interface is to be implemented to provide media assets related interfacing with<br/>
 * the Hootsuite Media Hosting Service.
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
public interface MediaService {


	/**
	 * Upload asset to Hootsuite Media Hosting service.
	 *
	 * @param asset{@link Asset} the asset
	 * @param mediaUploadResponse{@link MediaUploadResponse} the media upload response
	 */
	void uploadAsset(Asset asset, MediaUploadResponse mediaUploadResponse);

	/**
	 * Check asset upload status.
	 *
	 * @param mediaUploadResponse{@link MediaUploadResponse} the media upload response
	 * @param resolver{@link ResourceResolver} the resolver
	 * @param config{@link Resource} the config
	 * @return the media upload response{@link MediaUploadResponse}
	 */
	MediaUploadResponse checkAssetUploadStatus(MediaUploadResponse mediaUploadResponse, ResourceResolver resolver, Resource config);

	/**
	 * Creates the asset upload url.
	 *
	 * @param asset{@link Asset} the asset
	 * @param resolver{@link ResourceResolver} the resolver
	 * @param config{@link Resource} the config
	 * @return the media upload response {@link MediaUploadResponse}
	 */
	MediaUploadResponse createAssetUploadUrl(Asset asset, ResourceResolver resolver, Resource config);

}
