package com.dwenc.cmas.trip.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.utils.RequestUtil;
import com.dwenc.cmas.info.domain.SiteDomain;
import com.dwenc.cmas.trip.domain.InnerTrip;
import com.dwenc.cmas.trip.service.BdgtService;
import com.dwenc.cmas.trip.service.InnerTripService;
import com.dwenc.cmas.trip.util.CmasToEaiWSTest;

import docfbaro.common.WebContext;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

@Controller
@RequestMapping("/trip/bdgt/*") /* 개발하는 업무에 대한 HTTP Request URI */
public class BdgtController {

	/**
	 * Logger 객체 생성
	 */
	private static Logger logger = LoggerFactory.getLogger(BdgtController.class);

	/**
	 * 해당 Controller와 연결된 Service Class
	 */
//    @Autowired
//    private InnerTripService sService;

    /**
     * Common Message 처리
     */
    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @Autowired
    private CmasToEaiWSTest cmasToEaiWSTest;

    @Autowired
    private BdgtService bdgtService;


    /**
     * 조회
     * @param request
     * @param response
     */
    @RequestMapping("getBdgtNoList.*")
	public void getBdgtNoList(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		String pa = (String)map.get("Picgubun");
		String pb = (String)map.get("Piexecteam");
		String pc = (String)map.get("Piteamnm");
		String pd = (String)map.get("Piexenm");
		String pe = (String)map.get("Piiokey");
		String pf = (String)map.get("Piaufnr");


		System.out.println("pa : " + pa);
		System.out.println("pb : " + pb);
		System.out.println("pc : " + pc);
		System.out.println("pd : " + pd);
		System.out.println("pe : " + pe);
		System.out.println("pf : " + pf);


		try{

			cmasToEaiWSTest.getBdgtNoList(pa, pb, pc, pd, pe, pf);

		}catch(Exception e){
			e.printStackTrace();
		}
//		response.setMapList("output1", interfaceLogList);  		//Map을 Client로 전송
	}

    /*
     * 현장 경비 조회
     */
    @RequestMapping("getPrctrList.*")
	public void getPrctrList(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> prctrList = bdgtService.getPrctrList(map);

		response.setMapList("prctrList", prctrList);  		//Map을 Client로 전송
	}

    /*
     * PJ 경비 조회
     */
    @RequestMapping("getPrctrList1.*")
	public void getPrctrList1(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> prctrList1 = bdgtService.getPrctrList1(map);

		response.setMapList("prctrList1", prctrList1);  		//Map을 Client로 전송
	}


}

