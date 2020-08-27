<%--
  ADOBE CONFIDENTIAL
  __________________

   Copyright 2012 Adobe Systems Incorporated
   All Rights Reserved.

  NOTICE:  All information contained herein is, and remains
  the property of Adobe Systems Incorporated and its suppliers,
  if any.  The intellectual and technical concepts contained
  herein are proprietary to Adobe Systems Incorporated and its
  suppliers and are protected by trade secret or copyright law.
  Dissemination of this information or reproduction of this material
  is strictly forbidden unless prior written permission is obtained
  from Adobe Systems Incorporated.
--%>
    <%@page contentType="text/html" pageEncoding="utf-8" import="com.day.cq.i18n.I18n"%>
        <%@include file="/libs/foundation/global.jsp"%>
            <%
    I18n i18n = new I18n(slingRequest);
%>
                <cq:include script="init.jsp" />
                <cq:includeClientLib categories="cq.contentinsight.cloudconfig" />
                <div>
                    <div>
                        <h3>
                            <%=i18n.get("Account Settings")%>
                        </h3>
                        <ul>
                            <li>
                                <div class="li-bullet"><strong>Access Token generation endpoint: </strong>
                                    <%= xssAPI.encodeForHTML(properties.get("tokenEP", "")) %>
                                </div>
                                <div class="li-bullet"><strong>Scope: </strong>
                                    <%= xssAPI.encodeForHTML(properties.get("scope", "")) %>
                                </div>
                                <div class="li-bullet"><strong>Client ID: </strong>
                                    <%= xssAPI.encodeForHTML(properties.get("clientID", "")) %>
                                </div>
                                <div class="li-bullet"><strong>Client Secret: </strong>
                                    <%= xssAPI.encodeForHTML(properties.get("clientSecret", "")) %>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>