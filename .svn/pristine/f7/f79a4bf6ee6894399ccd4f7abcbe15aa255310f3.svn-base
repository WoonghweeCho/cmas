package com.dwenc.cmas.common.sysMng.service;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통모듈
 * 프로그램 : MonitorService
 * 설    명 : 서버 기동시 상태 확인 및 로깅
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
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

import com.dwenc.cmas.common.instance.service.InstcService;
import com.dwenc.cmas.common.sysMng.service.support.FtpUtil;
import com.dwenc.cmas.common.utils.MailUtil;

import docfbaro.common.Constants;
import docfbaro.common.DateUtil;
import docfbaro.common.StringUtil;
import docfbaro.query.CommonDao;

@Service
public class MonitorService {

    private static Logger logger = LoggerFactory.getLogger(MonitorService.class);

    private static String OGG_PROCESS_STOPPED = "STOPPED";

    private static String OGG_PROCESS_ABENDED = "ABENDED";

    private static final String ERROR_MSG_TITLE = "오류발생 ";
    private static final String WAS_ERROR_MSG = "WAS 이상";
    private static final String ECM_ERROR_MSG = "ECM 이상";
    private static final String OGG_ERROR_MSG = "OGG 이상";

    private static final String REPORT_TYPE = "오류보고";

    public static boolean BATCH_FLAG = false;

    public static Map<String, Object> MAIN_OGG_DISCARD_FILE_MAP = null;

    public static Map<String, Object> LOCL_OGG_DISCARD_FILE_MAP = null;

    @Autowired
    private InstcService service;

    @Autowired
    private WasService wasService;

    @Autowired
    private MailUtil mailUtil;

    @Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

    @Autowired
    @Qualifier("appProperties")
    private Properties appProperties;

    /**
     * 서버 기동 상태 확인 및 로깅
     *
     * @param input
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> checkAllWas(Map<String, Object> input){
        List<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();
//        String bChkParam = (StringUtil.getText(WebContext.getRequest().getAttribute("bChk")));
        mData = service.retrieveInstcWas(input);
        if(!StringUtil.getText(Constants.sysMngUseYn).equals("true"))
            return mData;

//       MapUtil.fillColValue(mData, "bChk", "");

//        String wasId = StringUtil.getText(input.get("wasId"));
        for(int j = 0; j < mData.size(); j++){
//            String bChk = "";
            String wasId = StringUtil.getText(mData.get(j).get("wasId"));
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("wasId", wasId);
            data.put("svrCls", input.get("svrCls"));
            wasService.execBatch(data);
//            if(!wasId.equals("") && wasLst.indexOf(wasId) == -1)
//                continue;
//            if(wasLst.indexOf(",") > -1){ // 이중화 서버
//                String targetArry[] = wasLst.split(",");
//                for(int k = 0; k < targetArry.length; k++){
//                    Map<String, Object> data = new HashMap<String, Object>();
//                    data.put("wasId", targetArry[k]);
//                    data.put("svrCls", input.get("svrCls"));
//                    wasService.execBatch(data);
//                    if(!bChkParam.equals("")){
//                        bChk += bChkParam;
//                        if(k < (targetArry.length - 1))
//                            bChk += ",";
//                    }
//                }
//            }else{
//                Map<String, Object> data = new HashMap<String, Object>();
//                data.put("wasId", wasLst);
//                data.put("svrCls", Constants.stage);
//                wasService.execBatch(data);
//                if(!bChkParam.equals("")){
//                    bChk = bChkParam;
//                }
//            }
//            mData.get(j).put("bChk", bChk);
//            break;
        }
        return mData;
    }

    public List<Map<String, Object>> getDomainList(List<Map<String, Object>> mData){
        List<Map<String, Object>> mData2 = new ArrayList<Map<String, Object>>();
        try{
            String script = "";
            for(int i = 0; i < mData.size(); i++){
                if(script.indexOf(StringUtil.getText(mData.get(i).get("instcId"))) == -1){
                    script += mData.get(i).get("instcId") + ",";
                    mData2.add(mData.get(i));
                }
            }
        }catch(Exception e){
            logger.debug(e.getMessage());
        }
        return mData2;
    }

    /***
     *
     * 인스턴스로 등록 된 WAS, OGG, ECM의 상태는 체크하여 ICMS.CO_INSTC를 업데이트 한다.
     *
     * 1. WAS Check
     *   - 웹분산인 경우는 WAS가 아닌 Apache가 설치되어 있기 때문에 static한 자원(html, 이미지파일 등)을 호출하여 response code로 정상여부를 판단한다.
     *   - 본사통합, 해외분산, 부분분산인 경우는  WAS가 설치되어 있기 때문에 정의된(모두 동일) URL을 호춣하여 response code와 check 결과로 정상여부를 판단한다.
     *   - 정의된 URL은 DB에 쿼리를 수행하여 정상여부를 Check하여 결과를 반환한다.
     *   - response가 200(정상)이 아니거나, check결과가 false일 경우는 이상으로 판단한다.
     *   - 실패 시는 정의된 retry 횟수 만큼 재시도 한다.
     *
     * 2. ECM Check
     *   - 모든 분산환경에는 ECM이 설치되어 있기 때문에 모든 Site을 확인한다.
     *   - ECM port로 socket을 연결하여 성공여부로 정상여부를 판단한다.
     *
     * 3. OGG Check
     *   - 웹분산인 경우 로컬 DB가 없기 때문에 OGG Check를 하지 않고 항상 '-'이 된다.
     *   - 해외분산, 부분분산인 경우는 로컬 DB가 설치되어 있고, OGG도 설치되어 있다.
     *   - Process 정상 여부는 정의된 경로의 정의된 파일을 Open하여, 상태가 STOP이거나 ABENDED일 경우는 오류 상태로 판단한다.
     *   - Exception 발생 여부는 정의된 경로의 정의된 패턴 파일들의 크기를 이전과 비교하여, 이전보다 클경우는 오류 상태로 판단한다.
     *   - 본사 OGG(서버) 인 경우
     *   	. WAS와 OGG가 다른 서버(H/W)에 설치되어 있기 때문에, FTP를 이용하여 정의된 파일들을 조회하여 정상 여부를 판단한다.
     *   - 지사 OGG(Client) 인 경우
     *   	. WAS와 OGG가 다른 서버(H/W)에 설치되어 있다.
     *   	. 정의된(모두 동일) URL을 호춣하여 response code와 check 결과로 정상여부를 판단한다.
     *   	. 정의된 URL은  로컬의 정의된 파일들을 조회하여 정상 여부를 판단한다.
     *   	. response가 200이 아니거나, check결과가 false일 경우는 이상으로 판단한다.
     *   - 현재 OGG_OK_YN이 'N'일 경우는 해당 지사는 호출하지 않는다.(관리자가 복구 후 상태 변경해야 함)
     *
     */
    public void checkAllInstces(Map<String, Object> param){

    	logger.debug("BATCH_FLAG====>"+BATCH_FLAG);

    	if(BATCH_FLAG == true) {
    		logger.debug("Instances checking process was alreaey doing~!!!");
    		return;
    	}

    	synchronized(this) {

    		if(MAIN_OGG_DISCARD_FILE_MAP == null) {
    			MAIN_OGG_DISCARD_FILE_MAP = new HashMap<String, Object>();
    		}

    		BATCH_FLAG = true;
    		String startTime = DateUtil.getCurrentDateString("yyyy-MM-dd HH:mm:ss.SSS");

    		param.put("useYn", "Y");
	    	List<Map<String, Object>> instcList = service.retrieveInstcWas(param);

	    	logger.debug("=== Start health check ====");

	    	int cnt = 0;
	    	for(Map<String, Object> map : instcList) {

	    		cnt++;
	    		boolean isFault = false;
	    		String ip = StringUtil.getText(map.get("ip"));
	    		String instcNm = StringUtil.getText(map.get("instcNm"));
	    		String oprMethCls = StringUtil.getText(map.get("oprMethCls"));
	    		String ecmIp = StringUtil.getText(map.get("ecmIp"));
	    		String svcUrl = StringUtil.getText(map.get("svcUrl"));
	    		String oggIp = StringUtil.getText(map.get("oggIp"));
	    		String oggOkYn = StringUtil.getText(map.get("oggOkYn"));
	    		boolean hasWas = (!(oprMethCls.equals("03")));

	    		if(checkWas(svcUrl, hasWas)) {
	    			map.put("wasOkYn", "Y");
	    		} else {
	    			map.put("wasOkYn", "N");
	    			isFault = true;
	    		}

    			// ECM Check
	    		if(checkEcm(ecmIp)) {
	    			map.put("ecmOkYn", "Y");
	    		} else {
	    			map.put("ecmOkYn", "N");
	    			isFault = true;
	    		}

		    	// 웹분산인 경우는  OGG를 Check 하지 않는다
		    	if(hasWas) {

		    		// OGG의 상태가 N인 경우는 복구될 때 까지 처리하지 않는다.
		    		if(!oggOkYn.equals("N")) {

		    			String oggServerIp = StringUtil.getText(appProperties.get("dwe.ogg.server.ftp.host"));

		    			// OGG 서버 IP와 현재 인스턴스의 OGG IP가 같으면 서버로 인식한다.
		    			if(checkOgg((oggServerIp.equals(oggIp)), svcUrl)) {
			    			map.put("oggOkYn", "Y");
			    		} else {
			    			map.put("oggOkYn", "N");
			    			isFault = true;
			    		}
		    		}

	    		} else {
	    			map.put("oggOkYn", "N");
	    		}

	    		// DB Update
	    		dao.update("instc.updateInstcOkYn", map);

	    		// 오류 발생 시 메일 및 문자 발송
	    		if(isFault == true) {
	    			sendAlarm(map);
	    		}

	    		logger.debug(cnt + ". " + instcNm + " : " + "WAS(" + String.valueOf(map.get("wasOkYn"))+ "), "
	    												  + "ECM(" + String.valueOf(map.get("ecmOkYn"))+ "), "
	    												  + "OGG(" + String.valueOf(map.get("oggOkYn")) +")");
	    	}

	    	BATCH_FLAG = false;

	    	String finishTime = DateUtil.getCurrentDateString("yyyy-MM-dd HH:mm:ss.SSS");
	    	logger.debug("=== Finish instances status checking(" + startTime + " ~ " + finishTime +") ====");
    	}
    }

    /***
     * 특정 URL을 호출하여 WAS 또는 Webserver의 상태를 확인한다.
     *
     * @param svcUrl
     * @return
     */
    private boolean checkWas(String svcUrl, boolean hasWas) {

    	if(svcUrl.equals(""))
    		return false;

    	if(hasWas) {
        	svcUrl += StringUtil.getText(appProperties.get("dwe.was.check.url"));
        	return checkStatusByHttp(svcUrl, true, Integer.parseInt(StringUtil.getText(appProperties.get("dwe.was.check.retry"))));
    	} else  {
        	svcUrl += StringUtil.getText(appProperties.get("dwe.webserver.check.url"));
        	return checkStatusByHttp(svcUrl, false, Integer.parseInt(StringUtil.getText(appProperties.get("dwe.was.check.retry"))));
    	}
    }

    /***
     * ECM 서비스 포트로 접속하여 ECM 상태를 확인한다.
     *
     * @param ecmIp
     * @return
     */
    private boolean checkEcm(String ecmIp) {

    	boolean ret = false;
    	Socket soc = null;

    	String portStr = StringUtil.getText(appProperties.get("dwe.file.ecm.port"));
    	String retryStr = StringUtil.getText(appProperties.get("dwe.ecm.check.retry"));
    	String timeoutStr = StringUtil.getText(appProperties.get("dwe.ecm.check.timeout"));

    	int port = 2104;
    	int retry = 4;
    	int timeout = 5000;

    	if(!portStr.equals(""))
    		port = Integer.parseInt(portStr);

    	if(!retryStr.equals(""))
    		retry = Integer.parseInt(retryStr) + 1;

    	if(!timeoutStr.equals(""))
    		timeout = Integer.parseInt(timeoutStr);

    	for(int i=0; i<retry; i++) {

    		//String startTime = DateUtil.getCurrentDateString("yyyy-MM-dd HH:mm:ss.SSS");

	    	try {
	    		soc = new Socket();
	    		SocketAddress socAddr = new InetSocketAddress(ecmIp, port);
	    		soc.connect(socAddr, timeout);
	    		soc.close();
	    		ret = true;

	    		break;
	    	} catch (Exception e) {
	    		ret = false;

	    		//checkTimelogging(startTime, DateUtil.getCurrentDateString("yyyy-MM-dd HH:mm:ss.SSS"));
	    	}
    	}

    	return ret;
    }

    /***
     * OGG 상태를 Check 한다.
     *
     * @param isBase
     * @param svcUrl
     * @return
     */
    private boolean checkOgg(boolean isBase, String svcUrl) {
    	if(isBase)
    		return checkBaseOgg();
    	else
    		return checkSiteOgg(svcUrl);
    }

    /***
     * 특정 URL을 호출하여 지사 OGG 상태를 확인한다.
     *
     * @param svcUrl
     * @return
     */
    private boolean checkSiteOgg(String svcUrl) {
    	if(svcUrl.equals(""))
    		return false;

    	svcUrl += StringUtil.getText(appProperties.get("dwe.ogg.client.health.check.url"));
    	return checkStatusByHttp(svcUrl, true, Integer.parseInt(StringUtil.getText(appProperties.get("dwe.ogg.check.retry"))));
    }

    /***
     * 본사 OGG 상태를 Check 한다.
     * @return
     */
    private boolean checkBaseOgg() {
    	boolean ret = false;

    	String host = StringUtil.getText(appProperties.get("dwe.ogg.server.ftp.host"));
    	String user = StringUtil.getText(appProperties.get("dwe.ogg.server.ftp.user"));
    	String password = StringUtil.getText(appProperties.get("dwe.ogg.server.ftp.password"));
    	String encoding = StringUtil.getText(appProperties.get("dwe.ogg.server.ftp.encoding"));

    	try {

	    	FtpUtil ftpUtil = new FtpUtil(host, user, password, encoding);

	    	StringBuffer fileName = new StringBuffer();
	    	String today = DateUtil.getCurrentDateString("yyyyMMdd");

	    	fileName.append(StringUtil.getText(appProperties.get("dwe.ogg.server.process.check.file.dir")));
			fileName.append("/");
			fileName.append(StringUtil.getText(appProperties.get("dwe.ogg.process.check.file.prefix")));
			fileName.append(today);
			fileName.append(".");
			fileName.append(StringUtil.getText(appProperties.get("dwe.ogg.process.check.file.extension")));
			String result = ftpUtil.downloadFileToString(fileName.toString(), "temp_pstatus.dec");

			if(result == null || result == "")
				return false;

			if(result.indexOf(OGG_PROCESS_STOPPED) != -1 || result.indexOf(OGG_PROCESS_ABENDED) != -1) {
				ret = false;
			} else {
				ret = true;
			}

			logger.debug("=========> OGG Health Check Result : " + ret);

			// Process 상태가 정상일 경우
//			if(ret == true) {
//				return ret ;
//				String pattern = StringUtil.getText(appProperties.get("dwe.ogg.exception.check.filename.pattern"));
//				String filePath = StringUtil.getText(appProperties.get("dwe.ogg.server.exception.check.file.dir"));
//				Map<String, Object> resultMap = ftpUtil.getFileInfosByPattern(filePath, pattern);
//
//				if(resultMap != null) {
//					Set<String> set = resultMap.keySet();
//					Iterator<String> iterator = set.iterator();
//
//					while (iterator.hasNext()) {
//						String key = iterator.next();
//
//						if(MAIN_OGG_DISCARD_FILE_MAP.containsKey(key)) {
//							long preSize = Long.parseLong(String.valueOf(MAIN_OGG_DISCARD_FILE_MAP.get(key)));
//							long currSize = Long.parseLong(String.valueOf(resultMap.get(key)));
//
//							if(preSize < currSize) {
//								ret = false;
//							}
//						}
//
//						MAIN_OGG_DISCARD_FILE_MAP.put(key, resultMap.get(key));
//
//					}
//				} else {
//					ret = false;
//				}
//
//				logger.debug("=========> OGG Discard Check Result : " + ret);
//
//			}
//
    	} catch(Exception e) {
    		e.printStackTrace();
    		ret = false;
    	}
    	return ret;
    }

    /***
     * TRUE(true)/FALSE(false) 출력하는 URL을 호출하여 결과를 Return 한다.
     *
     * @param svcUrl
     * @return
     */
    private boolean checkStatusByHttp(String svcUrl, boolean isReturnCheck, int retryCnt) {

    	boolean ret = false;
    	HttpURLConnection connection = null;

    	try {
			URL url = new URL(svcUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setInstanceFollowRedirects(false);
			connection.connect();
			int responseCode = connection.getResponseCode();

			// 404 또는 503일 경우 retry
			if(responseCode == HttpURLConnection.HTTP_NOT_FOUND || responseCode == HttpURLConnection.HTTP_UNAVAILABLE) {
				for(int i=0; i<retryCnt; i++) {
					connection.connect();
					responseCode = connection.getResponseCode();

					if(responseCode == HttpURLConnection.HTTP_OK)
						break;
				}
			}

			if(responseCode == HttpURLConnection.HTTP_OK) {

				if(isReturnCheck) {
					//BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "US-ASCII"));
					BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
					StringBuffer sb = new StringBuffer();

					String strData = "";
					while ((strData = br.readLine()) != null) {
						sb.append(strData);
					}

					String responseData = sb.toString().replaceAll(" ", "").toUpperCase();

					if(responseData.equals("TRUE"))
						ret = true;
					else
						ret = false;
				} else {
					ret = true;
				}
			} else {
				ret = false;
			}

			connection.disconnect();
    	} catch(Exception e) {
    		ret = false;
    	} finally {
    		if(connection != null)
    			connection.disconnect();
    	}

    	return ret;
    }

    /***
     * 관리자에게 알람 메일 및 문자 메시지를 전송한다.
     *
     * @param paramMap
     */
    private void sendAlarm(Map<String, Object> paramMap) {

    	String instcId = StringUtil.getText(paramMap.get("instcId"));
    	String instcNm = StringUtil.getText(paramMap.get("instcNm"));
    	String wasOkYn = StringUtil.getText(paramMap.get("wasOkYn"));
    	String ecmOkYn = StringUtil.getText(paramMap.get("ecmOkYn"));
    	String oggOkYn = StringUtil.getText(paramMap.get("oggOkYn"));
    	String systemName = StringUtil.getText(appProperties.get("dwe.serverInfo.systemNm"));
    	String title = ERROR_MSG_TITLE;

    	boolean oggMailSendYn = Boolean.parseBoolean(StringUtil.getText(appProperties.get("dwe.ogg.mail.send.Yn")));
    	boolean oggSmsSendYn = Boolean.parseBoolean(StringUtil.getText(appProperties.get("dwe.ogg.sms.send.Yn")));

    	Map<String, Object> input = new HashMap<String, Object>();
    	input.put("instcId", instcId);
    	List<Map<String, Object>> wasDataList = wasService.retrieveWasList(input);

    	if(wasDataList == null || wasDataList.size() == 0)
    		return;

    	String emailUseYn = StringUtil.getText(wasDataList.get(0).get("emailUseYn"));
    	String smsUseYn = StringUtil.getText(wasDataList.get(0).get("smsUseYn"));

        List<Map<String, Object>> wasAdminList = wasService.retrieveWasAdmin(wasDataList.get(0));

        if(wasAdminList == null || wasAdminList.size() == 0)
        	return;

        // 등록된 관리자에게 이메일 및 문자메시지 전송
        for(Map<String, Object> wasAdminMap : wasAdminList) {

        	String tempEmailUseYn = emailUseYn;
        	String tempSmsUseYn = smsUseYn;
        	title = instcNm + " " + title;

        	// 다른 오류는 없고, ogg만 오류일 경우는 properties를 Check 한다.
        	if(oggOkYn.equals("N") && wasOkYn.equals("Y") && ecmOkYn.equals("Y")) {

        		if(oggMailSendYn == false)
        			tempEmailUseYn = "N";

        		if(oggSmsSendYn == false)
        			tempSmsUseYn = "N";
        	}

	        if(tempEmailUseYn.equals("Y")) {

	            input.put("spec", "default");
	            input.put("bodyTemplate", "sysMng");
	            input.put("reportType", REPORT_TYPE);
	            input.put("createDate", DateUtil.getCurrentDateString("yyyy/MM/dd HH:mm:ss")); // "20020717123456"
	            input.put("mailId", StringUtil.getText(wasAdminMap.get("email")));
	            StringBuffer content = new StringBuffer();
	            content.append(title);
	            content.append("<br>");

	            if(wasOkYn.equals("N")) {
	            	content.append("<br>");
	            	content.append(WAS_ERROR_MSG);
	            }

	            if(ecmOkYn.equals("N")) {
	            	content.append("<br> ");
	            	content.append(ECM_ERROR_MSG);
	            }

	            if(oggMailSendYn == true && oggOkYn.equals("N")) {
	            	content.append("<br> ");
	            	content.append(OGG_ERROR_MSG);
	            }

	            input.put("Content", content.toString());
	            input.put("mailSubject", title);
	            input.put("projectName", systemName);

	            logger.debug("E-Mail input map: " + input.toString());

	            try{
	            	mailUtil.sendMail(input);
	            }catch(Exception e){
	                logger.debug(e.getMessage());
	            }
	        }

	        if(tempSmsUseYn.equals("Y")){

	            input.put("rphone", StringUtil.getText(wasAdminMap.get("mphoneNo")));
	            input.put("recvname", StringUtil.getText(wasAdminMap.get("adminPernm")));
	            input.put("sendname", "cmas");
	            input.put("sendid", "SYSTEM");
	            input.put("sphone", "02-2288-3114");
	            StringBuffer msg = new StringBuffer();
	            msg.append(title);
	            msg.append("\r\n");

	            if(wasOkYn.equals("N")) {
	            	msg.append(WAS_ERROR_MSG);
	            	msg.append("\r\n");
	            }

	            if(ecmOkYn.equals("N")) {
	            	msg.append(ECM_ERROR_MSG);
	            	msg.append("\r\n");
	            }

	            if(oggSmsSendYn == true && oggOkYn.equals("N")) {
	            	msg.append(OGG_ERROR_MSG);
	            	msg.append("\r\n");
	            }

	            input.put("msg", msg.toString());

	            logger.debug("SMS input map: " + input.toString());

	          /*  try{
	                //SmsUtil.sendSms(input);
	            	//SmsUtil smsUtil = new SmsUtil();
                    List<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();
                    mData.add(input);
	            	smsService.sendSms(mData);
	            }catch(Exception e){
	                logger.debug(e.getMessage());
	            }*/
	        }
        }
    }


    ///////////////////////// 각 지사에서 인스 턴스 Check를 위해서 호출되는 Method /////////////////////////

    /***
     * DB 접속하여 쿼리를 수행하여 연결을 테스트 한다.
     * WAS가 정상적으로 동작하는지 확인하는 용도이다.
     *
     * @param input
     * @return
     */
    public boolean checkDBConnection(Map<String, Object> input) {

    	boolean ret = true;

    	try {
	    	dao.queryForMap("instc.retriveSysdate", new HashMap<String, Object>());
    	} catch(Exception e) {
    		ret = false;
    	}

    	return ret;
    }

    /***
     * 로컬에 설치된 OGG의 상태를 check 한다.
     *
     * 1. Process 상태 Check
     * 	- 정의된 경로의 정의된 파일을 Open하여, 상태가 STOP이거나 ABENDED일 경우는 오류 상태로 판단 함
     *
     * 2. Exception 여부 Check
     * 	- 정의된 경로의 정의된 패턴 파일들의 크기를 이전과 비교하여, 이전보다 클경우는 오류 상태로 판단 함
     *
     * @return
     */
    public boolean localOggCheck() {

    	boolean ret = false;
    	File file = null;

		try {

			/**
			 * 1. Check OGG Process Check
			 */
			int ch = 0;
			String today = DateUtil.getCurrentDateString("yyyyMMdd");

			StringBuffer fileName = new StringBuffer();
			fileName.append(StringUtil.getText(appProperties.get("dwe.ogg.client.process.check.file.dir")));
			fileName.append("/");
			fileName.append(StringUtil.getText(appProperties.get("dwe.ogg.process.check.file.prefix")));
			fileName.append(today);
			fileName.append(".");
			fileName.append(StringUtil.getText(appProperties.get("dwe.ogg.process.check.file.extension")));

			logger.debug("fileName===>"+fileName);

			file = new File(fileName.toString());
	    	FileReader in = new FileReader(file);
			StringBuffer sb = new StringBuffer();

			while((ch=in.read())!=-1) {
				sb.append((char)ch);
			}

			logger.debug(sb.toString());
			String result = sb.toString();

			if(result.indexOf(OGG_PROCESS_STOPPED) != -1 || result.indexOf(OGG_PROCESS_ABENDED) != -1) {
				ret = false;
			} else {
				ret = true;
			}

			in.close();

//			if(ret == true) {
//
//				/**
//				 * 2. Check OGG Exception Check
//				 */
//				file = new File(StringUtil.getText(appProperties.get("dwe.ogg.client.exception.check.file.dir")));
//
//				File[] subFiles = file.listFiles();
//				String pattern = StringUtil.getText(appProperties.get("dwe.ogg.exception.check.filename.pattern"));
//				Pattern p = Pattern.compile(pattern);
//
//				for(File subFile : subFiles) {
//
//					String subFileName = subFile.getName();
//					Matcher m = p.matcher(subFileName);
//
//					// pattern과 일치하는 경우, 정보를 별도의 Map에 저장한다.
//					if(m.find()) {
//
//
//						long currSize = subFile.length();
//
//						if(LOCL_OGG_DISCARD_FILE_MAP == null) {
//							LOCL_OGG_DISCARD_FILE_MAP = new HashMap<String, Object>();
//						}
//
//						if(LOCL_OGG_DISCARD_FILE_MAP.containsKey(subFileName)) {
//							long preSize = Long.parseLong(String.valueOf(LOCL_OGG_DISCARD_FILE_MAP.get(subFileName)));
//
//							if(preSize < currSize) {
//								ret = false;
//							}
//						}
//
//
//						LOCL_OGG_DISCARD_FILE_MAP.put(subFileName, currSize);
//					}
//				}
//			}

		} catch(Exception e) {
			e.printStackTrace();
			ret = false;
		}

    	return ret;
    }

    // For just logging
    private void checkTimelogging(String startTime, String finishTime) {
    	logger.debug("Start : " + startTime + " ~ " + "Finish : " + finishTime);
    }
}
