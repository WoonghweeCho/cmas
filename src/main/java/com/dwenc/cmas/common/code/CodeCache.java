package com.dwenc.cmas.common.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.dwenc.cmas.common.cache.Cache;
import com.dwenc.cmas.common.cache.CacheFactory;
import com.dwenc.cmas.common.code.service.CodeService;
import com.dwenc.cmas.common.locale.service.LocaleService;
import com.dwenc.cmas.common.priv.service.PrivService;

import docfbaro.common.Constants;
import docfbaro.common.FileUtil;
import docfbaro.common.ObjUtil;
import docfbaro.common.StringUtil;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통
 * 프로그램 : CodeCache
 * 설    명 : 코드성 데이터 캐시 보관소.
 * 작 성 자 :
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
public class CodeCache {

    @Autowired
    @Qualifier("appProperties")
    private Properties appProperties;

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(CodeCache.class);

    public final String KEY_COMM_GRP_CDS = "KEY_COMM_GRP_CDS";

    // 내부 캐시
    private Cache _cache;

    private Cache _newCache;

    private int cacheCnt = 0;

    public int getCacheCnt(){
        return cacheCnt;
    }

    @Autowired
    CodeService commonService = new CodeService();

    @Autowired
    PrivService authService;

    @Autowired
    private LocaleService localeService;

    @PostConstruct
    public void init(){
        try{
            if(Constants.commCdCacheUseYn.equals("true")){
                loadCodeCache();
            }
            loadCodeForJQuery();
        }catch(Exception e){
            logger.error(e.getMessage());
        }
    }

    /**
     * 초기화 : 코드성 데이터들을 캐싱
     */
    public void loadCodeCache(){
        try{
            if(Constants.commCdCacheUseYn.equals("true")){
                _newCache = CacheFactory.newInstance(CacheFactory.LRU, CodeCache.class.getName(), 60 * 24 * 365 * 10,
                        Integer.parseInt(Constants.refreshCacheSize));
                cacheCnt = 0;
                if(Constants.refreshCacheUseYn.equals("false")){
                    loadCommGrpCdList();
                    loadCodeList();
                }else{
                    String target = Constants.refreshCacheTarget;
                    String targetArry[] = target.split("\\,");
                    for(int i = 0; i < targetArry.length; i++){
                        if(targetArry[i].equals("loadCommGrpCd")){
                            loadCommGrpCdList();
                        }else if(targetArry[i].equals("loadCode")){
                            loadCodeList();
                        }
                    }
                }
                _cache = _newCache;
            }
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * <pre>
     * 코드 그룹 캐쉬
     * </pre>
     * @throws Exception
     */
    private void loadCommGrpCdList(){
        try{
            List<Map<String, Object>> groupValueList = commonService.commGrpCdValueList();
            _newCache.add(buildCodeCacheKey(KEY_COMM_GRP_CDS), groupValueList);
            logger.debug("loadCommGrpCdList() cacheCnt : " + cacheCnt++);
        }catch(Exception e){
            logger.error("loadCommGrpCdList() : " + e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * <pre>
     *  코드목록 데이터를 캐시에 추가.
     * </pre>
     * @throws Exception
     */
    private void loadCodeList(){
        try{
            List<Map<String, Object>> groupKeyList = commonService.commGrpCdKeyList();
            int groupSize = groupKeyList.size();
            for(int i = 0; i < groupSize;){
                int loopSize = 100;
                if((groupSize - i) < 100){
                    loopSize = groupSize - i - 1;
                }
                ArrayList<String> commGrpCd = new ArrayList<String>();
                for(int j = 0; j <= loopSize; j++){
                    String comm_grp_cd = groupKeyList.get(i).get("code").toString();
                    commGrpCd.add(comm_grp_cd);
                    i++;
                }
                loadCode(commGrpCd);
            }
        }catch(Exception e){
            logger.error("loadCodeList() : " + e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * <pre>
     *  코드목록 데이터를 캐시에 추가.
     * </pre>
     * @param request
     * @param response
     */
    private List<Map<String, Object>> loadCode(ArrayList<String> commGrpCd){
        List<Map<String, Object>> codeListTmp = new ArrayList<Map<String, Object>>();
        try{
            Map<String, Object> input = new HashMap<String, Object>();
            input.put("sortCodeNotnull", "Y");
            List<Map<String, Object>> codeList = commonService.codeValueAllList(commGrpCd, input);
            for(int j = 0; j < codeList.size(); j++){
                if(codeList.size() > (j + 1)
                        && !codeList.get(j).get("commGrpCd").toString()
                                .equals(codeList.get(j + 1).get("commGrpCd").toString())){
                    codeListTmp.add(codeListTmp.size(), codeList.get(j));
                    _newCache.add(buildCodeCacheKey(codeList.get(j).get("commGrpCd").toString()), codeListTmp);
                    codeListTmp = new ArrayList<Map<String, Object>>();
                    logger.debug("==== CodeCache cacheCnt : " + cacheCnt++);
                }else{
                    codeListTmp.add(codeListTmp.size(), codeList.get(j));
                }
            }
            if(!codeListTmp.isEmpty()){
                _newCache.add(buildCodeCacheKey(codeList.get(codeList.size() - 1).get("commGrpCd").toString()),
                        codeListTmp);
            }
        }catch(Exception e){
            logger.error("loadCode() : " + e);
            throw new RuntimeException(e.getMessage());
        }
        return codeListTmp;
    }

    /**
     * <pre>
     * 캐시에 담기 위한 캐시키 생성
     * </pre>
     * @param comm_grp_cd
     * @param locale ko_KR, en_US 등
     * @return
     */
    private String buildCodeCacheKey(String comm_grp_cd){
        return comm_grp_cd + "_" + StringUtils.lowerCase(Constants.mLangDefaultLoclCd);
    }

    /**
     * <pre>
     * 주어진 코드그룹 및 언어에 해당하는
     * 캐시된 코드키/값 목록 리턴.
     * </pre>
     * @param comm_grp_cd
     * @param locale
     * @return
     */
    public List<Map<String, Object>> getCodeCache(String comm_grp_cd){
        String cacheKey = buildCodeCacheKey(comm_grp_cd);
        if(_cache.get(cacheKey) != null){
            return (List<Map<String, Object>>)_cache.get(cacheKey);
        }else{
            try{
                ArrayList<String> commGrpCd = new ArrayList<String>();
                commGrpCd.add(comm_grp_cd);
                return loadCode(commGrpCd);
            }catch(Exception e){
                logger.error(e.getMessage());
            }
            return new ArrayList<Map<String, Object>>();
        }
    }

    /**
     * <pre>
     * 모든 코드그룹키/값 목록을 리턴.
     * value는 MESSAGE_KO_KR 테이블의 해당 값.
     * </pre>
     * @return
     */
    public List<Map<String, Object>> getCommGrpCdCache(){
        String cacheKey = buildCodeCacheKey(KEY_COMM_GRP_CDS);
        if(_cache.get(cacheKey) != null){
            return (List<Map<String, Object>>)_cache.get(cacheKey);
        }else{
            try{
                ArrayList<String> commGrpCd = new ArrayList<String>();
                commGrpCd.add(KEY_COMM_GRP_CDS);
                return loadCode(commGrpCd);
            }catch(Exception e){
                logger.error(e.getMessage());
            }
        }
        return new ArrayList<Map<String, Object>>();
    }

    /**
     * <pre>
     * 캐시값 리턴.
     * </pre>
     * @param key
     * @return
     */
    public Object getCache(Object key){
        return _cache.get(key);
    }

    /**
     * <pre>
     * 캐시를 갱신한다.
     * </pre>
     * @param key 캐시 키
     * @param value 새로운 캐시 값
     */
    public void refresh(Object key, Object value){
        _newCache.add(key, value);
        logger.debug("refresh() cacheCnt : " + cacheCnt++);
    }

    /**
     * <pre>
     * 캐시를 삭제한다.
     * </pre>
     * @param key 캐시 키
     */
    public void remove(Object key){
        _newCache.remove(key);
        logger.debug("remove()");
    }

    /**
     * <pre>
     * 캐싱을 다시 하는 method
     * </pre>
     */
    public void refresh(){
        synchronized(this){
            cacheCnt = 0;
        }
    }

    /**
     * <pre>
     *  jquery  용 js 생성 파일 생성
     * </pre>
     * @param request
     * @param response
     */
    public void loadCodeForJQuery(){
        try{
            if(StringUtil.getText(appProperties.get("dwe.commCd.cache.jQuery.useYn")).equals("true")){
                List<Map<String, Object>> mData = localeService.retrieveLocaleList(new HashMap<String, Object>());
                for(int i = 0; i < mData.size(); i++){
                    String commCdFile = Constants.docRoot + appProperties.get("dwe.commCd.cache.jQuery").toString();

                    String loclCd = mData.get(i).get("loclCd").toString();
                    Map<String, Object> input = new HashMap<String, Object>();
                    input.put("sortCodeNotnull", "Y");
                    input.put("loclCd", loclCd);
                    List<Map<String, Object>> codeList = commonService.codeValueAllList(new ArrayList(), input);

                    StringBuffer strJs = new StringBuffer();
                    strJs.append("var gv_CommCd_"+loclCd+" = { \n");

             		String prevCommGrpCd = "";
             		String commGrpCd = "";
                    for(Map<String, Object> commCd : codeList){
                        String code = commCd.get("code").toString();
                        if(code.indexOf(" ") > -1)
                            continue;

                        commGrpCd = commCd.get("commGrpCd").toString();
                        if ( !ObjUtil.isNull(prevCommGrpCd) && !ObjUtil.isNull(prevCommGrpCd) ) {
                        	if ( commGrpCd.equals(prevCommGrpCd) ) {
                        		strJs.append(",\n");
                        	}
                        	else {
                        		strJs.append("\n ], \n");
                        	}
                        }


                        String value = commCd.get("value").toString();
                        String refCd1 = ObjUtil.isNull(commCd.get("refCd1")) ? "" : commCd.get("refCd1").toString();
                        String refCd2 = ObjUtil.isNull(commCd.get("refCd2")) ? "" : commCd.get("refCd2").toString();
                        String refCd3 = ObjUtil.isNull(commCd.get("refCd3")) ? "" : commCd.get("refCd3").toString();
                        String refCd4 = ObjUtil.isNull(commCd.get("refCd4")) ? "" : commCd.get("refCd4").toString();
                        String hgrCommGrpCd = ObjUtil.isNull(commCd.get("hgrCommGrpCd")) ? "" : commCd.get("hgrCommGrpCd").toString();
                        String hgrCommCd = ObjUtil.isNull(commCd.get("hgrCommCd")) ? "" : commCd.get("hgrCommCd")
                                .toString();

                        if ( !commGrpCd.equals(prevCommGrpCd)) {
                        	strJs.append("\""+commGrpCd+"\": [ \n");
                        }

                        value = "{ \"code\":\""+code+"\",\"value\":\""+value+"\",\"hgrCommGrpCd\":\""+hgrCommGrpCd+"\",\"hgrCommCd\":"+"\""+hgrCommCd+"\"" +
                                ",\"refCd1\":\""+refCd1+"\"" +
                                ",\"refCd2\":\""+refCd2+"\"" +
                                ",\"refCd3\":\""+refCd3+"\"" +
                                ",\"refCd4\":\""+refCd4+"\" }";

                        prevCommGrpCd = commGrpCd;


                        strJs.append(value);
                    }
                    if ( strJs.equals( "var gv_CommCd"+loclCd+" = { \n" )) {
                    	strJs.append(" \n }; \n ");
                    }
                    else {
                    	strJs.append(" \n ] \n }; \n ");
                    }

                    if(!commCdFile.equals("")) {
                        commCdFile = StringUtil.replace(commCdFile, ".js", "_" + loclCd + ".js");
                        logger.debug("load commcd js:" + commCdFile);
                        FileUtil.write(commCdFile, strJs, "utf-8");
                    }
                }
            }
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}