package com.applewear.crm.dao.category;

import java.util.List;

import com.applewear.crm.dao.generic.GenericDao;
import com.applewear.crm.entity.category.Category;

public interface CategoryDao extends GenericDao<Category, Long> {

	List<Category> getAllCategory();

}
