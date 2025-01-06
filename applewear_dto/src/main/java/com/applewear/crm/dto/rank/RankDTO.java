package com.applewear.crm.dto.rank;

import java.math.BigDecimal;

import com.applewear.crm.entity.rank.Rank;
import com.applewear.crm.util.common.CommonUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankDTO {

	private Long rankId;

	private String name;

	private BigDecimal discount;

	private String discountText;

	private Integer num;

	public RankDTO(Rank entity) {

		if (entity != null) {

			this.rankId = entity.getId();

			this.name = entity.getRankName();

			this.discount = entity.getDiscount();

			this.discountText = CommonUtil.formatNumber(entity.getDiscount());

		}

	}

}
