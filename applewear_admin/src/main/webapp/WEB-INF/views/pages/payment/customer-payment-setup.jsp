<%@ include file="../../include/tag-template.jsp"%>


<div class="row">
	<div class="col-sm-12">
		<!-- start card -->
		<div class="card">
			<div class="card-header">
				<h5>Customer Payment Setup</h5>
			</div>
			<div class="card-body">
				<form:form action="customer-payment-setup.html"
					modelAttribute="paymentDTO">

					<form:hidden path="customerDTO.customerId" />
					<form:hidden path="orderDTO.orderId" />
					<form:hidden path="paymentMethodDTO.paymentMethodId" value="1" />

					<div class="row mb-3">

						<div class="col-md-6">
							<div class="form-group">
								<label for="orderDTO.orderRef"> <spring:message
										code="order.order.no" text="Order No"></spring:message>
								</label>
								<form:input class="form-control bg-white"
									path="orderDTO.orderRef" readonly="true"></form:input>
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="orderDTO.orderDate"> <spring:message
										code="order.order.date" text="Order Date"></spring:message>
								</label>
								<form:input class="form-control bg-white"
									path="orderDTO.orderDate" readonly="true"></form:input>
							</div>
						</div>



						<div class="col-md-6">
							<div class="form-group">
								<label for="customerDTO.name">Customer Name </label>
								<form:input class="form-control bg-white"
									path="customerDTO.name" readonly="true"></form:input>
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="customerDTO.mobile">Customer Phone </label>
								<form:input class="form-control bg-white"
									path="customerDTO.mobile" readonly="true"></form:input>
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="orderDTO.totalAmountText"> <spring:message
										code="order.total.amount" text="Total Amount"></spring:message>
								</label>
								<form:input class="form-control bg-white"
									path="orderDTO.totalAmountText" readonly="true"></form:input>
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="orderDTO.totalPaidText"> <spring:message
										code="umcomplete.order.paid" text="Total Paid"></spring:message>
								</label>
								<form:input class="form-control bg-white"
									path="orderDTO.totalPaidText" readonly="true"></form:input>
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="orderDTO.remainingAmountText"> <spring:message
										code="customer.order.remaining" text="Remaining Amount"></spring:message>
								</label>
								<form:input class="form-control bg-white"
									path="orderDTO.remainingAmountText" readonly="true"></form:input>
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="totalPaidAmount">Amount </label>
								<form:input class="form-control integer-input bg-white"
									path="totalPaidAmount"></form:input>
								<form:errors path="totalPaidAmount"
									class="text-danger text-left" element="div" />
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="paymentMethodDTO.paymentMethodId"> <spring:message
										code="payment.method" text="Payment Method"></spring:message>
								</label>
								<form:select path="paymentMethodId"
									class="form-control bg-white">
									<form:option value="-1">Select One</form:option>
									<form:options items="${paymentMethodList}" itemLabel="name"
										itemValue="paymentMethodId"></form:options>
								</form:select>
								<form:errors path="paymentMethodId"
									class="text-danger text-left" element="div" />
							</div>
						</div>

					</div>

					<div class="row mb-3">
						<div class="col-md-12 text-right">
							<button type="submit" class="btn btn-primary  mr-3">Save
							</button>
							<a class="btn btn-success"
								href="customer-order-history.html?customerId=${paymentDTO.customerDTO.customerId }">Back</a>
						</div>
					</div>

				</form:form>

			</div>
		</div>
		<!-- end card -->
	</div>
</div>