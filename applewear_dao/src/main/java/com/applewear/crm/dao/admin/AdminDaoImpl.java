package com.applewear.crm.dao.admin;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.applewear.crm.dao.generic.GenericDaoImpl;
import com.applewear.crm.dto.admin.AdminSearchDTO;
import com.applewear.crm.entity.admin.Admin;
import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;
import com.applewear.crm.util.enums.RoleType;

@Repository
@Transactional
public class AdminDaoImpl extends GenericDaoImpl<Admin, Long> implements AdminDao {

//	@PersistenceContext
//    private EntityManager entityManager;
	
	@Override
	public Admin getUserByUserId(String adminName) {
		SQLQuery sqlQuery = getCurrentSession()
				.createSQLQuery("SELECT * FROM users WHERE del_flag = 0 AND TRIM(loginId) = :adminName LIMIT 1");
		sqlQuery.setParameter("adminName", adminName.trim());
		sqlQuery.addEntity(Admin.class);
		return (Admin) sqlQuery.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Admin> searchAdmin(AdminSearchDTO searchDTO) {

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(prepareSearchQuery(searchDTO, false));

		sqlQuery.addEntity(Admin.class);

		prepareParameterForSearchQuery(sqlQuery, searchDTO);

		return sqlQuery.list();
	}

	@Override
	public Long searchAdminCount(AdminSearchDTO searchDTO) {

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(prepareSearchQuery(searchDTO, true));

		prepareParameterForSearchQuery(sqlQuery, searchDTO);

		return ((Number) sqlQuery.uniqueResult()).longValue();
	}

	private String prepareSearchQuery(AdminSearchDTO searchDTO, boolean isCount) {

		StringBuilder query = new StringBuilder("");

		if (isCount) {
			query.append("SELECT COUNT(DISTINCT vl.id) ");
		} else {
			query.append("SELECT vl.* ");
		}

		query.append(" FROM users vl ");

		query.append(" wHERE 1=1 AND vl.del_flag = 0");

		if (CommonUtil.validString(searchDTO.getAdminName())) {
			query.append(" AND vl.name LIKE :adminName ");
		}

		if (CommonUtil.validString(searchDTO.getEmail())) {
			query.append(" AND vl.email =:email ");
		}

		if (CommonUtil.validLong(searchDTO.getRoleId())) {
			query.append(" AND vl.role  =:roleId ");
		}

		if (!isCount) {

			query.append(" GROUP BY vl.id ");
			query.append("ORDER BY vl.id DESC ");

			// if (!searchDTO.isExport()) {

			Integer start = searchDTO.getStart();
			Integer length = searchDTO.getLength();
			query.append("LIMIT ");
			if (!CommonUtil.validNumber(searchDTO.getStart())) {
				start = 0;
			}
			if (!CommonUtil.validNumber(searchDTO.getLength())) {
				length = CommonConstants.ADMIN_RECORD_PER_PAGE;
			}
			query.append(start);
			query.append(", ");
			query.append(length);

			// }
		}

		return query.toString();

	}

	private void prepareParameterForSearchQuery(SQLQuery sqlQuery, AdminSearchDTO searchDTO) {

		if (CommonUtil.validString(searchDTO.getAdminName())) {
			sqlQuery.setParameter("adminName", "%" + searchDTO.getAdminName() + "%");
		}

		if (CommonUtil.validString(searchDTO.getEmail())) {
			sqlQuery.setParameter("email", "%" + searchDTO.getEmail() + "%");
		}

		if (CommonUtil.validLong(searchDTO.getRoleId())) {
			sqlQuery.setParameter("roleId", searchDTO.getRoleId());
		}
	}

	@Override
	public boolean isLoginNameAlreadyExist(String adminName, Long adminId) {

		StringBuilder builder = new StringBuilder(" SELECT COUNT(Id) FROM users ");

		builder.append(" WHERE LOWER(loginId) = LOWER(:name) ");

		if (CommonUtil.validLong(adminId)) {
			builder.append(" AND id <>:id ");
		}

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(builder.toString());

		sqlQuery.setParameter("name", adminName);

		if (CommonUtil.validLong(adminId)) {
			sqlQuery.setParameter("id", adminId);
		}

		return ((Number) sqlQuery.uniqueResult()).longValue() > 0;
	}

	@Override
	public boolean isAdminRole(Long id) {
		StringBuilder builder = new StringBuilder(" SELECT COUNT(id) FROM users ");
		builder.append(" WHERE role =:role AND id =:id ");
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(builder.toString());
		sqlQuery.setParameter("role", RoleType.ADMIN.getCode());
		sqlQuery.setParameter("id", id);
		return ((Number) sqlQuery.uniqueResult()).longValue() > 0;
	}
	
	@Override
	public void updateDeleteFlag(Admin entity) {
	    String sql = "UPDATE users SET del_flag = 1 WHERE id = :id";
	    SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql);
	    sqlQuery.setParameter("id", entity.getId());
	    sqlQuery.executeUpdate();
	}
}
