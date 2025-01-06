package com.applewear.crm.service.brand;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.applewear.crm.dao.brand.BrandDao;
import com.applewear.crm.dto.brand.BrandDTO;
import com.applewear.crm.entity.brand.Brand;
import com.applewear.crm.util.common.CommonUtil;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandDao brandDao;

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
	public BrandDTO getBrandById(Long brandId) {

		if (!CommonUtil.validLong(brandId)) {
			return null;
		}

		Brand entity = brandDao.get(brandId);

		if (entity != null) {
			return new BrandDTO(entity);
		}

		return null;
	}

	@Override
	public Long manageBrand(BrandDTO brandDTO) {

		Brand brand = new Brand();

		if (CommonUtil.validLong(brandDTO.getBrandId())) {
			brand = brandDao.get(brandDTO.getBrandId());
			brand.setUpdatedTime(new Date());
		} else {
			brand.setCreatedTime(new Date());
			brand.setUpdatedTime(new Date());
		}
		brand.setBrandName(brandDTO.getBrandName());
		brandDao.saveOrUpdate(brand);
		return brand != null && CommonUtil.validLong(brand.getId()) ? brand.getId() : null;
	}

	@Override
	public boolean deleteBrandById(Long id) {

		if (!CommonUtil.validLong(id)) {
			return false;
		}

		Brand entity = brandDao.get(id);

		if (entity == null) {
			return false;
		}

		try {

			brandDao.delete(entity);
			return true;

		} catch (Exception e) {
			return false;
		}

	}
}
