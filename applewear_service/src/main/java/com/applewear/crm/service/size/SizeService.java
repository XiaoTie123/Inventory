package com.applewear.crm.service.size;

import java.util.List;
import com.applewear.crm.dto.size.SizeDTO;

public interface SizeService {
	Long manageSize(SizeDTO sizeDTO);

	List<SizeDTO> getAllSizeList();
	
	SizeDTO getSizeById(Long sizeId);
}
