package com.dwenc.cmas.trip.util;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jcf.data.GridData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.info.domain.SiteDomain;
import com.dwenc.cmas.trip.domain.CmasNo;
import com.dwenc.cmas.trip.domain.InnerTrip;
import com.dwenc.cmas.trip.service.CityTranspService;
import com.dwenc.cmas.trip.service.InnerTripService;
import com.dwenc.cmas.trip.service.OuterTripService;

import docfbaro.query.CommonDao;
import docfbaro.query.callback.AbstractRowStatusCallback;
import docfbaro.sua.exception.BusinessException;

/**
 * <pre>
 * --------------------------------------------------------------
 * 업무구분 : 공통 - 코드관리
 * 프로그램 : COA0101
 * 설 명 : 공통코드 관리를 위한 service 클래스
 * 작 성 자 :
 * 작성일자 :
 * 수정이력
 * --------------------------------------------------------------
 * 수정일                          이 름          사유
 * --------------------------------------------------------------
 *
 * --------------------------------------------------------------
 * </pre>
 * @version 1.0
 *
 */

@Service
public class CmasFlowUtil {

    /**
     * DB 처리를 위한 공통 dao
     */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	@Autowired
	private InnerTripService sService;

	@Autowired
	private CityTranspService cService;

	@Autowired
	private OuterTripService oService;

	/**
	 * sign callback 처리를 위한 메소드
	 *
	 * @param String signId
	 * @param String programCode
	 * @param String proxyType
	 * @param String docStsCd
	 * @param String legacyInfo
	 * @param String signUserId
	 * @param String docTitle
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> processSignCallback
	      ( String signId, String programCode, String docStsCd, String legacyInfo, String signUserId, String docTitle	){

		Map<String, Object> paramMap = new HashMap<String, Object>();
		boolean callBackResult  	 = true;

		paramMap.put("signId"		, signId);
		paramMap.put("programCode"	, programCode);
		paramMap.put("docStsCd"		, docStsCd);
		paramMap.put("signUserId"	, signUserId);
		paramMap.put("docTitle"		, docTitle);

		Map<String, Object> resultMap = new HashMap<String, Object>();

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
					paramMap.put(subAry[0], "");
				} else {
					paramMap.put(subAry[0], subAry[1]);
				}

			}
		}

		if("SGNS070002".equals(programCode)){	// 국내출장

//			signDocTitle=국내출장신청서, signId=SGNS-2015-000215, dutyLnkCd=SGNS070002, instanceId=104, programCode=SGNS070002, clbkPath=http://172.21.18.232:8081/trip/innerTrip/CallBackServlet.xpl, process=http://172.21.18.232:8081/trip/innerTrip/CallBackServlet.xpl, signInfo=1DFUR^1202429^1^T01##1DFUR^1202564^2^T02, curSiteCd=1DFUR, signSeq=2, docStsCd=D03, userId=1202429, dutySysCd=SGNS, legacyInfo=refNo:IO20153DFUR0032||tripUserId:1202429 강혜성||tripUserOrg:1DFUR IT운영팀||drafterId:1202429 강혜성||drafterOrg:1DFUR IT운영팀||bdgtType:I. 일반경비||aufnrNo:000010012300||execTeam:3DFUR IT운영팀||tripTarget:장소테스트||tripPurp:(국출)목적테스트||startDate:2015-09-03||endDate:2015-09-04||account:213211167098||compCar:N||tAmt1:1332||tAmt2:0||tAmt3:0||dayAmt:         50000||nightAmt:         40000||docNo:CMAS-2015-000023||ordDate:||ordNo:||agencyUser:||trRecvUser:||trAmount:||trAccount:||, signUserId=1202564, orgCd=1DFUR

			// 업무에 해당하는 call 처리 서비스를 각 업무 패키지에 구현하여 여기서는 서비스 메서드만을 호출 하게 한다.
			// 여기는 업무테이블의 결재필드 결재id와 상태값을 업데이트 하기 위한 서비스 호출이다.

//			1DFUR^1202429^1^T01##1DFUR^1202564^2^T02

//			String signInfo = (String)paramMap.get("signInfo");
//
//			Map<String, Object> signMap = new HashMap<String, Object>();
//
//			// string 이 비어 있지 않다면
//			if (!signInfo.isEmpty()) {
//				// legacy info 분해 작업을 시작한다.
//				String signAry[] = signInfo.split("\\#\\#");
//				// legacy info 는 key:value||key:value 의 형태로 전달되기때문에 || 를 기준으로 split
//				// 한것을 : 으로 다시 split 하여 map 에 값을 넣는다.
//				// value 가 key:value^value 로 연결된 1차월 배열 또는
//				// key:value^value##value^value 이런 형태로연결된 2차원 배열의 경우는 각 업무의
//				// 서비스단에서 처리 하도록 한다.
//				for (int i = 0; i < signAry.length; i++) {
//					String signSubAry[] = signAry[i].split("^");
//					signMap.put("apperOrgCd", signSubAry[0]);
//					signMap.put("signUserId", signSubAry[1]);
//					signMap.put("signSeq", signSubAry[2]);
//					signMap.put("signTpCd", signSubAry[3]);
//				}
//			}


//			int resultCmas = sService.updateInnerTripInfo(paramMap);
//			int resultSign = sService.updateSignDt(paramMap);

			resultMap.put("docSts", (String)paramMap.get("docSts"));
			resultMap.put("signId", (String)paramMap.get("signId"));


//			if(resultCmas == 0) callBackResult = false;
//			if(resultSign == 0) callBackResult = false;

			// 결재완료시 (DO3) eHR 에 근태 정보를 INSERT 한다.
			int resultEhr;

			if(docStsCd.equals("D03")){

//				tripUserId
//				tripUserOrg
//
//				#userId#, -- USER ID tripUserId
//				#startDate#, -- 출장시작일
//				#endDate#, -- 출장종료일
//				#vacatQty#, -- ? VACAT_QTY
				String userId = (String)paramMap.get("tripUserId");
				paramMap.put("userId", userId.split(" ")[0]);

				paramMap.put("approvalYn", "Y"); //#approvalYn#, -- Y
				paramMap.put("vacaId", "출장"); //#vacaId#, -- 출장
				paramMap.put("vacaAppId", "국내출장"); //#vacaAppId#, -- 국내출장
				paramMap.put("modLocId", "CMAS"); // #modLocId# -- CMAS 작성시스템

/*
인사시스템에 국내출장 근태 저장 제거(2020.11.13)(실비정산 적용으로 eHR저장 제거) (시작)

				//선조회를 해와서
				Map<String, Object> countMap = sService.selectEHRInfo(paramMap);

				String count = String.valueOf(countMap.get("count"));
				System.out.println("EHR 조회 결과 : " + count);
				if(Integer.parseInt(count) > 0){
					//있다면 update
					resultEhr = sService.updateUpdateEHRInfo(paramMap);
				}else{
					//없다면 insert
					resultEhr = sService.updateEHRInfo(paramMap);
				}

인사시스템에 국내출장 근태 저장 제거(2020.11.13)(실비정산 적용으로 eHR저장 제거) (종료)
*/

				// 바로콘 온실가스 정보 insert

				int resultEvg=1;

				try{
					sService.updateEvgBarnetInfo(paramMap);
				}catch(Exception e){
					e.printStackTrace();
					resultEvg = 0;
				}

				if(resultEvg == 0) callBackResult = false;
			}

			// callback 처리중 에러 발생시
			if (!callBackResult) {
				// 실패시 FAILURE , // 성공시 SUCCESS
				resultMap.put("TYPE", "FAILURE");
				// 메세지 처리를 원한다면 메세지 코드를 삽입
				resultMap.put("MSG_CODE", "처리에 실패하였습니다.");
				// 메세지 코드가 인자를 받아 처리하게 되어있다면 인자를 배열로 전달
				// resultMap.put("MSG_VALUES", new String[]{"arg1", "arg2"...."argN"});
				return resultMap;
			} else {
				// 성공시 SUCCESS
				resultMap.put("TYPE", "SUCCESS");
			}

			// 회계승인
			if(docStsCd.equals("D03")){
				resultMap.put("acctSts", "Y");
				resultMap.put("confirm", "Y");
				resultMap.put("refNo", (String)paramMap.get("refNo"));
				resultMap.put("docNo", (String)paramMap.get("docNo"));
				String userId = (String)paramMap.get("drafterId");
				resultMap.put("userId", userId.split(" ")[0]);
			} else if(docStsCd.equals("D04")){
				resultMap.put("acctSts", "");
				resultMap.put("confirm", "N");
				resultMap.put("refNo", (String)paramMap.get("refNo"));
				resultMap.put("docNo", (String)paramMap.get("docNo"));
				String userId = (String)paramMap.get("drafterId");
				resultMap.put("userId", userId.split(" ")[0]);
			}
		}

		if("SGNS070001".equals(programCode)){	// 시내교통비

//			signDocTitle=국내출장신청서, signId=SGNS-2015-000215, dutyLnkCd=SGNS070002, instanceId=104, programCode=SGNS070002, clbkPath=http://172.21.18.232:8081/trip/innerTrip/CallBackServlet.xpl, process=http://172.21.18.232:8081/trip/innerTrip/CallBackServlet.xpl, signInfo=1DFUR^1202429^1^T01##1DFUR^1202564^2^T02, curSiteCd=1DFUR, signSeq=2, docStsCd=D03, userId=1202429, dutySysCd=SGNS, legacyInfo=refNo:IO20153DFUR0032||tripUserId:1202429 강혜성||tripUserOrg:1DFUR IT운영팀||drafterId:1202429 강혜성||drafterOrg:1DFUR IT운영팀||bdgtType:I. 일반경비||aufnrNo:000010012300||execTeam:3DFUR IT운영팀||tripTarget:장소테스트||tripPurp:(국출)목적테스트||startDate:2015-09-03||endDate:2015-09-04||account:213211167098||compCar:N||tAmt1:1332||tAmt2:0||tAmt3:0||dayAmt:         50000||nightAmt:         40000||docNo:CMAS-2015-000023||ordDate:||ordNo:||agencyUser:||trRecvUser:||trAmount:||trAccount:||, signUserId=1202564, orgCd=1DFUR

			// 업무에 해당하는 call 처리 서비스를 각 업무 패키지에 구현하여 여기서는 서비스 메서드만을 호출 하게 한다.
			// 여기는 업무테이블의 결재필드 결재id와 상태값을 업데이트 하기 위한 서비스 호출이다.

//			1DFUR^1202429^1^T01##1DFUR^1202564^2^T02

//			String signInfo = (String)paramMap.get("signInfo");
//
//			Map<String, Object> signMap = new HashMap<String, Object>();
//
//			// string 이 비어 있지 않다면
//			if (!signInfo.isEmpty()) {
//				// legacy info 분해 작업을 시작한다.
//				String signAry[] = signInfo.split("\\#\\#");
//				// legacy info 는 key:value||key:value 의 형태로 전달되기때문에 || 를 기준으로 split
//				// 한것을 : 으로 다시 split 하여 map 에 값을 넣는다.
//				// value 가 key:value^value 로 연결된 1차월 배열 또는
//				// key:value^value##value^value 이런 형태로연결된 2차원 배열의 경우는 각 업무의
//				// 서비스단에서 처리 하도록 한다.
//				for (int i = 0; i < signAry.length; i++) {
//					String signSubAry[] = signAry[i].split("^");
//					signMap.put("apperOrgCd", signSubAry[0]);
//					signMap.put("signUserId", signSubAry[1]);
//					signMap.put("signSeq", signSubAry[2]);
//					signMap.put("signTpCd", signSubAry[3]);
//				}
//			}


//			int resultCmas = cService.updateCityTranspInfo(paramMap);
//			int resultSign = cService.updateSignDt(paramMap);

			resultMap.put("docSts", (String)paramMap.get("docSts"));
			resultMap.put("signId", (String)paramMap.get("signId"));

//			if(resultCmas == 0) callBackResult = false;
//			if(resultSign == 0) callBackResult = false;

			// callback 처리중 에러 발생시
//			 callBackResult = false;
			if (!callBackResult) {
				// 실패시 FAILURE
				// 성공시 SUCCESS
				resultMap.put("TYPE", "FAILURE");
				// 메세지 처리를 원한다면 메세지 코드를 삽입
				resultMap.put("MSG_CODE", "처리에 실패하였습니다.");
				// 메세지 코드가 인자를 받아 처리하게 되어있다면 인자를 배열로 전달
				// resultMap.put("MSG_VALUES", new String[]{"arg1",
				// "arg2"...."argN"});
			} else {
				// 성공시 SUCCESS
				resultMap.put("TYPE", "SUCCESS");
			}

			// 회계승인
			if(docStsCd.equals("D03")){
				resultMap.put("acctSts", "Y");
				resultMap.put("mode", "Y");
				resultMap.put("refNo", (String)paramMap.get("refNo"));
				resultMap.put("docNo", (String)paramMap.get("docNo"));
				String userId = (String)paramMap.get("drafterId");
				resultMap.put("userId", userId.split(" ")[0]);
			} else if(docStsCd.equals("D04")){
				resultMap.put("acctSts", "");
				resultMap.put("mode", "N");
				resultMap.put("refNo", (String)paramMap.get("refNo"));
				resultMap.put("docNo", (String)paramMap.get("docNo"));
				String userId = (String)paramMap.get("drafterId");
				resultMap.put("userId", userId.split(" ")[0]);
			}
		}

		if("SGNS070003".equals(programCode)){ // 해외출장

//			signDocTitle=국내출장신청서, signId=SGNS-2015-000215, dutyLnkCd=SGNS070002, instanceId=104, programCode=SGNS070002, clbkPath=http://172.21.18.232:8081/trip/innerTrip/CallBackServlet.xpl, process=http://172.21.18.232:8081/trip/innerTrip/CallBackServlet.xpl, signInfo=1DFUR^1202429^1^T01##1DFUR^1202564^2^T02, curSiteCd=1DFUR, signSeq=2, docStsCd=D03, userId=1202429, dutySysCd=SGNS, legacyInfo=refNo:IO20153DFUR0032||tripUserId:1202429 강혜성||tripUserOrg:1DFUR IT운영팀||drafterId:1202429 강혜성||drafterOrg:1DFUR IT운영팀||bdgtType:I. 일반경비||aufnrNo:000010012300||execTeam:3DFUR IT운영팀||tripTarget:장소테스트||tripPurp:(국출)목적테스트||startDate:2015-09-03||endDate:2015-09-04||account:213211167098||compCar:N||tAmt1:1332||tAmt2:0||tAmt3:0||dayAmt:         50000||nightAmt:         40000||docNo:CMAS-2015-000023||ordDate:||ordNo:||agencyUser:||trRecvUser:||trAmount:||trAccount:||, signUserId=1202564, orgCd=1DFUR

			// 업무에 해당하는 call 처리 서비스를 각 업무 패키지에 구현하여 여기서는 서비스 메서드만을 호출 하게 한다.
			// 여기는 업무테이블의 결재필드 결재id와 상태값을 업데이트 하기 위한 서비스 호출이다.

//			1DFUR^1202429^1^T01##1DFUR^1202564^2^T02

//			String signInfo = (String)paramMap.get("signInfo");
//
//			Map<String, Object> signMap = new HashMap<String, Object>();
//
//			// string 이 비어 있지 않다면
//			if (!signInfo.isEmpty()) {
//				// legacy info 분해 작업을 시작한다.
//				String signAry[] = signInfo.split("\\#\\#");
//				// legacy info 는 key:value||key:value 의 형태로 전달되기때문에 || 를 기준으로 split
//				// 한것을 : 으로 다시 split 하여 map 에 값을 넣는다.
//				// value 가 key:value^value 로 연결된 1차월 배열 또는
//				// key:value^value##value^value 이런 형태로연결된 2차원 배열의 경우는 각 업무의
//				// 서비스단에서 처리 하도록 한다.
//				for (int i = 0; i < signAry.length; i++) {
//					String signSubAry[] = signAry[i].split("^");
//					signMap.put("apperOrgCd", signSubAry[0]);
//					signMap.put("signUserId", signSubAry[1]);
//					signMap.put("signSeq", signSubAry[2]);
//					signMap.put("signTpCd", signSubAry[3]);
//				}
//			}


			int resultCmas = oService.updateOuterTripInfo(paramMap);
			int resultSign = oService.updateSignDt(paramMap);

//			if(resultCmas == 0) callBackResult = false;
//			if(resultSign == 0) callBackResult = false;

			// callback 처리중 에러 발생시
//			 callBackResult = false;
			if (!callBackResult) {
				// 실패시 FAILURE
				// 성공시 SUCCESS
				resultMap.put("TYPE", "FAILURE");
				// 메세지 처리를 원한다면 메세지 코드를 삽입
				resultMap.put("MSG_CODE", "처리에 실패하였습니다.");
				// 메세지 코드가 인자를 받아 처리하게 되어있다면 인자를 배열로 전달
				// resultMap.put("MSG_VALUES", new String[]{"arg1",
				// "arg2"...."argN"});
			} else {
				// 성공시 SUCCESS
				resultMap.put("TYPE", "SUCCESS");
			}

			// 신청서 여부 : Y 신청서 / N 정산서
//			resultMap.put("adjustYn", (String)paramMap.get("adjustYn"));

			// 회계승인
			if(docStsCd.equals("D03")){
				resultMap.put("acctSts", "Y");
				resultMap.put("confirm", "Y");
				resultMap.put("refNo", (String)paramMap.get("refNo"));
				resultMap.put("docNo", (String)paramMap.get("docNo"));
				String userId = (String)paramMap.get("drafterId");
				resultMap.put("userId", userId.split(" ")[0]);
			} else if(docStsCd.equals("D04")){
				resultMap.put("acctSts", "");
				resultMap.put("confirm", "N");
				resultMap.put("refNo", (String)paramMap.get("refNo"));
				resultMap.put("docNo", (String)paramMap.get("docNo"));
				String userId = (String)paramMap.get("drafterId");
				resultMap.put("userId", userId.split(" ")[0]);
			}
		}

		if("SGNS070005".equals(programCode)){ // 정산서

//			signDocTitle=국내출장신청서, signId=SGNS-2015-000215, dutyLnkCd=SGNS070002, instanceId=104, programCode=SGNS070002, clbkPath=http://172.21.18.232:8081/trip/innerTrip/CallBackServlet.xpl, process=http://172.21.18.232:8081/trip/innerTrip/CallBackServlet.xpl, signInfo=1DFUR^1202429^1^T01##1DFUR^1202564^2^T02, curSiteCd=1DFUR, signSeq=2, docStsCd=D03, userId=1202429, dutySysCd=SGNS, legacyInfo=refNo:IO20153DFUR0032||tripUserId:1202429 강혜성||tripUserOrg:1DFUR IT운영팀||drafterId:1202429 강혜성||drafterOrg:1DFUR IT운영팀||bdgtType:I. 일반경비||aufnrNo:000010012300||execTeam:3DFUR IT운영팀||tripTarget:장소테스트||tripPurp:(국출)목적테스트||startDate:2015-09-03||endDate:2015-09-04||account:213211167098||compCar:N||tAmt1:1332||tAmt2:0||tAmt3:0||dayAmt:         50000||nightAmt:         40000||docNo:CMAS-2015-000023||ordDate:||ordNo:||agencyUser:||trRecvUser:||trAmount:||trAccount:||, signUserId=1202564, orgCd=1DFUR

			// 업무에 해당하는 call 처리 서비스를 각 업무 패키지에 구현하여 여기서는 서비스 메서드만을 호출 하게 한다.
			// 여기는 업무테이블의 결재필드 결재id와 상태값을 업데이트 하기 위한 서비스 호출이다.

//			1DFUR^1202429^1^T01##1DFUR^1202564^2^T02

//			String signInfo = (String)paramMap.get("signInfo");
//
//			Map<String, Object> signMap = new HashMap<String, Object>();
//
//			// string 이 비어 있지 않다면
//			if (!signInfo.isEmpty()) {
//				// legacy info 분해 작업을 시작한다.
//				String signAry[] = signInfo.split("\\#\\#");
//				// legacy info 는 key:value||key:value 의 형태로 전달되기때문에 || 를 기준으로 split
//				// 한것을 : 으로 다시 split 하여 map 에 값을 넣는다.
//				// value 가 key:value^value 로 연결된 1차월 배열 또는
//				// key:value^value##value^value 이런 형태로연결된 2차원 배열의 경우는 각 업무의
//				// 서비스단에서 처리 하도록 한다.
//				for (int i = 0; i < signAry.length; i++) {
//					String signSubAry[] = signAry[i].split("^");
//					signMap.put("apperOrgCd", signSubAry[0]);
//					signMap.put("signUserId", signSubAry[1]);
//					signMap.put("signSeq", signSubAry[2]);
//					signMap.put("signTpCd", signSubAry[3]);
//				}
//			}


			int resultCmas = oService.updateAdjustOuterTripInfo(paramMap);
			int resultSign = oService.updateSignDt(paramMap);

//			if(resultCmas == 0) callBackResult = false;
//			if(resultSign == 0) callBackResult = false;

			// callback 처리중 에러 발생시
//			 callBackResult = false;
			if (!callBackResult) {
				// 실패시 FAILURE
				// 성공시 SUCCESS
				resultMap.put("TYPE", "FAILURE");
				// 메세지 처리를 원한다면 메세지 코드를 삽입
				resultMap.put("MSG_CODE", "처리에 실패하였습니다.");
				// 메세지 코드가 인자를 받아 처리하게 되어있다면 인자를 배열로 전달
				// resultMap.put("MSG_VALUES", new String[]{"arg1",
				// "arg2"...."argN"});
			} else {
				// 성공시 SUCCESS
				resultMap.put("TYPE", "SUCCESS");
			}

			// 신청서 여부 : Y 신청서 / N 정산서
//			resultMap.put("adjustYn", (String)paramMap.get("adjustYn"));

			// 회계승인
			if(docStsCd.equals("D03")){
				resultMap.put("acctSts", "Y");
				resultMap.put("confirm", "Y");
				resultMap.put("refNo", (String)paramMap.get("refNo"));
				resultMap.put("docNo", (String)paramMap.get("docNo"));
				String userId = (String)paramMap.get("drafterId");
				resultMap.put("userId", userId.split(" ")[0]);
			} else if(docStsCd.equals("D04")){
				resultMap.put("acctSts", "");
				resultMap.put("confirm", "N");
				resultMap.put("refNo", (String)paramMap.get("refNo"));
				resultMap.put("docNo", (String)paramMap.get("docNo"));
				String userId = (String)paramMap.get("drafterId");
				resultMap.put("userId", userId.split(" ")[0]);
			}
		}

//		resultMap.put("TYPE", "FAILURE");
//		// 메세지 처리를 원한다면 메세지 코드를 삽입
//		resultMap.put("MSG_CODE", "co.err.sendFailSlip");




		return resultMap;
	}


}

