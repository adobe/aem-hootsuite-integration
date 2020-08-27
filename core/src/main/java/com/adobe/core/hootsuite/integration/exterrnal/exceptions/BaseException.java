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
package com.adobe.core.hootsuite.integration.exterrnal.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to handle the system exceptions so that the errors stack trace is
 * effectively logged and/or reported.
 * 
 * @since 2020.04.15
 * @version 1.0
 * 
 *******************************************************************************
 *          				Version History
 *******************************************************************************
 * 
 *          		1.0 				Initial Version
 * 
 ********************************************************************************
 **/
public class BaseException extends RuntimeException {

	/**
	 * Default Serial UID.
	 */
	private static final long serialVersionUID = -8769398919193116867L;
	

	/**
	 * Variable holds the error string.
	 */
	private final String errorString;

	/**
	 * Variable holds the error code.
	 */
	private final String errorCode;

	/**
	 * Variable holds the error code List.
	 */
	private final List<String> errorCodeList;

	/**
	 * Parameterized constructor. Takes exception message, code and object as
	 * argument
	 * 
	 * @param message exception message.
	 * @param eCode   exception error code
	 */
	public BaseException(String message, String eCode) {
		super(message);
		this.errorString = message;
		this.errorCode = eCode;
		this.errorCodeList = new ArrayList<String>();
		errorCodeList.add(eCode);
	}

	/**
	 * Parameterized constructor. Takes exception message, code and object as
	 * argument
	 * 
	 * @param eCodes  the e codes
	 * @param message exception message.
	 */
	public BaseException(List<String> eCodes, String message) {
		super(message);
		this.errorString = message;
		this.errorCode = "";
		this.errorCodeList = eCodes;
	}

	/**
	 * Parameterized constructor. Takes exception message, code and object as
	 * argument
	 * 
	 * @param eCodes  the e codes
	 * @param message exception message.
	 */
	public BaseException(List<String> eCodes, String message, Throwable e) {
		super(message, e);
		this.errorString = message;
		this.errorCode = "";
		this.errorCodeList = eCodes;
	}

	/**
	 * Parameterized constructor. Takes exception message, code and object as
	 * argument
	 * 
	 * @param message exception message. throwable exception parameter.
	 * @param eCode   exception error code
	 * @param exc     exception
	 */
	public BaseException(String message, String eCode, Throwable exc) {
		super(message, exc);
		this.errorString = message;
		this.errorCode = eCode;
		this.errorCodeList = new ArrayList<String>();
		errorCodeList.add(eCode);
	}

	/**
	 * Parameterized constructor. Takes exception message, and object as argument
	 * 
	 * @param message exception message. throwable exception parameter.
	 * @param exc     exception
	 */
	public BaseException(String message, Throwable exc) {
		super(message, exc);
		this.errorString = message;
		this.errorCode = "";
		this.errorCodeList = null;
	}

	/**
	 * Gets the error string of exception.
	 * 
	 * @return String error string
	 */
	public String getErrorString() {
		return errorString;
	}

	/**
	 * Gets the error code of exception.
	 * 
	 * @return String error code
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Gets the error code list.
	 * 
	 * @return the error code list
	 */
	public List<String> getErrorCodeList() {
		return errorCodeList;
	}
}
