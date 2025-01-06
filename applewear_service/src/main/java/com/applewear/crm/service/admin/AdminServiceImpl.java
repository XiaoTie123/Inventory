package com.applewear.crm.service.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.applewear.crm.dao.admin.AdminDao;
import com.applewear.crm.dto.admin.AdminDTO;
import com.applewear.crm.dto.admin.AdminSearchDTO;
import com.applewear.crm.entity.admin.Admin;
import com.applewear.crm.service.exception.LoginNameAlreadyExistException;
import com.applewear.crm.util.common.CommonUtil;
import com.applewear.crm.util.common.Result;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	private final Logger LOG = LogManager.getLogger();

	@Autowired
	private AdminDao adminDao;

	@Override
	public List<AdminDTO> searchAdminList(AdminSearchDTO searchDTO, HttpServletRequest httpRequest) {

		List<AdminDTO> adminList = new ArrayList<AdminDTO>();

		List<Admin> admin = adminDao.searchAdmin(searchDTO);

		if (CommonUtil.validList(admin)) {

			Integer num = searchDTO.getStart() != null ? searchDTO.getStart() + 1 : 1;

			for (Admin entity : admin) {
				AdminDTO adminDTO = new AdminDTO(entity);
				adminDTO.setNum(num);
				num++;
				adminList.add(adminDTO);
			}
		}
		return adminList;
	}

	@Override
	public Long searchAdminCount(AdminSearchDTO searchDTO) {

		return adminDao.searchAdminCount(searchDTO);
	}

	@Override
	public AdminDTO getAdminById(Long adminId) {

		if (!CommonUtil.validLong(adminId)) {
			return null;
		}

		Admin entity = adminDao.get(adminId);

		if (entity != null && CommonUtil.validLong(entity.getId())) {
			return new AdminDTO(entity);
		}

		return null;
	}

	@Override
	public Long manageAdmin(AdminDTO adminDTO) throws LoginNameAlreadyExistException {

		Admin entity = new Admin();

		if (CommonUtil.validLong(adminDTO.getAdminId())) {
			entity = adminDao.get(adminDTO.getAdminId());
			entity.setUpdatedTime(new Date());
		} else {
			entity.setCreatedTime(new Date());
			entity.setUpdatedTime(new Date());
		}

		if (adminDao.isLoginNameAlreadyExist(adminDTO.getLoginName(), adminDTO.getAdminId())) {
			throw new LoginNameAlreadyExistException("Admin Name is already existed.");
		}

		entity.setLoginId(adminDTO.getLoginName());
		entity.setName(adminDTO.getAdminName());
		entity.setEmail(adminDTO.getEmail());
		entity.setApiKey("Testing");

		if (!CommonUtil.validLong(adminDTO.getAdminId())) {
			entity.setPassword(CommonUtil.hashString(adminDTO.getPassword()));
		}
		entity.setDelflag(0);;
		entity.setRole(adminDTO.getRole());
		adminDao.saveOrUpdate(entity);
		return entity != null && CommonUtil.validLong(entity.getId()) ? entity.getId() : null;
	}

	@Override
	public Result<AdminDTO> login(AdminDTO adminDto) {

		Result<AdminDTO> result = new Result<>();

		if (!CommonUtil.validString(adminDto.getLoginName())) {
			LOG.debug("Couldn't login admin user >>> Invalid userId. >>> userId = " + adminDto.getAdminId());
			result.setMessage("Login id is required.");
			return result;
		}

		if (!CommonUtil.validString(adminDto.getPassword())) {
			LOG.debug("Couldn't login admin user >>> Invalid password. >>> password = " + adminDto.getPassword());
			result.setMessage("Password is required.");
			return result;
		}

		Admin entity = adminDao.getUserByUserId(adminDto.getLoginName());
		if (entity == null) {
			LOG.debug("Couldn't login admin user >>> No record was found. >>> userId = " + adminDto.getAdminId());
			result.setMessage("Invalid login name.");
			return result;
		}

		String hashPwd = CommonUtil.hashString(adminDto.getPassword());

		if (!CommonUtil.validString(entity.getPassword()) || !hashPwd.equalsIgnoreCase(entity.getPassword())) {
			LOG.debug("Couldn't login admin user >>> Password doesn't match >>> userId = " + adminDto.getAdminName());
			result.setMessage("Invalid password");
			return result;
		}

		result.setSuccess(true);
		result.setMessage("Logged in successfully.");
		result.setData(new AdminDTO(entity));

		return result;
	}

	@Override
	public boolean deleteAdminById(Long id) {
		if (!CommonUtil.validLong(id)) {
			return false;
		}

		Admin entity = adminDao.get(id);

		if (entity == null) {
			return false;
		}

		try {
			adminDao.updateDeleteFlag(entity);
			return true;
		} catch (Exception e) {
			LOG.error("Exception occurred while deleting admin by id: " + id, e);
			return false;
		}
	}

	@Override
	public boolean isAdminRole(Long id) {

		if (!CommonUtil.validLong(id)) {
			return false;
		}

		return adminDao.isAdminRole(id);
	}

}
