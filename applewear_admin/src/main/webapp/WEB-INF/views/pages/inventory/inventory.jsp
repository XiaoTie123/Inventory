<%@ include file="../../include/tag-template.jsp"%>

<div class="row">
	<div class="col-sm-12">
		<div class="card">
			<div class="card-header">
				<spring:message code="label.inventory"></spring:message>
			</div>
			<div class="card-body">
				<form>

					<div class="row mb-3">


						<div class="col-md-6">
							<div class="form-group">
								<label for="productName"> <spring:message
										code="label.product" text="Product Name"></spring:message>
								</label> <input type="text" class="form-control bg-white"
									id="productName" />
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="productCode"> <spring:message
										code="label.product.code" text="Product Code"></spring:message>
								</label> <input type="text" class="form-control bg-white"
									id="productCode" />
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
							<table id="inventory-data-table"
								class="display table table-striped table-hover"
								style="width: 100%">
								<thead>
									<tr>

										<th><spring:message code="no" text="No" /></th>
										<th><spring:message code="label.product.code"
												text="Product Code"></spring:message></th>
										<th><spring:message code="item.name" text="Item Name" /></th>
										<th><spring:message code="number.remaining"
												text="Remaining Quantity" /></th>

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
	src="<%=request.getContextPath()%>/resources/js/inventory/inventory.js?modified=33"></script>