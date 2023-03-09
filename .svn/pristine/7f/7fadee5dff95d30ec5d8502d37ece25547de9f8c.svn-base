/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
var v_transpTr = "<tr><td class=\"inpt\" style=\"width:12%; text-align:center\"><input name=\"tStart\" type=\"text\" style=\"width:90px;\"></td>"
	+ "<td class=\"inpt\" style=\"width:12%; text-align:center\"><input name=\"tEnd\" type=\"text\" style=\"width:90px;\"></td>"
	+ "<td class=\"inpt\" style=\"width:15%; text-align:center\"><select name=\"tType\">"
	+ "<option value=\"1\">항공</option>"
	+ "<option value=\"2\">고속철도</option>"
	+ "<option value=\"3\">기차</option>"
	+ "<option value=\"4\">선박</option>"
	+ "<option value=\"5\">고속버스</option>"
	+ "<option value=\"6\">렌터카</option>"
	+ "<option value=\"7\">택시</option>"
	+ "<option value=\"8\">차량</option>"
	+ "<option value=\"9\">고속도로통행료</option>"
	+ "<option value=\"A\">대중교통</option>"
	+ "</select></td>"
	+ "<td class=\"inpt\" style=\"width:10%; text-align:center\">"
	+ "<select name=\"tRound\"><option value=\"R\" selected=\"selected\">왕복</option><option value=\"D\">편도</option></select></td>"
	+ "<td class=\"inpt\" style=\"width:16%; text-align:left\" value=\"0\"><input name=\"tDist\" type=\"text\" style=\"text-align:right; width:40px;\" disabled=\"disabled\" readonly>km <input name=\"tCalCarUseQty\" type=\"text\" style=\"text-align:right; width:40px;\" disabled=\"disabled\" readonly>ℓ</td>"
	+ "<td class=\"inpt\" style=\"width:20%; text-align:left\" value=\"0\"><input name=\"tAmount\" type=\"text\" style=\"text-align:right; width:80px;\">원 <input name=\"tCarUseQty\" type=\"text\" style=\"text-align:right; width:40px;\" disabled=\"disabled\" readonly>ℓ</td>"
	+ "<td class=\"inpt\" style=\"width:15%; text-align:center\" value=\"0\"><input name=\"tReqAmount\" type=\"text\" style=\"text-align:right; width:80px;\" disabled=\"disabled\" readonly>원</td>"
	+ "<td class=\"inpt\" style=\"width:5%; text-align:center\" value=\"0\"><img name=\"delDtl\" src=\"../../common/images/popup/icon_close.png\"></td></tr>";

var v_BdgtType = "";
var v_BdgtData;
var v_RefNo;
var v_OrderNo;

var v_Aufnr; // 예산
var v_KText; // 예산내역
var v_Kostv; // 집행팀
var v_Kostvnm; // 집행팀 이름
var v_Chief; // 집행팀장ID
var v_Chiefnm; // 집행팀장 이름

var ds_tripDraft = new DataSet(); // 상신용 DataSet

var nightAm; //숙박비 기준액
var dayAm; //일당비 기준액

var tripDate; //출장일 (계산용)

var tripUser; // 출장자
var tripUserNm;
var tripUserTeam; // 출장자팀
var tripUserTeamNm;
var tripUserPositCd;
var tripUserRpswrkCd;
var tripUserApptOrgCd; // 출장자 현발령

var tripAmt1 = 0;
var tripAmt2 = 0;
var tripAmt3 = 0;

// 타 집행팀 여부
var v_dSignType = "N";
var v_dSignUserCd = ""; // 타 집행팀장 ORG
var v_dSignUserId = ""; // 타 집행티장 ID
var v_dSignUserNm = "";
var v_dSignOrgNm = "";
var v_dSignRpwrkNm = "";
var v_dSignRpswrkCd = "";
var v_dSignPositCd = "";
var v_dSignPositNm = "";

//현장 소속일 경우 소장 저장용
var v_hSignType = "N";
var v_hSignUserCd = "";
var v_hSignUserId = "";
var v_hSignUserNm = "";
var v_hSignUserPositCd = "";
var v_hSignUserRpswrkCd = "";

var v_tSignUserCd; // 최종결재 ORG
var v_tSignUserId; // 최종결재 ID


var v_SapResult; // SAP 전송 결과 Result

var v_isSaveEnable = "N"; // 상신중 여부

var v_DocNo; // CMAS ID

// 업무대행자
var v_DutyAgncUserId = "";
var v_DutyAgncUserNm = "";
var v_DutyAgncOrgCd = "";

// 인원 여부
var v_IsOfficer = "N";

// 팀장 여부
var v_IsOrgBoss = "N";


var v_DdExpnExcl = "N";//일비용제외
var v_AccomoExpnExcl = "N";//숙박비용제외
var v_EduBustrYn = "N";//교육출장여부

var v_ReceiptUser = ""; // 교통비 수령인
var v_ReceiptUserNm = ""; // 교통비 수령인 이름
var v_rAmount = ""; // 교통비 수령액


// 임시저장
var v_CmasId; // 임시저장하여 이미 생성되어진 v_CmasId
var v_DocSts;

//작성자 정보
var sDrafterId;
var sDrafterNm;
var sDrafterOrgCd;
var sDrafterOrgNm;
var sDrafterRpswrkCd;
var sDrafterPositCd;

var ds_SavedDoc = new DataSet();
var ds_SavedDetailData = new DataSet();

//팝업이 닫힐때 리턴값을 전달할 callback 함수
var v_CallbackFunc;

var ds_Signln = new DataSet();			//결재선정보




// 타집행팀에 현장일 경우 현장관리책임자 협의가 추가된다.
var v_dHSignUserCd = "";
var v_dHSignUserId = "";
var v_dHSignUserNm = "";
var v_dHSignOrgNm = "";
var v_dHSignRpwrkNm = "";
var v_dHSignRpswrkCd = "";
var v_dHSignPositCd = "";
var v_dHSignPositNm = "";

// 최종결재자 임시저장
var v_tSign;

var ds_ChiefCd = new DataSet();

// 임시저장 확인용 Flag
var v_IsSavedDoc = "N";

// 결재선 지정 사용 여부
var v_isSignEdit = "N";

var v_FileAtchId;		//첨부파일ID

var v_CanSaveDraft;		//저장,상신 시도 구분 (1:저장, 2:상신)

var v_EHRAppYn = "N";	//e-HR시스템에 국내출장 근태등록여부 체크

var v_CoFileYn = "N";	//첨부파일 DB에 등록여부 체크


var v_totalAmt = 0;		//최종신청금액 (대리수령일때 비교 대상)

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


//	if(v_DocSts == "D16"){
//		alert("임시저장된 문서");
//	}

	//파일 업로드 콜백 함수 =>임시저장.
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
	//기안자 검색 컴포넌트 초기화
	gf_SetUserComponent($("#tripUserId"), function(data){
		var userInfo = data.orgNm + " " + data.userPositCd + " " + data.userKnm + "(" + data.userId + ")";
//		$("#userInfo").val(userInfo);

		tripUser = data.userId;
		tripUserNm = data.userKnm;
		tripUserTeam = data.orgCd;
		tripUserTeamNm = data.orgNm;
		tripUserPositCd = data.userPositCd;
		tripUserRpswrkCd = data.userRpswrkCd;
		tripUserApptOrgCd = data.apptOrgCd;

		//소속팀 셋팅
		var tripUserOrgNm = tripUserTeamNm + " (" + tripUserTeam + ")";
		$("#tripUserOrgNm").text(tripUserOrgNm);

		// 팀장 이상 여부 초기화
		v_IsOrgBoss = "N";

//		$("#innerTripSign").hide();

//		v_IsOrgBoss

//		alert(tripUser + " " + tripUserTeam);

		var searchParams = {
				Sabun : data.userId
		};


		//data.userId 가 없을때에는 조회되지 않아야..(19.04.26)
		if(data.userId == undefined){
		}
		else{
			gf_Transaction("SELECT_EXPENSE_STANDARD", "/trip/eai/getSendTripExpenseStandard.xpl", searchParams, {}, "f_Callback", true);
		}
	});

	gf_SetUserComponent($("#tripAgent"), function(data){
		var userInfo = data.orgNm + " " + data.userPositCd + " " + data.userKnm + "(" + data.userId + ")";

		v_DutyAgncUserId = data.userId;
		v_DutyAgncUserNm = data.userKnm;
		v_DutyAgncOrgCd = data.orgCd;
	});

	gf_SetUserComponent($("#receiptUser"), function(data){
		var userInfo = data.orgNm + " " + data.userPositCd + " " + data.userKnm + "(" + data.userId + ")";
		v_ReceiptUser = data.userId;
		v_ReceiptUserNm = data.userKnm;
	});


	//기안일 검색 Date Component 초기화
	$( "input[name='startDate']" ).mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
	$( "input[name='endDate']" ).mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });

	//기표/증빙일자
	$( "input[name='ordDate']" ).mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
	// 지불예정일
	$( "input[name='payDate']" ).mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });

	$("input[name='startDate']").attr("readonly", "true");
	$("input[name='endDate']").attr("readonly", "true");
	$("input[name='payDate']").attr("readonly", "true");

    // 숫자만 입력
    f_numberOnly("eduTripAmt");
    f_numberOnly("genAccomoDcnt");	//숙박시설 일수
    f_numberOnly("accomoAmt");		//숙박비
    f_numberOnly("eexpAmt");		//식비
    f_numberOnly("etcAmt");			//잡비

	//Attachment 컴포넌트 생성
	gf_InitFileUploadComponent();
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

	$("#sapConfN").click(function(){

		gf_PostOpen("/common/jsp/comp/sapConfN.jsp", null,
				"toolbar=no,scrollbars=no,width=665,height=600", {},
				true, true, "");

	});

	$("#addBtn").click(function(){

		cf_AppendTranspDetail();

	});

	$("#delBtn").click(function(){
		if($("#transpDetail tr").size() < 3){
			gf_AlertMsg("더이상 삭제할 수 없습니다");
		}else{
			$("#transpDetail tr:last").remove();

			var tAmount = $("input[name='tReqAmount']");
			var total = 0;
			var airTotal = 0;
			var etcTotal = 0;
			for(var i = 0; i < tAmount.size(); i++){
				var tVal = tAmount[i].value;
				if(tVal == "") continue;
				for(var j = 0; j < tVal.length; j++){
					tVal = tVal.replace(",", "");
				}
				total = total + parseInt(tVal);
				var tAmtType = $(tAmount[i]).parent().parent().find("select[name='tType']").val();
				if(tAmtType == "1"){
					airTotal = airTotal + parseInt(tVal);
				}else if(tAmtType != "1"){
					etcTotal = etcTotal + parseInt(tVal);
				}
			}

			$("#tAmountTotal").text(gf_AmtFormat(total) + "원");
			$("#tAmountTotal1").text(gf_AmtFormat(airTotal) + "원");
			$("#tAmountTotal2").text(gf_AmtFormat(etcTotal) + "원");

			var targetVal = e.currentTarget.value;
			for(var k = 0; k < targetVal.length; k++){
				targetVal = targetVal.replace(",", "");
			}
			e.currentTarget.value = gf_AmtFormat(targetVal);

			tripAmt1 = airTotal;
			tripAmt2 = etcTotal;

			f_GetTotalAmount();
		}

	});

	$("#innerTripBdgtChange").click(function(){

		var bdgtParams = {
				bdgtType : v_BdgtType
		};

		gf_PostOpen("/common/jsp/comp/budget/bdgtSelect.jsp", null,
				"toolbar=no,scrollbars=no,width=665,height=600", bdgtParams,
				true, true, "f_callBackFuncBdgtSelect");

	});

	$("#innerTripDetailConf").click(function(){

//		var tAmount = $("#transpDetail tr input[name='tAmount']");
//
//		var total = 0;
//
//		for(var i = 0; i < tAmount.size(); i++){
//
//			var amountTemp = $("#transpDetail tr input[name='tAmount']")[i];
//
//			total = total + parseInt($(amountTemp).val());
//
//		}
//
//		$("#tAmountTotal").text(total);

		//확정 처리 disable
		$("#transpDetail tr input").attr("readonly", "true");
		$("#transpDetail tr select").attr("disabled", "true");



	});

	$("#innerTripDetailEdit").click(function(){


		//확정 처리 disable
		$("#transpDetail tr input").removeAttr("readonly");
		$("#transpDetail tr select").removeAttr("disabled");

		$("#transpDetail tr input[name='tStart']").attr("readonly", "true");
		$("#transpDetail tr input[name='tEnd']").attr("readonly", "true");



	});

	$("input[name='startDate']").bind('change', function() {

		if($("input[name='endDate']").val() == ""){
			$("input[name='endDate']").datepicker("setDate", $("input[name='startDate']").val());
		}

		if($("input[name='startDate']").val() != "" && $("input[name='endDate']").val() != ""){
			var sDate = $("input[name='startDate']").val().split("-");
			var eDate = $("input[name='endDate']").val().split("-");

			var sDateTemp = sDate[0] + sDate[1] + sDate[2];
			var eDateTemp = eDate[0] + eDate[1] + eDate[2];

			var startDate = $.datepicker.parseDate("yymmdd" , sDateTemp);
			var endDate = $.datepicker.parseDate("yymmdd" , eDateTemp);

			var betDay = (endDate - startDate)/1000/60/60/24;

			var betDayText = "(" + betDay + "박 " + (betDay+1) + "일)";

			//숙박비, 일당비 계산
			tripDate = betDay+1; // 기준일 저장

			var dayAmAmt = dayAm * tripDate;
			var nightAmAmt = nightAm * (tripDate-1);

			var dayAmAmtText = gf_AmtFormat(dayAmAmt) + "원";
			var nightAmAmtText = gf_AmtFormat(nightAmAmt) + "원";

			if(betDay < 0){
				$("input[name='endDate']").val("");
				$("#betDate").text("0일");
				$("#dayAmAmt").text("0원");
				$("#nightAmAmt").text("0원");
				return;
			}else{
				$("#betDate").text(betDayText);
				$("#dayAmAmt").text(dayAmAmtText);
				$("#nightAmAmt").text(nightAmAmtText);
			}

			if(v_AccomoExpnExcl == "N"){
	  			var nightAmAmt = nightAm * (tripDate-1);
  				var nightAmAmtText = gf_AmtFormat(nightAmAmt) + "원";
  				if(tripDate == undefined) nightAmAmtText = "0원";
  				$("#nightAmAmt").text(nightAmAmtText);
	  		}else if(v_AccomoExpnExcl == "Y"){
	  			$("#nightAmAmt").text("0원");
	  			//체크박스 체크
	  			$("#excptNight").attr("checked", "true");
	  		}


	  		if(v_DdExpnExcl == "N"){
	  			var dayAmAmt = parseInt(dayAm) * parseInt(tripDate);
  				var dayAmAmtText = gf_AmtFormat(dayAmAmt) + "원";
  				if(tripDate == undefined) dayAmAmtText = "0원";
  				$("#dayAmAmt").text(dayAmAmtText);
	  		}else if(v_DdExpnExcl == "Y"){
	  			$("#dayAmAmt").text("0원");
	  			//체크박스 체크
	  			$("#excptDay").attr("checked", "true");
	  		}

	  		if(v_EduBustrYn == "Y"){
				var eduVal = $("#eduTripAmt").val();

				for(var k = 0; k < eduVal.length; k++){
					eduVal = eduVal.replace(",", "");
				}

				var eduAmtText = parseInt(eduVal) * tripDate;

				if(tripDate == undefined){
					$("#dayAmAmt").text("0원");
				}else{
					$("#dayAmAmt").text(gf_AmtFormat(eduAmtText) + "원");
				}
			}

			f_GetTotalAmount();

		}

		f_GetLimitAccomoAmt();	//숙박비 한도

	});

	$("input[name='endDate']").bind('change', function() {
		if($("input[name='startDate']").val() == ""){
			$("input[name='startDate']").datepicker("setDate", $("input[name='endDate']").val());
		}


		if($("input[name='startDate']").val() != "" && $("input[name='endDate']").val() != ""){

			var sDate = $("input[name='startDate']").val().split("-");
			var eDate = $("input[name='endDate']").val().split("-");

			var sDateTemp = sDate[0] + sDate[1] + sDate[2];
			var eDateTemp = eDate[0] + eDate[1] + eDate[2];

			var startDate = $.datepicker.parseDate("yymmdd" , sDateTemp);
			var endDate = $.datepicker.parseDate("yymmdd" , eDateTemp);

			var betDay = (endDate - startDate)/1000/60/60/24;

			var betDayText = "(" + betDay + "박 " + (betDay+1) + "일)";

			//숙박비, 일당비 계산
			tripDate = betDay+1; // 기준일 저장

			var dayAmAmt = dayAm * tripDate;
			var nightAmAmt = nightAm * (tripDate-1);

			var dayAmAmtText = gf_AmtFormat(dayAmAmt) + "원";
			var nightAmAmtText = gf_AmtFormat(nightAmAmt) + "원";

			if(betDay < 0){
				$("input[name='startDate']").val("");
				$("#betDate").text("0일");
				$("#dayAmAmt").text("0원");
				$("#nightAmAmt").text("0원");
				return;
			}else{
				$("#betDate").text(betDayText);
				$("#dayAmAmt").text(dayAmAmtText);
				$("#nightAmAmt").text(nightAmAmtText);
			}



			if(v_AccomoExpnExcl == "N"){
	  			var nightAmAmt = nightAm * (tripDate-1);
  				var nightAmAmtText = gf_AmtFormat(nightAmAmt) + "원";
  				if(tripDate == undefined) nightAmAmtText = "0원";
  				$("#nightAmAmt").text(nightAmAmtText);
	  		}else if(v_AccomoExpnExcl == "Y"){
	  			$("#nightAmAmt").text("0원");
	  			//체크박스 체크
	  			$("#excptNight").attr("checked", "true");
	  		}


	  		if(v_DdExpnExcl == "N"){
	  			var dayAmAmt = parseInt(dayAm) * parseInt(tripDate);
  				var dayAmAmtText = gf_AmtFormat(dayAmAmt) + "원";
  				if(tripDate == undefined) dayAmAmtText = "0원";
  				$("#dayAmAmt").text(dayAmAmtText);
	  		}else if(v_DdExpnExcl == "Y"){
	  			$("#dayAmAmt").text("0원");
	  			//체크박스 체크
	  			$("#excptDay").attr("checked", "true");
	  		}

	  		if(v_EduBustrYn == "Y"){
				var eduVal = $("#eduTripAmt").val();

				for(var k = 0; k < eduVal.length; k++){
					eduVal = eduVal.replace(",", "");
				}

				var eduAmtText = parseInt(eduVal) * tripDate;

				if(tripDate == undefined){
					$("#dayAmAmt").text("0원");
				}else{
					$("#dayAmAmt").text(gf_AmtFormat(eduAmtText) + "원");
				}
			}

			f_GetTotalAmount();

		}

		f_GetLimitAccomoAmt();	//숙박비 한도

	});

//	$("input[name='payDate']").bind('change', function() {
//		if($("input[name='startDate']").val() == ""){
//			gf_AlertMsg("출장 시작일이 입력되어야 합니다.");
//			$("input[name='payDate']").val("");
//		}
//	});


	// 문서 상신
	$("#innerTripDraft_old").click(function(){

		// 결재선 검사
		// 최종 결재자가 협의로 끝나면 안됨
		for(var i = 0; i < ds_Signln.size(); i++){
			// 마지막 결재자 검사
			if(i == ds_Signln.size()-1){
				if(ds_Signln.get(ds_Signln.size()-1).signTpCd == "T03"){
					 gf_AlertMsg("최종 결재자를 지정해야 합니다.\n\n결재선을 지정해주세요.");
					 return;
				}
			}
		}

		//출장비 확인
		if( $("#tripTotalAmt").text() == "0원"){
			gf_AlertMsg("국내출장 신청은 경비정산을 위한 신청입니다.\n근태를 위한 출장은 E-HR(개인전용)-근태관리-근태신청-국내출장으로 신청하시기 바랍니다.");
			return;
		}

		// 경비구분 확인
		if(v_BdgtType == ""){
			gf_AlertMsg("경비 구분이 선택되지 않았습니다.");
			return;
		}
		// 전표구분 확인
		if($("input[name='docType']:checked").val() == undefined){
			gf_AlertMsg(" 본사 / 지역 여부가 선택되지 않았습니다.");
			return;
		}
		// 집행팀 확인
		if(v_Kostv == undefined || v_Kostv == ""){
			gf_AlertMsg("집행팀이 입력되지 않았습니다.");
			return;
		}
		// 결재자 확인
		if(v_tSignUserId == undefined || v_tSignUserId == ""){
			gf_AlertMsg("최종 결재자가 입력되지 않았습니다.");
			return;
		}
		// 출장기간 확인
		if($("input[name='startDate']").val() == ""){
			gf_AlertMsg("출장예정일이 입력되지 않았습니다.");
			return;
		}
		if($("input[name='endDate']").val() == ""){
			gf_AlertMsg("출장종료일이 입력되지 않았습니다.");
			return;
		}
		// 출장목적 확인
		if($("#tripPurpose").val() == ""){
			gf_AlertMsg("출장목적이 입력되지 않았습니다.");
			return;
		}
		// 출장지역 확인
		if($("#tripTarget").val() == ""){
			gf_AlertMsg("출장장소가 입력되지 않았습니다.");
			return;
		}
		// 지불예정일
		if($("input[name='payDate']").val() == ""){
			gf_AlertMsg("지불예정일이 입력되지 않았습니다.");
			return;
		}

		// 지불예정일
		if($("input[name='ordDate']").val() == ""){
			gf_AlertMsg("기표/증빙일이 입력되지 않았습니다.");
			return;
		}

		// 업무대행자 확인
		if(v_DutyAgncUserId == ""){
			gf_AlertMsg("업무대행자가 입력되지 않았습니다.");
			return;
		}

		// 교통편 확인
		if($("#compCar").val() == "N"){
			if(f_TranspValCheck() == "N"){
				gf_AlertMsg("교통편에 입력되지 않은 항목이 있습니다.");
				return;
			}
		}

		// 타집행팀이면서 전결일 경우에 상신을 막는다.
		//2015. 05. 11 작성자가 임원일 경우까지 조건에 추가
		// 작성자가 임원이 아니라면 출장자 전결로 상신 가능
		if(v_dSignType == "Y" && v_tSignUserId == tripUser && v_tSignUserId == sDrafterId){
			gf_AlertMsg("타집행팀 예산 출장의 경우 전결로 상신할 수 없습니다.");
			return;
		}

		if(v_Chief == undefined){
			gf_AlertMsg("집행팀장이 등록되지 않는 예산의 경우는 상신이 불가합니다.");
			return;
		}

		if(v_IsOfficer == "Y"){
			if($("#noReceipt:checked").size() == 0){
				if($("#receiptUser_id").val() == ""){
					gf_AlertMsg("대리수령자를 입력하여 주세요.\n(수령인이 출장자라면 [대리수령 미지정]에 체크를 하셔야합니다");
					return;
				}
			}
		}



		// 금액 확인
		var trData = cf_getTranspDetail();

		for(var i = 0; i < trData.length; i++){



//			+ "<option value=\"1\">항공</option>"
//			+ "<option value=\"2\">고속철도</option>"
//			+ "<option value=\"3\">기차</option>"
//			+ "<option value=\"4\">선박</option>"
//			+ "<option value=\"5\">고속버스</option>"

//			<option value=\"R\" selected=\"selected\">왕복</option><option value=\"D\">편도

			var trafficCls = trData[i].trafficCls;
			var runtrpOneway = trData[i].runtrpOneway;
			var amt = trData[i].amt;
			switch(trafficCls){
				case "1" :
					if(v_IsOfficer == "Y"){ // 임원 금액 제한 변동
						// 항공
						if(runtrpOneway == "R"){
							// 왕복
							if(parseInt(amt) > 300000){
								gf_AlertMsg("항공 왕복 금액은 300000원 이하여야 합니다.");
								return;
							}
						}else if(runtrpOneway == "D"){
							// 편도
							if(parseInt(amt) > 150000){
								gf_AlertMsg("항공 편도 금액은 150000원 이하여야 합니다.");
								return;
							}
						}
					}else{
						// 항공
						if(runtrpOneway == "R"){
							// 왕복
							if(parseInt(amt) > 250000){
								gf_AlertMsg("항공 왕복 금액은 250000원 이하여야 합니다.");
								return;
							}
						}else if(runtrpOneway == "D"){
							// 편도
							if(parseInt(amt) > 125000){
								gf_AlertMsg("항공 편도 금액은 125000원 이하여야 합니다.");
								return;
							}
						}
					}

					break;
				case "2" :
					// 고속철도
					if(runtrpOneway == "R"){
						// 왕복
						if(parseInt(amt) > 150000){
							gf_AlertMsg("고속철도 왕복 금액은 150000원 이하여야 합니다.");
							return;
						}
					}else if(runtrpOneway == "D"){
						// 편도
						if(parseInt(amt) > 75000){
							gf_AlertMsg("고속철도 편도 금액은 75000원 이하여야 합니다.");
							return;
						}
					}
					break;
				case "3" :

					// 기차
					if(runtrpOneway == "R"){
						// 왕복
						if(parseInt(amt) > 100000){
							gf_AlertMsg("기차 왕복 금액은 100000원 이하여야 합니다.");
							return;
						}
					}else if(runtrpOneway == "D"){
						// 편도
						if(parseInt(amt) > 50000){
							gf_AlertMsg("기차 편도 금액은 50000원 이하여야 합니다.");
							return;
						}
					}

					break;

				case "4" :

					// 선박
					if(runtrpOneway == "R"){
						// 왕복
						if(parseInt(amt) > 100000){
							gf_AlertMsg("선박 왕복 금액은 100000원 이하여야 합니다.");
							return;
						}
					}else if(runtrpOneway == "D"){
						// 편도
						if(parseInt(amt) > 50000){
							gf_AlertMsg("선박 편도 금액은 50000원 이하여야 합니다.");
							return;
						}
					}
					break;

				case "5" :
					// 고속버스
					if(runtrpOneway == "R"){
						// 왕복
						if(parseInt(amt) > 100000){
							gf_AlertMsg("고속버스 왕복 금액은 100000원 이하여야 합니다.");
							return;
						}
					}else if(runtrpOneway == "D"){
						// 편도
						if(parseInt(amt) > 50000){
							gf_AlertMsg("고속버스 편도 금액은 50000원 이하여야 합니다.");
							return;
						}
					}
					break;
				default :
					break;
			}

		}

		//삭제금지_20200709_나중에 테스트후 적용필요(출장 중복신청 방지기능)
		//신청중복체크
		//f_CheckDraftDuplication();
		//return;


		if(v_isSaveEnable == "N"){
			v_isSaveEnable = "Y";

			// 클릭 못하도록 버튼 숨김
			$(".btn").hide();


			// 상신시 Modal처리
			gf_InitSpinner(top.$('body'));


			//SAP 상신
			cf_SendBizTrip();

		}else{
			gf_AlertMsg("문서 상신중입니다. 잠시만 기다려주세요.");
			return;
		}



	});

	// 문서 상신
	$("#innerTripDraft").click(function(){

		// 결재선 검사
		// 최종 결재자가 협의로 끝나면 안됨
		for(var i = 0; i < ds_Signln.size(); i++){
			// 마지막 결재자 검사
			if(i == ds_Signln.size()-1){
				if(ds_Signln.get(ds_Signln.size()-1).signTpCd == "T03"){
					 gf_AlertMsg("최종 결재자를 지정해야 합니다.\n\n결재선을 지정해주세요.");
					 return;
				}
			}
		}
/* 20201230 고희철변호사 오류로 막기
		//출장비 확인
		if( $("#tripTotalAmt").text() == "0원"){
			gf_AlertMsg("국내출장 신청은 경비정산을 위한 신청입니다.\n근태를 위한 출장은 E-HR(개인전용)-근태관리-근태신청-국내출장으로 신청하시기 바랍니다.");
			return;
		}
*/
		// 경비구분 확인
		if(v_BdgtType == ""){
			gf_AlertMsg("경비 구분이 선택되지 않았습니다.");
			return;
		}
		// 전표구분 확인
		if($("input[name='docType']:checked").val() == undefined){
			gf_AlertMsg(" 본사 / 지역 여부가 선택되지 않았습니다.");
			return;
		}
		// 집행팀 확인
		if(v_Kostv == undefined || v_Kostv == ""){
			gf_AlertMsg("집행팀이 입력되지 않았습니다.");
			return;
		}
		// 결재자 확인
		if(v_tSignUserId == undefined || v_tSignUserId == ""){
			gf_AlertMsg("최종 결재자가 입력되지 않았습니다.");
			return;
		}
		// 출장기간 확인
		if($("input[name='startDate']").val() == ""){
			gf_AlertMsg("출장예정일이 입력되지 않았습니다.");
			return;
		}
		if($("input[name='endDate']").val() == ""){
			gf_AlertMsg("출장종료일이 입력되지 않았습니다.");
			return;
		}
		// 출장목적 확인
		if($("#tripPurpose").val() == ""){
			gf_AlertMsg("출장목적이 입력되지 않았습니다.");
			return;
		}
		// 출장지역 확인
		if($("#tripTarget").val() == ""){
			gf_AlertMsg("출장장소가 입력되지 않았습니다.");
			return;
		}
		// 지불예정일
		if($("input[name='payDate']").val() == ""){
			gf_AlertMsg("지불예정일이 입력되지 않았습니다.");
			return;
		}

		// 지불예정일
		if($("input[name='ordDate']").val() == ""){
			gf_AlertMsg("기표/증빙일이 입력되지 않았습니다.");
			return;
		}

		// 업무대행자 확인
/*
		if(v_DutyAgncUserId == ""){
			gf_AlertMsg("업무대행자가 입력되지 않았습니다.");
			return;
		}
*/

		// 교통편 확인
		if($("#compCar").val() == "N"){
			if(f_TranspValCheck2() == "N"){
				gf_AlertMsg("교통편에 입력되지 않은 항목이 있습니다.");
				return;
			}
		}

		// 교통편 확인
		if($("#compCar").val() == "N"){
			if(f_TranspValCheck3() == "N"){
				gf_AlertMsg("교통편의 교통수단이 [차량]일 경우 \n\n [이용금액]의 리터(ℓ) 입력항목에 \n\n 0 ℓ (리터)로 입력할 수 없습니다. \n\n 유효한 숫자로 입력 하셔야합니다");
				return;
			}
		}

		// 타집행팀이면서 전결일 경우에 상신을 막는다.
		//2015. 05. 11 작성자가 임원일 경우까지 조건에 추가
		// 작성자가 임원이 아니라면 출장자 전결로 상신 가능
		if(v_dSignType == "Y" && v_tSignUserId == tripUser && v_tSignUserId == sDrafterId){
			gf_AlertMsg("타집행팀 예산 출장의 경우 전결로 상신할 수 없습니다.");
			return;
		}

		if(v_Chief == undefined){
			gf_AlertMsg("집행팀장이 등록되지 않는 예산의 경우는 상신이 불가합니다.");
			return;
		}

		if(v_IsOfficer == "Y"){
			if($("#noReceipt:checked").size() == 0){
				if($("#receiptUser_id").val() == ""){
					gf_AlertMsg("대리수령자를 입력하여 주세요.\n수령인이 출장자라면 [대리수령 미지정]에 체크를 하셔야합니다");
					return;
				}
			}
		}

		if(v_IsOfficer == "Y"){
			var vOf_rAmount = removeComma($("#rAmount").val());
			var vOf_totAmt = removeComma($("#totAmt").val());

			if($("#noReceipt:checked").size() == 0){
				if(parseInt(vOf_rAmount) > parseInt(vOf_totAmt)){
					gf_AlertMsg("[대리수령금액]이 [최종 신청금액]을 초과할 수 없습니다");
					return;
				}
			}
		}

		// 숙박시설 일수
		if($("#genAccomoDcnt").val() == ""){
			gf_AlertMsg("[숙박시설 일수]가 입력되지 않았습니다. \n\n 숙박시설을 이용하지 않았다면 0을 입력하세요");
			return;
		}

		// 숙박비
		if($("#accomoAmt").val() == ""){
			gf_AlertMsg("[숙박비]가 입력되지 않았습니다.");
			return;
		}

		// 식비
		if($("#eexpAmt").val() == ""){
			gf_AlertMsg("[식비]가 입력되지 않았습니다.");
			return;
		}

		// 잡비
		if($("#etcAmt").val() == ""){
			gf_AlertMsg("[잡비]가 입력되지 않았습니다.");
			return;
		}

		//특수문자 체크(출장장소)
		if(f_patternChk($("#tripTarget").val()) == false){
			gf_AlertMsg("[출장장소]에 특수문자를 입력할 수 없습니다");
			return;
		}

		//특수문자 체크(출장목적)
		if(f_patternChk($("#tripPurpose").val()) == false){
			gf_AlertMsg("[출장목적]에 특수문자를 입력할 수 없습니다");
			return;
		}

		//특수문자 체크(비고)
		if(f_patternChk($("#rem").val()) == false){
			gf_AlertMsg("[비고]에 특수문자를 입력할 수 없습니다");
			return;
		}

		// 첨부파일
		/*
		var fileAtch = gf_IsNull(v_FileAtchId) ? ""          : v_FileAtchId;
		alert("fileAtch:"+fileAtch);
		//var Csize = $("div[id='atchFile']").val();
		//alert("_Csize:"+Csize);

		if(fileAtch == ""){
			gf_AlertMsg("[첨부파일]이 입력되지 않았습니다.");
			//return;
		}
		*/

		//한도초과 체크(시작)
		var vcl_startDate = $("input[name='startDate']").val();
		if(vcl_startDate >= "2020-11-05"){	//2020-11-05 부터 한도 체크

			var vc_totAccomoAmt = removeComma($("#totAccomoAmt").text());
			var vc_limitAccomoAmt = removeComma($("#limitAccomoAmt").text());

			if(parseInt(vc_totAccomoAmt) > parseInt(vc_limitAccomoAmt)){
				gf_AlertMsg("[숙박비 및 기타경비]의 한도를 초과할 수 없습니다 \n\n (숙박비+식비+잡비)합계  >  (숙박비+식비+잡비)한도 ");
				return;
			}

		}
		//한도초과 체크(종료)

		//숙박시설일수 체크(시작)
		var vc_sDate = $("input[name='startDate']").val().split("-");
		var vc_eDate = $("input[name='endDate']").val().split("-");

		var vc_sDateTemp = vc_sDate[0] + vc_sDate[1] + vc_sDate[2];
		var vc_eDateTemp = vc_eDate[0] + vc_eDate[1] + vc_eDate[2];

		var vc_startDate = $.datepicker.parseDate("yymmdd" , vc_sDateTemp);
		var vc_endDate = $.datepicker.parseDate("yymmdd" , vc_eDateTemp);

		var vc_betDay = (vc_endDate - vc_startDate)/1000/60/60/24;

		var vc_genAccomoDcnt = removeComma($("#genAccomoDcnt").val());

		if(parseInt(vc_genAccomoDcnt) > parseInt(vc_betDay)){
			gf_AlertMsg("[숙박시설 일수]가 일정기간 몇박몇일의 박수 초과할 수 없습니다");
			return;
		}
		//숙박시설일수 체크(시작)


		//삭제금지_20200709_나중에 테스트후 적용필요(출장 중복신청 방지기능)
		//신청중복체크
		//f_CheckDraftDuplication();
		//return;

		//e-HR시스템에 국내출장 근태등록여부 체크(시작)
		f_CheckEHRAppYn();

		var vh_tripUserId = $("#tripUserId_id").val();	//출장자사번
		if(vh_tripUserId != undefined && vh_tripUserId.length == 7){		//테스트용
			if(vh_tripUserId.substr(0,1) == "D" || vh_tripUserId.substr(0,1) == "F"){		//eHR시스템을 사용할수 없는 사용자는 근태등록 여부 체크 안함 (D사번 F사번)
				v_EHRAppYn = "Y"
			}
		}

		if(gv_userId != undefined && gv_userId == "1202491"){		//테스트용
			v_EHRAppYn = "Y"	//나중에 막으세요
		}

		var v_EHRmsg = "e-HR시스템에 국내출장 근태신청이 되지 않아서 \n";
		v_EHRmsg += "상신할 수 없습니다. \n\n";
		v_EHRmsg += "먼저 e-HR시스템에 국내출장 근태신청을 결재완료 후 \n";
		v_EHRmsg += "정산서를 상신할 수 있습니다. \n\n";
		v_EHRmsg += "신청절차 : 근태신청(e-HR/국내출장) → 출장정산서 작성(바로넷) \n\n";
		v_EHRmsg += "문의 : 인사팀 윤은진 대리 (02-2288-3813)";

  		if (v_EHRAppYn != "Y") {
				 gf_AlertMsg(v_EHRmsg);
				 return;
  		}
		//e-HR시스템에 국내출장 근태등록여부 체크(종료)


  		//미래날짜 입력 제한 체크(시작)
		var vft_startDate = $("input[name='startDate']").val();	//출장 시작일자
		var vft_endDate = $("input[name='endDate']").val();	//출장 종료일자
		var todate = fnToDate();	//오늘날짜 구하기(아래에 함수사용함)

//alert("vft_startDate:"+vft_startDate);
//alert("vft_endDate:"+vft_endDate);
//alert("todate:"+todate);

		if(vft_startDate.length == 10 && vft_endDate.length == 10 && todate.length == 10){
			if(vft_startDate > todate || vft_endDate > todate){
				gf_AlertMsg("출장기간을 미래날짜로 입력할 수 없습니다 \n\n 출장종료일 이후 국내출장 정산서를 작성해 주시기 바랍니다");
				return;
			}
		}

  		//미래날짜 입력 제한 체크(종료)


		if(confirm("상신 하시겠습니까?")){
			v_CanSaveDraft = '2';	//첨부파일 콜백 작업시 '저장' 구분 (1:저장, 2:상신)
			gf_onFileUpload();
		}

	});

	/* Batch Input once Basic Day at checked row */
	$("#searchIst").click(function(e){

		var offset = $(e.target).offset();

		var top = (($(window).width() - $("#emmisionLayerDiv").outerWidth()) / 2) + $(window).scrollLeft();
		var left = (($(window).height() - $("#emmisionLayerDiv").outerHeight()) / 2) + $(window).scrollTop();

//		$("#emmisionLayerDiv").css({position:"absolute", top : (offset.top + $(e.target).height()), left:(offset.left + $("#emmisionLayerDiv").width())});
		$("#emmisionLayerDiv").css({position:"absolute", top : top, left : left});


		$("#emmisionLayerDiv").show();
		return false;

	});


	$('#layer_close2').click(function(){
		$('#emmisionLayerDiv').fadeOut();
		return false;
	});


	// 본사 지역 radio 클릭시 예산선택 팝업
//	$("input[name='docType']").click(function(){
//		var bdgtParams = {
//				bdgtType : v_BdgtType
//		};
//
//		gf_PostOpen("/common/jsp/comp/budget/bdgtSelect.jsp", null,
//				"toolbar=no,scrollbars=no,width=665,height=600", bdgtParams,
//				true, true, "f_callBackFuncBdgtSelect");
//	});

	$("#compCar").bind('change', function(e) {
		if(e.target.value == "N"){
			$("#transpDetail").show();
			$("#addBtn").show();
			$("#delBtn").show();
		}else if(e.target.value == "Y"){
			if(confirm("[교통비 미신청]을 Y로 선택하면, 입력된 교통편 정보가 삭제됩니다. 진행하시겠습니까?")){
				$("#transpDetail").hide();
				$("#addBtn").hide();
				$("#delBtn").hide();
			}else{
				e.target.value = "N";
				$("#addBtn").show();
				$("#delBtn").show();
			}
		}

		$("#tAmountTotal1").text("0원");
		$("#tAmountTotal2").text("0원");
		$("#tAmountTotal").text("0원");

		f_GetTotalAmount();

	});

	$("#excptNight").bind('change', function(e) {
		// 안체크
		if($("#excptNight:checked").size() < 1){
			v_AccomoExpnExcl = "N";
			var nightAmAmt = nightAm * (tripDate-1);
			var nightAmAmtText = gf_AmtFormat(nightAmAmt) + "원";
			if(tripDate == undefined) nightAmAmtText = "0원";
			$("#nightAmAmt").text(nightAmAmtText);
		}else{
			// 체크
			v_AccomoExpnExcl = "Y";
			$("#nightAmAmt").text("0원");
		}
		f_GetTotalAmount();
	});

	$("#excptDay").bind('change', function(e) {
		// 안체크
		if($("#excptDay:checked").size() < 1){
			v_DdExpnExcl = "N";//일비용제외
			var dayAmAmt = parseInt(dayAm) * parseInt(tripDate);
			var dayAmAmtText = gf_AmtFormat(dayAmAmt) + "원";
			if(tripDate == undefined) dayAmAmtText = "0원";
			$("#dayAmAmt").text(dayAmAmtText);
		}else{
			// 체크
			v_DdExpnExcl = "Y";
			$("#dayAmAmt").text("0원");

		}
		f_GetTotalAmount();
	});

	$("#eduTripChk").bind('change', function(e) {
		// 안체크
		if($("#eduTripChk:checked").size() < 1){
			v_EduBustrYn = "N";
			if(tripDate == undefined){
				$("#dayAmAmt").text("0원");
			}else{
				var dayAmAmt = parseInt(dayAm) * parseInt(tripDate);
				var dayAmAmtText = gf_AmtFormat(dayAmAmt) + "원";
				$("#dayAmAmt").text(dayAmAmtText);
			}
			$("#eduTripChkText").hide();

		}else{
			// 체크
			v_EduBustrYn = "Y";
			$("#dayAmAmt").text("0원");
			$("#eduTripChkText").show();

			var eduVal = $("#eduTripAmt").val();

			if(eduVal == "") eduVal = 0;

			for(var k = 0; k < eduVal.length; k++){
				eduVal = eduVal.replace(",", "");
			}

			var eduAmtText = parseInt(eduVal) * tripDate;

			if(tripDate == undefined){
				$("#dayAmAmt").text("0원");
			}else{
				$("#dayAmAmt").text(gf_AmtFormat(eduAmtText) + "원");
			}

			if(eduVal == 0){
				$("#dayAmAmt").text("0원");
			}
		}

		f_GetTotalAmount();
	});

	$("#eduTrip").bind('keyup', function(e) {
		var targetVal = e.currentTarget.value;

//		var totalAmt = tripAmt1 + tripAmt2;

		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}
		e.currentTarget.value = gf_AmtFormat(targetVal);

		f_GetTotalAmount();

	});

	// 교통비 수령 체크
	$("#noReceipt").bind('change', function(e) {
		// 안체크
		if($("#noReceipt:checked").size() < 1){
//			alert("안체크");
			//show()
			$("#rAmount").show();
			$("#receiptUser").show();
		}else{
			//hide()
//			alert("체크");
			$("#rAmount").hide();
			$("#receiptUser").hide();

		}
	});


	// 문서를 임시저장 처리 한다.
	// 이미 적상되어진 같을 JSON Object 형식으로 IF_PARAM 컬럼에 저장한다.
	$('#innerTripSave_old').click(function(){

//		// 예산번호 관련
//		v_BdgtType
//		v_Aufnr
//		v_KText
//		v_Kostv
//		v_Kostvnm
//		v_Chief
//		v_Chiefnm
//
//		// SAP 에 등록된 집행팀의 ORG_CD 와 CO 에 등록된 ORG_CD 가 틀리기 때문에 같은 조직이 맞는지
//		// 집행팀장의 ID 로 검증한다.
//		cf_getCouserInfo();
//		// 최종결재자와 비교하기 때문에 작성자팀장 결재선 생성 후에 돌릴것
//		if(v_tSignUserCd != undefined && v_Chief != undefined){
//
//		v_dSignType // 타 집행팀 여부
//		v_dSignUserCd // 타 집행팀장 ORG
//		v_dSignUserId // 타 집행티장 ID
//
//
//		// 출장자 출장자팀
//		var tripUser; // 출장자
//		var tripUserNm;
//		var tripUserTeam; // 출장자팀
//		var tripUserTeamNm;
//
//		// 출장장소
//		val
//		// 출장목적
//		val
//		// 시작일, 종료일
//		input[name='startDate']
//		input[name='endDate']
//		tripDate // 계산값
//		// 날짜 계산하여 셋팅할 것
//
//		// 업무대행자 업무대행자팀
//		v_DutyAgncUserId
//		v_DutyAgncOrgCd
//		// 지불예정일
//		input[name='payDate']
//		// 회사차량 사용여부
//		val
//		// 교육출장 여부
//		v_EduBustrYn
//		// check 처리 하고 로직 처리 할 것
//		// 일당비 제외 여부
//		v_DdExpnExcl
//		// check 처리 하고 로직 처리 할 것
//		// 숙박비 제외 여부
//		v_AccomoExpnExcl
//		// check 처리 하고 로직 처리 할 것
//
//		// 교통비 수령인
//		v_ReceiptUser
//		v_ReceiptUserNm

		// 수령인 금액
//		$("#rAmount").val()

		//DetailData (DataSet 의 View 상태를 제어하기 힘듬, 일괄 삭제 후 Insert 처리 할 것)

		if(confirm("작성 중인 문서를 저장하시겠습니까?")){

			// 버튼 숨김
			$(".btn").hide();
			// Modal 처리
			gf_InitSpinner(top.$('body'));

			var docSts = "D16";

			var docType;
			if($("input[name='docType']:checked").val() != undefined){
				docType = $("input[name='docType']:checked").val();
			}else{
				docType = "";
			}

			var data = {
					bdgtType : v_BdgtType,
					aufnr : v_Aufnr,
					kText : v_KText,
					kostV : v_Kostv,
					kostVNm : v_Kostvnm,
					chief : v_Chief,
					chiefNm : v_Chiefnm,
					tripUser : tripUser,
					tripUserNm : tripUserNm,
					tripUserTeam : tripUserTeam,
					tripUserTeamNm : tripUserTeamNm,
					tripUserApptOrgCd : tripUserApptOrgCd,
					tripTarget : $("#tripTarget").val(),
					tripPurpose : $("#tripPurpose").val(),
					startDate : $("input[name='startDate']").val(),
					endDate : $("input[name='endDate']").val(),
					tripDate : tripDate,
					dutyAgncUserId : v_DutyAgncUserId,
					dutyAgncUserNm : v_DutyAgncUserNm,
					dutyAgncOrgCd : v_DutyAgncOrgCd,
					payDate : $("input[name='payDate']").val(),
					ordDate : $("input[name='ordDate']").val(),
					compCar : $("#compCar").val(),
					eduBustrYn : v_EduBustrYn,
					eduTripAmt : $("#eduTripAmt").val(),
					ddExpnExcl : v_DdExpnExcl,
					accomoExpnExcl : v_AccomoExpnExcl,
					receiptUser : v_ReceiptUser,
					receiptUserNm : v_ReceiptUserNm,
					rAmount : $("#rAmount").val(),
					drafterId : gv_userId,
					drafterNm : gv_userNm,
					drafterOrgCd : gv_orgCd,
					drafterOrgNm : gv_orgNm,
					drafterRpswrkCd : gv_userRpswrkCd,
					drafterPositCd : gv_userPositCd,
					docType : docType,
					signln : JSON.stringify(ds_Signln.getAllData()), // 20150721 전결라인 저장
					v_dSignType : v_dSignType,
					v_dSignUserCd : v_dSignUserCd,
					v_dSignUserId : v_dSignUserId,
					v_dSignUserNm : v_dSignUserNm,
					v_dSignOrgNm : v_dSignOrgNm,
					v_dSignRpwrkNm : v_dSignRpwrkNm,
					v_dSignRpswrkCd : v_dSignRpswrkCd,
					v_dSignPositCd : v_dSignPositCd,
					v_dSignPositNm : v_dSignPositNm,
					v_hSignType : v_hSignType,
					v_hSignUserCd : v_hSignUserCd,
					v_hSignUserId : v_hSignUserId,
					v_hSignUserNm : v_hSignUserNm,
					v_hSignUserPositCd : v_hSignUserPositCd,
					v_hSignUserRpswrkCd : v_hSignUserRpswrkCd,
					v_dHSignUserCd : v_dHSignUserCd,
					v_dHSignUserId : v_dHSignUserId,
					v_dHSignUserNm : v_dHSignUserNm,
					v_dHSignOrgNm : v_dHSignOrgNm,
					v_dHSignRpwrkNm : v_dHSignRpwrkNm,
					v_dHSignRpswrkCd : v_dHSignRpswrkCd,
					v_dHSignPositCd : v_dHSignPositCd,
					v_dHSignPositNm : v_dHSignPositNm,
					v_tSignUserCd : v_tSignUserCd,
					v_tSignUserId : v_tSignUserId,
					v_IsOrgBoss : v_IsOrgBoss,
					v_IsOfficer : v_IsOfficer,
					noReceipt : $("#noReceipt:checked").size(),
					v_isSignEdit : v_isSignEdit
			};

			var detailData = cf_getTranspDetail();

			var ds_DetailData = new DataSet();

			for(var i = 0; i < detailData.length; i++){
				ds_DetailData.add(detailData[i]);
			}


			var draftDataSet;
			// 회사차량 사용시 DetailData 를 입력하지 않는다.
			if($("#compCar").val() == "Y"){
				draftDataSet = {};
			}else{
				draftDataSet = {
						ds_DetailData : ds_DetailData.getAllData("U")
				};
			}


			var param = {
					docNo : v_DocNo,
					docSts : docSts,
					ifParam : JSON.stringify(data)
			};

//			if(v_DocSts == "D16"){
//				gf_Transaction("SAVE_INNER_TRIP_DOC_SAVE", "/trip/innerTrip/updateInnerTripDocSave.xpl", param, draftDataSet, "f_Callback", true);
//			}else{
//				gf_Transaction("SAVE_INNER_TRIP_DOC_SAVE", "/trip/innerTrip/saveInnerTripDocSave.xpl", param, draftDataSet, "f_Callback", true);
//			}

			gf_Transaction("SAVE_INNER_TRIP_DOC_SAVE", "/trip/innerTrip/updateInnerTripDocSave.xpl", param, draftDataSet, "f_Callback", true);
		}
	});

	// 문서를 임시저장 처리 한다.
	// 이미 적상되어진 같을 JSON Object 형식으로 IF_PARAM 컬럼에 저장한다.
	$('#innerTripSave').click(function(){
		if(confirm("작성 중인 문서를 저장하시겠습니까?")){
			v_CanSaveDraft = '1';	//첨부파일 콜백 작업시 '저장' 구분 (1:저장, 2:상신)
			gf_onFileUpload();
		}
	});

	$("#innerTripDelete").click(function(){
		if(confirm("이 문서를 삭제하시겠습니까?")){

			var param = {
					docNo : v_DocNo
			};

			gf_Transaction("DELETE_INNER_TRIP_DOC", "/trip/innerTrip/deleteInnerTripSavedDoc.xpl", param, {}, "f_Callback", true);
		}

	});

	$("#innerTripSign").click(function(){
		//현장관리책임자나 현장책임자가 없는 조직의 사용자를 위한 결재선 지정 기능
		// 1. 일반 사용자 지정 팝업 (최종 결재자 선택)
		// 2. 선택된 최종 결재자의 조직코드를 구함
		// 3. 현장인지 아닌지 판단
		// 4. 현장이라면 현장책임자를 찾는다.

//		var callbackFunc = "f_SelectSignInfo";
//		window.open(gv_ContextPath + "/common/jsp/comp/userSelect.jsp?userNm=" + "&callbackFunc="+callbackFunc,"","toolbar=no,scrollbars=no,width=600,height=420");

		// 2016-01-21 IE 11 에서 결재자지정 기능 이상으로 통합결재 / 해외출장식 결재선 변경으로 교체
		var datas = {
			signln : ds_Signln.getAll()
		};
		gf_PostOpen("/common/jsp/sign/signUserSelect.jsp", null, "toolbar=no,scrollbars=no,width=1020,height=590", datas, true, true);


	});
/*
	$("#rAmount").bind('keyup', function(e) {
		var targetVal = e.currentTarget.value;

		var totalAmt = tripAmt1 + tripAmt2;

		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}
		if(parseInt(targetVal) > totalAmt){
			gf_AlertMsg("교통비 총 한도를 넘을 수 없습니다.");
			e.currentTarget.value = gf_AmtFormat(totalAmt);
			return;
		}
		e.currentTarget.value = gf_AmtFormat(targetVal);

	});
*/
	$("#rAmount").bind('keyup', function(e) {
		var targetVal = e.currentTarget.value;

		var totalAmt = v_totalAmt;

		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}
		if(parseInt(targetVal) > totalAmt){
			gf_AlertMsg("최종신청금액을 넘을 수 없습니다.");
			e.currentTarget.value = gf_AmtFormat(totalAmt);
			return;
		}
		e.currentTarget.value = gf_AmtFormat(targetVal);

	});

	$("#eduTripAmt").bind('keyup', function(e) {

		var td = tripDate;



		var targetVal = e.currentTarget.value;

		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}

		var dayAmAmtText = parseInt(targetVal) * tripDate;

		if(td == undefined){
			$("#dayAmAmt").text("0원");
		}else{
			$("#dayAmAmt").text(gf_AmtFormat(dayAmAmtText) + "원");
		}

		e.currentTarget.value = gf_AmtFormat(targetVal);

	});

	//숙박일수 클릭
	$("#genAccomoDcnt").bind('keyup', function(e) {

		//alert('숙박일수2');
		f_GetLimitAccomoAmt();	//숙박비 한도

	});

	//숙박비 클릭
	$("#accomoAmt").bind('keyup', function(e) {
		f_GetTotAccomoAmt();	//숙박비 합계 계산
	});

	//식비 클릭
	$("#eexpAmt").bind('keyup', function(e) {
		f_GetTotAccomoAmt();	//숙박비 합계 계산
	});

	//잡비 클릭
	$("#etcAmt").bind('keyup', function(e) {
		f_GetTotAccomoAmt();	//숙박비 합계 계산
	});

	$("#totAccomoAmt").bind('change', function(e) {
		alert('a');
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
/*
	//2021년도로 넘어감에 따른 증빙일자 안내메세지(시작)
	var alert_msg = "";
	alert_msg += "----------  안내  ----------\n";
	alert_msg += "\n";
	alert_msg += "증빙일자\n";
	alert_msg += "\n";
	alert_msg += "출발일 기준 2020-11-04 까지 현재시스템에서 신청 가능합니다 \n";
	alert_msg += "\n";
	alert_msg += "출발일 기준 2020-11-05 부터 현재시스템에서 신청 할 수 없습니다 \n";
	alert_msg += "\n";
	alert_msg += "(2020-11-04 까지) 출장 전 경비신청\n";
	alert_msg += "(2020-11-05 부터) 출장 후 실비정산\n";
	alert_msg += "\n";
	alert_msg += "2020-11-05 부터 발생하는 출장에 대해 \n";
	alert_msg += "\n";
	alert_msg += "근태는 e-HR시스템을 통해 출장 근태 신청하시기 바랍니다 \n";
	alert_msg += "\n";
	alert_msg += "실비정산은 2020-11-13(금) 부터 현재 메뉴에서 정산해 주시기 바랍니다\n";
	alert_msg += "\n";
	alert_msg += "문의 : 인사팀 윤은진 대리 (02-2288-3813)\n";
	alert(alert_msg);
	//2021년도로 넘어감에 따른 증빙일자 안내메세지(시작)
*/
	/**
	 * 결재선 초기화
	 */
	//gf_SignlnInit(v_DocNo);
	//gf_AddSignln("1202564", "1DFUR", "T02", 2);

//	var params = {
//			orgCd : gv_orgCd,
//			orgCls : "팀/현장",
//			userId : gv_userId
//	};

	//ds_Signln.setAllData(resultData.ds_SignlnForExcluRegl);

	//gf_AssembleSignln(ds_Signln);

	ds_ChiefCd.setAllData(gv_ChiefCd);

	// 관리자 일 경우 버튼 작동
	if(gv_AuthList.auth[0].code.match("RO_CMAS_CO_TRIP_ADM") != null) $("#sapConfN").show();

	// 초기값 로딩 Modal처리
	gf_InitSpinner(top.$('body'));
	// 임시저장 문서일 경우 IF_PARAM 에 저장된 JSON 값을 셋팅 한다.
	if(v_DocSts == "D16"){

		$("#innerTripDelete").show();

		v_DocNo = v_CmasId; // 이미 생성되어진 v_CmasId;
  		var docNoText = v_DocNo.split("-");
  		$("#docNo").text(docNoText[1] + "-" + docNoText[2]);

  		var searchParams = {
  				docNo : v_DocNo
  		};

  		// IF_PARAM 을 조회하여 문서에 셋팅한다.
  		gf_Transaction("SELECT_SAVED_DOC_INFO", "/trip/innerTrip/retrieveSavedDocInfo.xpl", searchParams, {}, "f_Callback", true);



	}else{
		// 최초 작성이므로 기본 정보를 조회한다.

		var drafterText = gv_userId + " " + gv_userNm;
		$("#drafter").text(drafterText);

		//소속팀 셋팅
		var drafterOrgNm = gv_orgNm + " (" + gv_orgCd + ")";
		$("#drafterOrgNm").text(drafterOrgNm);

		//소속팀 셋팅
		var tripUserOrgNm = gv_orgNm + " (" + gv_orgCd + ")";
		$("#tripUserOrgNm").text(tripUserOrgNm);

		//출장자 셋팅
		$("#tripUserId_id").val(gv_userId);
		$("#tripUserId_name").val(gv_userNm);

		tripUser = gv_userId;
		tripUserNm = gv_userNm;
		tripUserTeam = gv_orgCd;
		tripUserTeamNm = gv_orgNm;
		tripUserPositCd = gv_userPositCd;
		tripUserRpswrkCd = gv_userRpswrkCd;
		tripUserApptOrgCd = gv_orgCd;

		// 작성자 셋팅
		sDrafterId = gv_userId;
		sDrafterNm = gv_userNm;
		sDrafterOrgCd = gv_orgCd;
		sDrafterOrgNm = gv_orgNm;
		sDrafterPositCd = gv_userPositCd;
		sDrafterRpswrkCd = gv_userRpswrkCd;

		var today = new Date();
		today.setDate(today.getDate()+3);

		var yyyy = today.getFullYear();
		var MM = today.getMonth()+1;
		MM = MM < 10?"0"+MM:MM;
		var dd = today.getDate();
		dd = dd < 10?"0"+dd:dd;

		// 기표,증빙일자 오늘 날짜로 셋팅
		$("input[name='ordDate']").val($.datepicker.formatDate("yy-mm-dd", new Date()));

  		// 지불예정일
		//($.datepicker.parseDate("yymmdd" , new Date());
  		$("input[name='payDate']").val( yyyy + "-" + MM  + "-" + dd);


//		alert(tripUser + " " + tripUserTeam);

		var searchParams = {
				Sabun : gv_userId
		};

		if(gv_userId == undefined){		//userId가 undefined이면 경비 조회X

		}
		else{
			gf_Transaction("SELECT_EXPENSE_STANDARD", "/trip/eai/getSendTripExpenseStandard.xpl", searchParams, {}, "f_Callback", true);
			gf_Transaction("SELECT_CMAS_ID", "/trip/innerTrip/getCmasId.xpl", {}, {}, "f_Callback", true);
			cf_AppendTranspDetail();
		}
	}
}

/**
* @class Transaction 처리 후 수행되는 Callback 함수
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function f_Callback(strSvcId, obj, resultData){

	// 체크로직을 확인할 때에는 정상 메세지 나오지 않도록 한다
	if (strSvcId != null && strSvcId != "CHECK_DRAFT_DUPLICATION" && strSvcId != "CHECK_EHR_APP_YN" && strSvcId != "CHECK_CO_FILE_YN"){

	  // transaction의 정상 처리 여부를 판단한다.
	  if (!gf_ChkTransaction(strSvcId, resultData, true )) {
		  return;
	  }

	}

	  switch(strSvcId) {
	  	case "DELETE_INNER_TRIP_DOC" :
	  		// 문서가 성공적으로 처리되면 문서 리스트를 다시 조회하여 갱신하여 준다.
	  		if ( !gf_IsNull(v_CallbackFunc) ) {
	  			var openCallbackFunc = "opener."+v_CallbackFunc;
	  	    	eval(openCallbackFunc + "();");
	  	    }

	  		self.close();
	  		break;
	  	case "SELECT_SAVED_DOC_INFO" :
	  		//저장된 데이터 이므로 세션이 아닌 출장자정보에서 셋팅해야한다.
	  		ds_SavedDoc.setAllData(resultData.ds_DocData);

	  		v_IsSavedDoc = "Y";

	  		// 갑지조회 추가
	  		$("#genAccomoDcnt").val(ds_SavedDoc.get(0, "genAccomoDcnt"));	//알반숙박일수
	  		$("#accomoAmt").val(ds_SavedDoc.get(0, "accomoAmt"));			//숙박금액
	  		$("#eexpAmt").val(ds_SavedDoc.get(0, "eexpAmt"));				//식대금액
	  		$("#etcAmt").val(ds_SavedDoc.get(0, "etcAmt"));					//기타금액
	  		$("#totAmt").val(ds_SavedDoc.get(0, "totAmt"));					//총금액
	  		$("#rem").val(ds_SavedDoc.get(0, "rem"));						//비고

	  		var data = JSON.parse(ds_SavedDoc.get(0, "ifParam"));

	  		// 전역 변수 및 데이터 셋팅
	  		// 예산관련
	  		v_BdgtType = data.bdgtType;
	  		v_Aufnr = data.aufnr;
	  		v_KText = data.kText;
	  		v_Kostv = data.kostV;
	  		v_Kostvnm = data.kostVNm;
	  		v_Chief = data.chief;
	  		v_Chiefnm = data.chiefNm;

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


	  		if(data.docType == "H"){
	  			$("input[name='docType'][value='H']").attr("checked", "true");
	  		}else if(data.docType == "G"){
	  			$("input[name='docType'][value='G']").attr("checked", "true");
	  		}

	  		// 출장자 출장자팀
	  		tripUser = data.tripUser;
	  		tripUserNm = data.tripUserNm;
	  		tripUserTeam = data.tripUserTeam;
	  		tripUserTeamNm = data.tripUserTeamNm;
	  		tripUserApptOrgCd = data.tripUserApptOrgCd;

	  		// 출장자 소속팀
			var tripUserOrgNm = tripUserTeamNm + " (" + tripUserTeam + ")";
			$("#tripUserOrgNm").text(tripUserOrgNm);




	  		// 출장장소
	  		$("#tripTarget").val(data.tripTarget);
	  		// 출장 목적
	  		$("#tripPurpose").val(data.tripPurpose);
	  		// 출장 시작일 종료일
	  		$("input[name='startDate']").val(data.startDate);
	  		$("input[name='endDate']").val(data.endDate);
	  		tripDate = data.tripDate;

	  		if(tripDate != undefined){
	  			var betDayText = "("  + (tripDate-1) + "박 "+ tripDate + "일)";
				$("#betDate").text(betDayText);

	  		}

	  		// 업무대행자
	  		v_DutyAgncUserId = data.dutyAgncUserId;
	  		v_DutyAgncUserNm = data.dutyAgncUserNm;
	  		v_DutyAgncOrgCd = data.dutyAgncOrgCd;

	  		$("#tripAgent_id").val(v_DutyAgncUserId);
	  		$("#tripAgent_name").val(v_DutyAgncUserNm);

	  		//기표/증빙일자
	  		$("input[name='ordDate']").val(data.ordDate);
	  		// 지불예정일
	  		$("input[name='payDate']").val(data.payDate);

	  		// 회사차량
	  		$("#compCar").val(data.compCar);

	  		// 교육비 일당비 제외
	  		v_EduBustrYn = data.eduBustrYn;
	  		if(v_EduBustrYn == "Y"){
	  			$("#eduTripAmt").val(data.eduTripAmt);
	  			$("#eduTripChk").attr("checked", "true");

	  			$("#dayAmAmt").text("");
				$("#eduTripChkText").show();

				// 교육비 표준액 * tripDate 금액 표기
				var eduVal = $("#eduTripAmt").val();

				for(var k = 0; k < eduVal.length; k++){
					eduVal = eduVal.replace(",", "");
				}

				var eduAmtText = parseInt(eduVal) * tripDate;

				if(tripDate == undefined){
					$("#dayAmAmt").text("0원");
				}else{
					$("#dayAmAmt").text(gf_AmtFormat(eduAmtText) + "원");
				}
	  		}

			// 일당비 제외 숙박비 제외
	  		v_DdExpnExcl = data.ddExpnExcl;
	  		v_AccomoExpnExcl = data.accomoExpnExcl;

	  		if(v_AccomoExpnExcl == "N"){
	  			var nightAmAmt = nightAm * (tripDate-1);
  				var nightAmAmtText = gf_AmtFormat(nightAmAmt) + "원";
  				if(tripDate == undefined) nightAmAmtText = "0원";
  				$("#nightAmAmt").text(nightAmAmtText);
	  		}else if(v_AccomoExpnExcl == "Y"){
	  			$("#nightAmAmt").text("0원");
	  			//체크박스 체크
	  			$("#excptNight").attr("checked", "true");
	  		}

	  		if(v_DdExpnExcl == "N"){
	  			var dayAmAmt = parseInt(dayAm) * parseInt(tripDate);
  				var dayAmAmtText = gf_AmtFormat(dayAmAmt) + "원";
  				if(tripDate == undefined) dayAmAmtText = "0원";
  				$("#dayAmAmt").text(dayAmAmtText);
	  		}else if(v_DdExpnExcl == "Y"){
	  			$("#dayAmAmt").text("0원");
	  			//체크박스 체크
	  			$("#excptDay").attr("checked", "true");
	  		}

	  		f_GetTotalAmount();

	  		// 교통비 수령
	  		v_ReceiptUser = data.receiptUser;
	  		v_ReceiptUserNm = data.receiptUserNm;

	  		$("#receiptUser_id").val(v_ReceiptUser);
	  		$("#receiptUser_name").val(v_ReceiptUserNm);

	  		// 수령금액
	  		$("#rAmount").val(data.rAmount);

	  		//작성자 정보
	  		sDrafterId = data.drafterId;
	  		sDrafterNm = data.drafterNm;
	  		sDrafterOrgCd = data.drafterOrgCd;
	  		sDrafterOrgNm = data.drafterOrgNm;
			sDrafterRpswrkCd = data.drafterRpswrkCd;
			sDrafterPositCd = data.drafterPositCd;

			// 작성자 표시
			var drafterText = sDrafterId + " " + sDrafterNm;
			$("#drafter").text(drafterText);

	  		// 이미 작성되어진 Detail Data를 삽입한다.
//			cf_AppendTranspDetail();

	  		ds_SavedDetailData.setAllData(resultData.ds_DetailData);

	  		var totalAmt = 0;

	  		for(var i = 0; ds_SavedDetailData.size() > i; i++){

	  			var amt = cf_AppendSavedTranspDetail(ds_SavedDetailData.get(i));

	  			totalAmt = totalAmt + parseInt(amt);

	  			// readonly 처리
//	  			$("#transpDetail tr input[name='tStart']").attr("readonly", "true");
//	  			$("#transpDetail tr input[name='tEnd']").attr("readonly", "true");
//	  			$("#transpDetail tr select[name='tType']").attr("disabled", "true");
//	  			$("#transpDetail tr select[name='tRound']").attr("disabled", "true");
//	  			$("#transpDetail tr input[name='tDist']").attr("readonly", "true");
//	  			$("#transpDetail tr input[name='tAmount']").attr("readonly", "true");

			}

//	  		$("#tAmountTotal").text(gf_AmtFormat(totalAmt));

	  		// 저장된 문서  로드 후 최초 1회 교통비 계산
	  		// 이후부터는 Event Bind 된 항목에서 실시간으로 계산한다.
	  		// 처리하지 않을 시 임시저장 연 후 바로 상신하면 교통비를 입력하라는 SAP 에러 발생
	  		var tAmount = $("input[name='tReqAmount']");
			var total = 0;
			var airTotal = 0;
			var etcTotal = 0;
			for(var i = 0; i < tAmount.size(); i++){

//7788
				// 차량사용량 활성화(시작)
				var v_tAmtType_new = $(tAmount[i]).parent().parent().find("select[name='tType']").val();
				var v_tCarUseQty = $("input[name='tCarUseQty']");
				if(v_tAmtType_new == "8"){	//차량
					//alert('1차');
					$(v_tCarUseQty[i]).parent().parent().find("input[name='tCarUseQty']").removeAttr("disabled");	//차량사용량 활성화
					$(v_tCarUseQty[i]).parent().parent().find("input[name='tCarUseQty']").removeAttr("readonly");	//차량사용량 활성화
					$(v_tCarUseQty[i]).parent().parent().find("input[name='tDist']").removeAttr("disabled");	//이동거리 활성화
					$(v_tCarUseQty[i]).parent().parent().find("input[name='tDist']").removeAttr("readonly");	//이동거리 활성화
				}else if(v_tAmtType_new != "8"){	//차량 외
					//alert('1차외');
					$(v_tCarUseQty[i]).parent().parent().find("input[name='tCarUseQty']").attr("disabled", "true");	//차량사용량 비활성화
					$(v_tCarUseQty[i]).parent().parent().find("input[name='tCarUseQty']").attr("readonly", "true");	//차량사용량 비활성화
					$(v_tCarUseQty[i]).parent().parent().find("input[name='tDist']").attr("disabled", "true");	//이동거리 비활성화
					$(v_tCarUseQty[i]).parent().parent().find("input[name='tDist']").attr("readonly", "true");	//이동거리 비활성화
				}
				// 차량사용량 활성화(종료)


				var tVal = tAmount[i].value;
				if(tVal == "") continue;
				for(var j = 0; j < tVal.length; j++){
					tVal = tVal.replace(",", "");
				}
				total = total + parseInt(tVal);
				var tAmtType = $(tAmount[i]).parent().parent().find("select[name='tType']").val();
				if(tAmtType == "1"){
					airTotal = airTotal + parseInt(tVal);
				}else if(tAmtType != "1"){
					etcTotal = etcTotal + parseInt(tVal);
				}
			}

			$("#tAmountTotal").text(gf_AmtFormat(total) + "원");
			$("#tAmountTotal1").text(gf_AmtFormat(airTotal) + "원");
			$("#tAmountTotal2").text(gf_AmtFormat(etcTotal) + "원");

			tripAmt1 = airTotal;
			tripAmt2 = etcTotal;

			f_GetTotalAmount();

	  		//소속팀 셋팅
			var drafterOrgNm = sDrafterOrgNm + " (" + sDrafterOrgCd + ")";
			$("#drafterOrgNm").text(drafterOrgNm);

			//출장자 셋팅
			$("#tripUserId_id").val(tripUser);
			$("#tripUserId_name").val(tripUserNm);


			//소속팀 셋팅
			var tripUserOrgNm = tripUserTeamNm + " (" + tripUserTeam + ")";
			$("#drafterOrgNm").text(tripUserOrgNm);


			if(data.signln == undefined || data.signln == "" || data.signln == null){
				// 저장된 결재선이 존재하지 않으므로 다시 결재선을 조회하여 셋팅한다.
				v_IsSavedDoc = "N";
			}else{
				// 결재선 셋팅
				ds_Signln.setAllData(JSON.parse(data.signln));

				// 결재선과 관련된 전역변수 셋팅
				v_dSignType = data.v_dSignType;
				v_dSignUserCd = data.v_dSignUserCd;
				v_dSignUserId = data.v_dSignUserId;
				v_dSignUserNm = data.v_dSignUserNm;
				v_dSignOrgNm = data.v_dSignOrgNm;
				v_dSignRpwrkNm = data.v_dSignRpwrkNm;
				v_dSignRpswrkCd = data.v_dSignRpswrkCd;
				v_dSignPositCd = data.v_dSignPositCd;
				v_dSignPositNm = data.v_dSignPositNm;
				v_hSignType = data.v_hSignType;
				v_hSignUserCd = data.v_hSignUserCd;
				v_hSignUserId = data.v_hSignUserId;
				v_hSignUserNm = data.v_hSignUserNm;
				v_hSignUserPositCd = data.v_hSignUserPositCd;
				v_hSignUserRpswrkCd = data.v_hSignUserRpswrkCd;
				v_dHSignUserCd = data.v_dHSignUserCd;
				v_dHSignUserId = data.v_dHSignUserId;
				v_dHSignUserNm = data.v_dHSignUserNm;
				v_dHSignOrgNm = data.v_dHSignOrgNm;
				v_dHSignRpwrkNm = data.v_dHSignRpwrkNm;
				v_dHSignRpswrkCd = data.v_dHSignRpswrkCd;
				v_dHSignPositCd = data.v_dHSignPositCd;
				v_dHSignPositNm = data.v_dHSignPositNm;
				v_tSignUserCd = data.v_tSignUserCd;
				v_tSignUserId = data.v_tSignUserId;
				v_IsOrgBoss = data.v_IsOrgBoss;
				v_IsOfficer = data.v_IsOfficer;
				v_isSignEdit = data.v_isSignEdit;


				if(v_IsOfficer == "Y"){
					$("#receiptTr").show();

		  			$("#receiptUser_btn").show();
		  			$("#rAmountSpan").show();

		  			$("#receiptUser_id").removeAttr("readonly");
		  			$("#receiptUser_name").removeAttr("readonly");

		  			$("#excptDayChkSpan").show();
		  			$("#excptNightChkSpan").show();
		  			$("#eduTripChkSpan").hide();

		  			$("#innerTripSign").show();

		  			// 교통비미수령 지정 여부
		  			if(data.noReceipt != undefined){
		  				if(data.noReceipt > 0){
		  					// 미지정
		  					$("#noReceipt").attr("checked", "true");
		  					$("#rAmount").hide();
		  					$("#receiptUser").hide();
		  				}
		  			}

				}else{
					$("#receiptTr").hide();

		  			$("#receiptUser_btn").hide();
		  			$("#receiptUser_id").attr("readonly", "true");
		  			$("#receiptUser_name").attr("readonly", "true");
		  			$("#rAmountSpan").hide();

		  			$("#excptDayChkSpan").hide();
		  			$("#excptNightChkSpan").show();
		  			$("#eduTripChkSpan").show();
				}

			}

			//첨부파일
	  		gf_retrieveFileList(ds_SavedDoc.get(0,"fileAtchId"));
	  		v_FileAtchId = ds_SavedDoc.get(0,"fileAtchId");

			var searchParams = {
//					Sabun : sDrafterId
					Sabun : data.tripUser
			};

			if (data.tripUser == undefined){

			}
			else{
				gf_Transaction("SELECT_EXPENSE_STANDARD", "/trip/eai/getSendTripExpenseStandard.xpl", searchParams, {}, "f_Callback", true);
			}

			f_GetLimitAccomoAmt();	//숙박비 한도
			f_GetTotAccomoAmt();	//숙박비 합계 계산

			$("#viewOld1").hide();	//일당비(구항목)
			$("#viewOld2").hide();	//숙박비(구항목)
			$("#viewOld3").hide();	//합계(구항목)
			//$("#compCar").hide();	//회사차량 사용 여부선택

	  		break;
	  	case "SELECT_EXPENSE_STANDARD" :

// 2021.04.14 일당 / 숙박 표준액 가져올수 없다는 메세지 안나오도록 수정 (시작)
/*

	  		//alert(resultData.output1[0]);
	  		if(resultData.output1[0].Nightam == undefined || resultData.output1[0].Dayam == undefined){
	  			gf_AlertMsg("일당 / 숙박 표준액을 가져올 수 없습니다.");
	  		}else{
	  			nightAm = parseInt(resultData.output1[0].Nightam);
		  		dayAm = parseInt(resultData.output1[0].Dayam);

		  		var nightAmText = "표준액 : " + gf_AmtFormat(nightAm) + "원";
		  		var dayAmText = "표준액 : " + gf_AmtFormat(dayAm) + "원";

		  		$("#nightAm").text(nightAmText);
		  		$("#dayAm").text(dayAmText);

		  		if(tripDate == undefined){
					$("#dayAmAmt").text("0원");
					$("#nightAmAmt").text("0원");
				}else{
					var dayAmAmt = parseInt(dayAm) * parseInt(tripDate);
					var nightAmAmt = parseInt(nightAm) * parseInt(tripDate-1);
					var dayAmAmtText = gf_AmtFormat(dayAmAmt) + "원";
					var nightAmAmtText = gf_AmtFormat(nightAmAmt) + "원";
					$("#dayAmAmt").text(dayAmAmtText);
					$("#nightAmAmt").text(nightAmAmtText);
				}



		  		if(v_AccomoExpnExcl == "N"){
		  			var nightAmAmt = nightAm * (tripDate-1);
	  				var nightAmAmtText = gf_AmtFormat(nightAmAmt) + "원";
	  				if(tripDate == undefined) nightAmAmtText = "0원";
	  				$("#nightAmAmt").text(nightAmAmtText);
		  		}else if(v_AccomoExpnExcl == "Y"){
		  			$("#nightAmAmt").text("0원");
		  			//체크박스 체크
		  			$("#excptNight").attr("checked", "true");
		  		}


		  		if(v_DdExpnExcl == "N"){
		  			var dayAmAmt = parseInt(dayAm) * parseInt(tripDate);
	  				var dayAmAmtText = gf_AmtFormat(dayAmAmt) + "원";
	  				if(tripDate == undefined) dayAmAmtText = "0원";
	  				$("#dayAmAmt").text(dayAmAmtText);
		  		}else if(v_DdExpnExcl == "Y"){
		  			$("#dayAmAmt").text("0원");
		  			//체크박스 체크
		  			$("#excptDay").attr("checked", "true");
		  		}

		  		// 교육 출장이라면 일당비 대신 교육비 표준액 기준으로 일당비를 산정한다.
		  		if(v_EduBustrYn == "Y"){
					// 교육비 표준액 * tripDate 금액 표기
					var eduVal = $("#eduTripAmt").val();

					for(var k = 0; k < eduVal.length; k++){
						eduVal = eduVal.replace(",", "");
					}

					var eduAmtText = parseInt(eduVal) * tripDate;

					if(tripDate == undefined){
						$("#dayAmAmt").text("0원");
					}else{
						$("#dayAmAmt").text(gf_AmtFormat(eduAmtText) + "원");
					}



		  		}

		  		f_GetTotalAmount();

	  		}

*/
//2021.04.14 일당 / 숙박 표준액 가져올수 없다는 메세지 안나오도록 수정 (끝)

	  		if(v_IsSavedDoc == "Y"){
	  			gf_AssembleSignln(ds_Signln);
	  			// 이후부터는 다시 결재선 재조회
	  			v_IsSavedDoc = "N";
	  			$(".ajax_overlay").remove();

	  		}else{

	  			var isParam = {
		  				userId : tripUser,
		  				orgCd : tripUserTeam,
		  				drafterId : tripUser,
		  				drafterOrgCd : tripUserTeam
		  		};

		  		gf_Transaction("SELECT_IS_SPOT_MGMT", "/trip/innerTrip/retrieveIsSpotMgmt.xpl", isParam, {}, "f_Callback", true);

	  		}

	  		break;
	  	case "SELECT_IS_SPOT_MGMT" :

	  		//현장 소속인지 결과를 셋팅한다.
	  		//현장 소속이라면 현장소장/팀장 협의 결재선이 추가된다.

	  		// 0명 이상이라면 현장소속
	  		//resultData.ds_IsSpot

	  		if(resultData.ds_IsSpot != null){
	  			v_hSignType = "Y"; // 현장소장 Y / N
	  			v_hSignUserCd = resultData.ds_IsSpot[0].orgCd; // 현장관리책임자 ORG
	  			v_hSignUserId = resultData.ds_IsSpot[0].userId; // 현장관리책임자 ID
	  			v_hSignUserNm = resultData.ds_IsSpot[0].userKnm;
	  			v_hSignUserPositCd = resultData.ds_IsSpot[0].userPositCd;
	  			v_hSignUserRpswrkCd = resultData.ds_IsSpot[0].userRpswrkCd;

	  			// 2015-05-11
	  			// 만약 출장자가 현장관리책임자라면 중간결재가 생성되지 않는다.
	  			if(v_hSignUserId == tripUser) v_hSignType = "N";

	  			if(resultData.ds_IsSpotBoss != null){
		  			v_tSignUserCd = resultData.ds_IsSpotBoss[0].orgCd; // 현장소장 ORG
		  			v_tSignUserId = resultData.ds_IsSpotBoss[0].userId; // 현장소장 ID
	  			} else {
	  				if(resultData.ds_DrafterOrgBoss == null){
	  					gf_AlertMsg("팀장/현장소장이 존재하지 않습니다. 최종결재자를 지정하세요.");

	  					var callbackFunc = "f_NotExistOrgBossSelect";
	  					window.open(gv_ContextPath + "/common/jsp/comp/userSelect.jsp?userNm=" + "&callbackFunc="+callbackFunc,"","toolbar=no,scrollbars=no,width=600,height=420");
	  				}else{
	  					v_tSignUserCd = resultData.ds_DrafterOrgBoss[0].orgCd; // 최종 결재자 ORG
			  			v_tSignUserId = resultData.ds_DrafterOrgBoss[0].userId; // 최종결재자 ID
	  				}
	  			}
	  		}else{
	  			// 현장이 아닌경우
	  			v_hSignType = "N";


	  			// 일반적인 경우에는 대체로 최종결재자 ID가 만들어지지만
	  			// 출장자가 임원일 경우 같은 조직에 팀장이 없으므로
	  			// 따로 처리를 해줘야한다.
	  			if(resultData.ds_Officer != null || resultData.ds_Officer == undefined){
		  			// 임원일 경우
//	  				v_tSignUserCd = resultData.ds_DrafterOrgBoss[0].orgCd; // 최종 결재자 ORG
//		  			v_tSignUserId = resultData.ds_DrafterOrgBoss[0].userId; // 최종결재자 ID
	  				// 본인 전결
//	  				본인^본인
//	  	            (기안자^결재자)
	  				// 타집행팀 일 경우 다른 팀 예산 사용 못하도록 맞기
	  				// 본인 전결일 경우
	  				// 전결로 이 문서를 상신할 수 없습니다. 다른 작성자로 상신하세요.
//	  				v_tSignUserCd = tripUserTeam;
//	  				v_tSignUserId = tripUser;
		  		}else{

		  			// 현장관리책임자 존재 유무로 현장임을 체크하지만 현장관리책임자도 없고 팀장/현장소장도 없는 경우가 있으므로
		  			// 예외처리로 자신의 결재자를 지정하게 한다.
		  			if(resultData.ds_DrafterOrgBoss == null){

		  				gf_AlertMsg("팀장/현장소장이 존재하지 않습니다. 최종결재자를 지정하세요.");

	  					var callbackFunc = "f_NotExistOrgBossSelect";
	  					window.open(gv_ContextPath + "/common/jsp/comp/userSelect.jsp?userNm=" + "&callbackFunc="+callbackFunc,"","toolbar=no,scrollbars=no,width=600,height=420");
		  			}else{
		  				// 임원이 아닐 경우
			  			v_tSignUserCd = resultData.ds_DrafterOrgBoss[0].orgCd; // 최종 결재자 ORG
			  			v_tSignUserId = resultData.ds_DrafterOrgBoss[0].userId; // 최종결재자 ID

		  			}

		  		}
	  		}




	  		// 팀장 이상 여부 검사
	  		if(resultData.ds_IsOrgBoss != null){
	  			for(var i = 0; i < resultData.ds_IsOrgBoss.length; i++){
	  				if(ds_ChiefCd.find("code", resultData.ds_IsOrgBoss[i].userRpswrkCd) > -1){
	  					v_IsOrgBoss = "Y";
	  					$("#innerTripSign").show();
	  				}
	  			}
	  		}else{
	  			v_IsOrgBoss = "N";

	  		}


	  		// 임원여부 검사
	  		if(resultData.ds_Officer != null){

	  			v_IsOfficer = "Y"; // 임원여부 Y / N

	  			$("#receiptTr").show();

	  			$("#receiptUser_btn").show();
	  			$("#rAmountSpan").show();

	  			$("#receiptUser_id").removeAttr("readonly");
	  			$("#receiptUser_name").removeAttr("readonly");

	  			$("#excptDayChkSpan").show();
	  			$("#excptNightChkSpan").show();
	  			$("#eduTripChkSpan").hide();

	  			$("#innerTripSign").show();

	  		}else{

	  			/* 20201125 상무 대리수령 제거(SAP와 일치시키기 위함)(시작)
	  			// 2016-01-27
	  			// 사번이 A E S 가 아닌 경우에도
	  			// 호칭 임원 조건 추가
	  			if(tripUserPositCd == "상무"){ // 호칭 조건을 추가하세요.

	  				v_IsOfficer = "Y"; // 임원여부 Y / N
	  				// v_IsOfficer <-- 임원 여부 변수 가 Y 인 경우 이후에 결재선 조회 과정에서 임원 전결 결재선으로 만들어줌

		  			$("#receiptTr").show(); // 교통비 수령인

		  			$("#receiptUser_btn").show();
		  			$("#rAmountSpan").show();

		  			$("#receiptUser_id").removeAttr("readonly");
		  			$("#receiptUser_name").removeAttr("readonly");

		  			$("#excptDayChkSpan").show(); // 일당비 제외
		  			$("#excptNightChkSpan").show();
		  			$("#eduTripChkSpan").hide(); // 교육수당 제외

		  			$("#innerTripSign").show(); // 결재선 지정 팝업

	  			}else{
	  				v_IsOfficer = "N";

		  			$("#receiptTr").hide();

		  			$("#receiptUser_btn").hide();
		  			$("#receiptUser_id").attr("readonly", "true");
		  			$("#receiptUser_name").attr("readonly", "true");
		  			$("#rAmountSpan").hide();

		  			$("#excptDayChkSpan").hide();
		  			$("#excptNightChkSpan").show();
		  			$("#eduTripChkSpan").show();

	  			}
				20201125 상무 대리수령 제거(SAP와 일치시키기 위함)(종료) */


  				v_IsOfficer = "N";

	  			$("#receiptTr").hide();

	  			$("#receiptUser_btn").hide();
	  			$("#receiptUser_id").attr("readonly", "true");
	  			$("#receiptUser_name").attr("readonly", "true");
	  			$("#rAmountSpan").hide();

	  			$("#excptDayChkSpan").hide();
	  			$("#excptNightChkSpan").show();
	  			$("#eduTripChkSpan").show();




	  		}
	  		cf_retrieveSignLine();
	  		cf_getCouserInfo();

//	  		$(".ajax_overlay").remove();

	  		break;
	  	case "SELECT_SEND_BIZ_TRIP" :
	  		// resultData.output1[0];
	  		// SAP 으로 부터 상신되어옴 REFNO 를 삽입한다.
	  		// REFNO 로 SAP DATA 조회 가능

	  		// SAP 상신 성공시 DB INSERT
	  		// SGNS CALLBACK

	  		var result = resultData.output1[0];

	  		if(result.ErrMsg != undefined){
	  			// SAP 상신 실패시 Msg 출력
	  			gf_AlertMsg(result.ErrMsg);
	  			v_isSaveEnable = "N";
	  			$(".btn").show();
	  			$(".ajax_overlay").remove();
	  			return;
	  		}else{
	  			// SAP 상신 성공시 DB INSERT
	  			// SAP 상신 결과를 임시 전역변수에 저장
	  			v_SapResult = result;

	  			// 출장자팀 과 집행팀이 다를 경우 추가 결재선 셋팅
	  			var dSignType = v_dSignType; // 타 집행팀 Y / N
	  			// 타집행팀이 아닐 경우 null
	  			var dSignUserCd = v_dSignUserCd; // 타 집행팀장 ORG
	  			var dSignUserId = v_dSignUserId; // 타 집행티장 ID

	  			// 타집행 현장소장
	  			var dHSignUserCd = "";
	  			var dHSignUserId = "";
	  			if(dSignType == "Y" && v_BdgtType == "Q"){
	  				//타집행예산이면서 현장예산일 경우 현장책임관리자가 추가 협의자로 들어간다.
	  				dHSignUserCd = v_dHSignUserCd;
	  	  			dHSignUserId =  v_dHSignUserId;
	  			}

	  			// 현장소속 여부
	  			var hSignType = v_hSignType; // 현장소장 Y / N
	  			// 현장소속이 아닐경우 null
				var hSignUserCd = ""; // 련장소장 ORG
	  			var hSignUserId = ""; // 현장소장 ID
	  			if(hSignType == 'Y'){
	  				// 현장소속일 경우 결재선에 추가 입력할 아이디 적용
	  				hSignUserCd = v_hSignUserCd;
	  				hSignUserId = v_hSignUserId;
	  			}

	  			var tSignUserCd = v_tSignUserCd; // 최종결재 ORG
	  			var tSignUserId = v_tSignUserId; // 최종결재 ID

	  			var dutySysCd = "SGNS"; // DUTYSYSCD
	  			var programCode = "SGNS070002"; // 양식코드
	  			var signDocTitle = "국내출장정산서"; // 양식 제목

	  			var refNo = v_SapResult.ORefkeyNo; // 외부 전표번호

	  			var tripUserId = tripUser + " " + tripUserNm; // 출장자 ID (사번 + 이름)
	  			var tripUserOrg = tripUserTeam + " " + tripUserTeamNm; // 충장자 ORG

	  			var drafterId = gv_userId + " " + gv_userNm; // 작성자 ID
	  			var drafterOrg = gv_orgCd + " " + gv_orgNm; // 작성자 ORG

	  			var bdgtType = $("#bdgtType").text(); // 경비구분
	  			var aufnrNo = v_Aufnr; // 예산번호
	  			var execTeam = v_SapResult.OPrctr + " " + (v_SapResult.OPrctrTxt).replace(/&/gi,"＆"); // 집행팀
	  			var tripTarget = $("#tripTarget").val(); // 출장장소
	  			var tripPurp = "(국출)"+$("#tripPurpose").val(); // 출장목적
	  			var startDate = $("input[name='startDate']").val(); // 시작일
	  			var endDate = $("input[name='endDate']").val(); // 종료일
	  			var account = v_SapResult.OAccountNo; // 송금계좌
	  			var compCar = $("#compCar").val();
	  			var tAmt1 = tripAmt1; // 항공
	  			var tAmt2 = tripAmt2; // 고속철도+기차+선박+버스
	  			var tAmt3 = 0; // 0
	  			var dayAmt = v_SapResult.ODayTot; // 일당비
	  			var nightAmt = v_SapResult.ONightTot; // 숙박비
	  			var docNo = v_DocNo; // CMAS 문서번호

	  			//증빙일자 구함
	  			var sDate = $("input[name='startDate']").val().split("-");
	  			var iTravFdate = sDate[0] + sDate[1] + sDate[2]; // 출장시작예정일

	  			var payDate = $("input[name='payDate']").val().split("-");
	  			var iPaymDate = payDate[0] + payDate[1] + payDate[2]; // 지불예정일

	  			// 증빙일자 입력 조정로직 삭제. (ITSM-20200217-0011)
	  			// 출장 시작일보다 지불예정일이 뒤면 출장시작일
	  			// 지불예정일보다 출장시작일이 뒤면 지불 예정일
/*	  			var betDay = ($.datepicker.parseDate("yymmdd" , iPaymDate) - $.datepicker.parseDate("yymmdd" , iTravFdate))/1000/60/60/24;

	  			var iSendDate = ""; // 증빙일
	  			if(betDay < 0){
//	  				alert("출장 시작일이 뒤임");
	  				iSendDate = iPaymDate;
	  			}else{
//	  				alert("지불예정일이 뒤임")
	  				iSendDate = iTravFdate;
	  			}
*/
	  			var oDate = $("input[name='ordDate']").val().split("-");
	  			var iSendDate = oDate[0] + oDate[1] + oDate[2]; // 기표일자
	  			var ordDate = iSendDate; // 기표일자

	  			var ordNo = ""; // 전표번호
	  			var agencyUser = v_DutyAgncUserNm + " (" + v_DutyAgncUserId + ")"; // 업무대행자
	  			if(v_DutyAgncUserNm == "" && v_DutyAgncUserId == ""){
	  				agencyUser = "";
	  			}

	  			// 교통비 수령인 수령액
	  			var iRutLifnr = ""; // 교통비 수령인
	  			var iRutValue = ""; // 교통비 수령액
	  			var iRutAccount = "";

	  			// 임원만 입력됨

	  			if(v_IsOfficer == "Y"){

	  				// 안체크
	  				if($("#noReceipt:checked").size() < 1){
	  					iRutLifnr = v_ReceiptUserNm + " (" + v_ReceiptUser + ")";

		  				var rAmount = $("#rAmount").val();
		  				for(var j = 0; j < rAmount.length; j++){
		  					rAmount = rAmount.replace(",", "");
		  				 }
		  				iRutValue = rAmount;
	  				}
	  			}

	  			var trRecvUser = iRutLifnr; // 교통비수령인
	  			var trAmount = iRutValue; // 교통비수령금액
	  			var trAccount = iRutAccount; // 교통비수령계좌

	  			// SGNS REMOTE DRAFT
	  			var sgnsParams = {
	  					dSignType : dSignType,
	  					dSignUserCd : dSignUserCd,
	  					dSignUserId : dSignUserId,
	  					dHSignUserCd : dHSignUserCd,
	  		  			dHSignUserId : dHSignUserId,
	  					hSignType : hSignType,
	  					hSignUserCd : hSignUserCd,
	  					hSignUserId : hSignUserId,
	  					tSignUserCd : tSignUserCd,
	  					tSignUserId : tSignUserId,
	  					dutySysCd : dutySysCd,
	  					programCode : programCode,
	  					signDocTitle : signDocTitle,
	  					refNo : refNo,
	  					tripUserId : tripUserId,
	  					tripUserOrg : tripUserOrg,
	  					drafterId : drafterId,
	  					drafterOrg : drafterOrg,
	  					bdgtType : bdgtType,
	  					aufnrNo : aufnrNo,
	  					execTeam : execTeam,
	  					tripTarget : tripTarget,
	  					tripPurp : tripPurp,
	  					startDate : startDate,
	  					endDate : endDate,
	  					account : account,
	  					compCar : compCar,
	  					tAmt1 : tAmt1,
	  					tAmt2 : tAmt2,
	  					tAmt3 : tAmt3,
	  					dayAmt : dayAmt,
	  					nightAmt : nightAmt,
	  					docNo : docNo,
	  					ordDate : ordDate,
	  					ordNo : ordNo,
	  					agencyUser : agencyUser,
	  					trRecvUser : trRecvUser,
	  					trAmount : trAmount,
	  					trAccount : trAccount,
	  					bdgtCd : v_BdgtType,
	  					isOrgBoss : v_IsOrgBoss,
	  					isOfficer : v_IsOfficer,
	  					tripUser : tripUser,
	  					tripUserTeam : tripUserTeam
				};

	  			gf_Transaction("SELECT_SGNS_REMOTE_DRAFT", "/trip/innerTrip/saveSgnsRemoteDraft.xpl", sgnsParams, {}, "f_Callback", true);

	  		}

	  		break;
	  	case "SELECT_INNER_TRIP_DRAFT" :

	  		// DB INSERT 성공시 CALLBACK

	  	// SAP 통신 결과값
//	  		result.OAccountNo // 출장자 송금 계좌번호
//	  		result.ODayTot // 일당비
//	  		result.ONightTot // 숙박비
//	  		result.OPrctr // 집행팀코드
//	  		result.OPrctrTxt // 집행팀명
//	  		result.ORefkeyNo // RefNo 외부전표번호
//	  		result.OTotAmount // 일당비+숙박비+교통비


  			// CALLBACK 성공시 진짜 Data로 변경할 것
  			// 출장자팀 과 집행팀이 다를 경우 추가 결재선 셋팅
  			var dSignType = v_dSignType; // 타 집행팀 Y / N
  			// 타집행팀이 아닐 경우 null
  			var dSignUserCd = v_dSignUserCd; // 타 집행팀장 ORG
  			var dSignUserId = v_dSignUserId; // 타 집행티장 ID

  			// 타집행 현장소장
  			var dHSignUserCd = "";
  			var dHSignUserId = "";
  			if(dSignType == "Y" && v_BdgtType == "Q"){
  				//타집행예산이면서 현장예산일 경우 현장책임관리자가 추가 협의자로 들어간다.
  				dHSignUserCd = v_dHSignUserCd;
  	  			dHSignUserId =  v_dHSignUserId;
  			}

  			// 현장소속 여부
  			var hSignType = v_hSignType; // 현장소장 Y / N
  			// 현장소속이 아닐경우 null
			var hSignUserCd = ""; // 현장소장 ORG
  			var hSignUserId = ""; // 현장소장 ID
  			if(hSignType == 'Y'){
  				// 현장소속일 경우 결재선에 추가 입력할 아이디 적용
  				hSignUserCd = v_hSignUserCd;
  				hSignUserId = v_hSignUserId;
  			}

  			var tSignUserCd = v_tSignUserCd; // 최종결재 ORG
  			var tSignUserId = v_tSignUserId; // 최종결재 ID

  			var dutySysCd = "SGNS"; // DUTYSYSCD
  			var programCode = "SGNS070002"; // 양식코드
  			var signDocTitle = "국내출장정산서"; // 양식 제목

  			var refNo = v_SapResult.ORefkeyNo; // 외부 전표번호

  			var tripUserId = tripUser + " " + tripUserNm; // 출장자 ID (사번 + 이름)
  			var tripUserOrg = tripUserTeam + " " + tripUserTeamNm; // 출장자 ORG

  			var drafterId = gv_userId + " " + gv_userNm; // 작성자 ID
  			var drafterOrg = gv_orgCd + " " + gv_orgNm; // 작성자 ORG

  			var bdgtType = $("#bdgtType").text(); // 경비구분
  			var aufnrNo = v_Aufnr; // 예산번호
  			var execTeam = v_SapResult.OPrctr + " " + (v_SapResult.OPrctrTxt).replace(/&/gi,"＆"); // 집행팀
  			var tripTarget = $("#tripTarget").val(); // 출장장소
  			var tripPurp = "(국출)"+$("#tripPurpose").val(); // 출장목적
  			var startDate = $("input[name='startDate']").val(); // 시작일
  			var endDate = $("input[name='endDate']").val(); // 종료일
  			var account = v_SapResult.OAccountNo; // 송금계좌
  			var compCar = $("#compCar").val();
  			var tAmt1 = tripAmt1; // 항공
  			var tAmt2 = tripAmt2; // 고속철도+기차+선박+버스
  			var tAmt3 = 0; // 0
  			var dayAmt = v_SapResult.ODayTot; // 일당비
  			var nightAmt = v_SapResult.ONightTot; // 숙박비
  			var docNo = v_DocNo; // CMAS 문서번호
  			//증빙일자 구함
  			var sDate = $("input[name='startDate']").val().split("-");
  			var iTravFdate = sDate[0] + sDate[1] + sDate[2]; // 출장사적예정일

  			var payDate = $("input[name='payDate']").val().split("-");
  			var iPaymDate = payDate[0] + payDate[1] + payDate[2]; // 생성일 - 송금예정일

  			// 증빙일자 입력 조정로직 삭제. (ITSM-20200217-0011)
  			// 출장 시작일보다 지불예정일이 뒤면 출장시작일
  			// 지불예정일보다 출장시작일이 뒤면 지불 예정일
/*  			var betDay = ($.datepicker.parseDate("yymmdd" , iPaymDate) - $.datepicker.parseDate("yymmdd" , iTravFdate))/1000/60/60/24;

  			var iSendDate = ""; // 증빙일
  			if(betDay < 0){
//  				alert("출장 시작일이 뒤임");
  				iSendDate = iPaymDate;
  			}else{
//  				alert("지불예정일이 뒤임")
  				iSendDate = iTravFdate;
  			}
*/
  			var oDate = $("input[name='ordDate']").val().split("-");
  			var iSendDate = oDate[0] + oDate[1] + oDate[2]; // 기표일자
  			var ordDate = iSendDate; // 기표일자

  			var ordNo = ""; // 전표번호
  			var agencyUser = v_DutyAgncUserNm + " (" + v_DutyAgncUserId + ")"; // 업무대행자
  			if(v_DutyAgncUserNm == "" && v_DutyAgncUserId == ""){
  				agencyUser = "";
  			}

  			// 교통비 수령인 수령액

  			var iRutLifnr = ""; // 교통비 수령인
  			var iRutValue = ""; // 교통비 수령액
  			var iRutAccount = "";

  			// 임원만 입력됨
  			if(v_IsOfficer == "Y"){

  				// 안체크
  				if($("#noReceipt:checked").size() < 1){
  					iRutLifnr = v_ReceiptUserNm + " (" + v_ReceiptUser + ")";

  	  				var rAmount = $("#rAmount").val();
  	  				for(var j = 0; j < rAmount.length; j++){
  	  					rAmount = rAmount.replace(",", "");
  	  				 }
  	  				iRutValue = rAmount;
  				}else{

  				}

  			}

  			var trRecvUser = iRutLifnr; // 교통비수령인
  			var trAmount = iRutValue; // 교통비수령금액
  			var trAccount = iRutAccount; // 교통비수령계좌

  			// SGNS REMOTE DRAFT
  			var sgnsParams = {
  					dSignType : dSignType,
  					dSignUserCd : dSignUserCd,
  					dSignUserId : dSignUserId,
  					dHSignUserCd : dHSignUserCd,
  		  			dHSignUserId : dHSignUserId,
  					hSignType : hSignType,
  					hSignUserCd : hSignUserCd,
  					hSignUserId : hSignUserId,
  					tSignUserCd : tSignUserCd,
  					tSignUserId : tSignUserId,
  					dutySysCd : dutySysCd,
  					programCode : programCode,
  					signDocTitle : signDocTitle,
  					refNo : refNo,
  					tripUserId : tripUserId,
  					tripUserOrg : tripUserOrg,
  					drafterId : drafterId,
  					drafterOrg : drafterOrg,
  					bdgtType : bdgtType,
  					aufnrNo : aufnrNo,
  					execTeam : execTeam,
  					tripTarget : tripTarget,
  					tripPurp : tripPurp,
  					startDate : startDate,
  					endDate : endDate,
  					account : account,
  					compCar : compCar,
  					tAmt1 : tAmt1,
  					tAmt2 : tAmt2,
  					tAmt3 : tAmt3,
  					dayAmt : dayAmt,
  					nightAmt : nightAmt,
  					docNo : docNo,
  					ordDate : ordDate,
  					ordNo : ordNo,
  					agencyUser : agencyUser,
  					trRecvUser : trRecvUser,
  					trAmount : trAmount,
  					trAccount : trAccount,
  					bdgtCd : v_BdgtType,
  					isOfficer : v_IsOfficer,
  					tripUser : tripUser,
  					tripUserTeam : tripUserTeam
			};

  			gf_Transaction("SELECT_SGNS_REMOTE_DRAFT", "/trip/innerTrip/saveSgnsRemoteDraft.xpl", sgnsParams, {}, "f_Callback", true);

	  		break;
	  	case "SELECT_SGNS_REMOTE_DRAFT" :

	  		var result = resultData.output1[0];

	  		if(result.ErrMsg != undefined){
	  			// SAP 상신 실패시 Msg 출력
	  			gf_AlertMsg(result.ErrMsg);
	  			v_isSaveEnable = "N";
	  			$(".btn").show();
	  			$(".ajax_overlay").remove();
	  			return;
	  		}else{

	  			gf_AlertMsg("정상 처리 되었습니다.");

	  			if ( !gf_IsNull(v_CallbackFunc) ) {
		  			var openCallbackFunc = "opener."+v_CallbackFunc;
		  	    	eval(openCallbackFunc + "();");
		  	    }

	  			self.close();
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
	  	case "SELECT_EXEC_TEAM_INFO" :

	  		if(resultData.ds_coUser == null){

	  			// 집행팀 코드로 결과가 없을때 HR 조직으로 다시 검색
	  			cf_getCouserInfo();
	  		}else{
	  		// 최종결재자와 집행팀장의 CO 조직코드를 비교한다.
		  		if(v_tSignUserCd != resultData.ds_coUser[0].orgCd && v_Chief != tripUser){

		  			if(v_BdgtType == "Q" && tripUserTeam == v_Kostv){
		  				v_dSignType = "N";
		  			}else{
		  				gf_AlertMsg("타팀 예산 사용시 집행팀이 협의자로 지정됩니다.");
			  			v_dSignType = "Y"; // 타 집행팀 여부

			  			// 최종 결재자가 타집행팀장 아이디가 같다면 협의에서 제외처리
			  			if(v_tSignUserId == resultData.ds_coUser[0].userId){
			  				v_dSignUserId = "";
			  			}else{
			  				v_dSignUserCd = resultData.ds_coUser[0].orgCd; // 타 집행팀장 ORG
				  			v_dSignUserId = resultData.ds_coUser[0].userId; // 타 집행티장 ID
				  			v_dSignUserNm = resultData.ds_coUser[0].userKnm;
				  			v_dSignOrgNm = ''; // resultData.ds_coUser[0].userId;
				  			v_dSignRpwrkNm = resultData.ds_coUser[0].userRpswrkCd;
				  			v_dSignRpswrkCd = resultData.ds_coUser[0].userRpswrkCd;
				  			v_dSignPositCd = resultData.ds_coUser[0].userPositCd;
				  			v_dSignPositNm = resultData.ds_coUser[0].userPositCd;
			  			}

			  			// 타집행팀이면서 현장일 경우 현장관리 책임자도 협의 라인으로 추가한다.
			  			if(v_BdgtType == "Q"){

			  				// 현장관리책임자 없을경우 오류방지
			  				if (resultData.ds_IsSpot != null) {

				  				// 중간 결재자가 타집행팀 현장책임자 아이디가 같다면 협의에서 제외처리
				  				if(v_hSignUserId == resultData.ds_IsSpot[0].userId){
				  					v_dHSignUserId = "";
				  				}else{
				  					v_dHSignUserCd = resultData.ds_IsSpot[0].orgCd; // 타 집행팀장 ORG
						  			v_dHSignUserId = resultData.ds_IsSpot[0].userId; // 타 집행티장 ID
						  			v_dHSignUserNm = resultData.ds_IsSpot[0].userKnm;
						  			v_dHSignOrgNm = ''; // resultData.ds_coUser[0].userId;
						  			v_dHSignRpwrkNm = resultData.ds_IsSpot[0].userRpswrkCd;
						  			v_dHSignRpswrkCd = resultData.ds_IsSpot[0].userRpswrkCd;
						  			v_dHSignPositCd = resultData.ds_IsSpot[0].userPositCd;
						  			v_dHSignPositNm = resultData.ds_IsSpot[0].userPositCd;
				  				}

			  				}


			  			}

			  			if(v_dHSignUserId == "" && v_dSignUserId == "") v_dSignType = "N";
		  			}

		  		}else{
		  			v_dSignType = "N";
		  		}

		  		// 결재선을 다시 그린다.
		  		cf_retrieveSignLine();

		  		$(".ajax_overlay").remove();
	  		}

	  		break;
	  	case "SELECT_CO_USER_INFO" :

	  		if(resultData.ds_coUser == null){

	  			//2015-06-09
	  		}


	  		// 최종결재자와 집행팀장의 CO 조직코드를 비교한다.
	  		if(v_tSignUserCd != resultData.ds_coUser[0].orgCd && v_Chief != tripUser){

	  			if(v_BdgtType == "Q" && tripUserTeam == v_Kostv){
	  				v_dSignType = "N";
	  			}else{
	  				gf_AlertMsg("타팀 예산 사용시 집행팀이 협의자로 지정됩니다..");
		  			v_dSignType = "Y"; // 타 집행팀 여부

		  			// 최종 결재자가 타집행팀장 아이디가 같다면 협의에서 제외처리
		  			if(v_tSignUserId == resultData.ds_coUser[0].userId){
		  				v_dSignUserId = "";
		  			}else{
		  				v_dSignUserCd = resultData.ds_coUser[0].orgCd; // 타 집행팀장 ORG
			  			v_dSignUserId = resultData.ds_coUser[0].userId; // 타 집행티장 ID
			  			v_dSignUserNm = resultData.ds_coUser[0].userKnm;
			  			v_dSignOrgNm = ''; // resultData.ds_coUser[0].userId;
			  			v_dSignRpwrkNm = resultData.ds_coUser[0].userRpswrkCd;
			  			v_dSignRpswrkCd = resultData.ds_coUser[0].userRpswrkCd;
			  			v_dSignPositCd = resultData.ds_coUser[0].userPositCd;
			  			v_dSignPositNm = resultData.ds_coUser[0].userPositCd;
		  			}

		  			// 타집행팀이면서 현장일 경우 현장관리 책임자도 협의 라인으로 추가한다.
		  			if(v_BdgtType == "Q" && !(v_Kostv == 'PABC1' || v_Kostv == 'PABE1')){

		  				// 현장관리책임자 없을경우 오류방지
		  				if (resultData.ds_IsSpot != null) {

			  				// 중간 결재자가 타집행팀 현장책임자 아이디가 같다면 협의에서 제외처리
			  				if(v_hSignUserId == resultData.ds_IsSpot[0].userId){
			  					v_dHSignUserId = "";
			  				}else{
			  					v_dHSignUserCd = resultData.ds_IsSpot[0].orgCd; // 타 집행팀장 ORG
					  			v_dHSignUserId = resultData.ds_IsSpot[0].userId; // 타 집행티장 ID
					  			v_dHSignUserNm = resultData.ds_IsSpot[0].userKnm;
					  			v_dHSignOrgNm = ''; // resultData.ds_coUser[0].userId;
					  			v_dHSignRpwrkNm = resultData.ds_IsSpot[0].userRpswrkCd;
					  			v_dHSignRpswrkCd = resultData.ds_IsSpot[0].userRpswrkCd;
					  			v_dHSignPositCd = resultData.ds_IsSpot[0].userPositCd;
					  			v_dHSignPositNm = resultData.ds_IsSpot[0].userPositCd;
			  				}

		  				}

		  			}

		  			if(v_dHSignUserId == "" && v_dSignUserId == "") v_dSignType = "N";


//		  			signUserId: temp.signUserId,	// 세션에서 받아온 값
//		  			signUserNm: temp.signUserNm,	// 세션에서 받아온 값
//		  			psignUserNm : "",
//		  			apperPositCd: temp.apperPositCd,
//		  			apperPositNm: temp.apperPositNm,
//		  			perpsignPositNm : "",
//		  			apperRpswrkCd: temp.apperRpswrkCd,
//		  			apperRpswrkNm: temp.apperRpswrkNm,
//		  			apperOrgCd: temp.apperOrgCd,
//		  			apperOrgNm: temp.apperOrgNm,
	  			}




	  		}else{
	  			v_dSignType = "N";
	  		}

	  		// 결재선을 다시 그린다.
	  		cf_retrieveSignLine();

	  		$(".ajax_overlay").remove();


	  		break;
	  	case "SELECT_CMAS_ID" :

//	  		alert(resultData.ds_Result[0].docNo);

	  		v_DocNo = resultData.ds_Result[0].docNo;
	  		var docNoText = v_DocNo.split("-");
	  		$("#docNo").text(docNoText[1] + "-" + docNoText[2]);

	  		break;
	  	case "SAVE_INNER_TRIP_DOC_SAVE" :

	  		// 문서가 성공적으로 처리되면 문서 리스트를 다시 조회하여 갱신하여 준다.
	  		if ( !gf_IsNull(v_CallbackFunc) ) {
	  			var openCallbackFunc = "opener."+v_CallbackFunc;
	  	    	eval(openCallbackFunc + "();");
	  	    }

	  		self.close();
	  		break;
	  	case "SELECT_SIGN_LINE" :
	  		//ds_Signln.setAllData(resultData.ds_SignlnForExcluRegl);

	  		cf_setSignlnInfo(resultData);


	  		break;
	  	case "CHECK_DRAFT_DUPLICATION" :

	  		//alert("cb-1");

	  		var v_RefNo1 = '';
	  		var v_RefNo2 = '';
	  		var v_RefNo3 = '';
	  		var v_RefNo4 = '';

		  if (resultData.ds_Result1 != null) {
	  		v_RefNo1 = resultData.ds_Result1[0].refNo;
		  }else if (resultData.ds_Result2 != null) {
	  		v_RefNo2 = resultData.ds_Result2[0].refNo;
		  }else if (resultData.ds_Result3 != null) {
	  		v_RefNo3 = resultData.ds_Result3[0].refNo;
		  }else if (resultData.ds_Result4 != null) {
	  		v_RefNo4 = resultData.ds_Result4[0].refNo;
		  }

			if(v_RefNo1 != null && v_RefNo1 != ''){
				 gf_AlertMsg("시내교통비와 출장일자가 중복이라 신청할 수 없습니다 \n\n (시내교통비 Ref_NO: "+v_RefNo1+")");
				 return;
			}else if(v_RefNo2 != null && v_RefNo2 != ''){
				 gf_AlertMsg("국내출장신청서와 출장일자가 중복이라 신청할 수 없습니다 \n\n (국내출장신청서 Ref_NO: "+v_RefNo2+")");
				 return;
			}else if(v_RefNo3 != null && v_RefNo3 != ''){
				 gf_AlertMsg("해외출장신청서와 출장일자가 중복이라 신청할 수 없습니다 \n\n (해외출장신청서 Ref_NO: "+v_RefNo3+")");
				 return;
			}else if(v_RefNo3 != null && v_RefNo4 != ''){
				 gf_AlertMsg("해외출장정산서와 출장일자가 중복이라 신청할 수 없습니다 \n\n (해외출장정산서 Ref_NO: "+v_RefNo4+")");
				 return;
			}

			alert("정상")
		  //alert("v_RefNo1:"+v_RefNo1);
		  //alert("v_RefNo2:"+v_RefNo2);
		  //alert("v_RefNo3:"+v_RefNo3);
		  //alert("v_RefNo4:"+v_RefNo4);

		  return;


	  		var docNoText = v_DocNo.split("-");
	  		$("#docNo").text(docNoText[1] + "-" + docNoText[2]);

	  		break;
	  	case "CHECK_EHR_APP_YN" :
	  		//alert("콜백");
	  		if (resultData.ds_Result1 != null) {
		  		if (resultData.ds_Result1[0].count < 1) {
					 v_EHRAppYn = "N";
					 return;
		  		}
	  		}else{
				 v_EHRAppYn = "N";
				 return;
	  		}

	  		//var v_count = resultData.ds_Result1[0].count;
	  		//alert("v_count:"+v_count);

		  //alert("정상");
		  //return;
	  		v_EHRAppYn = "Y";


	  		break;
	  	case "CHECK_CO_FILE_YN" :
	  		//alert("콜백file");
	  		if (resultData.ds_Result1 != null) {
		  		if (resultData.ds_Result1[0].count < 1) {
					 v_CoFileYn = "N";
					 return;
		  		}
	  		}else{
				 v_CoFileYn = "N";
				 return;
	  		}

	  		//var v_count = resultData.ds_Result1[0].count;
	  		//alert("v_count:"+v_count);

		  //alert("정상");
		  //return;
	  		v_CoFileYn = "Y";


	  		break;
	  	case "SELECT_T_SIGN_USER_INFO" :

		  if (resultData.ds_IsSpot != null) {
			v_hSignType = "Y"; // 현장소장 Y / N
			v_hSignUserCd = resultData.ds_IsSpot[0].orgCd; // 현장관리책임자 ORG
			v_hSignUserId = resultData.ds_IsSpot[0].userId; // 현장관리책임자 ID
			v_hSignUserNm = resultData.ds_IsSpot[0].userKnm;
			v_hSignUserPositCd = resultData.ds_IsSpot[0].userPositCd;
			v_hSignUserRpswrkCd = resultData.ds_IsSpot[0].userRpswrkCd;

			// 2015-05-11
			// 만약 출장자가 현장관리책임자라면 중간결재가 생성되지 않는다.
			if (v_hSignUserId == tripUser)
				v_hSignType = "N";

		}

		ds_Signln.clear();

		var cnt = 0;

		ds_Signln.insert(cnt, {
			signId : "",
			signSeq : cnt + 1,
			signTpCd : "T01",
			signUserId : sDrafterId, // 세션에서 받아온 값
			signUserNm : sDrafterNm, // 세션에서 받아온 값
			psignUserNm : "",
			apperPositCd : sDrafterPositCd,
			apperPositNm : sDrafterPositCd,
			perpsignPositNm : "",
			apperRpswrkCd : sDrafterRpswrkCd,
			apperRpswrkNm : sDrafterRpswrkCd,
			apperOrgCd : sDrafterOrgCd,
			apperOrgNm : sDrafterOrgNm,
			orgChrcCls : "D"
		});

		// 현장소장 협의
		// 현장 담당자 추가되어야함
		if (v_hSignType == "Y") {
			cnt++;

			if (v_hSignUserRpswrkCd == "") {
				ds_Signln.insert(cnt, {
					signId : "",
					signSeq : cnt + 1,
					signTpCd : "T02",
					signUserId : v_hSignUserId,
					signUserNm : v_hSignUserNm,
					psignUserNm : "",
					apperPositCd : v_hSignUserPositCd,
					apperPositNm : v_hSignUserPositCd,
					perpsignPositNm : "",
					apperRpswrkCd : v_hSignUserRpswrkCd,
					apperRpswrkNm : v_hSignUserRpswrkCd,
					apperOrgCd : v_hSignUserCd,
					apperOrgNm : "",
					orgChrcCls : "D"
				});
			} else {
				ds_Signln.insert(cnt, {
					signId : "",
					signSeq : cnt + 1,
					signTpCd : "T02",
					signUserId : v_hSignUserId,
					signUserNm : v_hSignUserNm,
					psignUserNm : "",
					apperPositCd : v_hSignUserRpswrkCd,
					apperPositNm : v_hSignUserRpswrkCd,
					perpsignPositNm : "",
					apperRpswrkCd : v_hSignUserRpswrkCd,
					apperRpswrkNm : v_hSignUserRpswrkCd,
					apperOrgCd : v_hSignUserCd,
					apperOrgNm : "",
					orgChrcCls : "D"
				});
			}

		}

		// 2015-08-31 협의자가 결재선지정자가 같으면 협의에서 빠진다.
		if (v_dSignType == "Y" && v_Chief != tripUser && v_Chief != v_tSign.userId) {

			if (v_BdgtType == "Q") {

				// 타집행팀에 현장일 경우 현장관리책임자 협의가 추가된다.
				if(v_dHSignUserId != ""){
					cnt++;

					if (v_dHSignRpswrkCd == "") {
						ds_Signln.insert(cnt, {
							signId : "",
							signSeq : cnt + 1,
							signTpCd : "T03",
							signUserId : v_dHSignUserId, // 세션에서 받아온 값
							signUserNm : v_dHSignUserNm, // 세션에서 받아온 값
							psignUserNm : "",
							apperPositCd : v_dHSignPositCd,
							apperPositNm : v_dHSignPositCd,
							perpsignPositNm : "",
							apperRpswrkCd : v_dHSignRpswrkCd,
							apperRpswrkNm : v_dHSignRpswrkCd,
							apperOrgCd : v_dHSignUserCd,
							apperOrgNm : "",
							orgChrcCls : "D"
						});
					} else {
						ds_Signln.insert(cnt, {
							signId : "",
							signSeq : cnt + 1,
							signTpCd : "T03",
							signUserId : v_dHSignUserId, // 세션에서 받아온 값
							signUserNm : v_dHSignUserNm, // 세션에서 받아온 값
							psignUserNm : "",
							apperPositCd : v_dHSignRpswrkCd,
							apperPositNm : v_dHSignRpswrkCd,
							perpsignPositNm : "",
							apperRpswrkCd : v_dHSignRpswrkCd,
							apperRpswrkNm : v_dHSignRpswrkCd,
							apperOrgCd : v_dHSignUserCd,
							apperOrgNm : "",
							orgChrcCls : "D"
						});

					}
				}
			}

			if(v_dSignUserId != ""){
				cnt++;
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
			}

		}

		cnt++;

		ds_Signln.insert(cnt, {
			signId : "",
			signSeq : cnt + 1,
			signTpCd : "T02",
			signUserId : v_tSign.userId, // 세션에서 받아온 값
			signUserNm : v_tSign.userKnm, // 세션에서 받아온 값
			psignUserNm : "",
			apperPositCd : v_tSign.userPositCd,
			apperPositNm : v_tSign.userIdPositNm,
			perpsignPositNm : "",
			apperRpswrkCd : v_tSign.userRpswrkCd,
			apperRpswrkNm : v_tSign.userRpswrkNm,
			apperOrgCd : v_tSign.orgCd,
			apperOrgNm : v_tSign.orgNm,
			orgChrcCls : "D"
		});

		gf_AssembleSignln(ds_Signln);

		$(".ajax_overlay").remove();


	  		break;
	  	default :
	  		break;
	  }
}

function f_callBackFuncDistSelect(obj) {

// v_BdgtType = obj.bdgtType;

	v_BdgtData = obj.bdgtData;

	var tStart = obj.startDist + " " + obj.startCity;
	var tEnd = obj.endDist + " " + obj.endCity;

	$("#" + obj.targetId + " input[name='tStart']").val(tStart);
	$("#" + obj.targetId + " input[name='tEnd']").val(tEnd);

}


function cf_OpenDistSelectPop(e){

	var targetId = $(e.target).parent().parent().attr("id");

	var distParams = {
		targetId : targetId,
		tStart : $("#" + targetId + " input[name='tStart']").val(),
		tEnd : $("#" + targetId + " input[name='tEnd']").val()
	};

	gf_PostOpen("/common/jsp/comp/distSelect/distSelect.jsp", null,
			"toolbar=no,scrollbars=no,width=305,height=175", distParams,
			true, true, "f_callBackFuncDistSelect");


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

  			cf_getExecTeamInfo();
//  			cf_getCouserInfo();
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

  			cf_getExecTeamInfo();
//  			cf_getCouserInfo();
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

  			cf_getExecTeamInfo();
//  			cf_getCouserInfo();
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

  			cf_getExecTeamInfo();
//  			cf_getCouserInfo();
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

  			cf_getExecTeamInfo();
//  			cf_getCouserInfo();
  			break;
  		case "P" :
  			$("#bdgtType").text("P. 사업경비");
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

  			cf_getExecTeamInfo();
//  			cf_getCouserInfo();
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

  			cf_getExecTeamInfo();
//  			cf_getCouserInfo();
  			break;
  		case "Q" :
  			$("#bdgtType").text("Q. 현장경비");
  			// 결과 셋팅
  			v_Aufnr = obj.bdgtData.orgId;
  			v_KText = obj.bdgtData.orgNm;
  			AufnrText = obj.bdgtData.orgId + " / 내역 : " + obj.bdgtData.orgNm;
  			$("#Aufnr").text(AufnrText);

  			v_Kostv = obj.bdgtData.orgId;
  			v_Kostvnm = obj.bdgtData.orgNm;

//  			v_Chief = obj.bdgtData.SabunSo; // 집행팀장ID
//  			v_Chiefnm = obj.bdgtData.Sojang; // 집행팀장 이름

  			v_Chief = obj.bdgtData.orgChief.split(" ")[0]; // 집행팀장ID
  			v_Chiefnm = obj.bdgtData.orgChief.split(" ")[1]; // 집행팀장 이름

  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);

  			// 현장소장이 아직 결정이 안난 부서의 경우 해당과 같이 등록되어있음
  			// 현장소장을 선택하여 가져온다.
  			if(v_Chief == "9999999999" || v_Chiefnm == "미등록" || v_Chief == ""){
  				gf_AlertMsg("현장소장이 미등록되있는 현장입니다. 현장소장을 검색하여 입력해 주세요.");

  				var callbackFunc = "f_NotExistCheifSelect";
  				window.open(gv_ContextPath + "/common/jsp/comp/userSelect.jsp?userNm=" + "&callbackFunc="+callbackFunc,"","toolbar=no,scrollbars=no,width=600,height=420");

  			}

  				cf_getExecTeamInfo();
//  			cf_getCouserInfo();
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
  			v_Chief = obj.bdgtData.Chief; // 집행팀장ID
  			v_Chiefnm = obj.bdgtData.Chiefnm; // 집행팀장 이름
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);

  			cf_getExecTeamInfo();
//  			cf_getCouserInfo();
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
  			v_Chief = obj.bdgtData.Chief; // 집행팀장IEHD
  			v_Chiefnm = obj.bdgtData.Chiefnm; // 집행팀장 이름
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);

  			cf_getExecTeamInfo();
//  			cf_getCouserInfo();
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

function f_NotExistCheifSelect(obj){
	// 검색되어 지정한 현장소장
	v_Chief = obj.userId;
	cf_getCouserInfo();
}

function f_NotExistOrgBossSelect(obj){
	// 임원이 아닐 경우
	v_tSignUserCd = obj.orgCd; // 최종 결재자 ORG
	v_tSignUserId = obj.userId; // 최종결재자 ID
}



// SAP 에 등록된 집행팀의 ORG_CD 와 CO 에 등록된 ORG_CD 가 틀리기 때문에 같은 조직이 맞는지
// 집행팀장의 ID 로 검증한다.
// 2015 08 06
// 집행팀 코드로 현장소장과 책임자 조회를 실행한다. 만약 존재하지 않는다면 집행팀장의 HR 코드를 조회한다.

function cf_getExecTeamInfo(){
	if(v_Chief != undefined){

		// 집행팀팀장ID
//		v_Chief

		// 예산타입이 현장경비라면 현장관리책임자도 가져온다.
		var coParams = {
				userId : v_Chief,
				orgCd : v_Kostv,
				bdgtType : v_BdgtType
		};


		gf_Transaction("SELECT_EXEC_TEAM_INFO", "/trip/innerTrip/getExecTeamInfo.xpl", coParams, {}, "f_Callback", true);
//	}else if(v_Chief == undefined){
//		gf_AlertMsg("집행팀이 입력되지 않았습니다.");
	}else if(v_tSignUserCd == undefined){
//		gf_AlertMsg("출장자가 입력되지 않았습니다.");
	}else{
		//console.log("====== 타집행팀 여부 검증안됨");
	}
}

function cf_getCouserInfo(){
	if(v_Chief != undefined){

		// 집행팀팀장ID
//		v_Chief


		//2015-08-20
		//PABC1 현장의 경우는 PABCM 의 PCM 직위를 협의로 올린다.
		// 2015-11-05 이수진 과장 요청(백세봄 : PABE1 / PABC1 현장은 무조건 9521681 협의)
		if(v_Kostv == 'PABC1' || v_Kostv == 'PABE1'){
			gf_Transaction("SELECT_CO_USER_INFO", "/trip/innerTrip/retrievePABC1UserInfo.xpl", {}, {}, "f_Callback", true);

		}else{
			// 예산타입이 현장경비라면 현장관리책임자도 가져온다.
			var coParams = {
					userId : v_Chief,
					bdgtType : v_BdgtType
			};


			gf_Transaction("SELECT_CO_USER_INFO", "/trip/innerTrip/retrieveCoUserInfo.xpl", coParams, {}, "f_Callback", true);

		}


//	}else if(v_Chief == undefined){
//		gf_AlertMsg("집행팀이 입력되지 않았습니다.");
	}else if(v_tSignUserCd == undefined){
//		gf_AlertMsg("출장자가 입력되지 않았습니다.");
	}else{
		//console.log("====== 타집행팀 여부 검증안됨");
	}

}


 function cf_getTranspDetail(){

	 var dataSize = $("#transpDetail tr").size();
	 var idFlag = "tData";

	 var resultArr = new Array();

	 for(var i = 1; i < dataSize; i++){

		 var tData = new Object();


		 var dId = $("#transpDetail tr")[i].id;
		 if(dId == "") continue;
		 var start = $("#" + dId + " input[name='tStart']").val();
		 var end = $("#" + dId + " input[name='tEnd']").val();
		 var type = $("#" + dId + " select[name='tType']").val();
		 var round = $("#" + dId + " select[name='tRound']").val();
		 var dist = $("#" + dId + " input[name='tDist']").val();
		 var amount = $("#" + dId + " input[name='tAmount']").val();	//사용금액
		 var carUseAmt = "";		//차량사용금액
		 var carUseQty = "";	//차량사용량

		 if(type == "8"){	//차량
			 amount = $("#" + dId + " input[name='tReqAmount']").val();		//청구금액
			 carUseAmt = $("#" + dId + " input[name='tAmount']").val();		//차량사용금액
			 carUseQty = $("#" + dId + " input[name='tCarUseQty']").val();	//차량사용량
		 }


		 for(var j = 0; j < amount.length; j++){
			 amount = amount.replace(",", "");
		 }

		 for(var j = 0; j < carUseAmt.length; j++){
			 carUseAmt = carUseAmt.replace(",", "");
		 }

		 tData.dptPlace = start;
		 tData.arrPlace = end;
		 tData.trafficCls = type;
		 tData.runtrpOneway = round;
		 tData.mvDist = dist;
		 tData.amt = amount;
		 tData.carUseAmt = carUseAmt;
		 tData.carUseQty = carUseQty;

		 resultArr.push(tData);

	 }

	 return resultArr;


//	 $("#tData1 input[name='tAmount']").val()


//	 // 출발지
//   input[name='tStart']
//	 // 도착지
//	 input[name='tEnd']
//	 // 수단
//	 input[name='tType']
//	 // 왕복여부
//	 input[name='tRound']
//	 // 거리
//	 input[name='tDist']
//	 // 금액
//	 input[name='tAmount']



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

/**
 * EAI 상신
 * 2015-08-06
 */
function cf_SendBizTrip(){

	// SAP 상신 전에 Validation Check 실시할 것

	/**************************************************************************
	 * sap parameter init start
	 **************************************************************************/
	var iBdgtType = v_BdgtType; // 경비구분 P사업, I일반, C특정
	var iTripType = "D"; // 출장구분 국출 D
	var iClearType = "*"; // 정산구분
	var iDocType = $("input[name='docType']:checked").val(); // 전표구분 H본사, G지역
	var iNotesdoc = v_DocNo; // 바로넷 문서번호 생략가능

	// 예산 관련
	var iBdgtNo; // 예산번호
	if(v_BdgtType == "C" || v_BdgtType == "Q"){
		// C 팀인 경우 예산번호 입력하지 않음
		iBdgtNo = "";
	}else{
		iBdgtNo = v_Aufnr;
	}

	var iBdgtTeam = v_Kostv; // 집행팀
	var iBdgtTeamNm = v_Kostvnm; // 집행팀 이름

	var iSenderId = gv_userId; // 작성자사번
	var iTravId = tripUser; // 출장자사번
	var iTravTeam = tripUserApptOrgCd; // 출장자팀

	var startDate = $("input[name='startDate']").val().split("-");
	var iTravFdate = startDate[0] + startDate[1] + startDate[2]; // 출장사적예정일

	var endDate = $("input[name='endDate']").val().split("-");
	var iTravTdate = endDate[0] + endDate[1] + endDate[2]; // 출장종료예정일

	var iTravPurp = "(국출)"+$("#tripPurpose").val(); // 주석-출장목적
	var iTravArea = $("#tripTarget").val(); // 주석-출장지역
	var iCompCar = ""; // 회사차량여부
	if($("#compCar").val() == "Y") iCompCar = "X"; // 회사차량 여부

	var payDate = $("input[name='payDate']").val().split("-");
	var iPaymDate = payDate[0] + payDate[1] + payDate[2]; // 생성일 - 송금예정일

	// 증빙일자 입력 조정로직 삭제. (ITSM-20200217-0011)
	// 출장 시작일보다 지불예정일이 뒤면 출장시작일
	// 지불예정일보다 출장시작일이 뒤면 지불 예정일
/*	var betDay = ($.datepicker.parseDate("yymmdd" , iPaymDate) - $.datepicker.parseDate("yymmdd" , iTravFdate))/1000/60/60/24;

	var iSendDate = ""; // 증빙일
	if(betDay < 0){
//		alert("출장 시작일이 뒤임");
		iSendDate = iPaymDate;
	}else{
//		alert("지불예정일이 뒤임")
		iSendDate = iTravFdate;
	}
*/
	var iTran1Amt = tripAmt1; // 금액(현지통화) - 항공 합계
	var iTran2Amt = tripAmt2; // 금액(현지통화) - 기차/선박/고속버스/고속철도 합계

	// tripAmt3 금액(현지통화) - 대중교통 합계 => 0 (현재 사용안함)
	tripAmt3 = 0;
	var iTran3Amt = $("#accomoAmt").val(); // 금액(숙박비)
	var iTran4Amt = $("#eexpAmt").val();	//금액(식비)
	var iTran5Amt = $("#etcAmt").val();	//금액(식비)

	var iAccomoDcnt = $("#genAccomoDcnt").val();	//숙박시설 일수
	var iTravRem = $("#rem").val();	//비고

	var iGitaAmt = 0; // 기타경비 - 대중교통 합계 => 0 (현재 사용안함)

	var iNoWage = ""; // 일당비제외
	if(v_DdExpnExcl == "Y"){
		iNoWage = "X";
	}else if(v_DdExpnExcl == "N"){

	}

	var iNoDaym = ""; // 숙박비제외 (임원 실비정산 필수)

	if(v_AccomoExpnExcl == "Y"){
		iNoDaym = "X";
	}else if(v_AccomoExpnExcl == "N"){

	}


	var iRutLifnr = ""; // 교통비 수령인
	var iRutValue = ""; // 교통비 수령액


	// 교통비 수령
	// 임원만 입력됨
	if(v_IsOfficer == "Y"){
			// 안체크
			if($("#noReceipt:checked").size() < 1){
				iRutLifnr = v_ReceiptUser;

				var rAmount = $("#rAmount").val();
				for(var j = 0; j < rAmount.length; j++){
					rAmount = rAmount.replace(",", "");
				 }
				iRutValue = rAmount;
			}else{


			}
	}



	var iPosid; // WBS 코드 - R : 예산번호, else ""
	if(v_BdgtType == "R"){
		// R 인 경우 예산번호
		iPosid = v_Aufnr;
	}else{
		iPosid = "";
	}

	var iBukrs = "1000"; // 회사코드

	var iEduchk = ""; // 교육 출장 여부
	var iEduAmt = ""; // 교육출장시 일당비
	if(v_EduBustrYn == "Y"){

		var eduVal = $("#eduTripAmt").val();
		for(var j = 0; j < eduVal.length; j++){
			eduVal = eduVal.replace(",", "");
		 }
		iEduchk = "X";
		iEduAmt = eduVal;
	}else if(v_EduBustrYn == "N"){

	}

	/**************************************************************************
	 * sap parameter init end
	 **************************************************************************/

	/**************************************************************************
	 * sgns parameter init start
	 **************************************************************************/
	// 출장자팀 과 집행팀이 다를 경우 추가 결재선 셋팅
	var dSignType = v_dSignType; // 타 집행팀 Y / N
	// 타집행팀이 아닐 경우 null
	var dSignUserCd = v_dSignUserCd; // 타 집행팀장 ORG
	var dSignUserId = v_dSignUserId; // 타 집행티장 ID
	// 타집행 현장소장
	var dHSignUserCd = "";
	var dHSignUserId = "";
	if(dSignType == "Y" && v_BdgtType == "Q"){
		//타집행예산이면서 현장예산일 경우 현장책임관리자가 추가 협의자로 들어간다.
		dHSignUserCd = v_dHSignUserCd;
		dHSignUserId =  v_dHSignUserId;
	}
	// 현장소속 여부
	var hSignType = v_hSignType; // 현장소장 Y / N
	// 현장소속이 아닐경우 null
	var hSignUserCd = ""; // 현장소장 ORG
	var hSignUserId = ""; // 현장소장 ID
	if(hSignType == 'Y'){
		// 현장소속일 경우 결재선에 추가 입력할 아이디 적용
		hSignUserCd = v_hSignUserCd;
		hSignUserId = v_hSignUserId;
	}

	var tSignUserCd = v_tSignUserCd; // 최종결재 ORG
	var tSignUserId = v_tSignUserId; // 최종결재 ID
	var dutySysCd = "SGNS"; // DUTYSYSCD
	var programCode = "SGNS070002"; // 양식코드
	var signDocTitle = "국내출장정산서"; // 양식 제목

	var tripUserId  = tripUser + " " + tripUserNm; // 출장자 ID (사번 + 이름)
	var tripUserOrg = tripUserTeam + " " + tripUserTeamNm.replace(/&/gi,"＆"); // 출장자 팀명에 특수문자 변환(&)
	var drafterId  = gv_userId + " " + gv_userNm; // 작성자 ID
	var drafterOrg = gv_orgCd + " " + gv_orgNm.replace(/&/gi,"＆"); // 작성자 팀명에 특수문자 변환(&)


	var bdgtType = $("#bdgtType").text(); // 경비구분
	var aufnrNo = v_Aufnr; // 예산번호
	var tripTarget = $("#tripTarget").val(); // 출장장소
	var tripPurp = "(국출)"+$("#tripPurpose").val(); // 출장목적
	var startDate = $("input[name='startDate']").val(); // 시작일
	var endDate = $("input[name='endDate']").val(); // 종료일
	var compCar = $("#compCar").val();
	var tAmt1 = tripAmt1; // 항공
	var tAmt2 = tripAmt2; // 고속철도+기차+선박+버스
	var tAmt3 = 0; // 0
	var docNo = v_DocNo; // CMAS 문서번호

	//증빙일자 구함
	var sDate = $("input[name='startDate']").val().split("-");
	var iTravFdate = sDate[0] + sDate[1] + sDate[2]; // 출장사적예정일

	var payDate = $("input[name='payDate']").val().split("-");
	var iPaymDate = payDate[0] + payDate[1] + payDate[2]; // 생성일 - 송금예정일

	// 증빙일자 입력 조정로직 삭제. (ITSM-20200217-0011)
	// 출장 시작일보다 지불예정일이 뒤면 출장시작일
	// 지불예정일보다 출장시작일이 뒤면 지불 예정일
/*	var betDay = ($.datepicker.parseDate("yymmdd" , iPaymDate) - $.datepicker.parseDate("yymmdd" , iTravFdate))/1000/60/60/24;

	var iSendDate = ""; // 증빙일
	if(betDay < 0){
//		alert("출장 시작일이 뒤임");
		iSendDate = iPaymDate;
	}else{
//		alert("지불예정일이 뒤임")
		iSendDate = iTravFdate;
	}
*/

	var oDate = $("input[name='ordDate']").val().split("-");
	var iSendDate = oDate[0] + oDate[1] + oDate[2]; // 기표일자
	var ordDate = iSendDate; // 기표일자

	var ordNo = ""; // 전표번호
	var agencyUser = v_DutyAgncUserNm + " (" + v_DutyAgncUserId + ")"; // 업무대행자
	if(v_DutyAgncUserNm == "" && v_DutyAgncUserId == ""){
			agencyUser = "";
	}

	// 교통비 수령인 수령액
	var siRutLifnr = ""; // 교통비 수령인
	var siRutValue = ""; // 교통비 수령액
	var siRutAccount = "";

	// 임원만 입력됨

	if(v_IsOfficer == "Y"){
		// 안체크
		if($("#noReceipt:checked").size() < 1){
			siRutLifnr = v_ReceiptUserNm + " (" + v_ReceiptUser + ")";
			var rAmount = $("#rAmount").val();
			for(var j = 0; j < rAmount.length; j++){
				rAmount = rAmount.replace(",", "");
			 }
			siRutValue = rAmount;
		}
	}

	var trRecvUser = siRutLifnr; // 교통비수령인
	var trAmount = siRutValue; // 교통비수령금액
	var trAccount = siRutAccount; // 교통비수령계좌

	var isSignEdit = v_isSignEdit; // 결재선 수정여부
//	alert("isSignEdit : " + isSignEdit);

	/**************************************************************************
	 * sgns parameter init end
	 **************************************************************************/


	var detailData = cf_getTranspDetail();

	var ds_DetailData = new DataSet();

	for(var i = 0; i < detailData.length; i++){
		ds_DetailData.add(detailData[i]);
	}


	var draftDataSet;
	// 회사차량 사용시 DetailData 를 입력하지 않는다.
	if($("#compCar").val() == "Y"){
		draftDataSet = {
				ds_Signln : ds_Signln.getAllData("A")
		};
	}else{
		draftDataSet = {
				ds_DetailData : ds_DetailData.getAllData("U"),
				ds_Signln : ds_Signln.getAllData("A")
		};
	}

	//"최종 신청금액" 숫자만 남도록(시작)
	var v_totAmt = $("#totAmt").text();	//최종 신청금액
	if(v_totAmt == undefined || v_totAmt == "") v_totAmt = 0;
	for(var j = 0; j < v_totAmt.length; j++){
		v_totAmt = v_totAmt.replace(",", "");
	}
	for(var j = 0; j < v_totAmt.length; j++){
		v_totAmt = v_totAmt.replace("원", "");
	}
	//"최종 신청금액" 숫자만 남도록(종료)

//9999
	var draftParams = {
			// sap Params
			iBdgtType : iBdgtType,
			iTripType : iTripType,
			iClearType : iClearType,
			iDocType : iDocType,
			iNotesdoc : iNotesdoc,
			iBdgtNo : iBdgtNo,
			iBdgtTeam : iBdgtTeam,
			iBdgtTeamNm : iBdgtTeamNm,
			iSenderId : iSenderId,
			iSendDate : iSendDate,
			iTravId : iTravId,
			iTravTeam : iTravTeam,
			iTravFdate : iTravFdate,
			iTravTdate : iTravTdate,
			iTravPurp : iTravPurp,
			iTravArea : iTravArea,
			iCompCar : iCompCar,
			iPaymDate : iPaymDate,
			iTran1Amt : iTran1Amt,
			iTran2Amt : iTran2Amt,
			iTran3Amt : iTran3Amt,
			iTran4Amt : iTran4Amt,
			iTran5Amt : iTran5Amt,
			iGitaAmt : iGitaAmt,
			iNoDaym : iNoDaym,
			iNoWage : iNoWage,
			iRutLifnr : iRutLifnr,
			iRutValue : iRutValue,
			iPosid : iPosid,
			iBukrs : iBukrs,
			iEduchk : iEduchk,
			iEduAmt : iEduAmt,
			dutyAgncUserId : v_DutyAgncUserId,
			dutyAgncOrgCd : v_DutyAgncOrgCd,
			ddExpnExcl : v_DdExpnExcl,
  			accomoExpnExcl : v_AccomoExpnExcl,
			eduBustrYn : v_EduBustrYn,
			userId : tripUser,
			userOrgCd : tripUserTeam,
			docNo : v_DocNo,
			docSts : v_DocSts,
			receiptUserNm : v_ReceiptUserNm,
			iAccomoDcnt : iAccomoDcnt,
			iTravRem : iTravRem,
			// sgns Params
			dSignType : dSignType,
			dSignUserCd : dSignUserCd,
			dSignUserId : dSignUserId,
			dHSignUserCd : dHSignUserCd,
  			dHSignUserId : dHSignUserId,
			hSignType : hSignType,
			hSignUserCd : hSignUserCd,
			hSignUserId : hSignUserId,
			tSignUserCd : tSignUserCd,
			tSignUserId : tSignUserId,
			dutySysCd : dutySysCd,
			programCode : programCode,
			signDocTitle : signDocTitle,
			tripUserId : tripUserId,
			tripUserOrg : tripUserOrg,
			drafterId : drafterId,
			drafterOrg : drafterOrg,
			bdgtType : bdgtType,
			aufnrNo : aufnrNo,
			tripTarget : tripTarget,
			tripPurp : tripPurp,
			startDate : startDate,
			endDate : endDate,
			compCar : compCar,
			tAmt1 : tAmt1,
			tAmt2 : tAmt2,
			tAmt3 : tAmt3,
			docNo : docNo,
			ordDate : ordDate,
			ordNo : ordNo,
			agencyUser : agencyUser,
			trRecvUser : trRecvUser,
			trAmount : trAmount,
			trAccount : trAccount,
			bdgtCd : v_BdgtType,
			isOrgBoss : v_IsOrgBoss,
			isOfficer : v_IsOfficer,
			tripUser : tripUser,
			tripUserTeam : tripUserTeam,
			isSignEdit : isSignEdit,
			fileAtchId	: gf_IsNull(v_FileAtchId) ? "": v_FileAtchId,
			genAccomoDcnt : $("#genAccomoDcnt").val(),
			accomoAmt : $("#accomoAmt").val(),
			eexpAmt : $("#eexpAmt").val(),
			etcAmt : $("#etcAmt").val(),
			totAmt : v_totAmt,
			rem : $("#rem").val(),
	};

	gf_Transaction("SELECT_SGNS_REMOTE_DRAFT", "/trip/eai/getSendBizTripApplication.xpl", draftParams, draftDataSet, "f_Callback", true);
}

// 교통편에 빈값이 있는지 조사
function f_TranspValCheck(){
	var trData = $("#transpDetail input");

	var result = "Y";

	for(var i = 0; i < trData.size(); i++){
		if(trData[i].value == ""){
			result = "N";
		}
	}
	return result;
}

//교통편에 빈값이 있는지 조사(차량추가)
function f_TranspValCheck2(){

	var trData = cf_getTranspDetail();
	var result = "Y";

	for(var i = 0; i < trData.length; i++){

		var tStart = trData[i].dptPlace;		//출발지
		var tEnd = trData[i].arrPlace;			//도착지
		var tType = trData[i].trafficCls;		//교통수단
		var tRound = trData[i].runtrpOneway;	//왕복편도
		var tDist = trData[i].mvDist;			//이동거리
		var tAmount = trData[i].amt;			//이용금액
		var carUseAmt = trData[i].carUseAmt;	//차량사용금액
		var carUseQty = trData[i].carUseQty;	//차량사용량

		//8차량 만 전체항목 체크, 나머지는 차량사용금액/차량사용량은 체크 안함
		switch(tType){
			case "1" :	//항공
				if(
					tStart == "" ||
					tEnd == "" ||
					tType == "" ||
					tRound == "" ||
					//tDist == "" ||
					tAmount == ""
					//carUseAmt == "" ||
					//carUseQty == ""
				){
					result = "N";
				}
				break;
			case "2" :	//고속철도
				if(
					tStart == "" ||
					tEnd == "" ||
					tType == "" ||
					tRound == "" ||
					//tDist == "" ||
					tAmount == ""
					//carUseAmt == "" ||
					//carUseQty == ""
				){
					result = "N";
				}
				break;
			case "3" :	//기차
				if(
					tStart == "" ||
					tEnd == "" ||
					tType == "" ||
					tRound == "" ||
					//tDist == "" ||
					tAmount == ""
					//carUseAmt == "" ||
					//carUseQty == ""
				){
					result = "N";
				}
				break;
			case "4" :	//선박
				if(
					tStart == "" ||
					tEnd == "" ||
					tType == "" ||
					tRound == "" ||
					//tDist == "" ||
					tAmount == ""
					//carUseAmt == "" ||
					//carUseQty == ""
				){
					result = "N";
				}
				break;
			case "5" :	//고속버스
				if(
					tStart == "" ||
					tEnd == "" ||
					tType == "" ||
					tRound == "" ||
					//tDist == "" ||
					tAmount == ""
					//carUseAmt == "" ||
					//carUseQty == ""
				){
					result = "N";
				}
				break;
			case "6" :	//렌터카
				if(
					tStart == "" ||
					tEnd == "" ||
					tType == "" ||
					tRound == "" ||
					//tDist == "" ||
					tAmount == ""
					//carUseAmt == "" ||
					//carUseQty == ""
				){
					result = "N";
				}
				break;
			case "7" :	//택시
				if(
					tStart == "" ||
					tEnd == "" ||
					tType == "" ||
					tRound == "" ||
					//tDist == "" ||
					tAmount == ""
					//carUseAmt == "" ||
					//carUseQty == ""
				){
					result = "N";
				}
				break;
			case "8" :	//차량
				if(
					tStart == "" ||
					tEnd == "" ||
					tType == "" ||
					tRound == "" ||
					tDist == "" ||
					tAmount == "" ||
					carUseAmt == "" ||
					carUseQty == ""
				){
					result = "N";
				}
				break;
			case "9" :	//고속도로통행료
				if(
					tStart == "" ||
					tEnd == "" ||
					tType == "" ||
					tRound == "" ||
					//tDist == "" ||
					tAmount == ""
					//carUseAmt == "" ||
					//carUseQty == ""
				){
					result = "N";
				}
				break;

			case "A" :	//대중교통
				if(
					tStart == "" ||
					tEnd == "" ||
					tType == "" ||
					tRound == "" ||
					//tDist == "" ||
					tAmount == ""
					//carUseAmt == "" ||
					//carUseQty == ""
				){
					result = "N";
				}
				break;

			default :
				break;
		}

	}

	return result;
}

//교통편의 차량 입력체크(이용금액의 리터 "0" 입력하면 제한)
function f_TranspValCheck3(){

	var trData = cf_getTranspDetail();
	var result = "Y";

	for(var i = 0; i < trData.length; i++){

		var tStart = trData[i].dptPlace;		//출발지
		var tEnd = trData[i].arrPlace;			//도착지
		var tType = trData[i].trafficCls;		//교통수단
		var tRound = trData[i].runtrpOneway;	//왕복편도
		var tDist = trData[i].mvDist;			//이동거리
		var tAmount = trData[i].amt;			//이용금액
		var carUseAmt = trData[i].carUseAmt;	//차량사용금액
		var carUseQty = trData[i].carUseQty;	//차량사용량

		//8차량 만 전체항목 체크
		switch(tType){

			case "8" :	//차량
			if(
				carUseQty == "0"
			){
				result = "N";
			}
			break;

			default :
				break;
		}

	}

	return result;
}

function f_GetTotalAmount() {
	var t3 = $("#tAmountTotal").text();

	for(var j = 0; j < t3.length; j++){
		t3 = t3.replace(",", "");
	}
	for(var j = 0; j < t3.length; j++){
		t3 = t3.replace("원", "");
	}

	//교통비 수령에 자동 입력
//	$("#rAmount").val(t3);

	var t4 = $("#dayAmAmt").text();
	// 체크
	if(v_EduBustrYn == "Y"){
		t4 = $("#eduTripAmt").val();
		if(t4 == undefined || t4 == "") t4 = "0원";
	}
	for(var j = 0; j < t4.length; j++){
		t4 = t4.replace(",", "");
	}
	for(var j = 0; j < t4.length; j++){
		t4 = t4.replace("원", "");
	}
	if(v_EduBustrYn == "Y"){
		if(tripDate != undefined){
			t4 = t4 * tripDate;
		}
	}

//	var t5 = $("#eduTripAmt").val();
//	for(var j = 0; j < t5.length; j++){
//		t5 = t5.replace(",", "");
//	 }
//	for(var j = 0; j < t5.length; j++){
//		t5 = t5.replace("원", "");
//	 }


	var t6 = $("#nightAmAmt").text();
	for(var j = 0; j < t6.length; j++){
		t6 = t6.replace(",", "");
	 }
	for(var j = 0; j < t6.length; j++){
		t6 = t6.replace("원", "");
	 }

	var total = parseInt(t3) + parseInt(t4) + parseInt(t6);

	$("#tripTotalAmt").text(gf_AmtFormat(total) + "원");

	f_GetTotAmt();	//최종신청금액 계산

}

//총한도 계산
function f_GetLimitAccomoAmt() {

	//총한도 계산
	// A = (숙박일수 * 60,000원)
	// B = (출장일수 * 30,000원)
	// A+B = 총한도

	var v_genAccomoDcnt = $("input[id='genAccomoDcnt']").val();	//숙박일수
	if(v_genAccomoDcnt == undefined || v_genAccomoDcnt == "") v_genAccomoDcnt = 0;
	for(var j = 0; j < v_genAccomoDcnt.length; j++){
		v_genAccomoDcnt = v_genAccomoDcnt.replace(",", "");
	}

	v_genAccomoDcnt = v_genAccomoDcnt * 60000;	// A (숙박일수 * 60,000원)

	//B (출장일수 * 30,000원)
	var v_betDay = 0;
	if($("input[name='startDate']").val() != "" && $("input[name='endDate']").val() != ""){
		var sDate = $("input[name='startDate']").val().split("-");
		var eDate = $("input[name='endDate']").val().split("-");

		var sDateTemp = sDate[0] + sDate[1] + sDate[2];
		var eDateTemp = eDate[0] + eDate[1] + eDate[2];

		var startDate = $.datepicker.parseDate("yymmdd" , sDateTemp);
		var endDate = $.datepicker.parseDate("yymmdd" , eDateTemp);

		v_betDay = (endDate - startDate)/1000/60/60/24;
	}
	v_betDay = (v_betDay+1) * 30000;

	var total = parseInt(v_genAccomoDcnt) + parseInt(v_betDay);

	$("#limitAccomoAmt").text(gf_AmtFormat(total) + "원");

}

//숙박비 합계 계산
function f_GetTotAccomoAmt() {


	var v_accomoAmt = $("input[id='accomoAmt']").val();	//숙박비
	if(v_accomoAmt == undefined || v_accomoAmt == "") v_accomoAmt = 0;
	for(var j = 0; j < v_accomoAmt.length; j++){
		v_accomoAmt = v_accomoAmt.replace(",", "");
	}

	var v_eexpAmt = $("input[id='eexpAmt']").val();	//식비
	if(v_eexpAmt == undefined || v_eexpAmt == "") v_eexpAmt = 0;
	for(var j = 0; j < v_eexpAmt.length; j++){
		v_eexpAmt = v_eexpAmt.replace(",", "");
	}

	var v_etcAmt = $("input[id='etcAmt']").val();	//잡비
	if(v_etcAmt == undefined || v_etcAmt == "") v_etcAmt = 0;
	for(var j = 0; j < v_etcAmt.length; j++){
		v_etcAmt = v_etcAmt.replace(",", "");
	}

	var total = parseInt(v_accomoAmt) + parseInt(v_eexpAmt) + parseInt(v_etcAmt);

	$("#totAccomoAmt").text(gf_AmtFormat(total) + "원");

	f_GetTotAmt();	//최종신청금액 계산
}

//최종신청금액 계산
function f_GetTotAmt() {

	var v_tAmountTotal = $("#tAmountTotal").text();	//교통비 합계
	if(v_tAmountTotal == undefined || v_tAmountTotal == "") v_tAmountTotal = 0;
	for(var j = 0; j < v_tAmountTotal.length; j++){
		v_tAmountTotal = v_tAmountTotal.replace(",", "");
	}
	for(var j = 0; j < v_tAmountTotal.length; j++){
		v_tAmountTotal = v_tAmountTotal.replace("원", "");
	}

	var v_totAccomoAmt = $("#totAccomoAmt").text();	//숙박비 합계
	if(v_totAccomoAmt == undefined || v_totAccomoAmt == "") v_totAccomoAmt = 0;
	for(var j = 0; j < v_totAccomoAmt.length; j++){
		v_totAccomoAmt = v_totAccomoAmt.replace(",", "");
	}
	for(var j = 0; j < v_totAccomoAmt.length; j++){
		v_totAccomoAmt = v_totAccomoAmt.replace("원", "");
	}

	var total = parseInt(v_totAccomoAmt) + parseInt(v_tAmountTotal);

	$("#totAmt").text(gf_AmtFormat(total) + "원");

	//대리수령금액에 전체금액 자동 입력
	$("#rAmount").val(gf_AmtFormat(total));

	//전체금액을 대리수령금액과 비교하기 위해 변수에 저장
	v_totalAmt = total;
}

//임시저장된 교통비 삽입값 조회
function cf_AppendSavedTranspDetail(obj){

	$("#transpDetail").append(v_transpTr);

	// last 에 ID 부여
	var idFlag = "tData";
	var indexF = "" + $("#transpDetail tr:last").index();

	idFlag = idFlag + indexF;
	$("#transpDetail tr:last").attr("id", idFlag);

	$("#transpDetail tr:last input[name='tStart']").val(obj.dptPlace);
	$("#transpDetail tr:last input[name='tEnd']").val(obj.arrPlace);
	$("#transpDetail tr:last select[name='tType']").val(obj.trafficCls);
	$("#transpDetail tr:last select[name='tRound']").val(obj.runtrpOneway);
	$("#transpDetail tr:last input[name='tDist']").val(obj.mvDist);
	$("#transpDetail tr:last input[name='tAmount']").val(gf_AmtFormat(obj.amt));
//	$("#transpDetail tr:last input[name='tCarUseAmt']").val(obj.carUseAmt);
	$("#transpDetail tr:last input[name='tCarUseQty']").val(obj.carUseQty);
	//$("#transpDetail tr:last input[name='tReqAmount']").val(gf_AmtFormat(obj.amt));

	//차량일때 금액은 차량사용금액을 가져온다 (시작)
	if(obj.trafficCls == "8"){	//차량
		$("#transpDetail tr:last input[name='tAmount']").val(obj.carUseAmt);	//금액
	}
	//차량일때 금액은 차량사용금액을 가져온다 (종료)



	// 계산차량사용량 자동계산(시작)
	var v_tDist = obj.mvDist;
	var v_tAmtType_new = obj.trafficCls;

	if (v_tDist == ""){
		v_tDist = 0;
	}

	var v_cal = Math.floor(v_tDist / 8);
	var v_cal_result;	//계산된 차량사용량(아래에서 사용하기 위함)

	if(v_tAmtType_new == "8"){	//차량
		$("#transpDetail tr:last input[name='tCalCarUseQty']").val(v_cal); //차량사용량 자동계산
		v_cal_result = v_cal;
	}else if(v_tAmtType_new != "8"){	//차량 외
		$("#transpDetail tr:last input[name='tCalCarUseQty']").val(""); //차량사용량 제거
		v_cal_result = "";
	}
	// 계산차량사용량 자동계산(종료)



	//청구금액 자동계산(시작)
	//var v_tAmtType_new = $(tAmount[i]).parent().parent().find("select[name='tType']").val();
	//var v_tReqAmount = $("input[name='tReqAmount']");

	var v_tDist = obj.mvDist;
	var v_tAmtType_new = obj.trafficCls;
	var v_tAmount = $("#transpDetail tr:last input[name='tAmount']").val();	//이용금액 (상단에 차량은 [차량사용금액]을 [tAmount.val]에 세팅)
	var v_tDist = obj.mvDist;		//이동거리
	var v_tCarUseQty = obj.carUseQty;	//차량사용량
	var v_tCalCarUseQty = v_cal_result;	//계산차량사용량

	if (v_tAmount == ""){
		v_tAmount = 0;
	}
	if (v_tDist == ""){
		v_tDist = 0;
	}
	if (v_tCarUseQty == ""){
		v_tCarUseQty = 0;
	}
	if (v_tCalCarUseQty == ""){
		v_tCalCarUseQty = 0;
	}

	if (v_tAmount != "" && v_tAmount != 0){
		v_tAmount = v_tAmount.replace(",", "");	//금액계산시 콤마 오류 제거
	}

	var v_cal = v_tAmount;	//차량 외
	var v_cal8 = Math.floor( v_tAmount * (v_tCalCarUseQty/v_tCarUseQty) );	//차량

	if(v_tAmtType_new == "8"){	//차량
		if(parseInt(v_tCarUseQty) > parseInt(v_tCalCarUseQty)){	// 차량사용량  > 계산차량사용량      ---> 이용금액 이하로 지급
			$("#transpDetail tr:last input[name='tReqAmount']").val(v_cal8); //청구금액 자동계산
		}else{
			$("#transpDetail tr:last input[name='tReqAmount']").val(v_cal); //청구금액 자동계산
		}
	}else if(v_tAmtType_new != "8"){	//차량 외
		$("#transpDetail tr:last input[name='tReqAmount']").val(v_cal); //청구금액 자동계산
	}



	//청구금액 자동계산(끝)
























	// readonly 처리
	$("#transpDetail tr input[name='tStart']").attr("readonly", "true");
	$("#transpDetail tr input[name='tEnd']").attr("readonly", "true");

	// event rebind
	$("input[name='tStart']").unbind("click");
	$("input[name='tStart']").click(function(e){

		cf_OpenDistSelectPop(e);

	});

	// event rebind
	$("input[name='tEnd']").unbind("click");
	$("input[name='tEnd']").click(function(e){

		cf_OpenDistSelectPop(e);

	});

	$("input[name='tAmount']").unbind("keyup");
	$("input[name='tAmount']").bind('keyup', function(e) {
//alert("a1");
		var tAmount = $("input[name='tReqAmount']");
		var total = 0;
		var airTotal = 0;
		var etcTotal = 0;
		for(var i = 0; i < tAmount.size(); i++){

			//청구금액 자동계산(시작)
			var v_tAmtType_new = $(tAmount[i]).parent().parent().find("select[name='tType']").val();
			var v_tReqAmount = $("input[name='tReqAmount']");

			var v_tAmount = $(tAmount[i]).parent().parent().find("input[name='tAmount']").val();	//이용금액
			var v_tDist = $(tAmount[i]).parent().parent().find("input[name='tDist']").val();		//이동거리
			var v_tCarUseQty = $(tAmount[i]).parent().parent().find("input[name='tCarUseQty']").val();	//차량사용량
			var v_tCalCarUseQty = $(tAmount[i]).parent().parent().find("input[name='tCalCarUseQty']").val();	//계산차량사용량

			if (v_tAmount == ""){
				v_tAmount = 0;
			}
			if (v_tDist == ""){
				v_tDist = 0;
			}
			if (v_tCarUseQty == ""){
				v_tCarUseQty = 0;
			}
			if (v_tCalCarUseQty == ""){
				v_tCalCarUseQty = 0;
			}

			if (v_tAmount != "" && v_tAmount != 0){
				v_tAmount = v_tAmount.replace(",", "");	//금액계산시 콤마 오류 제거
			}

			var v_cal = v_tAmount;	//차량 외
			var v_cal8 = Math.floor( v_tAmount * (v_tCalCarUseQty/v_tCarUseQty) );	//차량

			if(v_tAmtType_new == "8"){	//차량
				if(parseInt(v_tCarUseQty) > parseInt(v_tCalCarUseQty)){	// 차량사용량  >= 계산차량사용량      ---> 이용금액 이하로 지급
					$(v_tReqAmount[i]).parent().parent().find("input[name='tReqAmount']").attr("value", v_cal8);	//청구금액 자동계산
				}else{
					$(v_tReqAmount[i]).parent().parent().find("input[name='tReqAmount']").attr("value", v_cal);	//청구금액 자동계산
				}
			}else if(v_tAmtType_new != "8"){	//차량 외
				$(v_tReqAmount[i]).parent().parent().find("input[name='tReqAmount']").attr("value", v_cal);	//청구금액 자동계산
			}
			//청구금액 자동계산(끝)


			var tVal = tAmount[i].value;
			if(tVal == "") continue;
			for(var j = 0; j < tVal.length; j++){
				tVal = tVal.replace(",", "");
			}
			total = total + parseInt(tVal);
			var tAmtType = $(tAmount[i]).parent().parent().find("select[name='tType']").val();
			if(tAmtType == "1"){
				airTotal = airTotal + parseInt(tVal);
			}else if(tAmtType != "1"){
				etcTotal = etcTotal + parseInt(tVal);
			}
		}

		$("#tAmountTotal").text(gf_AmtFormat(total) + "원");
		$("#tAmountTotal1").text(gf_AmtFormat(airTotal) + "원");
		$("#tAmountTotal2").text(gf_AmtFormat(etcTotal) + "원");

		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}
		e.currentTarget.value = gf_AmtFormat(targetVal);

		tripAmt1 = airTotal;
		tripAmt2 = etcTotal;

		f_GetTotalAmount();

	});

	$("input[name='tCarUseQty']").unbind("keyup");
	$("input[name='tCarUseQty']").bind('keyup', function(e) {

		var tAmount = $("input[name='tReqAmount']");
		var total = 0;
		var airTotal = 0;
		var etcTotal = 0;
		for(var i = 0; i < tAmount.size(); i++){

			//청구금액 자동계산(시작)
			var v_tAmtType_new = $(tAmount[i]).parent().parent().find("select[name='tType']").val();
			var v_tReqAmount = $("input[name='tReqAmount']");

			var v_tAmount = $(tAmount[i]).parent().parent().find("input[name='tAmount']").val();	//이용금액
			var v_tDist = $(tAmount[i]).parent().parent().find("input[name='tDist']").val();		//이동거리
			var v_tCarUseQty = $(tAmount[i]).parent().parent().find("input[name='tCarUseQty']").val();	//차량사용량
			var v_tCalCarUseQty = $(tAmount[i]).parent().parent().find("input[name='tCalCarUseQty']").val();	//계산차량사용량

			if (v_tAmount == ""){
				v_tAmount = 0;
			}
			if (v_tDist == ""){
				v_tDist = 0;
			}
			if (v_tCarUseQty == ""){
				v_tCarUseQty = 0;
			}
			if (v_tCalCarUseQty == ""){
				v_tCalCarUseQty = 0;
			}

			if (v_tAmount != "" && v_tAmount != 0){
				v_tAmount = v_tAmount.replace(",", "");	//금액계산시 콤마 오류 제거
			}

			var v_cal = v_tAmount;	//차량 외
			var v_cal8 = Math.floor( v_tAmount * (v_tCalCarUseQty/v_tCarUseQty) );	//차량

			if(v_tAmtType_new == "8"){	//차량
				//alert('8cal');
				if(parseInt(v_tCarUseQty) > parseInt(v_tCalCarUseQty)){	// 차량사용량  >= 계산차량사용량      ---> 이용금액 이하로 지급
					$(v_tReqAmount[i]).parent().parent().find("input[name='tReqAmount']").attr("value", v_cal8);	//청구금액 자동계산
				}else{
					$(v_tReqAmount[i]).parent().parent().find("input[name='tReqAmount']").attr("value", v_cal);	//청구금액 자동계산
				}
			}else if(v_tAmtType_new != "8"){	//차량 외
				//alert('1cal');
				$(v_tReqAmount[i]).parent().parent().find("input[name='tReqAmount']").attr("value", v_cal);	//청구금액 자동계산
			}
			//청구금액 자동계산(끝)

			var tVal = tAmount[i].value;
			if(tVal == "") continue;
			for(var j = 0; j < tVal.length; j++){
				tVal = tVal.replace(",", "");
			}
			total = total + parseInt(tVal);
			var tAmtType = $(tAmount[i]).parent().parent().find("select[name='tType']").val();
			if(tAmtType == "1"){
				airTotal = airTotal + parseInt(tVal);
			}else if(tAmtType != "1"){
				etcTotal = etcTotal + parseInt(tVal);
			}

		}

		$("#tAmountTotal").text(gf_AmtFormat(total) + "원");
		$("#tAmountTotal1").text(gf_AmtFormat(airTotal) + "원");
		$("#tAmountTotal2").text(gf_AmtFormat(etcTotal) + "원");

		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}
		e.currentTarget.value = gf_AmtFormat(targetVal);

		tripAmt1 = airTotal;
		tripAmt2 = etcTotal;

		f_GetTotalAmount();

	});



//112233
	$("select[name='tType']").unbind("change");
	$("select[name='tType']").bind('change', function(e) {

		var tAmount = $("input[name='tReqAmount']");
		var total = 0;
		var airTotal = 0;
		var etcTotal = 0;
		for(var i = 0; i < tAmount.size(); i++){

			// 차량사용량 활성화(시작)
			var v_tAmtType_new = $(tAmount[i]).parent().parent().find("select[name='tType']").val();
			var v_tCarUseQty = $("input[name='tCarUseQty']");
			if(v_tAmtType_new == "8"){	//차량
				//alert('1차');
				$(v_tCarUseQty[i]).parent().parent().find("input[name='tCarUseQty']").removeAttr("disabled");	//차량사용량 활성화
				$(v_tCarUseQty[i]).parent().parent().find("input[name='tCarUseQty']").removeAttr("readonly");	//차량사용량 활성화
				$(v_tCarUseQty[i]).parent().parent().find("input[name='tDist']").removeAttr("disabled");	//이동거리 활성화
				$(v_tCarUseQty[i]).parent().parent().find("input[name='tDist']").removeAttr("readonly");	//이동거리 활성화
			}else if(v_tAmtType_new != "8"){	//차량 외
				//alert('1차외');
				$(v_tCarUseQty[i]).parent().parent().find("input[name='tCarUseQty']").attr("disabled", "true");	//차량사용량 비활성화
				$(v_tCarUseQty[i]).parent().parent().find("input[name='tCarUseQty']").attr("readonly", "true");	//차량사용량 비활성화
				$(v_tCarUseQty[i]).parent().parent().find("input[name='tCarUseQty']").attr("value", "");		//차량사용량 비활성화
				$(v_tCarUseQty[i]).parent().parent().find("input[name='tDist']").attr("disabled", "true");	//이동거리 비활성화
				$(v_tCarUseQty[i]).parent().parent().find("input[name='tDist']").attr("readonly", "true");	//이동거리 비활성화
				$(v_tCarUseQty[i]).parent().parent().find("input[name='tDist']").attr("value", "");			//이동거리 비활성화
			}
			// 차량사용량 활성화(종료)
/*
			//값 초기화 (시작)
			$(v_tCarUseQty[i]).parent().parent().find("input[name='tDist']").attr("value", "");	//이동거리
			$(v_tCarUseQty[i]).parent().parent().find("input[name='tCalCarUseQty']").attr("value", "");	//계산차량사용량
			$(v_tCarUseQty[i]).parent().parent().find("input[name='tAmount']").attr("value", "");	//이용금액
			$(v_tCarUseQty[i]).parent().parent().find("input[name='tCarUseQty']").attr("value", "");	//차량사용량
			$(v_tCarUseQty[i]).parent().parent().find("input[name='tReqAmount']").attr("value", "");	//청구금액
			//값 초기화 (종료)
*/
/*
			// 계산차량사용량 자동계산(시작)
			var v_tAmtType_new2 = $(tAmount[i]).parent().parent().find("select[name='tType']").val();
			var v_tCalCarUseQty = $("input[name='tCalCarUseQty']");

			var v_tDist = $(tAmount[i]).parent().parent().find("input[name='tDist']").val();

			if (v_tDist == ""){
				v_tDist = 0;
			}

			var v_cal = Math.floor(v_tDist / 8);

			if(v_tAmtType_new2 == "8"){	//차량
				//alert('8cal');
				$(v_tCalCarUseQty[i]).parent().parent().find("input[name='tCalCarUseQty']").attr("value", v_cal);	//차량사용량 비활성화
			}else if(v_tAmtType_new2 != "8"){	//차량 외
				//alert('1cal');
				$(v_tCalCarUseQty[i]).parent().parent().find("input[name='tCalCarUseQty']").attr("value", "");	//차량사용량 비활성화
			}
			// 계산차량사용량 자동계산(종료)

			var tVal = tAmount[i].value;
			if(tVal == "") continue;
			for(var j = 0; j < tVal.length; j++){
				tVal = tVal.replace(",", "");
			}
			total = total + parseInt(tVal);
			var tAmtType = $(tAmount[i]).parent().parent().find("select[name='tType']").val();
			if(tAmtType == "1"){
				alert('+항공');
				airTotal = airTotal + parseInt(tVal);
			}else if(tAmtType == "2" || tAmtType == "3" || tAmtType == "4" || tAmtType == "5"){
				etcTotal = etcTotal + parseInt(tVal);
			}
*/
		}

		//값 초기화 (시작)
		var tType = $("select[name='tType']")
		var v_curIndex = tType.index(e.currentTarget); //몇번째 row

		$(v_tCarUseQty[v_curIndex]).parent().parent().find("input[name='tDist']").attr("value", "");	//이동거리
		$(v_tCarUseQty[v_curIndex]).parent().parent().find("input[name='tCalCarUseQty']").attr("value", "");	//계산차량사용량
		$(v_tCarUseQty[v_curIndex]).parent().parent().find("input[name='tAmount']").attr("value", "");	//이용금액
		$(v_tCarUseQty[v_curIndex]).parent().parent().find("input[name='tCarUseQty']").attr("value", "");	//차량사용량
		$(v_tCarUseQty[v_curIndex]).parent().parent().find("input[name='tReqAmount']").attr("value", "");	//청구금액
		//값 초기화 (종료)

		$("#tAmountTotal").text(gf_AmtFormat(total) + "원");
		$("#tAmountTotal1").text(gf_AmtFormat(airTotal) + "원");
		$("#tAmountTotal2").text(gf_AmtFormat(etcTotal) + "원");

		var targetVal = e.currentTarget.value;

		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}
		e.currentTarget.value = gf_AmtFormat(targetVal);

		tripAmt1 = airTotal;
		tripAmt2 = etcTotal;

		f_GetTotalAmount();

	});

	$("input[name='tDist']").unbind("keydown");
	$("input[name='tDist']").bind('keydown', function(e) {
		var keyID = (e.which) ? e.which : e.keyCode;
		if ((keyID >= 48 && keyID <= 57)||(keyID >=96 && keyID <= 105)|| keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ){
			return true;
		}else{
			alert("숫자만 입력가능합니다.");
			return false;
		}
	});

	$("input[name='tAmount']").unbind("keydown");
	$("input[name='tAmount']").bind('keydown', function(e) {
		var keyID = (e.which) ? e.which : e.keyCode;
		if ((keyID >= 48 && keyID <= 57)||(keyID >=96 && keyID <= 105)|| keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ){
			return true;
		}else{
			alert("숫자만 입력가능합니다.");
			return false;
		}
	});

	$("input[name='tCarUseQty']").unbind("keydown");
	$("input[name='tCarUseQty']").bind('keydown', function(e) {
		var keyID = (e.which) ? e.which : e.keyCode;
		if ((keyID >= 48 && keyID <= 57)||(keyID >=96 && keyID <= 105)|| keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ){
			return true;
		}else{
			alert("숫자만 입력가능합니다.");
			return false;
		}
	});

	$("img[name='delDtl']").unbind("click");
	$("img[name='delDtl']").click(function(e){

		if($("#transpDetail tr").size() < 3){
			gf_AlertMsg("더이상 삭제할 수 없습니다");
		}else{
			// 삭제
			$("#" + e.target.parentNode.parentNode.id).remove();

			var tAmount = $("input[name='tReqAmount']");
			var total = 0;
			var airTotal = 0;
			var etcTotal = 0;
			for(var i = 0; i < tAmount.size(); i++){
				var tVal = tAmount[i].value;
				if(tVal == "") continue;
				for(var j = 0; j < tVal.length; j++){
					tVal = tVal.replace(",", "");
				}
				total = total + parseInt(tVal);
				var tAmtType = $(tAmount[i]).parent().parent().find("select[name='tType']").val();
				if(tAmtType == "1"){
					airTotal = airTotal + parseInt(tVal);
				}else if(tAmtType != "1"){
					etcTotal = etcTotal + parseInt(tVal);
				}
			}

			$("#tAmountTotal").text(gf_AmtFormat(total) + "원");
			$("#tAmountTotal1").text(gf_AmtFormat(airTotal) + "원");
			$("#tAmountTotal2").text(gf_AmtFormat(etcTotal) + "원");

//			var targetVal = e.currentTarget.value;
//			for(var k = 0; k < targetVal.length; k++){
//				targetVal = targetVal.replace(",", "");
//			}
//			e.currentTarget.value = gf_AmtFormat(targetVal);

			tripAmt1 = airTotal;
			tripAmt2 = etcTotal;

			f_GetTotalAmount();
		}

	});

	//3333
	//이동거리 입력시 차량사용량 자동계산
	$("input[name='tDist']").unbind("keyup");
	$("input[name='tDist']").bind('keyup', function(e) {
		//alert('cal1');
		//alert('+change2');
		var tAmount = $("input[name='tAmount']");

		for(var i = 0; i < tAmount.size(); i++){

			var v_tAmtType_new = $(tAmount[i]).parent().parent().find("select[name='tType']").val();
			var v_tCalCarUseQty = $("input[name='tCalCarUseQty']");

			var v_tDist = $(tAmount[i]).parent().parent().find("input[name='tDist']").val();

			if (v_tDist == ""){
				v_tDist = 0;
			}

			var v_cal = Math.floor(v_tDist / 8);

			if(v_tAmtType_new == "8"){	//차량
				//alert('8cal');
				$(v_tCalCarUseQty[i]).parent().parent().find("input[name='tCalCarUseQty']").attr("value", v_cal);	//차량사용량 자동계산
			}else if(v_tAmtType_new != "8"){	//차량 외
				//alert('1cal');
				$(v_tCalCarUseQty[i]).parent().parent().find("input[name='tCalCarUseQty']").attr("value", "");	//차량사용량 제거
			}


			//청구금액 자동계산(시작)
			var v_tAmtType_new = $(tAmount[i]).parent().parent().find("select[name='tType']").val();
			var v_tReqAmount = $("input[name='tReqAmount']");

			var v_tAmount = $(tAmount[i]).parent().parent().find("input[name='tAmount']").val();	//이용금액
			var v_tDist = $(tAmount[i]).parent().parent().find("input[name='tDist']").val();		//이동거리
			var v_tCarUseQty = $(tAmount[i]).parent().parent().find("input[name='tCarUseQty']").val();	//차량사용량
			var v_tCalCarUseQty = $(tAmount[i]).parent().parent().find("input[name='tCalCarUseQty']").val();	//계산차량사용량

			if (v_tAmount == ""){
				v_tAmount = 0;
			}
			if (v_tDist == ""){
				v_tDist = 0;
			}
			if (v_tCarUseQty == ""){
				v_tCarUseQty = 0;
			}
			if (v_tCalCarUseQty == ""){
				v_tCalCarUseQty = 0;
			}

			if (v_tAmount != "" && v_tAmount != 0){
				v_tAmount = v_tAmount.replace(",", "");	//금액계산시 콤마 오류 제거
			}

			var v_cal = v_tAmount;	//차량 외
			var v_cal8 = Math.floor( v_tAmount * (v_tCalCarUseQty/v_tCarUseQty) );	//차량

			if(v_tAmtType_new == "8"){	//차량
				//alert('8cal');
				if(parseInt(v_tCarUseQty) > parseInt(v_tCalCarUseQty)){	// 차량사용량  >= 계산차량사용량      ---> 이용금액 이하로 지급
					$(v_tReqAmount[i]).parent().parent().find("input[name='tReqAmount']").attr("value", v_cal8);	//청구금액 자동계산
				}else{
					$(v_tReqAmount[i]).parent().parent().find("input[name='tReqAmount']").attr("value", v_cal);	//청구금액 자동계산
				}
			}else if(v_tAmtType_new != "8"){	//차량 외
				//alert('1cal');
				$(v_tReqAmount[i]).parent().parent().find("input[name='tReqAmount']").attr("value", v_cal);	//청구금액 자동계산
			}
			//청구금액 자동계산(끝)


		}
	});
/*
	//청구금액 자동계산
	$("input[name='tAmount']").unbind("keyup");
	$("input[name='tAmount']").bind('keyup', function(e) {
//alert('tAmount_a');
		//alert('+change2');
		var tAmount = $("input[name='tAmount']");

		for(var i = 0; i < tAmount.size(); i++){
//alert('tAmount_for1');
			var v_tAmtType_new = $(tAmount[i]).parent().parent().find("select[name='tType']").val();
			var v_tReqAmount = $("input[name='tReqAmount']");

			var v_tAmount = $(tAmount[i]).parent().parent().find("input[name='tAmount']").val();

			if (v_tAmount == ""){
				v_tAmount = 0;
			}
//alert('tAmount_for2:'+v_tAmount);
			var v_cal = v_tAmount;
			//var v_cal = Math.floor(v_tDist / 8);

			if(v_tAmtType_new == "8"){	//차량
				//alert('8cal');
				$(v_tReqAmount[i]).parent().parent().find("input[name='tReqAmount']").attr("value", "");	//청구금액 자동계산
			}else if(v_tAmtType_new != "8"){	//차량 외
				//alert('1cal');
				$(v_tReqAmount[i]).parent().parent().find("input[name='tReqAmount']").attr("value", v_cal);	//청구금액 자동계산
			}
		}
	});
*/
	return obj.amt;


}

//교통비 삽입후 작업
function cf_AppendTranspDetail(){
//alert('add');
	$("#transpDetail").append(v_transpTr);

/*
	var vv_tAmount = $("input[name='tAmount']");
	for(var i = 0; i < vv_tAmount.size(); i++){
		alert('for');
	}
*/

	// last 에 ID 부여
	var idFlag = "tData";
	var indexF = "" + $("#transpDetail tr:last").index();

	idFlag = idFlag + indexF;
	$("#transpDetail tr:last").attr("id", idFlag);

	// readonly 처리
	$("#transpDetail tr input[name='tStart']").attr("readonly", "true");
	$("#transpDetail tr input[name='tEnd']").attr("readonly", "true");

	// event rebind
	$("input[name='tStart']").unbind("click");
	$("input[name='tStart']").click(function(e){

		cf_OpenDistSelectPop(e);

	});

	// event rebind
	$("input[name='tEnd']").unbind("click");
	$("input[name='tEnd']").click(function(e){

		cf_OpenDistSelectPop(e);

	});

	$("input[name='tAmount']").unbind("keyup");
	$("input[name='tAmount']").bind('keyup', function(e) {

		var tAmount = $("input[name='tReqAmount']");
		var total = 0;
		var airTotal = 0;
		var etcTotal = 0;

		for(var i = 0; i < tAmount.size(); i++){

			//청구금액 자동계산(시작)
			var v_tAmtType_new = $(tAmount[i]).parent().parent().find("select[name='tType']").val();
			var v_tReqAmount = $("input[name='tReqAmount']");

			var v_tAmount = $(tAmount[i]).parent().parent().find("input[name='tAmount']").val();	//이용금액
			var v_tDist = $(tAmount[i]).parent().parent().find("input[name='tDist']").val();		//이동거리
			var v_tCarUseQty = $(tAmount[i]).parent().parent().find("input[name='tCarUseQty']").val();	//차량사용량
			var v_tCalCarUseQty = $(tAmount[i]).parent().parent().find("input[name='tCalCarUseQty']").val();	//계산차량사용량

			if (v_tAmount == ""){
				v_tAmount = 0;
			}
			if (v_tDist == ""){
				v_tDist = 0;
			}
			if (v_tCarUseQty == ""){
				v_tCarUseQty = 0;
			}
			if (v_tCalCarUseQty == ""){
				v_tCalCarUseQty = 0;
			}

			if (v_tAmount != "" && v_tAmount != 0){
				v_tAmount = v_tAmount.replace(",", "");	//금액계산시 콤마 오류 제거
			}

			var v_cal = v_tAmount;	//차량 외
			var v_cal8 = Math.floor( v_tAmount * (v_tCalCarUseQty/v_tCarUseQty) );	//차량

			if(v_tAmtType_new == "8"){	//차량
				//alert('8cal');
				if(parseInt(v_tCarUseQty) > parseInt(v_tCalCarUseQty)){	// 차량사용량  > 계산차량사용량      ---> 이용금액 이하로 지급
					$(v_tReqAmount[i]).parent().parent().find("input[name='tReqAmount']").attr("value", v_cal8);	//청구금액 자동계산
				}else{
					$(v_tReqAmount[i]).parent().parent().find("input[name='tReqAmount']").attr("value", v_cal);	//청구금액 자동계산
				}
			}else if(v_tAmtType_new != "8"){	//차량 외
				//alert('1cal');
				$(v_tReqAmount[i]).parent().parent().find("input[name='tReqAmount']").attr("value", v_cal);	//청구금액 자동계산
			}
			//청구금액 자동계산(끝)

			var tVal = tAmount[i].value;
			tVal = tVal.replace(/[^0-9]/gi,"");
			if(tVal == "") continue;
			for(var j = 0; j < tVal.length; j++){
				tVal = tVal.replace(",", "");
			}
			total = total + parseInt(tVal);
			var tAmtType = $(tAmount[i]).parent().parent().find("select[name='tType']").val();
			if(tAmtType == "1"){
				airTotal = airTotal + parseInt(tVal);
			}else if(tAmtType != "1"){
				etcTotal = etcTotal + parseInt(tVal);
			}
		}

		$("#tAmountTotal").text(gf_AmtFormat(total) + "원");
		$("#tAmountTotal1").text(gf_AmtFormat(airTotal) + "원");
		$("#tAmountTotal2").text(gf_AmtFormat(etcTotal) + "원");

//		var targetVal = e.currentTarget.value;
//		for(var k = 0; k < targetVal.length; k++){
//			targetVal = targetVal.replace(",", "");
//		}
//		e.currentTarget.value = gf_AmtFormat(targetVal);

		tripAmt1 = airTotal;
		tripAmt2 = etcTotal;

		f_GetTotalAmount();

	});

	$("input[name='tCarUseQty']").unbind("keyup");
	$("input[name='tCarUseQty']").bind('keyup', function(e) {

		var tAmount = $("input[name='tReqAmount']");
		var total = 0;
		var airTotal = 0;
		var etcTotal = 0;
		for(var i = 0; i < tAmount.size(); i++){

			//청구금액 자동계산(시작)
			var v_tAmtType_new = $(tAmount[i]).parent().parent().find("select[name='tType']").val();
			var v_tReqAmount = $("input[name='tReqAmount']");

			var v_tAmount = $(tAmount[i]).parent().parent().find("input[name='tAmount']").val();	//이용금액
			var v_tDist = $(tAmount[i]).parent().parent().find("input[name='tDist']").val();		//이동거리
			var v_tCarUseQty = $(tAmount[i]).parent().parent().find("input[name='tCarUseQty']").val();	//차량사용량
			var v_tCalCarUseQty = $(tAmount[i]).parent().parent().find("input[name='tCalCarUseQty']").val();	//계산차량사용량

			if (v_tAmount == ""){
				v_tAmount = 0;
			}
			if (v_tDist == ""){
				v_tDist = 0;
			}
			if (v_tCarUseQty == ""){
				v_tCarUseQty = 0;
			}
			if (v_tCalCarUseQty == ""){
				v_tCalCarUseQty = 0;
			}

			if (v_tAmount != "" && v_tAmount != 0){
				v_tAmount = v_tAmount.replace(",", "");	//금액계산시 콤마 오류 제거
			}

			var v_cal = v_tAmount;	//차량 외
			var v_cal8 = Math.floor( v_tAmount * (v_tCalCarUseQty/v_tCarUseQty) );	//차량

			if(v_tAmtType_new == "8"){	//차량
				//alert('8cal');
				if(parseInt(v_tCarUseQty) > parseInt(v_tCalCarUseQty)){	// 차량사용량  >= 계산차량사용량      ---> 이용금액 이하로 지급
					$(v_tReqAmount[i]).parent().parent().find("input[name='tReqAmount']").attr("value", v_cal8);	//청구금액 자동계산
				}else{
					$(v_tReqAmount[i]).parent().parent().find("input[name='tReqAmount']").attr("value", v_cal);	//청구금액 자동계산
				}
			}else if(v_tAmtType_new != "8"){	//차량 외
				//alert('1cal');
				$(v_tReqAmount[i]).parent().parent().find("input[name='tReqAmount']").attr("value", v_cal);	//청구금액 자동계산
			}
			//청구금액 자동계산(끝)

			var tVal = tAmount[i].value;
			tVal = tVal.replace(/[^0-9]/gi,"");
			if(tVal == "") continue;
			for(var j = 0; j < tVal.length; j++){
				tVal = tVal.replace(",", "");
			}
			total = total + parseInt(tVal);
			var tAmtType = $(tAmount[i]).parent().parent().find("select[name='tType']").val();
			if(tAmtType == "1"){
				airTotal = airTotal + parseInt(tVal);
			}else if(tAmtType != "1"){
				etcTotal = etcTotal + parseInt(tVal);
			}

		}

		$("#tAmountTotal").text(gf_AmtFormat(total) + "원");
		$("#tAmountTotal1").text(gf_AmtFormat(airTotal) + "원");
		$("#tAmountTotal2").text(gf_AmtFormat(etcTotal) + "원");

//		var targetVal = e.currentTarget.value;
//		for(var k = 0; k < targetVal.length; k++){
//			targetVal = targetVal.replace(",", "");
//		}
//		e.currentTarget.value = gf_AmtFormat(targetVal);

		tripAmt1 = airTotal;
		tripAmt2 = etcTotal;

		f_GetTotalAmount();

	});



//777
	$("select[name='tType']").unbind("change");
	$("select[name='tType']").bind('change', function(e) {
		//alert('a4');
		var tAmount = $("input[name='tReqAmount']");
		var total = 0;
		var airTotal = 0;
		var etcTotal = 0;
		for(var i = 0; i < tAmount.size(); i++){

			// 차량사용량 활성화(시작)
			var v_tAmtType_new = $(tAmount[i]).parent().parent().find("select[name='tType']").val();
			var v_tCarUseQty = $("input[name='tCarUseQty']");
			if(v_tAmtType_new == "8"){	//차량
				//alert('1차');
				$(v_tCarUseQty[i]).parent().parent().find("input[name='tCarUseQty']").removeAttr("disabled");	//차량사용량 활성화
				$(v_tCarUseQty[i]).parent().parent().find("input[name='tCarUseQty']").removeAttr("readonly");	//차량사용량 활성화
				$(v_tCarUseQty[i]).parent().parent().find("input[name='tDist']").removeAttr("disabled");	//이동거리 활성화
				$(v_tCarUseQty[i]).parent().parent().find("input[name='tDist']").removeAttr("readonly");	//이동거리 활성화
				//$(v_tCarUseQty[i]).parent().parent().find("input[name='tCarUseQty']").attr("style", "text-align:right; width:40px; display:block");
				//$(v_tCarUseQty[i]).parent().parent().find("span[id='carLiter']").attr("style", "display:block");			//리터 표시 (보이게)
			}else if(v_tAmtType_new != "8"){	//차량 외
				//alert('1차외');
				$(v_tCarUseQty[i]).parent().parent().find("input[name='tCarUseQty']").attr("disabled", "true");	//차량사용량 비활성화
				$(v_tCarUseQty[i]).parent().parent().find("input[name='tCarUseQty']").attr("readonly", "true");	//차량사용량 비활성화
				$(v_tCarUseQty[i]).parent().parent().find("input[name='tCarUseQty']").attr("value", "");		//차량사용량 비활성화
				$(v_tCarUseQty[i]).parent().parent().find("input[name='tDist']").attr("disabled", "true");	//이동거리 비활성화
				$(v_tCarUseQty[i]).parent().parent().find("input[name='tDist']").attr("readonly", "true");	//이동거리 비활성화
				$(v_tCarUseQty[i]).parent().parent().find("input[name='tDist']").attr("value", "");			//이동거리 비활성화
				//$(v_tCarUseQty[i]).parent().parent().find("input[name='tCarUseQty']").attr("style", "display:none");	//리터 표시 (안보이게)
				//$(v_tCarUseQty[i]).parent().parent().find("span[id='carLiter']").attr("style", "display:none");			//리터 표시 (안보이게)
			}
			// 차량사용량 활성화(종료)


			// 계산차량사용량 자동계산(시작)
			var v_tAmtType_new2 = $(tAmount[i]).parent().parent().find("select[name='tType']").val();
			var v_tCalCarUseQty = $("input[name='tCalCarUseQty']");

			var v_tDist = $(tAmount[i]).parent().parent().find("input[name='tDist']").val();

			if (v_tDist == ""){
				v_tDist = 0;
			}

			var v_cal = Math.floor(v_tDist / 8);

			if(v_tAmtType_new2 == "8"){	//차량
				//alert('8cal');
				$(v_tCalCarUseQty[i]).parent().parent().find("input[name='tCalCarUseQty']").attr("value", v_cal);	//차량사용량 비활성화
			}else if(v_tAmtType_new2 != "8"){	//차량 외
				//alert('1cal');
				$(v_tCalCarUseQty[i]).parent().parent().find("input[name='tCalCarUseQty']").attr("value", "");	//차량사용량 비활성화
			}
			// 계산차량사용량 자동계산(종료)


			var tVal = tAmount[i].value;
			if(tVal == "") continue;
			for(var j = 0; j < tVal.length; j++){
				tVal = tVal.replace(",", "");
			}
			total = total + parseInt(tVal);
			var tAmtType = $(tAmount[i]).parent().parent().find("select[name='tType']").val();
			if(tAmtType == "1"){
				airTotal = airTotal + parseInt(tVal);
			}else if(tAmtType != "1"){
				etcTotal = etcTotal + parseInt(tVal);
			}





		}

		//값 초기화 (시작)
		var tType = $("select[name='tType']")
		var v_curIndex = tType.index(e.currentTarget); //몇번째 row

		$(v_tCarUseQty[v_curIndex]).parent().parent().find("input[name='tDist']").attr("value", "");	//이동거리
		$(v_tCarUseQty[v_curIndex]).parent().parent().find("input[name='tCalCarUseQty']").attr("value", "");	//계산차량사용량
		$(v_tCarUseQty[v_curIndex]).parent().parent().find("input[name='tAmount']").attr("value", "");	//이용금액
		$(v_tCarUseQty[v_curIndex]).parent().parent().find("input[name='tCarUseQty']").attr("value", "");	//차량사용량
		$(v_tCarUseQty[v_curIndex]).parent().parent().find("input[name='tReqAmount']").attr("value", "");	//청구금액
		//값 초기화 (종료)

		$("#tAmountTotal").text(gf_AmtFormat(total) + "원");
		$("#tAmountTotal1").text(gf_AmtFormat(airTotal) + "원");
		$("#tAmountTotal2").text(gf_AmtFormat(etcTotal) + "원");

		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}
		e.currentTarget.value = gf_AmtFormat(targetVal);

		tripAmt1 = airTotal;
		tripAmt2 = etcTotal;

		f_GetTotalAmount();

	});

	$("input[name='tDist']").unbind("keydown");
	$("input[name='tDist']").bind('keydown', function(e) {
		var keyID = (e.which) ? e.which : e.keyCode;
		if ((keyID >= 48 && keyID <= 57)||(keyID >=96 && keyID <= 105)|| keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ){
			return true;
		}else{
			alert("숫자만 입력가능합니다.");
			return false;
		}
	});

	$("input[name='tAmount']").unbind("keydown");
	$("input[name='tAmount']").bind('keydown', function(e) {
		var keyID = (e.which) ? e.which : e.keyCode;
		if ((keyID >= 48 && keyID <= 57)||(keyID >=96 && keyID <= 105)|| keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ){
			return true;
		}else{
			alert("숫자만 입력가능합니다.");
			return false;
		}
	});

	$("input[name='tCarUseQty']").unbind("keydown");
	$("input[name='tCarUseQty']").bind('keydown', function(e) {
		var keyID = (e.which) ? e.which : e.keyCode;
		if ((keyID >= 48 && keyID <= 57)||(keyID >=96 && keyID <= 105)|| keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ){
			return true;
		}else{
			alert("숫자만 입력가능합니다.");
			return false;
		}
	});

//	$("#rAmount").bind('keyup', function(e) {
//		var targetVal = e.currentTarget.value;
//
//		var totalAmt = tripAmt1 + tripAmt2;
//
//		for(var k = 0; k < targetVal.length; k++){
//			targetVal = targetVal.replace(",", "");
//		}
//		if(parseInt(targetVal) > totalAmt){
//			gf_AlertMsg("교통비 총 한도를 넘을 수 없습니다.");
//			e.currentTarget.value = 0;
//			return;
//		}
//		e.currentTarget.value = gf_AmtFormat(targetVal);
//
//		f_GetTotalAmount();
//
//	});

	$("img[name='delDtl']").unbind("click");
	$("img[name='delDtl']").click(function(e){

		if($("#transpDetail tr").size() < 3){
			gf_AlertMsg("더이상 삭제할 수 없습니다");
		}else{
			// 삭제
			$("#" + e.target.parentNode.parentNode.id).remove();

			var tAmount = $("input[name='tReqAmount']");
			var total = 0;
			var airTotal = 0;
			var etcTotal = 0;
			for(var i = 0; i < tAmount.size(); i++){
				var tVal = tAmount[i].value;
				if(tVal == "") continue;
				for(var j = 0; j < tVal.length; j++){
					tVal = tVal.replace(",", "");
				}
				total = total + parseInt(tVal);
				var tAmtType = $(tAmount[i]).parent().parent().find("select[name='tType']").val();
				if(tAmtType == "1"){
					airTotal = airTotal + parseInt(tVal);
				}else if(tAmtType != "1"){
					etcTotal = etcTotal + parseInt(tVal);
				}
			}

			$("#tAmountTotal").text(gf_AmtFormat(total) + "원");
			$("#tAmountTotal1").text(gf_AmtFormat(airTotal) + "원");
			$("#tAmountTotal2").text(gf_AmtFormat(etcTotal) + "원");

			var targetVal = e.currentTarget.value;
			for(var k = 0; k < targetVal.length; k++){
				targetVal = targetVal.replace(",", "");
			}
			e.currentTarget.value = gf_AmtFormat(targetVal);

			tripAmt1 = airTotal;
			tripAmt2 = etcTotal;

			f_GetTotalAmount();
		}

	});

	//3333
	//이동거리 입력시 차량사용량 자동계산
	$("input[name='tDist']").unbind("keyup");
	$("input[name='tDist']").bind('keyup', function(e) {
		//alert('cal1');
		//alert('+change2');
		var tAmount = $("input[name='tAmount']");

		for(var i = 0; i < tAmount.size(); i++){

			var v_tAmtType_new = $(tAmount[i]).parent().parent().find("select[name='tType']").val();
			var v_tCalCarUseQty = $("input[name='tCalCarUseQty']");

			var v_tDist = $(tAmount[i]).parent().parent().find("input[name='tDist']").val();

			if (v_tDist == ""){
				v_tDist = 0;
			}

			var v_cal = Math.floor(v_tDist / 8);

			if(v_tAmtType_new == "8"){	//차량
				//alert('8cal');
				$(v_tCalCarUseQty[i]).parent().parent().find("input[name='tCalCarUseQty']").attr("value", v_cal);	//차량사용량 자동계산
			}else if(v_tAmtType_new != "8"){	//차량 외
				//alert('1cal');
				$(v_tCalCarUseQty[i]).parent().parent().find("input[name='tCalCarUseQty']").attr("value", "");	//차량사용량 자동계산
			}



			//청구금액 자동계산(시작)
			var v_tAmtType_new = $(tAmount[i]).parent().parent().find("select[name='tType']").val();
			var v_tReqAmount = $("input[name='tReqAmount']");

			var v_tAmount = $(tAmount[i]).parent().parent().find("input[name='tAmount']").val();	//이용금액
			var v_tDist = $(tAmount[i]).parent().parent().find("input[name='tDist']").val();		//이동거리
			var v_tCarUseQty = $(tAmount[i]).parent().parent().find("input[name='tCarUseQty']").val();	//차량사용량
			var v_tCalCarUseQty = $(tAmount[i]).parent().parent().find("input[name='tCalCarUseQty']").val();	//계산차량사용량

			if (v_tAmount == ""){
				v_tAmount = 0;
			}
			if (v_tDist == ""){
				v_tDist = 0;
			}
			if (v_tCarUseQty == ""){
				v_tCarUseQty = 0;
			}
			if (v_tCalCarUseQty == ""){
				v_tCalCarUseQty = 0;
			}

			if (v_tAmount != "" && v_tAmount != 0){
				v_tAmount = v_tAmount.replace(",", "");	//금액계산시 콤마 오류 제거
			}

			var v_cal = v_tAmount;	//차량 외
			var v_cal8 = Math.floor( v_tAmount * (v_tCalCarUseQty/v_tCarUseQty) );	//차량

			if(v_tAmtType_new == "8"){	//차량
				//alert('8cal');
				if(parseInt(v_tCarUseQty) > parseInt(v_tCalCarUseQty)){	// 차량사용량  >= 계산차량사용량      ---> 이용금액 이하로 지급
					$(v_tReqAmount[i]).parent().parent().find("input[name='tReqAmount']").attr("value", v_cal8);	//청구금액 자동계산
				}else{
					$(v_tReqAmount[i]).parent().parent().find("input[name='tReqAmount']").attr("value", v_cal);	//청구금액 자동계산
				}
			}else if(v_tAmtType_new != "8"){	//차량 외
				//alert('1cal');
				$(v_tReqAmount[i]).parent().parent().find("input[name='tReqAmount']").attr("value", v_cal);	//청구금액 자동계산
			}
			//청구금액 자동계산(끝)




		}
	});

}

function cf_retrieveSignLine(){
		// 화면 상단 결재선 셋팅
		// 현장소속의 경우 현장담당책임자를 가져오지않으므로 수동으로 중간협의자로 넣어줘야한다.
		var params = {
  			orgCd : tripUserTeam,
  			orgCls : "팀/현장",
  			userId : tripUser,
  			hSignType : v_hSignType,
  			hSignUserCd : v_hSignUserCd,
  			hSignUserId : v_hSignUserId
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
		signUserId : sDrafterId, // 세션에서 받아온 값
		signUserNm : sDrafterNm, // 세션에서 받아온 값
		psignUserNm : "",
		apperPositCd : sDrafterPositCd,
		apperPositNm : sDrafterPositCd,
		perpsignPositNm : "",
		apperRpswrkCd : sDrafterRpswrkCd,
		apperRpswrkNm : sDrafterRpswrkCd,
		apperOrgCd : sDrafterOrgCd,
		apperOrgNm : sDrafterOrgNm,
		orgChrcCls : "D"
	});

	// 현장소장 협의
	// 현장 담당자 추가되어야함
	if (v_hSignType == "Y") {
		cnt++;

		if(v_hSignUserRpswrkCd == ""){
			ds_Signln.insert(cnt, {
				signId : "",
				signSeq : cnt + 1,
				signTpCd : "T02",
				signUserId : v_hSignUserId,
				signUserNm : v_hSignUserNm,
				psignUserNm : "",
				apperPositCd : v_hSignUserPositCd,
				apperPositNm : v_hSignUserPositCd,
				perpsignPositNm : "",
				apperRpswrkCd : v_hSignUserRpswrkCd,
				apperRpswrkNm : v_hSignUserRpswrkCd,
				apperOrgCd : v_hSignUserCd,
				apperOrgNm : "",
				orgChrcCls : "D"
			});
		}else{
			ds_Signln.insert(cnt, {
				signId : "",
				signSeq : cnt + 1,
				signTpCd : "T02",
				signUserId : v_hSignUserId,
				signUserNm : v_hSignUserNm,
				psignUserNm : "",
				apperPositCd : v_hSignUserRpswrkCd,
				apperPositNm : v_hSignUserRpswrkCd,
				perpsignPositNm : "",
				apperRpswrkCd : v_hSignUserRpswrkCd,
				apperRpswrkNm : v_hSignUserRpswrkCd,
				apperOrgCd : v_hSignUserCd,
				apperOrgNm : "",
				orgChrcCls : "D"
			});
		}
	}

	// 타집행팀 협의 지정
	// 타집행팀장의 겸직으로 자신이 지정되어 있을 경우는 협의 과정이 빠진다.
	if (v_dSignType == "Y" && v_Chief != tripUser) {

		if(v_BdgtType == "Q"){

			// 타집행팀에 현장일 경우 현장관리책임자 협의가 추가된다.

			if(v_dHSignUserId != ""){
				cnt++;

				if(v_dHSignRpswrkCd == ""){
					ds_Signln.insert(cnt, {
						signId : "",
						signSeq : cnt + 1,
						signTpCd : "T03",
						signUserId : v_dHSignUserId, // 세션에서 받아온 값
						signUserNm : v_dHSignUserNm, // 세션에서 받아온 값
						psignUserNm : "",
						apperPositCd : v_dHSignPositCd,
						apperPositNm : v_dHSignPositCd,
						perpsignPositNm : "",
						apperRpswrkCd : v_dHSignRpswrkCd,
						apperRpswrkNm : v_dHSignRpswrkCd,
						apperOrgCd : v_dHSignUserCd,
						apperOrgNm : "",
						orgChrcCls : "D",
						canDelete : "N" // 결재선 삭제 불가능
					});
				}else{
					ds_Signln.insert(cnt, {
						signId : "",
						signSeq : cnt + 1,
						signTpCd : "T03",
						signUserId : v_dHSignUserId, // 세션에서 받아온 값
						signUserNm : v_dHSignUserNm, // 세션에서 받아온 값
						psignUserNm : "",
						apperPositCd : v_dHSignRpswrkCd,
						apperPositNm : v_dHSignRpswrkCd,
						perpsignPositNm : "",
						apperRpswrkCd : v_dHSignRpswrkCd,
						apperRpswrkNm : v_dHSignRpswrkCd,
						apperOrgCd : v_dHSignUserCd,
						apperOrgNm : "",
						orgChrcCls : "D",
						canDelete : "N" // 결재선 삭제 불가능
					});

				}
			}


		}


		if(v_dSignUserId != ""){

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
					orgChrcCls : "D",
					canDelete : "N" // 결재선 삭제 불가능
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
					orgChrcCls : "D",
					canDelete : "N" // 결재선 삭제 불가능
				});
			}

		}

	}


	// 임원이라면 전결처리
	// 팀/소장 전결 추가
	if (v_IsOrgBoss == "Y" || v_IsOfficer == "Y") {

		cnt++;

		ds_Signln.insert(cnt, {
			signId : "",
			signSeq : cnt + 1,
			signTpCd : "T02",
			signUserId : tripUser, // 세션에서 받아온 값
			signUserNm : tripUserNm, // 세션에서 받아온 값
			psignUserNm : "",
			apperPositCd : tripUserPositCd,
			apperPositNm : tripUserPositCd,
			perpsignPositNm : "",
			apperRpswrkCd : tripUserRpswrkCd,
			apperRpswrkNm : tripUserRpswrkCd,
			apperOrgCd : tripUserTeam,
			apperOrgNm : tripUserTeamNm,
			orgChrcCls : "D"
		});

		v_tSignUserCd = tripUserTeam;
		v_tSignUserId = tripUser;

		gf_AssembleSignln(ds_Signln);
	} else {

		if(resultData.ds_SignlnForExcluRegl == null){
			gf_AlertMsg("최종 결재자가 지정되지 않았습니다. 결재자를 선택해주세요");

			// 예산번호 선택 후 전결 규정 가져올 때 최종 결재자가 없다면 기존에 최종 결재자로 지정된 값 삭제
			v_tSignUserCd = "";
			v_tSignUserId = "";

			// 결재선을 지정할 수 있도록 버튼을 팝업한다.
			$("#innerTripSign").show();
		}else{
//			$("#innerTripSign").hide();
			for ( var i = 0; i < resultData.ds_SignlnForExcluRegl.length; i++) {

				cnt++;

				var temp = resultData.ds_SignlnForExcluRegl[i];

				if (temp.apperRpswrkCd == "") {
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
				}else{
					ds_Signln.insert(cnt, {
						signId : "",
						signSeq : cnt + 1,
						signTpCd : "T02",
						signUserId : temp.signUserId, // 세션에서 받아온 값
						signUserNm : temp.signUserNm, // 세션에서 받아온 값
						psignUserNm : "",
						apperPositCd : temp.apperRpswrkCd,
						apperPositNm : temp.apperRpswrkNm,
						perpsignPositNm : "",
						apperRpswrkCd : temp.apperRpswrkCd,
						apperRpswrkNm : temp.apperRpswrkNm,
						apperOrgCd : temp.apperOrgCd,
						apperOrgNm : temp.apperOrgNm,
						orgChrcCls : "D"
					});
				}

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

				// 전결라인의 마지막 결재자
				// 마지막 결재자와 집행팀의 팀장을 비교해 타집행팀 예산 여부를 체크한다.
				if (i == resultData.ds_SignlnForExcluRegl.length - 1) {
					v_tSignUserCd = temp.apperOrgCd;
					v_tSignUserId = temp.signUserId;
				}
			}

		}

		gf_AssembleSignln(ds_Signln);
	}

	$(".ajax_overlay").remove();

	v_isSignEdit = "N";
}

function f_SelectSignInfo(obj){
	// 사용자가 선택한 최종결재자
	v_tSign = obj;

	v_tSignUserId = obj.userId;
	v_tSignUserCd = obj.orgCd;

	var params = {
			userId : v_tSignUserId,
			orgCd : v_tSignUserCd
	};

	gf_Transaction("SELECT_T_SIGN_USER_INFO", "/trip/innerTrip/retrieveTSignUserInfo.xpl", params, {}, "f_Callback", true);

}

/**
* @class 결재선 데이터 갱신
* 2015-01-22 국내출장 전용 최종 결재자 입력 추가
*
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function setSignln(selSignln){
	//양식에 표현하기 위한 결재자 DataSet(ds_SignList)에 추가된 결재자를 삭제한다.
	ds_Signln.clear();

	//양식에 표현하기 위한 결재자 DataSet(ds_SignList)에 결재선을 추가한다.
	for(var i = 0 ; i < selSignln.size(); i++){
		ds_Signln.add(selSignln.get(i));

		// 결재자를 별도의 창을 이용해서 직접 지정한 경우에는 마지막 결재자가 최종 결재자가 되도록 한다.
		if(i == selSignln.size()-1){
			// 마지막 결재자
			v_tSignUserCd = selSignln.get(i).signUserId
			v_tSignUserId = selSignln.get(i).apperOrgCd
//			alert("마지막 결재자" + v_tSignUserId + " " + v_tSignUserCd);
		}
	}

	gf_AssembleSignln(ds_Signln);

	v_isSignEdit = "Y";

}


function f_numberOnly(id){

	$("#"+id).bind('keydown', function(e) {
		var keyID = (e.which) ? e.which : e.keyCode;
		if ((keyID >= 48 && keyID <= 57)||(keyID >=96 && keyID <= 105)|| keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ){
			return true;
		}else{
			alert("숫자만 입력가능합니다.");
			return false;
		}
	});

}

// 시내교통비/국내출장/해외출장 중복 신청 방지 체크
function f_CheckDraftDuplication(){

	if($("input[name='startDate']").val() != "" && $("input[name='endDate']").val() != ""){
		var sDate = $("input[name='startDate']").val().split("-");
		var eDate = $("input[name='endDate']").val().split("-");

		var startDate = sDate[0] + sDate[1] + sDate[2];
		var endDate = eDate[0] + eDate[1] + eDate[2];
	}

	var v_userId = tripUser;
	v_stYmd = startDate;
	v_edYmd = endDate;

	var params = {
			userId : v_userId,
			stYmd : v_stYmd,
			edYmd : v_edYmd
	};

	gf_Transaction("CHECK_DRAFT_DUPLICATION", "/trip/cityTransp/retrieveCheckDraftDuplication.xpl", params, {}, "f_Callback", true);

}

//저장,상신
function fn_SetUploadCallback( fileAtchId ) {

	//alert("_fileAtchId1:");
	//alert("_fileAtchId2:"+fileAtchId);
	v_FileAtchId = fileAtchId;

	//alert("back v_FileAtchId:"+v_FileAtchId);
	//return;

	if(v_CanSaveDraft == "1"){
		f_InnerTripSave();	//저장
	}else if(v_CanSaveDraft == "2"){
		f_InnerTripDraft();	//상신
	}

}

//저장 (파일첨부 처리후 저장작업 진행)
function f_InnerTripSave() {

//	// 예산번호 관련
//	v_BdgtType
//	v_Aufnr
//	v_KText
//	v_Kostv
//	v_Kostvnm
//	v_Chief
//	v_Chiefnm
//
//	// SAP 에 등록된 집행팀의 ORG_CD 와 CO 에 등록된 ORG_CD 가 틀리기 때문에 같은 조직이 맞는지
//	// 집행팀장의 ID 로 검증한다.
//	cf_getCouserInfo();
//	// 최종결재자와 비교하기 때문에 작성자팀장 결재선 생성 후에 돌릴것
//	if(v_tSignUserCd != undefined && v_Chief != undefined){
//
//	v_dSignType // 타 집행팀 여부
//	v_dSignUserCd // 타 집행팀장 ORG
//	v_dSignUserId // 타 집행티장 ID
//
//
//	// 출장자 출장자팀
//	var tripUser; // 출장자
//	var tripUserNm;
//	var tripUserTeam; // 출장자팀
//	var tripUserTeamNm;
//
//	// 출장장소
//	val
//	// 출장목적
//	val
//	// 시작일, 종료일
//	input[name='startDate']
//	input[name='endDate']
//	tripDate // 계산값
//	// 날짜 계산하여 셋팅할 것
//
//	// 업무대행자 업무대행자팀
//	v_DutyAgncUserId
//	v_DutyAgncOrgCd
//	// 지불예정일
//	input[name='payDate']
//	// 회사차량 사용여부
//	val
//	// 교육출장 여부
//	v_EduBustrYn
//	// check 처리 하고 로직 처리 할 것
//	// 일당비 제외 여부
//	v_DdExpnExcl
//	// check 처리 하고 로직 처리 할 것
//	// 숙박비 제외 여부
//	v_AccomoExpnExcl
//	// check 처리 하고 로직 처리 할 것
//
//	// 교통비 수령인
//	v_ReceiptUser
//	v_ReceiptUserNm

	// 수령인 금액
//	$("#rAmount").val()

	//DetailData (DataSet 의 View 상태를 제어하기 힘듬, 일괄 삭제 후 Insert 처리 할 것)

//	if(confirm("작성 중인 문서를 저장하시겠습니까?")){

		// 버튼 숨김
		$(".btn").hide();
		// Modal 처리
		gf_InitSpinner(top.$('body'));

		var docSts = "D16";

		var docType;
		if($("input[name='docType']:checked").val() != undefined){
			docType = $("input[name='docType']:checked").val();
		}else{
			docType = "";
		}

		var data = {
				bdgtType : v_BdgtType,
				aufnr : v_Aufnr,
				kText : v_KText,
				kostV : v_Kostv,
				kostVNm : v_Kostvnm,
				chief : v_Chief,
				chiefNm : v_Chiefnm,
				tripUser : tripUser,
				tripUserNm : tripUserNm,
				tripUserTeam : tripUserTeam,
				tripUserTeamNm : tripUserTeamNm,
				tripUserApptOrgCd : tripUserApptOrgCd,
				tripTarget : $("#tripTarget").val(),
				tripPurpose : $("#tripPurpose").val(),
				startDate : $("input[name='startDate']").val(),
				endDate : $("input[name='endDate']").val(),
				tripDate : tripDate,
				dutyAgncUserId : v_DutyAgncUserId,
				dutyAgncUserNm : v_DutyAgncUserNm,
				dutyAgncOrgCd : v_DutyAgncOrgCd,
				payDate : $("input[name='payDate']").val(),
				ordDate : $("input[name='ordDate']").val(),
				compCar : $("#compCar").val(),
				eduBustrYn : v_EduBustrYn,
				eduTripAmt : $("#eduTripAmt").val(),
				ddExpnExcl : v_DdExpnExcl,
				accomoExpnExcl : v_AccomoExpnExcl,
				receiptUser : v_ReceiptUser,
				receiptUserNm : v_ReceiptUserNm,
				rAmount : $("#rAmount").val(),
				drafterId : gv_userId,
				drafterNm : gv_userNm,
				drafterOrgCd : gv_orgCd,
				drafterOrgNm : gv_orgNm,
				drafterRpswrkCd : gv_userRpswrkCd,
				drafterPositCd : gv_userPositCd,
				docType : docType,
				signln : JSON.stringify(ds_Signln.getAllData()), // 20150721 전결라인 저장
				v_dSignType : v_dSignType,
				v_dSignUserCd : v_dSignUserCd,
				v_dSignUserId : v_dSignUserId,
				v_dSignUserNm : v_dSignUserNm,
				v_dSignOrgNm : v_dSignOrgNm,
				v_dSignRpwrkNm : v_dSignRpwrkNm,
				v_dSignRpswrkCd : v_dSignRpswrkCd,
				v_dSignPositCd : v_dSignPositCd,
				v_dSignPositNm : v_dSignPositNm,
				v_hSignType : v_hSignType,
				v_hSignUserCd : v_hSignUserCd,
				v_hSignUserId : v_hSignUserId,
				v_hSignUserNm : v_hSignUserNm,
				v_hSignUserPositCd : v_hSignUserPositCd,
				v_hSignUserRpswrkCd : v_hSignUserRpswrkCd,
				v_dHSignUserCd : v_dHSignUserCd,
				v_dHSignUserId : v_dHSignUserId,
				v_dHSignUserNm : v_dHSignUserNm,
				v_dHSignOrgNm : v_dHSignOrgNm,
				v_dHSignRpwrkNm : v_dHSignRpwrkNm,
				v_dHSignRpswrkCd : v_dHSignRpswrkCd,
				v_dHSignPositCd : v_dHSignPositCd,
				v_dHSignPositNm : v_dHSignPositNm,
				v_tSignUserCd : v_tSignUserCd,
				v_tSignUserId : v_tSignUserId,
				v_IsOrgBoss : v_IsOrgBoss,
				v_IsOfficer : v_IsOfficer,
				noReceipt : $("#noReceipt:checked").size(),
				v_isSignEdit : v_isSignEdit
		};

		var detailData = cf_getTranspDetail();

		var ds_DetailData = new DataSet();

		for(var i = 0; i < detailData.length; i++){
			ds_DetailData.add(detailData[i]);
		}


		var draftDataSet;
		// 회사차량 사용시 DetailData 를 입력하지 않는다.
		if($("#compCar").val() == "Y"){
			draftDataSet = {};
		}else{
			draftDataSet = {
					ds_DetailData : ds_DetailData.getAllData("U")
			};
		}

		//"최종 신청금액" 숫자만 남도록(시작)
		var v_totAmt = $("#totAmt").text();	//최종 신청금액
		if(v_totAmt == undefined || v_totAmt == "") v_totAmt = 0;
		for(var j = 0; j < v_totAmt.length; j++){
			v_totAmt = v_totAmt.replace(",", "");
		}
		for(var j = 0; j < v_totAmt.length; j++){
			v_totAmt = v_totAmt.replace("원", "");
		}
		//"최종 신청금액" 숫자만 남도록(종료)

		var param = {
				docNo : v_DocNo,
				docSts : docSts,
				fileAtchId	: gf_IsNull(v_FileAtchId) ? "": v_FileAtchId,
				genAccomoDcnt : $("#genAccomoDcnt").val(),
				accomoAmt : $("#accomoAmt").val(),
				eexpAmt : $("#eexpAmt").val(),
				etcAmt : $("#etcAmt").val(),
				totAmt : v_totAmt,
				rem : $("#rem").val(),
				ifParam : JSON.stringify(data)
		};

//		if(v_DocSts == "D16"){
//			gf_Transaction("SAVE_INNER_TRIP_DOC_SAVE", "/trip/innerTrip/updateInnerTripDocSave.xpl", param, draftDataSet, "f_Callback", true);
//		}else{
//			gf_Transaction("SAVE_INNER_TRIP_DOC_SAVE", "/trip/innerTrip/saveInnerTripDocSave.xpl", param, draftDataSet, "f_Callback", true);
//		}

		gf_Transaction("SAVE_INNER_TRIP_DOC_SAVE", "/trip/innerTrip/updateInnerTripDocSave.xpl", param, draftDataSet, "f_Callback", true);
	}

//}


// 상신 (첨부파일 작업후 상신진행)
function f_InnerTripDraft() {

	// 첨부파일 체크(시작)
	var vcf_startDate = $("input[name='startDate']").val();
	if(vcf_startDate >= "2020-11-05"){	//2020-11-05 부터 첨부파일 체크

		var fileAtch = gf_IsNull(v_FileAtchId) ? ""          : v_FileAtchId;
		//alert("s_fileAtch:"+fileAtch);

		var v_FileMsg1 = "[첨부파일]이 등록되지 않았습니다 \n\n";
		v_FileMsg1 += "증빙서류는 필수 첨부사항 입니다";

		if(fileAtch == ""){
			gf_AlertMsg(v_FileMsg1);
			return;
		}

	}
	// 첨부파일 체크(종료)


	//첨부파일 DB에 등록여부 체크_첨부파일 첨부후 삭제시 fileAtchId는 있는데 실제파일이 없을경우 방지(시작)
	var vcf2_startDate = $("input[name='startDate']").val();
	if(vcf2_startDate >= "2020-11-05"){	//2020-11-05 부터 첨부파일 체크

		f_CheckCoFileYn();

		//v_CoFileYn = "Y"	//나중에 막으세요

		var v_FileMsg2 = "[첨부파일]이 존재하지 않습니다 \n\n";
		v_FileMsg2 += "증빙서류는 필수 첨부사항 입니다.";

			if (v_CoFileYn != "Y") {
				 gf_AlertMsg(v_FileMsg2);
				 return;
			}

	}
	//첨부파일 DB에 등록여부 체크_첨부파일 첨부후 삭제시 fileAtchId는 있는데 실제파일이 없을경우 방지(종료)


	if(v_isSaveEnable == "N"){
		v_isSaveEnable = "Y";

		// 클릭 못하도록 버튼 숨김
		$(".btn").hide();


		// 상신시 Modal처리
		gf_InitSpinner(top.$('body'));


		//SAP 상신
		cf_SendBizTrip();

	}else{
		gf_AlertMsg("문서 상신중입니다. 잠시만 기다려주세요.");
		return;
	}

}

//특수문자 체크
function f_patternChk(txt){
	var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
	if( special_pattern.test(txt) == true ){
	    return false;
	}else{
		return true;
	}

}

// 특수문자 체크
function f_docPatterChk(){
	for(var i = 0; i < $("input").size(); i++){
		if(f_patternChk($("input")[i].value) == false){

			var id = $("input")[i].id;
			if(id == ""){
				id = $("input")[i].name;
			}

			if(id == "issNo1"){
				id = "발권번호";
			}else if(id == "issNo2"){
				id = "발권번호";
			}else if(id == "issComm"){
				id = "발권수수료";
			}else if(id == "fare"){
				id = "FARE";
			}else if(id == "warIns"){
				id = "전쟁보험료";
			}else if(id == "airPort1"){
				id = "공항이용료";
			}else if(id == "airPort2"){
				id = "현지공항세";
			}else if(id == "disC"){
				id = "D/C";
			}else if(id == "amtDiv"){
				id = "현금분할입력";
			}else if(id == "issNo2"){
				id = "발권번호";
			}else if(id == "jourN"){
				//여정란은 사용가능
				return true;
			}
			gf_AlertMsg(id + " 란에 특수문자를 입력하실 수 없습니다.");
			return false;
		}
	}
	return true;
}

// 콤마,원 제거
function removeComma(obj){
	for(var i = 0; i < obj.length; i++){
		obj = obj.replace(",", "");
	}
	for(var i = 0; i < obj.length; i++){
		obj = obj.replace("원", "");
	}
	return obj;
}

//e-HR시스템에 국내출장 근태등록여부 체크
function f_CheckEHRAppYn(){

	if($("input[name='startDate']").val() != "" && $("input[name='endDate']").val() != ""){
		var sDate = $("input[name='startDate']").val().split("-");
		var eDate = $("input[name='endDate']").val().split("-");

		var startDate = sDate[0] + sDate[1] + sDate[2];
		var endDate = eDate[0] + eDate[1] + eDate[2];
	}

	var v_tripUserId = tripUser;
	var v_startDate = startDate;
	var v_endDate = endDate;

	//테스트(시작)
	//v_tripUserId = "1202491";
	//v_startDate = "20200805";
	//v_endDate = "20200805";
	//테스트(종료)

	var params = {
			tripUserId : v_tripUserId,
			startDate : v_startDate,
			endDate : v_endDate
	};

	gf_Transaction("CHECK_EHR_APP_YN", "/trip/innerTrip/retrieveCheckEHRAppYn.xpl", params, {}, "f_Callback", false);

}

//첨부파일 DB에 등록여부 체크
function f_CheckCoFileYn(){

	var fileAtchId = gf_IsNull(v_FileAtchId) ? ""          : v_FileAtchId;

	var params = {
			fileAtchId : fileAtchId
	};

	gf_Transaction("CHECK_CO_FILE_YN", "/trip/innerTrip/retrieveCheckCoFileYn.xpl", params, {}, "f_Callback", false);

}

/*
현재날짜를 YYYY-MM-DD 형태로 리턴
fnLPAD()와 같이 필요
*/
function fnToDate()
{
   var today = new Date(); // 날자 변수 선언
   var dateNow = fnLPAD(String(today.getDate()),"0",2); //일자를 구함
   var monthNow = fnLPAD(String((today.getMonth()+1)),"0",2); // 월(month)을 구함
   var yearNow = String(today.getYear()); //년(year)을 구함

   return yearNow + "-" + monthNow + "-" + dateNow;
}

/*
왼쪽에 원하는 텍스트 추가
오라클 LPAD 함수와 같음
val         원래 값
set         왼쪽에 추가하려는 값
cnt         set 갯수
*/
function fnLPAD(val,set,cnt)
{
   if( !set || !cnt || val.length >= cnt)
   {
        return val;
   }

   var max = (cnt - val.length)/set.length;

   for(var i = 0; i < max; i++)
   {
        val = set + val;
   }

   return val;
}