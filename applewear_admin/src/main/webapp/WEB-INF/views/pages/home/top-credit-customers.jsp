<%@ include file="../../include/tag-template.jsp"%>

<div class="col-md-6">
	<div class="card user-list">
		<div class="card-header"><h5>Top Credit Customers</h5></div>
		
		<div class="card-block pb-0">
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>
								<spring:message code="top.customer.name" text="Name"></spring:message>
							</th>
							<th>
								<spring:message code="top.credit.amount" text="Amount"></spring:message>
							</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="row" items="${topCreditCustomers }" varStatus="status">
						<tr>
							<td>${row.name}</td>
							<td>${row.totalCreditText }</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>