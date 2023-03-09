package com.dwenc.cmas.common.login.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jcf.iam.core.common.util.UserInfoHolder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.security.service.AdditionalUserInfoServiceImpl;
import com.nets.sso.agent.AuthCheck;
import com.nets.sso.agent.AuthStatus;
import com.nets.sso.agent.SSOConfig;

import docfbaro.common.StringUtil;
import docfbaro.common.WebContext;
import docfbaro.iam.authentication.UserDefinition;
import docfbaro.query.CommonDao;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분    : 공통 - 내부결재
 * 프로그램   : ServletSSOService
 * 설      명 : 리모트로 호출되는 서블릿의 SSO 인증과 연동, SSO의 인증에 대한 검증과 결재에 필요한 세션정보 생성을 담당한다.
 * 작 성 자   : 변형구
 * 작성일자   : 2011-12-07
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2011-12-07             최초 작성
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */
@Service
public class ServletSSOService {

	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(ServletSSOService.class);


    @Autowired
	private UserDetailsService userDetailsService;

    @Autowired
    private AdditionalUserInfoServiceImpl auis;

	@Autowired
	private AuthenticationManager authenticationManager;

	/**
	 * DB처리를 위한  공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao commonDao;


	public void checkSSOAuthentication() throws Exception {

		String userId = "";
		// 여기에 sso 처리를 넣어보자

		HttpServletResponse res = WebContext.getResponse();
		HttpServletRequest  req = WebContext.getRequest();
		/**
		 * Cookie 출동 방지 체크 -> Cookie 사용자 정보
		 */
    	Cookie[] cookies = req.getCookies();
    	Map<String, Object> map = new HashMap();
    	if(cookies != null){
    		for(int i = 0 ; i < cookies.length ; i++){
    			map.put(cookies[i].getName(), cookies[i].getValue());
    		}
    	}
    	Map<String, Object> iMap = new HashMap();
    	String cookieId = StringUtil.getText(map.get("JSESSIONID"));
    	String cookieUserId = StringUtil.getText(map.get("userId"));
    	String netsCookieUserId = "";

		//if ( !(req.getSession().getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY) != null) ) {
			try {
			    SSOConfig.request = req;
		        AuthCheck auth = new AuthCheck(req, res);
		        AuthStatus status = auth.checkLogon();
		        if(status == AuthStatus.SSOSuccess)
		        {
		        	iMap.put("message", "if(status == AuthStatus.SSOSuccess)");
		        	System.out.println("SSO success");
		            userId = auth.getSSODomainCookieValue("loginid");
		            netsCookieUserId = auth.getSSODomainCookieValue("loginid");
		        }
		        else
		        {
		        	iMap.put("message", "SSO CHECK FAIL");
					System.out.println("SSO CHECK FAIL ");
					throw new Exception();
		        }
		    }
		    catch(Exception ex)
		    {
		    	logger.trace("Exception catched during sso check "+ex.getStackTrace());
		    	throw ex;
		    }



			UserDefinition uInfo = (docfbaro.iam.authentication.UserDefinition)userDetailsService.loadUserByUsername(userId);

			String name = uInfo.getUsername();
			UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(uInfo.getUsername(), uInfo.getPassword() );

			//newAuth.setDetails(authentication.getDetails());
			newAuth.setDetails(new WebAuthenticationDetails(req));

			Authentication authenticatedUser = authenticationManager.authenticate(newAuth);

			SecurityContextHolder.getContext().setAuthentication(authenticatedUser);

			SecurityContext securityContext = new SecurityContextImpl();
			securityContext.setAuthentication(authenticatedUser);
			SecurityContextHolder.setContext(securityContext);

			req.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);

			/**
			 * Cookie 출동 방지 체크 -> Session 사용자 정보
			 */
	    	String sessionId = "";
	    	String sessionUserId = "";
	    	HttpSession session = WebContext.getSession();
            sessionId = session.getId();
            sessionUserId = uInfo.getUsername();


            iMap.put("type", "ServletSSOService");
            iMap.put("cookieId", cookieId);
            iMap.put("cookieUserId", userId);
            iMap.put("sessionId", sessionId);
            iMap.put("sessionUserId", sessionUserId);
            iMap.put("referer", req.getHeader("referer"));

//            try{
//            	commonDao.update("Log.insertLog", iMap);
//            }catch(Exception e){
//            	e.printStackTrace();
//            }
			auis.addUserInfo(req, (UserDefinition)UserInfoHolder.getUserInfo(UserDefinition.class));
		//}
	}


	public void checkAuthentication(String userId, String password) throws Exception {
		try {
			// 여기에 sso 처리를 넣어보자
			HttpServletResponse res = WebContext.getResponse();
			HttpServletRequest  req = WebContext.getRequest();
			//if ( !(req.getSession().getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY) != null) ) {

			UserDefinition uInfo = (docfbaro.iam.authentication.UserDefinition)userDetailsService.loadUserByUsername(userId);

			UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(userId, password );

			//newAuth.setDetails(authentication.getDetails());
			newAuth.setDetails(new WebAuthenticationDetails(req));

			Authentication authenticatedUser = authenticationManager.authenticate(newAuth);

			SecurityContextHolder.getContext().setAuthentication(authenticatedUser);

			SecurityContext securityContext = new SecurityContextImpl();
			securityContext.setAuthentication(authenticatedUser);
			SecurityContextHolder.setContext(securityContext);
			req.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);

			auis.addUserInfo(req, (UserDefinition)UserInfoHolder.getUserInfo(UserDefinition.class));
		}
		catch (Exception ex) {
			logger.trace("Exception catched during auth check "+ex.getStackTrace());
	    	throw ex;
		}
	}

	public void insertLog(Map<String, Object> map) {
//		try{
//        	commonDao.update("Log.insertLog", map);
//        }catch(Exception e){
//        	e.printStackTrace();
//        }
	}
}
