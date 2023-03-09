package com.dwenc.cmas.common.accessLog.service;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통모듈
 * 프로그램 : AccessLogService
 * 설    명 : 사용량 통계 기록 서비스
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
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.accessLog.service.support.AccessLogUtil;
import com.dwenc.cmas.common.instance.service.InstcService;

import docfbaro.common.Constants;
import docfbaro.common.StringUtil;

@Service
public class AccessLogService {

	public static boolean BATCH_FLAG = false;

    @Autowired
    private InstcService instcService;

    @Autowired
    @Qualifier("appProperties")
    private Properties appProperties;

    /**
     * 사용자 접속(사용량) 통게 정보를 수집한다.
     * @param param
     */
	public void collectAllAccessLog(Map<String, Object> param) {
		//로그 배치가 동작 했을 경우 다시 실행시키지 않는다.
    	if(BATCH_FLAG == true) {
    		System.out.println("Instances checking process was alreaey doing~!!!");
    		return;
    	}

    	String downloadDictory = Constants.accessLogDirectory;

    	synchronized(this) {

    		BATCH_FLAG = true;

    		clearLog(param);

    		//WAS, 인스턴스 관리 정보를 조회한다.
    		param.put("useYn", "Y");
    		List<Map<String, Object>> instcList = instcService.retrieveInstcWas(param);

            String queryExecStart = String.valueOf(param.get("queryExecStart"));
            String queryExecEnd = String.valueOf(param.get("queryExecEnd"));

	    	for(Map<String, Object> map : instcList) {
	    		/**
	    		 * 서버종류
	    		 *   - 완전분산 : 02
	    		 *   - 웹분산    : 03
	    		 *   - 일부분산 : 04
	    		 */
	    		String oprMethCls = StringUtil.getText(map.get("oprMethCls"));
	    		String instcId = StringUtil.getText(map.get("instcId"));
	    		boolean isDistributed = false;

	    		if(oprMethCls.equals("02") || oprMethCls.equals("04"))
	    			isDistributed = true;

	    		String hqInstcId = StringUtil.getText(appProperties.get("dwe.accessLog.hq.instance.id"));
	    		String svcUrl = StringUtil.getText(map.get("svcUrl"));

	    		// 웹 분산과 본사통합이 아닌 경우만 download 시킨다.
	    		if(isDistributed && !instcId.equals(hqInstcId) && !svcUrl.equals("")) {
	            	svcUrl += StringUtil.getText(appProperties.get("dwe.accessLog.download.url"));

	            	try {

	            		String resultDir= downloadAccessLog(svcUrl, instcId, downloadDictory, queryExecStart, queryExecEnd);
	            		File dir = new File(resultDir);

	            		if(dir != null) {
		            		File [] files = dir.listFiles();

		            		String encoding = System.getProperty("file.encoding");
		                    for(File f : files) {
		                    	AccessLogUtil.copyFileByEncoding(f, new File((downloadDictory + "/" + f.getName())), encoding, encoding);
		                    }

		                    AccessLogUtil.deleteDirectory(dir);
	            		}

	            	} catch(Exception e) {
	            		e.printStackTrace();
	            	}
	    		}
	    	}
    	}

    	BATCH_FLAG = false;
	}

    /***
     * AccessLog를 다운로드 받는다.
     *
     * @param svcUrlsvcUrl
     * @return
     */
    private String downloadAccessLog(String svcUrl, String instcId, String downloadPath, String queryExecStart, String queryExecEnd) throws Exception{

    	String dir = downloadPath + "/" + instcId + "_" + queryExecStart + "_" + queryExecEnd;
    	String downloadFile = downloadPath + "/" + instcId + "_" + queryExecStart + "_" + queryExecEnd + ".zip";
    	svcUrl = svcUrl + "?queryExecStart=" + queryExecStart
    					+ "&queryExecEnd=" + queryExecEnd;

    	int retry = Integer.parseInt(StringUtil.getText(appProperties.get("dwe.accessLog.download.retry")));
    	File file = AccessLogUtil.downloadFileByHttp(svcUrl, downloadFile, retry);

    	if(file != null) {

    		File unZipDir = new File(dir);

    		if(!unZipDir.exists()) {
    			unZipDir.mkdirs();
    		}

	    	AccessLogUtil.unzip(file, new File(downloadPath), true);
	    	file.delete();
    	}

    	return dir;
    }

    /***
     * 기 download된 log를 삭제한다.
     * @param param
     */
    public void clearLog(Map<String, Object> param) {

       	// Delete LogFile
    	File directory = new File(Constants.accessLogDirectory);
    	File [] files = directory.listFiles();
    	String instcId = StringUtil.getText(param.get("instcId"));
        int execStartDate = Integer.parseInt(StringUtil.getText(param.get("queryExecStart")));
        int execEndDate = Integer.parseInt(StringUtil.getText(param.get("queryExecEnd")));

    	// 파일 중 해당 Site에서 download 하거나, 시작날짜와 종료 날짜 사이에 있는 파일만 삭제
    	for(File f : files) {
    		String fileName = f.getName();

        	String pattern = StringUtil.getText(appProperties.get("dwe.accessLog.site.filename.pattern"));
        	Pattern p = Pattern.compile(pattern);
        	Matcher m = p.matcher(fileName);

        	if(m.find()) {

        		f.delete();
        		/*
    			String date = fileName.substring(fileName.indexOf(".txt")-8, fileName.indexOf(".txt"));
    			int nDate = Integer.parseInt(date);

    			// 생성할 일자에 해당하는 파일만 삭제
    			if(nDate >= execStartDate && nDate <= execEndDate) {

    				if(instcId.equals("")) {
    					f.delete();
    				} else {
    					// 해당 instcId의 로그만 삭제
    					if(fileName.indexOf(instcId) > -1)
    						f.delete();
    				}

    			}
    			*/
        	}
    	}
    }
}
