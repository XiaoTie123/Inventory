package com.applewear.crm.dao.size;

import java.util.List;

import com.applewear.crm.dao.generic.GenericDao;
import com.applewear.crm.entity.size.Size;

public interface SizeDao extends GenericDao<Size, Long> {

	List<Size> getAllSize();

}
