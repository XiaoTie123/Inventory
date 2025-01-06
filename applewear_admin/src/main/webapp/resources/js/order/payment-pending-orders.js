$(document).ready(function() {
	if ($('#payment-pending-orders-data-table').length) {
		var dataTable = $('#payment-pending-orders-data-table').DataTable({
			searching: false,
			ordering: false,
			bLengthChange: false,
			proccessing: true,
			serverSide: true,
			ajax: {
				'url': 'payment-pending-orders.json',
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
					'data': 'orderId',
					className: "text-center",
					'render': function(data, type, row, meta) {
						let makePayment = $('#makePayment').val();

						if ($('#isEditPayment').val() == 'true') {
							data = '<a href="customer-payment-setup.html?orderId=' + row.orderId + '&customerId=' + row.customerId + '" class="btn btn-primary btn-sm">' + makePayment + '</a>';
						}else{
							data ='';
						}
						
						

						data += '<a href="customer_invoice.html?orderId=' + row.orderId + '" class="ml-3 btn btn-sm btn-primary" target="_blank">Print</a>';
						return data;
					}
				},

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