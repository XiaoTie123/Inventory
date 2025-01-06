package com.applewear.crm.dto.category;

import com.applewear.crm.entity.category.Category;
import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
	
	private Long categoryId;
	
	private String categoryName;
	
	private String createdTime;

	private String updatedTime;

	public CategoryDTO(Category entity) {

		if (entity != null) {

			this.categoryId = entity.getId();

			this.categoryName = entity.getCategoryName();

			this.createdTime = entity.getCreatedTime() != null
					? CommonUtil.dateToString(CommonConstants.STD_DATE_TIME_FORMAT, entity.getCreatedTime())
					: "";

			this.updatedTime = entity.getUpdatedTime() != null
					? CommonUtil.dateToString(CommonConstants.STD_DATE_TIME_FORMAT, entity.getUpdatedTime())
					: "";

		}

	}
}
