package com.dwenc.cmas.common.security.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dwenc.cmas.common.login.service.LoginService;
import com.dwenc.cmas.common.utils.CollectionUtil;

import docfbaro.common.ClassUtils;
import docfbaro.common.DateUtil;
import docfbaro.common.ServletUtil;
import docfbaro.common.StringUtil;
import docfbaro.iam.authentication.UserDefinition;
import docfbaro.iam.handler.service.AdditionalUserInfoService;


/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통모듈
 * 프로그램 : AdditionalUserInfoServiceImpl
 * 설    명 : 추가세션정보를 추가하기 위한
 *           AdditionalUserInfoService 의 바로콘프로젝트 구현클래스
 * 작 성 자 : 이재열
 * 작성일자 : 2012-02-28
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2012-02-28    이재열   주석최추 작성
 * ---------------------------------------------------------------
 * </pre>
 *
 * @version 1.0
 */
public class AdditionalUserInfoServiceImpl implements AdditionalUserInfoService {

	/**
	 * log 처리를 위한 변수 선언
	 */
	private final static Logger logger = LoggerFactory.getLogger(AdditionalUserInfoServiceImpl.class);

	/**
	 * 로그인 관련 서비스
	 */
	@Autowired
	private LoginService loginService;

	/*
	 * (non-Javadoc)
	 * @see docfbaro.iam.handler.service.AdditionalUserInfoService#addUserInfo(javax.servlet.http.HttpServletRequest, docfbaro.iam.authentication.UserDefinition)
	 */
	public void addUserInfo(HttpServletRequest request, UserDefinition userInfo) {

		// 여기에 sso 처리를 넣어보자
		/*try {
			HttpServletResponse res = WebContext.getResponse();
			HttpServletRequest  req = WebContext.getRequest();
		    SSOConfig.request = req;
	        AuthCheck auth = new AuthCheck(req, res);
	        //String siteDNS = SSOConfig.siteDomain();
	        //String ssositeValue = "&" + SSOConfig.REQUESTSSOSITEPARAM + "=" + siteDNS;
	        //navigateUrl = SSOConfig.logoffPage() + "?" + SSOConfig.returnURLTagName() + "=" + Util.uRLEncode(auth.thisURL(), "UTF8") + ssositeValue;
	        AuthStatus status = auth.checkLogon();

	        if(status == AuthStatus.SSOFirstAccess)
	        {
	            //auth.trySSO();
	        	System.out.println("SSO first access ");
	        }
	        else if(status == AuthStatus.SSOSuccess)
	        {
	        	System.out.println("SSO success");
	        }
	        else if(status == AuthStatus.SSOFail)
	        {
				System.out.println("SSO Fail");
	        }
	        else
	        {
	            System.out.print("nothing");
	        }
	    }
	    catch(Exception ex)
	    {
	    	logger.trace("Exception catched during sso check "+ex.getStackTrace());
	    }*/

		userInfo.setAccessTm(DateUtil.getCurrentDateString("yyyyMMddHHmmss"));

		if (userInfo.getSource() == null) {
			userInfo.setSource(request.getHeader("referer"));
		}

		logger.debug("addUserInfo() sessionId 1 :" + request.getSession().getId());
		userInfo.setIpAddress(request.getRemoteAddr());
		userInfo.setUserId(userInfo.getUsername());
		Map<String, Object> data = CollectionUtil.getData(request);
		data.put("tUserId", userInfo.getUsername());
        data.put("tUserPwd", userInfo.getPassword());
		data.put("source", ServletUtil.getData(request, "source"));

        String loclCd = StringUtil.getText(request.getParameter("loclCd"));
        if(!loclCd.equals("")) {
            userInfo.setLoclCd(loclCd);
            data.put("loclCd", loclCd);
        }
        String oggCd = StringUtil.getText(request.getParameter("oggCd"));
        if(!oggCd.equals("")) {
            userInfo.setCurSite(new HashMap());
        }
		userInfo = loginService.memberLogin(data, userInfo);
		logger.debug("addUserInfo() loginService.memberLogin() after : " + userInfo.getUserId() + " : "+ userInfo);

		try {
			userInfo = loginService.makePriv(data, userInfo);

			logger.debug("addUserInfo() loginService.makePriv() after : " + userInfo.getUserId() + " : "+ userInfo);

			loginService.retrieveLoadAllInfo(data, userInfo);

			ClassUtils.cloneObject(userInfo, userInfo);
		} catch (Exception e) {
			logger.trace("DefaultPrivService.loadUserByUsername() : error occured - user authorization failed"+e.getStackTrace());
		}
		logger.debug("addUserInfo() sessionId 2 :" + request.getSession().getId());
	}
}