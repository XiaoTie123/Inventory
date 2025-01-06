package com.applewear.crm.dao.stock;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.applewear.crm.dao.generic.GenericDaoImpl;
import com.applewear.crm.dto.inventory.InventoryDTO;
import com.applewear.crm.dto.inventory.InventorySearchDTO;
import com.applewear.crm.entity.stock.Stock;
import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;

@Repository
@Transactional
public class StockDaoImpl extends GenericDaoImpl<Stock, Long> implements StockDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<InventoryDTO> searchInventorys(InventorySearchDTO searchDTO) {

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(prepareSearchQuery(searchDTO, false))
				.addScalar("productId", StandardBasicTypes.LONG).addScalar("productCode", StandardBasicTypes.STRING)
				.addScalar("productName", StandardBasicTypes.STRING).addScalar("brandName", StandardBasicTypes.STRING)
				.addScalar("quantity", StandardBasicTypes.INTEGER);

		sqlQuery.setResultTransformer(Transformers.aliasToBean(InventoryDTO.class));

		addParameterForSearchQuery(sqlQuery, searchDTO);

		return sqlQuery.list();
	}

	@Override
	public Long searchInventoryCount(InventorySearchDTO searchDTO) {

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(prepareSearchQuery(searchDTO, true));

		addParameterForSearchQuery(sqlQuery, searchDTO);

		return ((Number) sqlQuery.uniqueResult()).longValue();
	}

	private String prepareSearchQuery(InventorySearchDTO searchDTO, boolean countOnly) {

		StringBuilder query = new StringBuilder();

		if (countOnly) {

			query.append(" SELECT COUNT(DISTINCT s.stock_id) ");

		} else {
			query.append(
					" SELECT p.product_id AS productId, p.product_code AS productCode, p.product_name AS productName, ");
			query.append(" b.brand_name AS brandName, ROUND((s.quantity / p.quantity_per_pack), 0) AS quantity ");
		}

		query.append(" FROM tbl_stock s ");
		query.append(" INNER JOIN tbl_product p ON s.product_id = p.product_id ");
		query.append(" LEFT JOIN tbl_brand b ON p.brand_id = b.brand_id ");

		query.append(" WHERE 1=1 ");

		if (CommonUtil.validString(searchDTO.getProductCode())) {
			query.append(" AND p.product_code LIKE :productCode ");
		}

		if (CommonUtil.validString(searchDTO.getProductName())) {
			query.append(" AND p.product_name LIKE :productName ");
		}

		if (!countOnly) {

			query.append("ORDER BY quantity DESC ");

			appendPagination(query, searchDTO);
		}

		return query.toString();

	}

	private void appendPagination(StringBuilder query, InventorySearchDTO searchDTO) {
		Integer start = CommonUtil.validNumber(searchDTO.getStart()) ? searchDTO.getStart() : 0;
		Integer length = CommonUtil.validNumber(searchDTO.getLength()) ? searchDTO.getLength()
				: CommonConstants.ADMIN_RECORD_PER_PAGE;
		query.append("LIMIT ").append(start).append(", ").append(length);
	}

	private void addParameterForSearchQuery(SQLQuery sqlQuery, InventorySearchDTO searchDTO) {

		if (CommonUtil.validString(searchDTO.getProductCode())) {
			sqlQuery.setParameter("productCode", "%" + searchDTO.getProductCode() + "%");
		}

		if (CommonUtil.validString(searchDTO.getProductName())) {
			sqlQuery.setParameter("productName", "%" + searchDTO.getProductName() + "%");
		}

	}

}
