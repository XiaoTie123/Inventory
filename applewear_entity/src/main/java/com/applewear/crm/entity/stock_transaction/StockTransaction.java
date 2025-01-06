package com.applewear.crm.entity.stock_transaction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.applewear.crm.entity.admin.Admin;
import com.applewear.crm.entity.base.BaseEntity;
import com.applewear.crm.entity.product.Product;
import com.applewear.crm.entity.transaction_type.StockTransactionType;
import com.applewear.crm.util.common.TableConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = TableConstants.TBL_STOCK_TRANSACTION)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StockTransaction extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_txn_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "quantity")
	private Integer quantity;

	@ManyToOne
	@JoinColumn(name = "txn_type_id")
	private StockTransactionType stockTransactionType;

	@Column(name = "ref_no")
	private String refNo;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Admin user;

	@Column(name = "remark")
	private String remark;

	@Column(name = "attachment")
	private String attachment;

}
