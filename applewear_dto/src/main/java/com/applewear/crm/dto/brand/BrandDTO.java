package com.applewear.crm.dto.brand;

import com.applewear.crm.entity.brand.Brand;
import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO {
	
	private Long brandId;
	
	private String brandName;
	
	private String createdTime;

	private String updatedTime;

	public BrandDTO(Brand entity) {

		if (entity != null) {

			this.brandId = entity.getId();

			this.brandName = entity.getBrandName();

			this.createdTime = entity.getCreatedTime() != null
					? CommonUtil.dateToString(CommonConstants.STD_DATE_TIME_FORMAT, entity.getCreatedTime())
					: "";

			this.updatedTime = entity.getUpdatedTime() != null
					? CommonUtil.dateToString(CommonConstants.STD_DATE_TIME_FORMAT, entity.getUpdatedTime())
					: "";

		}

	}
}
