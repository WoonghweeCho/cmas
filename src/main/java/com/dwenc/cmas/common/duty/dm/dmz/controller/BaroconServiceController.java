package com.dwenc.cmas.common.duty.dm.dmz.controller;

import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.duty.dm.dmz.service.BaroconService;
import com.dwenc.cmas.common.utils.RequestUtil;

import docfbaro.common.WebContext;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

/** * <pre>
* ---------------------------------------------------------------
* 업무구분 : 공통
* 프로그램 :
* 설 명    : Barocon to TDMS 인터페이스를 위한 컨트롤러 클래스
* 작 성 자 : 변형구
* 작성일자 : 2012-04-04
* 수정이력
* ---------------------------------------------------------------
* 수정일 이 름 사유
* ---------------------------------------------------------------
* 2012-04-04 변형구 최초 작성
* ---------------------------------------------------------------
* </pre>
* @version 1.0 */

@Controller
@RequestMapping("/co/duty/dmz/*")
public class BaroconServiceController {

	// Controller 변수 및 autowired 선언
    private static final Logger logger = LoggerFactory.getLogger(BaroconServiceController.class);

	@Autowired
	private BaroconService service;                // service class

	@Autowired
    @Qualifier("appProperties")
    private Properties appProperties;

	// 여기서 부터 controller 메서드 작업
	/**
	  * <pre>
	  * Tdms duplication 체크
	  * </pre>
	  * @param request
	  * @param response
	  * */
   @RequestMapping("checkTdmsDuplication.*")
   public void retrieveFileList(MciRequest request, MciResponse response) {
	   Map<String, Object> inputData = RequestUtil.getParam(request, WebContext.getRequest());

	   String existYn = "";

		try {
			existYn = service.existDocFileCheck(inputData);
			response.addParam("fv_TdmdExistYn", existYn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }

}
