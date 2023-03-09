package com.dwenc.cmas.trip.controller;

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
import com.dwenc.cmas.common.utils.RequestUtil;
import com.dwenc.cmas.common.utils.RexUtil;
import com.dwenc.cmas.info.domain.SiteDomain;
import com.dwenc.cmas.trip.domain.CityTranspDtl;
import com.dwenc.cmas.trip.domain.InnerTrip;
import com.dwenc.cmas.trip.domain.InnerTripDetail;
import com.dwenc.cmas.trip.domain.IsSpot;
import com.dwenc.cmas.trip.domain.Sign;
import com.dwenc.cmas.trip.service.InnerTripControllerHelper;
import com.dwenc.cmas.trip.service.InnerTripService;
import com.dwenc.cmas.trip.util.CmasFlowUtil;
import com.dwenc.cmas.trip.util.CmasToEaiWSTest;
import com.dwenc.cmas.trip.util.SignProcess;

import docfbaro.common.ObjUtil;
import docfbaro.common.WebContext;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

@Controller
@RequestMapping("/trip/innerTrip/*") /* 개발하는 업무에 대한 HTTP Request URI */
public class InnerTripController {

	/**
	 * Logger 객체 생성
	 */
	private static Logger logger = LoggerFactory.getLogger(InnerTripController.class);

	/**
	 * 해당 Controller와 연결된 Service Class
	 */
    @Autowired
    private InnerTripService sService;

    @Autowired
    private RexUtil rexUtil;

    @Autowired
    private InnerTripControllerHelper innerTripControllerHelper;

    /**
     * Common Message 처리
     */
    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @Autowired
    private CmasToEaiWSTest cmasToEaiWSTest;

    @Autowired
    private CmasFlowUtil cmasFlowUtil;

    @Autowired
    private SignProcess signProcess;

    @Autowired
    private SignUserService sUService;

    /**
     * 조회
     * @param request
     * @param response
     */
    @RequestMapping("retrieveInnerTripList.*")
	public void retrieveInnerTripList(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<InnerTrip> ds_InnerTripList = sService.retrieveInnerTripList(map);
		response.setList("ds_InnerTripList", ds_InnerTripList);  		//Map을 Client로 전송
	}

    @RequestMapping("retrieveInnerTripError.*")
	public void retrieveInnerTripError(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<InnerTrip> ds_InnerTripList = sService.retrieveInnerTripError(map);
		response.setList("ds_InnerTripList", ds_InnerTripList);  		//Map을 Client로 전송
	}



    /**
     * 조회
     * @param request
     * @param response
     */
    @RequestMapping("getCmasId.*")
	public void getCmasId(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> ResultMap = sService.getCmasId(map);

		sService.insertInnerTripTempDoc(ResultMap);

		response.setMap("ds_Result", ResultMap);  		//Map을 Client로 전송
	}

    /**
     * 조회
     * @param request
     * @param response
     */
    @RequestMapping("saveInnerTripDraft.*")
	public void saveInnerTripDraft(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> detailDataList = request.getGridData("ds_DetailData", HashMap.class);

		sService.saveInnerTripDraft(map);
		sService.saveInnerTripDraftDetail(detailDataList, (String)map.get("docNo"));
	}

    /**
     * 문서 조회
     * @param request
     * @param response
     */
    @RequestMapping("retrieveViewDocInfo.*")
	public void retrieveViewDocInfo(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> ds_DocData = sService.retrieveViewDocInfo(map);
		List<InnerTripDetail> ds_DetailData = sService.retrieveViewDocInfoDtl(map);

		Map<String, Object> resultMap = new HashMap<String, Object>();

		map.put("Gubun", "D"); // 경비구분 (D국출, O해출)
		map.put("Measure", (String)ds_DocData.get("refNo"));

		System.out.println("Gubun : " + (String)map.get("Gubun"));
		System.out.println("Measure : " + (String)map.get("Measure"));

		try{

			resultMap = cmasToEaiWSTest.getTripExpenseSearch(map);

		}catch(Exception e){
			e.printStackTrace();
		}

		response.setMap("ds_sapData", resultMap);

		response.setMap("ds_DocData", ds_DocData);  		//Map을 Client로 전송
		response.setList("ds_DetailData", ds_DetailData);  		//Map을 Client로 전송
	}

    /**
     * 조회
     * @param request
     * @param response
     */
    @RequestMapping("saveSgnsRemoteDraft.*")
	public void saveSgnsRemoteDraft(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		HttpServletRequest hRequest = WebContext.getRequest();
		String serverName = hRequest.getServerName();

		map.put("serverName", serverName);

		Map<String, Object> rMap = new HashMap<String, Object>();

		GridData<HashMap> signInfoList = request.getGridData("ds_Signln", HashMap.class);

		List<HashMap> lSignInfo = signInfoList.getList();

		try{
			rMap = signProcess.signRequest(map, lSignInfo);

		}catch(Exception e){
			e.printStackTrace();
		}

		response.setMap("output1", rMap);
	}

    @RequestMapping("retrieveIsSpotMgmt.*")
	public void retrieveIsSpotMgmt(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<IsSpot> ds_IsSpot = sService.retrieveIsSpotMgmt(map); // 현장관리챔임자
		List<IsSpot> ds_IsSpotBoss = sService.retrieveIsSpotMgmtBoss(map); // 현장소장
		List<IsSpot> ds_OrgBoss = sService.retrieveOrgTeamBoss(map); // 집행팀장 HR 조직
		List<IsSpot> ds_DrafterOrgBoss = sService.retrieveDrafterOrgTeamBoss(map); // 집행팀장 HR 조직
		List<IsSpot> ds_Officer = sService.retrieveIsOfficer(map); // 임원여부
		List<IsSpot> ds_IsOrgBoss = sService.retrieveIsOrgBoss(map); // 팀장소장여부



		response.setList("ds_IsSpot", ds_IsSpot);  		//Map을 Client로 전송
		response.setList("ds_IsSpotBoss", ds_IsSpotBoss);  		//Map을 Client로 전송
		response.setList("ds_OrgBoss", ds_OrgBoss);  		//Map을 Client로 전송
		response.setList("ds_DrafterOrgBoss", ds_DrafterOrgBoss);  		//Map을 Client로 전송
		response.setList("ds_Officer", ds_Officer);  		//Map을 Client로 전송
		response.setList("ds_IsOrgBoss", ds_IsOrgBoss);  		//Map을 Client로 전송
	}

    @RequestMapping("retrieveCoUserInfo.*")
	public void retrieveCoUserInfo(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> ds_coUser = sService.retrieveCoUserInfo(map);

		String bdgtType = (String)map.get("bdgtType");

		// 예산 타입이 현장경비일 경우 현장책임자도 가져온다.

		if(bdgtType.equals("Q")){
			System.out.println("=== 현장경비!! 책임자도 가져올것");

			Map<String, Object> hMap = new HashMap<String, Object>();

			if(!ObjUtil.isNull(ds_coUser)){
				hMap.put("orgCd", (String)ds_coUser.get("orgCd"));
				System.out.println("집행팀 HR 조직 코드 : " + (String)hMap.get("orgCd"));

				List<IsSpot> ds_IsSpot = sService.retrieveIsSpotMgmt(hMap); // 현장관리챔임자
				response.setList("ds_IsSpot", ds_IsSpot);  		//Map을 Client로 전송
			}


		}

		response.setMap("ds_coUser", ds_coUser);

	}

    @RequestMapping("retrievePABC1UserInfo.*")
	public void retrievePABC1UserInfo(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> ds_coUser = sService.retrievePABC1UserInfo(map);

		response.setMap("ds_coUser", ds_coUser);

	}

    @RequestMapping("getExecTeamInfo.*")
	public void getExecTeamInfo(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> ds_coUser = sService.retrieveCoUserInfo2(map);

		String bdgtType = (String)map.get("bdgtType");

		// 예산 타입이 현장경비일 경우 현장책임자도 가져온다.
		if(bdgtType.equals("Q")){
			System.out.println("=== 현장경비!! 책임자도 가져올것");

			Map<String, Object> hMap = new HashMap<String, Object>();

			hMap.put("orgCd", (String)map.get("orgCd"));
			System.out.println("집행팀 HR 조직 코드 : " + (String)map.get("orgCd"));

			List<IsSpot> ds_IsSpot = sService.retrieveIsSpotMgmt(hMap); // 현장관리챔임자
			response.setList("ds_IsSpot", ds_IsSpot);  		//Map을 Client로 전송


		}

		response.setMap("ds_coUser", ds_coUser);

	}


    @RequestMapping("CallBackServlet.*")
	public void CallBackServlet(MciRequest request, MciResponse response) {
    	Map<String, Object> data = request.get("json", HashMap.class);

//    	signDocTitle=국내출장신청서, signId=SGNS-2015-000215, dutyLnkCd=SGNS070002, instanceId=104, programCode=SGNS070002, clbkPath=http://172.21.18.232:8081/trip/innerTrip/CallBackServlet.xpl, process=http://172.21.18.232:8081/trip/innerTrip/CallBackServlet.xpl, signInfo=1DFUR^1202429^1^T01##1DFUR^1202564^2^T02, curSiteCd=1DFUR, signSeq=2, docStsCd=D03, userId=1202429, dutySysCd=SGNS, legacyInfo=refNo:IO20153DFUR0032||tripUserId:1202429 강혜성||tripUserOrg:1DFUR IT운영팀||drafterId:1202429 강혜성||drafterOrg:1DFUR IT운영팀||bdgtType:I. 일반경비||aufnrNo:000010012300||execTeam:3DFUR IT운영팀||tripTarget:장소테스트||tripPurp:(국출)목적테스트||startDate:2015-09-03||endDate:2015-09-04||account:213211167098||compCar:N||tAmt1:1332||tAmt2:0||tAmt3:0||dayAmt:         50000||nightAmt:         40000||docNo:CMAS-2015-000023||ordDate:||ordNo:||agencyUser:||trRecvUser:||trAmount:||trAccount:||, signUserId=1202564, orgCd=1DFUR

		String docTitle 	= data.get("signDocTitle").toString();
		String signId   	= data.get("signId").toString();
		String programCode 	= data.get("programCode").toString();
		String docStsCd     = data.get("docStsCd").toString();
		String signUserId   = data.get("userId").toString();
		String legacyInfo   = "";

		if ( !ObjUtil.isNull(data.get("legacyInfo")) ) {
			legacyInfo	= data.get("legacyInfo").toString();
		}



		System.out.println("docTitle : " + docTitle);
		System.out.println("signId : " + signId);
		System.out.println("programCode : " + programCode);
		System.out.println("docStsCd : " + docStsCd);
		System.out.println("signUserId : " + signUserId);
		System.out.println("legacyInfo : " + legacyInfo);







		/**
		 * 2015 07 03 Sap Interface 처리를 우선 하기로 함
		 * -------------------------------------------
		 */

		Map<String, Object> lParamMap = new HashMap<String, Object>();
		boolean callBackResult  	 = true;

		lParamMap.put("signId"		, signId);
		lParamMap.put("programCode"	, programCode);
		lParamMap.put("docStsCd"		, docStsCd);
		lParamMap.put("signUserId"	, signUserId);
		lParamMap.put("docTitle"		, docTitle);

		// string 이 비어 있지 않다면
		if (!legacyInfo.isEmpty()) {
			// legacy info 분해 작업을 시작한다.
			String strAry[] = legacyInfo.split("\\|\\|");
			// legacy info 는 key:value||key:value 의 형태로 전달되기때문에 || 를 기준으로 split
			// 한것을 : 으로 다시 split 하여 map 에 값을 넣는다.
			// value 가 key:value^value 로 연결된 1차월 배열 또는
			// key:value^value##value^value 이런 형태로연결된 2차원 배열의 경우는 각 업무의
			// 서비스단에서 처리 하도록 한다.
			for (int i = 0; i < strAry.length; i++) {
				String subAry[] = strAry[i].split(":");
				if (subAry.length == 1) {
					lParamMap.put(subAry[0], "");
				} else {
					lParamMap.put(subAry[0], subAry[1]);
				}

			}
		}


		/**
		 * 2015 11 17 회계상태와 먼저 비교하여 승인이나 반려 처리시 이미 선진행된 전표 이므로
		 * 이상 문서 운영팀으로 연락바란다는 Alert 전달
		 */

		Map<String, Object> rParamMap = new HashMap<String, Object>();
		rParamMap.put("cmasId", (String)lParamMap.get("docNo"));
		Map<String, Object> docMap = sService.retrieveViewDocInfo(rParamMap);

		System.out.println("ACCT_STS : " + docMap.get("acctSts"));
		if(!ObjUtil.isNull(docMap.get("acctSts"))){
			Map<String, Object> sapFailMap = new HashMap<String, Object>();
			// SAP 전송 실패의 경우
			sapFailMap.put("TYPE", "FAILURE");
			// 메세지 처리를 원한다면 메세지 코드를 삽입
			sapFailMap.put("MSG_CODE", "이미 전표처리가 완료된 문서 입니다.\n문의 : IT운영팀 나상문 과장");
			response.setMap("RESULT", sapFailMap);
			return;
		}




		// 회계승인
		if(docStsCd.equals("D03")){
			lParamMap.put("acctSts", "Y");
			lParamMap.put("confirm", "Y");
			String userId = (String)lParamMap.get("drafterId");
			lParamMap.put("userId", userId.split(" ")[0]);
		} else if(docStsCd.equals("D04")){
			lParamMap.put("acctSts", "");
			lParamMap.put("confirm", "N");
			String userId = (String)lParamMap.get("drafterId");
			lParamMap.put("userId", userId.split(" ")[0]);
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();

		boolean sapResultFlag = true;

		if(docStsCd.equals("D03") || docStsCd.equals("D04")){
			//회게 승인 처리
			System.out.println("docStsCd sap process: "+docStsCd);

			// Param Map
			Map<String, Object> paramMap = new HashMap<String, Object>();

//			I_REFKEY	CHAR	 15 	신청번호
//			I_NOTESDOC	CHAR	 32 	CMAS 문서번호
//			I_CONFIRM	CHAR	1	결재모드 ( Y: 결재, N: 반려 )
//			I_SENDER_ID	CHAR	7	결재자사번


			paramMap.put("IRefkey", (String)lParamMap.get("refNo"));
			paramMap.put("INotesdoc", (String)lParamMap.get("docNo"));
			paramMap.put("IConfirm", (String)lParamMap.get("confirm"));
			paramMap.put("ISenderId", (String)lParamMap.get("userId"));

			try{

				resultMap = cmasToEaiWSTest.confirmBizTrip(paramMap);

			}catch(Exception e){
				e.printStackTrace();

				// 실패했을 경우 처리해줘야함
				sapResultFlag = false;

			}

			String errMsg = (String)resultMap.get("OErrMsg");

			System.out.println("errMsg : " + errMsg);

			// 결재완료 이지만 결산마감 등 에러가 발생했을 경우에는 결재실패처리 한다.
			if(docStsCd.equals("D03") && errMsg != null){
				sapResultFlag = false;
				System.out.println("SAP 연동 실패");
			}else{

				int resultCmas = sService.updateInnerTripInfo(lParamMap);
				int resultSign = sService.updateSignDt(lParamMap);
				System.out.println("SAP 연동 성공");
			}

			// 팀장 반려 처리
			// SAP 데이터가 삭제되기 때문에 문서상태를 열람할 수 없게 상태값을 변경한다.
			if(docStsCd.equals("D04")){
				Map<String, Object> rejParam = new HashMap<String, Object>();
				System.out.println("Msg : " + (String)resultMap.get("OErrMsg"));
				rejParam.put("docNo", (String)lParamMap.get("docNo"));
				rejParam.put("docSts", docStsCd);
				rejParam.put("acctSts", "");
				rejParam.put("retResn", (String)resultMap.get("OErrMsg"));

				sService.updateSgnsReject(rejParam);
			}
		}else{
			// 결재중일시에는 결재선 업데이트만
			int resultSign = sService.updateSignDt(lParamMap);
		}

		// CALLBACK 성공 실패 메세지 보내기위한 map
		Map<String, Object> rslmap = new HashMap<String, Object>();

		/**
		 * 2015 07 03 Sap Interface 처리를 우선 하기로 함
		 * -------------------------------------------
		 */


		rslmap = cmasFlowUtil.processSignCallback(signId, programCode, docStsCd, legacyInfo, signUserId, docTitle);

		/**
		 * 결재 성공일 경우 PDF 변환 시작
		 */

		String acctSts = (String)rslmap.get("acctSts");

		System.out.println("acctSts : " + acctSts + " / docStsCd : "+docStsCd);

		if(docStsCd.equals("D03") || docStsCd.equals("D04")){
			//회게 승인 처리
//			System.out.println("docStsCd sap process: "+docStsCd);
//
//			// Param Map
//			Map<String, Object> paramMap = new HashMap<String, Object>();
//
////			I_REFKEY	CHAR	 15 	신청번호
////			I_NOTESDOC	CHAR	 32 	CMAS 문서번호
////			I_CONFIRM	CHAR	1	결재모드 ( Y: 결재, N: 반려 )
////			I_SENDER_ID	CHAR	7	결재자사번
//
//
//			paramMap.put("IRefkey", (String)rslmap.get("refNo"));
//			paramMap.put("INotesdoc", (String)rslmap.get("docNo"));
//			paramMap.put("IConfirm", (String)rslmap.get("confirm"));
//			paramMap.put("ISenderId", (String)rslmap.get("userId"));
//
//			try{
//
//				resultMap = cmasToEaiWSTest.confirmBizTrip(paramMap);
//
//			}catch(Exception e){
//				e.printStackTrace();
//			}
			// 팀장 반려 처리
			// SAP 데이터가 삭제되기 때문에 문서상태를 열람할 수 없게 상태값을 변경한다.
//			if(docStsCd.equals("D04")){
//				Map<String, Object> rejParam = new HashMap<String, Object>();
//				System.out.println("Msg : " + (String)resultMap.get("OErrMsg"));
//				rejParam.put("docNo", (String)rslmap.get("docNo"));
//				rejParam.put("docSts", docStsCd);
//				rejParam.put("acctSts", "");
//				rejParam.put("retResn", (String)resultMap.get("OErrMsg"));
//
//				sService.updateSgnsReject(rejParam);
//			}
			// 결재 완료시 전자증빙 첨부 pdf
			if(docStsCd.equals("D03")){

				Map<String, Object> legacyMap = new HashMap<String, Object>();

				// string 이 비어 있지 않다면
				if (!legacyInfo.isEmpty()) {
					// legacy info 분해 작업을 시작한다.
					String strAry[] = legacyInfo.split("\\|\\|");
					// legacy info 는 key:value||key:value 의 형태로 전달되기때문에 || 를 기준으로 split
					// 한것을 : 으로 다시 split 하여 map 에 값을 넣는다.
					// value 가 key:value^value 로 연결된 1차월 배열 또는
					// key:value^value##value^value 이런 형태로연결된 2차원 배열의 경우는 각 업무의
					// 서비스단에서 처리 하도록 한다.
					for (int i = 0; i < strAry.length; i++) {
						String subAry[] = strAry[i].split(":");
						if (subAry.length == 1) {
							legacyMap.put(subAry[0], "");
						} else {
							legacyMap.put(subAry[0], subAry[1]);
						}

					}
				}

				/* pdf 생성 */
				try {
					innerTripControllerHelper.createPdf(docTitle, signId, rslmap, legacyMap);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("Async Call");



//				createPdf(String docTitle, String signId, Map<String, Object> rslmap, Map<String, Object> legacyMap) {

				/* pdf 시작 */
				/*
				HashMap<String, Object> pdfmap = new HashMap<String, Object> ();

				Map<String, Object> signParam = new HashMap<String, Object>();
				signParam.put("docNo", (String)rslmap.get("docNo"));
				List<Sign> ds_Sign = sService.retrieveSignInfo(signParam);

				int asCnt = 0;
				int apCnt = 0;
				String signDt[];

				for(int i = 0; i < ds_Sign.size(); i++){
					Sign signData = ds_Sign.get(i);
//					System.out.println("ds_Sign " + i + " : " +signData.getSignUserId());
					signDt = signData.getSignDt().split(" ");
					if(signData.getSignSeq().equals("1")){

						pdfmap.put("ap_pos2_1", ObjUtil.isNull(signData.getApperRpswrkNm())?signData.getApperPositNm():signData.getApperRpswrkNm());
						pdfmap.put("ap_sign2_1", signData.getSignUserNm());
						pdfmap.put("ap_dt2_1", signDt[0]);
						pdfmap.put("drft_dt", signData.getSignDt());
						apCnt = apCnt+1;
					}
					if(signData.getSignTpCd().equals("T03")){
						asCnt = asCnt +1;
						pdfmap.put("as2_"+asCnt, signData.getApperPositNm() + " " + signData.getSignUserNm() + "(" + signDt[0] + ")");
						pdfmap.put("as_yn", "Y");
					} else if (signData.getSignTpCd().equals("T02")){
						apCnt = apCnt+1;
						pdfmap.put("ap_pos2_"+apCnt, ObjUtil.isNull(signData.getApperRpswrkNm())?signData.getApperPositNm():signData.getApperRpswrkNm());
						pdfmap.put("ap_sign2_"+apCnt, signData.getSignUserNm());
						pdfmap.put("ap_dt2_"+apCnt, signDt[0]);
					}
				}
				*/

				/*결재박스*/
				/*
				pdfmap.put("ap_rcnt", apCnt+"");

				String tripUser = (String)legacyMap.get("tripUserId").toString();
				String tripUserAry[] = tripUser.split(" ");
				//필수값
				pdfmap.put("rex_db", "oracle1");
				pdfmap.put("rex_rptname", "trip/INNERTRIP_R00");
				pdfmap.put("pdf_file_nm", tripUserAry[0] + "_HE15_I228_N_" + (String)rslmap.get("refNo") + ".pdf");

				//파라미터

				pdfmap.put("sign_id", signId);
				pdfmap.put("drft_user_nm", (String)legacyMap.get("drafterId").toString());
				pdfmap.put("sign_doc_title", docTitle);

				pdfmap.put("refNo", (String)rslmap.get("refNo"));
				pdfmap.put("docNo", (String)rslmap.get("docNo"));

				pdfmap.put("tripUserId", (String)legacyMap.get("tripUserId").toString());
				pdfmap.put("tripUserOrg", (String)legacyMap.get("tripUserOrg").toString());
				pdfmap.put("drafterId", (String)legacyMap.get("drafterId").toString());
				pdfmap.put("drafterOrg", (String)legacyMap.get("drafterOrg").toString());
				pdfmap.put("bdgtType", (String)legacyMap.get("bdgtType").toString());
				pdfmap.put("aufnrNo", (String)legacyMap.get("aufnrNo").toString());
				pdfmap.put("execTeam", (String)legacyMap.get("execTeam").toString());
				pdfmap.put("tripTarget", (String)legacyMap.get("tripTarget").toString());
				pdfmap.put("tripPurp", (String)legacyMap.get("tripPurp").toString());
				pdfmap.put("startDate", (String)legacyMap.get("startDate").toString());
				pdfmap.put("endDate", (String)legacyMap.get("endDate").toString());
				pdfmap.put("account", (String)legacyMap.get("account").toString());
				pdfmap.put("compCar", (String)legacyMap.get("compCar").toString());
				pdfmap.put("tAmt1", (String)legacyMap.get("tAmt1").toString());
				pdfmap.put("tAmt2", (String)legacyMap.get("tAmt2").toString());
				pdfmap.put("tAmt3", (String)legacyMap.get("tAmt3").toString());
				pdfmap.put("dayAmt", (String)legacyMap.get("dayAmt").toString());
				pdfmap.put("nightAmt", (String)legacyMap.get("nightAmt").toString());
				pdfmap.put("ordDate", (String)legacyMap.get("ordDate").toString());
				pdfmap.put("ordNo", (String)legacyMap.get("ordNo").toString());
				pdfmap.put("agencyUser", (String)legacyMap.get("agencyUser").toString());
				pdfmap.put("trRecvUser", (String)legacyMap.get("trRecvUser").toString());
				pdfmap.put("trAmount", (String)legacyMap.get("trAmount").toString());

				rexUtil.exportPDF(pdfmap);
				*/
				/* pdf 종료 */
			}

		}

		/**
		 * 2015 07 03
		 * PDF 변환 전 response 부터
		 */
		System.out.println("RESULT : " + (String)rslmap.get("TYPE"));
		response.setMap("ds_sapData", resultMap);

		if(sapResultFlag){
			response.setMap("RESULT", rslmap);
		}else{

			Map<String, Object> sapFailMap = new HashMap<String, Object>();
			// SAP 전송 실패의 경우
			sapFailMap.put("TYPE", "FAILURE");
			// 메세지 처리를 원한다면 메세지 코드를 삽입
			sapFailMap.put("MSG_CODE", "SAP 연동에 실패하였습니다.");
			response.setMap("RESULT", sapFailMap);

		}
	}


    /**
     * SGNS REMOTE DRAFT 후 signInfo 를 생성하여 넣는다.
     * @param request
     * @param response
     */
    @RequestMapping("saveCmasDocUpdateSignId.*")
	public void saveCmasDocUpdateSignId(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> signInfoList = request.getGridData("ds_SignInfo", HashMap.class);

		String docNo = (String)map.get("docNo");
		String signId = (String)map.get("signId");
		String dutyCls = (String)map.get("dutyCls");

		sService.saveSignInfoInnerTrip(signInfoList, docNo, signId, dutyCls);
	}

    @RequestMapping("saveInnerTripDocSave.*")
 	public void saveInnerTripDocSave(MciRequest request, MciResponse response) {
 		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
 		GridData<HashMap> detailDataList = request.getGridData("ds_DetailData", HashMap.class);

 		sService.saveInnerTripDocSave(map);
 		sService.saveInnerTripDraftDetail(detailDataList, (String)map.get("docNo"));


 	}

    @RequestMapping("updateInnerTripDocSave.*")
 	public void updateInnerTripDocSave(MciRequest request, MciResponse response) {
 		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

 		GridData<HashMap> detailDataList = request.getGridData("ds_DetailData", HashMap.class);

 		sService.updateInnerTripDocSave(map);
 		sService.deleteInnerTripDtlByDocNo(map);
 		sService.saveInnerTripDraftDetail(detailDataList, (String)map.get("docNo"));
 	}

    @RequestMapping("retrieveSavedDocInfo.*")
	public void retrieveSavedDocInfo(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> ds_DocData = sService.retrieveSavedDocInfo(map);
		map.put("cmasId", (String)map.get("docNo"));
		List<InnerTripDetail> ds_DetailData = sService.retrieveViewDocInfoDtl(map);

		response.setMap("ds_DocData", ds_DocData);  		//Map을 Client로 전송
		response.setList("ds_DetailData", ds_DetailData);  		//Map을 Client로 전송

    }

    @RequestMapping("updateInnerTripDraftDoc.*")
	public void updateInnerTripDraftDoc(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> detailDataList = request.getGridData("ds_DetailData", HashMap.class);

		sService.updateInnerTripDraftDoc(map);
		sService.deleteInnerTripDtlByDocNo(map);
		sService.saveInnerTripDraftDetail(detailDataList, (String)map.get("docNo"));
	}

    @RequestMapping("deleteInnerTripSavedDoc.*")
	public void deleteInnerTripSavedDoc(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		sService.deleteInnerTripByDocNo(map);
		sService.deleteInnerTripDtlByDocNo(map);
	}

    @RequestMapping("retrieveSignInfo.*")
	public void retrieveSignInfo(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Sign> ds_Sign = sService.retrieveSignInfo(map);

		response.setList("ds_Sign", ds_Sign);  		//Map을 Client로 전송

    }

    /**
     * 상신 실패시 doc을 상신실패 처리 한다.
     * @param request
     * @param response
     */
    @RequestMapping("saveCmasDocUpdateFail.*")
	public void saveCmasDocUpdateFail(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		sService.saveCmasDocUpdateFail(map);
	}

    @RequestMapping("deleteCmasDocDelete.*")
	public void deleteCmasDocDelete(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		sService.deleteInnerTripByDocNo(map);
		sService.deleteInnerTripDtlByDocNo(map);

		Map<String, Object> resultMap = new HashMap<String, Object>();

		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("IRefkey", (String)map.get("refNo"));
		paramMap.put("INotesdoc", (String)map.get("docNo"));
		paramMap.put("IConfirm", "N");
		paramMap.put("ISenderId", (String)map.get("userId"));

		try{

			resultMap = cmasToEaiWSTest.confirmBizTrip(paramMap);

		}catch(Exception e){
			e.printStackTrace();
		}

		response.setMap("ds_Result", resultMap);


	}

    @RequestMapping("retrieveTSignUserInfo.*")
	public void retrieveTSignUserInfo(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<IsSpot> ds_IsSpot = sService.retrieveIsSpotMgmt(map); // 현장관리챔임자

		response.setList("ds_IsSpot", ds_IsSpot);  		//Map을 Client로 전송

    }


    @RequestMapping("createPdfDoc.*")
	public void createPdfDoc(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> pMap = sService.retrievelegacyInfo(map);


		String clbkParam = (String)pMap.get("clbkParam");


		Map<String, Object> clbkMap = new HashMap<String, Object>();

		// string 이 비어 있지 않다면
		if (!clbkParam.isEmpty()) {
			// legacy info 분해 작업을 시작한다.
			String strAry[] = clbkParam.split("\\&");
			// legacy info 는 key:value||key:value 의 형태로 전달되기때문에 || 를 기준으로 split
			// 한것을 : 으로 다시 split 하여 map 에 값을 넣는다.
			// value 가 key:value^value 로 연결된 1차월 배열 또는
			// key:value^value##value^value 이런 형태로연결된 2차원 배열의 경우는 각 업무의
			// 서비스단에서 처리 하도록 한다.
			for (int i = 0; i < strAry.length; i++) {
				String subAry[] = strAry[i].split("\\=");
				if (subAry.length == 1) {
					clbkMap.put(subAry[0], "");
				} else {
					clbkMap.put(subAry[0], subAry[1]);
				}

			}
		}



		Set<String> set = clbkMap.keySet();
		 Iterator<String> iterator = set.iterator();
		 while (iterator.hasNext()) {
			 String key = iterator.next();
			 System.out.println("");
			 System.out.println("iterator==" + key);
			 System.out.println("iteratorVal==" + clbkMap.get(key));

//			 logger.debug("iterator==" + key);
//			 logger.debug("iteratorVal==" + resultMap.get(key));
		 }



		//
		Map<String, Object> rslmap = new HashMap<String, Object>();

		rslmap.put("refNo", (String)map.get("refNo"));
		rslmap.put("signId", (String)map.get("signId"));
		rslmap.put("docNo", (String)map.get("docNo"));

		String signId = (String)map.get("docNo");
		String docTitle = (String)clbkMap.get("signDocTitle");

		String legacyInfo = (String)clbkMap.get("legacyInfo");
		Map<String, Object> lParamMap = new HashMap<String, Object>();

		// string 이 비어 있지 않다면
		if (!legacyInfo.isEmpty()) {
			// legacy info 분해 작업을 시작한다.
			String strAry[] = legacyInfo.split("\\|\\|");
			// legacy info 는 key:value||key:value 의 형태로 전달되기때문에 || 를 기준으로 split
			// 한것을 : 으로 다시 split 하여 map 에 값을 넣는다.
			// value 가 key:value^value 로 연결된 1차월 배열 또는
			// key:value^value##value^value 이런 형태로연결된 2차원 배열의 경우는 각 업무의
			// 서비스단에서 처리 하도록 한다.
			for (int i = 0; i < strAry.length; i++) {
				String subAry[] = strAry[i].split(":");
				if (subAry.length == 1) {
					lParamMap.put(subAry[0], "");
				} else {
					lParamMap.put(subAry[0], subAry[1]);
				}

			}
		}



		/* pdf 생성 */
		try {
			innerTripControllerHelper.createPdf(docTitle, signId, rslmap, lParamMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


//		clbkParam: "instanceId=104&programCode=SGNS070002&orgCd=1DFUR&userId=1202429&legacyInfo=refNo:CA20153DFUR0083||tripUserId:1202429 강혜성||tripUserOrg:1DFUR IT운영팀||drafterId:1202429 강혜성||drafterOrg:1DFUR IT운영팀||bdgtType:C. 특정경비-팀||aufnrNo:3DFUR||execTeam:3DFUR IT운영팀||tripTarget:ㅂㅈㄷ||tripPurp:(국출)ㅂㅈㄷ||startDate:2015-08-26||endDate:2015-08-26||account:213211167098||compCar:N||tAmt1:1322||tAmt2:0||tAmt3:0||dayAmt: 25000||nightAmt: 0||docNo:CMAS-2015-000210||ordDate:20150613||ordNo:||agencyUser:이유재 (1204594)||trRecvUser:||trAmount:||trAccount:||&signId=SGNS-2015-000745&dutySysCd=SGNS&signInfo=1DFUR^1202429^1^T01##1DFUR^1202564^2^T02&signDocTitle=국내출장신청서&process=http://172.21.29.210:8081/trip/innerTrip/CallBackServlet.xpl&"
//			clbkPath: "http://172.21.29.210:8081/trip/innerTrip/CallBackServlet.xpl"
//			clbkTpCd: null
//			dutyLnkCd: "SGNS070002"
//			fnlEditDt: "2015-06-10 17:31:50"
//			fnlEditUserId: "1202429"
//			fstRegDt: "2015-06-10 17:31:50"
//			fstRegUserId: "1202429"
//			oggCd: "104"
//			oggTime: "2015-06-10 17:31:50"
//			signId: "SGNS-2015-000745"


//		response.setMap("ds_Result", pMap);
    }

    @RequestMapping("confirmSap.*")
   	public void confirmSap(MciRequest request, MciResponse response) {
   		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

   		Map<String, Object> paramMap = new HashMap<String, Object>();
   		Map<String, Object> resultMap = new HashMap<String, Object>();

		paramMap.put("IRefkey", (String)map.get("refNo"));
		paramMap.put("INotesdoc", (String)map.get("docNo"));
		paramMap.put("IConfirm", (String)map.get("confirm"));
		paramMap.put("ISenderId", (String)map.get("userId"));

		try{

			resultMap = cmasToEaiWSTest.confirmBizTrip(paramMap);

		}catch(Exception e){
			e.printStackTrace();

		}

		response.setMap("ds_Result", resultMap);


    }

    /**
     * 회계 승인으로 상태값 변경 처리
     * @param request
     * @param response
     */
    @RequestMapping("updateSgnsReject.*")
	public void updateSgnsReject(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		sService.updateSgnsReject(map);
	}

    /**
     * e-HR시스템에 국내출장 근태등록여부 체크
     * @param request
     * @param response
     */
    @RequestMapping("retrieveCheckEHRAppYn.*")
	public void retrieveCheckEHRAppYn(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		String userId = (String)map.get("tripUserId");
		map.put("userId", userId.split(" ")[0]);

		String startDate = (String)map.get("startDate");
		String endDate = (String)map.get("endDate");

		map.put("approvalYn", "Y"); //#approvalYn#, -- Y
		//map.put("vacaId", "출장"); //#vacaId#, -- 출장
		map.put("vacaAppId1", "국내출장"); //#vacaAppId#, -- 국내출장
		map.put("vacaAppId2", "사내교육"); //#vacaAppId#, -- 국내출장
		map.put("vacaAppId3", "사외교육"); //#vacaAppId#, -- 국내출장
		map.put("modLocId", "CMAS"); // #modLocId# -- CMAS 작성시스템

		Map<String, Object> ResultMap = sService.selectEHRInfo2(map);

		response.setMap("ds_Result1", (Map<String, Object>)ResultMap.get("result1"));

	}

    /**
     * 첨부파일 DB에 등록여부 체크
     * @param request
     * @param response
     */
    @RequestMapping("retrieveCheckCoFileYn.*")
	public void retrieveCheckCoFileYn(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		String fileAtchId = (String)map.get("fileAtchId");

		map.put("approvalYn", "Y"); //#approvalYn#, -- Y
		map.put("vacaId", "출장"); //#vacaId#, -- 출장
		map.put("vacaAppId", "국내출장"); //#vacaAppId#, -- 국내출장
		map.put("modLocId", "CMAS"); // #modLocId# -- CMAS 작성시스템

		Map<String, Object> ResultMap = sService.selectCoFileCount(map);

		response.setMap("ds_Result1", (Map<String, Object>)ResultMap.get("result1"));

	}

}

