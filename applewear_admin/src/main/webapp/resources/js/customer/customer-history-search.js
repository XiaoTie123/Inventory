$(document).ready(function() {
	if ($('#customer-data-table').length) {
		var dataTable = $('#customer-data-table').DataTable({
			searching: false,
			ordering: false,
			bLengthChange: false,
			proccessing: true,
			serverSide: true,
			ajax: {
				'url': 'customer-history-search.json',
				'contentType': 'application/json; charset=utf-8',
				'type': 'POST',
				'dataType': 'json',
				'data': function(d) {
					d.name = $('#name').val();
					d.email = $('#email').val();
					d.phoneNo = $('#phoneNo').val();
					d.loginId = $('#loginId').val();
					return JSON.stringify(d);
				}
			},
			columns: [
				{
					'data': 'customerId',
					className: "text-center",
					'render': function(data, type, row, meta) {
						let toViewOrder = $('#toViewOrder').val();
						data = '<a href="customer-order-history.html?customerId=' + row.customerId + '" class="btn btn-primary btn-sm">' + toViewOrder + '</a>';
						return data;
					}
				},

				{
					'data': 'num',
					className: "text-right"
				},
				{ 'data': 'name', className: "text-left" },
				{ 'data': 'rankName', className: "text-left" },
				{ 'data': 'loginId', className: "text-left" },
				{ 'data': 'mobile', className: "text-left" },
				{ 'data': 'address', className: "text-left" },


			]
		});

		if ($('#btn-search').length) {
			$('#btn-search').on('click', function() {
				dataTable.ajax.reload()
			})
		}
	}
});