package com.dwenc.cmas.common.eacct.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.utils.RequestUtil;

import docfbaro.common.ObjUtil;
import docfbaro.common.WebContext;
import docfbaro.query.CommonDao;
import docfbaro.sua.exception.BusinessException;
import docfbaro.sua.mvc.MciRequest;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통모듈
 * 프로그램 : EacctService
 * 설    명 : 전자증빙 필수 증빙항목 검증을 위한 서비스 클래스
 * 작 성 자 : 변형구
 * 작성일자 : 2012-04-04
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2011-04-04   변형구    최초 작성
 * ---------------------------------------------------------------
 * </pre>
 *
 * @version 1.0
 */

@Service
public class EacctService {

	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(EacctService.class);

	/**
	 * getLogger
	 */
	public Logger getLogger() {
		return logger;
	}

	/**
	 * DB처리를 위한 공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao commonDao;



	/**
	 * <pre>
	 * 전표유형을 조회한다.retrieveEacctAttTmpl
	 * </pre>
	 *
	 * @param requestMap
	 * @return
	 */
	public List<Map<String, Object>> retrieveWorkCd(Map<String, Object> requestMap) {
		//return commonDao.queryForObject("eAcct.retrieveWorkCd", requestMap, String.class);
		return commonDao.queryForMapList("eAcct.retrieveWorkCd", requestMap);
	}

	/**
	 * <pre>
	 * 전표유형을 조회한다.retrieveEacctAttTmpl
	 * </pre>
	 *
	 * @param requestMap
	 * @return
	 */
	public List<Map<String, Object>> retrieveEacctAttTmpl(Map<String, Object> requestMap) {
		return commonDao.queryForMapList("eAcct.retrieveEacctAttTmpl", requestMap);
	}



	/**
	 * <pre>
	 * 전표유형을 조회한다.retrieveEacctAttTmpl
	 * </pre>
	 *
	 * @param requestMap
	 * @return
	 */
	public int retrieveEacctAtchCnt(Map<String, Object> requestMap) {
		return commonDao.queryForInt("eAcct.retrieveEacctAtchCnt", requestMap);
	}


	public boolean checkEacctAtch(MciRequest request) {

		Map<String, Object> inputData = RequestUtil.getParam(request, WebContext.getRequest());
		boolean blnDocChk = true;

		if ( ObjUtil.isNull(inputData.get("refKey")) && ObjUtil.isNull(inputData.get("mngNo")) ) {
    	   throw new BusinessException("co.err.sendRefKey");
		}

		 List<Map<String, Object>> mapWrkCd = retrieveWorkCd(inputData);

		if ( ObjUtil.isNull(mapWrkCd) || mapWrkCd.size() == 0 ) {
    	   throw new BusinessException("co.info.notFoundEAcct");
		}


		String strWrkCd = "";

		for ( int o = 0; o < mapWrkCd.size(); o++) {

			Map<String, Object> workCdMap = new HashMap<String, Object>();

			strWrkCd = mapWrkCd.get(o).get("workCd").toString();

			workCdMap.put("workCd", strWrkCd);
			List<Map<String, Object>> eAcctList = retrieveEacctAttTmpl(workCdMap);

			String strDocGrp = "";
			String strDocNm = "";

			for ( int i = 0; i < eAcctList.size(); i++ ) {

				Map<String, Object> attTmpl = eAcctList.get(i);

				if ( !ObjUtil.isNull(attTmpl.get("docGrp")) && attTmpl.get("docGrp").toString() != "" && strDocGrp.equals(attTmpl.get("docGrp").toString()) ) {
					if ( blnDocChk ) {
						continue;
					}
				}
				else {
					if ( !blnDocChk ) {
						throw new BusinessException("co.err.reqEacctAtch", new Object[] { strDocNm });
					}
					strDocGrp = ObjUtil.isNull(attTmpl.get("docGrp")) ? "" : attTmpl.get("docGrp").toString();
				}

				attTmpl.put("refKey", inputData.get("refKey"));
				attTmpl.put("mngNo", inputData.get("mngNo"));

				if ( retrieveEacctAtchCnt(attTmpl) > 0 ) {
					blnDocChk = true;
				}
				else {
					blnDocChk = false;
					strDocNm = attTmpl.get("docNm").toString();
				}
			}

			if (!blnDocChk) {
				throw new BusinessException("co.err.reqEacctAtch", new Object[] { strDocNm });
			}
		}
		/// 여기에는 증빙 첨부 여부를 판단할 예외로직이 있으면 추가한다. 없으면 하지 않는다.

		return blnDocChk;

	}





}
