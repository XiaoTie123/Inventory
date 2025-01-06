package com.applewear.crm.dao.payment;

import java.math.BigDecimal;
import java.util.List;

import com.applewear.crm.dao.generic.GenericDao;
import com.applewear.crm.dto.payment.PaymentDTO;
import com.applewear.crm.dto.payment.PaymentSearchDTO;
import com.applewear.crm.entity.payment.Payment;

public interface PaymentDao extends GenericDao<Payment, Long> {

	List<PaymentDTO> searchPayments(PaymentSearchDTO searchDTO);

	Long searchPaymentCount(PaymentSearchDTO searchDTO);

	List<PaymentDTO> getPaymentsByCustomerId(Long customerId);

	BigDecimal getTotalPaidByCustomerId(Long customerId);

	boolean deletePaymentByOrderId(Long orderId);

	boolean isExistPaymentByCustomerId(Long customerId);

}
