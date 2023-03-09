package com.dwenc.cmas.common.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;


/**
* 통합결재시스템에서 전달된 HTTP Request(Callback)를 수신하고,
* 처리결과를 Response로 반환하는 Sample Servlet Class
*/
public class CallBackServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//resp.setCharacterEncoding("utf-8"); //WAS Version 에 따라 설정 값 유효
		resp.setContentType("application/json+sua; charset=UTF-8");
		process(req, resp);
	}

	/**
	* 통합결재시스템에서 전달된 HTTP Request(Callback)를 수신하고,
	* 처리결과를 Response로 반환함
	*/
	protected void process(HttpServletRequest req, HttpServletResponse resp)  {
//		JSONObject json = (JSONObject) HttpReceive.getParamToJSON(req);
//		JSONObject sub = json.getJSONObject("json");
		Map param = new HashMap();
		String signUserId = null;
		String docId = null;
		String returnDocId = null;
		String docStsCd = null;
//		Z01LoginUser loginUser = null;

//		System.out.println(sub);

		// 받은 signId → docId로 입찰참가원 데이터 조회 후, request에 할당
		try {
//			signUserId = (String)sub.get("signUserId");

			// 통합결재로부터 온 결재자와 영업시스템에 있는 결재자와 다를 경우, 결재 안됨
//			loginUser = new Z01LoginUser(signUserId);

//			docId = new ApprovalBean().getDocId( (String)sub.get("signId") );
//			docStsCd = (String)sub.get("docStsCd");
			System.out.println("getDocId() : "+docId);
		} catch (Exception e) {
		}

/*************************************************************************************************
 **																																									**
 **													     입찰참가원, [정정공고] 입찰참가원													    **
 **																																									**
*************************************************************************************************/

//		if ( ((String)sub.get("programCode")).equals("A01X010001") || ((String)sub.get("programCode")).equals("A01X010002") ) {
//			try {
//				req = new BBAFlowHandler().setRequestData(req, docId, docStsCd);
//				returnDocId = new BBAFlowHandler().decide(req,loginUser);
//				System.out.println("returnDocID = " + returnDocId);
//			} catch (Exception e) {
//			}
//			if (ConChar.convertNull(returnDocId).equals(docId) || ConChar.convertNull(returnDocId) == docId ) {
//				System.out.println("성공메세지 전송");
//				param.put("TYPE", "SUCCESS");
//				param.put("MSG", "TODO : CSYS Message Code");
//			} else {
//				System.out.println("실패메세지 전송");
//				param.put("TYPE", "FAIL");
//				param.put("MSG", "TODO : CSYS Message Code");
//			}
//		//처리결과 Response로 반환
//		HttpReceive.sendResponse(resp, param);
	}

}
