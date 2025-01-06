<%@ include file="../../include/tag-template.jsp"%>

<div class="row">
	<div class="col-sm-12">
		<div class="card">
			<div class="card-header">
				<h5>Product Search</h5>
			</div>
			<div class="card-body">
				<form:form action="list.html" method="post"
					modelAttribute="productSearchDTO">
					<div class="row mb-3">
						<div class="col-md-6">
							<div class="form-group">
								<label for="productCode"> ${list1} </label>
								<form:input path="productCode" class="form-control bg-white"
									id="productCode" />
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="fromDate"> ${list2} </label>
								<form:select path="brandId" class="form-control bg-white">
									<form:option value="-1">All</form:option>
									<form:options items="${brandList}" itemLabel="brandName"
										itemValue="brandId"></form:options>
								</form:select>
							</div>
						</div>

					</div>

					<div class="row mb-3">
						<div class="col-md-6">
							<div class="form-group">
								<label for="fromDate"> ${list3} </label>
								<form:select path="categoryId" class="form-control bg-white">
									<form:option value="-1">All</form:option>
									<form:options items="${categoryList}" itemLabel="categoryName"
										itemValue="categoryId"></form:options>
								</form:select>
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="fromDate"> ${list4} </label>
								<form:select path="sizeId" class="form-control bg-white">
									<form:option value="-1">All</form:option>
									<form:options items="${sizeList}" itemLabel="sizeCode"
										itemValue="sizeId"></form:options>
								</form:select>
							</div>
						</div>

					</div>

					<div class="row mb-3">
						<div class="col-md-6">
							<div class="form-group">
								<label for="productCode"> ${list6} </label>
								<form:input path="sellingPrice" class="form-control bg-white"
									id="sellingPrice" />
							</div>
						</div>
					</div>
					<div class="row mb-3">
						<div class="col-md-12 text-right">
							<button id="btn-search" type="button"
								class="btn btn-primary mr-3">Search</button>
						</div>
					</div>

				</form:form>
				<div class="row mb-3">
					<div class="col-md-12">
						<div class="table-responsive">
							<table id="customer-data-table"
								class="display table table-striped table-hover"
								style="width: 100%">
								<thead>
									<tr>
										<th></th>
										<th style="width: 5%; padding-bottom: 23px;">${list}</th>
										<th>${list1}</th>
										<th>${list2}</th>
										<th>Image</th>
										<th>${list3}</th>
										<th>${list4}</th>
										<th>${list5}</th>
										<th>${list6}</th>
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
	src="<%=request.getContextPath()%>/resources/js/product/list.js?modified=293335222"></script>