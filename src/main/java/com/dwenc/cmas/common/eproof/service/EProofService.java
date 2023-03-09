package com.dwenc.cmas.common.eproof.service;

/**
 * <pre>
 * --------------------------------------------------------------
 * 업무구분 : 공통
 * 프로그램 : EProofService
 * 설 명 : 전자증빙 시스템을 HTTP로 호출하여 ref_key를 mng_no로 업데이트 하는 Service 클래스
 * 작 성 자 : 변형구
 * 작성일자 : 2012.10.10
 * 수정이력
 * --------------------------------------------------------------
 * 수정일                          이 름          사유
 * --------------------------------------------------------------
 *
 * --------------------------------------------------------------
 * </pre>
 * @version 1.0
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.dwenc.cmas.common.ecm.service.EcmService;

import docfbaro.common.StringUtil;

public class EProofService {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(EcmService.class);

    @Autowired
    @Qualifier("appProperties")
    private Properties appProperties;

	/**
	 * 전자증빙 연동
	 *  - ref_key : 전자증빙 참조번호
	 *  - mng_no : 전자증빙 관리번호
	 *
	 * 전자증빙 시스템을 HTTP로 호출하여 ref_key를 mng_no로 업데이트 한다.
	 * @param map
	 */
    public void updateEProof(Map<String, Object> map){
    	String fv_eProofUrl = StringUtil.getText(appProperties.getProperty("dwe.dwees.url")) +  "/interface.do?method=IF06";
    	String fv_eProofParam = "";
    	String refKey = StringUtil.getText(map.get("refKey"));
    	String mngNo = StringUtil.getText(map.get("mngNo"));
    	//		"&user_id=" + user_id + "&work_cd=" + workCd + "&ref_key=" + refKey + "&mng_no=" + mngNo + "&conf_yn=N&doc_cd=" + docCd + "&scr_yn=" + scrYn;

    	if(!refKey.equals("") || !mngNo.equals("")){
        	fv_eProofParam += "&ref_key=" + refKey;
        	fv_eProofParam += "&mng_no=" + mngNo;
    		URL url;
    		BufferedReader br = null;
			try {
				url = new URL(fv_eProofUrl + fv_eProofParam);
	    		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    		connection.setRequestMethod("GET");
	    		connection.setDoOutput(true);
	    		connection.setInstanceFollowRedirects(false);
	    		connection.connect();

	    		br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
	    		StringBuffer sb = new StringBuffer();

	    		String strData = "";
	    		while ((strData = br.readLine()) != null) {
	    			sb.append(strData);
	    		}

	    		System.out.println("sb.toString() : " + sb.toString());
	    		logger.info("sb.toString() : " + sb.toString());
			} catch (MalformedURLException e) {
			} catch (IOException e) {
			} finally{
				if(br != null){
					try {
						br.close();
					} catch (IOException e) {
					}
				}
			}
    	}else {

    	}
    }
}
