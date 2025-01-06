package com.applewear.crm.dao.transaction_type;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.applewear.crm.dao.generic.GenericDaoImpl;
import com.applewear.crm.entity.transaction_type.StockTransactionType;

@Repository
@Transactional
public class TransactionTypeDaoImpl extends GenericDaoImpl<StockTransactionType, Long> implements TransactionTypeDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<StockTransactionType> getAllTransactionType() {
		StringBuilder builder = new StringBuilder(" SELECT * FROM tbl_stock_txntype ");
		builder.append(" ORDER BY txn_type_name ");
		SQLQuery sqlQuery =getCurrentSession().createSQLQuery(builder.toString());
		sqlQuery.addEntity(StockTransactionType.class);
		return sqlQuery.list();
	}

}
