package com.dwenc.cmas.common.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonEncoding;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : AuthenticationErrorProcessingFilter
 * 설    명 : AJAX 호출에 대한 권한 처리
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
public class AuthenticationErrorProcessingFilter implements Filter {

    // 기본 Ajax 요청 Contents Type
    private static final String DEFAULT_AJAX_CONTENT_TYPE = "application/ajax";

    // 기본 인코딩
    private static final JsonEncoding DEFAULT_AJAX_ENCODING = JsonEncoding.UTF8;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;

        try {
            chain.doFilter(request, response);
        } catch (AccessDeniedException e) {
            if(httpRequest.getRequestURI().endsWith(".xpl") && !httpResponse.isCommitted()) {
                // Ajax 요청에 따른 적절한 응답 메시지 생성
                httpRequest.setAttribute(WebAttributes.ACCESS_DENIED_403, e);
                httpResponse.addHeader("Pragma", "no-cache");
                httpResponse.addHeader("Cache-Control", "no-cache, no-store, max-age=0");
                httpResponse.addDateHeader("Expires", 1L);
                httpResponse.setContentType(DEFAULT_AJAX_CONTENT_TYPE);
                httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                httpResponse.setCharacterEncoding(DEFAULT_AJAX_ENCODING.getJavaName());
                httpResponse.getWriter().println("co.err.noPriv");
                httpResponse.getWriter().flush();
            } else {
                throw new AccessDeniedException(e.getMessage(), e);
            }
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }
}

