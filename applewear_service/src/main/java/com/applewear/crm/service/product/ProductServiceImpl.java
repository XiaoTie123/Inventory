package com.applewear.crm.service.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.applewear.crm.dao.brand.BrandDao;
import com.applewear.crm.dao.category.CategoryDao;
import com.applewear.crm.dao.order_item.OrderItemDao;
import com.applewear.crm.dao.product.ProductDao;
import com.applewear.crm.dao.size.SizeDao;
import com.applewear.crm.dto.brand.BrandDTO;
import com.applewear.crm.dto.category.CategoryDTO;
import com.applewear.crm.dto.product.ProductDTO;
import com.applewear.crm.dto.product.ProductSearchDTO;
import com.applewear.crm.dto.size.SizeDTO;
import com.applewear.crm.entity.brand.Brand;
import com.applewear.crm.entity.category.Category;
import com.applewear.crm.entity.product.Product;
import com.applewear.crm.entity.size.Size;
import com.applewear.crm.entity.user.User;
import com.applewear.crm.util.common.CommonUtil;
import com.applewear.crm.util.common.ImageConstant;
import com.applewear.crm.util.common.ImagesUtil;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private final Logger logger = LogManager.getLogger();

	@Autowired
	private BrandDao brandDao;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private SizeDao sizeDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private OrderItemDao orderItemDao;

	@Override
	public List<BrandDTO> getAllBrandList() {

		List<BrandDTO> barndDTOList = new ArrayList<BrandDTO>();

		List<Brand> entityList = brandDao.getAllBrand();

		if (CommonUtil.validList(entityList)) {

			for (Brand entity : entityList) {

				BrandDTO brandDTO = new BrandDTO(entity);
				barndDTOList.add(brandDTO);
			}
		}

		return barndDTOList;
	}

	@Override
	public List<CategoryDTO> getAllCategoryList() {

		List<CategoryDTO> categoryList = new ArrayList<CategoryDTO>();

		List<Category> entityList = categoryDao.getAllCategory();

		if (CommonUtil.validList(entityList)) {

			for (Category entity : entityList) {

				CategoryDTO categoryDTO = new CategoryDTO(entity);
				categoryList.add(categoryDTO);
			}
		}

		return categoryList;
	}

	@Override
	public List<SizeDTO> getAllSizeList() {

		List<SizeDTO> sizeList = new ArrayList<SizeDTO>();

		List<Size> entityList = sizeDao.getAllSize();

		if (CommonUtil.validList(entityList)) {

			for (Size entity : entityList) {

				SizeDTO sizeDTO = new SizeDTO(entity);
				sizeList.add(sizeDTO);
			}
		}

		return sizeList;
	}

	@Override
	public Long searchProductCount(ProductSearchDTO searchDTO) {

		return productDao.searchProductCount(searchDTO);
	}

	@Override
	public List<ProductDTO> searchProductList(ProductSearchDTO searchDTO, HttpServletRequest httpRequest) {

		List<ProductDTO> productList = new ArrayList<ProductDTO>();

		List<Product> product = productDao.searchProduct(searchDTO);

		if (CommonUtil.validList(product)) {

			Integer num = searchDTO.getStart() != null ? searchDTO.getStart() + 1 : 1;

			for (Product entity : product) {
				ProductDTO productDto = new ProductDTO(entity);
				productDto.setProductPhoto(
						CommonUtil.prepareImageUrl(httpRequest, ImageConstant.PRODUCT, entity.getProductPhoto()));
				productDto.setNum(num);
				num++;
				productList.add(productDto);
			}
		}
		return productList;
	}

	@Override
	public ProductDTO getProductById(Long productId) {

		if (!CommonUtil.validLong(productId)) {
			return null;
		}

		Product entity = productDao.get(productId);

		if (entity != null && CommonUtil.validLong(entity.getId())) {
			return new ProductDTO(entity);
		}

		return null;
	}

	@Override
	public Long manageProduct(ProductDTO productDTO) {

		Product entity = new Product();

		if (CommonUtil.validLong(productDTO.getProductId())) {
			entity = productDao.get(productDTO.getProductId());
			entity.setUpdatedTime(new Date());
		} else {
			entity.setCreatedTime(new Date());
			entity.setUpdatedTime(new Date());
		}

		entity.setProductCode(productDTO.getProductCode());
		entity.setProductName(productDTO.getProductCode());
		entity.setQuantityPerPack(productDTO.getQuantity());

		if (CommonUtil.validLong(productDTO.getBrandId())) {
			Brand brand = new Brand();
			brand.setId(productDTO.getBrandId());
			entity.setBrand(brand);
		}

		if (CommonUtil.validLong(productDTO.getCategoryId())) {
			Category category = new Category();
			category.setId(productDTO.getCategoryId());
			entity.setCategory(category);
		}

		if (CommonUtil.validLong(productDTO.getSizeId())) {
			Size size = new Size();
			size.setId(productDTO.getSizeId());
			entity.setSize(size);
		}

		if (CommonUtil.validLong(productDTO.getUserId())) {
			User user = new User();
			user.setId(productDTO.getUserId());
			entity.setUser(user);
		}

		if (CommonUtil.validInteger(productDTO.getMargin())) {
			entity.setMargin(productDTO.getMargin());
		} else {
			entity.setMargin(0);
		}

		if (CommonUtil.validInteger(productDTO.getTransportation())) {
			entity.setTransportation(productDTO.getTransportation());
		} else {
			entity.setTransportation(0);
		}

		entity.setBuyingPrice(productDTO.getBuyingPrice());
		entity.setSellingPrice(productDTO.getSellingPrice());
		entity.setOneTimeProduct(productDTO.getOneTimeProduct());
		entity.setPrice(new BigDecimal(0.00));

		if (productDTO.getProductImageFile() != null
				&& CommonUtil.validString(productDTO.getProductImageFile().getOriginalFilename())) {
			try {
				String fileName = ImagesUtil.uploadMultipartImage(productDTO.getProductImageFile(),
						ImageConstant.PRODUCT, "product");
				entity.setProductPhoto(fileName);
			} catch (Exception e) {
				logger.error("Exception occurred while deleting product image => Exception => "
						+ ExceptionUtils.getStackTrace(e));
			}
		}

		productDao.saveOrUpdate(entity);
		return entity != null && CommonUtil.validLong(entity.getId()) ? entity.getId() : null;
	}

	@Override
	public List<ProductDTO> searchProductAutoComplete(String searchKey) {

		List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();

		List<Product> productList = productDao.searchProductAutoComplete(searchKey);

		if (CommonUtil.validList(productList)) {
			for (Product product : productList) {
				ProductDTO productDTO = new ProductDTO(product);
				productDTOList.add(productDTO);
			}
		}

		return productDTOList;
	}

	@Override
	public boolean deleteProductById(Long id) {

		if (!CommonUtil.validLong(id)) {
			return false;
		}

		if (orderItemDao.isOrderItemExistByProductId(id)) {
			logger.debug("Couldn't delete product because it is already in used in order.");
			return false;
		}

		Product entity = productDao.get(id);

		if (entity == null) {
			return false;
		}

		try {
			productDao.delete(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<ProductDTO> getAllProducts() {

		List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();

		List<Product> entityList = productDao.getAllProducts();

		if (CommonUtil.validList(entityList)) {
			for (Product entity : entityList) {
				ProductDTO productDTO = new ProductDTO(entity);
				productDTOList.add(productDTO);
			}
		}

		return productDTOList;
	}
}
