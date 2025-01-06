package com.applewear.crm.service.customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.applewear.crm.dao.customer.CustomerDao;
import com.applewear.crm.dao.order.OrderDao;
import com.applewear.crm.dao.payment.PaymentDao;
import com.applewear.crm.dto.customer.CustomerBalanceDTO;
import com.applewear.crm.dto.customer.CustomerDTO;
import com.applewear.crm.dto.customer.CustomerSearchDTO;
import com.applewear.crm.dto.customer.TopCreditCustomerDTO;
import com.applewear.crm.dto.customer.TopTenCustomerDTO;
import com.applewear.crm.entity.customer.Customer;
import com.applewear.crm.entity.rank.Rank;
import com.applewear.crm.service.exception.OrderAlreadyExistException;
import com.applewear.crm.service.exception.PaymentAlreadyExistException;
import com.applewear.crm.util.common.CommonUtil;
import com.applewear.crm.util.enums.RecordStatus;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	private final Logger LOG = LogManager.getLogger();

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private PaymentDao paymentDao;

	@Override
	public List<CustomerDTO> searchCustomers(CustomerSearchDTO searchDTO) {

		List<Customer> customerList = customerDao.searchCustomers(searchDTO);

		List<CustomerDTO> customerDTOList = new ArrayList<CustomerDTO>();

		if (CommonUtil.validList(customerList)) {

			Integer num = searchDTO.getStart() != null ? searchDTO.getStart() + 1 : 1;

			for (Customer entity : customerList) {
				CustomerDTO customerDTO = new CustomerDTO(entity);
				customerDTO.setNum(num);
				customerDTOList.add(customerDTO);
				num++;
			}
		}

		return customerDTOList;
	}

	@Override
	public Long searchCustomerCount(CustomerSearchDTO searchDTO) {

		return customerDao.searchCustomerCount(searchDTO);
	}

	@Override
	public Long manageCustomer(CustomerDTO customerDTO) {

		Customer entity = new Customer();

		if (CommonUtil.validLong(customerDTO.getCustomerId())) {
			entity = customerDao.get(customerDTO.getCustomerId());
			entity.setUpdatedTime(new Date());
		} else {
			entity.setCreatedTime(new Date());
			entity.setUpdatedTime(new Date());
		}

		entity.setUserName(customerDTO.getName());
		entity.setMobile(customerDTO.getMobile());
		entity.setLoginId(customerDTO.getLoginId());
		entity.setAddress(customerDTO.getAddress());
		entity.setEmail(customerDTO.getEmail());
		entity.setPassword("");
		entity.setVisible(RecordStatus.ACTIVE.getCode());
		entity.setLoginId(CommonUtil.generateCustomerLoginId());
		entity.setApiKey(CommonUtil.generateRandomString(40));

		if (CommonUtil.validLong(customerDTO.getRankId())) {
			Rank rank = new Rank();
			rank.setId(customerDTO.getRankId());
			entity.setRank(rank);
		}

		customerDao.saveOrUpdate(entity);

		return entity != null && CommonUtil.validLong(entity.getId()) ? entity.getId() : null;
	}

	@Override
	public CustomerDTO getCustomerById(Long customerId) {

		if (!CommonUtil.validLong(customerId)) {
			return null;
		}

		Customer entity = customerDao.get(customerId);

		if (entity != null && CommonUtil.validLong(entity.getId())) {
			return new CustomerDTO(entity);
		}

		return null;
	}

	@Override
	public List<TopCreditCustomerDTO> getTopCreditCustomers() {

		List<TopCreditCustomerDTO> topCreditCustomers = customerDao.getTopCreditCustomers();

		if (CommonUtil.validList(topCreditCustomers)) {
			topCreditCustomers.stream().forEach(topCreditCustomerDTO -> topCreditCustomerDTO
					.setTotalCreditText(CommonUtil.formatNumber(topCreditCustomerDTO.getTotalCredit())));
		}

		return topCreditCustomers;
	}

	@Override
	public List<TopTenCustomerDTO> getTopTenCustomers() {

		List<TopTenCustomerDTO> topTenCustomers = customerDao.getTopTenCustomers();

		if (CommonUtil.validList(topTenCustomers)) {
			topTenCustomers.stream().forEach(topTenCustomerDTO -> topTenCustomerDTO
					.setTotalAmountDesc(CommonUtil.formatNumber(topTenCustomerDTO.getTotalAmount())));
		}

		return topTenCustomers;
	}

	@Override
	public List<CustomerDTO> searchCustomerAutoComplete(String searchKey) {

		List<Customer> entityList = customerDao.searchCustomerAutocomplte(searchKey);

		List<CustomerDTO> customerDTOList = new ArrayList<CustomerDTO>();

		if (CommonUtil.validList(entityList)) {
			for (Customer entity : entityList) {
				CustomerDTO customerDTO = new CustomerDTO(entity);
				customerDTOList.add(customerDTO);
			}
		}

		return customerDTOList;
	}

	@Override
	public boolean deleteCustomerById(Long customerId) throws PaymentAlreadyExistException, OrderAlreadyExistException {

		if (!CommonUtil.validLong(customerId)) {
			return false;
		}

		Customer entity = customerDao.get(customerId);

		if (entity != null) {

			if (paymentDao.isExistPaymentByCustomerId(customerId)) {
				throw new PaymentAlreadyExistException("Unable to delete because of existing payments.");
			}

			if (orderDao.isOrderExistByCustomerId(customerId)) {
				throw new OrderAlreadyExistException("Unable to delete because of existing orders.");
			}

			customerDao.delete(entity);

		}

		return false;
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {

		List<CustomerDTO> customerDTOList = new ArrayList<CustomerDTO>();

		List<Customer> entityList = customerDao.getAllCustomers();

		if (CommonUtil.validList(entityList)) {
			for (Customer entity : entityList) {
				CustomerDTO customerDTO = new CustomerDTO(entity);
				customerDTOList.add(customerDTO);
			}
		}

		return customerDTOList;
	}

}
