package com.dwenc.cmas.trip.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jcf.data.GridData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.info.domain.SiteDomain;
import com.dwenc.cmas.trip.domain.InnerTrip;
import com.dwenc.cmas.trip.domain.InnerTripDetail;
import com.dwenc.cmas.trip.domain.IsSpot;
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
public class InnerTripService {

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
     * DB 처리를 위한 공통 dao
     */
	@Autowired
	@Qualifier("sgnsDB")
	private CommonDao sgnsDB;


	/**
	 * 그룹 코드목록을 조회하기위한 메소드
	 *
	 *
	 * @param input
	 * @return mData (그룹 코드 목록)
	 * @throws Exception
	 */
	public List<InnerTrip> retrieveInnerTripList(Map<String, Object> params) {
		params.put("loginUserId", UserInfo.getLoginId());

		//20150610 권한 체크 없는 고로 일단 하드코딩
		String adminYn = (String)params.get("adminYn");

		if(adminYn.equals("Y")){
			params.remove("loginUserId");
		}

		return dao.queryForList("InnerTrip.retrieveInnerTripList", params, InnerTrip.class);
	}

	/**
	 * 그룹 코드목록을 조회하기위한 메소드
	 *
	 *
	 * @param input
	 * @return mData (그룹 코드 목록)
	 * @throws Exception
	 */
	public List<InnerTrip> retrieveInnerTripError(Map<String, Object> params) {
		params.put("loginUserId", UserInfo.getLoginId());

		//20150610 권한 체크 없는 고로 일단 하드코딩
		String adminYn = (String)params.get("adminYn");

		if(adminYn.equals("Y")){
			params.remove("loginUserId");
		}

		return dao.queryForList("InnerTrip.retrieveInnerTripError", params, InnerTrip.class);
	}

	public Map<String, Object> getCmasId(Map<String, Object> map) throws BusinessException{

		Map<String, Object> rslMap = null;
		rslMap = dao.queryForMap("InnerTrip.getMaxCmasId", map);
		if(rslMap == null || rslMap.equals("null")){
			rslMap = map;
		}
		return dao.queryForMap("InnerTrip.getCmasId", rslMap);
	}

	public void insertInnerTripTempDoc(Map<String, Object> map) throws BusinessException{
		map.put("fstRegUserId", UserInfo.getLoginId());
		map.put("fnlEditUserId", UserInfo.getLoginId());
		dao.update("InnerTrip.insertInnerTripTempDoc", map);
	}

	public void saveInnerTripDraft(Map<String, Object> map) throws BusinessException{
		map.put("fstRegUserId", UserInfo.getLoginId());
		map.put("fnlEditUserId", UserInfo.getLoginId());
		dao.update("InnerTrip.saveInnerTripDraft", map);
	}


	public void saveInnerTripDraftDetail(GridData<HashMap> gridData, final String docNo) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {

			@Override
			public void insert(HashMap record, int index) {
				record.put("docNo", docNo);
				record.put("fstRegUserId", UserInfo.getLoginId());
				record.put("fnlEditUserId", UserInfo.getLoginId());

				System.out.println("insert 실행");
				dao.update("InnerTrip.insertInnerTripDtl", record);
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
		return dao.queryForMap("InnerTrip.retrieveViewDocInfo", params);
	}

	/**
	 * 그룹 코드목록을 조회하기위한 메소드
	 *
	 *
	 * @param input
	 * @return mData (그룹 코드 목록)
	 * @throws Exception
	 */
	public List<InnerTripDetail> retrieveViewDocInfoDtl(Map<String, Object> params) {
		return dao.queryForList("InnerTrip.retrieveViewDocInfoDtl", params, InnerTripDetail.class);
	}

	/**
	 * 그룹 코드목록을 조회하기위한 메소드
	 *
	 *
	 * @param input
	 * @return mData (그룹 코드 목록)
	 * @throws Exception
	 */
	public List<IsSpot> retrieveIsSpotMgmt(Map<String, Object> params) {
		return dao.queryForList("InnerTrip.retrieveIsSpotMgmt", params, IsSpot.class);
	}

	public List<IsSpot> retrieveIsSpotMgmtBoss(Map<String, Object> params) {
		return dao.queryForList("InnerTrip.retrieveIsSpotMgmtBoss", params, IsSpot.class);
	}

	public List<IsSpot> retrieveOrgTeamBoss(Map<String, Object> params) {
		return dao.queryForList("InnerTrip.retrieveOrgTeamBoss", params, IsSpot.class);
	}

	public List<IsSpot> retrieveDrafterOrgTeamBoss(Map<String, Object> params) {
		return dao.queryForList("InnerTrip.retrieveDrafterOrgTeamBoss", params, IsSpot.class);
	}

	public List<IsSpot> retrieveIsOfficer(Map<String, Object> params) {
		return dao.queryForList("InnerTrip.retrieveIsOfficer", params, IsSpot.class);
	}

	public List<IsSpot> retrieveIsOrgBoss(Map<String, Object> params) {
		return dao.queryForList("InnerTrip.retrieveIsOrgBoss", params, IsSpot.class);
	}

	public Map<String, Object> retrieveCoUserInfo(Map<String, Object> params) {
		return dao.queryForMap("InnerTrip.retrieveCoUserInfo", params);
	}

	public Map<String, Object> retrievePABC1UserInfo(Map<String, Object> params) {
		return dao.queryForMap("InnerTrip.retrievePABC1UserInfo", params);
	}

	public Map<String, Object> retrieveCoUserInfo2(Map<String, Object> params) {
		return dao.queryForMap("InnerTrip.retrieveCoUserInfo2", params);
	}

	public int updateInnerTripInfo(Map<String, Object> params) {
		int result = dao.update("InnerTrip.updateInnerTripInfo", params);
		System.out.println("result : " + result);
		return result;
	}

	public int updateEHRInfo(Map<String, Object> params) {
		int result = dao2.update("InnerTrip.updateEHRInfo", params);
		System.out.println("result : " + result);
		return result;
	}

	public int updateUpdateEHRInfo(Map<String, Object> params) {
		int result = dao2.update("InnerTrip.updateUpdateEHRInfo", params);
		System.out.println("result : " + result);
		return result;
	}

	public int deleteEHRInfo(Map<String, Object> params) {
		int result = dao2.update("InnerTrip.deleteEHRInfo", params);
		System.out.println("result : " + result);
		return result;
	}

	public Map<String, Object> selectEHRInfo(Map<String, Object> params) {
		Map<String, Object> result = dao2.queryForMap("InnerTrip.selectEHRInfo", params);
		return result;
	}

	public Map<String, Object> selectEHRInfo2(Map<String, Object> params) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("result1", dao2.queryForMap("InnerTrip.selectEHRInfo2", params));
		return resMap;
	}

	public Map<String, Object> selectCoFileCount(Map<String, Object> params) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("result1", dao.queryForMap("InnerTrip.selectCoFileCount", params));
		return resMap;
	}

	public void saveInnerTripDocSave(Map<String, Object> map) throws BusinessException{
		map.put("fstRegUserId", UserInfo.getLoginId());
		map.put("fnlEditUserId", UserInfo.getLoginId());
		dao.update("InnerTrip.saveInnerTripDocSave", map);
	}

	public void updateInnerTripDocSave(Map<String, Object> map) throws BusinessException{
		map.put("fnlEditUserId", UserInfo.getLoginId());
		dao.update("InnerTrip.updateInnerTripDocSave", map);
	}

	public Map<String, Object> retrieveSavedDocInfo(Map<String, Object> params) {
		return dao.queryForMap("InnerTrip.retrieveSavedDocInfo", params);
	}

	public void deleteInnerTripByDocNo(Map<String, Object> map) throws BusinessException{
		dao.update("InnerTrip.deleteInnerTripByDocNo", map);
	}

	public void deleteInnerTripDtlByDocNo(Map<String, Object> map) throws BusinessException{
		dao.update("InnerTrip.deleteInnerTripDtlByDocNo", map);
	}

	public void updateInnerTripDraftDoc(Map<String, Object> map) throws BusinessException{;
		map.put("fnlEditUserId", UserInfo.getLoginId());
		dao.update("InnerTrip.updateInnerTripDraftDoc", map);
	}

	public void saveSignInfoInnerTrip(GridData<HashMap> gridData, final String docNo, final String signId, final String dutyCls) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {

			@Override
			public void insert(HashMap record, int index) {
				record.put("docNo", docNo);
				record.put("signId", signId);
				record.put("dutyCls", dutyCls);

				record.put("fstRegUserId", UserInfo.getLoginId());
				record.put("fnlEditUserId", UserInfo.getLoginId());

				dao.update("InnerTrip.insertSignInfoInnerTrip", record);
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

	public void saveSignInfoInnerTripFromList(List listData, final String docNo, final String signId, final String dutyCls){
		for(int i = 0; i < listData.size(); i++){

			HashMap record = (HashMap)listData.get(i);

			record.put("docNo", docNo);
			record.put("signId", signId);
			record.put("dutyCls", dutyCls);

			record.put("fstRegUserId", UserInfo.getLoginId());
			record.put("fnlEditUserId", UserInfo.getLoginId());

			dao.update("InnerTrip.insertSignInfoInnerTrip", record);
		}
	}

	public List<Sign> retrieveSignInfo(Map<String, Object> params) {
		return dao.queryForList("InnerTrip.retrieveSignInfo", params, Sign.class);
	}

	public int updateSignDt(Map<String, Object> params) {
		int result = dao.update("InnerTrip.updateSignDt", params);
		System.out.println("result : " + result);
		return result;
	}

	public void updateSgnsReject(Map<String, Object> params) {
		dao.update("InnerTrip.updateSgnsReject", params);
	}

	public void saveCmasDocUpdateFail(Map<String, Object> params) {
		dao.update("InnerTrip.saveCmasDocUpdateFail", params);
	}

	public Map<String, Object> retrieveDocInfo(Map<String, Object> params){
		return dao.queryForMap("InnerTrip.retrieveDocInfo", params);
	}

	public Map<String, Object> retrievelegacyInfo(Map<String, Object> params){
		return sgnsDB.queryForMap("InnerTrip.retrievelegacyInfo", params);
	}

	public void updateEvgBarnetInfo(Map<String, Object> params){
		dao.update("InnerTrip.updateEvgBarnetInfo", params);
	}


}

