package com.dwenc.cmas.common.menu.service.support;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : ExtractUrlFromXfdUtil
 * 설    명 : XFDL로부터 URL을 추출하는 유틸
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
@Service
public class ExtractUrlFromXfdUtil {

    /**
     * log 처리를 위한 변수 선언
     */
    private final Logger logger = LoggerFactory.getLogger(ExtractUrlFromXfdUtil.class);

    private List list = new ArrayList();

    public void resetList(){
        this.list = new ArrayList();
    }

    /**
     * <pre>
     *  XFDL로부터 URL을 추출한다.
     * </pre>
     */
    public List<Map<String, Object>> extractAll(String path){
        List<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();

        File file = new File(path);
        File afile[] = file.listFiles();
        try{
            for(int i = 0; i < afile.length; i++){
                if(afile[i].isDirectory()){
                    String s1 = afile[i].getPath();
                    extractAll(s1);
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
                    if(extend.equals("xfdl")){
                        String filePath = file2.getAbsolutePath();
                        resetList();
                        mData = extraceUrl(mData, filePath);
                    }
                }
            }
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return mData;
    }

    /**
     * <pre>
     *  XFDL로부터 URL을 추출
     * </pre>
     * @throws IOException
     */
    public List<Map<String, Object>> extraceUrl(List<Map<String, Object>> mData, String filePath) throws Exception{
        String fileNm = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.indexOf(".xfdl")).toLowerCase();
        InputStreamReader is = new InputStreamReader(new FileInputStream(filePath));
        BufferedReader in = new BufferedReader(is);
        String strStr = "";

        Map<String, Object> input = new HashMap<String, Object>();
        int readed = 0;
        while((strStr = in.readLine()) != null){
            if(strStr.indexOf(".xpl") > -1 && strStr.indexOf(fileNm) == -1){
                strStr = strStr.substring(strStr.indexOf("\"") + 1, strStr.indexOf(".xpl") + 4);
                if(!list.contains(fileNm)){
                    System.out.println("fileNm -> " + fileNm + " / " + strStr + " -> " + strStr);
                    list.add(fileNm);
                    input.put("menuUrl", fileNm);
                    input.put("actionUrl", strStr);
                    mData.add(input);
                }
            }else if(strStr.indexOf("Tabpage") > -1 && strStr.indexOf("url=") > -1){
                strStr = strStr.substring(strStr.indexOf("::") + 2, strStr.indexOf(".xfdl") + 5);
                filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1) + strStr;
                extraceUrl(mData, filePath);
            }
        }
        return mData;
    }

    /**
     * <pre>
     * </pre>
     * @param args
     * @throws IOException
     */
    public static void main(String[] args){
        try{
            ExtractUrlFromXfdUtil conv = new ExtractUrlFromXfdUtil();
            List<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();
            String filePath = "C:/DoCFBaro-IDE/workspace/daewooenc.cmas/src/main/webapp/xui/co/coa/COA0201.xfdl";
            filePath = "C:/DoCFBaro-IDE/workspace/daewooenc.cmas/src/main/webapp/xui/pm/pma/PMA0201.xfdl";
            conv.resetList();
            mData = conv.extraceUrl(mData, filePath);

            String xuiPath = ":/DoCFBaro-IDE/workspace/daewooenc.cmas/src/main/webapp/xui";
            mData = conv.extractAll(xuiPath);
            System.out.println("");
        }catch(Exception e){
        }
    }
}
