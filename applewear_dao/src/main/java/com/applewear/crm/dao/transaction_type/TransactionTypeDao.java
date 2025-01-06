package com.applewear.crm.dao.transaction_type;

import java.util.List;

import com.applewear.crm.dao.generic.GenericDao;
import com.applewear.crm.entity.transaction_type.StockTransactionType;

public interface TransactionTypeDao extends GenericDao<StockTransactionType, Long> {

	List<StockTransactionType> getAllTransactionType();

}
