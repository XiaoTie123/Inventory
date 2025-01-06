<%@ include file="../../include/tag-template.jsp"%>

<div class="row">
	<div class="col-sm-12">
		<div class="card">
			<div class="card-header">
				<h5>Stock Transaction Search</h5>
			</div>
			<div class="card-body">
				<form:form modelAttribute="searchDTO"
					action="stock-transaction-search.html">

					<div class="row mb-3">


						<div class="col-md-6">
							<div class="form-group">
								<label for="productName"> <spring:message
										code="top.product.name" text="Product Name" />
								</label> <input type="text" class="form-control bg-white"
									id="productName" />
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="refNo"> <spring:message code="ref.no"
										text="Ref No"></spring:message>
								</label> <input type="text" class="form-control bg-white" id="refNo" />
							</div>
						</div>


					</div>
					<div class="row mb-3">
						<div class="col-md-12 text-right">
							<button id="btn-search" type="button"
								class="btn btn-primary mr-3">Search</button>
							<button type="submit" name="exportStockTransactionReport"
								value="exportStockTransactionReport" class="btn btn-primary mr-3">Export
								Excel</button>


						</div>
					</div>

			</form:form>
				<div class="row mb-3">
					<div class="col-md-12">
						<div class="table-responsive">
							<table id="stock-transaction-data-table"
								class="display table table-striped table-hover"
								style="width: 100%">
								<thead>
									<tr>
										<th></th>
										<th><spring:message code="no" text="No" /></th>
										<th><spring:message code="top.product.name"
												text="Product Name" /></th>
										<th><spring:message code="label.type" text="Type" /></th>
										<th><spring:message code="product.quantity"
												text="Quantity" /></th>
										<th><spring:message code="ref.no" text="Ref No" /></th>
										<th><spring:message code="label.evidence" text="Evidence" /></th>
										<th><spring:message code="label.remark" text="Remark" /></th>
										<th><spring:message code="submitted.name"
												text="Submitted By" /></th>
										<th><spring:message code="order.order.date"
												text="Created Date" /></th>
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
	src="<%=request.getContextPath()%>/resources/js/transaction/stock-transaction-search.js?modified=33"></script>