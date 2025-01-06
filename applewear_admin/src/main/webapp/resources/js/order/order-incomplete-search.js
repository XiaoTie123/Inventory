$(document).ready(function() {
	if ($('#incomplete-order-data-table').length) {
		var dataTable = $('#incomplete-order-data-table').DataTable({
			searching: false,
			ordering: false,
			bLengthChange: false,
			proccessing: true,
			serverSide: true,
			ajax: {
				'url': 'order-incomplete-search.json',
				'contentType': 'application/json; charset=utf-8',
				'type': 'POST',
				'dataType': 'json',
				'data': function(d) {
					
					return JSON.stringify(d);
				}
			},
			columns: [
				
				{ 'data': 'orderRef', className: "text-left" },
				{ 'data': 'orderDate', className: "text-left" },
				{ 'data': 'totalAmountText', className: "text-left" },
				{ 'data': 'totalPaidText', className: "text-left" },
				{ 'data': 'discountText', className: "text-left" },
				{ 'data': 'customerName', className: "text-left" },
			
				

			]
		});

		if ($('#btn-search').length) {
			$('#btn-search').on('click', function() {
				dataTable.ajax.reload()
			})
		}
	}
});