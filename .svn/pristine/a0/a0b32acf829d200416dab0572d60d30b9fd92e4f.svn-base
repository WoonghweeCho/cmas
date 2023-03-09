/**
 * <pre>
 * --------------------------------------------------------------
 * 업무구분 : 공통
 * 프로그램 : Site
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.info.domain.SiteDomain;
import com.dwenc.cmas.info.service.SiteService;

import docfbaro.common.MapUtil;
import docfbaro.iam.UserInfo;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

@Controller
@RequestMapping("/info/*")
public class SiteController {

    @Autowired
    private SiteService siteService;
    /**
     * <PRE>
     *  코드 단건 조회
     * </PRE>
     * @param request
     * @param response
     */
    @RequestMapping("siteLocList.*")
    public void retrieveCode(MciRequest request, MciResponse response){
        Map<String, Object> data = request.getParam();
        List<SiteDomain> returnData = siteService.siteLocList(data); //
        response.setList("output", returnData);
    }


}
