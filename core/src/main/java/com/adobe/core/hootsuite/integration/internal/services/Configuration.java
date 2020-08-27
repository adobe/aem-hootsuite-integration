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

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Hootsuite - Content Configuration")
public @interface Configuration {

	/**
	 * Gets the whitelisted resource types.
	 *
	 * @return the whitelisted resource types
	 */
	@AttributeDefinition(name = "WHITELISTED_RESOURCE_TYPES", description = "WhiteListed Resource Types", type = AttributeType.STRING)
	String[] getWhitelistedResourceTypes() default "<RESOURCE_TYPE>|<CONTENT_TYPE_IMAGE_TEXT>|<PROPERTY_NAME>";

}