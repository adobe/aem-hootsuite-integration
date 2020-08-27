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

import java.util.Iterator;

import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.framework.ServiceException;
import org.osgi.service.component.annotations.Component;

import com.adobe.core.hootsuite.integration.external.services.UtilityService;
import com.adobe.core.hootsuite.integration.external.services.beans.AuthTokenResponse;
import com.day.cq.commons.inherit.HierarchyNodeInheritanceValueMap;

@Component(immediate = true, service = UtilityService.class)
public class UtilityServiceImpl implements UtilityService {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Resource findConfigurationInHierarchy(Resource resource,
			String templateName) {
		try {
			HierarchyNodeInheritanceValueMap pageProperties = new HierarchyNodeInheritanceValueMap(resource);
			String confRoot = pageProperties.getInherited("cq:conf", String.class);
			Resource confResource = resource.getResourceResolver().getResource(confRoot);
			if(null == confResource || null == confResource.getChild("settings/cloudconfigs")) {
				throw new ServiceException("No Cloud Configuration(s) found");
			}
			Iterator<Resource> allServices = confResource.getChild("settings/cloudconfigs").listChildren();
			while (allServices.hasNext()) {
				Resource configResource = allServices.next();
				if(!(configResource.getChild("jcr:content")).isResourceType(templateName)) {
					continue;
				}
				return configResource.getChild("jcr:content");
				
			}
			throw new ServiceException("No Cloud Configuration(s) found");
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Resource findConfiguration(Resource resource,
			String templateName) {
		try {
			ValueMap valueMap = resource.getValueMap();
			if(!valueMap.containsKey("cq:conf")) {
				return findConfiguration(resource.getParent(), templateName);
			}
			String confRoot = valueMap.get("cq:conf", String.class);
			Resource confResource = resource.getResourceResolver().getResource(confRoot);
			if(null == confResource || null == confResource.getChild("settings/cloudconfigs")) {
				throw new ServiceException("No Cloud Configuration(s) found");
			}
			Iterator<Resource> allServices = confResource.getChild("settings/cloudconfigs").listChildren();
			while (allServices.hasNext()) {
				Resource configResource = allServices.next();
				if(!(configResource.getChild("jcr:content")).isResourceType(templateName)) {
					continue;
				}
				return configResource.getChild("jcr:content");
				
			}
			throw new ServiceException("No Cloud Configuration(s) found");
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateConfiguration(Resource resource, ResourceResolver resolver, AuthTokenResponse authTokenResponse) {
		try {
			ModifiableValueMap valueMap = resource.adaptTo(ModifiableValueMap.class);
			valueMap.put("accessToken", authTokenResponse.getAccessToken());
			valueMap.put("refreshToken", authTokenResponse.getRefreshToken());
			if(resolver.hasChanges()) {
				resolver.commit();
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
		
	}

}
