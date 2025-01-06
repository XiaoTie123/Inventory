package com.applewear.crm.service.size;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.applewear.crm.dao.size.SizeDao;
import com.applewear.crm.dto.size.SizeDTO;
import com.applewear.crm.entity.size.Size;
import com.applewear.crm.util.common.CommonUtil;

@Service
@Transactional
public class SizeServiceImpl implements SizeService {

	@Autowired
	private SizeDao sizeDao;

	
	@Override
	public List<SizeDTO> getAllSizeList() {

		List<SizeDTO> sizeDTOList = new ArrayList<SizeDTO>();

		List<Size> entityList = sizeDao.getAllSize();

		if (CommonUtil.validList(entityList)) {

			for (Size entity : entityList) {

				SizeDTO sizeDTO = new SizeDTO(entity);				
				sizeDTOList.add(sizeDTO);
			}
		}

		return sizeDTOList;
	}
	
	@Override
	public SizeDTO getSizeById(Long sizeId) {

		if (!CommonUtil.validLong(sizeId)) {
			return null;
		}

		Size entity = sizeDao.get(sizeId);

		if (entity != null) {
			return new SizeDTO(entity);
		}

		return null;
	}
	
	
	@Override
	public Long manageSize(SizeDTO sizeDTO) {

		Size size = new Size();

		if (CommonUtil.validLong(sizeDTO.getSizeId())) {
			size = sizeDao.get(sizeDTO.getSizeId());
			size.setUpdatedTime(new Date());
		} else {
			size.setCreatedTime(new Date());
			size.setUpdatedTime(new Date());
		}
		size.setSizeName(sizeDTO.getSizeName());
		size.setSizeCode(sizeDTO.getSizeCode());
		sizeDao.saveOrUpdate(size);
		return size != null && CommonUtil.validLong(size.getId()) ? size.getId() : null;
	}
}
