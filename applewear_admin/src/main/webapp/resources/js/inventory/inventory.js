$(document).ready(function() {
	if ($('#inventory-data-table').length) {
		var dataTable = $('#inventory-data-table').DataTable({
			searching: false,
			ordering: false,
			bLengthChange: false,
			proccessing: true,
			serverSide: true,
			ajax: {
				'url': 'inventory-search.json',
				'contentType': 'application/json; charset=utf-8',
				'type': 'POST',
				'dataType': 'json',
				'data': function(d) {
					d.productName = $('#productName').val();
					d.productCode = $('#productCode').val();
					return JSON.stringify(d);
				}
			},
			columns: [


				{
					'data': 'num',
					className: "text-right"
				},
				{ 'data': 'productCode', className: "text-left" },
				{ 'data': 'productName', className: "text-left" },
				{ 'data': 'quantity', className: "text-left" },



			]
		});

		if ($('#btn-search').length) {
			$('#btn-search').on('click', function() {
				dataTable.ajax.reload()
			})
		}
	}
});