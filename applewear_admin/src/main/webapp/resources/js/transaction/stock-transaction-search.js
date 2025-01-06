$(document).ready(function() {
	if ($('#stock-transaction-data-table').length) {
		var dataTable = $('#stock-transaction-data-table').DataTable({
			searching: false,
			ordering: false,
			bLengthChange: false,
			proccessing: true,
			serverSide: true,
			ajax: {
				'url': 'stock-transaction-search.json',
				'contentType': 'application/json; charset=utf-8',
				'type': 'POST',
				'dataType': 'json',
				'data': function(d) {
					d.productName = $('#productName').val();
					d.refNo = $('#refNo').val();
					
					return JSON.stringify(d);
				}
			},
			columns: [
				
				{ 'data': 'stockTransactionId', 
	            	className: "text-center", 
	            	'render': function(data, type, row, meta){ 
						data = '<a href="stock-transaction-setup.html?id='+row.stockTransactionId+'"><span class="feather icon-edit table-icon"></span></a>'; 
						return data;
					} 
	            },
				{
					'data': 'num',
					className: "text-right"
				},
				{ 'data': 'productName', className: "text-left" },
				{ 'data': 'transactionTypeName', className: "text-left" },
				{ 'data': 'quantity', className: "text-left" },
				{ 'data': 'refNo', className: "text-left" },
				{ 'data': 'attachment', className: "text-left" },
				{ 'data': 'remark', className: "text-left" },
				{ 'data': 'submittedByName', className: "text-left" },
				{ 'data': 'createdDate', className: "text-left" },
				

			]
		});

		if ($('#btn-search').length) {
			$('#btn-search').on('click', function() {
				dataTable.ajax.reload()
			})
		}
	}
});