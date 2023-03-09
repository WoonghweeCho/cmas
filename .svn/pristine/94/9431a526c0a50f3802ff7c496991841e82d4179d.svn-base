package com.dwenc.cmas.common.session.controller;

import java.util.List;
import java.util.Map;

import jcf.data.GridData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.session.domain.CoMenuBkmrk;
import com.dwenc.cmas.common.session.service.SessionService;
import com.dwenc.cmas.common.utils.RequestUtil;

import docfbaro.common.Constants;
import docfbaro.common.StringUtil;
import docfbaro.common.WebContext;
import docfbaro.iam.UserInfo;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : SessionController
 * 설    명 : 로그인 사용자의 자료 조회 controller 클래스
 * 작 성 자 : 홍두희
 * 작성일자 : 2012-12-05
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2011-12-07             최초 작성
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */
@Controller
@RequestMapping("/co/common/session/*")
public class SessionController {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(SessionController.class);

	@Autowired
	private SessionService sessionService;

	/**
	 * <pre>
	 *  retrieveLoadAllInfo
	 * </pre>
	 * @param
	 * @param
	 */
	@RequestMapping("retrieveLoadAllInfo.*")
	public void retrieveLoadAllInfo(MciRequest request, MciResponse response)  {
	    Map<String, Object> input = RequestUtil.getParam(request, WebContext.getRequest());
	    if(input.get("orgCd") == null || input.get("orgCd").equals(""))
	    	input.put("orgCd", StringUtil.getText(UserInfo.getOrgInfo().get("orgCd"))) ;
		//권한변경 및 로긴시 권한,현장 조회범위등 관리
		List<Map<String, Object>> authList = sessionService.changePriv(input);

		if(input.get("orgCd") != null) {
			sessionService.changeSession(input);
		}

        // 최초 로딩 시에 올려야 할 것들만 기술함.
        String initInfo = "EtcInfo,UserNm,CurSite,LoclCd,Conf,Passwd,UserId,CurPriv";
        input.put("initInfo", initInfo);

        // 관리자 권한을 가지고 잇는 시스템 코드 세션과 global dataset 에 추가 하기 위해 2012-05-24
        List<Map<String,Object>> sysList = sessionService.retrieveAdminSysCd(input);
        input.put("sysList", sysList);

		List<Map<String, Object>> sessionList = retrieveSession(input);
        List<Map<String, Object>> menuList = sessionService.retrieveMenu(input);
//		if(!StringUtil.getText(input.get("source")).equals("link")) {
	        List<CoMenuBkmrk> bkmrkList = retrieveCoMenuBkmrkList(input);// 즐겨찾기
	        response.setList("bkmrk", bkmrkList, CoMenuBkmrk.class);// 즐겨찾기
//		}
		String sysCd = Constants.sysCd;
		input.put("sysCd", sysCd);
		List<Map<String, Object>> privTpList = sessionService.retrievePrivTpList(input);
		response.setList("privTp", privTpList);

		response.setList("auth", authList);
		response.setList("session", sessionList);
		response.setList("menu", menuList);
		response.setMapList("adminsyscd", sysList);
	}

    /**
     * <pre>
     *  retrieveSession
     * </pre>
     * @param
     * @param
     */
    @RequestMapping("retrieveSession.*")
    public void retrieveSession(MciRequest request, MciResponse response)  {
        List<Map<String, Object>> sessionList = retrieveSession(request.getParam());
        response.setList("session", sessionList);
    }

	/**
	 * <pre>
	 * 즐겨찾기자료
	 * </pre>
	 * @param inputData
	 * @return
	 */
	private List<CoMenuBkmrk> retrieveCoMenuBkmrkList(Map<String, Object> inputData) {
		inputData.put("userId",UserInfo.getUserId());
		String sysCd = Constants.sysCd;
		inputData.put("sysCd", sysCd);
	    return sessionService.retrieveCoMenuBkmrkList(inputData);
	}
	/**
	 * <pre>
	 * 조직 변경시 권한변경
	 * </pre>
	 * @param inputData
	 * @return
	 */
	@RequestMapping("changePrivM.*")
	public void changePrivM(MciRequest request, MciResponse response)  {
	    Map<String, Object> input = RequestUtil.getParam(request, WebContext.getRequest());
	    sessionService.changePriv(input);
	    sessionService.changeSession(input);

        // 관리자 권한을 가지고 잇는 시스템 코드 세션과 global dataset 에 추가 하기 위해 2012-05-24
        List<Map<String,Object>> sysList = sessionService.retrieveAdminSysCd(input);
        input.put("sysList", sysList);
		//사용자별 세션정보
		List<Map<String, Object>> sessionList = retrieveSession(input);
		//사용자별 탑메뉴
		List<Map<String, Object>> menuList = sessionService.retrieveMenu(input);

		response.setList("session", sessionList);
		response.setList("menu", menuList);
	}

	public List<Map<String, Object>> retrieveSession(Map<String, Object> inputData)  {
		return sessionService.retrieveSessionList(inputData);
	}

	/**
	 * <pre>
	 *  CO메뉴즐겨찾기 GRID DATA 추가/삭제/수정 한다.
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveCoMenuBkmrkList.*")
	public void saveCoMenuBkmrkList(MciRequest request, MciResponse response){
		GridData<CoMenuBkmrk> gridData  = request.getGridData("input1", CoMenuBkmrk.class);
		sessionService.saveCoMenuBkmrkList(gridData);
	    Map<String, Object> requestMap = RequestUtil.getParam(request, WebContext.getRequest());
		String sysCd = Constants.sysCd;
	    requestMap.put("sysCd", sysCd);
	    requestMap.put("userId", UserInfo.getUserId());
	    List<CoMenuBkmrk> coMenuBkmrkList = sessionService.retrieveCoMenuBkmrkList(requestMap);
		response.setList("output1", coMenuBkmrkList, CoMenuBkmrk.class);
	}
}