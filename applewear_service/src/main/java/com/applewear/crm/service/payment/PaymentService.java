package com.applewear.crm.service.payment;

import java.math.BigDecimal;
import java.util.List;

import com.applewear.crm.dto.payment.PaymentDTO;
import com.applewear.crm.dto.payment.PaymentSearchDTO;

public interface PaymentService {

	List<PaymentDTO> searchPayments(PaymentSearchDTO searchDTO);

	Long searchPaymentCount(PaymentSearchDTO searchDTO);

	Long managePayment(PaymentDTO paymentDTO);

	PaymentDTO getPaymentById(Long paymentId);

	List<PaymentDTO> getPaymentByCustomerId(Long customerId);

	String getTotalPaidByCustomerId(Long customerId);

}
