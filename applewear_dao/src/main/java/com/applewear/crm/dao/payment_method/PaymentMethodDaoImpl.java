package com.applewear.crm.dao.payment_method;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.applewear.crm.dao.generic.GenericDaoImpl;
import com.applewear.crm.entity.payment_method.PaymentMethod;

@Repository
@Transactional
public class PaymentMethodDaoImpl extends GenericDaoImpl<PaymentMethod, Long> implements PaymentMethodDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<PaymentMethod> getAllPaymentMethods() {
		StringBuilder builder = new StringBuilder(" SELECT * FROM tbl_paymentmethod ");
		builder.append(" ORDER BY paymentmethod_name ");
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(builder.toString());
		sqlQuery.addEntity(PaymentMethod.class);
		return sqlQuery.list();
	}

}
