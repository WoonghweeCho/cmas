package com.dwenc.cmas.common.sysMng.service.support;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.instance.service.support.InstcFactory;
import com.dwenc.cmas.common.sysMng.service.WasService;
import com.dwenc.cmas.common.utils.ConfigUtil;
import com.dwenc.cmas.common.utils.MailUtil;

import docfbaro.common.Constants;
import docfbaro.common.DateUtil;
import docfbaro.common.SpringUtil;
import docfbaro.common.StringUtil;
import docfbaro.common.WebContext;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통모듈
 * 프로그램 : SysMngUtil
 * 설    명 : 시스템 유지보수에 필요한 UTILITY 클래스
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
@Service
public class SysMngUtil {

    private static Logger logger = LoggerFactory.getLogger(SysMngUtil.class);

    @Autowired
    private WasService wasService;

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private InstcFactory instcFactory;

    @Autowired
    @Qualifier("appProperties")
    private Properties appProperties;

    /**
     * 시스템 오류 발생 시 담당자게에 알림 서비스 처리
     */
    public void sysMng(Map<String, Object> input){
        try{
            String bChk = "N";
            if(!StringUtil.getText(appProperties.get("dwe.sysMng.useYn")).equals("true"))
                return;

            Map<String, Object> inputData = new HashMap<String, Object>();
            List<Map<String, Object>> mData = wasService.retrieveWasList(input);
            if(!StringUtil.getText(input.get("wasId")).equals("")
                    || !StringUtil.getText(input.get("instcId")).equals("")){
                inputData = mData.get(0);
            }else{
                String instcId = instcFactory.getInstcIdFromOggCd();
                for(int i = 0; i < mData.size(); i++){
                    if(StringUtil.getText(mData.get(i).get("instcId")).equals(instcId)){
                        inputData = mData.get(i);
                        break;
                    }
                }
            }

            // 해당 WAS 장비의 담당자는 누구인가?
            List<Map<String, Object>> mData1 = new ArrayList<Map<String, Object>>();
            mData1 = wasService.retrieveWasAdmin(inputData);
            if(mData1.size() == 0){
                return;
            }

            String strContent = "";
            Exception err = null;
            // 상세 로그 추출
            if(input.get("exception") != null){
                err = (Exception)input.get("exception");
                StringWriter traceWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(traceWriter, false);
                ((Exception)err).printStackTrace(printWriter);
                String tmp = traceWriter.getBuffer().toString();
                printWriter.close();
                strContent = "Service error!!!(" + ((Exception)err).getMessage() + ")";
                strContent += tmp.replaceAll("\r", "<br>");
                strContent += tmp.replaceAll("\n", "<br>");
                strContent += "<br><br>";
            }else{
                strContent = "Service error!!!" + input.get("msg");
            }

            // 담당자가 email 또는 sms 수신하고자 하는가?
            String emailUseYn = StringUtil.getText(inputData.get("emailUseYn"));
            String smsUseYn = StringUtil.getText(inputData.get("smsUseYn"));
            if(bChk.equals("N")){
                logger.debug("=========== strEmail:" + emailUseYn);
                if(emailUseYn.equals("Y")){
                    input.put("spec", "default");
                    input.put("bodyTemplate", "sysMng");
                    input.put("reportType", "에러보고");
                    input.put("createDate", DateUtil.getCurrentDateString("yyyy/MM/dd HH:mm:ss")); // "20020717123456"
                    strContent += "<br><br>";
                    input.put("Content", strContent);
                    String mailSubject = "Service error!!!";
                    input.put("mailSubject", mailSubject); // Mail Title
                    for(int i = 0; i < mData1.size(); i++){
                        input.put("mailId", StringUtil.getText(mData1.get(i).get("email"))); // Mail Address
                        try{
                            mailUtil.sendMail(input);
                        }catch(Exception e){
                            logger.debug(e.getMessage());
                        }
                    }
                }
                /*if(smsUseYn.equals("Y")){
                    for(int i = 0; i < mData1.size(); i++){
                        input.put("rphone", StringUtil.getText(mData1.get(i).get("mphoneNo"))); // phone no
                        input.put("sendname", StringUtil.getText(mData1.get(i).get("adminPernm"))); //받는사람 이름
                        input.put("msg", "Service error!!!");
                        try{
                            SmsUtil.sendSms(input);
                        }catch(Exception e){
                            logger.debug(e.getMessage());
                        }
                    }
                }*/ // 2013.02.20 변형구
            }
        }catch(Exception e){
            logger.debug(e.getMessage());
        }
    }

    /**
     * 시스템별 담당자 정보를 조회
     */
    public String getContact(Map<String, Object> inputData){
        String strRtn = "";
        String email = "";
        String sms = "";
        String centerManger = "";
        String instcId = instcFactory.getInstcIdFromOggCd();
        List<Map<String, Object>> mData = wasService.retrieveWasList(inputData);
        if(mData.isEmpty()){
            logger.debug(instcId + "가 없음!");
        }
        inputData = new HashMap<String, Object>();
        for(int i = 0; i < mData.size(); i++){
            if(StringUtil.getText(mData.get(i).get("id")).equals(instcId)){
                inputData = mData.get(i);
                break;
            }
        }
        email = StringUtil.getText(inputData.get("email"));
        sms = StringUtil.getText(inputData.get("sms"));
        centerManger = StringUtil.getText(inputData.get("centerManger"));

        if(StringUtil.getText(appProperties.get("dwe.sysMng.useYn")).equals("true")){
            strRtn = "<span class=\"bt\">Contact Information or Troubleshooting Tips</span><br/><br/>";
        }
        String arryCenterMangers[] = centerManger.split("\\^");
        if(arryCenterMangers.length > 0){
            for(int i = 0; i < arryCenterMangers.length; i++){
                String arryCenterManger[] = arryCenterMangers[i].trim().split(";");
                if(email.equals("true"))
                    strRtn += "E-mail us at <a href=\"mailto://" + arryCenterManger[2] + "\">" + arryCenterManger[2]
                            + "</a><br/>";
                if(sms.equals("true"))
                    strRtn += "Call us at " + arryCenterManger[3] + "<br><br>";
            }
        }else{
            String arryCenterManger[] = centerManger.split(";");
            if(email.equals("true"))
                strRtn += "E-mail us at <a href=\"mailto://" + arryCenterManger[2] + "\">" + arryCenterManger[2]
                        + "</a><br/>";
            if(sms.equals("true"))
                strRtn += "Call us at " + arryCenterManger[3] + "<br>";
        }
        return strRtn;
    }

    /**
     * 담당자에게 메일 발송
     */
    public void sendEmail(Map<String, Object> inputData) throws Exception{
        inputData.put("spec", "default");
        inputData.put("bodyTemplate", "sysMng");
        inputData.put("mailId", StringUtil.getText(inputData.get("emailId")));
        inputData.put("mailSubject", StringUtil.getText(inputData.get("emailSubject")));
        mailUtil.sendMail(inputData);
    }

    /**
     * 담당자에게 sms 발송
     */
    /*public void sendSms(Map<String, Object> inputData){
        List<Map<String, Object>> input = new ArrayList<Map<String, Object>>();
        input.add(inputData);
        try{
            SmsUtil.sendSms(input);
        }catch(Exception se){
            logger.debug("sendEmail()" + "=>" + se.getMessage());
        }
    }*/  // 2013.02.20 변형구

    /**
     * 에러 메시지 추출
     */
    public String getErrorMsg(Map<String, Object> inputData){
        String strRtn = "";
        if(!StringUtil.getText(appProperties.get("dwe.sysMng.useYn")).equals("true")){
            strRtn = "";
        }

        String errorCause = StringUtil.getText(inputData.get("errorCause"));
        String msgType = StringUtil.getText(inputData.get("msgType"));
        if(errorCause == null)
            errorCause = "";
        String errorMsg[] = null;
        if(errorCause.indexOf("^") > 0){ // cmd에서 에러 메시지 처리할 경우  ServletUtil.redirectErrPage(errData)
            errorMsg = errorCause.split("\\^");
        }else{
            errorMsg = errorCause.split("\n"); // exception 에서 에러 처리될 경우
        }

        if(msgType.endsWith("title")){
            strRtn = errorMsg[0];
            if(strRtn.length() > 80){
                strRtn = strRtn.substring(0, 80);
            }
        }else{
            if(errorMsg.length > 1){
                if(errorCause.indexOf("^") > 0){
                    strRtn = errorCause.replaceAll("\\^", "<br>");
                }else{
                    String fullMsg = errorCause;
                    strRtn = fullMsg.replaceAll("\\$", "");
                }
            }else{
                strRtn = errorCause;
            }
        }
        return strRtn;
    }

    /**
     * 에러 메시지 전송
     */
    public static void sendErrorMsg(String message, Exception exception){
        try{
            if(!StringUtil.getText(ConfigUtil.getProperties(null).get("dwe.sysMng.exception.useYn")).equals("true"))
                return;

            String dutySysCd = "";
            String strContent = "";

            // 상세 로그 추출
            StringWriter traceWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(traceWriter, false);
            exception.printStackTrace(printWriter);
            printWriter.close();
            strContent = traceWriter.getBuffer().toString();

            if(message.indexOf(".err.") > -1){
                dutySysCd = message.substring(0, message.indexOf(".err."));
            }else{
                String tmpArry[] = strContent.split("\tat ");
                if(tmpArry.length > 0){
                    for(int j = 0; j < tmpArry.length; j++){
                        if(tmpArry[j].startsWith("com.dwenc.cmas")){
                            dutySysCd = tmpArry[j];
                            break;
                        }
                    }
                    if(!dutySysCd.equals("")){
                        dutySysCd = dutySysCd.substring("com.dwenc.cmas".length() + 1, dutySysCd.length());
                        dutySysCd = dutySysCd.substring(0, dutySysCd.indexOf("."));
                    }
                }
            }
            if(!dutySysCd.equals("")){
                WasFactory wasFactory = (WasFactory)SpringUtil.getBean(WebContext.getRequest(), "wasFactory");
                SysMngUtil sysMngUtil = (SysMngUtil)SpringUtil.getBean(WebContext.getRequest(), "sysMngUtil");
                List<Map<String, Object>> mData = wasFactory.getWasAdminList();
                for(int i = 0; i < mData.size(); i++){
                    String rem = StringUtil.getText(mData.get(i).get("rem"));
                    String remArry[] = rem.split(",");
                    for(int j = 0; j < remArry.length; j++){
                        if(remArry[j].equals(dutySysCd)){
                            Map<String, Object> inputData = new HashMap<String, Object>();
                            inputData.put("instcId", Constants.instcId);
                            inputData.put("exception", exception);
                            sysMngUtil.sysMng(inputData);
                        }
                    }
                }
            }
        }catch(Exception e){

        }
    }
}