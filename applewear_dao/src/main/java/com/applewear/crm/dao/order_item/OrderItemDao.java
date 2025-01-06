package com.applewear.crm.dao.order_item;

import java.util.List;

import com.applewear.crm.dao.generic.GenericDao;
import com.applewear.crm.dto.product.TopTenProductDTO;
import com.applewear.crm.entity.order_item.OrderItem;

public interface OrderItemDao extends GenericDao<OrderItem, Long> {

	List<TopTenProductDTO> getTopTenProductList();

	List<OrderItem> getOrderItemByOrderId(Long orderId);

	boolean deleteOrderItemsByOrderId(Long orderId);
	
	boolean isOrderItemExistByProductId(Long productId);

}
