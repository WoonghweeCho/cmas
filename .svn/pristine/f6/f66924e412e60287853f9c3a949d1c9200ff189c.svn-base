/**************************************************************************************
 								지금 사용중인 함수 여기서부터
***************************************************************************************/

//최대 결재자 수
var gv_MaxHoSign = 5;
var gv_MaxSiteSign = 3;
var gv_MaxAssist = 6
var gv_MaxLap = 4;
var gv_callBackParams = "" ;
var gv_FixHeight = 581;
var gv_FixWidth = 1009;
var gv_DialogHeight = -1;
var MAX_NUMBER = 9007199254740992;
var MIN_NUMBER = -9007199254740992;

var gds_SignFrom = new DataSet();

var gds_SignUser = new DataSet();

var gds_UserEtcInfo = new DataSet();

/*----------------------------------------------------------------------------------
 * 시스템   : 내부결재
 * 설  명    : 업무시스템에서 결재요청시 호출되며 아래의 처리 과정을 거친다.
 *              1. 업무시스템의 결재요청 파라미터 유효성 체크
 *				2. 내부결재 : 결재요청 호출
 *                 - 해당 결재요청 정보를 DB에 저장하고 결재문서번호(sign_id)를 채번한다.
 *				   - 연동정보 저장 상태로 업무시스템에 Callback 한다.
 *				3. 내부결재 기안작성 화면을 호출한다.
 * 				   - 결재요청 파라미터 중 programCode를 통해 양식 정보를 호출하여 본문을 표출한다.
 *                 - 결재본문 유형
 *                   . 양식 : Rexpert 디자인 파일
 *                   . 업무화면 : 업무화면 URL
 *                   . 첨부파일 : 문서관리 연동 시 첨부된 첫번째 첨부파일
 *                   . 본문없음 : 본문이 없는 결재문서
 *
 * 파라미터 : params
 *             - programCode :  결재 연동 프로그램 코드 (필수)
 *             - dutySysCd :  시스템 코드 (필수)
 *             - process :  업무측 callback url(필수)
 *             - callbackType :  callback Type : APPROVAL, FINAL(필수)
 *             - reportPath :  본문 경로(양식, 업무화면 URL)(필수)
 *             - signDocTitle :  문서 제목
 *             - rexParams{} :  양식 결재일 경우 양식 연동을 위한 파라미터
 *             - 기타 Callback, 업무화면 요청 시 인자로 넘겨줄 파라미터
 *
 * 리턴값   : boolean
 * 작성자   : 고준석
 * 작성일   : 2012.01.04
 ----------------------------------------------------------------------------------------------*/
var fv_SignId = "";
var fv_FileAtchId = "";
var fv_ViewSignbxCd = "";
var fv_ViewSignbxNm = "";

var fv_Params;
var fv_RexParams;
var fv_IsLocal;
var fv_signValid = false;

/*----------------------------------------------------------------------------------
 * 시스템   : 내부결재
 * 설  명    : 결재문서 본문 Display
 * 파라미터 : formType - 양식유형
 *             formPath - 양식경로
 *			   parentName - 양식이 삽입될 Component명
 *			   childName - 삽입할 Component명
 * 리턴값   : void
 * 작성자   : 고준석
 * 작성일   : 2012.01.04
 ----------------------------------------------------------------------------------------------*/
function gf_SignBody(signId, params, viewEle, height)
{
	var formType = params.signFormTpCd;
	var signView = new SignViewRenderer(viewEle, formType, params, height);
	signView.render();
	return signView;
}

 /*----------------------------------------------------------------------------------------------
 * 시스템   : 내부결재
 * 설  명    : 결재완료문서 본문 Display
 * 파라미터 : formType - 양식유형
 *             formPath - 양식경로
 *			   parentName - 양식이 삽입될 Component명
 *			   childName - 삽입할 Component명
 * 리턴값   :
 * 작성자   : 고준석
 * 작성일   : 2012.01.04
 ----------------------------------------------------------------------------------------------*/
function gf_FianlSignBody(signId, params, viewEle, height)
{
	var formType = params.signFormTpCd;
	var signView = new SignViewRenderer(viewEle, formType, params, height);
	signView.render();
	return signView;
}
/*----------------------------------------------------------------------------------
 * 시스템   : 내부결재
 * 설  명    : Rexpert 본문 로딩을 위한 Parameter 생성
 * 파라미터 : signId - 결재문서 ID
               formId - 결재양식 경로
 * 리턴값   : string(url)
 * 작성자   : 고준석
 * 작성일   : 2012.01.04
 ----------------------------------------------------------------------------------------------*/
function gf_RexReportSignUrl(type, filePath, rexParams)
{
	var path = filePath.indexOf(".reb") > 0? filePath.substring(0, filePath.lastIndexOf(".reb")) : filePath ;
	//공통모듈에서 공통 Parameter 생성
	for(var key in rexParams){
		if(typeof(rexParams[key]) == "string"){
			rexParams[key] = rexParams[key].replace(/\n/g, "\\n");
			rexParams[key] = rexParams[key].replace(/\r/g, "\\r");
		}
	}

	var url = gf_RexReportUrl(path, rexParams);
	//url += "&" + gv_callBackParams;
	return url;
}
/*----------------------------------------------------------------------------------
 * 시스템   : 내부결재
 * 설명     : 현재 결재정보를 통해 본문에 결재자 직위와 승인자들의 서명을 표시하기 위한
 *             파라미터를 생성한다.
 *             - ds_SignDoc : 결재문서정보 DataSet
 *             - ds_SignList : 결재자정보 DataSet
 *             - ds_SignForm : 결재양식정보 DataSet
 *             - ds_MSign : 이단결재정보 DataSet
 * 파리미터 : type - 문서열람 구분
 *             - DRFT : 기안
 *             - MDRFT : 이단기안
 *             - VIEW : 문서열람
 * 참고 : orgChrcCls 코드
 * 리턴값   : object(rexParams)
 * 작성자   : 고준석
 * 작성일  : 2012.01.03
 ----------------------------------------------------------------------------------------------*/
function gf_AssembleSign(type, v_paramMap, selSignlnOrg, signDoc, signForm, mSign){
	var selSignln = new DataSet(selSignlnOrg.getAll()); //원본 결재선을 보호하기 위해 사본을 생성

	var rexParams = {};
	rexParams.is_sign = "Y";
	//var v_paramMap = gf_getCallBackParamMap(ds_SignDoc.getColumn(0,'signId'));
	v_paramMap.mSignOrgChrc = gf_IsNull(v_paramMap.mSignOrgChrc)? "Y" : v_paramMap.mSignOrgChrc;
	v_paramMap.isHiddenDt = gf_IsNull(v_paramMap.isHiddenDt)? "N" : v_paramMap.isHiddenDt;
	v_paramMap.setDraftDt = gf_IsNull(v_paramMap.setDraftDt)? "" : v_paramMap.setDraftDt;4
	var dutyLnkCd = signForm.get(0, "dutyLnkCd");
	var size = selSignln.size(); // 전체 결재자 수
	var multiSign = 0; //다중결재 구분
	var signTp; //결재유형
	var signPos = ""; //결재자 직위
	var signNm = ""; //결재자 이름
	var signId = ""; //결재자 아이디
	var signDt = ""; //결재일자
	var docStsCd = ""; //결재문서 상태
	var tmp =" ";
    var instcId = "100";//gf_GetConfData("dwe.serverInfo.instcId");
	//Rexpert Parameter : 결재문서 ID
	var msignYn = v_paramMap.msignYn;

	docStsCd = signDoc.get(0, "docStsCd");

	rexParams.sign_id = v_paramMap.signId;
	rexParams.sign_doc_title = v_paramMap.signDocTitle;
	rexParams.drft_user_nm = selSignln.get(0, 'signUserNm');
	rexParams.drft_dt = gf_IsNull(signDoc.get(0, 'drftDt'))? "" : signDoc.get(0, 'drftDt');
	rexParams.drft_dt = (rexParams.drft_dt && rexParams.drft_dt.length > 10)
							? rexParams.drft_dt.substring(0,10) : rexParams.drft_dt;
	if(v_paramMap.setDraftDt != "")
		rexParams.drft_dt = v_paramMap.setDraftDt;
	var excluCd = signDoc.get(0, 'excluCd');	//TODO : 향후 전결코드 컬럼으로 이름 변경
	var rpswrkNm = signDoc.get(0, 'rpswrkNm');	//TODO : 향후 전결자 컬럼으로 이름 변경
	rexParams.tempText = "";
	if(excluCd != "")
		rexParams.tempText = "이 문서의 최종 전결권자는 "+rpswrkNm+" 입니다. 전결코드 : "+excluCd;
	if(msignYn == "Y"){
		rexParams.msign_org_nm = mSign.get(0, 'msignOrgNm');
		rexParams.msign_inchrg_user_nm = mSign.get(0, 'msignInchrgUserNm');
		rexParams.msign_dt = signDoc.get(0, 'msignDt');
		rexParams.msign_dt = (rexParams.msign_dt && rexParams.msign_dt.length > 10)
								? rexParams.msign_dt.substring(0,10) : rexParams.msign_dt;
	}
	//결재방 개수 생성을 위해 결재자 수 설정
	var ap_lcnt = 0; //결재양식의 좌측 결재방 수
	var ap_rcnt = 0; //결재양식의 우측 결재방 수
	//결재순번 - 결재방 매핑
	var apStep = 0;
	var asStep = 0;
	//결재문서가 이단 결재인지 여부 체크
	var isMSign = false;
	if(type == "DRFT"){
		if(signForm.get(0, "msignYn") == 'Y'){
			isMSign = true;
		}
	}else{
		if(mSign.size() > 0){
			isMSign = true;
		}
	}
	//결재방 나뉨 예외처리 '감사보고서' 일경우 본부장을 기준으로 좌우로 결재방을 나눔
	if((!isMSign && dutyLnkCd == "cmas050007") || (!isMSign && dutyLnkCd == "cmas130008")){
		var rowIdx = -1;
		for(var i = 0 ; i < selSignln.size(); i++){
			var apperRpswrkCd = selSignln.get(i, "apperRpswrkCd");
			if(apperRpswrkCd.indexOf("본부장") > -1 || apperRpswrkCd.indexOf("실장") > -1){
				rowIdx = i;
				break;
			}
		}

		if(rowIdx > 0 && (rowIdx+1) < selSignln.size()){
			selSignln.set((rowIdx+1), "signTpCd", "T01");
			isMSign = true;
		}
	}

	rexParams.is_msign = isMSign ? "Y" : "N";
	var beforeRow = 0;
	// 현장과 본사 결재자가 같이 있을경우.
	//협의결재자를 제외한 일반결재자 수(결재양식의 결재방 수)를 구한다.
	var levelCnt = 0;
	beforeRow = 0;
	for( var j = 0 ; j < size ; j++){
		var signTp = selSignln.get(j, 'signTpCd');
			signTp = signTp == "T04" ? "T01" : signTp; //기안자전결 -> 기안자
		if(!isMSign){ //다중결재가 아닐 경우
			if(signTp != "T03"){ //협의결재가 아닐 경우
				ap_rcnt++; //다중결재가 아닐 경우 결재방이 우측에 있다.
			}
		}else{
			if(signTp == 'T01' ){ //기안자이면
				levelCnt++;
			}
			if(signTp != 'T03'){ //협의결재가 아닐 경우
				if(levelCnt == 1){
					ap_lcnt++; // 다중결재 1단 결재방은 좌측에 있다.
				}else{
					ap_rcnt++; //다중결재 2단 결재방은 우측에 있다.
				}
			}
		}
	}
	multiSign = isMSign?0:1;
	beforeRow = 0;
	//Rexpert Parameter : 결재자
	var excluMap = null;
	for( var i = 0 ; i < size ; i++){
		var signTp = selSignln.get(i, 'signTpCd');
		signTp = signTp == "T04" ? "T01" : signTp;
		signStsCd = selSignln.get(i, 'signStsCd');
		signPos = selSignln.get(i, 'apperPositCd');
		signRpswrk = selSignln.get(i, 'apperRpswrkCd');
		signNm = selSignln.get(i, 'signUserNm');
		signId = selSignln.get(i, 'signUserId');
		signOrg = selSignln.get(i, 'apperOrgNm');

 		psignNm = selSignln.get(i, 'psignUserNm');
 		psignPos = selSignln.get(i, 'perpsignPositNm');


 		if(psignNm != null && psignNm != ""){
 			signNm = "代  " + psignNm;
 			signPos = psignPos;
 		}
		signDt = selSignln.get(i, 'signDt');
		signDt = (signDt != null && signDt.length > 10)? signDt.substring(0,10): signDt;

		if(signTp == 'T01'){
			multiSign = multiSign + 1;
			if(multiSign == 1){ //다중결재 1단
				apStep = 0;
			}else{ //다중결재 2단
				apStep = 0;
			}
		}
		signPos = signPos?signPos : "없음"; // TODO: 향후 삭제
		if(signTp != 'T03'){ //협의결재가 아닐 경우
			apStep = apStep + 1;
			rexParams["ap_pos"+multiSign+"_"+apStep]= gf_IsNull(signRpswrk)?signPos : signRpswrk;

			/**
			 * 첫번째 결재라인 생성의 경우 직위를 우선적으로 쓴다.
			 * 이후는 직위가 없을 경우 직책을 쓴다.
			 */
//			if(i == 0)
//				rexParams["ap_pos"+multiSign+"_"+apStep]= gf_IsNull(signPos)?signRpswrk : signPos;
//			else
//				rexParams["ap_pos"+multiSign+"_"+apStep]= gf_IsNull(signRpswrk)?signPos : signRpswrk;

			rexParams["ap_sign"+multiSign+"_"+apStep]=signNm;
			if(docStsCd == "D16") continue; // 결재문서 상태가 임시저장이면 결재가 승인된것이 아님.
			//eval("rexParams.ap_pos"+apStep+"=signPos");
			if(signStsCd == 'S04' || signStsCd == 'S01' || signStsCd == 'S10' || signStsCd == 'S13'){ //기안확인, 결재승인, 전결, 투자심의
				//전결일경우 다음 서명란에 사인을 넣는다.
				if(signTp == "S10"){
					excluMap = {
						"signNm" : signNm
						, "signDt" : signStsCd
					//	, "signImg" : gv_BaronetUrl + "/gwlib/sign/"+signId.substring(signId.length-1, signId.length)+"/"+signId+".gif"
					};

					//rexParams["ap_img"+multiSign+"_"+apStep]= gv_ServerUrl + "/images/exclu_sign.gif";
				}else{
					if(v_paramMap.isHiddenDt == "N")
						rexParams["ap_dt"+multiSign+"_"+apStep]=signDt;
					//if(instcId == "100")
					rexParams["ap_img"+multiSign+"_"+apStep]= gv_cmasComm.baroNetUrl + "/gwlib/sign/"+signId.substring(signId.length-1, signId.length)+"/"+signId+".gif";
				}
				//rexParams["ap_img"+multiSign+"_"+apStep]= gv_BaronetUrl + "/gwlib/sign/2/9520282.gif";
			} else if(signStsCd == 'S05' || signStsCd == 'S06'){
				rexParams["ap_dt"+multiSign+"_"+apStep]=signStsCd;
			}
		}else{ //협의결재일경우
			asStep = asStep + 1;
			if(signStsCd == 'S04'){ // 결재승인
				rexParams["as2_"+asStep]= signOrg + tmp + signPos + tmp + signNm + tmp + "(" + signDt + ")";
			}else{
				rexParams["as2_"+asStep]= signOrg + tmp + signPos + tmp + signNm;
			}
		}
	}
	//전결로 결재 되었을 경우 마지막결재자에 서명을함.
	if(excluMap != null){
		rexParams["ap_sign"+multiSign+"_"+apStep] = excluMap.signNm;
		if(v_paramMap.isHiddenDt == "N")
			rexParams["ap_dt"+multiSign+"_"+apStep] = excluMap.signDt;
		if(instcId == "100")
			rexParams["ap_img"+multiSign+"_"+apStep] = excluMap.signImg;
	}
	//결재방 개수 지정 Parameter 설정
	rexParams.ap_lcnt = ap_lcnt; //좌측결재방 개수
	rexParams.ap_rcnt = ap_rcnt; //우측결재방 개수
	rexParams.as_yn = asStep > 0 ? "Y" : "N";
	rexParams.as_cnt = asStep;
	return rexParams;
}

/*----------------------------------------------------------------------------------
 * 시스템   : 내부결재
 * 설명   : 이단결재 상신 팝업을 오픈한다.
 * 파라미터 :  signId - 결재문서 signId
 *              dutyLnkCd - 결재문서 연동코드
 *				msignInchrgUserId - 이단결재 상신자
 *
 * 리턴값   : void
 * 작성자 : 고준석
 * 작성일 : 2011.12.22
 ----------------------------------------------------------------------------------------------*/
function gf_MDraft(signId, dutyLnkCd, msignInchrgUserId)
{
	var objRetVal = gf_Dialog("child1",
							  "cof::COF_MDraft.xfdl", -1, -1, 1024, 700, false, "-1",
							  {signId:signId, msignInchrgUserId:msignInchrgUserId, dutyLnkCd:dutyLnkCd}, false, true, false);
	return objRetVal;
}

/**
* @class MP 를 오픈하기 위한 인자를 만든다.
* @param legacyInfo - DB에서 추출한 legacyinfo 문자열
* @returns MP 를 오픈하기 위한 인자
* @author 변형구
* @since 2013-04-04
* @version 1.0
*/
function gf_MakeMPParam(legacyInfo) {
	var legacyInfos = legacyInfo.split('||');
	var paramGubn = "";
	var paramScrid = "";
	var paramArg = "";
	for ( var i = 0; i < legacyInfos.length; i++ ) {
		var params = legacyInfos[i].split(':');

		if ( params[0] == 'GUBN') {
			paramGubn = params[1];
		}
		else if ( params[0] == "SCRID") {
			paramScrid = params[1];
		}
		else {
			if ( paramArg == "" ) {
				paramArg = params[0] + '="' + params[1]+'"';
			}
			else {
				paramArg = paramArg + ' ' + params[0] + '="' + params[1]+'"';
			}
		}
	}
	var retVal = paramGubn + "||" + paramScrid + "||" + paramArg;
	retVal = retVal.simpleReplace("**", "::");
	return  retVal ;
}


/*----------------------------------------------------------------------------------
 * 시스템   : 내부결재
 * 설명   : 결재정보조회 팝업을 오픈한다.
 * 파라미터 : signId - 결재문서 signId
 * 리턴값   : void
 * 작성자 : 고준석
 * 작성일 : 2011.12.22
 ----------------------------------------------------------------------------------------------*/
function gf_ViewSign(signId)
{
	var aSignId = gds_Sign.getColumn(0, 'signId');
	if(!signId && !aSignId){// || !dutyLnkCd){
		gf_AlertMsg('co.inf.noSelectData');
		return;
	}
	signId = !signId ? aSignId : signId;
	var objRetVal = gf_Dialog("viewSign",
							  "cof::COF_ViewSign.xfdl", -1, -1, 800, 500, false, "-1",
							  {signId:signId}, false, true, false);
}

/*----------------------------------------------------------------------------------
 * 시스템   : 내부결재
 * 설명   : 회람정보조회 팝업을 오픈한다.
 * 파라미터 : signId - 결재문서 signId
 * 리턴값   : void
 * 작성자 : 고준석
 * 작성일 : 2011.12.22
 ----------------------------------------------------------------------------------------------*/
function gf_ViewReadtn(signId)
{
	if(!signId){// || !dutyLnkCd){
		gf_AlertMsg('co.inf.noSelectData');
		return;
	}
	var objRetVal = gf_Dialog("viewReadtn",
							  "cof::COF_ViewReadtn.xfdl", -1, -1, 800, 500, false, "-1",
							  {signId:signId}, false, true, false);
}
/*----------------------------------------------------------------------------------
 * 시스템   : 내부결재
 * 설명   : 내부결재에서 사용하는 컴포넌트를 초기화 한다.
 * 파라미터 : 컴포넌트 Object
 * 리턴값   : void
 * 작성자 : 고준석
 * 작성일 : 2012.01.16
 ----------------------------------------------------------------------------------------------*/
function gf_ResetCode(obj){
	switch(obj)
	{
		case "COA0107" : //TODO : ID 값으로 변경
			gf_ResetUser();
			break;
		case "COA0104" :
			gf_ResetOrg();
			break;
	}
}

/*----------------------------------------------------------------------------------
 * 시스템   : 내부결재
 * 설명   : 사용자 조회 컴포넌트 - COA0107 를 초기화한다.
 * 파라미터 : 없음
 * 리턴값   : void
 * 작성자 : 고준석
 * 작성일 : 2012.01.16
 ----------------------------------------------------------------------------------------------*/
function gf_ResetUser(){
	$("com_userId").setUserId("");    //사용자 ID
	$("com_userId").setUserName("");    //사용자 이름
}

/*----------------------------------------------------------------------------------
 * 시스템   : 내부결재
 * 설명   : 부서조회 컴포넌트 - COA0104 를 초기화한다.
 * 파라미터 : 없음
 * 리턴값   : void
 * 작성자 : 고준석
 * 작성일 : 2012.01.16
 ----------------------------------------------------------------------------------------------*/
function gf_ResetOrg(){
	$("com_orgCd").resetCode();    //조직 코드, 이름 초기화
}

/*----------------------------------------------------------------------------------
 * 시스템   : 내부결재
 * 설명   : 결재문서가 업무화면일때 해당 결재업무화면에서 결재가 되어도 되는지 여부를 체크함.
 * 파라미터 : v_signFormTpCd : 결재문서 형식
 *             eventType : 결재문서상태
 * 리턴값   : boolean
 * 작성자 : 고준석
 * 작성일 : 2012.01.16
 ----------------------------------------------------------------------------------------------*/
function gf_SignCheck(v_signFormTpCd, eventType){
	if( v_signFormTpCd == "F02" && typeof($("signViewer").cf_SignCheck) != "undefined"){
		var flag = $("signViewer").cf_SignCheck(eventType, this);
		return flag;
	}
	return true;
}

 /*----------------------------------------------------------------------------------------------
 * 시스템   : 내부결재
 * 설명   :  완결PDF존재여부를 출력함.
 *             - ds_SignForm : 결재양식정보 DataSet
 *             - ds_SignDoc : 결재문서정보 DataSet
 *             - ds_SignFileAtch : 결재첨부파일정보 DataSet
 * 파라미터 : 없음
 * 리턴값   : boolean
 * 작성자 : 권준호
 * 작성일 : 2012.04.22
 ----------------------------------------------------------------------------------------------*/
function gf_IsSignFinalPDF(fileAtchId, fileSeq, atchSeq)
{
	if(!gf_IsNull(fileAtchId))
		return f_ExistSignFile(fileAtchId, fileSeq, atchSeq);

	var v_signFormTpCd = ds_SignForm.getColumn(0, "signFormTpCd");
	fileAtchId = null;
	var atchFileSeq = gf_IsNull(ds_SignDoc.getColumn(0, "coverFileSeq"))?"":ds_SignDoc.getColumn(0, "coverFileSeq");
	if(atchFileSeq > 0 && v_signFormTpCd == "F01"){
		ds_SignFileAtch.filter("fileAtchClscd == 'T'");
		fileAtchId = ds_SignFileAtch.getColumn(0, "fileAtchId")  ;
		ds_SignFileAtch.filter("");
	}else if(gf_IsNull(atchFileSeq)&& v_signFormTpCd == "F01"){
		ds_SignFileAtch.filter("fileAtchClscd == 'F'");
		if(!gf_IsNull(ds_SignFileAtch.getColumn(0, "fileAtchId"))){
			fileAtchId = ds_SignFileAtch.getColumn(0, "fileAtchId") ;
		}
		ds_SignFileAtch.filter("");
	}
	if(gf_IsNull(fileAtchId))
		return false;
	 return f_ExistSignFile(fileAtchId, "", atchFileSeq);
}

 /*----------------------------------------------------------------------------------------------
 * 시스템   : 내부결재
 * 설명   :  결재 문서 상태 조회
 * 파라미터 : - aSignId : 결재문서 번호
 * 리턴값   : string(결재문서상태코드)
 * 작성자 : 권준호
 * 작성일 : 2012.04.22
 ----------------------------------------------------------------------------------------------*/
function gf_GetSignStatus(aSignId) {
	if (aSignId == null || aSignId == "")
		return "";
	//var docStsCd = gf_GetSingleData("sign.getSignStatus", "signId=" + aSignId, "docStsCd", false);
	//새로운 결재 모듈로 전환 : 2012.01.29 : by jsko
	var docStsCd = gf_GetSingleData("COF0101.getSignStatus", "signId=" + aSignId, "docStsCd", false);
	if(gf_IsNull(docStsCd)) docStsCd = "D00";
	return docStsCd;
}


 /*----------------------------------------------------------------------------------------------
 * 시스템   : 내부결재
 * 설명   :  임시 데이터셋을 생성함.(공통코드 검색용)
 * 파라미터 : - form : 데이터셋이 생성될 폼.
 *             - datasetName : 데이터셋명.
 *             - columns : 데이터셋에 포함될 컬럼 정보
 * 리턴값   : dataset
 * 작성자 : 권준호
 * 작성일 : 2012.04.22
 ----------------------------------------------------------------------------------------------*/
function getDummyDataset(form, datasetName, columns){
	if(typeof(form[datasetName]) == "undefined"){
		var dummyDataset = new Dataset;
		dummyDataset.name = datasetName;
		form.addChild(datasetName, dummyDataset);
	}
	if(!gf_IsNull(columns)){
		for(var i = 0 ; i < columns.length; i++){
			var name = columns[0].name;
			var type = columns[0].type;
			var size = columns[0].size;
			if(gf_IsNull(size))
				dummyDataset.addColumn( name, type, size );
			else
				dummyDataset.addColumn( name, type );
		}
	}
	return form[datasetName];
}

/*----------------------------------------------------------------------------------
 * 시스템   : 내부결재
 * 설명 		: 결재 문서 수정 가능 여부 판단
 * 파라미터	: - aObj : string(signId):dataset()
 *                - aDocStsCd : 결재문서 상태코드
 *                - button : object(컨트롤 될 결재문서 버튼)
 * 리턴값 	    : boolean(결재 문서 수정 가능 여부)
 * 작성자 		: 홍두희
 * 작성일 		: 2011.10
----------------------------------------------------------------------------------*/
function gf_IsEditSign(aObj, aDocStsCd, button) {
	var rslt;
	var signId;
	var docStsCd;
	var signInfo = new Array();
	var dsDocSts = getDummyDataset(this, "ds_dummy_docsts");
	var signBtns = gf_CompSelector(this, "btn_SignRequest*");
	button = typeof(button) == "undefined" ? "btn_SignRequest" : button;
	//var signRequestBtns = this.getElementsByTagAttribute("Buttom", "id", "btn_SignRequest*");
	for(var index in signBtns){
		gf_EnableButton(signBtns[index].name, false);
	}
	if(gf_IsNull(aObj)) {
		afrm_WorkSet.getActiveFrame().form.stc_ViewSign.style.border = "0";
		afrm_WorkSet.getActiveFrame().form.btn_ViewSign.visible = false;
		afrm_WorkSet.getActiveFrame().form.stc_ViewSign.text = "";
		gf_EnableButton(button, true);
		return true;
	} else if (typeof (aObj) == "string") {
		signId = aObj;
		if( !gf_IsNull(aDocStsCd) ) {
			docStsCd = aDocStsCd;
		} else {
			docStsCd = gf_GetSignStatus(signId);
		}
	} else {	// dataset
		signId = aObj.getColumn(aObj.rowposition, "signId");
		docStsCd = aObj.getColumn(aObj.rowposition, "docStsCd");
	}
	signInfo[0] = 'signId=' + signId;
	signInfo[1] = 'docStsCd=' + docStsCd;
	if (gf_IsNull(docStsCd) || docStsCd == 'D00' || docStsCd == 'D04' || docStsCd == 'D05' || docStsCd == 'D06') {
		gf_EnableButton(button, true);
		//gf_Trace('signId : ' + signId + '/docStsCd : ' + docStsCd + ' => save o, sign o');
		rslt = true;
	} else {
		gf_EnableButton(button, false);
		//gf_Trace('signId : ' + signId + '/docStsCd : ' + docStsCd + ' => save x, sign x');
		rslt = false;
	}
	try{
		if (gf_IsNull(docStsCd) || docStsCd == 'D00' || docStsCd == 'D06') {
			afrm_WorkSet.getActiveFrame().form.btn_ViewSign.visible = false;
		} else {
			afrm_WorkSet.getActiveFrame().form.btn_ViewSign.visible = true;
		}
	}catch(e){gf_Trace("D00 || D06 : " + e);}
	if (gf_IsNull(docStsCd) || docStsCd == "D00"){
		try{
		afrm_WorkSet.getActiveFrame().form.stc_ViewSign.style.border = "0";
		afrm_WorkSet.getActiveFrame().form.stc_ViewSign.text = "";
		afrm_WorkSet.getActiveFrame().form.stc_ViewSign.visible = false;
		}catch(e){gf_Trace("D00 : " + e.description);}
	}else {
		gf_SetCommCdDataset( dsDocSts.name, "CO410"  );
		try{
		afrm_WorkSet.getActiveFrame().form.stc_ViewSign.style.border = "0";
		afrm_WorkSet.getActiveFrame().form.stc_ViewSign.visible = true;
		afrm_WorkSet.getActiveFrame().form.stc_ViewSign.background.image = "img::ico_sign_"+ docStsCd +".png";
		afrm_WorkSet.getActiveFrame().form.stc_ViewSign.text = dsDocSts.getColumn(dsDocSts.findRow("code", docStsCd), "value") ;
		}catch(e){gf_Trace("D00 !!! : " + e.description);}
	}
	gf_SetSignInfo(signInfo);
	return rslt;
}

/*----------------------------------------------------------------------------------
 * 시스템   : 내부결재
 * 설명 		: 결재정보 기록
 * 파라미터	: - aObj : array(signId, 결재문서상태)
 * 리턴값 	    : N/A
 * 작성자 		: 홍두희
 * 작성일 		: 2011.10
----------------------------------------------------------------------------------*/
function gf_SetSignInfo(signInfo) {
	gf_Trace("gf_SetSignInfo start!!! : " + gds_Sign);
	gds_Sign.clearData();
	gds_Sign.addRow();
	for(var i=0;i<signInfo.length;i++) {
		var tmpArry = signInfo[i].split('=');
		gds_Sign.setColumn(0, tmpArry[0], tmpArry[1]);
	}
	gf_Trace("gf_SetSignInfo end!!! : " + gds_Sign);
}

/*----------------------------------------------------------------------------------
 * 설명 : 권한 정보를 참조한 결재 상태에 따른 공통 버튼 제어   - 사용안함
 *
 * <pre>
 * ex) gf_EnableButtonBySign(aSignId)
 * </pre>
----------------------------------------------------------------------------------*/
function gf_EnableButtonBySign(aSignId) {
	var v_SignEditable = gf_IsEditSign(ds_DataSet.getColumn(ds_DataSet.rowposition, 'signId'), 'signId');
	if(v_SignEditable == false) {
		for ( var i = 0; i < fv_AllObjs.length; i++) {
			if(gf_GetElementType(fv_AllObjs[i]) == "BUTTON") {
				gf_EnableObjs(fv_AllObjs[i], false);
			}
		}
	} else {
		// 결재 정보로 활성화 처리한 후
		for ( var i = 0; i < fv_AllObjs.length; i++) {
			if(gf_GetElementType(fv_AllObjs[i]) == "BUTTON") {
				gf_EnableObjs(fv_AllObjs[i], true);
			}
		}
		// 개인 정보로 filtering
		for ( var i = 0; i < fv_AllObjs.length; i++) {
			if(gf_GetElementType(fv_AllObjs[i]) == "BUTTON") {
				gf_PrivDetail(fv_AllObjs[i]);
			}
		}
	}
	return v_SignEditable;
}

/*----------------------------------------------------------------------------------
 * 설명 : 권한 정보를 참조한 결재 상태에 따른 공통 버튼 제어  -- 사용안함
 *
 * <pre>
 * ex) gf_EnableButtonByDocStats(docStsCd)
 * </pre>
----------------------------------------------------------------------------------*/
function gf_EnableButtonByDocStats(docStsCd) {
	if (docStsCd == 'D00' || docStsCd == 'D04' || docStsCd == 'D06') {
		for ( var i = 0; i < fv_AllObjs.length; i++) {
			if(gf_GetElementType(fv_AllObjs[i]) == "BUTTON") {
				gf_EnableObjs(fv_AllObjs[i], true);
			}
		}
		return true;
	} else {
		// 결재 정보로 활성화 처리한 후
		for ( var i = 0; i < fv_AllObjs.length; i++) {
			if(gf_GetElementType(fv_AllObjs[i]) == "BUTTON") {
				gf_EnableObjs(fv_AllObjs[i], true);
			}
		}
		// 개인별 정보로 filtering
		for ( var i = 0; i < fv_AllObjs.length; i++) {
			if(gf_GetElementType(fv_AllObjs[i]) == "BUTTON") {
				gf_PrivDetail(fv_AllObjs[i]);
			}
		}
		return false;
	}
}


/**
 * 해당 서비스에서 넘어온 String로 되어 있는 파라메터를 JSONObject로 변환함.
 * @params str(String)
 * @returns param(JSONObject)
 * @author 권준호
 * @since  2013-04-01
 * @version 1.0
 */
function gf_LegacyInfoToJSON(str){
	if(typeof(str) == "undefined")
		return {};

	var paramsStr = str.split("||");
	var param = {};
	var i = 0 ;
	while( i < paramsStr.length){
		var paramStr = paramsStr[i];
		var index = paramStr.indexOf(":");
		var key = paramStr.substr(0, index);
		var val = paramStr.substr(index+1);
		var isArray = val.indexOf("^") > -1 ? true : false;
		var is2Array = false;

		if(!isArray){
			param[key] = val;
		}else{
			is2Array = val.indexOf("##") > -1 ? true : false;
			if(!is2Array){
				param[key] = val.split("^");
			}else{
				var val2 = val.split("##");
				var j = 0;
				while(j < val2.length){
					val2[j] = val2[j].split("^");
					j++;
				}
			}
		}

		i++;
	}
	return param;
}

/**
* @class 결재문서를 인쇄한다.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function gf_PrintDoc(v_signView, ds_SignDoc, ds_SignDocDtl, ds_Signln, ds_Readtn, ds_MSign, ds_SignForm, ds_FileList, ds_SignDocAtch, v_signServerMsg){
	if(v_signView.getType() == "S01" || v_signView.getType() == "S02"){ // 웹에디터/웹링크일 경우
		var dataSets = {
			ds_SignDoc : ds_SignDoc.getAll(),	//결재문서 정보
			ds_SignDocDtl : ds_SignDocDtl.getAll(),	//결재문서 상세정보
			ds_Signln : ds_Signln.getAll(),		//결재선 정보
			ds_Readtn : ds_Readtn.getAll(),		//회람자 정보
			ds_MSign : ds_MSign.getAll(),		//이단결재 정보
			ds_SignForm : ds_SignForm.getAll(),	//양식 정보
			ds_FileList : ds_FileList.getAll(),
			ds_SignDocAtch : ds_SignDocAtch.getAll()
		};
		v_signView.print(dataSets);
	}else if( v_signView.getType() == "S04"){
		v_signView.print(v_signServerMsg);
	}else if(v_signView.getType() == "S03"){
		var dataSets = {
				ds_SignDoc : ds_SignDoc,	//결재문서 정보
				ds_Signln : ds_Signln,		//결재선 정보
				ds_MSign : ds_MSign,		//이단결재 정보
				ds_SignForm : ds_SignForm
		//양식 정보
			};
		v_signView.print(dataSets);
	}else{
		v_signView.print();
	}
}

function gf_RefreshLeftMenu(){
	if(gv_IsTablet == true){   //타블릿일 경우 메뉴 리플레쉬
		//var expendMenuIds = f_GetExpendMenu();
		loadMenu(false);
		//f_ExpendMenus(expendMenuIds);
	}else{
		var leftFrame = window.parent.left;
		//var expendMenuIds = leftFrame.f_GetExpendMenu();
		leftFrame.loadMenu(false);
		//leftFrame.f_ExpendMenus(expendMenuIds);
	}
}

/**
* @class 결재선 데이터 갱신
*
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function gf_AssembleSignView(data){
	var ap_lcnt = data.ap_lcnt;
	var ap_rcnt = data.ap_rcnt;
	var as_cnt = data.as_cnt;
	var as_yn = data.as_yn;

	// 전체 결재선을 숨김.
	$("#left_signln .signln_pos th").css("display", "none");
	$("#left_signln .signln_img td").css("display", "none");
	$("#left_signln .signln_nm td").css("display", "none");
	$("#left_signln .signln_dt td").css("display", "none");

	$("#right_signln .signln_pos th").css("display", "none");
	$("#right_signln .signln_img td").css("display", "none");
	$("#right_signln .signln_nm td").css("display", "none");
	$("#right_signln .signln_dt td").css("display", "none");

	$("#signAssistor").css("display", "none");
	$("#signAssistorVal").html("");

	// ap_lcnt의 갯수만큼 결재선을 표시함.
	for(var i = 0 ; i < ap_lcnt; i++){
		var imgUrl = data["ap_img1_"+(i+1)];
		$([
			$("#left_signln .signln_pos th")[i],
			$("#left_signln .signln_img td")[i],
			$("#left_signln .signln_nm td")[i],
			$("#left_signln .signln_dt td")[i]
		]).css("display", "");

		var signDt = data["ap_dt1_"+(i+1)];
		if(signDt == "S05"){
			signDt = "<span style=\"color:#FF0000; font-weight: bold;\">" + gf_FindLang("반려") + "</span>";
		}else if(signDt == "S06"){
			signDt = "<span style=\"color:#FF0000; font-weight: bold;\">" + gf_FindLang("회수") + "</span>";
		}else if(signDt == "S10"){
			signDt = "<span style=\"color:#0000FF; font-weight: bold;\">" + gf_FindLang("전결") + "</span>";
		}

		$("#left_signln .signln_pos th:eq("+i+")").text(data["ap_pos1_"+(i+1)]);
		$("#left_signln .signln_nm td:eq("+i+")").text(data["ap_sign1_"+(i+1)]);
		$("#left_signln .signln_dt td:eq("+i+")").html(signDt);
		if(gf_IsNull(imgUrl))
			$("#left_signln .signln_img td:eq("+i+")").html();
		else{
			var imgLoadFunc = function(index){
				var signlnImg = $("#left_signln .signln_img td:eq("+index+")");
				var imgTag = $("<img/>");
				signlnImg.empty();
				signlnImg.append(imgTag);
				try{
				imgTag.bind("load", function(e) {
						if(imgTag[0].complete
					        || imgTag[0].readyState == "complete"
				            || imgTag[0].readyState == 4)
							imgTag.show();
					})
					.hide()
					.css({width:"80%", height:"100%"})
					.attr("src", imgUrl);
				}catch(e){

				}
			};
			imgLoadFunc(i);
		}
	}

	// ap_rcnt의 갯수만큼 결재선을 표시함.
	for(var i = 0 ; i < ap_rcnt; i++){
		var imgUrl = data["ap_img2_"+(i+1)];
		$([
			$("#right_signln .signln_pos th")[i],
			$("#right_signln .signln_img td")[i],
			$("#right_signln .signln_nm td")[i],
			$("#right_signln .signln_dt td")[i]
		]).css("display", "");

		var signDt = data["ap_dt2_"+(i+1)];
		if(signDt == "S05"){
			signDt = "<span style=\"color:#FF0000; font-weight: bold;\">" + gf_FindLang("반려") + "</span>";
		}else if(signDt == "S06"){
			signDt = "<span style=\"color:#FF0000; font-weight: bold;\">" + gf_FindLang("회수") + "</span>";
		}else if(signDt == "S10"){
			signDt = "<span style=\"color:#0000FF; font-weight: bold;\">" + gf_FindLang("전결") + "</span>";
		}

		$("#right_signln .signln_pos th:eq("+i+")").text(data["ap_pos2_"+(i+1)]);
		$("#right_signln .signln_nm td:eq("+i+")").text(data["ap_sign2_"+(i+1)]);
		$("#right_signln .signln_dt td:eq("+i+")").html(signDt);
		if(gf_IsNull(imgUrl))
			$("#right_signln .signln_img td:eq("+i+")").html();
		else{
			var imgLoadFunc = function(index){
				var signlnImg = $("#right_signln .signln_img td:eq("+index+")");
				var imgTag = $("<img/>");
				signlnImg.empty();
				signlnImg.append(imgTag);
				try{
				imgTag.bind("load", function(e) {
						if(imgTag[0].complete
					        || imgTag[0].readyState == "complete"
				            || imgTag[0].readyState == 4)
							imgTag.show();
					})
					.hide()
					.css({width:"80%", height:"100%"})
					.attr("src", imgUrl);
				}catch(e){

				}
			};
			imgLoadFunc(i);
		}
	}

	//결재방을 화면에 출력함.
	$("#appr_line1").css("display", "block");
	$("#appr_line2").css("display", "block");

	if(as_yn == "Y"){
		$("#signAssistor").css("display", "");
		var asHtml = "";

		for(var i = 1 ; i <= as_cnt; i++){
			if(asHtml != "") asHtml += " , ";

			//협의자 가져옴.
			asHtml += data["as2_"+i];
		}
		$("#signAssistorVal").html(asHtml);
	}
}
/**
* @class 결재순번을 한칸씩 밀어냄.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function gf_RightShiftSignln(ds_Signln){
	for(var i = 1 ; i < ds_Signln.size(); i++){
		var signSeq = Number(ds_Signln.get(i, "signSeq"));
		signSeq += 1;
		ds_Signln.set(i, "signSeq", signSeq);
	}
}

/**
* @class 결재순번을 한칸씩 당김.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function gf_LeftShiftSignln(ds_Signln){
	for(var i = 1 ; i < ds_Signln.size(); i++){
		var signSeq = Number(ds_Signln.get(i, "signSeq"));
		signSeq -= 1;
		ds_Signln.set(i, "signSeq", signSeq);
	}
}

/**
* @class 고정사용자에 들어 있는 결재선데이터를 결재선 데이터셋 컬럼명에 맞게 변경하여 삽입함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function gf_FixUserToSignln(ds_FixUser, index, ds_Signln, signId, signSeq, signTpCd){
	var insertPos = signSeq == 2 ? 1 : 2;
	ds_Signln.insert(insertPos, {
		signId: signId,
		signSeq: signSeq,
		signTpCd: signTpCd,
		signUserId: ds_FixUser.get(index, "userId"),
		signUserNm: ds_FixUser.get(index, "userNm"),
		psignUserNm: "",
		apperPositCd: ds_FixUser.get(index, "positNm"),
		apperPositNm: ds_FixUser.get(index, "positNm"),
		perpsignPositNm:"",
		apperRpswrkCd: ds_FixUser.get(index, "rpswrkNm"),
		apperRpswrkNm: ds_FixUser.get(index, "rpswrkNm"),
		apperOrgCd: ds_FixUser.get(index, "orgCd"),
		apperOrgNm: ds_FixUser.get(index, "orgNm"),
		orgChrcCls: ds_FixUser.get(index, "orgChrcCls"),
		canDelete: "N"
	});
}

/**
* @class 고정사용자에 들어 있는 회람데이터를 회람자 데이터셋 컬럼명에 맞게 변경하여 삽입함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function gf_FixUserToReadtn(ds_FixUser, index, ds_Readtn, signId, readtnTp){
	var userCls = "P";

	if(readtnTp == "G"){
		userCls = ds_FixUser.get(index, "orgCd").length == 5 ? "O" : "G"; // 부서(O)/그룹(G)로 타입을 변경함.

		ds_Readtn.add({
			canDelete: "N",
			fileAtchId: "",
			fileId: "",
			fileNm: "",
			filePath: "",
			fileSize: "",
			readtnDt: "",
			readtnId: "",
			readtnStsCd: "",
			readtnTp: readtnTp,
			readtnTpNm: "",
			readtnUserCls: userCls,
			readtnUserId: "",
			readtnUserNm: "",
			readtnUserOrgCd:ds_FixUser.get(index, "orgCd"),
			readtnUserOrgNm: ds_FixUser.get(index, "orgNm"),
			readtnUserPositCd: "",
			readtnUserRpswrkCd: "",
			regDt: "",
			signId: signId,
			signOpnCont: "",
			stsCd: ""
		});
	}else{
		userCls = gf_IsNull(ds_FixUser.get(index, "userId"))? "O" : "P"; // 부서(O)/사원(P)로 타입을 변경함.
		ds_Readtn.add({
			canDelete: "N",
			fileAtchId: "",
			fileId: "",
			fileNm: "",
			filePath: "",
			fileSize: "",
			readtnDt: "",
			readtnId: "",
			readtnStsCd: "",
			readtnTp: readtnTp,
			readtnTpNm: "",
			readtnUserCls: userCls,
			readtnUserId: ds_FixUser.get(index, "userId"),
			readtnUserNm: ds_FixUser.get(index, "userNm"),
			readtnUserOrgCd:ds_FixUser.get(index, "orgCd"),
			readtnUserOrgNm: ds_FixUser.get(index, "orgNm"),
			readtnUserPositCd: ds_FixUser.get(index, "positNm"),
			readtnUserRpswrkCd: ds_FixUser.get(index, "rpswrkNm"),
			regDt: "",
			signId: signId,
			signOpnCont: "",
			stsCd: ""
		});
	}
}
function gf_SetSignSeq(ds_Signln){

	//결재유형 지정 여부 체크
	var rowCnt = ds_Signln.size();
	var apSeq = 1;//일반결재 순번 : 결재 <- 향후 다른 결재 유형이 추가될 수 있음
	var asSeq = 1;//협조결재 순번 : 협조

	var v_signCnt = 0;
	var v_assistCnt = 0;
	var v_clsChangeCnt = 0;

	var isAsType = false;

	//이단결재 양식 일 경우 현장에서 본사 협의자 지정 가능 : jsko : 0712
	for ( var i = 0; i< rowCnt; i++ ) {

		//결재자 부가 정보 처리 :  signSeq
		if(ds_Signln.get(i, "signTpCd" ) != 'T03'){
			//협조 다음 결재자일 경우 순번을 1 증가시킨다.
			if(isAsType){
				apSeq = apSeq + 1;
				asSeq = asSeq + 1;
			}

			ds_Signln.set(i,'signSeq', apSeq);
			apSeq = apSeq + 1;
			asSeq = asSeq + 1;

			isAsType = false;
			v_signCnt++;
		}else{
			ds_Signln.set(i,'signSeq', asSeq);
			isAsType = true;
			v_assistCnt++;
		}
	}
}

/**
 * @class 결재본문 처리
 * @params parent(Element)
 * 		   type(String)
 *         param(JSONObject)
 * @returns this{SignViewRenderer}
 * @author 권준호
 * @since  2013-04-01
 * @version 1.0
 */
var SignViewRenderer = function(parent, type, param, height){
	this.viewEle = parent;
	this.renderer = null;
	this.type = param.signFormTpCd;

	if(this.type == "F01" || this.type == "F02" || this.type == "F03"){
		this.renderer = null;
	}else if(this.type == "F05" || this.type == "S01" || this.type == "S02"){ // TODO: F05 삭제, 결재본문 : 웹에디터
		this.renderer = new WysiwygRenderer(parent, param, height);
	}else if(this.type == "F08" || this.type == "S03"){ // TODO: F08 삭제결재본문 : 레포트
		this.renderer = new ReportRenderer(parent, param, height);
	}else if(this.type == "F07" || this.type == "S04"){ // TODO: F07 삭제결재본문 : 외부 WEB 문서
		this.renderer = new WebLinkRenderer(parent, param, height);
	}else if(this.type == "F06" || this.type == "S05"){ // TODO: F06 삭제결재본문 : 첨부문서
		this.renderer = new AttachRenderer(parent, param, height);
	}else if(this.type == "F04"){ // TODO: F04 삭제결재본문 : 본문없음
		this.renderer = new HiddenRenderer(parent, param, height);
	}else{
		throw "존재하지 않는 문서 형식입니다.";
	}
};

/**
 * 결재 본문 내용을 생성함.
 * @author 권준호
 * @since  2013-04-01
 * @version 1.0
 */
SignViewRenderer.prototype.render = function(){
	this.renderer.render();
};

/**
 * 결재 본문 내용을 생성함.
 * @author 권준호
 * @since  2013-04-01
 * @version 1.0
 */
SignViewRenderer.prototype.reload = function(){
	this.renderer.clear();
	this.renderer.render();
};

/**
 * 결재 본문의 출력형태코드를 출력함.
 * @author 권준호
 * @since  2013-04-01
 * @version 1.0
 */
SignViewRenderer.prototype.getType = function(){
	return this.type;
};

/**
 * 결재 본문 내용을 인쇄함.
 * @author 권준호
 * @since  2013-04-01
 * @version 1.0
 */
SignViewRenderer.prototype.print = function(datas){
	this.renderer.print(datas);
};

/**
 * 에디터 본문 내용을 출력함.
 * - ### 웹에디터만 지원됨 ###
 * @author 권준호
 * @since  2013-04-01
 * @version 1.0
 */
SignViewRenderer.prototype.getData = function(){
	return this.renderer.getData();
};

/**
 * 에디터 Object 출력.
 * - ### 웹에디터만 지원됨 ###
 * @author 권준호
 * @since  2013-04-01
 * @version 1.0
 */
SignViewRenderer.prototype.getEditor = function(){
	return this.renderer.getEditor();
};

SignViewRenderer.prototype.resize = function(){
	this.renderer.resize();
};

/**
 * 에디터 이미지 업로드
 * - ### 굿센 에디터만 지원됨 ###
 * @author 권준호
 * @since  2013-04-01
 * @version 1.0
 */
SignViewRenderer.prototype.imgUpload = function(){
	return this.renderer.imgUpload();
};

/**
 * @class 결재본문 처리
 * @params parent(Element)
 *         param(JSONObject)
 * @returns this{WysiwygRenderer}
 * @author 권준호
 * @since  2013-04-01
 * @version 1.0
 */
var WysiwygRenderer = function(parent, param, height){
	this.parent = parent;
	this.param = param;
	this.height = height;
	this.editor = null;
	this.editorView = null;

};
WysiwygRenderer.prototype = {
	/**
	 * 결재 본문에 에디터/HTML을 출력함
	 * 		- 기안창 : 본문에 위지윅에디터를 생성하고 양식이 존재할경우 양식을 출력함
	 * 		- 결재문서창 : 본문에 기존에 저장되어 있던 HTML문서를 출력함.
	 * @author 권준호
	 * @since  2013-04-01
	 * @version 1.0
	 */
	render : function(){
		var params = this.param;
		var viewEle = this.parent;
		var that = this;

		if(params.viewType == "DRAFT"){
			if(typeof($.browser.msie) != "undefined"){

				// IE일경우 GoodSen 에디터를 실행시킴
				var editorElm = $("<div id=\"editor\" style=\"width:100%; height:100%; display:block;\"></div>");
				viewEle.empty();
				viewEle.append(editorElm);

				// 태그프리 에디터
				var editor = new tweditor(editorElm, null, function(a,b,c,d){
					if(typeof(editor) == "undefined")
						return;
					that._editorLoader({editor : editor});
					//twe.HtmlValue = params.signCont;
//					alert(params.formCont);
					that.editor.setData(params.formCont);
				});
				// 굿센 에디터
//				var editor = new GoodEditor(editorElm, null, function(file){
//					if(file == "NEW"){
//						that._editorLoader({editor : editor});
//					    that.editor.setData(params.formCont);
//					    that.resize();
//					}
				//$(editor.editor[0].body).css("margin", "20px 35px 20px 40px")
			}else{
				viewEle.html("<textarea id=\"editor\"></textarea>");
				CKEDITOR.replace( 'editor', {
					resize_enabled : false,
					height : "100%",
					//height : viewEle.height() + "px",
					filebrowserUploadUrl: gContextPath+'/co/common/file/uploadWebFile.xpl?type=editor',
					contentsCss: 'body {margin: 20px 35px 20px 40px; }',
					on: {
						'instanceReady': function(e){
					         //evt.editor.execCommand( 'maximize' );
					         that._editorLoader(e);
						     that.editor.setData(params.formCont);
						     that.resize();
					    }
					}
				});
			}

		}else if(params.viewType == "REDRAFT" || params.viewType == "EDIT"){
			if(typeof($.browser.msie) != "undefined"){	// IE일경우 GoodSen 에디터를 실행시킴
				//var editorElm = $("<div id=\"editor\"></div>");
				var editorElm = $("<div id=\"editor\" style=\"width:100%; height:100%; display:block;\"></div>");
				viewEle.empty();
				viewEle.append(editorElm);

				// 태그프리 에디터
				var editor = new tweditor(editorElm, null, function(a,b,c,d){
					if(typeof(editor) == "undefined")
						return;
					that._editorLoader({editor : editor});
					//twe.HtmlValue = params.signCont;
					that.editor.setData(params.signCont);
				});


				// 굿센 에디터
//				var editor = new GoodEditor(editorElm, null, function(file){
//					if(file == "NEW"){
//						that._editorLoader({editor : editor});
//						that.editor.setData(params.signCont);
//						that.resize();
//					}
//				});
			}else{
				viewEle.html("<textarea id=\"editor\"></textarea>");
				CKEDITOR.replace( 'editor', {
					filebrowserUploadUrl: gContextPath+'/co/common/file/uploadWebFile.xpl?type=editor',
					contentsCss: 'body {margin: 20px 35px 20px 40px; }',
					on: {
						instanceReady: function(e){
					        that._editorLoader(e);
					        that.editor.setData(params.signCont);
						    that.resize();
						}
					}
				});
			}
		}else{
			viewEle.html("<div id=\"editorView\"></div>");
			this.editorView = $("#editorView");
			this.editorView.css("padding", "20px 20px 10px 20px");
			this.editorView.html(params.signCont);
		}
	},
	/**
	 * 결재 본문에 위지윅에디터를 생성한후 HTML양식이 존재할경우 양식을 에디터에 출력함.
	 * @author 권준호
	 * @since  2013-04-01
	 * @version 1.0
	 */
	_editorLoader : function(e){
		var params = this.param;
		var editor = this.editor = e.editor;
		//alert(editor.editor);
	},
	/**
	 * 결재 본문의 내용을 새창을 열어 인쇄함.
	 * @author 권준호
	 * @since  2013-04-01
	 * @version 1.0
	 */
	print : function(datas){
		var viewParams = DataSet.Util.objectClone(this.param);
		if(this.editorView == null)
			viewParams.signCont = this.editor.getData();
		else
			viewParams.signCont = this.editorView.html();

		datas.params = viewParams;
		if(viewParams.viewType == "DRAFT" || viewParams.viewType == "REDRAFT")
			gf_PostOpen("/common/jsp/sign/drft/draftPrint.jsp?timeline="+ (new Date()).getTime(), "draftPrintPopup", "toolbar=no,scrollbars=yes,width=820,height=700", datas);
		else
			gf_PostOpen("/common/jsp/sign/view/viewPrint.jsp?timeline="+ (new Date()).getTime(), "viewPrintPopup", "toolbar=no,scrollbars=yes,width=820,height=700", datas);
	},
	/**
	 * 레포트 본문 생성 관련 내용을 삭제함.
	 * @author 권준호
	 * @since  2013-04-01
	 * @version 1.0
	 */
	clear : function(){
		var viewEle = this.parent;
		viewEle.empty();
	},
	/**
	 * 본문화면을 부모창의 크기에 맞게 늘림.
	 * @author 권준호
	 * @since  2013-04-01
	 * @version 1.0
	 */
	resize : function(){
		var params = this.param;
		var editor = this.editor;

		if(params.viewType == "VIEW"){

		}else if(editor instanceof GoodEditor){
			editor.editor.height = this.parent.height() + "px";
		}else{
			editor.resize(editor.config.width, this.parent.height(), false, false);
		}
	},
	/**
	 * 에디터 본문 내용을 출력함.
	 * @author 권준호
	 * @since  2013-04-01
	 * @version 1.0
	 */
	getData : function(){
		return this.editor.getData();
	},
	/**
	 * 에디터 Object.
	 * @author 권준호
	 * @since  2013-04-01
	 * @version 1.0
	 */
	getEditor : function(){
		return this.editor;
	},
	/**
	 * 에디터 파일 업로드 굿센 에디터만 해당 .
	 * @author 권준호
	 * @since  2013-04-01
	 * @version 1.0
	 */
	imgUpload : function() {
		var params = this.param;
		var that = this;
		if(params.viewType == "EDIT" || params.viewType == "DRAFT" || params.viewType == "REDRAFT" ){
			if(typeof($.browser.msie) != "undefined"){	// IE일경우 GoodSen 에디터를 실행시킴
				var editorObj = that.getEditor();
				if ( editorObj instanceof GoodEditor ) {
					// 업로드 수행
					var retVal = editorObj.editor.HttpSendImg(gv_cmasComm.cmasUrl+"/co/common/file/uploadWebFile.xpl?type=geditor", "utf-8");
					if ( retVal < 0 ) {
						var nVar = editorObj.editor.GetLastError();
						gf_AlertMsg(nVar);
					}
				}
				else if ( editorObj instanceof tweditor ) {

					// 이미지 업로드 처리
					var files = editorObj.editor.GetLocalFiles();
					if(files != "")
					{
						var filesList = files.split(";");
						for(i=0; i<filesList.length;i++)
						{
							if(filesList[i].substr(0,2)!="\\\\")
							{
								editorObj.editor.AddFile(filesList[i]);
							}
						}
						var mainDocument = editorObj.editor.getDom();
						var targetElements = mainDocument.getElementsByTagName("img");
						var targetElementsLength = targetElements.length;

						gf_Transaction("SELECT_FILEATCHID", "/co/common/file/retrieveFileAtchId.xpl?saveYn=Y", {}, {}, "f_FileList_Callback", false);
						editorObj.editor.AddParameter("fileAtchId",fv_FileAtchId);

						// 업로드 수행
						var retVal = editorObj.editor.StartUpload();
						retVal = $("#uploadRst").val();


						for(var i = 0; i < targetElementsLength; i++) {
							var targetElement =targetElements[i];
							if(targetElement) {
								targetElement.src = gv_cmasComm.cmasUrl+"/co/common/file/downloadFileStream.xpl?fileAtchId="+fv_FileAtchId+"&fileId="+(i+1); //이미지 경로 변경
							}
						}
					} else {
						$("#upType").val("NoUpload");
						var mimeValue = editorObj.editor.MimeEnValue;
						$("#mime_contents").val(mimeValue);
					}

//					var mainDocument = editorObj.editor.getDom();
//					var targetElements = mainDocument.getElementsByTagName("img");
//					var targetElementsLength = targetElements.length;
//
//					gf_Transaction("SELECT_FILEATCHID", "/co/common/file/retrieveFileAtchId.xpl?saveYn=Y", {}, {}, "f_FileList_Callback", false);
//					editorObj.editor.AddParameter("fileAtchId",fv_FileAtchId);
//
//					// 업로드 수행
//					var retVal = editorObj.editor.StartUpload();
//					retVal = $("#uploadRst").val();
//
//
//					for(var i = 0; i < targetElementsLength; i++) {
//						var targetElement =targetElements[i];
//						if(targetElement) {
//							targetElement.src = gv_cmasComm.cmasUrl+"/co/common/file/downloadFileStream.xpl?fileAtchId="+fv_FileAtchId+"&fileId="+(i+1); //이미지 경로 변경
//						}
//					}
					if ( retVal < 0 ) {
//						var nVar = editorObj.editor.GetLastError();
						gf_AlertMsg(nVar);
					}
				}
			}
		}
	}

};

/**
 * @class 결재본문 처리
 * @params parent(Element)
 *         param(JSONObject)
 * @returns this{AttachRenderer}
 * @author 권준호
 * @since  2013-04-01
 * @version 1.0
 */
var AttachRenderer = function(parent, param, height){
	this.parent = parent;
	this.param = param;
	this.height = height;
};
AttachRenderer.prototype = {
	/**
	 * 결재 본문에 타시스템의 결재 본문 화면을 링크함.
	 * 		- 기안창 : 결재양식 문서목록을 링크함
	 * 		- 결재문서창 : 기존에 저장되어 있던 파일의 다운로드링크를 출력함.
	 * @author 권준호
	 * @since  2013-04-01
	 * @version 1.0
	 */
	render : function(){
		var params = this.param;
		var viewEle = this.parent;

		$("#btnPrint").css("display", "none");
		viewEle.parent().css("display", "none");
		//window.resizeTo($(window).width()+40, this.height -100);

		if(params.viewType == "DRAFT" || params.viewType == "REDRAFT"){
			params.btnFormFile.css("display", "block");
			if(params.viewType == "DRAFT")
				params.btnFormFile.trigger("click");
		}
	},
	/**
	 * 파일첨부 결재시 인쇄 없음?
	 * @author 권준호
	 * @since  2013-04-01
	 * @version 1.0
	 */
	print : function(){

	},
	/**
	 * 레포트 본문 생성 관련 내용을 삭제함.
	 * @author 권준호
	 * @since  2013-04-01
	 * @version 1.0
	 */
	clear : function(){
		var viewEle = this.parent;
		viewEle.empty();
	}
};

/**
 * @class 결재본문 처리
 * @params parent(Element)
 *         param(JSONObject)
 * @returns this{HiddenRenderer}
 * @author 권준호
 * @since  2013-04-01
 * @version 1.0
 */
var HiddenRenderer = function(parent, param, height){
	this.parent = parent;
	this.param = param;
	this.height = height;
};
HiddenRenderer.prototype = {
	/**
	 * 결재 본문에 타시스템의 결재 본문 화면을 링크함.
	 * 		- 기안창 : 결재양식 문서목록을 링크함
	 * 		- 결재문서창 : 기존에 저장되어 있던 파일의 다운로드링크를 출력함.
	 * @author 권준호
	 * @since  2013-04-01
	 * @version 1.0
	 */
	render : function(){
		var params = this.param;
		var viewEle = this.parent;

		$("#btnPrint").css("display", "none");
		viewEle.parent().css("display", "none");
		//window.resizeTo($(window).width()+40, this.height -100);

	},
	/**
	 * 파일첨부 결재시 인쇄 없음?
	 * @author 권준호
	 * @since  2013-04-01
	 * @version 1.0
	 */
	print : function(){

	},
	/**
	 * 레포트 본문 생성 관련 내용을 삭제함.
	 * @author 권준호
	 * @since  2013-04-01
	 * @version 1.0
	 */
	clear : function(){
		var viewEle = this.parent;
		viewEle.empty();
	}
};

/**
 * @class 결재본문 처리
 * @params parent(Element)
 *         param(JSONObject)
 * @returns this{WebLinkRenderer}
 * @author 권준호
 * @since  2013-04-01
 * @version 1.0
 */
var WebLinkRenderer = function(parent, param, height){
	this.parent = parent;
	this.param = param;
	this.height = height;
};
WebLinkRenderer.prototype = {
	/**
	 * 결재 본문에 타시스템의 결재 본문 화면을 링크함.
	 * @author 권준호
	 * @since  2013-04-01
	 * @version 1.0
	 */
	render : function(){
		var params = this.param;
		var viewEle = this.parent;

		var form = $("<form action=\""+params.signDocfile+"\" method=\"post\" target=\"webBox\"></form>");
		viewEle.html("<iframe name=\"webBox\" id=\"webBox\" style=\"border: 0px; width:100%; height:100%\"  frameborder=\"0\" ></iframe>")
			   .append(form);
		for(var key in params){
			form.append("<input type=\"hidden\" name=\"" + key + "\" value=\""+ params[key] +"\"/>");
		}
		form.submit();
		form.remove();
	},
	print : function(signServerMsg){
		try{	// IE일 경우 해당 포커스를 기준으로 인쇄됨.
			webBox.focus();
		}catch(e){}
		signServerMsg.print();
	},
	/**
	 * 레포트 본문 생성 관련 내용을 삭제함.
	 * @author 권준호
	 * @since  2013-04-01
	 * @version 1.0
	 */
	clear : function(){
		var viewEle = this.parent;
		viewEle.empty();
	},
	/**
	 * 본문화면을 부모창의 크기에 맞게 늘림.
	 * @author 권준호
	 * @since  2013-04-01
	 * @version 1.0
	 */
	resize : function(){

	}
};

var ReportRenderer = function(parent, param, height){
	this.parent = parent;
	this.param = param;
	this.height = height;
	this.view = null;
	this.printView = null;
	this.hideUpload = null;
	this.toolbar = null;

	this.zoom = 100;

	this.uploader = null;
};
ReportRenderer.prototype = {
	render : function(){
		var params = this.param;
		var viewEle = this.parent;
		var _that = this;

		viewEle.empty();
		var view = this.view = $("<iframe id=\"reportBox\" style=\"overflow:hidden; width:100%; height:100%;\" frameborder=\"0\"></iframe>");
		this.toolbar = $("<div id=\"toolbar\" class=\"btn-area\" style=\"margin:auto; text-align: center; height: 20px; background-color:#FFF; border-bottom:1px solid #DFDFDF;\" ></div>");

		viewEle.append(this.toolbar)
			   .append(this.view);


		this.resize();
		var path = params.signDocfile;
		var datas = $.extend(params, gf_LegacyInfoToJSON(params.legacyInfo));

		/* 레포트에 필요없는 속성은 제거함.*/
		datas.attachInfo = "";

		var url = gf_RexReportSignUrl(status, path, datas);

		var hiddenLoadFunc = function(type, current, total){
			_that._label(type, current, total);
		};

		var hiddenLoadFuncNm = "hiddenLoadFunc" + $.now();
		window[hiddenLoadFuncNm] = hiddenLoadFunc;
		view[0].setAttribute("hiddenLoadFunc", hiddenLoadFuncNm);


//		var v_jsonUrl = gf_UrlQueryToJson(url);
//
//		url = gv_ContextPath + url;
//
//		var form = $("<form action=\""+url+"\" method=\"post\" target=\""+name+"\"></form>");
//		$("body").append(form);
//
//		$("<input type=\"hidden\" name=\"datas\" />")
//			.appendTo(form)
//			.val(JSON.stringify(params));
//
//		// 전역 배열 변수에 팝업 개체 삽입
//		if(parentClose == true)
//			gf_ChildWindow.push(newWin);
//
//		newWin.focus();
//		form.submit();
//		form.detach();



		//ActiveX 생성
		$("#reportBox").attr("src", url);
		this._setToolbar();
	},

	_setToolbar : function(){
		var _that = this;
		var view = this.view;
		var toolbar = this.toolbar;
		var first = $("<a>").attr("href", "#").addClass("btn").css({"margin-right" : "5px", "font-size" : "12px"});
		var prev = $("<a>").attr("href", "#").addClass("btn").css({"margin-right" : "5px", "font-size" : "12px"});
		var curBox = $("<input>").attr("readOnly","readOnly").css({"float": "left", "text-align" : "center", "width": "20px", "border" : 0});
		var space =  $("<span>").text("/").css({"font-size": "13pt", "float": "left", "width": "10px", "text-align" : "center"});
		var totBox = $("<input>").attr("readOnly","readOnly").css({"float": "left", "text-align" : "center", "width": "20px", "border" : 0});
		var next = $("<a>").attr("href", "#").addClass("btn").css({"margin-right" : "5px", "font-size" : "12px"});
		var last = $("<a>").attr("href", "#").addClass("btn").css({"margin-right" : "5px", "font-size" : "12px"});
		var zoomIn = $("<a>").button().attr("href", "#").addClass("btn").css({"margin-left" : "5px", "margin-right" : "5px", "padding": "0 5px"});
		var zoomOut = $("<a>").button().attr("href", "#").addClass("btn").css({"margin-right" : "5px", "padding": "0 5px"});

		$(first, prev, next, last).attr("href", "#").addClass("btn");

		first.text("◀◀").bind("click", function(){
				view[0].contentWindow.RexCtl.MoveFirst();
				_that._label("first");
				return false;
			});
		prev.text("◀").bind("click", function(){
			view[0].contentWindow.RexCtl.MovePrev();
			_that._label("prev");
			return false;
		});
		next.text("▶").bind("click", function(){
			view[0].contentWindow.RexCtl.MoveNext();
			var page = _that.toolbar.find("input:eq(0)").val();
			page = ++page > _that.toolbar.find("input:eq(1)").val() ?  _that.toolbar.find("input:eq(1)").val() : page;
			_that._label("next", page);
			return false;
		});
		last.text("▶▶").bind("click", function(){
			view[0].contentWindow.RexCtl.MoveLast();
			_that._label("last");
			return false;
		});
		zoomIn.text("확대 ").bind("click", function(){
			var zoom = _that.zoom;
			zoom = (zoom += 25) >= 250 ? 250 : zoom;
			_that.zoom = zoom;
			view[0].contentWindow.RexCtl.Zoom(zoom + "%");
			return false;
		});
		zoomOut.text("축소 ").bind("click", function(){
			var zoom = _that.zoom;
			zoom = (zoom -= 25) <= 50 ? 50 : zoom;
			_that.zoom = zoom;
			view[0].contentWindow.RexCtl.Zoom(zoom + "%");
			return false;
		});

		toolbar.append(first)
			   .append(prev)
			   .append(curBox)
			   .append(space)
			   .append(totBox)
			   .append(next)
			   .append(last)
			   .append(zoomIn)
			   .append(zoomOut);

	},

	_label : function(type, current, total){
		var toolbar = this.toolbar;
		var curBox = toolbar.find("input:eq(0)");
		var totBox = toolbar.find("input:eq(1)");
		if(type == "finish"){
			curBox.val(current);
			totBox.val(total);
		}else if(type == "first"){
			curBox.val(1);

		}else if(type == "prev"){
			var page = parseInt(curBox.val());
			page = --page < 1 ? 1 : page;
			curBox.val(page);

		}else if(type == "next"){
			var page = parseInt(curBox.val());
			var lastPage = parseInt(totBox.val());
			page = ++page > lastPage ?  lastPage : page;
			curBox.val(page);

		}else if(type == "last"){
			curBox.val(totBox.val());

		}else{
			curBox.val(current);
		}
	},
	/**
	 * 파일첨부 결재시 인쇄 없음?
	 * @author 권준호
	 * @since  2013-04-01
	 * @version 1.0
	 */
	print : function(dataSets){
		var params = this.param;
		var ds_Signln = dataSets.ds_Signln;
		var ds_SignDoc = dataSets.ds_SignDoc;
		var ds_SignForm = dataSets.ds_SignForm;
		var ds_MSign = dataSets.ds_MSign;

		var path = params.signDocfile;
		var datas = $.extend(params, gf_LegacyInfoToJSON(params.legacyInfo));

		var assembleSignData = gf_AssembleSign(params.viewType, datas, ds_Signln, ds_SignDoc, ds_SignForm, ds_MSign);
		datas = $.extend(datas, assembleSignData);

		var url = gf_RexReportSignUrl(params.viewType, path, datas);

		if(this.printView == null ){
			this.printView = $("<iframe id=\"printView\" style=\"display:none\"></iframe>");
			$("body").append(this.printView);
		}
		url += "&direct_print=true";
		this.printView.attr("src", url);
	},
	/**
	 * 레포트 본문 생성 관련 내용을 삭제함.
	 * @author 권준호
	 * @since  2013-04-01
	 * @version 1.0
	 */
	clear : function(){
		var viewEle = this.parent;
		if(this.printView != null ){
			this.printView.remove();
			this.printView = null;
		}
		viewEle.empty();
	},
	/**
	 * 본문화면을 부모창의 크기에 맞게 늘림.
	 * @author 권준호
	 * @since  2013-04-01
	 * @version 1.0
	 */
	resize : function(){
		this.view.height(this.parent.height()-30);
	},
	download : function(path, fileName){
		var _that = this;

		if(this.uploader == null){
			var callbackName = "ReportRendererCallback_" + (new Date()).getTime();
			window[callbackName] = function(str){_that._upload(str);};

			this.uploader = new upload4Rex("hiduploaddiv");
			this.uploader.initFileUploadComponent("", callbackName);
		}

		var _path = path;
		var _fileName = typeof(fileName) == "undefined"? (new Date().getTime()): fileName;
		var _that = this;
		var doc = document;
		var reportBox = doc.getElementById("reportBox");
		_fileName += ".tmp";

		reportBox.contentWindow.fnFileDownload(function(oRexCtl, sEvent, oArgs){
				_that._download(oRexCtl, sEvent, oArgs);
			}, "htm", _path + "\\" +_fileName);
	},
	_download : function(oRexCtl, sEvent, oArgs){
		var _that = this;
		var _fileName = "C:\\Users\\jun\\log.txt";
		var _viewEle = this.parent;
		setTimeout(function(){
			_that.view.attr("src", "about:blank");
			setTimeout(function(){
//				alert(_fileName);
				_that.uploader.setFileInfo(_fileName);
				_that.uploader.onFileUpload();

			}, 1000);
		}, 1000);
	},
	_upload : function(str){
		alert("html : " + str);
	}
};

function f_upCallback() {
	v_signView.renderer.uploadCallback();
}

/**
 * @class 외부 WEB 화면과 연동하기 위한 통신모듈중 서버모듈
 * @params params(JSONObject)
 * @returns this{SignServerMsg}
 * @author 권준호
 * @since  2013-04-01
 * @version 1.0
 */
var SignServerMsg = function(param){
	this.client = null;
	this.clientUrl = null;
	this.funcs = {
		cf_LinkForm : false ,
		cf_SignCheck : false
	};
	this.param = param == null ? {} : param;
	this.callbacks = {};
};

/**
 * 클라이언트서비스에서 요청이 오는 메시지를 받기위해 대기함.
 * @params e(onMessageEvent)
 * 		   loadCallback(Function)
 * @author 권준호
 * @since  2013-04-01
 * @version 1.0
 */
SignServerMsg.prototype._run = function(e, loadCallback){
	var orgEvent = e.originalEvent;

	this.client = orgEvent.source;
	this.clientUrl = orgEvent.origin;
	var funcs = this.funcs;

	var param = {};

	try{
		param = JSON.parse(orgEvent.data);
	}catch(e){
		return;
	}

	var func = param.func;
	var rsl = param.rsl;
	var callbacks = this.callbacks;
	var callbackName = param.callbackName;

	if(func == "load"){
		if(typeof(loadCallback) != "undefined") loadCallback();
		var execFuncs = rsl.split(",");
		for(var i = 0 ; i < execFuncs.length ; i++){
			if(typeof(funcs[execFuncs[i]]) != "undefined" )
				funcs[execFuncs[i]] = true;
		}
		if(!funcs["cf_LinkForm"])
			return;

		var msg = {
			func : "cf_LinkForm",
			args : this.param
		};
		this.client.postMessage(JSON.stringify(msg), this.clientUrl);

	}else if(func == "cf_SignCheck"){
		if(typeof(callbacks[callbackName]) == "undefined")
			return;

		var callback = callbacks[callbackName];
		delete callbacks[callbackName];
		callback(rsl);
	}else if(func == "getBody"){
		if(typeof(callbacks[callbackName]) == "undefined")
			return;

		var callback = callbacks[callbackName];
		delete callbacks[callbackName];
		callback(rsl);
	}else if(func == "cf_Close"){
		eval(func+"()");
	}else if(func == "cf_Refresh"){
		eval(func+"()");
	}
};
/**
 * 서버모듈을 열고 클라이언트가 접속을 하면 클라이언트에 onload관련 메시지를 발송한다.
 * @params loadCallback(Function)
 * @author 권준호
 * @since  2013-04-01
 * @version 1.0
 */
SignServerMsg.prototype.run = function(loadCallback){
	var _that = this;
	$(window).bind("message", function(e){
		_that._run(e, loadCallback);
	});
};

/**
 * 결재스텝 별로 상태체크 및 결재처리 여부를 묻는다.
 * @params extParam(JSONObject)
 * 		   callback(Function)
 * @author 권준호
 * @since  2013-04-01
 * @version 1.0
 */
SignServerMsg.prototype.signCheck = function(extParam, callback){
	if(!this.funcs["cf_SignCheck"]){
		callback(true);
		return;
	}

	var baseParam = JSON.parse(JSON.stringify(this.param));
	$.extend(baseParam, extParam);

	var callbackName = "callback_"+$.now();
	if(typeof(callback) != "undefined")
		this.callbacks[callbackName] = callback;

	var msg = {
		func : "cf_SignCheck",
		args : baseParam,
		callbackName : callbackName
	};
	this.client.postMessage(JSON.stringify(msg), this.clientUrl);
};

/**
 * 결재스텝 별로 상태체크 및 결재처리 여부를 묻는다.
 * @params extParam(JSONObject)
 * 		   callback(Function)
 * @author 권준호
 * @since  2013-04-01
 * @version 1.0
 */
SignServerMsg.prototype.print = function(extParam, callback){

	var msg = {
		func : "cf_Print"
	};
	this.client.postMessage(JSON.stringify(msg), this.clientUrl);
};

/**
 * 외부웹화면의 내용을 끍어옴 (향후 인쇄시 사용여부에 따라 처리).
 * @params extParam(JSONObject)
 * 		   callback(Function)
 * @author 권준호
 * @since  2013-04-01
 * @version 1.0
 */
SignServerMsg.prototype.getBody = function(callback){
	var baseParam = JSON.parse(JSON.stringif(this.param));
	$.extend(baseParam, extParam);

	var callbackName = "callback_"+$.now();
	if(typeof(callback) != "undefined")
		this.callbacks[callbackName] = callback;

	var msg = {
		func : "cf_GetBody",
		args : baseParam
	};
	this.client.postMessage(JSON.stringify(msg), this.clientUrl);
};



/*----------------------------------------------------------------------------------
 * 시스템   : 내부결재
 * 설  명    : Parameter Object를 서버 전송을 위해 String으로 변환한다.
 *              [예]
 *				param Object
 *				- param.sign_id = "pm000000005810";
 *				- param.title = "내부결재"
 *              String
 *              - sign_id=pm000000005810 title=내부결재
 * 리턴값   :
 * 작성자   : 고준석
 * 작성일   : 2012.01.11
 ----------------------------------------------------------------------------------------------*/
function gf_ObjectToString(params){
	if(!params) return "";
	var parse = function(obj){
		var args = [], val;
		for(var key in obj){
			if(obj.hasOwnProperty(key)){
				val = obj[key];
				if(val && typeof val == "object"){
					args[args.length] = key + ":{ " + gf_SetQuote(arguments.callee(t).join(", ")) + "}";
				}else{
					args[args.length] = [key+"="+gf_SetQuote(val.toString())];
				}
			}
		}
		return args;
	};

	return parse(params).join(" ");
}

/*----------------------------------------------------------------------------------
 * 시스템   : 내부결재
 * 설  명    : 업무에서 받은 값을 JSON형태로 데이터 변환.
 *
 * 파라미터 : params
 *             - dataset :  url파라메터가 들어갈 dataset
 *             - urlQuery :  url파라메터
 *
 * 리턴값   : void
 * 작성자   : 권준호
 * 작성일   : 2012.01.11
 ----------------------------------------------------------------------------------------------*/
function gf_UrlQueryToDataset(dataset, urlQuery){
	gds_Temp.clear();
	gds_Temp.addColumn('key', 'string');
	gds_Temp.addColumn('value', 'string');
	var querys = urlQuery.split("&");
	for(var i = 0 ; i < querys.length ; i++){
		var query = querys[i];
		var divIdx = query.indexOf("=");
		var key = query.substring(0, divIdx);
		var value = query.substring(++divIdx, query.length);
		if(!gf_IsNull(key)){
			gds_Temp.addRow();
			var rowIdx = gds_Temp.getRowCount()-1;
			gds_Temp.setColumn(rowIdx, "key", key);
			gds_Temp.setColumn(rowIdx, "value", value);
		}
	}
}

/*----------------------------------------------------------------------------------
 * 시스템   : 내부결재
 * 설  명    : URLQuery를 JSON형태로 변경함.
 *
 * 파라미터 : params
 *             - urlQuery :  url파라메터
 *
 * 리턴값   : object(urlQuery)
 * 작성자   : 권준호
 * 작성일   : 2012.01.11
 ----------------------------------------------------------------------------------------------*/
function gf_UrlQueryToJson(urlQuery){
	var datas = {};
	var querys = urlQuery.split("&");
	for(var i = 0 ; i < querys.length ; i++){
		var query = querys[i];
		var divIdx = query.indexOf("=");
		var key = query.substring(0, divIdx);
		var value = query.substring(++divIdx, query.length);
		if(!gf_IsNull(key)){
			datas[key] = value;
		}
	}
	return datas;
}

/*----------------------------------------------------------------------------------
 * 시스템   : 내부결재
 * 설  명    : 파일 경로에서 파일명을 추출함.
 * 파라미터 : filePath - 파일 경로
 * 리턴값   : string(fileName)
 * 작성자   : 권준호
 * 작성일   : 2012.01.04
 ----------------------------------------------------------------------------------------------*/
function gf_GetFileName(filePath){
	var v_File = filePath.replace(/\\/g, '/');
	var v_FileNAme = v_File.spilt("/")[v_File.spilt("/").length-1];
	return v_FileNAme;
}

/*----------------------------------------------------------------------------------
 * 시스템   : 내부결재
 * 설  명    : 로딩된 업무화면의 컴포넌트들을 비활성화 시킨다
 *              - 버튼 : 비활성화
 *              - 버튼 외 : 읽기전용
 * 파라미터 : oForm - Elements 들을 Disable 할 Frame(Component)
 * 리턴값   : void
 * 작성자   : 고준석
 * 작성일   : 2012.01.04
 ----------------------------------------------------------------------------------------------*/
function gf_SetUIDisable(oForm)
{
	gf_GetAllElements(oForm);
	for ( var i = 0; i < fv_AllObjs.length; i++)
	{
		if(gf_GetElementType(fv_AllObjs[i]) == "BUTTON")
		{
			fv_AllObjs[i].enable = false;
		} else
		{
			gf_SetReadOnly(fv_AllObjs[i]);
		}
	}
	gf_GetAllElements(this);
}

/*----------------------------------------------------------------------------------
 * 시스템   : 내부결재
* 설명   : PDV 뷰어용 url 얻어오기
 * 파라미터 : pdfPath : PDF파일 경로
 * 리턴값   : string(url)
 * 작성자   : 권준호
 * 작성일   : 2012.01.04
----------------------------------------------------------------------------------------------*/
function gf_PDFUrl(pdfPath) {
	var url = gf_getAppUrl('') + '/jsp/common/PDFViewer/view.jsp?pdfPath=' + pdfPath;
	return url;
}

/*----------------------------------------------------------------------------------
 * 시스템   : 내부결재
 * 설  명    : 한글뷰어 로드 URL을 생성
 * 파라미터 : formPath - HWP 파일 경로
 * 리턴값   : string(url)
 * 작성자   : 권준호
 * 작성일   : 2012.01.04
 ----------------------------------------------------------------------------------------------*/
function gf_HwpViewUrl(formPath)
{
	var formUrl = formPath.search(/^http[s]?:/gi) == 0 ? formPath : gf_getAppUrl('/') + formPath;
	return formUrl;
}

/*----------------------------------------------------------------------------------
 * 시스템   : 내부결재
 * 설  명    : office뷰어 로드 URL을 생성
 * 파라미터 : formPath - office 파일 경로
 * 리턴값   : string(url)
 * 작성자   : 권준호
 * 작성일   : 2012.01.04
 ----------------------------------------------------------------------------------------------*/
function gf_OfficeViewUrl(formPath)
{
	//var formUrl = formPath.search(/^http[s]?:/gi) == 0 ? formPath : gf_getAppUrl('/') + formPath;
	var url = gf_getAppUrl('/') + 'jsp/common/ocx/sign/signofficeviewer.jsp?path=' + formPath;
	return url;
}

  /*----------------------------------------------------------------------------------------------
  * 시스템   : 내부결재
  * 설명   : 결재문서 본문이 레포트형식일 경우 문서를 확대함.
  * 파라미터 : 없음
  * 리턴값   : void
  * 작성자 : 고준석
  * 작성일 : 2012.06.22
  ----------------------------------------------------------------------------------------------*/
 var gv_ReportZoomSize = new Array("50", "75", "100", "125", "150", "200");
 var gv_ReportZoomSizeIndex = -1;

 function gf_ReportZoomIn(){
 	if(gv_ReportZoomSizeIndex == -1)
 		gv_ReportZoomSizeIndex = 2;
 	else
 		gv_ReportZoomSizeIndex++;
 	if(gv_ReportZoomSize.length - 1  < gv_ReportZoomSizeIndex)
 		gv_ReportZoomSizeIndex = gv_ReportZoomSize.length - 1;
 	$("signViewer").Document.Script.ExecScript("RexCtl.Zoom('"+ gv_ReportZoomSize[gv_ReportZoomSizeIndex] +"')");
 }

  /*----------------------------------------------------------------------------------------------
  * 시스템   : 내부결재
  * 설명   : 결재문서 본문이 레포트형식일 경우 문서를 확대함.
  * 파라미터 : 없음
  * 리턴값   : void
  * 작성자 : 고준석
  * 작성일 : 2012.06.22
  ----------------------------------------------------------------------------------------------*/
 function gf_ReportZoomOut(){
 	if(gv_ReportZoomSizeIndex == -1)
 		gv_ReportZoomSizeIndex = 2;
 	else
 		gv_ReportZoomSizeIndex--;
 	if(0  > gv_ReportZoomSizeIndex)
 		gv_ReportZoomSizeIndex = 0;
 	$("signViewer").Document.Script.ExecScript("RexCtl.Zoom('"+ gv_ReportZoomSize[gv_ReportZoomSizeIndex] +"')");
 }

/**
 * 현재 날짜/시간 YYYY-MM-DD hh:mm:ss 계산
 * @returns {String}
 */
function gf_GetTimeStamp() {
	  var d = new Date();

	  var s =
		  gf_LeadingZeros(d.getFullYear(), 4) + '-' +
		  gf_LeadingZeros(d.getMonth() + 1, 2) + '-' +
		  gf_LeadingZeros(d.getDate(), 2) + ' ' +

		  gf_LeadingZeros(d.getHours(), 2) + ':' +
		  gf_LeadingZeros(d.getMinutes(), 2) + ':' +
		  gf_LeadingZeros(d.getSeconds(), 2);

	  return s;
}


/**
 * 현재 날짜/시간 YYYY-MM-DD hh:mm:ss 계산
 * @param n
 * @param digits
 * @returns {String}
 */
function gf_LeadingZeros(n, digits) {
  var zero = '';
  n = n.toString();

  if (n.length < digits) {
    for (i = 0; i < digits - n.length; i++)
      zero += '0';
  }
  return zero + n;
}

 /**
  * @class 문서함 부가정보 표출
  *        - 문서함 네비게이션 : (예) 문서함 > 미결함
  *        - 문서함 목록 명   :  (예) 미결함 목록
  *        - 건수
  *
  * @author 고준석
  * @since 2013-04-04
  * @version 1.0
  */
 function gf_SetBoxInfo(signbxNm, listCnt){
	$("#signbxNmNavi").text(signbxNm); 	//문서함 네비게이션
	var count = "["+listCnt+"]";
	$("#listCnt").text(count); 	//문서함 목록
 }

 /**
  * @class 파일첨부/문서첨부 컴포넌트 표출을 제어한다.
  *
  * @param tabNm
  * 		- atchFileTab : 파일첨부 Tab Show
  * 		- atchDocTab : 문서첨부 Tab Show
  * @author 고준석
  * @since 2013-04-04
  * @version 1.0
  */
 function gf_DisplayAtchInfo(tabNm){
	 if(tabNm =="atchFileTab"){
		 $("#atchFileTab").removeClass("tab off").addClass("tab on");
		 $("#atchDocTab").removeClass("tab on").addClass("tab off");
		 $("#atchDoc").hide();//.attr("style", "display:none"); //문서첨부 컴포넌트 : Hide
		 if(ds_SignForm.get(0,"fileAtchYn")=="Y")
			 $("#atchFile").show();//css("display", ""); //파일첨부 컴포넌트 : Show
		 else
			 $("#atchFile").hide();//css("display", ""); //파일첨부 컴포넌트 : Show

	 }else{
		 $("#atchFileTab").removeClass("tab on").addClass("tab off");
		 $("#atchDocTab").removeClass("tab off").addClass("tab on");
		 $("#atchFile").hide(); //.attr("style", "display:none"); //파일첨부 컴포넌트 : Hide
		 if(ds_SignForm.get(0,"docAtchYn")=="Y")
			 $("#atchDoc").show();//.css("display", ""); //문서첨부 컴포넌트 표출  : Show
		 else
			 $("#atchDoc").hide();//.css("display", ""); //문서첨부 컴포넌트 표출  : Show
	 }
 }

 function gf_IsAssist(signId, ds_Signln){
	for(var i = 0; i < ds_Signln.size() ; i++){
		if(ds_Signln.get(i, "signTpCd") == "T03" && ds_Signln.get(i, "signSeq") != "3"){
			return false;
		}
 	}
 	return true;
 }

 /**
 * @class 1단결재선/2단결재선 각 결재자가 8명이 초과하면 false를 리턴함.
 * @author 권준호
 * @since 2013-04-04
 * @version 1.0
 */
 function gf_IsSignlnCount(ds_Signln){
 	var signlnCnt = new Array(0,0);
 	var index = -1 ;
 	for(var i = 0 ; i < ds_Signln.size(); i++){
 		if(ds_Signln.get(i, "signTpCd") == "T01") // 이단결재 기안자가 있을시 이단결재자를 카운터한다.
 			index++;

 		if(ds_Signln.get(i, "signTpCd") != "T03")
 			signlnCnt[index]++;
 	}

 	if(signlnCnt[0] > 8 || signlnCnt[1] > 8 )
 		return false;

 	return true;
 }

 /**
 * @function
 * @param  strSvcId<String> transaction 서비스 id
 *         resultData<JsonObject> transaction 결과로 리턴된 jsonobject
 * @see    파일업로드중 transaction 실행후 수행될 callback 함수
 * @return N/A
 * @author 변형구
 * @since 2013-03-20
 * @version 1.0
 */
function f_FileList_Callback(strSvcId, obj, resultData){
	// transaction의 정상 처리 여부를 판단한다.
	if (!gf_ChkTransaction(strSvcId, resultData, true )) {
		return;
	}

	if ( strSvcId == 'SELECT_FILEATCHID') {
		var fileAtchId = "";
		$.each(resultData, function(i, itemAry) {
			if ( i == "fileAtchId") {
				$.each(itemAry, function( j, item)  {
					fileAtchId = item.fileAtchId;
				} );
				//}
			};
		});
		fv_FileAtchId = fileAtchId;

	}
}

function gf_SignlnInit(docNo){
	ds_Signln.clear();
	if(gf_IsNull(docNo)){
		ds_Signln.insert(0,{
			signId: "",
			signSeq: "1",
			signTpCd: "T01",
			signUserId: gv_userId,	// 세션에서 받아온 값
			signUserNm: gv_userNm,	// 세션에서 받아온 값
			psignUserNm : "",
			apperPositCd: gv_userPositCd,
			apperPositNm: gv_userPositCd,
			perpsignPositNm : "",
			apperRpswrkCd: gv_userRpswrkCd,
			apperRpswrkNm: gv_userRpswrkCd,
			apperOrgCd: gv_orgCd,
			apperOrgNm: gv_orgNm,
			orgChrcCls: "D"
		});
	} else {
		var datas = {};
		datas.docNo			= docNo;

	  	gf_Transaction(	"SELECT_SIGNLN", "/co/common/user/retrieveSignln.xpl", datas, {}, "gf_Callback", false);
	}
}

function gf_AssembleSignln(sign){
	var signln = new DataSet(sign.getAll()); //원본 결재선을 보호하기 위해 사본을 생성
	var size = signln.size(); // 전체 결재자 수

	// 전체 결재선을 숨김.
	$("#left_signln .signln_pos th").css("display", "none");
	$("#left_signln .signln_nm td").css("display", "none");
	$("#left_signln .signln_dt td").css("display", "none");

	$("#right_signln .signln_pos th").css("display", "none");
	$("#right_signln .signln_nm td").css("display", "none");
	$("#right_signln .signln_dt td").css("display", "none");

	$("#signDrafter").css("display", "none");

	$("#signAssistor").css("display", "none");
	$("#signAssistorVal").html("");

	// 협의자 및 결재방 처리 (1단결재)
	for( var i=0, dtCnt=0 ; i < size ; i++, dtCnt++){
		var apperOrgNm = signln.get(i, 'apperOrgNm');
		var apperPositCd = signln.get(i, 'apperPositCd');
		var apperRpswrkCd = signln.get(i, 'apperRpswrkCd');
		var signUserNm = signln.get(i, 'signUserNm');
		var signDt = signln.get(i, 'signDt');
		var signStsCd = signln.get(i, 'signStsCd');
		var signTpCd = signln.get(i, 'signTpCd');

		// 기안자 처리
		if(signTpCd == "T01"){
//			var drftHtml = apperPositCd + " " + signUserNm;
			$("#signDrafter").css("display", "");
			$("#drftUserNm").text(signUserNm + "(" + apperOrgNm + ")"); 		//기안자(부서명)
//			$("#signDrafterVal").html(drftHtml);
		}

		// 협의자 처리
		if(signTpCd == "T03"){
			var asstHtml = $("#signAssistorVal").html();

			if (asstHtml != "") asstHtml += ", ";

			/**
			 * 협조 표시
			 * i = 0 일 경우는 기안자 (호칭으로 표시한다)
			 * i 가 0 이상의 경우는 직책을 표시하되 없으면 호칭으로 표현한다.
			 */

			if(i > 0){
				if(apperRpswrkCd == ""){
					asstHtml = asstHtml + apperOrgNm + " " + apperPositCd + " " + signUserNm;
				}else{
					asstHtml = asstHtml + apperOrgNm + " " + apperRpswrkCd + " " + signUserNm;
				}

			}else{
				asstHtml = asstHtml + apperOrgNm + " " + apperPositCd + " " + signUserNm;
			}

			if (signDt != "" && typeof(signDt) != "undefined"){
				if(signStsCd == "S05"){
					signDt = "<span style=\"color:#FF0000; font-weight: bold;\">" + gf_FindLang("반려") + "</span>";
				}
				asstHtml += "(" + signDt + ")";
			}

			$("#signAssistor").css("display", "");
			$("#signAssistorVal").html(asstHtml);
			dtCnt--;
			continue;
		}

		// 결재방 처리(1단)
		if(signStsCd == "S05"){
			signDt = "<span style=\"color:#FF0000; font-weight: bold;\">" + gf_FindLang("반려") + "</span>";
		}else if(signStsCd == "S06"){
			signDt = "<span style=\"color:#FF0000; font-weight: bold;\">" + gf_FindLang("회수") + "</span>";
		}else if(signStsCd == "S10"){
			signDt = "<span style=\"color:#0000FF; font-weight: bold;\">" + gf_FindLang("전결") + "</span>";
		}

		/**
		 * 결재박스
		 * i = 0 일 경우는 기안자 (호칭으로 표시한다)
		 * i 가 0 이상의 경우는 직책을 표시하되 없으면 호칭으로 표현한다.
		 */
		if(i > 0){
			if(apperRpswrkCd == ""){
				$("#right_signln .signln_pos th:eq("+dtCnt+")").text(apperPositCd);
			}else{

				//2018.10.22 신동길대리가 사장실에서 연락받고 결재선 줄이는 작업 진행
				if(apperRpswrkCd == "대표이사" || apperRpswrkCd == "대표이사사장"){
					$("#right_signln .signln_pos th:eq("+dtCnt+")").text("CEO");
				}else if(apperRpswrkCd == "본부장직무대리"){
					$("#right_signln .signln_pos th:eq("+dtCnt+")").text("본부장대리");
				}else if(apperRpswrkCd == "실장직무대리"){
					$("#right_signln .signln_pos th:eq("+dtCnt+")").text("실장대리");
				}else if(apperRpswrkCd == "원장직무대리"){
					$("#right_signln .signln_pos th:eq("+dtCnt+")").text("원장대리");
				}else if(apperRpswrkCd == "현장관리책임자"){
					$("#right_signln .signln_pos th:eq("+dtCnt+")").text("관리책임자");

				}else{
					$("#right_signln .signln_pos th:eq("+dtCnt+")").text(apperRpswrkCd);
				}

			}
		}else{
			$("#right_signln .signln_pos th:eq("+dtCnt+")").text(apperPositCd);
		}
		$("#right_signln .signln_nm td:eq("+dtCnt+")").text(signUserNm);

		//2015-11-26
		//반려시에는 결재일시 대신 반려 라고 텍스트 표시
//		if(signStsCd == "S05"){
//			$("#right_signln .signln_dt td:eq("+dtCnt+")").html("반려");
//		}else{
//			$("#right_signln .signln_dt td:eq("+dtCnt+")").html(signDt);
//		}

		$("#right_signln .signln_dt td:eq("+dtCnt+")").html(signDt);

		$([
			$("#right_signln .signln_pos th")[dtCnt],
			$("#right_signln .signln_nm td")[dtCnt],
			$("#right_signln .signln_dt td")[dtCnt]
		]).css("display", "");
	}
}

function gf_AddSignln(userId, orgCd, signTpCd, signSeq){
	gf_GetSignUser(userId, orgCd);
	gf_AddSignUser(gds_SignUser, signTpCd, signSeq);
}

function gf_GetSignUser(userId, orgCd){
	var datas = {};
	datas.userId = userId;
	datas.orgCd = orgCd;

	gds_SignUser.clear();
  	gf_Transaction(	"SELECT_SIGN_USER", "/co/common/user/retrieveSignUser.xpl", datas, {}, "gf_Callback", false);
}

function gf_AddSignUser(gds_SignUser, signTpCd, signSeq){
	ds_Signln.add({
		signId: "",
		signSeq: signSeq,
		signTpCd: signTpCd,
		signUserId: gds_SignUser.get(0, "signUserId"),
		signUserNm: gds_SignUser.get(0, "signUserNm"),
		psignUserNm: "",
		apperPositCd: gds_SignUser.get(0, "apperPositCd"),
		apperPositNm: gds_SignUser.get(0, "apperPositCd"),
		perpsignPositNm:"",
		apperRpswrkCd: gds_SignUser.get(0, "apperRpswrkCd"),
		apperRpswrkNm: gds_SignUser.get(0, "apperRpswrkCd"),
		apperOrgCd: gds_SignUser.get(0, "apperOrgCd"),
		apperOrgNm: gds_SignUser.get(0, "apperOrgNm"),
		canDelete: "N"
	});
}

/**
 *
 * @param userId		유저
 * @param orgCd			유저의 조직코드
 * @param signTpCd		결재유형 (T02 : 결재, T03 : 협의)
 * @param signSeq		결재순번
 */
function gf_InsertSignUserWithShift(userId, orgCd, signTpCd, signSeq){
	gf_GetSignUser(userId, orgCd);
	for(var i=0; i<ds_Signln.size(); i++){		// 전체 결재선 루프
		var beforeSignSeq = ds_Signln.get(i, "signSeq");
		if(beforeSignSeq >= signSeq){
			for(var k=i; k<ds_Signln.size(); k++){		// 이후 결재선 Shift
				ds_Signln.set(k, "signSeq", parseInt(ds_Signln.get(k, "signSeq")) + 1);
			}
			ds_Signln.insert(i, {
									signId: "",
									signSeq: signSeq,
									signTpCd: signTpCd,
									signUserId: gds_SignUser.get(0, "signUserId"),
									signUserNm: gds_SignUser.get(0, "signUserNm"),
									psignUserNm: "",
									apperPositCd: gds_SignUser.get(0, "apperPositCd"),
									apperPositNm: gds_SignUser.get(0, "apperPositCd"),
									perpsignPositNm:"",
									apperRpswrkCd: gds_SignUser.get(0, "apperRpswrkCd"),
									apperRpswrkNm: gds_SignUser.get(0, "apperRpswrkCd"),
									apperOrgCd: gds_SignUser.get(0, "apperOrgCd"),
									apperOrgNm: gds_SignUser.get(0, "apperOrgNm"),
									canDelete: "N"
								});
			break;
		}
	}
}

/**
*
* @param userId		유저
* @param orgCd			유저의 조직코드
* @param signTpCd		결재유형 (T02 : 결재, T03 : 협의)
* @param signSeq		결재순번
*/
function gf_InsertSignUser(userId, orgCd, signTpCd, signSeq){
	gf_GetSignUser(userId, orgCd);
	for(var i=0; i<ds_Signln.size(); i++){		// 전체 결재선 루프
		var beforeSignSeq = ds_Signln.get(i, "signSeq");
		if(beforeSignSeq >= signSeq){
			ds_Signln.insert(i, {
									signId: "",
									signSeq: signSeq,
									signTpCd: signTpCd,
									signUserId: gds_SignUser.get(0, "signUserId"),
									signUserNm: gds_SignUser.get(0, "signUserNm"),
									psignUserNm: "",
									apperPositCd: gds_SignUser.get(0, "apperPositCd"),
									apperPositNm: gds_SignUser.get(0, "apperPositCd"),
									perpsignPositNm:"",
									apperRpswrkCd: gds_SignUser.get(0, "apperRpswrkCd"),
									apperRpswrkNm: gds_SignUser.get(0, "apperRpswrkCd"),
									apperOrgCd: gds_SignUser.get(0, "apperOrgCd"),
									apperOrgNm: gds_SignUser.get(0, "apperOrgNm"),
									canDelete: "N"
								});
			break;
		}
	}
}

function gf_ChangePrivDraft(strPrivCd, strSiteCd, strOrgCd) {
	var strSvcAct =  "/co/common/user/retrieveUserEtcInfo.xpl";
	var datas= {
			privCd : strPrivCd,
			siteCd : strSiteCd,
			orgCd : strOrgCd
		};
	gf_Transaction("SELECT_CHANGE_PRIV", strSvcAct, datas, {}, "gf_Callback", true);
}

function gf_Callback(strSvcId, obj, resultData){

	  // transaction의 정상 처리 여부를 판단한다.
	  if (!gf_ChkTransaction(strSvcId, resultData, true )) {
		  return;
	  }

	  switch(strSvcId) {
	  	case "SELECT_SIGNLN" :
	  		ds_Signln.setAllData(resultData.ds_Signln);
			break;
	  	case "SELECT_SIGN_USER" :
	  		gds_SignUser.setAllData(resultData.gds_SignUser);
	  		break;
	  	case "SELECT_CHANGE_PRIV" :
	  		gds_UserEtcInfo.setAllData(resultData.ds_UserEtcInfo);
	  		var userPositCd = gds_UserEtcInfo.get(0, "userPositCd");
	  		var userRpswrkCd = gds_UserEtcInfo.get(0, "userRpswrkCd");

	  		//직책, 직위 셋팅
	  		ds_Signln.set(0, "apperPositCd", userPositCd);
			ds_Signln.set(0, "apperPositNm", userPositCd);
			ds_Signln.set(0, "apperRpswrkCd", userRpswrkCd);
			ds_Signln.set(0, "apperRpswrkNm", userRpswrkCd);
	  		break;
	  	default :
	  		break;
	  }
}