package com.dwenc.cmas.common.message.service;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : MessageService
 * 설    명 : 메시지 관리 Service Class
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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.locale.service.LocaleService;

import docfbaro.common.Constants;
import docfbaro.common.FileUtil;
import docfbaro.common.ObjUtil;
import docfbaro.common.SpringUtil;
import docfbaro.common.StringUtil;
import docfbaro.common.WebContext;
import docfbaro.query.CommonDao;
import docfbaro.sua.message.DatabaseMessageSource;

@Service("messageService")
public class MessageService {

	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

    @Autowired
    @Qualifier("appProperties")
    private Properties appProperties;

    @Autowired
    private LocaleService localeService;

    @PostConstruct
    public void init() {
        try {

            //writeMessageForXP();
        	writeMessageForJQuery();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

	/**
	 * 메세지 목록 조회
	 *
	 * @param input
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveMessageList(Map<String, Object> input) {
		if ( ObjUtil.isNull(input.get("sysCd"))) {
			input.put("sysCd", Constants.sysCd);
		}
		return dao.queryForMapList("message.retrieveMessageList", input);
	}

	/**
	 * 메세지 상세 목록 조회
	 *
	 * @param input
	 * @return Map
	 */
	public Map<String, Object> retrieveMessage(Map<String, Object> input) {
		if ( ObjUtil.isNull(input.get("sysCd"))) {
			input.put("sysCd", Constants.sysCd);
		}

		return dao.queryForMap("message.retrieveMessage", input);
	}

	/**
	 * 메세지를 조회하는 메소드
	 *
	 * @param input
	 * @return
	 */
	public String getMessage(Map<String, Object> input) {
		if ( ObjUtil.isNull(input.get("sysCd"))) {
			input.put("sysCd", Constants.sysCd);
		}
		return dao.queryForMap("message.retrieveMessage", input).get("message").toString();
	}

	/**
	 * 메세지 존재 여부 확인을 하는 메소드
	 *
	 * @param input
	 * @return boolean
	 */
	public boolean isMessageExist(Map<String, Object> input) {
		if (!checkMessageExist(input).isEmpty())
			return true;
		return false;
	}

	/**
	 * 메세지 존재 여부 확인을 하는 메소드
	 *
	 * @param input
	 * @return Map
	 */
	public Map<String, Object> checkMessageExist(Map<String, Object> input) {
		if ( ObjUtil.isNull(input.get("sysCd"))) {
			input.put("sysCd", Constants.sysCd);
		}
		return dao.queryForMap("message.checkMessageExist", input);
	}

    public void writeMessageForXP() {
        try {
            List<Map<String, Object>> mData = localeService.retrieveLocaleList(new HashMap<String, Object>());
            for(int i=0;i<mData.size();i++) {
                writeMessageForXP(mData.get(i).get("loclCd").toString());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Xplatform 용 메시지를 생성한다.
     * @param loclCd
     */
	public void writeMessageForXP(String loclCd) {
		String messageFile = StringUtil.getText(appProperties.get("dwe.message.xPlatform"));
		if (messageFile.equals("")) {
			return;
		}
		Map<String, Object> input = new HashMap<String, Object>();
		try {
			messageFile = StringUtil.replace(WebContext.getSysBase(), "\\", "/") + messageFile;
			input.put("sysCd", Constants.sysCd);
			input.put("localeCd", loclCd);
			List<Map<String, Object>> mData = dao.queryForMapList("message.retrieveMessageList", input);
			StringBuffer strJs = new StringBuffer();
			strJs.append("CSV:utf-8\n");
			strJs.append("Dataset:output\n");
	        strJs.append("MSG_ID:STRING(256),LOCL_CD:STRING(10),MSG_TEXT:STRING(256),MSG_TYPE:STRING(256)\n");
			for (int j = 0; j < mData.size(); j++) {
				String code = mData.get(j).get("code").toString();
				if (code.indexOf(" ") > -1)
					continue;
				code = StringUtil.replace(code, ".", "_");
				String messageStr = mData.get(j).get("message").toString();
				messageStr = messageStr.replace('\"', '\'');
				strJs.append(String.format("\"%s\",\"%s\",\"%s\",\"%s\"\n", code, loclCd, messageStr, (code.indexOf("_cfm") > -1 ? "C" : "A")));
			}
			messageFile = StringUtil.replace(messageFile, ".xfdl", "_" + loclCd + ".xfdl");
			logger.debug("messageFile file 수정 : " + messageFile);
			FileUtil.write(messageFile, strJs, "utf-8");
		} catch (Exception e) {
			logger.error("**** Could not read source file: " + e.getMessage());
		}
	}

	/**
	 * Ajax용 메시지를 생성한다.
	 * @param paramMap
	 * @return
	 */
    public String getMessageByAjax(Map<String, Object> paramMap){
        DatabaseMessageSource messageSource = (DatabaseMessageSource)SpringUtil.getBean(WebContext.getRequest(), "messageSource");
        String param = StringUtil.getText(paramMap.get("param"));
        String paramArray[] = param.split(",");
        String loclCd = StringUtil.getText(paramMap.get("loclCd"));
        if(loclCd.equals(""))
            loclCd = "ko_KR";
        String msg = "";
        try{
            if(loclCd.equals("ko_KR")) {
                msg = messageSource.getMessage(StringUtil.getText(paramMap.get("msgId")), new Object[] { param },
                        new Locale("ko", "KR"));
            } else {
                msg = messageSource.getMessage(StringUtil.getText(paramMap.get("msgId")), new Object[] { param },
                        new Locale("en", "US"));
            }
        }catch(Exception e){
            msg = messageSource.getMessage(StringUtil.getText(paramMap.get("msgId")), new Object[] { param },
                    new Locale("ko", "KR"));
        }
        return msg;
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
		String messageFile = StringUtil.getText(appProperties.get("dwe.serverInfo.docRoot")) + StringUtil.getText(appProperties.get("dwe.message.jQuery"));
		if (messageFile.equals("")) {
			return;
		}
		Map<String, Object> input = new HashMap<String, Object>();
		try {
//			messageFile = StringUtil.replace(WebContext.getSysBase(), "\\", "/") + messageFile;
			input.put("sysCd", StringUtil.getText(appProperties.get("dwe.serverInfo.sysCd")));
			input.put("localeCd", loclCd);
			List<Map<String, Object>> mData = dao.queryForMapList("message.retrieveMessageList", input);
			StringBuffer strJs = new StringBuffer();
			strJs.append("var gv_Message_"+loclCd+" = { \n");


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
