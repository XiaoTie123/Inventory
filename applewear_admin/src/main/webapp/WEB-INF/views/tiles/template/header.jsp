<%@ include file="../../include/tag-template.jsp"%>

<header
	class="navbar pcoded-header navbar-expand-lg navbar-blue header-blue headerpos-fixed">
	<div class="m-header">
		<a class="mobile-menu" id="mobile-collapse1" href="#!"><span></span></a>
		<a href="home.html" class="b-brand">
			<div class="b-bg">
				<img alt="" src="resources/images/applewear_logo.png"
					class="img-fluid" height="50px">
			</div> <span class="b-title fs-14">Apple UnderWear</span>
		</a> <a class="mobile-menu" id="mobile-collapse" href="javascript:;"><span></span></a>
	</div>
	<a class="mobile-menu" id="mobile-header" href="#!"> <i
		class="feather icon-more-horizontal"></i>
	</a>
	<div class="collapse navbar-collapse">
		<ul class="navbar-nav mr-auto">
			<li><a href="#!" class="full-screen"
				onclick="javascript:toggleFullScreen()"><i
					class="feather icon-maximize"></i></a></li>
			<li><h4 class="pro-head mb-0">${pageTitle}</h4></li>
		</ul>
		<ul class="navbar-nav ml-auto">
			<li><a href="customer-order-list.html" class="nav-link"
				style="margin-left: 10px; position: relative;"> <c:choose>
						<c:when test="${count > 0}">
							<img alt="" src="resources/images/noti.svg" class="img-fluid"
								width="25px;">
							<span class="badge badge-pill badge-danger"
								style="position: absolute; top: -17px; right: -17px; font-size: 12px;">
								${count} </span>
						</c:when>
						<c:otherwise>
							<img alt="" src="resources/images/noti.svg" class="img-fluid"
								width="25px;">
						</c:otherwise>
					</c:choose>
			</a></li>
			<li>
				<div class="dropdown drp-user">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						${sessionScope.loginUser.adminName} </a>
					<div class="dropdown-menu dropdown-menu-right profile-notification">
						<div class="pro-head">
							<img src="resources/images/avatar.jpg" class="img-radius"
								alt="User-Profile-Image"> <span>Hello! ${sessionScope.loginUser.adminName}</span>
						</div>

						<ul class="pro-body">
							<li><a href="logout.html" class="dropdown-item"><i
									class="feather icon-log-out"></i> Logout</a></li>
						</ul>
					</div>
				</div>
			</li>
		</ul>
	</div>
</header>