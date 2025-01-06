package com.applewear.crm.entity.payment;

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

import com.applewear.crm.entity.base.BaseEntity;
import com.applewear.crm.entity.customer.Customer;
import com.applewear.crm.entity.order.Order;
import com.applewear.crm.entity.payment_method.PaymentMethod;
import com.applewear.crm.util.common.TableConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = TableConstants.TBL_PAYMENT)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Payment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@Column(name = "order_ref")
	private String orderRef;

	@Column(name = "order_date")
	private Date orderDate;

	@Column(name = "total_paid")
	private BigDecimal totalPaid;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "payment_method_id")
	private PaymentMethod paymentMethod;

	@Column(name = "payment_slip")
	private String paymentSlip;

}
