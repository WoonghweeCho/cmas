package com.dwenc.cmas.baronet.sgn.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import jcf.sua.mvc.MciRequestContextHolder;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.baronet.sgn.service.BaronetSignService;

import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

@Controller
@RequestMapping("/baronet/sgn/*")
public class BaronetSignController {
	/**
	 * Logger 객체 생성
	 */
	private static Logger logger = LoggerFactory.getLogger(BaronetSignController.class);

	@Autowired
    @Qualifier("appProperties")
    private Properties appProperties;

	@Autowired
    @Qualifier("baronetSignService")
	private BaronetSignService baronetSignService;

	/**
	 * <pre>
	 * 바로넷 결재 문서 열람
	 * </pre>
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "baronetDocViewer.*")
	public void getBaronetSignDoc(MciRequest request, MciResponse response)
	{
		JSONObject contents = null;

		try {
			Map<String, String> params = request.get("param", HashMap.class);

			String username = new String(Base64.decodeBase64((String) params.get("username")));
			String password = new String(Base64.decodeBase64((String) params.get("password")));

			params.put("username", username);
			params.put("password", password);
			System.out.println("##### BaronetSignController getParameter == " + username + ", " + password);

			String data = baronetSignService.getNotesData(MciRequestContextHolder.get().getHttpServletRequest(), params);
			System.out.println("##### Contents == " + data);

			contents = (!"".equals(data) && "{".equals(data.trim().substring(0, 1))) ? JSONObject.fromObject(data) : null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		response.setMap("result", contents);
	}

	/**
	 * 업무공지 내용 조회
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "baronetDocContents.*")
	public void getBaronetSignContents(MciRequest request, MciResponse response)
	{
		String contents = "";

		try {
			Map<String, String> params = request.get("param", HashMap.class);

			String username = new String(Base64.decodeBase64((String) params.get("username")));
			String password = new String(Base64.decodeBase64((String) params.get("password")));

			params.put("username", username);
			params.put("password", password);

			contents = baronetSignService.getNotesData(MciRequestContextHolder.get().getHttpServletRequest(), params);
			System.out.println("##### Contents == " + contents);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, String> result = new HashMap<String, String>();
		result.put("contents", contents);
		response.setMap("result", result);
	}
}
