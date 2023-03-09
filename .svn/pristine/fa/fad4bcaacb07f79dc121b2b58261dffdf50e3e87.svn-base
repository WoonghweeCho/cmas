package com.dwenc.cmas.common.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.user.domain.CoUserInfo;
import com.dwenc.cmas.common.user.domain.Sign;

import docfbaro.query.CommonDao;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : CoUserService
 * 설    명 : Co사용자 관리를 위한 service 클래스
 * 작 성 자 : 홍두희
 * 작성일자 : 2012-12-05
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2011-12-07             최초 작성
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */
@Service
public class CoUserService {

	/**
	 * DB처리를 위한  공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao commonDao;

	/**
	 * <pre>
	 * 사용자 정보(List) 조회
	 * </pre>
	 * @param param
	 * @return
	 */
	public List<CoUserInfo> retrieveUserList(Map<String, Object> param) {
		return commonDao.queryForList("CoUser.selectCoUserList", param, CoUserInfo.class);
	}

	/**
	 * <pre>
	 * 조직정보를 조건에 의한 자료 조회한다.
	 * </pre>
	 * @param requestMap 검색조건
	 * @return 부서 정보 리스트
	 */
	public List<Map<String, Object>> retrieveSignOrgMapList(Map<String, Object> map) {
		return commonDao.queryForMapList("CoUser.selectSignOrgMapList", map);
	}

	/**
	 * <pre>
	 * 사원정보를 조건에 의한 자료 조회한다.
	 * </pre>
	 * @param requestMap 검색조건
	 * @return 사용자정보 리스트
	 */
	public List<Sign> retrieveSignUserList(Map<String, Object> map) {
		return commonDao.queryForList("CoUser.selectSignUserList", map, Sign.class);
	}
}
