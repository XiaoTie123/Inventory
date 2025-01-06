package com.applewear.crm.entity.transaction_type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.applewear.crm.util.common.TableConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = TableConstants.TBL_STOCK_TRANSACTION_TYPE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockTransactionType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "txn_type_id")
	private Long id;

	@Column(name = "txn_type_name")
	private String typeName;

	@Column(name = "txn_desc")
	private String txnDesc;

	@Column(name = "indicator")
	private Integer indicator;

}
