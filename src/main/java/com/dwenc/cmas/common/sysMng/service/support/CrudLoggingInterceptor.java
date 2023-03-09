package com.dwenc.cmas.common.sysMng.service.support;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jcf.iam.core.common.util.UserInfoHolder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dwenc.cmas.common.accessLog.service.AccessLog;
import com.dwenc.cmas.common.metaData.service.OracleMetaDataService;
import com.dwenc.cmas.common.utils.LogUtil;

import docfbaro.common.ClassUtils;
import docfbaro.common.DateUtil;
import docfbaro.common.MenuFactory;
import docfbaro.common.ObjUtil;
import docfbaro.common.SpringUtil;
import docfbaro.common.StringUtil;
import docfbaro.common.WebContext;
import docfbaro.iam.UserInfo;
import docfbaro.iam.authentication.UserDefinition;
import docfbaro.query.trace.QueryInfo;
import docfbaro.query.trace.context.QueryContext;
import docfbaro.query.trace.context.QueryContextHolder;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통모듈
 * 프로그램 : CrudLoggingInterceptor
 * 설    명 : CRUD MATRIX 생성용
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
public class CrudLoggingInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    @Qualifier("appProperties")
    private Properties appProperties;

    private List logList = new ArrayList();

    private Map<String, Object> sqlList = new HashMap<String, Object>();

    @Autowired
    private OracleMetaDataService service;

    @Autowired
    private MenuFactory menuFactory;

    private String strStartTime = "";

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(CrudLog.class);

    /**
     *  <pre>
     *  Controller의 handler 메소드가 수행되기 이전 단계에서 실행되며, {@link QueryContext}를 생성하고 TXID를 채번하여 요청 수행과정중 실행되는 Query를 수집하기 위한 사전 작업을 수행한다.
     *  </pre>
     *
     *  @param request  HttpServletRequest
     *  @param response HttpServletResponse
     *  @param handler  Controller 객체
     *  @return false가 반환되는 경우 handler 메소드가 수행되지 않는다.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String crudLogUseYn = StringUtil.getText(appProperties.getProperty("dwe.crudLog.useYn"));
        if(crudLogUseYn.equals("") || crudLogUseYn.equals("false"))
            return true;

        if(StringUtil.getText(appProperties.getProperty("dwe.accessLog.manual.useYn")).equals("true")){
            if(UserInfoHolder.getUserInfo(UserDefinition.class) == null)
                return true;
            if(AccessLog.validLogStart() == false)
                return true;
        }

        QueryContextHolder.setTraceId(UUID.randomUUID().toString());
        strStartTime = AccessLog.getTime();
        return true;
    }

    /**
     * <pre>
     * Controller의 handler 메소드가 성공적으로 수행된 경우 실행되며, {@link QueryContext}를 활용한 로직을 기술한다.
     * </pre>
     *
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param modelAndView ModelAndView (target view 및 전달 객체를 가지는 자료구조)
     */
    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView modelAndView)
            throws Exception{

        String crudLogUseYn = StringUtil.getText(appProperties.getProperty("dwe.crudLog.useYn"));
        if(crudLogUseYn.equals("") || crudLogUseYn.equals("false")){
            logger.debug("crudLogUseYn : " + crudLogUseYn);
            return;
        }

        if(AccessLog.validLogStart() == false) return;

        String strEndTime = AccessLog.getTime();
        float fGap = DateUtil.getTimeGap(strStartTime, strEndTime);

        QueryContext queryContext = QueryContextHolder.getContext();

        List<QueryInfo> queryInfos = queryContext.getQueryInfos();

        String dbSpec = StringUtil.getText(appProperties.getProperty("dwe.serverInfo.sysCd"));
        String menuId = "";
        String menuUrl = "";
        if(UserInfoHolder.getUserInfo(UserDefinition.class) != null){
            menuId = UserInfo.getMenuId(); // timeLogInterceptor 가 활성화 되어야 함.
            if(!menuId.equals("")) {
                MenuFactory menuFactory = (MenuFactory)SpringUtil.getBean(WebContext.getRequest(), "menuFactory");
                menuUrl = StringUtil.getText(menuFactory.getMenuInfo("ko_KR", menuId).get("menuUrl"));
                logger.debug("crud menuUrl : " + menuUrl);
            }
        }

        try{
            for(QueryInfo q : queryInfos){
                String sqlStr = q.getStatement();
                try{
                    sqlStr = convertQueryStr(sqlStr, q.getParameter());
                    String sqlId = "";
                    if(sqlStr.indexOf("/*") > -1) {
                        sqlId = sqlStr.substring(sqlStr.indexOf("/*") + 2, sqlStr.indexOf("*/"));
                        sqlId = StringUtil.LRTrim(sqlId);
                    }
                    Map<String, Object> tableList = null;
                    if(!sqlList.containsKey(sqlId)){
                        tableList = service.getTableList(dbSpec, sqlStr);
                        sqlList.put(sqlId, tableList);
                    } else {
                        tableList = (Map<String, Object>)sqlList.get(sqlId);
                    }

                    Map<String, Object> input = new HashMap<String, Object>();
                    input.put("menuId", menuId);
                    input.put("menuUrl", menuUrl);

                    Set dataKeySet = tableList.keySet();
                    Iterator dataIterator = dataKeySet.iterator();
                    while(dataIterator.hasNext()){
                        String dataKey = dataIterator.next().toString();
                        String str = tableList.get(dataKey).toString();
                        input.put("requestUrl", req.getRequestURI());
                        input.put("tableNm", dataKey);
                        input.put("tableId", str);
                        String tempSql = StringUtil.LTrim(sqlStr);
                        if(tempSql.indexOf("*/") > -1){
                            String tempArry[] = StringUtil.split(tempSql, "*/");
                            input.put("queryId", StringUtil.LRTrim(tempArry[0]));
                            tempSql = StringUtil.LTrim(tempArry[1].toUpperCase());
                        }else{
                            input.put("queryId", "?");
                        }
                        if(tempSql.startsWith("INSERT")){
                            input.put("CRUD", "C");
                        }else if(tempSql.startsWith("UPDATE")){
                            input.put("CRUD", "U");
                        }else if(tempSql.startsWith("DELETE")){
                            input.put("CRUD", "D");
                        }else if(tempSql.startsWith("SELECT")){
                            input.put("CRUD", "R");
                        }

                        String strLog = LogUtil.toStringForCSV(input).toString();
                        if(!logList.contains(strLog)){
                            logList.add(strLog);
                            CrudLog.getInstance().write(strLog);
                        }
                        // DB 종료 시간
                        if(req != null){
                            if(!ObjUtil.isNull(req.getSession().getAttribute(
                                    HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY))
                                    && (req.getServletPath().indexOf("writeAccessLog.co") == -1)
                                    && (req.getServletPath().indexOf("writeAccessLogForMenu.co") == -1)
                                    && UserInfo.getGuid() != null && fGap >= 0.0){
                                sqlStr = StringUtil.replace(sqlStr, "\n", "$n");
                                AccessLog.getInstance().write(req.getServletPath(), "", Float.toString(fGap), "db");// + "#" + strLog

                                /*Map<String, Object> info = new HashMap<String, Object>();
                                String reqUri = req.getServletPath();
                                if(reqUri.startsWith("/")) {
                                    info.put("dutySysCd", reqUri.substring(1, 3));
                                    reqUri = reqUri.substring(1, reqUri.length());
                                } else {
                                    info.put("dutySysCd", reqUri.substring(0, 2));
                                    reqUri = reqUri.substring(0, reqUri.length());
                                }
                                if(reqUri.indexOf(".") > -1) reqUri = reqUri.substring(0, reqUri.lastIndexOf(".")) + ".xpl";
                                info.put("sysCd", Constants.sysCd);
                                info.put("srcCls", "Table");
                                info.put("serviceId", menuFactory.getMenuCd(menuId));
                                info.put("reqUri", reqUri);
                                info.put("analy1", sqlId);
                                info.put("analy2", ExtUtil.getTableIds(tableList));
                                info.put("analy3", StringUtil.getText(input.get("CRUD")));
                                info.put("analy4", reqUri);
                                ExtInfoLog.getInstance().setTabList(info);*/  // 2013.02.20 변형구
                            }
                        }
                    }
                }catch(Exception e){
                    System.out.println("!!!CRUD error! : " + sqlStr);
                }
            }
        }catch(Exception e){
            System.out.println("!!!CRUD error! : " + e);
        }
    }

    /**
     * <pre>
     * Controller의 handler 메소드가 수행된 이후 실행되며(예외없이 실행된 경우 postHandler의 다음단계로 실행됨), {@link QueryContext}를 초기화하는 작업을 수행한다.
     * </pre>
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param handler  Controller 객체
     * @param ex handler 메소드에서 전파된 예외
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception{
        QueryContextHolder.clear();
    }

    /**
     * <pre>
     *  OGG_CD, OGG_TIME 고려한 쿼리 처리
     * </pre>
     */
    public String convertQueryStr(String sqlStr, Object obj){
        if(obj == null)
            return sqlStr;
        try{
            String objType = obj.getClass().getCanonicalName();
            if(obj.getClass().getCanonicalName().equals("java.util.HashMap")){
                HashMap param = (HashMap)obj;
                Set dataKeySet = param.keySet();
                Iterator dataIterator = dataKeySet.iterator();
                while(dataIterator.hasNext()){
                    String dataKey = dataIterator.next().toString();
                    sqlStr = makeQueryStr(sqlStr, dataKey, param.get(dataKey));
                }
            }else{
                Method methods[] = obj.getClass().getMethods();
                for(int i = 0; i < methods.length; i++){
                    String methodNm = methods[i].getName();
                    try{
                        if(methodNm.startsWith("get")){
                            if(methodNm.equals("getOggCd")){
                                sqlStr = makeQueryStr(sqlStr, "oggCd", "");
                            }else if(methodNm.equals("getOggTime")){
                                sqlStr = makeQueryStr(sqlStr, "oggTime", "");
                            }else{
                                Object obj2 = ClassUtils.invokeSimple(obj, methodNm);
                                if(obj2 != null){
                                    String colNm = StringUtil.lowerFirst(methodNm.substring(3, methodNm.length()));
                                    sqlStr = makeQueryStr(sqlStr, colNm, obj2);
                                }
                            }
                        }
                    }catch(Exception e){
                        System.out.println(e + "=>" + methodNm + "/" + i);
                    }
                }
            }
            String tmp = sqlStr;
            tmp = StringUtil.replace(tmp, "\t", " ");
            tmp = StringUtil.replace(tmp, "\n", " ");
            tmp = StringUtil.replace(tmp, "\r", " ");
            Map tmpArry = new HashMap();
            while(true){
                if(tmp.indexOf(":") == -1)
                    break;
                String tmp2 = tmp.substring(tmp.indexOf(":"), tmp.indexOf(" ", tmp.indexOf(":")));
                if(!tmp2.equals(":'") && !tmp2.endsWith("'") && !tmp2.endsWith(")")){
                    if(tmp2.endsWith(",")){
                        tmpArry.put(tmp2.substring(1, tmp2.length() - 1), "");
                    }else{
                        tmpArry.put(tmp2.substring(1, tmp2.length()), "");
                    }
                }else if(tmp2.equals("")){
                    break;
                }
                tmp = StringUtil.replace(tmp, tmp2, "");
            }
            if(tmpArry.size() > 0){
                System.out.println("누락된 params : " + tmpArry);
                sqlStr = convertQueryStr(sqlStr, tmpArry);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return sqlStr;
    }

    /**
     * <pre>
     *  쿼리에 변수값 주입 처리
     * </pre>
     * @throws Exception
     */
    public String makeQueryStr(String sqlStr, String dataKey, Object obj) throws Exception{
        if(obj == null)
            return sqlStr;
        String objType = obj.getClass().getCanonicalName();
        String str = obj.toString();
        if(sqlStr.indexOf(":" + dataKey + "[") > -1){
            objType = "java.util.ArrayList";
        }
        if(objType.equals("java.lang.String")){
            sqlStr = StringUtil.replace(sqlStr, ":" + dataKey, "'" + str + "'");
        }else if(objType.equals("java.util.ArrayList")){
            String token = ":" + dataKey;
            if(sqlStr.indexOf(token) > -1){
                String tmp0 = sqlStr.substring(0, sqlStr.indexOf(token));
                String tmp1 = sqlStr.substring(sqlStr.lastIndexOf(token) + token.length(), sqlStr.length());
                tmp1 = tmp1.substring(tmp1.indexOf("]") + 1, tmp1.length());
                sqlStr = tmp0 + token + tmp1;
                str = str.substring(1, str.length() - 1);
                str = "'" + StringUtil.replace(str, ", ", "', '") + "'";
                sqlStr = StringUtil.replace(sqlStr, ":" + dataKey, str);
            }
        }else{
            sqlStr = StringUtil.replace(sqlStr, ":" + dataKey, "'" + str + "'");
        }
        return sqlStr;
    }

}
