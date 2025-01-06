package com.applewear.crm.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.applewear.crm.dto.admin.AdminDTO;
import com.applewear.crm.dto.admin.AdminSearchDTO;
import com.applewear.crm.service.exception.LoginNameAlreadyExistException;
import com.applewear.crm.util.common.Result;

public interface AdminService {

	Result<AdminDTO> login(AdminDTO adminDto);

	List<AdminDTO> searchAdminList(AdminSearchDTO searchDTO, HttpServletRequest httpRequest);

	Long searchAdminCount(AdminSearchDTO searchDTO);

	AdminDTO getAdminById(Long adminId);

	Long manageAdmin(AdminDTO adminDTO) throws LoginNameAlreadyExistException;

	boolean deleteAdminById(Long id);

	boolean isAdminRole(Long id);

}
