package com.dwenc.cmas.common.duty.dm.dmz.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.duty.dm.dmz.service.SignToPdfServcie;
import com.dwenc.cmas.common.utils.RequestUtil;
import com.dwenc.cmas.common.utils.StringUtil;

import docfbaro.common.WebContext;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : SignToPdfController
 * 설    명 : PDF변환 상태 처리를 위한 Service
 * 작 성 자 : 한지훈
 * 작성일자 : 2012-05-18
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2012-05-18            최초 작성
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */

@Controller
@RequestMapping("/co/common/sign/*")
//@RequestMapping("/co/duty/dmz/*")
public class SignToPdfController {

    @Autowired
    private SignToPdfServcie signToPdfServcie;

//	@Autowired
//	private PdfService pdfService;

    /***
     * PDF 서버에서 변환 완료 후 호출 됨, 파일 처리 및 TDMS API를 호출한다.
     * @param request
     * 		  1. 공통
     *          pdfChgId : PDF 변환 ID
     *          sysDiv : PDF 변환 시스템 구분(1 : 이파피루수, 2 : 폭싯)
     *        2. Only 폭식
     *		    squrvwMno, rvwFileSeq, progClscd, fileNm, ecmNo
     * @param response
     */
	@RequestMapping("/savePdfConvertResult.*")
	public void savePdfConvertResult(MciRequest request, MciResponse response) {

		Map<String, Object> retMap = new HashMap<String, Object>();
		response.setViewName("/common/jsp/common/json.jsp");

		List<String> list = new ArrayList<String>();
		try {

			Map<String, Object> inputData = RequestUtil.getParam(request, WebContext.getRequest());
			String sysDiv = StringUtil.nvl(inputData.get("sysDiv"));

			if(sysDiv.equals("1")) {
				signToPdfServcie.savePdfConvertResult(inputData);
			} else if(sysDiv.equals("2")){
				signToPdfServcie.savePdfConvertResultForOnlyTdms(inputData);

				/*
				inputData.put("outcomFileAtchId", fileAtchId);
				pdfService.outcomSquadFile(inputData);
				*/
			}

			list.add("true");
		} catch (Exception e) {
			e.printStackTrace();
			list.add("false");
		} finally {
			response.setList("json", list);
		}

	}
}
