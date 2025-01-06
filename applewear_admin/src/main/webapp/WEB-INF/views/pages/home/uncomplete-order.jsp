<%@ include file="../../include/tag-template.jsp"%>

<div class="col-md-6">
	<div class="card user-list">
		<div class="card-header"><h5>Incomplete Orders</h5></div>
		
		<div class="card-block pb-0">
			<div class="table-responsive">
				<table class="table table-hover" id="incomplete-order-data-table">
					<thead>
						<tr>
							<th>
								<spring:message code="order.order.no" text="Order No"></spring:message>
							</th>
							<th>
								<spring:message code="order.order.date" text="Order Date"></spring:message>
							</th>
							<th>
								<spring:message code="umcomplete.order.total" text="Total Amount"></spring:message>
							</th>
							<th>
								<spring:message code="umcomplete.order.paid" text="Total Paid"></spring:message>
							</th>
							<th>
								<spring:message code="umcomplete.order.discount" text="Discount"></spring:message>
							</th>
							<th>
								<spring:message code="order.customer.name" text="Customer Name"></spring:message>
							</th>
						</tr>
					</thead>
					<tbody>
					
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>


