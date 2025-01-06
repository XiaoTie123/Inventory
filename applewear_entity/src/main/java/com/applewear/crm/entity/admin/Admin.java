package com.applewear.crm.entity.admin;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.applewear.crm.entity.base.BaseEntity;
import com.applewear.crm.util.common.TableConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = TableConstants.USERS)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Admin extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "loginId")
	private String loginId;

	@Column(name = "email")
	private String email;

	@Column(name = "api_key")
	private String apiKey;

	@Column(name = "email_verified_at")
	private Date eamilVerified;

	@Column(name = "password")
	private String password;

	@Column(name = "remember_token")
	private String rememberToken;

	@Column(name = "role")
	private Integer role;

	@Column(name = "sessionId")
	private String sessionId;
	
	@Column(name = "del_flag")
	private Integer delflag;
}
