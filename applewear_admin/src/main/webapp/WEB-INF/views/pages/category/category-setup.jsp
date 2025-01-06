<%@ include file="../../include/tag-template.jsp"%>

<div class="row">
	<div class="col-sm-12">
		<!-- start card -->
		<div class="card">
			<div class="card-header">
				<h5>Category Setup</h5>
			</div>
			<div class="card-body">
				<form:form action="category-setup.html" modelAttribute="categoryDTO">
					<form:hidden path="categoryId" />
					<div class="row mb-3">
						<div class="col-md-6">
							<div class="form-group">
								<label for="categoryName"> Name </label>
								<form:input class="form-control bg-white" path="categoryName"></form:input>
								<form:errors path="categoryName" class="text-danger text-left"
									element="div" />
							</div>
						</div>
					</div>

					<div class="row mb-3">
						<div class="col-md-12 text-right">
							<button type="submit" class="btn btn-primary mr-3">Save
							</button>
							<a href="category-setup.html" class="btn btn-warning mr-3">
								Clear </a>
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
									<c:forEach var="row" items="${categoryList }"
										varStatus="status">
										<tr>
											<td><a href="category-setup.html?id=${row.categoryId }"><span
													class="feather icon-edit table-icon"></span></a> <a
												href="javascript:void(0)"
												class="delete-category ml-3 text-danger"
												data-id="${row.categoryId }"><span
													class="feather icon-trash table-icon"></span></a></td>
											<td>${status.count }</td>
											<td>${row.categoryName }</td>
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

<script src="<%=request.getContextPath()%>/resources/js/vendor-all.min.js"></script>
<script>
		
$(document).on('click', '.delete-category', function() {
	let id = $(this).data('id');

	swal({
		title: "Confirmation",
		text: "Are your sure, you want to delete?",
		buttons: true,
		buttons: ["No", "Yes"],
		dangerMode: true,
	}).then((willDelete) => {
		if (willDelete) {

			deleteCategoryById(id);

		} else {
			console.log("cancel")
		}
	});

});

function deleteCategoryById(id) {

	$.ajax({
		url: url + '/deleteCategoryById.json',
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(id),
		success: function(response) {
			console.log(response);
			if (response == "1") {
				swal("", "Category deleted successfully.", "success");
				location.reload();

			} else {
				swal("", response, "error");
			}
		},
		error: function(xhr, status, error) {
			console.error('Error:', error);
			swal("", "Failed to delete categroy.", "error");
		}
	});

}
		
</script>