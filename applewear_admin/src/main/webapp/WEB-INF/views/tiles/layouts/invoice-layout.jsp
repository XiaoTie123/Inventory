<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../include/tag-template.jsp"%>
<html>
<title><tiles:getAsString name="title" /></title>
<tiles:insertAttribute name="head" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>APPLEWear | Invoice</title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<body class="bg-white">
	<input id="frmMessage" type="hidden" value="${frmMessage}">
		
	<div class="bg-white ml-2 mt-3 mb-2 mr-2">
		<tiles:insertAttribute name="body" />
    </div>

</body>
</html>