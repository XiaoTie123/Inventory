<%@ include file="../../include/tag-template.jsp"%>

<spring:message code="to.view.order" var="toViewOrder"></spring:message>

<input type="hidden" value="${toViewOrder }" id="toViewOrder" />

<div class="row">
	<div class="col-sm-12">
		<div class="card">
			<div class="card-body">
				<form>

					<div class="row mb-3">


						<div class="col-md-6">
							<div class="form-group">
								<label for="name"> <spring:message code="customer.name"
										text="Name"></spring:message>
								</label> <select class="form-control select-2" id="customerId">
									<option value="-1">Select One</option>
									<c:forEach var="row" items="${customerList }">
										<option value="${row.customerId }">${row.name }</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<%-- <div class="col-md-6">
							<div class="form-group">
								<label for="loginId"> 
									<spring:message code="customer.loginId" text="Login Id"></spring:message>
								 </label>
								<input type="text" class="form-control bg-white"
									id="loginId" />
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="form-group">
								<label for="email"> 
									<spring:message code="customer.email" text="Email"></spring:message>
								 </label>
								<input type="text" class="form-control bg-white"
									id="email" />
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="form-group">
								<label for="phoneNo"> 
									<spring:message code="customer.mobile" text="Mobile"></spring:message>
								 </label>
								<input type="text" class="form-control bg-white"
									id="phoneNo" />
							</div>
						</div> --%>

					</div>
					<div class="row mb-3">
						<div class="col-md-12">
							<button id="viewOrder" type="button" class="btn btn-primary mr-3">
								<spring:message code="to.view.order"></spring:message>
							</button>
						</div>
					</div>

				</form>
				<%-- <div class="row mb-3">
					<div class="col-md-12">
						<div class="table-responsive">
							<table id="customer-data-table"
								class="display table table-striped table-hover"
								style="width: 100%">
								<thead>
									<tr>
										<th></th>
										<th><spring:message code="no" text="No"/> </th>
										<th><spring:message code="customer.name" text="Name"/></th>
										<th><spring:message code="customer.rank" text="Rank"/></th>
										<th><spring:message code="customer.loginId" text="Login Id"/></th>
										<th><spring:message code="customer.mobile" text="Mobile"/></th>
										<th><spring:message code="customer.address" text="Address"/></th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
					</div>
				</div> --%>
			</div>
		</div>
	</div>
</div>

<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/select2.full.min.js"
	defer></script>
<script>
	$(document).ready(function() {
		$("#customerId").select2({});
		
		$('#viewOrder').click(function(){
			let customerId = $('#customerId').val();
			
			console.log(customerId);
			
			if(!customerId || customerId < 0){
				notify("Please select customer.", "danger");
			}else{
				window.location.href ="customer-order-history.html?customerId="+customerId;
			}
			
		})
		
	});
</script>

<%-- <script src="<%=request.getContextPath()%>/resources/js/customer/customer-history-search.js?modified=33"></script> --%>