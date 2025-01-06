package com.applewear.crm.dto.customer;

import java.util.List;

import com.applewear.crm.dto.search.SearchDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomerSearchDTO extends SearchDTO{

	private String name;

	private String loginId;

	private String email;

	private String phoneNo;

	private List<CustomerDTO> data;

}
