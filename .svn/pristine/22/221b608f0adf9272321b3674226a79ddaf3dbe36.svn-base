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
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.login.service.AuthenticationListenerUtils.Status;

import docfbaro.iam.authentication.UserDefinition;
import docfbaro.query.CommonDao;

@Service
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent>, InitializingBean {

	public static Logger logger = LoggerFactory.getLogger(AuthenticationSuccessListener.class);

	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	@Autowired
	@Qualifier("appProperties")
	private Properties properties;

	private static String STAGE;

	/**
	 * 로그인 성공시 이력 기록함
	 * 로컬 환경이면 기록하지 않음
	 * 로그인 프로세스와 비동기로 수행됨
	 */
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		if (!(event.getSource() instanceof UsernamePasswordAuthenticationToken) || "L".equals(STAGE)) return;

		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) event.getSource();
		Map<String, String> item = new HashMap<String, String>();
		item.put("sysCd", properties.getProperty("dwe.serverInfo.sysCd"));
		item.put("userId", (String) event.getAuthentication().getCredentials());
		item.put("oggCd", AuthenticationListenerUtils.UNKNOWN);
		item.put("addrCont", AuthenticationListenerUtils.getRemoteAddress(token)); // get session id
		item.put("sessId", AuthenticationListenerUtils.getSessionId(token)); // get session id
		if (token.getPrincipal() instanceof UserDefinition) {
			UserDefinition user = (UserDefinition) token.getPrincipal();
			item.put("userId", user.getUsername());
			item.put("pathCont", user.getSource());
		}
		item.put("outcomCont", token.getAuthorities().size() != 0? Status.SUCCESS.name():Status.NOAUTH.name());
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

