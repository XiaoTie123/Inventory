package com.applewear.crm.service.rank;

import java.util.List;

import com.applewear.crm.dto.rank.RankDTO;
import com.applewear.crm.dto.rank.RankSearchDTO;

public interface RankService {

	Long manageRank(RankDTO rankDTO);

	List<RankDTO> searchRanks(RankSearchDTO searchDTO);

	Long searchRankCount(RankSearchDTO searchDTO);

	List<RankDTO> getAllRanks();

	RankDTO getRankById(Long rankId);
	
	boolean deleteRankById(Long rankId);

}
