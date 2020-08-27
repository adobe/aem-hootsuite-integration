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
package com.adobe.core.hootsuite.integration.external.services.beans;

/**
 * 
 * A simple POJO for marshalling/unmarshalling Media Asset Upload Request.
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
public class MediaUploadRequest {

	/** The size bytes. */
	private long sizeBytes;
	
	/** The mime type. */
	private String mimeType;

	/**
	 * Instantiates a new media upload request.
	 *
	 * @param sizeBytes the size bytes
	 * @param mimeType the mime type
	 */
	public MediaUploadRequest(long sizeBytes, String mimeType) {
		super();
		this.sizeBytes = sizeBytes;
		this.mimeType = mimeType;
	}

	/**
	 * Gets the size bytes.
	 *
	 * @return the size bytes
	 */
	public long getSizeBytes() {
		return sizeBytes;
	}

	/**
	 * Sets the size bytes.
	 *
	 * @param sizeBytes the new size bytes
	 */
	public void setSizeBytes(long sizeBytes) {
		this.sizeBytes = sizeBytes;
	}

	/**
	 * Gets the mime type.
	 *
	 * @return the mime type
	 */
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * Sets the mime type.
	 *
	 * @param mimeType the new mime type
	 */
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

}
