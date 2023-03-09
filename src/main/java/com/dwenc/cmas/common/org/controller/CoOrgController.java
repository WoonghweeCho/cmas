package com.dwenc.cmas.common.org.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.org.domain.OrgInfo;
import com.dwenc.cmas.common.org.service.CoOrgService;
import com.dwenc.cmas.common.utils.RequestUtil;

import docfbaro.common.WebContext;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;
/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통
 * 설    명 : 부서 조회 관련 공통모듈
 * 작 성 자 : 이재열
 * 작성일자 : 2011-12-08
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일 이 름 사유
 * ---------------------------------------------------------------
 * 2011-12-08 이재열 최초 작성
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */
@Controller
@RequestMapping("/co/common/org/*")
public class CoOrgController {

	/**
	 * Service 클래스 이용하기위한 변수
	 */
	@Autowired
	private CoOrgService service;

	/**
	 * <pre>
	 * 본부코드/조직트리조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveOnload.*")
	public void retrieveOnload(MciRequest request, MciResponse response){
        Map<String, Object> param = RequestUtil.getParam(request, WebContext.getRequest());
        List<OrgInfo> returnData = service.retrieveHqList(param);
        response.setList("output1", returnData);//본부
        returnData = service.retrieveOrgTreeList(param);
        response.setList("output2", returnData);//조직트리
    }

	/**
	 * <pre>
	 * 조직코드조회
	 * </pre>
	 * @param request
	 * @param response
	 */
    @RequestMapping("retrieveOrgList.*")
    public void retrieveOrgList(MciRequest request, MciResponse response){
        Map<String, Object> param = RequestUtil.getParam(request, WebContext.getRequest());
        List<OrgInfo> returnData = service.retrieveOrgList(param);
        response.setList("output1", returnData);
    }

	/**
	 * <pre>
	 * 조직코드조회
	 * </pre>
	 * @param request
	 * @param response
	 */
    @RequestMapping("retrieveOrgsList.*")
    public void retrieveOrgsList(MciRequest request, MciResponse response){
        Map<String, Object> param = RequestUtil.getParam(request, WebContext.getRequest());
        List<OrgInfo> returnData = service.retrieveOrgsList(param);
        response.setList("output1", returnData);
    }
}