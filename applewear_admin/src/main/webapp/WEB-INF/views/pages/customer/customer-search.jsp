<%@ include file="../../include/tag-template.jsp"%>

<div class="row">
	<div class="col-sm-12">
		<div class="card">
			<div class="card-header">
				<h5>Customer Search</h5>
			</div>
			<div class="card-body">
				<form>

					<div class="row mb-3">


						<div class="col-md-6">
							<div class="form-group">
								<label for="name"> <spring:message code="order.order.no"
										text="Order No"></spring:message>
								</label> <input type="text" class="form-control bg-white" id="name" />
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="loginId"> <spring:message
										code="customer.loginId" text="Login Id"></spring:message>
								</label> <input type="text" class="form-control bg-white" id="loginId" />
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="email"> <spring:message
										code="customer.email" text="Email"></spring:message>
								</label> <input type="text" class="form-control bg-white" id="email" />
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="phoneNo"> <spring:message
										code="customer.mobile" text="Mobile"></spring:message>
								</label> <input type="text" class="form-control bg-white" id="phoneNo" />
							</div>
						</div>

					</div>
					<div class="row mb-3">
						<div class="col-md-12 text-right">
							<button id="btn-search" type="button"
								class="btn btn-primary mr-3">Search</button>



						</div>
					</div>

				</form>
				<div class="row mb-3">
					<div class="col-md-12">
						<div class="table-responsive">
							<table id="customer-data-table"
								class="display table table-striped table-hover"
								style="width: 100%">
								<thead>
									<tr>
										<th></th>
										<th><spring:message code="no" text="No" /></th>
										<th><spring:message code="customer.name" text="Name" /></th>
										<th><spring:message code="customer.rank" text="Rank" /></th>
										<th><spring:message code="customer.mobile" text="Mobile" /></th>
										<th><spring:message code="customer.address"
												text="Address" /></th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/customer/customer-search.js?modified=345625"></script>