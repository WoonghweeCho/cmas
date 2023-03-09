package com.dwenc.cmas.common.sysMng.service;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통모듈
 * 프로그램 : WasService
 * 설    명 : 와스 정보를 관리하는 서비스
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.utils.HttpUtil;
import com.dwenc.cmas.common.utils.MailUtil;

import docfbaro.common.DateUtil;
import docfbaro.common.StringUtil;
import docfbaro.query.CommonDao;

@Service
public class WasService {

    private static Logger logger = LoggerFactory.getLogger(WasService.class);

    @Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

    @Autowired
    private MailUtil mailUtil;

    /**
     * 와스 리스트 조회
     * @param parameter
     * @return
     */
    public List<Map<String, Object>> retrieveWasList(Map<String, Object> parameter){
        return dao.queryForMapList("was.retrieveWasList", parameter);
    }

    /**
     * 와스어드민 리스트 조회
     * @param parameter
     * @return
     */
    public List<Map<String, Object>> retrieveWasAdmin(Map<String, Object> mdata){
        return dao.queryForMapList("was.retrieveWasAdmin", mdata);
    }

    /**
     * 예정된 파일 서비스 크기 확인 후 오류 발생시 메시지, sms 발송
     * @param inputData Command로 부턴 전달받은 input Map<String, Object>Protocol
     */
    public String execBatch(Map<String, Object> input){
        String bChk = "Y";
        try{
            logger.debug("=========" + input);
            String wasId = StringUtil.getText(input.get("wasId"));
            if(wasId.equals("")){
                return bChk = "-";
            }
            List<Map<String, Object>> mData = retrieveWasList(input);
            if(mData.isEmpty()){
                logger.debug(wasId + "가 없음!");
                return bChk = "-";
            }
            Map<String, Object> inputData = mData.get(0);

            String svcUrl = StringUtil.getText(inputData.get("svcUrl"));
            String sizeChk = StringUtil.getText(inputData.get("sizeChk"));
            String emailUseYn = StringUtil.getText(inputData.get("emailUseYn"));
            String smsUseYn = StringUtil.getText(inputData.get("smsUseYn"));
//            String wasManger = StringUtil.getText(inputData.get("wasManger"));
//            String arryMangerList[] = wasManger.split("\\^");
            String strRslt = "";
            try{
                Map<String, Object> header = new HashMap<String, Object>();
                header.put("charset", "utf-8");
                strRslt = HttpUtil.callUrl(svcUrl, input, header);
                int nRslt = Integer.parseInt(sizeChk);
                logger.debug("HttpUtil.callUrl(" + svcUrl + ") real size => " + strRslt.length());
                if(strRslt.length() != nRslt){
                    logger.debug("HttpUtil.callUrl(" + svcUrl + ") expected size => " + nRslt);
                    bChk = "N";
                }
            }catch(Exception e){
                logger.debug(this.getClass().getName() + "." + "HttpUtil.callUrl(" + svcUrl + ") => "
                        + e.getMessage());
                bChk = "N";
            }
            //인스턴스별 와스 상태 UPDATE
            inputData.put("bChk", bChk);
            dao.update("instc.updateWasOkYn", inputData);
            //와스 관리자 정보 조회
            List<Map<String, Object>> mData1 = new ArrayList<Map<String, Object>>();
            mData1 = retrieveWasAdmin(inputData);
            if (mData1.size() == 0) {
            	return bChk;
            }
            if(bChk.equals("N")){
                logger.debug("HttpUtil.callUrl(" + svcUrl + ") Wrong size!!!");
                logger.debug("=========== strEmail:" + emailUseYn);
                if(emailUseYn.equals("Y")){
                    input.put("spec", "default");
                    input.put("bodyTemplate", "sysMng");
                    input.put("reportType", "에러보고");
                    input.put("createDate", DateUtil.getCurrentDateString("yyyy/MM/dd HH:mm:ss")); // "20020717123456"
                    String strContent = "Service error!!!(" + svcUrl + ")";
                    strContent += "<br> Expected file size = " + sizeChk;
                    strContent += "<br><br>";
                    input.put("Content", strContent);
                    String mailSubject = "Service error!!!(" + svcUrl + ")";
                    input.put("mailSubject", mailSubject); // Mail Title
//                    logger.debug("=========== arryMangerList.length:" + arryMangerList.length);
                    for(int i=0;i<mData1.size();i++) {
//                        logger.debug("=========== arryMangerList[i]:" + arryMangerList[i]);
//                        String arryManager[] = arryMangerList[i].trim().split(";");
//                        logger.debug("=========== arryManager[2]:" + arryManager[2]);
                        input.put("mailId", StringUtil.getText(mData1.get(i).get("email"))); // Mail Address
                        try{
                            mailUtil.sendMail(input);
                        }catch(Exception e){
                            logger.debug(e.getMessage());
                        }
                    }
                }
                /*if(smsUseYn.equals("Y")){
                    for(int i=0;i<mData1.size();i++) {
                        input.put("rphone", StringUtil.getText(mData1.get(i).get("mphoneNo"))); // phone no
                        input.put("sendname", StringUtil.getText(mData1.get(i).get("adminPernm"))); //받는사람 이름
                        input.put("msg", "Service error!!!(" + svcUrl + ")");
                        try{
                            SmsUtil.sendSms(input);
                        }catch(Exception e){
                            logger.debug(e.getMessage());
                        }
                    }
                }*/ // 2013.02.20 변형구
            }
            logger.debug("WasService called at " + DateUtil.getCurrentDateString());
        }catch(Exception e){
            logger.debug(this.getClass().getName() + "." + "execBatch()" + "=>" + e.getMessage());
        }
        return bChk;
    }

}
