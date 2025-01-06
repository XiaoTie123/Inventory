package com.applewear.crm.dto.sale_report;

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
public class SaleReportSearchDTO extends SearchDTO {

	private Integer filterType;

	private String fromDate;

	private String toDate;

	private String fromMonthYear;

	private String toMonthYear;

	private String fromYear;

	private String toYear;
	
	private boolean isExcelReport;

	private List<SaleReportDTO> data;

}
