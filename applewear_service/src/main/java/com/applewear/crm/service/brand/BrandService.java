package com.applewear.crm.service.brand;

import java.util.List;
import com.applewear.crm.dto.brand.BrandDTO;
public interface BrandService {
	
	Long manageBrand(BrandDTO brandDTO);

	List<BrandDTO> getAllBrandList();
	
	BrandDTO getBrandById(Long brandId);
	
	boolean deleteBrandById(Long id);

}
