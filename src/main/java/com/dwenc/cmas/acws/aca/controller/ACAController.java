package com.dwenc.cmas.acws.aca.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import docfbaro.common.WebContext;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

import com.dwenc.cmas.acws.aca.service.ACAService;
import com.dwenc.cmas.common.utils.RequestUtil;


@Controller
@RequestMapping("/acws/aca/*") /* 개발하는 업무에 대한 HTTP Request URI */
public class ACAController {

	/**
	 * Logger 객체 생성
	 */
	private static Logger logger = LoggerFactory.getLogger(ACAController.class);


	/**
	 * Service 클래스 이용하기위한 변수
	 */
	@Autowired
	private ACAService service;


    /**
    *
    *
    * @param request
    * @param response
    */
   @RequestMapping("retrieveExeSap.*")
	public void retrieveExeSap(MciRequest request, MciResponse response) {
	   Map<String, Object> requestMap = RequestUtil.getParam(request, WebContext.getRequest());
	   System.out.println("siteCd ---->   "+requestMap.get("siteCd"));
   	    Map<String, Object> mapList = new HashMap();
		try {
			mapList = service.retrieveExeSap(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    response.setMap("output1", mapList);

	}
}
