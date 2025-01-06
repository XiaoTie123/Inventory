$(document).ready(function() {
	if ($('#sale-monthly-data-table').length) {
		var dataTable = $('#sale-monthly-data-table').DataTable({
			searching: false,
			ordering: false,
			bLengthChange: false,
			proccessing: true,
			serverSide: true,
			ajax: {
				'url': 'sale-monthly-report.json',
				'contentType': 'application/json; charset=utf-8',
				'type': 'POST',
				'dataType': 'json',
				'data': function(d) {
					d.fromMonthYear = $('#fromMonthYear').val();
					d.toMonthYear = $('#toMonthYear').val();
					return JSON.stringify(d);
				}
			},
			columns: [
				
				{ 'data': 'year', className: "text-left" },
				{ 'data': 'month', className: "text-left" },
				{ 'data': 'totalOrders', className: "text-left" },
				{ 'data': 'totalAmountDesc', className: "text-left" },
				{ 'data': 'totalPaidDesc', className: "text-left" },
				
				

			]
		});

		if ($('#btn-search').length) {
			$('#btn-search').on('click', function() {
				dataTable.ajax.reload()
			})
		}
	}
});