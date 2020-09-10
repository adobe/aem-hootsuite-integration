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
import java.net.URLDecoder;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.adobe.core.hootsuite.integration.external.services.OAuthService;
import com.adobe.core.hootsuite.integration.external.services.UtilityService;
import com.adobe.core.hootsuite.integration.external.services.beans.AuthTokenResponse;
import com.day.cq.wcm.api.Page;

@Component(
		immediate = true, 
		service = Servlet.class, 
		property = {
				"sling.servlet.methods=GET",
				"sling.servlet.paths=/bin/services/authorize" 
				}
		)
public class AuthorizationServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;
    
    @Reference
	private transient OAuthService oAuthService;
    
    @Reference
    private transient UtilityService utilityService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void doGet(final SlingHttpServletRequest request,
            final SlingHttpServletResponse response) throws ServletException, IOException {
    	ResourceResolver resolver =null;
    	try {
    		resolver = request.getResourceResolver();
    		
    		String code = request.getParameter("code");
    		String configPath = URLDecoder.decode(request.getParameter("state"),"UTF-8");
    		Page configPage = resolver.getResource(configPath).adaptTo(Page.class);
    		AuthTokenResponse authTokenResponse = this.oAuthService.getAccessToken(code, configPage);
    		this.utilityService.updateConfiguration(configPage.getContentResource(), resolver, authTokenResponse);
    		response.getWriter().write("Hootsuite Configuration has been created successfully!");
		} catch (Exception e) {
			response.sendError(500);
		}
    }
}
