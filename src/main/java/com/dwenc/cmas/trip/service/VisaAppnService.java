package com.dwenc.cmas.trip.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jcf.data.GridData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.trip.domain.VisaAppn;
import com.dwenc.cmas.common.user.domain.Sign;
import com.dwenc.cmas.common.utils.MailUtil;
import com.dwenc.cmas.id.domain.DaewooEmp;

import docfbaro.common.StringUtil;
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
public class VisaAppnService {

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


	@Autowired
	private MailUtil mailUtil;

	/**
	 * 그룹 코드목록을 조회하기위한 메소드
	 *
	 *
	 * @param input
	 * @return mData (그룹 코드 목록)
	 * @throws Exception
	 */
	public List<VisaAppn> retrieveVisaAppnList(Map<String, Object> params) {
		params.put("loginUserId", UserInfo.getLoginId());

		String adminYn = (String)params.get("adminYn");

		if(adminYn.equals("Y")){
			params.remove("loginUserId");
		}

		return dao.queryForList("VisaAppn.retrieveVisaAppnList", params, VisaAppn.class);
	}

	public Map<String, Object> getDocNo(Map<String, Object> map) throws BusinessException{

		Map<String, Object> rslMap = null;
		rslMap = dao.queryForMap("VisaAppn.getMaxDocNo", map);
		if(rslMap == null || rslMap.equals("null")){
			rslMap = map;
		}
		return dao.queryForMap("VisaAppn.getDocNo", rslMap);
	}

	public Map<String, Object> retrieveGeneralInfo(Map<String, Object> params) {
		return dao.queryForMap("VisaAppn.retrieveGeneralInfo", params);
	}

	/**
	 * VISA 담당자 조회
	 * RO_CMAS_OT_007
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> retrieveCMASOT007(Map<String, Object> params) {
		return dao.queryForMapList("VisaAppn.retrieveCMASOT007", params);
	}

	public void insertVisaAppnTempDoc(Map<String, Object> map) throws BusinessException{
		map.put("fstRegUserId", UserInfo.getLoginId());
		map.put("fnlEditUserId", UserInfo.getLoginId());
		dao.update("VisaAppn.insertVisaAppnTempDoc", map);
	}

	public void saveVisaAppn(Map<String, Object> map) throws BusinessException{
		map.put("fstRegUserId", UserInfo.getLoginId());
		map.put("fnlEditUserId", UserInfo.getLoginId());
		dao.update("VisaAppn.saveVisaAppn", map);
	}

	//결재 후 Action
	public Map<String, Object> sign(Map<String, Object> map, Map<String, Object> data) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(map.get("signStsCd").equals("S03")){	//결재완료시 메일 발송
				sendMailToVisaAdmin(data);
			}
		} catch (Exception e) {
			result.put("TYPE", "FAIL");
			result.put("MSG", "VISA : 메일발송 실패");
			e.printStackTrace();
			return result;
		}

		try {
			dao.update("SignUser.updateSign", map);
		} catch (Exception e){
			result.put("TYPE", "FAIL");
			result.put("MSG", "VISA : 결재선정보 업데이트 실패");
			e.printStackTrace();
			return result;
		}

		try {
			map.put("fnlEditUserId", data.get("signUserId"));
			dao.update("VisaAppn.Approval", map);
		} catch (Exception e) {
			result.put("TYPE", "FAIL");
			result.put("MSG", "VISA : 문서정보 업데이트 실패");
			e.printStackTrace();
			return result;
		}

		result.put("TYPE", "SUCCESS");
		result.put("MSG", "VISA : 성공");
		return result;
	}

	public int updateRejectComment(Map<String, Object> params) {
		int result = dao.update("VisaAppn.updateRejectComment", params);
		System.out.println("result : " + result);
		return result;
	}

	public void deleteVisaAppn(Map<String, Object> map) throws BusinessException{
		map.put("fnlEditUserId", UserInfo.getUserId());
		dao.update("VisaAppn.deleteVisaAppn", map);
	}


	public Map<String, Object> retrieveSavedDocInfo(Map<String, Object> params) {
		return dao.queryForMap("VisaAppn.retrieveSavedDocInfo", params);
	}

	public void deleteVisaAppnByDocNo(Map<String, Object> map) throws BusinessException{
		dao.update("VisaAppn.deleteVisaAppnByDocNo", map);
	}

	public void deleteVisaAppnDtlByDocNo(Map<String, Object> map) throws BusinessException{
		dao.update("VisaAppn.deleteVisaAppnDtlByDocNo", map);
	}

	public Map<String, Object> retrieveErrMsg(Map<String, Object> params) {
		return dao.queryForMap("VisaAppn.retrieveErrMsg", params);
	}

	public void visaSubmitNotify(Map<String, Object> map) throws BusinessException{
		map.put("fnlEditUserId", UserInfo.getLoginId());
		dao.update("VisaAppn.visaSubmitNotify", map);
	}

	public void visaIssueNotify(Map<String, Object> map) throws BusinessException{
		map.put("fnlEditUserId", UserInfo.getLoginId());
		dao.update("VisaAppn.visaIssueNotify", map);
	}

	private void sendMailToVisaAdmin(Map<String, Object> data) {
		try {

			String mailTo = dao.queryForObject("VisaAppn.retrieveVisaAdminEmail", "", String.class);

			Map<String, Object> mail = new HashMap<String, Object>();
			mail.put("fromMailName", "비자시스템관리자");
			mail.put("mailSubject", "[비자 신청] 결재완료");
			mail.put("fromMailId", "cmasvisaadmin@daewooenc.com");
			mail.put("mailId", mailTo);

			System.out.println("수신자: "+mailTo);

			String htmlBody = data.get("signDocTitle").toString() +"비자 팀장 결재 완료<br>";

			mail.put("htmlBody", htmlBody);

			mailUtil.sendMail(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}