package com.applewear.crm.dao.stock_transaction;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.applewear.crm.dao.generic.GenericDaoImpl;
import com.applewear.crm.dto.stock_transaction.StockTransactionSearchDTO;
import com.applewear.crm.entity.stock_transaction.StockTransaction;
import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;

@Repository
@Transactional
public class StockTransactionDaoImpl extends GenericDaoImpl<StockTransaction, Long> implements StockTransactionDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<StockTransaction> searchStockTransactions(StockTransactionSearchDTO searchDTO) {

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(prepareSearchQuery(searchDTO, false));

		addParameterForSearchQuery(sqlQuery, searchDTO);

		sqlQuery.addEntity(StockTransaction.class);

		return sqlQuery.list();
	}

	@Override
	public Long searchStockTransactionCount(StockTransactionSearchDTO searchDTO) {

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(prepareSearchQuery(searchDTO, true));

		addParameterForSearchQuery(sqlQuery, searchDTO);

		return ((Number) sqlQuery.uniqueResult()).longValue();
	}

	private String prepareSearchQuery(StockTransactionSearchDTO searchDTO, boolean countOnly) {

		StringBuilder builder = new StringBuilder();

		if (countOnly) {
			builder.append(" SELECT COUNT(DISTINCT st.stock_txn_id) ");
		} else {
			builder.append(" SELECT * ");
		}

		builder.append(" FROM tbl_stock_transaction st ");

		builder.append(" LEFT JOIN tbl_product p ON p.product_id = st.product_id ");

		builder.append(" WHERE 1=1 ");

		if (CommonUtil.validString(searchDTO.getProductName())) {
			builder.append(" AND p.product_name LIKE :productName ");
		}

		if (CommonUtil.validLong(searchDTO.getTransactionTypeId())) {
			builder.append(" AND st.txn_type_id =:txnTypeId ");
		}

		if (CommonUtil.validString(searchDTO.getRefNo())) {
			builder.append(" AND st.ref_no LIKE :refNo ");
		}

		if (!countOnly) {

			builder.append("ORDER BY st.created_at DESC ");

			appendPagination(builder, searchDTO);
		}

		return builder.toString();

	}

	private void addParameterForSearchQuery(SQLQuery sqlQuery, StockTransactionSearchDTO searchDTO) {

		if (CommonUtil.validString(searchDTO.getProductName())) {
			sqlQuery.setParameter("productName", "%" + searchDTO.getProductName() + "%");
		}

		if (CommonUtil.validLong(searchDTO.getTransactionTypeId())) {
			sqlQuery.setParameter("txnTypeId", searchDTO.getTransactionTypeId());
		}

		if (CommonUtil.validString(searchDTO.getRefNo())) {
			sqlQuery.setParameter("refNo", searchDTO.getRefNo());
		}

	}

	private void appendPagination(StringBuilder query, StockTransactionSearchDTO searchDTO) {
		Integer start = CommonUtil.validNumber(searchDTO.getStart()) ? searchDTO.getStart() : 0;
		Integer length = CommonUtil.validNumber(searchDTO.getLength()) ? searchDTO.getLength()
				: CommonConstants.ADMIN_RECORD_PER_PAGE;
		query.append("LIMIT ").append(start).append(", ").append(length);
	}

}
