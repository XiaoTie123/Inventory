<%@ include file="../../include/tag-template.jsp"%>

<div class="row">
	<div class="col-sm-12">
		<div class="card">
			<div class="card-header">
				<h5>Sale Daily Report</h5>
			</div>
			<div class="card-body">
				<form:form modelAttribute="searchDTO"
					action="sale-daily-report.html">

					<div class="row mb-3">

						<div class="col-md-6">
							<div class="form-group">
								<label for="fromDate"> From Date </label>
								<form:input path="fromDate"
									class="form-control bg-white date-picker" id="fromDate"></form:input>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="toDate"> To Date </label>
								<form:input path="toDate"
									class="form-control bg-white date-picker" id="toDate"></form:input>
							</div>
						</div>


					</div>
					<div class="row mb-3">
						<div class="col-md-12 text-right">
							<button id="btn-search" type="button"
								class="btn btn-primary mr-3">Search</button>
							<button type="submit"
								name="exportDailyExcelReport"
								value="exportDailyExcelReport"
								class="btn btn-primary mr-3">Export Excel</button>


						</div>
					</div>

					</form:form>
					<div class="row mb-3">
						<div class="col-md-12">
							<div class="table-responsive">
								<table id="sale-daily-data-table"
									class="display table table-striped table-hover"
									style="width: 100%">
									<thead>
										<tr>
											<th>Date</th>
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
	src="<%=request.getContextPath()%>/resources/js/report/sale-daily-search.js?modified=33"></script>