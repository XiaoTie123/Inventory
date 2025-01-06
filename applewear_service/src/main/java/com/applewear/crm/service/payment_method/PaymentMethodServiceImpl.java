package com.applewear.crm.service.payment_method;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.applewear.crm.dao.payment_method.PaymentMethodDao;
import com.applewear.crm.dto.payment_method.PaymentMethodDTO;
import com.applewear.crm.entity.payment_method.PaymentMethod;
import com.applewear.crm.util.common.CommonUtil;

@Service
@Transactional
public class PaymentMethodServiceImpl implements PaymentMethodService {

	@Autowired
	private PaymentMethodDao paymentMethodDao;

	@Override
	public List<PaymentMethodDTO> getAllPaymentMethods() {

		List<PaymentMethod> entityList = paymentMethodDao.getAllPaymentMethods();

		List<PaymentMethodDTO> paymentMethodDTOList = new ArrayList<PaymentMethodDTO>();

		if (CommonUtil.validList(entityList)) {

			for (PaymentMethod entity : entityList) {

				PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO(entity);

				paymentMethodDTOList.add(paymentMethodDTO);

			}

		}

		return paymentMethodDTOList;
	}

	@Override
	public Long managePaymentMethod(PaymentMethodDTO paymentMethodDTO) {

		PaymentMethod entity = new PaymentMethod();

		if (CommonUtil.validLong(paymentMethodDTO.getPaymentMethodId())) {

			entity = paymentMethodDao.get(paymentMethodDTO.getPaymentMethodId());

			entity.setUpdatedTime(new Date());

		} else {

			entity.setCreatedTime(new Date());

			entity.setUpdatedTime(new Date());

		}

		entity.setName(paymentMethodDTO.getName());

		paymentMethodDao.save(entity);

		return entity != null && CommonUtil.validLong(entity.getId()) ? entity.getId() : null;
	}

	@Override
	public PaymentMethodDTO getPaymentMethodById(Long paymentMethodId) {

		if (!CommonUtil.validLong(paymentMethodId)) {
			return null;
		}

		PaymentMethod entity = paymentMethodDao.get(paymentMethodId);

		if (entity != null) {

			return new PaymentMethodDTO(entity);

		}

		return null;
	}

	@Override
	public boolean deletePaymentById(Long paymentMethodId) {

		if (!CommonUtil.validLong(paymentMethodId)) {
			return false;
		}

		PaymentMethod entity = paymentMethodDao.get(paymentMethodId);

		if (entity == null) {
			return false;
		}

		try {
			paymentMethodDao.delete(entity);
			return true;

		} catch (Exception e) {
			return false;
		}

	}

}
