<%@ include file="../../include/tag-template.jsp"%>

<script type="text/javascript">
	var url = '${pageContext.request.contextPath}';
</script>

<div class="row">
	<div class="col-sm-12">
		<!-- start card -->
		<div class="card">
			<div class="card-body">

				<form:form action="stock-transaction-setup.html"
					modelAttribute="stockTransactionDTO">
					<form:hidden path="stockTransactionId" />
					<div class="row mb-3">
						<div class="col-md-6">
							<div class="form-group">
								<label for="transactionTypeId">TXN Type (In/Out) </label>
								<form:select path="transactionTypeId"
									class="form-control bg-white">
									<form:option value="-1">Select One</form:option>
									<form:options items="${transactionTypeList}"
										itemLabel="transactionTypeName" itemValue="transactionTypeId"></form:options>
								</form:select>
								<form:errors path="transactionTypeId"
									class="text-danger text-left" element="div" />
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="refNo">Reference No </label>
								<form:input class="form-control bg-white" path="refNo"></form:input>
								<form:errors path="refNo" class="text-danger text-left"
									element="div" />
							</div>
						</div>

					</div>


					<div class="row mb-3">

						<div class="col-md-6">
							<div class="form-group">
								<label for="productId"> <spring:message
										code="label.product" text="Product Name"></spring:message> <span
									class="ml-2 text-danger">*</span></label>
								<form:select path="productId"
									class="js-data-example-ajax form-control" id="productId">
									<c:if test="${not empty stockTransactionDTO.productId}">
										<form:option value="${stockTransactionDTO.productId}"
											selected="selected">
												${stockTransactionDTO.productName}
											</form:option>
									</c:if>
								</form:select>
								<form:errors path="productId" class="text-danger text-left"
									element="div" />
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="quantity">Quantity </label>
								<form:input class="form-control integer-input bg-white" path="quantity"></form:input>
								<form:errors path="quantity" class="text-danger text-left"
									element="div" />
							</div>
						</div>

					</div>


					<div class="row mb-3">
						<div class="col-md-6">
							<div class="form-group">
								<label for="remark">Remark </label>
								<form:input class="form-control bg-white" path="remark"></form:input>
								<form:errors path="remark" class="text-danger text-left"
									element="div" />
							</div>
						</div>
					</div>

					<div class="row mb-3">
						<div class="col-md-12 text-right">
							<button type="submit" class="btn btn-primary mr-3">Save
							</button>
							<a href="stock-transaction-setup.html"
								class="btn btn-warning mr-3"> Clear </a>
						</div>
					</div>

				</form:form>
			</div>
		</div>
		<!-- -end card -->
	</div>
</div>

<script
	src="<%=request.getContextPath()%>/resources/js/vendor-all.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/select2.full.min.js"></script>


<script>
	
$("#productId").select2({
	ajax: {
		url: url + '/product-search-autocomplete.json',
		dataType: 'json',
		delay: 500,
		type: "GET",
		data: function(params) {
			return {
				searchKey: params.term, // search term
			};
		},
		processResults: function(response) {
			console.log(response);
			return {
				results: $.map(response, function(product) {
					return {
						text: product.productName,
						name: product.productName,
						id: product.productId,
					}
				})
			}
		},
		cache: true,
	},
	placeholder: 'Search products',
	minimumInputLength: 1,
})
	
</script>