package com.applewear.crm.service.order_item;

import java.util.List;

import com.applewear.crm.dto.order_item.OrderItemDTO;
import com.applewear.crm.dto.product.TopTenProductDTO;

public interface OrderItemService {

	List<TopTenProductDTO> getTopTenProducts();

	List<OrderItemDTO> getOrderItemListByOrderId(Long orderId);

}
