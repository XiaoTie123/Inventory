package com.applewear.crm.dao.customer;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.applewear.crm.dao.generic.GenericDaoImpl;
import com.applewear.crm.dto.customer.CustomerSearchDTO;
import com.applewear.crm.dto.customer.TopCreditCustomerDTO;
import com.applewear.crm.dto.customer.TopTenCustomerDTO;
import com.applewear.crm.entity.customer.Customer;
import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;

@Repository
@Transactional
public class CustomerDaoImpl extends GenericDaoImpl<Customer, Long> implements CustomerDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> searchCustomers(CustomerSearchDTO searchDTO) {

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(prepareSearchQuery(searchDTO, false));

		sqlQuery.addEntity(Customer.class);

		addParameterForSearchQuery(searchDTO, sqlQuery);

		return sqlQuery.list();
	}

	@Override
	public Long searchCustomerCount(CustomerSearchDTO searchDTO) {

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(prepareSearchQuery(searchDTO, true));

		addParameterForSearchQuery(searchDTO, sqlQuery);

		return ((Number) sqlQuery.uniqueResult()).longValue();
	}

	private void addParameterForSearchQuery(CustomerSearchDTO searchDTO, SQLQuery sqlQuery) {

		if (CommonUtil.validString(searchDTO.getName())) {
			sqlQuery.setParameter("name", "%" + searchDTO.getName() + "%");
		}

		if (CommonUtil.validString(searchDTO.getEmail())) {
			sqlQuery.setParameter("email", "%" + searchDTO.getEmail() + "%");
		}

		if (CommonUtil.validString(searchDTO.getPhoneNo())) {
			sqlQuery.setParameter("mobile", "%" + searchDTO.getPhoneNo() + "%");
		}

		if (CommonUtil.validString(searchDTO.getLoginId())) {
			sqlQuery.setParameter("loginId", "%" + searchDTO.getLoginId() + "%");
		}

	}

	private String prepareSearchQuery(CustomerSearchDTO searchDTO, boolean countOnly) {

		StringBuilder query = new StringBuilder("");

		if (countOnly) {
			query.append("SELECT COUNT(DISTINCT c.customer_id) ");
		} else {
			query.append("SELECT c.* ");
		}

		query.append(" FROM tbl_customer c ");

		query.append(" WHERE 1=1 ");

		if (CommonUtil.validString(searchDTO.getName())) {
			query.append(" AND c.username LIKE :name ");
		}

		if (CommonUtil.validString(searchDTO.getEmail())) {
			query.append(" AND c.email LIKE :email ");
		}

		if (CommonUtil.validString(searchDTO.getPhoneNo())) {
			query.append(" AND c.mobile LIKE :mobile ");
		}

		if (CommonUtil.validString(searchDTO.getLoginId())) {
			query.append(" AND c.login_id LIKE :loginId ");
		}

		if (!countOnly) {

			query.append(" GROUP BY c.customer_id ");
			query.append("ORDER BY c.created_at DESC ");

			Integer start = searchDTO.getStart();
			Integer length = searchDTO.getLength();
			query.append("LIMIT ");
			if (!CommonUtil.validNumber(searchDTO.getStart())) {
				start = 0;
			}
			if (!CommonUtil.validNumber(searchDTO.getLength())) {
				length = CommonConstants.ADMIN_RECORD_PER_PAGE;
			}
			query.append(start);
			query.append(", ");
			query.append(length);
		}

		return query.toString();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TopCreditCustomerDTO> getTopCreditCustomers() {

		StringBuilder builder = new StringBuilder(
				" SELECT c.customer_id AS customerId, c.username AS name, SUM(o.total) AS totalCredit ");
		builder.append(" FROM tbl_customer c ");
		builder.append(" JOIN tbl_order o ON o.customer_id = c.customer_id ");
		builder.append(" WHERE o.order_status = 2 ");
		builder.append(" AND o.order_id NOT IN (SELECT DISTINCT p.order_id FROM payment p ) ");
		builder.append(" GROUP BY c.customer_id, c.username ");
		builder.append(" ORDER BY totalCredit DESC LIMIT 10 ");

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(builder.toString())
				.addScalar("customerId", StandardBasicTypes.LONG).addScalar("name", StandardBasicTypes.STRING)
				.addScalar("totalCredit", StandardBasicTypes.BIG_DECIMAL);

		sqlQuery.setResultTransformer(Transformers.aliasToBean(TopCreditCustomerDTO.class));

		return sqlQuery.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TopTenCustomerDTO> getTopTenCustomers() {
		StringBuilder builder = new StringBuilder(
				" SELECT o.customer_id AS customerId, c.username AS name, SUM(od.total) AS totalAmount ");
		builder.append(" FROM tbl_order o ");
		builder.append(" JOIN tbl_orderdetail od ON o.order_id = od.order_id ");
		builder.append(" JOIN tbl_customer c ON o.customer_id = c.customer_id ");
		builder.append(" WHERE o.order_status = 2 ");
		builder.append(" GROUP BY o.customer_id, c.username ");
		builder.append(" ORDER BY totalAmount DESC ");
		builder.append(" LIMIT 10 ");
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(builder.toString())
				.addScalar("customerId", StandardBasicTypes.LONG).addScalar("name", StandardBasicTypes.STRING)
				.addScalar("totalAmount", StandardBasicTypes.BIG_DECIMAL);

		sqlQuery.setResultTransformer(Transformers.aliasToBean(TopTenCustomerDTO.class));

		return sqlQuery.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> searchCustomerAutocomplte(String searchKey) {

		StringBuilder builder = new StringBuilder(" SELECT * FROM tbl_customer ");

		builder.append(" WHERE 1=1 ");

		if (CommonUtil.validString(searchKey)) {
			builder.append(" AND username LIKE :name ");
		}

		builder.append(" ORDER BY username LIMIT 20 ");

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(builder.toString());

		sqlQuery.addEntity(Customer.class);

		sqlQuery.setParameter("name", "%" + searchKey + "%");

		return sqlQuery.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAllCustomers() {
		StringBuilder builder = new StringBuilder(" SELECT * FROM tbl_customer ");
		builder.append(" ORDER BY username ");
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(builder.toString());
		sqlQuery.addEntity(Customer.class);
		return sqlQuery.list();
	}

}
