package com.dwenc.cmas.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.Cookie;							// SSO 인증용
import javax.servlet.http.HttpServletRequest;		// SSO 인증용

import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;

public class HttpSend {
    /**
     * <pre>
     * HTTP Connection을 이용하여 데이터를 POST로 보내고,
     * 상대방의 Response Body를 JSON으로 파싱하여 리턴함
     * Response Body가 JSON형태가 아니면 JSONException이 발생함.
     * </pre>
     * @param request
     * @param response
     * @return Map(JSONObject)
     */
	public static Map invokeURL(String urlStr, Map param) {
		URL url;
		URLConnection con;
	    OutputStreamWriter wr = null;
	    BufferedReader in = null;
	    JSONObject rsl = new JSONObject();
		try {
			url = new URL(urlStr);
			con = url.openConnection();

			con.setRequestProperty("charset", "utf-8");
			con.setRequestProperty("BARONET_AUTHKEY", param.get("userId").toString() );
			System.out.println("[BARONET_AUTHKEY] userId ==> " + param.get("userId").toString() );

			con.setDoOutput(true);
			con.setUseCaches(false);

			StringBuffer parameter = new StringBuffer();
			Iterator iterator = param.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry entry = (Map.Entry) iterator.next();
				if(parameter.length() > 0)
						parameter.append("&");
				parameter.append(entry.getKey() );
				parameter.append("=");
				parameter.append(entry.getValue() );
			}

			//통합결재시스템 URL 호출
			wr = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
			System.out.println("sgnsURL ==> "+parameter.toString());
			wr.write(parameter.toString());
			wr.flush();

			//통합결재시스템 처리후 Response 데이터 수신
	      in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"), 8 * 1024);

	      String line = null;
	      StringBuffer buffer = new StringBuffer();
	      while((line = in.readLine()) != null){
	      	System.out.println("readLine() ==> " + line);
	      	buffer.append(line);
	      	buffer.append("\r\n");
	      }

	      /*
	      * 수신된 Response 데이터를 JSON 형태로 변환
	      * convertStr2Map 함수는 JSON 형태의 Response 데이터를 Map<String, Object> 로 변환해주는 함수임
	      * 해당 업무시스템에서 이미 JSON 형태 데이터를 처리하는 모듈이 개발되어 있다면 그 모듈을 사용해도 됨
	      */

	     /**
	      * 통합결재시스템 Response 형식
	      *
	      * 1. 성공
	      *     rsl.put("TYPE", "SUCCESS");
	      *
	      * 2. 실패
	      *     rsl.put("TYPE", "SUCCESS");
	      *			rsl.put("MSG_VALUES", "MSG_VALUES");
	      */
	      rsl = convertObj2JSON(buffer.toString());

		} catch (JSONException e) {
			rsl = new JSONObject();
			rsl.put("TYPE", "FAIL");
			rsl.put("MSG_VALUES", "JSON Parse Exception");
		} catch (MalformedURLException e) {
			rsl = new JSONObject();
			rsl.put("TYPE", "FAIL");
			rsl.put("MSG_VALUES", "Malformed URL Exception");
		} catch (IOException e) {
			rsl = new JSONObject();
			rsl.put("TYPE", "FAIL");
			rsl.put("MSG_VALUES", "IO Exception");
		} catch (Exception e) {
			rsl = new JSONObject();
			rsl.put("TYPE", "FAIL");
			rsl.put("MSG_VALUES", "IO Exception");
		} finally{
			try {
				wr.close();
			} catch (Exception e) {}
			try {
				in.close();
			} catch (Exception e) {}
		}
		return rsl;
	}

	/**
     * <pre>
     * JSON형태의 Object(List,Map,String)을 JSONObject로 파싱함.
     * </pre>
     * @param String
     * @return Map(JSONObject)
     */
    public static JSONObject convertObj2JSON(Object input) throws JSONException {
    	JSONObject result = JSONObject.fromObject(input);

        result = (JSONObject)new Object(){
        	public Object toMap(final Object object){
        		if(object instanceof Map){
        			final Map map = (Map) object;
        			Iterator iterator = map.entrySet().iterator();
        			while(iterator.hasNext()){
        				Map.Entry entry = (Entry) iterator.next();
        				String key = (String) entry.getKey();
        				map.put(key, toMap(entry.getValue()));
        			}
        			return map;
        		}else if(object instanceof DynaBean){
        			final Map map = new HashMap();
        			final MorphDynaBean bean = (MorphDynaBean) object;
        			DynaProperty[] properties = bean.getDynaClass().getDynaProperties();
        			for(int i = 0 ; i < properties.length ; i++){
        				DynaProperty property = properties[i];
        				map.put(property.getName(), toMap(bean.get(property.getName())));
        			}
        			return map;
        		}else if(object instanceof List){
        			final List list = (List) object;
        			for(int i = 0 ; i < list.size(); i++){
        				list.set(i, toMap(list.get(i)));
        			}
        			return list;
        		}
        		return object;
        	}
        }.toMap(result);

        return result;
    }
}
