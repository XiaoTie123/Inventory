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
				style="float: left; width: 80px; height: 80px;" />APPLE<br /> No.
			(26) , Thida Street , Kyimyindine Tsp, Yangon.<br /> Phone No:
			09403337250 , 095065483, 09771228599 , 09780000093
		</h4>
	</section>
	<hr class="n1" />
	<!-- Main content -->
	<section class="invoice" style="padding-left: 5px; font-size: 16px;">
		<!-- title row -->
		<h4 class="text-black" style="text-align: center">Invoice</h4>

		<div class="invoice-info">
			<div class="invoice-col">
				<strong class="text-black">To</strong> <span id="customeraddress"></span>
			</div>
			<div class="invoice-col text-black">
				<strong><spring:message code="customer.name" />:
					${customerDTO.name }</strong>
			</div>

			<div class="invoice-col text-black">
				<strong><spring:message code="customer.address" />:
					${customerDTO.address }</strong>
			</div>

			<div class="invoice-col text-black">
				<strong><spring:message code="customer.mobile" />:
					${customerDTO.mobile }</strong>
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
							<th>Date</th>
							<th>Order Ref</th>
							<th>Total Paid</th>
						</tr>
					</thead>
					<tbody id="tbl_invoice_container">
						<c:forEach var="row" items="${paymentList }" varStatus="status">
							<tr>

								<td>${row.paymentDate }</td>
								<td>${row.orderRef }</td>
								<td>${row.totalPaidAmountText }</td>
							</tr>

						</c:forEach>
						<tr>
							<td colspan="3" class="text-center"><spring:message
									code="total.charges"></spring:message> =
								${orderDTO.totalAmountText }</td>
						</tr>
						<tr>
							<td colspan="3" class="text-center"><spring:message
									code="already.paid"></spring:message> = ${totalPaid }</td>
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
			<a href="customer-order-history.html?customerId=${customerId }"
				class="btn btn-primary noprint" id="back">Back</a>
		</div>
	</div>

</body>