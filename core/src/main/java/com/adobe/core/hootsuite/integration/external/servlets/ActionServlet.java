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
package com.adobe.core.hootsuite.integration.external.servlets;

import java.io.IOException;

import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.day.cq.workflow.WorkflowService;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkflowData;
import com.day.cq.workflow.model.WorkflowModel;

@Component(
		immediate = true, 
		service = Servlet.class, 
		property = {
				"sling.servlet.methods=POST",
				"sling.servlet.paths=/bin/services/hootsuite" 
				}
		)
public class ActionServlet extends SlingAllMethodsServlet {

    private static final long serialVersionUID = 1L;
    
    @Reference
	private transient WorkflowService workflowService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void doPost(final SlingHttpServletRequest request,
            final SlingHttpServletResponse response) throws ServletException, IOException {
    	ResourceResolver resolver =null;
    	try {
    		resolver = request.getResourceResolver();
    		
    		Session session = resolver.adaptTo(Session.class);
    		WorkflowSession wfSession = workflowService.getWorkflowSession(session);
    		
    		WorkflowModel wfModel = wfSession.getModel("/etc/workflow/models/hootsuite-xf-publish-workflow");
    		WorkflowData wfData = wfSession.newWorkflowData("JCR_PATH", request.getParameter("payload"));
    		wfData.getMetaDataMap().put("socialMediaID", request.getParameter("socialMediaID"));
    		wfSession.startWorkflow(wfModel, wfData);
    		response.setStatus(201);
		} catch (Exception e) {
			System.out.println(e);
		}
    }
}
