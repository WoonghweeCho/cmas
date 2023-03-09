package com.dwenc.cmas.common.login.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.login.service.LoginService;
import com.dwenc.cmas.common.util.service.UtilService;
import com.dwenc.cmas.common.utils.RequestUtil;

import docfbaro.common.StringUtil;
import docfbaro.common.WebContext;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

/**
 * @author
 * 로그인 처리를 위한 콘트롤러
 *
 */
@Controller
@RequestMapping("/co/common/login/*")
public class LoginController {

	@Autowired
	private UtilService service;

    @Autowired
    private LoginService loginService;

    @Autowired
	private MessageSourceAccessor messageSourceAccessor;

    /**
     * <PRE>
     * DB 조회를 통해 특정 값을 조회
     * </PRE>
     * @author
     */
    @RequestMapping("existUserId.*")
    public void retrieveSingleData(MciRequest request, MciResponse response) {
    	Map<String,Object> paramMap = request.get("params", HashMap.class);
        String msg = service.retrieveSingleData(paramMap);
        if(msg == null) {
            Object[] arguments = null;
            msg = messageSourceAccessor.getMessage("co.err.noUserId", arguments);
        } else {
            msg = "";
        }
        response.addSuccessMessage(msg);
    }

    /**
     * <pre>
     *  logout
     * </pre>
     * @param
     * @param
     */
    @RequestMapping("logout.*")
    public void logout(MciRequest request, MciResponse response)  {
        Map<String, Object> input = RequestUtil.getParam(request, WebContext.getRequest());
        loginService.logOut(input);
    }

    /**
     * <PRE>
     * 본사 / 현장 포탈용
     * 특정 현장에서 접근하는 로그인 페이지에 전달해야 하는 변수 처리용
     * http://cmasdev.daewooenc.com/cmas/co/common/login/loginPage.xpl?instcId=102
     * </PRE>
     * @author
     * @return
     * @return
     */
    @RequestMapping("loginPageSvc.*")
    public void loginPageSvc(MciRequest request, MciResponse response) {
        Map<String,Object> paramMap = RequestUtil.getParam(request, WebContext.getRequest());
        Map<String,Object> page = new HashMap<String, Object>();
        page.put("loginPage", "/common/jsp/comm/login/SsoLoginXpl.jsp");//LoginXpl.jsp");//

        page = loginService.getRSAKey(page);
        response.addParam("publicKeyModulus", StringUtil.getText(page.get("publicKeyModulus")));
        response.addParam("publicKeyExponent", StringUtil.getText(page.get("publicKeyExponent")));
        response.setViewName(StringUtil.getText(page.get("loginPage")));
    }

    /**
     * <PRE>
     * </PRE>
     * @author
     * @return
     * @return
     */
    @RequestMapping("loginRSAKey.*")
    public void loginRSAKey(MciRequest request, MciResponse response) {
        Map<String,Object> input = RequestUtil.getParam(request, WebContext.getRequest());
        input = loginService.getRSAKey(input);
        String msg = StringUtil.getText(input.get("publicKeyModulus")) + "|" + StringUtil.getText(input.get("publicKeyExponent"));
        response.addSuccessMessage(msg);
    }
}
