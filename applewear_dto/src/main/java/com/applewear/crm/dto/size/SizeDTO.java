package com.applewear.crm.dto.size;

import com.applewear.crm.entity.size.Size;
import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SizeDTO {
	
	private Long sizeId;
	
	private String sizeName;
	
	private String sizeCode;
	
	private String createdTime;

	private String updatedTime;

	public SizeDTO(Size entity) {

		if (entity != null) {

			this.sizeId = entity.getId();

			this.sizeName = entity.getSizeName();
			
			this.sizeCode = entity.getSizeCode();

			this.createdTime = entity.getCreatedTime() != null
					? CommonUtil.dateToString(CommonConstants.STD_DATE_TIME_FORMAT, entity.getCreatedTime())
					: "";

			this.updatedTime = entity.getUpdatedTime() != null
					? CommonUtil.dateToString(CommonConstants.STD_DATE_TIME_FORMAT, entity.getUpdatedTime())
					: "";

		}

	}
}
