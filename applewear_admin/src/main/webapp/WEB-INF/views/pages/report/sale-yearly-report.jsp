<%@ include file="../../include/tag-template.jsp"%>

<style>
.datepicker-years {
	display: block !important;
}
</style>

<div class="row">
	<div class="col-sm-12">
		<div class="card">
			<div class="card-header">
				<h5>Sale Yearly Report</h5>
			</div>
			<div class="card-body">
				<form>

					<div class="row mb-3">

						<div class="col-md-6">
							<div class="form-group">
								<label for="fromDate"> From Year </label> <input
									class="form-control bg-white year-picker" id="fromYear"></input>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="toDate"> To Year </label> <input
									class="form-control bg-white year-picker" id="toYear"></input>
							</div>
						</div>


					</div>
					<div class="row mb-3">
						<div class="col-md-12 text-right">
							<button id="btn-search" type="button"
								class="btn btn-primary mr-3">Search</button>



						</div>
					</div>

				</form>
				<div class="row mb-3">
					<div class="col-md-12">
						<div class="table-responsive">
							<table id="sale-yearly-data-table"
								class="display table table-striped table-hover"
								style="width: 100%">
								<thead>
									<tr>
										<th>Year</th>
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
	src="<%=request.getContextPath()%>/resources/js/report/sale-yearly-search.js?modified=33"></script>