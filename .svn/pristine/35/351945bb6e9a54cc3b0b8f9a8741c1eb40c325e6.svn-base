/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/

var v_tripUserDetailTr = "<tr><td class=\"inpt\" style=\"width:40%; text-align:center\"><input type=\"text\" name=\"tUserEnm\" style=\"border:0\" readonly=\"readonly\"></td>"
 + "<td class=\"inpt\" style=\"width:30%; text-align:center\"><select name=\"tGrade\" style=\"border:0\" disabled=\"disabled\"><option value=\"F\" selected=\"selected\">First</option><option value=\"B\">Business</option><option value=\"E\">Economy</option></select></td>"
 + "<td class=\"inpt\" style=\"width:30%; text-align:center\"><input type=\"text\" name=\"tAirAmount\" style=\"border:0\" readonly=\"readonly\">원</td></tr>";

var v_visaInfoDetailTr = "<tr><td class=\"inpt\" style=\"width:20%; text-align:center\"><input type=\"text\" name=\"vNat\" style=\"border:0\" readonly=\"readonly\"></td><td class=\"inpt\" style=\"width:20%; text-align:center\"><input type=\"text\" name=\"vVisaYn\" style=\"border:0\" readonly=\"readonly\"></td>"
 + "<td class=\"inpt\" style=\"width:60%; text-align:center\">	발급일 : <input type=\"text\" name=\"vStartDate\" style=\"border:0\" readonly=\"readonly\"> / 만료일 : <input type=\"text\" name=\"vEndDate\" style=\"border:0\" readonly=\"readonly\"></td></tr>";

var v_cityDetailTr = "<tr><td class=\"inpt\" style=\"width:30%; text-align:center\">	<input type=\"text\" name=\"natNm\" style=\"width: 120px\" style=\"border:0\" readonly=\"readonly\"> / <input type=\"text\" name=\"cityNm\" style=\"width: 120px\" style=\"border:0\" readonly=\"readonly\"></td>"
+ "<td class=\"inpt\" style=\"width:20%; text-align:center\">	<input type=\"text\" name=\"stayStYmd\" style=\"width: 100px;border:0\" readonly=\"readonly\">부터<br><input type=\"text\" name=\"stayEdYmd\" style=\"width: 100px;border:0\" readonly=\"readonly\">까지</td>"
+ "<td class=\"inpt\" style=\"width:50%; text-align:center\"><textarea style=\"width:95%;border:0\" readonly=\"readonly\" name=\"dutyCont\"></textarea></td></tr>";

// 문서번호
var v_DocNo;
// RefNo
var v_RefNo;
// 작성자 정보
var v_DraftUserId;
var v_DraftUserKnm;
var v_DraftUserEnm;
var v_DraftUserOrgCd;
var v_DraftUserOrgNm;
// 출장자 정보
var v_TripUserId;
var v_TripUserKnm;
var v_TripUserEnm;
var v_TripUserOrgCd;
var v_TripUserOrgNm;
//좌석 등급
var v_TripUserSGrade;
// 출장자 항공료
var v_TripUserAmt;

//출장자 동행인 목록
var ds_TripUserList = new DataSet();

// 출장 시작일
var v_StartDate;
// 출장 종료일
var v_EndDate;
// 출장 기간
var v_TripDate;
// 출장 목적
var v_TripPurp;
// 요청 사항
var v_TripReq;
// 여권 발급일
var v_PpStartDt;
// 여관 만료일
var v_PpEndDt;

//비자정보 목록
var ds_VisaInfoList = new DataSet();

//방문지 목록
var ds_CityList = new DataSet();

// 예산구분
var v_BdgtType = "";
// 경비구분
var v_VTCode;
var v_Aufnr; // 예산
// 예산내역
var v_KText; //내역
var v_Kostv; //집행팀
var v_Kostvnm; //집행팀 이름
var v_Chief; // 집행팀장ID
var v_Chiefnm; // 집행팀장 이름

// 항공료 총합
var v_AirTotalAmt = 0;
// 항공료 요청사항
var v_AirReq;

// 현지항공료
var v_TAirAmt;
var v_TAirAmtDtl;
// 현지 교통비
var v_TTranspAmt;
var v_TTranspAmtDtl;
// Visa Fee
var v_VisaFee;
var v_VisaFeeDtl;
// Over Charge
var v_OverCharge;
var v_OverChargeDtl;
// 복리후생
var v_Benefits;
var v_BenefitsDtl;
// 접대비
var v_HostAmt;
var v_HostAmtDtl;

// 기타경비 총합
var v_EtcTotalAmt;
// 기타경비 송금예정일
var v_EtcPayDate;

// 체제비
var ds_InputAmt = new DataSet();
// 비고
var v_EtcReq;

// 국가 목록
var ds_NatList = new DataSet();

// 기준액 목록
var ds_RefList = new DataSet();

// 환율 정보 목록
var ds_ExRList = new DataSet();

var v_EUR;
var v_GBP;
var v_USD;
var v_JPY;

// 체제비 총합
var v_cTotalAmt = 0;

// 체제비 단위별 총합
var v_cTotUSD;
var v_cTotEUR;
var v_cTotGBP;
var v_cTotJPY;


// 총합
var v_TotalAmount = 0;

var ds_ExpnList = new DataSet();

var ds_Sign = new DataSet();

var ds_OuterTripDoc = new DataSet();
var ds_ExtnlPer = new DataSet();
var ds_Expn = new DataSet();
var ds_Visa = new DataSet();

var ds_Signln = new DataSet();			//결재선정보

var v_DocSts;
var v_AdjustDocSts;
var v_CallbackFunc;

// 복리후생 + 접대비 계산용
var hostCheck = 0;

var v_RefKey;
var v_SignId;

var ds_sapData = new DataSet();
var ds_RejCont = new DataSet();

var v_CmasList;

/**
* @class 타블릿 화면 사용시 각페이지에서 생성되는 전역변수를 초기화시킴(팝업화면은 제외)
*        - 메모리 해제 관련 처리
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_GarbageCollector(){
}

/**
* @class 화면 로드 완료 시 필요한 초기 작업 수행.
*        1. 파라미터 초기화
*        2. 컴포넌트 초기화
*        3. Event Listener 초기화
*        4. 화면내 Form 객체 초기화
*        5. 다국어 처리
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
$(function() {
	/* 컴포넌트 처리 */
	cf_InitParam();
	cf_SetComponents();
	cf_SetEventListener();
	cf_SetBinding();
	cf_InitForm();
	fn_MlangSet();
});

/**
* @class 파라미터 초기화
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_InitParam()
{

	v_DocNo = gf_IsNull(datas.fromList) ? "" : datas.fromList;
	v_DocSts = gf_IsNull(datas.docSts) ? "" : datas.docSts;
	v_AdjustDocSts = gf_IsNull(datas.adjustDocSts) ? "" : datas.adjustDocSts;
	v_RefKey = gf_IsNull(datas.refNo) ? "" : datas.refNo;
	v_SignId = gf_IsNull(datas.signId) ? "" : datas.signId;
	v_CallbackFunc = gf_IsNull(datas.callbackFunc) ? "" : datas.callbackFunc;

	v_CmasList = gf_IsNull(datas.cmasList) ? "" : datas.cmasList;

	//파일 업로드 콜백 함수 Initialize
	gf_SetUploadCallback("fn_SetUploadCallback");
}

/**
* @class Form Onload 시 컴포넌트 초기화
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetComponents()
{
	//Attachment 컴포넌트 생성
	gf_InitFileUploadComponent();

	// upload 모드로 컴포넌트의 mode  설정
    gf_setMode("download");

    if(gv_AuthList.auth[0].code.match("RO_CMAS_CO_TRIP_ADM") != null){ //관리자
		// 관리자
		$("#createPdf").show();

		// 결재완료 문서의 경우 회계승인 버튼 생성
//		if(v_DocSts == "D03"){
//			$("#confirmSap").show();
//			$("#confirmAcct").show();
//		}

	}else{
		// 관리자가 아닌 경우
	}
}

/**
* @class Form Onload DataSet Binding 처리
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetBinding()
{


}

/**
* @class Form Onload 시 Element/Component 인벤트 초기화
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetEventListener()
{

	$("#docDelBtn").click(function(){	//신청서삭제 (임시전표 포함X) 별도 ISSUE 있을 수 있어서 분리.

		if(confirm("신청서를 삭제하시겠습니까") == true){
			//신청서 삭제
			f_deleteDoc();
		};
	});

	$("#print").click(function(){

		var params = {
				ds_Signln : ds_Signln.getAllData(),
				ds_OuterTripDoc : ds_OuterTripDoc.getAllData(),
				ds_ExtnlPer : ds_ExtnlPer.getAllData(),
				ds_Expn : ds_Expn.getAllData(),
				ds_Visa : ds_Visa.getAllData(),
				ds_sapData : ds_sapData.getAllData(),
				v_DocSts : v_DocSts,
				v_RefKey : v_RefKey,
				v_SignId : v_SignId,
				v_DocNo : v_DocNo,
				ds_NatList : ds_NatList.getAllData(),
				v_EUR : v_EUR,
				v_GBP : v_GBP,
				v_USD : v_USD,
				v_JPY : v_JPY
		};


		gf_PostOpen("/trip/outerTrip/draftPrint.jsp", null,
				"toolbar=no,scrollbars=yes,width=800,height=700", params,
				true, true, null);
	});


	$("#outerTripClose").click(function(){
		self.close();
	});

	$("#outerTripAdjust").click(function(){

		var param = {

				fromList : v_DocNo,
				docSts : v_DocSts,
				adjustDocSts : v_AdjustDocSts
		};

		gf_PostReplace("/trip/outerTrip/outerTripAdjust.jsp", param, true, true, null);
	});

	$("#ghrCmtUpdate").click(function(){

		if(confirm("GHR 검토의견을 수정하시겠습니까?")){
			var docNo = v_DocNo;
			var perchrgRvwOpn2 = $("#ghrComment").val();
			var rem = $("#rem").val();

			if(docNo == "" || docNo == undefined){
				alert("문서번호가 올바르지 않습니다.");
				return;
			}

			var params = {
					docNo : docNo,
					perchrgRvwOpn2 : perchrgRvwOpn2,
					rem : rem
			};


			gf_Transaction("SAVE_GHR_COMMENT", "/trip/outerTrip/updateOuterTripGHRCmt.xpl", params, {}, "f_Callback", true);

		}
	});


	$("#createPdf").click(function(){
		// 수동으로 증빙 pdf 신청
		gf_InitSpinner(top.$('body'));

		if(v_SignId == undefined || v_SignId == ""){
			alert("결재문서 번호가 존재하지 않습니다.");
			return;
		}

		if(v_RefKey == undefined || v_RefKey == ""){
			alert("REF NO 가 존재하지 않습니다.");
			return;
		}

		if(v_DocNo == undefined || v_DocNo == ""){
			alert("문서 번호가 존재하지 않습니다.");
			return;
		}

		var params = {
				signId : v_SignId,
				refNo : v_RefKey,
				docNo : v_DocNo
		};

		gf_Transaction("SAVE_CREATE_PDF_DOC", "/trip/outerTrip/createPdfDoc.xpl", params, {}, "f_Callback", true);


	});

	// 반려된 문서 재작성
	$("#outerTripReDraft").click(function(){


		var params = {
				fromReject : "Y",
				cmasList : v_CmasList,
				ds_Signln : ds_Signln.getAllData(),
				docData : ds_OuterTripDoc.getAllData(),
				ds_ExtnlPer : ds_ExtnlPer.getAllData(),
				ds_Visa : ds_Visa.getAllData(),
				ds_Expn : ds_Expn.getAllData()
		};

		gf_PostReplace("/trip/outerTrip/outerTripDraft.jsp", params, true, true, null);


	});

	//임시전표 삭제

	$("#rejectSap").click(function(){

		if(confirm("임시전표를 삭제하시겠습니까? (사용자는 문서 내용을 조회할 수 없게됩니다.)")){

			gf_InitSpinner(top.$('body'));

			var data = JSON.parse(ds_OuterTripDoc.get(0, "ifParam"));

			if(v_RefKey == "" || v_RefKey == undefined){
				alert("REF NO 가 존재하지 않습니다.");
				return;
			}else if(v_DocNo == "" || v_DocNo == undefined){
				alert("문서번호가 존재하지 않습니다.");
				return;
			}else if(data.drafterUserId == "" || data.drafterUserId == undefined){
				alert("작성자가 등록되어 있지 않습니다.");
				return;
			}else{

//				I_REFKEY	CHAR	 15 	신청번호
//				I_NOTESDOC	CHAR	 32 	CMAS 문서번호
//				I_CONFIRM	CHAR	1	결재모드 ( Y: 결재, N: 반려 )
//				I_SENDER_ID	CHAR	7	결재자사번
//				I_BUKRS	CHAR	4	회사코드 (무조건 1000)

//				resultMap.put("acctSts", "Y");
//				resultMap.put("confirm", "Y");
//				resultMap.put("refNo", (String)paramMap.get("refNo"));
//				resultMap.put("docNo", (String)paramMap.get("docNo"));
//				String userId = (String)paramMap.get("drafterId");
//				resultMap.put("userId", userId.split(" ")[0]);

				var params = {
						refNo : v_RefKey,
						docNo : v_DocNo,
						confirm : "N",
						userId : data.drafterUserId
				};

				gf_Transaction("SAVE_CONFIRM_SAP", "/trip/outerTrip/confirmSap.xpl", params, {}, "f_Callback", true);
			}

		}

	});

	$("#confirmSap").click(function(){
		// 수동으로 증빙 pdf 신청
		gf_InitSpinner(top.$('body'));

		var data = JSON.parse(ds_OuterTripDoc.get(0, "ifParam"));

		if(v_RefKey == "" || v_RefKey == undefined){
			alert("REF NO 가 존재하지 않습니다.");
			return;
		}else if(v_DocNo == "" || v_DocNo == undefined){
			alert("문서번호가 존재하지 않습니다.");
			return;
		}else if(data.drafterUserId == "" || data.drafterUserId == undefined){
			alert("작성자가 등록되어 있지 않습니다.");
			return;
		}else{

//			I_REFKEY	CHAR	 15 	신청번호
//			I_NOTESDOC	CHAR	 32 	CMAS 문서번호
//			I_CONFIRM	CHAR	1	결재모드 ( Y: 결재, N: 반려 )
//			I_SENDER_ID	CHAR	7	결재자사번
//			I_BUKRS	CHAR	4	회사코드 (무조건 1000)

//			resultMap.put("acctSts", "Y");
//			resultMap.put("confirm", "Y");
//			resultMap.put("refNo", (String)paramMap.get("refNo"));
//			resultMap.put("docNo", (String)paramMap.get("docNo"));
//			String userId = (String)paramMap.get("drafterId");
//			resultMap.put("userId", userId.split(" ")[0]);

			var params = {
					refNo : v_RefKey,
					docNo : v_DocNo,
					confirm : "Y",
					userId : data.drafterUserId
			};

			gf_Transaction("SAVE_CONFIRM_SAP", "/trip/outerTrip/confirmSap.xpl", params, {}, "f_Callback", true);
		}
	});

	$("#confirmAcct").click(function(){

		if(confirm("회계승인 없이 문서를 '회계승인'상태로 변경하시겠습니까?")){
			// 수동으로 승인으로 상태값 변경
			gf_InitSpinner(top.$('body'));

			if(v_DocNo == "" || v_DocNo == undefined){
				alert("문서번호가 존재하지 않습니다.");
				return;
			}else{
				var params = {
						acctSts : '완료',
						docNo : v_DocNo,
						docSts : 'D03',
						retResn : ''
				};

				gf_Transaction("SAVE_CONFIRM_ACCT", "/trip/outerTrip/updateSgnsReject.xpl", params, {}, "f_Callback", true);
			}

		}

	});

}

/**
* @class Form Onload 시 객체 초기 값 설정
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_InitForm()
{

	if(v_DocSts == "D99"){		//상신실패
		$("#docDelBtn").show();
		$("#rejectSap").show();
	}

	var ghrFlg = false;

	// GHR 팀 여부 검사
	for(var i = 0; i < v_CmasList.length ; i++){

		if(gv_userId == v_CmasList[i].userId){
			if("RO_CMAS_OT_001".match(v_CmasList[0].privCd) != null){
				ghrFlg = true;
			}
		}
	}

	// GHR 팀일 경우 검토창을 연다
	if(ghrFlg){
		$("#ghrCmtUpdate").show();

		$("#ghrTr").show();
		$("#ghrComment").removeAttr("readonly");

		// 비고창도 수정할 수 있음
		$("#rem").removeAttr("readonly");

	}

	if(v_DocSts == "D03"){
		// 결재완료 문서 정산서 작성 버튼 팝업
		$("#outerTripAdjust").show();
	}

	if(v_DocSts == "D04"){
		// 반려 문서 재작성 버튼 팝업
		$("#outerTripReDraft").show();
		// 반려 사유
		$("#rejContTr").show();
		if(v_SignId == "" || v_SignId == undefined){
			alert("결재문서 번호가 존재하지 않습니다.");
		}else{
			var params = {
				signId : v_SignId
			};
			gf_Transaction("SELECT_REJ_CONT", "/trip/outerTrip/retrieveRejCont.xpl", params, {}, "f_Callback", true);
		}
	}


	// 관리자 Only
	if(gv_AuthList.auth[0].code.match("RO_CMAS_CO_TRIP_ADM") != null){
		$("#createPdf").show();
		//결재중 상태인데..SGNS로 값이 안 넘어갔을때만

		//결재중인데,SIGN 값이 없다면 삭제가 필요함.
		if(v_DocSts == "D02" && v_SignId ==""){
			$("#rejectSap").show();
			$("#docDelBtn").show();
		}

		// 결재완료 문서의 경우 회계승인 버튼 생성
		if(v_DocSts == "D03"){
			$("#confirmSap").show();
			$("#confirmAcct").show();
		}

	}else{
		// 관리자가 아닌 경우
	}


	gf_Transaction("SELECT_NAT_LIST", "/trip/outerTrip/retrieveNatList.xpl", {}, {}, "f_Callback", true);
	gf_Transaction("SELECT_REF_LIST", "/trip/outerTrip/retrieveTripRef.xpl", {}, {}, "f_Callback", true);

	cf_RetrieveSignInfo();

}

/**
* @class Transaction 처리 후 수행되는 Callback 함수
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function f_Callback(strSvcId, obj, resultData){

	  // transaction의 정상 처리 여부를 판단한다.
	  if (!gf_ChkTransaction(strSvcId, resultData, true )) {
		  return;
	  }

	  switch(strSvcId) {
		  case "SELECT_OUTER_TRIP_VIEW_DOC" :

			  ds_OuterTripDoc.setAllData(resultData.ds_OuterTripDoc);
			  ds_ExtnlPer.setAllData(resultData.ds_ExtnlPer);
			  ds_Expn.setAllData(resultData.ds_Expn);
			  ds_Visa.setAllData(resultData.ds_Visa);


			  ds_sapData.setAllData(resultData.ds_sapData);

//			  $("#refTr").show();
	  		  $("#refDay").text(ds_OuterTripDoc.get(0, "fstRegDt").substr(0, 10));

			  var savedYmd = ds_OuterTripDoc.get(0, "fstRegDt").substr(0, 10);

		  	  var ymdParams = {
		  			 Ymd : $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", savedYmd))
		  	  };

			 gf_Transaction("SELECT_EXRATE", "/trip/eai/getSendExrate.xpl", ymdParams, {}, "f_Callback", true);

			 cf_setViewDocData();

			 break;
	  	case "SELECT_CMAS_ID" :

	  		v_DocNo = resultData.ds_Result[0].docNo;
	  		var docNoText = v_DocNo.split("-");
	  		$("#docNo").text(docNoText[1] + "-" + docNoText[2]);

	  		break;
	  	case "SELECT_NAT_LIST" :
	  		ds_NatList.setAllData(resultData.ds_NatList);

	  		if(v_RefKey != "" && v_RefKey != undefined){
	  			var viewDocParams = {
		  				docNo : v_DocNo,
		  				refNo : v_RefKey
		  		};

		  		gf_Transaction("SELECT_OUTER_TRIP_VIEW_DOC", "/trip/outerTrip/retrieveOuterTripViewDoc.xpl", viewDocParams, {}, "f_Callback", true);
	  		}

			break;
	  	case "SELECT_REF_LIST" :
	  		ds_RefList.setAllData(resultData.ds_RefList);
	  		break;
	  	case "SELECT_EXRATE" :
	  		ds_ExRList.setAllData(resultData.output1[0].SapItab);

	  		// EUR
	  		v_EUR = ds_ExRList.get(ds_ExRList.find("Currency", "EUR"), "Exrate");
	  		v_EUR = v_EUR.substr(0, v_EUR.length-3);
	  		$("#euroRef").text(v_EUR);

	  		// GBP
	  		v_GBP = ds_ExRList.get(ds_ExRList.find("Currency", "GBP"), "Exrate");
	  		v_GBP = v_GBP.substr(0, v_GBP.length-3);
	  		$("#gbpRef").text(v_GBP);

	  		// USD
	  		v_USD = ds_ExRList.get(ds_ExRList.find("Currency", "USD"), "Exrate");
	  		v_USD = v_USD.substr(0, v_USD.length-3);
	  		$("#usdRef").text(v_USD);

	  		// JPY
	  		v_JPY = ds_ExRList.get(ds_ExRList.find("Currency", "JPY"), "Exrate");
	  		v_JPY = v_JPY.substr(0, v_JPY.length-1);
	  		v_JPY = parseFloat(v_JPY).toFixed(4);
	  		$("#jaRef").text(v_JPY);

	  		cf_AppendSavedExpn(ds_Expn.getAllData());

	  		// 환율 게산 데이터

	  		if(ds_OuterTripDoc.get(0, "lclAfare") == ""){
	  			$("#loAirFareEx").text("0");
	  		}else{
	  			$("#loAirFareEx").text(gf_AmtFormat(Math.round(parseFloat(removeComma(ds_OuterTripDoc.get(0, "lclAfare"))) * parseFloat(v_USD))));
	  		}

	  		if(ds_OuterTripDoc.get(0, "lclTrafficExpn") == ""){
	  			$("#loTranFareEx").text("0");
	  		}else{
	  			$("#loTranFareEx").text(gf_AmtFormat(Math.round(parseFloat(removeComma(ds_OuterTripDoc.get(0, "lclTrafficExpn"))) * parseFloat(v_USD))));
	  		}
	  		if(ds_OuterTripDoc.get(0, "visaFee") == ""){
	  			$("#visaFeeFareEx").text("0");
	  		}else{
	  			$("#visaFeeFareEx").text(gf_AmtFormat(Math.round(parseFloat(removeComma(ds_OuterTripDoc.get(0, "visaFee"))) * parseFloat(v_USD))));
	  		}
	  		if(ds_OuterTripDoc.get(0, "excsExpn") == ""){
	  			$("#ovCharFareEx").text("0");
	  		}else{
	  			$("#ovCharFareEx").text(gf_AmtFormat(Math.round(parseFloat(removeComma(ds_OuterTripDoc.get(0, "excsExpn"))) * parseFloat(v_USD))));
	  		}
	  		if(ds_OuterTripDoc.get(0, "wef") == ""){
	  			$("#vocLeeFareEx").text("0");
	  		}else{
	  			$("#vocLeeFareEx").text(gf_AmtFormat(Math.round(parseFloat(removeComma(ds_OuterTripDoc.get(0, "wef"))) * parseFloat(v_USD))));
	  		}
	  		if(ds_OuterTripDoc.get(0, "svcExpn") == ""){
	  			$("#hostFareEx").text("0");
	  		}else{
	  			$("#hostFareEx").text(gf_AmtFormat(Math.round(parseFloat(removeComma(ds_OuterTripDoc.get(0, "svcExpn"))) * parseFloat(v_USD))));
	  		}

	  		f_calcAirAmount();

	  		break;
	  	case "SELECT_CHECK_OS_BIZ_TRIP" :

	  		var result = resultData.output1[0];

	  		if(result.ErrMsg != undefined){
	  			// SAP 상신 실패시 Msg 출력
	  			gf_AlertMsg(result.ErrMsg);
	  			v_isSaveEnable = "N";
	  			gf_DisableCurrentOverlay();
	  			return;
	  		}else if(result.ORefkeyNo != undefined){

	  			cf_saveOuterDraftDoc();

//				if(v_DocSts == "D16"){
//					gf_Transaction("SELECT_OUTER_TRIP_DRAFT", "/trip/outerTrip/updateOuterTripDraftDoc.xpl", draftParams, draftDataSet, "f_Callback", true);
//				}else{
//					gf_Transaction("SELECT_OUTER_TRIP_DRAFT", "/trip/outerTrip/saveOuterTripDraft.xpl", draftParams, draftDataSet, "f_Callback", true);
//				}

	  		}else{
	  			gf_AlertMsg("통신 중 에러가 발생하였습니다. 잠시 후 다시 시도하세요.");
	  			v_isSaveEnable = "N";
	  			gf_DisableCurrentOverlay();
	  			return;
	  		}

	  		// 예산 체크 확인
	  		// 1. 사전협조
	  		// 1-1 (위험국가일 경우) 해외영업기획팀
	  		// 1-2 (위험국가일 경우) 해외SECURITY팀
	  		// 2. 글로벌 HR팀 으로 넘겨준다.
	  		// 3. 사전협조 완료 통합결재로 기안

	  		break;
	  	case "SELECT_SIGN_INFO" :
	  		ds_Signln.clear();
	  		ds_Signln.setAllData(resultData.ds_Sign);

	  		var lastSignDt = -1;
	  		for ( var i = 0; i < ds_Signln.size(); i++) {

	  			if(i > 0){
	  				if(ds_Signln.get(i, "apperRpswrkCd") == ""){
	  					ds_Signln.set(i, "apperRpswrkCd", ds_Signln.get(i, "apperPositCd"));
	  				}
	  				ds_Signln.set(i, "apperPositCd", ds_Signln.get(i, "apperRpswrkCd"));
	  			}
	  			// 결재 시각을 자른다. 일자만 보임
  				if(ds_Signln.get(i, "signDt") != ""){
  					var signDt = ds_Signln.get(i, "signDt");
  					signDt = signDt.substr(0, 10);
  					ds_Signln.set(i, "signDt", signDt);
  				}
  				// 반려 표시 처리
  				if(v_DocSts == "D04"){
  					if(ds_Signln.get(i, "signDt") != "") lastSignDt = i;
  				}
			}

	  		// 반려 표시 처리
	  		if(v_DocSts == "D04"){
	  			if(lastSignDt != -1){
	  				ds_Signln.set(lastSignDt, "signStsCd", "S05");
	  			}
			}

	  		gf_AssembleSignln(ds_Signln);


	  		// 반려사유 표시
	  		// 마지막 결재인을 구함

	  		if(v_DocSts == "D04"){
	  			var rejSignSeq = "";

		  		for ( var i = 0; i < ds_Signln.size(); i++) {
	  				if(ds_Signln.get(i, "signDt") != ""){
	  					rejSignSeq = ds_Signln.get(i, "signSeq");
	  				}
				}

		  		if(rejSignSeq != ""){
		  			for ( var i = 0; i < ds_RejCont.size(); i++) {
		  				if(parseInt(rejSignSeq) == ds_RejCont.get(i, "signSeq")){
		  					// 반려 사유 표시
		  					$("#rejCont").text(ds_RejCont.get(i, "signOpnCont"));
		  				}
					}

		  		}

	  		}

	  		var arrT02 = new Array;
	  		// 타집행팀 경고 문구 표시
	  		for ( var i = 0; i < ds_Signln.size(); i++) {

	  			// 협조 목록을 가져온다.
	  			if(ds_Signln.get(i, "signTpCd") == "T03"){
	  					arrT02.push(ds_Signln.get(i));
	  			}
			}

	  		// 경영관리 책임자를 csys 목록에서 가져온다.
	  		// 여러명일수도 있음
	  		for(var i = 0; i < v_CmasList.length; i++){
				if(v_CmasList[i].privCd == "RO_CMAS_OT_004"){
					for(var j = 0; j < arrT02.length; j++){
//			  			console.log(arrT02[j].signTpCd);
//			  			console.log(arrT02[j].signUserId);

						// 결재선에 들어있는 협조가 경영관리 담당자라면 빠진다.
						if(arrT02[j].signUserId == v_CmasList[i].userId){
							arrT02.remove(j);
						}
			  		}
				}
			}

	  		if(arrT02.length > 0){
//	  			console.log("타집행팀 협조 있음!!!");
//	  			for(var j = 0; j < arrT02.length; j++){
//		  			console.log(arrT02[j].signTpCd);
//		  			console.log(arrT02[j].signUserId);
//		  		}
	  			$("#dSignDiv").show();
	  		}

	  		break;

	  	case "SAVE_GHR_COMMENT" :
	  		break;

	  	case "SAVE_CREATE_PDF_DOC" :
	  		$(".ajax_overlay").remove();
	  		break;

	  	case "SELECT_REJ_CONT" :
	  		ds_RejCont.setAllData(resultData.output1);
	  		break;
	  	case "SAVE_CONFIRM_SAP" :
	  		if(resultData.ds_Result[0].ErrMsg != undefined) alert(resultData.ds_Result[0].ErrMsg);
	  		$(".ajax_overlay").remove();
	  		break;
	  	case "SAVE_CONFIRM_ACCT" :
	  		$(".ajax_overlay").remove();
	  		break;
	  	default :
	  		break;
	  }
}

/**
 * 금액 포맷에 맞춤
 * @param val
 * @returns
 */
function gf_AmtFormat(val){
	var rslVal = val;
	var exp = new RegExp("(\\d)(?=(?:\\d{" + 3 + "})+(?!\\d))", "g");
	rslVal = rslVal.toString().replace(exp, '$1,');

	return rslVal;
}


// 국가리스트에서 나라이름으로 나라코드를 구한다.
function cf_GetNatCd(natNm){
	return ds_NatList.get(ds_NatList.find("natNm", natNm), "natCd");
}

//selector 의 id 로 show / hide 여부를 제어한다.
function cf_showHideSpanById(idName){

	var selector = "#" + idName;
	var idName = selector + ":checked";
	var idTr = selector + "Tr";
	if($(idName).size() < 1){
		//안체크
		$(idTr).hide();
	}else{
		// 체크
		$(idTr).show();
	}

}

// 기타비의 총합을 구한다
function totalEtcAmt(){

	var v1 = $("#loAirFare").val();
	v1 = removeComma(v1);
	if(v1 == "") v1 = 0;

	var v2 = $("#loTranFare").val();
	if(v2 == "") v2 = 0;
	v2 = removeComma(v2);

	var v3 = $("#visaFeeFare").val();
	if(v3 == "") v3 = 0;
	v3 = removeComma(v3);

	var v4 = $("#ovCharFare").val();
	if(v4 == "") v4 = 0;
	v4 = removeComma(v4);

	var v5 = $("#vocLeeFare").val();
	if(v5 == "") v5 = 0;
	v5 = removeComma(v5);

	var v6 = $("#hostFare").val();
	if(v6 == "") v6 = 0;
	v6 = removeComma(v6);

	var etcTotal = parseFloat(v1) + parseFloat(v2) + parseFloat(v3) + parseFloat(v4) + parseFloat(v5) + parseFloat(v6);

//	$("#etcTotalUSD").text(gf_AmtFormat(etcTotal));
	$("#etcTotalUSD").text(parseFloat(etcTotal.toFixed(3)));

	// 2015-09-02 계산 방식 변경
	// 기존 : 합계*환율, 변경 : 각각 금액*환율 하여 총합
//	var etcTotalEx = Math.round(etcTotal * v_USD);

	var etcTotalEx = Math.round(v1 * v_USD) + Math.round(v2 * v_USD) + Math.round(v3 * v_USD) + Math.round(v4 * v_USD) + Math.round(v5 * v_USD) + Math.round(v6 * v_USD);
	$("#etcTotalEx").text(gf_AmtFormat(etcTotalEx));

	return etcTotalEx;
}

function removeComma(obj){
	for(var i = 0; i < obj.length; i++){
		obj = obj.replace(",", "");
	}
	return obj;
}

// 예산 확인
function cf_CheckOsBizTrip(){


//	// 현지항공료
//	var v_TAirAmt;
//	var v_TAirAmtDtl;
//	// 현지 교통비
//	var v_TTranspAmt;
//	var v_TTranspAmtDtl;
//	// Visa Fee
//	var v_VisaFee;
//	var v_VisaFeeDtl;
//	// Over Charge
//	var v_OverCharge;
//	var v_OverChargeDtl;
//	// 복리후생
//	var v_Benefits;
//	var v_BenefitsDtl;
//	// 접대비
//	var v_HostAmt;
//	var v_HostAmtDtl;

	var params = {
			IBdgtType : v_BdgtType,
			IBdgtNo : v_Aufnr,
			IBdgtTeam : v_Kostv,
			ISendDate : $.datepicker.formatDate("yymmdd", new Date()),
			AirAmt : v_AirTotalAmt,
			StayAmt : v_cTotalAmt,
			EtcLairAmt : v_TAirAmt,
			EtcLtrAmt : v_TTranspAmt,
			EtcVisaAmt : v_VisaFee,
			EtcOverAmt : v_OverCharge,
			EtcWelAmt : v_Benefits,
			EtcRcAmt : v_HostAmt
	};



	gf_Transaction("SELECT_CHECK_OS_BIZ_TRIP", "/trip/eai/checkOsBizTrip.xpl", params, {}, "f_Callback", true);

}

function checkRefVal(positNm, cls){
	for(var i = 0; i < ds_RefList.size(); i++){
		if(ds_RefList.get(i, "positNm") == positNm && ds_RefList.get(i, "cls") == cls){
			return ds_RefList.get(i, "val");
		};
	}
	return -1;
}


function cf_getTripUserTrData(){

	 var dataSize = $("#tripUserDetail tr").size();
	 var idFlag = "tData";

	 var resultArr = new Array();

	 for(var i = 1; i < dataSize; i++){

		 var tData = new Object();

		 var dId = idFlag + i + " ";

		 var userEnm = $("#" + dId + "input[name='tUserEnm']").val();
		 var grade = $("#" + dId + "select[name='tGrade']").val();
		 var airAmount = $("#" + dId + "input[name='tAirAmount']").val();

		 for(var j = 0; j < airAmount.length; j++){
			 airAmount = airAmount.replace(",", "");
		 }

		 tData.userEnm = userEnm;
		 tData.seatGrade = grade;
		 tData.afare = airAmount;

		 resultArr.push(tData);

	 }

	 return resultArr;
}

function cf_saveOuterDraftDoc(){


	var pportIssueYmd = $("#fPassp").val().split("-")[0] + $("#fPassp").val().split("-")[1] + $("#fPassp").val().split("-")[2];
	var pportExprtnYmd = $("#tPassp").val().split("-")[0] + $("#tPassp").val().split("-")[1] + $("#tPassp").val().split("-")[2];

	var draftParams = {
			docNo : v_DocNo,
			refNo : "",
			userKnm : gv_userNm,
			userEnm : gv_userEnm,
			seatGrade : $("select[name='tripUserGrade']").val(),
			afare : v_AirTotalAmt,
			demItm : $("#tReq").val(),
			pportIssueYmd : pportIssueYmd,
			pportExprtnYmd : pportExprtnYmd,
			rem : $("#rem").val(),
			lclAfare : removeComma($("#loAirFareEx").text()),
			lclTrafficExpn : removeComma($("#loTranFareEx").text()),
			visaFee : removeComma($("#visaFeeFareEx").text()),
			excsExpn : removeComma($("#ovCharFareEx").text()),
			userId : gv_userId,
			docSts : 'D50', // 결재전 글로벌HR 검토
			wef : removeComma($("#vocLeeFareEx").text()),
			svcExpn : removeComma($("#hostFareEx").text())
	};

		var tripUserList = cf_getTripUserTrData();

//		!!

	for(var i = 0; i < tripUserList.length; i++){
		ds_TripUserList.add(tripUserList[i]);
	}


	var draftDataSet = {
			ds_TripUserList : ds_TripUserList.getAllData("U"),
			ds_VisaInfoList : ds_VisaInfoList.getAllData("U"),
			ds_CityList : ds_CityList.getAllData("U"),
			ds_ExpnList : ds_ExpnList.getAllData("U"),
			ds_Sign : ds_Sign.getAllData("U")
	};

	gf_Transaction("SAVE_OUTER_TRIP_DRAFT", "/trip/outerTrip/saveOuterTripDraft.xpl", draftParams, draftDataSet, "f_Callback", true);
}

function cf_setViewDocData(){

//	if(ds_sapData.get(0, "SapItab") == undefined){
//		alert('SAP 정보를 가져올 수 없습니다.');
//		self.close();
//		return;
//	}

	// 전표번호
	if(v_DocSts == "D03"){
		//전표번호
		$("#tOrdNoTr").show();

		// 비용이 없어 전표가 생성 되지 않은 문서의 경우 RefKey 가 .
		if(v_RefKey == "."){
			// 확정이 나지 않으므로 전표번호를 표시할 수 없음.
			$("#tOrdNo").text("");
			// 확정이 나지 않으므로 지불예정일도 기입되지 않음
			$("#payMDate").text("");
		}else{
			// 전표 번호 표시
			$("#tOrdNo").text(ds_sapData.get(0, "SapItab").Belnr);
			//지불예정일
			$("#payMDate").text(ds_sapData.get(0, "SapItab").Paydate);
		}


	}


	$("#refNo").text(v_RefKey);

	if(ds_sapData.get(0, "SapItab") != undefined){
		//gita no
		var gitaNo = ds_sapData.get(0, "SapItab").Refkey1;
		if(gitaNo != "" && gitaNo != undefined){
			$("#gitaNo").text(gitaNo);
		}
	}



	$("#signId").text(v_SignId);

	// 인터페이스 Param
	// 경비구분 예산번호 업무구분 집행팀
	var data = JSON.parse(ds_OuterTripDoc.get(0, "ifParam"));

		if(!(data.fileAtchId == "undefined" || data.fileAtchId == undefined)){
  			// 첨부파일이 존재하는 경우
  			gf_retrieveFileList(data.fileAtchId);

  		}

	// 문서 번호
	var docNoText = v_DocNo.split("-");
	$("#docNo").text(docNoText[1] + "-" + docNoText[2]);

	// 증빙일자
	var oDate = data.oDate;
//	if(oDate != "" && oDate != undefined && oDate != "undefined"){
//		oDate = $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", oDate));
//	}
	$("#oDate").text(oDate);

	// 숙소 구분
	if(data.innType == "H"){
  		$("#innType").text("현장숙소");
  	}else if(data.innType == "G"){
  		$("#innType").text("외부숙소(호텔)");
  	}

	// 작성자
	$("#drafter").text(data.drafterUserKnm + " (" + data.drafterUserId + ")");
	// 소속팀
	$("#drafterOrgNm").text(data.drafterUserOrgNm + " (" + data.drafterUserOrgCd + ")");

	var tripUserInfo = data.tripUserOrgNm + " (" + data.tripUserOrgCd + ") " + data.tripUserPositCd + " " +  data.tripUserId  + " " + data.tripUserKnm;
	$("#tripUserInfo").text(tripUserInfo);

	// 출장자 영문이름 좌석등급 항공료
	$("#tripUserEnm").val(ds_OuterTripDoc.get(0, "userEnm"));
//	$("#tripUserGrade").val(ds_OuterTripDoc.get(0, "seatGrade"));
	$("select[name='tripUserGrade']").val(ds_OuterTripDoc.get(0, "seatGrade"));
	$("#tripUserAirAmount").val(gf_AmtFormat(ds_OuterTripDoc.get(0, "afare")));
	// 동행인 영문이름 좌석등급 항공료
	for(var i = 0; i < ds_ExtnlPer.size(); i++){

		cf_AppendTripUser(ds_ExtnlPer.get(i));

	}

	// 총인원
	$("#totalUser").text($("#tripUserDetail tr").size()-1);

	var startDate = data.startDate;
	if(startDate != "" || startDate != undefined){
		startDate = $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", startDate));
	}

	var endDate = data.endDate;
	if(endDate != "" || endDate != undefined){
		endDate = $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", endDate));
	}

	// 출장기간
	var tripDate = startDate + " ~ " + endDate + " (기간 : " + (parseInt(data.tripDate)+1) + "일)";
	$("#tripDate").text(tripDate);

	// 출장목적
	$("#tPurp").val(ds_OuterTripDoc.get(0, "bustrGl"));
	// 요청사항
	$("#tReq").val(ds_OuterTripDoc.get(0, "demItm"));
	// 여권발급일 여권만료일

	var pportIssueYmd = ds_OuterTripDoc.get(0, "pportIssueYmd");
	if(pportIssueYmd != "" || pportIssueYmd != undefined){
		pportIssueYmd = $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", pportIssueYmd));
	}
	$("#fPassp").val(pportIssueYmd);

	var pportExprtnYmd = ds_OuterTripDoc.get(0, "pportExprtnYmd");
	if(pportExprtnYmd != "" || pportExprtnYmd != undefined){
		pportExprtnYmd = $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", pportExprtnYmd));
	}
	$("#tPassp").val(pportExprtnYmd);
	// 비자정보 국가 비자유무 비자정보
	for(var i = 0; i < ds_Visa.size(); i++){

		if(ds_Visa.get(i, "visaOwnYn") != ""){
			cf_AppendVisa(ds_Visa.get(i));
		}

	}
	// 방문지 국가 도시명 체류기간 주요업무내용
	for(var i = 0; i < ds_Visa.size(); i++){

		if(ds_Visa.get(i, "visaOwnYn") == ""){
			cf_AppendCity(ds_Visa.get(i));
		}

	}
	// 총방문지
	$("#totalVisaInfo").text($("#visaInfoDetail tr").size() - 1);
	// SAP 예산진행

	var bdgtType = data.bdgtType;
	switch(bdgtType) {
		case "A" :
			$("#bdgtType").text("A. 특정경비-임원");
			break;
		case "B" :
			$("#bdgtType").text("B. 특정경비-팀장");
			break;
		case "C" :
			$("#bdgtType").text("C. 특정경비-팀");
			break;
		case "I" :
			$("#bdgtType").text("I. 일반경비");
			break;
		case "O" :
			$("#bdgtType").text("O. 입찰경비");
			break;
		case "P" :
			$("#bdgtType").text("P. 사업경비");
			break;
		case "S" :
			$("#bdgtType").text("S. 사업경비-현장코드");
			break;
		case "R" :
			$("#bdgtType").text("R. 기술연구원경비");
			break;
		case "Q" :
			$("#bdgtType").text("Q. 현장경비");
			break;
		case "Q1" :
			$("#bdgtType").text("Q. PJ-현장경비");
			break;
		case "Q2" :
			$("#bdgtType").text("Q. PJ-PM경비");
			break;
		case "Q3" :
			$("#bdgtType").text("Q. PJ-PPM경비");
			break;
		case "Q4" :
			$("#bdgtType").text("Q. PJ-EM경비");
			break;
		case "Q5" :
			$("#bdgtType").text("Q. PJ-PCM경비");
			break;
		case "K" :
			$("#bdgtType").text("K. 본사집행현장원가");
			break;
		default :
  		break;
	}


	var AufnrText = data.aufnr + " / 내역 : " + data.kText;
	$("#Aufnr").text(AufnrText);

//	$("#Aufnr").text(data.aufnr);
	$("#cGubun").val(data.cGugun);
	$("#bdgtTeam").text(data.kostVNm + " (" + data.kostV + ")");

	if(bdgtType == "I" || bdgtType == "O" || bdgtType == "K"){
  		// I 일반경비 일 경우 경비 이체코드 입력창을 보인다.
  		$("#VTCodeSpan").show();
  		$("#vTcode").val(data.vTcode);
  	}
//	$("#bdgtTeam").text(data.kostVNm + " (" + data.kostV + ") / 집행팀장 : " + data.chiefNm + " (" + data.chief + ")");

	// 항공료

	// 특이사항
	$("#pecul").val(ds_OuterTripDoc.get(0, "pecul"));
	// 기타경비
	// 현지항공료 현지교통비 VISAFEE OverCharge 복리후생 접대비 합계
	$("#loAirFare").val(ds_OuterTripDoc.get(0, "lclAfare"));

	$("#loTranFare").val(ds_OuterTripDoc.get(0, "lclTrafficExpn"));

	$("#visaFeeFare").val(ds_OuterTripDoc.get(0, "visaFee"));

	$("#ovCharFare").val(ds_OuterTripDoc.get(0, "excsExpn"));

	$("#vocLeeFare").val(ds_OuterTripDoc.get(0, "wef"));

	$("#hostFare").val(ds_OuterTripDoc.get(0, "svcExpn"));

	// 집행세부내역
	$("#loAirFareDtl").val(data.tAirAmtDtl);
	$("#loTranFareDtl").val(data.tTranspAmtDtl);
	$("#visaFeeFareDtl").val(data.visaFeeDtl);
	$("#ovCharFareDtl").val(data.overChargeDtl);
	$("#vocLeeFareDtl").val(data.benefitsDtl);
	$("#hostFareDtl").val(data.hostAmtDtl);

	// 송급계좌
	$("#retAccount").text(data.accountNo);

	//기타경비지불일자
	$("#etcPayDate").val(data.etcPayDate);

	// 체제비

	// 체제비 합계 단위별 환율조회(작성시 날짜)

	// 개인지급합계
	// 총계
	// 비고
	$("#rem").text(ds_OuterTripDoc.get(0, "rem"));
	// 협조내역
	for(var i = 0; i < data.assistComment.length;i++){
		data.assistComment = data.assistComment.replace("##", "\n");
	}
	$("#assistComment").val(data.assistComment);

	// SEC팀 여부
	if(!(data.secYn == "undefined" || data.secYn == undefined)){
		v_secYn = data.secYn;
		if(v_secYn == "Y"){
  			$("#secTr").show();
  		}
	}

	if(ds_OuterTripDoc.get(0, "perchrgRvwOpn1") != ""){
		$("#secTr").show();
		//sec팀 의견
		$("#secComment").val(ds_OuterTripDoc.get(0, "perchrgRvwOpn1"));

	}

	if(ds_OuterTripDoc.get(0, "perchrgRvwOpn2") != ""){
//		$("#ghrTr").show();
		//sec팀 의견
		$("#ghrComment").val(ds_OuterTripDoc.get(0, "perchrgRvwOpn2"));

	}

	// 첨부파일


}

//ViewDoc 전용
function cf_AppendTripUser(obj){

	if($("#tripUserDetail tr").size() < 4){
		$("#tripUserDetail").append(v_tripUserDetailTr);

		// last 에 ID 부여
		var idFlag = "tData";
		var indexF = "" + $("#tripUserDetail tr:last").index();

		idFlag = idFlag + indexF;
		$("#tripUserDetail tr:last").attr("id", idFlag);

//		var v_tripUserDetailTr = "<tr><td class=\"inpt\" style=\"width:40%; text-align:center\"><input type=\"text\" name=\"tUserEnm\" style=\"border:0\" readonly=\"readonly\"></td>"
//			 + "<td class=\"inpt\" style=\"width:30%; text-align:center\"><select name=\"tGrade\" style=\"border:0\" disabled=\"disabled\"><option value=\"F\" selected=\"selected\">First</option><option value=\"B\">Business</option><option value=\"E\">Economy</option></select></td>"
//			 + "<td class=\"inpt\" style=\"width:30%; text-align:center\"><input type=\"text\" name=\"tAirAmount\" style=\"border:0\" readonly=\"readonly\">원</td></tr>";


		$("#tripUserDetail tr:last input[name='tUserEnm']").val(obj.userEnm);
		$("#tripUserDetail tr:last select[name='tGrade']").val(obj.seatGrade);
		$("#tripUserDetail tr:last input[name='tAirAmount']").val(gf_AmtFormat(obj.afare));

	}else{
		gf_AlertMsg("더이상 추가할 수 없습니다");
	}

}

//ViewDoc 전용
function cf_AppendVisa(obj) {

	// 새로운 Tr Append
	$("#visaInfoDetail").append(v_visaInfoDetailTr);

	var idFlag = "vData";
	var indexF = "" + $("#visaInfoDetail tr:last").index();

	idFlag = idFlag + indexF;
	$("#visaInfoDetail tr:last").attr("id", idFlag);

//	var v_visaInfoDetailTr = "<tr><td class=\"inpt\" style=\"width:20%; text-align:center\"><input type=\"text\" name=\"vNat\" style=\"border:0\" readonly=\"readonly\"></td><td class=\"inpt\" style=\"width:20%; text-align:center\"><input type=\"text\" name=\"vVisaYn\" style=\"border:0\" readonly=\"readonly\"></td>"
//		 + "<td class=\"inpt\" style=\"width:60%; text-align:center\">	발급일 : <input type=\"text\" name=\"vStartDate\" style=\"border:0\" readonly=\"readonly\"> / 만료일 : <input type=\"text\" name=\"vEndDate\" style=\"border:0\" readonly=\"readonly\"></td></tr>";

	$("#visaInfoDetail tr:last input[name='vNat']").val(cf_GetNatNm(obj.natCd));

	var visaOwnYn = obj.visaOwnYn;
	if(visaOwnYn == "Y"){
		visaOwnYn = "소지";
	}else if(visaOwnYn == "N"){
		visaOwnYn = "미소지";
	}else if(visaOwnYn == "V"){
		visaOwnYn = "비자면제국가방문";
	}else if(visaOwnYn == "T"){
		visaOwnYn = "현지공항비자발급";
	}

	$("#visaInfoDetail tr:last input[name='vVisaYn']").val(visaOwnYn);
	$("#visaInfoDetail tr:last input[name='vStartDate']").val($.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", obj.visaIssueYmd)));
	$("#visaInfoDetail tr:last input[name='vEndDate']").val($.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", obj.visaExprtnYmd)));

}

// ViewDoc 전용
function cf_AppendCity(obj){


	// 새로운 Tr Append
	$("#cityDetail").append(v_cityDetailTr);
	$("#totalCityInfo").text($("#cityDetail tr").size() - 1);

	var idFlag = "cData";
	var indexF = "" + $("#cityDetail tr:last").index();

	idFlag = idFlag + indexF;
	$("#cityDetail tr:last").attr("id", idFlag);

	$("#cityDetail tr:last input[name='natNm']").val(cf_GetNatNm(obj.natCd));
	$("#cityDetail tr:last input[name='cityNm']").val(obj.cityNm);

	$("#cityDetail tr:last input[name='stayStYmd']").val($.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", obj.stayStYmd)));
	$("#cityDetail tr:last input[name='stayEdYmd']").val($.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", obj.stayEdYmd)));
	$("#cityDetail tr:last textarea[name='dutyCont']").val(obj.dutyCont);





}

function cf_RetrieveSignInfo(){

	//해당 업무구분과 업무분류에 해당되는 양식명을 가져옴
	var signParams = {
			docNo : v_DocNo
	};

	gf_Transaction("SELECT_SIGN_INFO", "/trip/outerTrip/retrieveSignInfo.xpl", signParams, {}, "f_Callback", true);
}


function cf_AppendSavedExpn(obj){

	var result = obj;

	var edtTxtVal = "";

	v_cTotalAmt = 0;

	v_cTotUSD = 0;
	v_cTotEUR = 0;
	v_cTotGBP = 0;
	v_cTotJPY = 0;

	// DataSet 초기화
//	ds_ExpnList.clear();


//	expn_cls, 비용구분   --> 구분코드
//	accomo_cls, 숙박구분 --> 구분코드(한글)
//	accomo_expn,  숙박비용 --> KRW
//	accomo_expn_dcnt, 숙박비용일수 --> 사용일수
//	dd_expn, 일비용 --> 기준액 * 일
//	dd_expn_dcnt, 일비용일수 --> 기준액

	var tripUserPositCd = JSON.parse(ds_OuterTripDoc.get(0, "ifParam")).tripUserPositCd;

	for(var i = 0; i < result.length; i++){
		var idTr = "#" + result[i].expnCls + "Tr";
		$(idTr).show();
		//숙박비(＄) 	유럽숙박비(€)	영국숙박비(￡)	일본숙박비(￥)

		if(result[i].expnCls == "usNight"){
			edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
			  		+ " $" + gf_AmtFormat(parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))
			  		+ " / ￦" + gf_AmtFormat(Math.round(v_USD * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));

			v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));

			v_cTotUSD = v_cTotUSD + (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt));

			var obj = new Object();
			obj.expnCls = result[i].expnCls; // 구분코드
			obj.accomoCls = "일반숙박"; // 구분코드(한글)
			obj.accomoExpn = Math.round(v_USD * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))); // KRW
			obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
			obj.ddExpnDcnt = parseInt(result[i].ddExpnDcnt); // 기준액
			obj.ddExpn = (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수

			ds_ExpnList.add(obj);


		}else if(result[i].expnCls == "usDay"){
			edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
	  		+ " $" + gf_AmtFormat(parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))
	  		+ " / ￦" + gf_AmtFormat(Math.round(v_USD * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));

			v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));

			v_cTotUSD = v_cTotUSD + (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt));

			var obj = new Object();
			obj.expnCls = result[i].expnCls; // 구분코드
			obj.accomoCls = "일반일당"; // 구분코드(한글)
			obj.accomoExpn = Math.round(v_USD * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))); // KRW
			obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
			obj.ddExpnDcnt = parseInt(result[i].ddExpnDcnt); // 기준액
			obj.ddExpn = (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수

			ds_ExpnList.add(obj);


		}else if(result[i].expnCls == "edu"){
			edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
	  		+ " $" + gf_AmtFormat(parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))
	  		+ " / ￦" + gf_AmtFormat(Math.round(v_USD * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));

			v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));

			v_cTotUSD = v_cTotUSD + (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt));

			var obj = new Object();
			obj.expnCls = result[i].expnCls; // 구분코드
			obj.accomoCls = "연수경비(숙)"; // 구분코드(한글)
			obj.accomoExpn = Math.round(v_USD * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))); // KRW
			obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
			obj.ddExpnDcnt = parseInt(result[i].ddExpnDcnt); // 기준액
			obj.ddExpn = (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수

			ds_ExpnList.add(obj);


		}else if(result[i].expnCls == "spot"){
			edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
	  		+ " $" + gf_AmtFormat(parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))
	  		+ " / ￦" + gf_AmtFormat(Math.round(v_USD * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));

			v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));

			v_cTotUSD = v_cTotUSD + (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt));

			var obj = new Object();
			obj.expnCls = result[i].expnCls; // 구분코드
			obj.accomoCls = "연수경비(숙식)"; // 구분코드(한글)
			obj.accomoExpn = Math.round(v_USD * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))); // KRW
			obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
			obj.ddExpnDcnt = parseInt(result[i].ddExpnDcnt); // 기준액
			obj.ddExpn = (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수

			ds_ExpnList.add(obj);


		}else if(result[i].expnCls == "euNight"){
			edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
	  		+ " €" + gf_AmtFormat(parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))
	  		+ " / ￦" + gf_AmtFormat(Math.round(v_EUR * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));

			v_cTotalAmt = v_cTotalAmt + (Math.round(v_EUR * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));

			v_cTotEUR = v_cTotEUR + (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt));

			var obj = new Object();
			obj.expnCls = result[i].expnCls; // 구분코드
			obj.accomoCls = "유럽숙박"; // 구분코드(한글)
			obj.accomoExpn = Math.round(v_USD * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))); // KRW
			obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
			obj.ddExpnDcnt = parseInt(result[i].ddExpnDcnt); // 기준액
			obj.ddExpn = (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수

			ds_ExpnList.add(obj);

		}else if(result[i].expnCls == "euDay"){
			edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
	  		+ " €" + gf_AmtFormat(parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))
	  		+ " / ￦" + gf_AmtFormat(Math.round(v_EUR * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));

			v_cTotalAmt = v_cTotalAmt + (Math.round(v_EUR * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));

			v_cTotEUR = v_cTotEUR + (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt));

			var obj = new Object();
			obj.expnCls = result[i].expnCls; // 구분코드
			obj.accomoCls = "유럽일당"; // 구분코드(한글)
			obj.accomoExpn = Math.round(v_USD * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))); // KRW
			obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
			obj.ddExpnDcnt = parseInt(result[i].ddExpnDcnt); // 기준액
			obj.ddExpn = (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수

			ds_ExpnList.add(obj);

		}else if(result[i].expnCls == "enNight"){
			edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
	  		+ " ￡" + gf_AmtFormat(parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))
	  		+ " / ￦" + gf_AmtFormat(Math.round(v_GBP * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));

			v_cTotalAmt = v_cTotalAmt + (Math.round(v_GBP * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));

			v_cTotGBP = v_cTotGBP + (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt));

			var obj = new Object();
			obj.expnCls = result[i].expnCls; // 구분코드
			obj.accomoCls = "영국숙박"; // 구분코드(한글)
			obj.accomoExpn = Math.round(v_USD * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))); // KRW
			obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
			obj.ddExpnDcnt = parseInt(result[i].ddExpnDcnt); // 기준액
			obj.ddExpn = (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수

			ds_ExpnList.add(obj);

		}else if(result[i].expnCls == "enDay"){
			edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
	  		+ " ￡" + gf_AmtFormat(parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))
	  		+ " / ￦" + gf_AmtFormat(Math.round(v_GBP * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));

			v_cTotalAmt = v_cTotalAmt + (Math.round(v_GBP * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));

			v_cTotGBP = v_cTotGBP + (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt));

			var obj = new Object();
			obj.expnCls = result[i].expnCls; // 구분코드
			obj.accomoCls = "영국일당"; // 구분코드(한글)
			obj.accomoExpn = Math.round(v_USD * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))); // KRW
			obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
			obj.ddExpnDcnt = parseInt(result[i].ddExpnDcnt); // 기준액
			obj.ddExpn = (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수

			ds_ExpnList.add(obj);

		}else if(result[i].expnCls == "jaNight"){
			edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
	  		+ " ￥" + gf_AmtFormat(parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))
	  		+ " / ￦" + gf_AmtFormat(Math.round(v_JPY * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));

			v_cTotalAmt = v_cTotalAmt + (Math.round(v_JPY * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));

			v_cTotJPY = v_cTotJPY + (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt));

			var obj = new Object();
			obj.expnCls = result[i].expnCls; // 구분코드
			obj.accomoCls = "일본숙박"; // 구분코드(한글)
			obj.accomoExpn = Math.round(v_USD * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))); // KRW
			obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
			obj.ddExpnDcnt = parseInt(result[i].ddExpnDcnt); // 기준액
			obj.ddExpn = (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수

			ds_ExpnList.add(obj);

		}else if(result[i].expnCls == "jaDay"){
			edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
	  		+ " ￥" + gf_AmtFormat(parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))
	  		+ " / ￦" + gf_AmtFormat(Math.round(v_JPY * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));

			v_cTotalAmt = v_cTotalAmt + (Math.round(v_JPY * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));

			v_cTotJPY = v_cTotJPY + (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt));


			var obj = new Object();
			obj.expnCls = result[i].expnCls; // 구분코드
			obj.accomoCls = "일본일당"; // 구분코드(한글)
			obj.accomoExpn = Math.round(v_USD * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))); // KRW
			obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
			obj.ddExpnDcnt = parseInt(result[i].ddExpnDcnt); // 기준액
			obj.ddExpn = (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수

			ds_ExpnList.add(obj);
		}

		$("#" + result[i].expnCls).text(edtTxtVal);

	}



//	for(var i = 0; i < result.length; i++){
//			var idTr = "#" + result[i].expnCls + "Tr";
//			$(idTr).show();
//			//숙박비(＄) 	유럽숙박비(€)	영국숙박비(￡)	일본숙박비(￥)
//
//			if(result[i].expnCls == "usNight"){
//				edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//				  		+ " $" + gf_AmtFormat(checkRefVal(tripUserPositCd, "일반숙박") * parseInt(result[i].accomoExpnDcnt))
//				  		+ " / ￦" + gf_AmtFormat(Math.round(v_USD * (checkRefVal(tripUserPositCd, "일반숙박") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (checkRefVal(tripUserPositCd, "일반숙박") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotUSD = v_cTotUSD + (checkRefVal(tripUserPositCd, "일반숙박") * parseInt(result[i].accomoExpnDcnt));
//
//				var obj = new Object();
//				obj.expnCls = result[i].expnCls; // 구분코드
//				obj.accomoCls = "일반숙박"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(tripUserPositCd, "일반숙박") * parseInt(result[i].accomoExpnDcnt))); // KRW
//				obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(tripUserPositCd, "일반숙박"); // 기준액
//				obj.ddExpn = (checkRefVal(tripUserPositCd, "일반숙박") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//
//			}else if(result[i].expnCls == "usDay"){
//				edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//		  		+ " $" + gf_AmtFormat(checkRefVal(tripUserPositCd, "일반일당") * parseInt(result[i].accomoExpnDcnt))
//		  		+ " / ￦" + gf_AmtFormat(Math.round(v_USD * (checkRefVal(tripUserPositCd, "일반일당") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (checkRefVal(tripUserPositCd, "일반일당") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotUSD = v_cTotUSD + (checkRefVal(tripUserPositCd, "일반일당") * parseInt(result[i].accomoExpnDcnt));
//
//				var obj = new Object();
//				obj.expnCls = result[i].expnCls; // 구분코드
//				obj.accomoCls = "일반일당"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(tripUserPositCd, "일반일당") * parseInt(result[i].accomoExpnDcnt))); // KRW
//				obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(tripUserPositCd, "일반일당"); // 기준액
//				obj.ddExpn = (checkRefVal(tripUserPositCd, "일반일당") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//
//			}else if(result[i].expnCls == "edu"){
//				edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//		  		+ " $" + gf_AmtFormat(checkRefVal(tripUserPositCd, "연수경비(숙)") * parseInt(result[i].accomoExpnDcnt))
//		  		+ " / ￦" + gf_AmtFormat(Math.round(v_USD * (checkRefVal(tripUserPositCd, "연수경비(숙)") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (checkRefVal(tripUserPositCd, "연수경비(숙)") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotUSD = v_cTotUSD + (checkRefVal(tripUserPositCd, "연수경비(숙)") * parseInt(result[i].accomoExpnDcnt));
//
//				var obj = new Object();
//				obj.expnCls = result[i].expnCls; // 구분코드
//				obj.accomoCls = "연수경비(숙)"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(tripUserPositCd, "연수경비(숙)") * parseInt(result[i].accomoExpnDcnt))); // KRW
//				obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(tripUserPositCd, "연수경비(숙)"); // 기준액
//				obj.ddExpn = (checkRefVal(tripUserPositCd, "연수경비(숙)") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//
//			}else if(result[i].expnCls == "spot"){
//				edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//		  		+ " $" + gf_AmtFormat(checkRefVal(tripUserPositCd, "연수경비(숙식)") * parseInt(result[i].accomoExpnDcnt))
//		  		+ " / ￦" + gf_AmtFormat(Math.round(v_USD * (checkRefVal(tripUserPositCd, "연수경비(숙식)") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (checkRefVal(tripUserPositCd, "연수경비(숙식)") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotUSD = v_cTotUSD + (checkRefVal(tripUserPositCd, "연수경비(숙식)") * parseInt(result[i].accomoExpnDcnt));
//
//				var obj = new Object();
//				obj.expnCls = result[i].expnCls; // 구분코드
//				obj.accomoCls = "연수경비(숙식)"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(tripUserPositCd, "연수경비(숙식)") * parseInt(result[i].accomoExpnDcnt))); // KRW
//				obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(tripUserPositCd, "연수경비(숙식)"); // 기준액
//				obj.ddExpn = (checkRefVal(tripUserPositCd, "연수경비(숙식)") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//
//			}else if(result[i].expnCls == "euNight"){
//				edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//		  		+ " €" + gf_AmtFormat(checkRefVal(tripUserPositCd, "유럽숙박") * parseInt(result[i].accomoExpnDcnt))
//		  		+ " / ￦" + gf_AmtFormat(Math.round(v_EUR * (checkRefVal(tripUserPositCd, "유럽숙박") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_EUR * (checkRefVal(tripUserPositCd, "유럽숙박") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotEUR = v_cTotEUR + (checkRefVal(tripUserPositCd, "유럽숙박") * parseInt(result[i].accomoExpnDcnt));
//
//				var obj = new Object();
//				obj.expnCls = result[i].expnCls; // 구분코드
//				obj.accomoCls = "유럽숙박"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(tripUserPositCd, "유럽숙박") * parseInt(result[i].accomoExpnDcnt))); // KRW
//				obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(tripUserPositCd, "유럽숙박"); // 기준액
//				obj.ddExpn = (checkRefVal(tripUserPositCd, "유럽숙박") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//			}else if(result[i].expnCls == "euDay"){
//				edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//		  		+ " €" + gf_AmtFormat(checkRefVal(tripUserPositCd, "유럽일당") * parseInt(result[i].accomoExpnDcnt))
//		  		+ " / ￦" + gf_AmtFormat(Math.round(v_EUR * (checkRefVal(tripUserPositCd, "유럽일당") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_EUR * (checkRefVal(tripUserPositCd, "유럽일당") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotEUR = v_cTotEUR + (checkRefVal(tripUserPositCd, "유럽일당") * parseInt(result[i].accomoExpnDcnt));
//
//				var obj = new Object();
//				obj.expnCls = result[i].expnCls; // 구분코드
//				obj.accomoCls = "유럽일당"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(tripUserPositCd, "유럽일당") * parseInt(result[i].accomoExpnDcnt))); // KRW
//				obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(tripUserPositCd, "유럽일당"); // 기준액
//				obj.ddExpn = (checkRefVal(tripUserPositCd, "유럽일당") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//			}else if(result[i].expnCls == "enNight"){
//				edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//		  		+ " ￡" + gf_AmtFormat(checkRefVal(tripUserPositCd, "영국숙박") * parseInt(result[i].accomoExpnDcnt))
//		  		+ " / ￦" + gf_AmtFormat(Math.round(v_GBP * (checkRefVal(tripUserPositCd, "영국숙박") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_GBP * (checkRefVal(tripUserPositCd, "영국숙박") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotGBP = v_cTotGBP + (checkRefVal(tripUserPositCd, "영국숙박") * parseInt(result[i].accomoExpnDcnt));
//
//				var obj = new Object();
//				obj.expnCls = result[i].expnCls; // 구분코드
//				obj.accomoCls = "영국숙박"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(tripUserPositCd, "영국숙박") * parseInt(result[i].accomoExpnDcnt))); // KRW
//				obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(tripUserPositCd, "영국숙박"); // 기준액
//				obj.ddExpn = (checkRefVal(tripUserPositCd, "영국숙박") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//			}else if(result[i].expnCls == "enDay"){
//				edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//		  		+ " ￡" + gf_AmtFormat(checkRefVal(tripUserPositCd, "영국일당") * parseInt(result[i].accomoExpnDcnt))
//		  		+ " / ￦" + gf_AmtFormat(Math.round(v_GBP * (checkRefVal(tripUserPositCd, "영국일당") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_GBP * (checkRefVal(tripUserPositCd, "영국일당") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotGBP = v_cTotGBP + (checkRefVal(tripUserPositCd, "영국일당") * parseInt(result[i].accomoExpnDcnt));
//
//				var obj = new Object();
//				obj.expnCls = result[i].expnCls; // 구분코드
//				obj.accomoCls = "영국일당"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(tripUserPositCd, "영국일당") * parseInt(result[i].accomoExpnDcnt))); // KRW
//				obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(tripUserPositCd, "영국일당"); // 기준액
//				obj.ddExpn = (checkRefVal(tripUserPositCd, "영국일당") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//			}else if(result[i].expnCls == "jaNight"){
//				edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//		  		+ " ￥" + gf_AmtFormat(checkRefVal(tripUserPositCd, "일본숙박") * parseInt(result[i].accomoExpnDcnt))
//		  		+ " / ￦" + gf_AmtFormat(Math.round(v_JPY * (checkRefVal(tripUserPositCd, "일본숙박") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_JPY * (checkRefVal(tripUserPositCd, "일본숙박") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotJPY = v_cTotJPY + (checkRefVal(tripUserPositCd, "일본숙박") * parseInt(result[i].accomoExpnDcnt));
//
//				var obj = new Object();
//				obj.expnCls = result[i].expnCls; // 구분코드
//				obj.accomoCls = "일본숙박"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(tripUserPositCd, "일본숙박") * parseInt(result[i].accomoExpnDcnt))); // KRW
//				obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(tripUserPositCd, "일본숙박"); // 기준액
//				obj.ddExpn = (checkRefVal(tripUserPositCd, "일본숙박") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//			}else if(result[i].expnCls == "jaDay"){
//				edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//		  		+ " ￥" + gf_AmtFormat(checkRefVal(tripUserPositCd, "일본일당") * parseInt(result[i].accomoExpnDcnt))
//		  		+ " / ￦" + gf_AmtFormat(Math.round(v_JPY * (checkRefVal(tripUserPositCd, "일본일당") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_JPY * (checkRefVal(tripUserPositCd, "일본일당") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotJPY = v_cTotJPY + (checkRefVal(tripUserPositCd, "일본일당") * parseInt(result[i].accomoExpnDcnt));
//
//
//				var obj = new Object();
//				obj.expnCls = result[i].expnCls; // 구분코드
//				obj.accomoCls = "일본일당"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(tripUserPositCd, "일본일당") * parseInt(result[i].accomoExpnDcnt))); // KRW
//				obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(tripUserPositCd, "일본일당"); // 기준액
//				obj.ddExpn = (checkRefVal(tripUserPositCd, "일본일당") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//			}
//
//			$("#" + result[i].expnCls).text(edtTxtVal);
//
//	}

	$("#usdTotal").text(v_cTotUSD);
	$("#usdTotalKr").text(gf_AmtFormat(Math.round(v_cTotUSD * v_USD)));

	$("#euroTotal").text(v_cTotEUR);
	$("#euroTotalKr").text(gf_AmtFormat(Math.round(v_cTotEUR * v_EUR)));

	$("#gbpTotal").text(v_cTotGBP);
	$("#gbpTotalKr").text(gf_AmtFormat(Math.round(v_cTotGBP * v_GBP)));

	$("#jaTotal").text(v_cTotJPY);
	$("#jaTotalKr").text(gf_AmtFormat(Math.round(v_cTotJPY * v_JPY)));

	$("#cTotalAmt").text(gf_AmtFormat(v_cTotalAmt + totalEtcAmt()));


	//2015-08-31 동행인이 있을 경우 체제비는 동행인 수만큼 배수
	var pTU = 1;
	if($("#tripUserDetail tr").size() > 1){
		pTU = $("#tripUserDetail tr").size();
		v_cTotalAmt = v_cTotalAmt*pTU;
	}

	$("#cTotalAmt").text(gf_AmtFormat(v_cTotalAmt + totalEtcAmt()));


	// 개인지급 합계 (체재비 + 항공료)
	v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;
	$("#totalAmount").text(gf_AmtFormat(v_TotalAmount));

}

//국가리스트에서 나라이름으로 나라코드를 구한다.
function cf_GetNatNm(natCd){
	return ds_NatList.get(ds_NatList.find("natCd", natCd), "natNm");
}


function f_calcAirAmount(){
	var tAmount = $("input[name='tAirAmount']");
	var total = 0;
	for(var i = 0; i < tAmount.size(); i++){
		var tVal = tAmount[i].value;
		if(tVal == "") continue;
		for(var j = 0; j < tVal.length; j++){
			tVal = tVal.replace(",", "");
		}
		total = total + parseInt(tVal);
	}

	var tUserVal = $("#tripUserAirAmount").val();

	for(var j = 0; j < tUserVal.length; j++){
		tUserVal = tUserVal.replace(",", "");
	}

	total = total + parseInt(tUserVal);

	v_AirTotalAmt = total;
	$("#airTotalAmount").text(gf_AmtFormat(total));

	// 개인지급 합계 (채재바 + 항공료)
	v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;

	$("#totalAmount").text(gf_AmtFormat(v_TotalAmount));
}

function f_notSpace(val){
	if(val == "" || val == undefined) val = 0;
	return val;
}

//문서만 삭제
function f_deleteDoc(){
	var params = {
			docNo : v_DocNo
	};

	gf_Transaction("SAVE_DELETE_DOC", "/trip/outerTrip/deleteOuterTripDoc.xpl", params, {}, "f_Callback", true);

}

//임시전표만 삭제
function f_rejectSAP(){

	gf_InitSpinner(top.$('body'));

	var data = JSON.parse(ds_OuterTripDoc.get(0, "ifParam"));

/*	if(v_RefKey == "" || v_RefKey == undefined){
//		alert("REF NO 가 존재하지 않습니다.");
		return;
	}else if(v_DocNo == "" || v_DocNo == undefined){
//		alert("문서번호가 존재하지 않습니다.");
		return;
	}else if(data.drafterUserId == "" || data.drafterUserId == undefined){
//		alert("작성자가 등록되어 있지 않습니다.");
		return;
	}else{
*/
	//	I_REFKEY	CHAR	 15 	신청번호
	//	I_NOTESDOC	CHAR	 32 	CMAS 문서번호
	//	I_CONFIRM	CHAR	1	결재모드 ( Y: 결재, N: 반려 )
	//	I_SENDER_ID	CHAR	7	결재자사번
	//	I_BUKRS	CHAR	4	회사코드 (무조건 1000)

		var params = {
				refNo : v_RefKey,
				docNo : v_DocNo,
				confirm : "N",
				userId : data.drafterUserId
		};

		gf_Transaction("SAVE_CONFIRM_SAP", "/trip/outerTrip/confirmSap.xpl", params, {}, "f_Callback", true);
/*	}*/

}