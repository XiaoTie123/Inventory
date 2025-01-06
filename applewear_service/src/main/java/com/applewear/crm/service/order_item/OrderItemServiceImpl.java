package com.applewear.crm.service.order_item;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.applewear.crm.dao.order_item.OrderItemDao;
import com.applewear.crm.dto.order_item.OrderItemDTO;
import com.applewear.crm.dto.product.TopTenProductDTO;
import com.applewear.crm.entity.order_item.OrderItem;
import com.applewear.crm.util.common.CommonUtil;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private OrderItemDao orderItemDao;

	@Override
	public List<TopTenProductDTO> getTopTenProducts() {

		List<TopTenProductDTO> topTenProductList = orderItemDao.getTopTenProductList();

		return topTenProductList;
	}

	@Override
	public List<OrderItemDTO> getOrderItemListByOrderId(Long orderId) {

		List<OrderItem> orderItemList = orderItemDao.getOrderItemByOrderId(orderId);

		List<OrderItemDTO> orderItemDTOList = new ArrayList<OrderItemDTO>();

		if (CommonUtil.validList(orderItemList)) {
			for (OrderItem entity : orderItemList) {
				OrderItemDTO orderItemDTO = new OrderItemDTO(entity);
				orderItemDTOList.add(orderItemDTO);
			}
		}

		return orderItemDTOList;
	}

}
