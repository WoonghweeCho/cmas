<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap,
                                java.util.Map,
                                java.util.ArrayList,
                                docfbaro.iam.UserInfo,
                                docfbaro.iam.authentication.UserDefinition,
                                jcf.iam.core.common.util.UserInfoHolder,
                                docfbaro.common.WebContext,
                                com.dwenc.cmas.common.utils.ConfigUtil,
                                docfbaro.common.ServletUtil,
                                java.util.Properties,
                                docfbaro.common.ObjUtil,
                                docfbaro.common.StringUtil,
                                javax.servlet.http.Cookie,
                                java.net.URLDecoder,
                                com.dwenc.cmas.common.utils.RefererUtil,
                                com.nets.sso.agent.*,
                                java.util.ResourceBundle,
                                com.dwenc.cmas.common.login.service.ServletSSOService,
                                java.util.Enumeration"%>
<%@ page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@ page import="org.springframework.web.context.support.SpringBeanAutowiringSupport"%>

<%!
    public void jspInit()
    {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
        getServletContext());
    }

    @Autowired
    private ServletSSOService service;
%>

<%
	Map<String, Object> iMap = new HashMap();
    try
    {

    	/**
    	* WebContext HttpRequest 에서 Cookie 값 추출
    	*/
        Cookie[] cookies = request.getCookies();
        Map<String, Object> map = new HashMap();
        if(cookies != null){
                for(int i = 0 ; i < cookies.length ; i++){
                        map.put(cookies[i].getName(), cookies[i].getValue());
                }
        }

    	iMap.put("type", "netsSsocheck");
    	String cookieId = StringUtil.getText(map.get("JSESSIONID"));
    	iMap.put("cookieId", cookieId);

    	/**
    	* Session 에서 ID를 얻어온다.
    	*/


        String netsCookieUserId = "";
        String sessionUserId = "";
        String sessionId = "";

        SSOConfig.request = request;
        AuthCheck auth = new AuthCheck(request, response);
        AuthStatus status = auth.checkLogon(AuthCheckLevel.Medium);
        if(status == AuthStatus.SSOSuccess) {
                /**
                * Cookie userId
                */
                netsCookieUserId = StringUtil.getText(auth.getSSODomainCookieValue("loginid"));
                UserDefinition uInfo    = UserInfoHolder.getUserInfo(UserDefinition.class);
                iMap.put("cookieUserId", netsCookieUserId);
                iMap.put("referer", request.getHeader("referer"));

                /**
                 * Session JSESSION ID
                 */
                	if(WebContext.getSession() != null){
                		sessionId = WebContext.getSession().getId();
                		iMap.put("sessionId", sessionId);
                	}

                /**
                * Session userId
                */
                sessionUserId = StringUtil.getText(uInfo.getUsername());
                iMap.put("sessionUserId", sessionUserId);

                if ( !netsCookieUserId.equals(sessionUserId) ) {
                    iMap.put("message", "CookieUserId is not equal SessionUserId ");
                    /**
                    * NetSSO 인증 체크가 성공일 경우
                    * - Cookie UserId 와 Session UserId 가 다를 경우
                    *   -> Cookie UserId로 자동으로 로그인 시킨다.
                    */
                    service.insertLog(iMap);
%>
                    <script language="javascript">
	                    var valuePath = window.top == window ? window : window.top;
	                    var check = (window.top == window ? "window" : "window.top");
	                    valuePath.location.href = "${pageContext.request.contextPath}/common/jsp/comm/login/SsoOpenXplSvc.jsp";
                    </script>
<%
                }else{
            		iMap.put("message","CookieUserId is equal SessionUserId");
            		iMap.put("status", "status :"+status.toString());

            		/**
            		 * Header 값을 속성별로 순차적으로 넣는다.
            		 */

            		String reqHeader = "";

            		Enumeration em = request.getHeaderNames();
            		while(em.hasMoreElements()){
            			String hName = (String)em.nextElement();
            			String hValue = request.getHeader(hName);
            			reqHeader = hName + ":" + hValue + "/";
            		}

            		iMap.put("header", reqHeader);

            		service.insertLog(iMap);
                }
        } else {
	       /**
	        * Nets SSO 인증 체크가 실패일 경우 로그인 화면으로 분기한다.
	        */
	        iMap.put("message","SSO Fail :"+auth.errorNumber());
	        iMap.put("status", "status :"+status.toString());

	        /**
    		 * Header 값을 속성별로 순차적으로 넣는다.
    		 */

    		String reqHeader = "";

    		Enumeration em = request.getHeaderNames();
    		while(em.hasMoreElements()){
    			String hName = (String)em.nextElement();
    			String hValue = request.getHeader(hName);
    			reqHeader = hName + ":" + hValue + "/";
    		}

    		iMap.put("header", reqHeader);

	        service.insertLog(iMap);

%>
            <script language="javascript">
	            var valuePath = window.top == window ? window : window.top;
	            var check = (window.top == window ? "window" : "window.top");
	            valuePath.location.href = "${pageContext.request.contextPath}/index.jsp";
            </script>
<%

        }

    }
    catch(Exception ex)
    {
        response.getWriter().print(ex.getMessage());
    }
%>
