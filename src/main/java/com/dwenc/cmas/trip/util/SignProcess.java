package com.dwenc.cmas.trip.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwenc.cmas.common.file.service.FileService;
import com.dwenc.cmas.common.utils.HttpSend;
import com.dwenc.cmas.trip.service.OuterTripService;


import docfbaro.query.CommonDao;
import docfbaro.iam.UserInfo;
import docfbaro.sua.exception.BusinessException;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
import java.util.HashMap;

/**
 * @author 1204594
 *
 *         To change this generated comment edit the template variable
 *         "typecomment": Window>Preferences>Java>Templates. To enable and
 *         disable the creation of type comments go to
 *         Window>Preferences>Java>Code Generation.
 */

@Service
public class SignProcess {

	@Autowired
	private OuterTripService oService;

	@Autowired FileService fService;

	@Autowired
	@Qualifier("mainDB")
	private CommonDao commonDao;

	//비자신청(VisaAppnController)에서 호출

	public Map<String, Object> signVisaRequest(Map<String, Object> map) throws Exception{
		Map<String, Object> rsl = new HashMap<String, Object>();
		rsl = new HttpSend().invokeURL((String)map.get("invokeURL"), map);
		return rsl;
	}

	//국내출장 (InnerTripController)에서 호출
	public Map<String, Object> signRequest(Map<String, Object> pMap, List<HashMap> pSignInfo)
			throws Exception {
		boolean result = false;
		int size = 1;

		System.out.println("pSignInfo size : " + pSignInfo.size());

		for (int i = 0; i < pSignInfo.size(); i++) {

			HashMap<String, Object> tempSign = pSignInfo.get(i);
			System.out.println("index : " + i);
			System.out.println("tempSign : " + tempSign.get("signUserId"));
		}


		String isOfficer = (String) pMap.get("isOfficer");
		String isOrgBoss = (String) pMap.get("isOrgBoss");

		String p_userId;
		String p_orgCd;

/*		if (isOfficer.equals("Y") || isOrgBoss.equals("Y")) {
			// 임원일 경우에는 전결처리를 위해 기안자를 출장자로 셋팅한다.
			p_userId = (String) pMap.get("tripUser"); // 작성자 ID
			p_orgCd = (String) pMap.get("tripUserTeam"); // 작성자 ORG

		} else {*/
			p_userId = UserInfo.getLoginId(); // 작성자 ID
			p_orgCd = UserInfo.getOrgCd(); // 작성자 ORG
/*		}*/

		String p_signInfo = p_orgCd + "^" + p_userId + "^1^T01"; // 상신자
		String p_teamJang = "";

		String isSignEdit = (String)pMap.get("isSignEdit");

		// 결재자 지정 버튼을 통해 변경된 결재선일 경우 해외 출장과 같이 전송된 ds_Signln 을 이용해 결재선 정보를 생성한다.
		if(isSignEdit.equals("Y")){

			System.out.println("결재선 지정!!");

			// 해외출장의 경우 ds_Signln 정보로 signInfo 를 작성한다.
//			for (int i = 1; i < pSignInfo.size(); i++) {
//
//				HashMap<String, Object> tempSign = pSignInfo.get(i);
//
//				size++;
//				p_signInfo = p_signInfo + "##"
//						+ (String) tempSign.get("apperOrgCd") + "^"
//						+ (String) tempSign.get("signUserId") + "^" + size + "^" + (String) tempSign.get("signTpCd");
//
//			}


			// 기안자
			HashMap<String, Object> firstSign = pSignInfo.get(0);

			p_userId = (String) firstSign.get("signUserId"); // 작성자 ID
			p_orgCd = (String) firstSign.get("apperOrgCd"); // 작성자 ORG
			p_signInfo = p_orgCd + "^" + p_userId + "^1^T01"; // 상신자

			// 해외출장의 경우 ds_Signln 정보로 signInfo 를 작성한다.
			for (int i = 1; i < pSignInfo.size(); i++) {

				HashMap<String, Object> tempSign = pSignInfo.get(i);

				size++;
				p_signInfo = p_signInfo + "##"
						+ (String) tempSign.get("apperOrgCd") + "^"
						+ (String) tempSign.get("signUserId") + "^" + size + "^" + (String) tempSign.get("signTpCd");

			}

		}else{

			System.out.println("결재선 미지정!!");

			// 국내출장 결재 프로세스
			// // 로그인한 사람 (세션에서 얻어옴)
			// 상신자(작성자)
			//
			// // 집행팀이 다를 경우
			// --> 타집행팀팀장 협의 삽입
			//
			// // 현장소속일 경우
			// --> 현장관리책임자 중간결재자
			// --> 현장소장 최종결재자
			//
			// // 현장소속 아닐 경우
			// --> 작성자의 팀장이 최종결재자

			//T01:기안, T02:결재, T03:협의(병렬협의), T04:기안자전결, T05:전결, T06:협의(순차협의)

			// 타 집행팀일 경우 팀장/소장 합의 필요
			String dSignType = (String) pMap.get("dSignType");
			String bdgtCd = (String) pMap.get("bdgtCd"); // 경비구분코드
			if (dSignType.equals("Y")) {
				if (bdgtCd.equals("Q")) {
					// 타집행팀이면서 현장예산일 경우 현장관리책임자가 추가로 삽입된다.
					String dHSignUserId = (String) pMap.get("dHSignUserId");
					if (!dHSignUserId.equals("")) {
						size++;
						p_signInfo = p_signInfo + "##"
								+ (String) pMap.get("dHSignUserCd") + "^"
								+ (String) pMap.get("dHSignUserId") + "^" + size
								+ "^T03";
					}
				}

				String dSignUserId = (String) pMap.get("dSignUserId");
				if (!dSignUserId.equals("")) {
					size++;
					p_signInfo = p_signInfo + "##"
							+ (String) pMap.get("dSignUserCd") + "^"
							+ (String) pMap.get("dSignUserId") + "^" + size
							+ "^T03";
				}

			}

			if (isOfficer.equals("Y") || isOrgBoss.equals("Y")) {

				// 팀장/현장소장 이상 전결
				// 최종 팀장 소장 결재
				size++;
				p_signInfo = p_signInfo + "##" + (String) pMap.get("tSignUserCd")
						+ "^" + (String) pMap.get("tSignUserId") + "^" + size
						+ "^T02";

			} else {

				// 현장소속일 경우 현장책임자 결재 필요
				String hSignType = (String) pMap.get("hSignType");
				if (hSignType.equals("Y")) {
					size++;
					p_signInfo = p_signInfo + "##"
							+ (String) pMap.get("hSignUserCd") + "^"
							+ (String) pMap.get("hSignUserId") + "^" + size
							+ "^T02";
				}

				// 최종 팀장 소장 결재
				size++;
				p_signInfo = p_signInfo + "##" + (String) pMap.get("tSignUserCd")
						+ "^" + (String) pMap.get("tSignUserId") + "^" + size
						+ "^T02";
			}

		}


		Map map = new HashMap();
		map.put("dutySysCd", (String) pMap.get("dutySysCd"));
		map.put("programCode", (String) pMap.get("programCode"));
		// map.put("signDocTitle", SgnsURLEncoder.encode( signDocTitle ,
		// "utf-8"));
		map.put("signDocTitle",
				URLEncoder.encode(
						(String) pMap.get("signDocTitle") + " ("
								+ (String) pMap.get("tripUserId") + " :  "
								+ (String) pMap.get("startDate") + "~"
								+ (String) pMap.get("endDate") + ")", "utf-8"));
		// URL Encode 해줄것

		Map rsl;

		String serverType = (String) pMap.get("serverName");
		System.out.println("serverType : " + serverType);

		// Callback 입력
		if (serverType.equals("iworks.daewooenc.com")) {
			map.put("process",
					"http://iworks.daewooenc.com/cmas/trip/innerTrip/CallBackServlet.xpl"); // 운영
		} else if (serverType.equals("test.daewooenc.com")) {
			map.put("process",
					"http://test.daewooenc.com/cmas/trip/innerTrip/CallBackServlet.xpl"); // 개발
		} else {
			map.put("process",
					"http://test.daewooenc.com/cmas/trip/innerTrip/CallBackServlet.xpl"); // 개발
		}

		// legacyInfo 파라미터 셋팅
		String refNo = (String) pMap.get("refNo"); // SAP전표번호
		String bdgtType = (String) pMap.get("bdgtType"); // 경비구분
		String tripUserId = (String) pMap.get("tripUserId"); // 출장자ID
		String tripUserOrg = (String) pMap.get("tripUserOrg"); // 출장자소속팀
		String drafterId = (String) pMap.get("drafterId"); // 작성자
		String drafterOrg = (String) pMap.get("drafterOrg"); // 작성자소속팀
		String aufnrNo = (String) pMap.get("aufnrNo"); // 예산번호
		String execTeam = (String) pMap.get("execTeam"); // 집행팀
		String tripTarget = (String) pMap.get("tripTarget"); // 출장장소
		tripTarget = tripTarget.replace("&", " ");

		// String tripPurp = URLEncoder.encode((String)pMap.get("tripPurp"),
		// "utf-8");
		String tripPurp = (String) pMap.get("tripPurp"); // 출장목적
		tripPurp = tripPurp.replace("&", " ");
		tripPurp = tripPurp.substring(tripPurp.indexOf("(국출)") + 4);

		// System.out.println(tripPurp);

		String startDate = (String) pMap.get("startDate"); // 시작일
		String endDate = (String) pMap.get("endDate"); // 종료일
		String account = (String) pMap.get("account"); // 송금계좌
		String compCar = (String) pMap.get("compCar"); // 회사차량사용여부 (Y 사용, N 미사용)
		String tAmt1 = (String) pMap.get("tAmt1"); // 항공료합계
		String tAmt2 = (String) pMap.get("tAmt2"); // 기차+선박+고속버스 합계
		String tAmt3 = (String) pMap.get("tAmt3"); // 대중교통 합계
		String dayAmt = (String) pMap.get("dayAmt"); // 일당비
		String nightAmt = (String) pMap.get("nightAmt"); // 숙박비
		String docNo = (String) pMap.get("docNo"); // 문서번호
		String ordDate = (String) pMap.get("ordDate"); // 기표일자
		String ordNo = (String) pMap.get("ordNo"); // 전표번호
		String agencyUser = (String) pMap.get("agencyUser"); // 업무대행자
		String trRecvUser = (String) pMap.get("trRecvUser"); // 교통비수령인
		String trAmount = (String) pMap.get("trAmount"); // 교통비수령금액
		String trAccount = (String) pMap.get("trAccount"); // 교통비수령계좌

		String p_legacyInfo = "refNo:" + refNo + "||" + "tripUserId:"
				+ tripUserId + "||" + "tripUserOrg:" + tripUserOrg + "||"
				+ "drafterId:" + drafterId + "||" + "drafterOrg:" + drafterOrg
				+ "||" + "bdgtType:" + bdgtType + "||" + "aufnrNo:" + aufnrNo
				+ "||" + "execTeam:" + execTeam + "||" + "tripTarget:"
				+ tripTarget + "||" + "tripPurp:" + tripPurp + "||"
				+ "startDate:" + startDate + "||" + "endDate:" + endDate + "||"
				+ "account:" + account + "||" + "compCar:" + compCar + "||"
				+ "tAmt1:" + tAmt1 + "||" + "tAmt2:" + tAmt2 + "||" + "tAmt3:"
				+ tAmt3 + "||" + "dayAmt:" + dayAmt + "||" + "nightAmt:"
				+ nightAmt + "||" + "docNo:" + docNo + "||" + "ordDate:"
				+ ordDate + "||" + "ordNo:" + ordNo + "||" + "agencyUser:"
				+ agencyUser + "||" + "trRecvUser:" + trRecvUser + "||"
				+ "trAmount:" + trAmount + "||" + "trAccount:" + trAccount
				+ "||";

		map.put("userId", p_userId);
		map.put("orgCd", p_orgCd);
		map.put("legacyInfo", URLEncoder.encode(p_legacyInfo, "utf-8")); // SGNS
																			// 에서
																			// 받아야되는
																			// 필요한
																			// 파라미터는
																			// legacyInfo
																			// 에
																			// 추가하여
																			// 넣을것
		map.put("signInfo", p_signInfo);

		//2020-10-16 결재문서 파일 첨부 추가
		// 파일 정보 가져와서 넘김
		//1^첨부파일.pdf^13421^sgns/2013/0408/aaaaa.pdf||2^첨부파일2.pdf^131^sgns/2013/0408/bbbbb.pdf
		//fileSeq^fileNm^fileSize^filePath||

		List<Map<String, Object>> fileList = fService.retrieveFilesListInfo(pMap);

		String p_attachInfo = "";

		if(fileList.size() > 0){
			for(int i = 0; i < fileList.size(); i++){
				Map<String, Object> fMap = fileList.get(i);

				System.out.println((String)fMap.get("fileNm"));
				System.out.println((String)fMap.get("fileSize"));
				System.out.println((String)fMap.get("filePath"));
				if(i == 0){
					p_attachInfo = "1"
							+ "^" + (String)fMap.get("fileNm")
							+ "^" + (String)fMap.get("fileSize")
							+ "^" + (String)fMap.get("filePath");
				}else if(i > 0){
					p_attachInfo = p_attachInfo + "##" + (i+1)
							+ "^" + (String)fMap.get("fileNm")
							+ "^" + (String)fMap.get("fileSize")
							+ "^" + (String)fMap.get("filePath");
				}
			}
		}
		System.out.println(p_attachInfo);

		map.put("attachInfo",  URLEncoder.encode(p_attachInfo, "utf-8"));

		if (serverType.equals("iworks.daewooenc.com")) {
			rsl = com.dwenc.cmas.common.utils.HttpSend.invokeURL(
					"http://iworks.daewooenc.com/sgns/sn/sign/remoteDraft.xpl",
					map);
		} else {
			rsl = com.dwenc.cmas.common.utils.HttpSend
					.invokeURL(
							"http://test.daewooenc.com/sgns/sn/sign/remoteDraft.xpl",
							map);
		}

		Map<String, Object> rslMap = new HashMap<String, Object>();

		System.out.println("rsl ==> " + rsl);
		System.out.println("map ==> " + map);
		if (((String) rsl.get("TYPE")).equals("SUCCESS")) {
			result = true;
			rslMap.put("signId", (String) rsl.get("signId"));
			rslMap.put("signInfo", p_signInfo);
			rslMap.put("type", (String) rsl.get("TYPE"));
			// request.setAttribute("signId", (String)rsl.get("signId"));
		}else{
			rslMap.put("type", (String) rsl.get("TYPE"));
		}

		rslMap.put("result", result);

		return rslMap;
	}

	//시내교통비에서 호출
	public Map<String, Object> signCityRequest(Map<String, Object> pMap, List<HashMap> pSignInfo)
			throws Exception {
		boolean result = false;
		int size = 1;

		System.out.println("pSignInfo size : " + pSignInfo.size());

		for (int i = 0; i < pSignInfo.size(); i++) {

			HashMap<String, Object> tempSign = pSignInfo.get(i);
			System.out.println("index : " + i);
			System.out.println("tempSign : " + tempSign.get("signUserId"));
		}


		String isOfficer = (String) pMap.get("isOfficer");
		String isOrgBoss = (String) pMap.get("isOrgBoss");

		String p_userId;
		String p_orgCd;

		if (isOfficer.equals("Y") || isOrgBoss.equals("Y")) {
			// 임원일 경우에는 전결처리를 위해 기안자를 출장자로 셋팅한다.
			p_userId = (String) pMap.get("tSignUserId"); // 작성자 ID
			p_orgCd = (String) pMap.get("tSignUserCd"); // 작성자 ORG

		} else {
			p_userId = UserInfo.getLoginId(); // 작성자 ID
			p_orgCd = UserInfo.getOrgCd(); // 작성자 ORG
		}

		String p_signInfo = p_orgCd + "^" + p_userId + "^1^T01"; // 상신자
		String p_teamJang = "";

		String isSignEdit = (String)pMap.get("isSignEdit");

		// 결재자 지정 버튼을 통해 변경된 결재선일 경우 해외 출장과 같이 전송된 ds_Signln 을 이용해 결재선 정보를 생성한다.
		if(isSignEdit.equals("Y")){

			System.out.println("결재선 지정!!");

			// 해외출장의 경우 ds_Signln 정보로 signInfo 를 작성한다.
//			for (int i = 1; i < pSignInfo.size(); i++) {
//
//				HashMap<String, Object> tempSign = pSignInfo.get(i);
//
//				size++;
//				p_signInfo = p_signInfo + "##"
//						+ (String) tempSign.get("apperOrgCd") + "^"
//						+ (String) tempSign.get("signUserId") + "^" + size + "^" + (String) tempSign.get("signTpCd");
//
//			}


			// 기안자
			HashMap<String, Object> firstSign = pSignInfo.get(0);

			p_userId = (String) firstSign.get("signUserId"); // 작성자 ID
			p_orgCd = (String) firstSign.get("apperOrgCd"); // 작성자 ORG
			p_signInfo = p_orgCd + "^" + p_userId + "^1^T01"; // 상신자

			// 해외출장의 경우 ds_Signln 정보로 signInfo 를 작성한다.
			for (int i = 1; i < pSignInfo.size(); i++) {

				HashMap<String, Object> tempSign = pSignInfo.get(i);

				size++;
				p_signInfo = p_signInfo + "##"
						+ (String) tempSign.get("apperOrgCd") + "^"
						+ (String) tempSign.get("signUserId") + "^" + size + "^" + (String) tempSign.get("signTpCd");

			}

		}else{

			System.out.println("결재선 미지정!!");

			// 국내출장 결재 프로세스
			// // 로그인한 사람 (세션에서 얻어옴)
			// 상신자(작성자)
			//
			// // 집행팀이 다를 경우
			// --> 타집행팀팀장 협의 삽입
			//
			// // 현장소속일 경우
			// --> 현장관리책임자 중간결재자
			// --> 현장소장 최종결재자
			//
			// // 현장소속 아닐 경우
			// --> 작성자의 팀장이 최종결재자

			// 타 집행팀일 경우 팀장/소장 합의 필요
			String dSignType = (String) pMap.get("dSignType");
			String bdgtCd = (String) pMap.get("bdgtCd"); // 경비구분코드

			if (isOfficer.equals("Y") || isOrgBoss.equals("Y")) {

				// 팀장/현장소장 이상 전결
				// 최종 팀장 소장 결재
				size++;
				p_signInfo = p_signInfo + "##" + (String) pMap.get("tSignUserCd")
						+ "^" + (String) pMap.get("tSignUserId") + "^" + size
						+ "^T02";

			} else {

				if (dSignType.equals("Y")) {
					if (bdgtCd.equals("Q")) {
						// 타집행팀이면서 현장예산일 경우 현장관리책임자가 추가로 삽입된다.
						String dHSignUserId = (String) pMap.get("dHSignUserId");
						if (!dHSignUserId.equals("")) {
							size++;
							p_signInfo = p_signInfo + "##"
									+ (String) pMap.get("dHSignUserCd") + "^"
									+ (String) pMap.get("dHSignUserId") + "^"
									+ size + "^T02";
						}
					}

					String dSignUserId = (String) pMap.get("dSignUserId");
					if (!dSignUserId.equals("")) {
						size++;
						p_signInfo = p_signInfo + "##"
								+ (String) pMap.get("dSignUserCd") + "^"
								+ (String) pMap.get("dSignUserId") + "^" + size
								+ "^T02";
					}

				} else {
					// 현장소속일 경우 현장책임자 결재 필요
					String hSignType = (String) pMap.get("hSignType");
					if (hSignType.equals("Y")) {
						size++;
						p_signInfo = p_signInfo + "##"
								+ (String) pMap.get("hSignUserCd") + "^"
								+ (String) pMap.get("hSignUserId") + "^" + size
								+ "^T02";
					}

					// 최종 팀장 소장 결재
					size++;
					p_signInfo = p_signInfo + "##"
							+ (String) pMap.get("tSignUserCd") + "^"
							+ (String) pMap.get("tSignUserId") + "^" + size
							+ "^T02";

				}

			}

		}

		// 기존소스
//		String isOfficer = (String) pMap.get("isOfficer");
//		String isOrgBoss = (String) pMap.get("isOrgBoss");
//
//		String p_userId;
//		String p_orgCd;
//
//		if (isOfficer.equals("Y") || isOrgBoss.equals("Y")) {
//			// 임원일 경우에는 전결처리를 위해 기안자를 출장자로 셋팅한다.
//			p_userId = (String) pMap.get("drUser"); // 작성자 ID
//			p_orgCd = (String) pMap.get("drUserCd"); // 작성자 ORG
//
//		} else {
//			p_userId = UserInfo.getLoginId(); // 작성자 ID
//			p_orgCd = UserInfo.getOrgCd(); // 작성자 ORG
//		}
//
//		String p_signInfo = p_orgCd + "^" + p_userId + "^1^T01"; // 상신자
//		String p_teamJang = "";
//
//		// 국내출장 결재 프로세스
//		// // 로그인한 사람 (세션에서 얻어옴)
//		// 상신자(작성자)
//		//
//		// // 집행팀이 다를 경우
//		// --> 타집행팀팀장 협의 삽입
//		//
//		// // 현장소속일 경우
//		// --> 현장관리책임자 중간결재자
//		// --> 현장소장 최종결재자
//		//
//		// // 현장소속 아닐 경우
//		// --> 작성자의 팀장이 최종결재자
//
//		// 타 집행팀일 경우 팀장/소장 합의 필요
//		String dSignType = (String) pMap.get("dSignType");
//		String bdgtCd = (String) pMap.get("bdgtCd"); // 경비구분코드
//
//		if (isOfficer.equals("Y") || isOrgBoss.equals("Y")) {
//
//			// 팀장/현장소장 이상 전결
//			// 최종 팀장 소장 결재
//			size++;
//			p_signInfo = p_signInfo + "##" + (String) pMap.get("tSignUserCd")
//					+ "^" + (String) pMap.get("tSignUserId") + "^" + size
//					+ "^T02";
//
//		} else {
//
//			if (dSignType.equals("Y")) {
//				if (bdgtCd.equals("Q")) {
//					// 타집행팀이면서 현장예산일 경우 현장관리책임자가 추가로 삽입된다.
//					String dHSignUserId = (String) pMap.get("dHSignUserId");
//					if (!dHSignUserId.equals("")) {
//						size++;
//						p_signInfo = p_signInfo + "##"
//								+ (String) pMap.get("dHSignUserCd") + "^"
//								+ (String) pMap.get("dHSignUserId") + "^"
//								+ size + "^T02";
//					}
//				}
//
//				String dSignUserId = (String) pMap.get("dSignUserId");
//				if (!dSignUserId.equals("")) {
//					size++;
//					p_signInfo = p_signInfo + "##"
//							+ (String) pMap.get("dSignUserCd") + "^"
//							+ (String) pMap.get("dSignUserId") + "^" + size
//							+ "^T02";
//				}
//
//			} else {
//				// 현장소속일 경우 현장책임자 결재 필요
//				String hSignType = (String) pMap.get("hSignType");
//				if (hSignType.equals("Y")) {
//					size++;
//					p_signInfo = p_signInfo + "##"
//							+ (String) pMap.get("hSignUserCd") + "^"
//							+ (String) pMap.get("hSignUserId") + "^" + size
//							+ "^T02";
//				}
//
//				// 최종 팀장 소장 결재
//				size++;
//				p_signInfo = p_signInfo + "##"
//						+ (String) pMap.get("tSignUserCd") + "^"
//						+ (String) pMap.get("tSignUserId") + "^" + size
//						+ "^T02";
//
//			}
//
//		}
		// end of 기존소스

		Map map = new HashMap();
		map.put("dutySysCd", (String) pMap.get("dutySysCd"));
		map.put("programCode", (String) pMap.get("programCode"));
		// map.put("signDocTitle", SgnsURLEncoder.encode( signDocTitle ,
		// "utf-8"));
		map.put("signDocTitle",
				URLEncoder.encode((String) pMap.get("signDocTitle"), "utf-8"));
		// URL Encode 해줄것

		Map rsl;

		String serverType = (String) pMap.get("serverName");
		System.out.println("serverType : " + serverType);


		//2020-11-19 결재문서 파일 첨부 추가(시작)
		// 파일 정보 가져와서 넘김
		//1^첨부파일.pdf^13421^sgns/2013/0408/aaaaa.pdf||2^첨부파일2.pdf^131^sgns/2013/0408/bbbbb.pdf
		//fileSeq^fileNm^fileSize^filePath||

		List<Map<String, Object>> fileList = fService.retrieveFilesListInfo(pMap);

		String p_attachInfo = "";

		if(fileList.size() > 0){
			for(int i = 0; i < fileList.size(); i++){
				Map<String, Object> fMap = fileList.get(i);

				System.out.println((String)fMap.get("fileNm"));
				System.out.println((String)fMap.get("fileSize"));
				System.out.println((String)fMap.get("filePath"));
				if(i == 0){
					p_attachInfo = "1"
							+ "^" + (String)fMap.get("fileNm")
							+ "^" + (String)fMap.get("fileSize")
							+ "^" + (String)fMap.get("filePath");
				}else if(i > 0){
					p_attachInfo = p_attachInfo + "##" + (i+1)
							+ "^" + (String)fMap.get("fileNm")
							+ "^" + (String)fMap.get("fileSize")
							+ "^" + (String)fMap.get("filePath");
				}
			}
		}
		System.out.println(p_attachInfo);

		map.put("attachInfo",  URLEncoder.encode(p_attachInfo, "utf-8"));
		//결재문서 파일 첨부 추가(종료)

		// Callback 입력
		if (serverType.equals("iworks.daewooenc.com")) {
			map.put("process",
					"http://iworks.daewooenc.com/cmas/trip/cityTransp/CallBackServlet.xpl"); // 운영
		} else if (serverType.equals("test.daewooenc.com")) {
			map.put("process",
					"http://test.daewooenc.com/cmas/trip/cityTransp/CallBackServlet.xpl"); // 개발
		} else {
			map.put("process",
					"http://test.daewooenc.com/cmas/trip/cityTransp/CallBackServlet.xpl"); // 개발

		}

		// legacyInfo 파라미터 셋팅
		String refNo = (String) pMap.get("refNo"); // SAP전표번호
		String drafterId = (String) pMap.get("drafterId"); // 작성자
		String drafterOrg = (String) pMap.get("drafterOrg"); // 작성자소속팀
		String bdgtType = (String) pMap.get("bdgtType"); // 경비구분
		String aufnrNo = (String) pMap.get("aufnrNo"); // 예산번호
		String execTeam = (String) pMap.get("execTeam"); // 집행팀
		String docNo = (String) pMap.get("docNo"); // 문서번호
		String ordDate = (String) pMap.get("ordDate"); // 기표일자
		String ordNo = (String) pMap.get("ordNo"); // 전표번호

		String p_legacyInfo = "refNo:" + refNo + "||" + "drafterId:"
				+ drafterId + "||" + "drafterOrg:" + drafterOrg + "||"
				+ "bdgtType:" + bdgtType + "||" + "aufnrNo:" + aufnrNo + "||"
				+ "execTeam:" + execTeam + "||" + "docNo:" + docNo + "||"
				+ "ordDate:" + ordDate + "||" + "ordNo:" + ordNo + "||";

		map.put("userId", p_userId);
		map.put("orgCd", p_orgCd);
		map.put("legacyInfo", URLEncoder.encode(p_legacyInfo, "utf-8")); // SGNS에서 받아야되는 필요한 파라미터는 legacyInfo에 추가하여 넣을것
		map.put("signInfo", p_signInfo);

		if (serverType.equals("iworks.daewooenc.com")) {
			rsl = com.dwenc.cmas.common.utils.HttpSend.invokeURL(
					"http://iworks.daewooenc.com/sgns/sn/sign/remoteDraft.xpl",
					map);
		} else {
			rsl = com.dwenc.cmas.common.utils.HttpSend
					.invokeURL(
							"http://test.daewooenc.com/sgns/sn/sign/remoteDraft.xpl",
							map);
		}

		Map<String, Object> rslMap = new HashMap<String, Object>();

		System.out.println("rsl ==> " + rsl);
		System.out.println("map ==> " + map);
		if (((String) rsl.get("TYPE")).equals("SUCCESS")) {
			result = true;
			rslMap.put("signId", (String) rsl.get("signId"));
			rslMap.put("signInfo", p_signInfo);
			rslMap.put("type", (String) rsl.get("TYPE"));
			// request.setAttribute("signId", (String)rsl.get("signId"));

		}else{
			rslMap.put("type", (String) rsl.get("TYPE"));
		}

		rslMap.put("result", result);

		return rslMap;
	}

	//해외출장 Request
	public Map<String, Object> signOuterRequest(Map<String, Object> pMap,
			List<HashMap> pSignInfo) throws Exception {
		boolean result = false;
		int size = 1;

		System.out.println("pSignInfo size : " + pSignInfo.size());

		for (int i = 0; i < pSignInfo.size(); i++) {

			HashMap<String, Object> tempSign = pSignInfo.get(i);
			System.out.println("index : " + i);
			System.out.println("tempSign : " + tempSign.get("signUserId"));
		}

		// 기안자
		HashMap<String, Object> firstSign = pSignInfo.get(0);

		String p_userId = (String) firstSign.get("signUserId"); // 작성자 ID
		String p_orgCd = (String) firstSign.get("apperOrgCd"); // 작성자 ORG
		String p_signInfo = p_orgCd + "^" + p_userId + "^1^T01"; // 상신자
		String p_teamJang = "";

		// 해외출장의 경우 ds_Signln 정보로 signInfo 를 작성한다.
		for (int i = 1; i < pSignInfo.size(); i++) {

			HashMap<String, Object> tempSign = pSignInfo.get(i);

			size++;
			p_signInfo = p_signInfo + "##"
					+ (String) tempSign.get("apperOrgCd") + "^"
					+ (String) tempSign.get("signUserId") + "^" + size + "^" + (String) tempSign.get("signTpCd");

		}

		// 최종 팀장 소장 결재
		// size++;
		// p_signInfo = p_signInfo + "##" + (String)pMap.get("tSignUserCd") +
		// "^" + (String)pMap.get("tSignUserId") + "^" + size + "^T02";

		Map map = new HashMap();
		map.put("dutySysCd", (String) pMap.get("dutySysCd"));
		map.put("programCode", (String) pMap.get("programCode"));
		// map.put("signDocTitle", SgnsURLEncoder.encode( signDocTitle ,
		// "utf-8"));
		map.put("signDocTitle",
				URLEncoder.encode((String) pMap.get("signDocTitle"), "utf-8"));
		// URL Encode 해줄것

		Map rsl;

		String serverType = (String) pMap.get("serverName");
		System.out.println("serverType : " + serverType);

		// Callback 입력
		if (serverType.equals("iworks.daewooenc.com")) {
			map.put("process",
					"http://iworks.daewooenc.com/cmas/trip/outerTrip/CallBackServlet.xpl"); // 운영
		} else if (serverType.equals("test.daewooenc.com")) {
			map.put("process",
					"http://test.daewooenc.com/cmas/trip/outerTrip/CallBackServlet.xpl"); // 개발
		} else {
			map.put("process",
					"http://test.daewooenc.com/cmas/trip/outerTrip/CallBackServlet.xpl"); // 로컬..
		}

		// legacyInfo 파라미터 셋팅

		// dutySysCd : dutySysCd,
		// programCode : programCode,
		// signDocTitle : signDocTitle,
		String ordDate = (String) pMap.get("ordDate"); // 기표일자
		String ordNo = (String) pMap.get("ordNo"); // 전표번호
		String adjustYn = (String) pMap.get("adjustYn"); // N 신청서 Y 정산서
		String docNo = (String) pMap.get("docNo"); // 문서번호
		String drafterId = (String) pMap.get("drafterId");
//		String drafter = (String) pMap.get("drafter");
//		String drafterOrg = (String) pMap.get("drafterOrg");
//		String drafterOrgNm = (String) pMap.get("drafterOrgNm");
//		String tripUser = (String) pMap.get("tripUser");
		String tripUserId = (String) pMap.get("tripUserId");
//		String tripUserEnm = (String) pMap.get("tripUserEnm");
//		String tripUserGrade = (String) pMap.get("tripUserGrade");
//		String tripUserAirAmount = (String) pMap.get("tripUserAirAmount");
//		String tripDate = (String) pMap.get("tripDate");
//		String tPurp = (String) pMap.get("tPurp");
//		String tReq = (String) pMap.get("tReq");
//		String fPassp = (String) pMap.get("fPassp");
//		String tPassp = (String) pMap.get("tPassp");
//		String bdgtType = (String) pMap.get("bdgtType");
//		String aufnr = (String) pMap.get("aufnr");
//		String cGubun = (String) pMap.get("cGubun");
//		String bdgtTeam = (String) pMap.get("bdgtTeam");
		String airTotalAmount = (String) pMap.get("airTotalAmount");
//		String pecul = (String) pMap.get("pecul");
//		String loAirFare = (String) pMap.get("loAirFare");
//		String loTranFare = (String) pMap.get("loTranFare");
//		String visaFeeFare = (String) pMap.get("visaFeeFare");
//		String ovCharFare = (String) pMap.get("ovCharFare");
//		String vocLeeFare = (String) pMap.get("vocLeeFare");
//		String hostFare = (String) pMap.get("hostFare");
//		String etcTotal = (String) pMap.get("etcTotal");
//		String usNight = (String) pMap.get("usNight");
//		String usDay = (String) pMap.get("usDay");
//		String edu = (String) pMap.get("edu");
//		String spot = (String) pMap.get("spot");
//		String euNight = (String) pMap.get("euNight");
//		String euDay = (String) pMap.get("euDay");
//		String enNight = (String) pMap.get("enNight");
//		String enDay = (String) pMap.get("enDay");
//		String jaNight = (String) pMap.get("jaNight");
//		String jaDay = (String) pMap.get("jaDay");
		String expnTotal = (String) pMap.get("expnTotal");
		String cTotalAmt = (String) pMap.get("cTotalAmt");
		String totalAmount = (String) pMap.get("totalAmount");
//		String rem = (String) pMap.get("rem");
//		String assistComment = (String) pMap.get("assistComment");
		String refNo = (String) pMap.get("refNo");
		String usd = (String) pMap.get("usd");
		String eur = (String) pMap.get("eur");
		String gbp = (String) pMap.get("gbp");
		String jpy = (String) pMap.get("jpy");
		String vTcode = (String) pMap.get("vTcode");
		String oDate = (String) pMap.get("oDate");
		// 2016-04-20 eHR 반려 처리를 위해 출장 시작 종료일 추가
		String startDate = (String) pMap.get("startDate");
		String endDate = (String) pMap.get("endDate");

		String p_legacyInfo =
		// "refNo:" + refNo + "||" +
				"drafterId:" + drafterId + "||"
				+ "ordDate:" + ordDate + "||"	// 기표일자
				+ "ordNo:" + ordNo+ "||"		// 전표번호
				+ "adjustYn:" + adjustYn + "||"	// N 신청서 Y 정산서
				+ "docNo:" + docNo + "||"		// 문서번호
				+ "tripUserId:" + tripUserId + "||"		// 문서번호
//				"drafterId:" + drafterId + "||"
//				+ "drafter:" + drafter + "||"
//				+ "drafterOrg:" + drafterOrg + "||"
//				+ "drafterOrgNm:" + drafterOrgNm + "||" + "tripUser:" + tripUser + "||"
//				+ "tripUserEnm:" + tripUserEnm + "||" + "tripUserGrade:"
//				+ tripUserGrade + "||" + "tripUserAirAmount:"
//				+ tripUserAirAmount + "||"
//				+ "tripDate:" + tripDate + "||"
//				+ "tPurp:" + tPurp + "||" + "tReq:" + tReq + "||"
//				+ "tReq:" + tReq + "||"
//				+ "fPassp:"	+ fPassp + "||" + "tPassp:" + tPassp + "||"
//				+ "bdgtType:" + bdgtType + "||" + "aufnr:" + aufnr + "||"
//				+ "cGubun:" + cGubun + "||" + "bdgtTeam:" + bdgtTeam + "||"
				+ "airTotalAmount:" + airTotalAmount + "||"
//				+ "pecul:" + pecul + "||"
//				+ "loAirFare:" + loAirFare + "||"
//				+ "loTranFare:" + loTranFare + "||"
//				+ "visaFeeFare:" + visaFeeFare + "||"
//				+ "ovCharFare:" + ovCharFare + "||"
//				+ "vocLeeFare:" + vocLeeFare + "||"
//				+ "hostFare:" + hostFare + "||"
//				+ "etcTotal:" + etcTotal + "||"
//				+ "usNight:" + usNight + "||"
//				+ "usDay:" + usDay + "||"
//				+ "edu:" + edu + "||"
//				+ "spot:" + spot + "||"
//				+ "euNight:" + euNight + "||"
//				+ "euDay:" + euDay + "||"
//				+ "enNight:" + enNight + "||"
//				+ "enDay:" + enDay + "||"
//				+ "jaNight:" + jaNight + "||"
//				+ "jaDay:" + jaDay + "||"
				+ "expnTotal:" + expnTotal + "||"
				+ "cTotalAmt:" + cTotalAmt + "||"
				+ "totalAmount:" + totalAmount + "||"
//				+ "rem:" + rem+ "||"
//				+ "assistComment:" + assistComment+ "||"
				+ "refNo:"+ refNo + "||"
				+ "usd:" + usd + "||"
				+ "eur:" + eur + "||"
				+ "gbp:" + gbp + "||"
				+ "jpy:" + jpy + "||"
				+ "vTcode:" + vTcode + "||"
				+ "oDate:" + oDate + "||"
				+ "startDate:" + startDate + "||"
				+ "endDate:" + endDate + "||"
				;

		//

		map.put("userId", p_userId);
		map.put("orgCd", p_orgCd);
		map.put("legacyInfo", URLEncoder.encode(p_legacyInfo, "utf-8")); // SGNS에서 받아야되는 필요한 파라미터는 legacyInfo에 추가하여 넣을것
		map.put("signInfo", p_signInfo);

		//2015-09-16 결재문서 파일 첨부 추가
		// 파일 정보 가져와서 넘김
		//1^첨부파일.pdf^13421^sgns/2013/0408/aaaaa.pdf||2^첨부파일2.pdf^131^sgns/2013/0408/bbbbb.pdf
		//fileSeq^fileNm^fileSize^filePath||

		List<Map<String, Object>> fileList = fService.retrieveFilesListInfo(pMap);

		String p_attachInfo = "";

		if(fileList.size() > 0){
			for(int i = 0; i < fileList.size(); i++){
				Map<String, Object> fMap = fileList.get(i);

				System.out.println((String)fMap.get("fileNm"));
				System.out.println((String)fMap.get("fileSize"));
				System.out.println((String)fMap.get("filePath"));
				if(i == 0){
					p_attachInfo = "1"
							+ "^" + (String)fMap.get("fileNm")
							+ "^" + (String)fMap.get("fileSize")
							+ "^" + (String)fMap.get("filePath");
				}else if(i > 0){
					p_attachInfo = p_attachInfo + "##" + (i+1)
							+ "^" + (String)fMap.get("fileNm")
							+ "^" + (String)fMap.get("fileSize")
							+ "^" + (String)fMap.get("filePath");
				}
			}
		}
		System.out.println(p_attachInfo);

		map.put("attachInfo",  URLEncoder.encode(p_attachInfo, "utf-8"));




		if (serverType.equals("iworks.daewooenc.com")) {
			rsl = com.dwenc.cmas.common.utils.HttpSend.invokeURL(
					"http://iworks.daewooenc.com/sgns/sn/sign/remoteDraft.xpl",
					map);
		} else {
			rsl = com.dwenc.cmas.common.utils.HttpSend
					.invokeURL(
							"http://test.daewooenc.com/sgns/sn/sign/remoteDraft.xpl",
							map);
		}

		Map<String, Object> rslMap = new HashMap<String, Object>();

		System.out.println("rsl ==> " + rsl);
		System.out.println("map ==> " + map);
		if (((String) rsl.get("TYPE")).equals("SUCCESS")) {
			result = true;
			rslMap.put("signId", (String) rsl.get("signId"));
			rslMap.put("signInfo", p_signInfo);
			rslMap.put("type", (String) rsl.get("TYPE"));
			// request.setAttribute("signId", (String)rsl.get("signId"));

			// 결재문서 등록 성공시 문서상태를 결재중으로 변경한다.
			map.put("docNo", (String) pMap.get("docNo"));
			map.put("docSts", "D02");

			oService.saveCmasDocUpdateFail(map);

		}else{
			rslMap.put("type", (String) rsl.get("TYPE"));
/*
			// 실패시에는  STATUS를 99로? 60으로??(2019.05.08 G.KIM)
			// 이거 정산서도 이 로직이 제대로 들어가 있는지?? 삭제 말고 재전송 기능도 있어야 하지 않는지?
			map.put("docNo", (String) pMap.get("docNo"));
			map.put("docSts", "D99");

			oService.saveCmasDocUpdateFail(map);
*/
		}

		rslMap.put("result", result);

		return rslMap;
	}
	//출장 정산
	public Map<String, Object> signAdjustOuterRequest(Map<String, Object> pMap,
			List<HashMap> pSignInfo) throws Exception {
		boolean result = false;
		int size = 1;

		// 기안자
		HashMap<String, Object> firstSign = pSignInfo.get(0);

		String p_userId = (String) firstSign.get("signUserId"); // 작성자 ID
		String p_orgCd = (String) firstSign.get("apperOrgCd"); // 작성자 ORG
		String p_signInfo = p_orgCd + "^" + p_userId + "^1^T01"; // 상신자
		String p_teamJang = "";

		// 해외출장의 경우 ds_Signln 정보로 signInfo 를 작성한다.
		for (int i = 1; i < pSignInfo.size(); i++) {

			HashMap<String, Object> tempSign = pSignInfo.get(i);

			size++;
			p_signInfo = p_signInfo + "##"
					+ (String) tempSign.get("apperOrgCd") + "^"
					+ (String) tempSign.get("signUserId") + "^" + size + "^" + (String) tempSign.get("signTpCd");

		}

		// 최종 팀장 소장 결재
		// size++;
		// p_signInfo = p_signInfo + "##" + (String)pMap.get("tSignUserCd") +
		// "^" + (String)pMap.get("tSignUserId") + "^" + size + "^T02";

		Map map = new HashMap();
		map.put("dutySysCd", (String) pMap.get("dutySysCd"));
		map.put("programCode", (String) pMap.get("programCode"));
		// map.put("signDocTitle", SgnsURLEncoder.encode( signDocTitle ,
		// "utf-8"));
		map.put("signDocTitle",
				URLEncoder.encode((String) pMap.get("signDocTitle"), "utf-8"));
		// URL Encode 해줄것

		Map rsl;

		String serverType = (String) pMap.get("serverName");
		System.out.println("serverType : " + serverType);

		// Callback 입력
		if (serverType.equals("iworks.daewooenc.com")) {
			map.put("process",
					"http://iworks.daewooenc.com/cmas/trip/outerTrip/CallBackServlet.xpl"); // 운영
		} else if (serverType.equals("test.daewooenc.com")) {
			map.put("process",
					"http://test.daewooenc.com/cmas/trip/outerTrip/CallBackServlet.xpl"); // 개발
		} else {
			map.put("process",
					"http://172.21.18.38:8080/cmas/trip/outerTrip/CallBackServlet.xpl"); // 내
																						// 로컬
		}

		// legacyInfo 파라미터 셋팅

		String ordDate = (String) pMap.get("ordDate"); // 기표일자
		String ordNo = (String) pMap.get("ordNo"); // 전표번호
		String docNo = (String) pMap.get("docNo"); // CMAS 문서번호
		String drafterId = (String) pMap.get("drafterId"); // 작성자 ID
		String drafter = (String) pMap.get("drafter");
		String drafterOrg = (String) pMap.get("drafterOrg"); // 작성자 ORG
		String drafterOrgNm = (String) pMap.get("drafterOrgNm");
		String tripUser = (String) pMap.get("tripUser");
		String tripUserId = (String) pMap.get("tripUserId");
		String tripUserInfo = (String) pMap.get("tripUserInfo");

		// 2016-04-21 정산서 상신시 실제 출장기간 저장
		// 변경된 정산서의 출장 기간
		String rTripDateStart = (String) pMap.get("rTripDateStart");
		String rTripDateEnd = (String) pMap.get("rTripDateEnd");
		// 최초 신청서의 출장 기간
		String startDate = (String) pMap.get("startDate");
		String endDate = (String) pMap.get("endDate");



//		String tripNat = (String) pMap.get("tripNat");
//		String tripDate = (String) pMap.get("tripDate");
//		String realTripDate = (String) pMap.get("realTripDate");
//		String payDate = (String) pMap.get("payDate");
//		String tReq = (String) pMap.get("tReq");
//		String tPurp = (String) pMap.get("tPurp");
//		String tPlanDtl = (String) pMap.get("tPlanDtl");
//		String tDataList = (String) pMap.get("tDataList");
//		String s1 = (String) pMap.get("s1");
//		String s2 = (String) pMap.get("s2");
//		String s3 = (String) pMap.get("s3");
//		String bdgtType = (String) pMap.get("bdgtType");
//		String cGubun = (String) pMap.get("cGubun");
//		String aufnr = (String) pMap.get("aufnr");
//		String kostv = (String) pMap.get("kostv");
//		String drafterInfo = (String) pMap.get("drafterInfo");
//		String rem = (String) pMap.get("rem");
//		String assDtl = (String) pMap.get("assDtl");
		String refNo = (String) pMap.get("refNo");

		// 추가정보
		String af1 = (String) pMap.get("af1");
		String af2 = (String) pMap.get("af2");
		String af3 = (String) pMap.get("af3");

		String usNightRef = (String) pMap.get("usNightRef");
		String usDayRef = (String) pMap.get("usDayRef");
		String eduRef = (String) pMap.get("eduRef");
		String spotRef = (String) pMap.get("spotRef");
		String euNightRef = (String) pMap.get("euNightRef");
		String euDayRef = (String) pMap.get("euDayRef");
		String enNightRef = (String) pMap.get("enNightRef");
		String enDayRef = (String) pMap.get("enDayRef");
		String jaNightRef = (String) pMap.get("jaNightRef");
		String jaDayRef = (String) pMap.get("jaDayRef");

		String usNightDay = (String) pMap.get("usNightDay");
		String usDayDay = (String) pMap.get("usDayDay");
		String eduDay = (String) pMap.get("eduDay");
		String spotDay = (String) pMap.get("spotDay");
		String euNightDay = (String) pMap.get("euNightDay");
		String euDayDay = (String) pMap.get("euDayDay");
		String enNightDay = (String) pMap.get("enNightDay");
		String enDayDay = (String) pMap.get("enDayDay");
		String jaNightDay = (String) pMap.get("jaNightDay");
		String jaDayDay = (String) pMap.get("jaDayDay");

		String usd = (String) pMap.get("usd");
		String eur = (String) pMap.get("eur");
		String gbp = (String) pMap.get("gbp");
		String jpy = (String) pMap.get("jpy");




		String p_legacyInfo = "ordDate:" + ordDate + "||" + "ordNo:" + ordNo
				+ "||" + "docNo:" + docNo + "||" + "drafterId:" + drafterId
				+ "||" + "drafter:" + drafter + "||" + "drafterOrg:"
				+ drafterOrg + "||" + "drafterOrgNm:" + drafterOrgNm + "||"
				+ "tripUser:" + tripUser + "||" + "tripUserInfo:" + tripUserInfo + "||"
				+ "tripUserId:" + tripUserId + "||"		// 문서번호
//				+ "tripNat:" + tripNat + "||"
//				+ "tripDate:" + tripDate + "||" + "realTripDate:"
//				+ realTripDate + "||" + "payDate:" + payDate + "||" + "tReq:"
//				+ tReq + "||" + "tPurp:" + tPurp + "||" + "tPlanDtl:"
//				+ tPlanDtl + "||" + "tDataList:" + tDataList + "||" + "s1:"
//				+ s1 + "||" + "s2:" + s2 + "||" + "s3:" + s3 + "||"
//				+ "bdgtType:" + bdgtType + "||" + "cGubun:" + cGubun + "||"
//				+ "aufnr:" + aufnr + "||" + "kostv:" + kostv + "||"
//				+ "drafterInfo:" + drafterInfo + "||" + "rem:" + rem + "||"
//				+ "assDtl:" + assDtl + "||"
				+ "refNo:" + refNo + "||"

				+ "af1:" + af1 + "||"
				+ "af2:" + af2 + "||"
				+ "af3:" + af3 + "||"

				+ "usNightRef:" + usNightRef + "||"
				+ "usDayRef:" + usDayRef + "||"
				+ "eduRef:" + eduRef + "||"
				+ "spotRef:" + spotRef + "||"
				+ "euNightRef:" + euNightRef + "||"
				+ "euDayRef:" + euDayRef + "||"
				+ "enNightRef:" + enNightRef + "||"
				+ "enDayRef:" + enDayRef + "||"
				+ "jaNightRef:" + jaNightRef + "||"
				+ "jaDayRef:" + jaDayRef + "||"

				+ "usNightDay:" + usNightDay + "||"
				+ "usDayDay:" + usDayDay + "||"
				+ "eduDay:" + eduDay + "||"
				+ "spotDay:" + spotDay + "||"
				+ "euNightDay:" + euNightDay + "||"
				+ "euDayDay:" + euDayDay + "||"
				+ "enNightDay:" + enNightDay + "||"
				+ "enDayDay:" + enDayDay + "||"
				+ "jaNightDay:" + jaNightDay + "||"
				+ "jaDayDay:" + jaDayDay + "||"

				+ "usd:" + usd + "||"
				+ "eur:" + eur + "||"
				+ "gbp:" + gbp + "||"
				+ "jpy:" + jpy + "||"

				+ "rTripDateStart:" + rTripDateStart + "||"
				+ "rTripDateEnd:" + rTripDateEnd + "||"

				+ "startDate:" + startDate + "||"
				+ "endDate:" + endDate + "||"
				;

		map.put("userId", p_userId);
		map.put("orgCd", p_orgCd);
		map.put("legacyInfo", URLEncoder.encode(p_legacyInfo, "utf-8")); // SGNS
																			// 에서
																			// 받아야되는
																			// 필요한
																			// 파라미터는
																			// legacyInfo
																			// 에
																			// 추가하여
																			// 넣을것

		map.put("signInfo", p_signInfo);

		//2015-09-16 결재문서 파일 첨부 추가
		// 파일 정보 가져와서 넘김
		//1^첨부파일.pdf^13421^sgns/2013/0408/aaaaa.pdf||2^첨부파일2.pdf^131^sgns/2013/0408/bbbbb.pdf
		//fileSeq^fileNm^fileSize^filePath||

		List<Map<String, Object>> fileList = fService.retrieveFilesListInfo(pMap);

		String p_attachInfo = "";

		if(fileList.size() > 0){
			for(int i = 0; i < fileList.size(); i++){
				Map<String, Object> fMap = fileList.get(i);
				System.out.println((String)fMap.get("fileNm"));
				System.out.println((String)fMap.get("fileSize"));
				System.out.println((String)fMap.get("filePath"));
				if(i == 0){
					p_attachInfo = "1"
							+ "^" + (String)fMap.get("fileNm")
							+ "^" + (String)fMap.get("fileSize")
							+ "^" + (String)fMap.get("filePath");
				}else if(i > 0){
					p_attachInfo = p_attachInfo + "##" + (i+1)
							+ "^" + (String)fMap.get("fileNm")
							+ "^" + (String)fMap.get("fileSize")
							+ "^" + (String)fMap.get("filePath");
				}
			}
		}
		System.out.println(p_attachInfo);

		map.put("attachInfo",  URLEncoder.encode(p_attachInfo, "utf-8"));

		if (serverType.equals("iworks.daewooenc.com")) {
			rsl = com.dwenc.cmas.common.utils.HttpSend.invokeURL(
					"http://iworks.daewooenc.com/sgns/sn/sign/remoteDraft.xpl",
					map);
		} else {
			rsl = com.dwenc.cmas.common.utils.HttpSend
					.invokeURL(
							"http://test.daewooenc.com/sgns/sn/sign/remoteDraft.xpl",
							map);
		}

		Map<String, Object> rslMap = new HashMap<String, Object>();

		System.out.println("rsl ==> " + rsl);
		System.out.println("map ==> " + map);
		if (((String) rsl.get("TYPE")).equals("SUCCESS")) {
			result = true;
			rslMap.put("signId", (String) rsl.get("signId"));
			rslMap.put("signInfo", p_signInfo);
			rslMap.put("type", (String) rsl.get("TYPE"));
			// request.setAttribute("signId", (String)rsl.get("signId"));

			// 결재문서 등록 성공시 문서상태를 결재중으로 변경한다.
			map.put("docNo", (String) pMap.get("docNo"));
			map.put("docSts", "D02");

			oService.saveAdjustCmasDocUpdateFail(map);

		}else{
/*			rslMap.put("type", (String) rsl.get("TYPE"));

			// 실패시에는  STATUS를 99로? 60으로??(2019.05.08 G.KIM)
			// 이거 정산서도 이 로직이 제대로 들어가 있는지?? 삭제 말고 재전송 기능도 있어야 하지 않는지?
			map.put("docNo", (String) pMap.get("docNo"));
			map.put("docSts", "D99");

			oService.saveAdjustCmasDocUpdateFail(map);*/
		}

		rslMap.put("result", result);

		return rslMap;
	}

}
