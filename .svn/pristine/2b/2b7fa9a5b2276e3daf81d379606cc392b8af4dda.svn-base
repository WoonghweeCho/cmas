package com.dwenc.cmas.common.config.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.config.service.ConfigService;

import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통
 * 프로그램 : ConfigController
 * 설    명 : 설정 정보(대우건설 정보)를 db에 업데이트 처리
 * 작 성 자 :
 * 작성일자 : 2012-03-19
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2012-03-19             최초 작성
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */
@Controller
@RequestMapping("/co/common/config/*")
public class ConfigController {

	/**
	 * 로그처리를 위한 logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(ConfigController.class);

	/**
	 * Service 클래스 이용하기위한 변수
	 */
	@Autowired
	private ConfigService service;

	/**
	 * <pre>
	 *  etax에 환경 정보를 업데이트 한다.
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("updateConfig.*")
	public void updateConfig(MciRequest request, MciResponse response){
	    service.updateConfig();
	}

    /**
     * <pre>
     *  환경 정보를 조회한다.
     * </pre>
     * @param request
     * @param response
     */
    @RequestMapping("retrieveConfig.*")
    public void retrieveConfig(MciRequest request, MciResponse response){
        String msg = service.retrieveConfig(request.getParam());
        response.addSuccessMessage(msg);
    }
}
