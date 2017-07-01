<!DOCTYPE HTML>
<%@ page language="java" import="java.util.Calendar" pageEncoding="utf-8" %>
<html lang="ZH">
<head>
    <title>${systemConfig.systemTitle}</title>
</head>

<!-- Scriptlet -->
    <%
		String path = request.getContextPath();
	%>
<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="btx"
       value="${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<!-- JavaSctipt -->
<script src="${ctx}/js/jquery/jquery-3.1.1.min.js"></script>

<%--全局CSS--%>
<link rel="stylesheet" href="${ctx}/css/blueMap.css">

<jsp:useBean id="dateStr" class="java.util.Date"/>
<fmt:formatDate value="${dateStr}" type="both" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss" var="nowDAT"/>
    <%
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
    request.setAttribute("oneHAgo",calendar.getTime());
%>
<fmt:formatDate value="${oneHAgo}" type="both" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss" var="oneHAgo"/>