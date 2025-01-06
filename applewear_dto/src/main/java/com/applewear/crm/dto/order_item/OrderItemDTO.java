package com.applewear.crm.dto.order_item;

import java.math.BigDecimal;

import com.applewear.crm.entity.order_item.OrderItem;
import com.applewear.crm.util.common.CommonUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {

	private Long orderItemId;

	private Long orderId;

	private String orderRef;

	private Long productId;

	private String productName;

	private BigDecimal originalPrice;

	private String originalPriceDesc;

	private BigDecimal discount;

	private String discountDesc;

	private BigDecimal sellingPrice;

	private String sellingPriceDesc;

	private Integer pack;

	private Integer quantityPerPack;

	private Integer quantity;

	private BigDecimal totalAmount;

	private String totalAmountDesc;

	public OrderItemDTO(OrderItem entity) {

		if (entity != null) {

			this.orderItemId = entity.getId();

			this.productId = entity.getProduct().getId();

			this.productName = entity.getProduct().getProductName();

			this.originalPrice = entity.getProduct().getBuyingPrice();

			this.originalPriceDesc = CommonUtil.formatNumber(entity.getProduct().getBuyingPrice());

			this.discount = entity.getDiscount();

			this.discountDesc = CommonUtil.formatNumber(entity.getDiscount());

			this.sellingPrice = entity.getSellingPrice();

			this.sellingPriceDesc = CommonUtil.formatNumber(entity.getSellingPrice());

			this.pack = entity.getPack();

			this.quantityPerPack = entity.getQuantityPerPack();

			this.quantity = entity.getQuantity();

			this.totalAmount = entity.getTotal();

			this.totalAmountDesc = CommonUtil.formatNumber(entity.getTotal());

		}

	}

}
