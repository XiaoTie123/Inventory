<%@ include file="../../include/tag-template.jsp"%>

<div class="row">
	<div class="col-sm-12">
		<div class="card">
			<div class="card-header">
				<h5>Finished Orders Search</h5>
			</div>
			<div class="card-body">
				<form>

					<div class="row mb-3">


						<div class="col-md-6">
							<div class="form-group">
								<label for="orderRef"> <spring:message
										code="customer.name" text="Name"></spring:message>
								</label> <input type="text" class="form-control bg-white" id="orderRef" />
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="customerName"> <spring:message
										code="order.customer.name" text="Customer Name"></spring:message>
								</label> <input type="text" class="form-control bg-white"
									id="customerName" />
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="customerPhone"> <spring:message
										code="order.phone.no" text="Phone No"></spring:message>
								</label> <input type="text" class="form-control bg-white"
									id="customerPhone" />
							</div>
						</div>



						<div class="col-md-6">
							<div class="form-group">
								<label for="fromDate"> From Date </label> <input
									class="form-control bg-white date-picker" id="fromDate"></input>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="toDate"> To Date </label> <input
									class="form-control bg-white date-picker" id="toDate"></input>
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
							<table id="order-data-table"
								class="display table table-striped table-hover"
								style="width: 100%">
								<thead>
									<tr>
										<th></th>
										<th><spring:message code="no" text="No" /></th>
										<th><spring:message code="order.order.no" text="Order No" /></th>
										<th><spring:message code="order.order.date"
												text="Order Date" /></th>
										<th><spring:message code="order.total.en"
												text="Total Amount" /></th>
										<th><spring:message code="order.total.paid.en"
												text="Total Paid" /></th>

										<th><spring:message code="order.discount" text="Discount" /></th>
										<th><spring:message code="order.customer.name"
												text="Customer Name" /></th>
										<th><spring:message code="order.customer.rank"
												text="Customer Rank" /></th>
										<th><spring:message code="order.customer.address"
												text="Customer Address" /></th>
										<th><spring:message code="order.phone.no" text="Phone No" /></th>
										<th><spring:message code="order.order.status"
												text="Status" /></th>
										<th><spring:message code="order.submitted.by.name"
												text="Submitted By" /></th>
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
	src="<%=request.getContextPath()%>/resources/js/order/order-finish-search.js?modified=33333233"></script>