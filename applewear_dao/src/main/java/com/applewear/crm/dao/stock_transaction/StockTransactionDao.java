package com.applewear.crm.dao.stock_transaction;

import java.util.List;

import com.applewear.crm.dao.generic.GenericDao;
import com.applewear.crm.dto.stock_transaction.StockTransactionSearchDTO;
import com.applewear.crm.entity.stock_transaction.StockTransaction;

public interface StockTransactionDao extends GenericDao<StockTransaction, Long> {

	List<StockTransaction> searchStockTransactions(StockTransactionSearchDTO searchDTO);

	Long searchStockTransactionCount(StockTransactionSearchDTO searchDTO);

}
