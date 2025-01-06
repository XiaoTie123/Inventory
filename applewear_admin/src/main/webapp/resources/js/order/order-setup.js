$(document).ready(function() {

	$('#customerId').on('change', function() {
		var mobile = $(this).find(':selected').data('mobile');
		var address = $(this).find(':selected').data('address');
		var rankName = $(this).find(':selected').data('rankname');

		console.log(rankName);

		if (mobile) {
			$("#customerPhone").val(mobile);
		}

		if (address) {
			$('#customerAddress').val(address);
		}

		if (rankName) {
			$('#customerRank').val($.trim(rankName));
		}

	});



	$("#customerId").select2({

	});

	$("#productId").select2({

	});

	$('#productId').on('change', function() {
		var sellingprice = $(this).find(':selected').data('sellingprice');
		var quantityperpack = $(this).find(':selected').data('quantityperpack');
		var buyPrice = $(this).find(':selected').data('buyingprice');

		console.log("sell price: " + sellingprice);
		console.log("quantity per pack: " + quantityperpack)
		console.log("buy price: " + buyPrice);

		if (sellingprice) {
			$("#sellingPrice").val(sellingprice);
		}

		if (buyPrice) {
			$("#originalPrice").val(buyPrice);
		}

		if (quantityperpack) {
			console.log("greaterh");
			$('#quantityPerPack').val(quantityperpack);
		} else {
			$('#quantityPerPack').val(1);
		}


	});


	/*$('#productId').on('select2:select', function(e) {
		let selectedData = e.params.data;

		console.log("selected "+ JSON.stringify(selectedData));

		if (selectedData.sellingPrice) {
			$("#originalPrice").val(selectedData.sellingPrice);
		}


		if (selectedData.quantityPerPack) {
			$('#quantityPerPack').val(selectedData.quantityPerPack);
		} else {
			$('#quantityPerPack').val(1);
		}

	});*/


	var checkAddProductValication = function() {

		let productId = $('#productId').val();
		let originalPrice = $('#originalPrice').val();
		let discount = $('#discount').val();
		let sellingPrice = $('#sellingPrice').val();
		let pack = $('#pack').val();
		let totalQuantity = $('#totalQuantity').val();

		console.log("product id => " + productId);
		console.log("original price : " + originalPrice);
		console.log("discount => " + discount);
		console.log("selling price => " + sellingPrice);
		console.log("pack => " + pack);
		console.log("total quantity => " + totalQuantity)

		if (!productId || productId <= 0) {
			notify("Product is required.", "danger");
			return true;
		}

		if (!originalPrice || originalPrice <= 0) {
			notify("Original price is required.", "danger");
			return true;
		}

		if (!sellingPrice || sellingPrice <= 0) {
			notify("Selling price is required.", "danger");
			return true;
		}

		if (!pack || pack <= 0) {
			notify("Pack is required.", "danger");
			return true;
		}

		if (!totalQuantity || totalQuantity <= 0) {
			notify("Total quantity is required.", "danger");
			return true;
		}

		return false;


	};

	var prepareStockRow = function(isNew, rowIndex) {

		var count = isNew ? ($('#add-product-entry > tr').length + 1)
			: (parseInt(rowIndex) + 1);

		var productName = $('#productId option:selected').text();
		let productId = $('#productId').val();
		let originalPrice = $('#originalPrice').val();
		let discount = $('#discount').val();
		let sellingPrice = $('#sellingPrice').val();
		let pack = $('#pack').val();
		let quantityPerPack = $('#quantityPerPack').val();
		let totalQuantity = $('#totalQuantity').val();
		let totalAmount = 0;

		if (!totalQuantity) {
			totalQuantity = 0;
		}
		
		if(!quantityPerPack){
			quantityPerPack =1;
		}

		totalAmount = (sellingPrice * totalQuantity).toFixed(2);

		var row = '<tr ';
		row += 'data-row-index="' + (count - 1) + '" ';
		row += '>';
		row += '<td class="text-right">' + '<span class="tableRowNo">' + count + '</span>';

		row += '<input type="hidden" class="hid-productId" name="itemList['
			+ (count - 1)
			+ '].productId" value="'
			+ productId
			+ '">';

		row += '<input type="hidden" class="hid-productName" name="itemList['
			+ (count - 1)
			+ '].productName" value="'
			+ productName
			+ '">';

		row += '<input type="hidden" class="hid-pack" name="itemList['
			+ (count - 1)
			+ '].pack" value="'
			+ pack
			+ '">';

		row += '<input type="hidden" class="hid-originalPrice" name="itemList['
			+ (count - 1)
			+ '].originalPrice" value="'
			+ originalPrice
			+ '">';

		row += '<input type="hidden" class="hid-discount" name="itemList['
			+ (count - 1)
			+ '].discount" value="'
			+ discount
			+ '">';

		row += '<input type="hidden" class="hid-sellingPrice" name="itemList['
			+ (count - 1)
			+ '].sellingPrice" value="'
			+ sellingPrice
			+ '">';

		row += '<input type="hidden" class="hid-quantityPerPack" name="itemList['
			+ (count - 1)
			+ '].quantityPerPack" value="'
			+ quantityPerPack
			+ '">';

		row += '<input type="hidden" class="hid-quantity" name="itemList['
			+ (count - 1)
			+ '].quantity" value="'
			+ totalQuantity
			+ '">';

		row += '<input type="hidden" class="hid-totalAmount" name="itemList['
			+ (count - 1)
			+ '].totalAmount" value="'
			+ totalAmount
			+ '">';

		row += '</td>';
		row += '<td class="text-left">' + productName + '</td>';
		row += '<td class="text-left">' + originalPrice + '</td>';
		row += '<td class="text-left">' + discount + '</td>';
		row += '<td class="text-left">' + sellingPrice + '</td>';
		row += '<td class="text-left">' + pack + '</td>';
		row += '<td class="text-left">' + quantityPerPack + '</td>';
		row += '<td class="text-left">' + totalQuantity + '</td>';
		row += '<td class="text-left">' + totalAmount + '</td>';
		row += '<td>'
		row += '<div style="display:inline-flex; align-items: center; gap:5px; flex-wrap: nowrap;justify-content: center;">'
		row += '<a href="javascript:void(0)" class="btn-edit-icon edit-stock">'
		row += '<span class="feather icon-edit" style="font-size: 20px;"></span>'
		row += '</a>'
		row += '<a href="javascript:void(0)" class="ml-3 btn-delete-icon delete-stock">'
		row += '<span class="feather icon-trash-2" style="font-size: 20px;"></span>'
		row += '</a>'
		row += '</div>'
		row += '</td>';
		row += '</tr>';

		return row;



	}

	var addProductTable = function() {

		var isNew = $('#hid-is-new').val() === 'true';
		var rowIndex = $('#hid-update-row-index').val();
		var row = '';


		console.log("is new => " + isNew);
		console.log("index => " + rowIndex);

		if (isNew) {

			row = prepareStockRow(true, null);

			$('#add-product-entry').append(row);

		} else {

			$('#add-product-entry').find('tr').each(
				function(i, tr) {
					var idx = $(tr).data('row-index');
					if (idx == rowIndex) {
						row = prepareStockRow(false, rowIndex);
						var row = $(tr).replaceWith(row);
					}
				});
		}

		updateItemTable();

		resetStockInputForm();

	}

	var updateItemTable = function() {
		$('#add-product-entry').find('tr').each(function(i, tr) {
			$(tr).find('td').each(function(j, td) {
				if (j == 0) { // Update hidden input indexes
					// Update row number display text;
					$(td).find('input[type="hidden"][class="hid-productId"]').attr('name', 'itemList[' + i + '].productId');
					$(td).find('input[type="hidden"][class="hid-productName"]').attr('name', 'itemList[' + i + '].productName');
					$(td).find('input[type="hidden"][class="hid-pack"]').attr('name', 'itemList[' + i + '].pack');
					$(td).find('input[type="hidden"][class="hid-originalPrice"]').attr('name', 'itemList[' + i + '].originalPrice');
					$(td).find('input[type="hidden"][class="hid-discount"]').attr('name', 'itemList[' + i + '].discount');
					$(td).find('input[type="hidden"][class="hid-sellingPrice"]').attr('name', 'itemList[' + i + '].sellingPrice');
					$(td).find('input[type="hidden"][class="hid-quantityPerPack"]').attr('name', 'itemList[' + i + '].quantityPerPack');
					$(td).find('input[type="hidden"][class="hid-quantity"]').attr('name', 'itemList[' + i + '].quantity');
					$(td).find('input[type="hidden"][class="hid-totalAmount"]').attr('name', 'itemList[' + i + '].totalAmount');
					var label = $(td).find('span[class="tableRowNo"]');
					label.text(i + 1);

				}
			});

			$(this).data('row-index', i);

			updateItemTableFooter();

		});
		if ($('#add-product-entry').find('tr').length <= 0) {
			$('#hid-is-new').val('true');
		}

	}



	var updateItemTableFooter = function() {

		var subTotalAmount = 0;

		$('#add-product-entry').find('tr').each(function(i, tr) {
			$(tr).find('td').each(function(j, td) {
				if (j == 0) { // Update hidden input indexes
					var itemTotalAmount = $(td).find('input[type="hidden"][class="hid-totalAmount"]').attr('name', 'itemList[' + i + '].totalAmount').val();
					subTotalAmount = (parseFloat(subTotalAmount) + parseFloat(itemTotalAmount)).toFixed(4);
				}
			});
		});

		var footer = $('#add-product-entry').siblings('tfoot');
		$(footer).find('#tatoalFooter').find('td').each(function(i, td) {
			if (i == 1) { // footer (total amount)
				$('#grandTotalAmount').html(numberWithCommas(parseFloat(subTotalAmount)));
			}
		});

	}

	updateItemTableFooter();

	var deleteStockRow = function(row) {

		if (row) {

			var rowIndex = $(row).data('row-index');

			$('#add-product-entry').find('tr').each(
				function(i, tr) {
					var idx = $(tr).data('row-index');
					if (idx == rowIndex) {
						$(tr).remove();
					}
				});
		}
		updateItemTable();
		updateItemTableFooter();
	}

	$('#addProduct').click(function() {

		var error = checkAddProductValication();

		if (!error) {
			addProductTable();
		}

	});

	$(document).on("click", ".delete-stock", function() {

		deleteStockRow($(this).closest('tr'));

	});

	var prepareItemFormForUpdate = function(row) {

		if (row) {
			var rowIndex = $(row).data('row-index');

			var td = $(row).find('td:first-child');
			var productId = $(td).find('input[type="hidden"][class="hid-productId"]').val();
			var productName = $(td).find('input[type="hidden"][class="hid-productName"]').val();
			var pack = $(td).find('input[type="hidden"][class="hid-pack"]').val();
			var quantityPerPack = $(td).find('input[type="hidden"][class="hid-quantityPerPack"]').val();
			var quantity = $(td).find('input[type="hidden"][class="hid-quantity"]').val();
			var originalPrice = $(td).find('input[type="hidden"][class="hid-originalPrice"]').val();
			var sellingPrice = $(td).find('input[type="hidden"][class="hid-sellingPrice"]').val();
			var totalAmount = $(td).find('input[type="hidden"][class="hid-totalAmount"]').val();
			var discount = $(td).find('input[type="hidden"][class="hid-discount"]').val();

			$('#originalPrice').val(originalPrice);
			$('#discount').val(discount);
			$('#sellingPrice').val(sellingPrice);
			$('#pack').val(pack);
			$('#totalQuantity').val(quantity);
			$('#quantityPerPack').val(quantityPerPack);

			console.log(productId);
			console.log(productName);
			console.log("edit: " +quantityPerPack);

			/*var newOption = new Option(productName, productId, true, true);
			$('#productId').append(newOption).trigger('change');*/
			
			$('#productId').val(productId).change();

			$('#hid-is-new').val('false');
			$('#hid-update-row-index').val(rowIndex);

		}
	};

	var resetStockInputForm = function() {

		$('#hid-is-new').val('true');
		$('#hid-update-row-index').val('0');
		$('#originalPrice').val("");
		$('#discount').val("");
		$('#sellingPrice').val("");
		$('#pack').val("");
		$('#totalQuantity').val("");
		$('#productId').val('').trigger('change');

	};

	$(document).on("click", ".edit-stock", function() {
		prepareItemFormForUpdate($(this).closest('tr'));
	});

	$('#pack').on('keyup', function() {

		let pack = $('#pack').val();

		let quantityPerPack = $('#quantityPerPack').val();

		if (!pack) {
			pack = 0;
		}

		if (!quantityPerPack) {
			quantityPerPack = 0;
		}

		let totalQuantity = pack * quantityPerPack;

		$('#totalQuantity').val(totalQuantity);

	});

	function numberWithCommas(x) {

		if (x == undefined || x == '') {
			return '';
		}
		x = parseFloat(x).toFixed(2);
		return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}

});