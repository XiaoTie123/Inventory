package com.applewear.crm.dto.inventory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDTO {

	private Long productId;

	private String productCode;

	private String productName;

	private String brandName;

	private Integer num;

	private String productPhoto;

	private Integer quantity;

}
