package com.applewear.crm.dao.product;

import java.util.List;

import com.applewear.crm.dao.generic.GenericDao;
import com.applewear.crm.dto.product.ProductSearchDTO;
import com.applewear.crm.entity.product.Product;

public interface ProductDao extends GenericDao<Product, Long> {

	List<Product> searchProduct(ProductSearchDTO searchDTO);

	Long searchProductCount(ProductSearchDTO searchDTO);

	List<Product> searchProductAutoComplete(String searchKey);

	List<Product> getAllProducts();

}
