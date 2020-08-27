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
package com.adobe.core.hootsuite.integration.external.ui.models;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.core.hootsuite.integration.external.services.ProfileService;
import com.adobe.core.hootsuite.integration.external.services.UtilityService;
import com.adobe.core.hootsuite.integration.external.services.beans.Profiles;
import com.adobe.core.hootsuite.integration.external.services.beans.SocialProfile;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ActionBarModel {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@Self
	private Resource currentResource;

	@OSGiService
	private ProfileService profileService;

	@OSGiService
	private UtilityService utilityService;

	private List<SocialProfile> profileList;

	@PostConstruct
	protected void postConstruct() {
		try {
			ResourceResolver resolver = this.currentResource.getResourceResolver();
			Resource pageResource = resolver.getResource(
					StringUtils.substringBefore(this.currentResource.getPath(), "/jcr:content") + "/jcr:content");

			ValueMap valueMap = pageResource.getValueMap();
			Profiles profiles = this.profileService.getSocialProfiles(StringUtils.substringBefore(this.currentResource.getPath(), "/jcr:content"));

			for (SocialProfile socialProfile : profiles.getData()) {
				if (valueMap.containsKey(socialProfile.getId())) {
					socialProfile.getAdditionalProperties().put("published", true);
					socialProfile.getAdditionalProperties().put("messageID", valueMap.get(socialProfile.getId()));
				}
			}
			this.profileList = profiles.getData();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public List<SocialProfile> getProfileList() {
		return profileList;
	}

}
