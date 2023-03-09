package com.dwenc.cmas.acws.aca.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import docfbaro.common.StringUtil;


@Service
public class ACAService {

	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(ACAService.class);
	/**
	 * getLogger
	 */
	public Logger getLogger(){
		return logger;
	}

    @Autowired
    @Qualifier("appProperties")
    private Properties appProperties;


    // sap 실행
 	public Map<String, Object> retrieveExeSap(Map<String, Object> requestMap) throws Exception {
 		Map<String, Object> resMap = new HashMap();
	    String arg = null;
	    String siteCd = (String)requestMap.get("siteCd");
	    String gjahr = (String)requestMap.get("gjahr");
	    String monat = (String)requestMap.get("monat");

		String appUrl 	= 	StringUtil.getText(appProperties.get("dwe.serverInfo.url.context"));
		String user 	= 	StringUtil.getText(appProperties.get("dwe.sap.mail.user"));
		String pw 		= 	StringUtil.getText(appProperties.get("dwe.sap.mail.password"));
		String system 	= 	StringUtil.getText(appProperties.get("dwe.sap.mail.system"));
		String gui 		=	StringUtil.getText(appProperties.get("dwe.sap.mail.gui"));
		String client 	= 	StringUtil.getText(appProperties.get("dwe.sap.mail.client"));
		String code 	= 	StringUtil.getText(appProperties.get("dwe.sap.mail.code"));
		String prctr 	=   " PA_PRCTR="+siteCd;
		String gjah 	=   " PA_GJAHR="+gjahr;
		String mona 	=   " PA_MONAT="+monat;

//		arg = "-user="+user+" -pw="+pw+" -language=KO"+" -system="+system+" -gui=\""+gui+"\""+" -command=\"*ZTR0010\""+" -Type=Transaction"+" -Client="+client+" -PRC=\"KNEE0\"";
		arg = "-user="+user+" -pw="+pw+" -language=KO"+" -system="+system+" -gui=\""+gui+"\""+" -command="+code+prctr+";"+gjah+";"+mona+ " -Type=Transaction"+" -Client="+client;


		resMap.put("user", user);
		resMap.put("pw",   pw);
		resMap.put("language", "KO");
		resMap.put("system", system);
		resMap.put("gui", gui);
		resMap.put("command", "*ZTR0010");
		resMap.put("sType", "Transaction");
		resMap.put("client", client);
		resMap.put("PRC", "KVEE0");
		resMap.put("arg", arg);

		return resMap;
 	}
}
