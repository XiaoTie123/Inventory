<%@ include file="../../include/tag-template.jsp"%>

<input type="hidden" id="hid-is-new" value="true">
<input type="hidden" id="hid-update-row-index" value="0">

<div class="col-md-12 mt-3">


	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label for="productId"> <spring:message code="label.product"
						text="Product Name"></spring:message> <span
					class="ml-2 text-danger">*</span></label> <select class="form-control"
					id="productId">
					<option value="-1">Select One</option>
					<c:forEach var="row" items="${productList }">
						<option value="${row.productId }"
							data-buyingprice="${row.buyingPrice}"
							data-sellingprice="${row.sellingPrice }"
							data-quantityperpack="${row.quantity }">${row.productName }</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="col-md-6">
			<div class="form-group">
				<label for="originalPrice"> <spring:message
						code="original.price" text="Original Price"></spring:message> <span
					class="ml-2 text-danger">*</span></label> <input type="text"
					class="form-control integer-input" id="originalPrice" />
			</div>
		</div>

		<div class="col-md-6">
			<div class="form-group">
				<label for="discount"> <spring:message code="label.discount"
						text="Discount"></spring:message> <span class="ml-2 text-danger">*</span></label>
				<input type="text" class="form-control integer-input" id="discount" />
			</div>
		</div>

		<div class="col-md-6">
			<div class="form-group">
				<label for="sellingPrice"> <spring:message
						code="selling.price" text="Selling Price"></spring:message> <span
					class="ml-2 text-danger">*</span></label> <input type="text"
					class="form-control integer-input" id="sellingPrice" />
			</div>
		</div>

		<div class="col-md-6">
			<div class="form-group">
				<label for="pack"> <spring:message code="total.pack"
						text="Total Pack"></spring:message> <span class="ml-2 text-danger">*</span></label>
				<input type="text" class="form-control integer-input" id="pack" />
			</div>
		</div>

		<div class="col-md-6">
			<div class="form-group">
				<label for="quantityPerPack"> <spring:message
						code="quantity.per.pack" text="Quantity Per Pack"></spring:message>
					<span class="ml-2 text-danger">*</span></label> <input type="text"
					class="form-control integer-input" id="quantityPerPack"
					disabled="disabled" />
			</div>
		</div>

		<div class="col-md-6">
			<div class="form-group">
				<label for="totalQuantity"> <spring:message
						code="product.quantity"></spring:message> <span
					class="ml-2 text-danger">*</span></label> <input type="text"
					class="form-control integer-input" id="totalQuantity"
					disabled="disabled" />
			</div>
		</div>

	</div>

	<div class="row">
		<div class="col-md-12 text-right">
			<button class="btn btn-primary addProduct text-right" type="button"
				id="addProduct" style="text-align: right;">
				<spring:message code="add.item" text="Add Item"></spring:message>
			</button>
		</div>
	</div>
</div>