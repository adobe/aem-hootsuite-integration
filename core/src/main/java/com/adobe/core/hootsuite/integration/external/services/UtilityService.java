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

import com.adobe.core.hootsuite.integration.external.services.beans.AuthTokenResponse;

/**
 * 
 * Interface is to be implemented to provide Common Ultity method(s) <br/>
 * to be used throughout the application.
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
public interface UtilityService {

	/**
	 * Find configuration in hierarchy.
	 *
	 * @param resource{@link Resource} the resource
	 * @param templateName{@link String} the template name
	 * @return the resource{@link Resource}
	 */
	Resource findConfigurationInHierarchy(Resource resource, String templateName);

	/**
	 * Find configuration.
	 *
	 * @param resource{@link Resource} the resource
	 * @param templateName{@link String} the template name
	 * @return the resource{@link Resource}
	 */
	Resource findConfiguration(Resource resource, String templateName);

	/**
	 * Update configuration.
	 *
	 * @param resource{@link Resource} the resource
	 * @param resolver{@link ResourceResolver} the resolver
	 * @param authTokenResponse{@link AuthTokenResponse} the auth token response
	 */
	void updateConfiguration(Resource resource, ResourceResolver resolver, AuthTokenResponse authTokenResponse);

}
