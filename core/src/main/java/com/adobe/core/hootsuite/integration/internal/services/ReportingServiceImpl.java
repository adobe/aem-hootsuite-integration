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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Session;

import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.framework.ServiceException;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.adobe.core.hootsuite.integration.external.services.ReportingService;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

@Component(immediate = true, service = ReportingService.class)
public class ReportingServiceImpl implements ReportingService {
	
	@Reference
	private transient QueryBuilder queryBuilder;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Hit> getPublishedResources(ResourceResolver resolver) {
		try {
			Session session = resolver.adaptTo(Session.class);
			Map<String, String> params = new HashMap<>();
			params.put("path", "/content/experience-fragments");
			params.put("type", "nt:unstructured");
			params.put("p.limit", "10");
			params.put("1_property", "publishedToHootsuite");
			params.put("1_property.value", "true");
			params.put("orderby", "@jcr:lastModified");
			params.put("orderby.sort", "desc");
			
			Query query = queryBuilder.createQuery(PredicateGroup.create(params), session);
			SearchResult results = query.getResult();
			
			return results.getHits();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
}
