package com.applewear.crm.dto.admin;

import java.util.Date;
import com.applewear.crm.entity.admin.Admin;
import com.applewear.crm.util.common.CommonUtil;
import com.applewear.crm.util.enums.RoleType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class AdminDTO {

	private Long adminId;
	private String adminName;

	private String loginName;

	private String email;
	private String apiKey;
	private Date emailVerified;
	private String password;
	private String rememberToken;
	private Integer role;
	private String roleName;
	private String sessionId;
	private Integer num;
	private String passwordName;
	private Integer delflag;

	public AdminDTO(Admin entity) {

		if (entity != null) {

			this.adminId = entity.getId();

			this.adminName = entity.getName();

			this.loginName = entity.getLoginId();

			this.email = entity.getEmail();

			this.apiKey = entity.getApiKey();

			this.emailVerified = entity.getEamilVerified();

			// this.password = entity.getPassword();

			this.passwordName = ".........";

			this.rememberToken = entity.getRememberToken();

			this.role = entity.getRole();

			this.roleName = CommonUtil.validInteger(entity.getRole()) ? RoleType.getDescriptionByCode(entity.getRole())
					: "";

			this.sessionId = entity.getSessionId();
			
			this.delflag = entity.getDelflag();
		}

	}

}
