/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/

var v_DocSts; // 상태
var v_AdjustDocSts; // 정산서 상태


var ds_OuterTripDoc = new DataSet();
var ds_ExtnlPer = new DataSet();
var ds_Expn = new DataSet();
var ds_Visa = new DataSet();
var ds_CityList = new DataSet();

var ds_SavedDoc = new DataSet();

var v_IfParam = "";

// 문서번호
var v_DocNo;
// 통합결재
var v_AdjustSignId;
// RefNo
var v_RptRefNo;
// 작성자 정보
var v_DraftUserId;
var v_DraftUserKnm;
var v_DraftUserEnm;
var v_DraftUserOrgCd;
var v_DraftUserOrgNm;
var v_DraftUserPositCd;
var v_DraftUserRpswrkCd;

var ds_NatList = new DataSet();
var ds_RefList = new DataSet();
var ds_ExRList = new DataSet();
var v_EUR;
var v_GBP;
var v_USD;
var v_JPY;

var v_cTotalAmt = 0;
var v_cTotalAmt2 = 0;

var v_cTotUSD = 0;
var v_cTotEUR = 0;
var v_cTotGBP = 0;
var v_cTotJPY = 0;

var v_GitaAmt = 0; // 기타비용(현지항공료+현지교통비+VISA FEE+Over charge)
var v_GitaAmt2 = 0; // 기타비용 (복리후생비)
var v_GitaAmt3 = 0; // 기타비용 (접대비)

var v_CallbackFunc;

//본부장 이상 직책 삽입
var ds_HqRpswrkCd = new DataSet();

// 팀장 이상 직책 삽입
var ds_ChiefCd = new DataSet();

var ds_Signln = new DataSet();


var hostCheck = 0;
var hostCheck2 = 0;

var v_bossSignYn = "N";

var ds_sapData = new DataSet();
var ds_aSapData = new DataSet();

var v_SapData;


var v_CmasList;

var ds_RejCont = new DataSet();



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
	v_loclCd = gf_GetCookie("loclCd");		// 로케일 20140724

	v_DocNo = gf_IsNull(datas.fromList) ? "" : datas.fromList;
	v_RptRefNo = gf_IsNull(datas.rptRefNo) ? "" : datas.rptRefNo;
	v_DocSts = gf_IsNull(datas.docSts) ? "" : datas.docSts;
	v_AdjustDocSts = gf_IsNull(datas.adjustDocSts) ? "" : datas.adjustDocSts;
	v_AdjustSignId = gf_IsNull(datas.adjustSignId) ? "" : datas.adjustSignId;
	v_CmasList = gf_IsNull(datas.cmasList) ? "" : datas.cmasList;

	v_CallbackFunc = gf_IsNull(datas.callbackFunc) ? ""          : datas.callbackFunc;
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

	//(정산)신청서 삭제 .2019.05.17 - g.kim
	$("#docDelBtn").click(function(){	//신청서삭제 (임시전표 포함X) 별도 ISSUE 있을 수 있어서 분리.
		if(confirm("신청서를 삭제하시겠습니까") == true){
			//신청서 삭제
			f_deleteDoc();
		};
	});

	//(정산) 임시전표 삭제. 2091.05.17 - g.kim

	$("#rejectSap").click(function(){

		if(confirm("임시전표를 삭제하시겠습니까? (사용자는 문서 내용을 조회할 수 없게됩니다.)")){

			gf_InitSpinner(top.$('body'));

			var data = JSON.parse(ds_OuterTripDoc.get(0, "ifParam"));

			if(v_RptRefNo == "" || v_RptRefNo == undefined){
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
						refNo : v_RptRefNo,
						docNo : v_DocNo,
						confirm : "N",
						userId : data.drafterUserId
				};

				gf_Transaction("SAVE_CONFIRM_SAP", "/trip/outerTrip/confirmSap.xpl", params, {}, "f_Callback", true);
			}

		}

	});

	$("#outerTripClose").click(function(){
		self.close();
	});

	// 반려된 문서 재작성
	$("#adjustReDraft").click(function(){

		var params = {
				fromReject : "Y",
				cmasList : v_CmasList,
				fromList : v_DocNo,
				refNo : v_RptRefNo,
				docSts : v_DocSts,
				ds_SavedDoc : ds_SavedDoc.getAllData(),
				ds_Signln : ds_Signln.getAllData()
		};

		gf_PostReplace("/trip/outerTrip/outerTripAdjust.jsp", params, true, true, null);


	});

	$("#print").click(function(){

		var params = {
				ds_Signln : ds_Signln.getAllData(),
				tripUserInfo : $("#tripUserInfo").text(),
				tripUserTotal : $("#tripUserTotal").text(),
				tripNat : $("#tripNat").val(),
				fTripDate : $("#fTripDate").text(),
				rTripDateStart : $("#rTripDateStart").val(),
				rTripDateEnd : $("#rTripDateEnd").val(),
				rTripDate : $("#rTripDate").text(),
				payDate : $("#payDate").val(),
				tReq : $("#tReq").val(),
				tPurp : $("#tPurp").val(),
				tPlanDtl : $("#tPlanDtl").val(),
				tRptDtl : $("#tRptDtl").val(),
				tDataList : $("#tDataList").val(),
				s1 : $("#s1").val(),
				s2 : $("#s2").val(),
				s3 : $("#s3").val(),
				af1 : $("#af1").text(),
				af2 : $("#af2").text(),
				af3 : $("#af3").text(),
				cTotalAmt : $("#cTotalAmt").text(),
				cTotalAmt2 : $("#cTotalAmt2").text(),
				cTotalAmt3 : $("#cTotalAmt3").text(),
				loAirFare1 : $("#loAirFare1").text(),
				loAirFareEx1 : $("#loAirFareEx1").text(),
				loAirFare2 : $("#loAirFare2").val(),
				loAirFareEx2 : $("#loAirFareEx2").text(),
				loAirFare3 : $("#loAirFare3").text(),
				loTranFare1 : $("#loTranFare1").text(),
				loTranFareEx1 : $("#loTranFareEx1").text(),
				loTranFare2 : $("#loTranFare2").val(),
				loTranFareEx2 : $("#loTranFareEx2").text(),
				loTranFare3 : $("#loTranFare3 ").text(),
				visaFeeFare1 : $("#visaFeeFare1").text(),
				visaFeeFareEx1 : $("#visaFeeFareEx1").text(),
				visaFeeFare2 : $("#visaFeeFare2").val(),
				visaFeeFareEx2 : $("#visaFeeFareEx2").text(),
				visaFeeFare3 : $("#visaFeeFare3").text(),
				ovCharFare1 : $("#ovCharFare1").text(),
				ovCharFareEx1 : $("#ovCharFareEx1").text(),
				ovCharFare2 : $("#ovCharFare2").val(),
				ovCharFareEx2 : $("#ovCharFareEx2").text(),
				ovCharFare3 : $("#ovCharFare3").text(),
				vocLeeFare1 : $("#vocLeeFare1").text(),
				vocLeeFareEx1 : $("#vocLeeFareEx1").text(),
				vocLeeFare2 : $("#vocLeeFare2").val(),
				vocLeeFareEx2 : $("#vocLeeFareEx2").text(),
				vocLeeFare3 : $("#vocLeeFare3").text(),
				hostFare1 : $("#hostFare1").text(),
				hostFareEx1 : $("#hostFareEx1").text(),
				hostFare2 : $("#hostFare2").val(),
				hostFareEx2 : $("#hostFareEx2").text(),
				hostFare3 : $("#hostFare3").text(),
				execDtl : $("#execDtl").val(),
				rem : $("#rem").val(),
				jsGubun : $("#jsGubun").val(),
				etcTotal1 : $("#etcTotal1").text(),
				etcTotalEx1 : $("#etcTotalEx1").text(),
				etcTotal2 : $("#etcTotal2").text(),
				etcTotalEx2 : $("#etcTotalEx2").text(),
				etcTotal3 : $("#etcTotal3").text()
		};


		gf_PostOpen("/trip/outerTrip/adjustPrint.jsp", null,
				"toolbar=no,scrollbars=yes,width=800,height=700", params,
				true, true, null);


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

			// 반려 코멘트 처리
			var ipDatas = JSON.parse(ds_SavedDoc.get(0, "ifParam"));

			ipDatas.rem = rem;

			var params = {
					docNo : docNo,
					perchrgRvwOpn2 : perchrgRvwOpn2,
					ifParam : JSON.stringify(ipDatas)
			};


			gf_Transaction("SAVE_GHR_COMMENT", "/trip/outerTrip/updateAdjustGHRCmt.xpl", params, {}, "f_Callback", true);

		}

	});

	$("#createPdf").click(function(){

		// 수동으로 증빙 pdf 신청
		gf_InitSpinner(top.$('body'));

		if(v_AdjustSignId == undefined || v_AdjustSignId == ""){
			alert("결재문서 번호가 존재하지 않습니다.");
			return;
		}

		if(v_RptRefNo == undefined || v_RptRefNo == ""){
			alert("REF NO 가 존재하지 않습니다.");
			return;
		}

		if(v_DocNo == undefined || v_DocNo == ""){
			alert("문서 번호가 존재하지 않습니다.");
			return;
		}

		var params = {
				signId : v_AdjustSignId,
				refNo : v_RptRefNo,
				docNo : v_DocNo
		};

		gf_Transaction("SAVE_CREATE_PDF_DOC", "/trip/outerTrip/createPdfDoc2.xpl", params, {}, "f_Callback", true);


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
		$("#rejectSap").show();
		$("#docDelBtn").show();
	}

	//반려 D04, SEC반려 D54, GHR반려 D64, 경영관리 D84

	if(v_AdjustDocSts == "D04"){
		// 반려 문서 재작성 버튼 팝업
		$("#adjustReDraft").show();

	}

	// 본부장 이상 직책 삽입
	ds_HqRpswrkCd.setAllData(gv_hqRpswrkCd);

	// 팀장 이상 직책 삽입
	ds_ChiefCd.setAllData(gv_ChiefCd);

	// 수정불가 처리
	$("input").attr("readonly", "readonly");
	$("textarea").attr("readonly", "readonly");
	$("select").attr("disabled", "disabled");

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

	// 관리자 Only
	if(gv_AuthList.auth[0].code.match("RO_CMAS_CO_TRIP_ADM") != null){
		$("#createPdf").show();

		//결재중인데,SIGN 값이 없다면 삭제가 필요함.
		if(v_AdjustDocSts == "D02" && v_AdjustSignId ==""){
			$("#rejectSap").show();
			$("#docDelBtn").show();
		}
	}else{
		// 관리자가 아닌 경우
	}

	var params = {
			docNo : v_DocNo,
			refNo : v_RptRefNo
	};

	gf_Transaction("SELECT_OUTER_TRIP_VIEW_DOC", "/trip/outerTrip/retrieveOuterTripViewDoc.xpl", params, {}, "f_Callback", true);

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

	  		ds_sapData.setAllData(resultData.ds_sapData);

	  		ds_OuterTripDoc.setAllData(resultData.ds_OuterTripDoc);
			ds_ExtnlPer.setAllData(resultData.ds_ExtnlPer);
			ds_Expn.setAllData(resultData.ds_Expn);
			ds_Visa.setAllData(resultData.ds_Visa);

			if(ds_OuterTripDoc.get(0, "ifParam") != undefined) v_IfParam =  JSON.parse(ds_OuterTripDoc.get(0, "ifParam"));

//			$("#refTr").show();
  			$("#refDay").text(ds_OuterTripDoc.get(0, "fstRegDt").substr(0, 10));

			var savedYmd = ds_OuterTripDoc.get(0, "fstRegDt").substr(0, 10);

	  		var ymdParams = {
	  			Ymd : $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", savedYmd))
	  		};

			gf_Transaction("SELECT_NAT_LIST", "/trip/outerTrip/retrieveNatList.xpl", {}, {}, "f_Callback", true);
			gf_Transaction("SELECT_REF_LIST", "/trip/outerTrip/retrieveTripRef.xpl", {}, {}, "f_Callback", true);
			gf_Transaction("SELECT_EXRATE", "/trip/eai/getSendExrate.xpl", ymdParams, {}, "f_Callback", true);

//			cf_setAdjustDocInfo();

			//20150919 저장된 결재선을 불러온다.
			if(v_AdjustDocSts == "D03"){
//				alert("결재중인 문서");
				cf_RetrieveSignInfo2();
			}else if(v_AdjustDocSts == "D02"){
				cf_RetrieveSignInfo2();
			}else if(v_AdjustDocSts == "D04"){
				cf_RetrieveSignInfo2();
			}else{
				cf_RetrieveSignInfo();
			}

//			cf_retrieveSignLine();

			break;
	  	case "SELECT_NAT_LIST" :
	  		ds_NatList.setAllData(resultData.ds_NatList);

	  		// 문서 정보 셋팅
	  		cf_setAdjustDocInfo();

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

	  		$("#loAirFare1").text(gf_AmtFormat(ds_OuterTripDoc.get(0, "lclAfare")));
	  		$("#loTranFare1").text(gf_AmtFormat(ds_OuterTripDoc.get(0, "lclTrafficExpn")));
	  		$("#visaFeeFare1").text(gf_AmtFormat(ds_OuterTripDoc.get(0, "visaFee")));
	  		$("#ovCharFare1").text(gf_AmtFormat(ds_OuterTripDoc.get(0, "excsExpn")));
	  		$("#vocLeeFare1").text(gf_AmtFormat(ds_OuterTripDoc.get(0, "wef")));
	  		$("#hostFare1").text(gf_AmtFormat(ds_OuterTripDoc.get(0, "svcExpn")));

	  		// 환율 게산 데이터
	  		$("#loAirFareEx1").text(gf_AmtFormat(Math.round(parseFloat(removeComma(ds_OuterTripDoc.get(0, "lclAfare"))) * parseFloat(v_USD))));
	  		$("#loTranFareEx1").text(gf_AmtFormat(Math.round(parseFloat(removeComma(ds_OuterTripDoc.get(0, "lclTrafficExpn"))) * parseFloat(v_USD))));
	  		$("#visaFeeFareEx1").text(gf_AmtFormat(Math.round(parseFloat(removeComma(ds_OuterTripDoc.get(0, "visaFee"))) * parseFloat(v_USD))));
	  		$("#ovCharFareEx1").text(gf_AmtFormat(Math.round(parseFloat(removeComma(ds_OuterTripDoc.get(0, "excsExpn"))) * parseFloat(v_USD))));
	  		$("#vocLeeFareEx1").text(gf_AmtFormat(Math.round(parseFloat(removeComma(ds_OuterTripDoc.get(0, "wef"))) * parseFloat(v_USD))));
	  		$("#hostFareEx1").text(gf_AmtFormat(Math.round(parseFloat(removeComma(ds_OuterTripDoc.get(0, "svcExpn"))) * parseFloat(v_USD))));

	  		var etcTotal1 = parseFloat(removeComma($("#loAirFare1").text())) +
	  		parseFloat(removeComma($("#loTranFare1").text())) +
	  		parseFloat(removeComma($("#visaFeeFare1").text())) +
	  		parseFloat(removeComma($("#ovCharFare1").text())) +
	  		parseFloat(removeComma($("#vocLeeFare1").text())) +
	  		parseFloat(removeComma($("#hostFare1").text()));

	  		var etcTotalEx1 = parseFloat(removeComma($("#loAirFareEx1").text())) +
	  		parseFloat(removeComma($("#loTranFareEx1").text())) +
	  		parseFloat(removeComma($("#visaFeeFareEx1").text())) +
	  		parseFloat(removeComma($("#ovCharFareEx1").text())) +
	  		parseFloat(removeComma($("#vocLeeFareEx1").text())) +
	  		parseFloat(removeComma($("#hostFareEx1").text()));

	  		$("#etcTotal1").text(parseFloat(etcTotal1.toFixed(4)));
	  		$("#etcTotalEx1").text(gf_AmtFormat(etcTotalEx1));

	  		hostCheck = parseInt(f_notSpace(removeComma(ds_OuterTripDoc.get(0, "wef")))) + parseInt(f_notSpace(removeComma(ds_OuterTripDoc.get(0, "svcExpn"))));

//	  		f_calcAirAmount();

	  		break;
	  	case "SELECT_SAVED_ADJUST_DOC" :
	  		ds_SavedDoc.setAllData(resultData.ds_SavedDoc);
	  		ds_aSapData.setAllData(resultData.ds_aSapData);

	  		cf_setSavedDocInfo();
	  		break;
	  	case "SELECT_SIGN_LINE" :
//	  		ds_Signln.setAlldata(resultData.ds_Signln);

	  		cf_setSignlnInfo(resultData);
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
  				if(v_AdjustDocSts == "D04"){
  					if(ds_Signln.get(i, "signDt") != "") lastSignDt = i;
  				}
			}

	  		// 반려 표시 처리
	  		if(v_AdjustDocSts == "D04"){
	  			if(lastSignDt != -1){
	  				ds_Signln.set(lastSignDt, "signStsCd", "S05");
	  			}
			}

	  		gf_AssembleSignln(ds_Signln);

	  		// 결재선 표시
	  		if(ds_Signln.size() > 0){
	  			$("#signId").text(ds_Signln.get(0).signId);

	  			if(v_AdjustDocSts == "D04"){
	  				// 반려 사유
	  				$("#rejContTr").show();
	  				if(ds_Signln.get(0).signId == "" || ds_Signln.get(0).signId == undefined){
	  					alert("결재문서 번호가 존재하지 않습니다.");
	  				}else{
	  					var params = {
	  						signId : ds_Signln.get(0).signId
	  					};
	  					gf_Transaction("SELECT_REJ_CONT", "/trip/outerTrip/retrieveRejCont.xpl", params, {}, "f_Callback", true);
	  				}

	  			}

	  		}

	  		break;
	  	case "SELECT_AIRFARE_INVOICE" :
	  		v_SapData = resultData.output1[0];

	  		if(v_SapData.SapItab == undefined){
	  			$("#af1").text("0");
		  		$("#af2").text("0");
		  		$("#af3").text("0");
	  		}else{

	  			var user;

		  		// 결과가 하나일 경우 오브젝트로 전달
		  		if(v_SapData.SapItab.constructor == Object){
		  			user = new Array();
		  			user[0] = v_SapData.SapItab;
		  		}else if(v_SapData.SapItab.constructor == Array){
		  			user = v_SapData.SapItab;
		  		}

		  		var Initfare = 0;
		  		var Confirmamt = 0;

		  		// 출장자 수만큼 데이터를 출력한다.
		  		for(var i = 0; i < user.length; i++){

		  			var tempIF = parseFloat(user[i].Initfare) * 100;
		  			Initfare = Initfare + parseInt(tempIF.toFixed(0));

		  			var tempCF = parseFloat(user[i].Confirmamt) * 100;
		  			Confirmamt = Confirmamt + parseInt(tempCF.toFixed(0));
		  		}

		  		var af3 = Confirmamt - Initfare;

		  		$("#af1").text(gf_AmtFormat(Initfare));
		  		$("#af2").text(gf_AmtFormat(Confirmamt));
		  		$("#af3").text(gf_AmtFormat(af3));

	  		}

	  		var ifParam = ds_SavedDoc.get(0, "ifParam");
	  		var data;
	  		if(ifParam != "" || ifParam != undefined){
	  			data = JSON.parse(ifParam);
	  		}

	  		// 20151001 기준액 저장
	  		cf_AppendSavedExpn(ds_Expn.getAllData(), data);


	  		if(!(data.fileAtchId == "undefined" || data.fileAtchId == undefined)){

	  			v_FileAtchId = data.fileAtchId;

	  			// 첨부파일이 존재하는 경우
	  			gf_retrieveFileList(data.fileAtchId);
	  		}

	  		$("#jsGubun").val(data.jsGubun);

	  		if($("#jsGubun").val() == "R"){
	  			// 반납 확인중 문서 필들 팝업
	  			$("#culkum").show();
	  			$("#retCost").show();

	  			var pUserTxt = data.payRutUserName + " (" + data.payRutUserId + ")";

	  			$("#payRutUser").text(pUserTxt);
	  			$("#payRutDate").text(data.payRutDate);

//	  			<span id="payRutUser">
//	  			payRutDate payRutUserId: "1203220"
//	  			payRutUserName: "이금재"
	  		}else if($("#jsGubun").val() == "N" || $("#jsGubun").val() == "A"){
	  			// 반납 확인중 문서 필들 팝업
	  			$("#culkum").show();
	  			$("#retCost").show();

	  			var pUserTxt = data.payRutUserName + " (" + data.payRutUserId + ")";

	  			$("#payRutUser").text(pUserTxt);
	  			$("#payRutDate").text(data.payRutDate);
	  		}

//	  		$("#tripNat").val(cf_GetNatNm(v_TripNat));

	  		var tripNat = data.tripNat;
	  		if(ds_NatList.find("natCd", data.tripNat) != -1){
	  			tripNat = cf_GetNatNm(data.tripNat);
	  		}

	  		$("#tripNat").val(tripNat);

	  		// 증빙일자
	  		$("#oDate").text(data.oDate);

	  		$("#rTripDateStart").val(data.rTripDateStart);
	  		$("#rTripDateEnd").val(data.rTripDateEnd);

	  		if(data.rTripDateStart != undefined && data.rTripDateEnd != undefined){
	  			var startDate = $.datepicker.parseDate("yy-mm-dd" , data.rTripDateStart);
	  			var endDate = $.datepicker.parseDate("yy-mm-dd" , data.rTripDateEnd);

	  			var rTripDate = (endDate - startDate)/1000/60/60/24;

	  			$("#rTripDate").text(rTripDate+1);
	  		}

	  		$("#payDate").val(data.payDate);

	  		for(var i = 0; i < data.tReq.length; i++){
				data.tReq = data.tReq.replace("##", "\n");
			}
	  		$("#tReq").val(data.tReq);

	  		$("#tPurp").val(data.tPurp);

	  		for(var i = 0; i < data.tPlanDtl.length; i++){
				data.tPlanDtl = data.tPlanDtl.replace("##", "\n");
			}
	  		$("#tPlanDtl").val(data.tPlanDtl);

	  		for(var i = 0; i < data.tRptDtl.length; i++){
				data.tRptDtl = data.tRptDtl.replace("##", "\n");
			}
	  		$("#tRptDtl").val(data.tRptDtl);
	  		$("#tDataList").val(data.tDataList);
	  		$("#s1").val(data.s1);
	  		$("#s2").val(data.s2);
	  		$("#s3").val(data.s3);
	  		$("#culkum").val(data.Culkum);
	  		$("#loAirFare2").val(data.loAirFare2);
	  		$("#loTranFare2").val(data.loTranFare2);
	  		$("#visaFeeFare2").val(data.visaFeeFare2);
	  		$("#ovCharFare2").val(data.ovCharFare2);
	  		$("#vocLeeFare2").val(data.vocLeeFare2);
	  		$("#hostFare2").val(data.hostFare2);

	  		for(var i = 0; i < data.execDtl.length; i++){
				data.execDtl = data.execDtl.replace("##", "\n");
			}
	  		$("#execDtl").val(data.execDtl);
	  		$("#usNightDay2").val(data.usNightDay2);
	  		$("#usDayDay2").val(data.usDayDay2);
	  		$("#eduDay2").val(data.eduDay2);
	  		$("#spotDay2").val(data.spotDay2);
	  		$("#euNightDay2").val(data.euNightDay2);
	  		$("#euDayDay2").val(data.euDayDay2);
	  		$("#enNightDay2").val(data.enNightDay2);
	  		$("#enDayDay2").val(data.enDayDay2);
	  		$("#jaNightDay2").val(data.jaNightDay2);
	  		$("#jaDayDay2").val(data.jaDayDay2);

	  		for(var i = 0; i < data.rem.length; i++){
				data.rem = data.rem.replace("##", "\n");
			}
	  		$("#rem").val(data.rem);

	  		for(var i = 0; i < data.assDtl.length; i++){
	  			data.assDtl = data.assDtl.replace("##", "\n");
			}

	  		$("#assDtl").val(data.assDtl);

	  		f_calcEtcAmt(data.loAirFare2, "loAirFare1", "loAirFareEx2", "loAirFare3");
	  		f_calcEtcAmt(data.loTranFare2, "loTranFare1", "loTranFareEx2", "loTranFare3");
	  		f_calcEtcAmt(data.visaFeeFare2, "visaFeeFare1", "visaFeeFareEx2", "visaFeeFare3");
	  		f_calcEtcAmt(data.ovCharFare2, "ovCharFare1", "ovCharFareEx2", "ovCharFare3");
	  		f_calcEtcAmt(data.vocLeeFare2, "vocLeeFare1", "vocLeeFareEx2", "vocLeeFare3");
	  		f_calcEtcAmt(data.hostFare2, "hostFare1", "hostFareEx2", "hostFare3");
	  		f_calcExpnAmt(data.usNightDay2, "usNightRef", "usNightDayAmt2", "usNightDayAmt", "usNight3");
	  		f_calcExpnAmt(data.usDayDay2, "usDayRef", "usDayDayAmt2", "usDayDayAmt", "usDay3");
	  		f_calcExpnAmt(data.eduDay2, "eduRef", "eduDayAmt2", "eduDayAmt", "edu3");
	  		f_calcExpnAmt(data.spotDay2, "spotRef", "spotDayAmt2", "spotDayAmt", "spot3");
	  		f_calcExpnAmt(data.euNightDay2, "euNightRef", "euNightDayAmt2", "euNightDayAmt", "euNight3");
	  		f_calcExpnAmt(data.euDayDay2, "euDayRef", "euDayDayAmt2", "euDayDayAmt", "euDay3");
	  		f_calcExpnAmt(data.enNightDay2, "enNightRef", "enNightDayAmt2", "enNightDayAmt", "enNight3");
	  		f_calcExpnAmt(data.enDayDay2, "enDayRef", "enDayDayAmt2", "enDayDayAmt", "enDay3");
	  		f_calcExpnAmt(data.jaNightDay2, "jaNightRef", "jaNightDayAmt2", "jaNightDayAmt", "jaNight3");
	  		f_calcExpnAmt(data.jaDayDay2, "jaDayRef", "jaDayDayAmt2", "jaDayDayAmt", "jaDay3");

	  		//ghrComment
	  		$("#ghrComment").val(ds_SavedDoc.get(0, "perchrgRvwOpn2"));

	  		hostCheck2 = parseInt(f_notSpace(removeComma($("#vocLeeFare2").val()))) + parseInt(f_notSpace(removeComma($("#hostFare2").val())));

	  		//refNo, gitaNo, 전표번호

	  		// 일반의 경우 refNo 가 없어서 SAP 데이터가 없다.
	  		if(ds_aSapData.get(0, "SapItab") != undefined){
	  			var refNo = ds_aSapData.get(0, "SapItab").Refkey;
	  			if(refNo != "" && refNo != undefined){
	  				$("#refNo").text(refNo);
	  			}

//	  			var gitaNo = ds_aSapData.get(0, "SapItab").Refkey2;
//	  			if(gitaNo != "" && gitaNo != undefined){
//	  				$("#gitaNo").text(gitaNo);
//	  			}

	  			// 전표번호
	  			if(v_AdjustDocSts == "D03"){
	  				//전표번호
	  				$("#tOrdNoTr").show();
	  				$("#tOrdNo").text(ds_aSapData.get(0, "SapItab").Belnr);
	  			}
	  		}

	  		break;
	  	case "SELECT_REJ_CONT" :
	  		ds_RejCont.setAllData(resultData.output1);

	  		// 반려사유 표시
	  		// 마지막 결재인을 구함

	  		if(v_AdjustDocSts == "D04"){
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

	  		break;
	  	case "SAVE_GHR_COMMENT" :
	  		break;

	  	case "SAVE_CONFIRM_SAP" :
	  		if(resultData.ds_Result[0].ErrMsg != undefined) alert(resultData.ds_Result[0].ErrMsg);
	  		$(".ajax_overlay").remove();
	  		break;

	  	case "SAVE_CREATE_PDF_DOC" :
	  		$(".ajax_overlay").remove();
	  		break;

	  	default :
	  		break;
	  }
}

function cf_setAdjustDocInfo(){
	// 문서번호
	$("#docNo").text(v_DocNo);

	// 작성자
	var drafter = v_IfParam.drafterUserId  + " " + v_IfParam.drafterUserKnm;
	$("#drafter").text(drafter);
	// 소속팀
	$("#drafterOrgNm").text(v_IfParam.drafterUserOrgNm + " (" + v_IfParam.drafterUserOrgCd + ")");
	//출장자
	// IT운영팀(1DFUR) 강지인
	// 직책(직급) 120223 강지인 전화번호 (출장인원: 1명)
	var tripUserInfo = v_IfParam.tripUserOrgNm + " (" + v_IfParam.tripUserOrgCd + ") " + v_IfParam.tripUserPositCd + " " +  v_IfParam.tripUserId  + " " + v_IfParam.tripUserKnm;
	$("#tripUserInfo").text(tripUserInfo);

	$("#tripUserTotal").text(ds_ExtnlPer.size()+1);

	// 출장국가
	var tripNat = "";
	for(var i = 0; i < ds_Visa.size(); i++){
		if(ds_Visa.get(i, "visaOwnYn") != ""){
			tripNat = cf_GetNatNm(ds_Visa.get(i, "natCd")) + " ";
		}
	}
	$("#tripNat").val(tripNat);

	// 출장기간
	// [최    초] 2014.04.14 ~ 2014.04.15 (기간 : 2 일)
	var ftTripDate = parseInt(v_IfParam.tripDate)+1;
	var fTripDate = $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", v_IfParam.startDate)) + " ~ " + $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", v_IfParam.endDate)) + " (기간 : " + ftTripDate + "일)";
	$("#fTripDate").text(fTripDate);

	// [실사용]  ... ~  ... (기간 :   일)   지불/반납예정일 :  ...
	$("#rTripDateStart").val($.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", v_IfParam.startDate)));
	$("#rTripDateEnd").val($.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", v_IfParam.endDate)));
//	rTripDate
	var startDate = $.datepicker.parseDate("yymmdd" , v_IfParam.startDate);
	var endDate = $.datepicker.parseDate("yymmdd" , v_IfParam.endDate);

	var rTripDate = (endDate - startDate)/1000/60/60/24;
	$("#rTripDate").text(rTripDate+1);

//	payDate

	// 요청사항

	//출장목적
	$("#tPurp").val(ds_OuterTripDoc.get(0, "bustrGl"));
	// 경비구분
	// 일반경비  업무구분:연수   경비이체코드:1DFUR
	var bdgtType = v_IfParam.bdgtType;
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

	var cGubun = v_IfParam.cGugun;

	switch(cGubun) {
	case "c01" :
		$("#cGubun").text("견적/Site");
		break;
	case "c02" :
		$("#cGubun").text("공사");
		break;
	case "c03" :
		$("#cGubun").text("기술연구");
		break;
	case "c04" :
		$("#cGubun").text("기타");
		break;
	case "c05" :
		$("#cGubun").text("사후관리");
		break;
	case "c06" :
		$("#cGubun").text("설계/엔지니어링");
		break;
	case "c07" :
		$("#cGubun").text("수주/영업");
		break;
	case "c08" :
		$("#cGubun").text("연수");
		break;
	case "c09" :
		$("#cGubun").text("일반관리");
		break;
	case "c10" :
		$("#cGubun").text("전략/기획");
		break;
	case "c11" :
		$("#cGubun").text("홍보");
		break;
	case "c12" :
		$("#cGubun").text("F/S");
		break;
	default :
		break;
	}

	// 예산번호
	// 예산번호 / 내역 : 내역
	var aufnr = v_IfParam.aufnr + " / 내역 : " + v_IfParam.kText;
	$("#aufnr").text(aufnr);

	// 집행팀
	// 집행팀이름(집행팀코드) / 집행팀장 : 사번 이름
	var kostv = v_IfParam.kostVNm + " (" + v_IfParam.kostV + ")" + " / 집행팀장 : " + v_IfParam.chief;
	$("#kostv").text(kostv);

	// 작성자 직급 사번 이름 전화번호
	var drafterInfo = v_IfParam.drafterUserOrgNm + " (" + v_IfParam.drafterUserOrgCd + ") " + v_IfParam.drafterUserPositCd + " " + v_IfParam.drafterUserKnm + " (" + v_IfParam.drafterUserId + ")";
	$("#drafterInfo").text(drafterInfo);
	// 송금계좌 /
	$("#accRetInfo").text(v_IfParam.accountNo);

	// 새로 작성하는 문서가 아니라면 저장내역을 불러온다.
	if(v_AdjustDocSts != ""){
		cf_setSavedAdjustDoc();
	}

	// 기타경비
	//체재비
	// 기준액
	// 최초지급분 셋팅

}

//국가리스트에서 나라이름으로 나라코드를 구한다.
function cf_GetNatNm(natCd){
	return ds_NatList.get(ds_NatList.find("natCd", natCd), "natNm");
}

function checkRefVal(positNm, cls){
	for(var i = 0; i < ds_RefList.size(); i++){
		if(ds_RefList.get(i, "positNm") == positNm && ds_RefList.get(i, "cls") == cls){
			return ds_RefList.get(i, "val");
		};
	}
	return -1;
}

function cf_AppendSavedExpn(obj, data){

	var result = obj;

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
		var id = result[i].expnCls;
		if(id == "usNight"){
			$("#" + id + "Ref").text(result[i].ddExpnDcnt);
		}else if(id == "usDay"){
			$("#" + id + "Ref").val(result[i].ddExpnDcnt);

		}else if(id == "edu"){
			$("#" + id + "Ref").val(result[i].ddExpnDcnt);

		}else if(id == "spot"){
			$("#" + id + "Ref").val(result[i].ddExpnDcnt);

		}else if(id == "euNight"){
			$("#" + id + "Ref").text(result[i].ddExpnDcnt);
		}else if(id == "euDay"){
			$("#" + id + "Ref").val(result[i].ddExpnDcnt);

		}else if(id == "enNight"){
			$("#" + id + "Ref").text(result[i].ddExpnDcnt);
		}else if(id == "enDay"){
			$("#" + id + "Ref").val(result[i].ddExpnDcnt);

		}else if(id == "jaNight"){
			$("#" + id + "Ref").text(result[i].ddExpnDcnt);
		}else if(id == "jaDay"){
			$("#" + id + "Ref").val(result[i].ddExpnDcnt);

		}

	}


	if($("#" + "usNight" + "Ref").text() == ""){
		$("#" + "usNight" + "Ref").text(checkRefVal(tripUserPositCd, "일반숙박"));
	}

	if($("#" + "usDay" + "Ref").val() == ""){
		$("#" + "usDay" + "Ref").val(checkRefVal(tripUserPositCd, "일반일당"));
	}

	if($("#" + "edu" + "Ref").val() == ""){
		$("#" + "edu" + "Ref").val(checkRefVal(tripUserPositCd, "연수경비(숙)"));
	}

	if($("#" + "spot" + "Ref").val() == ""){
		$("#" + "spot" + "Ref").val(checkRefVal(tripUserPositCd, "연수경비(숙식)"));
	}

	if($("#" + "euNight" + "Ref").text() == ""){
		$("#" + "euNight" + "Ref").text(checkRefVal(tripUserPositCd, "유럽숙박"));
	}

	if($("#" + "euDay" + "Ref").val() == ""){
		$("#" + "euDay" + "Ref").val(checkRefVal(tripUserPositCd, "유럽일당"));
	}

	if($("#" + "enNight" + "Ref").text() == ""){
		$("#" + "enNight" + "Ref").text(checkRefVal(tripUserPositCd, "영국숙박"));
	}

	if($("#" + "enDay" + "Ref").val() == ""){
		$("#" + "enDay" + "Ref").val(checkRefVal(tripUserPositCd, "영국일당"));
	}

	if($("#" + "jaNight" + "Ref").text() == ""){
		$("#" + "jaNight" + "Ref").text(checkRefVal(tripUserPositCd, "일본숙박"));
	}

	if($("#" + "jaDay" + "Ref").val() == ""){
		$("#" + "jaDay" + "Ref").val(checkRefVal(tripUserPositCd, "일본일당"));
	}

	// GHR 수정 여부
	if(data.usDayRef == "" || data.usDayRef == undefined){
	}else{
		$("#" + "usDay" + "Ref").val(data.usDayRef);
	}
	// GHR 수정 필드
	if(data.eduRef == "" || data.eduRef == undefined){
	}else{
		$("#" + "edu" + "Ref").val(data.eduRef);
	}
	// GHR 수정 필드
	if(data.spotRef == "" || data.spotRef == undefined){
	}else{
		$("#" + "spot" + "Ref").val(data.spotRef);
	}

	// GHR 수정 필드
	if(data.euDayRef == "" || data.euDayRef == undefined){
	}else{
		$("#" + "euDay" + "Ref").val(data.euDayRef);
	}

	// GHR 수정 필드
	if(data.enDayRef == "" || data.enDayRef == undefined){
	}else{
		$("#" + "enDay" + "Ref").val(data.enDayRef);
	}

	// GHR 수정 필드
	if(data.jaDayRef == "" || data.jaDayRef == undefined){
	}else{
		$("#" + "jaDay" + "Ref").val(data.jaDayRef);
	}


	// 계산
	for(var i = 0; i < result.length; i++){
		var idTr = result[i].expnCls;

		if(result[i].expnCls == "usNight"){
			var day = parseInt(result[i].accomoExpnDcnt);
			var dayAmt = parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt);

			$("#" + idTr + "Day").text(day);
			$("#" + idTr + "DayAmt").text(dayAmt);

			if(v_AdjustDocSts == ""){
				$("#" + idTr + "Day2").val(day);
				f_calcExpnAmt(day, idTr+"Ref", idTr+"DayAmt2", idTr+"DayAmt", idTr+"3");
			}

			v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));
			v_cTotUSD = v_cTotUSD + (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt));


		}else if(result[i].expnCls == "usDay"){

			// GHR 수정 여부
//			if(data.usDayRef == "" || data.usDayRef == undefined){
//			}else{
//				result[i].ddExpnDcnt = data.usDayRef;
//			}

			var day = parseInt(result[i].accomoExpnDcnt);
			var dayAmt = parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt);

			$("#" + idTr + "Day").text(day);
			$("#" + idTr + "DayAmt").text(dayAmt);

			if(v_AdjustDocSts == ""){
				$("#" + idTr + "Day2").val(day);
				f_calcExpnAmt(day, idTr+"Ref", idTr+"DayAmt2", idTr+"DayAmt", idTr+"3");
			}

			v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));
			v_cTotUSD = v_cTotUSD + (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt));


		}else if(result[i].expnCls == "edu"){

			// GHR 수정 필드
//			if(data.eduRef == "" || data.eduRef == undefined){
//			}else{
//				result[i].ddExpnDcnt = data.eduRef;
//			}

			var day = parseInt(result[i].accomoExpnDcnt);
			var dayAmt = parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt);

			$("#" + idTr + "Day").text(day);
			$("#" + idTr + "DayAmt").text(dayAmt);

			if(v_AdjustDocSts == ""){
				$("#" + idTr + "Day2").val(day);
				f_calcExpnAmt(day, idTr+"Ref", idTr+"DayAmt2", idTr+"DayAmt", idTr+"3");
			}

			v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));
			v_cTotUSD = v_cTotUSD + (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt));


		}else if(result[i].expnCls == "spot"){

			// GHR 수정 필드
//			if(data.spotRef == "" || data.spotRef == undefined){
//			}else{
//				result[i].ddExpnDcnt = data.spotRef;
//			}


			var day = parseInt(result[i].accomoExpnDcnt);
			var dayAmt = parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt);

			$("#" + idTr + "Day").text(day);
			$("#" + idTr + "DayAmt").text(dayAmt);

			if(v_AdjustDocSts == ""){
				$("#" + idTr + "Day2").val(day);
				f_calcExpnAmt(day, idTr+"Ref", idTr+"DayAmt2", idTr+"DayAmt", idTr+"3");
			}

			v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));
			v_cTotUSD = v_cTotUSD + (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt));

		}else if(result[i].expnCls == "euNight"){

			var day = parseInt(result[i].accomoExpnDcnt);
			var dayAmt = parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt);

			$("#" + idTr + "Day").text(day);
			$("#" + idTr + "DayAmt").text(dayAmt);

			if(v_AdjustDocSts == ""){
				$("#" + idTr + "Day2").val(day);
				f_calcExpnAmt(day, idTr+"Ref", idTr+"DayAmt2", idTr+"DayAmt", idTr+"3");
			}

			v_cTotalAmt = v_cTotalAmt + (Math.round(v_EUR * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));
			v_cTotEUR = v_cTotEUR + (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt));


		}else if(result[i].expnCls == "euDay"){

			// GHR 수정 필드
//			if(data.euDayRef == "" || data.euDayRef == undefined){
//			}else{
//				result[i].ddExpnDcnt = data.euDayRef;
//			}

			var day = parseInt(result[i].accomoExpnDcnt);
			var dayAmt = parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt);

			$("#" + idTr + "Day").text(day);
			$("#" + idTr + "DayAmt").text(dayAmt);

			if(v_AdjustDocSts == ""){
				$("#" + idTr + "Day2").val(day);
				f_calcExpnAmt(day, idTr+"Ref", idTr+"DayAmt2", idTr+"DayAmt", idTr+"3");
			}

			v_cTotalAmt = v_cTotalAmt + (Math.round(v_EUR * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));
			v_cTotEUR = v_cTotEUR + (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt));

		}else if(result[i].expnCls == "enNight"){

			var day = parseInt(result[i].accomoExpnDcnt);
			var dayAmt = parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt);

			$("#" + idTr + "Day").text(day);
			$("#" + idTr + "DayAmt").text(dayAmt);

			if(v_AdjustDocSts == ""){
				$("#" + idTr + "Day2").val(day);
				f_calcExpnAmt(day, idTr+"Ref", idTr+"DayAmt2", idTr+"DayAmt", idTr+"3");
			}

			v_cTotalAmt = v_cTotalAmt + (Math.round(v_GBP * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));
			v_cTotGBP = v_cTotGBP + (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt));

		}else if(result[i].expnCls == "enDay"){

			// GHR 수정 필드
//			if(data.enDayRef == "" || data.enDayRef == undefined){
//			}else{
//				result[i].ddExpnDcnt = data.enDayRef;
//			}


			var day = parseInt(result[i].accomoExpnDcnt);
			var dayAmt = parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt);

			$("#" + idTr + "Day").text(day);
			$("#" + idTr + "DayAmt").text(dayAmt);

			if(v_AdjustDocSts == ""){
				$("#" + idTr + "Day2").val(day);
				f_calcExpnAmt(day, idTr+"Ref", idTr+"DayAmt2", idTr+"DayAmt", idTr+"3");
			}

			v_cTotalAmt = v_cTotalAmt + (Math.round(v_GBP * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));
			v_cTotGBP = v_cTotGBP + (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt));

		}else if(result[i].expnCls == "jaNight"){

			var day = parseInt(result[i].accomoExpnDcnt);
			var dayAmt = parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt);

			$("#" + idTr + "Day").text(day);
			$("#" + idTr + "DayAmt").text(dayAmt);

			if(v_AdjustDocSts == ""){
				$("#" + idTr + "Day2").val(day);
				f_calcExpnAmt(day, idTr+"Ref", idTr+"DayAmt2", idTr+"DayAmt", idTr+"3");
			}

			v_cTotalAmt = v_cTotalAmt + (Math.round(v_JPY * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));
			v_cTotJPY = v_cTotJPY + (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt));

		}else if(result[i].expnCls == "jaDay"){


			// GHR 수정 필드
//			if(data.jaDayRef == "" || data.jaDayRef == undefined){
//			}else{
//				result[i].ddExpnDcnt = data.jaDayRef;
//			}

			var day = parseInt(result[i].accomoExpnDcnt);
			var dayAmt = parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt);

			$("#" + idTr + "Day").text(day);
			$("#" + idTr + "DayAmt").text(dayAmt);

			if(v_AdjustDocSts == ""){
				$("#" + idTr + "Day2").val(day);
				f_calcExpnAmt(day, idTr+"Ref", idTr+"DayAmt2", idTr+"DayAmt", idTr+"3");
			}

			v_cTotalAmt = v_cTotalAmt + (Math.round(v_JPY * (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt))));
			v_cTotJPY = v_cTotJPY + (parseInt(result[i].ddExpnDcnt) * parseInt(result[i].accomoExpnDcnt));

		}

	}

	$("#usdTotal").text(v_cTotUSD);

	$("#euroTotal").text(v_cTotEUR);

	$("#gbpTotal").text(v_cTotGBP);

	$("#jaTotal").text(v_cTotJPY);


	// 동행인 + 출장자
	var ut = ds_ExtnlPer.size()+1;
	v_cTotalAmt = v_cTotalAmt*ut;
	$("#cTotalAmt").text(gf_AmtFormat(v_cTotalAmt));

//	$("#TotalAmt").text(gf_AmtFormat(v_cTotalAmt + parseInt(ds_OuterTripDoc.get(0, "afare"))));
	$("#TotalAmt").text(gf_AmtFormat(v_cTotalAmt + parseInt(removeComma($("#af1").text()))));



	// 개인지급 합계 (채재바 + 항공료)
//	v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;
//	$("#totalAmount").text(gf_AmtFormat(v_TotalAmount));

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

function removeComma(obj){
	if(obj == "") obj = 0;

	for(var i = 0; i < obj.length; i++){
		obj = obj.replace(",", "");
	}
	return obj;
}

function f_calcEtcAmt(amt, orgId, krId, diffId){
	$("#" + krId).text(gf_AmtFormat(Math.round(parseFloat(removeComma(amt)) * parseFloat(v_USD))));

	var diffAmt = parseFloat(removeComma(amt)) - parseFloat(removeComma($("#" + orgId).text()));

	$("#" + diffId).text(parseFloat(diffAmt.toFixed(4)));

	f_calcTotalEtcAmt();

}


function f_calcTotalEtcAmt(){

	var etcTotal2 = parseFloat(removeComma($("#loAirFare2").val())) +
		parseFloat(removeComma($("#loTranFare2").val())) +
		parseFloat(removeComma($("#visaFeeFare2").val())) +
		parseFloat(removeComma($("#ovCharFare2").val())) +
		parseFloat(removeComma($("#vocLeeFare2").val())) +
		parseFloat(removeComma($("#hostFare2").val()));

	var etcTotalEx2 = parseFloat(removeComma($("#loAirFareEx2").text())) +
		parseFloat(removeComma($("#loTranFareEx2").text())) +
		parseFloat(removeComma($("#visaFeeFareEx2").text())) +
		parseFloat(removeComma($("#ovCharFareEx2").text())) +
		parseFloat(removeComma($("#vocLeeFareEx2").text())) +
		parseFloat(removeComma($("#hostFareEx2").text()));

	var etcTotal3 = parseFloat(removeComma($("#loAirFare3").text())) +
		parseFloat(removeComma($("#loTranFare3").text())) +
		parseFloat(removeComma($("#visaFeeFare3").text())) +
		parseFloat(removeComma($("#ovCharFare3").text())) +
		parseFloat(removeComma($("#vocLeeFare3").text())) +
		parseFloat(removeComma($("#hostFare3").text()));

	v_GitaAmt = parseFloat(removeComma($("#loAirFareEx2").text())) +
	parseFloat(removeComma($("#loTranFareEx2").text())) +
	parseFloat(removeComma($("#visaFeeFareEx2").text())) +
	parseFloat(removeComma($("#ovCharFareEx2").text()));
	v_GitaAmt2 = removeComma($("#vocLeeFareEx2").text()); // 기타비용 (복리후생비)
	v_GitaAmt3 = removeComma($("#hostFareEx2").text()); // 기타비용 (접대비)

	$("#etcTotal2").text(parseFloat(etcTotal2.toFixed(4)));
	$("#etcTotalEx2").text(gf_AmtFormat(etcTotalEx2));
	$("#etcTotal3").text(parseFloat(etcTotal3.toFixed(4)));
}


function f_calcExpnAmt(day, refId, amtId, dayAmtId, diffId){

	if(day == "") day = 0;

	var ref = $("#" + refId).text();

	// input 같은 경우에는 text가 적혀있지 않으므로 val() 로 값을 다시 가져온다.
	if(ref == ""){
		ref = $("#" + refId).val();
	}

//	f_calcEtcAmt(e.currentTarget.value, "usNightRef", "usNightDayAmt2", "usNightDayAmt", "usNight3");

	$("#" + amtId).text(parseFloat(day) * parseFloat(ref));

	var diffAmt = parseFloat(removeComma($("#" + amtId).text())) - parseFloat(removeComma($("#" + dayAmtId).text()));

	$("#" + diffId).text(gf_AmtFormat(diffAmt));

	f_calcTotalExpnAmt();

}


function f_calcTotalExpnAmt(){

	// USD 계산
	var etcTotalDayAmtUSD = parseFloat(removeComma($("#usNightDayAmt2").text())) +
	parseFloat(removeComma($("#usDayDayAmt2").text())) +
	parseFloat(removeComma($("#eduDayAmt2").text())) +
	parseFloat(removeComma($("#spotDayAmt2").text()));

	// EUR 계산
	var etcTotalDayAmtEUR = parseFloat(removeComma($("#euNightDayAmt2").text())) +
	parseFloat(removeComma($("#euDayDayAmt2").text()));

	// GBP 계산
	var etcTotalDayAmtGBP = parseFloat(removeComma($("#enNightDayAmt2").text())) +
	parseFloat(removeComma($("#enDayDayAmt2").text()));

	// JPY 계산
	var etcTotalDayAmtJPY = parseFloat(removeComma($("#jaNightDayAmt2").text())) +
	parseFloat(removeComma($("#jaDayDayAmt2").text()));

//	var etcTotalDayAmt2 = Math.round(v_USD * etcTotalDayAmtUSD) + Math.round(v_EUR * etcTotalDayAmtEUR) + Math.round(v_GBP * etcTotalDayAmtGBP) + Math.round(v_JPY * etcTotalDayAmtJPY);


	// USD 계산
	var etcTotalDayAmtUSD2 = Math.round(v_USD * parseFloat(removeComma($("#usNightDayAmt2").text()))) +
	Math.round(v_USD * parseFloat(removeComma($("#usDayDayAmt2").text()))) +
	Math.round(v_USD * parseFloat(removeComma($("#eduDayAmt2").text()))) +
	Math.round(v_USD * parseFloat(removeComma($("#spotDayAmt2").text())));


	// EUR 계산
	var etcTotalDayAmtEUR2 = Math.round(v_EUR * parseFloat(removeComma($("#euNightDayAmt2").text()))) +
	Math.round(v_EUR * parseFloat(removeComma($("#euDayDayAmt2").text())));

	// GBP 계산
	var etcTotalDayAmtGBP2 = Math.round(v_GBP * parseFloat(removeComma($("#enNightDayAmt2").text()))) +
	Math.round(v_GBP * parseFloat(removeComma($("#enDayDayAmt2").text())));

	// JPY 계산
	var etcTotalDayAmtJPY2 = Math.round(v_JPY * parseFloat(removeComma($("#jaNightDayAmt2").text()))) +
	Math.round(v_JPY * parseFloat(removeComma($("#jaDayDayAmt2").text())));

	var etcTotalDayAmt2 = etcTotalDayAmtUSD2 + etcTotalDayAmtEUR2 + etcTotalDayAmtGBP2 + etcTotalDayAmtJPY2;


	$("#usdTotal2").text(gf_AmtFormat(etcTotalDayAmtUSD));
	var usdTotal3 = etcTotalDayAmtUSD - parseFloat(removeComma($("#usdTotal").text()));
	$("#usdTotal3").text(gf_AmtFormat(usdTotal3));

	$("#euroTotal2").text(gf_AmtFormat(etcTotalDayAmtEUR));
	var euroTotal3 = etcTotalDayAmtEUR - parseFloat(removeComma($("#euroTotal").text()));
	$("#euroTotal3").text(gf_AmtFormat(euroTotal3));

	$("#gbpTotal2").text(gf_AmtFormat(etcTotalDayAmtGBP));
	var gbpTotal3 = etcTotalDayAmtGBP - parseFloat(removeComma($("#gbpTotal").text()));
	$("#gbpTotal3").text(gf_AmtFormat(gbpTotal3));

	$("#jaTotal2").text(gf_AmtFormat(etcTotalDayAmtJPY));
	var jaTotal3 = etcTotalDayAmtJPY - parseFloat(removeComma($("#jaTotal").text()));
	$("#jaTotal3").text(gf_AmtFormat(jaTotal3));


	// 동행인 + 출장자
	var ut = ds_ExtnlPer.size()+1;
	etcTotalDayAmt2 = etcTotalDayAmt2*ut;

	v_cTotalAmt2 = etcTotalDayAmt2;
	$("#cTotalAmt2").text(gf_AmtFormat(gf_AmtFormat(etcTotalDayAmt2)));
	var cTotalAmt3 = etcTotalDayAmt2 - parseFloat(removeComma($("#cTotalAmt").text()));
	$("#cTotalAmt3").text(gf_AmtFormat(cTotalAmt3));




//	var TotalAmt2 = etcTotalDayAmt2 + parseInt(ds_OuterTripDoc.get(0, "afare"));
	var TotalAmt2 = etcTotalDayAmt2 + parseInt(removeComma($("#af2").text()));

	$("#TotalAmt2").text(gf_AmtFormat(TotalAmt2));
	var TotalAmt3 = TotalAmt2 - parseFloat(removeComma($("#TotalAmt").text()));
	$("#TotalAmt3").text(gf_AmtFormat(TotalAmt3));

}

function cf_settleOsBizTrip(){

	var IBdgtType = v_IfParam.bdgtType; //경비구분(P:사업, I:일반, C:특정)
	var IClearType = $("#jsGubun").val(); //정산구분(1:반납, 2:추가, 3:일반)
	if(IClearType == "A"){ // 추가
		IClearType = "2";
	}else if(IClearType == "R"){ // 반납
		IClearType = "1";
	}else if(IClearType == "N"){ // 일반
		IClearType = "3";
	}

	var IDocType = "H"; //전표구분(H:본사, G:지역)
	var INotesdoc = v_DocNo; // 문서번호
	var IRefkey = ds_OuterTripDoc.get(0, "refNo"); // 출장신청 전표번호
	var IGitaRefkey = ""; //가불신청번호
	var IBdgtNo = v_IfParam.aufnr; //예산번호
	var IBdgtTeam = v_IfParam.kostV; //집행팀
	var IPostTeam = ""; //경비이체코드
	var ISenderId = v_IfParam.drafterUserId; //작성자사번
	var ISendDate = v_IfParam.ordDate; //증빙일
	var ITravId = v_IfParam.tripUserId; //출장자사번
	var ITravTeam = v_IfParam.tripUserOrgCd; //출장자팀
	var ITravFdate = v_IfParam.startDate; //출장시작일
	var ITravTdate = v_IfParam.endDate; //출장종료일
	var ITravArea = $("#tripNat").val(); // 출장지역
	var ITravPurp = "(해출)"+$("#tPurp").val(); // 출장목적
	var IPaymDate = $("#payDate").val(); // 지불/반납예정일
	var IRetUsnam = ""; // 반납자
	var IRetDatum = ""; // 반납일
	var IRetMethod = ""; // 반납방법
	var IRetBank = ""; // 반납은행
	if(IClearType == "1"){
		// 만약 정산 구분이 반납이라면 값을 셋팅하여 넘긴다.
		IRetUsnam = v_IfParam.tripUserId; //
		IRetDatum = $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", $("#payRutDate").val())); // 반납일
		IRetMethod = "은행"; // 반납방법
		IRetBank = $("#culkum").val(); // 반납은행
	}

	var IAirFare = ds_OuterTripDoc.get(0, "afare"); // 신청 항공료
	var IDayAmt = v_cTotalAmt2; // 신청 체제비
	var IPosid = ""; // 작업분석요소 (WBS요소)
	var IBukrs = "1000"; // 회사코드
	var IGitaAmt = v_GitaAmt; // 기타비용(현지항공료+현지교통비+VISA FEE+Over charge)
	var IGitaAmt2 = v_GitaAmt2; // 기타비용 (복리후생비)
	var IGitaAmt3 = v_GitaAmt3; // 기타비용 (접대비)

	var params = {
			IBdgtType : IBdgtType,
			IClearType : IClearType,
			IDocType : IDocType,
			INotesdoc : INotesdoc,
			IRefkey : IRefkey,
			IGitaRefkey : IGitaRefkey,
			IBdgtNo : IBdgtNo,
			IBdgtTeam : IBdgtTeam,
			IPostTeam : IPostTeam,
			ISenderId : ISenderId,
			ISendDate : ISendDate,
			ITravId : ITravId,
			ITravTeam : ITravTeam,
			ITravFdate : ITravFdate,
			ITravTdate : ITravTdate,
			ITravArea : ITravArea,
			ITravPurp : ITravPurp,
			IPaymDate : IPaymDate,
			IRetUsnam : IRetUsnam,
			IRetDatum : IRetDatum,
			IRetMethod : IRetMethod,
			IRetBank : IRetBank,
			IAirFare : IAirFare,
			IDayAmt : IDayAmt,
			IPosid : IPosid,
			IBukrs : IBukrs,
			IGitaAmt : IGitaAmt,
			IGitaAmt2 : IGitaAmt2,
			IGitaAmt3 : IGitaAmt3
	};

	gf_Transaction("SELECT_SETTLE_OS_BIZ_TIP", "/trip/eai/settleOsBizTrip.xpl", params, {}, "f_Callback", true);


}

function cf_adjustDocSave(){


	var data = {
			jsGubun : $("#jsGubun").val(),
			tripNat : $("#tripNat").val(),
			rTripDateStart : $("#rTripDateStart").val(),
			rTripDateEnd : $("#rTripDateEnd").val(),
			payDate : $("#payDate").val(),
			tReq : $("#tReq").val(),
			tPurp : $("#tPurp").val(),
			tPlanDtl : $("#tPlanDtl").val(),
			tRptDtl : $("#tRptDtl").val(),
			tDataList : $("#tDataList").val(),
			s1 : $("#s1").val(),
			s2 : $("#s2").val(),
			s3 : $("#s3").val(),
			Culkum : $("#culkum").val(),
			loAirFare2 : $("#loAirFare2").val(),
			loTranFare2 : $("#loTranFare2").val(),
			visaFeeFare2 : $("#visaFeeFare2").val(),
			ovCharFare2 : $("#ovCharFare2").val(),
			vocLeeFare2 : $("#vocLeeFare2").val(),
			hostFare2 : $("#hostFare2").val(),
			execDtl : $("#execDtl").val(),
			usNightDay2 : $("#usNightDay2").val(),
			usDayDay2 : $("#usDayDay2").val(),
			eduDay2 : $("#eduDay2").val(),
			spotDay2 : $("#spotDay2").val(),
			euNightDay2 : $("#euNightDay2").val(),
			euDayDay2 : $("#euDayDay2").val(),
			enNightDay2 : $("#enNightDay2").val(),
			enDayDay2 : $("#enDayDay2").val(),
			jaNightDay2 : $("#jaNightDay2").val(),
			jaDayDay2 : $("#jaDayDay2").val(),
			rem : $("#rem").val(),
			assDtl : $("#assDtl").val()
	};


	var params = {
			docNo : v_DocNo,
			docSts : "D16",
			ifParam : JSON.stringify(data)
	};

	if(v_AdjustDocSts == ""){
		gf_Transaction("SAVE_ADJUST_DOC", "/trip/outerTrip/saveAdjustDoc.xpl", params, {}, "f_Callback", true);
	}else{
		gf_Transaction("SAVE_ADJUST_DOC", "/trip/outerTrip/updateAdjustDoc.xpl", params, {}, "f_Callback", true);
	}

}


function cf_setSavedAdjustDoc(){
	var params = {
			docNo : v_DocNo,
			adjustStsCd : v_AdjustDocSts
	};

	gf_Transaction("SELECT_SAVED_ADJUST_DOC", "/trip/outerTrip/getAdjustSavedDoc.xpl", params, {}, "f_Callback", true);

}

function cf_setSavedDocInfo(){

	var ifParam = ds_SavedDoc.get(0, "ifParam");
	var data;
	if(ifParam != "" || ifParam != undefined){
		data = JSON.parse(ifParam);
	};

	var fDate = v_IfParam.startDate;
	var tDate = v_IfParam.endDate;
	var refNo = ds_OuterTripDoc.get(0).refNo;
	var IBdocno = ds_OuterTripDoc.get(0).docNo; // 문서번호

	// 최초항공료와 확정항공료를 가져온다.
	var params = {
		Refkey : refNo,
		Fdate : fDate,
		Tdate : tDate,
		Notesdoc : IBdocno
	};

	gf_Transaction("SELECT_AIRFARE_INVOICE", "/trip/eai/getAirfareInvoiceDay.xpl", params, {}, "f_Callback", true);
}



function cf_adjustDraft(adjustDocSts){

	var confirmText = "";
	if(adjustDocSts == "D16"){
		confirmText = "정산서를 협조의뢰 하시겠습니까?";
	}else if(adjustDocSts == "D50"){
		confirmText = "문서를 검토완료 하시겠습니까?";
	}else if(adjustDocSts == "D60"){
		confirmText = "문서를 검토완료 하시겠습니까?";
	}


	if(confirm(confirmText)){

		var data = {
				jsGubun : $("#jsGubun").val(),
				tripNat : $("#tripNat").val(),
				rTripDateStart : $("#rTripDateStart").val(),
				rTripDateEnd : $("#rTripDateEnd").val(),
				payDate : $("#payDate").val(),
				tReq : $("#tReq").val(),
				tPurp : $("#tPurp").val(),
				tPlanDtl : $("#tPlanDtl").val(),
				tRptDtl : $("#tRptDtl").val(),
				tDataList : $("#tDataList").val(),
				s1 : $("#s1").val(),
				s2 : $("#s2").val(),
				s3 : $("#s3").val(),
				Culkum : $("#culkum").val(),
				loAirFare2 : $("#loAirFare2").val(),
				loTranFare2 : $("#loTranFare2").val(),
				visaFeeFare2 : $("#visaFeeFare2").val(),
				ovCharFare2 : $("#ovCharFare2").val(),
				vocLeeFare2 : $("#vocLeeFare2").val(),
				hostFare2 : $("#hostFare2").val(),
				execDtl : $("#execDtl").val(),
				usNightDay2 : $("#usNightDay2").val(),
				usDayDay2 : $("#usDayDay2").val(),
				eduDay2 : $("#eduDay2").val(),
				spotDay2 : $("#spotDay2").val(),
				euNightDay2 : $("#euNightDay2").val(),
				euDayDay2 : $("#euDayDay2").val(),
				enNightDay2 : $("#enNightDay2").val(),
				enDayDay2 : $("#enDayDay2").val(),
				jaNightDay2 : $("#jaNightDay2").val(),
				jaDayDay2 : $("#jaDayDay2").val(),
				rem : $("#rem").val(),
				assDtl : $("#assDtl").val()
		};


		var params = {
				docNo : v_DocNo,
				docSts : adjustDocSts,
				ifParam : JSON.stringify(data)
		};

		gf_Transaction("SAVE_ADJUST_DOC", "/trip/outerTrip/updateAdjustDoc.xpl", params, {}, "f_Callback", true);
	}
}

function cf_retrieveSignLine(){


	// 출장자 정보
//	v_TripUserId;
//	v_TripUserOrgCd;
//	v_TripUserPositCd;

	var orgCls = "";


	// 팀장 이상 직책일 체크
	if(ds_ChiefCd.find("value", v_IfParam.tripUserRpswrkCd) != -1){
		v_IsCheif = "Y";
	}else{

	}


	// 본부장 이상 직책일 체크
	if(ds_HqRpswrkCd.find("value", v_IfParam.tripUserRpswrkCd) != -1){
		orgCls = "본부/실";
	}else{
		orgCls = "팀/현장";
	}

	// 화면 상단 결재선 셋팅
	// 현장소속의 경우 현장담당책임자를 가져오지않으므로 수동으로 중간협의자로 넣어줘야한다.
	var params = {
  		orgCd : v_IfParam.tripUserOrgCd,
  		orgCls : orgCls,
  		userId : v_IfParam.tripUserId
	};

	gf_Transaction("SELECT_SIGN_LINE", "/co/common/user/retrieveSignlnForExcluRegl.xpl", params, {}, "f_Callback", true);
}

function cf_setSignlnInfo(resultData) {
	ds_Signln.clear();

	var cnt = 0;

	ds_Signln.insert(cnt, {
		signId : "",
		signSeq : cnt + 1,
		signTpCd : "T01",
		signUserId : v_IfParam.drafterUserId, // 세션에서 받아온 값
		signUserNm : v_IfParam.drafterUserKnm, // 세션에서 받아온 값
		psignUserNm : "",
		apperPositCd : v_IfParam.drafterUserPositCd,
		apperPositNm : v_IfParam.drafterUserPositCd,
		perpsignPositNm : "",
		apperRpswrkCd : "",
		apperRpswrkNm : "",
		apperOrgCd : v_IfParam.drafterUserOrgCd,
		apperOrgNm : v_IfParam.drafterUserOrgNm,
		orgChrcCls : "D"
	});

	if(v_IfParam.tripUserRpswrkCd == "대표이사사장"){
		cnt++;

		ds_Signln.insert(cnt, {
			signId : "",
			signSeq : cnt + 1,
			signTpCd : "T02",
			signUserId : v_IfParam.tripUserId, // 세션에서 받아온 값
			signUserNm : v_IfParam.tripUserKnm, // 세션에서 받아온 값
			psignUserNm : "",
			apperPositCd : v_IfParam.tripUserPositCd,
			apperPositNm : v_IfParam.tripUserPositCd,
			perpsignPositNm : "",
			apperRpswrkCd : "",
			apperRpswrkNm : "",
			apperOrgCd : v_IfParam.tripUserOrgCd,
			apperOrgNm : v_IfParam.tripUserOrgNm,
			orgChrcCls : "D"
		});


	}else{
		// 사장님 아님
		for ( var i = 0; i < resultData.ds_SignlnForExcluRegl.length; i++) {

			// 기안자와 ID가 같다면 결재라인에서 빠진다.
			var temp = resultData.ds_SignlnForExcluRegl[i];
			if(resultData.ds_SignlnForExcluRegl.length == 1){
					cnt++;

					ds_Signln.insert(cnt, {
						signId : "",
						signSeq : cnt + 1,
						signTpCd : "T02",
						signUserId : temp.signUserId, // 세션에서 받아온 값
						signUserNm : temp.signUserNm, // 세션에서 받아온 값
						psignUserNm : "",
						apperPositCd : temp.apperPositCd,
						apperPositNm : temp.apperPositNm,
						perpsignPositNm : "",
						apperRpswrkCd : temp.apperRpswrkCd,
						apperRpswrkNm : temp.apperRpswrkNm,
						apperOrgCd : temp.apperOrgCd,
						apperOrgNm : temp.apperOrgNm,
						orgChrcCls : "D"
					});
			}else if(resultData.ds_SignlnForExcluRegl.length > 0){
				if(v_IfParam.tripUserId != temp.signUserId){

					/**
					 * 사장전결의 경우 수석부사장은 결재라인에서 빠짐
					 */
					if(orgCls == "회장" && temp.apperPositCd == "수석부사장"){

					}else{
						cnt++;

						ds_Signln.insert(cnt, {
							signId : "",
							signSeq : cnt + 1,
							signTpCd : "T02",
							signUserId : temp.signUserId, // 세션에서 받아온 값
							signUserNm : temp.signUserNm, // 세션에서 받아온 값
							psignUserNm : "",
							apperPositCd : temp.apperPositCd,
							apperPositNm : temp.apperPositNm,
							perpsignPositNm : "",
							apperRpswrkCd : temp.apperRpswrkCd,
							apperRpswrkNm : temp.apperRpswrkNm,
							apperOrgCd : temp.apperOrgCd,
							apperOrgNm : temp.apperOrgNm,
							orgChrcCls : "D"
						});
					}
				}

			}

		}

	}

	gf_AssembleSignln(ds_Signln);

	gf_DisableCurrentOverlay();

	$(".ajax_overlay").remove();



}

function f_notSpace(val){
	if(val == "" || val == undefined) val = 0;
	return val;
}

/**
 * 임시저장된 결재선 조회
 */
function cf_RetrieveSignInfo(){

	//해당 업무구분과 업무분류에 해당되는 양식명을 가져옴
	var signParams = {
			docNo : v_DocNo
	};

	gf_Transaction("SELECT_SIGN_INFO", "/trip/outerTrip/retrieveSignInfo3.xpl", signParams, {}, "f_Callback", true);
}

/**
 *  결재중이거나 완료된 결재선 조회
 */
function cf_RetrieveSignInfo2(){

	//해당 업무구분과 업무분류에 해당되는 양식명을 가져옴
	var signParams = {
			docNo : v_DocNo
	};

	gf_Transaction("SELECT_SIGN_INFO", "/trip/outerTrip/retrieveSignInfo4.xpl", signParams, {}, "f_Callback", true);
}


//(정산)문서만 삭제
function f_deleteDoc(){
	var params = {
			docNo : v_DocNo
	};

	gf_Transaction("SAVE_DELETE_DOC", "/trip/outerTrip/deleteOuterTripAdjustDoc.xpl", params, {}, "f_Callback", true);

}