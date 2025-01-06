$(document).ready(function() {
	if ($('#payment-completed-orders-data-table').length) {
		var dataTable = $('#payment-completed-orders-data-table').DataTable({
			searching: false,
			ordering: false,
			bLengthChange: false,
			proccessing: true,
			serverSide: true,
			ajax: {
				'url': 'payment-completed-orders.json',
				'contentType': 'application/json; charset=utf-8',
				'type': 'POST',
				'dataType': 'json',
				'data': function(d) {
					d.orderRef = $('#orderRef').val();
					d.fromDate = $('#fromDate').val();
					d.toDate = $('#toDate').val();
					d.customerId = $('#customerId').val();
					return JSON.stringify(d);
				}
			},
			columns: [
				
				
				{
					'data': 'num',
					className: "text-right"
				},
				{ 'data': 'orderRef', className: "text-left" },
				{ 'data': 'orderDate', className: "text-left" },
				{ 'data': 'customerName', className: "text-left" },
				{ 'data': 'totalAmountText', className: "text-left" },
				{ 'data': 'totalPaidText', className: "text-left" },
				{ 'data': 'remainingAmountText', className: "text-left" },
							

			]
		});

		if ($('#btn-search').length) {
			$('#btn-search').on('click', function() {
				dataTable.ajax.reload()
			})
		}
	}
});