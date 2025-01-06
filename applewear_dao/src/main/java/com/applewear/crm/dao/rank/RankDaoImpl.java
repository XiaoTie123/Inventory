package com.applewear.crm.dao.rank;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.applewear.crm.dao.generic.GenericDaoImpl;
import com.applewear.crm.dto.rank.RankSearchDTO;
import com.applewear.crm.entity.rank.Rank;
import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;

@Repository
@Transactional
public class RankDaoImpl extends GenericDaoImpl<Rank, Long> implements RankDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Rank> searchRanks(RankSearchDTO searchDTO) {

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(prepareSearchQuery(searchDTO, false));

		sqlQuery.addEntity(Rank.class);

		return sqlQuery.list();
	}

	@Override
	public Long searchRankCount(RankSearchDTO searchDTO) {

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(prepareSearchQuery(searchDTO, true));

		addSearchParameter(sqlQuery, searchDTO);

		return ((Number) sqlQuery.uniqueResult()).longValue();
	}

	private String prepareSearchQuery(RankSearchDTO searchDTO, boolean countOnly) {
		
		StringBuilder query = new StringBuilder();

		query.append(countOnly ? "SELECT COUNT(DISTINCT r.rank_id) " : "SELECT r.* ")
				.append(" FROM tbl_rank r WHERE 1=1 ");

		addSearchCriteria(query, searchDTO);

		if (!countOnly) {

			query.append("ORDER BY r.rank_name DESC ");

			appendPagination(query, searchDTO);
		}

		return query.toString();
	}

	private void addSearchCriteria(StringBuilder query, RankSearchDTO searchDTO) {

		if (CommonUtil.validString(searchDTO.getName())) {
			query.append(" AND r.rank_name LIKE :name ");
		}

	}

	private void addSearchParameter(SQLQuery sqlQuery, RankSearchDTO searchDTO) {
		if (CommonUtil.validString(searchDTO.getName())) {
			sqlQuery.setParameter("name", "%" + searchDTO.getName() + "%");
		}
	}

	private void appendPagination(StringBuilder query, RankSearchDTO searchDTO) {
		Integer start = CommonUtil.validNumber(searchDTO.getStart()) ? searchDTO.getStart() : 0;
		Integer length = CommonUtil.validNumber(searchDTO.getLength()) ? searchDTO.getLength()
				: CommonConstants.ADMIN_RECORD_PER_PAGE;
		query.append("LIMIT ").append(start).append(", ").append(length);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rank> getAllRanks() {
		StringBuilder builder = new StringBuilder(" SELECT * FROM tbl_rank ");
		builder.append(" ORDER BY rank_name ");
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(builder.toString());
		sqlQuery.addEntity(Rank.class);
		return sqlQuery.list();
	}

}
