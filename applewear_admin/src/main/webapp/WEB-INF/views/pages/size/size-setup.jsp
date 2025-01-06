<%@ include file="../../include/tag-template.jsp"%>

<div class="row">
	<div class="col-sm-12">
		<!-- start card -->
		<div class="card">
			<div class="card-header">
				<h5>Size Setup</h5>
			</div>
			<div class="card-body">
				<form:form action="size-setup.html" modelAttribute="sizeDTO">
					<form:hidden path="sizeId" />
					<div class="row mb-3">
						<div class="col-md-6">
							<div class="form-group">
								<label for="sizeName"> Name </label>
								<form:input class="form-control bg-white" path="sizeName"></form:input>
								<form:errors path="sizeName" class="text-danger text-left"
									element="div" />
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="sizeCode"> Code </label>
								<form:input class="form-control bg-white" path="sizeCode"></form:input>
								<form:errors path="sizeCode" class="text-danger text-left"
									element="div" />
							</div>
						</div>
					</div>

					<div class="row mb-3">
						<div class="col-md-12 text-right">
							<button type="submit" class="btn btn-primary mr-3">Save
							</button>
							<a href="size-setup.html" class="btn btn-warning mr-3"> Clear
							</a>
						</div>
					</div>
				</form:form>

				<div class="row mb-3">
					<div class="col-md-12">
						<div class="table-responsive">
							<table class="display table table-striped table-hover"
								style="width: 100%">
								<thead>
									<tr>
										<th></th>
										<th>No</th>
										<th>Name</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="row" items="${sizeList }" varStatus="status">
										<tr>
											<td><a href="size-setup.html?id=${row.sizeId }"><span
													class="feather icon-edit table-icon"></span></a></td>
											<td>${status.count }</td>
											<td>${row.sizeName }</td>
											<td>${row.sizeCode }</td>

										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>


			</div>
		</div>
		<!-- end card -->
	</div>
</div>