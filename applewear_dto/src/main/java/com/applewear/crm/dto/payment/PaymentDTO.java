package com.applewear.crm.dto.payment;

import java.math.BigDecimal;

import com.applewear.crm.dto.customer.CustomerDTO;
import com.applewear.crm.dto.order.OrderDTO;
import com.applewear.crm.dto.payment_method.PaymentMethodDTO;
import com.applewear.crm.entity.payment.Payment;
import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

	private Long paymentId;

	private String orderRef;

	private OrderDTO orderDTO;

	private String paymentDate;

	private BigDecimal totalPaidAmount;

	private String totalPaidAmountText;

	private CustomerDTO customerDTO;

	private PaymentMethodDTO paymentMethodDTO;
	
	private Long paymentMethodId;

	public PaymentDTO(Payment entity) {

		if (entity != null) {

			this.paymentId = entity.getId();

			this.paymentDate = entity.getOrderDate() != null
					? CommonUtil.dateToString(CommonConstants.STD_DATE_FORMAT, entity.getOrderDate())
					: "";

			this.totalPaidAmount = entity.getTotalPaid();

			this.totalPaidAmountText = CommonUtil.formatNumber(entity.getTotalPaid());

			this.customerDTO = new CustomerDTO(entity.getCustomer());

			this.paymentMethodDTO = new PaymentMethodDTO(entity.getPaymentMethod());
			
			this.orderDTO = new OrderDTO(entity.getOrder());

		}

	}
}
