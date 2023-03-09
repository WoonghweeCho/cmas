package com.dwenc.cmas.trip.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jcf.data.GridData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.info.domain.SiteDomain;
import com.dwenc.cmas.trip.domain.Expn;
import com.dwenc.cmas.trip.domain.ExtnlPer;
import com.dwenc.cmas.trip.domain.InnerTrip;
import com.dwenc.cmas.trip.domain.InnerTripDetail;
import com.dwenc.cmas.trip.domain.Nat;
import com.dwenc.cmas.trip.domain.OuterTrip;
import com.dwenc.cmas.trip.domain.Sign;
import com.dwenc.cmas.trip.domain.TripRef;
import com.dwenc.cmas.trip.domain.Visa;

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
public class OuterTripService {

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
	@Qualifier("sgnsDB")
	private CommonDao sgnsDB;


	public List<OuterTrip> retrieveOuterTripList(Map<String, Object> params) {
		params.put("loginUserId", UserInfo.getLoginId());
		//20150610 권한 체크 없는 고로 일단 하드코딩
		String adminYn = (String)params.get("adminYn");

		if(adminYn.equals("Y")){
			params.remove("loginUserId");
		}
		return dao.queryForList("OuterTrip.retrieveOuterTripList", params, OuterTrip.class);
	}

	public List<OuterTrip> retrieveOuterTripError(Map<String, Object> params) {
		params.put("loginUserId", UserInfo.getLoginId());

		//20150610 권한 체크 없는 고로 일단 하드코딩
		String adminYn = (String)params.get("adminYn");

		if(adminYn.equals("Y")){
			params.remove("loginUserId");
		}

		return dao.queryForList("OuterTrip.retrieveOuterTripError", params, OuterTrip.class);
	}


	/**
	 * 그룹 코드목록을 조회하기위한 메소드
	 *
	 *
	 * @param input
	 * @return mData (그룹 코드 목록)
	 * @throws Exception
	 */
	public List<Nat> retrieveNatList(Map<String, Object> params) {
		return dao.queryForList("OuterTrip.retrieveNatList", params, Nat.class);
	}

	/**
	 * 그룹 코드목록을 조회하기위한 메소드
	 *
	 *
	 * @param input
	 * @return mData (그룹 코드 목록)
	 * @throws Exception
	 */
	public List<TripRef> retrieveTripRef(Map<String, Object> params) {
		return dao.queryForList("OuterTrip.retrieveTripRef", params, TripRef.class);
	}

	/**
	 * 그룹 코드목록을 조회하기위한 메소드
	 *
	 *
	 * @param input
	 * @return mData (그룹 코드 목록)
	 * @throws Exception
	 */
	public List<OuterTrip> retrieveAirFareList(Map<String, Object> params) {
		return dao.queryForList("OuterTrip.retrieveAirFareList", params, OuterTrip.class);
	}


	/**
	 * 해외 출장을 저장한다.
	 * @param map
	 * @throws BusinessException
	 */
	public void saveOuterTripDraft(Map<String, Object> map) throws BusinessException{
		map.put("fstRegUserId", UserInfo.getLoginId());
		map.put("fnlEditUserId", UserInfo.getLoginId());
		dao.update("OuterTrip.saveOuterTripDraft", map);
	}

	/**
	 * 출장자 정보 입력
	 * @param gridData
	 * @param docNo
	 */
	public void saveOuterTripUserList(GridData<HashMap> gridData, final String docNo) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {

			@Override
			public void insert(HashMap record, int index) {
				record.put("docNo", docNo);
				record.put("fstRegUserId", UserInfo.getLoginId());
				record.put("fnlEditUserId", UserInfo.getLoginId());

				System.out.println("insert 실행");
				dao.update("OuterTrip.saveOuterTripUserList", record);
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
	 * 비자 정보 입력
	 * @param gridData
	 * @param docNo
	 */
	public void saveOuterTripVisaInfoList(GridData<HashMap> gridData, final String docNo) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {

			@Override
			public void insert(HashMap record, int index) {
				record.put("docNo", docNo);
				record.put("fstRegUserId", UserInfo.getLoginId());
				record.put("fnlEditUserId", UserInfo.getLoginId());

				System.out.println("insert 실행");
				dao.update("OuterTrip.saveOuterTripVisaInfoList", record);
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
	 * 방문지 정보 입력
	 * @param gridData
	 * @param docNo
	 */
	public void saveOuterTripCityList(GridData<HashMap> gridData, final String docNo) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {

			@Override
			public void insert(HashMap record, int index) {
				record.put("docNo", docNo);
				record.put("fstRegUserId", UserInfo.getLoginId());
				record.put("fnlEditUserId", UserInfo.getLoginId());

				System.out.println("insert 실행");
				dao.update("OuterTrip.saveOuterTripCityList", record);
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
	 * 체제비 정보 입력
	 * @param gridData
	 * @param docNo
	 */
	public void saveOuterTripExpnList(GridData<HashMap> gridData, final String docNo) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {

			@Override
			public void insert(HashMap record, int index) {
				record.put("docNo", docNo);
				record.put("fstRegUserId", UserInfo.getLoginId());
				record.put("fnlEditUserId", UserInfo.getLoginId());

				System.out.println("insert 실행");
				dao.update("OuterTrip.saveOuterTripExpnList", record);
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

	public Map<String, Object> getCmasId(Map<String, Object> map) throws BusinessException{

		Map<String, Object> rslMap = null;
		rslMap = dao.queryForMap("OuterTrip.getMaxCmasId", map);
		if(rslMap == null || rslMap.equals("null")){
			rslMap = map;
		}
		return dao.queryForMap("OuterTrip.getCmasId", rslMap);
	}

	public void insertOuterTripTempDoc(Map<String, Object> map) throws BusinessException{
		map.put("fstRegUserId", UserInfo.getLoginId());
		map.put("fnlEditUserId", UserInfo.getLoginId());
		dao.update("OuterTrip.insertOuterTripTempDoc", map);
	}

	/**
	 * 문서 조회
	 * @param params
	 * @return
	 */
	public List<OuterTrip> retrieveOuterTripViewDoc(Map<String, Object> params) {
		return dao.queryForList("OuterTrip.retrieveOuterTripViewDoc", params, OuterTrip.class);
	}

	/**
	 * 바로콘 위험국가 메일 내용 조회
	 * @param params
	 * @return
	 */
	public List<Nat> retrieveSpgEmailMgm(Map<String, String> params) {
		return dao.queryForList("OuterTrip.retrieveSpgEmailMgm", params, Nat.class);
	}



	/**
	 * 추가 출장자 조회
	 * @param params
	 * @return
	 */
	public List<ExtnlPer> retrieveOuterTripExtnlPerViewDoc(Map<String, Object> params) {
		return dao.queryForList("OuterTrip.retrieveOuterTripExtnlPerViewDoc", params, ExtnlPer.class);
	}

	/**
	 * 체제비 조회
	 * @param params
	 * @return
	 */
	public List<Expn> retrieveOuterTripExpnViewDoc(Map<String, Object> params) {
		return dao.queryForList("OuterTrip.retrieveOuterTripExpnViewDoc", params, Expn.class);
	}

	/**
	 * 비자 방문지 조회
	 * @param params
	 * @return
	 */
	public List<Visa> retrieveOuterTripVisaViewDoc(Map<String, Object> params) {
		return dao.queryForList("OuterTrip.retrieveOuterTripVisaViewDoc", params, Visa.class);
	}

	public int updateOuterTripInfo(Map<String, Object> params) {
		int result = dao.update("OuterTrip.updateOuterTripInfo", params);
		System.out.println("result : " + result);
		return result;
	}

	public int updateAdjustOuterTripInfo(Map<String, Object> params) {
		int result = dao.update("OuterTrip.updateAdjustOuterTripInfo", params);
		System.out.println("result : " + result);
		return result;
	}

	public void deleteSignInfoOuterTrip(Map<String, Object> params) {
		dao.update("OuterTrip.deleteSignInfoOuterTrip", params);
	}

	public void saveSignInfoOuterTrip(GridData<HashMap> gridData, final String docNo, final String signId, final String dutyCls) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {

			@Override
			public void insert(HashMap record, int index) {
				record.put("docNo", docNo);
				record.put("signId", signId);
				record.put("dutyCls", dutyCls);
				record.put("signDt", "N");

				record.put("fstRegUserId", UserInfo.getLoginId());
				record.put("fnlEditUserId", UserInfo.getLoginId());

				dao.update("OuterTrip.insertSignInfoOuterTrip", record);
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
	 * 정산서 결재선 처리 콜백용
	 * @param gridData
	 * @param docNo
	 * @param signId
	 * @param dutyCls
	 */
	public void saveSignInfoOuterTrip2(GridData<HashMap> gridData, final String docNo, final String signId, final String dutyCls) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {

			@Override
			public void insert(HashMap record, int index) {
				record.put("docNo", docNo);
				record.put("signId", signId);
				record.put("dutyCls", dutyCls);

				record.put("fstRegUserId", UserInfo.getLoginId());
				record.put("fnlEditUserId", UserInfo.getLoginId());

				dao.update("OuterTrip.insertSignInfoOuterTrip", record);
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

	public void saveOuterTripDocSave(Map<String, Object> map) throws BusinessException{
		map.put("fstRegUserId", UserInfo.getLoginId());
		map.put("fnlEditUserId", UserInfo.getLoginId());
		dao.update("OuterTrip.saveOuterTripDocSave", map);
	}

	public void updateOuterTripDocSave(Map<String, Object> map) throws BusinessException{
		map.put("fnlEditUserId", UserInfo.getLoginId());
		dao.update("OuterTrip.updateOuterTripDocSave", map);
	}

	public void updateOuterTripDocSave2(Map<String, Object> map) throws BusinessException{
		map.put("fnlEditUserId", UserInfo.getLoginId());
		dao.update("OuterTrip.updateOuterTripDocSave2", map);
	}

	public void deleteOuterTripUserList(Map<String, Object> map) throws BusinessException{
		dao.update("OuterTrip.deleteOuterTripUserList", map);
	}

	public void deleteOuterTripVisaInfoList(Map<String, Object> map) throws BusinessException{
		dao.update("OuterTrip.deleteOuterTripVisaInfoList", map);
	}

	public void deleteOuterTripExpnList(Map<String, Object> map) throws BusinessException{
		dao.update("OuterTrip.deleteOuterTripExpnList", map);
	}

	public Map<String, Object> retrieveSavedDocInfo(Map<String, Object> params) {
		return dao.queryForMap("OuterTrip.retrieveSavedDocInfo", params);
	}

	public void saveCmasDocUpdateFail(Map<String, Object> params) {
		dao.update("OuterTrip.saveCmasDocUpdateFail", params);
	}

	public void saveAdjustCmasDocUpdateFail(Map<String, Object> params) {
		dao.update("OuterTrip.saveAdjustCmasDocUpdateFail", params);
	}


	public List<Sign> retrieveSignInfo2(Map<String, Object> params) {
		return dao.queryForList("OuterTrip.retrieveSignInfo2", params, Sign.class);
	}

	public List<Sign> retrieveSignInfo3(Map<String, Object> params) {
		return dao.queryForList("OuterTrip.retrieveSignInfo3", params, Sign.class);
	}

	public List<Sign> retrieveSignInfo4(Map<String, Object> params) {
		return dao.queryForList("OuterTrip.retrieveSignInfo4", params, Sign.class);
	}

	public List<Sign> retrieveSignInfo(Map<String, Object> params) {
		return dao.queryForList("OuterTrip.retrieveSignInfo", params, Sign.class);
	}

	public void updateOuterTripDraft(Map<String, Object> map) {
		dao.update("OuterTrip.updateOuterTripDraft", map);
	}

	public int updateSignDt(Map<String, Object> params) {
		int result = dao.update("OuterTrip.updateSignDt", params);
		System.out.println("result : " + result);
		return result;
	}

	public int updateAdjustSignDt(Map<String, Object> params) {
		int result = dao.update("OuterTrip.updateAdjustSignDt", params);
		System.out.println("result : " + result);
		return result;
	}

	public void saveAdjustDoc(Map<String, Object> map) throws BusinessException{
		map.put("fstRegUserId", UserInfo.getLoginId());
		map.put("fnlEditUserId", UserInfo.getLoginId());
		dao.update("OuterTrip.saveAdjustDoc", map);
	}

	public void updateAdjustDoc(Map<String, Object> map) throws BusinessException{
		map.put("fnlEditUserId", UserInfo.getLoginId());
		dao.update("OuterTrip.updateAdjustDoc", map);
	}

	public void updateCancelAdjustDoc(Map<String, Object> map) throws BusinessException{
		map.put("fnlEditUserId", UserInfo.getLoginId());
		dao.update("OuterTrip.updateCancelAdjustDoc", map);
	}

	public Map<String, Object> getAdjustSavedDoc(Map<String, Object> params) {
		return dao.queryForMap("OuterTrip.getAdjustSavedDoc", params);
	}

	public void updateAdjustDraftDoc(Map<String, Object> map) throws BusinessException{
		map.put("fnlEditUserId", UserInfo.getLoginId());
		dao.update("OuterTrip.updateAdjustDraftDoc", map);
	}

	public void updateOuterTripAirConf(Map<String, Object> map) throws BusinessException{
		dao.update("OuterTrip.updateOuterTripAirConf", map);
	}

	public void deleteOuterTripByDocNo(Map<String, Object> map) throws BusinessException{
		dao.update("OuterTrip.deleteOuterTripByDocNo", map);
	}

	public void deleteOuterTripAdjustByDocNo(Map<String, Object> map) throws BusinessException{
		dao.update("OuterTrip.deleteOuterTripAdjustByDocNo", map);
	}

	public void saveSignInfoOuterTripFromList(List listData, final String docNo, final String signId, final String dutyCls){
		for(int i = 0; i < listData.size(); i++){

			HashMap record = (HashMap)listData.get(i);

			record.put("docNo", docNo);
			record.put("signId", signId);
			record.put("dutyCls", dutyCls);

			record.put("fstRegUserId", UserInfo.getLoginId());
			record.put("fnlEditUserId", UserInfo.getLoginId());

			dao.update("OuterTrip.insertSignInfoOuterTrip", record);
		}
	}

	public void saveSignInfoOuterTripFromList2(List listData, final String docNo, final String signId, final String dutyCls){
		for(int i = 0; i < listData.size(); i++){

			HashMap record = (HashMap)listData.get(i);

			record.put("docNo", docNo);
			record.put("signId", signId);
			record.put("dutyCls", dutyCls);

			record.put("fstRegUserId", UserInfo.getLoginId());
			record.put("fnlEditUserId", UserInfo.getLoginId());

			dao.update("OuterTrip.insertSignInfoOuterTrip", record);
		}
	}


	/**
	 * 경영관리팀 협조를 결재선에 삽입하기 위해 CSYS에 등록된 경영관리팀 담당자를 가져온다.
	 * CSYS 에 등록된 첫번째 사용자만 가져온다. (반드시 한명만 등록해놓을것)
	 * RO_CMAS_OT_004
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> retrieveCMASOT004(Map<String, Object> params) {
		return dao.queryForMapList("OuterTrip.retrieveCMASOT004", params);
	}

	/**
	 * 해외출장 협의결재자를(현재는 글로벌지원팀장) 삽입하기 위함.(반드시 한명만 등록해놓을것)
	 * RO_CMAS_OT_006
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> retrieveCMASOT006(Map<String, Object> params) {
		return dao.queryForMapList("OuterTrip.retrieveCMASOT006", params);
	}

	public List<Map<String, Object>> retrieveCMASOT008(Map<String, Object> params) {
		return dao.queryForMapList("OuterTrip.retrieveCMASOT008", params);
	}

	public List<Map<String, Object>> retrieveCMASOTList(Map<String, Object> params) {
		return dao.queryForMapList("OuterTrip.retrieveCMASOTList", params);
	}

	public void updateOuterTripGHRCmt(Map<String, Object> map) throws BusinessException{
		dao.update("OuterTrip.updateOuterTripGHRCmt", map);
	}

	public Map<String, Object> retrievelegacyInfo(Map<String, Object> params){
		return sgnsDB.queryForMap("OuterTrip.retrievelegacyInfo", params);
	}

	public Map<String, Object> getUserRealPositCd(Map<String, Object> params) {
		return dao.queryForMap("OuterTrip.getUserRealPositCd", params);
	}

	public List<Map<String, Object>> retrieveRejCont(Map<String, Object> params) {
		return dao.queryForMapList("OuterTrip.retrieveRejCont", params);
	}

	public void updateErrMsg(Map<String, Object> map) throws BusinessException{
		dao.update("OuterTrip.updateErrMsg", map);
	}

	public Map<String, Object> retrieveErrMsg(Map<String, Object> params) {
		return dao.queryForMap("OuterTrip.retrieveErrMsg", params);
	}

	public void updateErrMsgAdjust(Map<String, Object> map) throws BusinessException{
		dao.update("OuterTrip.updateErrMsgAdjust", map);
	}

	public Map<String, Object> retrieveErrMsgAdjust(Map<String, Object> params) {
		return dao.queryForMap("OuterTrip.retrieveErrMsgAdjust", params);
	}

	public void updateAdjustGHRCmt(Map<String, Object> map) throws BusinessException{
		dao.update("OuterTrip.updateAdjustGHRCmt", map);
	}

	public void updateSgnsReject(Map<String, Object> map) throws BusinessException{
		dao.update("OuterTrip.updateSgnsReject", map);
	}


}

