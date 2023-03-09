package com.dwenc.cmas.common.accessLog.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import jcf.iam.core.common.util.UserInfoHolder;

import com.dwenc.cmas.common.accessLog.service.support.AccessLogUtil;
import com.dwenc.cmas.common.utils.ConfigUtil;

import docfbaro.common.Constants;
import docfbaro.common.DateUtil;
import docfbaro.common.MenuFactory;
import docfbaro.common.ObjUtil;
import docfbaro.common.SpringUtil;
import docfbaro.common.StringUtil;
import docfbaro.common.WebContext;
import docfbaro.iam.UserInfo;
import docfbaro.iam.authentication.UserDefinition;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 :
 * 설    명 : User의 접속 통계를 이용하기위해 Accesslog를 남기는 유틸
 * 작 성 자 : DWE
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
public class AccessLog extends PrintWriter {

    private static List appenders = new ArrayList();

    private static List<String> authList = new ArrayList<String>();

    private static HashMap appIdList = new HashMap();

    private static List logList = new ArrayList();

    private static int curAppId;

    private static AccessLog xfb;

    private static String instcId = "";

    private static String current = "";

    private static String sgm = ",";

    private static String nm = "\n";

    private static String log = Constants.accessLogDirectory;

    private static HttpServletRequest req;

    private static MenuFactory menuFactory;

    private static Properties configProperties;

    private static String hqInstcId = "";

    private static String command = "";

    private static String hqServerInfosProp = "";

    private static String myIp = "";

    private static String infix = "";

    private Properties appProperties;


    /**
     * <pre>
     *  AccessLog getInstance()
     * </pre>
     * @return
     */
    public static synchronized AccessLog getInstance(){
        try{
            menuFactory = (MenuFactory)SpringUtil.getBean(WebContext.getRequest(), "menuFactory");

            req = WebContext.getRequest();
            java.text.SimpleDateFormat formatter = null;
            formatter = new java.text.SimpleDateFormat("yyyyMMdd", java.util.Locale.KOREA);
            current = formatter.format(new java.util.Date());
            instcId = StringUtil.getText(UserInfo.getUserInfo().getEtcInfo().get("instcId"));
            if(instcId == null || instcId.equals(""))
                instcId = Constants.instcId;

            if(configProperties == null) {
            	configProperties = ConfigUtil.getProperties(null);
            	hqInstcId = String.valueOf(configProperties.get("dwe.accessLog.hq.instance.id"));
            	command = String.valueOf(configProperties.get("dwe.accessLog.hq.infix.command"));
            	hqServerInfosProp = String.valueOf(configProperties.get("dwe.accessLog.hq.server.infos"));
            	myIp = InetAddress.getLocalHost().getHostAddress();
            }

            // 현재 IP가 본사 IP와 동일할 때만 인스턴스 별 파일로 생성(웹분산일 경우도 WAS 서버명으로 로그파일이 생성되도록 수정)
            if(hqServerInfosProp.indexOf(myIp) > -1 && infix.equals(""))
            	infix = AccessLogUtil.getLogFileInfix(command);

            String logFileName = log + "/" + instcId + "_" + infix + current + ".txt";

            boolean bChk = false;
            Set dataKeySet = appIdList.keySet();
            Iterator dataIterator = dataKeySet.iterator();
            //int nCnt = 0;

            // 기 생성된 로그 Writer인지 확인
            while(dataIterator.hasNext()){
                String dataKey = dataIterator.next().toString();
                if(dataKey.equals(logFileName)){
                    bChk = true;

                    // PrinterWrite의
                    curAppId = Integer.parseInt(String.valueOf(appIdList.get(dataKey)));
                    //curAppId = nCnt;
                    break;
                }
                //nCnt++;
            }

            if(xfb == null || !bChk){
                xfb = new AccessLog(logFileName);
            }
            return xfb;
        }catch(Exception e){
            System.out.println("Error on loading AccessLog Instance!!!" + e);
            try{
                throw new Exception("Error on loading AccessLog Instance", e);
            }catch(Exception e1){
            }
            return xfb;
        }
    }

    /**
     * <pre>
     * AccessLog 기본 생성자.
     * </pre>
     * @param logFileName
     */
    public AccessLog(String logFileName) {
        super(System.out, true);

        try{
            File logFile = new File(logFileName.substring(0, logFileName.lastIndexOf("/")));
            if(!logFile.exists()){
                logFile.mkdirs();
            }

            FileOutputStream fos = new FileOutputStream(logFileName, true);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            PrintStream out = new PrintStream(bos, true);
            appenders.add(new PrintWriter(out, true));
            //appIdList.put(logFileName, appenders.size());
            appIdList.put(logFileName, (appenders.size()-1));

            // 새로 생성한 Pritwriter의 Index
            curAppId = appIdList.size() - 1;
        }catch(FileNotFoundException e){
            System.out.println("Error on loading AccessLog!!!" + e);
        }
    }

    /**
     * <pre>
     * Accesslog 를 File에 Write하는 메소드
     * </pre>
     * @param strInput
     */
    public String writeLog(String strInput){
        UserDefinition userData = UserInfo.getUserInfo();
        if(userData == null)
            return "";
        if(Constants.accessLogTypes.indexOf("cmd") > -1) {
            if(userData.getGuid() == null){
                System.out.println("TimeLogInterceptor 활성화 필요");
                return "";
            }
        }

        //접속 시간을 기록
        String strLog = getTime();
        if(!strLog.startsWith(current)){
            System.out.println("date changed!!! : " + current);
            //String logFileName = log + "/" + instcId + "_" + strLog.substring(0, 8) + ".txt";
            String logFileName = log + "/" + instcId + "_" + infix + strLog.substring(0, 8) + ".txt";
            new AccessLog(logFileName);
        }

        //업무모듈별 Prefix 검사 : Prefix가 없을 경우 co로 처리
        if(strInput.indexOf(nm) == -1){
            String requestUrl = req.getRequestURI();
            if(requestUrl.equals("/co/common/accessLog/writeAccessLogForMenu.co"))
                requestUrl = "menu_Open";
            if(checkFilterUrl(requestUrl) == false)
                return "";
            String sType = "cmd";
            if(req.getParameter("sType") != null && req.getParameter("sType").equals("ui")){
                sType = "ui";
            }

            String dutySysCd = StringUtil.getText(req.getParameter("dutySysCd"));

            strLog = getLog(requestUrl, "", userData.getAccessTm(), sType, dutySysCd);
        }else{
            strLog = strInput;
        }

        if(strLog == null)
            return "";

        //Session 정보의 logStart(true, false) 여부에 따라 로깅처리
        if(StringUtil.getText(userData.getEtcInfo().get("logStart")).equals("true")) {
            logList.add(strLog);
        } else {
            logList.clear();
            userData.getEtcInfo().put("logStart", "false");
        }

        PrintWriter appender = (PrintWriter)appenders.get(curAppId);
        appender.print(strLog);
        appender.flush();
        return strLog;
    }

    /**
     * <pre>
     * Accesslog 를 File에 Write하는 메소드
     * </pre>
     * @param strInput
     */
    public void write(String strInput){
        synchronized(lock){
            PrintWriter appender = (PrintWriter)appenders.get(curAppId);
            appender.print(strInput);
            appender.flush();
        }
    }

    /**
     * <pre>
     * Accesslog 를 File에 Write하는 메소드
     * </pre>
     * @param requestPath
     * @param startTime
     * @param elapsTime
     * @param sType
     */
    public String getLog(String requestPath, String startTime, String elapsTime, String sType, String dutySysCd){
        synchronized(lock){
            String strLog = "";
            if(startTime != null && !startTime.equals("")){
                strLog = startTime;
            }else{
                strLog = getTime();
            }
            UserDefinition userData = UserInfo.getUserInfo();
            if(userData == null)
                return null;
            if(userData.getUserId() == null)
                return null;
            String menuId = userData.getMenuId();
            String menuCd = userData.getMenuCd();

            String guid = "";
            if(Constants.accessLogTypes.indexOf("cmd") > -1) {
                if(userData.getGuid() == null){
                    System.out.println("TimeLogInterceptor 활성화 필요");
                    return null;
                }
                guid = userData.getGuid(); // guid
                if(requestPath.equals("menu_Open")){
                    guid = guid.replace("onload", requestPath);
                }
                if(guid.indexOf("menu_Open") > -1){
                    if(requestPath.indexOf("menu_Open") == -1)
                        return null;
                    if(sType.equals("db")){
                        return null;
                    }else{
                        elapsTime = "0.00";
                    }
                }
                if(Float.parseFloat(elapsTime) < 0){
                    elapsTime = "0.00";
                    System.out.println("getLog == elapsTime 에러" + requestPath);
                }
                menuId = guid.substring(0, guid.indexOf("-"));
            }
            String menuNm = StringUtil.getText(menuFactory.getMenuInfo("ko_KR", menuFactory.getMenuId(menuCd)).get(
                    "menuNm"));

            strLog += sgm + StringUtil.getText(userData.getEtcInfo().get("sysCd")); // sysCd
            strLog += sgm + requestPath; // menuUrl
            strLog += sgm + userData.getAccessTm();
            strLog += sgm + menuId;
            strLog += sgm + userData.getIpAddress(); // ip
            strLog += sgm + guid;
            strLog += sgm + menuNm.replaceAll(",", "$$");
            strLog += sgm + userData.getUserId();
            strLog += sgm + userData.getUserNm().replaceAll(",", "$$");;
            strLog += sgm + StringUtil.getText(userData.getOrgInfo().get("hdofcOrgCd")); // hgrOrgCd, centerOrgCd 상위 부서 코드
            strLog += sgm + StringUtil.getText(userData.getOrgInfo().get("orgCd"));
            strLog += sgm + StringUtil.getText(userData.getOrgInfo().get("orgNm")).replaceAll(",", "$$");;
            strLog += sgm + elapsTime;
            strLog += sgm + sType;
            strLog += sgm + Constants.instcId;
            strLog += sgm + dutySysCd;
            strLog += sgm + menuCd + nm;

            return strLog;
        }
    }

    /**
     * <pre>
     * Accesslog 를 File에 Write하는 메소드
     * </pre>
     * @param requestPath
     * @param startTime
     * @param elapsTime
     * @param sType
     */
    public String write(String requestPath, String startTime, String elapsTime, String sType){
        UserDefinition userData = UserInfo.getUserInfo();
        String guid = userData.getGuid(); // guid
        if(Constants.accessLogTypes.indexOf("cmd") > -1) {
            if(checkFilterUrl(requestPath + guid) == false)
                return "";
        }
        String types = Constants.accessLogTypes;
        if(types.indexOf(sType) == -1)
            return "";
        String rowcount = "0";
        if(!ObjUtil.isNull(req.getParameter("svcAct"))){
            requestPath = req.getParameter("svcAct");
            if(requestPath.indexOf("rowcount") > -1){
                try{
                    rowcount = requestPath.substring(requestPath.indexOf("rowcount") + "rowcount".length() + 1,
                            requestPath.indexOf("|", requestPath.indexOf("rowcount") + 1));
                }catch(Exception e){
                }
            }
            requestPath = requestPath.substring(requestPath.indexOf("!") + 1, requestPath.indexOf("$"));
            if(!ObjUtil.isNull(req.getParameter("rowcount"))){
                rowcount = req.getParameter("rowcount");
            }
            requestPath += "?rowcount=" + rowcount;
            sType = "ui";
        }

        String dutySysCd = StringUtil.getText(req.getParameter("dutySysCd"));

        String strLog = AccessLog.getInstance().getLog(requestPath, startTime, elapsTime, sType, dutySysCd);
        if(strLog != null)
            AccessLog.getInstance().write(strLog);

//        String clientIp = req.getHeader("Proxy-Client-IP");
//        if(clientIp == null){
//            clientIp = req.getHeader("WL-Proxy-Client-IP");
//            if(clientIp == null){
//                clientIp = req.getHeader("X-Forwarded-For");
//                if(clientIp == null){
//                    clientIp = req.getRemoteAddr();
//                }
//            }
//        }
//
//        String urlPath = req.getRequestURL().toString();
//        StringBuffer sb = new StringBuffer();
//        sb.append(" *******************************************************************************************\n");
//        sb.append(" * Request Date : " + DateUtil.getCurrentDateString("yyyyMMddHHmmss") + "\n");
//        sb.append(" * Request URL : " + urlPath + "(" + sType + ")\n");
//        sb.append(" * Request Client IP : " + clientIp + "\n");
//        sb.append(" *------------------------------------------------------------------------------------------\n");
//        sb.append(" * Elapsed Time : " + elapsTime + "\n");
//        sb.append(" *******************************************************************************************\n");
//        System.out.println(sb.toString());
        return strLog;
    }

    /**
     * <pre>
     * 접속 시간을 기록 하기 위한 메소드
     * </pre>
     * @return formatter.format(new java.util.Date()) 지정된 형식의 date
     */
    public static String getTime(){
        java.text.SimpleDateFormat formatter = null;
        formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmssSS", java.util.Locale.KOREA);
        return formatter.format(new java.util.Date());
    }

    /**
     * <pre>
     * 접속 시간을 기록 하기 위한 메소드
     * </pre>
     */
    public static String printTime(String strMsg, String strStartTime){
        String strEndTime = AccessLog.getTime();
        float fGap = DateUtil.getTimeGap(strStartTime, AccessLog.getTime());
        System.out.println("[delayTime]" + strMsg + "========" + fGap);
        return strEndTime;
    }

    /**
     * <pre>
     * Write a portion of an array of characters.
     * </pre>
     * @param buf - Array of characters
     * @param off - Offset from which to start writing characters
     * @param len - Number of characters to write
     */
    public void write(char buf[], int off, int len){
        if(buf == null){
            throw new NullPointerException();
        }else if((off < 0) || (off > buf.length) || (len < 0) || ((off + len) > buf.length) || ((off + len) < 0)){
            throw new IndexOutOfBoundsException();
        }else if(len == 0){
            return;
        }
        for(int i = 0; i < len; i++){
            write(buf[off + i]);
        }
    }

    /**
     * <pre>
     * Write an array of characters. This method cannot be inherited from the
     * Writer class because it must suppress I/O exceptions.
     * </pre>
     * @param buf - Array of characters to be written
     */
    public void write(char buf[]){
        write(buf, 0, buf.length);
    }

    /**
     * <pre>
     * Terminate the current line by writing the line separator string. The line
     * separator string is defined by the system property
     * <code>line.separator</code>, and is not necessarily a single newline character (<code>'\n'</code>).
     * </pre>
     */
    public void println(){}

    /**
     * <pre>
     * Print a boolean value and then terminate the line. This method behaves as
     * though it invokes <code>{@link #print(boolean)}</code> and then <code>{@link #println()}</code>.
     * </pre>
     * @param x - the <code>boolean</code> value to be printed
     */
    public void println(boolean x){
        write(x ? "true" : "false");
    }

    /**
     * <pre>
     * Print a character and then terminate the line. This method behaves as
     * though it invokes <code>{@link #print(char)}</code> and then <code>{@link #println()}</code>.
     * </pre>
     *
     * @param x - the <code>char</code> value to be printed
     */
    public void println(char x){
        write(x);
    }

    /**
     * <pre>
     * Print an integer and then terminate the line. This method behaves as
     * though it invokes <code>{@link #print(int)}</code> and then <code>{@link #println()}</code>.
     * </pre>
     *
     * @param x - the <code>int</code> value to be printed
     */
    public void println(int x){
        write(x);
    }

    /**
     * <pre>
     * Print a long integer and then terminate the line. This method behaves as
     * though it invokes <code>{@link #print(long)}</code> and then <code>{@link #println()}</code>.
     *
     *
     * @param x - the <code>long</code> value to be printed
     */
    public void println(long x){
        write(String.valueOf(x));
    }

    /**
     * <pre>
     * Print a floating-point number and then terminate the line. This method
     * behaves as though it invokes <code>{@link #print(float)}</code> and then <code>{@link #println()}</code>.
     * </pre>
     * @param x - the <code>float</code> value to be printed
     */
    public void println(float x){
        write(String.valueOf(x));
    }

    /**
     * <pre>
     * Print a double-precision floating-point number and then terminate the
     * line. This method behaves as though it invokes <code>{@link #print(double)}</code> and then <code>{@link #println()}</code>.
     * </pre>
     * @param x - the <code>double</code> value to be printed
     */
    public void println(double x){
        write(String.valueOf(x));
    }

    /**
     * <pre>
     * Print an array of characters and then terminate the line. This method
     * behaves as though it invokes <code>{@link #print(char[])}</code> and then <code>{@link #println()}</code>.
     * </pre>
     * @param x - the array of <code>char</code> values to be printed
     */
    public void println(char x[]){
        write(x);
    }

    /**
     * <pre>
     * Print a String and then terminate the line. This method behaves as though
     * it invokes <code>{@link #print(String)}</code> and then <code>{@link #println()}</code>.
     * </pre>
     * @param x - the <code>String</code> value to be printed
     */
    public void println(String x){
        write(x);
    }

    /**
     * <pre>
     * Print an Object and then terminate the line. This method behaves as
     * though it invokes <code>{@link #print(Object)}</code> and then <code>{@link #println()}</code>.
     * </pre>
     * @param x - the <code>Object</code> value to be printed
     */
    public void println(Object x){
        write(String.valueOf(x));
    }

    /**
     * <pre>
     * 체크에서 제외할 URL 목록을 로딩.
     * </pre>
     * @param url
     */
    public boolean checkFilterUrl(String url){
        if(url.indexOf("!") > 0)
            url = url.substring(url.indexOf("!") + 1, url.length());
        if(!url.startsWith("/"))
            url = "/" + url;
        boolean bChk = false;
        if(Constants.accessLogTypes.indexOf("cmd") > -1) {
            String include = Constants.accessLogFilterInclude;
            String includeArry[] = include.split(",");
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
        } else {
            bChk = true; // O
        }
        String exclude = Constants.accessLogFilterExclude;
        String excludeArry[] = exclude.split(",");
        if(!exclude.trim().equals("")){
            if(exclude.trim().equals("*"))
                return false;
            for(int i = 0; i < excludeArry.length; i++){
                if(url.indexOf(StringUtil.Trim(excludeArry[i])) > -1){
                    bChk = false; // X
                    break;
                }
            }
        }
        return bChk; // X
    }

    /**
     * 사용자의 Session 정보에 logStart를 true로 설정
     * logStart의 true, false 값 여부에 따라 Access 로그 파일을 저장, 초기화 한다.
     */
    public static void setLogStart(){
        HashMap ectInfo = UserInfo.getUserInfo().getEtcInfo();
        ectInfo.put("logStart", "true");
        UserInfo.getUserInfo().setEtcInfo(ectInfo);
    }

    /**
     * 사용자의 Session 정보에 logStart를 true로 설정
     * logStart의 true, false 값 여부에 따라 Access 로그 파일을 저장, 초기화 한다.
     */
    public static void setLogEnd(){
        HashMap ectInfo = UserInfo.getUserInfo().getEtcInfo();
        ectInfo.put("logStart", "false");
        UserInfo.getUserInfo().setEtcInfo(ectInfo);
        logList = new ArrayList();
    }

    /**
     * 로깅대상의 List를 반환한다.
     * @return
     */
    public static List getLogList(){
        return logList;
    }

    /**
     * 사용자 세션정보에서 logStart 값을 반환한다.
     * @return
     */
    public static String getLogStart(){
        return StringUtil.getText(UserInfo.getUserInfo().getEtcInfo().get("logStart"));
    }

    /**
     * 사용자 Session의 "accessLogManual" 값을 통해 logStart 유효성을 검증한다.
     * @return
     */
    public static boolean validLogStart(){
        if(UserInfoHolder.getUserInfo(UserDefinition.class) == null) return false;
        // 화면에서 호출하는 로깅을 사용할 경우 매뉴얼로 기록을 클릭할 경우에만 로깅함
        if(StringUtil.getText(UserInfo.getUserInfo().getEtcInfo().get("accessLogManual")).equals("true")) {
            if(!AccessLog.getLogStart().equals("true")) {
                return false;
            }
        }
        return true;
    }

    /**
     * 로그 리스트 중 인증관련 리스트를 반환한다.
     * @return
     */
    public static List<String> getAuthList(){
        return authList;
    }

    /**
     * 로그 리스트 중 인증관련 리스트를 AccessLog Class에 저장한다.
     * @param authList
     */
    public static void setAuthList(List<String> authList){
        AccessLog.authList = authList;
    }


    /***
     *
     *	Access Log를 설정된 위치에 Zip 파일로 압축한다.
     *
     * 	@param appProperties
     * 	@return
     */
    public static String downloadAccessLog(Map<String, Object> paramMap, Properties appProperties) {

    	String result = "";

    	try {

    		// 자신의 instance ID를 가져온다.
    		String instcId1 = StringUtil.getText(appProperties.get("dwe.serverInfo.instcId"));
    		String docRoot = StringUtil.getText(appProperties.get("dwe.serverInfo.docRoot"));

            String infix = "";

            if(configProperties == null) {
            	configProperties = ConfigUtil.getProperties(null);
            	hqInstcId = String.valueOf(configProperties.get("dwe.accessLog.hq.instance.id"));
            }

            String queryExecStart = String.valueOf(paramMap.get("queryExecStart"));
            String queryExecEnd = String.valueOf(paramMap.get("queryExecEnd"));

            if(instcId1.equals(hqInstcId))
            	infix = AccessLogUtil.getLogFileInfix(instcId1);

            java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd", java.util.Locale.KOREA);
            Date startDate = formatter.parse(queryExecStart);
            Date endDate = formatter.parse(queryExecEnd);
            Calendar cal = Calendar.getInstance();

    		// 압축할 폴더 생성
    		String dirName = instcId1 + "_" + queryExecStart + "_" + queryExecEnd;
    		String dirPath = log + "/" + dirName;
    		File zipDir = new File(dirPath);

    		if(!zipDir.exists())
    			zipDir.mkdirs();

    		// 요청된 일자를 loop돌면서 encoding 변환 시킴
    		// 해당 파일을 압축 directory에 copy 시킴

    		Date date = startDate;
			String encoding = System.getProperty("file.encoding");
			String serverEncoding = String.valueOf(configProperties.get("dwe.accessLog.hq.server.encoding"));

    		while(endDate.compareTo(date) > -1) {

    			String dateStr = formatter.format(date);

                String fileName = instcId1 + "_" + infix + dateStr;
                String source = log + "/" + fileName + ".txt";

				// Web Root에 copy 시킨다.
				File originFile = new File(source);

				if(originFile != null) {
					File copyFile = new File(dirPath + "/"  + fileName + ".txt");
					AccessLogUtil.copyFileByEncoding(originFile, copyFile, encoding, serverEncoding);
				}

        		cal.setTime(date);
        		cal.add(Calendar.DATE, 1);
        		date = cal.getTime();
    		}

            // 해당 경로의 모든 로그 관련 zip 파일을 삭제
            File file = new File(docRoot);
            File [] files = file.listFiles();

            for(File f : files) {
            	if(f.getName().indexOf(".zip") > -1 && f.getName().indexOf(instcId1) > -1) {
            		f.delete();
            	}
            }

	    	result = "/" + dirName + ".zip";

	    	AccessLogUtil.zip(dirPath, (docRoot + result));

    		if(zipDir != null)
    			AccessLogUtil.deleteDirectory(zipDir);

    	} catch (Exception e) {
    		e.printStackTrace();
    		result = "";
    	}

    	return result;
    }
}
