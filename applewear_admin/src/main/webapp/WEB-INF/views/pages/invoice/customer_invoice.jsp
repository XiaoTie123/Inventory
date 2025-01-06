<%@ include file="../../include/tag-template.jsp"%>

<style>
@media print {
	/* style sheet for print goes here */
	.noprint {
		visibility: hidden;
	}
}

.resize {
	width: 90px;
	height: 90px;
	float: left;
}

.text-black {
	color: #000;
}

body {
	font-size: 17px;
}

#btn_p {
	background-color: #008CBA; /* Green */
	border: none;
	color: white;
	padding: 15px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 15px;
	margin: 4px 2px;
	cursor: pointer;
}

.align {
	text-align: center;
}

#back {
	background-color: lightcoral; /* Green */
	border: none;
	color: white;
	padding: 15px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 15px;
	margin: 3px 2px;
	cursor: pointer;
}

table#tbl_invoice_orderdetail {
	font-size: 20px;
}

hr.n1 {
	border-bottom: 5px double grey;
}

table, tr, td, th {
	color: #000;
}
</style>

<body>
	<section>
		<h4 style="text-align: center;" class="fw-600">
			<img src="resources/images/applewear_logo.png"
				style="float: left; width: 80px; height: 80px;" />APPLE<br /> No. (26) , Thida Street ,
			Kyimyindine Tsp, Yangon.<br /> Phone No: 09403337250 , 095065483,
			09771228599 , 09780000093
		</h4>
	</section>
	<hr class="n1" />
	<!-- Main content -->
	<section class="invoice" style="padding-left: 5px; font-size: 16px;">
		<!-- title row -->
		<h4 class="text-black" style="text-align: center">Invoice</h4>
		<div class="invoice-col text-black" style="float: left;">
			<b><spring:message code="order.order.no"></spring:message>:</b>&nbsp;<span
				id="order_ref" class="text-black"> ${orderDTO.orderRef } </span><br>
			<b> <spring:message code="order.order.date"></spring:message>:
			</b>&nbsp;&nbsp;<span id="order_date" class="text-black">
				${orderDTO.orderDate } </span>
		</div>
		<div class="invoice-info" style="float: right;">
			<div class="invoice-col">
				<strong class="text-black">To</strong> <span id="customeraddress"></span>
			</div>
			<div class="invoice-col text-black">
				<strong><spring:message code="customer.name" />:
					${orderDTO.customerName }</strong>
			</div>

			<div class="invoice-col text-black">
				<strong><spring:message code="customer.address" />:
					${orderDTO.customerAddress }</strong>
			</div>

			<div class="invoice-col text-black">
				<strong><spring:message code="customer.mobile" />:
					${orderDTO.customerPhone }</strong>
			</div>

		</div>
	</section>
	<!-- Main content -->
	<div style="clear: both;"></div>
	<div class="row mt-4">
		<div class="col-md-12">
			<div class="table-responsive">
				<table class="display table table-border table-hover"
					style="width: 100%">
					<thead>
						<tr>
							<th><spring:message code="order.order.no"></spring:message></th>
							<th><spring:message code="label.product"></spring:message></th>
							<th><spring:message code="total.pack"></spring:message></th>
							<th><spring:message code="quantity.per.pack"></spring:message></th>
							<th><spring:message code="product.quantity"></spring:message></th>
							<th><spring:message code="selling.price"></spring:message></th>
							<th><spring:message code="charges"></spring:message></th>
						</tr>
					</thead>
					<tbody id="tbl_invoice_container">
						<c:forEach var="row" items="${orderDTO.itemList }"
							varStatus="status">
							<tr>
								<td>${status.count }</td>
								<td>${row.productName }</td>
								<td>${row.pack }</td>
								<td>${row.quantityPerPack }</td>
								<td>${row.quantity}</td>
								<td>${row.sellingPriceDesc}</td>
								<td>${row.totalAmountDesc}</td>
							</tr>

						</c:forEach>
						<tr>
							<td colspan="7" class="text-right">Total Amount =
								${orderDTO.totalAmountText }</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<br />
	<br />
	<div class="row no-print ml-3">
		<div class="col-xs-12">
			<button onclick="javascript:window.print();"
				class="noprint btn btn-primary">
				<i class="fa fa-print" style="margin-top: 10px; margin-left: 8px;"></i>
				Print
			</button>
			<a href="order-search.html" class="btn btn-primary noprint" id="back">Back</a>
		</div>
	</div>

</body>