package com.dwenc.cmas.common.logger.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import docfbaro.iam.UserInfo;
import docfbaro.query.CommonDao;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import jcf.sua.mvc.MciRequestContextHolder;

@Service
public class AccessLogger implements InitializingBean {

	private static final Logger logger = LoggerFactory.getLogger(AccessLogger.class);
	public Logger getLogger() { return logger; }

//	@Autowired
//	private QueryExecutorWrapper executor;
//
//	private QueryExecutorWrapperDelegator delegate;

	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	private ExecutorService threadExecutor = Executors.newFixedThreadPool(5);

	@Autowired
	@Qualifier("appProperties")
	private Properties prop;

	private static boolean IS_LOCAL;
	private static String SYSTEM_CODE;
	private static AccessLogger mciAccessLogger;

	public static AccessLogger get() {
		return mciAccessLogger;
	}

	/**
	 * 로그인 이력을 남긴다.
	 */
//	public void writeLogin() {
//
//	}

	public void writeMenu(Object menuCd) {
		writeMenu(menuCd, null);
	}

	/**
	 * 메뉴 접근 이력을 남긴다.
	 * 로컬환경은 기록을 남기지 않는다.
	 * @param item
	 */
	public void writeMenu(Object menuCd, Object menuUrl) {
		if (!StringUtils.hasText((String) menuCd)) {
			logger.info("[ menuCd ] is empty, this will be ignored ...");
			return;
		}

		Map<String, Object> item = new HashMap<String, Object>();
		item.put("menuCd", menuCd);
		item.put("menuUrl", menuUrl);
		item.put("sysCd", SYSTEM_CODE);
		item.put("userId", getUserId());
//		item.put("menuCd", value);
//		item.put("menuUrl", value);
		HttpServletRequest request = MciRequestContextHolder.get().getHttpServletRequest();
		if (request != null) {
			UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
			item.put("userIp", getClientIP(request));
			item.put("accessSrc", request.getHeader("Referer"));
			item.put("oprs", userAgent.getOperatingSystem().getName());
			item.put("mach", getBrowserNameAndVersion(userAgent));
			item.put("wholeInfo", request.getHeader("User-Agent"));
		}
		if (IS_LOCAL) return;
		runAsyncStatement("access.updateAccessMenu", item);
	}

	private String getBrowserNameAndVersion(UserAgent userAgent) {
		return userAgent.getBrowser().getName() + "-"
				+ (userAgent.getBrowserVersion() != null? userAgent.getBrowserVersion():Browser.UNKNOWN);
	}

	/**
	 * 비동기로 DB에 insert/update 문을 수행한다.
	 * @param item
	 */
	private void runAsyncStatement(final String statement, final Map<String, Object> item) {
		threadExecutor.execute(new Runnable() {
			@Override
			public void run() {
				runStatement(statement, item);
			}

		});
	}

	/**
	 * DB에 insert/update 문을 수행한다.
	 * @param statement
	 * @param item
	 */
	private void runStatement(String statement, Map<String, Object> item) {
		try {
			dao.update(statement, item);
		} catch (DuplicateKeyException e) {
			logger.error("error occured while inserting log ... {}", e.getMessage());
			runStatement(statement, item);
		} catch (Throwable e) {
			logger.error("error occured while inserting log ... {}", e.getMessage());
		}
	}

	/**
	 * client ip 정보 얻어오기
	 * @return
	 */
	private String getClientIP(HttpServletRequest request) {
		String address = request.getHeader("X-Forwarded-For");
        if (address == null || address.length() == 0 || "unknown".equalsIgnoreCase(address)) {
            address = request.getHeader("Proxy-Client-IP");
        }
        if (address == null || address.length() == 0 || "unknown".equalsIgnoreCase(address)) {
            address = request.getHeader("WL-Proxy-Client-IP");
        }
        if (address == null || address.length() == 0 || "unknown".equalsIgnoreCase(address)) {
            address = request.getHeader("HTTP_CLIENT_IP");
        }
        if (address == null || address.length() == 0 || "unknown".equalsIgnoreCase(address)) {
            address = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (address == null || address.length() == 0 || "unknown".equalsIgnoreCase(address)) {
            address = request.getRemoteAddr();
        }
        return address;
	}

	/**
	 * user id 가져오기 (없으면 익명으로 기록)
	 * @return
	 */
	private String getUserId() {
		try {
			return UserInfo.getUserId();
		} catch (Exception e) {
			return "anonymous";
		}

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		IS_LOCAL = "L".equals(prop.getProperty("dwe.serverInfo.stage"));
		SYSTEM_CODE = prop.getProperty("dwe.serverInfo.sysCd");
		mciAccessLogger = this;
	}

}
