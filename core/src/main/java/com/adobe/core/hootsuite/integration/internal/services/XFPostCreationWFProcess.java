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
import java.util.Map;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.core.hootsuite.integration.external.services.MessageService;
import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.wcm.api.Page;

@Component(
		immediate = true, 
		service = WorkflowProcess.class, 
		property = { 
				"process.label=Publish XF to Hootsuite" 
				}
		)
public class XFPostCreationWFProcess implements WorkflowProcess {

	@Reference
	private transient ResourceResolverFactory resolverFactory;
	
	@Reference
	private transient MessageService messageService;
	
	private static final Logger log = LoggerFactory.getLogger(XFPostCreationWFProcess.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(WorkItem item, WorkflowSession session, MetaDataMap args) throws WorkflowException {
		ResourceResolver resolver = null;
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put(ResourceResolverFactory.SUBSERVICE, "write-service");
			resolver = this.resolverFactory.getServiceResourceResolver(param);

			this.messageService.createSocialPost(resolver.getResource(item.getContentPath()).adaptTo(Page.class),
					resolver, item.getWorkflowData().getMetaDataMap().get("socialMediaID", String.class));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			if (null != resolver) {
				resolver.close();
			}
		}

	}


}
