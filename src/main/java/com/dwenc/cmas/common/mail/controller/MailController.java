package com.dwenc.cmas.common.mail.controller;

import java.util.HashMap;

import jcf.data.GridData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.mail.service.MailService;

import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

/**
 * Mail 처리를 위한 Controller
 */
@Controller
@RequestMapping("/co/common/mail/*")
public class MailController {

    @Autowired
    private MailService service;
	/**
	 * <pre>
	 * Xplaform을 통한 메일전송
	 * </pre>
	 * @param map
	 */
    @RequestMapping("sendMail.*")
    public void sendMail(MciRequest request, MciResponse response) throws Exception{
		GridData<HashMap> data = request.getGridData("input1", HashMap.class);
        service.sendMail(data);
    }
	/**
	 * <pre>
	 * Xplaform을 통한 메일전송
	 * </pre>
	 * @param map
	 */
    @RequestMapping("sendMail2.*")
    public void sendMail2(MciRequest request, MciResponse response) throws Exception{
        GridData<HashMap> data = request.getGridData("input1", HashMap.class);
        service.sendMail2(data);
    }
}