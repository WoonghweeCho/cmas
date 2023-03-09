package com.dwenc.cmas.trip.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import jcf.data.GridData;
import jcf.data.RowStatus;
import jcf.data.RowStatusCallback;
import jcf.sua.dataset.DataSet;
import jcf.sua.dataset.DataSetImpl;
import jcf.sua.dataset.GridDataImpl;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.utils.RequestUtil;
import com.dwenc.cmas.trip.service.CityTranspService;
import com.dwenc.cmas.trip.service.InnerTripService;
import com.dwenc.cmas.trip.service.OuterTripService;
import com.dwenc.cmas.trip.util.CmasToEaiWSTest;
import com.dwenc.cmas.trip.util.SignProcess;

import docfbaro.common.ObjUtil;
import docfbaro.common.WebContext;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

@Controller
@RequestMapping("/trip/eai/*") /* 개발하는 업무에 대한 HTTP Request URI */
public class EaiSampleController {

	/**
	 * Logger 객체 생성
	 */
	private static Logger logger = LoggerFactory.getLogger(EaiSampleController.class);

	/**
	 * 해당 Controller와 연결된 Service Class
	 */
//    @Autowired
//    private InterfaceLogService sService;

    /**
     * Common Message 처리
     */
    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @Autowired
    private CmasToEaiWSTest cmasToEaiWSTest;

    @Autowired
    private InnerTripService iService;

    @Autowired
    private CityTranspService cService;

    @Autowired
    private OuterTripService oService;


    @Autowired
    private SignProcess signProcess;


    /**
     * 조회
     * @param request
     * @param response
     */
    @RequestMapping("getBdgtNoList.*")
	public void callToEaiSample(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		String pa = (String)map.get("Picgubun");
		String pb = (String)map.get("Piexecteam");
		String pc = (String)map.get("Piteamnm");
		String pd = (String)map.get("Piexenm");
		String pe = (String)map.get("Piiokey");
		String pf = (String)map.get("Piaufnr");

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try{

			resultMap = cmasToEaiWSTest.getBdgtNoList(pa, pb, pc, pd, pe, pf);

		}catch(Exception e){
			e.printStackTrace();
		}

		response.setMap("output1", resultMap);
	}

    /**
     * I 조회
     * @param request
     * @param response
     */
    @RequestMapping("getSendBudgetDocList.*")
	public void getSendBudgetDocList(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		String pa = (String)map.get("Gubun");
		String pb = (String)map.get("Measure");

		try{

			cmasToEaiWSTest.getSendBudgetDoc(pa, pb);

		}catch(Exception e){
			e.printStackTrace();
		}
//		response.setMapList("output1", interfaceLogList);  		//Map을 Client로 전송
	}

    /**
     * Q 조회
     * @param request
     * @param response
     */
    @RequestMapping("getPrctrList.*")
	public void getPrctrList(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		String pa = (String)map.get("Bukrs");
		String pb = (String)map.get("Prctrnm");

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try{

			resultMap = cmasToEaiWSTest.getPrctrList(pa, pb);

		}catch(Exception e){
			e.printStackTrace();
		}
//		response.setMapList("output1", interfaceLogList);  		//Map을 Client로 전송

		response.setMap("output1", resultMap);
	}


    /**
     * 국내출장상신
     * @param request
     * @param response
     */
    @RequestMapping("getSendBizTripApplication.*")
	public void getSendBizTripApplication(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> detailDataList = request.getGridData("ds_DetailData", HashMap.class);

		Map<String, Object> resultMap = new HashMap<String, Object>();

		boolean sapResult = true;

		try{

			resultMap = cmasToEaiWSTest.getSendBizTripApplication(map);

		}catch(Exception e){
			e.printStackTrace();

			//sap 에러 처리
			sapResult = false;
		}
//		response.setMapList("output1", interfaceLogList);  		//Map을 Client로 전송

		// 국내출장 상신 후 CMAS DOC Insert 를 실행한다.
		// 실패시 반려 처리 한다.
		// resultData.output1[0];
  		// SAP 으로 부터 상신되어옴 REFNO 를 삽입한다.
  		// REFNO 로 SAP DATA 조회 가능

  		// SAP 상신 성공시 DB INSERT
  		// SGNS CALLBACK

//		response.setMap("output1", resultMap);

//		String ErrMsg = (String)resultMap.get("ErrMsg");

		logger.debug("ErrMsg : " + resultMap.get("ErrMsg"));

		if(resultMap.get("ErrMsg") != null){
			response.setMap("output1", resultMap);
		}else if(resultMap.get("ORefkeyNo") == null){
			// refNo 가 null 로 상신되는 경우 방생
			// 방지 처리
			Map<String, Object> failMap = new HashMap<String, Object>();

			failMap.put("ErrMsg", "상신중 에러가 발생 하였습니다.(EAI SAP호출)");
			response.setMap("output1", failMap);
		}else if(!sapResult){
			// SAP 통신 중 Exception 발생
			// 방지 처리
			Map<String, Object> failMap = new HashMap<String, Object>();

			failMap.put("ErrMsg", "SAP 서버와 통신 중 에러가 발생하였습니다.");
			response.setMap("output1", failMap);
		}else{

			// 생성된 RefNo 값 입력
  			String refNo = (String)resultMap.get("ORefkeyNo");
  			System.out.println("refNo : " + refNo);
  			// 업무대행자
			String dutyAgncUserId = (String)map.get("dutyAgncUserId");
			String dutyAgncOrgCd = (String)map.get("dutyAgncOrgCd");
			String signId = ""; // 상신 결과는 CallBackServlet 에서 처리

			String stYmd = (String)map.get("iTravFdate");// 맨처음 날린거 사용
			String edYmd = (String)map.get("iTravTdate");// 맨처음 날린거 사용

			String ddExpnExcl = (String)map.get("ddExpnExcl");//일비용제외
  			String accomoExpnExcl = (String)map.get("accomoExpnExcl");//숙박비용제외
			String eduBustrYn = (String)map.get("eduBustrYn");//교육출장여부

			String signStsCd = "D02";

			// 집행팀 이름이 sap 에서 전달되지 않으므로 임시 파라미터로 저장하여 갖고 있는다.
			// 교통비 수령인도 갖고 있는다.
			String ifParam = "{\"kostvnm\":\"" + (String)map.get("iBdgtTeamNm")+ "\",\"receiptUserNm\":\"" + (String)map.get("receiptUserNm") + "\"}";
			System.out.println("ifParam : " + ifParam);

			String docNo = (String)map.get("docNo");//교육출장여부
			String userId = (String)map.get("userId");//교육출장여부
			String userOrgCd = (String)map.get("userOrgCd");//교육출장여부

			String fileAtchId = (String)map.get("fileAtchId");//첨부파일

			String genAccomoDcnt = (String)map.get("genAccomoDcnt");//일반숙박일수
			String accomoAmt = (String)map.get("accomoAmt");//숙박금액
			String eexpAmt = (String)map.get("eexpAmt");//식대금액
			String etcAmt = (String)map.get("etcAmt");//기타금액
			String totAmt = (String)map.get("totAmt");//총금액
			String rem = (String)map.get("rem");//비고


			Map<String, Object> dMap = new HashMap<String, Object>();

			dMap.put("docNo", docNo);
			dMap.put("refNo", refNo);
			dMap.put("dutyAgncUserId", dutyAgncUserId);
			dMap.put("dutyAgncOrgCd", dutyAgncOrgCd);
			dMap.put("signId", signId);
			dMap.put("signStsCd", signStsCd);
			dMap.put("userId", userId);
			dMap.put("stYmd", stYmd);
			dMap.put("edYmd", edYmd);
			dMap.put("userOrgCd", userOrgCd);
			dMap.put("ddExpnExcl", ddExpnExcl);
			dMap.put("accomoExpnExcl", accomoExpnExcl);
			dMap.put("eduBustrYn", eduBustrYn);
			dMap.put("ifParam", ifParam);
			dMap.put("fileAtchId", fileAtchId);
			dMap.put("genAccomoDcnt", genAccomoDcnt);
			dMap.put("accomoAmt", accomoAmt);
			dMap.put("eexpAmt", eexpAmt);
			dMap.put("etcAmt", etcAmt);
			dMap.put("totAmt", totAmt);
			dMap.put("rem", rem);




			String docSts = (String)map.get("docSts");

			int result = 1;

			try{

				iService.updateInnerTripDraftDoc(dMap);
				iService.deleteInnerTripDtlByDocNo(dMap);
				iService.saveInnerTripDraftDetail(detailDataList, (String)dMap.get("docNo"));

			}catch(Exception e){
				e.printStackTrace();
				result = 0;
			}

			/**
			 * 진행 중 실패한 과정이 있으면 반려 처리한다.
			 * 상신 실패시 밤려 처리 한다.
			 */
			if(result == 0){
				// insert 혹은 update 실패
				// 상신 반려 처리한다.

				// Param Map
				Map<String, Object> paramMap = new HashMap<String, Object>();

				Map<String, Object> cResultMap = new HashMap<String, Object>();

				paramMap.put("IRefkey", refNo);
				paramMap.put("INotesdoc", docNo);
				paramMap.put("IConfirm", "N");
				paramMap.put("ISenderId", (String)map.get("iSenderId"));

				try{

					cResultMap = cmasToEaiWSTest.confirmBizTrip(paramMap);

				}catch(Exception e){
					e.printStackTrace();
				}

				// 문서 삭제
				iService.deleteInnerTripByDocNo(dMap);
				iService.deleteInnerTripDtlByDocNo(dMap);

			}

			resultMap.put("result", Integer.toString(result));
//			response.setMap("output1", resultMap);

			// 2015 08 06 모든 처리를 서버로 돌림

			// SGNS REMOTE DRAFT
			//
			System.out.println("ErrMsg : " + resultMap.get("ErrMsg"));

			if(!ObjUtil.isNull(resultMap.get("ErrMsg"))){
				System.out.println("ErrMsg 가 존재");
			}else{
				System.out.println("ErrMsg 가 존재하지 않음");
				// SAP 상신 성공시 DB INSERT
	  			// SAP 상신 결과를 임시 전역변수에 저장

				//resultMap
				//v_SapResult = result;

				String sRefNo = (String)resultMap.get("ORefkeyNo");
				String execTeam;
				if(resultMap.get("OPrctrTxt") != null){
					execTeam = (String)resultMap.get("OPrctr")+ " " + ((String)resultMap.get("OPrctrTxt")).replaceAll("&","＆"); //집행팀 특수문자 변환
				}else{
					execTeam = (String)resultMap.get("OPrctr");
				}
				String account = (String)resultMap.get("OAccountNo");
				String dayAmt = (String)resultMap.get("ODayTot");
				String nightAmt = (String)resultMap.get("ONightTot");

				map.put("refNo", sRefNo);
				map.put("execTeam", execTeam);
				map.put("account", account);
				map.put("dayAmt", dayAmt);
				map.put("nightAmt", nightAmt);

				HttpServletRequest hRequest = WebContext.getRequest();
				String serverName = hRequest.getServerName();

				map.put("serverName", serverName);

				Map<String, Object> rMap = new HashMap<String, Object>();

				GridData<HashMap> isignInfoList = request.getGridData("ds_Signln", HashMap.class);
				List<HashMap> lSignInfo = isignInfoList.getList();
				// 새로운 리스트에 담는다.
				List<HashMap> lSignInfo2 = new ArrayList<HashMap>();

				int cnt = 0;

				for (int i = 0; i < lSignInfo.size(); i++) {

					HashMap<String, Object> tempSign = lSignInfo.get(i);
					System.out.println("index : " + i);
					System.out.println("tempSign : " + tempSign.get("signUserId"));
					tempSign.put("signSeq", cnt+1);
					lSignInfo2.add(tempSign);

					cnt = cnt + 1;
				}

				try{
					rMap = signProcess.signRequest(map, lSignInfo2);

				}catch(Exception e){
					e.printStackTrace();

					// SGNS 등록 실패
					// SUCCESS 가 아닌 다른 경우 상신 실패 상태로 업데이트 함

					Map<String, Object> fMap = new HashMap<String, Object>();

					fMap.put("docNo", docNo);
					fMap.put("docSts", "D99");

//					iService.saveCmasDocUpdateFail(fMap);

		  			// 상신 반려 처리한다.

					// Param Map
					Map<String, Object> paramMap = new HashMap<String, Object>();

					Map<String, Object> cResultMap = new HashMap<String, Object>();

					paramMap.put("IRefkey", refNo);
					paramMap.put("INotesdoc", docNo);
					paramMap.put("IConfirm", "N");
					paramMap.put("ISenderId", (String)map.get("iSenderId"));

					try{

						cResultMap = cmasToEaiWSTest.confirmBizTrip(paramMap);

					}catch(Exception ex){
						ex.printStackTrace();
					}

					// 문서 삭제
//					iService.deleteInnerTripByDocNo(dMap);
//					iService.deleteInnerTripDtlByDocNo(dMap);

					Map<String, Object> failMap = new HashMap<String, Object>();

					failMap.put("ErrMsg", "통합결재 시스템 등록에 실패하였습니다.");
					response.setMap("output1", failMap);
				}

//				response.setMap("output1", rMap);


				System.out.println("type : " + rMap.get("type"));
				String type = (String)rMap.get("type");

				if(type.equals("SUCCESS")){
					// SGNS 등록 성공
					signId = (String)rMap.get("signId");
					String signInfo = (String)rMap.get("signInfo");

//			  		signInfo=1DFUR^1202429^1^T01##1DFUR^1202564^2^T02


					List signInfoList = new ArrayList();

					// string 이 비어 있지 않다면
					if (!signInfo.isEmpty()) {
						// legacy info 분해 작업을 시작한다.
						String strAry[] = signInfo.split("\\#\\#");
						// legacy info 는 key:value||key:value 의 형태로 전달되기때문에 || 를 기준으로 split
						// 한것을 : 으로 다시 split 하여 map 에 값을 넣는다.
						// value 가 key:value^value 로 연결된 1차월 배열 또는
						// key:value^value##value^value 이런 형태로연결된 2차원 배열의 경우는 각 업무의
						// 서비스단에서 처리 하도록 한다.
						for (int i = 0; i < strAry.length; i++) {
							String subAry[] = strAry[i].split("\\^");
							String tSignStsCd = "";
				  			if(subAry[2].equals("1")){
				  				tSignStsCd = "S02";
				  			}else{
				  				tSignStsCd = "S04";
				  			}
				  			String signDt = "N";
				  			if(subAry[3].equals("T01")){
				  				signDt = "Y";
				  			}

				  			Map<String, Object> signMap = new HashMap<String, Object>();

				  			signMap.put("apperOrgCd", subAry[0]);
				  			signMap.put("signUserId", subAry[1]);
				  			signMap.put("signSeq", subAry[2]);
				  			signMap.put("signTpCd", subAry[3]);
				  			signMap.put("signStsCd", tSignStsCd);
				  			signMap.put("signDt", signDt);

				  			signInfoList.add(signMap);
						}
					}

					String dutyCls = "01";

					iService.saveSignInfoInnerTripFromList(signInfoList, docNo, signId, dutyCls);

					Map<String, Object> sMap = new HashMap<String, Object>();

					sMap.put("type", "SUCCESS");

					response.setMap("output1", sMap);

				}else{
					// SGNS 등록 실패
					// SUCCESS 가 아닌 다른 경우 상신 실패 상태로 업데이트 함

					Map<String, Object> fMap = new HashMap<String, Object>();

					fMap.put("docNo", docNo);
					fMap.put("docSts", "D99");

//					iService.saveCmasDocUpdateFail(fMap);

		  			// 상신 반려 처리한다.

					// Param Map
					Map<String, Object> paramMap = new HashMap<String, Object>();

					Map<String, Object> cResultMap = new HashMap<String, Object>();

					paramMap.put("IRefkey", refNo);
					paramMap.put("INotesdoc", docNo);
					paramMap.put("IConfirm", "N");
					paramMap.put("ISenderId", (String)map.get("iSenderId"));

					try{

						cResultMap = cmasToEaiWSTest.confirmBizTrip(paramMap);

					}catch(Exception e){
						e.printStackTrace();
					}

					// 문서 삭제
//					iService.deleteInnerTripByDocNo(dMap);
//					iService.deleteInnerTripDtlByDocNo(dMap);


					Map<String, Object> failMap = new HashMap<String, Object>();

					failMap.put("ErrMsg", "통합결재 시스템 등록에 실패하였습니다.");
					response.setMap("output1", failMap);
				}

	  		}

			// SAVE_CMAS_DOC_UPDATE_SIGN_ID



		}


	}

    /**
     * 국출, 해출 REFNO 로 전표 조회
     * @param request
     * @param response
     */
    @RequestMapping("getTripExpenseSearch.*")
	public void getTripExpenseSearch(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try{

			resultMap = cmasToEaiWSTest.getTripExpenseSearch(map);

		}catch(Exception e){
			e.printStackTrace();
		}
//		response.setMapList("output1", interfaceLogList);  		//Map을 Client로 전송

		response.setMap("output1", resultMap);
	}

    /**
     * 직급별 출장비 조회
     * @param request
     * @param response
     */
    @RequestMapping("getSendTripExpenseStandard.*")
	public void getSendTripExpenseStandard(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try{

			resultMap = cmasToEaiWSTest.getSendTripExpenseStandard(map);

		}catch(Exception e){
			e.printStackTrace();
		}
//		response.setMapList("output1", interfaceLogList);  		//Map을 Client로 전송

		response.setMap("output1", resultMap);
	}

    /**
     * 시내 교통비 상신
     * @param request
     * @param response
     */
    @RequestMapping("getSendTrafficCost.*")
	public void getSendTrafficCost(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> detailDataList = request.getGridData("ds_TUserList", HashMap.class);

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try{

			resultMap = cmasToEaiWSTest.getSendTrafficCost(map, detailDataList);

		}catch(Exception e){
			e.printStackTrace();
		}
//		response.setMapList("output1", interfaceLogList);  		//Map을 Client로 전송

//		response.setMap("output1", resultMap);


		if(!ObjUtil.isNull(resultMap.get("SapDoctab"))){
			if(!ObjUtil.isNull(resultMap.get("SapErrtab"))){
				String SapErrtabType = resultMap.get("SapErrtab").getClass().getName();
//				System.out.println("SapErrtabType : " + SapErrtabType);
				// java.util.HashMap
				// 에러가 단일이라면 HashMap Class 로 온다.
				if(SapErrtabType.equals("java.util.HashMap")){
					Map<String, Object> errMap = (HashMap)resultMap.get("SapErrtab");
					System.out.println((String)errMap.get("Message"));
					Map<String, Object> failMap = new HashMap<String, Object>();

					failMap.put("ErrMsg", (String)errMap.get("Message"));
					response.setMap("output1", failMap);
				}

				// java.util.ArrayList
				// 에러가 멀티라면 ArrayList Class 로 온다.
				if(SapErrtabType.equals("java.util.ArrayList")){

					List errList = (ArrayList)resultMap.get("SapErrtab");

					String errStr = "";
					for(int i = 0; i < errList.size(); i++){
						Map<String, Object> tempMap = (HashMap)errList.get(i);
						System.out.println((String)tempMap.get("Message"));
						errStr += (String)tempMap.get("Message") + "\n";

					}
					Map<String, Object> failMap = new HashMap<String, Object>();

					failMap.put("ErrMsg", errStr);
					response.setMap("output1", failMap);
				}

			}else{
//				System.out.println("Error 가 없음 진행할 것");
				String SapDoctabType = resultMap.get("SapDoctab").getClass().getName();
				System.out.println("SapDoctabType : " + SapDoctabType);

				String refKey = "err";
				String Kostl1 = "err";
				String Kostxt = "err";

				if(SapDoctabType.equals("java.util.HashMap")){
					Map<String, Object> sapMap = (HashMap)resultMap.get("SapDoctab");
					refKey = (String)sapMap.get("Refkey");
					Kostl1 = (String)sapMap.get("Kostl1");
					Kostxt = (String)sapMap.get("Kostxt");

				}

				// java.util.ArrayList
				// 에러가 멀티라면 ArrayList Class 로 온다.
				if(SapDoctabType.equals("java.util.ArrayList")){

					List sapList = (ArrayList)resultMap.get("SapDoctab");
					Map<String, Object> tempMap = (HashMap)sapList.get(0);
					refKey = (String)tempMap.get("Refkey");
					Kostl1 = (String)tempMap.get("Kostl1");
					Kostxt = (String)tempMap.get("Kostxt");
				}

				if(refKey == "err"){
					System.out.println("refKey Error 반려처리 할 것");

				}else{
					System.out.println("ref 생성 성공 : " + refKey);
					System.out.println("집행팀 : " + Kostl1 + " " + Kostxt);
					map.put("refNo", refKey);

//					var execTeam = v_SapDoc.Kostl1 + " " + v_SapDoc.Kostxt; // 집행팀
					//String execTeam = Kostl1 + " " + Kostxt.replaceAll("&","＆"); //집행팀 특수문자 변환

					String execTeam;
					if(Kostxt != null){
						execTeam = Kostl1 + " " + Kostxt.replaceAll("&","＆"); //집행팀 특수문자 변환
					}else{
						execTeam = Kostl1;
					}
					map.put("execTeam", execTeam);

					GridData<HashMap> dtlDataList = request.getGridData("ds_DraftDtl", HashMap.class);

					cService.updateCityTranspDraftDoc(map);
					cService.deleteCityTranspDtlByDocNo(map);
					cService.saveCityTranspDraftDtl(dtlDataList, (String)map.get("docNo"));

					HttpServletRequest hRequest = WebContext.getRequest();
					String serverName = hRequest.getServerName();

					map.put("serverName", serverName);

					Map<String, Object> rMap = new HashMap<String, Object>();

					GridData<HashMap> isignInfoList = request.getGridData("ds_Signln", HashMap.class);
					List<HashMap> lSignInfo = isignInfoList.getList();
					// 새로운 리스트에 담는다.
					List<HashMap> lSignInfo2 = new ArrayList<HashMap>();

					int cnt = 0;

					for (int i = 0; i < lSignInfo.size(); i++) {

						HashMap<String, Object> tempSign = lSignInfo.get(i);
						System.out.println("index : " + i);
						System.out.println("tempSign : " + tempSign.get("signUserId"));
						tempSign.put("signSeq", cnt+1);
						lSignInfo2.add(tempSign);

						cnt = cnt + 1;
					}


					try{
						rMap = signProcess.signCityRequest(map, lSignInfo);

					}catch(Exception e){
						e.printStackTrace();
						// SGNS 등록 실패
						// SUCCESS 가 아닌 다른 경우 상신 실패 상태로 업데이트 함

						Map<String, Object> fMap = new HashMap<String, Object>();
						String docNo = (String)map.get("docNo");
						fMap.put("docNo", docNo);
						fMap.put("docSts", "D99");

//						cService.saveCmasDocUpdateFail(fMap);

			  			// 상신 반려 처리한다.

						// Param Map
						Map<String, Object> paramMap = new HashMap<String, Object>();

						Map<String, Object> cResultMap = new HashMap<String, Object>();

//
//						resultMap.put("acctSts", "");
//						resultMap.put("mode", "N");
//						resultMap.put("refNo", (String)paramMap.get("refNo"));
//						resultMap.put("docNo", (String)paramMap.get("docNo"));
//						String userId = (String)paramMap.get("drafterId");
//						resultMap.put("userId", userId.split(" ")[0]);

						paramMap.put("IRefkey", refKey);
						paramMap.put("INotesdoc", docNo);
						paramMap.put("IMode", "N");
						paramMap.put("ISenderId", (String)map.get("drafterId"));
						paramMap.put("IBukrs", "1000");

						try{

							cResultMap = cmasToEaiWSTest.confirmTrafficCost(paramMap);

						}catch(Exception ex){
							ex.printStackTrace();
						}

						// 문서 삭제
//						cService.deleteCityTranspByDocNo(map);
//						cService.deleteCityTranspDtlByDocNo(map);


						Map<String, Object> failMap = new HashMap<String, Object>();

						failMap.put("ErrMsg", "통합결재 시스템 등록에 실패하였습니다.");
						response.setMap("output1", failMap);
					}

					System.out.println("type : " + rMap.get("type"));
					String type = (String)rMap.get("type");

					if(type.equals("SUCCESS")){
						// SGNS 등록 성공
						String signId = (String)rMap.get("signId");
						String docNo = (String)map.get("docNo");
						String signInfo = (String)rMap.get("signInfo");

//				  		signInfo=1DFUR^1202429^1^T01##1DFUR^1202564^2^T02


						List signInfoList = new ArrayList();

						// string 이 비어 있지 않다면
						if (!signInfo.isEmpty()) {
							// legacy info 분해 작업을 시작한다.
							String strAry[] = signInfo.split("\\#\\#");
							// legacy info 는 key:value||key:value 의 형태로 전달되기때문에 || 를 기준으로 split
							// 한것을 : 으로 다시 split 하여 map 에 값을 넣는다.
							// value 가 key:value^value 로 연결된 1차월 배열 또는
							// key:value^value##value^value 이런 형태로연결된 2차원 배열의 경우는 각 업무의
							// 서비스단에서 처리 하도록 한다.
							for (int i = 0; i < strAry.length; i++) {
								String subAry[] = strAry[i].split("\\^");
								String tSignStsCd = "";
					  			if(subAry[2].equals("1")){
					  				tSignStsCd = "S02";
					  			}else{
					  				tSignStsCd = "S04";
					  			}
					  			String signDt = "N";
					  			if(subAry[3].equals("T01")){
					  				signDt = "Y";
					  			}

					  			Map<String, Object> signMap = new HashMap<String, Object>();

					  			signMap.put("apperOrgCd", subAry[0]);
					  			signMap.put("signUserId", subAry[1]);
					  			signMap.put("signSeq", subAry[2]);
					  			signMap.put("signTpCd", subAry[3]);
					  			signMap.put("signStsCd", tSignStsCd);
					  			signMap.put("signDt", signDt);

					  			signInfoList.add(signMap);
							}
						}



						String dutyCls = "03";

						cService.saveSignInfoCityTranspFromList(signInfoList, docNo, signId, dutyCls);

						Map<String, Object> sMap = new HashMap<String, Object>();

						sMap.put("type", "SUCCESS");

						response.setMap("output1", sMap);
					}else{
						// SGNS 등록 실패
						// SUCCESS 가 아닌 다른 경우 상신 실패 상태로 업데이트 함

						Map<String, Object> fMap = new HashMap<String, Object>();
						String docNo = (String)map.get("docNo");
						fMap.put("docNo", docNo);
						fMap.put("docSts", "D99");

//						cService.saveCmasDocUpdateFail(fMap);

			  			// 상신 반려 처리한다.

						// Param Map
						Map<String, Object> paramMap = new HashMap<String, Object>();

						Map<String, Object> cResultMap = new HashMap<String, Object>();

//
//						resultMap.put("acctSts", "");
//						resultMap.put("mode", "N");
//						resultMap.put("refNo", (String)paramMap.get("refNo"));
//						resultMap.put("docNo", (String)paramMap.get("docNo"));
//						String userId = (String)paramMap.get("drafterId");
//						resultMap.put("userId", userId.split(" ")[0]);

						paramMap.put("IRefkey", refKey);
						paramMap.put("INotesdoc", docNo);
						paramMap.put("IMode", "N");
						paramMap.put("ISenderId", (String)map.get("drafterId"));
						paramMap.put("IBukrs", "1000");

						try{

							cResultMap = cmasToEaiWSTest.confirmTrafficCost(paramMap);

						}catch(Exception e){
							e.printStackTrace();
						}

						// 문서 삭제
//						cService.deleteCityTranspByDocNo(map);
//						cService.deleteCityTranspDtlByDocNo(map);


						Map<String, Object> failMap = new HashMap<String, Object>();

						failMap.put("ErrMsg", "통합결재 시스템 등록에 실패하였습니다.");
						response.setMap("output1", failMap);
					}

				}


			}
		}else{
			System.out.println("SAP 전송 결과가 없음");
		}

//		String SapDoctabType = resultMap.get("SapDoctab").getClass().getName();
//		String SapErrtabType = resultMap.get("SapErrtab").getClass().getName();




		// 2015 08 06
		// SELECT_CITY_TRANSP_DRAFT 문서 업데이트
//		if(resultData.output1[0].SapDoctab != undefined){
//  			if(resultData.output1[0].SapErrtab != undefined){
//  				if(resultData.output1[0].SapErrtab.constructor == Object){
//  					gf_AlertMsg(resultData.output1[0].SapErrtab.Message);
//  				}
//
//  				if(resultData.output1[0].SapErrtab.constructor == Array){
//  					for(var i = 0; i < resultData.output1[0].SapErrtab.length; i++){
//  						gf_AlertMsg(resultData.output1[0].SapErrtab[i].Message);
//  					}
//  				}
//
//
//
//  				v_isSaveEnable = "N";
//  				$(".btn").show();
//  				$(".ajax_overlay").remove();
//
//  				return;
//  			}else{
//  				cf_SaveCityTranspDraft(resultData.output1[0].SapDoctab);
//  			}
//
//  		}else{
//  			gf_AlertMsg("SAP 상신 중 에러가 발생했습니다.");
//  			v_isSaveEnable = "N";
//  			$(".btn").show();
//  			$(".ajax_overlay").remove();
//  			return;
//  		}

		// SELECT_SGNS_REMOTE_DRAFT 통합결재 업데이트
//		v_SapDoc = obj;
//
//		// 문서번호
//		var docNo = v_DocNo;
//		// refNo SAP 통신 후 상신되어 온 값
//		v_RefNo = obj.Refkey;
//
//		// Result 가 Object 인지 검사
//		// 출장자가 2이상일 경우는 배열로 온다.
//		if(obj.constructor == Array) v_RefNo = obj[0].Refkey;
//		// signId SGNS 에서 차후 업데이트
//		var signId = "";
//		// signStsCd 결재상태
//		var signStsCd = "D02";
//		// 출장자 조직코드
//		var doOrgCd = gv_orgCd;
//		// 결비구분
//		var expCls = v_BdgtType;
//		// 예산내역
//		var bdgtItem = v_KText;
//		// 회계반려 여부
//		var acctSts = "";
//		// 회계 반려 사유
//		var retResn = "";
//
//		var ds_DraftDtl = new DataSet();
//
//		for(var i = 0; i < ds_DetailData.size();i++){
//
//			ds_DraftDtl.add(ds_DetailData.get(i));
//		}
//
//		var draftParams = {
//				docNo : docNo,
//				refNo : v_RefNo,
//				signId : signId,
//				docSts : signStsCd,
//				doOrgCd : doOrgCd,
//				expCls : expCls,
//				bdgtItem : bdgtItem,
//				acctSts : acctSts,
//				retResn : retResn
//		};
//
//		var draftDataSet = {
//				ds_DraftDtl : ds_DraftDtl.getAllData("U")
//		};
//
////		if(v_DocSts == "D16"){
////			gf_Transaction("SELECT_CITY_TRANSP_DRAFT", "/trip/cityTransp/updateCityTranspDraftDoc.xpl", draftParams, draftDataSet, "f_Callback", true);
////		}else{
////			gf_Transaction("SELECT_CITY_TRANSP_DRAFT", "/trip/cityTransp/saveCityTranspDraft.xpl", draftParams, draftDataSet, "f_Callback", true);
////		}
//
//		gf_Transaction("SELECT_CITY_TRANSP_DRAFT", "/trip/cityTransp/updateCityTranspDraftDoc.xpl", draftParams, draftDataSet, "f_Callback", true);
//

		// SAVE_CMAS_DOC_UPDATE_SIGN_ID 결재선 업데이트

//		var tSignUserCd = v_tSignUserCd; // 최종결재 ORG
//			var tSignUserId = v_tSignUserId; // 최종결재 ID
//
//
//			// 현장소속 여부
//			var hSignType = v_hSignType; // 현장소장 Y / N
//			// 현장소속이 아닐경우 null
//		var hSignUserCd = ""; // 련장소장 ORG
//			var hSignUserId = ""; // 현장소장 ID
//			if(hSignType == 'Y'){
//				// 현장소속일 경우 결재선에 추가 입력할 아이디 적용
//				hSignUserCd = v_hSignUserCd;
//				hSignUserId = v_hSignUserId;
//			}
//
//			var dutySysCd = "SGNS"; // DUTYSYSCD
//			var programCode = "SGNS070001"; // 양식코드
//			var signDocTitle = "시내교통비"; // 양식 제목
//
//			var refNo = v_RefNo; // 외부 전표번호
//
//			var drafterId = gv_userId + " " + gv_userNm; // 작성자 ID
//			var drafterOrg = gv_orgCd + " " + gv_orgNm; // 작성자 ORG
//
//			var bdgtType = $("#bdgtType").text(); // 경비구분
//			var aufnrNo = v_Aufnr; // 예산번호
//			var execTeam = v_SapDoc.Kostl1 + " " + v_SapDoc.Kostxt; // 집행팀
//			var docNo = v_DocNo; // CMAS 문서번호
//			var ordDate = ""; // 기표일자
//			var ordNo = ""; // 전표번호
//
//			var dSignType = v_dSignType; // 타 집행팀 Y / N
//			// 타집행팀이 아닐 경우 null
//			var dSignUserCd = v_dSignUserCd; // 타 집행팀장 ORG
//			var dSignUserId = v_dSignUserId; // 타 집행티장 ID
//
//			// 타집행 현장소장
//			var dHSignUserCd = "";
//			var dHSignUserId = "";
//			if(dSignType == "Y" && v_BdgtType == "Q"){
//				//타집행예산이면서 현장예산일 경우 현장책임관리자가 추가 협의자로 들어간다.
//				dHSignUserCd = v_dHSignUserCd;
//	  			dHSignUserId =  v_dHSignUserId;
//			}
//
//
//			// SGNS REMOTE DRAFT
//			var sgnsParams = {
//					dSignType : dSignType,
//					dSignUserCd : dSignUserCd,
//					dSignUserId : dSignUserId,
//					dHSignUserCd : dHSignUserCd,
//		  			dHSignUserId : dHSignUserId,
//					hSignType : hSignType,
//					hSignUserCd : hSignUserCd,
//					hSignUserId : hSignUserId,
//					tSignUserCd : tSignUserCd,
//					tSignUserId : tSignUserId,
//					dutySysCd : dutySysCd,
//					programCode : programCode,
//					signDocTitle : signDocTitle,
//					refNo : refNo,
//					drafterId : drafterId,
//					drafterOrg : drafterOrg,
//					bdgtType : bdgtType,
//					aufnrNo : aufnrNo,
//					execTeam : execTeam,
//					docNo : docNo,
//					ordDate : ordDate,
//					ordNo : ordNo,
//					bdgtCd : v_BdgtType,
//					isOrgBoss : v_IsOrgBoss,
//					isOfficer : "N",
//					drUser : v_DrafterId,
//					drUserCd : v_DrafterOrgCd
//		};
//
//			gf_Transaction("SELECT_SGNS_REMOTE_DRAFT", "/trip/cityTransp/saveSgnsRemoteDraft.xpl", sgnsParams, {}, "f_Callback", true);
//
//			// SAVE_CMAS_DOC_UPDATE_SIGN_ID 결재선 업데이트
//
//			var signId = resultData.output1[0].signId;
//	  		var signInfo = resultData.output1[0].signInfo;
//
////	  		signInfo=1DFUR^1202429^1^T01##1DFUR^1202564^2^T02
//	  		var ds_SignInfo = new DataSet();
//	  		var signInfoList = signInfo.split("##");
//	  		for(var i = 0; i < signInfoList.length; i++){
//	  			var sign = signInfoList[i].split("^");
//	  			var signStsCd = "";
//	  			if(sign[2] == "1"){
//	  				signStsCd = "S02";
//	  			}else{
//	  				signStsCd = "S04";
//	  			}
//	  			var signDt = "N";
//	  			if(sign[3] == "T01"){
//	  				signDt = "Y";
//	  			}
//
//	  			var signParam = {
//	  					apperOrgCd : sign[0],
//	  					signUserId : sign[1],
//	  					signSeq : sign[2],
//	  					signTpCd : sign[3],
//	  					signStsCd : signStsCd,
//	  					signDt : signDt
//	  			};
//
//	  			ds_SignInfo.add(signParam);
//
//	  		}
//
//
//	  		var dataSetParam = {
//	  				ds_SignInfo : ds_SignInfo.getAllData("U")
//			};
//
//	  		if(resultData.output1[0].type == "SUCCESS"){
//
//	  			// 국출 01 해출 02 시내 03
//		  		var updateParams = {
//		  				signId : signId,
//		  				docNo : v_DocNo,
//		  				dutyCls : '03'
//		  		};
//
//	  			gf_Transaction("SAVE_CMAS_DOC_UPDATE_SIGN_ID", "/trip/cityTransp/saveCmasDocUpdateSignId.xpl", updateParams, dataSetParam, "f_Callback", true);
//	  		}else{
//
//	  			var updateParams = {
//		  				docSts : "D99",
//		  				docNo : v_DocNo
//		  		};
//
//	  			gf_Transaction("SAVE_CMAS_DOC_UPDATE_SIGN_ID", "/trip/cityTransp/saveCmasDocUpdateFail.xpl", updateParams, {}, "f_Callback", true);
//
//	  		}
	}

    /**
     * 국출, 해출 REFNO 로 시내교통비 조회
     * @param request
     * @param response
     */
    @RequestMapping("getTrafficExpenseSearch.*")
	public void getTrafficExpenseSearch(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try{

			resultMap = cmasToEaiWSTest.getTrafficExpenseSearch(map);

		}catch(Exception e){
			e.printStackTrace();
		}
//		response.setMapList("output1", interfaceLogList);  		//Map을 Client로 전송

		response.setMap("output1", resultMap);
	}

    /**
     * ZN_RFC_SEND_EXRATE
     * 환율 조회
     * @param request
     * @param response
     */
    @RequestMapping("getSendExrate.*")
	public void getSendExrate(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> resultMap = new HashMap<String, Object>();

		//System.out.println("환율 Ymd : " + (String)map.get("Ymd"));

		try{

			resultMap = cmasToEaiWSTest.getSendExrate(map);

		}catch(Exception e){
			e.printStackTrace();
		}
//		response.setMapList("output1", interfaceLogList);  		//Map을 Client로 전송

		response.setMap("output1", resultMap);
	}

    // 시내교통비 회계승인
    @RequestMapping("confirmTrafficCost.*")
	public void confirmTrafficCost(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try{

			resultMap = cmasToEaiWSTest.confirmTrafficCost(map);

		}catch(Exception e){
			e.printStackTrace();
		}
//		response.setMapList("output1", interfaceLogList);  		//Map을 Client로 전송

		response.setMap("output1", resultMap);
	}


    // 국내출장 회계승인
    @RequestMapping("confirmBizTrip.*")
	public void confirmBizTrip(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try{

			resultMap = cmasToEaiWSTest.confirmBizTrip(map);

		}catch(Exception e){
			e.printStackTrace();
		}
//		response.setMapList("output1", interfaceLogList);  		//Map을 Client로 전송

		response.setMap("output1", resultMap);
	}


    // 해외출장 회계승인
    @RequestMapping("confirmOsBizTrip.*")
	public void confirmOsBizTrip(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try{

			resultMap = cmasToEaiWSTest.confirmOsBizTrip(map);

		}catch(Exception e){
			e.printStackTrace();
		}
//		response.setMapList("output1", interfaceLogList);  		//Map을 Client로 전송

		response.setMap("output1", resultMap);
	}

    //AirFare 수정
    @RequestMapping("updateAirFareN.*")
	public void updateAirFareN(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try{

			resultMap = cmasToEaiWSTest.updateAirFareN(map);

		}catch(Exception e){
			e.printStackTrace();
		}
//		response.setMapList("output1", interfaceLogList);  		//Map을 Client로 전송

		response.setMap("output1", resultMap);
	}


    // 입력날짜별 조회
    @RequestMapping("getAirfareInvoiceDay.*")
	public void getAirfareInvoiceDay(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try{

			resultMap = cmasToEaiWSTest.getAirfareInvoiceDay(map);

		}catch(Exception e){
			e.printStackTrace();
		}
//		response.setMapList("output1", interfaceLogList);  		//Map을 Client로 전송

		response.setMap("output1", resultMap);
	}


    // 전표 리스트 조회
    @RequestMapping("getAirfareInvoiceList.*")
	public void getAirfareInvoiceList(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try{

			resultMap = cmasToEaiWSTest.getAirfareInvoiceList(map);

		}catch(Exception e){
			e.printStackTrace();
		}
//		response.setMapList("output1", interfaceLogList);  		//Map을 Client로 전송

		response.setMap("output1", resultMap);
	}

    // 항공료 입력
    @RequestMapping("getAirfareInvoice.*")
	public void getAirfareInvoice(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try{

			resultMap = cmasToEaiWSTest.getAirfareInvoice(map);

		}catch(Exception e){
			e.printStackTrace();
		}
//		response.setMapList("output1", interfaceLogList);  		//Map을 Client로 전송

		response.setMap("output1", resultMap);
	}

    // 항공료 입력
    @RequestMapping("checkOsBizTrip.*")
	public void checkOsBizTrip(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try{

			resultMap = cmasToEaiWSTest.checkOsBizTrip(map);

		}catch(Exception e){
			e.printStackTrace();
		}
//		response.setMapList("output1", interfaceLogList);  		//Map을 Client로 전송

		response.setMap("output1", resultMap);
	}

    // 해외출장 전표 생성
    @RequestMapping("submitOsBizTrip.*")
	public void submitOsBizTrip1(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		GridData<HashMap> ds_TripUserList = request.getGridData("ds_TripUserList", HashMap.class);
		GridData<HashMap> ds_VisaInfoList = request.getGridData("ds_VisaInfoList", HashMap.class);
		GridData<HashMap> ds_CityList = request.getGridData("ds_CityList", HashMap.class);
		GridData<HashMap> ds_ExpnList = request.getGridData("ds_ExpnList", HashMap.class);
		GridData<HashMap> ds_Sign = request.getGridData("ds_Sign", HashMap.class);


		/**
		 * 2015-12-21
		 * 전표 상신전에 예산체크를 실행한다.
		 * checkOsBizTrip
		 */


		Map<String, Object> checkRMap = new HashMap<String, Object>();

		boolean sapResult = true;

		try{

			checkRMap = cmasToEaiWSTest.checkOsBizTrip(map);

		}catch(Exception e){
			e.printStackTrace();
			sapResult = false;
		}

		System.out.println("CheckOsErrMsg : " + checkRMap.get("ErrMsg"));

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try{

			resultMap = cmasToEaiWSTest.submitOsBizTrip(map);

		}catch(Exception e){
			e.printStackTrace();
			sapResult = false;
		}
//		response.setMapList("output1", interfaceLogList);  		//Map을 Client로 전송


		System.out.println("ErrMsg : " + resultMap.get("ErrMsg"));

		if(checkRMap.get("ErrMsg") != null){
			response.setMap("output1", checkRMap);
    	}else if(resultMap.get("ErrMsg") != null){
			response.setMap("output1", resultMap);
		}else if(resultMap.get("ORefkeyNo") == null){
			// refNo 가 null 로 상신되는 경우 방생
			// 방지 처리
			Map<String, Object> failMap = new HashMap<String, Object>();

			failMap.put("ErrMsg", "상신중 에러가 발생 하였습니다.(EAI SAP호출)");
			response.setMap("output1", failMap);
		}else if(!sapResult){
			// SAP 통신 중 Exception 발생
			// 방지 처리
			Map<String, Object> failMap = new HashMap<String, Object>();

			failMap.put("ErrMsg", "SAP 서버와 통신 중 에러가 발생하였습니다.");
			response.setMap("output1", failMap);
		}else{

			/***
			 * 문서 업데이트
			 */

//			ORefkeyNo: ".", OPrctr: "3DFUR", OPrctrTxt: "IT운영팀", OAccountNo: "213211167098"

//			alert("전표 번호 : " + resultData.output1[0].ORefkeyNo);

//			(String)resultMap.get("ORefkeyNo");
//			(String)resultMap.get("OAccountNo");

			String signStsCd = "D02";

			// 임시저장 파라미터
			String ifParam = "{\"bdgtType\":\"" + (String)map.get("bdgtType")
					+ "\",\"aufnr\":\"" + (String)map.get("aufnr")
					+ "\",\"kText\":\"" + (String)map.get("kText")
					+ "\",\"kostV\":\"" + (String)map.get("kostV")
					+ "\",\"kostVNm\":\"" + (String)map.get("kostVNm")
					+ "\",\"chief\":\"" + (String)map.get("chief")
					+ "\",\"chiefNm\":\"" + (String)map.get("chiefNm")
					+ "\",\"cGugun\":\"" + (String)map.get("cGugun")
					+ "\",\"userOrgCd\":\"" + (String)map.get("userOrgCd")
					+ "\",\"accountNo\":\"" + (String)resultMap.get("OAccountNo")
					+ "\",\"startDate\":\"" + (String)map.get("startDate")
					+ "\",\"endDate\":\"" + (String)map.get("endDate")
					+ "\",\"tripDate\":\"" + (String)map.get("iTripDate")
					+ "\",\"drafterUserId\":\"" + (String)map.get("drafterUserId")
					+ "\",\"drafterUserKnm\":\"" + (String)map.get("drafterUserKnm")
					+ "\",\"drafterUserEnm\":\"" + (String)map.get("drafterUserEnm")
					+ "\",\"drafterUserOrgCd\":\"" + (String)map.get("drafterUserOrgCd")
					+ "\",\"drafterUserOrgNm\":\"" + (String)map.get("drafterUserOrgNm")
					+ "\",\"drafterUserPositCd\":\"" + (String)map.get("drafterUserPositCd")
					+ "\",\"drafterUserRpswrkCd\":\"" + (String)map.get("drafterUserRpswrkCd")
					+ "\",\"tripUserId\":\"" + (String)map.get("tripUserId")
					+ "\",\"tripUserKnm\":\"" + (String)map.get("tripUserKnm")
					+ "\",\"tripUserEnm\":\"" + (String)map.get("tripUserEnm")
					+ "\",\"tripUserOrgCd\":\"" + (String)map.get("tripUserOrgCd")
					+ "\",\"tripUserOrgNm\":\"" + (String)map.get("tripUserOrgNm")
					+ "\",\"tripUserPositCd\":\"" + (String)map.get("tripUserPositCd")
					+ "\",\"tripUserRpswrkCd\":\"" + (String)map.get("tripUserRpswrkCd")
					+ "\",\"ordDate\":\"" + (String)map.get("ordDate")
					+ "\",\"etcPayDate\":\"" + (String)map.get("etcPayDate")
					+ "\",\"assistComment\":\"" + (String)map.get("assistComment")
					+ "\",\"fileAtchId\":\"" + (String)map.get("fileAtchId")
					+ "\",\"tAirAmtDtl\":\"" + (String)map.get("tAirAmtDtl")
					+ "\",\"tTranspAmtDtl\":\"" + (String)map.get("tTranspAmtDtl")
					+ "\",\"visaFeeDtl\":\"" + (String)map.get("visaFeeDtl")
					+ "\",\"overChargeDtl\":\"" + (String)map.get("overChargeDtl")
					+ "\",\"benefitsDtl\":\"" + (String)map.get("benefitsDtl")
					+ "\",\"hostAmtDtl\":\"" + (String)map.get("hostAmtDtl")
					+ "\",\"vTcode\":\"" + (String)map.get("vTcode")
					+ "\",\"oDate\":\"" + (String)map.get("oDate")
					+ "\",\"innType\":\"" + (String)map.get("innType")
					+ "\"}";

			System.out.println("ifParam : " + ifParam);

			String docNo = (String)map.get("docNo");//교육출장여부
			String userId = (String)map.get("userId");//교육출장여부

			Map<String, Object> dMap = new HashMap<String, Object>();

			dMap.put("docNo", docNo);
			dMap.put("refNo", (String)resultMap.get("ORefkeyNo"));
			dMap.put("userKnm", (String)map.get("userKnm"));
			dMap.put("userEnm", (String)map.get("userEnm"));
			dMap.put("seatGrade", (String)map.get("seatGrade"));
			dMap.put("afare", (String)map.get("afare"));
			dMap.put("demItm", (String)map.get("demItm"));
			dMap.put("pportIssueYmd", (String)map.get("pportIssueYmd"));
			dMap.put("pportExprtnYmd", (String)map.get("pportExprtnYmd"));
			dMap.put("rem", (String)map.get("rem"));
			dMap.put("lclAfare", (String)map.get("lclAfare"));
			dMap.put("lclTrafficExpn", (String)map.get("lclTrafficExpn"));
			dMap.put("visaFee", (String)map.get("visaFee"));
			dMap.put("excsExpn", (String)map.get("excsExpn"));
			dMap.put("userId", (String)map.get("userId"));
			dMap.put("wef", (String)map.get("wef"));
			dMap.put("svcExpn", (String)map.get("svcExpn"));
			dMap.put("ifParam", ifParam);
			dMap.put("bustrGl", (String)map.get("bustrGl"));
			dMap.put("pecul", (String)map.get("pecul"));
			dMap.put("startDate", (String)map.get("startDate"));
			dMap.put("endDate", (String)map.get("endDate"));
			dMap.put("tripDate", (String)map.get("tripDate"));
			dMap.put("secComment", (String)map.get("secComment"));
			dMap.put("ghrComment", (String)map.get("ghrComment"));

			String docSts = (String)map.get("docSts");

			int result = 1;

			try{		//데이터 업데이트

				oService.updateOuterTripDraft(dMap);
				oService.deleteOuterTripUserList(dMap);
				oService.saveOuterTripUserList(ds_TripUserList, (String)dMap.get("docNo"));

				// 같은 테이블 사용 visaOwnYn 값 유무로 구별
				oService.deleteOuterTripVisaInfoList(dMap);
				oService.saveOuterTripVisaInfoList(ds_VisaInfoList, (String)dMap.get("docNo"));
				oService.saveOuterTripVisaInfoList(ds_CityList, (String)dMap.get("docNo"));

				oService.deleteOuterTripExpnList(dMap);
				oService.saveOuterTripExpnList(ds_ExpnList, (String)dMap.get("docNo"));

			}catch(Exception e){
				e.printStackTrace();

				// Param Map
				Map<String, Object> paramMap = new HashMap<String, Object>();

//				I_REFKEY	CHAR	 15 	신청번호
//				I_NOTESDOC	CHAR	 32 	CMAS 문서번호
//				I_CONFIRM	CHAR	1	결재모드 ( Y: 결재, N: 반려 )
//				I_SENDER_ID	CHAR	7	결재자사번
//				I_BUKRS	CHAR	4	회사코드 (무조건 1000)

				paramMap.put("IRefkey", (String)resultMap.get("ORefkeyNo"));
				paramMap.put("INotesdoc", (String)map.get("docNo"));
				paramMap.put("IConfirm", "N");
				paramMap.put("ISenderId", (String)map.get("userId"));
				paramMap.put("IBukrs", "1000");

				try{
					resultMap = cmasToEaiWSTest.confirmOsBizTrip(paramMap);

				}catch(Exception ex){
					ex.printStackTrace();
				}

				// 문서 삭제
//				oService.deleteOuterTripByDocNo(map);
				// Detail
//				oService.deleteOuterTripUserList(map);
//				oService.deleteOuterTripVisaInfoList(map);
//				oService.deleteOuterTripExpnList(map);

			}

			resultMap.put("result", Integer.toString(result));

			// SGNS REMOTE DRAFT
			//
			System.out.println("ErrMsg : " + resultMap.get("ErrMsg"));

			if(!ObjUtil.isNull(resultMap.get("ErrMsg"))){
				System.out.println("ErrMsg 가 존재");
			}else{
				System.out.println("ErrMsg 가 존재하지 않음");
				// SAP 상신 성공시 DB INSERT
	  			// SAP 상신 결과를 임시 전역변수에 저장

					/**
					 * SAP 에서 받아온 결과값을 추가로 저장 후 통합결재 상신
					 */
					String sRefNo = (String)resultMap.get("ORefkeyNo");

					map.put("refNo", sRefNo);
					System.out.println("sRefNo : " + sRefNo);

					GridData<HashMap> signInfoList = request.getGridData("ds_Signln", HashMap.class);

					HttpServletRequest hRequest = WebContext.getRequest();
					String serverName = hRequest.getServerName();

					map.put("serverName", serverName);

					List<HashMap> lSignInfo = signInfoList.getList();

					logger.debug("lSignInfo size : " + lSignInfo.size());

					String mSignType = (String)map.get("mSignType");
					logger.debug("mSignType : " + mSignType);

					// 새로운 리스트에 담는다.
					List<HashMap> lSignInfo2 = new ArrayList<HashMap>();

					int cnt = 0;

					for (int i = 0; i < lSignInfo.size(); i++) {

						HashMap<String, Object> tempSign = lSignInfo.get(i);
						System.out.println("index : " + i);
						System.out.println("tempSign : " + tempSign.get("signUserId"));
						tempSign.put("signSeq", cnt+1);
						lSignInfo2.add(tempSign);

						cnt = cnt + 1;
					} // end of for-loop

					Map<String, Object> rMap = new HashMap<String, Object>();

					try{
						rMap = signProcess.signOuterRequest(map, lSignInfo2);

					}catch(Exception e){
						e.printStackTrace();

						// SGNS 등록 실패
						// SUCCESS 가 아닌 다른 경우 상신 실패 상태로 업데이트 함

						Map<String, Object> fMap = new HashMap<String, Object>();

						fMap.put("docNo", docNo);
						fMap.put("docSts", "D99");

	//					iService.saveCmasDocUpdateFail(fMap);

			  			// 상신 반려 처리한다.

						// Param Map
						Map<String, Object> paramMap = new HashMap<String, Object>();

	//					I_REFKEY	CHAR	 15 	신청번호
	//					I_NOTESDOC	CHAR	 32 	CMAS 문서번호
	//					I_CONFIRM	CHAR	1	결재모드 ( Y: 결재, N: 반려 )
	//					I_SENDER_ID	CHAR	7	결재자사번
	//					I_BUKRS	CHAR	4	회사코드 (무조건 1000)


						paramMap.put("IRefkey", (String)resultMap.get("ORefkeyNo"));
						paramMap.put("INotesdoc", (String)map.get("docNo"));
						paramMap.put("IConfirm", "N");
						paramMap.put("ISenderId", (String)map.get("userId"));
						paramMap.put("IBukrs", "1000");

						try{

							resultMap = cmasToEaiWSTest.confirmOsBizTrip(paramMap);

						}catch(Exception ex){
							ex.printStackTrace();
						}

	//					// 문서 삭제
	//					oService.deleteOuterTripByDocNo(map);
	//					// Detail
	//					oService.deleteOuterTripUserList(map);
	//					oService.deleteOuterTripVisaInfoList(map);
	//					oService.deleteOuterTripExpnList(map);


						Map<String, Object> failMap = new HashMap<String, Object>();

						failMap.put("ErrMsg", "통합결재 시스템 등록에 실패하였습니다.");
						response.setMap("output1", failMap);
					}

	//				response.setMap("output1", rMap);


					System.out.println("type : " + rMap.get("type"));
					String type = (String)rMap.get("type");

					if(type.equals("SUCCESS")){
						// SGNS 등록 성공
						String signId = (String)rMap.get("signId");
						String signInfo = (String)rMap.get("signInfo");

	//			  		signInfo=1DFUR^1202429^1^T01##1DFUR^1202564^2^T02

						List oSignInfoList = new ArrayList();

						// string 이 비어 있지 않다면
						if (!signInfo.isEmpty()) {
							// legacy info 분해 작업을 시작한다.
							String strAry[] = signInfo.split("\\#\\#");
							// legacy info 는 key:value||key:value 의 형태로 전달되기때문에 || 를 기준으로 split
							// 한것을 : 으로 다시 split 하여 map 에 값을 넣는다.
							// value 가 key:value^value 로 연결된 1차월 배열 또는
							// key:value^value##value^value 이런 형태로연결된 2차원 배열의 경우는 각 업무의
							// 서비스단에서 처리 하도록 한다.
							for (int i = 0; i < strAry.length; i++) {
								String subAry[] = strAry[i].split("\\^");
								String tSignStsCd = "";
					  			if(subAry[2].equals("1")){
					  				tSignStsCd = "S02";
					  			}else{
					  				tSignStsCd = "S04";
					  			}
					  			String signDt = "N";
					  			if(subAry[3].equals("T01")){
					  				signDt = "Y";
					  			}

					  			Map<String, Object> signMap = new HashMap<String, Object>();

					  			signMap.put("apperOrgCd", subAry[0]);
					  			signMap.put("signUserId", subAry[1]);
					  			signMap.put("signSeq", subAry[2]);
					  			signMap.put("signTpCd", subAry[3]);
					  			signMap.put("signStsCd", tSignStsCd);
					  			signMap.put("signDt", signDt);

					  			oSignInfoList.add(signMap);
							}
						}

						String dutyCls = "02";

						Map<String, Object> oMap = new HashMap<String, Object>();
						oMap.put("docNo", docNo);
						oMap.put("dutyCls", dutyCls);
						oService.deleteSignInfoOuterTrip(oMap);
						oService.saveSignInfoOuterTripFromList(oSignInfoList, docNo, signId, dutyCls);

						// 2016 04 20 글로벌 HR 승인이후 모든 과정이 완료된 후에 eHR 일정 등록 시작
						try{

							int resultEhr;

	//						map.put("userId", userId.split(" ")[0]);
							map.put("userId", (String)map.get("tripUserId"));

							map.put("approvalYn", "Y"); //#approvalYn#, -- Y
							map.put("vacaId", "출장"); //#vacaId#, -- 출장
							map.put("vacaAppId", "해외출장"); //#vacaAppId#, -- 국내출장
							map.put("modLocId", "CMAS"); // #modLocId# -- CMAS 작성시스템

							//선조회를 해와서
							Map<String, Object> countMap = iService.selectEHRInfo(map);

							String count = String.valueOf(countMap.get("count"));
							System.out.println("EHR 조회 결과 : " + count);
							if(Integer.parseInt(count) > 0){
								//있다면 update
								resultEhr = iService.updateUpdateEHRInfo(map);
								System.out.println("EHR update 결과 : " + resultEhr);
							}else{
								//없다면 insert
								resultEhr = iService.updateEHRInfo(map);
								System.out.println("EHR insert 결과 : " + resultEhr);
							}

						}catch(Exception e){
							e.printStackTrace();
						}

						Map<String, Object> sMap = new HashMap<String, Object>();

						sMap.put("type", "SUCCESS");

						response.setMap("output1", sMap);

					}else{
						// SGNS 등록 실패
						// SUCCESS 가 아닌 다른 경우 상신 실패 상태로 업데이트 함

						Map<String, Object> fMap = new HashMap<String, Object>();

						fMap.put("docNo", docNo);
						fMap.put("docSts", "D99");

	//					iService.saveCmasDocUpdateFail(fMap);

			  			// 상신 반려 처리한다.

						// Param Map
						Map<String, Object> paramMap = new HashMap<String, Object>();

	//					I_REFKEY	CHAR	 15 	신청번호
	//					I_NOTESDOC	CHAR	 32 	CMAS 문서번호
	//					I_CONFIRM	CHAR	1	결재모드 ( Y: 결재, N: 반려 )
	//					I_SENDER_ID	CHAR	7	결재자사번
	//					I_BUKRS	CHAR	4	회사코드 (무조건 1000)


						paramMap.put("IRefkey", (String)resultMap.get("ORefkeyNo"));
						paramMap.put("INotesdoc", (String)map.get("docNo"));
						paramMap.put("IConfirm", "N");
						paramMap.put("ISenderId", (String)map.get("userId"));
						paramMap.put("IBukrs", "1000");

						try{

							resultMap = cmasToEaiWSTest.confirmOsBizTrip(paramMap);

						}catch(Exception ex){
							ex.printStackTrace();
						}

	//					// 문서 삭제
	//					oService.deleteOuterTripByDocNo(map);
	//					// Detail
	//					oService.deleteOuterTripUserList(map);
	//					oService.deleteOuterTripVisaInfoList(map);
	//					oService.deleteOuterTripExpnList(map);


						Map<String, Object> failMap = new HashMap<String, Object>();

						failMap.put("ErrMsg", "통합결재 시스템 등록에 실패하였습니다.");
						response.setMap("output1", failMap);
					}
			}
		}

		response.setMap("output1", resultMap);
	}

    // 해외출장 전표 생성
    @RequestMapping("submitOsBizTripTest.*")
	public void submitOsBizTrip(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		GridData<HashMap> ds_TripUserList = request.getGridData("ds_TripUserList", HashMap.class);
		GridData<HashMap> ds_VisaInfoList = request.getGridData("ds_VisaInfoList", HashMap.class);
		GridData<HashMap> ds_CityList = request.getGridData("ds_CityList", HashMap.class);
		GridData<HashMap> ds_ExpnList = request.getGridData("ds_ExpnList", HashMap.class);
		GridData<HashMap> ds_Sign = request.getGridData("ds_Sign", HashMap.class);


		/**
		 * 2015-12-21
		 * 전표 상신전에 예산체크를 실행한다.
		 * checkOsBizTrip
		 */


		System.out.println("1. 경비구분 : " + (String)map.get("IBdgtType"));
		System.out.println("2. 예산번호 : " + (String)map.get("IBdgtNo"));
		System.out.println("3. 집 행 팀 : " + (String)map.get("IBdgtTeam"));
		System.out.println("4. 증 빙 일 : " + (String)map.get("ISendDateC"));
		System.out.println("5. 항 공 료 : " + (String)map.get("AirAmt"));
		System.out.println("6. 체 제 비 : " + (String)map.get("StayAmt"));
		System.out.println("7. 현지항공료 : " + (String)map.get("EtcLairAmt"));
		System.out.println("8. 현지교통비 : " + (String)map.get("EtcLtrAmt"));
		System.out.println("9. VISA FEE : " + (String)map.get("EtcVisaAmt"));
		System.out.println("10. Over Charge :" + (String)map.get("EtcOverAmt"));
		System.out.println("11. 복리후생 : " + (String)map.get("EtcWelAmt"));
		System.out.println("12. 접 대 비 : " + (String)map.get("EtcRcAmt"));
		System.out.println("13. REFKEY : " + (String)map.get("IRefkey"));

		Map<String, Object> checkRMap = new HashMap<String, Object>();

		try{

			checkRMap = cmasToEaiWSTest.checkOsBizTrip(map);

		}catch(Exception e){
			e.printStackTrace();
		}


		System.out.println("ErrMsg : " + checkRMap.get("ErrMsg"));

		response.setMap("output1", checkRMap);
//
//		if(checkRMap.get("ErrMsg") != null){
//			response.setMap("output1", checkRMap);
//		}else{
//			// refNo 가 null 로 상신되는 경우 방생
//			// 방지 처리
//			Map<String, Object> failMap = new HashMap<String, Object>();
//
//			failMap.put("ErrMsg", "상신중 에러가 발생 하였습니다.(EAI SAP호출)");
//			response.setMap("output1", failMap);
//
//		}
	}

     // 해외출장 정산서 상신
    @RequestMapping("settleOsBizTrip.*")
	public void settleOsBizTrip(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> resultMap = new HashMap<String, Object>();

		boolean sapResult = true;

		/**
		 * 2015-12-21
		 * 전표 상신전에 예산체크를 실행한다.
		 * checkOsBizTrip
		 */


		Map<String, Object> checkRMap = new HashMap<String, Object>();

		try{

			checkRMap = cmasToEaiWSTest.checkOsBizTrip(map);

		}catch(Exception e){
			e.printStackTrace();
			sapResult = false;
		}

		System.out.println("CheckOsErrMsg : " + checkRMap.get("ErrMsg"));


		try{

			resultMap = cmasToEaiWSTest.settleOsBizTrip(map);

		}catch(Exception e){
			e.printStackTrace();
			sapResult = false;
		}
//		response.setMapList("output1", interfaceLogList);  		//Map을 Client로 전송

		System.out.println("ErrMsg : " + resultMap.get("ErrMsg"));

		if(checkRMap.get("ErrMsg") != null){ // 예산 부족의 경우 sap 메세지 출력
			response.setMap("output1", checkRMap);
		}else if(resultMap.get("ErrMsg") != null){ // 전표생성 실패의 경우 sap 메세지 출력
			response.setMap("output1", resultMap);
		}else if(resultMap.get("ORefkeyNo") == null){
			// refNo 가 null 로 상신되는 경우 방생
			// 방지 처리
			Map<String, Object> failMap = new HashMap<String, Object>();

			failMap.put("ErrMsg", "상신중 에러가 발생 하였습니다.(EAI SAP호출)");
			response.setMap("output1", failMap);
		}else if(!sapResult){
			// SAP 통신 중 Exception 발생
			// 방지 처리
			Map<String, Object> failMap = new HashMap<String, Object>();

			failMap.put("ErrMsg", "SAP 서버와 통신 중 에러가 발생하였습니다.");
			response.setMap("output1", failMap);
		}else{

			/***
			 * 문서 업데이트
			 */

//			ORefkeyNo: ".", OPrctr: "3DFUR", OPrctrTxt: "IT운영팀", OAccountNo: "213211167098"

//			alert("전표 번호 : " + resultData.output1[0].ORefkeyNo);

//			(String)resultMap.get("ORefkeyNo");
//			(String)resultMap.get("OAccountNo");

			String signStsCd = "D02";



			// 임시저장 파라미터
			String ifParam = "{\"jsGubun\":\"" + (String)map.get("jsGubun")
					+ "\",\"tripNat\":\"" + (String)map.get("tripNat")
					+ "\",\"rTripDateStart\":\"" + (String)map.get("rTripDateStart")
					+ "\",\"rTripDateEnd\":\"" + (String)map.get("rTripDateEnd")
					+ "\",\"payDate\":\"" + (String)map.get("payDate")
					+ "\",\"tReq\":\"" + (String)map.get("itReq")
					+ "\",\"tPurp\":\"" + (String)map.get("tPurp")
					+ "\",\"tPlanDtl\":\"" + (String)map.get("itPlanDtl")
					+ "\",\"tRptDtl\":\"" + (String)map.get("itRptDtl")
					+ "\",\"tDataList\":\"" + (String)map.get("tDataList")
					+ "\",\"s1\":\"" + (String)map.get("s1")
					+ "\",\"s2\":\"" + (String)map.get("s2")
					+ "\",\"s3\":\"" + (String)map.get("s3")
					+ "\",\"Culkum\":\"" + (String)map.get("Culkum")
					+ "\",\"loAirFare2\":\"" + (String)map.get("loAirFare2")
					+ "\",\"loTranFare2\":\"" + (String)map.get("loTranFare2")
					+ "\",\"visaFeeFare2\":\"" + (String)map.get("visaFeeFare2")
					+ "\",\"ovCharFare2\":\"" + (String)map.get("ovCharFare2")
					+ "\",\"vocLeeFare2\":\"" + (String)map.get("vocLeeFare2")
					+ "\",\"hostFare2\":\"" + (String)map.get("hostFare2")
					+ "\",\"execDtl\":\"" + (String)map.get("iexecDtl")
					+ "\",\"usNightDay2\":\"" + (String)map.get("usNightDay2")
					+ "\",\"usDayDay2\":\"" + (String)map.get("usDayDay2")
					+ "\",\"eduDay2\":\"" + (String)map.get("eduDay2")
					+ "\",\"spotDay2\":\"" + (String)map.get("spotDay2")
					+ "\",\"euNightDay2\":\"" + (String)map.get("euNightDay2")
					+ "\",\"euDayDay2\":\"" + (String)map.get("euDayDay2")
					+ "\",\"enNightDay2\":\"" + (String)map.get("enNightDay2")
					+ "\",\"enDayDay2\":\"" + (String)map.get("enDayDay2")
					+ "\",\"jaNightDay2\":\"" + (String)map.get("jaNightDay2")
					+ "\",\"jaDayDay2\":\"" + (String)map.get("jaDayDay2")
					+ "\",\"rem\":\"" + (String)map.get("irem")
					+ "\",\"assDtl\":\"" + (String)map.get("assDtl")
					+ "\",\"fileAtchId\":\"" + (String)map.get("fileAtchId")
					+ "\",\"tripNat\":\"" + (String)map.get("tripNat")
					+ "\",\"oDate\":\"" + (String)map.get("oDate")
					+ "\",\"payRutUserId\":\"" + (String)map.get("payRutUserId")
					+ "\",\"payRutUserName\":\"" + (String)map.get("payRutUserName")
					+ "\",\"payRutDate\":\"" + (String)map.get("payRutDate")
					+ "\",\"usDayRef\":\"" + (String)map.get("usDayRef")
					+ "\",\"eduRef\":\"" + (String)map.get("eduRef")
					+ "\",\"spotRef\":\"" + (String)map.get("spotRef")
					+ "\",\"euDayRef\":\"" + (String)map.get("euDayRef")
					+ "\",\"enDayRef\":\"" + (String)map.get("enDayRef")
					+ "\",\"jaDayRef\":\"" + (String)map.get("jaDayRef")
					+ "\"}";

			System.out.println("ifParam : " + ifParam);

			String docNo = (String)map.get("docNo");//교육출장여부


			Map<String, Object> dMap = new HashMap<String, Object>();


			dMap.put("docNo", docNo);
			dMap.put("refNo", (String)resultMap.get("ORefkeyNo"));
			dMap.put("stlCls", (String)map.get("stlCls"));
			dMap.put("proofYmd", (String)map.get("proofYmd"));
			dMap.put("bustrNatCd", (String)map.get("bustrNatCd"));
			dMap.put("bustrStYmd", (String)map.get("bustrStYmd"));
			dMap.put("bustrEdYmd", (String)map.get("bustrEdYmd"));
			dMap.put("payScdYmd", (String)map.get("payScdYmd"));
			dMap.put("demItm", (String)map.get("demItm"));
			dMap.put("bustrGl", (String)map.get("bustrGl"));
			dMap.put("dtlSch", (String)map.get("dtlSch"));
			dMap.put("dutyRptCont", (String)map.get("dutyRptCont"));
			dMap.put("genAccomoDcnt", (String)map.get("genAccomoDcnt"));
			dMap.put("siteAccomoDcnt", (String)map.get("siteAccomoDcnt"));
			dMap.put("rtrnMeth", (String)map.get("rtrnMeth"));
			dMap.put("rmtAccntBnk", (String)map.get("rmtAccntBnk"));
			dMap.put("rmtAcctno", (String)map.get("rmtAcctno"));
			dMap.put("lclAfare", (String)map.get("lclAfare"));
			dMap.put("lclTrafficExpn", (String)map.get("lclTrafficExpn"));
			dMap.put("visaFee", (String)map.get("visaFee"));
			dMap.put("excsExpn", (String)map.get("excsExpn"));
			dMap.put("wef", (String)map.get("wef"));
			dMap.put("svcExpn", (String)map.get("svcExpn"));
			dMap.put("doDtlItem", (String)map.get("doDtlItem"));
			dMap.put("rem", (String)map.get("rem"));
			dMap.put("fileAtchId", (String)map.get("fileAtchId"));
			dMap.put("userId", (String)map.get("userId"));
			dMap.put("docSts", (String)map.get("docSts"));
			dMap.put("ghrComment", (String)map.get("ghrComment"));
			dMap.put("ifParam", ifParam);



			String docSts = (String)map.get("docSts");

			try{
	 			oService.updateAdjustDraftDoc(dMap);
	 		}catch(Exception e){
	 			e.printStackTrace();

	 			/**
	 			 * 업데이트 실패의 경우 반려 처리
	 			 */
	 			Map<String, Object> paramMap = new HashMap<String, Object>();

	 			dMap.put("refNo", (String)resultMap.get("ORefkeyNo"));
				paramMap.put("INotesdoc", (String)map.get("docNo"));
				paramMap.put("IConfirm", "N");
				paramMap.put("ISenderId", (String)map.get("userId"));
				paramMap.put("IBukrs", "1000");

				try{

					resultMap = cmasToEaiWSTest.confirmOsBizTrip(paramMap);
				}catch(Exception r){
					r.printStackTrace();
				}
	 		}

			// SGNS REMOTE DRAFT
			//
			System.out.println("ErrMsg : " + resultMap.get("ErrMsg"));

			if(!ObjUtil.isNull(resultMap.get("ErrMsg"))){
				System.out.println("ErrMsg 가 존재");
			}else{
				System.out.println("ErrMsg 가 존재하지 않음");
				// SAP 상신 성공시 DB INSERT
	  			// SAP 상신 결과를 임시 전역변수에 저장


				/**
				 * SAP 에서 받아온 결과값을 추가로 저장 후 통합결재 상신
				 */
				String sRefNo = (String)resultMap.get("ORefkeyNo");

				map.put("refNo", sRefNo);
				System.out.println("sRefNo : " + sRefNo);

				GridData<HashMap> signInfoList = request.getGridData("ds_Signln", HashMap.class);

				HttpServletRequest hRequest = WebContext.getRequest();
				String serverName = hRequest.getServerName();

				map.put("serverName", serverName);

				List<HashMap> lSignInfo = signInfoList.getList();

				System.out.println("lSignInfo size : " + lSignInfo.size());

				String mSignType = (String)map.get("mSignType");
				System.out.println("mSignType : " + mSignType);

				// 새로운 리스트에 담는다.
				List<HashMap> lSignInfo2 = new ArrayList<HashMap>();

				int cnt = 0;

				for (int i = 0; i < lSignInfo.size(); i++) {

					HashMap<String, Object> tempSign = lSignInfo.get(i);
					System.out.println("index : " + i);
					System.out.println("tempSign : " + tempSign.get("signUserId"));
					tempSign.put("signSeq", cnt+1);
					lSignInfo2.add(tempSign);

					cnt = cnt + 1;

					//정산에는 글로벌지원팀도 경영관리팀도 없음 (아마도 호출 안되는 루틴)

					if(i == 0){
						if(mSignType.equals("Y")){
							System.out.println("경영관리팀 삽입!!");
/*
							//2015-09-15 경영관리팀 검토를 협조로 변경 결재선에 삽입한다.
							// 경영관리팀 담당자 정보를 가져온다.
							Map<String, Object> ot004map = oService.retrieveCMASOT004(map);
							// 결재선에 삽입
							HashMap<String, Object> tempSign2 = new HashMap<String, Object>();
							tempSign2.put("signId", "");
							tempSign2.put("signSeq", cnt+1);
							tempSign2.put("signTpCd", "T03");
							tempSign2.put("signUserId", (String)ot004map.get("userId"));
							tempSign2.put("signUserNm", (String)ot004map.get("userKnm"));
							tempSign2.put("psignUserNm", "");
							tempSign2.put("apperPositCd", (String)ot004map.get("userPositCd"));
							tempSign2.put("apperPositNm", (String)ot004map.get("userPositCd"));
							tempSign2.put("perpsignPositNm", "");
							tempSign2.put("apperRpswrkCd", (String)ot004map.get("userPositCd"));
							tempSign2.put("apperRpswrkNm", (String)ot004map.get("userPositCd"));
							tempSign2.put("apperOrgCd", (String)ot004map.get("orgCd"));
							tempSign2.put("apperOrgNm", "");
							tempSign2.put("orgChrcCls", "D");

							lSignInfo2.add(tempSign2);

							cnt = cnt + 1;*/
						}
					}
				}

				Map<String, Object> rMap = new HashMap<String, Object>();

				try{
					rMap = signProcess.signAdjustOuterRequest(map, lSignInfo2);

				}catch(Exception e){
					e.printStackTrace();

					// SGNS 등록 실패
					// SUCCESS 가 아닌 다른 경우 상신 실패 상태로 업데이트 함

					Map<String, Object> fMap = new HashMap<String, Object>();

					fMap.put("docNo", docNo);
					fMap.put("docSts", "D99");

//					iService.saveCmasDocUpdateFail(fMap);

		  			// 상신 반려 처리한다.

					/**
		 			 * 업데이트 실패의 경우 반려 처리
		 			 */
		 			Map<String, Object> paramMap = new HashMap<String, Object>();

		 			dMap.put("refNo", (String)resultMap.get("ORefkeyNo"));
					paramMap.put("INotesdoc", (String)map.get("docNo"));
					paramMap.put("IConfirm", "N");
					paramMap.put("ISenderId", (String)map.get("userId"));
					paramMap.put("IBukrs", "1000");

					try{

						resultMap = cmasToEaiWSTest.confirmOsBizTrip(paramMap);
					}catch(Exception r){
						r.printStackTrace();
					}


					Map<String, Object> failMap = new HashMap<String, Object>();

					failMap.put("ErrMsg", "통합결재 시스템 등록에 실패하였습니다.");
					response.setMap("output1", failMap);
				}

//				response.setMap("output1", rMap);


				System.out.println("type : " + rMap.get("type"));
				String type = (String)rMap.get("type");

				if(type.equals("SUCCESS")){
					// SGNS 등록 성공
					String signId = (String)rMap.get("signId");
					String signInfo = (String)rMap.get("signInfo");

//			  		signInfo=1DFUR^1202429^1^T01##1DFUR^1202564^2^T02


					List oSignInfoList = new ArrayList();

					// string 이 비어 있지 않다면
					if (!signInfo.isEmpty()) {
						// legacy info 분해 작업을 시작한다.
						String strAry[] = signInfo.split("\\#\\#");
						// legacy info 는 key:value||key:value 의 형태로 전달되기때문에 || 를 기준으로 split
						// 한것을 : 으로 다시 split 하여 map 에 값을 넣는다.
						// value 가 key:value^value 로 연결된 1차월 배열 또는
						// key:value^value##value^value 이런 형태로연결된 2차원 배열의 경우는 각 업무의
						// 서비스단에서 처리 하도록 한다.
						for (int i = 0; i < strAry.length; i++) {
							String subAry[] = strAry[i].split("\\^");
							String tSignStsCd = "";
				  			if(subAry[2].equals("1")){
				  				tSignStsCd = "S02";
				  			}else{
				  				tSignStsCd = "S04";
				  			}
				  			String signDt = "N";
				  			if(subAry[3].equals("T01")){
				  				signDt = "Y";
				  			}

				  			Map<String, Object> signMap = new HashMap<String, Object>();

				  			signMap.put("apperOrgCd", subAry[0]);
				  			signMap.put("signUserId", subAry[1]);
				  			signMap.put("signSeq", subAry[2]);
				  			signMap.put("signTpCd", subAry[3]);
				  			signMap.put("signStsCd", tSignStsCd);
				  			signMap.put("signDt", signDt);

				  			oSignInfoList.add(signMap);
						}
					}



					String dutyCls = "04";

					Map<String, Object> oMap = new HashMap<String, Object>();
					oMap.put("docNo", docNo);
					oMap.put("dutyCls", dutyCls);
					oService.deleteSignInfoOuterTrip(oMap);
					oService.saveSignInfoOuterTripFromList2(oSignInfoList, docNo, signId, dutyCls);

					Map<String, Object> sMap = new HashMap<String, Object>();

					sMap.put("type", "SUCCESS");

					response.setMap("output1", sMap);

				}else{
					// SGNS 등록 실패
					// SUCCESS 가 아닌 다른 경우 상신 실패 상태로 업데이트 함

					Map<String, Object> fMap = new HashMap<String, Object>();

					fMap.put("docNo", docNo);
					fMap.put("docSts", "D99");

//					iService.saveCmasDocUpdateFail(fMap);

		  			// 상신 반려 처리한다.

					// Param Map
					Map<String, Object> paramMap = new HashMap<String, Object>();

//					I_REFKEY	CHAR	 15 	신청번호
//					I_NOTESDOC	CHAR	 32 	CMAS 문서번호
//					I_CONFIRM	CHAR	1	결재모드 ( Y: 결재, N: 반려 )
//					I_SENDER_ID	CHAR	7	결재자사번
//					I_BUKRS	CHAR	4	회사코드 (무조건 1000)


					paramMap.put("IRefkey", (String)resultMap.get("ORefkeyNo"));
					paramMap.put("INotesdoc", (String)map.get("docNo"));
					paramMap.put("IConfirm", "N");
					paramMap.put("ISenderId", (String)map.get("userId"));
					paramMap.put("IBukrs", "1000");

					try{

						resultMap = cmasToEaiWSTest.confirmOsBizTrip(paramMap);

					}catch(Exception ex){
						ex.printStackTrace();
					}

//					// 문서 삭제
//					oService.deleteOuterTripByDocNo(map);
//					// Detail
//					oService.deleteOuterTripUserList(map);
//					oService.deleteOuterTripVisaInfoList(map);
//					oService.deleteOuterTripExpnList(map);


					Map<String, Object> failMap = new HashMap<String, Object>();

					failMap.put("ErrMsg", "통합결재 시스템 등록에 실패하였습니다.");
					response.setMap("output1", failMap);
				}

	  		}

		}


		String sType = (String)map.get("sType");

		resultMap.put("sType", sType);

		response.setMap("output1", resultMap);
	}

}

