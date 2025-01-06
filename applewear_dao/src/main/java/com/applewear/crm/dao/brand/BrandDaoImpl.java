package com.applewear.crm.dao.brand;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.applewear.crm.dao.generic.GenericDaoImpl;
import com.applewear.crm.entity.brand.Brand;

@Repository
@Transactional
public class BrandDaoImpl extends GenericDaoImpl<Brand, Long> implements BrandDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Brand> getAllBrand() {

		StringBuilder builder = new StringBuilder(" SELECT * FROM tbl_brand ");

		builder.append(" ORDER BY brand_name ");

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(builder.toString());

		sqlQuery.addEntity(Brand.class);

		return sqlQuery.list();
	}
}
