package com.applewear.crm.dao.rank;

import java.util.List;

import com.applewear.crm.dao.generic.GenericDao;
import com.applewear.crm.dto.rank.RankSearchDTO;
import com.applewear.crm.entity.rank.Rank;

public interface RankDao extends GenericDao<Rank, Long> {

	List<Rank> searchRanks(RankSearchDTO searchDTO);

	Long searchRankCount(RankSearchDTO searchDTO);

	List<Rank> getAllRanks();

}
