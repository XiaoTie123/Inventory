$(document).ready(function() {
	if ($('#customer-data-table').length) {
		var dataTable = $('#customer-data-table').DataTable({
			searching: false,
			ordering: false,
			bLengthChange: false,
			proccessing: true,
			serverSide: true,
			ajax: {
				'url': 'admin-search.json',
				'contentType': 'application/json; charset=utf-8',
				'type': 'POST',
				'dataType': 'json',
				'data': function(d) {
					d.adminName = $('#adminName').val();
					d.email = $('#email').val();
					d.roleId = $('#roleId').val();
					return JSON.stringify(d);
				}
			},
			columns: [
				{ 'data': 'adminId', 
	            	className: "text-center", 
	            	'render': function(data, type, row, meta){ 
						data = '<a href="admin-setup.html?id='+row.adminId+'"><span class="feather icon-edit table-icon"></span></a>';
						data += '<a href="javascript:void(0)" class="delete-admin ml-3 text-danger" data-id="' + row.adminId + '"><span class="feather icon-trash table-icon"></span></a>'; 
						return data;
					} 
	            },
				
				
				{ 'data': 'adminId', className: "text-left" },
				{ 'data': 'adminName', className: "text-left" },
				{ 'data': 'loginName', className: "text-left" },
				{ 'data': 'email', className: "text-left" },
				{ 'data': 'roleName', className: "text-left" },
				{ 'data': 'passwordName', className: "text-left" },
			]
		});

		if ($('#btn-search').length) {
			$('#btn-search').on('click', function() {
				dataTable.ajax.reload()
			})
		}
		
		$(document).on('click', '.delete-admin', function() {
			let id = $(this).data('id');

			swal({
				title: "Confirmation",
				text: "Are your sure, you want to delete?",
				buttons: true,
				buttons: ["No", "Yes"],
				dangerMode: true,
			}).then((willDelete) => {
				if (willDelete) {

					deleteAdminById(id);

				} else {
					console.log("cancel")
				}
			});

		});

		function deleteAdminById(id) {

			$.ajax({
				url: url + '/deleteAdminById.json',
				type: 'POST',
				contentType: 'application/json',
				data: JSON.stringify(id),
				success: function(response) {
					console.log(response);
					if (response == "1") {
						swal("", "Admin deleted successfully.", "success");
						location.reload();

					} else {
						swal("", response, "error");
					}
				},
				error: function(xhr, status, error) {
					console.error('Error:', error);
					swal("", "Failed to delete admin.", "error");
				}
			});

		}
		
	}
});