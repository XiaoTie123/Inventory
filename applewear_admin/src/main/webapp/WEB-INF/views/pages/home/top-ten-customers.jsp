<%@ include file="../../include/tag-template.jsp"%>

<div class="col-md-6">
	<div class="card user-list">
		<div class="card-header"><h5>Top Ten Customers</h5></div>
		
		<div class="card-block pb-0">
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>
								<spring:message code="top.customer.name" text="Name"></spring:message>
							</th>
							<th>
								<spring:message code="top.total.amount" text="Total Amount"></spring:message>
							</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="row" items="${topTenCustomers }" varStatus="status">
						<tr>
							<td>${row.name}</td>
							<td>${row.totalAmountDesc }</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>