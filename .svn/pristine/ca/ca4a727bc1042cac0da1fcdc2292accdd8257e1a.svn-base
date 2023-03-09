/**
 * <pre>
 * --------------------------------------------------------------
 * 업무구분 : 공통
 * 프로그램 : HQFloor
 * 설 명 : 코드 관리를 위한 controller 클래스
 * 작 성 자 :
 * 작성일자 : 2011-10-30
 * 수정이력
 * --------------------------------------------------------------
 * 수정일                          이 름          사유
 * --------------------------------------------------------------
 * 2011-10-30
 *
 * --------------------------------------------------------------
 * </pre>
 * @version 1.0
 *
 */

package com.dwenc.cmas.info.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jcf.data.GridData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.utils.MessageUtil;
import com.dwenc.cmas.common.utils.RequestUtil;
import com.dwenc.cmas.info.domain.FloorDomain;
import com.dwenc.cmas.info.service.FloorService;

import docfbaro.common.MapUtil;
import docfbaro.common.WebContext;
import docfbaro.iam.UserInfo;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;


@Controller
@RequestMapping("/info/*")
public class FloorController {

	/**
	 * 해당 Controller와 연결된 Service Class
	 */

    @Autowired
    private FloorService floorService;

    /**
     * Common Message 처리
     */
    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    /**
     * <PRE>
     *  코드 단건 조회
     * </PRE>
     * @param request
     * @param response
     */
    @RequestMapping("floorList.*")
    public void retrieveCode(MciRequest request, MciResponse response){
        Map<String, Object> data = request.getParam();
        List<FloorDomain> returnData = floorService.floorList(data); //
        response.setList("output", returnData);
    }

    /**
     * 층별정보 저장
     */
    @RequestMapping("insertFloor.*")
	public void insertFloor(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		Map<String, Object> resMap = floorService.insertFloor(map);
		MessageUtil.outputCallbackMessage(messageSourceAccessor, response, resMap);
	}

    /**
     * 층별정보 업데이트
     */
    @RequestMapping("updateFloor.*")
	public void updateFloor(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		Map<String, Object> resMap = floorService.updateFloor(map);
		MessageUtil.outputCallbackMessage(messageSourceAccessor, response, resMap);
	}

    /**
     * 층별정보 삭제
     */
    @RequestMapping("deleteFloor.*")
	public void deleteFloor(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		Map<String, Object> resMap = floorService.deleteFloor(map);
		MessageUtil.outputCallbackMessage(messageSourceAccessor, response, resMap);
	}

}

