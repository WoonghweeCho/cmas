package com.dwenc.cmas.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;

public class HttpReceive {

    /**
     * <pre>
     * JSON������ RequestParameter�� JSONObject�� �Ľ���.
     *
     * Map<String, Object> map = HttpReceive.getParamToJSON(request);
     * JSONObject json = (JSONObject)HttpReceive.getParamToJSON(request);
     *
     * </pre>
     * @param HttpServletRequest
     * @return Map(JSONObject)
     */
	public static Map getParamToJSON(HttpServletRequest request) {
        JSONObject json = null;
        BufferedReader in = null;

        try {
			in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"), 8 * 1024);
	        String line = null;
	        StringBuffer buffer = new StringBuffer();
	        while((line = in.readLine()) != null){
	        	buffer.append(line);
	        	buffer.append("\r\n");
	        }

	        /**
	        *
	        *
	        */
	        json = HttpReceive.convertObj2JSON(buffer.toString());
	        System.out.println("map : " + json);
		} catch (UnsupportedEncodingException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		} finally{
			try {
				in.close();
			} catch (IOException e) {}
		}
		return json;
	}

	/**
	* HTTP Request�� ���� Response�� ����Ѵ�.	*
	*/
	public static void sendResponse(HttpServletResponse response, Map param) {
		//response.setCharacterEncoding("utf-8");
		response.setContentType("application/json+sua; charset=UTF-8");
		PrintWriter writer = null ;
		JSONObject result = new JSONObject();
		JSONObject json = JSONObject.fromObject(param);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(json);
		result.put("RESULT", jsonArray);

		try {
			writer = response.getWriter();
			String outStr = new String(result.toString().getBytes(), "UTF-8");
			writer.println(outStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			writer.close();
		}
	}

    /**
     * <pre>
     * JSON������ Object(List,Map,String)�� JSONObject�� �Ľ���.
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
