package com.dwenc.cmas.common.code.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.code.CodeData;
import com.dwenc.cmas.common.code.service.CodeService;
import com.dwenc.cmas.common.utils.RequestUtil;

import docfbaro.common.DateUtil;
import docfbaro.common.ObjUtil;
import docfbaro.common.WebContext;
import docfbaro.iam.UserInfo;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : CodeController
 * 설    명 : 공통코드 처리를 위한 controller 클래스 1
 * 작 성 자 : 배태일
 * 작성일자 :
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 *
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */
@Controller
@RequestMapping("/co/common/code/*")
public class CodeController {

	@Autowired
	private CodeService service;

	@Autowired
	private CodeData codeData;

	/**
	 * <pre>
	 *  공통콤보리스트 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveCommCdComboList.*")
	public void retrieveCommCdComboList(MciRequest request, MciResponse response) {

		Map<String, Object> data = RequestUtil.getParam(request, WebContext.getRequest());
		// data.put("userId", UserInfo.getUserId());
		// data.put("today", DateUtil.getCurrentDateString());

		List<Map<String, Object>> mData;
		if (ObjUtil.isNull(data.get("highLevelGroup"))) {
			mData = codeData.codeList(data.get("queryCommGrpCd").toString());
		} else {
			data.put("commGrpCd", data.get("queryCommGrpCd"));
			mData = service.codeValueList(data);
		}

		List<Map<String, Object>> mData2 = service.retrieveCodeList(data);

		response.setMapList("output1", mData);
		response.setMapList("output2", mData2);
	}

	/**
	 * <pre>
	 *  공통 코드 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveCommCdList.*")
	public void retrieveCommCdList(MciRequest request, MciResponse response) {
		Map<String, Object> data = RequestUtil.getParam(request, WebContext.getRequest());
		data.put("userId", UserInfo.getUserId());
		data.put("today", DateUtil.getCurrentDateString());
		List<Map<String, Object>> mData = service.retrieveCodeList(data);
		response.setMapList("output1", mData);
	}

	/**
	 * <pre>
	 *  공통 코드 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveSingleCommCd.*")
	public void retrieveSingleCommCd(MciRequest request, MciResponse response) {
		Map<String, Object> data = RequestUtil.getParam(request, WebContext.getRequest());
		data.put("userId", UserInfo.getUserId());
		data.put("today", DateUtil.getCurrentDateString());
		List<Map<String, Object>> mData = service.retrieveCodeList(data);
		response.setMapList("output1", mData);
	}
}