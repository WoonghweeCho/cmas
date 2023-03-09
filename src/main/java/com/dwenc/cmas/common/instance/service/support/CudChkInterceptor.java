package com.dwenc.cmas.common.instance.service.support;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : CudChkInterceptor
 * 설    명 : 해외 현장 서버의 상태에 문제가 있을 경우 본사에서 입력처리하지 못하도록 제어하는 클래스
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
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jcf.iam.core.common.util.UserInfoHolder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dwenc.cmas.common.accessLog.service.AccessLog;
import com.dwenc.cmas.common.instance.service.InstcService;
import com.dwenc.cmas.common.sysMng.service.support.SysMngUtil;

import docfbaro.common.StringUtil;
import docfbaro.iam.UserInfo;
import docfbaro.iam.authentication.UserDefinition;

public class CudChkInterceptor extends HandlerInterceptorAdapter {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(CudChkInterceptor.class);

    @Autowired
    private InstcService service;

    @Autowired
    @Qualifier("appProperties")
    private Properties appProperties;

    @Autowired
    private InstcFactory instcFactory;

    @Autowired
    private CudChkFactory cudChkFactory;

    @Autowired
    private SysMngUtil sysMngUtil;

    @Autowired
    private MessageSourceAccessor accessor;

    /**
     * 해외 현장 서버의 상태에 문제가 있을 경우 본사에서 입력처리하지 못하도록 제어한다.
     */
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception{
        String cudChk = StringUtil.getText(appProperties.get("dwe.ogg.cudChk.useYn"));
        if(!cudChk.equals("true"))
            return true;

        if(StringUtil.getText(appProperties.getProperty("dwe.sysMng.log.manual.useYn")).equals("true")) {
            if(UserInfoHolder.getUserInfo(UserDefinition.class) == null) return true;
        }

        if(AccessLog.validLogStart() == false) return true;

        // check 할 url인지 확인 (저장 처리 인가?)
        String useYn = cudChkFactory.getCudChkByUrl(req.getRequestURI());
        if(useYn.equals("N"))
            return true;

        // 처리하고자 하는 현장이 해외인가?
        // 본사에서 현장쪽의 데이터를 수정하고자 할 때
        // 현장 쪽 서버의 health를 확인하여 문제가 있을 때에는
        // 본사에서 저장처리 하지 못하도록 제어함
        if(!UserInfo.getInstanceId().equals("100")){ // 본사가 아닐 경우에는 문제 없음
            return true;
        }

        // 해당 현장의 인스턴스가 무엇인가?
        String instcId = instcFactory.getInstcIdFromOggCd();
        if(!instcId.equals("")) {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("instcId", instcId);
            // 인스턴스의 상태는 문제없는가? (WAS, OGG, ECM)
            String rslt = service.checkInstc(data);
            if(!rslt.equals("")){
                logger.error("instcId 장애!! : " + instcId + " / " + rslt);
                Map<String, Object> inputData = new HashMap<String, Object>();
                inputData.put("instcId", instcId);
                inputData.put("msg", accessor.getMessage("co.err.sysmng", new Object[]{rslt}));
                sysMngUtil.sysMng(inputData);
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView modelAndView)
            throws Exception{}

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex)
            throws Exception{}
}
