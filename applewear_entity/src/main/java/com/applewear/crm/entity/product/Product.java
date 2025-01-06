package com.applewear.crm.entity.product;

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
import com.applewear.crm.entity.brand.Brand;
import com.applewear.crm.entity.category.Category;
import com.applewear.crm.entity.size.Size;
import com.applewear.crm.entity.user.User;
import com.applewear.crm.util.common.TableConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = TableConstants.TBL_PRODUCT)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;

	@Column(name = "product_code")
	private String productCode;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "quantity_per_pack")
	private Integer quantityPerPack;

	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "size_id")
	private Size size;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "product_photo")
	private String productPhoto;

	@Column(name = "buying_price")
	private BigDecimal buyingPrice;

	@Column(name = "margin")
	private Integer margin;

	@Column(name = "selling_price")
	private BigDecimal sellingPrice;

	@Column(name = "transportation")
	private Integer transportation;

	@Column(name = "one_time_product")
	private Integer oneTimeProduct;
}
