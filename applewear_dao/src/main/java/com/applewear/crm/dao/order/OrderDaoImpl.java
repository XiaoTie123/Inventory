package com.applewear.crm.dao.order;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.applewear.crm.dao.generic.GenericDaoImpl;
import com.applewear.crm.dto.customer.CustomerBalanceDTO;
import com.applewear.crm.dto.order.OrderDTO;
import com.applewear.crm.dto.order.OrderSearchDTO;
import com.applewear.crm.dto.sale_report.SaleReportDTO;
import com.applewear.crm.dto.sale_report.SaleReportSearchDTO;
import com.applewear.crm.entity.order.Order;
import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;
import com.applewear.crm.util.enums.OrderStatus;

@Repository
@Transactional
public class OrderDaoImpl extends GenericDaoImpl<Order, Long> implements OrderDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDTO> searchOrders(OrderSearchDTO searchDTO) {

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(searchQueryForOrders(searchDTO, false))
				.addScalar("orderId", StandardBasicTypes.LONG).addScalar("orderRef", StandardBasicTypes.STRING)
				.addScalar("orderDate", StandardBasicTypes.STRING).addScalar("customerId", StandardBasicTypes.LONG)
				.addScalar("totalPaid", StandardBasicTypes.BIG_DECIMAL)
				.addScalar("totalAmount", StandardBasicTypes.BIG_DECIMAL)
				.addScalar("discount", StandardBasicTypes.BIG_DECIMAL)
				.addScalar("remainingAmount", StandardBasicTypes.BIG_DECIMAL)
				.addScalar("customerName", StandardBasicTypes.STRING)
				.addScalar("customerPhone", StandardBasicTypes.STRING)
				.addScalar("customerRank", StandardBasicTypes.STRING)
				.addScalar("orderStatus", StandardBasicTypes.INTEGER)
				.addScalar("submittedByName", StandardBasicTypes.STRING);

		addParameterForSearchQuery(sqlQuery, searchDTO);

		sqlQuery.setResultTransformer(Transformers.aliasToBean(OrderDTO.class));

		return sqlQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDTO> searchOrdersNoti(OrderSearchDTO searchDTO) {

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(searchQueryForOrdersNoti(searchDTO, false))
				.addScalar("orderId", StandardBasicTypes.LONG).addScalar("orderRef", StandardBasicTypes.STRING)
				.addScalar("orderDate", StandardBasicTypes.STRING).addScalar("customerId", StandardBasicTypes.LONG)
				.addScalar("totalPaid", StandardBasicTypes.BIG_DECIMAL)
				.addScalar("totalAmount", StandardBasicTypes.BIG_DECIMAL)
				.addScalar("discount", StandardBasicTypes.BIG_DECIMAL)
				.addScalar("remainingAmount", StandardBasicTypes.BIG_DECIMAL)
				.addScalar("customerName", StandardBasicTypes.STRING)
				.addScalar("customerPhone", StandardBasicTypes.STRING)
				.addScalar("customerRank", StandardBasicTypes.STRING)
				.addScalar("orderStatus", StandardBasicTypes.INTEGER)
				.addScalar("submittedByName", StandardBasicTypes.STRING);

		addParameterForSearchQuery(sqlQuery, searchDTO);

		sqlQuery.setResultTransformer(Transformers.aliasToBean(OrderDTO.class));

		return sqlQuery.list();
	}

	@Override
	public Long searchOrderCount(OrderSearchDTO searchDTO) {

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(searchQueryForOrders(searchDTO, true));

		addParameterForSearchQuery(sqlQuery, searchDTO);

		return ((Number) sqlQuery.uniqueResult()).longValue();
	}
	
	@Override
	public Long searchOrderCountNoti(OrderSearchDTO searchDTO) {

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(searchQueryForOrdersNoti(searchDTO, true));

		addParameterForSearchQuery(sqlQuery, searchDTO);

		return ((Number) sqlQuery.uniqueResult()).longValue();
	}

	private String searchQueryForOrders(OrderSearchDTO searchDTO, boolean isCountOnly) {

		StringBuilder builder = new StringBuilder("");

		if (isCountOnly) {
			builder.append(" SELECT COUNT(DISTINCT o.order_id) ");
		} else {
			builder.append(
					" SELECT o.order_id AS orderId, o.order_ref AS orderRef, DATE_FORMAT(o.order_date, '%d/%m/%Y') AS orderDate, ");
			builder.append(
					" o.total AS totalAmount, o.totalpaid AS totalPaid, o.discount AS discount, COALESCE(o.total, 0) - COALESCE(o.totalpaid, 0) AS remainingAmount,  ");
			builder.append(" c.username AS customerName, c.mobile AS customerPhone, r.rank_name AS customerRank, ");
			builder.append(" o.order_status AS orderStatus, u.name AS submittedByName, c.customer_id AS customerId ");
		}

		builder.append(" FROM tbl_order o ");
		builder.append(" LEFT JOIN tbl_customer c ON c.customer_id = o.customer_id ");
		builder.append(" LEFT JOIN tbl_rank r ON r.rank_id = c.rank_id ");
		builder.append(" LEFT JOIN users u ON u.Id = o.user_id ");

		builder.append(" WHERE 1=1 ");

		addSearchCreteria(builder, searchDTO);

		if (!isCountOnly) {

			builder.append(" GROUP BY o.order_id ");

			builder.append("ORDER BY o.order_id DESC ");

			appendPagination(builder, searchDTO);
		}

		return builder.toString();

	}

	private void appendPagination(StringBuilder builder, OrderSearchDTO searchDTO) {

		Integer start = CommonUtil.validNumber(searchDTO.getStart()) ? searchDTO.getStart() : 0;
		Integer length = CommonUtil.validNumber(searchDTO.getLength()) ? searchDTO.getLength()
				: CommonConstants.ADMIN_RECORD_PER_PAGE;
		builder.append("LIMIT ").append(start).append(", ").append(length);

	}

	private void addSearchCreteria(StringBuilder builder, OrderSearchDTO searchDTO) {

		if (CommonUtil.validString(searchDTO.getOrderRef())) {
			builder.append(" AND o.order_ref LIKE :orderRef ");
		}

		if (CommonUtil.validString(searchDTO.getFromDate())) {
			builder.append(" AND DATE(o.order_date) >= DATE(:fromDate) ");
		}

		if (CommonUtil.validString(searchDTO.getToDate())) {
			builder.append(" AND DATE(o.order_date) <= DATE(:toDate) ");
		}

		if (CommonUtil.validString(searchDTO.getCustomerName())) {
			builder.append(" AND c.username LIKE :customerName ");
		}

		if (CommonUtil.validString(searchDTO.getCustomerPhone())) {
			builder.append(" AND c.mobile LIKE :customerPhone ");
		}

		if (CommonUtil.validInteger(searchDTO.getOrderStatus())) {
			builder.append(" AND o.order_status = :orderStatus ");
		}

		if (searchDTO.isProcessOnly()) {
			builder.append(" AND o.order_status <> " + OrderStatus.COMPLETE.getCode());
		}

		if (searchDTO.isCompleteOnly()) {
			builder.append(" AND o.order_status = " + OrderStatus.COMPLETE.getCode());
		}

		if (CommonUtil.validLong(searchDTO.getCustomerId())) {
			builder.append(" AND c.customer_id =:customerId ");
		}

		if (searchDTO.isPaymentCompleted()) {
			builder.append(" AND o.payment = 1 ");
		}

		if (searchDTO.isPaymentPending()) {
			builder.append(" AND o.payment <> 1 ");
		}

	}

	private void addParameterForSearchQuery(SQLQuery sqlQuery, OrderSearchDTO searchDTO) {

		if (CommonUtil.validString(searchDTO.getOrderRef())) {
			sqlQuery.setParameter("orderRef", "%" + searchDTO.getOrderRef() + "%");
		}

		if (CommonUtil.validString(searchDTO.getFromDate())) {
			sqlQuery.setParameter("fromDate",
					CommonUtil.stringToDate(CommonConstants.STD_DATE_FORMAT, searchDTO.getFromDate()));
		}

		if (CommonUtil.validString(searchDTO.getToDate())) {
			sqlQuery.setParameter("toDate",
					CommonUtil.stringToDate(CommonConstants.STD_DATE_FORMAT, searchDTO.getToDate()));
		}

		if (CommonUtil.validString(searchDTO.getCustomerName())) {
			sqlQuery.setParameter("customerName", "%" + searchDTO.getCustomerName() + "%");
		}

		if (CommonUtil.validString(searchDTO.getCustomerPhone())) {
			sqlQuery.setParameter("customerPhone", "%" + searchDTO.getCustomerPhone() + "%");
		}

		if (CommonUtil.validInteger(searchDTO.getOrderStatus())) {
			sqlQuery.setParameter("orderStatus", searchDTO.getOrderStatus());
		}

		if (CommonUtil.validLong(searchDTO.getCustomerId())) {
			sqlQuery.setParameter("customerId", searchDTO.getCustomerId());
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SaleReportDTO> searchMonthlyReport(SaleReportSearchDTO searchDTO) {

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(searchMonthlyReportQuery(searchDTO, false))
				.addScalar("year", StandardBasicTypes.INTEGER).addScalar("month", StandardBasicTypes.STRING)
				.addScalar("totalOrders", StandardBasicTypes.LONG)
				.addScalar("totalAmount", StandardBasicTypes.BIG_DECIMAL)
				.addScalar("totalPaid", StandardBasicTypes.BIG_DECIMAL);

		addParameterForSearchMonthlyReport(sqlQuery, searchDTO);

		sqlQuery.setResultTransformer(Transformers.aliasToBean(SaleReportDTO.class));

		return sqlQuery.list();
	}

	private void addParameterForSearchMonthlyReport(SQLQuery sqlQuery, SaleReportSearchDTO searchDTO) {

		if (CommonUtil.validString(searchDTO.getFromMonthYear())) {
			String[] parts = searchDTO.getFromMonthYear().split("/");
			sqlQuery.setParameter("fromMonth", parts[0]);
			sqlQuery.setParameter("fromYear", parts[1]);
		}

		if (CommonUtil.validString(searchDTO.getToMonthYear())) {
			String[] parts = searchDTO.getToMonthYear().split("/");
			sqlQuery.setParameter("toMonth", parts[0]);
			sqlQuery.setParameter("toYear", parts[1]);
		}

	}

	private String searchMonthlyReportQuery(SaleReportSearchDTO searchDTO, boolean isCountOnly) {

		StringBuilder builder = new StringBuilder();

		if (isCountOnly) {
			builder.append(" SELECT COUNT(DISTINCT YEAR(o.order_date), MONTH(o.order_date)) AS total_groups ");
		} else {
			builder.append(" SELECT YEAR(o.order_date) AS year, m.month_name AS month, ");
			builder.append(" COUNT(*) AS totalOrders, SUM(o.total) AS totalAmount, SUM(o.totalpaid) AS totalPaid ");
		}

		builder.append(" FROM tbl_order o ");
		builder.append(" JOIN tbl_month m ON MONTH(o.order_date) = m.month ");
		builder.append(" WHERE o.order_status = 2 ");

		addSearchMontlyReportCreteria(builder, searchDTO);

		if (!isCountOnly) {

			builder.append(" GROUP BY YEAR(o.order_date), m.month_name ");
			builder.append(" ORDER BY YEAR(o.order_date) DESC, m.month_name ASC ");

			if (!searchDTO.isExcelReport()) {
				appendMonthlyReportPagination(builder, searchDTO);
			}

		}

		return builder.toString();

	}

	private void appendMonthlyReportPagination(StringBuilder query, SaleReportSearchDTO searchDTO) {
		Integer start = CommonUtil.validNumber(searchDTO.getStart()) ? searchDTO.getStart() : 0;
		Integer length = CommonUtil.validNumber(searchDTO.getLength()) ? searchDTO.getLength()
				: CommonConstants.ADMIN_RECORD_PER_PAGE;
		query.append("LIMIT ").append(start).append(", ").append(length);
	}

	private void addSearchMontlyReportCreteria(StringBuilder builder, SaleReportSearchDTO searchDTO) {

		if (CommonUtil.validString(searchDTO.getFromMonthYear())) {
			builder.append(" AND MONTH(o.order_date) >=:fromMonth AND YEAR(o.order_date) >=:fromYear ");
		}

		if (CommonUtil.validString(searchDTO.getToMonthYear())) {
			builder.append(" AND MONTH(o.order_date) <=:toMonth AND YEAR(o.order_date) >=:toYear ");
		}

	}

	@Override
	public Long searchMonthlyReportCount(SaleReportSearchDTO searchDTO) {

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(searchMonthlyReportQuery(searchDTO, true));

		addParameterForSearchMonthlyReport(sqlQuery, searchDTO);

		return ((Number) sqlQuery.uniqueResult()).longValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SaleReportDTO> searchYearlyReport(SaleReportSearchDTO searchDTO) {
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(searchYearlyReportQuery(searchDTO, false))
				.addScalar("year", StandardBasicTypes.INTEGER).addScalar("totalOrders", StandardBasicTypes.LONG)
				.addScalar("totalAmount", StandardBasicTypes.BIG_DECIMAL)
				.addScalar("totalPaid", StandardBasicTypes.BIG_DECIMAL);

		sqlQuery.setResultTransformer(Transformers.aliasToBean(SaleReportDTO.class));

		addParameterForYearlyReport(sqlQuery, searchDTO);

		return sqlQuery.list();
	}

	@Override
	public Long searchYearlyReportCount(SaleReportSearchDTO searchDTO) {

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(searchYearlyReportQuery(searchDTO, true));

		addParameterForYearlyReport(sqlQuery, searchDTO);

		return ((Number) sqlQuery.uniqueResult()).longValue();
	}

	private String searchYearlyReportQuery(SaleReportSearchDTO searchDTO, boolean isCountOnly) {

		StringBuilder builder = new StringBuilder();

		if (isCountOnly) {
			builder.append(" SELECT COUNT(DISTINCT YEAR(o.order_date)) AS total_groups ");
		} else {
			builder.append(" SELECT YEAR(o.order_date) AS year, ");
			builder.append(" COUNT(*) AS totalOrders, SUM(o.total) AS totalAmount, SUM(o.totalpaid) AS totalPaid ");
		}

		builder.append(" FROM tbl_order o ");
		builder.append(" WHERE o.order_status = 2 ");

		addSearchYearlyReportCreteria(builder, searchDTO);

		if (!isCountOnly) {

			builder.append(" GROUP BY YEAR(o.order_date) ");
			builder.append(" ORDER BY YEAR(o.order_date) DESC ");

			appendYearlyReportPagination(builder, searchDTO);

		}

		return builder.toString();

	}

	private void addParameterForYearlyReport(SQLQuery sqlQuery, SaleReportSearchDTO searchDTO) {

		if (CommonUtil.validString(searchDTO.getFromYear())) {
			sqlQuery.setParameter("from", searchDTO.getFromYear());
		}

		if (CommonUtil.validString(searchDTO.getToYear())) {
			sqlQuery.setParameter("to", searchDTO.getToYear());
		}

	}

	private void addSearchYearlyReportCreteria(StringBuilder builder, SaleReportSearchDTO searchDTO) {

		if (CommonUtil.validString(searchDTO.getFromYear())) {
			builder.append(" AND YEAR(o.order_date) >=:from ");
		}

		if (CommonUtil.validString(searchDTO.getToYear())) {
			builder.append(" AND YEAR(o.order_date) <=:to ");
		}

	}

	private void appendYearlyReportPagination(StringBuilder query, SaleReportSearchDTO searchDTO) {
		Integer start = CommonUtil.validNumber(searchDTO.getStart()) ? searchDTO.getStart() : 0;
		Integer length = CommonUtil.validNumber(searchDTO.getLength()) ? searchDTO.getLength()
				: CommonConstants.ADMIN_RECORD_PER_PAGE;
		query.append("LIMIT ").append(start).append(", ").append(length);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SaleReportDTO> searchDailySaleReport(SaleReportSearchDTO searchDTO) {

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(searchDailyReportQuery(searchDTO, false))
				.addScalar("saleRawDate", StandardBasicTypes.DATE).addScalar("totalOrders", StandardBasicTypes.LONG)
				.addScalar("totalAmount", StandardBasicTypes.BIG_DECIMAL)
				.addScalar("totalPaid", StandardBasicTypes.BIG_DECIMAL);

		sqlQuery.setResultTransformer(Transformers.aliasToBean(SaleReportDTO.class));

		addParameterForDailyReport(sqlQuery, searchDTO);

		return sqlQuery.list();
	}

	@Override
	public Long searchDailySaleReportCount(SaleReportSearchDTO searchDTO) {

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(searchDailyReportQuery(searchDTO, true));

		addParameterForDailyReport(sqlQuery, searchDTO);

		return ((Number) sqlQuery.uniqueResult()).longValue();
	}

	private String searchDailyReportQuery(SaleReportSearchDTO searchDTO, boolean isCountOnly) {
		StringBuilder builder = new StringBuilder();

		if (isCountOnly) {
			builder.append(" SELECT COUNT(DISTINCT DATE_FORMAT(o.order_date, '%d/%m/%Y')) AS total_groups ");
		} else {

			builder.append(
					" SELECT COUNT(*) AS totalOrders, SUM(o.total) AS totalAmount, SUM(o.totalpaid) AS totalPaid, DATE(o.order_date) AS saleRawDate ");
		}

		builder.append(" FROM tbl_order o ");
		builder.append(" WHERE o.order_status = 2 ");

		addSearchDailyReportCriteria(builder, searchDTO);

		if (!isCountOnly) {
			builder.append(" GROUP BY DATE(o.order_date) ");
			builder.append(" ORDER BY saleRawDate DESC ");

			if (!searchDTO.isExcelReport()) {
				appendDailyReportPagination(builder, searchDTO);
			}

		}

		return builder.toString();
	}

	private void appendDailyReportPagination(StringBuilder builder, SaleReportSearchDTO searchDTO) {
		Integer start = CommonUtil.validNumber(searchDTO.getStart()) ? searchDTO.getStart() : 0;
		Integer length = CommonUtil.validNumber(searchDTO.getLength()) ? searchDTO.getLength()
				: CommonConstants.ADMIN_RECORD_PER_PAGE;
		builder.append("LIMIT ").append(start).append(", ").append(length);

	}

	private void addParameterForDailyReport(SQLQuery sqlQuery, SaleReportSearchDTO searchDTO) {

		if (CommonUtil.validString(searchDTO.getFromDate())) {
			sqlQuery.setParameter("from",
					CommonUtil.stringToDate(CommonConstants.STD_DATE_FORMAT, searchDTO.getFromDate()));
		}

		if (CommonUtil.validString(searchDTO.getToDate())) {
			sqlQuery.setParameter("to",
					CommonUtil.stringToDate(CommonConstants.STD_DATE_FORMAT, searchDTO.getToDate()));
		}

	}

	private void addSearchDailyReportCriteria(StringBuilder builder, SaleReportSearchDTO searchDTO) {

		if (CommonUtil.validString(searchDTO.getFromDate())) {
			builder.append(" AND DATE(o.order_date) >=:from ");
		}

		if (CommonUtil.validString(searchDTO.getToDate())) {
			builder.append(" AND DATE(o.order_date) <=:to ");
		}

	}

	@Override
	public CustomerBalanceDTO getCustomerBalance(Long customerId) {

		StringBuilder builder = new StringBuilder(
				" SELECT SUM(COALESCE(o.total, 0)) AS totalAmount, SUM(COALESCE(o.totalpaid, 0)) AS totalPaidAmount, ");
		builder.append(" SUM(COALESCE(o.total, 0)) - SUM(COALESCE(o.totalpaid, 0)) AS totalRemainingAmount ");

		builder.append(" FROM tbl_order o ");
		builder.append(" WHERE o.customer_id = :customerId ");

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(builder.toString())
				.addScalar("totalAmount", StandardBasicTypes.BIG_DECIMAL)
				.addScalar("totalPaidAmount", StandardBasicTypes.BIG_DECIMAL)
				.addScalar("totalRemainingAmount", StandardBasicTypes.BIG_DECIMAL);

		sqlQuery.setParameter("customerId", customerId);

		sqlQuery.setResultTransformer(Transformers.aliasToBean(CustomerBalanceDTO.class));

		return (CustomerBalanceDTO) sqlQuery.uniqueResult();
	}

	@Override
	public CustomerBalanceDTO getCustomerBalanceTotal() {

		StringBuilder builder = new StringBuilder(
				" SELECT SUM(COALESCE(o.total, 0)) AS totalAmount, SUM(COALESCE(o.totalpaid, 0)) AS totalPaidAmount, ");
		builder.append(" SUM(COALESCE(o.total, 0)) - SUM(COALESCE(o.totalpaid, 0)) AS totalRemainingAmount ");

		builder.append(" FROM tbl_order o ");
		builder.append(" WHERE 1 = 1 ");

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(builder.toString())
				.addScalar("totalAmount", StandardBasicTypes.BIG_DECIMAL)
				.addScalar("totalPaidAmount", StandardBasicTypes.BIG_DECIMAL)
				.addScalar("totalRemainingAmount", StandardBasicTypes.BIG_DECIMAL);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(CustomerBalanceDTO.class));

		return (CustomerBalanceDTO) sqlQuery.uniqueResult();
	}
	
	@Override
	public OrderDTO getOrderById(Long orderId) {

		StringBuilder builder = new StringBuilder("");

		builder.append(
				" SELECT o.order_id AS orderId, o.order_ref AS orderRef, DATE_FORMAT(o.order_date, '%d/%m/%Y') AS orderDate, ");
		builder.append(
				" o.total AS totalAmount, o.totalpaid AS totalPaid, o.discount AS discount, COALESCE(o.total, 0) - COALESCE(o.totalpaid, 0) AS remainingAmount,  ");
		builder.append(" c.username AS customerName, c.mobile AS customerPhone, ");
		builder.append(" o.order_status AS orderStatus, c.customer_id AS customerId ");

		builder.append(" FROM tbl_order o ");
		builder.append(" LEFT JOIN tbl_customer c ON c.customer_id = o.customer_id ");
		builder.append(" WHERE o.order_id = :orderId ");

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(builder.toString())
				.addScalar("orderId", StandardBasicTypes.LONG).addScalar("orderRef", StandardBasicTypes.STRING)
				.addScalar("orderDate", StandardBasicTypes.STRING).addScalar("customerId", StandardBasicTypes.LONG)
				.addScalar("totalPaid", StandardBasicTypes.BIG_DECIMAL)
				.addScalar("totalAmount", StandardBasicTypes.BIG_DECIMAL)
				.addScalar("discount", StandardBasicTypes.BIG_DECIMAL)
				.addScalar("remainingAmount", StandardBasicTypes.BIG_DECIMAL)
				.addScalar("customerId", StandardBasicTypes.LONG).addScalar("customerName", StandardBasicTypes.STRING)
				.addScalar("customerPhone", StandardBasicTypes.STRING)
				.addScalar("orderStatus", StandardBasicTypes.INTEGER);

		sqlQuery.setParameter("orderId", orderId);

		sqlQuery.setResultTransformer(Transformers.aliasToBean(OrderDTO.class));

		return (OrderDTO) sqlQuery.uniqueResult();
	}

	@Override
	public OrderDTO getOrderTotalAmountByCustomerId(Long customerId) {
		StringBuilder builder = new StringBuilder(
				" SELECT SUM(IFNULL(o.total, 0)) AS totalAmount, SUM(IFNULL(o.totalpaid, 0)) AS totalPaid ");
		builder.append(" FROM tbl_order o ");
		builder.append(" WHERE o.customer_id =:customerId ");
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(builder.toString())
				.addScalar("totalAmount", StandardBasicTypes.BIG_DECIMAL)
				.addScalar("totalPaid", StandardBasicTypes.BIG_DECIMAL);

		sqlQuery.setResultTransformer(Transformers.aliasToBean(OrderDTO.class));

		sqlQuery.setParameter("customerId", customerId);

		return (OrderDTO) sqlQuery.uniqueResult();
	}

	@Override
	public boolean isOrderExistByCustomerId(Long customerId) {
		StringBuilder builder = new StringBuilder(
				" SELECT COUNT(order_id) FROM tbl_order WHERE customer_id =:customerId ");
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(builder.toString());
		sqlQuery.setParameter("customerId", customerId);
		return ((Number) sqlQuery.uniqueResult()).longValue() > 0;
	}
	
	private String searchQueryForOrdersNoti(OrderSearchDTO searchDTO, boolean isCountOnly) {

		StringBuilder builder = new StringBuilder("");

		if (isCountOnly) {
			builder.append(" SELECT COUNT(DISTINCT o.order_id) ");
		} else {
			builder.append(
					" SELECT o.order_id AS orderId, o.order_ref AS orderRef, DATE_FORMAT(o.order_date, '%d/%m/%Y') AS orderDate, ");
			builder.append(
					" o.total AS totalAmount, o.totalpaid AS totalPaid, o.discount AS discount, COALESCE(o.total, 0) - COALESCE(o.totalpaid, 0) AS remainingAmount,  ");
			builder.append(" c.username AS customerName, c.mobile AS customerPhone, r.rank_name AS customerRank, ");
			builder.append(" o.order_status AS orderStatus, u.name AS submittedByName, c.customer_id AS customerId ");
		}

		builder.append(" FROM tbl_order o ");
		builder.append(" LEFT JOIN tbl_customer c ON c.customer_id = o.customer_id ");
		builder.append(" LEFT JOIN tbl_rank r ON r.rank_id = c.rank_id ");
		builder.append(" LEFT JOIN users u ON u.Id = o.user_id ");

		builder.append(" WHERE 1=1 AND o.order_date < DATE_SUB(CURDATE(), INTERVAL 1 WEEK) AND o.payment !=1 ");

		addSearchCreteria(builder, searchDTO);

		if (!isCountOnly) {

			builder.append(" GROUP BY o.order_id ");

			builder.append("ORDER BY o.order_id DESC ");

			appendPagination(builder, searchDTO);
		}

		return builder.toString();

	}

}
