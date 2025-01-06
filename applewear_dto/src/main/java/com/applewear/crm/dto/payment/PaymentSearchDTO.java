package com.applewear.crm.dto.payment;

import java.util.List;

import com.applewear.crm.dto.search.SearchDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PaymentSearchDTO extends SearchDTO {

	private String fromDate;

	private String toDate;

	private String orderRef;

	private Long customerId;

	private List<PaymentDTO> data;

}
