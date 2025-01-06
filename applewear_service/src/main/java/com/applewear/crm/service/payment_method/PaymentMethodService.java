package com.applewear.crm.service.payment_method;

import java.util.List;

import com.applewear.crm.dto.payment_method.PaymentMethodDTO;

public interface PaymentMethodService {

	List<PaymentMethodDTO> getAllPaymentMethods();

	Long managePaymentMethod(PaymentMethodDTO paymentMethodDTO);

	PaymentMethodDTO getPaymentMethodById(Long paymentMethodId);
	
	boolean deletePaymentById(Long paymentMethodId);

}
