package com.dwenc.cmas.trip.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jcf.data.GridData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.trip.domain.CityTransp;
import com.dwenc.cmas.trip.domain.CityTranspDtl;
import com.dwenc.cmas.trip.domain.Emp;
import com.dwenc.cmas.trip.domain.InnerTripDetail;
import com.dwenc.cmas.trip.domain.Sign;

import docfbaro.iam.UserInfo;
import docfbaro.query.CommonDao;
import docfbaro.query.callback.AbstractRowStatusCallback;
import docfbaro.sua.exception.BusinessException;

/**
 * <pre>
 * --------------------------------------------------------------
 * 업무구분 : 공통 - 코드관리
 * 프로그램 : COA0101
 * 설 명 : 공통코드 관리를 위한 service 클래스
 * 작 성 자 :
 * 작성일자 :
 * 수정이력
 * --------------------------------------------------------------
 * 수정일                          이 름          사유
 * --------------------------------------------------------------
 *
 * --------------------------------------------------------------
 * </pre>
 * @version 1.0
 *
 */

@Service
public class BdgtService {

    /**
     * DB 처리를 위한 공통 dao
     */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	/**
     * DB 처리를 위한 공통 dao
     */
	@Autowired
	@Qualifier("2ndDB")
	private CommonDao dao2;

	/**
	 * 현장코드그룹 코드목록을 조회하기위한 메소드
	 * @param input
	 * @return mData (그룹 코드 목록)
	 * @throws Exception
	 */
	public List<Map<String, Object>> getPrctrList(Map<String, Object> params) {
		return dao2.queryForMapList("Bdgt.getPrctrList", params);
	}

	/**
	 * 외곽조직코드그룹 코드목록을 조회하기위한 메소드
	 * @param input
	 * @return mData (그룹 코드 목록)
	 * @throws Exception
	 */
	public List<Map<String, Object>> getPrctrList1(Map<String, Object> params) {
		return dao2.queryForMapList("Bdgt.getPrctrList1", params);
	}

}

