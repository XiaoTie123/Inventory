package com.applewear.crm.dto.stock_transaction;

import com.applewear.crm.entity.stock_transaction.StockTransaction;
import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockTransactionDTO {

	private Long stockTransactionId;

	private Long productId;

	private String productName;

	private Integer quantity;

	private Long transactionTypeId;

	private String transactionTypeName;

	private String refNo;

	private String remark;

	private String attachment;

	private Long submittedById;

	private String submittedByName;

	private Integer num;

	private String createdDate;

	public StockTransactionDTO(StockTransaction entity) {

		if (entity != null) {

			this.stockTransactionId = entity.getId();

			if (entity.getProduct() != null) {

				this.productId = entity.getProduct().getId();

				this.productName = entity.getProduct().getProductName();

			}

			this.quantity = entity.getQuantity();

			this.refNo = entity.getRefNo();

			this.remark = entity.getRemark();

			this.attachment = entity.getAttachment();

			if (entity.getStockTransactionType() != null) {

				this.transactionTypeId = entity.getStockTransactionType().getId();

				this.transactionTypeName = entity.getStockTransactionType().getTypeName();

			}

			if (entity.getUser() != null) {

				this.submittedById = entity.getUser().getId();

				this.submittedByName = entity.getUser().getName();

			}

			this.createdDate = entity.getCreatedTime() != null
					? CommonUtil.dateToString(CommonConstants.STD_DATE_FORMAT, entity.getCreatedTime())
					: "";

		}

	}

}
