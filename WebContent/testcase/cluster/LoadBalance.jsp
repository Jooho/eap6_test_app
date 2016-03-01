<!-- This test jsp is base on WebLogic Test JSP.
     2013.01.24 Created by Jooho Lee <Jay> (ljhiyh@gmail.com)  -->

<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Load Balance Test </title>
</head>
<body>
<body bgcolor=#FFFFFF>
<p>
<h2>
<font color=#DB1260>
LoadBalancing Test 
</font>
</h2>

<p>
This servlet returns information about the HTTP request
itself. You can modify this servlet to take this information
and store it elsewhere for your HTTP server records. This
servlet is also useful for debugging.

<h3>
Server Name
</h3>

<pre>
<%= System.getProperty("jboss.server.name") %>
</pre>

<h3>
Servlet Spec Version Implemented
</h3>

<pre>
<%= getServletConfig().getServletContext().getMajorVersion() + "." + getServletConfig().getServletContext().getMinorVersion() %>
</pre>

<h3>
Requested URL
</h3>

<pre>
<%= HttpUtils.getRequestURL(request) %>
</pre>

<h3>
Request parameters
</h3>

<pre>
<%
Enumeration requesParams = request.getParameterNames();
while(requesParams.hasMoreElements()){
  String key = (String)requesParams.nextElement();
  String[] paramValues = request.getParameterValues(key);
  for(int i=0;i < paramValues.length;i++){
      out.println(key + " : "  + paramValues[i]); 
  }
}
%>
</pre>

<h3>
Request information
</h3>

<pre>
Request Method: <%= request.getMethod() %>
Request URI: <%= request.getRequestURI() %>
Request Protocol: <%= request.getProtocol() %>
Servlet Path: <%= request.getServletPath() %>
Path Info: <%= request.getPathInfo() %>
Path Translated: <%= request.getPathTranslated() %>
Query String: <%= request.getQueryString() %>
Content Length: <%= request.getContentLength() %>
Content Type: <%= request.getContentType() %>
Server Name: <%= request.getServerName() %>
Server Port: <%= request.getServerPort() %>
Remote User: <%= request.getRemoteUser() %>
Remote Address: <%= request.getRemoteAddr() %>
Remote Host: <%= request.getRemoteHost() %>
Authorization Scheme: <%= request.getAuthType() %>
</pre>

<h3>
Request headers
</h3>

<pre>
<%
requesParams = request.getHeaderNames();
while (requesParams.hasMoreElements()) {
  String name = (String)requesParams.nextElement();
  out.println(name + ": " + request.getHeader(name));
}
%>
</pre>

</body>
</html>