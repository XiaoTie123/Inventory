package com.applewear.crm.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopTenProductDTO {

	private String productName;

	private String productCode;

	private Long totalSales;

}
