package com.applewear.crm.dao.category;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.applewear.crm.dao.generic.GenericDaoImpl;
import com.applewear.crm.entity.category.Category;

@Repository
@Transactional
public class CategoryDaoImpl extends GenericDaoImpl<Category, Long> implements CategoryDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAllCategory() {

		StringBuilder builder = new StringBuilder(" SELECT * FROM tbl_category ");

		builder.append(" ORDER BY category_name ");

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(builder.toString());

		sqlQuery.addEntity(Category.class);

		return sqlQuery.list();
	}
}
