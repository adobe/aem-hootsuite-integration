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

import java.util.List;

import org.apache.sling.api.resource.ResourceResolver;

import com.day.cq.search.result.Hit;

/**
 * 
 * Interface is to be implemented to provide Reporting services <br/>
 * around the content published from the AEM TO Hootsuite.
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
public interface ReportingService {
	
	/**
	 * Gets the published resources.
	 *
	 * @param resolver{@link ResourceResolver} the resolver
	 * @return the published resources{@link List}
	 */
	List<Hit> getPublishedResources(ResourceResolver resolver);

}
