package com.dwenc.cmas.common.eacct.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.eacct.service.EacctService;

import docfbaro.sua.exception.BusinessException;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;


/** * <pre>
* ---------------------------------------------------------------
* 업무구분 : 공통
* 프로그램 :
* 설 명    : 전자증빙의 필수 증빙 첨부항목 검증을 위한 컨트롤러 클래스
* 작 성 자 : 변형구
* 작성일자 : 2012-04-04
* 수정이력
* ---------------------------------------------------------------
* 수정일 이 름 사유
* ---------------------------------------------------------------
* 2012-04-04 변형구 최초 작성
* ---------------------------------------------------------------
* </pre>
* @version 1.0 */

@Controller
@RequestMapping("/co/common/eacct/*")
public class EacctController {

	/**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(EacctController.class);

    @Autowired
    private EacctService service;


    /**
	  * <pre>
	  * 첨부파일 목록 조회 컨트롤러
	  * </pre>
	  * @param request
	  * @param response
	  * */
   @RequestMapping("checkEacctAtch.*")
   public void checkEacctAtch(MciRequest request, MciResponse response) {
       /*Map<String, Object> inputData = RequestUtil.getParam(request, WebContext.getRequest());

       if ( ObjUtil.isNull(inputData.get("refKey"))) {
    	   throw new BusinessException("co.err.sendRefKey");
	   }

       String strWrkCd = service.retrieveWorkCd(inputData);

       if (ObjUtil.isNull(strWrkCd)) {
    	   throw new BusinessException("co.info.notFoundEAcct");
       }


       Map<String, Object> workCdMap = new HashMap<String, Object>();
       workCdMap.put("workCd", strWrkCd);
       List<Map<String, Object>> eAcctList = service.retrieveEacctAttTmpl(workCdMap);
       String strDocGrp = "";
       boolean blnDocChk = true;

       for ( int i = 0; i < eAcctList.size(); i++ ) {

    	   Map<String, Object> attTmpl = eAcctList.get(i);

    	   if ( !ObjUtil.isNull(attTmpl.get("docGrp")) && attTmpl.get("docGrp").toString() != "" && strDocGrp.equals(attTmpl.get("docGrp").toString()) ) {
    		   if ( blnDocChk ) {
    			   continue;
    		   }
    	   }
    	   else {
    		   if ( !blnDocChk ) {
    			   throw new BusinessException("co.err.reqEacctAtch", new Object[] { attTmpl.get("docNm").toString() });
    		   }
    		   strDocGrp = attTmpl.get("docGrp").toString();
    	   }

    	   attTmpl.put("refKey", inputData.get("refKey").toString());

    	   if ( service.retrieveEacctAtchCnt(attTmpl) > 0 ) {
    		   blnDocChk = true;
    	   }
    	   else {
    		   blnDocChk = false;
    	   }
       }*/

	   	boolean blnDocChk = false;

	   	try {
	   		blnDocChk = service.checkEacctAtch(request);
	   	}
	   	catch (BusinessException e) {
	   		throw e;
	   	}

       	String  fv_eAcctYn = "";
       	if ( blnDocChk ) {
    	   	fv_eAcctYn = "Y";
       	}
       	else {
    	   	fv_eAcctYn = "N";
       	}

       	response.addParam("fv_eAcctYn", fv_eAcctYn);

   	}

   /**
	  * <pre>
	  * 첨부파일 목록 조회 컨트롤러
	  * </pre>
	  * @param request
	  * @param response
	  * */
 @RequestMapping("checkEacctAtchNoMsg.*")
 public void checkEacctAtchNoMsg(MciRequest request, MciResponse response) {
	   	boolean blnDocChk = false;
	   	String  fv_eAcctYn = "";
	   	try {
	   		blnDocChk = service.checkEacctAtch(request);
	   	}
	   	catch (BusinessException e) {
	   		fv_eAcctYn = "N";
	   		response.addParam("fv_eAcctYn", fv_eAcctYn);
	   		throw e;
	   	}

     	if ( blnDocChk ) {
  	   		fv_eAcctYn = "Y";
     	}
     	else {
  	   		fv_eAcctYn = "N";
     	}
     	response.addParam("fv_eAcctYn", fv_eAcctYn);
 	}

}
