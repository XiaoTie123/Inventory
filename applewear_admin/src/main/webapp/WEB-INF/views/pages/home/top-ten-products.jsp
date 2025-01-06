<%@ include file="../../include/tag-template.jsp"%>

<div class="col-md-6">
	<div class="card user-list">
		<div class="card-header"><h5>Top Ten Products</h5></div>
		
		<div class="card-block pb-0">
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>
								<spring:message code="top.product.name" text="Product Name"></spring:message>
							</th>
							<th>
								<spring:message code="quantity" text="Quantity"></spring:message>
							</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="row" items="${topTenProducts }" varStatus="status">
						<tr>
							<td>${row.productName }</td>
							<td>${row.totalSales }</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>