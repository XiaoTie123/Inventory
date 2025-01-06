$(document).ready(function() {
	if ($('#sale-daily-data-table').length) {
		var dataTable = $('#sale-daily-data-table').DataTable({
			searching: false,
			ordering: false,
			bLengthChange: false,
			proccessing: true,
			serverSide: true,
			ajax: {
				'url': 'sale-daily-report.json',
				'contentType': 'application/json; charset=utf-8',
				'type': 'POST',
				'dataType': 'json',
				'data': function(d) {
					d.fromDate = $('#fromDate').val();
					d.toDate = $('#toDate').val();
					return JSON.stringify(d);
				}
			},
			columns: [
				
				{ 'data': 'saleDate', className: "text-left" },
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