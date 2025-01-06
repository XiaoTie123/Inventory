package com.applewear.crm.dto.customer;

import com.applewear.crm.entity.customer.Customer;
import com.applewear.crm.util.common.CommonUtil;
import com.applewear.crm.util.enums.RecordStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

	private Long customerId;

	private String name;

	private String email;

	private String password;

	private String loginId;

	private String mobile;

	private String address;

	private String rankName;

	private Long rankId;

	private Integer visible;

	private String visibleDesc;

	private Integer num;

	public CustomerDTO(Customer entity) {

		if (entity != null) {

			this.customerId = entity.getId();

			this.name = entity.getUserName();

			this.email = entity.getEmail();

			this.password = entity.getPassword();

			this.loginId = entity.getLoginId();

			this.mobile = entity.getMobile();

			this.address = entity.getAddress();

			this.visible = entity.getVisible();

			this.visibleDesc = CommonUtil.validInteger(entity.getVisible())
					? RecordStatus.getDescriptionByCode(entity.getVisible())
					: "Inactive";

			if (entity.getRank() != null) {

				this.rankId = entity.getRank().getId();

				this.rankName = entity.getRank().getRankName();

			}

		}

	}

}
