<%@ include file="../../include/tag-template.jsp"%>

<div class="row">
	<div class="col-sm-12">
		<!-- start card -->
		<div class="card">
			<div class="card-header">
				<h5>Admin Setup</h5>
			</div>
			<div class="card-body">
				<form:form action="admin-setup.html" modelAttribute="adminDTO">
					<form:hidden path="adminId" />
					<div class="row mb-3">

						<div class="col-md-6">
							<div class="form-group">
								<label for="productCode"> Name </label>
								<form:input class="form-control bg-white" path="adminName"></form:input>
								<form:errors path="adminName" class="text-danger text-left"
									element="div" />
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="form-group">
								<label for="loginName"> Login Id </label>
								<form:input class="form-control bg-white" path="loginName"></form:input>
								<form:errors path="loginName" class="text-danger text-left"
									element="div" />
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="quantity"> Email </label>
								<form:input class="form-control bg-white" path="email"></form:input>
								<form:errors path="email" class="text-danger text-left"
									element="div" />
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="brandId"> Role </label>
								<form:select path="role" class="form-control bg-white">
									<form:option value="-1">Select One</form:option>
									<form:options items="${roleList}" itemLabel="desc"
										itemValue="code"></form:options>
								</form:select>
								<form:errors path="role" class="text-danger text-left"
									element="div" />
							</div>
						</div>

						<c:choose>
							<c:when test="${not empty adminDTO.adminId }">
							</c:when>
							<c:otherwise>
								<div class="col-md-6">
									<div class="form-group">
										<label for="quantity"> Password </label>
										<form:input class="form-control bg-white" path="password"></form:input>
										<form:errors path="password" class="text-danger text-left"
											element="div" />
									</div>
								</div>
							</c:otherwise>
						</c:choose>


					</div>

					<div class="row mb-3">
						<div class="col-md-12 text-right">
							<button type="submit" class="btn btn-primary mr-3">Save
							</button>
							<a href="admin-list.html" class="btn btn-primary mr-3"> List
							</a> <a href="admin-setup.html" class="btn btn-warning mr-3">
								Clear </a>

						</div>
					</div>
				</form:form>




			</div>
		</div>
		<!-- end card -->
	</div>
</div>