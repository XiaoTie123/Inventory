package com.applewear.crm.entity.order;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.applewear.crm.entity.admin.Admin;
import com.applewear.crm.entity.base.BaseEntity;
import com.applewear.crm.entity.customer.Customer;
import com.applewear.crm.util.common.TableConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = TableConstants.TBL_ORDER)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Order extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long id;

	@Column(name = "order_ref")
	private String order_ref;

	@Column(name = "order_date")
	private Date orderDate;

	@Column(name = "total")
	private BigDecimal total;

	@Column(name = "totalpaid")
	private BigDecimal totalPaid;

	@Column(name = "discount")
	private BigDecimal discount;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Admin admin;

	@Column(name = "order_status")
	private Integer orderStatus;

	@Column(name = "attachment")
	private String attachment;

	@Column(name = "payment")
	private Integer payment;

}
