package com.dwenc.cmas.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletRequest;

import docfbaro.common.WebContext;
import docfbaro.sua.mvc.MciRequest;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통
 * 프로그램 : StringUtil
 * 설      명 : String 처리를 위한 Utility Class
 * 작 성 자 : 홍두희
 * 작성일자 : 2012-12-05
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
public class RequestUtil {
	/**
	 * String to XML 변환
	 * 
	 * @param str
	 * @return
	 */
	public static Map<String, Object> getParam(MciRequest request, ServletRequest orgRequest) {

		Map<String, Object> mciParam = request.getParam();

		if(!mciParam.isEmpty())
			return mciParam;
		
		Map<String, Object> paramMap = new HashMap<String, Object>();

		Map requestParams = orgRequest.getParameterMap();

		Iterator it = requestParams.keySet().iterator();

		while (it.hasNext()) {
			String paramName = (String) it.next();
			String[] paramValue = (String[]) requestParams.get(paramName);

			if (paramValue == null)
				continue;
			try {
				if (paramValue != null) {
					if (paramValue.length > 1)
						paramMap.put(paramName, paramValue);
					else {
						paramMap.put(paramName,
								URLDecoder.decode(paramValue[0], "UTF-8"));
					}
				}
			} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			}
		}
		return paramMap;
	}

}