<%@ include file="../../include/tag-template.jsp"%>

<input type="hidden" id="hid-is-new" value="true">
<input type="hidden" id="hid-update-row-index" value="0">

<div class="col-md-12 mt-3">

	<div class="row mt-3">
		<div class="col-md-12">
			<div class="table-responsive">
				<table class="display table table-striped table-hover"
					style="width: 100%">

					<thead>
						<tr>
							<th>#</th>
							<th><spring:message code="label.product"></spring:message></th>
							<th><spring:message code="original.price"></spring:message>
							</th>
							<th><spring:message code="label.discount"></spring:message>
							</th>
							<th><spring:message code="selling.price"></spring:message></th>
							<th><spring:message code="total.pack"></spring:message></th>
							<th><spring:message code="quantity.per.pack"></spring:message>
							</th>
							<th><spring:message code="product.quantity"></spring:message>
							</th>
							<th><spring:message code="charges"></spring:message></th>
							<th></th>
						</tr>
					</thead>
					<tbody id="add-product-entry">

						<c:forEach var="row" items="${orderDTO.itemList }"
							varStatus="status">
							<tr data-row-index="${status.index}">
								<td class="text-right"><span class="tableRowNo">${status.index+1 }</span>
									<form:hidden class="hid-productId"
										path="itemList[${status.index }].productId" /> <form:hidden
										class="hid-productName"
										path="itemList[${status.index }].productName" /> <form:hidden
										class="hid-pack" path="itemList[${status.index }].pack" /> <form:hidden
										class="hid-quantityPerPack"
										path="itemList[${status.index }].quantityPerPack" /> <form:hidden
										class="hid-originalPrice"
										path="itemList[${status.index }].originalPrice" /> <form:hidden
										class="hid-discount"
										path="itemList[${status.index }].discount" /> <form:hidden
										class="hid-sellingPrice"
										path="itemList[${status.index }].sellingPrice" /> <form:hidden
										class="hid-quantity"
										path="itemList[${status.index }].quantity" /> <form:hidden
										class="hid-totalAmount"
										path="itemList[${status.index }].totalAmount" /></td>
								<td>${row.productName }</td>
								<td><fmt:formatNumber maxFractionDigits="2"
										value="${row.originalPrice}" type="number" /></td>
								<td><fmt:formatNumber maxFractionDigits="2"
										value="${row.discount}" type="number" /></td>
								<td><fmt:formatNumber maxFractionDigits="2"
										value="${row.sellingPrice}" type="number" /></td>
								<td>${row.pack }</td>
								<td>${row.quantityPerPack}</td>
								<td>${row.quantity}</td>
								<td><fmt:formatNumber maxFractionDigits="2"
										value="${row.totalAmount}" type="number" /></td>
								<td style="width: 10%;">
									<div
										style="display: inline-flex; align-items: center; gap: 5px; flex-wrap: nowrap; justify-content: center;">
										<a href="javascript:void(0)" class="btn-edit-icon edit-stock">
											<span class="feather icon-edit" style="font-size: 20px"></span>
										</a> <a href="javascript:void(0)"
											class="ml-3 btn-delete-icon delete-stock"> <span
											class="feather icon-trash-2" style="font-size: 20px"></span>
										</a>
									</div>
								</td>
							</tr>
						</c:forEach>
					<tfoot>
						<tr id="tatoalFooter">
							<form:hidden path="totalAmountText"/>
							<td colspan="8" class="text-right"><spring:message
									code="order.grand.total" text="Grand Total"></spring:message> =
								<span id="grandTotalAmount">${orderDTO.totalAmountText }</span>
							</td>
							<td></td>
						</tr>
					</tfoot>

					</tbody>
				</table>
			</div>
		</div>

	</div>
</div>