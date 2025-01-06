<%@ include file="../../include/tag-template.jsp"%>

<div class="row">
	<div class="col-sm-12">
		<!-- start card -->
		<div class="card">
			<div class="card-header">
				<h5>Product Setup</h5>
			</div>
			<div class="card-body">
				<form:form action="product-setup.html" modelAttribute="productDTO"
					enctype="multipart/form-data">
					<form:hidden path="productId" />
					<div class="row mb-3">

						<div class="col-md-6">
							<div class="form-group">
								<label for="productCode"> Product Code </label>
								<form:input class="form-control bg-white" path="productCode"></form:input>
								<form:errors path="productCode" class="text-danger text-left"
									element="div" />
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="quantity"> Quantity Per Pack </label>
								<form:input class="form-control bg-white" path="quantity"></form:input>
								<form:errors path="quantity" class="text-danger text-left"
									element="div" />
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="brandId"> Brand </label>
								<form:select path="brandId" class="form-control bg-white">
									<form:option value="-1">Select Brand</form:option>
									<form:options items="${brandList}" itemLabel="brandName"
										itemValue="brandId"></form:options>
								</form:select>
								<form:errors path="brandId" class="text-danger text-left"
									element="div" />
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="categoryId"> Category </label>
								<form:select path="categoryId" class="form-control bg-white">
									<form:option value="-1">Select Category</form:option>
									<form:options items="${categoryList}" itemLabel="categoryName"
										itemValue="categoryId"></form:options>
								</form:select>
								<form:errors path="categoryId" class="text-danger text-left"
									element="div" />
							</div>
						</div>


						<div class="col-md-6">
							<div class="form-group">
								<label for="sizeId"> Size </label>
								<form:select path="sizeId" class="form-control bg-white">
									<form:option value="-1">Select Size</form:option>
									<form:options items="${sizeList}" itemLabel="sizeCode"
										itemValue="sizeId"></form:options>
								</form:select>
								<form:errors path="sizeId" class="text-danger text-left"
									element="div" />
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="buyingPrice"> Buying Price </label>
								<form:input class="form-control bg-white" path="buyingPrice"></form:input>
								<form:errors path="buyingPrice" class="text-danger text-left"
									element="div" />
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="margin"> Margin% </label>
								<form:input class="form-control bg-white" path="margin"></form:input>
								<form:errors path="margin" class="text-danger text-left"
									element="div" />
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="transportation"> Transportation </label>
								<form:input class="form-control bg-white" path="transportation"></form:input>
								<form:errors path="transportation" class="text-danger text-left"
									element="div" />
							</div>
						</div>


						<div class="col-md-6">
							<div class="form-group">
								<label for="sellingPrice"> Selling Price </label>
								<form:input class="form-control bg-white" path="sellingPrice"></form:input>
								<form:errors path="sellingPrice" class="text-danger text-left"
									element="div" />
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="sizeId"> Product Type </label>
								<form:select path="oneTimeProduct" class="form-control bg-white">
									<form:option value="-1">Select Product Type</form:option>
									<form:options items="${productTypeList}" itemLabel="desc"
										itemValue="code"></form:options>
								</form:select>
								<form:errors path="sizeId" class="text-danger text-left"
									element="div" />
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<form:label path="productImageFile">
										Product Image
									</form:label>
								<div class="custom-file">
									<input type="file" class="custom-file-input form-control"
										id="productImageFile" name="productImageFile" accept="image/*">
									<label class="custom-file-label product-image-label"
										for="productImageFile"> ${not empty productDTO.productPhoto ? 'product_image.jpg':'No file chosen...'}
									</label>
								</div>

								<br />
								<c:if
									test="${not empty productDTO.productPhoto}">
									<a href="${productDTO.productPhoto}" data-lightbox="1"
										data-title=""> <img
										class="img-fluid img-thumbnail product-image"
										src="${productDTO.productPhoto}" alt="Product Image"
										style="max-height: 70px;">
									</a>
								</c:if>
								<img class="product-image-preview" style="max-height: 70px;">
							</div>

						</div>

					</div>

					<div class="row mb-3">
						<div class="col-md-12 text-right">
							<button type="submit" class="btn btn-primary mr-3">Save
							</button>
							<a href="list.html" class="btn btn-primary mr-3"> List </a> <a
								href="product-setup.html" class="btn btn-warning mr-3">
								Clear </a>

						</div>
					</div>
				</form:form>




			</div>
		</div>
		<!-- end card -->
	</div>
</div>

<script src="<%=request.getContextPath()%>/resources/js/vendor-all.min.js"></script>
<script>
$("#productImageFile").change(function(e) {
	e.preventDefault();
	console.log("image changed")
	$(".product-image").hide();
	$(".product-image-label").text("product_image.jpg");
	$(".product-image-preview").addClass("img-fluid img-thumbnail");
	readURL(this, $(".product-image-preview"));
});
</script>