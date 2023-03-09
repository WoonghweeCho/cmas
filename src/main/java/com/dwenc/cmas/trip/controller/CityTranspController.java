package com.dwenc.cmas.trip.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jcf.data.GridData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.utils.RequestUtil;
import com.dwenc.cmas.info.domain.SiteDomain;
import com.dwenc.cmas.trip.domain.CityTransp;
import com.dwenc.cmas.trip.domain.CityTranspDtl;
import com.dwenc.cmas.trip.domain.Emp;
import com.dwenc.cmas.trip.domain.InnerTrip;
import com.dwenc.cmas.trip.domain.InnerTripDetail;
import com.dwenc.cmas.trip.domain.Sign;
import com.dwenc.cmas.trip.service.CityTranspService;
import com.dwenc.cmas.trip.service.InnerTripService;
import com.dwenc.cmas.trip.util.CmasFlowUtil;
import com.dwenc.cmas.trip.util.CmasToEaiWSTest;
import com.dwenc.cmas.trip.util.SignProcess;

import docfbaro.common.ObjUtil;
import docfbaro.common.WebContext;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

@Controller
@RequestMapping("/trip/cityTransp/*") /* 개발하는 업무에 대한 HTTP Request URI */
public class CityTranspController {

	/**
	 * Logger 객체 생성
	 */
	private static Logger logger = LoggerFactory.getLogger(CityTranspController.class);

	/**
	 * 해당 Controller와 연결된 Service Class
	 */
    @Autowired
    private CityTranspService sService;

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


    /**
     * 조회
     * @param request
     * @param response
     */
    @RequestMapping("retrieveCityTranspList.*")
	public void retrieveCityTranspList(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<CityTransp> ds_CityTranspList = sService.retrieveCityTranspList(map);
		response.setList("ds_CityTranspList", ds_CityTranspList);  		//Map을 Client로 전송
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

		sService.insertCityTranspTempDoc(ResultMap);

		response.setMap("ds_Result", ResultMap);  		//Map을 Client로 전송
	}

    /**
     * 조회
     * @param request
     * @param response
     */
    @RequestMapping("saveCityTranspDraft.*")
	public void saveCityTranspDraft(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> detailDataList = request.getGridData("ds_DraftDtl", HashMap.class);

		System.out.println("detailDataList size : " + detailDataList.size());

		sService.saveCityTranspDraft(map);
		sService.saveCityTranspDraftDtl(detailDataList, (String)map.get("docNo"));
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
		List<CityTranspDtl> ds_DetailData = sService.retrieveViewDocInfoDtl(map);

//		Map<String, Object> resultMap = new HashMap<String, Object>();
//
//		map.put("Gubun", "D"); // 경비구분 (D국출, O해출)
//		map.put("Measure", (String)ds_DocData.get("refNo"));
//
//		System.out.println("Gubun : " + (String)map.get("Gubun"));
//		System.out.println("Measure : " + (String)map.get("Measure"));
//
//		try{
//
//			resultMap = cmasToEaiWSTest.getTripExpenseSearch(map);
//
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//
//		response.setMap("ds_sapData", resultMap);

		response.setMap("ds_DocData", ds_DocData);  		//Map을 Client로 전송
		response.setList("ds_DetailData", ds_DetailData);  		//Map을 Client로 전송
	}


    @RequestMapping("saveCityTranspDocSave.*")
 	public void saveCityTranspDocSave(MciRequest request, MciResponse response) {
 		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
 		GridData<HashMap> detailDataList = request.getGridData("ds_DraftDtl", HashMap.class);

 		sService.saveCityTranspDocSave(map);
 		sService.saveCityTranspDraftDtl(detailDataList, (String)map.get("docNo"));


 	}

    @RequestMapping("updateCityTranspDocSave.*")
 	public void updateCityTranspDocSave(MciRequest request, MciResponse response) {
 		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

 		// 이미 결재 진행중인 문서인지 확인한다.

 		Map<String, Object> ds_DocData = sService.retrieveSavedDocInfo(map);
 		String docSts = (String)ds_DocData.get("docSts");

 		if(docSts == null) docSts = "";

 		if(docSts.equals("D02")){
 			response.setMap("ds_DocData", ds_DocData);  		//Map을 Client로 전송
 		}else if(docSts.equals("D03")){
 			response.setMap("ds_DocData", ds_DocData);  		//Map을 Client로 전송
 		}else{
 			GridData<HashMap> detailDataList = request.getGridData("ds_DraftDtl", HashMap.class);

 	 		sService.updateCityTranspDocSave(map);
 	 		sService.deleteCityTranspDtlByDocNo(map);
 	 		sService.saveCityTranspDraftDtl(detailDataList, (String)map.get("docNo"));
 		}
 	}

    @RequestMapping("retrieveSavedDocInfo.*")
	public void retrieveSavedDocInfo(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> ds_DocData = sService.retrieveSavedDocInfo(map);
		map.put("cmasId", (String)map.get("docNo"));
		List<CityTranspDtl> ds_DetailData = sService.retrieveViewDocInfoDtl(map);

		for(int i = 0; i < ds_DetailData.size(); i++){
			CityTranspDtl tempData = ds_DetailData.get(i);
			String trData = "tData" + (i+1);
			tempData.setTargetId(trData);
		}

		response.setMap("ds_DocData", ds_DocData);  		//Map을 Client로 전송
		response.setList("ds_DetailData", ds_DetailData);  		//Map을 Client로 전송

    }

    @RequestMapping("saveSgnsRemoteDraft.*")
	public void saveSgnsRemoteDraft(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

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
		}

		response.setMap("output1", rMap);
	}

    @RequestMapping("saveCmasDocUpdateSignId.*")
	public void saveCmasDocUpdateSignId(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> signInfoList = request.getGridData("ds_SignInfo", HashMap.class);

		String docNo = (String)map.get("docNo");
		String signId = (String)map.get("signId");
		String dutyCls = (String)map.get("dutyCls");

		sService.saveSignInfoCityTransp(signInfoList, docNo, signId, dutyCls);
	}

    @RequestMapping("CallBackServlet.*")
	public void CallBackServlet(MciRequest request, MciResponse response) {
    	Map<String, Object> data = request.get("json", HashMap.class);

//    	signDocTitle=시내교통비, signId=SGNS-2015-000215, dutyLnkCd=SGNS070001, instanceId=104, programCode=SGNS070001, clbkPath=http://172.21.18.232:8081/trip/cityTransp/CallBackServlet.xpl, process=http://172.21.18.232:8081/trip/cityTransp/CallBackServlet.xpl, signInfo=1DFUR^1202429^1^T01##1DFUR^1202564^2^T02, curSiteCd=1DFUR, signSeq=2, docStsCd=D03, userId=1202429, dutySysCd=SGNS, legacyInfo=refNo:IO20153DFUR0032||tripUserId:1202429 강혜성||tripUserOrg:1DFUR IT운영팀||drafterId:1202429 강혜성||drafterOrg:1DFUR IT운영팀||bdgtType:I. 일반경비||aufnrNo:000010012300||execTeam:3DFUR IT운영팀||tripTarget:장소테스트||tripPurp:(시내교통비)목적테스트||startDate:2015-09-03||endDate:2015-09-04||account:213211167098||compCar:N||tAmt1:1332||tAmt2:0||tAmt3:0||dayAmt:50000||nightAmt:40000||docNo:CMAS-2015-000023||ordDate:||ordNo:||agencyUser:||trRecvUser:||trAmount:||trAccount:||, signUserId=1202564, orgCd=1DFUR

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


		// CALLBACK 성공 실패 메세지 보내기위한 map
		Map<String, Object> rslmap = new HashMap<String, Object>();

		// 결재 callback이력은 transaction 을 분리 하기 위해 controller 에서 호출한다.
//		signHistService.insertSignIfHistory(signId, programCode, proxyType, docStsCd, legacyInfo, signUserId, docTitle);
		// 후처리 서비스 호출
//		rslmap = callbackService.processSignCallback(signId, programCode, proxyType, docStsCd, legacyInfo, signUserId, docTitle);

		rslmap = cmasFlowUtil.processSignCallback(signId, programCode, docStsCd, legacyInfo, signUserId, docTitle);

		String acctSts = (String)rslmap.get("acctSts");

		Map<String, Object> resultMap = new HashMap<String, Object>();

		boolean sapResultFlag = true;

		System.out.println("acctSts : " + acctSts + " / docStsCd : "+docStsCd);

		if(docStsCd.equals("D03") || docStsCd.equals("D04")){
			//회게 승인 처리
			System.out.println("docStsCd sap process: "+docStsCd);

			// Param Map
			Map<String, Object> paramMap = new HashMap<String, Object>();

//			신청번호
//			CMAS 문서번호
//			결재모드 ( Y: 결재, N: 반려 )
//			결재자사번
//			회사코드 (무조건 1000)


			paramMap.put("IRefkey", (String)rslmap.get("refNo"));
			paramMap.put("INotesdoc", (String)rslmap.get("docNo"));
			paramMap.put("IMode", (String)rslmap.get("mode"));
			paramMap.put("ISenderId", (String)rslmap.get("userId"));
			paramMap.put("IBukrs", "1000");

			System.out.println("confirm CityTransp : " + (String)rslmap.get("refNo"));
			System.out.println("confirm CityTransp : " + (String)rslmap.get("docNo"));
			System.out.println("confirm CityTransp : " + (String)rslmap.get("mode"));
			System.out.println("confirm CityTransp : " + (String)rslmap.get("userId"));

			try{

				resultMap = cmasToEaiWSTest.confirmTrafficCost(paramMap);

			}catch(Exception e){
				e.printStackTrace();
				// 실패했을 경우 처리해줘야함
				sapResultFlag = false;
			}



			String errMsg = (String)resultMap.get("ErrMsg");

			System.out.println("errMsg : " + errMsg);

			// 결재완료 이지만 결산마감 등 에러가 발생했을 경우에는 결재실패처리 한다.
			if(docStsCd.equals("D03") && !errMsg.equals("Y")){
				sapResultFlag = false;
			}else{
				rslmap.put("docStsCd", docStsCd);

				int resultCmas = sService.updateCityTranspInfo(rslmap);
				int resultSign = sService.updateSignDt(rslmap);
			}

			// 팀장 반려 처리
			// SAP 데이터가 삭제되기 때문에 문서상태를 열람할 수 없게 상태값을 변경한다.
			if(docStsCd.equals("D04")){

				System.out.println("Msg : " + (String)resultMap.get("ErrMsg"));

				Map<String, Object> rejParam = new HashMap<String, Object>();
				rejParam.put("docNo", (String)rslmap.get("docNo"));
				rejParam.put("docSts", docStsCd);
				rejParam.put("acctSts", resultMap.get(""));
				rejParam.put("retResn", (String)resultMap.get("ErrMsg"));
				sService.updateSgnsReject(rejParam);
			}

		}else{
			// 결재중일시에는 결재선 업데이트만
			int resultSign = sService.updateSignDt(rslmap);
		}
		response.setMap("ds_sapData", resultMap);
		response.setMap("RESULT", rslmap);

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

    @RequestMapping("updateCityTranspDraftDoc.*")
	public void updateCityTranspDraftDoc(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> detailDataList = request.getGridData("ds_DraftDtl", HashMap.class);

		sService.updateCityTranspDraftDoc(map);
		sService.deleteCityTranspDtlByDocNo(map);
		sService.saveCityTranspDraftDtl(detailDataList, (String)map.get("docNo"));
    }

    @RequestMapping("deleteCityTranspSavedDoc.*")
	public void deleteCityTranspSavedDoc(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		sService.deleteCityTranspByDocNo(map);
		sService.deleteCityTranspDtlByDocNo(map);
    }

    @RequestMapping("retrieveEmpListByOrgCd.*")
	public void retrieveEmpListByOrgCd(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Emp> ds_EmpList = sService.retrieveEmpListByOrgCd(map);

		response.setList("ds_EmpList", ds_EmpList);  		//Map을 Client로 전송

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

		sService.deleteCityTranspByDocNo(map);
		sService.deleteCityTranspDtlByDocNo(map);

		Map<String, Object> resultMap = new HashMap<String, Object>();

		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("IRefkey", map.get("refNo"));
		paramMap.put("INotesdoc", map.get("docNo"));
		paramMap.put("IMode", "N");
		paramMap.put("ISenderId", map.get("userId"));
		paramMap.put("IBukrs", "1000");

		try{

			resultMap = cmasToEaiWSTest.confirmTrafficCost(paramMap);

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
     * 회계 승인으로 상태값 변경 처리
     * @param request
     * @param response
     */
    @RequestMapping("updateSgnsReject2.*")
	public void updateSgnsReject2(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		sService.updateSgnsReject(map);
		sService.updateSignDt(map);

	}

    /**
     * 시내교통비/국내출장/해외출장 중복 신청 방지 체크
     * @param request
     * @param response
     */
    @RequestMapping("retrieveCheckDraftDuplication.*")
	public void retrieveCheckDraftDuplication(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> ResultMap = sService.retrieveCheckDraftDuplication(map);

		response.setMap("ds_Result1", (Map<String, Object>)ResultMap.get("result1"));
		response.setMap("ds_Result2", (Map<String, Object>)ResultMap.get("result2"));
		response.setMap("ds_Result3", (Map<String, Object>)ResultMap.get("result3"));
		response.setMap("ds_Result4", (Map<String, Object>)ResultMap.get("result4"));

	}

}

