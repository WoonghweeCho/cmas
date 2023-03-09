package com.dwenc.cmas.common.tdms.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.tdms.service.TransferTDMSService;
import com.dwenc.cmas.common.utils.RequestUtil;

import docfbaro.common.WebContext;
import docfbaro.iam.UserInfo;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;


@Controller
@RequestMapping("/co/common/tdms/*")
public class TransferTDMSController {

	private static Logger logger = LoggerFactory.getLogger(TransferTDMSController.class);


    @Autowired
    private TransferTDMSService transferTDMSService;

}
