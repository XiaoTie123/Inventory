package com.applewear.crm.entity.payment_method;

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
@Table(name = TableConstants.TBL_PAYMENT_METHOD)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PaymentMethod extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "paymentmethod_id")
	private Long id;

	@Column(name = "paymentmethod_name")
	private String name;

}
