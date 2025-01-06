package com.applewear.crm.dao.admin;

import java.util.List;

import com.applewear.crm.dao.generic.GenericDao;
import com.applewear.crm.dto.admin.AdminSearchDTO;
import com.applewear.crm.entity.admin.Admin;

public interface AdminDao extends GenericDao<Admin, Long> {

	Admin getUserByUserId(String adminName);

	List<Admin> searchAdmin(AdminSearchDTO searchDTO);

	Long searchAdminCount(AdminSearchDTO searchDTO);

	boolean isLoginNameAlreadyExist(String adminName, Long adminId);

	boolean isAdminRole(Long id);
	
	void updateDeleteFlag(Admin entity);

}
