package com.applewear.crm.dto.order;

import java.math.BigDecimal;
import java.util.List;

import com.applewear.crm.dto.order_item.OrderItemDTO;
import com.applewear.crm.entity.order.Order;
import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

	private Long orderId;

	private String orderRef;

	private String orderDate;

	private BigDecimal totalAmount;

	private String totalAmountText;

	private BigDecimal totalPaid;

	private String totalPaidText;

	private BigDecimal remainingAmount;

	private String remainingAmountText;

	private BigDecimal discount;

	private String discountText;

	private Long customerId;

	private String customerName;

	private String customerRank;

	private String customerPhone;
	
	private String customerAddress;

	private String customerEmail;

	private Integer orderStatus;

	private String orderStatusDesc;

	private Long submittedById;
	
	private String submittedByName;

	private Integer num;

	private List<OrderItemDTO> itemList;

	public OrderDTO(Order entity) {

		if (entity != null) {

			this.orderId = entity.getId();

			this.orderRef = entity.getOrder_ref();

			this.orderDate = entity.getOrderDate() != null
					? CommonUtil.dateToString(CommonConstants.STD_DATE_FORMAT, entity.getOrderDate())
					: "";

			this.totalAmount = entity.getTotal() != null ? entity.getTotal() : BigDecimal.ZERO;

			this.totalAmountText = CommonUtil.formatNumber(entity.getTotal());

			this.totalPaid = entity.getTotalPaid() != null ? entity.getTotalPaid() : BigDecimal.ZERO;

			this.totalPaidText = CommonUtil.formatNumber(entity.getTotalPaid());

			this.remainingAmount = this.totalAmount.subtract(this.totalPaid);

			if (entity.getCustomer() != null) {

				this.customerId = entity.getCustomer().getId();

				this.customerName = entity.getCustomer().getUserName();

				this.customerEmail = entity.getCustomer().getEmail();

				this.customerPhone = entity.getCustomer().getMobile();

			}

		}

	}

}
