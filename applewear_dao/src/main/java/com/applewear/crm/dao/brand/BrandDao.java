package com.applewear.crm.dao.brand;

import java.util.List;

import com.applewear.crm.dao.generic.GenericDao;
import com.applewear.crm.entity.brand.Brand;

public interface BrandDao extends GenericDao<Brand, Long>{

	List<Brand> getAllBrand();

}
