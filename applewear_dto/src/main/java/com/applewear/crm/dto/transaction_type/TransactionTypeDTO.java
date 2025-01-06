package com.applewear.crm.dto.transaction_type;

import com.applewear.crm.entity.transaction_type.StockTransactionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionTypeDTO {

	private Long transactionTypeId;

	private String transactionTypeName;

	private String transactionTypeDesc;

	private Integer indicator;

	public TransactionTypeDTO(StockTransactionType entity) {

		if (entity != null) {

			this.transactionTypeId = entity.getId();

			this.transactionTypeName = entity.getTypeName();

			this.transactionTypeDesc = entity.getTxnDesc();

			this.indicator = entity.getIndicator();

		}

	}

}
