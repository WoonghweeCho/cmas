package com.dwenc.cmas.common.privmenu.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.login.domain.LoginInfo;
import com.dwenc.cmas.common.utils.RequestUtil;

import docfbaro.common.WebContext;
import docfbaro.iam.UserInfo;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

/**

 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 사용자별 권한
 * 프로그램 : PrivMenu
 * 설    명 : 사용자별 권한관리를 위한 controller 클래스
 * 작 성 자 : 박광섭
 * 작성일자 :
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2012-02-22             최초 작성
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */
@Controller
@RequestMapping("co/common/privmenu/*")
public class PrivMenuController {
    /**
    *
    * @param request
    * @param response
    */
    @RequestMapping(value = { "retrieveMenuPriv.*" })
    public void retrieveMenuPriv(MciRequest request, MciResponse response) {
        Map<String, Object> inputData = RequestUtil.getParam(request, WebContext.getRequest());
        String menuId = inputData.get("menuId").toString();
        UserInfo.getUserInfo().setMenuId(menuId);
        String menuCd = UserInfo.getMenuCd();
        String authString = ((LoginInfo)UserInfo.getLoginInfo()).getUserPrivByMenuCd(menuCd);
        response.addParam("fv_ButtonPriv", authString);
    }
}
