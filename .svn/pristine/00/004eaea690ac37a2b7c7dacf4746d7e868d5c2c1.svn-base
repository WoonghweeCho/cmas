package com.dwenc.cmas.trip.controller;

import java.net.URLEncoder;

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

import com.dwenc.cmas.common.file.service.FileService;
import com.dwenc.cmas.common.user.domain.Sign;
import com.dwenc.cmas.common.user.service.SignUserService;
import com.dwenc.cmas.common.utils.RequestUtil;

import com.dwenc.cmas.trip.domain.VisaAppn;
import com.dwenc.cmas.trip.service.VisaAppnService;

import docfbaro.common.StringUtil;
import docfbaro.common.WebContext;
import docfbaro.sua.exception.BusinessException;
import docfbaro.sua.mvc.MciRequest;
import docfbaro.sua.mvc.MciResponse;

import com.dwenc.cmas.trip.util.SignProcess;

@Controller
@RequestMapping("/trip/visaAppn/*") /* 개발하는 업무에 대한 HTTP Request URI */
public class VisaAppnController {

	/**
	 * Logger 객체 생성
	 */
	private static Logger logger = LoggerFactory.getLogger(VisaAppnController.class);

	/**
	 * 해당 Controller와 연결된 Service Class
	 */
    @Autowired
    private VisaAppnService sService;

    @Autowired
    private SignUserService suService;

    @Autowired
    private SignProcess signProcess;

	@Autowired FileService fService;

    /**
     * Common Message 처리
     */
    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    /**
     * 비자신청 목록 조회(visaAppnList.js)
     * @param request
     * @param response
     */
    @RequestMapping("retrieveVisaAppnList.*")
	public void retrieveVisaAppnList(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<VisaAppn> ds_VisaAppnList = sService.retrieveVisaAppnList(map);
		response.setList("ds_VisaAppnList", ds_VisaAppnList);  		//Map을 Client로 전송
	}

    /**
     * 협의결재자(VISA담당자) 조회 (CMAS_OT_007)
     * @param request
     * @param response
     */

    @RequestMapping("retrieveCoSignList.*")
	public void retrieveCoSignList(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		//CMAS 시스템 검토담당자 조회
		List<Map<String, Object>> ds_Cmas007 = sService.retrieveCMASOT007(map);
		response.setList("ds_Assist", ds_Cmas007);
	}

    /**
     * 문서 채번
     * @param request
     * @param response
     */
    @RequestMapping("getDocNo.*")
	public void getDocNo(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> ResultMap = sService.getDocNo(map);

		sService.insertVisaAppnTempDoc(ResultMap);

		response.setMap("ds_Result", ResultMap);  		//Map을 Client로 전송
	}


    /**
     * 일반정보 조회(from C51DB view)
     * @param request
     * @param response
     */
    @RequestMapping("retrieveGeneralInfo.*")
	public void retrieveGeneralInfo(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> ds_GeneralInfo = sService.retrieveGeneralInfo(map);

		response.setMap("ds_GeneralInfo", ds_GeneralInfo);  		//Map을 Client로 전송
	}

    /**
     * 저장
     * @param request
     * @param response
     */
    @RequestMapping("saveVisaAppn.*")
	public void saveVisaAppn(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<Sign> gridSign = request.getGridData("signln", Sign.class);

		sService.saveVisaAppn(map);
		suService.insertSign(gridSign);
	}

    @RequestMapping("draftVisaAppn.*")
    public void draftVisaAppn(MciRequest request, MciResponse response){
    	Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		Map<String, Object> rMap = new HashMap<String, Object>();
		GridData<Sign> gridSign = request.getGridData("signln", Sign.class);

		/**
		 * signProcess.draft required map info:
		 * userId		: 사용자 		ex) 1204594
		 * orgCd		: 조직코드		ex) 1DFUR
		 * dutySysCd	: 업무시스템코드 	ex) SGNS
		 * programCode	: 결재양식코드 	ex) SGNS070003
		 * signDocTitle : 결재문서제목	ex) 제목... URLEncoder.encode("제목", "utf-8")
		 * legacyInfo	: etc(Rexpert등)	ex) key:value||key:value||... URLEncoder.encode("key:value||", "utf-8"));
		 * signInfo		: 결재선정보		ex) 1DFUR^1204594^1^T01##1DFUR^1202564^2^T02##1DFUR^1304252^3^T03##1DFUR^1203160^4^T02
		 * process 		: 콜백URL		ex) http://iworks.daewooenc.com/cmas/visa/visaAppn/SignCallBack.xpl
		 *
		 */

		// 결재상신 처리
		try{
			/**
			 * 운영/개발 분기 처리
			 */
			HttpServletRequest req = WebContext.getRequest();
			String serverType = req.getServerName();
			String serverURL = "";
			if 	(serverType.equals("iworks.daewooenc.com"))	serverURL = "http://iworks.daewooenc.com";	// 운영
			else serverURL = "http://test.daewooenc.com";	// 개발

			/**
			 * 결재선 정보 생성(통합결재 전송용)
			 */
			String signInfo = "";
			for(int i=0; i<gridSign.size(); i++){
				signInfo = signInfo + gridSign.get(i).getApperOrgCd() + "^" + gridSign.get(i).getSignUserId() + "^"
						+ gridSign.get(i).getSignSeq() + "^" + gridSign.get(i).getSignTpCd() + "##";
			}
			/**
			 * 제목 정보 생성 (통합결재 전송용)
			 */
			String signDocTitle = "비자신청";

			System.out.println("인자값:"+StringUtil.getText(map.get("userDeptNm"))+
					" "+StringUtil.getText(map.get("userKnm")));

			signDocTitle = "[비자신청] "+StringUtil.getText(map.get("userDeptNm"))+
					       " "+StringUtil.getText(map.get("userKnm"))+
					       " ("+StringUtil.getText(map.get("visitNatNmList"))+")";

			/**
			 * userId : visaAppnDraft.js 로 부터 옴
			 * orgCd : visaAppnDraft.js 로 부터 옴
			 */
			map.put("useSignForm", "Y");	//양식정보의 협의자 사용여부
			map.put("dutySysCd", "SGNS");
			map.put("programCode", StringUtil.getText(map.get("programCode")));
			map.put("signDocTitle", URLEncoder.encode(signDocTitle, "utf-8"));
			map.put("legacyInfo", URLEncoder.encode("docNo:" + (String)map.get("docNo"), "utf-8"));
			map.put("signInfo", signInfo);
			map.put("process", serverURL + "/cmas/trip/visaAppn/SignCallBack.xpl");
			map.put("invokeURL", serverURL + "/sgns/sn/sign/remoteDraft.xpl");

			//2019-09-04 결재문서 파일 첨부 추가
			// 파일 정보 가져와서 넘김
			//1^첨부파일.pdf^13421^sgns/2013/0408/aaaaa.pdf||2^첨부파일2.pdf^131^sgns/2013/0408/bbbbb.pdf
			//fileSeq^fileNm^fileSize^filePath||

			List<Map<String, Object>> fileList = fService.retrieveFilesListInfo(map);

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

			rMap = signProcess.signVisaRequest(map);
			/**
			 * 상신 성공 시, 처리
			 */
			if ( ((String)rMap.get("TYPE")).equals("SUCCESS") ) {
				map.put("docSts", "D02");
				for(int i=0; i<gridSign.size(); i++){
					if(gridSign.get(i).getSignSeq().equals("1")){
						gridSign.get(i).setSignStsCd("S01");
						gridSign.get(i).setSignDt("now");
					}
					gridSign.get(i).setSignId((String)rMap.get("signId"));
				}
				sService.saveVisaAppn(map);

				suService.insertSign(gridSign);
			} else {
				throw new Exception();
			}
		}catch(Exception e){
			/**
			 * 상신 실패 시, 처리
			 */
			throw new BusinessException(null, (String)rMap.get("MSG_VALUES"), null);
		}
    }

    // DOC STS에 따른 분기
    @RequestMapping("SignCallBack.*")
	public void signCallBack(MciRequest request, MciResponse response) {
    	Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> data = request.get("json", HashMap.class);
		Map<String, Object> result = new HashMap<String, Object>();

		String docStsCd = data.get("docStsCd").toString();
		map.put("signId", data.get("signId").toString());
		map.put("signUserId", data.get("signUserId").toString());
		map.put("signSeq", data.get("signSeq").toString());
		map.put("docNo", data.get("docNo").toString());

		map.put("docSts", docStsCd); 		// 업무의 결재문서상태 업데이트

		if(docStsCd.equals("D02"))			// 결재중
			map.put("signStsCd", "S04");
		else if(docStsCd.equals("D03"))		// 결재완료
			map.put("signStsCd", "S03");
		else if (docStsCd.equals("D04")) 	// 반려
			map.put("signStsCd", "S05");
		else if (docStsCd.equals("D11")) {	// 협의요청
			if(!data.get("signSeq").toString().equals("2"))	// 협의
				map.put("signStsCd", "S04");
			else
				map.put("signStsCd", "S11");
		}
		else if (docStsCd.equals("D12")) {	// 협의완료
			map.put("signStsCd", "S04");
		}

		//결재 후에 하는 ACTION을 기술한다.
		result = sService.sign(map, data);

		response.setMap("RESULT", result);
    }

    /**
     * 반려
     * @param request
     * @param response
     */

    @RequestMapping("updateRejectComment.*")
 	public void updateRejectComment(MciRequest request, MciResponse response) throws Exception{
 		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		sService.updateRejectComment(map);
 	}

    @RequestMapping("retrieveErrMsg.*")
	public void retrieveErrMsg(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> resultMap = sService.retrieveErrMsg(map);

		response.setMap("output1", resultMap);  		//Map을 Client로 전송
    }

    /**
     * 삭제
     * @param request
     * @param response
     */

    @RequestMapping("deleteVisaAppn.*")
	public void deleteVisaAppn(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		sService.deleteVisaAppn(map);

	}

    /**
     * 조회
     * @param request
     * @param response
     */
    @RequestMapping("retrieveSavedDocInfo.*")
	public void retrieveSavedDocInfo(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		Map<String, Object> ds_DocData = sService.retrieveSavedDocInfo(map);
		map.put("cmasId", (String)map.get("docNo"));

		response.setMap("ds_DocData", ds_DocData);  		//Map을 Client로 전송

    }

    /**
     * 접수
     * @param request
     * @param response
     */
    @RequestMapping("visaSubmitNotify.*")
	public void visaSubmitNoti(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		sService.visaSubmitNotify(map);

	}

    /**
     * 발급통보
     * @param request
     * @param response
     */
    @RequestMapping("visaIssueNotify.*")
	public void visaIssueNotify(MciRequest request, MciResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		sService.visaIssueNotify(map);

	}
}

