package com.dwenc.cmas.common.accessLog.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.accessLog.service.AccessLog;
import com.dwenc.cmas.common.accessLog.service.AccessLogService;
import com.dwenc.cmas.common.logger.service.AccessLogger;
import com.dwenc.cmas.common.utils.RequestUtil;

import docfbaro.common.Constants;
import docfbaro.common.MapUtil;
import docfbaro.common.StringUtil;
import docfbaro.common.WebContext;
import docfbaro.iam.UserInfo;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : Controller에 접근하는 Request를 로깅하기 위한 Controller Class
 *
 * 프로그램 : AccessLogController
 * 설    명 :  AccessLog 처리를 위한 controller 클래스
 * 작 성 자 : 홍두희
 * 작성일자 : 2012-12-05
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
public class AccessLogController {

    @Autowired
    @Qualifier("appProperties")
    private Properties appProperties;

    @Autowired
    private AccessLogService service;


    //@Autowired
    //private ExtSrc extSrc;

    /**
     * <pre>
     *  Xplatform의 메뉴에서 발생된 Request에 대한 Access Log를 AccessLog Class를 이용해서
     *  파일에 저장한다.
     * </pre>
     * @param request
     * @param response
     */
    @RequestMapping("writeAccessLog.*")
    public void writeAccessLog(MciRequest request, MciResponse response){
//    	Map<String,Object> inputData = RequestUtil.getParam(request, WebContext.getRequest());
//    	String fv_AccessLogData = "";
//        if(Constants.accessLogUseYn.equals("true")){ //Access Log 사용유무
//            if(StringUtil.getText(inputData.get("gap")).equals("")) {
//                String menuCd = StringUtil.getText(inputData.get("menuCd"));
//                WebContext.getRequest().getSession().setAttribute("menuCd", menuCd);
//                UserInfo.getUserInfo().setMenuCd(menuCd);
//
//                fv_AccessLogData = AccessLog.getInstance().writeLog( "menu_Open" );
//            } else {
//                float frslt = ( Integer.parseInt(StringUtil.getText(inputData.get("gap"))) )/1000.0f;
//                String strStartTime = StringUtil.getText(inputData.get("startTime"));
//                String svcAct = StringUtil.getText(inputData.get("svcAct"));
//                svcAct = svcAct.substring(0, svcAct.indexOf("$") + 1) + StringUtil.getText(inputData.get("rowcount"));
//                fv_AccessLogData = AccessLog.getInstance().write( svcAct, strStartTime, Float.toString(frslt), "ui" );
//            }
//        }
//        response.addParam("fv_AccessLogData", fv_AccessLogData);
    }

    /**
     * <pre>
     *  Xplatform의 메뉴에서 발생된 Request에 대한 Access Log를 AccessLog Class를 이용해서
     *  파일에 저장한다.
     * </pre>
     * @param request
     * @param response
     */
    @RequestMapping("writeAccessLogForMenu.*")
    public void writeAccessLogForMenu(MciRequest request, MciResponse response){
//        writeAccessLog(request, response);
    	Map<String, Object> param = request.getParam();
    	AccessLogger.get().writeMenu(param.get("menuCd"), param.get("menuUrl"));
    }

    /**
     * <pre>
     *  AccessLog Class를 이용해서 사용자 Session에 logStart를 true로 설정한다.
     * </pre>
     * @param request
     * @param response
     */
    @RequestMapping("setLogStart.*")
    public void SetRecord(MciRequest request, MciResponse response){
        AccessLog.setLogStart();
    }

    /**
     * <pre>
     *  AccessLog Class를 이용해서 사용자 Session에 logStart를 false로 설정하고
     * </pre>
     * @param request
     * @param response
     */
    @RequestMapping("setLogEnd.*")
    public void PrintScript(MciRequest request, MciResponse response){
        List list = AccessLog.getLogList();
        for(int i=0;i<list.size();i++) {
            AccessLog.getInstance().write(list.get(i).toString());
        }
        AccessLog.setLogEnd();

        /**
         * 로깅 대상에 대한 로깅정보가 저장되는 List를 생성한다.
         */
       /* ExtInfo extInfo = ExtInfoLog.getInstance().get();
        log(extInfo.getUiXplList());
        log(extInfo.getCtrlSrcList());
        log(extInfo.getTabList());*/

        /**
         * 로깅된 정보를 초기화(Null) 한다.
         */
        //ExtInfoLog.getInstance().refresh();
   }

    /**
     * 로그정보를 CO_SRC_ANALY Table에 저장하고
     * System.out 으로 출력한다.
     *
     * @param mData
     */
    public void log(List<Map<String, Object>> mData){
        MapUtil.fillColValue(mData, "inputCls", "L");
        //CO_SRC_ANALY Table에 로그정보를 저장한다.
        //extSrc.insertExtSrc(mData);

        //로그정보를 System.out 으로 출력한다.
        Iterator dataIterator = mData.iterator();
        while(dataIterator.hasNext()){
           String log = "";
            Map<String, Object> data = (Map<String, Object>)dataIterator.next();
            Set dataKeySet = data.keySet();
            Iterator dataIterator2 = dataKeySet.iterator();
            while(dataIterator2.hasNext()){
                String dataKey = dataIterator2.next().toString();
                log += StringUtil.getText(data.get(dataKey)) + ",";
            }
            log = log.substring(0, log.length() - 1);
            System.out.println(log);
        }
    }

    /***
     * 해당 일자의 로그 파일을 반환한다.
     * @param request
     * @param response
     */
    @RequestMapping("downloadAccessLog.*")
    public void downloadAccessLog(MciRequest request, MciResponse response){

    	Map<String,Object> inputData = RequestUtil.getParam(request, WebContext.getRequest());
    	String result = AccessLog.downloadAccessLog(inputData, appProperties);
    	response.setViewName(result);
    }
}