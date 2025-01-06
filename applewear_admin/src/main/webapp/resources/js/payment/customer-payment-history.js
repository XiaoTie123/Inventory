$(document).ready(function() {
	if ($('#payment-history-data-table').length) {
		var dataTable = $('#payment-history-data-table').DataTable({
			searching: false,
			ordering: false,
			bLengthChange: false,
			proccessing: true,
			serverSide: true,
			ajax: {
				'url': 'customer-payment-history.json',
				'contentType': 'application/json; charset=utf-8',
				'type': 'POST',
				'dataType': 'json',
				'data': function(d) {
					d.customerId = $('#customerId').val();
					return JSON.stringify(d);
				}
			},
			columns: [
				{ 'data': 'paymentId', 
	            	className: "text-center", 
	            	'render': function(data, type, row, meta){ 
						data = '<a href="customer-payment-setup.html?id='+row.paymentId+'"><span class="feather icon-edit table-icon"></span></a>'; 
						return data;
					} 
	            },
		
				{ 'data': 'paymentDate', className: "text-left" },
				{ 'data': 'orderRef', className: "text-left" },
				{ 'data': 'totalPaidAmountText', className: "text-left" },
			
							

			]
		});

		if ($('#btn-search').length) {
			$('#btn-search').on('click', function() {
				dataTable.ajax.reload()
			})
		}
	}
});