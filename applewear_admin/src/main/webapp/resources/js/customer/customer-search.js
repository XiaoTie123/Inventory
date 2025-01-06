$(document).ready(function() {
	if ($('#customer-data-table').length) {
		var dataTable = $('#customer-data-table').DataTable({
			searching: false,
			ordering: false,
			bLengthChange: false,
			proccessing: true,
			serverSide: true,
			ajax: {
				'url': 'customer-search.json',
				'contentType': 'application/json; charset=utf-8',
				'type': 'POST',
				'dataType': 'json',
				'data': function(d) {
					d.name = $('#name').val();
					d.email = $('#email').val();
					d.phoneNo = $('#phoneNo').val();
					return JSON.stringify(d);
				}
			},
			columns: [
				{ 'data': 'customerId', 
	            	className: "text-center", 
	            	'render': function(data, type, row, meta){ 
						data = '<a href="customer-setup.html?id='+row.customerId+'"><span class="feather icon-edit table-icon"></span></a>';
						data += '<a href="javascript:void(0)" class="delete-customer ml-3 text-danger" data-id="' + row.customerId + '"><span class="feather icon-trash table-icon"></span></a>'; 
						return data;
					} 
	            },
				
				{
					'data': 'num',
					className: "text-right"
				},
				{ 'data': 'name', className: "text-left" },
				{ 'data': 'rankName', className: "text-left" },
				{ 'data': 'mobile', className: "text-left" },
				{ 'data': 'address', className: "text-left" },
				

			]
		});

		if ($('#btn-search').length) {
			$('#btn-search').on('click', function() {
				dataTable.ajax.reload()
			})
		}
		
		$(document).on('click', '.delete-customer', function() {
			let id = $(this).data('id');

			swal({
				title: "Confirmation",
				text: "Are your sure, you want to delete?",
				buttons: true,
				buttons: ["No", "Yes"],
				dangerMode: true,
			}).then((willDelete) => {
				if (willDelete) {

					deleteCustomerById(id);

				} else {
					console.log("cancel")
				}
			});

		});

		function deleteCustomerById(id) {

			$.ajax({
				url: url + '/deleteCustomerById.json',
				type: 'POST',
				contentType: 'application/json',
				data: JSON.stringify(id),
				success: function(response) {
					console.log(response);
					if (response == "1") {
						swal("", "Customer deleted successfully.", "success");
						location.reload();

					} else {
						swal("", response, "error");
					}
				},
				error: function(xhr, status, error) {
					console.error('Error:', error);
					swal("", "Failed to delete customer.", "error");
				}
			});

		}
		
	}
});