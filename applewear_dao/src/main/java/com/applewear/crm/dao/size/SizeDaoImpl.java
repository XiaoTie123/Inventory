package com.applewear.crm.dao.size;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.applewear.crm.dao.generic.GenericDaoImpl;
import com.applewear.crm.entity.size.Size;

@Repository
@Transactional
public class SizeDaoImpl extends GenericDaoImpl<Size, Long> implements SizeDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Size> getAllSize() {

		StringBuilder builder = new StringBuilder(" SELECT * FROM tbl_size ");

		builder.append(" ORDER BY size_id ");

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(builder.toString());

		sqlQuery.addEntity(Size.class);

		return sqlQuery.list();
	}
}
