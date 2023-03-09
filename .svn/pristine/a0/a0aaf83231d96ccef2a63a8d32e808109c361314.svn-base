package com.dwenc.cmas.common.locale.controller;

import java.util.Map;

import jcf.data.GridData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.locale.domain.LoclInfo;
import com.dwenc.cmas.common.locale.service.LocaleService;
import com.dwenc.cmas.common.utils.RequestUtil;

import docfbaro.common.WebContext;
import docfbaro.iam.UserInfo;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : LocaleController
 * 설    명 : Locale 관련 컨트롤러
 * 작 성 자 : 변형구
 * 작성일자 : 2012.04.17
 * 수정이력 :
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2012.04.12    변형구    최초  작성1
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */

@Controller
@RequestMapping("/co/common/locale/*")
public class LocaleController {

	/**
	 * <pre>
	 *  changeLocale
	 * </pre>
	 * @param request
	 * @param response
	 */
	/**
	 * Service 클래스 이용하기위한 변수
	 */
	@Autowired
	private LocaleService service;

	@RequestMapping("changeLocale.*")
    public void changeLocaleSession(MciRequest request, MciResponse response) throws Exception {

		Map<String,Object> inputData = RequestUtil.getParam(request, WebContext.getRequest());
        GridData<LoclInfo> mData = request.getGridData("input1", LoclInfo.class);

		UserInfo.getUserInfo().setLoclCd(inputData.get("localeCd").toString());
		service.updateUserLocl(mData);
    }
}
