<%@ include file="../../include/tag-template.jsp"%>


<spring:message code="make.payment" text="Make Payment" var="makePayment"></spring:message>

<input type="hidden" value="${makePayment }" id="makePayment"/>

<input type="hidden" value="${isEditPayment }" id="isEditPayment"/>

<div class="row">
	<!-- Total Amount-->
	<div class="col-md-12 col-xl-4">
		<div class="card user-card">
			<div class="card-block">
				<h5 class="m-b-15"><spring:message code="customer.order.history.total.amount"></spring:message></h5>
				<h4 class="f-w-300 mb-3">${customerBalanceDTO.totalAmountText }</h4>

			</div>
		</div>
	</div>
	<!-- Total Amount end -->

	<!-- Total Paid Amount-->
	<div class="col-md-12 col-xl-4">
		<div class="card user-card">
			<div class="card-block">
				<h5 class="m-b-15"><spring:message code="customer.order.history.paid" text="Total Paid"></spring:message></h5>
				<h4 class="f-w-300 mb-3">${customerBalanceDTO.totalPaidAmountText }</h4>

			</div>
		</div>
	</div>
	<!-- Total Paid end -->

	<!-- Total Remaining Amount-->
	<div class="col-md-12 col-xl-4">
		<div class="card user-card">
			<div class="card-block">
					<h5 class="m-b-15"><spring:message code="customer.order.remaining" text="Remaining Amount"></spring:message></h5>
				<h4 class="f-w-300 mb-3">${customerBalanceDTO.totalRemainingAmountText }</h4>

			</div>
		</div>
	</div>
	<!-- Total Paid end -->

	<!-- [ tabs ] start -->
	<div class="col-sm-12">
		<ul class="nav nav-tabs nav-justified" id="myTab" role="tablist">
			<li class="nav-item"><a class="nav-link active text-uppercase"
				id="unPaidCustomerOrder-tab" data-toggle="tab"
				href="#unPaidCustomerOrder" role="tab"
				aria-controls="unPaidCustomerOrder" aria-selected="true">
					<spring:message code="payment.pending.orders" text="Paid Orders"></spring:message>
				</a></li>
		</ul>

		<div class="tab-content" id="myTabContent">
			<div class="tab-pane fade show active" id="unPaidCustomerOrder"
				role="tabpanel" aria-labelledby="unPaidCustomerOrder-tab">
				<%@ include file="payment-pending-orders.jsp" %>
			</div>
		</div>

	</div>
	<!-- [tabs] end -->


</div>

<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>

<script
	src="<%=request.getContextPath()%>/resources/js/order/payment-pending-noti.js?modified=55555"></script>	