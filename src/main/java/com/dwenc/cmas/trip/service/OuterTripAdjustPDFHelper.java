package com.dwenc.cmas.trip.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.common.utils.RexUtil;
import com.dwenc.cmas.trip.domain.Sign;

import docfbaro.common.ObjUtil;
import docfbaro.common.WebContext;

@Service
public class OuterTripAdjustPDFHelper {

//					private static Logger logger = LoggerFactory.getLogger(InnerTripControllerHelper.class);

	@Autowired
    private RexUtil rexUtil;

	@Autowired
	private OuterTripService oService;

	@Async
	public void testCreatePdf() {
		try {
			System.out.print("start creating pdf ... ");
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("end creating pdf ... ");
	}

	public void createPdf(String docTitle, String signId, Map<String, Object> rslmap, Map<String, Object> legacyMap) {
		System.out.print("end creating pdf ... ");
		HashMap<String, Object> pdfmap = new HashMap<String, Object> ();

		Map<String, Object> signParam = new HashMap<String, Object>();
		signParam.put("docNo", (String)rslmap.get("docNo"));

		//해외출장 신청서 결재선 조회
		List<Sign> ds_Sign = oService.retrieveSignInfo4(signParam);

		int asCnt = 0;
		int apCnt = 0;
		String signDt[];

		for(int i = 0; i < ds_Sign.size(); i++){
			Sign signData = ds_Sign.get(i);
//    					System.out.println("ds_Sign " + i + " : " +signData.getSignUserId());
			System.out.println("signData.getSignDt() : " + signData.getSignDt());
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

		/*결재박스*/
		pdfmap.put("ap_rcnt", apCnt+"");

		String tripUser = (String)legacyMap.get("tripUserId").toString();
		String tripUserAry[] = tripUser.split(" ");
		//필수값
		pdfmap.put("rex_db", "oracle1");
		pdfmap.put("rex_rptname", "trip/OUTERTRIP_R00");
		pdfmap.put("pdf_file_nm", tripUserAry[0] + "_HE15_I228_N_" + (String)rslmap.get("refNo") + ".pdf");

		//파라미터

		pdfmap.put("sign_id", signId);
		pdfmap.put("drft_user_nm", (String)legacyMap.get("drafterId").toString());
		pdfmap.put("sign_doc_title", docTitle);

		pdfmap.put("refNo", (String)rslmap.get("refNo"));
		pdfmap.put("docNo", (String)rslmap.get("docNo"));


		pdfmap.put("ordDate", (String)legacyMap.get("drafter").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("drafterOrg").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("drafterOrgNm").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("tripUserInfo").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("af1").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("af2").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("af3").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("usNightRef").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("usDayRef").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("eduRef").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("euNightRef").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("euDayRef").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("enNightRef").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("enDayRef").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("jaNightRef").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("jaDayRef").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("usNightDay").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("usDayDay").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("eduDay").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("spotDay").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("euNightDay").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("euDayDay").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("enNightDay").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("enDayDay").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("jaNightDay").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("jaDayDay").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("usd").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("eur").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("gbp").toString());
		pdfmap.put("ordDate", (String)legacyMap.get("jpy").toString());



		HttpServletRequest req = WebContext.getRequest();
		HttpServletResponse res = WebContext.getResponse();
		// server ip address
		String serverType = req.getServerName();
		if 	(serverType.equals("iworks.daewooenc.com")){
			System.out.println("운영 서버 exportPDF 생성 시작");
			rexUtil.exportPDF(pdfmap);
		}else{
			System.out.println("운영 서버가 아님 PDF 생성하지 않습니다.");
		}

//		rexUtil.exportPDF(pdfmap);


	}
}