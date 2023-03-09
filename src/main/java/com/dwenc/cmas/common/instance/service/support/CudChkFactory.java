package com.dwenc.cmas.common.instance.service.support;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : CudChkFactory
 * 설    명 : 현장별 서비스를 점검할지를 메모리에서 관리하는 서비스
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

import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import docfbaro.common.StringUtil;

@Service
public class CudChkFactory implements Observer {

    private static final Logger logger = LoggerFactory.getLogger(CudChkFactory.class);

    @Autowired
    @Qualifier("appProperties")
    private Properties appProperties;

    private String urlArry[] = null;

    @PostConstruct
    public void init(){
        String urlPattern = StringUtil.getText(appProperties.get("dwe.ogg.cudChk.urlPattern"));
        urlArry = urlPattern.split(",");
    }

    /**
     * CudChkFactory에서 사용하는 Map을 초기화한다.
     */
    public void refresh(){
        synchronized(this){
            init();
        }
    }

    /**
     * <PRE>
     *  Instance 정보를 Refresh 한다.
     * </PRE>
     *
     * @param o
     * @param arg
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    public void update(Observable o, Object arg){
        this.refresh();
    }

    /**
     * <pre>
     *  URL을 통해 CUD Transaction 관련 요청인지 Check한다.
     * </pre>
     * @param request
     * @param response
     */
    public String getCudChkByUrl(String reqUrl){
        if(reqUrl.equals("")) return "N";
        for(int i=0;i<urlArry.length;i++) {
            String tmpJob = urlArry[i];
            tmpJob = tmpJob.replace("*", "");
            if(reqUrl.indexOf(tmpJob) > -1){
                return "Y";
            } else {
                return "N";
            }
        }
        return "N";
    }

}
