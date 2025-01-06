package com.applewear.crm.dto.stock_transaction;

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
public class StockTransactionSearchDTO extends SearchDTO {

	private String productName;

	private String refNo;

	private Long transactionTypeId;

	private String fromDate;

	private String toDate;

	private List<StockTransactionDTO> data;
	
	private boolean isExcelReport;


}
