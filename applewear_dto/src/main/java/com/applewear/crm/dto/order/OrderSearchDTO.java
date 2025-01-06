package com.applewear.crm.dto.order;

import java.util.List;

import com.applewear.crm.dto.search.SearchDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderSearchDTO extends SearchDTO {

	private String orderRef;

	private String fromDate;

	private String toDate;

	private String customerName;

	private String customerPhone;

	private Integer orderStatus;

	private Long customerId;

	private boolean processOnly;

	private boolean completeOnly;

	private List<OrderDTO> data;

	private boolean isPaymentCompleted;

	private boolean isPaymentPending;

}
