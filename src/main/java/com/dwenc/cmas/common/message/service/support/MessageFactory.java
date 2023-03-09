package com.dwenc.cmas.common.message.service.support;

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
import com.dwenc.cmas.common.message.service.MessageService;

import docfbaro.common.Constants;
import docfbaro.common.FileUtil;
import docfbaro.common.StringUtil;
import docfbaro.common.WebContext;


/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통
 * 프로그램 : MessageFactory
 * 설    명 : 다국어 정보를 메모리에 캐쉬
 * 작 성 자 : DWE
 * 작성일자 : 2013.04.01
 * 수정이력 :
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2013.04.01   변형구 	최초작성
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */

@Service
public class MessageFactory implements Observer {

	 /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(MessageFactory.class);

    @Autowired
    private MessageService service;

    @Autowired
    private LocaleService localeService;

    @Autowired
    @Qualifier("appProperties")
    private Properties appProperties;



    /**
     * 메세지 정보를 cache 한다.
     */
	@PostConstruct
	public void init() {
        try {

            //writeMessageForXP();
        	//writeMessageForJQuery();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


	/**
     * 해당 다국어의 메시지 정보를 조회한다.
     */
	public Map<String, Object> findMessageInfo(String aJavaLoclCd) {
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("loclCd", aJavaLoclCd);
		Map<String, Object> mHolder2 = new HashMap<String, Object>();
		mHolder2.put(aJavaLoclCd, service.retrieveMessageList(input));
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
     * Jquery 용 메시지를 생성한다.
     */
    public void writeMessageForJQuery() {
        try {
            List<Map<String, Object>> mData = localeService.retrieveLocaleList(new HashMap<String, Object>());
            for(int i=0;i<mData.size();i++) {
            	writeMessageForJQuery(mData.get(i).get("loclCd").toString());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Jquery 용 메시지를 생성한다.
     * @param loclCd
     */
	public void writeMessageForJQuery(String loclCd) {
		String messageFile = StringUtil.getText(appProperties.get("dwe.message.jQuery"));
		if (messageFile.equals("")) {
			return;
		}
		Map<String, Object> input = new HashMap<String, Object>();
		try {
			messageFile = StringUtil.replace(WebContext.getSysBase(), "\\", "/") + messageFile;
			input.put("sysCd", Constants.sysCd);
			input.put("localeCd", loclCd);
			List<Map<String, Object>> mData = service.retrieveMessageList(input);
			StringBuffer strJs = new StringBuffer();
			strJs.append("var gv_Message = { \n");


			for (int j = 0; j < mData.size(); j++) {
				String code = mData.get(j).get("code").toString();
				if (code.indexOf(" ") > -1)
					continue;
				code = StringUtil.replace(code, ".", "_");
				String messageStr = mData.get(j).get("message").toString();
				messageStr = messageStr.replace('\"', '\'');
				strJs.append("\""+code+"\": { \"locale\":\""+loclCd+"\",\"message\":\""+messageStr+"\",\"msgType\":\""+(code.indexOf("_cfm") > -1 ? "C" : "A")+"\" }  " );
                if ( j+1 == mData.size()) {
                	strJs.append(" \n }; \n ");
                }
                else {
                	strJs.append(" , \n ");
                }
			}
			messageFile = StringUtil.replace(messageFile, ".js", "_" + loclCd + ".js");
			logger.debug("js messageFile file 수정 : " + messageFile);
			FileUtil.write(messageFile, strJs, "utf-8");
		} catch (Exception e) {
			logger.error("**** Could not read source file: " + e.getMessage());
		}
	}


}
