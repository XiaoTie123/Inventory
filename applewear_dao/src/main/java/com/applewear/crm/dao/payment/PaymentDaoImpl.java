package com.applewear.crm.dao.payment;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.applewear.crm.dao.generic.GenericDaoImpl;
import com.applewear.crm.dto.payment.PaymentDTO;
import com.applewear.crm.dto.payment.PaymentSearchDTO;
import com.applewear.crm.entity.payment.Payment;
import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;

@Repository
@Transactional
public class PaymentDaoImpl extends GenericDaoImpl<Payment, Long> implements PaymentDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<PaymentDTO> searchPayments(PaymentSearchDTO searchDTO) {

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(searchPaymentQuery(searchDTO, false))
				.addScalar("paymentId", StandardBasicTypes.LONG).addScalar("paymentDate", StandardBasicTypes.STRING)
				.addScalar("totalPaidAmount", StandardBasicTypes.BIG_DECIMAL)
				.addScalar("orderRef", StandardBasicTypes.STRING);

		addParameterForSearchQuery(sqlQuery, searchDTO);

		sqlQuery.setResultTransformer(Transformers.aliasToBean(PaymentDTO.class));

		return sqlQuery.list();
	}

	@Override
	public Long searchPaymentCount(PaymentSearchDTO searchDTO) {

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(searchPaymentQuery(searchDTO, true));

		addParameterForSearchQuery(sqlQuery, searchDTO);

		return ((Number) sqlQuery.uniqueResult()).longValue();
	}

	private String searchPaymentQuery(PaymentSearchDTO searchDTO, boolean isCountOnly) {

		StringBuilder builder = new StringBuilder();

		if (isCountOnly) {
			builder.append(" SELECT COUNT(DISTINCT p.id) ");
		} else {
			builder.append(
					" SELECT p.id AS paymentId, p.order_ref AS orderRef, DATE_FORMAT(p.order_date, '%d/%m/%Y') AS paymentDate, ");
			builder.append(" p.total_paid AS totalPaidAmount ");
		}

		builder.append(" FROM payment p ");
		builder.append(" WHERE 1=1 ");

		addSearchPaymentCreteria(builder, searchDTO);

		if (!isCountOnly) {

			builder.append("ORDER BY p.order_date DESC ");

			appendPagination(builder, searchDTO);
		}

		return builder.toString();

	}

	private void appendPagination(StringBuilder builder, PaymentSearchDTO searchDTO) {

		Integer start = CommonUtil.validNumber(searchDTO.getStart()) ? searchDTO.getStart() : 0;
		Integer length = CommonUtil.validNumber(searchDTO.getLength()) ? searchDTO.getLength()
				: CommonConstants.ADMIN_RECORD_PER_PAGE;
		builder.append("LIMIT ").append(start).append(", ").append(length);

	}

	private void addSearchPaymentCreteria(StringBuilder builder, PaymentSearchDTO searchDTO) {

		if (CommonUtil.validLong(searchDTO.getCustomerId())) {
			builder.append(" AND p.customer_id =:customerId ");
		}

	}

	private void addParameterForSearchQuery(SQLQuery sqlQuery, PaymentSearchDTO searchDTO) {
		if (CommonUtil.validLong(searchDTO.getCustomerId())) {
			sqlQuery.setParameter("customerId", searchDTO.getCustomerId());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PaymentDTO> getPaymentsByCustomerId(Long customerId) {
		StringBuilder builder = new StringBuilder();
		builder.append(
				" SELECT p.id AS paymentId, p.order_ref AS orderRef, DATE_FORMAT(p.order_date, '%d/%m/%Y') AS paymentDate, ");
		builder.append(" p.total_paid AS totalPaidAmount ");
		builder.append(" FROM payment p ");
		builder.append(" WHERE p.customer_id =:customerId ");

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(builder.toString())
				.addScalar("paymentId", StandardBasicTypes.LONG).addScalar("paymentDate", StandardBasicTypes.STRING)
				.addScalar("totalPaidAmount", StandardBasicTypes.BIG_DECIMAL)
				.addScalar("orderRef", StandardBasicTypes.STRING);

		sqlQuery.setParameter("customerId", customerId);

		sqlQuery.setResultTransformer(Transformers.aliasToBean(PaymentDTO.class));

		return sqlQuery.list();
	}

	@Override
	public BigDecimal getTotalPaidByCustomerId(Long customerId) {
		StringBuilder builder = new StringBuilder(" SELECT SUM(IFNULL(p.total_paid,0)) AS totalPaid ");
		builder.append(" FROM payment p ");
		builder.append(" WHERE p.customer_id =:customerId ");
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(builder.toString()).addScalar("totalPaid",
				StandardBasicTypes.BIG_DECIMAL);
		sqlQuery.setParameter("customerId", customerId);
		return (BigDecimal) sqlQuery.uniqueResult();
	}

	@Override
	public boolean deletePaymentByOrderId(Long orderId) {
		StringBuilder builder = new StringBuilder(" DELETE FROM payment WHERE order_id =:orderId ");
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(builder.toString());
		sqlQuery.setParameter("orderId", orderId);
		return sqlQuery.executeUpdate() > 0;
	}

	@Override
	public boolean isExistPaymentByCustomerId(Long customerId) {
		StringBuilder builder = new StringBuilder(" SELECT COUNT(id) FROM payment ");
		builder.append(" WHERE customer_id =:customerId");
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(builder.toString());
		sqlQuery.setParameter("customerId", customerId);
		return ((Number) sqlQuery.uniqueResult()).longValue() > 0;
	}

}
