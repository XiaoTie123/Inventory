<%@ include file="../../include/tag-template.jsp"%>
<div class="card">
	<div class="card-body text-center">
		<div class="mb-3">
			<img alt="" src="resources/images/applewear_logo.png"
				class="img-fluid" style="max-height: 100px;">
		</div>
		<h4 class="mb-4">Apple UnderWear</h4>
		<form:form action="login.html" method="POST" modelAttribute="loginDto">
			<div class="mb-3">
				<form:input path="loginName" class="form-control"
					placeholder="Login Name"></form:input>
				<form:errors path="loginName" class="text-danger text-left"
					element="div" />
			</div>
			<div class="mb-4">
				<form:password path="password" class="form-control"
					placeholder="Password"></form:password>
				<form:errors path="password" class="text-danger text-left"
					element="div" />
			</div>
			<button class="btn btn-primary shadow-2 mb-4 w-100">Login</button>
		</form:form>
	</div>
</div>