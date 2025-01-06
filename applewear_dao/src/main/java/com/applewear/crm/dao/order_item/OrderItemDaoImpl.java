package com.applewear.crm.dao.order_item;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.applewear.crm.dao.generic.GenericDaoImpl;
import com.applewear.crm.dto.product.TopTenProductDTO;
import com.applewear.crm.entity.order_item.OrderItem;

@Repository
@Transactional
public class OrderItemDaoImpl extends GenericDaoImpl<OrderItem, Long> implements OrderItemDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TopTenProductDTO> getTopTenProductList() {

		StringBuilder builder = new StringBuilder(
				" SELECT p.product_name AS productName, p.product_code AS productCode, ");
		builder.append(" COUNT(od.orderdetail_Id) AS totalSales ");
		builder.append(" FROM tbl_orderdetail od ");
		builder.append(" INNER JOIN tbl_product p ON od.product_id = p.product_id ");
		builder.append(" GROUP BY p.product_name, p.product_code ");
		builder.append(" ORDER BY totalSales DESC LIMIT 10 ");
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(builder.toString())
				.addScalar("productName", StandardBasicTypes.STRING).addScalar("productCode", StandardBasicTypes.STRING)
				.addScalar("totalSales", StandardBasicTypes.LONG);

		sqlQuery.setResultTransformer(Transformers.aliasToBean(TopTenProductDTO.class));

		return sqlQuery.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderItem> getOrderItemByOrderId(Long orderId) {
		StringBuilder builder = new StringBuilder(" SELECT * FROM tbl_orderdetail ");
		builder.append(" WHERE order_id =:orderId ");
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(builder.toString());
		sqlQuery.setParameter("orderId", orderId);
		sqlQuery.addEntity(OrderItem.class);
		return sqlQuery.list();
	}

	@Override
	public boolean deleteOrderItemsByOrderId(Long orderId) {
		StringBuilder builder = new StringBuilder(" DELETE FROM tbl_orderdetail WHERE order_id =:orderId ");
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(builder.toString());
		sqlQuery.setParameter("orderId", orderId);
		return sqlQuery.executeUpdate() > 0;
	}

	@Override
	public boolean isOrderItemExistByProductId(Long productId) {
		StringBuilder builder = new StringBuilder(" SELECT COUNT(Id) FROM tbl_orderdetail ");
		builder.append(" WHERE product_id =:productId ");
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(builder.toString());
		sqlQuery.setParameter("productId", productId);
		return ((Number) sqlQuery.uniqueResult()).longValue() > 0;
	}

}
