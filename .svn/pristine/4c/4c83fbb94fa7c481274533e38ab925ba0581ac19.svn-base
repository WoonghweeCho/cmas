/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/

var v_tripUserDetailTr = "<tr><td class=\"inpt\" style=\"width:40%; text-align:center\"><input type=\"text\" name=\"tUserEnm\"></td>"
 + "<td class=\"inpt\" style=\"width:30%; text-align:center\"><select name=\"tGrade\"><option value=\"F\" selected=\"selected\">First</option><option value=\"B\">Business</option><option value=\"E\">Economy</option></select></td>"
 + "<td class=\"inpt\" style=\"width:30%; text-align:center\"><input type=\"text\" name=\"tAirAmount\" value=\"0\">원</td></tr>";

var v_visaInfoDetailTr = "<tr><td class=\"inpt\" style=\"width:20%; text-align:center\"><input type=\"text\" name=\"vNat\"></td><td class=\"inpt\" style=\"width:20%; text-align:center\"><input type=\"text\" name=\"vVisaYn\"></td>"
 + "<td class=\"inpt\" style=\"width:60%; text-align:center\">	발급일 : <input type=\"text\" name=\"vStartDate\"> / 만료일 : <input type=\"text\" name=\"vEndDate\"></td></tr>";

var v_cityDetailTr = "<tr><td class=\"inpt\" style=\"width:30%; text-align:center\">	<input type=\"text\" name=\"natNm\" style=\"width: 120px\"> / <input type=\"text\" name=\"cityNm\" style=\"width: 120px\"></td>"
+ "<td class=\"inpt\" style=\"width:20%; text-align:center\">	<input type=\"text\" name=\"stayStYmd\" style=\"width: 100px\">부터<br><input type=\"text\" name=\"stayEdYmd\" style=\"width: 100px\">까지</td>"
+ "<td class=\"inpt\" style=\"width:50%; text-align:center\"><textarea style=\"width:95%\" name=\"dutyCont\"></textarea></td></tr>";

//임시저장
var v_DocSts; // 상태
var v_DraftSts;
var ds_SavedDoc = new DataSet();
var ds_Visa = new DataSet();
var ds_HqRpswrkCd = new DataSet();


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
var v_DraftUserPositCd;
var v_DraftUserRpswrkCd;


// 출장자 정보
var v_TripUserId;
var v_TripUserKnm;
var v_TripUserEnm;
var v_TripUserOrgCd;
var v_TripUserOrgNm;
var v_TripUserPositCd;
var v_TripUserRpswrkCd;
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

var ds_Signln = new DataSet();			//결재선정보

var v_CallbackFunc;

var hostCheck = 0;

var orgCls = "";

var v_secYn = "";
var v_ghrYn = "";

var v_ErrMsg = "";



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

	v_CmasId = gf_IsNull(datas.fromList) ? "" : datas.fromList;
	v_DocSts = gf_IsNull(datas.docSts) ? "" : datas.docSts;

	v_CallbackFunc = gf_IsNull(datas.callbackFunc) ? ""          : datas.callbackFunc;

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
	//기안일 검색 Date Component 초기화
//	$("#fPassp").mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
//	$("#tPassp").mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });

	$("#fPassp").attr("readonly", "true");
	$("#tPassp").attr("readonly", "true");

	gf_SetUserComponent($("#tripUser"), function(data){
//		var userInfo = data.orgNm + " " + data.userPositCd + " " + data.userKnm + "(" + data.userId + ")";
//		$("#userInfo").val(userInfo);

		// 출장자 정보
		v_TripUserId = data.userId;
		v_TripUserKnm = data.userKnm;
		v_TripUserEnm = data.userEnm;
		v_TripUserOrgCd = data.orgCd;
		v_TripUserOrgNm = data.orgNm;
		v_TripUserPositCd = data.userPositCd;
		v_TripUserRpswrkCd = data.userRpswrkCd;

		$("#tripUserEnm").val(v_TripUserEnm);

		cf_retrieveSignLine();
	});

	//Attachment 컴포넌트 생성
	gf_InitFileUploadComponent();

	// upload 모드로 컴포넌트의 mode  설정
    gf_setMode("download");
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

	$("#tripUserAddBtn").click(function(){

		cf_AppendTripUser();
	});

	$("#tripUserDelBtn").click(function(){
		if($("#tripUserDetail tr").size() < 3){
			gf_AlertMsg("더이상 삭제할 수 없습니다");
		}else{
			$("#tripUserDetail tr:last").remove();
			$("#totalUser").text($("#tripUserDetail tr").size()-1);
		}

	});



	$("#visaAddBtn").click(function(){
		//$("#visaInfoDetail").append(v_visaInfoDetailTr);

//		gf_PostOpen("/common/jsp/comp/budget/bdgtSelect.jsp", null,
//				"toolbar=no,scrollbars=no,width=665,height=600", bdgtParams,
//				true, true, "f_callBackFuncBdgtSelect");


		var param = {
				ds_NatList : ds_NatList.getAllData(),
				dsIndex : -1
		};


		gf_PostOpen("/trip/outerTrip/visaInfoAddP.jsp", null,
				"toolbar=no,scrollbars=yes,width=620,height=483", param,
				true, true, "f_callBackFuncVisaAddP");

	});



	$("#visaDelBtn").click(function(){
		if($("#visaInfoDetail tr").size() < 3){
			gf_AlertMsg("더이상 삭제할 수 없습니다");
		}else{
			$("#visaInfoDetail tr:last").remove();
		}

	});


	$("#cityAddBtn").click(function(){

		var param = {
				ds_NatList : ds_NatList.getAllData(),
				dsIndex : -1
		};


		gf_PostOpen("/trip/outerTrip/cityInfoAddP.jsp", null,
				"toolbar=no,scrollbars=yes,width=620,height=560", param,
				true, true, "f_callBackFuncCityAddP");


	});

	$("#cityDelBtn").click(function(){
		if($("#cityDetail tr").size() < 3){
			gf_AlertMsg("더이상 삭제할 수 없습니다");
		}else{
			$("#cityDetail tr:last").remove();
		}

	});


	$("#visaInfo").click(function(){

		var param = {
				ds_NatList : ds_NatList.getAllData(),
				dsIndex : -1
		};


		gf_PostOpen("/trip/outerTrip/visaInfoList.jsp", null,
				"toolbar=no,scrollbars=yes,width=810,height=820", param,
				false, false, "null");

	});

	$("#inputAmt").click(function(){
//		$("#cityDetail").append(v_cityDetailTr);

		var param = {
				ds_NatList : ds_NatList.getAllData()
		};


		gf_PostOpen("/trip/outerTrip/inputAmt.jsp", null,
				"toolbar=no,scrollbars=yes,width=620,height=560", param,
				true, true, "f_callBackFuncInputAmt");


	});


	$("#bdgtBtn").click(function(){

		var bdgtParams = {
				bdgtType : v_BdgtType
		};

		gf_PostOpen("/common/jsp/comp/budget/bdgtSelect.jsp", null,
				"toolbar=no,scrollbars=no,width=665,height=600", bdgtParams,
				true, true, "f_callBackFuncBdgtSelect");


	});

	$("#tripUserAirAmount").bind('keyup', function(e) {
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

		// 출장자 항공료도 합친다.
		var tUserVal = $("#tripUserAirAmount").val();

		for(var j = 0; j < tUserVal.length; j++){
			tUserVal = tUserVal.replace(",", "");
		}

		total = total + parseInt(tUserVal);

		v_AirTotalAmt = total;
		$("#airTotalAmount").text(gf_AmtFormat(total));

		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}
		e.currentTarget.value = gf_AmtFormat(targetVal);

	});

	$("#loAirFare").bind('keyup', function(e) {
		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}
		e.currentTarget.value = gf_AmtFormat(targetVal);

		var exVal = Math.round(parseFloat(targetVal) * parseFloat(v_USD));

		$("#loAirFareEx").text(gf_AmtFormat(exVal));

		v_TAirAmt = exVal;

		// 개인지급 합계 (채재바 + 항공료)
		v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;
		$("#totalAmount").text(gf_AmtFormat(v_TotalAmount));

	});

	$("#loTranFare").bind('keyup', function(e) {
		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}
		e.currentTarget.value = gf_AmtFormat(targetVal);

		var exVal = Math.round(parseFloat(targetVal) * parseFloat(v_USD));

		$("#loTranFareEx").text(gf_AmtFormat(exVal));

		v_TTranspAmt = exVal;

		// 개인지급 합계 (채재바 + 항공료)
		v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;
		$("#totalAmount").text(gf_AmtFormat(v_TotalAmount));
	});

	$("#visaFeeFare").bind('keyup', function(e) {
		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}
		e.currentTarget.value = gf_AmtFormat(targetVal);

		var exVal = Math.round(parseFloat(targetVal) * parseFloat(v_USD));

		$("#visaFeeFareEx").text(gf_AmtFormat(exVal));

		v_VisaFee = exVal;

		// 개인지급 합계 (채재바 + 항공료)
		v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;
		$("#totalAmount").text(gf_AmtFormat(v_TotalAmount));
	});

	$("#ovCharFare").bind('keyup', function(e) {
		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}
		e.currentTarget.value = gf_AmtFormat(targetVal);

		var exVal = Math.round(parseFloat(targetVal) * parseFloat(v_USD));

		v_OverCharge = exVal;

		$("#ovCharFareEx").text(gf_AmtFormat(exVal));

		// 개인지급 합계 (채재바 + 항공료)
		v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;
		$("#totalAmount").text(gf_AmtFormat(v_TotalAmount));
	});

	$("#vocLeeFare").bind('keyup', function(e) {
		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}
		e.currentTarget.value = gf_AmtFormat(targetVal);

		var exVal = Math.round(parseFloat(targetVal) * parseFloat(v_USD));

		v_Benefits = exVal;

		$("#vocLeeFareEx").text(gf_AmtFormat(exVal));

		// 개인지급 합계 (채재바 + 항공료)
		v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;
		$("#totalAmount").text(gf_AmtFormat(v_TotalAmount));
	});

	$("#hostFare").bind('keyup', function(e) {
		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}
		e.currentTarget.value = gf_AmtFormat(targetVal);

		var exVal = Math.round(parseFloat(targetVal) * parseFloat(v_USD));

		$("#hostFareEx").text(gf_AmtFormat(exVal));

		v_HostAmt = exVal;

		// 개인지급 합계 (채재바 + 항공료)
		v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;
		$("#totalAmount").text(gf_AmtFormat(v_TotalAmount));
	});

	$("#outerTripDraft").click(function(){

		v_DraftSts = "D50";

		gf_onFileUpload();

//		cf_saveOuterDraftDoc(docSts);

		// 예산 체크
//		cf_CheckOsBizTrip();


	});


	// 문서를 임시저장 처리 한다.
	// 이미 적상되어진 같을 JSON Object 형식으로 IF_PARAM 컬럼에 저장한다.
	$('#outerTripSave').click(function(){

		v_DraftSts = "D16";

		gf_onFileUpload();

//		cf_saveOuterDocSave(docSts);


	});

	// 글로벌 HR팀 검토승인
	// SGNS DRAFT 시작
	$('#outerTripHRConfirm').click(function(){

		cf_sgnsRemoteDraft();


	});

	// 글로벌 HR팀 검토반려
	// 문서 반려 처리
	$('#outerTripHRNotConfirm').click(function(){


		alert("글로벌HR검토반려");
		self.close();


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

	$("input").css("border", "0px");
	$("textarea").css("border", "0px");
	$("#tripUser_btn").hide();



	// 본부장 이상 직책 삽입
	//ds_HqRpswrkCd.setAllData(gv_hqRpswrkCd);

	if(v_DocSts == "D16"){

		$("#outerTripDelete").show();

		v_DocNo = v_CmasId; // 이미 생성되어진 v_CmasId;
  		var docNoText = v_DocNo.split("-");
  		$("#docNo").text(docNoText[1] + "-" + docNoText[2]);



		gf_Transaction("SELECT_NAT_LIST_2", "/trip/outerTrip/retrieveNatList.xpl", {}, {}, "f_Callback", true);
		gf_Transaction("SELECT_REF_LIST", "/trip/outerTrip/retrieveTripRef.xpl", {}, {}, "f_Callback", true);


	}else if(v_DocSts == "D50"){

		// 글로벌 HR팀 사전검토
		v_DocNo = v_CmasId; // 이미 생성되어진 v_CmasId;
  		var docNoText = v_DocNo.split("-");
  		$("#docNo").text(docNoText[1] + "-" + docNoText[2]);



		gf_Transaction("SELECT_NAT_LIST_2", "/trip/outerTrip/retrieveNatList.xpl", {}, {}, "f_Callback", true);
		gf_Transaction("SELECT_REF_LIST", "/trip/outerTrip/retrieveTripRef.xpl", {}, {}, "f_Callback", true);

	}else if(v_DocSts == "D60"){

		// 글로벌 HR팀 사전검토
		v_DocNo = v_CmasId; // 이미 생성되어진 v_CmasId;
  		var docNoText = v_DocNo.split("-");
  		$("#docNo").text(docNoText[1] + "-" + docNoText[2]);



		gf_Transaction("SELECT_NAT_LIST_2", "/trip/outerTrip/retrieveNatList.xpl", {}, {}, "f_Callback", true);
		gf_Transaction("SELECT_REF_LIST", "/trip/outerTrip/retrieveTripRef.xpl", {}, {}, "f_Callback", true);
	}else if(v_DocSts == "D70"){

		// 글로벌 HR팀 사전검토
		v_DocNo = v_CmasId; // 이미 생성되어진 v_CmasId;
  		var docNoText = v_DocNo.split("-");
  		$("#docNo").text(docNoText[1] + "-" + docNoText[2]);



		gf_Transaction("SELECT_NAT_LIST_2", "/trip/outerTrip/retrieveNatList.xpl", {}, {}, "f_Callback", true);
		gf_Transaction("SELECT_REF_LIST", "/trip/outerTrip/retrieveTripRef.xpl", {}, {}, "f_Callback", true);


	}else{
		// 최초 작성이므로 기본 정보를 조회한다.

	}

	// 에러내역 조회
	var params = {
			docNo : v_DocNo
	};

	gf_Transaction("SELECT_ERR_MSG", "/trip/outerTrip/retrieveErrMsg.xpl", params, {}, "f_Callback", true);


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
	  	case "SELECT_SAVED_DOC_INFO" :

	  		//저장된 데이터 이므로 세션이 아닌 출장자정보에서 셋팅해야한다.
	  		ds_SavedDoc.setAllData(resultData.ds_DocData);

			ds_TripUserList.setAllData(resultData.ds_ExtnlPer);
			ds_InputAmt.setAllData(resultData.ds_Expn);
			ds_Visa.setAllData(resultData.ds_Visa);

	  		if(v_DocSts == "D16"){
//	  			alert("오늘 일자로 환율 조회");

	  			var ymdParams = {
			  		Ymd : $.datepicker.formatDate("yymmdd", new Date())
			  	};

			  	gf_Transaction("SELECT_SAVED_EXRATE", "/trip/eai/getSendExrate.xpl", ymdParams, {}, "f_Callback", true);

	  		}else{
//	  			alert("저장된 일자로 환율 조회");
//	  			$("#refTr").show();
	  			$("#refDay").text(ds_SavedDoc.get(0, "fstRegDt").substr(0, 10));

	  			var savedYmd = ds_SavedDoc.get(0, "fstRegDt").substr(0, 10);

		  		var ymdParams = {
		  			Ymd : $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", savedYmd))
		  		};

		  		gf_Transaction("SELECT_SAVED_EXRATE", "/trip/eai/getSendExrate.xpl", ymdParams, {}, "f_Callback", true);

	  		}




	  		break;
	  	case "SELECT_CMAS_ID" :

	  		v_DocNo = resultData.ds_Result[0].docNo;
	  		var docNoText = v_DocNo.split("-");
	  		$("#docNo").text(docNoText[1] + "-" + docNoText[2]);

	  		break;
	  	case "SELECT_NAT_LIST" :
	  		ds_NatList.setAllData(resultData.ds_NatList);

			break;
	  	case "SELECT_NAT_LIST_2" :


	  		ds_NatList.setAllData(resultData.ds_NatList);

	  		if(v_DocNo == undefined || v_DocNo == "" || v_DocNo == "undefined"){
	  			gf_AlertMsg("문서번호를 가져올 수 없습니다.");

	  			if ( !gf_IsNull(v_CallbackFunc) ) {
		  			var openCallbackFunc = "opener."+v_CallbackFunc;
		  	    	eval(openCallbackFunc + "();");
		  	    }

		  		self.close();
	  		}else{
	  			var searchParams = {
		  				docNo : v_DocNo
		  		};

		  		// IF_PARAM 을 조회하여 문서에 셋팅한다.
		  		gf_Transaction("SELECT_SAVED_DOC_INFO", "/trip/outerTrip/retrieveSavedDocInfo.xpl", searchParams, {}, "f_Callback", true);

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
	  	case "SELECT_SGNS_REMOTE_DRAFT" :
//	  		var signId = resultData.output1[0].signId;
//	  		var signInfo = resultData.output1[0].signInfo;
//
////	  		signInfo=1DFUR^1202429^1^T01##1DFUR^1202564^2^T02
//	  		var ds_SignInfo = new DataSet();
//	  		var signInfoList = signInfo.split("##");
//	  		for(var i = 0; i < signInfoList.length; i++){
//	  			var sign = signInfoList[i].split("^");
//	  			var signStsCd = "";
//	  			if(sign[2] == "1"){
//	  				signStsCd = "S02";
//	  			}else{
//	  				signStsCd = "S04";
//	  			}
//	  			var signParam = {
//	  					apperOrgCd : sign[0],
//	  					signUserId : sign[1],
//	  					signSeq : sign[2],
//	  					signTpCd : sign[3],
//	  					signStsCd : signStsCd
//	  			};
//
//	  			ds_SignInfo.add(signParam);
//
//	  		}
//
//
//	  		// 국출 01 해출 02 시내 03
//	  		var updateParams = {
//	  				signId : signId,
//	  				docNo : v_DocNo,
//	  				dutyCls : '02'
//	  		};
//
//	  		var dataSetParam = {
//	  				ds_SignInfo : ds_SignInfo.getAllData("U")
//			};
//
//	  		gf_Transaction("SAVE_CMAS_DOC_UPDATE_SIGN_ID", "/trip/outerTrip/saveCmasDocUpdateSignId.xpl", updateParams, dataSetParam, "f_Callback", true);


	  		var signId = resultData.output1[0].signId;
	  		var signInfo = resultData.output1[0].signInfo;

//	  		signInfo=1DFUR^1202429^1^T01##1DFUR^1202564^2^T02
	  		var ds_SignInfo = new DataSet();
	  		var signInfoList = signInfo.split("##");
	  		for(var i = 0; i < signInfoList.length; i++){
	  			var sign = signInfoList[i].split("^");
	  			var signStsCd = "";
	  			if(sign[2] == "1"){
	  				signStsCd = "S02";
	  			}else{
	  				signStsCd = "S04";
	  			}
	  			var signDt = "N";
	  			if(sign[3] == "T01"){
	  				signDt = "Y";
	  			}
	  			var signParam = {
	  					apperOrgCd : sign[0],
	  					signUserId : sign[1],
	  					signSeq : sign[2],
	  					signTpCd : sign[3],
	  					signStsCd : signStsCd,
	  					signDt : signDt
	  			};

	  			ds_SignInfo.add(signParam);

	  		}


	  		var dataSetParam = {
	  				ds_SignInfo : ds_SignInfo.getAllData("U")
			};

	  		if(resultData.output1[0].type == "SUCCESS"){

		  		// 국출 01 해출 02 시내 03
		  		var updateParams = {
		  				signId : signId,
		  				docNo : v_DocNo,
		  				dutyCls : '02'
		  		};

	  			gf_Transaction("SAVE_CMAS_DOC_UPDATE_SIGN_ID", "/trip/outerTrip/saveCmasDocUpdateSignId.xpl", updateParams, dataSetParam, "f_Callback", true);
	  		}else{

	  			var updateParams = {
		  				docSts : "D99",
		  				docNo : v_DocNo
		  		};

	  			gf_Transaction("SAVE_CMAS_DOC_UPDATE_SIGN_ID", "/trip/outerTrip/saveCmasDocUpdateFail.xpl", updateParams, {}, "f_Callback", true);
	  		}


	  		break;
	  	case "SAVE_CMAS_DOC_UPDATE_SIGN_ID" :
	  		// 문서가 성공적으로 처리되면 문서 리스트를 다시 조회하여 갱신하여 준다.
	  		if ( !gf_IsNull(v_CallbackFunc) ) {
	  			var openCallbackFunc = "opener."+v_CallbackFunc;
	  	    	eval(openCallbackFunc + "();");
	  	    }

	  		self.close();

	  		break;
	  	case "SELECT_SIGN_LINE" :
//	  		ds_Signln.setAlldata(resultData.ds_Signln);

	  		cf_setSignlnInfo(resultData);
	  		break;
	  	case "SAVE_OUTER_TRIP_DOC_SAVE" :

	  		// 문서가 성공적으로 처리되면 문서 리스트를 다시 조회하여 갱신하여 준다.
	  		if ( !gf_IsNull(v_CallbackFunc) ) {
	  			var openCallbackFunc = "opener."+v_CallbackFunc;
	  	    	eval(openCallbackFunc + "();");
	  	    }

	  		self.close();
	  		break;
	  	case "SELECT_SIGN_INFO" :
	  		ds_Signln.clear();
	  		ds_Signln.setAllData(resultData.ds_Sign);
	  		gf_AssembleSignln(ds_Signln);
	  		break;
	  	case "SELECT_SAVED_EXRATE" :


	  		ds_ExRList.setAllData(resultData.output1[0].SapItab);

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


	  		var data = JSON.parse(ds_SavedDoc.get(0, "ifParam"));

	  		if(!(data.fileAtchId == "undefined" || data.fileAtchId == undefined)){
	  			// 첨부파일이 존재하는 경우
	  			gf_retrieveFileList(data.fileAtchId);

	  		}

	  		// 증빙일자
	  		$("#oDate").val(data.oDate);

	  		// 숙소구분
	  		if(data.innType == "H"){
	  			$("#innType").text("현장숙소");
	  		}else if(data.innType == "G"){
	  			$("#innType").text("외부숙소(호텔)");
	  		}

	  		// 전역 변수 및 데이터 셋팅
	  		// 예산관련
	  		v_BdgtType = data.bdgtType;
	  		v_Aufnr = data.aufnr;
	  		v_KText = data.kText;
	  		v_Kostv = data.kostv;
	  		v_Kostvnm = data.kostvnm;
	  		v_Chief = data.chief;
	  		v_Chiefnm = data.chiefNm;


	  		$("#cGubun").val(data.cGubun);

	  		if(v_BdgtType != ""){
	  			var AufnrText = v_Aufnr +  " / 내역 : " + v_KText;
		  		$("#Aufnr").text(AufnrText);
		  		var KostvText = v_Kostvnm + " (" + v_Kostv + ")";
	  			$("#bdgtTeam").text(KostvText);


		  		switch(v_BdgtType) {
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
			  		case "K" :
			  			$("#bdgtType").text("K. 본사집행현장원가");
			  			break;
			  		default :
				  		break;
				}

	  		}

	  		if(v_BdgtType == "I"){
	  			// I 일반경비 일 경우 경비 이체코드 입력창을 보인다.
	  			$("#VTCodeSpan").show();
	  			$("#vTcode").val(data.vTcode);
	  		}

	  		// 작성자 정보
			v_DraftUserId = data.draftUserId;
			v_DraftUserKnm = data.draftUserKnm;
			v_DraftUserEnm = data.draftUserEnm;
			v_DraftUserOrgCd = data.draftUserOrgCd;
			v_DraftUserOrgNm = data.draftUserOrgNm;
			v_DraftUserPositCd = data.draftUserPositCd;
			v_DraftUserRpswrkCd = data.draftUserRpswrkCd;



			var drafterText = v_DraftUserId + " " + v_DraftUserKnm;
			$("#drafter").text(drafterText);

			// 출장자 정보
			v_TripUserId = data.tripUserId;
			v_TripUserKnm = data.tripUserKnm;
			v_TripUserEnm = data.tripUserEnm;
			v_TripUserOrgCd = data.tripUserOrgCd;
			v_TripUserOrgNm = data.tripUserOrgNm;
			v_TripUserPositCd = data.tripUserPositCd;
			v_TripUserRpswrkCd = data.tripUserRpswrkCd;

			// 협의자 정보
			v_dSignType = data.v_dSignType; // 타 집행팀 여부
			v_dSignUserCd = data.v_dSignUserCd; // 타 집행팀장 ORG
			v_dSignUserId = data.v_dSignUserId; // 타 집행티장 ID
			v_dSignUserNm = data.v_dSignUserNm;
			v_dSignOrgNm = data.v_dSignOrgNm; // resultData.ds_coUser[0].userId;
			v_dSignRpwrkNm = data.v_dSignRpwrkNm;
			v_dSignRpswrkCd = data.v_dSignRpswrkCd;
			v_dSignPositCd = data.v_dSignPositCd;
			v_dSignPositNm = data.v_dSignPositNm;

			if(v_dSignType == "Y"){
				//타팀 협의 일 경우 텍스트 팝업
  				$("#dSignDiv").show();
			}

			//소속팀 셋팅
			var drafterOrgNm = v_DraftUserOrgNm + " (" + v_DraftUserOrgCd + ")";
			$("#drafterOrgNm").text(drafterOrgNm);

			var drafter = v_DraftUserOrgNm + " (" + v_DraftUserOrgCd + ") " + v_DraftUserPositCd + " " + v_DraftUserKnm + " (" + v_DraftUserId + ")";

			//소속팀 직급 이름 (사번)
			$("#drafterSap").text(drafter);

			//출장자 셋팅
			$("#tripUser_id").val(v_TripUserId);
			$("#tripUser_name").val(v_TripUserKnm);
			$("#tripUserEnm").val(v_TripUserEnm);

			var tripUserInfo = v_TripUserOrgNm + " (" + v_TripUserOrgCd + ") " + v_TripUserPositCd + " " +  v_TripUserId  + " " + v_TripUserKnm;

			$("#tripUserInfo").text(tripUserInfo);

			//좌석 등급
			$("select[name='tripUserGrade']").val(data.tripUserSGrade);
			// 출장자 항공료
			$("#tripUserAirAmount").val(data.tripUserAmt);
			// 출장 목적
			$("#tPurp").val(data.tripPurp);
			// 요청 사항
			$("#tReq").val(data.tripReq);
			// 여권 발급일
			$("#fPassp").val(data.ppStartDt);
			// 여관 만료일
			$("#tPassp").val(data.ppEndDt);

			// 항공료 요청사항
			$("#pecul").val(data.airReq);

			// 현지항공료
			var tAirAmt = data.tAirAmt;
			if(tAirAmt == undefined || tAirAmt == "") tAirAmt = 0;
			$("#loAirFare").val(tAirAmt);
			$("#loAirFareEx").text(gf_AmtFormat(Math.round(parseFloat(removeComma(tAirAmt)) * parseFloat(v_USD))));
			$("#loAirFareDtl").val(data.tAirAmtDtl);
			// 현지 교통비
			var tTranspAmt = data.tTranspAmt;
			if(tTranspAmt == undefined || tTranspAmt == "") tTranspAmt = 0;
			$("#loTranFare").val(tTranspAmt);
			$("#loTranFareEx").text(gf_AmtFormat(Math.round(parseFloat(removeComma(tTranspAmt)) * parseFloat(v_USD))));
			$("#loTranFareDtl").val(data.tTranspAmtDtl);
			// Visa Fee
			var visaFee = data.visaFee;
			if(visaFee == undefined || visaFee == "") visaFee = 0;
			$("#visaFeeFare").val(visaFee);
			$("#visaFeeFareEx").text(gf_AmtFormat(Math.round(parseFloat(removeComma(visaFee)) * parseFloat(v_USD))));
			$("#visaFeeFareDtl").val(data.visaFeeDtl);
			// Over Charge
			var overCharge = data.overCharge;
			if(overCharge == undefined || overCharge == "") overCharge = 0;
			$("#ovCharFare").val(overCharge);
			$("#ovCharFareEx").text(gf_AmtFormat(Math.round(parseFloat(removeComma(overCharge)) * parseFloat(v_USD))));
			$("#ovCharFareDtl").val(data.overChargeDtl);
			// 복리후생
			var benefits = data.benefits;
			if(benefits == undefined || benefits == "") benefits = 0;
			$("#vocLeeFare").val(benefits);
			$("#vocLeeFareEx").text(gf_AmtFormat(Math.round(parseFloat(removeComma(benefits)) * parseFloat(v_USD))));
			$("#vocLeeFareDtl").val(data.benefitsDtl);
			// 접대비
			var hostAmt = data.hostAmt;
			if(hostAmt == undefined || hostAmt == "") hostAmt = 0;
			$("#hostFare").val(hostAmt);
			$("#hostFareEx").text(gf_AmtFormat(Math.round(parseFloat(removeComma(hostAmt)) * parseFloat(v_USD))));
			$("#hostFareDtl").val(data.hostAmtDtl);
			// 기타경비 송금예정일
			$("#etcPayDate").val(data.etcPayDate);

			// 비고
			$("#rem").val(data.rtcReq);
			//협조내역
			data.assistComment = data.assistComment.replace("##", "\n");
			$("#assistComment").val(data.assistComment);

	  		// SEC팀 여부
	  		if(!(data.secYn == "undefined" || data.secYn == undefined)){
	  			v_secYn = data.secYn;
		  		if(v_secYn == "Y"){
		  			$("#secTr").show();
		  		}
	  		}

	  		// GHR팀 여부
	  		if(!(data.ghrYn == "undefined" || data.ghrYn == undefined)){
	  			v_ghrYn = data.ghrYn;
		  		if(v_ghrYn == "Y"){
//		  			$("#ghrTr").show();
		  			if(v_DocSts == "D60"){
//		  				$("#ghrComment").removeAttr("readonly");
		  			}
		  		}
	  		}

	  		$("#secComment").val(ds_SavedDoc.get(0).perchrgRvwOpn1);
	  		$("#ghrComment").val(ds_SavedDoc.get(0).perchrgRvwOpn2);

			// 동행인 영문이름 좌석등급 항공료
			for(var i = 0; i < ds_TripUserList.size(); i++){

				cf_AppendSavedTripUser(ds_TripUserList.get(i));

			}
			// 비자정보 국가 비자유무 비자정보
			for(var i = 0; i < ds_Visa.size(); i++){

				if(ds_Visa.get(i, "visaOwnYn") != ""){
					cf_AppendSavedVisa(ds_Visa.get(i));
					ds_VisaInfoList.add(ds_Visa.get(i));
				}

			}
			// 방문지 국가 도시명 체류기간 주요업무내용
			for(var i = 0; i < ds_Visa.size(); i++){

				if(ds_Visa.get(i, "visaOwnYn") == ""){
					cf_AppendSavedCity(ds_Visa.get(i));
					ds_CityList.add(ds_Visa.get(i));
				}

			}

			cf_AppendSavedExpn(ds_InputAmt.getAllData());

			// 출장기간 계산
			f_calcTripDate();

			// 수정불가 처리
			$("input").attr("readonly", "readonly");
			$("textarea").attr("readonly", "readonly");
			$("select").attr("disabled", "disabled");


			//저장된 결재선 불러옴
			cf_RetrieveSignInfo();

//			if(data.signln == undefined || data.signln == "" || data.signln == null){
//				// 저장된 결재선이 존재하지 않으므로 다시 결재선을 조회하여 셋팅한다.
//				cf_retrieveSignLine();
//			}else{
//				// 결재선 셋팅
//				ds_Signln.setAllData(JSON.parse(data.signln));
//				gf_AssembleSignln(ds_Signln);
//			}

			f_calcAirAmount();


	  		break;
	  	case "SELECT_ERR_MSG" :
	  		if(resultData.output1 != undefined){
	  			if(resultData.output1[0].errItem != ""){
	  				if(resultData.output1[0].errItem != null){
	  					v_ErrMsg = resultData.output1[0].errItem;
	  				}
	  			}
	  		}

	  		if(v_ErrMsg != ""){
	  			$("#errMsgTr").show();
	  			$("#errMsg").text(v_ErrMsg);
	  		}
	  		break;
	  	default :
	  		break;
	  }
}




function f_callBackFuncBdgtSelect(obj){

	v_BdgtType = obj.bdgtType;



	switch(v_BdgtType) {
  		case "A" :
  			$("#bdgtType").text("A. 특정경비-임원");
  		    // 결과 셋팅
  			v_Aufnr = obj.bdgtData.Aufnr;
  			v_KText = obj.bdgtData.Ktext;
  			AufnrText = obj.bdgtData.Aufnr + " / 내역 : " + obj.bdgtData.Ktext;
  			$("#Aufnr").text(AufnrText);

  			v_Kostv = obj.bdgtData.Kostv;
  			v_Kostvnm = obj.bdgtData.Kostvnm;
  			v_Chief = obj.bdgtData.Chief; // 집행팀장ID
  			v_Chiefnm = obj.bdgtData.Chiefnm; // 집행팀장 이름
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);

  			// I 일반경비 일 경우 경비 이체코드 입력창을 보인다.
  			$("#VTCodeSpan").hide();
  			break;
  		case "B" :
  			$("#bdgtType").text("B. 특정경비-팀장");
  			// 결과 셋팅
  			v_Aufnr = obj.bdgtData.Aufnr;
  			v_KText = obj.bdgtData.Ktext;
  			AufnrText = obj.bdgtData.Aufnr + " / 내역 : " + obj.bdgtData.Ktext;
  			$("#Aufnr").text(AufnrText);

  			v_Kostv = obj.bdgtData.Kostv;
  			v_Kostvnm = obj.bdgtData.Kostvnm;
  			v_Chief = obj.bdgtData.Chief; // 집행팀장ID
  			v_Chiefnm = obj.bdgtData.Chiefnm; // 집행팀장 이름
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);
  			// I 일반경비 일 경우 경비 이체코드 입력창을 보인다.
  			$("#VTCodeSpan").hide();
  			break;
  		case "C" :
  			$("#bdgtType").text("C. 특정경비-팀");
  			// 결과 셋팅
  			v_Aufnr = obj.bdgtData.Kostv;
  			v_KText = obj.bdgtData.Kostvnm;
  			AufnrText = obj.bdgtData.Kostv + " / 내역 : " + obj.bdgtData.Kostvnm;
  			$("#Aufnr").text(AufnrText);

  			v_Kostv = obj.bdgtData.Kostv;
  			v_Kostvnm = obj.bdgtData.Kostvnm;
  			v_Chief = obj.bdgtData.Chief; // 집행팀장ID
  			v_Chiefnm = obj.bdgtData.Chiefnm; // 집행팀장 이름
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);

  		// I 일반경비 일 경우 경비 이체코드 입력창을 보인다.
  			$("#VTCodeSpan").hide();
  			break;
  		case "I" :
  			$("#bdgtType").text("I. 일반경비");
  			// 결과 셋팅
  			v_Aufnr = obj.bdgtData.Aufnr;
  			v_KText = obj.bdgtData.Ktext;
  			AufnrText = obj.bdgtData.Aufnr + " / 내역 : " + obj.bdgtData.Ktext;
  			$("#Aufnr").text(AufnrText);

  			v_Kostv = obj.bdgtData.Kostv;
  			v_Kostvnm = obj.bdgtData.Kostvnm;
  			v_Chief = obj.bdgtData.Chief; // 집행팀장ID
  			v_Chiefnm = obj.bdgtData.Chiefnm; // 집행팀장 이름
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);

  			// I 일반경비 일 경우 경비 이체코드 입력창을 보인다.
  			$("#VTCodeSpan").show();
  			break;
  		case "O" :
  			$("#bdgtType").text("O. 입찰경비");
  			// 결과 셋팅
  			v_Aufnr = obj.bdgtData.Aufnr;
  			v_KText = obj.bdgtData.Ktext;
  			AufnrText = obj.bdgtData.Aufnr + " / 내역 : " + obj.bdgtData.Ktext;
  			$("#Aufnr").text(AufnrText);

  			v_Kostv = obj.bdgtData.Kostv;
  			v_Kostvnm = obj.bdgtData.Kostvnm;
  			v_Chief = obj.bdgtData.Chief; // 집행팀장ID
  			v_Chiefnm = obj.bdgtData.Chiefnm; // 집행팀장 이름
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);

  			// I 일반경비 일 경우 경비 이체코드 입력창을 보인다.
  			$("#VTCodeSpan").hide();
  			break;
  		case "P" :
  			$("#bdgtType").text("P. 사업경비");
  			// 결과 셋팅
  			v_Aufnr = obj.bdgtData.Orderno;
  			v_KText = obj.bdgtData.Ktext;
  			AufnrText = obj.bdgtData.Orderno + " / 내역 : " + obj.bdgtData.Ktext;
  			$("#Aufnr").text(AufnrText);

  			v_Kostv = obj.bdgtData.Kostv;
  			v_Kostvnm = obj.bdgtData.Kostvnm;
  			v_Chief = obj.bdgtData.Chief; // 집행팀장ID
  			v_Chiefnm = obj.bdgtData.Chiefnm; // 집행팀장 이름
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);

  			// I 일반경비 일 경우 경비 이체코드 입력창을 보인다.
  			$("#VTCodeSpan").hide();
  			break;
  		case "R" :
  			$("#bdgtType").text("R. 기술연구원경비");
  			// 결과 셋팅
  			v_Aufnr = obj.bdgtData.Aufnr;
  			v_KText = obj.bdgtData.Ktext;
  			AufnrText = obj.bdgtData.Aufnr + " / 내역 : " + obj.bdgtData.Ktext;
  			$("#Aufnr").text(AufnrText);

  			v_Kostv = obj.bdgtData.Kostv;
  			v_Kostvnm = obj.bdgtData.Kostvnm;
  			v_Chief = obj.bdgtData.Chief; // 집행팀장ID
  			v_Chiefnm = obj.bdgtData.Chiefnm; // 집행팀장 이름
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);

  			// I 일반경비 일 경우 경비 이체코드 입력창을 보인다.
  			$("#VTCodeSpan").hide();
  			break;
  		case "Q" :
  			$("#bdgtType").text("Q. 현장경비");
  			// 결과 셋팅
  			v_Aufnr = obj.bdgtData.Sitprt;
  			v_KText = obj.bdgtData.Ktext;
  			AufnrText = obj.bdgtData.Sitprt + " / 내역 : " + obj.bdgtData.Ktext;
  			$("#Aufnr").text(AufnrText);

  			v_Kostv = obj.bdgtData.Sitprt;
  			v_Kostvnm = obj.bdgtData.Ktext;
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);

  			// I 일반경비 일 경우 경비 이체코드 입력창을 보인다.
  			$("#VTCodeSpan").hide();
  			break;
  		case "K" :
  			$("#bdgtType").text("K. 본사집행현장원가");
  			// 결과 셋팅
  			v_Aufnr = obj.bdgtData.Aufnr;
  			v_KText = obj.bdgtData.Ktext;
  			AufnrText = obj.bdgtData.Aufnr + " / 내역 : " + obj.bdgtData.Ktext;
  			$("#Aufnr").text(AufnrText);

  			v_Kostv = obj.bdgtData.Kostv;
  			v_Kostvnm = obj.bdgtData.Kostvnm;
  			v_Chief = obj.bdgtData.SabunSo; // 집행팀장ID
  			v_Chiefnm = obj.bdgtData.Sojang; // 집행팀장 이름
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);

  			// I 일반경비 일 경우 경비 이체코드 입력창을 보인다.
  			$("#VTCodeSpan").hide();
  			break;
  		case "S" :
  			$("#bdgtType").text("S. 사업경비-현장코드");
  			// 결과 셋팅
  			v_Aufnr = obj.bdgtData.Aufnr;
  			v_KText = obj.bdgtData.Ktext;
  			AufnrText = obj.bdgtData.Aufnr + " / 내역 : " + obj.bdgtData.Ktext;
  			$("#Aufnr").text(AufnrText);

  			v_Kostv = obj.bdgtData.Kostv;
  			v_Kostvnm = obj.bdgtData.Kostvnm;
  			v_Chief = obj.bdgtData.SabunSo; // 집행팀장ID
  			v_Chiefnm = obj.bdgtData.Sojang; // 집행팀장 이름
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);

  			// I 일반경비 일 경우 경비 이체코드 입력창을 보인다.
  			$("#VTCodeSpan").hide();
  			break;

  		default :
	  		break;
	}


//	<select id="bdgtType" style="width: 250px">
//		<option value="A">특정경비-임원</option>
//		<option value="B">특정경비-팀장</option>
//		<option value="C">특정경비-팀</option>
//		<option value="I">일반경비</option>
//		<option value="O">입찰경비</option>
//		<option value="P">사업경비</option>
//		<option value="R">기술연구원경비</option>
//		<option value="Q">현장경비</option>
//		<option value="K">본사집행현장원가</option>
//	</select>

}

//function f_callBackFuncInputAmt(obj){
//
////	usNight
////	usDay
////	edu
////	spot
////	euNight
////	euDay
////	enNight
////	enDay
////	jaNight
////	jaDay
//
//	if(obj.usNight != undefined || obj.usNight == null || obj.usNight != ""){
//		var id = "usNight";
//		$("#" + id + "Tr").show();
//		$("#" + id).text(obj.usNight);
//	}
//	if(obj.usDay != undefined || obj.usDay == null || obj.usDay != ""){
//		var id = "usDay";
//		$("#" + id + "Tr").show();
//		$("#" + id).text(obj.usDay);
//	}
//	if(obj.edu != undefined || obj.edu == null || obj.edu != ""){
//		var id = "edu";
//		$("#" + id + "Tr").show();
//		$("#" + id).text(obj.edu);
//	}
//	if(obj.spot != undefined || obj.spot == null || obj.spot != ""){
//		var id = "spot";
//		$("#" + id + "Tr").show();
//		$("#" + id).text(obj.spot);
//	}
//	if(obj.euNight != undefined || obj.euNight == null || obj.euNight != ""){
//		var id = "euNight";
//		$("#" + id + "Tr").show();
//		$("#" + id).text(obj.euNight);
//	}
//	if(obj.euDay != undefined || obj.euDay == null || obj.euDay != ""){
//		var id = "euDay";
//		$("#" + id + "Tr").show();
//		$("#" + id).text(obj.euDay);
//	}
//	if(obj.enNight != undefined || obj.enNight == null || obj.enNight != ""){
//		var id = "enNight";
//		$("#" + id + "Tr").show();
//		$("#" + id).text(obj.enNight);
//	}
//	if(obj.enDay != undefined || obj.enDay == null || obj.enDay != ""){
//		var id = "enDay";
//		$("#" + id + "Tr").show();
//		$("#" + id).text(obj.enDay);
//	}
//	if(obj.jaNight != undefined || obj.jaNight == null || obj.jaNight != ""){
//		var id = "jaNight";
//		$("#" + id + "Tr").show();
//		$("#" + id).text(obj.jaNight);
//	}
//	if(obj.jaDay != undefined || obj.jaDay == null || obj.jaDay != ""){
//		var id = "jaDay";
//		$("#" + id + "Tr").show();
//		$("#" + id).text(obj.jaDay);
//	}
//
//}


function f_callBackFuncVisaAddP(obj){

//	var callbackObj = {
//			natCd : v_NatCd,
//			natNm : v_NatNm,
//			visaYn : $("#visaYn").val(),
//			visaYnDtl : $("#visaYn option:selected").text(),
//			visaStartDate : $("input[name='visaStartDate']").val(),
//			visaEndDate : $("input[name='visaEndDate']").val(),
//			dsIndex : v_DsIndex
//	};


	if(obj.dsIndex < 0){

		// 새로운 비자정보
		var visaInfo = {
				natCd : obj.natCd,
				visaOwnYn : obj.visaYn,
				visaIssueYmd : $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", obj.visaStartDate)),
				visaExprtnYmd : $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", obj.visaEndDate))
		};

		ds_VisaInfoList.add(visaInfo);

		// 새로운 Tr Append
		$("#visaInfoDetail").append(v_visaInfoDetailTr);
		$("#totalVisaInfo").text($("#visaInfoDetail tr").size()-1);

		var idFlag = "vData";
		var indexF = "" + $("#visaInfoDetail tr:last").index();

		idFlag = idFlag + indexF;
		$("#visaInfoDetail tr:last").attr("id", idFlag);

		$("#visaInfoDetail tr:last input[name='vNat']").val(obj.natNm);
		$("#visaInfoDetail tr:last input[name='vVisaYn']").val(obj.visaYnDtl);
		$("#visaInfoDetail tr:last input[name='vStartDate']").val(obj.visaStartDate);
		$("#visaInfoDetail tr:last input[name='vEndDate']").val(obj.visaEndDate);

		// readonly 처리
		$("#visaInfoDetail tr input[name='vNat']").attr("readonly", "true");
		$("#visaInfoDetail tr input[name='vVisaYn']").attr("readonly", "true");
		$("#visaInfoDetail tr input[name='vStartDate']").attr("readonly", "true");
		$("#visaInfoDetail tr input[name='vEndDate']").attr("readonly", "true");

		// 클릭시 수정 팝업 이벤트 Rebind
		// event rebind
		$("input[name='vNat']").unbind("click");
		$("input[name='vNat']").click(function(e){
			cf_OpenVisaInfoPop(e);
		});
		// event rebind
		$("input[name='vVisaYn']").unbind("click");
		$("input[name='vVisaYn']").click(function(e){
			cf_OpenVisaInfoPop(e);
		});
		// event rebind
		$("input[name='vStartDate']").unbind("click");
		$("input[name='vStartDate']").click(function(e){
			cf_OpenVisaInfoPop(e);
		});
		// event rebind
		$("input[name='vEndDate']").unbind("click");
		$("input[name='vEndDate']").click(function(e){
			cf_OpenVisaInfoPop(e);
		});




	}else{

		// 이미 존재하는 정보 수정
		var index = obj.dsIndex;
		ds_VisaInfoList.set(index, "natCd", obj.natCd);
		ds_VisaInfoList.set(index, "visaOwnYn", obj.visaYn);
		ds_VisaInfoList.set(index, "visaIssueYmd", $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", obj.visaStartDate)));
		ds_VisaInfoList.set(index, "visaExprtnYmd", $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", obj.visaEndDate)));

		$("#" + obj.targetId + " input[name='vNat']").val(obj.natNm);
		$("#" + obj.targetId + " input[name='vVisaYn']").val(obj.visaYnDtl);
		$("#" + obj.targetId + " input[name='vStartDate']").val(obj.visaStartDate);
		$("#" + obj.targetId + " input[name='vEndDate']").val(obj.visaEndDate);

	}

}

function f_callBackFuncCityAddP(obj){


	if(obj.dsIndex < 0){

		// 새로운 비자정보
		var cityInfo = {
				natCd : obj.natCd,
				cityNm : obj.cityNm,
				visaOwnYn : '',
				stayStYmd : $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", obj.stayStYmd)),
				stayEdYmd : $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", obj.stayEdYmd)),
				dutyCont : obj.dutyCont
		};


		ds_CityList.add(cityInfo);

		// 새로운 Tr Append
		$("#cityDetail").append(v_cityDetailTr);
		$("#totalCityInfo").text($("#cityDetail tr").size()-1);

		var idFlag = "cData";
		var indexF = "" + $("#cityDetail tr:last").index();

		idFlag = idFlag + indexF;
		$("#cityDetail tr:last").attr("id", idFlag);

		$("#cityDetail tr:last input[name='natNm']").val(obj.natNm);
		$("#cityDetail tr:last input[name='cityNm']").val(obj.cityNm);
		$("#cityDetail tr:last input[name='stayStYmd']").val(obj.stayStYmd);
		$("#cityDetail tr:last input[name='stayEdYmd']").val(obj.stayEdYmd);
		$("#cityDetail tr:last textarea[name='dutyCont']").val(obj.dutyCont);

		// readonly 처리
		$("#cityDetail tr input[name='natNm']").attr("readonly", "true");
		$("#cityDetail tr input[name='cityNm']").attr("readonly", "true");
		$("#cityDetail tr input[name='stayStYmd']").attr("readonly", "true");
		$("#cityDetail tr input[name='stayEdYmd']").attr("readonly", "true");
		$("#cityDetail tr textarea[name='dutyCont']").attr("readonly", "true");

		// 클릭시 수정 팝업 이벤트 Rebind
		// event rebind
		$("input[name='natNm']").unbind("click");
		$("input[name='natNm']").click(function(e){
			cf_OpenCityInfoPop(e);
		});
		// event rebind
		$("input[name='cityNm']").unbind("click");
		$("input[name='cityNm']").click(function(e){
			cf_OpenCityInfoPop(e);
		});
		// event rebind
		$("input[name='stayStYmd']").unbind("click");
		$("input[name='stayStYmd']").click(function(e){
			cf_OpenCityInfoPop(e);
		});
		// event rebind
		$("input[name='stayEdYmd']").unbind("click");
		$("input[name='stayEdYmd']").click(function(e){
			cf_OpenCityInfoPop(e);
		});
		// event rebind
		$("textarea[name='dutyCont']").unbind("click");
		$("textarea[name='dutyCont']").click(function(e){
			cf_OpenCityInfoPop(e);
		});



	}else{

		// 이미 존재하는 정보 수정
		var index = obj.dsIndex;
		ds_CityList.set(index, "natCd", obj.natCd);
		ds_CityList.set(index, "cityNm", obj.cityNm);
		ds_CityList.set(index, "visaOwnYn", "");
		ds_CityList.set(index, "stayStYmd", $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", obj.stayStYmd)));
		ds_CityList.set(index, "stayEdYmd", $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", obj.stayEdYmd)));
		ds_CityList.set(index, "dutyCont", obj.dutyCont);

		$("#" + obj.targetId + " input[name='natNm']").val(obj.natNm);
		$("#" + obj.targetId + " input[name='cityNm']").val(obj.cityNm);
		$("#" + obj.targetId + " input[name='stayStYmd']").val(obj.stayStYmd);
		$("#" + obj.targetId + " input[name='stayEdYmd']").val(obj.stayEdYmd);
		$("#" + obj.targetId + " textarea[name='dutyCont']").val(obj.dutyCont);

	}

}


function cf_AppendTripUser(){

	if($("#tripUserDetail tr").size() < 4){
		$("#tripUserDetail").append(v_tripUserDetailTr);

		// last 에 ID 부여
		var idFlag = "tData";
		var indexF = "" + $("#tripUserDetail tr:last").index();

		idFlag = idFlag + indexF;
		$("#tripUserDetail tr:last").attr("id", idFlag);

		// 총인원 표시
		$("#totalUser").text($("#tripUserDetail tr").size()-1);

		$("input[name='tAirAmount']").unbind("keyup");
		$("input[name='tAirAmount']").bind('keyup', function(e) {
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

			// 출장자 항공료도 합친다.
			var tUserVal = $("#tripUserAirAmount").val();

			for(var j = 0; j < tUserVal.length; j++){
				tUserVal = tUserVal.replace(",", "");
			}

			total = total + parseInt(tUserVal);

			v_AirTotalAmt = total;
			$("#airTotalAmount").text(gf_AmtFormat(total));

			var targetVal = e.currentTarget.value;
			for(var k = 0; k < targetVal.length; k++){
				targetVal = targetVal.replace(",", "");
			}
			e.currentTarget.value = gf_AmtFormat(targetVal);

			// 개인지급 합계 (채재바 + 항공료)
			v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;

		});
	}else{
		gf_AlertMsg("더이상 추가할 수 없습니다");
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

function cf_OpenVisaInfoPop(e){

	var targetId = $(e.target).parent().parent().attr("id");

	// 국가코드를 구한다.
	var natCd = cf_GetNatCd($("#" + targetId + " input[name='vNat']").val());

	// DataSet 의 Index 를 구한다.
	var dsIndex = ds_VisaInfoList.find("natCd", natCd);

	var visaParams = {
		ds_NatList : ds_NatList.getAllData(),
		targetId : targetId,
		dsIndex : dsIndex,
		natCd : ds_VisaInfoList.get(dsIndex, "natCd"),
		natNm : $("#" + targetId + " input[name='vNat']").val(),
		visaYn : ds_VisaInfoList.get(dsIndex, "visaOwnYn"),
		visaStartDate : $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", ds_VisaInfoList.get(dsIndex, "visaIssueYmd"))),
		visaEndDate : $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", ds_VisaInfoList.get(dsIndex, "visaExprtnYmd")))
	};


	gf_PostOpen("/trip/outerTrip/visaInfoAddP.jsp", null,
			"toolbar=no,scrollbars=yes,width=620,height=483", visaParams,
			true, true, "f_callBackFuncVisaAddP");

}



function cf_OpenCityInfoPop(e){

	var targetId = $(e.target).parent().parent().attr("id");

	// 국가코드를 구한다.
	var natCd = cf_GetNatCd($("#" + targetId + " input[name='natNm']").val());

	// DataSet 의 Index 를 구한다.
	var dsIndex = ds_CityList.find("natCd", natCd);

	var cityParams = {
		ds_NatList : ds_NatList.getAllData(),
		targetId : targetId,
		dsIndex : dsIndex,
		natCd : ds_CityList.get(dsIndex, "natCd"),
		natNm : $("#" + targetId + " input[name='natNm']").val(),
		cityNm : ds_CityList.get(dsIndex, "cityNm"),
		stayStYmd : $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", ds_CityList.get(dsIndex, "stayStYmd"))),
		stayEdYmd : $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", ds_CityList.get(dsIndex, "stayEdYmd"))),
		dutyCont : ds_CityList.get(dsIndex, "dutyCont")
	};


	gf_PostOpen("/trip/outerTrip/cityInfoAddP.jsp", null,
			"toolbar=no,scrollbars=yes,width=620,height=560", cityParams,
			true, true, "f_callBackFuncCityAddP");

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

// 체제비 Callback
function f_callBackFuncInputAmt(obj){

//	obj.result[5]
//	Object {id: "euDay", chk: true, , value, rowStatus: "NORMAL"}
	var result = obj.result;

	var edtTxtVal = "";

	v_cTotalAmt = 0;

	v_cTotUSD = 0;
	v_cTotEUR = 0;
	v_cTotGBP = 0;
	v_cTotJPY = 0;

	// DataSet 초기화
	ds_ExpnList.clear();


//	expn_cls, 비용구분   --> 구분코드
//	accomo_cls, 숙박구분 --> 구분코드(한글)
//	accomo_expn,  숙박비용 --> KRW
//	accomo_expn_dcnt, 숙박비용일수 --> 사용일수
//	dd_expn, 일비용 --> 기준액 * 일
//	dd_expn_dcnt, 일비용일수 --> 기준액



	for(var i = 0; i < result.length; i++){
		if(result[i].chk == true){
			var idTr = "#" + result[i].id + "Tr";
			$(idTr).show();
			//숙박비(＄) 	유럽숙박비(€)	영국숙박비(￡)	일본숙박비(￥)

			if(result[i].id == "usNight"){
				edtTxtVal = parseInt(result[i].val) + "일 /"
				  		+ " $" + (checkRefVal(gv_userPositCd, "일반숙박") * parseInt(result[i].val))
				  		+ " / ￦" + Math.round(v_USD * (checkRefVal(gv_userPositCd, "일반숙박") * parseInt(result[i].val)));

				v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (checkRefVal(gv_userPositCd, "일반숙박") * parseInt(result[i].val))));

				v_cTotUSD = v_cTotUSD + (checkRefVal(gv_userPositCd, "일반숙박") * parseInt(result[i].val));

				var obj = new Object();
				obj.expnCls = result[i].id; // 구분코드
				obj.accomoCls = "일반숙박"; // 구분코드(한글)
				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "일반숙박") * parseInt(result[i].val))); // KRW
				obj.accomoExpnDcnt = result[i].val; // 사용일수
				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "일반숙박"); // 기준액
				obj.ddExpn = (checkRefVal(gv_userPositCd, "일반숙박") * parseInt(result[i].val)); // 기준액 * 일수

				ds_ExpnList.add(obj);


			}else if(result[i].id == "usDay"){
				edtTxtVal = parseInt(result[i].val) + "일 /"
		  		+ " $" + (checkRefVal(gv_userPositCd, "일반일당") * parseInt(result[i].val))
		  		+ " / ￦" + Math.round(v_USD * (checkRefVal(gv_userPositCd, "일반일당") * parseInt(result[i].val)));

				v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (checkRefVal(gv_userPositCd, "일반일당") * parseInt(result[i].val))));

				v_cTotUSD = v_cTotUSD + (checkRefVal(gv_userPositCd, "일반일당") * parseInt(result[i].val));

				var obj = new Object();
				obj.expnCls = result[i].id; // 구분코드
				obj.accomoCls = "일반일당"; // 구분코드(한글)
				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "일반일당") * parseInt(result[i].val))); // KRW
				obj.accomoExpnDcnt = result[i].val; // 사용일수
				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "일반일당"); // 기준액
				obj.ddExpn = (checkRefVal(gv_userPositCd, "일반일당") * parseInt(result[i].val)); // 기준액 * 일수

				ds_ExpnList.add(obj);


			}else if(result[i].id == "edu"){
				edtTxtVal = parseInt(result[i].val) + "일 /"
		  		+ " $" + (checkRefVal(gv_userPositCd, "연수경비(숙)") * parseInt(result[i].val))
		  		+ " / ￦" + Math.round(v_USD * (checkRefVal(gv_userPositCd, "연수경비(숙)") * parseInt(result[i].val)));

				v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (checkRefVal(gv_userPositCd, "연수경비(숙)") * parseInt(result[i].val))));

				v_cTotUSD = v_cTotUSD + (checkRefVal(gv_userPositCd, "연수경비(숙)") * parseInt(result[i].val));

				var obj = new Object();
				obj.expnCls = result[i].id; // 구분코드
				obj.accomoCls = "연수경비(숙)"; // 구분코드(한글)
				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "연수경비(숙)") * parseInt(result[i].val))); // KRW
				obj.accomoExpnDcnt = result[i].val; // 사용일수
				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "연수경비(숙)"); // 기준액
				obj.ddExpn = (checkRefVal(gv_userPositCd, "연수경비(숙)") * parseInt(result[i].val)); // 기준액 * 일수

				ds_ExpnList.add(obj);


			}else if(result[i].id == "spot"){
				edtTxtVal = parseInt(result[i].val) + "일 /"
		  		+ " $" + (checkRefVal(gv_userPositCd, "연수경비(숙식)") * parseInt(result[i].val))
		  		+ " / ￦" + Math.round(v_USD * (checkRefVal(gv_userPositCd, "연수경비(숙식)") * parseInt(result[i].val)));

				v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (checkRefVal(gv_userPositCd, "연수경비(숙식)") * parseInt(result[i].val))));

				v_cTotUSD = v_cTotUSD + (checkRefVal(gv_userPositCd, "연수경비(숙식)") * parseInt(result[i].val));

				var obj = new Object();
				obj.expnCls = result[i].id; // 구분코드
				obj.accomoCls = "연수경비(숙식)"; // 구분코드(한글)
				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "연수경비(숙식)") * parseInt(result[i].val))); // KRW
				obj.accomoExpnDcnt = result[i].val; // 사용일수
				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "연수경비(숙식)"); // 기준액
				obj.ddExpn = (checkRefVal(gv_userPositCd, "연수경비(숙식)") * parseInt(result[i].val)); // 기준액 * 일수

				ds_ExpnList.add(obj);


			}else if(result[i].id == "euNight"){
				edtTxtVal = parseInt(result[i].val) + "일 /"
		  		+ " €" + (checkRefVal(gv_userPositCd, "유럽숙박") * parseInt(result[i].val))
		  		+ " / ￦" + Math.round(v_EUR * (checkRefVal(gv_userPositCd, "유럽숙박") * parseInt(result[i].val)));

				v_cTotalAmt = v_cTotalAmt + (Math.round(v_EUR * (checkRefVal(gv_userPositCd, "유럽숙박") * parseInt(result[i].val))));

				v_cTotEUR = v_cTotEUR + (checkRefVal(gv_userPositCd, "유럽숙박") * parseInt(result[i].val));

				var obj = new Object();
				obj.expnCls = result[i].id; // 구분코드
				obj.accomoCls = "유럽숙박"; // 구분코드(한글)
				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "유럽숙박") * parseInt(result[i].val))); // KRW
				obj.accomoExpnDcnt = result[i].val; // 사용일수
				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "유럽숙박"); // 기준액
				obj.ddExpn = (checkRefVal(gv_userPositCd, "유럽숙박") * parseInt(result[i].val)); // 기준액 * 일수

				ds_ExpnList.add(obj);

			}else if(result[i].id == "euDay"){
				edtTxtVal = parseInt(result[i].val) + "일 /"
		  		+ " €" + (checkRefVal(gv_userPositCd, "유럽일당") * parseInt(result[i].val))
		  		+ " / ￦" + Math.round(v_EUR * (checkRefVal(gv_userPositCd, "유럽일당") * parseInt(result[i].val)));

				v_cTotalAmt = v_cTotalAmt + (Math.round(v_EUR * (checkRefVal(gv_userPositCd, "유럽일당") * parseInt(result[i].val))));

				v_cTotEUR = v_cTotEUR + (checkRefVal(gv_userPositCd, "유럽일당") * parseInt(result[i].val));

				var obj = new Object();
				obj.expnCls = result[i].id; // 구분코드
				obj.accomoCls = "유럽일당"; // 구분코드(한글)
				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "유럽일당") * parseInt(result[i].val))); // KRW
				obj.accomoExpnDcnt = result[i].val; // 사용일수
				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "유럽일당"); // 기준액
				obj.ddExpn = (checkRefVal(gv_userPositCd, "유럽일당") * parseInt(result[i].val)); // 기준액 * 일수

				ds_ExpnList.add(obj);

			}else if(result[i].id == "enNight"){
				edtTxtVal = parseInt(result[i].val) + "일 /"
		  		+ " ￡" + (checkRefVal(gv_userPositCd, "영국숙박") * parseInt(result[i].val))
		  		+ " / ￦" + Math.round(v_GBP * (checkRefVal(gv_userPositCd, "영국숙박") * parseInt(result[i].val)));

				v_cTotalAmt = v_cTotalAmt + (Math.round(v_GBP * (checkRefVal(gv_userPositCd, "영국숙박") * parseInt(result[i].val))));

				v_cTotGBP = v_cTotGBP + (checkRefVal(gv_userPositCd, "영국숙박") * parseInt(result[i].val));

				var obj = new Object();
				obj.expnCls = result[i].id; // 구분코드
				obj.accomoCls = "영국숙박"; // 구분코드(한글)
				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "영국숙박") * parseInt(result[i].val))); // KRW
				obj.accomoExpnDcnt = result[i].val; // 사용일수
				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "영국숙박"); // 기준액
				obj.ddExpn = (checkRefVal(gv_userPositCd, "영국숙박") * parseInt(result[i].val)); // 기준액 * 일수

				ds_ExpnList.add(obj);

			}else if(result[i].id == "enDay"){
				edtTxtVal = parseInt(result[i].val) + "일 /"
		  		+ " ￡" + (checkRefVal(gv_userPositCd, "영국일당") * parseInt(result[i].val))
		  		+ " / ￦" + Math.round(v_GBP * (checkRefVal(gv_userPositCd, "영국일당") * parseInt(result[i].val)));

				v_cTotalAmt = v_cTotalAmt + (Math.round(v_GBP * (checkRefVal(gv_userPositCd, "영국일당") * parseInt(result[i].val))));

				v_cTotGBP = v_cTotGBP + (checkRefVal(gv_userPositCd, "영국일당") * parseInt(result[i].val));

				var obj = new Object();
				obj.expnCls = result[i].id; // 구분코드
				obj.accomoCls = "영국일당"; // 구분코드(한글)
				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "영국일당") * parseInt(result[i].val))); // KRW
				obj.accomoExpnDcnt = result[i].val; // 사용일수
				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "영국일당"); // 기준액
				obj.ddExpn = (checkRefVal(gv_userPositCd, "영국일당") * parseInt(result[i].val)); // 기준액 * 일수

				ds_ExpnList.add(obj);

			}else if(result[i].id == "jaNight"){
				edtTxtVal = parseInt(result[i].val) + "일 /"
		  		+ " ￥" + (checkRefVal(gv_userPositCd, "일본숙박") * parseInt(result[i].val))
		  		+ " / ￦" + Math.round(v_JPY * (checkRefVal(gv_userPositCd, "일본숙박") * parseInt(result[i].val)));

				v_cTotalAmt = v_cTotalAmt + (Math.round(v_JPY * (checkRefVal(gv_userPositCd, "일본숙박") * parseInt(result[i].val))));

				v_cTotJPY = v_cTotJPY + (checkRefVal(gv_userPositCd, "일본숙박") * parseInt(result[i].val));

				var obj = new Object();
				obj.expnCls = result[i].id; // 구분코드
				obj.accomoCls = "일본숙박"; // 구분코드(한글)
				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "일본숙박") * parseInt(result[i].val))); // KRW
				obj.accomoExpnDcnt = result[i].val; // 사용일수
				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "일본숙박"); // 기준액
				obj.ddExpn = (checkRefVal(gv_userPositCd, "일본숙박") * parseInt(result[i].val)); // 기준액 * 일수

				ds_ExpnList.add(obj);

			}else if(result[i].id == "jaDay"){
				edtTxtVal = parseInt(result[i].val) + "일 /"
		  		+ " ￥" + (checkRefVal(gv_userPositCd, "일본일당") * parseInt(result[i].val))
		  		+ " / ￦" + Math.round(v_JPY * (checkRefVal(gv_userPositCd, "일본일당") * parseInt(result[i].val)));

				v_cTotalAmt = v_cTotalAmt + (Math.round(v_JPY * (checkRefVal(gv_userPositCd, "일본일당") * parseInt(result[i].val))));

				v_cTotJPY = v_cTotJPY + (checkRefVal(gv_userPositCd, "일본일당") * parseInt(result[i].val));


				var obj = new Object();
				obj.expnCls = result[i].id; // 구분코드
				obj.accomoCls = "일본일당"; // 구분코드(한글)
				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "일본일당") * parseInt(result[i].val))); // KRW
				obj.accomoExpnDcnt = result[i].val; // 사용일수
				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "일본일당"); // 기준액
				obj.ddExpn = (checkRefVal(gv_userPositCd, "일본일당") * parseInt(result[i].val)); // 기준액 * 일수

				ds_ExpnList.add(obj);

			}

			$("#" + result[i].id).text(edtTxtVal);
		}else{
			var idTr = "#" + result[i].id + "Tr";
			$(idTr).hide();
		}
	}

	$("#usdTotal").text(v_cTotUSD);
	$("#usdTotalKr").text(gf_AmtFormat(Math.round(v_cTotUSD * v_USD)));

	$("#euroTotal").text(v_cTotEUR);
	$("#euroTotalKr").text(gf_AmtFormat(Math.round(v_cTotEUR * v_EUR)));

	$("#gbpTotal").text(v_cTotGBP);
	$("#gbpTotalKr").text(gf_AmtFormat(Math.round(v_cTotGBP * v_GBP)));

	$("#jaTotal").text(v_cTotJPY);
	$("#jaTotalKr").text(gf_AmtFormat(Math.round(v_cTotJPY * v_JPY)));

	$("#cTotalAmt").text(gf_AmtFormat(v_cTotalAmt + totalEtcAmt()));



	// 개인지급 합계 (채재바 + 항공료)
	v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;
	$("#totalAmount").text(gf_AmtFormat(v_TotalAmount));

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

	$("#etcTotalUSD").text(gf_AmtFormat(etcTotal));

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

	var ifParam = {
			bdgtType : v_BdgtType,
			aufnr : v_Aufnr,
			kText : v_KText,
			kostV : v_Kostv,
			kostVNm : v_Kostvnm,
			chief : v_Chief,
			chiefNm : v_Chiefnm,
			cGugun : $("#cGubun").val(),
			userOrgCd : gv_orgCd
	};

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
			svcExpn : removeComma($("#hostFareEx").text()),
			ifParam : JSON.stringify(ifParam),
			bustrGl : $("#tPurp").val(),
			pecul : $("#pecul").val()

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
			ds_Signln : ds_Signln.getAllData("U")
	};

	gf_Transaction("SAVE_OUTER_TRIP_DRAFT", "/trip/outerTrip/saveOuterTripDraft.xpl", draftParams, draftDataSet, "f_Callback", true);
}

function cf_sgnsRemoteDraft(){


	var dutySysCd = "SGNS"; // DUTYSYSCD
	var programCode = "SGNS070003"; // 양식코드
	var signDocTitle = "해외출장신청서"; // 양식 제목
	var drafterId = v_DraftUserId + " " + v_DraftUserKnm; // 작성자 ID
	var drafterOrg = v_DraftUserOrgCd + " " + v_DraftUserOrgNm; // 작성자 ORG

	var bdgtType = $("#bdgtType").text(); // 경비구분

	var execTeam = v_Kostvnm + " " + v_Kostv; // 집행팀
	var docNo = v_DocNo; // CMAS 문서번호
	var ordDate = ""; // 기표일자
	var ordNo = ""; // 전표번호


	// SGNS REMOTE DRAFT
	var sgnsParams = {
			dutySysCd : dutySysCd,
			programCode : programCode,
			signDocTitle : signDocTitle,
			refNo : "",
			drafterId : drafterId,
			drafterOrg : drafterOrg,
			bdgtType : bdgtType,
			aufnrNo : v_Aufnr,
			execTeam : execTeam,
			docNo : docNo,
			ordDate : ordDate,
			ordNo : ordNo,
	};

	var tripUserList = cf_getTripUserTrData();

//	!!

	for(var i = 0; i < tripUserList.length; i++){
		ds_TripUserList.add(tripUserList[i]);
	}


	var draftDataSet = {
			ds_TripUserList : ds_TripUserList.getAllData("U"),
			ds_VisaInfoList : ds_VisaInfoList.getAllData("U"),
			ds_CityList : ds_CityList.getAllData("U"),
			ds_ExpnList : ds_ExpnList.getAllData("U"),
			ds_Signln : ds_Signln.getAllData("U")
	};

	gf_Transaction("SELECT_SGNS_REMOTE_DRAFT", "/trip/outerTrip/saveSgnsRemoteDraft.xpl", sgnsParams, draftDataSet, "f_Callback", true);

}


function cf_AppendSavedTripUser(obj){

	if($("#tripUserDetail tr").size() < 4){
		$("#tripUserDetail").append(v_tripUserDetailTr);

		// last 에 ID 부여
		var idFlag = "tData";
		var indexF = "" + $("#tripUserDetail tr:last").index();

		idFlag = idFlag + indexF;
		$("#tripUserDetail tr:last").attr("id", idFlag);

		$("#tripUserDetail tr:last input[name='tUserEnm']").val(obj.userEnm);
		$("#tripUserDetail tr:last select[name='tGrade']").val(obj.seatGrade);
		$("#tripUserDetail tr:last input[name='tAirAmount']").val(gf_AmtFormat(obj.afare));

		// 총인원 표시
		$("#totalUser").text($("#tripUserDetail tr").size()-1);

		$("input[name='tAirAmount']").unbind("keyup");
		$("input[name='tAirAmount']").bind('keyup', function(e) {
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

			// 출장자 항공료도 합친다.
			var tUserVal = $("#tripUserAirAmount").val();

			for(var j = 0; j < tUserVal.length; j++){
				tUserVal = tUserVal.replace(",", "");
			}

			total = total + parseInt(tUserVal);

			v_AirTotalAmt = total;
			$("#airTotalAmount").text(gf_AmtFormat(total));

			var targetVal = e.currentTarget.value;
			for(var k = 0; k < targetVal.length; k++){
				targetVal = targetVal.replace(",", "");
			}
			e.currentTarget.value = gf_AmtFormat(targetVal);

			// 개인지급 합계 (채재바 + 항공료)
			v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;

		});
	}else{
		gf_AlertMsg("더이상 추가할 수 없습니다");
	}

}

function cf_AppendSavedVisa(obj){

	// 새로운 Tr Append
	$("#visaInfoDetail").append(v_visaInfoDetailTr);
	$("#totalVisaInfo").text($("#visaInfoDetail tr").size()-1);

	var idFlag = "vData";
	var indexF = "" + $("#visaInfoDetail tr:last").index();

	idFlag = idFlag + indexF;
	$("#visaInfoDetail tr:last").attr("id", idFlag);

	$("#visaInfoDetail tr:last input[name='vNat']").val(ds_NatList.get(ds_NatList.find("natCd", obj.natCd), "natNm"));

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

	// readonly 처리
	$("#visaInfoDetail tr input[name='vNat']").attr("readonly", "true");
	$("#visaInfoDetail tr input[name='vVisaYn']").attr("readonly", "true");
	$("#visaInfoDetail tr input[name='vStartDate']").attr("readonly", "true");
	$("#visaInfoDetail tr input[name='vEndDate']").attr("readonly", "true");

	// 클릭시 수정 팝업 이벤트 Rebind
	// event rebind
//	$("input[name='vNat']").unbind("click");
//	$("input[name='vNat']").click(function(e){
//		cf_OpenVisaInfoPop(e);
//	});
//	// event rebind
//	$("input[name='vVisaYn']").unbind("click");
//	$("input[name='vVisaYn']").click(function(e){
//		cf_OpenVisaInfoPop(e);
//	});
//	// event rebind
//	$("input[name='vStartDate']").unbind("click");
//	$("input[name='vStartDate']").click(function(e){
//		cf_OpenVisaInfoPop(e);
//	});
//	// event rebind
//	$("input[name='vEndDate']").unbind("click");
//	$("input[name='vEndDate']").click(function(e){
//		cf_OpenVisaInfoPop(e);
//	});

}

function cf_AppendSavedCity(obj){

	// 새로운 Tr Append
	$("#cityDetail").append(v_cityDetailTr);
	$("#totalCityInfo").text($("#cityDetail tr").size()-1);

	var idFlag = "cData";
	var indexF = "" + $("#cityDetail tr:last").index();

	idFlag = idFlag + indexF;
	$("#cityDetail tr:last").attr("id", idFlag);

	$("#cityDetail tr:last input[name='natNm']").val(ds_NatList.get(ds_NatList.find("natCd", obj.natCd), "natNm"));
	$("#cityDetail tr:last input[name='cityNm']").val(obj.cityNm);
	$("#cityDetail tr:last input[name='stayStYmd']").val($.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", obj.stayStYmd)));
	$("#cityDetail tr:last input[name='stayEdYmd']").val($.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", obj.stayEdYmd)));
	$("#cityDetail tr:last textarea[name='dutyCont']").val(obj.dutyCont);

	// readonly 처리
	$("#cityDetail tr input[name='natNm']").attr("readonly", "true");
	$("#cityDetail tr input[name='cityNm']").attr("readonly", "true");
	$("#cityDetail tr input[name='stayStYmd']").attr("readonly", "true");
	$("#cityDetail tr input[name='stayEdYmd']").attr("readonly", "true");
	$("#cityDetail tr textarea[name='dutyCont']").attr("readonly", "true");

	// 클릭시 수정 팝업 이벤트 Rebind
	// event rebind
//	$("input[name='natNm']").unbind("click");
//	$("input[name='natNm']").click(function(e){
//		cf_OpenCityInfoPop(e);
//	});
//	// event rebind
//	$("input[name='cityNm']").unbind("click");
//	$("input[name='cityNm']").click(function(e){
//		cf_OpenCityInfoPop(e);
//	});
//	// event rebind
//	$("input[name='stayStYmd']").unbind("click");
//	$("input[name='stayStYmd']").click(function(e){
//		cf_OpenCityInfoPop(e);
//	});
//	// event rebind
//	$("input[name='stayEdYmd']").unbind("click");
//	$("input[name='stayEdYmd']").click(function(e){
//		cf_OpenCityInfoPop(e);
//	});
//	// event rebind
//	$("textarea[name='dutyCont']").unbind("click");
//	$("textarea[name='dutyCont']").click(function(e){
//		cf_OpenCityInfoPop(e);
//	});

}


function cf_saveOuterDocSave(docSts){

	//문서번호
	//리스트로부터 옴

	// 작성자 정보
	var draftUserId = v_DraftUserId;
	var draftUserKnm = v_DraftUserKnm;
	var draftUserEnm = v_DraftUserEnm;
	var draftUserOrgCd = v_DraftUserOrgCd;
	var draftUserOrgNm = v_DraftUserOrgNm;
	var draftUserPositCd = v_DraftUserPositCd;
	var draftUserRpswrkCd = v_DraftUserRpswrkCd;

	// 출장자 정보
	var tripUserId = v_TripUserId;
	var tripUserKnm = v_TripUserKnm;
	var tripUserEnm = v_TripUserEnm;
	var tripUserOrgCd = v_TripUserOrgCd;
	var tripUserOrgNm = v_TripUserOrgNm;
	var tripUserPositCd = v_TripUserPositCd;
	var tripUserRpswrkCd = v_TripUserRpswrkCd;

	//좌석 등급
	var tripUserSGrade = $("select[name='tripUserGrade']").val();
	// 출장자 항공료
	var tripUserAmt = $("#tripUserAirAmount").val();
	// 출장 목적
	var tripPurp = $("#tPurp").val();
	// 요청 사항
	var tripReq = $("#tReq").val();
	// 여권 발급일
	var ppStartDt = $("#fPassp").val();
	// 여관 만료일
	var ppEndDt = $("#tPassp").val();

	// 예산구분
	var bdgtType = v_BdgtType;
	// 경비구분
	var cGubun = $("#cGubun").val();
	var aufnr = v_Aufnr; // 예산
	// 예산내역
	var kText = v_KText; //내역
	var kostv = v_Kostv; //집행팀
	var kostvnm = v_Kostvnm; //집행팀 이름
	var chief = v_Chief; // 집행팀장ID
	var chiefnm = v_Chiefnm; // 집행팀장 이름
	// 항공료 요청사항
	var airReq = $("#pecul").val();
	// 현지항공료
	var tAirAmt = $("#loAirFare").val();
	var tAirAmtDtl = $("#loAirFareDtl").val();
	// 현지 교통비
	var tTranspAmt = $("#loTranFare").val();
	var tTranspAmtDtl = $("#loTranFareDtl").val();
	// Visa Fee
	var visaFee = $("#visaFeeFare").val();
	var visaFeeDtl = $("#visaFeeFareDtl").val();
	// Over Charge
	var overCharge = $("#ovCharFare").val();
	var overChargeDtl = $("#ovCharFareDtl").val();
	// 복리후생
	var benefits = $("#vocLeeFare").val();
	var benefitsDtl = $("#vocLeeFareDtl").val();
	// 접대비
	var hostAmt = $("#hostFare").val();
	var hostAmtDtl = $("#hostFareDtl").val();
	// 기타경비 송금예정일
	var etcPayDate = $("#etcPayDate").val();
	// 비고
	var rtcReq = $("#rem").val();
	// 협조내역
	var assistComment = $("#assistComment").val();

	var confirmText = "";
	if(docSts == "D16"){
		confirmText = "작성 중인 문서를 저장하시겠습니까?";
	}else if(docSts == "D50"){
		confirmText = "문서를 협조의뢰 하시겠습니까?";
	}

	if(confirm(confirmText)){

		var data = {
				draftUserId : draftUserId,
				draftUserKnm : draftUserKnm,
				draftUserEnm : draftUserEnm,
				draftUserOrgCd : draftUserOrgCd,
				draftUserOrgNm : draftUserOrgNm,
				draftUserPositCd : draftUserPositCd,
				draftUserRpswrkCd : draftUserRpswrkCd,
				tripUserId : tripUserId,
				tripUserKnm : tripUserKnm,
				tripUserEnm : tripUserEnm,
				tripUserOrgCd : tripUserOrgCd,
				tripUserOrgNm : tripUserOrgNm,
				tripUserPositCd : tripUserPositCd,
				tripUserRpswrkCd : tripUserRpswrkCd,
				tripUserSGrade : tripUserSGrade,
				tripUserAmt : tripUserAmt,
				tripPurp : tripPurp,
				tripReq : tripReq,
				ppStartDt : ppStartDt,
				ppEndDt : ppEndDt,
				bdgtType : bdgtType,
				cGubun : cGubun,
				aufnr : aufnr, // 예산
				kText : kText, //내역
				kostv : kostv, //집행팀
				kostvnm : kostvnm, //집행팀 이름
				chief : chief, // 집행팀장ID
				chiefnm : chiefnm, // 집행팀장 이름
				airReq : airReq,
				tAirAmt : tAirAmt,
				tAirAmtDtl : tAirAmtDtl,
				tTranspAmt : tTranspAmt,
				tTranspAmtDtl : tTranspAmtDtl,
				visaFee : visaFee,
				visaFeeDtl : visaFeeDtl,
				overCharge : overCharge,
				overChargeDtl : overChargeDtl,
				benefits : benefits,
				benefitsDtl : benefitsDtl,
				hostAmt : hostAmt,
				hostAmtDtl : hostAmtDtl,
				etcPayDate : etcPayDate,
				rtcReq : rtcReq,
				assistComment : assistComment
		};


		var param = {
				docNo : v_DocNo,
				docSts : docSts,
				ifParam : JSON.stringify(data)
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
				ds_Signln : ds_Signln.getAllData("U")
		};

		if(v_DocSts == "D16" || v_DocSts == "D50"){
			gf_Transaction("SAVE_OUTER_TRIP_DOC_SAVE", "/trip/outerTrip/updateOuterTripDocSave.xpl", param, draftDataSet, "f_Callback", true);
		}else{
			gf_Transaction("SAVE_OUTER_TRIP_DOC_SAVE", "/trip/outerTrip/saveOuterTripDocSave.xpl", param, draftDataSet, "f_Callback", true);
		}
	}

}

function cf_retrieveSignLine(){


	// 출장자 정보
//	v_TripUserId;
//	v_TripUserOrgCd;
//	v_TripUserPositCd;

	// 복리후생비 + 접대비 계산
	hostCheck = parseInt(f_notSpace(removeComma($("#hostFare").val()))) + parseInt(f_notSpace(removeComma($("#vocLeeFare").val())));
	if(hostCheck > 1400){
			v_bossSignYn = "Y";
			orgCls = "회장";

			// 화면 상단 결재선 셋팅
			// 현장소속의 경우 현장담당책임자를 가져오지않으므로 수동으로 중간협의자로 넣어줘야한다.
			var params = {
		  		orgCd : v_TripUserOrgCd,
		  		orgCls : orgCls,
		  		userId : v_TripUserId
			};

			// 상신시 Modal처리
			gf_InitSpinner(top.$('body'));

			gf_Transaction("SELECT_SIGN_LINE", "/co/common/user/retrieveSignlnForExcluRegl.xpl", params, {}, "f_Callback", true);
	}else{
		// 본부장 이상 직책일 체크
		if(ds_HqRpswrkCd.find("value", v_TripUserRpswrkCd) != -1){
			orgCls = "회장";
		}else{
			orgCls = "본부/실";
		}

		// 화면 상단 결재선 셋팅
		// 현장소속의 경우 현장담당책임자를 가져오지않으므로 수동으로 중간협의자로 넣어줘야한다.
		var params = {
	  		orgCd : v_TripUserOrgCd,
	  		orgCls : orgCls,
	  		userId : v_TripUserId
		};

		gf_Transaction("SELECT_SIGN_LINE", "/co/common/user/retrieveSignlnForExcluRegl.xpl", params, {}, "f_Callback", true);
	}
}


function cf_setSignlnInfo(resultData) {
	ds_Signln.clear();

	var cnt = 0;

	ds_Signln.insert(cnt, {
		signId : "",
		signSeq : cnt + 1,
		signTpCd : "T01",
		signUserId : v_DraftUserId, // 세션에서 받아온 값
		signUserNm : v_DraftUserKnm, // 세션에서 받아온 값
		psignUserNm : "",
		apperPositCd : v_DraftUserPositCd,
		apperPositNm : v_DraftUserPositCd,
		perpsignPositNm : "",
		apperRpswrkCd : "",
		apperRpswrkNm : "",
		apperOrgCd : v_DraftUserOrgCd,
		apperOrgNm : v_DraftUserOrgNm,
		orgChrcCls : "D"
	});

	if(v_dSignType == "Y"){
		cnt++;
		if(v_dSignRpswrkCd == ""){
			ds_Signln.insert(cnt, {
				signId : "",
				signSeq : cnt + 1,
				signTpCd : "T03",
				signUserId : v_dSignUserId, // 세션에서 받아온 값
				signUserNm : v_dSignUserNm, // 세션에서 받아온 값
				psignUserNm : "",
				apperPositCd : v_dSignPositCd,
				apperPositNm : v_dSignPositCd,
				perpsignPositNm : "",
				apperRpswrkCd : v_dSignRpswrkCd,
				apperRpswrkNm : v_dSignRpswrkCd,
				apperOrgCd : v_dSignUserCd,
				apperOrgNm : "",
				orgChrcCls : "D"
			});
		}else{
			ds_Signln.insert(cnt, {
				signId : "",
				signSeq : cnt + 1,
				signTpCd : "T03",
				signUserId : v_dSignUserId, // 세션에서 받아온 값
				signUserNm : v_dSignUserNm, // 세션에서 받아온 값
				psignUserNm : "",
				apperPositCd : v_dSignRpswrkCd,
				apperPositNm : v_dSignRpswrkCd,
				perpsignPositNm : "",
				apperRpswrkCd : v_dSignRpswrkCd,
				apperRpswrkNm : v_dSignRpswrkCd,
				apperOrgCd : v_dSignUserCd,
				apperOrgNm : "",
				orgChrcCls : "D"
			});
		}
	}



	for ( var i = 0; i < resultData.ds_SignlnForExcluRegl.length; i++) {

		// 기안자와 ID가 같다면 결재라인에서 빠진다.
		var temp = resultData.ds_SignlnForExcluRegl[i];
		if(v_DraftUserId != temp.signUserId){

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

	gf_AssembleSignln(ds_Signln);

	$(".ajax_overlay").remove();

}

//function cf_setSignlnInfo(resultData) {
//	ds_Signln.clear();
//
//	var cnt = 0;
//
//	ds_Signln.insert(cnt, {
//		signId : "",
//		signSeq : cnt + 1,
//		signTpCd : "T01",
//		signUserId : v_DraftUserId, // 세션에서 받아온 값
//		signUserNm : v_DraftUserKnm, // 세션에서 받아온 값
//		psignUserNm : "",
//		apperPositCd : v_DraftUserPositCd,
//		apperPositNm : v_DraftUserPositCd,
//		perpsignPositNm : "",
//		apperRpswrkCd : "",
//		apperRpswrkNm : "",
//		apperOrgCd : v_DraftUserOrgCd,
//		apperOrgNm : v_DraftUserOrgNm,
//		orgChrcCls : "D"
//	});
//
//	for ( var i = 0; i < resultData.ds_SignlnForExcluRegl.length; i++) {
//
//		// 기안자와 ID가 같다면 결재라인에서 빠진다.
//		var temp = resultData.ds_SignlnForExcluRegl[i];
//		if(v_DraftUserId != temp.signUserId){
//
//			/**
//			 * 사장전결의 경우 수석부사장은 결재라인에서 빠짐
//			 */
//			if(orgCls == "회장" && temp.apperPositCd == "수석부사장"){
//
//			}else{
//				cnt++;
//
//				ds_Signln.insert(cnt, {
//					signId : "",
//					signSeq : cnt + 1,
//					signTpCd : "T02",
//					signUserId : temp.signUserId, // 세션에서 받아온 값
//					signUserNm : temp.signUserNm, // 세션에서 받아온 값
//					psignUserNm : "",
//					apperPositCd : temp.apperPositCd,
//					apperPositNm : temp.apperPositNm,
//					perpsignPositNm : "",
//					apperRpswrkCd : temp.apperRpswrkCd,
//					apperRpswrkNm : temp.apperRpswrkNm,
//					apperOrgCd : temp.apperOrgCd,
//					apperOrgNm : temp.apperOrgNm,
//					orgChrcCls : "D"
//				});
//			}
//		}
//	}
//
//	gf_AssembleSignln(ds_Signln);
//
//	gf_DisableCurrentOverlay();
//
//	$(".ajax_overlay").remove();
//
//}

function fn_SetUploadCallback( fileAtchId ) {
	cf_saveOuterDocSave(v_DraftSts);

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
	ds_ExpnList.clear();


//	expn_cls, 비용구분   --> 구분코드
//	accomo_cls, 숙박구분 --> 구분코드(한글)
//	accomo_expn,  숙박비용 --> KRW
//	accomo_expn_dcnt, 숙박비용일수 --> 사용일수
//	dd_expn, 일비용 --> 기준액 * 일
//	dd_expn_dcnt, 일비용일수 --> 기준액


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
//				  		+ " $" + gf_AmtFormat((checkRefVal(gv_userPositCd, "일반숙박") * parseInt(result[i].accomoExpnDcnt)))
//				  		+ " / ￦" +gf_AmtFormat( Math.round(v_USD * (checkRefVal(gv_userPositCd, "일반숙박") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (checkRefVal(gv_userPositCd, "일반숙박") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotUSD = v_cTotUSD + (checkRefVal(gv_userPositCd, "일반숙박") * parseInt(result[i].accomoExpnDcnt));
//
//				var obj = new Object();
//				obj.expnCls = result[i].expnCls; // 구분코드
//				obj.accomoCls = "일반숙박"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "일반숙박") * parseInt(result[i].accomoExpnDcnt))); // KRW
//				obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "일반숙박"); // 기준액
//				obj.ddExpn = (checkRefVal(gv_userPositCd, "일반숙박") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//
//			}else if(result[i].expnCls == "usDay"){
//				edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//		  		+ " $" + gf_AmtFormat((checkRefVal(gv_userPositCd, "일반일당") * parseInt(result[i].accomoExpnDcnt)))
//		  		+ " / ￦" + gf_AmtFormat(Math.round(v_USD * (checkRefVal(gv_userPositCd, "일반일당") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (checkRefVal(gv_userPositCd, "일반일당") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotUSD = v_cTotUSD + (checkRefVal(gv_userPositCd, "일반일당") * parseInt(result[i].accomoExpnDcnt));
//
//				var obj = new Object();
//				obj.expnCls = result[i].expnCls; // 구분코드
//				obj.accomoCls = "일반일당"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "일반일당") * parseInt(result[i].accomoExpnDcnt))); // KRW
//				obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "일반일당"); // 기준액
//				obj.ddExpn = (checkRefVal(gv_userPositCd, "일반일당") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//
//			}else if(result[i].expnCls == "edu"){
//				edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//		  		+ " $" + gf_AmtFormat((checkRefVal(gv_userPositCd, "연수경비(숙)") * parseInt(result[i].accomoExpnDcnt)))
//		  		+ " / ￦" + gf_AmtFormat(Math.round(v_USD * (checkRefVal(gv_userPositCd, "연수경비(숙)") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (checkRefVal(gv_userPositCd, "연수경비(숙)") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotUSD = v_cTotUSD + (checkRefVal(gv_userPositCd, "연수경비(숙)") * parseInt(result[i].accomoExpnDcnt));
//
//				var obj = new Object();
//				obj.expnCls = result[i].expnCls; // 구분코드
//				obj.accomoCls = "연수경비(숙)"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "연수경비(숙)") * parseInt(result[i].accomoExpnDcnt))); // KRW
//				obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "연수경비(숙)"); // 기준액
//				obj.ddExpn = (checkRefVal(gv_userPositCd, "연수경비(숙)") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//
//			}else if(result[i].expnCls == "spot"){
//				edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//		  		+ " $" + gf_AmtFormat((checkRefVal(gv_userPositCd, "연수경비(숙식)") * parseInt(result[i].accomoExpnDcnt)))
//		  		+ " / ￦" + gf_AmtFormat(Math.round(v_USD * (checkRefVal(gv_userPositCd, "연수경비(숙식)") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (checkRefVal(gv_userPositCd, "연수경비(숙식)") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotUSD = v_cTotUSD + (checkRefVal(gv_userPositCd, "연수경비(숙식)") * parseInt(result[i].accomoExpnDcnt));
//
//				var obj = new Object();
//				obj.expnCls = result[i].expnCls; // 구분코드
//				obj.accomoCls = "연수경비(숙식)"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "연수경비(숙식)") * parseInt(result[i].accomoExpnDcnt))); // KRW
//				obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "연수경비(숙식)"); // 기준액
//				obj.ddExpn = (checkRefVal(gv_userPositCd, "연수경비(숙식)") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//
//			}else if(result[i].expnCls == "euNight"){
//				edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//		  		+ " €" + gf_AmtFormat((checkRefVal(gv_userPositCd, "유럽숙박") * parseInt(result[i].accomoExpnDcnt)))
//		  		+ " / ￦" + gf_AmtFormat(Math.round(v_EUR * (checkRefVal(gv_userPositCd, "유럽숙박") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_EUR * (checkRefVal(gv_userPositCd, "유럽숙박") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotEUR = v_cTotEUR + (checkRefVal(gv_userPositCd, "유럽숙박") * parseInt(result[i].accomoExpnDcnt));
//
//				var obj = new Object();
//				obj.expnCls = result[i].expnCls; // 구분코드
//				obj.accomoCls = "유럽숙박"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "유럽숙박") * parseInt(result[i].accomoExpnDcnt))); // KRW
//				obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "유럽숙박"); // 기준액
//				obj.ddExpn = (checkRefVal(gv_userPositCd, "유럽숙박") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//			}else if(result[i].expnCls == "euDay"){
//				edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//		  		+ " €" + gf_AmtFormat((checkRefVal(gv_userPositCd, "유럽일당") * parseInt(result[i].accomoExpnDcnt)))
//		  		+ " / ￦" + gf_AmtFormat(Math.round(v_EUR * (checkRefVal(gv_userPositCd, "유럽일당") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_EUR * (checkRefVal(gv_userPositCd, "유럽일당") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotEUR = v_cTotEUR + (checkRefVal(gv_userPositCd, "유럽일당") * parseInt(result[i].accomoExpnDcnt));
//
//				var obj = new Object();
//				obj.expnCls = result[i].expnCls; // 구분코드
//				obj.accomoCls = "유럽일당"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "유럽일당") * parseInt(result[i].accomoExpnDcnt))); // KRW
//				obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "유럽일당"); // 기준액
//				obj.ddExpn = (checkRefVal(gv_userPositCd, "유럽일당") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//			}else if(result[i].expnCls == "enNight"){
//				edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//		  		+ " ￡" + gf_AmtFormat((checkRefVal(gv_userPositCd, "영국숙박") * parseInt(result[i].accomoExpnDcnt)))
//		  		+ " / ￦" + gf_AmtFormat(Math.round(v_GBP * (checkRefVal(gv_userPositCd, "영국숙박") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_GBP * (checkRefVal(gv_userPositCd, "영국숙박") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotGBP = v_cTotGBP + (checkRefVal(gv_userPositCd, "영국숙박") * parseInt(result[i].accomoExpnDcnt));
//
//				var obj = new Object();
//				obj.expnCls = result[i].expnCls; // 구분코드
//				obj.accomoCls = "영국숙박"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "영국숙박") * parseInt(result[i].accomoExpnDcnt))); // KRW
//				obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "영국숙박"); // 기준액
//				obj.ddExpn = (checkRefVal(gv_userPositCd, "영국숙박") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//			}else if(result[i].expnCls == "enDay"){
//				edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//		  		+ " ￡" + gf_AmtFormat((checkRefVal(gv_userPositCd, "영국일당") * parseInt(result[i].accomoExpnDcnt)))
//		  		+ " / ￦" + gf_AmtFormat(Math.round(v_GBP * (checkRefVal(gv_userPositCd, "영국일당") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_GBP * (checkRefVal(gv_userPositCd, "영국일당") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotGBP = v_cTotGBP + (checkRefVal(gv_userPositCd, "영국일당") * parseInt(result[i].accomoExpnDcnt));
//
//				var obj = new Object();
//				obj.expnCls = result[i].expnCls; // 구분코드
//				obj.accomoCls = "영국일당"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "영국일당") * parseInt(result[i].accomoExpnDcnt))); // KRW
//				obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "영국일당"); // 기준액
//				obj.ddExpn = (checkRefVal(gv_userPositCd, "영국일당") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//			}else if(result[i].expnCls == "jaNight"){
//				edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//		  		+ " ￥" + gf_AmtFormat((checkRefVal(gv_userPositCd, "일본숙박") * parseInt(result[i].accomoExpnDcnt)))
//		  		+ " / ￦" + gf_AmtFormat(Math.round(v_JPY * (checkRefVal(gv_userPositCd, "일본숙박") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_JPY * (checkRefVal(gv_userPositCd, "일본숙박") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotJPY = v_cTotJPY + (checkRefVal(gv_userPositCd, "일본숙박") * parseInt(result[i].accomoExpnDcnt));
//
//				var obj = new Object();
//				obj.expnCls = result[i].expnCls; // 구분코드
//				obj.accomoCls = "일본숙박"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "일본숙박") * parseInt(result[i].accomoExpnDcnt))); // KRW
//				obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "일본숙박"); // 기준액
//				obj.ddExpn = (checkRefVal(gv_userPositCd, "일본숙박") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//			}else if(result[i].expnCls == "jaDay"){
//				edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//		  		+ " ￥" + gf_AmtFormat((checkRefVal(gv_userPositCd, "일본일당") * parseInt(result[i].accomoExpnDcnt)))
//		  		+ " / ￦" + gf_AmtFormat(Math.round(v_JPY * (checkRefVal(gv_userPositCd, "일본일당") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_JPY * (checkRefVal(gv_userPositCd, "일본일당") * parseInt(result[i].accomoExpnDcnt))));
//
//				v_cTotJPY = v_cTotJPY + (checkRefVal(gv_userPositCd, "일본일당") * parseInt(result[i].accomoExpnDcnt));
//
//
//				var obj = new Object();
//				obj.expnCls = result[i].expnCls; // 구분코드
//				obj.accomoCls = "일본일당"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "일본일당") * parseInt(result[i].accomoExpnDcnt))); // KRW
//				obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "일본일당"); // 기준액
//				obj.ddExpn = (checkRefVal(gv_userPositCd, "일본일당") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
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



	// 개인지급 합계 (채재바 + 항공료)
	v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;
	$("#totalAmount").text(gf_AmtFormat(v_TotalAmount));

}

function f_calcTripDate(){

	if(ds_CityList.size() > 0){
		for(var i = 0; i < ds_CityList.size(); i++){
			var temp = ds_CityList.get(i);
			if(i == 0){
				v_StartDate = parseInt(temp.stayStYmd);
				v_EndDate = parseInt(temp.stayEdYmd);
			}else{
				if(v_StartDate > parseInt(temp.stayStYmd)) v_StartDate = parseInt(temp.stayStYmd);
				if(v_EndDate < parseInt(temp.stayEdYmd)) v_EndDate = parseInt(temp.stayEdYmd);
			}
		}

		var startDate = $.datepicker.parseDate("yymmdd" , v_StartDate);
		var endDate = $.datepicker.parseDate("yymmdd" , v_EndDate);

		v_TripDate = (endDate - startDate)/1000/60/60/24;

		var betDayText = v_TripDate + "박 " + (v_TripDate+1) + "일";



//		$("#tripDate").text(betDayText);

		var tripDate = $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", v_StartDate)) + " ~ " + $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", v_EndDate)) + " (기간 : " + (parseInt(v_TripDate)+1) + "일)";
		$("#tripDate").text(tripDate);
	}



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

	if(tUserVal == "") tUserVal = 0;

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

function cf_RetrieveSignInfo(){

	//해당 업무구분과 업무분류에 해당되는 양식명을 가져옴
	var signParams = {
			docNo : v_DocNo
	};

	gf_Transaction("SELECT_SIGN_INFO", "/trip/outerTrip/retrieveSignInfo2.xpl", signParams, {}, "f_Callback", true);
}