package com.dwenc.cmas.common.sysMng.service;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통모듈
 * 프로그램 : SyncService
 * 설    명 : 해외 현장의 db정보와 동기화 처리하는 서비스
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
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.lang.time.StopWatch;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.metaData.service.OracleMetaDataService;
import com.dwenc.cmas.common.utils.ConfigUtil;

import docfbaro.common.Constants;
import docfbaro.common.StringUtil;

@Service
public class SyncService {

    private static Logger logger = LoggerFactory.getLogger(SyncService.class);

    @Autowired
    private OracleMetaDataService service;

    private Properties appProperties = null;

    private FileOutputStream fos = null;

    private BufferedOutputStream bos = null;

    private PrintStream out = null;

    private boolean logFlag = false;

    private String schema = "center";

    private static long maxCnt = 3000;

    private static List appenders = new ArrayList();

    private static List<String> appIdList = new ArrayList<String>();

    /**
     * 본사와 현장 간의 데이터 비교
     * 1. 쿼리 생성
     * 2. 본사 - 현장 쿼리
     * 3. 결과 비교
     */
    public List<Object> compareSite(List<String> aTableList, List<String> siteList, Map<String, Object> input){
        List<Object> listAll = new ArrayList<Object>();
        try{
            if(service == null)
                service = new OracleMetaDataService();

            // environment-offshore-database.properties 임의 로딩
            appProperties = ConfigUtil.getProperties(appProperties);
            service.setAppProperties(appProperties);

            for(int i = 0; i < aTableList.size(); i++){
                Map<String, Object> tableList = new HashMap<String, Object>();
                String tableId = StringUtil.getText(aTableList.get(i));
                tableList.put(tableId, tableId);
                if(tableId.startsWith("CO")){
                    schema = "csys";
                }
                LinkedMap tableInfo = service.getColInfoList(schema, tableList, null, null);
                Map<String, Object> pkInfo = service.getPkColList(schema, tableId);
                Iterator keys = pkInfo.keySet().iterator();
                while(keys.hasNext()){
                    String col = (String)keys.next();
                    String val2 = StringUtil.getText(tableInfo.get(col)) + ",PK";
                    tableInfo.put(col, val2);
                }

                List<Map<String, Object>> list = null;
                String oggFilter = StringUtil.getText(input.get("oggFilter"));
                String siteFilter = StringUtil.getText(input.get("siteFilter"));
                if(!oggFilter.equals("true") && !siteFilter.equals("true")){
                    list = retrieveList(schema, tableId, tableInfo, input);
                }
                for(int k = 0; k < siteList.size(); k++){
                    String siteCd = siteList.get(k).toString();
                    if(oggFilter.equals("true")){
                        input.put("oggCd", siteCd);
                    }
                    if(siteFilter.equals("true")){
                        input.put("siteCd", siteCd);
                    }
                    if(oggFilter.equals("true") || siteFilter.equals("true")){
                        list = retrieveList(schema, tableId, tableInfo, input);
                    }

                    StopWatch stopWatch = new StopWatch();
                    stopWatch.start();

                    List<Map<String, Object>> list2 = retrieveListFromRemote(siteCd, tableId, tableInfo, input);

                    // 본사에만 있는 것
                    List<Map<String, Object>> gap1 = new ArrayList<Map<String, Object>>();
                    for(int j = 0; j < list.size(); j++){
                        if(!list2.contains(list.get(j))){
                            gap1.add(list.get(j));
                        }
                    }

                    // 현장에만 있는 것
                    List<Map<String, Object>> gap2 = new ArrayList<Map<String, Object>>();
                    for(int j = 0; j < list2.size(); j++){
                        if(!list.contains(list2.get(j))){
                            gap2.add(list2.get(j));
                        }
                    }

                    Map<String, Object> holder = new HashMap<String, Object>();
                    holder.put("siteCd", siteCd);
                    holder.put("tableName", tableId);
                    holder.put("gap1", gap1);
                    holder.put("gap2", gap2);
                    listAll.add(holder);

                    stopWatch.stop();
                    printLog(tableId, "처리 시간 : " + stopWatch.getTime());
                }
                printRslt(tableId, listAll);
            }
            flushLog();
        }catch(Exception e){
            logger.debug(this.getClass().getName() + "." + "compareSite()" + "=>" + e.getMessage());
        }finally{
            try{
                if(logFlag){
                    fos.close();
                    bos.close();
                    out.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return listAll;
    }

    /**
     * 현장으로부터 쿼리 결과를 조회
     */
    public List<Map<String, Object>> retrieveListFromRemote(String dbSpec, String tableId, LinkedMap tableInfo,
            Map<String, Object> input){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        try{
            input.put("dbSpec", dbSpec);
            input.put("tableName", tableId);
            String strTableInfo = "";
            Iterator keys = tableInfo.keySet().iterator();
            while(keys.hasNext()){
                String col = (String)keys.next();
                String val = StringUtil.getText(tableInfo.get(col));
                strTableInfo += col + "^" + val + "|";
            }
            strTableInfo = strTableInfo.substring(0, strTableInfo.length() - 1);
            input.put("tableInfo", strTableInfo);

            String strInput = "";
            keys = input.keySet().iterator();
            while(keys.hasNext()){
                String col = (String)keys.next();
                String val = StringUtil.getText(input.get(col));
                strInput += col + "^" + val + "|";
            }
            strInput = strInput.substring(0, strInput.length() - 1);
            input.put("input", strInput);

            appProperties = ConfigUtil.getProperties(appProperties);
            String reqUrl = StringUtil.getText(appProperties.getProperty("com.dwenc." + dbSpec + ".contextUrl"))
                    + "/co/common/sysmng/retrieveListFromRemote.xpl";
            //"http://localhost:8081/co/common/sysmng/retrieveListFromRemote.xpl";
            String strRslt = httpCall(reqUrl, input);
            //            System.out.println("ISO-8859-1 -> " + new String(strRslt.getBytes("ISO-8859-1")));
            //            System.out.println("ISO-8859-1 | utf-8 -> " + new String(strRslt.getBytes("ISO-8859-1"), "utf-8"));
            //            System.out.println("ISO-8859-1 | euc-kr -> " + new String(strRslt.getBytes("ISO-8859-1"), "euc-kr"));
            //
            //            System.out.println("MS949 -> " + new String(strRslt.getBytes("MS949")));
            //            System.out.println("MS949 | utf-8 -> " + new String(strRslt.getBytes("MS949"), "utf-8"));
            //            System.out.println("MS949 | euc-kr -> " + new String(strRslt.getBytes("MS949"), "euc-kr"));
            //
            //            System.out.println("utf-8 -> " + new String(strRslt.getBytes("utf-8")));
            //            System.out.println("utf-8 | ISO-8859-1 -> " + new String(strRslt.getBytes("utf-8"), "ISO-8859-1"));
            //            System.out.println("utf-8 | euc-kr -> " + new String(strRslt.getBytes("utf-8"), "euc-kr"));
            //
            //            System.out.println("euc-kr -> " + new String(strRslt.getBytes("euc-kr")));
            //            System.out.println("euc-kr | ISO-8859-1 -> " + new String(strRslt.getBytes("euc-kr"), "ISO-8859-1"));
            //            System.out.println("euc-kr | utf-8 -> " + new String(strRslt.getBytes("euc-kr"), "utf-8"));

            strRslt = new String(strRslt.getBytes("ISO-8859-1"), "euc-kr");
            if(!strRslt.equals("") && strRslt.indexOf("^") > -1){
                String strArry[] = strRslt.split("/n");
                for(int i = 0; i < strArry.length; i++){
                    String strTmp2 = strArry[i];
                    Map<String, Object> tmp = new HashMap<String, Object>();
                    String strTmp3 = strTmp2.split("\\|")[0];
                    tmp.put(strTmp3.split("\\^")[0], strTmp3.split("\\^")[1]);
                    if(strTmp2.split("\\|").length > 1){
                        strTmp3 = strTmp2.split("\\|")[1];
                        tmp.put(strTmp3.split("\\^")[0], strTmp3.split("\\^")[1]);
                    }
                    list.add(tmp);
                }
            } else {
                Map<String, Object> tmp = new HashMap<String, Object>();
                tmp.put("PK", "");
                tmp.put("COL", "no response");
                list.add(tmp);
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return list;
    }

    /**
     * 현장으로 쿼리를 요청하기위한 http call
     */
    public String httpCall(String reqUrl, Map<String, Object> input){
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try{
            httpclient.addRequestInterceptor(new HttpRequestInterceptor() {

                public void process(final HttpRequest request, final HttpContext context) throws HttpException,
                        IOException{
                    if(!request.containsHeader("Accept-Encoding")){
                        request.addHeader("Accept-Encoding", "gzip");
                    }
                }
            });

            httpclient.addResponseInterceptor(new HttpResponseInterceptor() {

                public void process(final HttpResponse response, final HttpContext context) throws HttpException,
                        IOException{
                    HttpEntity entity = response.getEntity();
                    if(entity != null){
                        Header ceheader = entity.getContentEncoding();
                        if(ceheader != null){
                            HeaderElement[] codecs = ceheader.getElements();
                            for(int i = 0; i < codecs.length; i++){
                                if(codecs[i].getName().equalsIgnoreCase("gzip")){
                                    response.setEntity(new GzipDecompressingEntity(response.getEntity()));
                                    return;
                                }
                            }
                        }
                    }
                }
            });

            HttpPost httpPost = new HttpPost(reqUrl);

            List<NameValuePair> list2 = new ArrayList<NameValuePair>();
            Iterator keys = input.keySet().iterator();
            while(keys.hasNext()){
                String col = (String)keys.next();
                String val = StringUtil.getText(input.get(col));
                list2.add(new BasicNameValuePair(col, val));
            }

            httpPost.setEntity(new UrlEncodedFormEntity(list2, "UTF-8"));
            HttpResponse response = httpclient.execute(httpPost);

            HttpEntity entity = response.getEntity();
            if(entity != null){
                return EntityUtils.toString(entity);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            httpclient.getConnectionManager().shutdown();
        }
        return "";
    }

    /**
     * 특정한 테이블에 대한 쿼리 결과를 조회
     */
    public List<Map<String, Object>> retrieveList(Map<String, Object> meta, Map<String, Object> input){
        return retrieveList(StringUtil.getText(meta.get("dbSpec")), StringUtil.getText(meta.get("tableName")),
                (LinkedMap)(meta.get("tableInfo")), input);
    }

    /**
     * 특정한 테이블에 대한 쿼리 결과를 조회
     */
    public List<Map<String, Object>> retrieveList(String dbSpec, String tableId, LinkedMap tableInfo,
            Map<String, Object> input){
        List<Map<String, Object>> list = null;
        try{
            String oggTimeFilter = StringUtil.getText(input.get("oggTimeFilter"));

            String sql = "SELECT ";
            String cols = "";
            String pks = "";
            Iterator keys = tableInfo.keySet().iterator();
            while(keys.hasNext()){
                String col = (String)keys.next();
                if(!col.equals("FST_REG_DT") && !col.equals("FST_REG_USER_ID") && !col.equals("FNL_EDIT_DT")
                        && !col.equals("FNL_EDIT_USER_ID") && !col.equals("OGG_CD")){
                    if(StringUtil.getText(tableInfo.get(col)).indexOf(",PK") > -1){
                        pks += col + "||'§'||";
                    }else{
                        if(oggTimeFilter.equals("")) {
                            cols += col + "||'§'||";
                        }
                    }
                }
            }
            if(cols.length() > 0) {
                cols = cols.substring(0, cols.length() - 7);
            }
            if(pks.length() > 2){
                pks = pks.substring(0, pks.length() - 7);
                sql += pks + " AS PK, \n";
            }
            if(!oggTimeFilter.equals("")) {
                cols = " OGG_TIME";
            }
            sql += cols + " AS COL \n";
            String where = "FROM " + tableId.toUpperCase() + "\n";
            where += "WHERE 1 = 1 \n";
            if(!StringUtil.getText(input.get("siteCd")).equals("")){
                where += " AND SITE_CD = '" + StringUtil.getText(input.get("siteCd")) + "'";
            }
            String oggTime = StringUtil.getText(input.get("oggTime"));
            String oggTime2 = StringUtil.getText(input.get("oggTime2"));
            if(!oggTime2.equals("")){
                where += " AND OGG_TIME BETWEEN TO_TIMESTAMP('" + oggTime
                        + "', 'YYYYMMDDHH24MISSFF') AND TO_TIMESTAMP('" + oggTime2 + "', 'YYYYMMDDHH24MISSFF')";
            }else{
                if(!oggTime.equals("")){
                    where += " AND OGG_TIME > TO_TIMESTAMP('" + oggTime + "', 'YYYYMMDDHH24MISSFF')";
                }
            }
            if(!StringUtil.getText(input.get("oggCd")).equals("")){
                where += " AND OGG_CD = '" + StringUtil.getText(input.get("oggCd")) + "'";
            }
            String fstRegDt = StringUtil.getText(input.get("fstRegDt"));
            String fstRegDt2 = StringUtil.getText(input.get("fstRegDt2"));
            if(!fstRegDt2.equals("")){
                where += " AND FST_REG_DT BETWEEN TO_TIMESTAMP('" + fstRegDt
                        + "', 'YYYYMMDDHH24MISSFF') AND TO_TIMESTAMP('" + fstRegDt2 + "', 'YYYYMMDDHH24MISSFF')";
            }else{
                if(!fstRegDt.equals("")){
                    where += " AND FST_REG_DT > TO_TIMESTAMP('" + fstRegDt + "', 'YYYYMMDDHH24MISSFF')";
                }
            }
            if(!StringUtil.getText(input.get("fstRegUserId")).equals("")){
                where += " AND FST_REG_USER_ID = '" + StringUtil.getText(input.get("fstRegUserId")) + "'";
            }
            String fnlEditDt = StringUtil.getText(input.get("fnlEditDt"));
            String fnlEditDt2 = StringUtil.getText(input.get("fnlEditDt2"));
            if(!fstRegDt2.equals("")){
                where += " AND FNL_EDIT_DT BETWEEN TO_TIMESTAMP('" + fnlEditDt
                        + "', 'YYYYMMDDHH24MISSFF') AND TO_TIMESTAMP('" + fnlEditDt2 + "', 'YYYYMMDDHH24MISSFF')";
            }else{
                if(!fnlEditDt.equals("")){
                    where += " AND FNL_EDIT_DT > TO_TIMESTAMP('" + fnlEditDt + "', 'YYYYMMDDHH24MISSFF')";
                }
            }
            if(!StringUtil.getText(input.get("fnlEditUserId")).equals("")){
                where += " AND FNL_EDIT_USER_ID = '" + StringUtil.getText(input.get("fnlEditUserId")) + "'";
            }
            System.out.println(sql);
            printLog(tableId, sql);
            list = service.retrieveList(dbSpec, "SELECT COUNT(1) AS CNT " + where);
            if(!oggTimeFilter.equals("")) maxCnt = 50000;
            if(list.size() > 0
                    && Long.parseLong(list.get(0).get("CNT").toString()) > maxCnt){
                list = new ArrayList<Map<String, Object>>();
                Map<String, Object> tmp = new HashMap<String, Object>();
                tmp.put("PK", list.size());
                tmp.put("COL", "over " + String.valueOf(maxCnt));
                list.add(tmp);
            }else{
                list = service.retrieveList(dbSpec, sql + where);
            }
        }catch(Exception e){
            logger.debug(this.getClass().getName() + "." + "execBatch()" + "=>" + e.getMessage());
        }
        return list;
    }

    /**
     * 특정한 테이블에 대한 쿼리 결과를 String 포맷으로 변경
     */
    public String retrieveList2Str(String dbSpec, String tableId, LinkedMap tableInfo, Map<String, Object> input){
        String strStr = "";
        try{
            List<Map<String, Object>> list = retrieveList(dbSpec, tableId, tableInfo, input);
            if(list.size() < maxCnt){
                for(int i = 0; i < list.size(); i++){
                    Iterator keys = list.get(i).keySet().iterator();
                    while(keys.hasNext()){
                        String col = (String)keys.next();
                        String val = list.get(i).get(col).toString();
                        strStr += col + "^" + val + "|";
                    }
                    strStr += "/n";
                }
                strStr = strStr.substring(0, strStr.length() - 3);
            }else{
                strStr = "rowCount^over " + String.valueOf(maxCnt);
            }
        }catch(Exception e){
            logger.debug(this.getClass().getName() + "." + "execBatch()" + "=>" + e.getMessage());
        }
        return strStr;
    }

    /**
     * 쿼리 결과를 화면에 출력
     */
    public void printRslt(String logId, List<Object> listAll){
        for(int i = 0; i < listAll.size(); i++){
            Map<String, Object> holder = (Map<String, Object>)listAll.get(i);
            printLog(logId, "[본사 > 현장(" + holder.get("siteCd") + ")] - "
                    + holder.get("tableName").toString().toUpperCase());
            List<Map<String, Object>> list1 = (List<Map<String, Object>>)holder.get("gap1");
            for(int j = 0; j < list1.size(); j++){
                printLog(logId, list1.get(j).toString());
            }
            printLog(logId, "[본사 < 현장(" + holder.get("siteCd") + ")] - "
                    + holder.get("tableName").toString().toUpperCase());
            List<Map<String, Object>> list2 = (List<Map<String, Object>>)holder.get("gap2");
            for(int j = 0; j < list2.size(); j++){
                printLog(logId, list2.get(j).toString());
            }
            printLog(
                    logId,
                    "======================================================================================================================================================");
        }
    }

    /**
     * 결과를 로그 파일로 기록
     */
    public void printLog(String logId, String log){
        try{
            if(logFlag == false)
                return;
            if(!appIdList.contains(logId)){
                String path = "";
                if(Constants.workingDir == null)
                    path = "c:/temp/dweHome";
                String fileName = path + "/logs/" + logId + ".log";
                fos = new FileOutputStream(fileName, false);
                bos = new BufferedOutputStream(fos);
                PrintStream out = new PrintStream(bos, true);
                appenders.add(new PrintWriter(out, true));
                appIdList.add(logId);
            }
            ((PrintWriter)(appenders.get(appIdList.indexOf(logId)))).write(log + "\n");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void flushLog(){
        try{
            for(int i = 0; i < appIdList.size(); i++){
                ((PrintWriter)(appenders.get(i))).flush();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception{
        try{
            SyncService service = new SyncService();
            service.setLogFlag(true);
            // 테이블 비교
            List<String> aTableList = new ArrayList<String>();
            //            aTableList.add("co_org");
            //            aTableList.add("co_user");
            aTableList.add("co_sign_doc");
            List<String> siteList = new ArrayList<String>();
            //            siteList.add("uat1");
            //            siteList.add("uat2");
            siteList.add("PU450");
            siteList.add("PN120");
            siteList.add("PC390");
            siteList.add("PC380");
            siteList.add("OPAA1");
            siteList.add("OPAB1");
            siteList.add("PC410");
            siteList.add("OAAC1");
            siteList.add("PU390");
            Map<String, Object> input = new HashMap<String, Object>();
            input.put("oggTime", "20120916");
            input.put("oggFilter", "Y");
            //            input.put("siteFilter", "Y");
            List<Object> listAll = service.compareSite(aTableList, siteList, input);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public void setLogFlag(boolean logFlag){
        this.logFlag = logFlag;
    }

    public void setSchema(String schema){
        this.schema = schema;
    }
}
