package com.dwenc.cmas.tech.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import jcf.data.GridData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.user.service.SignUserService;
import com.dwenc.cmas.common.utils.MessageUtil;
import com.dwenc.cmas.common.utils.RequestUtil;
import com.dwenc.cmas.common.utils.RexUtil;
import com.dwenc.cmas.common.mail.service.MailService;

import com.dwenc.cmas.tech.domain.TechDat;
import com.dwenc.cmas.tech.domain.TechAppn;
import com.dwenc.cmas.tech.service.TechDatService;

import docfbaro.common.ObjUtil;
import docfbaro.common.WebContext;
import docfbaro.iam.UserInfo;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

@Controller
@RequestMapping("/tech/publication/*") /* 개발하는 업무에 대한 HTTP Request URI */
public class TechPubDatController {
	/**
	 * Logger 객체 생성
	 */
	private static Logger logger = LoggerFactory.getLogger(TechPubDatController.class);

	/**
	 * 해당 Controller와 연결된 Service Class
	 */
    @Autowired
    private TechDatService sService;


    /**
     * Common Message 처리
     */
    @Autowired
    private MessageSourceAccessor messageSourceAccessor;


    /**
     * 사내간행물 저장
     * @param request
     * @param response
     */
    @RequestMapping("insertTechPubDat.*")
	public void insertTechPubDat(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		Map<String, Object> resMap = sService.insertTechPubDat(map);
		MessageUtil.outputCallbackMessage(messageSourceAccessor, response, resMap);
	}

    /**
     * 사내간행물 수정
     **/
    @RequestMapping("updateTechPubDat.*")
	public void updateTechPubDat(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		Map<String, Object> resMap = sService.updateTechPubDat(map);
		MessageUtil.outputCallbackMessage(messageSourceAccessor, response, resMap);
	}

    /**
     * 사내간행물 자료 등록완료
     **/
    @RequestMapping("updateTechPubDatComp.*")
	public void updateTechPubDatComp(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		Map<String, Object> resMap = sService.updateTechPubDatComp(map);
		MessageUtil.outputCallbackMessage(messageSourceAccessor, response, resMap);
	}

    /**
     * 사내간행물 담당자 문서등록
     * @param request
     * @param response
     */
    @RequestMapping("insertTechPubDatComp.*")
	public void insertTechPubDatComp(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		Map<String, Object> resMap = sService.insertTechPubDatComp(map);
		MessageUtil.outputCallbackMessage(messageSourceAccessor, response, resMap);
	}

    /**
     * 자료삭제
     **/
    @RequestMapping("deleteTechDat.*")
	public void deleteTechDat(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		Map<String, Object> resMap = sService.deleteTechDat(map);
		MessageUtil.outputCallbackMessage(messageSourceAccessor, response, resMap);
	}

    /**
     * 사내간행물목록 자료조회
     **/
    @RequestMapping("retrieveTechPubDatList.*")
	public void retrieveTechPubDatList(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<TechDat> ds_TechDataList = sService.retrieveTechPubDatList(map);
		response.setList("ds_TechDataList", ds_TechDataList);  		//List을 Client로 전송
	}

    /**
     * 사내간행물등록신청 자료조회
     **/
    @RequestMapping("retrieveTechPubDatList1.*")
	public void retrieveTechPubDatList1(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<TechDat> ds_TechDataList = sService.retrieveTechPubDatList1(map);
		response.setList("ds_TechDataList", ds_TechDataList);  		//List을 Client로 전송
	}

    /**
     * 사내간행물 신청 조회
     **/
    @RequestMapping("retrieveTechPubAppnList.*")
	public void retrieveTechPubAppnList(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<TechAppn> ds_TechAppnList = sService.retrieveTechPubAppnList(map);
		response.setList("ds_TechAppnList", ds_TechAppnList);  		//List을 Client로 전송
	}


    /**
     * 사내간행물 신청 Bas
     */
    @RequestMapping("insertPubAppnBas.*")
	public void insertPubAppnBas(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		Map<String, Object> resMap = sService.insertPubAppnBas(map);
		MessageUtil.outputCallbackMessage(messageSourceAccessor, response, resMap);
	}

    /**
     * 도서/DVD 대출/반납 문서상태
     */
    @RequestMapping("updateTechAppnBas.*")
	public void updateTechAppnBas(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		Map<String, Object> resMap = sService.updateTechAppnBas(map);
		MessageUtil.outputCallbackMessage(messageSourceAccessor, response, resMap);
	}

}


