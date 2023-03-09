package com.dwenc.cmas.eaps.carapp.service;

import java.util.HashMap;
import java.util.Map;

import jcf.data.GridData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.eaps.carapp.domain.CarApp;

import docfbaro.query.CommonDao;
import docfbaro.query.callback.AbstractRowStatusCallback;


@Service
public class CarAppService {

	/**
	 * DB처리를 위한  공통 dao
	 */
	@Autowired
	@Qualifier("2ndDB")
	private CommonDao commonDao;

	/**
	 * 1. 함수명 변경 : CarApp 부분
	 * 2. Sqlmap 명 변경 : CarAppSqlmap.insertCarApp
	 * @param map
	 */
	public Map<String, Object> insertCarApp(Map<String, Object> map){
		Map<String, Object> resMap = new HashMap();

		try{
			commonDao.update("CarAppSqlmap.insertCarApp", map);
			resMap.put("TYPE", "SUCCESS");
		}catch(Exception e){
			e.printStackTrace();
			resMap.put("TYPE", "FAIL");
			resMap.put("MSG", "system.err.notsave");
		}
		return resMap;
	}

	/**
	 * 1. 함수명 변경 : CarApp 부분
	 * 2. Sqlmap 명 변경 : CarAppSqlmap.insertCarApp
	 * @param map
	 */
	public Map<String, Object> updateCarApp(Map<String, Object> map){
		Map<String, Object> resMap = new HashMap();

		try{
			commonDao.update("CarAppSqlmap.updateCarApp", map);
			resMap.put("TYPE", "SUCCESS");
		}catch(Exception e){
			resMap.put("TYPE", "FAIL");
			resMap.put("MSG", "Message Content");
		}
		return resMap;

	}

	/**
	 * 1. 함수명 변경 : CarApp 부분
	 * 2. Sqlmap 명 변경 : CarAppSqlmap.insertCarApp
	 * @param map
	 */
	public Map<String, Object> deleteCarApp(Map<String, Object> map){
		Map<String, Object> resMap = new HashMap();

		try{
			commonDao.update("CarAppSqlmap.deleteCarApp", map);
			resMap.put("TYPE", "SUCCESS");
		}catch(Exception e){
			resMap.put("TYPE", "FAIL");
			resMap.put("MSG", "Message Content");
		}
		return resMap;
	}

	/**
	 * 1. 함수명 변경 : CarApp 부분
	 * 2. Sqlmap 명 변경 : CarAppSqlmap.selectCarApp
	 * @param map
	 */
	public Map<String, Object> selectCarApp(Map<String, Object> map){
		 Map<String, Object> resMap = commonDao.queryForMap("CarAppSqlmap.selectCarApp", map);
		return resMap;
	}

	/**
	 * <pre>
	 * 결재문서 수정 이력정보를 저장한다.
	 * </pre>
	 * @param gridData
	 */
	private void insertCarApp(GridData<CarApp> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<CarApp>() {
			/**
			 * 추가
			 */
			@Override
			public void normal(CarApp record, int rowNum) {
//				record.setUserId(UserInfo.getUserId());
//				record.setFstRegUserId(UserInfo.getUserId());
//				record.setFnlEditUserId(UserInfo.getUserId());
//				dao.update("SignDocEditHst.insertSignDocEditHst", record);
			}

			/**
			 * 추가
			 */
			@Override
			public void insert(CarApp record, int rowNum) {
//				record.setUserId(UserInfo.getUserId());
//				record.setFstRegUserId(UserInfo.getUserId());
//				record.setFnlEditUserId(UserInfo.getUserId());
//				dao.update("SignDocEditHst.insertSignDocEditHst", record);
			}

			/**
			 * 결재문서 수정 이력 정보 업데이트
			 */
			@Override
			public void update(CarApp newRecord, CarApp oldRecord, int rowNum) {
//				newRecord.setFstRegUserId(UserInfo.getUserId());
//				newRecord.setFnlEditUserId(UserInfo.getUserId());
//				dao.update("SignDocEditHst.updateSignDocEditHst", newRecord);
			}
		});
	}


}