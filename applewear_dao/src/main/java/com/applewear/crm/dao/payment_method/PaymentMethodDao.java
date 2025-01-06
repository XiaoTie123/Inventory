package com.applewear.crm.dao.payment_method;

import java.util.List;

import com.applewear.crm.dao.generic.GenericDao;
import com.applewear.crm.entity.payment_method.PaymentMethod;

public interface PaymentMethodDao extends GenericDao<PaymentMethod, Long> {

	List<PaymentMethod> getAllPaymentMethods();

}
