package com.dwenc.cmas.common.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import docfbaro.common.FileUtil;
import docfbaro.common.StringUtil;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통
 * 프로그램 : ConfigUtil
 * 설      명 : 다국어 처리 공통 클래스
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
public class ConvertMLang {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(ConvertMLang.class);

    private String[] includes;

    private String include;

    public String getInclude(){
        return include;
    }

    private List list = new ArrayList();
    private List fulList = new ArrayList();

    public void setInclude(String include){
        includes = StringUtil.split(include, ",");
        this.include = include;
    }

    /**
     * <pre>
     *  DB에 등록된 MLANG 정보를 Xplatform에 전달하기 위해 변환함다.
     * </pre>
     * @param aFrom
     * @param aTo
     * @param path
     */
    public void extract(String path){
        File file = new File(path);
        File afile[] = file.listFiles();
        try{
            for(int i = 0; i < afile.length; i++){
                if(afile[i].isDirectory()){
                    String s1 = afile[i].getPath();
                    extract(s1);
                }else{
                    File file2 = afile[i];
                    String fileName = file2.getName();
                    String extend = "";
                    if(fileName.indexOf(".", fileName.length() - 11) > -1){
                        extend = fileName.substring(fileName.indexOf(".", fileName.length() - 11) + 1,
                                fileName.length());
                    }else{
                        extend = fileName.substring(fileName.indexOf(".") + 1, fileName.length());
                    }

                    if(fileName.toUpperCase().startsWith("MM")
                            || fileName.toUpperCase().startsWith("LL")
                            || fileName.toUpperCase().startsWith("HMF")
                            || file2.getAbsolutePath().indexOf("framework") > -1
                            || file2.getAbsolutePath().indexOf("template") > -1) {
                        continue;
                    }
                    if(checkAllowed(extend) && !file2.getName().startsWith("IA_")){
                        String strStr = FileUtil.getFromFile(file2.getAbsolutePath()).toString();

                        if("java".equals(extend) && strStr.indexOf("throw new ") > -1){
                            String strArry[] = strStr.split("throw new ");
                            for(int x=1;x<strArry.length;x++) {
                                String tmp = strArry[x];
                                if(tmp.indexOf("\"", 1) > -1) {
                                    tmp = tmp.substring(tmp.indexOf("\"") + 1, tmp.indexOf("\"", tmp.indexOf("\"") + 1));
                                    if(!list.contains(tmp)) {
                                        list.add(tmp);
                                        tmp = file2.getName() + "^" + tmp;
                                        if(tmp.indexOf("\n") == -1) {
                                            fulList.add(tmp);
                                            System.out.println(tmp);
                                        }
                                    }
                                }
                            }
                        }

                        if("xfdl".equals(extend) && strStr.indexOf("text=") > -1){
                            String strArry[] = strStr.split("text=");
                            for(int x=1;x<strArry.length;x++) {
                                String tmp = strArry[x];
                                if(tmp.indexOf("\"", 1) > -1
                                        && !tmp.startsWith("&quot;")
                                        && !tmp.startsWith("\"bind:")
                                        && !tmp.startsWith("\"expr:")) {
                                    tmp = tmp.substring(1, tmp.indexOf("\"", 1));
                                    if(!list.contains(tmp)) {
                                        list.add(tmp);
                                        tmp = file2.getName() + "^" + tmp;
                                        if(tmp.indexOf("\n") == -1) {
                                            fulList.add(tmp);
                                            System.out.println(tmp);
                                        }
                                    }
                                }
                            }
                        }
                        if("xfdl".equals(extend) && strStr.indexOf("titletext=") > -1){
                            String strArry[] = strStr.split("titletext=");
                            for(int x=1;x<strArry.length;x++) {
                                String tmp = strArry[x];
                                if(tmp.indexOf("\"", 1) > -1
                                        && !tmp.startsWith("&quot;")
                                        && !tmp.startsWith("\"bind:")
                                        && !tmp.startsWith("\"expr:")) {
                                    tmp = tmp.substring(1, tmp.indexOf("\"", 1));
                                    if(!list.contains(tmp)) {
                                        list.add(tmp);
                                        tmp = file2.getName() + "^" + tmp;
                                        if(tmp.indexOf("\n") == -1) {
                                            fulList.add(tmp);
                                            System.out.println(tmp);
                                        }
                                    }
                                }
                            }
                        }
                        if("xfdl".equals(extend) && strStr.indexOf("gf_AlertMsg") > -1){
                            String strArry[] = strStr.split("gf_AlertMsg");
                            for(int x=1;x<strArry.length;x++) {
                                String tmp = strArry[x];
                                if(tmp.indexOf("\"", 1) > -1 || tmp.indexOf("'", 1) > -1) {
                                    if(tmp.startsWith("(\"")) {
                                        tmp = tmp.substring(2, tmp.indexOf("\"", 2));
                                    } else if(tmp.startsWith("('")) {
                                        tmp = tmp.substring(2, tmp.indexOf("'", 2));
                                    }
                                    if(!list.contains(tmp)) {
                                        list.add(tmp);
                                        tmp = file2.getName() + "^" + tmp;
                                        if(tmp.indexOf("\n") == -1) {
                                            fulList.add(tmp);
                                            System.out.println(tmp);
                                        }
                                    }
                                }
                            }
                        }
                        if("xfdl".equals(extend) && strStr.indexOf("gf_ConfirmMsg") > -1){
                            String strArry[] = strStr.split("gf_ConfirmMsg");
                            for(int x=1;x<strArry.length;x++) {
                                String tmp = strArry[x];
                                if(tmp.indexOf("\"", 1) > -1 || tmp.indexOf("'", 1) > -1) {
                                    if(tmp.startsWith("(\"")) {
                                        tmp = tmp.substring(2, tmp.indexOf("\"", 2));
                                    } else if(tmp.startsWith("('")) {
                                        tmp = tmp.substring(2, tmp.indexOf("'", 2));
                                    }
                                    if(!list.contains(tmp)) {
                                        list.add(tmp);
                                        tmp = file2.getName() + "^" + tmp;
                                        if(tmp.indexOf("\n") == -1) {
                                            fulList.add(tmp);
                                            System.out.println(tmp);
                                        }
                                    }
                                }
                            }
                        }
                        if("xfdl".equals(extend) && strStr.indexOf("alert") > -1){
                            String strArry[] = strStr.split("alert");
                            boolean bChk = false;
                            for(int x=1;x<strArry.length;x++) {
                                bChk = false;
                                String tmp = strArry[x];
                                if(tmp.indexOf("\"", 1) > -1 || tmp.indexOf("'", 1) > -1) {
                                    if(tmp.startsWith("(\"")) {
                                        tmp = tmp.substring(2, tmp.indexOf("\"", 2));
                                        bChk = true;
                                    } else if(tmp.startsWith("('")) {
                                        tmp = tmp.substring(2, tmp.indexOf("'", 2));
                                        bChk = true;
                                    }
                                    if(bChk && !list.contains(tmp)) {
                                        list.add(tmp);
                                        tmp = file2.getName() + "^" + tmp;
                                        if(tmp.indexOf("\n") == -1) {
                                            fulList.add(tmp);
                                            System.out.println(tmp);
                                        }
                                    }
                                }
                            }
                        }
                        //validExp='
                    }

                }
            }
        }catch(Exception e){
            logger.error(e.getMessage());
        }
    }

    /**
     * <pre>
     * 현재 요청 파일명이 적용이 필요한 지 판단
     * </pre>
     * @param current
     */
    protected boolean checkAllowed(String current){
        if(includes == null){
            return true;
        }
        for(int i = 0; i < includes.length; i++){
            if(current.equals(includes[i]))
                return true;
        }
        return false;
    }

    /**
     * <pre>
     * main(String[] args)
     * </pre>
     * @param args
     */
    public static void main(String[] args){
        ConvertMLang conv = new ConvertMLang();
        conv.setInclude("xfdl,xjs");
        conv.extract("C:/DoCFBaro-IDE/workspace/daewooenc.cmas/src/main/webapp/xui/sp");
        conv.setInclude("java");
        conv.extract("C:/DoCFBaro-IDE/workspace/daewooenc.cmas/src/main/java/sp");
        System.out.println("Convert Success!!");
    }
}
