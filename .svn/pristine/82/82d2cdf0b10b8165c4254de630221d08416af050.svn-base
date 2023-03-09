package com.dwenc.cmas.eaps.carapp.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.eaps.carapp.service.CarAppService;
import com.dwenc.cmas.common.utils.RequestUtil;
import com.dwenc.cmas.common.utils.MessageUtil;

import docfbaro.common.WebContext;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

@Controller
@RequestMapping("/eaps/*") /* 개발하는 업무에 대한 HTTP Request URI */
public class CarAppController {

	/**
	 * Logger 객체 생성
	 */
	private static Logger logger = LoggerFactory.getLogger(CarAppController.class);

	/**
	 * 해당 Controller와 연결된 Service Class
	 */
    @Autowired
    private CarAppService sService;

    /**
     * Common Message 처리
     */
    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    /**
     * 저장
     *
     * @param request
     * @param response
     */
    @RequestMapping("insertCarApp.*")
	public void insertCarApp(MciRequest request, MciResponse response) {
    	/**
    	 * Client에서 전송한 key:value 파라미터를 Map 형태로 받음
    	 */
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		/**
		 * Client에서 전송한 DataSet을 DocfBaro Framework의 GridData로 전송 받음
		 */
		//GridData<CarApp> carApp = request.getGridData("input1", CarApp.class);
		Map<String, Object> resMap = sService.insertCarApp(map);

		MessageUtil.outputCallbackMessage(messageSourceAccessor, response, resMap);
	}

    /**
     * 수정
     *
     * @param request
     * @param response
     */
    @RequestMapping("upateCarApp.*")
	public void upadteCarApp(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> resMap = sService.updateCarApp(map);

		MessageUtil.outputCallbackMessage(messageSourceAccessor, response, resMap);
	}


    /**
     * 삭제
     * @param request
     * @param response
     */
    @RequestMapping("deleteCarApp.*")
	public void deleteCarApp(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
	}


    /**
     * 조회
     * @param request
     * @param response
     */
    @RequestMapping("selectCarApp.*")
	public void selectCarApp(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		Map<String, Object> resMap = sService.selectCarApp(map);
		response.setMap("output1", resMap); 		//Map을 Client로 전송
	}

}

