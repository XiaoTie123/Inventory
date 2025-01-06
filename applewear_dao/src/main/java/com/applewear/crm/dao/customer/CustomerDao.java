package com.applewear.crm.dao.customer;

import java.util.List;

import com.applewear.crm.dao.generic.GenericDao;
import com.applewear.crm.dto.customer.CustomerSearchDTO;
import com.applewear.crm.dto.customer.TopCreditCustomerDTO;
import com.applewear.crm.dto.customer.TopTenCustomerDTO;
import com.applewear.crm.entity.customer.Customer;

public interface CustomerDao extends GenericDao<Customer, Long> {

	List<Customer> searchCustomers(CustomerSearchDTO searchDTO);

	Long searchCustomerCount(CustomerSearchDTO searchDTO);

	List<TopCreditCustomerDTO> getTopCreditCustomers();

	List<TopTenCustomerDTO> getTopTenCustomers();

	List<Customer> searchCustomerAutocomplte(String searchKey);

	List<Customer> getAllCustomers();

}
