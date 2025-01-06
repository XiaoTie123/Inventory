<%@ include file="../../include/tag-template.jsp"%>

<script type="text/javascript">
	var url = '${pageContext.request.contextPath}';
</script>

<form:form action="order-setup.html" modelAttribute="orderDTO"
	method="post">

	<form:hidden path="orderId" />
	<form:hidden path="customerName" id="customerName" />

	<div class="row">
		<div class="col-sm-4">
			<!-- [start card] -->
			<div class="card">

				<div class="card-header">
					<h5>Customer Information</h5>
				</div>

				<div class="card-body">


					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="customerId"> <spring:message
										code="customer.name" text="Name"></spring:message> <span
									class="ml-2 text-danger">*</span></label>
								<form:select path="customerId" class="select-2 form-control"
									id="customerId">
									<form:option value="-1">Select One</form:option>
									<c:forEach var="row" items="${customerDTOList }">
										<form:option value="${row.customerId }"
											data-address="${row.address }"
											data-rankname="${row.rankName }" data-mobile="${row.mobile }">${row.name }</form:option>
									</c:forEach>
								</form:select>
								<form:errors path="customerId" class="text-danger text-left"
									element="div" />
							</div>
						</div>

						<div class="col-md-12">
							<div class="form-group">
								<label for="customerRank"> <spring:message
										code="customer.rank"></spring:message>
								</label>
								<form:input class="form-control bg-white" path="customerRank"
									id="customerRank" readonly="readonly" />

							</div>
						</div>

						<div class="col-md-12">
							<div class="form-group">
								<label for="mobile"> <spring:message
										code="customer.mobile" text="Mobile"></spring:message>
								</label>
								<form:input class="form-control bg-white" path="customerPhone"
									id="customerPhone" readonly="readonly" />

							</div>
						</div>

						<div class="col-md-12">
							<div class="form-group">
								<label for="address"> <spring:message
										code="customer.address" text="Address"></spring:message>
								</label>
								<form:input class="form-control bg-white" path="customerAddress"
									id="customerAddress" readonly="readonly" />

							</div>
						</div>

						<div class="col-md-12">
							<div class="form-group">
								<label for="orderStatus"> <spring:message
										code="order.order.status" text="Order Status"></spring:message>
									<span class="ml-2 text-danger">*</span></label>
								<form:select path="orderStatus" class="form-control"
									id="orderStatus">
									<form:options itemLabel="desc" itemValue="code"
										items="${orderStatusList }" />
								</form:select>
							</div>
						</div>

					</div>


				</div>
			</div>
			<!-- [end card] -->
		</div>

		<div class="col-md-8">

			<div class="card">

				<div class="card-header">
					<h5>Product</h5>
				</div>

				<div class="card-body">
					<%@ include file="product-entry.jsp"%>
				</div>
			</div>

		</div>

	</div>

	<div class="row">
		<div class="col-md-12">

			<div class="card">
				<div class="card-body">
					<%@ include file="product-list-entry.jsp"%>

					<div class="row">
						<div class="col-md-12 text-right mt-3">
							<button class="btn btn-primary">Submit</button>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>


</form:form>


<script
	src="<%=request.getContextPath()%>/resources/js/vendor-all.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/select2.full.min.js"
	defer></script>
<script
	src="<%=request.getContextPath()%>/resources/js/order/order-setup.js?modified=363332136222"></script>
