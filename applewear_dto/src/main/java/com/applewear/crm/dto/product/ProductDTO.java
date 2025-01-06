package com.applewear.crm.dto.product;

import com.applewear.crm.entity.product.Product;
import com.applewear.crm.util.common.CommonUtil;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class ProductDTO {

	private Long productId;

	private String productCode;

	private String productName;

	private Integer quantity;

	private Long brandId;

	private String brandName;

	private Long categoryId;

	private String categoryName;

	private Long sizeId;

	private String sizeName;

	private Long userId;

	private String userName;

	private double price;

	private String productPhoto;

	private BigDecimal buyingPrice;

	private Integer margin;

	private BigDecimal sellingPrice;

	private Integer transportation;

	private Integer num;

	private Integer oneTimeProduct;

	private MultipartFile productImageFile;

	public ProductDTO(Product entity) {

		if (entity != null) {

			this.productId = entity.getId();

			this.productName = entity.getProductName();

			this.productCode = entity.getProductCode();

			if (entity.getBrand() != null) {

				this.brandId = entity.getBrand().getId();

				this.brandName = entity.getBrand().getBrandName();

			}

			if (entity.getCategory() != null) {
				this.categoryId = entity.getCategory().getId();

				this.categoryName = entity.getCategory().getCategoryName();
			}

			if (entity.getSize() != null) {

				if (CommonUtil.validLong(entity.getSize().getId())) {
					this.sizeId = entity.getSize().getId();
				}

				this.sizeName = entity.getSize().getSizeName();

			}

			this.userId = entity.getUser().getId();

			this.userName = entity.getUser().getName();

			this.sellingPrice = entity.getSellingPrice();

			this.oneTimeProduct = entity.getOneTimeProduct();

			this.buyingPrice = entity.getBuyingPrice();

			this.margin = entity.getMargin();

			this.transportation = entity.getTransportation();

			this.quantity = entity.getQuantityPerPack();

			this.productPhoto = CommonUtil.validString(entity.getProductPhoto()) ? entity.getProductPhoto() : "";
		}

	}

}
