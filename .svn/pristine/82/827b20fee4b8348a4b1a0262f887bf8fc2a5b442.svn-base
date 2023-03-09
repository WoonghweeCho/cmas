package com.dwenc.cmas.common.utils;

import java.util.List;
import java.util.Map;

import org.springframework.context.support.MessageSourceAccessor;

import docfbaro.sua.mvc.MciResponse;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통 - 내부결재
 * 프로그램 : MessageUtil
 * 설      명 : 내부결재에서 타 시스템(업무시스템 등)에 Callback Message 처리를 담당하는 Class
 * 작 성 자 : 고준석
 * 작성일자 : 2011-12-07
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2011-12-07             최초 작성
 * ---------------------------------------------------------------
 * </pre>
 *
 * @version 1.0
 */
public class MessageUtil {
	/**
	 * <pre>
	 * 업무시스템에서 전달 받은 Callback Message 처리
	 * </pre>
	 *
	 * @param messageSourceAccessor
	 * @param response
	 * @param map
	 * @throws Exception
	 */
	public static void outputCallbackMessage(MessageSourceAccessor messageSourceAccessor, MciResponse response, Map<String, Object> map){
		if(map.get("MSG_CODE") != null && !map.get("MSG_CODE").equals("")){
			String msgCode = (String) map.get("MSG_CODE");
			String[] msgValues = null;
			String message = null;

			if (map.get("MSG_VALUES") != null && ((List) map.get("MSG_VALUES")).size() > 0) {
				msgValues = (String[]) ((List) map.get("MSG_VALUES")).toArray(new String[] {});
				message = messageSourceAccessor.getMessage(msgCode, msgValues);
			} else {
				message = messageSourceAccessor.getMessage(msgCode, new String[]{});
			}
			response.addSuccessMessage(message);
		}
	}
}
