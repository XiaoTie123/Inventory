package com.applewear.crm.service.category;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.applewear.crm.dao.category.CategoryDao;
import com.applewear.crm.dto.category.CategoryDTO;
import com.applewear.crm.entity.category.Category;
import com.applewear.crm.util.common.CommonUtil;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<CategoryDTO> getAllCategoryList() {

		List<CategoryDTO> categoryDTOList = new ArrayList<CategoryDTO>();

		List<Category> entityList = categoryDao.getAllCategory();

		if (CommonUtil.validList(entityList)) {

			for (Category entity : entityList) {

				CategoryDTO categoryDTO = new CategoryDTO(entity);
				categoryDTOList.add(categoryDTO);
			}
		}

		return categoryDTOList;
	}

	@Override
	public CategoryDTO getCategoryById(Long categoryId) {

		if (!CommonUtil.validLong(categoryId)) {
			return null;
		}

		Category entity = categoryDao.get(categoryId);

		if (entity != null) {
			return new CategoryDTO(entity);
		}

		return null;
	}

	@Override
	public Long manageCategory(CategoryDTO categoryDTO) {

		Category category = new Category();

		if (CommonUtil.validLong(categoryDTO.getCategoryId())) {
			category = categoryDao.get(categoryDTO.getCategoryId());
			category.setUpdatedTime(new Date());
		} else {
			category.setCreatedTime(new Date());
			category.setUpdatedTime(new Date());
		}
		category.setCategoryName(categoryDTO.getCategoryName());
		categoryDao.saveOrUpdate(category);
		return category != null && CommonUtil.validLong(category.getId()) ? category.getId() : null;
	}

	@Override
	public boolean deleteCategoryById(Long id) {

		if (!CommonUtil.validLong(id)) {
			return false;
		}

		Category entity = categoryDao.get(id);

		if (entity == null) {
			return false;
		}

		try {

			categoryDao.delete(entity);

			return true;

		} catch (Exception e) {
			return false;
		}

	}
}
