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
public class CityTranspService {

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
	public List<CityTransp> retrieveCityTranspList(Map<String, Object> params) {
		params.put("loginUserId", UserInfo.getLoginId());

		//20150610 권한 체크 없는 고로 일단 하드코딩
		String adminYn = (String)params.get("adminYn");

		if(adminYn.equals("Y")){
			params.remove("loginUserId");
		}

		return dao.queryForList("CityTransp.retrieveCityTranspList", params, CityTransp.class);
	}

	public Map<String, Object> getCmasId(Map<String, Object> map) throws BusinessException{

		Map<String, Object> rslMap = null;
		rslMap = dao.queryForMap("CityTransp.getMaxCmasId", map);
		if(rslMap == null || rslMap.equals("null")){
			rslMap = map;
		}
		return dao.queryForMap("CityTransp.getCmasId", rslMap);
	}

	public void insertCityTranspTempDoc(Map<String, Object> map) throws BusinessException{
		map.put("fstRegUserId", UserInfo.getLoginId());
		map.put("fnlEditUserId", UserInfo.getLoginId());
		dao.update("CityTransp.insertCityTranspTempDoc", map);
	}

	public void saveCityTranspDraft(Map<String, Object> map) throws BusinessException{
		map.put("fstRegUserId", UserInfo.getLoginId());
		map.put("fnlEditUserId", UserInfo.getLoginId());
		dao.update("CityTransp.saveCityTranspDraft", map);
	}


	public void saveCityTranspDraftDtl(GridData<HashMap> gridData, final String cmasId) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {

			@Override
			public void insert(HashMap record, int index) {
				record.put("docNo", cmasId);
				record.put("fstRegUserId", UserInfo.getLoginId());
				record.put("fnlEditUserId", UserInfo.getLoginId());

				System.out.println("insert 실행");
				dao.update("CityTransp.saveCityTranspDraftDtl", record);
			}

			@Override
			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getLoginId());

				System.out.println("insert 실행");
//				dao.update("InnerTrip.updateInnerTripDtl", newRecord);
			}

			@Override
			public void delete(HashMap record, int index) {

				System.out.println("delete 실행");
//				dao.update("InnerTrip.deleteInnerTripDtl", record);
			}
		});
	}

	/**
	 * 그룹 코드목록을 조회하기위한 메소드
	 *
	 *
	 * @param input
	 * @return mData (그룹 코드 목록)
	 * @throws Exception
	 */
	public Map<String, Object> retrieveViewDocInfo(Map<String, Object> params) {
		return dao.queryForMap("CityTransp.retrieveViewDocInfo", params);
	}

	/**
	 * 그룹 코드목록을 조회하기위한 메소드
	 *
	 *
	 * @param input
	 * @return mData (그룹 코드 목록)
	 * @throws Exception
	 */
	public List<CityTranspDtl> retrieveViewDocInfoDtl(Map<String, Object> params) {
		return dao.queryForList("CityTransp.retrieveViewDocInfoDtl", params, CityTranspDtl.class);
	}

	public void saveCityTranspDocSave(Map<String, Object> map) throws BusinessException{
		map.put("fstRegUserId", UserInfo.getLoginId());
		map.put("fnlEditUserId", UserInfo.getLoginId());
		dao.update("CityTransp.saveCityTranspDocSave", map);
	}

	public void updateCityTranspDocSave(Map<String, Object> map) throws BusinessException{
		map.put("fnlEditUserId", UserInfo.getLoginId());
		dao.update("CityTransp.updateCityTranspDocSave", map);
	}

	public Map<String, Object> retrieveSavedDocInfo(Map<String, Object> params) {
		return dao.queryForMap("CityTransp.retrieveSavedDocInfo", params);
	}

	public void deleteCityTranspByDocNo(Map<String, Object> map) throws BusinessException{
		dao.update("CityTransp.deleteInnerTripByDocNo", map);
	}

	public void deleteCityTranspDtlByDocNo(Map<String, Object> map) throws BusinessException{
		dao.update("CityTransp.deleteInnerTripDtlByDocNo", map);
	}

	public void saveSignInfoCityTransp(GridData<HashMap> gridData, final String docNo, final String signId, final String dutyCls) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {

			@Override
			public void insert(HashMap record, int index) {
				record.put("docNo", docNo);
				record.put("signId", signId);
				record.put("dutyCls", dutyCls);

				record.put("fstRegUserId", UserInfo.getLoginId());
				record.put("fnlEditUserId", UserInfo.getLoginId());

				dao.update("CityTransp.insertSignInfoCityTransp", record);
			}

			@Override
			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getLoginId());

				System.out.println("insert 실행");
//				dao.update("InnerTrip.updateInnerTripDtl", newRecord);
			}

			@Override
			public void delete(HashMap record, int index) {

				System.out.println("delete 실행");
//				dao.update("InnerTrip.deleteInnerTripDtl", record);
			}
		});
	}

	public void saveSignInfoCityTranspFromList(List listData, final String docNo, final String signId, final String dutyCls){
		for(int i = 0; i < listData.size(); i++){

			HashMap record = (HashMap)listData.get(i);

			record.put("docNo", docNo);
			record.put("signId", signId);
			record.put("dutyCls", dutyCls);

			record.put("fstRegUserId", UserInfo.getLoginId());
			record.put("fnlEditUserId", UserInfo.getLoginId());

			dao.update("CityTransp.insertSignInfoCityTransp", record);
		}
	}

	public int updateCityTranspInfo(Map<String, Object> params) {
		int result = dao.update("CityTransp.updateCityTranspInfo", params);
		System.out.println("result : " + result);
		return result;
	}

	public void updateCityTranspDraftDoc(Map<String, Object> map) throws BusinessException{
		map.put("fnlEditUserId", UserInfo.getLoginId());
		dao.update("CityTransp.updateCityTranspDraftDoc", map);
	}

	public List<Emp> retrieveEmpListByOrgCd(Map<String, Object> params) {
		return dao.queryForList("CityTransp.retrieveEmpListByOrgCd", params, Emp.class);
	}

	public List<Sign> retrieveSignInfo(Map<String, Object> params) {
		return dao.queryForList("CityTransp.retrieveSignInfo", params, Sign.class);
	}

	public int updateSignDt(Map<String, Object> params) {
		int result = dao.update("CityTransp.updateSignDt", params);
		System.out.println("result : " + result);
		return result;
	}

	public void updateSgnsReject(Map<String, Object> params) {
		dao.update("CityTransp.updateSgnsReject", params);
	}

	public void saveCmasDocUpdateFail(Map<String, Object> params) {
		dao.update("CityTransp.saveCmasDocUpdateFail", params);
	}

	// 중복신청체크
	public Map<String, Object> retrieveCheckDraftDuplication(Map<String, Object> params) {

		Map<String, Object> resMap = new HashMap<String, Object>();

		resMap.put("result1", dao.queryForMap("CityTransp.retrieveCheckDraftDuplication1", params));	//시내교통비
		resMap.put("result2", dao.queryForMap("CityTransp.retrieveCheckDraftDuplication2", params));	//국내출장
		resMap.put("result3", dao.queryForMap("CityTransp.retrieveCheckDraftDuplication3", params));	//해외출장신청서
		resMap.put("result4", dao.queryForMap("CityTransp.retrieveCheckDraftDuplication4", params)); //해외출장정산서

		return resMap;

	}

}

