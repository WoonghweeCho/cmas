package com.dwenc.cmas.common.accessLog.controller;

import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.accessLog.service.MaxCallCntLog;
import com.dwenc.cmas.common.utils.RequestUtil;

import docfbaro.common.StringUtil;
import docfbaro.common.WebContext;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : AccessLogController
 * 설    명 :  AccessLog 처리를 위한 controller 클래스
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
@Controller
@RequestMapping("/co/common/accessLog/*")
public class MaxCallCntLogController {

    @Autowired
    @Qualifier("appProperties")
    private Properties appProperties;

    /**
     * <pre>
     *  화면에서 최대 transaction 수를 넘는 처리가 있을 경우 로깅한다.
     * </pre>
     * @param request
     * @param response
     */
    @RequestMapping("writeMaxCallCnt.*")
    public void writeExcessAccessLog(MciRequest request, MciResponse response){
        Map<String,Object> inputData = RequestUtil.getParam(request, WebContext.getRequest());

        if(StringUtil.getText(appProperties.get("dwe.accessLog.maxCallCntLog.useYn")).equals("true")){
            String log = StringUtil.getText(inputData.get("menuCd")); // menuCd, menuName, rowcount
            log += "^t" + StringUtil.getText(inputData.get("menuName"));
            log += "^t" + StringUtil.getText(inputData.get("rowcount"));
            log += "^t" + StringUtil.getText(inputData.get("svcAct"));
            MaxCallCntLog.getInstance().write( log );
        }
    }
}