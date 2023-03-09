package com.dwenc.cmas.common.org.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.org.domain.OrgInfo;

import docfbaro.query.CommonDao;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통
 * 프로그램 : CoOrgService
 * 설    명 : 조직정보 조회를 위한 service 클래스
 * 작 성 자 : 이재열
 * 작성일자 : 2011-12-08
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일       이 름 사유
 * ---------------------------------------------------------------
 * 2011-12-08 이재열 최초 작성
 * ---------------------------------------------------------------
 * </pre>
 * 
 * @version 1.0
 */
@Service
public class CoOrgService {
	/**
	 * DB 처리를 위한 공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao commonDao;

	/**
	 * <pre>
	 * 본부정보를 조건에 의한 자료 조회한다.
	 * </pre>
	 * 
	 * @param param 조회조건(사용여부)
	 * @return 조직정보리스트
	 */
	public List<OrgInfo> retrieveHqList(Map<String, Object> param) {
		return commonDao.queryForList("CoOrg.retrieveHqList", param, OrgInfo.class);
	}

	/**
	 * <pre>
	 * 조직 정보를 조건에 의한 자료 조회한다.
	 * </pre>
	 * 
	 * @param param 조회조건 조직코드,조직명
	 * @return 조직정보리스트
	 */
	public List<OrgInfo> retrieveOrgList(Map<String, Object> param) {
		return commonDao.queryForList("CoOrg.retrieveOrgList", param, OrgInfo.class);
	}

	/**
	 * <pre>
	 * 조직 정보를 조건에 의한 자료 조회한다.
	 * </pre>
	 * 
	 * @param param 조회조건 조직코드,조직명
	 * @return 조직정보리스트
	 */
	public List<OrgInfo> retrieveOrgsList(Map<String, Object> param) {
		Map<String, Object> map = new HashMap<String, Object>();
		String orgCdStr = (String) param.get("orgCds");
		String[] orgCds = orgCdStr.split(",");
		map.put("orgCds", orgCds);

		return commonDao.queryForList("CoOrg.retrieveOrgsList", map, OrgInfo.class);
	}

	/**
	 * <pre>
	 * 조직 정보를 조건에 의한 자료 조회한다.
	 * </pre>
	 * 
	 * @param param 조회조건 조직코드,조직명
	 * @return 조직정보리스트
	 */
	public List<OrgInfo> retrieveOrgTreeList(Map<String, Object> param) {
		return commonDao.queryForList("CoOrg.retrieveOrgTreeList", param, OrgInfo.class);
	}
}
