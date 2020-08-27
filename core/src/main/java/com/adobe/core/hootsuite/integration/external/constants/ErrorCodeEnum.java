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
 * This enum stores  the common error codes used throughout the 
 * integration bundle.
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
public enum ErrorCodeEnum {

	/** The err01. */
	ERR01("ERR01", "Invalid/Empty Email Address"),

	/** The err02. */
	ERR02("ERR02", "Blank Field"),

	/** The err03. */
	ERR03("ERR03", "Vault Configuration is not found"),

	/** The err04. */
	ERR04("ERR04", "Error while fetching the error token"),

	/** Redis Error Code - Input is not available or valid. */
	ERR05("ERR05", "Input is not available or valid"),

	/** Redis Error Code - Value not available. */
	ERR06("ERR06", "value not found in the cache"),

	/** The err07. */
	ERR07("ERR07", "OAuth Configuration is not found"),

	/** The err08. */
	ERR08("ERR08", "OAuth Configuration is invalid"),

	/** The err09. */
	ERR09("ERR09", "Unable persist K/V pair"),

	/** The err10. */
	ERR10("ERR10", "K/V pair not found in vault");

	/** The error code. */
	private final String errorCode;

	/** The error msg. */
	private final String errorMsg;

	/**
	 * Instantiates a new error code enum.
	 *
	 * @param errorCode the error code
	 * @param errorMsg  the error msg
	 */
	ErrorCodeEnum(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Gets the error msg.
	 *
	 * @return the error msg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

}
