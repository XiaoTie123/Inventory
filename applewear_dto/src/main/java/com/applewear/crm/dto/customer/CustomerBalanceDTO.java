package com.applewear.crm.dto.customer;



import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerBalanceDTO {

	private BigDecimal totalAmount;

	private String totalAmountText;

	private BigDecimal totalPaidAmount;

	private String totalPaidAmountText;

	private BigDecimal totalRemainingAmount;

	private String totalRemainingAmountText;

}
