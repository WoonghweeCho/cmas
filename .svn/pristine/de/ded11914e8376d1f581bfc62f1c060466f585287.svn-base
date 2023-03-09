package com.dwenc.cmas.common.config.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.utils.XmlUtil;

import docfbaro.common.Constants;
import docfbaro.common.StringUtil;
import docfbaro.query.CommonDao;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통
 * 프로그램 : ConfigService
 * 설    명 : 설정 정보(대우건설 정보)를 db에 업데이트 처리하는 서비스
 * 작 성 자 :
 * 작성일자 :
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2012-03-19             최초 작성
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */
@Service("ConfigService")
public class ConfigService {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(ConfigService.class);

    @Autowired
    @Qualifier("appProperties")
    private Properties appProperties;

    /**
     * DB처리를 위한  공통 dao
     */
    @Autowired
    @Qualifier("mainDB")
    private CommonDao dao;

    //@Autowired
    //@Qualifier("5thDB")
    //private CommonDao ecosDao;

    @PostConstruct
    public void init() {
        // 임시 기능 중지
//        updateConfig();
    }

    /**
     * 설정 정보 업데이트
     */
    public void updateConfig(){
        try{
            if(!StringUtil.getText(appProperties.get("dwe.company.useYn")).equals("true")) return;
            if(Constants.stage.equals("L")) return;

            Map<String, Object> input = new HashMap<String, Object>();
            input.put("key", "dwe.company.");
            List<Map<String, Object>> mData = retrieveConfigList(input);
            Map<String, Object> input2 = mData.get(0);
            Map<String, Object> input3 = new HashMap<String, Object>();

            Set dataKeySet = input2.keySet();
            Iterator dataIterator = dataKeySet.iterator();
            while(dataIterator.hasNext()){
                String dataKey = dataIterator.next().toString();
                String dataKey2 = dataKey.substring("dwe.company.".length(), dataKey.length());
                input3.put(dataKey2, input2.get(dataKey));
            }
            /*String userId = "dweadm";
            input3.put("fstRegUserId", userId);
            input3.put("fnlEditUserId", userId);
            ecosDao.update("ecosConfig.updateEcosConfig", input3);
            dao.update("config.updateConfig", input3);*/
        }catch(Exception e){
            logger.error(e.getMessage());
        }
    }

    /**
     * <pre>
     *  properties에서 설정 목록 조회
     * </pre>
     * @param requestMap
     */
    public List<Map<String, Object>> retrieveConfigList(Map<String, Object> requestMap){
        List<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();
        String condi = StringUtil.getText(requestMap.get("key"));
        Set keySet = appProperties.keySet();
        Iterator iter = keySet.iterator();
        Map<String, Object> inputData = new HashMap<String, Object>();
        while(iter.hasNext()){
            String col = (String)iter.next();
            if(condi.equals("")){
                String val = StringUtil.getText(appProperties.get(col));
                inputData.put(col, val);
            }else if(col.indexOf(condi) > -1){
                String val = StringUtil.getText(appProperties.get(col));
                inputData.put(col, val);
            }
        }
        mData.add(inputData);
        return mData;
    }

    /**
     * <pre>
     *  properties에서 설정 정보 조회
     * </pre>
     * @param requestMap
     */
    public String retrieveConfig(Map<String, Object> inputData){
        List<Map<String, Object>> mData = retrieveConfigList(inputData);
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("root", "config");
        return XmlUtil.MultiToXmlStr(input, mData);
    }

}
