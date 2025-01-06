<%@ include file="../../include/tag-template.jsp"%>

<div class="row">
	<div class="col-sm-12">
		<!-- start card -->
		<div class="card">
			<div class="card-header">
				<h5>Customer Setup</h5>
			</div>
			<div class="card-body">
				<form:form action="customer-setup.html" modelAttribute="customerDTO">
					<form:hidden path="customerId" />
					<div class="row mb-3">

						<div class="col-md-6">
							<div class="form-group">
								<label for="name"> <spring:message code="customer.name"
										text="Customer Name"></spring:message>
								</label>
								<form:input class="form-control bg-white" path="name"></form:input>
								<form:errors path="name" class="text-danger text-left"
									element="div" />
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="vehicleId"> <spring:message
										code="customer.rank"></spring:message>
								</label>
								<form:select path="rankId" class="form-control bg-white">
									<form:option value="-1">Select One</form:option>
									<form:options items="${rankList}" itemLabel="name"
										itemValue="rankId"></form:options>
								</form:select>
								<form:errors path="rankId" class="text-danger text-left"
									element="div" />
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="email"> <spring:message
										code="customer.email" text="Email"></spring:message>
								</label>
								<form:input class="form-control bg-white" path="email"></form:input>
								<form:errors path="email" class="text-danger text-left"
									element="div" />
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="mobile"> <spring:message
										code="customer.mobile" text="Mobile"></spring:message>
								</label>
								<form:input class="form-control bg-white" path="mobile"></form:input>
								<form:errors path="mobile" class="text-danger text-left"
									element="div" />
							</div>
						</div>

						

						<div class="col-md-6">
							<div class="form-group">
								<label for="address"> <spring:message
										code="customer.address" text="Address"></spring:message>
								</label>
								<form:textarea class="form-control bg-white" path="address"
									rows="3"></form:textarea>
								<form:errors path="address" class="text-danger text-left"
									element="div" />
							</div>
						</div>


					</div>

					<div class="row mb-3">
						<div class="col-md-12 text-right">
							<button type="submit" class="btn btn-primary mr-3">Save
							</button>
							<a href="rank-setup.html" class="btn btn-warning mr-3"> Clear
							</a>
						</div>
					</div>
				</form:form>




			</div>
		</div>
		<!-- end card -->
	</div>
</div>