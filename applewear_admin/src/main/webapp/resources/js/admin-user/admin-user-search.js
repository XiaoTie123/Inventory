$(document).ready(function() {
	if ($('#user-data-table').length) {
		var dataTable = $('#user-data-table').DataTable({
			searching: false,
			ordering: false,
			bLengthChange: false,
			proccessing: true,
			serverSide: true,
			ajax: {
				'url': 'admin-user-search.json',
				'contentType': 'application/json; charset=utf-8',
				'type': 'POST',
				'dataType': 'json',
				'data': function(d) {
					d.name = $('#name').val();
					d.loginName = $('#loginName').val();
					d.phoneNo = $('#phoneNo').val();
					d.userRoleId = $('#userRoleId').val();
					d.status = $('#status').val();
					d.deviceId = $('#deviceId').val();
					return JSON.stringify(d);
				}
			},
			columns: [
				{ 
					'data': 'userId', 
	            	className: "text-center", 
	            	'render': function(data, type, row, meta){ 
						data = '<a href="admin-user-setup.html?id='+row.userId+'"><span class="feather icon-edit table-icon"></span></a>'; 
						return data;
					} 
	            },
				{
					'data': 'num',
					className: "text-right"
				},
				{ 'data': 'name', className: "text-left" },
				{ 'data': 'loginName', className: "text-left" },
				{ 'data': 'phoneNo', className: "text-left" },				
				{ 'data': 'userRoleName', className: "text-left" },
				{ 'data': 'statusName', className: "text-left" },
				{ 'data': 'deviceIdName', className: "text-left" },

			]
		});

		if ($('#btn-search').length) {
			$('#btn-search').on('click', function() {
				dataTable.ajax.reload()
			})
		}
	}
});