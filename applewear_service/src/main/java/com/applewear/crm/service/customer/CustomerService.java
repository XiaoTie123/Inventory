package com.applewear.crm.service.customer;

import java.util.List;

import com.applewear.crm.dto.customer.CustomerDTO;
import com.applewear.crm.dto.customer.CustomerSearchDTO;
import com.applewear.crm.dto.customer.TopCreditCustomerDTO;
import com.applewear.crm.dto.customer.TopTenCustomerDTO;
import com.applewear.crm.service.exception.OrderAlreadyExistException;
import com.applewear.crm.service.exception.PaymentAlreadyExistException;

public interface CustomerService {

	List<CustomerDTO> searchCustomers(CustomerSearchDTO searchDTO);

	Long searchCustomerCount(CustomerSearchDTO searchDTO);

	Long manageCustomer(CustomerDTO customerDTO);

	CustomerDTO getCustomerById(Long customerId);

	List<TopCreditCustomerDTO> getTopCreditCustomers();

	List<TopTenCustomerDTO> getTopTenCustomers();

	List<CustomerDTO> searchCustomerAutoComplete(String searchKey);

	boolean deleteCustomerById(Long customerId) throws PaymentAlreadyExistException, OrderAlreadyExistException;

	List<CustomerDTO> getAllCustomers();
}
