package com.dwenc.cmas.common.sms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jcf.data.GridData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.sms.domain.Sms;
import com.dwenc.cmas.common.sms.domain.SmsResult;

import docfbaro.common.StringUtil;
import docfbaro.query.CommonDao;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : SmsService
 * 설    명 : Sms 발송 관리 정보의 조회/추가/수정/삭제/ 업무를 처리하는 Service 클래스.
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
public class SmsService {

    /**
     * DB처리를 위한  공통 dao
     */
    @Autowired
    @Qualifier("3rdDB")
    private CommonDao dao;

    /**
     * <pre>
     * Sms 발송 관리 정보를 추가하는 메소드.
     * </pre>
     * @param GridData<HashMap> Command로 부터 전달
     */
    @Async
    public List<SmsResult> sendSms(GridData<HashMap> mData){
        List<SmsResult> mResult = new ArrayList<SmsResult>();
        if(mData == null){
            SmsResult result = new SmsResult();
            result.setPhoneNo("");
            result.setResult("No Data");
            mResult.add(result);
            return mResult;
        }

        String rphone = ""; //받는사람 전화번호 전체
        String rphone1 = ""; //받는사람 전화번호1
        String rphone2 = ""; //받는사람 전화번호2
        String rphone3 = ""; //받는사람 전화번호3

        String sphone = ""; //보낸사람 전화번호 전체
        String sphone1 = ""; //보낸사람 전화번호1
        String sphone2 = ""; //보낸사람 전화번호2
        String sphone3 = ""; //보낸사람 전화번호3
        String sendid = ""; //보낸사람 ID
        String sendname = ""; //보낸사람명

        for(int i = 0; i < mData.size(); i++){
            Map<String, Object> inputData = mData.get(i);

            rphone = StringUtil.getText(mData.get(i).get("rphone")).toString().replace("-", ""); //받는사람 전화번호 "-" 치환
            rphone = rphone.toString().replace(" ", ""); //받는사람 전화번호 " " 치환

            if(rphone.length() == 10){ //10자리 폰번호인경우
                rphone1 = rphone.substring(0, 3); //받는사람폰번호1
                rphone2 = rphone.substring(3, 6); //받는사람폰번호2
                rphone3 = rphone.substring(6); //받는사람폰번호3
            }

            if(rphone.length() == 11){ //11자리 폰번호인경우
                rphone1 = rphone.substring(0, 3); //받는사람폰번호1
                rphone2 = rphone.substring(3, 7); //받는사람폰번호2
                rphone3 = rphone.substring(7); //받는사람폰번호3
            }

            sphone = StringUtil.getText(mData.get(i).get("sphone")).toString();

            sphone = StringUtil.getText(mData.get(i).get("sphone")).toString().replace("-", ""); //보낸사람 전화번호 "-" 치환
            sphone = sphone.toString().replace(" ", ""); //보낸사람 전화번호 " " 치환

            if(sphone.length() == 7){ //7자리 폰번호인경우

                sphone1 = "02"; //송신자폰번호1
                sphone2 = sphone.substring(0, 3); //송신자폰번호2
                sphone3 = sphone.substring(3, 7); //송신자폰번호3
            }
            if(sphone.length() == 8){ //8자리 폰번호인경우
                sphone1 = "02"; //송신자폰번호1
                sphone2 = sphone.substring(0, 4); //송신자폰번호2
                sphone3 = sphone.substring(4, 8); //송신자폰번호3
            }
            if(sphone.length() == 9){ //9자리 폰번호인경우
                sphone1 = sphone.substring(0, 3); //송신자폰번호1
                sphone2 = sphone.substring(3, 7); //송신자폰번호2
                sphone3 = sphone.substring(7); //송신자폰번호3
            }
            if(sphone.length() == 10){
                sphone1 = sphone.substring(0, 3);
                sphone2 = sphone.substring(3, 6);
                sphone3 = sphone.substring(6);
            }

            if(sphone.length() == 11){
                sphone1 = sphone.substring(0, 3);
                sphone2 = sphone.substring(3, 7);
                sphone3 = sphone.substring(7);
            }

            //보낸사람 ID가 null 일경우 사용자정보에서 로긴ID를 사용
            sendid = StringUtil.getText(mData.get(i).get("sendid")).toString();
            if(StringUtil.getText(sendid).equals("")){
                sendid = "autouser";
            }

            //보낸사람명이 null 일경우 사용자정보에서 보낸사람명 사용
            sendname = StringUtil.getText(mData.get(i).get("sendname")).toString();

            if(StringUtil.getText(sendname).equals("")){
                sendname = "autouser";
            }
            if(StringUtil.getText(sphone1).equals("")){
                sphone1 = "000";
            }
            if(StringUtil.getText(sphone2).equals("")){
                sphone2 = "0000";
            }
            if(StringUtil.getText(sphone3).equals("")){
                sphone3 = "0000";
            }
            inputData.put("sendid", sendid); // 보낸사람ID
            inputData.put("sendname", sendname); // 보낸사람명
            inputData.put("rphone1", rphone1); // 받는사람 폰번호1
            inputData.put("rphone2", rphone2); // 받는사람 폰번호2
            inputData.put("rphone3", rphone3); // 받는사람 폰번호3
            inputData.put("sphone1", sphone1); // 보낸사람 폰번호1
            inputData.put("sphone2", sphone2); // 보낸사람 폰번호2
            inputData.put("sphone3", sphone3); // 보낸사람 폰번호3
            inputData.put("recvname", StringUtil.getText(mData.get(i).get("recvname")).toString()); // 받는사람명
            inputData.put("msg", StringUtil.getText(mData.get(i).get("msg")).toString()); // 메세지
            inputData.put("rdate", StringUtil.getText(mData.get(i).get("rdate")).toString()); // 예약일자
            inputData.put("rtime", StringUtil.getText(mData.get(i).get("rtime")).toString()); // 예약시간

            if(StringUtil.getText(rphone).equals("")){
                SmsResult result = new SmsResult();
                result.setPhoneNo(StringUtil.getText(mData.get(i).get("rphone")).toString());
                result.setResult(StringUtil.getText(mData.get(i).get("recvname")).toString() + " No phone number");
                mResult.add(result);
            }else{
                try{
                    dao.update("Sms.createSmsSend", inputData);
                    SmsResult result = new SmsResult();
                    result.setPhoneNo(StringUtil.getText(mData.get(i).get("rphone")).toString());
                    result.setResult("Success");
                    mResult.add(result);

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return mResult;
    }

    /**
     * <pre>
     * 복수의 Sms 발송
     * </pre>
     * @param mData
     * @return List<SmsResult>
     */
    @Async
    public List<SmsResult> sendSms(List<Map<String, Object>> mData){
        List<SmsResult> mResult = new ArrayList<SmsResult>();
        if(mData == null){
            SmsResult result = new SmsResult();
            result.setPhoneNo("");
            result.setResult("No Data");
            mResult.add(result);
            return mResult;
        }

        String rphone = ""; //받는사람 전화번호 전체
        String rphone1 = ""; //받는사람 전화번호1
        String rphone2 = ""; //받는사람 전화번호2
        String rphone3 = ""; //받는사람 전화번호3

        String sphone = ""; //보낸사람 전화번호 전체
        String sphone1 = ""; //보낸사람 전화번호1
        String sphone2 = ""; //보낸사람 전화번호2
        String sphone3 = ""; //보낸사람 전화번호3
        String sendid = ""; //보낸사람 ID
        String sendname = ""; //보낸사람명

        for(int i = 0; i < mData.size(); i++){
            Map<String, Object> inputData = mData.get(i);

            rphone = StringUtil.getText(mData.get(i).get("rphone")).toString().replace("-", ""); //받는사람 전화번호 "-" 치환
            rphone = rphone.toString().replace(" ", ""); //받는사람 전화번호 " " 치환

            if(rphone.length() == 10){ //10자리 폰번호인경우
                rphone1 = rphone.substring(0, 3); //받는사람폰번호1
                rphone2 = rphone.substring(3, 6); //받는사람폰번호2
                rphone3 = rphone.substring(6); //받는사람폰번호3
            }

            if(rphone.length() == 11){ //11자리 폰번호인경우
                rphone1 = rphone.substring(0, 3); //받는사람폰번호1
                rphone2 = rphone.substring(3, 7); //받는사람폰번호2
                rphone3 = rphone.substring(7); //받는사람폰번호3
            }

            sphone = StringUtil.getText(mData.get(i).get("sphone")).toString();
            sphone = StringUtil.getText(mData.get(i).get("sphone")).toString().replace("-", ""); //보낸사람 전화번호 "-" 치환
            sphone = sphone.toString().replace(" ", ""); //보낸사람 전화번호 " " 치환
            if(sphone.length() == 8){ //8자리 폰번호인경우
                sphone1 = "02"; //송신자폰번호1
                sphone2 = sphone.substring(0, 4); //송신자폰번호2
                sphone3 = sphone.substring(4, 8); //송신자폰번호3
            }
            if(sphone.length() == 10){
                sphone1 = sphone.substring(0, 3);
                sphone2 = sphone.substring(3, 6);
                sphone3 = sphone.substring(6);
            }

            if(sphone.length() == 11){
                sphone1 = sphone.substring(0, 3);
                sphone2 = sphone.substring(3, 7);
                sphone3 = sphone.substring(7);
            }

            //보낸사람 ID가 null 일경우 사용자정보에서 로긴ID를 사용
            sendid = StringUtil.getText(mData.get(i).get("sendid")).toString();

            //보낸사람명이 null 일경우 사용자정보에서 보낸사람명 사용
            sendname = StringUtil.getText(mData.get(i).get("sendname")).toString();

            inputData.put("sendid", sendid); // 보낸사람ID
            inputData.put("sendname", sendname); // 보낸사람명
            inputData.put("rphone1", rphone1); // 받는사람 폰번호1
            inputData.put("rphone2", rphone2); // 받는사람 폰번호2
            inputData.put("rphone3", rphone3); // 받는사람 폰번호3
            inputData.put("sphone1", sphone1); // 보낸사람 폰번호1
            inputData.put("sphone2", sphone2); // 보낸사람 폰번호2
            inputData.put("sphone3", sphone3); // 보낸사람 폰번호3
            inputData.put("recvname", StringUtil.getText(mData.get(i).get("recvname")).toString()); // 받는사람명
            inputData.put("msg", StringUtil.getText(mData.get(i).get("msg")).toString()); // 메세지
            inputData.put("rdate", StringUtil.getText(mData.get(i).get("rdate")).toString()); // 예약일자
            inputData.put("rtime", StringUtil.getText(mData.get(i).get("rtime")).toString()); // 예약시간

            if(StringUtil.getText(rphone).equals("")){
                SmsResult result = new SmsResult();
                result.setPhoneNo(StringUtil.getText(mData.get(i).get("rphone")).toString());
                result.setResult(StringUtil.getText(mData.get(i).get("recvname")).toString() + " No phone number");
                mResult.add(result);
            }else{
                try{
                    dao.update("Sms.createSmsSend", inputData);
                }catch(Exception e){
                    SmsResult result = new SmsResult();
                    result.setPhoneNo(StringUtil.getText(mData.get(i).get("rphone")).toString());
                    result.setResult(StringUtil.getText(mData.get(i).get("recvname")).toString() + " Fail Send Sms");
                    mResult.add(result);
                    return mResult;
                }
                SmsResult result = new SmsResult();
                result.setPhoneNo(StringUtil.getText(mData.get(i).get("rphone")).toString());
                result.setResult("Success");
                mResult.add(result);
            }
        }
        return mResult;
    }

    /**
     * SMS 발신목록 조회하기위한 메소드
     *
     *
     * @param input
     * @return List<Sms> (발신목록)
     */
    public List<Sms> retrieveSms(Map<String, Object> input){
        return dao.queryForList("Sms.retrieveSms", input, Sms.class);
    }
}
