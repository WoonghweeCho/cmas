package com.dwenc.cmas.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통
 * 프로그램 : UnixUtil
 * 설      명 : java 소스에서 Unix 명령어 처리를 위한 Utility Class
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
public class UnixUtil {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(UnixUtil.class);

	/**
	 * <pre>
	 * Unix 시스템인지 확인 하는 메소드
     * 시스템 변수로 os의 이름이 명시되어 있어야 함
	 * </pre>
	 * @param request
	 * @param response
	 */
    public static boolean checkUnix(){
        String osName = " " + System.getProperty("os.name");
        logger.debug("checkUnix command:" + osName);
        if(osName.indexOf("Solaris") > 0)
            return true;
        if(osName.indexOf("AIX") > 0)
            return true;
        if(osName.indexOf("Unix") > 0)
            return true;
        if(osName.indexOf("HP-UX") > 0)
            return true;
        if(osName.indexOf("Linux") > 0)
            return true;
        return false;
    }

    /**
     * <pre>
     * execCommand(HashMap input)
     * </pre>
     * @param input
     * @throws Exception
     */
    public static void execCommand(HashMap input) throws Exception{
        String command = input.get("command").toString();
        logger.debug("execUnixCommand command:" + command);

        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec(command);
        } catch (Exception e) {
            logger.debug("execCommand return1 :" + e.getMessage());
            throw new Exception("execCommand command error 1!!!:" + command);
        }
    }

    /**
     * <pre>
     *  Unix 명령어 실행 Process를 새로 생성하여 명령어를 실행한다
     * </pre>
     * @param command : unix 명령어
     * @return
     * @throws Exception
     */
     public static String execUnixCommand(String command) throws Exception{
        logger.debug("execUnixCommand command:" + command);
        //if(!checkUnix()) return "";

        StringBuffer strReturn = new StringBuffer();
        Runtime rt = Runtime.getRuntime();
        Process ps = null;
        try {
            ps = rt.exec(command);
            ps.waitFor();
        } catch (Exception e) {
            logger.debug("execUnixCommand return1 :" + e.getMessage());
            throw new Exception("execUnixCommand command error 1!!!:" + command);
        }

        if(ps.exitValue() == 0) {
            BufferedReader br = new BufferedReader(new InputStreamReader(new SequenceInputStream(ps.getInputStream(), ps
                    .getErrorStream())));
            try {
                String readLine = null;
                while((readLine = br.readLine()) != null){
                    strReturn.append(readLine).append("\n");
                }
            } catch (IOException e) {
                logger.debug("execUnixCommand return2 :" + e.getMessage());
                throw new Exception("execUnixCommand command error 2!!!:" + command);
            }
            logger.debug("execUnixCommand return :" + strReturn);
        }
        return strReturn.toString();
    }

}
