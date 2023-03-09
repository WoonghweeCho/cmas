package com.dwenc.cmas.info.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import jcf.data.GridData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//import java.util.Locale;
//import java.util.Properties;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.dwenc.cmas.info.domain.FloorDomain;

import docfbaro.query.CommonDao;
import docfbaro.query.callback.AbstractRowStatusCallback;

/**
 * <pre>
 * --------------------------------------------------------------
 * 업무구분 : 공통 - 코드관리
 * 프로그램 : Floor
 * 설 명 : 공통코드 관리를 위한 service 클래스
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
public class FloorService {

    /**
     * DB 처리를 위한 공통 dao
     */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	/**
	 * 그룹 코드목록을 조회하기위한 메소드
	 *
	 *
	 * @param input
	 * @return mData (그룹 코드 목록)
	 * @throws Exception
	 */
	public List<FloorDomain> floorList(Map<String, Object> input) {
		return dao.queryForList("Floor.floorList", input, FloorDomain.class);
	}


	/**
	 * 1. 함수명    : 층별정보 저장
	 * 2. Sqlmap명 : Floor.insertFloor
	 * @param map
	 */
	public Map<String, Object> insertFloor(Map<String, Object> map){
		Map<String, Object> resMap = new HashMap();

		try{
			dao.update("Floor.insertFloor", map);
			resMap.put("TYPE", "SUCCESS");
		}catch(Exception e){
			e.printStackTrace();
			resMap.put("TYPE", "FAIL");
			resMap.put("MSG", "system.err.notsave");
		}
		return resMap;
	}

	/**
	 * 1. 함수명   : 층별정보 업데이트
	 * 2. Sqlmap명 : Floor.updateFloor
	 * @param map
	 */
	public Map<String, Object> updateFloor(Map<String, Object> map){
		Map<String, Object> resMap = new HashMap();

		try{
			dao.update("Floor.updateFloor", map);
			resMap.put("TYPE", "SUCCESS");
		}catch(Exception e){
			e.printStackTrace();
			resMap.put("TYPE", "FAIL");
			resMap.put("MSG", "system.err.notupdate");
		}
		return resMap;
	}

	/**
	 * 1. 함수명    : 층별정보 삭제
	 * 2. Sqlmap명 : Floor.deleteFloor
	 * @param map
	 */
	public Map<String, Object> deleteFloor(Map<String, Object> map){
		Map<String, Object> resMap = new HashMap();

		try{
			dao.update("Floor.deleteFloor", map);
			resMap.put("TYPE", "SUCCESS");
		}catch(Exception e){
			e.printStackTrace();
			resMap.put("TYPE", "FAIL");
			resMap.put("MSG", "system.err.notdelete");
		}
		return resMap;
	}

}

