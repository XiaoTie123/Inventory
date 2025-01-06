<%@ include file="../../include/tag-template.jsp"%>

<div class="row mb-3">
	<div class="col-md-12">
		<div class="table-responsive">
			<table id="payment-pending-orders-data-table"
				class="display table table-striped table-hover" style="width: 100%">
				<thead>
					<tr>
						<th></th>
						<th><spring:message code="no" text="No" /></th>
						<th><spring:message code="order.order.no" text="Order No" /></th>
						<th><spring:message code="order.order.date" text="Order Date" /></th>

						<th><spring:message code="order.customer.name"
								text="Customer Name" /></th>
						<th><spring:message code="order.total.amount"
								text="Total Amount" /></th>
						<th><spring:message code="customer.order.history.paid"
								text="Total Paid" /></th>
						<th><spring:message code="customer.order.remaining"
								text="Remaining Amount" /></th>	
				</thead>
				<tbody>

				</tbody>
			</table>
		</div>
	</div>
</div>

