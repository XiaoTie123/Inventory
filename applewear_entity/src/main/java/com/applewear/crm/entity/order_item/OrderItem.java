package com.applewear.crm.entity.order_item;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.applewear.crm.entity.base.BaseEntity;
import com.applewear.crm.entity.order.Order;
import com.applewear.crm.entity.product.Product;
import com.applewear.crm.util.common.TableConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = TableConstants.TBL_ORDER_DETAIL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderItem extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderdetail_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "discount")
	private BigDecimal discount;

	@Column(name = "selling_price")
	private BigDecimal sellingPrice;

	@Column(name = "pack")
	private Integer pack;

	@Column(name = "quantity_per_pack")
	private Integer quantityPerPack;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "total")
	private BigDecimal total;

}
