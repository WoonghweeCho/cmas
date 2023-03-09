package com.dwenc.cmas.common.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.user.domain.CoUserInfo;
import com.dwenc.cmas.common.user.service.CoUserService;
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
 * 프로그램 : CoUserController
 * 설    명 : CO사용자 처리를 위한 controller 클래스
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
public class CoUserController {

	@Autowired
	private CoUserService service;

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveUserList.*")
	public void retrieveUserList(MciRequest request, MciResponse response) {
		Map<String,Object> param = RequestUtil.getParam(request, WebContext.getRequest());
		List<CoUserInfo> userList = service.retrieveUserList(param);
		response.setList("output1", userList,CoUserInfo.class);
	}

	/**
	 * <pre>
	 *  조건에 의한 조직를  조회한다.
	 * </pre>
	 */
	@RequestMapping("retrieveSignOrgMapList.*")
	public void retrieveSignOrgMapList(MciRequest request, MciResponse response){
	    Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

	    map.put("loclCd", CookieUtils.readCookieValue(WebContext.getRequest(), "loclCd"));

		List<Map<String, Object>> signOrgList = service.retrieveSignOrgMapList(map);

		response.setMapList("ds_SignOrg", signOrgList);
	}

	/**
	 * <pre>
	 *  조건에 의한 직원을  조회한다.
	 * </pre>
	 */
	@RequestMapping("retrieveSignUserList.*")
	public void retrieveSignUserList(MciRequest request, MciResponse response){
	    Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

	    map.put("loclCd", CookieUtils.readCookieValue(WebContext.getRequest(), "loclCd"));

		List<Sign> signUserList = service.retrieveSignUserList(map);

		response.setList("ds_SignUser", signUserList);
	}
}
