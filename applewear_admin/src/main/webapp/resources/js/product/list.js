$(document).ready(function() {
	if ($('#customer-data-table').length) {
		var dataTable = $('#customer-data-table').DataTable({
			searching: false,
			ordering: false,
			bLengthChange: false,
			proccessing: true,
			serverSide: true,
			ajax: {
				'url': 'product-search.json',
				'contentType': 'application/json; charset=utf-8',
				'type': 'POST',
				'dataType': 'json',
				'data': function(d) {
					d.productCode = $('#productCode').val();
					d.brandId = $('#brandId').val();
					d.categoryId = $('#categoryId').val();
					d.sizeId = $('#sizeId').val();
					d.sellingPrice = $('#sellingPrice').val();
					return JSON.stringify(d);
				}
			},
			columns: [
				{
					'data': 'customerId',
					className: "text-center",
					'render': function(data, type, row, meta) {
						data = '<a href="product-setup.html?id=' + row.productId + '"><span class="feather icon-edit table-icon"></span></a>';
						data += '<a href="javascript:void(0)" class="delete-product ml-3 text-danger" data-id="' + row.productId + '"><span class="feather icon-trash table-icon"></span></a>';
						return data;
					}
				},


				{ 'data': 'productId', className: "text-left" },
				{ 'data': 'productCode', className: "text-left" },
				{ 'data': 'brandName', className: "text-left" },
				{
					'data': 'productPhoto',
					className: "text-center",
					'render': function(data, type, row, meta) {

						if (row.productPhoto) {
							data = `
						        <a href="${row.productPhoto}" data-lightbox="1">
						            <img class="img-fluid img-thumbnail product-image"
						                 src="${row.productPhoto}" alt="Product Image"
						                 style="max-height: 50px;">
						        </a>
						    `;
						}


						return data;
					}
				},
				
				{ 'data': 'categoryName', className: "text-left" },
				{ 'data': 'sizeName', className: "text-left" },
				{ 'data': 'userName', className: "text-left" },
				{ 'data': 'sellingPrice', className: "text-left" },


			]
		});

		if ($('#btn-search').length) {
			$('#btn-search').on('click', function() {
				dataTable.ajax.reload()
			})
		}

		$(document).on('click', '.delete-product', function() {
			let id = $(this).data('id');

			swal({
				title: "Confirmation",
				text: "Are your sure, you want to delete?",
				buttons: true,
				buttons: ["No", "Yes"],
				dangerMode: true,
			}).then((willDelete) => {
				if (willDelete) {

					deleteProductById(id);

				} else {
					console.log("cancel")
				}
			});

		});

		function deleteProductById(id) {

			$.ajax({
				url: url + '/deleteProductById.json',
				type: 'POST',
				contentType: 'application/json',
				data: JSON.stringify(id),
				success: function(response) {
					console.log(response);
					if (response == "1") {
						swal("", "Product deleted successfully.", "success");
						location.reload();

					} else {
						swal("", response, "error");
					}
				},
				error: function(xhr, status, error) {
					console.error('Error:', error);
					swal("", "Failed to delete product.", "error");
				}
			});

		}
	}
});