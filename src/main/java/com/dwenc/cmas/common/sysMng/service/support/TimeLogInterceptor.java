package com.dwenc.cmas.common.sysMng.service.support;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통모듈
 * 프로그램 : TimeLogInterceptor
 * 설    명 : HandlerInterceptorAdapter를 상속해서 시간로그에 대한 preHandle, postHandle 처리를 하는
 *         Utility Class
 * 작 성 자 :
 * 작성일자 : 2011-12-01
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2011-12-01             최초 작성
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dwenc.cmas.common.accessLog.service.AccessLog;

import docfbaro.common.DateUtil;
import docfbaro.common.MenuFactory;
import docfbaro.common.ObjUtil;
import docfbaro.common.SpringUtil;
import docfbaro.common.StringUtil;
import docfbaro.common.WebContext;
import docfbaro.common.WebContextFilter;
import docfbaro.iam.UserInfo;

public class TimeLogInterceptor extends HandlerInterceptorAdapter {

    private String strStartTime = "";

    private String menuId = "";
    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(WebContextFilter.class);

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        if(AccessLog.validLogStart() == false) return true;

        if(req != null) {
            // contoller 시작 기록
            String guid = req.getParameter("guid");
            logger.info("TimeLogInterceptor guid : " + guid);
            if(!ObjUtil.isNull(req.getSession().getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY))) {
                if(guid != null && guid.indexOf("-") > -1) {
                    menuId = guid.substring(0, guid.indexOf("-"));
                    if(UserInfo.getMenuId() != null && !UserInfo.getMenuId().equals(menuId) && !"undefined".equals(menuId)) {
                        UserInfo.getUserInfo().setMenuId(menuId);
                        MenuFactory menuFactory = (MenuFactory) SpringUtil.getBean(WebContext.getRequest(), "menuFactory");
                        String menuNm = StringUtil.getText(menuFactory.getMenuInfo("ko_KR", menuId).get("menuNm"));
                        UserInfo.getUserInfo().setMenuNm(menuNm);
                        WebContext.getRequest().getSession().setAttribute("menuId", menuId);
                    }
                    UserInfo.getUserInfo().setGuid(guid);
                }
            }
            strStartTime = AccessLog.getTime();
        }
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView modelAndView) throws Exception {
        if(AccessLog.validLogStart() == false) return;

        // contoller 종료 시간
        if(req != null) {
            String strEndTime = AccessLog.getTime();
            float fGap = DateUtil.getTimeGap(strStartTime, strEndTime);
            if(!ObjUtil.isNull(req.getSession().getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY))
                    && (req.getServletPath().indexOf("writeAccessLog.co") == -1)
                    && (req.getServletPath().indexOf("writeAccessLogForMenu.co") == -1)
                    && UserInfo.getGuid() != null
                    && fGap >= 0.0) {
                AccessLog.getInstance().write( req.getServletPath(), "", Float.toString(fGap), "cmd" );

                /*MenuFactory menuFactory = (MenuFactory) SpringUtil.getBean(WebContext.getRequest(), "menuFactory");

                ExtInfo extinfo = new ExtInfo();
                Map<String, Object> info = new HashMap<String, Object>();
                String reqUri = req.getServletPath();
                if(reqUri.startsWith("/")) {
                    info.put("dutySysCd", reqUri.substring(1, 3));
                    reqUri = reqUri.substring(1, reqUri.length());
                } else {
                    info.put("dutySysCd", reqUri.substring(0, 2));
                    reqUri = reqUri.substring(0, reqUri.length());
                }
                if(reqUri.indexOf(".") > -1) reqUri = reqUri.substring(0, reqUri.lastIndexOf(".")) + ".xpl";
                info.put("sysCd", Constants.sysCd);
                info.put("srcCls", "Controller");
                info.put("serviceId", handler.getClass().getSimpleName());
                info.put("analy1", handler.getClass().getSimpleName()); // menuFactory.getMenuCd(menuId)
                info.put("analy2", reqUri);
                String method = "";
                if(reqUri.indexOf("/") > -1) {
                    reqUri = reqUri.substring(reqUri.lastIndexOf("/") + 1, reqUri.indexOf("."));
                    Method methods[] = handler.getClass().getMethods();
                    for(int i = 0; i < methods.length; i++){
                        if(methods[i].getName().equals(reqUri)) {
                            method = reqUri;
                            break;
                        }
                    }
                }
                info.put("analy3", method);
                ExtInfoLog.getInstance().setCtrlSrcList(info);*/ //2013.02.20 변형구
            }
        }
	}

	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex)	throws Exception {
	}
}
