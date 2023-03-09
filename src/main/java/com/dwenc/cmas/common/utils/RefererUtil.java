package com.dwenc.cmas.common.utils;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import docfbaro.common.StringUtil;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통
 * 프로그램 : RefererUtil
 * 설      명 : Referer 접근통제 처리를 위한 Utility Class
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
@Service
public class RefererUtil {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(RefererUtil.class);

    @Autowired
    @Qualifier("appProperties")
    private Properties appProperties;

    /**
     * <pre>
     * 체크에서 제외할 URL 목록을 로딩.
     * </pre>
     * @param url
     */
    public boolean checkFilterUrl(String url){
        String useYn = StringUtil.getText(ConfigUtil.getProperties(appProperties).get("dwe.referer.useYn"));
        if(!useYn.equals("true")) return true;

        if (url.equals("")) return true;
        if(url.indexOf("!") > 0)
            url = url.substring(url.indexOf("!") + 1, url.length());
        if(!url.startsWith("/"))
            url = "/" + url;
        String include = StringUtil.getText(ConfigUtil.getProperties(appProperties).get("dwe.referer.list"));
        String includeArry[] = include.split(",");
        boolean bChk = false;
        if(!include.trim().equals("")){
            if(!include.trim().equals("*")){
                for(int i = 0; i < includeArry.length; i++){
                    if(url.indexOf(StringUtil.Trim(includeArry[i])) > -1){
                        bChk = true; // O
                        break;
                    }
                }
            }else{
                bChk = true; // O
            }
        }
        return bChk; // X
    }
}
