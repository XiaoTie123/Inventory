<%@ include file="../../include/tag-template.jsp"%>

<nav class="pcoded-navbar menu-light brand-blue icon-colored">
	<div class="navbar-wrapper">
		<div class="navbar-brand header-logo">
			<a href="home.html" class="b-brand">
				<div class="b-bg">
					<img alt="" src="resources/images/applewear_logo.png"
						class="img-fluid" height="50px">
				</div> <span class="b-title fs-14">Apple UnderWear</span>
			</a> <a class="mobile-menu" id="mobile-collapse" href="javascript:;"><span></span></a>
		</div>
		<div class="navbar-content scroll-div">
			<ul class="nav pcoded-inner-navbar">



				<li class="nav-item ${active eq '1' ? ' active' : '' }"><a
					href="home.html" class="nav-link"> <span class="pcoded-micon">
							<i class="feather icon-home"></i>
					</span> <span class="pcoded-mtext"> <spring:message
								code="menu.home" text="Home"></spring:message>
					</span>
				</a></li>


				<li class="nav-item ${active eq '2' ? ' active' : '' }"><a
					href="order-search.html" class="nav-link"> <span
						class="pcoded-micon"> <i class="feather icon-shopping-cart"></i>
					</span> <span class="pcoded-mtext"> <spring:message
								code="menu.order" text="Orders"></spring:message>
					</span>
				</a></li>

				<li class="nav-item ${active eq '3' ? ' active' : '' }"><a
					href="order-setup.html" class="nav-link"> <span
						class="pcoded-micon"> <i class="feather icon-shopping-cart"></i>
					</span> <span class="pcoded-mtext"> <spring:message
								code="menu.to.create.order" text="Order New"></spring:message>
					</span>
				</a></li>





				<li class="nav-item ${active eq '4' ? ' active' : '' }"><a
					href="order-finish-search.html" class="nav-link"> <span
						class="pcoded-micon"> <i class="feather icon-check-circle"></i>
					</span> <span class="pcoded-mtext"> <spring:message
								code="menu.order.finish" text="Order (Finish)"></spring:message>
					</span>
				</a></li>

				<li
					class="nav-item pcoded-hasmenu
				${(active == '5' || active == '6' || active == '7') ? ' active pcoded-trigger' : ''}"><a
					href="javascript:;" class="nav-link"> <span
						class="pcoded-micon"> <i class="feather icon-grid"></i>
					</span> <span class="pcoded-mtext"> <spring:message
								code="menu.stock.transaction" text="Stock Transaction"></spring:message>
					</span></a>

					<ul class="pcoded-submenu">
						<li class="${active eq '5' ? ' active' : '' }"><a
							href="inventory.html" class=""> <spring:message
									code="label.inventory" text="Inventory"></spring:message>
						</a></li>
						<li class="${active eq '6' ? ' active' : '' }"><a
							href="stock-transaction-setup.html" class=""> Stock
								Transaction Setup </a></li>
						<li class="${active eq '7' ? ' active' : '' }"><a
							href="stock-transaction-search.html" class=""> Stock
								Transaction Search </a></li>

					</ul></li>

				<li
					class="nav-item pcoded-hasmenu
				${(active == '8' || active == '9') ? ' active pcoded-trigger' : ''}"><a
					href="javascript:;" class="nav-link"> <span
						class="pcoded-micon"> <i class="feather icon-users"></i>
					</span> <span class="pcoded-mtext"> <spring:message
								code="menu.customer" text="Customer"></spring:message>
					</span></a>
					<ul class="pcoded-submenu">
						<li class="${active eq '8' ? ' active' : '' }"><a
							href="customer-setup.html" class=""> <spring:message
									code="menu.new" text="Create"></spring:message>
						</a></li>
						<li class="${active eq '9' ? ' active' : '' }"><a
							href="customer-search.html" class=""> <spring:message
									code="menu.search" text="Search"></spring:message>
						</a></li>

					</ul></li>

				<li class="nav-item ${active eq '10' ? ' active' : '' }">
					<div class="row">
						<a href="customer-history.html" class="nav-link"> <span
							class="pcoded-micon" style="padding-left: 13px;"> <img
								alt="" src="resources/images/file.svg" class="img-fluid"
								height="50px">
						</span> <span class="pcoded-mtext" style="padding-left: 12px;"> <spring:message
									code="menu.customer.history" text="Customer History"></spring:message>
						</span>
						</a>
					</div>
				</li>


				<li
					class="nav-item pcoded-hasmenu 
					${(active == '11' || active == '12') ? ' active pcoded-trigger' : ''}">
					<a href="javascript:;" class="nav-link"> <span
						class="pcoded-micon"> <i class="feather icon-user"></i>
					</span> <span class="pcoded-mtext"> <spring:message
								code="menu.admin" text="Admin"></spring:message>
					</span></a>

					<ul class="pcoded-submenu">
						<li class="${active eq '11' ? ' active' : '' }"><a
							href="admin-setup.html" class=""> <spring:message
									code="menu.new" text="Create"></spring:message>
						</a></li>
						<li class="${active eq '12' ? ' active' : '' }"><a
							href="admin-list.html" class=""> <spring:message
									code="menu.search" text="Search"></spring:message>
						</a></li>

					</ul>
				</li>

				<li
					class="nav-item pcoded-hasmenu
				${(active == '13' || active == '14') ? ' active pcoded-trigger' : ''}"><a
					href="javascript:;" class="nav-link"> <span
						class="pcoded-micon"> <i class="feather icon-package"></i>
					</span> <span class="pcoded-mtext"> <spring:message
								code="menu.product" text="Product"></spring:message>
					</span></a>
					<ul class="pcoded-submenu">
						<li class="${active eq '13' ? ' active' : '' }"><a href="product-setup.html" class=""> <spring:message
									code="menu.new" text="Create"></spring:message>
						</a></li>
						<li class="${active eq '14' ? ' active' : '' }"><a href="list.html" class=""> <spring:message
									code="menu.search" text="Search"></spring:message>
						</a></li>

					</ul></li>

				<li class="nav-item pcoded-hasmenu
					${(active == '15' || active == '16' || active == '17') ? ' active pcoded-trigger' : ''}"><a href="javascript:;"
					class="nav-link"> <span class="pcoded-micon"> <i
							class="feather feather icon-folder"></i>
					</span> <span class="pcoded-mtext"> Report </span></a>

					<ul class="pcoded-submenu">
						<li class="${active eq '15' ? ' active' : '' }"><a href="sale-daily-report.html" class="">
								Sale Daily Report </a></li>

						<li class="${active eq '16' ? ' active' : '' }"><a href="sale-monthly-report.html" class="">
								Sale Monthly Report </a></li>

						<li class="${active eq '17' ? ' active' : '' }"><a href="sale-yearly-report.html" class="">
								Sale Yearly Report </a></li>


					</ul></li>

				<li class="nav-item pcoded-hasmenu
					${(active == '18' || active == '19' || active == '20' || active == '21' || active == '22') ? ' active pcoded-trigger' : ''}"><a href="javascript:;"
					class="nav-link"> <span class="pcoded-micon"> <i
							class="feather icon-circle"></i>
					</span> <span class="pcoded-mtext"> Master </span></a>

					<ul class="pcoded-submenu">
						<li class="${active eq '18' ? ' active' : '' }"><a href="rank-setup.html" class=""> <spring:message
									code="rank.setup" text="Rank Setup"></spring:message>
						</a></li>

						<li class="${active eq '19' ? ' active' : '' }"><a href="payment_method_setup.html" class="">
								<spring:message code="payment.method.setup"
									text="Payment Method Setup"></spring:message>
						</a></li>

						<li class="${active eq '21' ? ' active' : '' }"><a href="brand-setup.html" class=""> <spring:message
									code="brand.setup" text="Brand Setup"></spring:message>
						</a></li>

						<li class="${active eq '20' ? ' active' : '' }"><a href="category-setup.html" class=""> <spring:message
									code="category.setup" text="Category Setup"></spring:message>
						</a></li>

						<li class="${active eq '22' ? ' active' : '' }"><a href="size-setup.html" class=""> <spring:message
									code="size.setup" text="Size Setup"></spring:message>
						</a></li>

					</ul></li>

			</ul>
		</div>
	</div>
</nav>