package com.dwenc.cmas.common.instance.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.instance.service.support.InstcFactory;

import docfbaro.common.StringUtil;
import docfbaro.query.CommonDao;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : InstcService
 * 설    명 : 인스턴스 관리하기 위한 service 클래스
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
@Service
public class InstcService {

    private static Logger logger = LoggerFactory.getLogger(InstcService.class);

    @Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

    @Autowired
    private InstcFactory instcFactory;

    /**
     * Instance 목록 조회
     * @param parameter
     * @return
     */
    public List<Map<String, Object>> retrieveInstcList(Map<String, Object> parameter) {
        return dao.queryForMapList("instc.retrieveInstcList", parameter);
    }

    /**
     * Was Instance 조회
     * @param parameter
     * @return
     */
    public List<Map<String, Object>> retrieveInstcWas(Map<String, Object> parameter) {
        return dao.queryForMapList("instc.retrieveInstcWas", parameter);
    }

    /**
     * 현장서버별 Instance 목록 조회
     * @param parameter
     * @return
     */
    public List<Map<String, Object>> retrieveInstcSiteLst(Map<String, Object> parameter) {
        return dao.queryForMapList("instc.retrieveInstcSiteLst", parameter);
    }

    /**
     * 인스턴스 상태 점검
     * @param input
     * @return
     */
    public boolean checkInstcFromDB(Map<String, Object> input) {
        Map<String, Object> data = dao.queryForMap("instc.checkInstc", input);

        if(data.isEmpty()) {
            logger.debug("was Health not exist! : " + input.toString());
            return false;
        }
        String oggOkYn = "Y";
        String wasOkYn = "Y";
        if(StringUtil.getText(data.get("oggOkYn")).equals("N")) {
            logger.debug("oggOkYn : N " + input.toString());
            oggOkYn = "N";
            return false;
        } else {
            if(StringUtil.getText(data.get("wasOkYn")).equals("N")) {
                logger.debug("wasOkYn : N " + input.toString());
                wasOkYn = "N";
                return false;
            }
        }

        List<Map<String, Object>> mData = instcFactory.getmInst();
        for(int i=0; i<mData.size(); i++) {
            if(StringUtil.getText(mData.get(i).get("instcId")).equals(StringUtil.getText(input.get("instcId")))) {
                mData.get(i).put("oggOkYn", oggOkYn);
                mData.get(i).put("wasOkYn", wasOkYn);
                break;
            }
        }
        instcFactory.setmInst(mData);

        return true;
    }

    /**
     * 인스턴스 상태 점검
     * @param input
     * @return
     */
    public String checkInstc(Map<String, Object> input) {
        List<Map<String, Object>> mData = instcFactory.getmInst();
        Map<String, Object> data = null;
        for(int i=0; i<mData.size(); i++) {
            if(StringUtil.getText(mData.get(i).get("instcId")).equals(StringUtil.getText(input.get("instcId")))) {
                data = mData.get(i);
                break;
            }
        }

        if(data.isEmpty()) {
            logger.debug("was Health not exist! : " + input.toString());
            return "";
        }
        String oggOkYn = "Y";
        String wasOkYn = "Y";
        if(StringUtil.getText(data.get("oggOkYn")).equals("N")) {
            logger.debug("oggOkYn : N " + input.toString());
            oggOkYn = "N";
            return "ogg";
        } else {
            if(StringUtil.getText(data.get("wasOkYn")).equals("N")) {
                logger.debug("wasOkYn : N " + input.toString());
                wasOkYn = "N";
                return "was";
            }
        }

        for(int i=0; i<mData.size(); i++) {
            if(StringUtil.getText(mData.get(i).get("instcId")).equals(StringUtil.getText(input.get("instcId")))) {
                mData.get(i).put("oggOkYn", oggOkYn);
                mData.get(i).put("wasOkYn", wasOkYn);
                break;
            }
        }
        instcFactory.setmInst(mData);

        return "";
    }
}
