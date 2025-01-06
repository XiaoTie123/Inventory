package com.applewear.crm.dto.sale_report;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleReportDTO {

	private Integer year;

	private String month;

	private String saleDate;

	private Date saleRawDate;

	private Long totalOrders;

	private BigDecimal totalAmount;

	private String totalAmountDesc;

	private BigDecimal totalPaid;

	private String totalPaidDesc;

}
