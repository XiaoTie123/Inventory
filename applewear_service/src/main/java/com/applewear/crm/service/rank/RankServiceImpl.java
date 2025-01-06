package com.applewear.crm.service.rank;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.applewear.crm.dao.rank.RankDao;
import com.applewear.crm.dto.rank.RankDTO;
import com.applewear.crm.dto.rank.RankSearchDTO;
import com.applewear.crm.entity.rank.Rank;
import com.applewear.crm.util.common.CommonUtil;

@Service
@Transactional
public class RankServiceImpl implements RankService {

	private final Logger logger = LogManager.getLogger();

	@Autowired
	private RankDao rankDao;

	@Override
	public Long manageRank(RankDTO rankDTO) {

		Rank rank = new Rank();

		if (CommonUtil.validLong(rankDTO.getRankId())) {
			rank = rankDao.get(rankDTO.getRankId());
			rank.setUpdatedTime(new Date());
		} else {
			rank.setCreatedTime(new Date());
			rank.setUpdatedTime(new Date());
		}

		rank.setRankName(rankDTO.getName());

		rank.setDiscount(rankDTO.getDiscount());

		rankDao.saveOrUpdate(rank);

		return rank != null && CommonUtil.validLong(rank.getId()) ? rank.getId() : null;
	}

	@Override
	public List<RankDTO> searchRanks(RankSearchDTO searchDTO) {

		List<RankDTO> rankDTOList = new ArrayList<RankDTO>();

		List<Rank> rankList = rankDao.searchRanks(searchDTO);

		if (CommonUtil.validList(rankList)) {

			Integer num = searchDTO.getStart() != null ? searchDTO.getStart() + 1 : 1;

			for (Rank rank : rankList) {
				RankDTO rankDTO = new RankDTO(rank);
				rankDTO.setNum(num);
				rankDTOList.add(rankDTO);
				num++;
			}

		}

		return rankDTOList;
	}

	@Override
	public Long searchRankCount(RankSearchDTO searchDTO) {

		return rankDao.searchRankCount(searchDTO);
	}

	@Override
	public List<RankDTO> getAllRanks() {

		List<RankDTO> rankDTOList = new ArrayList<RankDTO>();

		List<Rank> rankList = rankDao.getAllRanks();

		if (CommonUtil.validList(rankList)) {
			for (Rank entity : rankList) {
				RankDTO rankDTO = new RankDTO(entity);
				rankDTOList.add(rankDTO);
			}
		}

		return rankDTOList;
	}

	@Override
	public RankDTO getRankById(Long rankId) {

		if (!CommonUtil.validLong(rankId)) {
			return null;
		}

		Rank entity = rankDao.get(rankId);

		if (entity != null) {
			return new RankDTO(entity);
		}

		return null;
	}

	@Override
	public boolean deleteRankById(Long rankId) {

		if (!CommonUtil.validLong(rankId)) {
			return false;
		}

		Rank entity = rankDao.get(rankId);

		if (entity == null) {
			return false;
		}

		try {

			rankDao.delete(entity);

			return true;

		} catch (Exception e) {
			logger.error("Exception occurred while deleting rank => rank : " + entity.getRankName());
			return false;
		}

	}

}
