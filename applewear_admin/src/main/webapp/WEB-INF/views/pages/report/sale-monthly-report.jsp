<%@ include file="../../include/tag-template.jsp"%>

<style>
.datepicker-months {
	display: block !important;
}
</style>

<div class="row">
	<div class="col-sm-12">
		<div class="card">
			<div class="card-header">
				<h5>Sale Monthly Report</h5>
			</div>
			<div class="card-body">
				<form:form modelAttribute="searchDTO"
					action="sale-monthly-report.html" method="POST">

					<div class="row mb-3">

						<div class="col-md-6">
							<div class="form-group">
								<label for="fromDate"> From Month </label>
								<form:input path="fromMonthYear"
									class="form-control bg-white month-year-picker"
									id="fromMonthYear"></form:input>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="toDate"> To Month </label>
								<form:input path="toMonthYear"
									class="form-control bg-white month-year-picker"
									id="toMonthYear"></form:input>
							</div>
						</div>


					</div>
					<div class="row mb-3">
						<div class="col-md-12 text-right">
							<button id="btn-search" type="button"
								class="btn btn-primary mr-3">Search</button>

							<button type="submit" name="exportSaleMonthlyReport" value="exportSaleMonthlyReport"
								class="btn btn-primary mr-3">Export Excel</button>

						</div>
					</div>

					</form:form>
					<div class="row mb-3">
						<div class="col-md-12">
							<div class="table-responsive">
								<table id="sale-monthly-data-table"
									class="display table table-striped table-hover"
									style="width: 100%">
									<thead>
										<tr>
											<th>Year</th>
											<th>Month</th>
											<th>Total Orders</th>
											<th>Total</th>
											<th>Total Paid</th>
										</tr>
									</thead>
									<tbody>

									</tbody>
								</table>
							</div>
						</div>
					</div>
			</div>
		</div>
	</div>
</div>

<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/report/sale-monthly-search.js?modified=33"></script>