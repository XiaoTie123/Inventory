$(document).ready(function() {
	if ($('#sale-yearly-data-table').length) {
		var dataTable = $('#sale-yearly-data-table').DataTable({
			searching: false,
			ordering: false,
			bLengthChange: false,
			proccessing: true,
			serverSide: true,
			ajax: {
				'url': 'sale-yearly-report.json',
				'contentType': 'application/json; charset=utf-8',
				'type': 'POST',
				'dataType': 'json',
				'data': function(d) {
					d.fromYear = $('#fromYear').val();
					d.toYear = $('#toYear').val();
					return JSON.stringify(d);
				}
			},
			columns: [

				{ 'data': 'year', className: "text-left" },
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