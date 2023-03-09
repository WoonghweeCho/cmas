package com.dwenc.cmas.common.user.controller;

import java.util.List;
import java.util.Map;

import jcf.data.GridData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.user.domain.CoUserInfo;
import com.dwenc.cmas.common.user.service.SignUserService;
import com.dwenc.cmas.common.utils.CookieUtils;
import com.dwenc.cmas.common.utils.RequestUtil;
import com.dwenc.cmas.common.user.domain.Sign;

import docfbaro.common.WebContext;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : SignUserController
 * 설    명 : 결재사용자 처리를 위한 controller 클래스
 * 작 성 자 :
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
@RequestMapping("/co/common/user/*")
public class SignUserController {

	@Autowired
	private SignUserService service;

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param request
	 * @param response
	 */

	@RequestMapping("retrieveSignln.*")
	public void retrieveUserList(MciRequest request, MciResponse response){
	    Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		response.setList("ds_Signln", service.retrieveSignln(map));
	}

	@RequestMapping("retrieveSignUser.*")
	public void retrieveSignUserId(MciRequest request, MciResponse response){
	    Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		response.setList("gds_SignUser", service.retrieveSignUser(map));
	}

	/**
	 * <pre>
	 * 2014-09-15 전결규정 기반, 결재선 조회
	 *
	 * </pre>
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveSignlnForExcluRegl.*")
	public void retrieveSignlnForExcluRegl(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		map.put("loclCd", CookieUtils.readCookieValue(WebContext.getRequest(), "loclCd"));

		Map<String, Object> resMap = service.retrieveSignlnForExcluRegl(map);
		response.setList("ds_SignlnForExcluRegl", (List<Sign>)resMap.get("SIGN_LIST"), Sign.class);
	}

	/**
	 * <pre>
	 * 2014-07-30 작성팀 변경을 위해 조직 변경시 직책, 직위를 조회한다.
	 *
	 * </pre>
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveUserEtcInfo.*")
	public void retrieveUserEtcInfo(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> list = service.retrieveUserEtcInfo(map);
		response.setMap("ds_UserEtcInfo", list);
	}

	@RequestMapping("saveSign.*")
	public void draftSign(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<Sign> gridSign = request.getGridData("signln", Sign.class); 						// 결재선 정보

		service.insertSign(gridSign);
	}


	@RequestMapping("retrieveChiefYn.*")
	public void retrieveChiefYn(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> list = service.retrieveChiefYn(map);
		response.setMap("output1", list);
	}
}
