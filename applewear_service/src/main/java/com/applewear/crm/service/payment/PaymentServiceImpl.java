package com.applewear.crm.service.payment;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.applewear.crm.dao.order.OrderDao;
import com.applewear.crm.dao.payment.PaymentDao;
import com.applewear.crm.dto.payment.PaymentDTO;
import com.applewear.crm.dto.payment.PaymentSearchDTO;
import com.applewear.crm.entity.customer.Customer;
import com.applewear.crm.entity.order.Order;
import com.applewear.crm.entity.payment.Payment;
import com.applewear.crm.entity.payment_method.PaymentMethod;
import com.applewear.crm.util.common.CommonUtil;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentDao paymentDao;

	@Autowired
	private OrderDao orderDao;

	@Override
	public List<PaymentDTO> searchPayments(PaymentSearchDTO searchDTO) {

		List<PaymentDTO> paymentDTOList = paymentDao.searchPayments(searchDTO);

		if (CommonUtil.validList(paymentDTOList)) {
			for (PaymentDTO paymentDTO : paymentDTOList) {
				paymentDTO.setTotalPaidAmountText(CommonUtil.formatNumber(paymentDTO.getTotalPaidAmount()));
			}
		}

		return paymentDTOList;
	}

	@Override
	public Long searchPaymentCount(PaymentSearchDTO searchDTO) {

		return paymentDao.searchPaymentCount(searchDTO);
	}

	@Override
	public Long managePayment(PaymentDTO paymentDTO) {

		Payment entity = new Payment();

		BigDecimal oldPaidAmount = BigDecimal.ZERO;
		BigDecimal paidAmount = CommonUtil.validBigDecimal(paymentDTO.getTotalPaidAmount())
				? paymentDTO.getTotalPaidAmount()
				: BigDecimal.ZERO;

		if (CommonUtil.validLong(paymentDTO.getPaymentId())) {

			entity = paymentDao.get(paymentDTO.getPaymentId());

			if (entity.getTotalPaid() != null) {
				oldPaidAmount = entity.getTotalPaid();
			}

			entity.setUpdatedTime(new Date());

		} else {
			entity.setCreatedTime(new Date());
			entity.setUpdatedTime(new Date());
		}

		entity.setOrderDate(new Date());

		if (CommonUtil.validLong(paymentDTO.getOrderDTO().getOrderId())) {
			
			Long orderId = paymentDTO.getOrderDTO().getOrderId();			
			Order order = orderDao.get(orderId);
			entity.setOrder(order);
			entity.setOrderRef(order.getOrder_ref());

			BigDecimal totalOrderPaidAmount = CommonUtil.validBigDecimal(order.getTotalPaid()) ? order.getTotalPaid()
					: BigDecimal.ZERO;

			totalOrderPaidAmount = totalOrderPaidAmount.subtract(oldPaidAmount).add(paidAmount);

			order.setTotalPaid(totalOrderPaidAmount);

			if (order.getTotal().compareTo(order.getTotalPaid()) == 0) {
				order.setPayment(1);
			}

			orderDao.saveOrUpdate(order);

		}

		if (paymentDTO.getCustomerDTO() != null && CommonUtil.validLong(paymentDTO.getCustomerDTO().getCustomerId())) {
			Customer customer = new Customer();
			customer.setId(paymentDTO.getCustomerDTO().getCustomerId());
			entity.setCustomer(customer);
		}

		if (CommonUtil.validLong(paymentDTO.getPaymentMethodId())) {
			PaymentMethod paymentMethod = new PaymentMethod();
			paymentMethod.setId(paymentDTO.getPaymentMethodId());
			entity.setPaymentMethod(paymentMethod);
		}

		entity.setTotalPaid(paidAmount);

		paymentDao.saveOrUpdate(entity);

		return entity != null && CommonUtil.validLong(entity.getId()) ? entity.getId() : null;
	}

	@Override
	public PaymentDTO getPaymentById(Long paymentId) {

		if (!CommonUtil.validLong(paymentId)) {
			return null;
		}

		Payment entity = paymentDao.get(paymentId);

		if (entity != null) {

			return new PaymentDTO(entity);

		}

		return null;
	}

	@Override
	public List<PaymentDTO> getPaymentByCustomerId(Long customerId) {

		List<PaymentDTO> paymentDTOList = paymentDao.getPaymentsByCustomerId(customerId);

		if (CommonUtil.validList(paymentDTOList)) {
			for (PaymentDTO paymentDTO : paymentDTOList) {
				paymentDTO.setTotalPaidAmountText(CommonUtil.formatNumber(paymentDTO.getTotalPaidAmount()));
			}
		}

		return paymentDTOList;
	}

	@Override
	public String getTotalPaidByCustomerId(Long customerId) {

		BigDecimal totalPaid = paymentDao.getTotalPaidByCustomerId(customerId);

		return CommonUtil.formatNumber(totalPaid);
	}

}
