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
@RequestMapping("/tech/techAppn/*") /* 개발하는 업무에 대한 HTTP Request URI */
public class TechDatController {
	/**
	 * Logger 객체 생성
	 */
	private static Logger logger = LoggerFactory.getLogger(TechDatController.class);

	/**
	 * 해당 Controller와 연결된 Service Class
	 */
    @Autowired
    private TechDatService sService;

    @Autowired
    private MailService mService;


    /**
     * Common Message 처리
     */
    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    /**
     * docNo 조회
     * @param request
     * @param response
     */
    @RequestMapping("getDocNo.*")
	public void getDocNo(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		String docNo = (String) sService.getDocNo(map).get("docNo");
		map.put("docNo", docNo);
		//map.put("fstRegUserId", UserInfo.getUserId());

	//	response.setMap("ds_Result", map);
	//	sService.insertTechAppnBas(map);
	}


    /**
     * 도서자료저장
     * @param request
     * @param response
     */
    @RequestMapping("insertTechDat.*")
	public void insertTechDat(MciRequest request, MciResponse response) {
    	/**
    	 * Client에서 전송한 key:value 파라미터를 Map 형태로 받음
    	 */
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		/**
		 * Client에서 전송한 DataSet을 DocfBaro Framework의 GridData로 전송 받음
		 */
		//GridData<TechDat> TechDat = request.getGridData("input1", TechDat.class);
		Map<String, Object> resMap = sService.insertTechDat(map);

		MessageUtil.outputCallbackMessage(messageSourceAccessor, response, resMap);
	}

    /**
     * DVD자료저장
     * @param request
     * @param response
     */
    @RequestMapping("insertTechDvdDat.*")
	public void insertTechDvdDat(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		Map<String, Object> resMap = sService.insertTechDvdDat(map);
		MessageUtil.outputCallbackMessage(messageSourceAccessor, response, resMap);
	}


    /**
     * DVD자료수정
     **/
    @RequestMapping("updateTechDvdDat.*")
	public void updateTechDvdDat(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		Map<String, Object> resMap = sService.updateTechDvdDat(map);
		MessageUtil.outputCallbackMessage(messageSourceAccessor, response, resMap);
	}


    /**
     * 자료수정
     **/
    @RequestMapping("upateTechDat.*")
	public void upadteTechDat(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		Map<String, Object> resMap = sService.updateTechDat(map);
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
     * 도서/간행물 자료조회
     **/
    @RequestMapping("retrieveTechDatList.*")
	public void retrieveTechDatList(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<TechDat> ds_TechDataList = sService.retrieveTechDatList(map);
		response.setList("ds_TechDataList", ds_TechDataList);  		//List을 Client로 전송
	}

    /**
     * DVD 자료조회
     **/
    @RequestMapping("retrieveTechDvdDatList.*")
	public void retrieveTechDvdDatList(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<TechDat> ds_TechDataList = sService.retrieveTechDvdDatList(map);
		response.setList("ds_TechDataList", ds_TechDataList);  		//List을 Client로 전송
	}

    /**
     * 해외산업규격 자료조회
     **/
    @RequestMapping("retrieveTechOsDatList.*")
	public void retrieveTechOsDatList(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<TechDat> ds_TechDataList = sService.retrieveTechOsDatList(map);
		response.setList("ds_TechDataList", ds_TechDataList);  		//List을 Client로 전송
	}

    /**
     * 도서/DVD 신청 조회
     **/
    @RequestMapping("retrieveTechAppnList.*")
	public void retrieveTechAppnList(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<TechAppn> ds_TechAppnList = sService.retrieveTechAppnList(map);
		response.setList("ds_TechAppnList", ds_TechAppnList);  		//List을 Client로 전송
	}

    /**
     * 해외산업규격 조회
     **/
    @RequestMapping("retrieveTechOsAppnList.*")
	public void retrieveTechOsAppnList(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<TechAppn> ds_TechAppnList = sService.retrieveTechOsAppnList(map);
		response.setList("ds_TechAppnList", ds_TechAppnList);  		//List을 Client로 전송
	}

    /**
     * 도서/간행물 신청 Bas
     */
    @RequestMapping("insertTechAppnBas.*")
	public void insertTechAppnBas(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		Map<String, Object> resMap = sService.insertTechAppnBas(map);
		MessageUtil.outputCallbackMessage(messageSourceAccessor, response, resMap);
	}


    /**
     * 도서/간행물 신청 Dat
     * * @param request
     * @param response
     */
    @RequestMapping("updateTechAppnDat.*")
	public void updateTechAppnDat(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		Map<String, Object> resMap = sService.updateTechAppnDat(map);
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


    /**
     * 공지사항 자료조회
     **/
    @RequestMapping("retrieveTechNoticeList.*")
	public void retrieveTechNoticeList(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<TechDat> ds_TechDataList = sService.retrieveTechNoticeList(map);
		response.setList("ds_TechDataList", ds_TechDataList);  		//List을 Client로 전송
	}

    /**
     * 공지사항 저장
     */
    @RequestMapping("insertTechNotice.*")
	public void insertTechNotice(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		Map<String, Object> resMap = sService.insertTechNotice(map);
		MessageUtil.outputCallbackMessage(messageSourceAccessor, response, resMap);
	}

    /**
     * 공지사항 업데이트
     */
    @RequestMapping("updateTechNotice.*")
	public void updateTechNotice(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		Map<String, Object> resMap = sService.updateTechNotice(map);
		MessageUtil.outputCallbackMessage(messageSourceAccessor, response, resMap);
	}


    /**
     * 해왜산업규격 문서상태 변경
     */
    @RequestMapping("updateTechOsAppnBas.*")
	public void updateTechOsAppnBas(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		Map<String, Object> resMap = sService.updateTechOsAppnBas(map);
		MessageUtil.outputCallbackMessage(messageSourceAccessor, response, resMap);
	}


    @RequestMapping("sendSTypeMail.*")
 	public void sendSTypeMail(MciRequest request, MciResponse response) throws Exception{
 		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

 		// 메일 전송
 		GridData<HashMap> data = request.getGridData("input1", HashMap.class);
 		String mailYn = (String)map.get("mailYn");
 		if(mailYn.equals("Y")){
 			mService.sendMail(data);
 		}
 	}


}


