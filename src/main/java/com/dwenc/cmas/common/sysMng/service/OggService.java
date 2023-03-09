package com.dwenc.cmas.common.sysMng.service;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통모듈
 * 프로그램 : OggService
 * 설    명 : Ogg 정보 관리하는 서비스
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

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.sysMng.service.support.SysMngUtil;

import docfbaro.common.DateUtil;
import docfbaro.query.CommonDao;

@Service
public class OggService {

    private static Logger logger = LoggerFactory.getLogger(OggService.class);

    @Autowired
    @Qualifier("mainDB")
    private CommonDao dao;

    @Autowired
    private SysMngUtil sysMngUtil;

    /**
     * 오류 발생시 메시지, sms 발송
     */
    public String sendOggErr(Map<String, Object> input){
        String bChk = "N";
        try{
            //인스턴스별 와스 상태 UPDATE
            Map<String, Object> inputData = new HashMap<String, Object>();
            inputData.put("bChk", bChk);
            dao.update("instc.updateOggOkYn", inputData);
            sysMngUtil.sysMng(input);
            logger.debug("OggService called at " + DateUtil.getCurrentDateString());
        }catch(Exception e){
            logger.debug(this.getClass().getName() + "." + "execBatch()" + "=>" + e.getMessage());
        }
        return bChk;
    }

}
