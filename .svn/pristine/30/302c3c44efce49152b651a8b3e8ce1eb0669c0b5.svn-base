package com.dwenc.cmas.common.sms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jcf.data.GridData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.sms.domain.Sms;
import com.dwenc.cmas.common.sms.domain.SmsResult;
import com.dwenc.cmas.common.sms.service.SmsService;

import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : SmsController
 * 설    명 : Sms 처리를 위한  controller 클래스
 * 작 성 자 : 홍두희
 * 작성일자 : 2012-12-05
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2011-12-07             최초 작성
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */
@Controller
@RequestMapping("co/common/sms/*")
public class SmsController {

	@Autowired
	private SmsService service;

	/**
	 * <pre>
	 *  sendSms
	 *  Xplaform에서 SMS 전송시
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("sendSms.*")
	public List<SmsResult> sendSms(MciRequest request, MciResponse response) {
		GridData<HashMap> mData = request.getGridData("input1", HashMap.class);
//		service.sendSms(mData);
		List<SmsResult> returnData = service.sendSms(mData); //
        response.setList("output1", returnData);
		return returnData;
	}
	/**
	 * <pre>
	 *  sendSms
	 *  Service에서 SMS 전송시
	 * </pre>
	 * @param request
	 * @param response
	 */
    @RequestMapping("retrieveSms.*")
    public void retrieveSms(MciRequest request, MciResponse response){
        Map<String, Object> data = request.getParam();
        List<Sms> returnData = service.retrieveSms(data); //
        response.setList("output1", returnData);
    }
}