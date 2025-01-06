package com.applewear.crm.service.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.applewear.crm.dto.brand.BrandDTO;
import com.applewear.crm.dto.category.CategoryDTO;
import com.applewear.crm.dto.product.ProductDTO;
import com.applewear.crm.dto.product.ProductSearchDTO;
import com.applewear.crm.dto.size.SizeDTO;

public interface ProductService {

	List<BrandDTO> getAllBrandList();

	List<CategoryDTO> getAllCategoryList();

	List<SizeDTO> getAllSizeList();

	List<ProductDTO> searchProductList(ProductSearchDTO searchDTO, HttpServletRequest httpRequest);

	Long searchProductCount(ProductSearchDTO searchDTO);

	Long manageProduct(ProductDTO productDTO);

	ProductDTO getProductById(Long productId);

	List<ProductDTO> searchProductAutoComplete(String searchKey);

	boolean deleteProductById(Long id);

	List<ProductDTO> getAllProducts();

}
