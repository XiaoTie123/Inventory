<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../include/tag-template.jsp"%>
<html>
<title><tiles:getAsString name="title" /></title>
<tiles:insertAttribute name="head" />

<head>
<meta name="description" content="Apple UnderWear | Login">
<meta name="keywords" content="Apple UnderWear | Login">
<meta name="robots" content="index, follow">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="icon" href="resources/images/applewear_logo.png"
	type="image/png">
</head>
<body>
	<input id="frmMessage" type="hidden" value="${frmMessage}">
	<div class="auth-wrapper">
		<div class="auth-content">

			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<%@ include file="../../include/js-template.jsp"%>
</body>
</html>