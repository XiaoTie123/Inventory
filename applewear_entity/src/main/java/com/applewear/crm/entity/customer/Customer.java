package com.applewear.crm.entity.customer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.applewear.crm.entity.base.BaseEntity;
import com.applewear.crm.entity.rank.Rank;
import com.applewear.crm.util.common.TableConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = TableConstants.TBL_CUSTOMER)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 6844678835315340177L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Long id;

	@Column(name = "username")
	private String userName;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "login_id")
	private String loginId;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "address")
	private String address;

	@ManyToOne
	@JoinColumn(name = "rank_id")
	private Rank rank;

	@Column(name = "visible")
	private Integer visible;

	@Column(name = "api_key")
	private String apiKey;

}
