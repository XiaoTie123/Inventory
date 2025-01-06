package com.applewear.crm.service.stock_transaction;

import java.util.List;

import com.applewear.crm.dto.stock_transaction.StockTransactionDTO;
import com.applewear.crm.dto.stock_transaction.StockTransactionSearchDTO;

public interface StockTransactionService {

	Long manageStockTransaction(StockTransactionDTO stockTransactionDTO);

	List<StockTransactionDTO> searchStockTransactions(StockTransactionSearchDTO searchDTO);

	Long searchStockTransactionCount(StockTransactionSearchDTO searchDTO);

	StockTransactionDTO getTransactionById(Long id);

}
