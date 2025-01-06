package com.applewear.crm.dto.customer;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopTenCustomerDTO {

	private Long customerId;

	private String name;

	private BigDecimal totalAmount;

	private String totalAmountDesc;

}
