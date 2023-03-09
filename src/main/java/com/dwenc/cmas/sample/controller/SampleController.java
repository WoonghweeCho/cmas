package com.dwenc.cmas.sample.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.utils.RequestUtil;

import com.dwenc.cmas.sample.domain.Draft;
import com.dwenc.cmas.sample.service.SampleService;

import docfbaro.common.WebContext;
import docfbaro.iam.UserInfo;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

@Controller
@RequestMapping("/sample/*")
public class SampleController {


	/**
	 * Logger 객체 생성
	 */
	private static Logger logger = LoggerFactory.getLogger(SampleController.class);

	/**
	 * 해당 Controller와 연결된 Service Class
	 */
    @Autowired
    private SampleService service;

    /**
     * Common Message 처리
     */
    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    /**
     * 마스터 조회
     * @param request
     * @param response
     */
    @RequestMapping("retrieveDraftList.*")
	public void retrieveDraftList(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Draft> ds_DraftList = service.retrieveDraftList(map);
		response.setList("ds_DraftList", ds_DraftList);  		//Map을 Client로 전송
	}

    @RequestMapping("SignCallBack.*")
	public void signCallBack(MciRequest request, MciResponse response) {
    	Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> data = request.get("json", HashMap.class);
		Map<String, Object> result = new HashMap<String, Object>();

		System.out.println("-----------------------------------");
		System.out.println(data);
		System.out.println("-----------------------------------");
		String signId = data.get("signId").toString();
		String docStsCd = data.get("docStsCd").toString();

		if(docStsCd.equals("D01"))			// 결재(요청)화면 오픈 시
			System.out.println("신규작성되었습니다.");
		else if(docStsCd.equals("D02"))		// 결재중 / 상신시 / 협의시
			System.out.println("결재중입니다.");
		else if(docStsCd.equals("D03"))		// 결재완료
			System.out.println("결재완료되었습니다.");
		else if (docStsCd.equals("D04")) 	// 반려
			System.out.println("반려되었습니다.");
		else if (docStsCd.equals("D05"))	// 회수
			System.out.println("회수되었습니다.");
		else if (docStsCd.equals("D06"))
			System.out.println("삭제되었습니다.");
		else if (docStsCd.equals("D07"))	// 결재요청취소 OR 창닫기, 저장 후 창 닫아도 콜백옴
			System.out.println("창닫기되었습니다.");
		else if (docStsCd.equals("D08")){	// 상신
			System.out.println("상신완료되었습니다.");
			docStsCd = "D02";
		}
		else if (docStsCd.equals("D09"))	// 이단결재대기
			System.out.println("이단결재대기되었습니다.");
		else if (docStsCd.equals("D11"))	// 협의요청
			System.out.println("협의요청되었습니다.");
		else if (docStsCd.equals("D12"))	// 협의완료
			System.out.println("협의완료되었습니다.");
		else if (docStsCd.equals("D16"))	// 저장시
			System.out.println("임시저장되었습니다.");

		map.put("signId", signId);			// 결재문서번호
		map.put("docStsCd", docStsCd); 		// 문서상태
		service.saveDraft(map);

		// 후처리

//		resMap.put("TYPE", "FAIL");
//		result.put("MSG", "CMAS : 성공");

		result.put("TYPE", "SUCCESS");
		result.put("MSG", "CMAS : 성공");

		response.setMap("RESULT", result);
    }
}

