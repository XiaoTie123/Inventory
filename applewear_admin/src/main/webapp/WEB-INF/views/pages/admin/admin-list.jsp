<%@ include file="../../include/tag-template.jsp"%>

<div class="row">
	<div class="col-sm-12">
		<div class="card">
			<div class="card-header">
				<h5>Admin Search</h5>
			</div>
			<div class="card-body">
				<form:form action="admin-list.html" method="post"
					modelAttribute="adminSearchDTO">

					<div class="row mb-3">
						<div class="col-md-6">
							<div class="form-group">
								<label for="adminName"> ${list1} </label>
								<form:input path="adminName" class="form-control bg-white"
									id="adminName" />
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="email"> ${list2} </label>
								<form:input path="email" class="form-control bg-white"
									id="email" />
							</div>
						</div>
					</div>


					<div class="row mb-3">
						<div class="col-md-6">
							<div class="form-group">
								<label for="fromDate"> ${list3} </label>
								<form:select path="roleId" class="form-control bg-white">
									<form:option value="-1">All</form:option>
									<form:options items="${roleList}" itemLabel="desc"
										itemValue="code"></form:options>
								</form:select>
							</div>
						</div>
					</div>



					<div class="row mb-3">
						<div class="col-md-12 text-right">
							<button id="btn-search" type="button"
								class="btn btn-primary mr-3">Search</button>
						</div>
					</div>

				</form:form>
				<div class="row mb-3">
					<div class="col-md-12">
						<div class="table-responsive">
							<table id="customer-data-table"
								class="display table table-striped table-hover"
								style="width: 100%">
								<thead>
									<tr>
										<th></th>
										<th style="width: 5%; padding-bottom: 23px;">User ID</th>
										<th>${list1}</th>
										<th>Login Id</th>
										<th>${list2}</th>
										<th>${list3}</th>
										<th>${list4}</th>
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
	src="<%=request.getContextPath()%>/resources/js/admin-user/admin-list.js?modified=29334533"></script>