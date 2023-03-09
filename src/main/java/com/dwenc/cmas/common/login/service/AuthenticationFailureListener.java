package com.dwenc.cmas.common.login.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.login.service.AuthenticationListenerUtils.Status;

import docfbaro.query.CommonDao;

@Service
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent>, InitializingBean {

	public static Logger logger = LoggerFactory.getLogger(AuthenticationFailureListener.class);

	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	@Autowired
	@Qualifier("appProperties")
	private Properties properties;

	private static String STAGE;

	/**
	 * 로그인 실패시 이력 기록함
	 * 로컬 환경이면 기록하지 않음
	 * 로그인 프로세스와 비동기로 수행됨
	 */
	@Override
	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
		if (!(event.getSource() instanceof UsernamePasswordAuthenticationToken) || "L".equals(STAGE)) return;

		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) event.getAuthentication();
		Map<String, String> item = new HashMap<String, String>();
		item.put("sysCd", properties.getProperty("dwe.serverInfo.sysCd"));
		item.put("userId", (String) event.getAuthentication().getPrincipal());
		item.put("oggCd", AuthenticationListenerUtils.UNKNOWN);
		item.put("addrCont", AuthenticationListenerUtils.getRemoteAddress(token));
		item.put("sessId", AuthenticationListenerUtils.getSessionId(token)); // get session id
		item.put("outcomCont", Status.FAILURE.name());
		createItem(item);
	}

	private void createItem(Map<String, String> item) {
		try {
			dao.update("authentication.insertItem", item);
		} catch (Throwable e) {
			logger.error("error occured while inserting login history ... {}", e.getMessage());
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		STAGE = properties.getProperty("dwe.serverInfo.stage");
	}
}
