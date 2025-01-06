<%@ include file="../../include/tag-template.jsp"%>

<div class="row mb-3">
	<div class="col-md-12">
		<div class="text-right">
			<a href="payment_invoice.html?customerId=${customerId }"
				class="btn btn-primary"> Print </a>
		</div>
		<div class="table-responsive">
			<table id="payment-history-data-table"
				class="display table table-striped table-hover" style="width: 100%">
				<thead>
					<tr>

						<th></th>
						<th><spring:message code="order.order.date" text="Order Date" /></th>
						<th><spring:message code="order.order.no" text="Order No" /></th>
						<th><spring:message code="customer.order.history.paid"
								text="Total Paid" /></th>
				</thead>
				<tbody>

				</tbody>
			</table>
		</div>
	</div>
</div>

