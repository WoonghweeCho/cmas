package com.dwenc.cmas.trip.controller;

import java.util.ArrayList;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.mail.service.MailService;
import com.dwenc.cmas.common.utils.MailUtil;
import com.dwenc.cmas.common.utils.RequestUtil;
import com.dwenc.cmas.trip.domain.Expn;
import com.dwenc.cmas.trip.domain.ExtnlPer;
import com.dwenc.cmas.trip.domain.Nat;
import com.dwenc.cmas.trip.domain.OuterTrip;
import com.dwenc.cmas.trip.domain.Sign;
import com.dwenc.cmas.trip.domain.TripRef;
import com.dwenc.cmas.trip.domain.Visa;
import com.dwenc.cmas.trip.service.InnerTripService;
import com.dwenc.cmas.trip.service.OuterTripAdjustPDFHelper;
import com.dwenc.cmas.trip.service.OuterTripDraftPDFHelper;
import com.dwenc.cmas.trip.service.OuterTripService;
import com.dwenc.cmas.trip.util.CmasFlowUtil;
import com.dwenc.cmas.trip.util.CmasToEaiWSTest;
import com.dwenc.cmas.trip.util.SignProcess;

import docfbaro.common.ObjUtil;
import docfbaro.common.WebContext;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

@Controller
@RequestMapping("/trip/outerTrip/*") /* 개발하는 업무에 대한 HTTP Request URI */
public class OuterTripController {

	/**
	 * Logger 객체 생성
	 */
	private static Logger logger = LoggerFactory.getLogger(OuterTripController.class);

	/**
	 * 해당 Controller와 연결된 Service Class
	 */
    @Autowired
    private OuterTripService sService;

    @Autowired
    private InnerTripService iService;

    @Autowired
    private MailService mService;

    @Autowired
    private MailUtil mUtil;


    /**
     * Common Message 처리
     */

    @Autowired
    private CmasToEaiWSTest cmasToEaiWSTest;

    @Autowired
    private CmasFlowUtil cmasFlowUtil;

    @Autowired
    private SignProcess signProcess;

    /**
     * PDF Service
     */
    @Autowired
    private OuterTripDraftPDFHelper outerTripDraftPDFHelper;


    @Autowired
    private OuterTripAdjustPDFHelper outerTripAdjustPDFHelper;



    /**
     * 조회
     * @param request
     * @param response
     */
    @RequestMapping("retrieveNatList.*")
	public void retrieveNatList(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Nat> ds_NatList = sService.retrieveNatList(map);
		List<TripRef> ds_RefList = sService.retrieveTripRef(map);


		response.setList("ds_NatList", ds_NatList);  		//Map을 Client로 전송
		response.setList("ds_RefList", ds_RefList);  		//Map을 Client로 전송
	}

    @RequestMapping("retrieveTripRef.*")
	public void retrieveTripRef(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<TripRef> ds_RefList = sService.retrieveTripRef(map);
		List<Nat> ds_NatList = sService.retrieveNatList(map);

		response.setList("ds_NatList", ds_NatList);  		//Map을 Client로 전송
		response.setList("ds_RefList", ds_RefList);  		//Map을 Client로 전송
	}

    @RequestMapping("retrieveAirFareList.*")
	public void retrieveAirFareList(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<OuterTrip> ds_AirFareList = sService.retrieveAirFareList(map);

		response.setList("ds_AirFareList", ds_AirFareList);  		//Map을 Client로 전송
	}

    @RequestMapping("getCmasId.*")
	public void getCmasId(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> ResultMap = sService.getCmasId(map);

		sService.insertOuterTripTempDoc(ResultMap);

		response.setMap("ds_Result", ResultMap);  		//Map을 Client로 전송
	}

    @RequestMapping("getCmasId2.*")
	public void getCmasId2(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> ResultMap = sService.getCmasId(map);

		ResultMap.put("fstRegDt", (String)map.get("fstRegDt"));

		sService.insertOuterTripTempDoc(ResultMap);

		response.setMap("ds_Result", ResultMap);  		//Map을 Client로 전송
	}

    /**
     * 조회
     * @param request
     * @param response
     */
    @RequestMapping("retrieveOuterTripList.*")
	public void retrieveOuterTripList(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<OuterTrip> ds_OuterTripList = sService.retrieveOuterTripList(map);

		//CMAS 시스템 검토담당자 조회
		List<Map<String, Object>> ds_CmasList = sService.retrieveCMASOTList(map);

		response.setList("ds_OuterTripList", ds_OuterTripList);  		//Map을 Client로 전송
		response.setList("ds_CmasList", ds_CmasList);
	}
    /**
     * 협의결재자 조회 (CMAS_OT_004, CMAS_OT_006)
     * @param request
     * @param response
     */

    @RequestMapping("retrieveCoSignList.*")
	public void retrieveCoSignList(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		//CMAS 시스템 검토담당자 조회
		List<Map<String, Object>> ds_Cmas004 = sService.retrieveCMASOT004(map);
		List<Map<String, Object>> ds_Cmas006 = sService.retrieveCMASOT006(map);
		List<Map<String, Object>> ds_Cmas008 = sService.retrieveCMASOT008(map);
		response.setList("ds_Cmas004", ds_Cmas004);
		response.setList("ds_Cmas006", ds_Cmas006);
		response.setList("ds_Cmas008", ds_Cmas008);
	}

    @RequestMapping("retrieveOuterTripError.*")
	public void retrieveOuterTripError(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<OuterTrip> ds_OuterTripList = sService.retrieveOuterTripError(map);
		response.setList("ds_OuterTripList", ds_OuterTripList);  		//Map을 Client로 전송
	}



    /**
     * 조회
     * @param request
     * @param response
     */
    @RequestMapping("retrieveOuterTripViewDoc.*")
	public void retrieveOuterTripViewDoc(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<OuterTrip> ds_OuterTripDoc = sService.retrieveOuterTripViewDoc(map);
		//추가 출장자
		List<ExtnlPer> ds_ExtnlPer = sService.retrieveOuterTripExtnlPerViewDoc(map);
		//체제비
		List<Expn> ds_Expn = sService.retrieveOuterTripExpnViewDoc(map);
		//비자 방문지
		List<Visa> ds_Visa = sService.retrieveOuterTripVisaViewDoc(map);

		Map<String, Object> resultMap = new HashMap<String, Object>();

		map.put("Gubun", "O"); // 경비구분 (D국출, O해출)
		map.put("Measure", (String)map.get("refNo"));

		System.out.println("Gubun : " + (String)map.get("Gubun"));
		System.out.println("Measure : " + (String)map.get("Measure"));

		try{

			resultMap = cmasToEaiWSTest.getTripExpenseSearch(map);

		}catch(Exception e){
			e.printStackTrace();
		}

		response.setMap("ds_sapData", resultMap);



		response.setList("ds_OuterTripDoc", ds_OuterTripDoc);  		//Map을 Client로 전송
		response.setList("ds_ExtnlPer", ds_ExtnlPer);  		//Map을 Client로 전송
		response.setList("ds_Expn", ds_Expn);  		//Map을 Client로 전송
		response.setList("ds_Visa", ds_Visa);  		//Map을 Client로 전송
	}

    /**
     * 임시저장
     * @param request
     * @param response
     */
    @RequestMapping("saveOuterTripDraft.*")
	public void saveOuterTripDraft(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

//		ds_TripUserList : ds_TripUserList.getAllData("U"),
//		ds_VisaInfoList : ds_VisaInfoList.getAllData("U"),
//		ds_CityList : ds_CityList.getAllData("U"),
//		ds_ExpnList
//		ds_Sign : ds_Sign.getAllData("U")

		GridData<HashMap> ds_TripUserList = request.getGridData("ds_TripUserList", HashMap.class);
		GridData<HashMap> ds_VisaInfoList = request.getGridData("ds_VisaInfoList", HashMap.class);
		GridData<HashMap> ds_CityList = request.getGridData("ds_CityList", HashMap.class);
		GridData<HashMap> ds_ExpnList = request.getGridData("ds_ExpnList", HashMap.class);
		GridData<HashMap> ds_Sign = request.getGridData("ds_Sign", HashMap.class);

		sService.saveOuterTripDraft(map);
		sService.saveOuterTripUserList(ds_TripUserList, (String)map.get("docNo"));

		// 같은 테이블 사용 visaOwnYn 값 유무로 구별
		sService.saveOuterTripVisaInfoList(ds_VisaInfoList, (String)map.get("docNo"));
		sService.saveOuterTripVisaInfoList(ds_CityList, (String)map.get("docNo"));

		sService.saveOuterTripExpnList(ds_ExpnList, (String)map.get("docNo"));

	}

    /**
     * 통합결재 상신
     * @param request
     * @param response
     */
    @RequestMapping("saveSgnsRemoteDraft.*")
	public void saveSgnsRemoteDraft(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> signInfoList = request.getGridData("ds_Signln", HashMap.class);

		HttpServletRequest hRequest = WebContext.getRequest();
		String serverName = hRequest.getServerName();

		map.put("serverName", serverName);

		List<HashMap> lSignInfo = signInfoList.getList();

		System.out.println("lSignInfo size : " + lSignInfo.size());

		for (int i = 1; i < lSignInfo.size(); i++) {

			HashMap<String, Object> tempSign = lSignInfo.get(i);
			System.out.println("index : " + i);
			System.out.println("tempSign : " + tempSign.get("signUserId"));
		}

		Map<String, Object> rMap = new HashMap<String, Object>();

		try{
			rMap = signProcess.signOuterRequest(map, lSignInfo);

		}catch(Exception e){
			e.printStackTrace();
		}

		response.setMap("output1", rMap);
	}

    /**
     * 통합결재 상신후 Callback
     * @param request
     * @param response
     */

    @RequestMapping("CallBackServlet.*")
	public void CallBackServlet(MciRequest request, MciResponse response) {
    	Map<String, Object> data = request.get("json", HashMap.class);

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

		rslmap = cmasFlowUtil.processSignCallback(signId, programCode, docStsCd, legacyInfo, signUserId, docTitle);

		String acctSts = (String)rslmap.get("acctSts");

		Map<String, Object> resultMap = new HashMap<String, Object>();

		System.out.println("acctSts : " + acctSts + " / docStsCd : "+docStsCd);

		if(docStsCd.equals("D03") || docStsCd.equals("D04")){		//결재완료(승인) / 결재반려(회계취소)
			//회계 승인 처리
			System.out.println("docStsCd sap process: "+docStsCd);

			// Param Map
			Map<String, Object> paramMap = new HashMap<String, Object>();

//			I_REFKEY	CHAR	 15 	신청번호
//			I_NOTESDOC	CHAR	 32 	CMAS 문서번호
//			I_CONFIRM	CHAR	1	결재모드 ( Y: 결재, N: 반려 )
//			I_SENDER_ID	CHAR	7	결재자사번
//			I_BUKRS	CHAR	4	회사코드 (무조건 1000)


			paramMap.put("IRefkey", (String)rslmap.get("refNo"));
			paramMap.put("INotesdoc", (String)rslmap.get("docNo"));
			paramMap.put("IConfirm", (String)rslmap.get("confirm"));
			paramMap.put("ISenderId", (String)rslmap.get("userId"));
			paramMap.put("IBukrs", "1000");

			System.out.println("confirm OuterTrip : " + (String)rslmap.get("refNo"));
			System.out.println("confirm OuterTrip : " + (String)rslmap.get("docNo"));
			System.out.println("confirm OuterTrip : " + (String)rslmap.get("confirm"));
			System.out.println("confirm OuterTrip : " + (String)rslmap.get("userId"));

			try{
				resultMap = cmasToEaiWSTest.confirmOsBizTrip(paramMap);

			}catch(Exception e){
				e.printStackTrace();
			}

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

			// 2016-04-20 결재 반려시 eHR 정보 삭제 / 출장시작일, 종료일 정보가 없어 legacyInfo 에 추가
			if(docStsCd.equals("D04")){

//				legacyInfo=drafterId:1202588 이은숙||ordDate:20160420||ordNo:||adjustYn:N||docNo:CMAS-2016-000069||tripUserId:1202588||airTotalAmount:23||expnTotal:||cTotalAmt:142,271||totalAmount:142,294||
//				refNo:CA20163DFUR0003||usd:1185.59||eur:1335.80||gbp:1820.19||jpy:9.8827||vTcode:||oDate:2016-04-20||, dutySysCd=SGNS, signId=SGNS-2016-001200, clbkParam=instanceId=104&programCode=SGNS070003&attachInfo=&orgCd=1DFIW&userId=1202588&legacyInfo=drafterId:1202588 이은숙||ordDate:20160420||ordNo:||adjustYn:N||docNo:CMAS-2016-000069||tripUserId:1202588||airTotalAmount:23||expnTotal:||cTotalAmt:142,271||totalAmount:142,294||refNo:CA20163DFUR0003||usd:1185.59||eur:1335.80||gbp:1820.19||jpy:9.8827||vTcode:||oDate:2016-04-20||
//				&signId=SGNS-2016-001200&dutySysCd=SGNS&signInfo=1DFIW^1202588^1^T01##1DFUR^1202564^2^T03##1DFIW^1202588^3^T02&signDocTitle=해외출장신청서&process=http://172.21.18.38:8080/cmas/trip/outerTrip/CallBackServlet.xpl&, oggTime=20160420111154308, signDocTitle=해외출장신청서}


				// 신청서 반려시에만 작동
				if(programCode.equals("SGNS070003")){		//해외출장
					// 결재 처리 과정에 문제가 없도록 try catch 로 감싸줌
					try{

						int resultEhr;

						legacyMap.put("userId", (String)legacyMap.get("tripUserId"));

						legacyMap.put("approvalYn", "Y"); //#approvalYn#, -- Y
						legacyMap.put("vacaId", "출장"); //#vacaId#, -- 출장
						legacyMap.put("vacaAppId", "해외출장"); //#vacaAppId#, -- 국내출장
						legacyMap.put("modLocId", "CMAS"); // #modLocId# -- CMAS 작성시스템


						//선조회를 해와서
						Map<String, Object> countMap = iService.selectEHRInfo(legacyMap);

						String count = String.valueOf(countMap.get("count"));
						System.out.println("EHR 조회 결과 : " + count);
						if(Integer.parseInt(count) > 0){
							//있다면 delete
							resultEhr = iService.deleteEHRInfo(legacyMap);
							System.out.println("EHR delete 결과 : " + resultEhr);
						}

					}catch(Exception e){
						e.printStackTrace();
					}

				}

			}

			if(docStsCd.equals("D03")){

				// 정산서 결재 완료 시 eHR 변경된 날짜 삽입
				if(programCode.equals("SGNS070005")){		//해외출장정산서

					// 결재 처리 과정에 문제가 없도록 try catch 로 감싸줌
					try{

						int resultEhr;

						String userId = (String)legacyMap.get("tripUserId");
						legacyMap.put("userId", userId.split(" ")[0]);

						legacyMap.put("approvalYn", "Y"); //#approvalYn#, -- Y
						legacyMap.put("vacaId", "출장"); //#vacaId#, -- 출장
						legacyMap.put("vacaAppId", "해외출장"); //#vacaAppId#, -- 국내출장
						legacyMap.put("modLocId", "CMAS"); // #modLocId# -- CMAS 작성시스템


						//선조회를 해와서
						Map<String, Object> countMap = iService.selectEHRInfo(legacyMap);

						String count = String.valueOf(countMap.get("count"));
						System.out.println("EHR 조회 결과 : " + count);
						if(Integer.parseInt(count) > 0){
							//있다면 delete
							resultEhr = iService.deleteEHRInfo(legacyMap);
							System.out.println("EHR delete 결과 : " + resultEhr);
						}

						//정산서의 변경된 날짜로 eHR 삽입
						legacyMap.put("startDate", (String)legacyMap.get("rTripDateStart"));
						legacyMap.put("endDate", (String)legacyMap.get("rTripDateEnd"));

						//선조회를 해와서
						Map<String, Object> nCountMap = iService.selectEHRInfo(legacyMap);

						String nCount = String.valueOf(nCountMap.get("count"));
						System.out.println("EHR 조회 결과 : " + nCount);
						if(Integer.parseInt(nCount) > 0){
							//있다면 update
							resultEhr = iService.updateUpdateEHRInfo(legacyMap);
							System.out.println("EHR update 결과 : " + resultEhr);
						}else{
							//없다면 insert
							resultEhr = iService.updateEHRInfo(legacyMap);
							System.out.println("EHR insert 결과 : " + resultEhr);
						}

					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}

			if(docStsCd.equals("D03")){
				if(programCode.equals("SGNS070003")){
					System.out.println("신청서 PDF 증빙 시작");

					/* pdf 생성 */
					try {
						outerTripDraftPDFHelper.createPdf(docTitle, signId, rslmap, legacyMap);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					System.out.println("Async Call");




				}else if(programCode.equals("SGNS070005")){
					System.out.println("정산서 PDF 증빙 시작");

					/* pdf 생성 */
					try {
						outerTripAdjustPDFHelper.createPdf(docTitle, signId, rslmap, legacyMap);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					System.out.println("Async Call");

				}
			}
		}

		response.setMap("ds_sapData", resultMap);
		response.setMap("RESULT", rslmap);
	}

    @RequestMapping("saveCmasDocUpdateSignId.*")
	public void saveCmasDocUpdateSignId(MciRequest request, MciResponse response) throws Exception{
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> signInfoList = request.getGridData("ds_SignInfo", HashMap.class);

		String docNo = (String)map.get("docNo");
		String signId = (String)map.get("signId");
		String dutyCls = (String)map.get("dutyCls");
		sService.deleteSignInfoOuterTrip(map);

		sService.saveSignInfoOuterTrip2(signInfoList, docNo, signId, dutyCls);

		// 메일 전송
		GridData<HashMap> data = request.getGridData("input1", HashMap.class);
		String mailYn = (String)map.get("mailYn");
		if(mailYn.equals("Y")){
			mService.sendMail(data);
		}


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


    @RequestMapping("saveOuterTripDocSave.*")
 	public void saveOuterTripDocSave(MciRequest request, MciResponse response) {
 		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

 		sService.saveOuterTripDocSave(map);

 		GridData<HashMap> ds_TripUserList = request.getGridData("ds_TripUserList", HashMap.class);
		GridData<HashMap> ds_VisaInfoList = request.getGridData("ds_VisaInfoList", HashMap.class);
		GridData<HashMap> ds_CityList = request.getGridData("ds_CityList", HashMap.class);
		GridData<HashMap> ds_ExpnList = request.getGridData("ds_ExpnList", HashMap.class);
		GridData<HashMap> ds_Sign = request.getGridData("ds_Sign", HashMap.class);


		sService.saveOuterTripUserList(ds_TripUserList, (String)map.get("docNo"));

		// 같은 테이블 사용 visaOwnYn 값 유무로 구별
		sService.saveOuterTripVisaInfoList(ds_VisaInfoList, (String)map.get("docNo"));
		sService.saveOuterTripVisaInfoList(ds_CityList, (String)map.get("docNo"));

		sService.saveOuterTripExpnList(ds_ExpnList, (String)map.get("docNo"));


 	}

    @RequestMapping("updateOuterTripDocSave.*")
 	public void updateOuterTripDocSave(MciRequest request, MciResponse response) {
 		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

 		sService.updateOuterTripDocSave(map);


 		GridData<HashMap> ds_TripUserList = request.getGridData("ds_TripUserList", HashMap.class);
		GridData<HashMap> ds_VisaInfoList = request.getGridData("ds_VisaInfoList", HashMap.class);
		GridData<HashMap> ds_CityList = request.getGridData("ds_CityList", HashMap.class);
		GridData<HashMap> ds_ExpnList = request.getGridData("ds_ExpnList", HashMap.class);
		GridData<HashMap> ds_Sign = request.getGridData("ds_Sign", HashMap.class);



		sService.deleteOuterTripUserList(map);
		sService.saveOuterTripUserList(ds_TripUserList, (String)map.get("docNo"));

		sService.deleteOuterTripVisaInfoList(map);
		// 같은 테이블 사용 visaOwnYn 값 유무로 구별
		sService.saveOuterTripVisaInfoList(ds_VisaInfoList, (String)map.get("docNo"));
		sService.saveOuterTripVisaInfoList(ds_CityList, (String)map.get("docNo"));

		sService.deleteOuterTripExpnList(map);
		sService.saveOuterTripExpnList(ds_ExpnList, (String)map.get("docNo"));

		map.put("dutyCls", "12");
		sService.deleteSignInfoOuterTrip(map);
		sService.saveSignInfoOuterTrip(ds_Sign, (String)map.get("docNo"), "", "12");

 	}

    @RequestMapping("updateOuterTripDocSave2.*")
 	public void updateOuterTripDocSave2(MciRequest request, MciResponse response) throws Exception{
 		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

 		sService.updateOuterTripDocSave2(map);


 		GridData<HashMap> ds_TripUserList = request.getGridData("ds_TripUserList", HashMap.class);
		GridData<HashMap> ds_VisaInfoList = request.getGridData("ds_VisaInfoList", HashMap.class);
		GridData<HashMap> ds_CityList = request.getGridData("ds_CityList", HashMap.class);
		GridData<HashMap> ds_ExpnList = request.getGridData("ds_ExpnList", HashMap.class);
		GridData<HashMap> ds_Sign = request.getGridData("ds_Sign", HashMap.class);





		sService.deleteOuterTripUserList(map);
		sService.saveOuterTripUserList(ds_TripUserList, (String)map.get("docNo"));

		sService.deleteOuterTripVisaInfoList(map);
		// 같은 테이블 사용 visaOwnYn 값 유무로 구별
		sService.saveOuterTripVisaInfoList(ds_VisaInfoList, (String)map.get("docNo"));
		sService.saveOuterTripVisaInfoList(ds_CityList, (String)map.get("docNo"));

		sService.deleteOuterTripExpnList(map);
		sService.saveOuterTripExpnList(ds_ExpnList, (String)map.get("docNo"));

		map.put("dutyCls", "12");
		sService.deleteSignInfoOuterTrip(map);
		sService.saveSignInfoOuterTrip(ds_Sign, (String)map.get("docNo"), "", "12");

		// 메일 전송
		GridData<HashMap> data = request.getGridData("input1", HashMap.class);
		String mailYn = (String)map.get("mailYn");
		if(mailYn.equals("Y")){
			mService.sendMail(data);
		}

 	}

    @RequestMapping("retrieveSavedDocInfo.*")
	public void retrieveSavedDocInfo(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> ds_DocData = sService.retrieveSavedDocInfo(map);

		List<ExtnlPer> ds_ExtnlPer = sService.retrieveOuterTripExtnlPerViewDoc(map);
		//체제비
		List<Expn> ds_Expn = sService.retrieveOuterTripExpnViewDoc(map);
		//비자 방문지
		List<Visa> ds_Visa = sService.retrieveOuterTripVisaViewDoc(map);

		response.setMap("ds_DocData", ds_DocData);  		//Map을 Client로 전송
		response.setList("ds_ExtnlPer", ds_ExtnlPer);  		//Map을 Client로 전송
		response.setList("ds_Expn", ds_Expn);  		//Map을 Client로 전송
		response.setList("ds_Visa", ds_Visa);  		//Map을 Client로 전송

    }

    @RequestMapping("retrieveSignInfo.*")
	public void retrieveSignInfo(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Sign> ds_Sign = sService.retrieveSignInfo(map);

		response.setList("ds_Sign", ds_Sign);  		//Map을 Client로 전송

    }

    @RequestMapping("retrieveSignInfo2.*")
	public void retrieveSignInfo2(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Sign> ds_Sign = sService.retrieveSignInfo2(map);

		response.setList("ds_Sign", ds_Sign);  		//Map을 Client로 전송

    }

    @RequestMapping("retrieveSignInfo3.*")
	public void retrieveSignInfo3(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Sign> ds_Sign = sService.retrieveSignInfo3(map);

		response.setList("ds_Sign", ds_Sign);  		//Map을 Client로 전송

    }

    @RequestMapping("retrieveSignInfo4.*")
	public void retrieveSignInfo4(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Sign> ds_Sign = sService.retrieveSignInfo4(map);

		response.setList("ds_Sign", ds_Sign);  		//Map을 Client로 전송

    }

    @RequestMapping("updateOuterTripDraft.*")
	public void updateOuterTripDraft(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

//		ds_TripUserList : ds_TripUserList.getAllData("U"),
//		ds_VisaInfoList : ds_VisaInfoList.getAllData("U"),
//		ds_CityList : ds_CityList.getAllData("U"),
//		ds_ExpnList
//		ds_Sign : ds_Sign.getAllData("U")

		GridData<HashMap> ds_TripUserList = request.getGridData("ds_TripUserList", HashMap.class);
		GridData<HashMap> ds_VisaInfoList = request.getGridData("ds_VisaInfoList", HashMap.class);
		GridData<HashMap> ds_CityList = request.getGridData("ds_CityList", HashMap.class);
		GridData<HashMap> ds_ExpnList = request.getGridData("ds_ExpnList", HashMap.class);
		GridData<HashMap> ds_Sign = request.getGridData("ds_Sign", HashMap.class);

		sService.updateOuterTripDraft(map);
		sService.deleteOuterTripUserList(map);
		sService.saveOuterTripUserList(ds_TripUserList, (String)map.get("docNo"));

		// 같은 테이블 사용 visaOwnYn 값 유무로 구별
		sService.deleteOuterTripVisaInfoList(map);
		sService.saveOuterTripVisaInfoList(ds_VisaInfoList, (String)map.get("docNo"));
		sService.saveOuterTripVisaInfoList(ds_CityList, (String)map.get("docNo"));

		sService.deleteOuterTripExpnList(map);
		sService.saveOuterTripExpnList(ds_ExpnList, (String)map.get("docNo"));

	}


    /**
     * 정산서 관련
     */
    @RequestMapping("saveAdjustDoc.*")
 	public void saveAdjustDoc(MciRequest request, MciResponse response) {
 		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
 		System.out.println("saveAdjustDoc");

 		sService.saveAdjustDoc(map);

 		GridData<HashMap> ds_Sign = request.getGridData("ds_Sign", HashMap.class);

		map.put("dutyCls", "14");
		sService.deleteSignInfoOuterTrip(map);
		sService.saveSignInfoOuterTrip(ds_Sign, (String)map.get("docNo"), "", "14");

 	}

    /*해외출장 담당자 검토 반려*/
    @RequestMapping("updateAdjustDoc.*")
 	public void updateAdjustDoc(MciRequest request, MciResponse response) throws Exception {
 		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
 		System.out.println("updateAdjustDoc");

 		sService.updateAdjustDoc(map);

 		GridData<HashMap> ds_Sign = request.getGridData("ds_Sign", HashMap.class);

 		map.put("dutyCls", "14");
		sService.deleteSignInfoOuterTrip(map);
		sService.saveSignInfoOuterTrip(ds_Sign, (String)map.get("docNo"), "", "14");

		// 메일 전송
		GridData<HashMap> data = request.getGridData("input1", HashMap.class);
		String mailYn = (String)map.get("mailYn");
		if(mailYn.equals("Y")){
			mService.sendMail(data);
		}

 	}

    /*반납취소*/
    @RequestMapping("updateCancelAdjustDoc.*")
 	public void updateCancelAdjustDoc(MciRequest request, MciResponse response) throws Exception{
 		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
 		System.out.println("updateCancelAdjustDoc");
 		sService.updateCancelAdjustDoc(map);

 		//GridData<HashMap> ds_Sign = request.getGridData("ds_Sign", HashMap.class);
 		//map.put("dutyCls", "14");
		//sService.deleteSignInfoOuterTrip(map);
		//sService.saveSignInfoOuterTrip(ds_Sign, (String)map.get("docNo"), "", "14");
    }


    @RequestMapping("getAdjustSavedDoc.*")
 	public void getAdjustSavedDoc(MciRequest request, MciResponse response) {
 		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

 		Map<String, Object> ds_SavedDoc = sService.getAdjustSavedDoc(map);

 		String adjustStsCd = (String)map.get("adjustStsCd");

 		String refNo = "";
 		if(adjustStsCd.equals("D02")){
 			refNo = (String)ds_SavedDoc.get("refNo");
 		}else if(adjustStsCd.equals("D03")){
 			refNo = (String)ds_SavedDoc.get("refNo");
 	}


 		Map<String, Object> resultMap = new HashMap<String, Object>();

		map.put("Gubun", "O"); // 경비구분 (D국출, O해출)
		map.put("Measure", refNo);

		System.out.println("Gubun : " + (String)map.get("Gubun"));
		System.out.println("Measure : " + (String)map.get("Measure"));

		try{

			resultMap = cmasToEaiWSTest.getTripExpenseSearch(map);

		}catch(Exception e){
			e.printStackTrace();
		}

		response.setMap("ds_aSapData", resultMap);

		response.setMap("ds_SavedDoc", ds_SavedDoc);  		//Map을 Client로 전송

 	}

    @RequestMapping("updateAdjustDraftDoc.*")
 	public void updateAdjustDraftDoc(MciRequest request, MciResponse response) {
 		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

 		Map<String, Object> resultMap = new HashMap<String, Object>();

 		try{
 			sService.updateAdjustDraftDoc(map);
 		}catch(Exception e){
 			e.printStackTrace();

 			/**
 			 * 업데이트 실패의 경우 반려 처리
 			 */
 			Map<String, Object> paramMap = new HashMap<String, Object>();

 			paramMap.put("IRefkey", (String)map.get("refNo"));
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

 		response.setMap("output1", resultMap);


 	}


    @RequestMapping("saveAdjustSgnsRemoteDraft.*")
	public void saveAdjustSgnsRemoteDraft(MciRequest request, MciResponse response)  throws Exception{
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
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

		}

		Map<String, Object> rMap = new HashMap<String, Object>();

		try{
			rMap = signProcess.signAdjustOuterRequest(map, lSignInfo2);

		}catch(Exception e){
			e.printStackTrace();
		}

		String sType = (String)map.get("sType");

		rMap.put("sType", sType);

		response.setMap("output1", rMap);
	}

    @RequestMapping("updateOuterTripInfo.*")
 	public void updateOuterTripInfo(MciRequest request, MciResponse response) throws Exception{
 		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		sService.updateOuterTripInfo(map);

		// 메일 전송
		GridData<HashMap> data = request.getGridData("input1", HashMap.class);
		String mailYn = (String)map.get("mailYn");
		if(mailYn.equals("Y")){
			mService.sendMail(data);
		}

 	}

    @RequestMapping("updateOuterTripAirConf.*")
 	public void updateOuterTripAirConf(MciRequest request, MciResponse response) {
 		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

 		sService.updateOuterTripAirConf(map);

 	}

    @RequestMapping("deleteOuterTripDoc.*")
 	public void deleteOuterTripDoc(MciRequest request, MciResponse response) {
 		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

 		sService.deleteOuterTripByDocNo(map);
 		sService.deleteOuterTripUserList(map);
 		sService.deleteOuterTripVisaInfoList(map);
 		sService.deleteOuterTripExpnList(map);
 	}

    @RequestMapping("deleteOuterTripAdjustDoc.*")
 	public void deleteOuterTripAdjustDoc(MciRequest request, MciResponse response) {
 		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

 		sService.deleteOuterTripAdjustByDocNo(map);
 	}


    @RequestMapping("updateOuterTripGHRCmt.*")
 	public void updateOuterTripGHRCmt(MciRequest request, MciResponse response) {
 		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

 		sService.updateOuterTripGHRCmt(map);

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

		System.out.println("신청서 PDF 증빙 시작");

		/* pdf 생성 */
		try {
			outerTripDraftPDFHelper.createPdf(docTitle, signId, rslmap, lParamMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Async Call");
    }

    @RequestMapping("createPdfDoc2.*")
	public void createPdfDoc2(MciRequest request, MciResponse response) {
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

		System.out.println("해외출장정산서 PDF 증빙 시작");

		/* pdf 생성 */
		try {
			outerTripAdjustPDFHelper.createPdf(docTitle, signId, rslmap, lParamMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Async Call");
    }

    @RequestMapping("getUserRealPositCd.*")
	public void getUserRealPositCd(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> resultMap = sService.getUserRealPositCd(map);

		response.setMap("output1", resultMap);  		//Map을 Client로 전송
    }

    @RequestMapping("retrieveRejCont.*")
	public void retrieveRejCont(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> resultMap = sService.retrieveRejCont(map);

		response.setList("output1", resultMap);  		//Map을 Client로 전송
    }

    @RequestMapping("updateErrMsg.*")
 	public void updateErrMsg(MciRequest request, MciResponse response) {
 		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

 		sService.updateErrMsg(map);

 	}

    @RequestMapping("retrieveErrMsg.*")
	public void retrieveErrMsg(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> resultMap = sService.retrieveErrMsg(map);

		response.setMap("output1", resultMap);  		//Map을 Client로 전송
    }

    @RequestMapping("updateErrMsgAdjust.*")
 	public void updateErrMsgAdjust(MciRequest request, MciResponse response) {
 		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

 		sService.updateErrMsgAdjust(map);

 	}

    @RequestMapping("retrieveErrMsgAdjust.*")
	public void retrieveErrMsgAdjust(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> resultMap = sService.retrieveErrMsgAdjust(map);

		response.setMap("output1", resultMap);  		//Map을 Client로 전송
    }

    @RequestMapping("updateAdjustGHRCmt.*")
 	public void updateAdjustGHRCmt(MciRequest request, MciResponse response) {
 		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

 		sService.updateAdjustGHRCmt(map);

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
		paramMap.put("IBukrs", "1000");

		try{

			resultMap = cmasToEaiWSTest.confirmOsBizTrip(paramMap);

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
     * 바로콘 위험국가 메일내용 조회
     * @param request
     * @param response
     */
    @RequestMapping("retrieveSpgEmailMgm.*")
 	public void retrieveSpgEmailMgm(MciRequest request, MciResponse response) {
 		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

 		String natCdStr = (String)map.get("natCd");            // NG||GT||LY||DZ||IQ 루프.
 		String tripUserIdStr = (String)map.get("tripUserId");  // 출장자.
 		String strAry[] = natCdStr.split("\\|\\|");            // "||" 를 기준으로 split

 		//System.out.println("메일수신인 : " + tripUserIdStr);

 		for (int i = 0; i < strAry.length; i++) {
			// strAry[i] ( natCd ) 값으로 각각의 국가에 대한 메일 내용을 조회해서 새로운 Map 에 저장.
			Map<String, String> natMap = new HashMap<String, String>();
			natMap.put("natCd", strAry[i]);

			List<Nat> mailContList = sService.retrieveSpgEmailMgm(natMap);
			// 가지고온 메일 컨텐트 로 메일 발송.
			// 메일 발송 함수 호출.
			//System.out.println("mailContList.size(): "+mailContList.size());

			if( mailContList.size() > 0 ){
				Nat nat = (Nat)mailContList.get(0);
				// 메일 내용/타이틀/발송자/첨부파일 등 정보를 가지고 온다.
				String cont = nat.getCont();
				String title = nat.getTitle();
				String sendUserId = nat.getSendUserId();
				String htmlCont ="<table style='width:80%;border:solid 1px #eeeeee'>";
					   htmlCont +="<tr><td style='background-color:#eeeeee;text-align:center;padding:5px;'><h3><b>"+title+"</b></h3></td></tr>";
					   htmlCont +="<tr><td style='padding:5px;'><pre style=\"line-height:150%;font-family:'맑은 고딕';\">"+cont+"</pre></td></tr></table>";

				//System.out.println("메일내용: "+cont);

				// 메일 관련 파라미터
				HashMap<String, Object> mailParam = new HashMap<String, Object>();
				mailParam.put("spec", "");
				mailParam.put("bodyTemplate", "");
				mailParam.put("fromMailId", "1DFVL@daewooenc.com");
				mailParam.put("fromMailName", "글로벌지원팀");
				mailParam.put("mailId", tripUserIdStr+"@daewooenc.com");
				mailParam.put("mailSubject", title);
				mailParam.put("htmlBody", htmlCont);

				//System.out.println("메일내용: "+htmlCont);

                StringBuffer files=new StringBuffer();
                StringBuffer ecmNos=new StringBuffer();
                Map<String, Object> mapAttachs = new HashMap<String, Object>();

                for(int j=0 ; j<mailContList.size() ; j++) {
                	if(mailContList.get(j).getFileNm() != null){
	                    files.append (mailContList.get(j).getFileNm().replace(",", "_")); // 파일명에 , 포함 시 메일발송 오류로 인해 ,를 _로 치환
	                    ecmNos.append(mailContList.get(j).getEcmNo());
	                    files.append (",");
	                    ecmNos.append(",");
                	}
                }

                String sFiles = files.toString();
                String sEcmNos = ecmNos.toString();

                // -1은 마지막 ,를 없앤다.
                if(sFiles.length() > 0) {
                    sFiles = sFiles.substring(0, sFiles.length() - 1);
                }
                if(sEcmNos.length() > 0) {
                    sEcmNos = sEcmNos.substring(0, sEcmNos.length() - 1);
                }

                mapAttachs.put("fileList", sFiles);
                mapAttachs.put("ecmList", sEcmNos);
               // System.out.println("========================================>"+mapAttachs);

                if(sFiles.length() > 0 && sEcmNos.length() > 0) {
                	mailParam.put("attachs", mapAttachs); // 15. 첨부 파일
                }

				try {
					mUtil.sendMail(mailParam);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
    }
}