package com.dwenc.cmas.common.mlang.service.support;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통
 * 프로그램 : MlangFactory
 * 설    명 : 다국어 정보를 메모리에 캐쉬
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

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.locale.service.LocaleService;
import com.dwenc.cmas.common.mlang.service.MlangService;

import docfbaro.common.Constants;
import docfbaro.common.FileUtil;
import docfbaro.common.StringUtil;
import docfbaro.config.EnvironmentConfigProperties;

@Service
public class MlangFactory implements Observer {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(MlangFactory.class);

    @Autowired
    private MlangService service;

    @Autowired
    private LocaleService localeService;

    @Autowired
    @Qualifier("appProperties")
    private Properties appProperties;

    @Autowired
    private EnvironmentConfigProperties properties;

    /**
     * dwe.mLang.cache.useYn 여부에 따라 다국어 정보를 cache 한다.
     */
	@PostConstruct
	public void init() {
		try {
		    if(StringUtil.getText(appProperties.get("dwe.mLang.cache.useYn")).equals("true")) return;
		    List<Map<String, Object>> mData = localeService.retrieveLocaleList(new HashMap<String, Object>());
		    for(int i=0;i<mData.size();i++) {
	            findMlangInfo(mData.get(i).get("loclCd").toString());
	            //writeMlangForXPlatform(mData.get(i).get("loclCd").toString());
	            writeMlangForJQuery(mData.get(i).get("loclCd").toString());
		    }
		} catch (Exception e) {
		    logger.error(e.getMessage());
		}
	}

    /**
     * 해당 다국어의 메시지 정보를 조회한다.
     */
	public Map<String, Object> findMlangInfo(String aJavaLoclCd) {
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("loclCd", aJavaLoclCd);
		Map<String, Object> mHolder2 = new HashMap<String, Object>();
		mHolder2.put(aJavaLoclCd, service.retrieveMlangList(input));
		return mHolder2;
	}

	/**
	 * MlangFactory에서 사용하는 Map을 초기화한다.
	 */
	public void refresh() {
		synchronized (this) {
		    init();
		}
	}

	/**
	 * <PRE>
	 * 메모리에 Cache 된 다국어 정보를 초기화한다.
	 * </PRE>
	 *
	 * @param o
	 * @param arg
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable o, Object arg) {
		this.refresh();
	}

	/**
	 * Xplatform에 반환하기 위한 다국어 정보를 생성한다.
	 * @param aJavaLoclCd
	 * @throws Exception
	 */
    public void writeMlangForXPlatform(String aJavaLoclCd) throws Exception {
        String mlangFile = properties.getDocRoot() + properties.getmLangXPlatform();

        if ( mlangFile.equals( "" ) ) {
            return;
        }
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("sysCd", properties.getSysCd());
        try {
            input.put("loclCd", aJavaLoclCd);
            List<Map<String, Object>> mData = service.retrieveMlangList(input);
            StringBuffer strJs = new StringBuffer();
            strJs.append("CSV:utf-8\n");
            strJs.append("Dataset:output\n");
            strJs.append("MLANG_ID:STRING(256),LOCL_CD:STRING(10),DUTY_SYS_CD:STRING(10),OBJ_TP_CD:STRING(20),MLANG_NM:STRING(256),MLANG_ABBRNM:STRING(256)\n");
            for ( int j = 0; j < mData.size(); j++ ) {
                String mlangId = "\"" + StringUtil.getText(mData.get(j).get("mlangId")).replace('.', '_') + "\",\"";
                String loclCd = StringUtil.getText(mData.get(j).get("loclCd")) + "\",\"";
                String dutySysCd = StringUtil.getText(mData.get(j).get("dutySysCd")) + "\",\"";
                String objTpCd = StringUtil.getText(mData.get(j).get("objTpCd")) + "\",\"";
                String mlangNm = StringUtil.getText(mData.get(j).get("mlangNm")) + "\",\"";
                String mlangAbbrnm = StringUtil.getText(mData.get(j).get("mlangAbbrnm")).replace( '\"', '\'') + "\"\n";
                strJs.append(mlangId).append(loclCd).append(dutySysCd).append(objTpCd).append(mlangNm).append(mlangAbbrnm);
            }
            mlangFile = StringUtil.replace(mlangFile, ".xfdl", "_" + aJavaLoclCd + ".xfdl");
            logger.debug( "mlang file 수정 : " + mlangFile);
            FileUtil.write( mlangFile, strJs, "utf-8" );
        } catch ( IOException e ) {
            logger.debug( "**** Could not read source file: " + e.getMessage() );
        }
    }

    /**
	 * JQuery를 위한 다국어 js 파일 생성
	 * @param aJavaLoclCd
	 * @throws Exception
	 */
    public void writeMlangForJQuery(String aJavaLoclCd) throws Exception {
        String mlangFile = properties.getDocRoot() + StringUtil.getText(appProperties.get("dwe.mLang.jQuery"));

        if ( mlangFile.equals( "" ) ) {
            return;
        }
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("sysCd", Constants.sysCd);
        try {
            input.put("loclCd", aJavaLoclCd);
            List<Map<String, Object>> mData = service.retrieveMlangList(input);
            StringBuffer strJs = new StringBuffer();
            strJs.append("var gv_mlang_"+aJavaLoclCd+" = { \n");
            for ( int j = 0; j < mData.size(); j++ ) {
            	if ( j +1 == mData.size()) {
            		strJs.append(" \""+StringUtil.getText(mData.get(j).get("mlangId"))+"\":\""+StringUtil.getText(mData.get(j).get("mlangNm"))+"\"\n");
            		continue;
            	}
            	// '사용자를 검색합니다. 성명을 입력해주시기 바랍니다.':'search user. please input user name.',
            	strJs.append(" \""+StringUtil.getText(mData.get(j).get("mlangId"))+"\":\""+StringUtil.getText(mData.get(j).get("mlangNm"))+"\",\n");
            }

            strJs.append("\n}\n");
            mlangFile = StringUtil.replace(mlangFile, ".js", "_" + aJavaLoclCd + ".js");
            logger.debug( "mlang file 수정 : " + mlangFile);
            FileUtil.write( mlangFile, strJs, "utf-8" );
        } catch ( IOException e ) {
            logger.debug( "**** Could not read source file: " + e.getMessage() );
        }
    }
}

