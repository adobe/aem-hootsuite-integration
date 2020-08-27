<!--/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  ~ Copyright 2020 Adobe. All rights reserved.
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License");
  ~ you may not use this file except in compliance with the License.
  ~ You may obtain a copy of the License at
  ~
  ~     http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/-->
<%@page session="false"
        import="org.apache.sling.api.resource.Resource,
                com.adobe.granite.ui.components.Config,
                com.adobe.granite.ui.components.ExpressionHelper,
                org.apache.sling.api.resource.ValueMap,
                com.adobe.granite.ui.components.PagingIterator,
                com.adobe.granite.ui.components.ds.*,
                org.apache.sling.api.wrappers.ValueMapDecorator,
                java.io.File,
                java.io.FilenameFilter,

                java.time.format.DateTimeFormatter,
                java.util.*,
                java.time.ZoneId,
                java.time.Instant,
				java.util.List,
				com.day.cq.search.result.Hit,com.adobe.core.hootsuite.integration.external.services.ReportingService" %>

<%@include file="/libs/granite/ui/global.jsp" %>
<%@include file="/libs/granite/backup/components/commons.jsp" %>
<%!

    private DateTimeFormatter getDateFormatter(Locale locale) {
        return DateTimeFormatter.ISO_LOCAL_DATE.withLocale(locale).withZone(ZoneId.systemDefault());
    }

    private DateTimeFormatter getTimeFormatter(Locale locale) {
        return DateTimeFormatter.ISO_LOCAL_TIME.withLocale(locale).withZone(ZoneId.systemDefault());
    }

    private String formatDate(Locale locale, long value) {
        Instant instantValue = Instant.ofEpochMilli(removeMillisecondsFromDate(value));
        DateTimeFormatter formatter = getDateFormatter(locale);
        return formatter.format(instantValue);
    }

    private String formatTime(Locale locale, long value) {
        Instant instantValue = Instant.ofEpochMilli(removeMillisecondsFromDate(value));
        DateTimeFormatter formatter = getTimeFormatter(locale);
        return formatter.format(instantValue);
    }

    private long removeMillisecondsFromDate(long value) {
        return 1000 * (value / 1000);
    }
%>

<%
    DataSource ds;
    ValueMap newPropertyMap;
    ExpressionHelper ex = cmp.getExpressionHelper();

	ReportingService reportingService = sling.getService(ReportingService.class);
	List<Hit> results = reportingService.getPublishedResources(resourceResolver);

    Config cfg = new Config(resource.getChild(Config.DATASOURCE));
    final String itemRT = cfg.get("itemResourceType", String.class);
    final Integer offset = ex.get(cfg.get("offset", String.class), Integer.class);
    final Integer limit = ex.get(cfg.get("limit", String.class), Integer.class);

    final List<Resource> resultList = new ArrayList<Resource>();


	if(null != results){
        for(Hit hit : results){
			newPropertyMap = new ValueMapDecorator(new HashMap<>());
            newPropertyMap.put("name", hit.getResource().getParent().getParent().getChild("jcr:content").getValueMap().get("jcr:title", String.class));
            newPropertyMap.put("path", hit.getResource().getParent().getParent().getPath());
            newPropertyMap.put("formattedDate", hit.getProperties().get("cq:lastModified"));
        	resultList.add(new ValueMapResource(resourceResolver, "", itemRT, newPropertyMap));
        }
    }

    ds = new AbstractDataSource() {
        public Iterator<Resource> iterator() {
            return new PagingIterator<>(resultList.iterator(), offset, limit);
        }
    };

    request.setAttribute(DataSource.class.getName(), ds);
%>
