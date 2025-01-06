$(document).ready(function() {
	if ($('#order-data-table').length) {
		var dataTable = $('#order-data-table').DataTable({
			searching: false,
			ordering: false,
			bLengthChange: false,
			proccessing: true,
			serverSide: true,
			ajax: {
				'url': 'order-search.json',
				'contentType': 'application/json; charset=utf-8',
				'type': 'POST',
				'dataType': 'json',
				'data': function(d) {
					d.orderRef = $('#orderRef').val();
					d.fromDate = $('#fromDate').val();
					d.toDate = $('#toDate').val();
					d.customerName = $('#customerName').val();
					d.customerPhone = $('#customerPhone').val();
					d.orderStatus = $('#orderStatus').val();
					return JSON.stringify(d);
				}
			},
			columns: [
				{
					'data': 'orderId',
					className: "text-center",
					'render': function(data, type, row, meta) {
						data = '<a href="order-setup.html?id=' + row.orderId + '"><span class="feather icon-edit table-icon"></span></a>';
						data += '<a href="javascript:void(0)" class="delete-order ml-3 text-danger" data-id="' + row.orderId + '"><span class="feather icon-trash table-icon"></span></a>';
						data += '<a href="invoice.html?orderId=' + row.orderId + '" class="ml-3 btn btn-sm btn-primary">Print</a>';
						return data;
					}
				},

				{
					'data': 'num',
					className: "text-right"
				},
				{ 'data': 'orderRef', className: "text-left" },
				{ 'data': 'orderDate', className: "text-left" },
				{ 'data': 'totalAmountText', className: "text-left" },
				{ 'data': 'customerName', className: "text-left" },
				{ 'data': 'customerRank', className: "text-left" },
				{ 'data': 'customerPhone', className: "text-left" },
				{ 'data': 'customerPhone', className: "text-left" },
				{ 'data': 'orderStatusDesc', className: "text-left" },
				{ 'data': 'submittedByName', className: "text-left" },


			]
		});

		if ($('#btn-search').length) {
			$('#btn-search').on('click', function() {
				dataTable.ajax.reload()
			})
		}

		$(document).on('click', '.delete-order', function() {
			let orderId = $(this).data('id');

			swal({
				title: "Confirmation",
				text: "Are your sure, you want to delete?",
				buttons: true,
				buttons: ["No", "Yes"],
				dangerMode: true,
			}).then((willDelete) => {
				if (willDelete) {

					deleteOrderById(orderId);

				} else {
					console.log("cancel")
				}
			});

		});

		function deleteOrderById(orderId) {

			$.ajax({
				url: url + '/deleteOrderById.json',
				type: 'POST',
				contentType: 'application/json',
				data: JSON.stringify(orderId),
				success: function(response) {
					console.log(response);
					if (response == "1") {
						swal("", "Order deleted successfully.", "success");
						location.reload();

					} else {
						swal("", "Failed to delete order.", "error");
					}
				},
				error: function(xhr, status, error) {
					console.error('Error:', error);
					swal("", "Failed to delete order.", "error");
				}
			});

		}
	}
});