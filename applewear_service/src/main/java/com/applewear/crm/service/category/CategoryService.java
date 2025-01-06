package com.applewear.crm.service.category;

import java.util.List;
import com.applewear.crm.dto.category.CategoryDTO;

public interface CategoryService {
	Long manageCategory(CategoryDTO categoryDTO);

	List<CategoryDTO> getAllCategoryList();
	
	CategoryDTO getCategoryById(Long categoryId);
	
	boolean deleteCategoryById(Long id);

}
