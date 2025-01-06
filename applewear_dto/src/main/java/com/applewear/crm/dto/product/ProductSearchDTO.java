package com.applewear.crm.dto.product;

import java.math.BigDecimal;
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
public class ProductSearchDTO extends SearchDTO {

	private Long productId;
	private String productCode;
	private Long brandId;
	private Long categoryId;
	private Long sizeId;
	private BigDecimal sellingPrice;
	
	private List<ProductDTO> data;


}
