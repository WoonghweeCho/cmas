package com.dwenc.cmas.common.sysMng.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.sysMng.service.MonitorService;
import com.dwenc.cmas.common.sysMng.service.OggService;
import com.dwenc.cmas.common.sysMng.service.WasService;
import com.dwenc.cmas.common.utils.RequestUtil;

import docfbaro.common.WebContext;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

/**
 * <pre>
 * --------------------------------------------------------------
 * 업무구분 : 공통
 * 프로그램 : SysMngController
 * 설 명 : 시스템 모니터링 관리
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
@RequestMapping("/co/common/sysmng/*")
public class SysMngController {

    @Autowired
    private MonitorService monitorService;

    @Autowired
    private WasService wasService;

    @Autowired
    private OggService oggService;

    private static Logger logger = LoggerFactory.getLogger(SysMngController.class);

    /**
     * <PRE>
     * 서버 기동시 상태 확인 및 로깅
     * </PRE>
     */
    @RequestMapping("checkAllWas.*")
    public void checkAllWas(MciRequest request, MciResponse response){
        Map<String, Object> data = RequestUtil.getParam(request, WebContext.getRequest());
        List<Map<String, Object>> mData = monitorService.checkAllWas(data);
        logger.debug(mData.toString());
    }

    /**
     * <PRE>
     * 특정 서버 기동시 상태 확인 및 로깅
     * </PRE>
     */
    @RequestMapping("checkWas.*")
    public void checkWas(MciRequest request, MciResponse response){
        Map<String, Object> data = RequestUtil.getParam(request, WebContext.getRequest());
        String bChk = wasService.execBatch(data);
        if(bChk.equals("N")) {
            logger.debug(bChk);
        }
    }

    /**
     * <PRE>
     * 특정 서버의 OGG 오류 리포트
     * </PRE>
     */
    @RequestMapping("sendOggErr.*")
    public void sendOggErr(MciRequest request, MciResponse response){
        Map<String, Object> data = RequestUtil.getParam(request, WebContext.getRequest());
        String bChk = oggService.sendOggErr(data);
        if(bChk.equals("N")) {
            logger.debug(bChk);
        }
    }

    /***
     * <PRE>
     * 모든 Site의 Instances(WAS, ECM, OGG)의 상태를 Check하여 업데이트 한다.
     * </PRE>
     * @param request
     * @param response
     */

    @RequestMapping("checkAllInstces.*")
    public void checkAllInstces(MciRequest request, MciResponse response){
    	Map<String, Object> data = RequestUtil.getParam(request, WebContext.getRequest());
    	monitorService.checkAllInstces(data);
    	response.setViewName("/common/jsp/dummy.jsp");
    }

    /***
     * <PRE>
     * WAS와 DB의 연결 상태를 check하여 정상여부(true/false)를 return 한다.
     * </PRE>
     * @param request
     * @param response
     */
    @RequestMapping("wasCheck.*")
    public void localWasCheck(MciRequest request, MciResponse response){

    	response.setViewName("/common/jsp/common/json.jsp");
    	Map<String, Object> data = RequestUtil.getParam(request, WebContext.getRequest());
    	List<String> list = new ArrayList<String>();
    	list.add(String.valueOf(monitorService.checkDBConnection(data)));

    	response.setList("json", list);
    }

    /***
     * <PRE>
     * OGG 연결 상태를 check하여 정상여부(true/false)를 return 한다.
     * </PRE>
     * @param request
     * @param response
     */
    @RequestMapping("oggCheck.*")
    public void localOggCheck(MciRequest request, MciResponse response){

    	response.setViewName("/common/jsp/common/json.jsp");
    	List<String> list = new ArrayList<String>();
    	list.add(String.valueOf(monitorService.localOggCheck()));

    	response.setList("json", list);
    }

    /*
    @RequestMapping("wasServerNameTest.*")
    public void wasServerNameTest(MciRequest request, MciResponse response) throws Exception {
    	Properties configProperties = ConfigUtil.getProperties(null);
    	String command = String.valueOf(configProperties.get("dwe.accessLog.hq.infix.command"));
    	String infix = AccessLogUtil.getLogFileInfix(command);

    	response.setViewName("/common/jsp/common/json.jsp");
    	List<String> list = new ArrayList<String>();
    	list.add(infix);
    	response.setList("json", list);
    }
    */
}
