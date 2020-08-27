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
package com.adobe.core.hootsuite.integration.external.constants;

/**
 * 
 * Interface is used as a constant repository to be used throughout the <br/>
 * application.
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
public interface GlobalConstants {

	/** The mime type text. */
	String MIME_TYPE_TEXT = "text/plain";

	/** The mime type json. */
	String MIME_TYPE_JSON = "application/json";
	
	/** The mime type urlencoded. */
	String MIME_TYPE_URLENCODED = "application/x-www-form-urlencoded";

	/** The system prop tmpdir. */
	String SYSTEM_PROP_TMPDIR = "java.io.tmpdir";
	
	/** The header cache control. */
	String HEADER_CACHE_CONTROL = "cache-control";
	
	/** The header accept. */
	String HEADER_ACCEPT = "accept";
	
	/** The header content type. */
	String HEADER_CONTENT_TYPE = "Content-Type";
	
	/** The header authorization. */
	String HEADER_AUTHORIZATION = "Authorization";
	
	/** The colon. */
	String COLON = ":";
	
	/** The at. */
	String AT = "@";
}
