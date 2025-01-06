package com.applewear.crm.dao.product;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.applewear.crm.dao.generic.GenericDaoImpl;
import com.applewear.crm.dto.product.ProductSearchDTO;
import com.applewear.crm.entity.product.Product;
import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;

@Repository
@Transactional
public class ProductDaoImpl extends GenericDaoImpl<Product, Long> implements ProductDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> searchProduct(ProductSearchDTO searchDTO) {

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(prepareSearchQuery(searchDTO, false));

		sqlQuery.addEntity(Product.class);

		prepareParameterForSearchQuery(sqlQuery, searchDTO);

		return sqlQuery.list();
	}

	@Override
	public Long searchProductCount(ProductSearchDTO searchDTO) {

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(prepareSearchQuery(searchDTO, true));

		prepareParameterForSearchQuery(sqlQuery, searchDTO);

		return ((Number) sqlQuery.uniqueResult()).longValue();
	}

	private String prepareSearchQuery(ProductSearchDTO searchDTO, boolean isCount) {

		StringBuilder query = new StringBuilder("");

		if (isCount) {
			query.append("SELECT COUNT(DISTINCT vl.product_id) ");
		} else {
			query.append("SELECT vl.* ");
		}

		query.append(" FROM tbl_product vl ");

		query.append(" wHERE 1=1 ");

		if (CommonUtil.validString(searchDTO.getProductCode())) {
			query.append(" AND vl.product_code LIKE :productCode ");
		}

		if (CommonUtil.validLong(searchDTO.getBrandId())) {
			query.append(" AND vl.brand_id =:brandId ");
		}

		if (CommonUtil.validLong(searchDTO.getCategoryId())) {
			query.append(" AND vl.category_id  =:categoryId ");
		}

		if (CommonUtil.validLong(searchDTO.getSizeId())) {
			query.append(" AND vl.size_id =:sizeId ");
		}

		if (CommonUtil.validBigDecimal(searchDTO.getSellingPrice())) {
			query.append(" AND vl.selling_price LIKE :sellingPrice ");
		}

		if (!isCount) {

			query.append(" GROUP BY vl.product_id ");
			query.append("ORDER BY vl.product_id DESC ");

			// if (!searchDTO.isExport()) {

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

			// }
		}

		return query.toString();

	}

	private void prepareParameterForSearchQuery(SQLQuery sqlQuery, ProductSearchDTO searchDTO) {

		if (CommonUtil.validString(searchDTO.getProductCode())) {
			sqlQuery.setParameter("productCode", "%" + searchDTO.getProductCode() + "%");
		}

		if (CommonUtil.validLong(searchDTO.getBrandId())) {
			sqlQuery.setParameter("brandId", searchDTO.getBrandId());
		}

		if (CommonUtil.validLong(searchDTO.getCategoryId())) {
			sqlQuery.setParameter("categoryId", searchDTO.getCategoryId());
		}

		if (CommonUtil.validLong(searchDTO.getSizeId())) {
			sqlQuery.setParameter("sizeId", searchDTO.getSizeId());
		}

		if (CommonUtil.validBigDecimal(searchDTO.getSellingPrice())) {
			sqlQuery.setParameter("sellingPrice", "%" + searchDTO.getSellingPrice() + "%");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> searchProductAutoComplete(String searchKey) {

		StringBuilder builder = new StringBuilder(" SELECT * FROM tbl_product ");

		builder.append(" WHERE 1=1 ");

		if (CommonUtil.validString(searchKey)) {
			builder.append(" AND product_name LIKE :searchKey ");
		}

		builder.append(" ORDER BY product_name LIMIT 20 ");

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(builder.toString());

		sqlQuery.addEntity(Product.class);

		if (CommonUtil.validString(searchKey)) {
			sqlQuery.setParameter("searchKey", "%" + searchKey + "%");
		}

		return sqlQuery.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProducts() {
		StringBuilder builder = new StringBuilder(" SELECT * FROM tbl_product ");
		builder.append(" ORDER BY product_name ");
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(builder.toString());
		sqlQuery.addEntity(Product.class);
		return sqlQuery.list();
	}

	
}
