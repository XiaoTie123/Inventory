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
<body class="layout layout-header-fixed">
	<input id="frmMessage" type="hidden" value="${ frmMessage }">
	<tiles:insertAttribute name="menu" />
	<tiles:insertAttribute name="header" />

	<div class="pcoded-main-container">
		<div class="pcoded-wrapper">
			<div class="pcoded-content">
				<div class="pcoded-inner-content">
					<div class="main-body">
						<div class="page-wrapper">
							<div class="row">
								<div class="col-sm-12">
									<tiles:insertAttribute name="body" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../../include/js-template.jsp"%>
</body>
</html>