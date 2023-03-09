/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
var v_transpTr = "<tr><td class=\"inpt\" style=\"width:12%; text-align:center\"><input name=\"tStart\" type=\"text\" style=\"width:90px;\"></td>"
	+ "<td class=\"inpt\" style=\"width:12%; text-align:center\"><input name=\"tEnd\" type=\"text\" style=\"width:90px;\"></td>"
	+ "<td class=\"inpt\" style=\"width:15%; text-align:center\"><select name=\"tType\"><option value=\"\" selected=\"selected\"></option>"
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
	+ "<td class=\"inpt\" style=\"width:16%; text-align:left\" value=\"0\"><input name=\"tDist\" type=\"text\" style=\"text-align:right; width:40px;\">km <input name=\"tCalCarUseQty\" type=\"text\" style=\"text-align:right; width:40px;\" disabled=\"disabled\" readonly>ℓ</td>"
	+ "<td class=\"inpt\" style=\"width:20%; text-align:left\" value=\"0\"><input name=\"tAmount\" type=\"text\" style=\"text-align:right; width:80px;\">원 <input name=\"tCarUseQty\" type=\"text\" style=\"text-align:right; width:40px;\" disabled=\"disabled\">ℓ</td>"
	+ "<td class=\"inpt\" style=\"width:15%; text-align:center\" value=\"0\"><input name=\"tReqAmount\" type=\"text\" style=\"text-align:right; width:80px;\" disabled=\"disabled\" readonly>원</td></tr>"
var v_BdgtType = "";

var v_CmasId;

// DataSet
var ds_DocData = new DataSet();
var ds_DetailData = new DataSet();
var ds_sapData = new DataSet();

// 교육 출장 여부
var v_EduBustrYn = "";

var ds_Signln = new DataSet();			//결재선정보

var v_DocSts = "";

var v_RefNo = "";

var v_FileAtchId;		//첨부파일ID

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
	v_CmasId = gf_IsNull(datas.fromList) ? "" : datas.fromList;
	v_DocSts = gf_IsNull(datas.docSts) ? "" : datas.docSts;
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
	$("#clsBtn").click(function(){

		self.close();

	});

	$("#docDelBtn").click(function(){

		if(confirm("신청서를 삭제하시겠습니까?") == true){
			cf_cmasDocDelete();
		};


	});

	$("#createPdf").click(function(){
		// 수동으로 증빙 pdf 신청
		gf_InitSpinner(top.$('body'));

		var params = {
				signId : ds_DocData.get(0).signId,
				refNo : v_RefNo,
				docNo : v_CmasId
		};

		gf_Transaction("SAVE_CREATE_PDF_DOC", "/trip/innerTrip/createPdfDoc.xpl", params, {}, "f_Callback", true);
	});

	$("#confirmSap").click(function(){
		// 수동으로 증빙 pdf 신청
		gf_InitSpinner(top.$('body'));

		if(v_RefNo == "" || v_RefNo == undefined){
			alert("REF NO 가 존재하지 않습니다.");
			return;
		}else if(v_CmasId == "" || v_CmasId == undefined){
			alert("문서번호가 존재하지 않습니다.");
			return;
		}else if(ds_DocData.get(0).dutyAgncUserId == "" || ds_DocData.get(0).dutyAgncUserId == undefined){
			alert("작성자가 등록되어 있지 않습니다.");
			return;
		}else{
			var params = {
					refNo : v_RefNo,
					docNo : v_CmasId,
					confirm : "Y",
					userId : ds_DocData.get(0).dutyAgncUserId
			};

			gf_Transaction("SAVE_CONFIRM_SAP", "/trip/innerTrip/confirmSap.xpl", params, {}, "f_Callback", true);
		}
	});

	$("#confirmAcct").click(function(){

		if(confirm("회계승인 없이 문서를 '회계승인'상태로 변경하시겠습니까?")){
			// 수동으로 승인으로 상태값 변경
			gf_InitSpinner(top.$('body'));

			if(v_CmasId == "" || v_CmasId == undefined){
				alert("문서번호가 존재하지 않습니다.");
				return;
			}else{
				var params = {
						acctSts : '완료',
						docNo : v_CmasId,
						docSts : 'D03',
						retResn : ''
				};

				gf_Transaction("SAVE_CONFIRM_ACCT", "/trip/innerTrip/updateSgnsReject.xpl", params, {}, "f_Callback", true);
			}

		}

	});

	$("#rejectSap").click(function(){

		if(confirm("임시전표를 삭제하시겠습니까? (사용자는 문서 내용을 조회할 수 없게됩니다.)")){

			gf_InitSpinner(top.$('body'));

			if(v_RefNo == "" || v_RefNo == undefined){
				alert("REF NO 가 존재하지 않습니다.");
				return;
			}else if(v_CmasId == "" || v_CmasId == undefined){
				alert("문서번호가 존재하지 않습니다.");
				return;
			}else if(ds_DocData.get(0).dutyAgncUserId == "" || ds_DocData.get(0).dutyAgncUserId == undefined){
				alert("작성자가 등록되어 있지 않습니다.");
				return;
			}else{
				var params = {
						refNo : v_RefNo,
						docNo : v_CmasId,
						confirm : "N",
						userId : ds_DocData.get(0).dutyAgncUserId
				};

				gf_Transaction("SAVE_CONFIRM_SAP", "/trip/innerTrip/confirmSap.xpl", params, {}, "f_Callback", true);
			}

		}


	});

	$("#innerTripDelete").click(function(){
		if(confirm("이 문서를 삭제하시겠습니까?")){

			var param = {
					docNo : v_CmasId
			};

			gf_Transaction("DELETE_INNER_TRIP_DOC", "/trip/innerTrip/deleteInnerTripSavedDoc.xpl", param, {}, "f_Callback", true);
		}

	});







//	$("#addBtn").click(function(){
//
//		cf_AppendTranspDetail();
//
//	});
//
//	$("#delBtn").click(function(){
//		if($("#transpDetail tr").size() < 3){
//			gf_AlertMsg("더이상 삭제할 수 없습니다");
//		}else{
//			$("#transpDetail tr:last").remove();
//		}
//
//	});
//
//	$("#innerTripBdgtChange").click(function(){
//
//		var bdgtParams = {
//				bdgtType : v_BdgtType
//		};
//
//		gf_PostOpen("/common/jsp/comp/budget/bdgtSelect.jsp", null,
//				"toolbar=no,scrollbars=no,width=665,height=600", bdgtParams,
//				true, true, "f_callBackFuncBdgtSelect");
//
//	});
//
//	$("#innerTripDetailConf").click(function(){
//
////		var tAmount = $("#transpDetail tr input[name='tAmount']");
////
////		var total = 0;
////
////		for(var i = 0; i < tAmount.size(); i++){
////
////			var amountTemp = $("#transpDetail tr input[name='tAmount']")[i];
////
////			total = total + parseInt($(amountTemp).val());
////
////		}
////
////		$("#tAmountTotal").text(total);
//
//		//확정 처리 disable
//		$("#transpDetail tr input").attr("readonly", "true");
//		$("#transpDetail tr select").attr("disabled", "true");
//
//
//
//	});
//
//	$("#innerTripDetailEdit").click(function(){
//
//
//		//확정 처리 disable
//		$("#transpDetail tr input").removeAttr("readonly");
//		$("#transpDetail tr select").removeAttr("disabled");
//
//		$("#transpDetail tr input[name='tStart']").attr("readonly", "true");
//		$("#transpDetail tr input[name='tEnd']").attr("readonly", "true");
//
//
//
//	});
//
//	$("input[name='startDate']").bind('change', function() {
//		if($("input[name='startDate']").val() != "" && $("input[name='endDate']").val() != ""){
//			var sDate = $("input[name='startDate']").val().split("-");
//			var eDate = $("input[name='endDate']").val().split("-");
//
//			var sDateTemp = sDate[0] + sDate[1] + sDate[2];
//			var eDateTemp = eDate[0] + eDate[1] + eDate[2];
//
//			var startDate = $.datepicker.parseDate("yymmdd" , sDateTemp);
//			var endDate = $.datepicker.parseDate("yymmdd" , eDateTemp);
//
//			var betDay = (endDate - startDate)/1000/60/60/24;
//
//			var betDayText = "(" + betDay + "일)";
//
//			if(betDay < 1){
//				alert("같은 날짜나 이전일을 선택할 수 없습니다.");
//				$("input[name='startDate']").val("");
//				$("#betDate").text("");
//				return;
//			}else{
//				$("#betDate").text(betDayText);
//			}
//
//		}
//
//	});
//
//	$("input[name='endDate']").bind('change', function() {
//		if($("input[name='startDate']").val() != "" && $("input[name='endDate']").val() != ""){
//
//			var sDate = $("input[name='startDate']").val().split("-");
//			var eDate = $("input[name='endDate']").val().split("-");
//
//			var sDateTemp = sDate[0] + sDate[1] + sDate[2];
//			var eDateTemp = eDate[0] + eDate[1] + eDate[2];
//
//			var startDate = $.datepicker.parseDate("yymmdd" , sDateTemp);
//			var endDate = $.datepicker.parseDate("yymmdd" , eDateTemp);
//
//			var betDay = (endDate - startDate)/1000/60/60/24;
//
//			var betDayText = "(" + betDay + "일)";
//
//			if(betDay < 1){
//				alert("같은 날짜나 이전일을 선택할 수 없습니다.");
//				$("input[name='endDate']").val("");
//				$("#betDate").text("");
//				return;
//			}else{
//				$("#betDate").text(betDayText);
//			}
//
//		}
//
//	});
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
	}

	if(gv_AuthList.auth[0].code.match("RO_CMAS_CO_TRIP_ADM") != null){
		// 관리자
		$("#createPdf").show();

		//통합결재로 제대로 넘어가지 않았을 경우에는 회계 반려 및 신청서 삭제..가 필요할 수도 있다.
		$("#rejectSap").show();
		$("#docDelBtn").show();

		// 결재완료 문서의 경우 회계승인 버튼 생성
		if(v_DocSts == "D03"){
			$("#confirmSap").show();
			$("#confirmAcct").show();
		}

	}else{
		// 관리자가 아닌 경우
	}

	gf_InitSpinner(top.$('body'));
	//소속팀 셋팅
//	var drafterOrgNm = gv_orgNm + " (" + gv_orgCd + ")";
//	$("#drafterOrgNm").text(drafterOrgNm);
//
//	//출장자 셋팅
//	$("#tripUserId_id").val(gv_userId);
//	$("#tripUserId_name").val(gv_userNm);
//
//	cf_AppendTranspDetail();

	var docNoText = v_CmasId.split("-");
	$("#docNo").text(docNoText[1] + "-" + docNoText[2]);


	var acctSts = gf_IsNull(datas.acctSts) ? "" : datas.acctSts;
	var retResn = gf_IsNull(datas.retResn) ? "" : datas.retResn;
	var refNo = gf_IsNull(datas.refNo) ? "" : datas.refNo;
	// P 회계승인 ; R 반려
	if(acctSts == "R" || acctSts == "반려"){
		var overlay = $("<div>") ;
		overlay.addClass("ui-widget-overlay ui-front").appendTo(  $('body') );
		alert("회계반려 된 문서입니다.\nRef No : " + refNo + "\n\n사유 : " + retResn);
		//self.close();
	}else if(v_DocSts == "D04"){
		var overlay = $("<div>") ;
		overlay.addClass("ui-widget-overlay ui-front").appendTo(  $('body') );
		alert("Ref No : " + refNo + "\n\n사유 : " + retResn);
		self.close();
	}else{
		cf_RetrieveViewDocInfo();
		cf_RetrieveSignInfo();
	}


}

/**
* @class 업무시스템과 업무분류에 따른 양식문 Select Box를 구성한다.
* @author 방유성
* @since 2013-07-09
* @version 1.0
*/
function cf_RetrieveViewDocInfo(){

	//해당 업무구분과 업무분류에 해당되는 양식명을 가져옴
	var docParams = {
			cmasId : v_CmasId
	};

	gf_Transaction("SELECT_VIEW_DOC_INFO", "/trip/innerTrip/retrieveViewDocInfo.xpl", docParams, {}, "f_Callback", true);
}

function cf_RetrieveSignInfo(){

	//해당 업무구분과 업무분류에 해당되는 양식명을 가져옴
	var signParams = {
			docNo : v_CmasId
	};

	gf_Transaction("SELECT_SIGN_INFO", "/trip/innerTrip/retrieveSignInfo.xpl", signParams, {}, "f_Callback", true);
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
	  	case "SELECT_SIGN_INFO" :
	  		ds_Signln.clear();
	  		ds_Signln.setAllData(resultData.ds_Sign);

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
			}

	  		gf_AssembleSignln(ds_Signln);

	  		break;
	  	case "SELECT_VIEW_DOC_INFO" :

	  		ds_DocData.setAllData(resultData.ds_DocData);

	  		ds_DetailData.setAllData(resultData.ds_DetailData);

	  		var totalAmt = 0;

	  		for(var i = 0; ds_DetailData.size() > i; i++){

	  			var amt = cf_AppendTranspDetail(ds_DetailData.get(i));

	  			totalAmt = totalAmt + parseInt(amt);


	  			// readonly 처리
	  			$("#transpDetail tr input[name='tStart']").attr("readonly", "true");
	  			$("#transpDetail tr input[name='tEnd']").attr("readonly", "true");
	  			$("#transpDetail tr select[name='tType']").attr("disabled", "true");
	  			$("#transpDetail tr select[name='tRound']").attr("disabled", "true");
	  			$("#transpDetail tr input[name='tDist']").attr("readonly", "true");
	  			$("#transpDetail tr input[name='tCalCarUseQty']").attr("readonly", "true");
	  			$("#transpDetail tr input[name='tAmount']").attr("readonly", "true");
	  			$("#transpDetail tr input[name='tCarUseQty']").attr("readonly", "true");
	  			$("#transpDetail tr input[name='tReqAmount']").attr("readonly", "true");
			}

	  		$("#tAmountTotal").text(gf_AmtFormat(totalAmt)+"원");


	  		ds_sapData.setAllData(resultData.ds_sapData);

	  		if(ds_sapData.get(0).SapItab == undefined){
	  			alert("ESB 서버가 작동하지 않습니다.");

	  			if(gv_AuthList.auth[0].code.match("RO_CMAS_CO_TRIP_ADM") != null){
	  				// 관리자
	  				$(".ajax_overlay").remove();

	  			}else{
	  				// 관리자가 아닌 경우
	  				self.close();
	  			}
	  		}

	  		// 관리자일 경우 CMAS 문서번호 팝업
	  		if(gv_AuthList.auth[0].code.match("RO_CMAS_CO_TRIP_ADM") != null){
	  			$("#docNoTr").show();
	  		}else{
	  			$("#docNoTr").hide();
	  		}

	  		// 일반 사용자에게 결재문서번호를 문서번호에 보여줌
	  		$("#signId").text(ds_DocData.get(0, "signId"));

	  		// 출장자 소속팀
	  		var drafterOrgNm = ds_DocData.get(0, "userOrgNm") + " (" + ds_DocData.get(0, "userOrgCd") + ")";
	  		$("#drafterOrgNm").text(drafterOrgNm);

	  		// 데이터 조회 형식 ds_sapData.get(0).SapItab.Date1
			// Ref No
			$("#tRefNo").text(ds_sapData.get(0).SapItab.Refkey);
			v_RefNo = ds_sapData.get(0).SapItab.Refkey;
			// 경비구분
			var gubun = ds_sapData.get(0).SapItab.Gubun;
			var gText = "";
			switch(gubun) {
		  		case "A" :
		  			gText = "A. 특정경비-임원";
		  			break;
		  		case "B" :
		  			gText = "B. 특정경비-팀장";
		  			break;
		  		case "C" :
		  			gText = "C. 특정경비-팀";
		  			break;
		  		case "I" :
		  			gText = "I. 일반경비";
		  			break;
		  		case "O" :
		  			gText = "O. 입찰경비";
		  			break;
		  		case "P" :
		  			gText = "P. 사업경비";
		  			break;
		  		case "R" :
		  			gText = "R. 기술연구원경비";
		  			break;
		  		case "Q" :
		  			gText = "Q. 현장경비";
		  			break;
		  		case "K" :
		  			gText = "K. 본사집행현장원가";
		  			break;
		  		case "S" :
		  			gText = "S. 사업경비-현장코드";
		  			break;
		  		default :
			  		break;
			}

			var gubun4 = ds_sapData.get(0).SapItab.Gubun4; // 전표구분 H본사, G지역

			if(gubun4 == "H"){
				gText = gText + " (본사)";
			}else if(gubun4 == "G"){
				gText = gText + " (지역)";
			}
			$("#bdgtType").text(gText);
			// 전표번호
			$("#tOrdNo").text(ds_sapData.get(0).SapItab.Belnr);
			// 기표 / 증빙일자 000000 / 000000
//			$("#tOrdDate / tOrdDate2").text();
			$("#tOrdDate").text(ds_sapData.get(0).SapItab.Date1);
			$("#tOrdDate2").text(ds_sapData.get(0).SapItab.Date1);
			// 예산번호
			$("#tAufnr").text(ds_sapData.get(0).SapItab.Aufnr);

			// 출장자
			var tTripUser = ds_DocData.get(0, "userNm") + " (" + ds_DocData.get(0, "userId") + ")";
			$("#tTripUser").text(tTripUser);

			// 업무대행자
			var tTripAgent = ds_DocData.get(0, "dutyAgncOrgNm") + " (" + ds_DocData.get(0, "dutyAgncOrgCd") + ") "+ ds_DocData.get(0, "dutyAgncUserNm") + " (" + ds_DocData.get(0, "dutyAgncUserId") + ")";
			if(ds_DocData.get(0, "dutyAgncUserNm") == "" && ds_DocData.get(0, "dutyAgncUserId") == ""){
				tTripAgent = "";
			}
			$("#tTripAgent").text(tTripAgent);

			// 집행팀
			// 집행팀 이름은 ifParam 에서 가져온다.
			var ifParam;
			if(ds_DocData.get(0, "ifParam") == ""){
				ifParam = {};
			}else{
				ifParam = JSON.parse(ds_DocData.get(0, "ifParam"));
			}
			$("#tExecTeam").text(ifParam.kostvnm + " (" + ds_sapData.get(0).SapItab.Kostl1 + ")");
			// 출장장소
			$("#tTripTarget").text(ds_sapData.get(0).SapItab.Area + " (목적 : " + ds_sapData.get(0).SapItab.Descript.replace("(국출)", "") + ")");
			// 일정
			$("#startDate").text(ds_sapData.get(0).SapItab.Fromdate1);
			$("#endDate").text(ds_sapData.get(0).SapItab.Todate1);

			var sDate = ds_sapData.get(0).SapItab.Fromdate1.split("-");
			var eDate = ds_sapData.get(0).SapItab.Todate1.split("-");

			var sDateTemp = sDate[0] + sDate[1] + sDate[2];
			var eDateTemp = eDate[0] + eDate[1] + eDate[2];

			var startDate = $.datepicker.parseDate("yymmdd" , sDateTemp);
			var endDate = $.datepicker.parseDate("yymmdd" , eDateTemp);

			var betDay = (endDate - startDate)/1000/60/60/24;
			var betDayText = "(" + betDay + "박 " + (betDay+1) + "일)";

			$("#betDate").text(betDayText);

			// 업무대행자
			$("#tTripAgent").text();
			// 송급계좌
			$("#tAccount").text(ds_sapData.get(0).SapItab.Bankno);
			// 지불예정일
			$("#tPayDate").text(ds_sapData.get(0).SapItab.Paydate);
			// 회사차량
			$("#compCar").attr("disabled", "true");
			if(ds_sapData.get(0).SapItab.Transport == undefined){
				$("#compCar").val("N");
			}else{
				$("#compCar").val("Y");
				$("#transpDetail").hide();
			}

//			$("#compCar Y N").text();
			// 교통비
			var amt1 = ds_sapData.get(0).SapItab.Amount1;
			if(amt1 == "0.00"){
				amt1 = "0";
			}
			for(var a = 0; a < amt1.length; a++){
				amt1 = amt1.replace(".", "");
			}

			// 일당비 합계
			var amt2 = ds_sapData.get(0).SapItab.Amount2;
			if(amt2 == "0.00"){
				amt2 = "0";
			}
			for(var s = 0; s < amt2.length; s++){
				amt2 = amt2.replace(".", "");
			}

			// 숙박비 합계
			var amt3 = ds_sapData.get(0).SapItab.Amount3;
			if(amt3 == "0.00"){
				amt3 = "0";
			}
			for(var d = 0; d < amt3.length; d++){
				amt3 = amt3.replace(".", "");
			}

			// 일당비 표준액
			var amt5 = ds_sapData.get(0).SapItab.Amount5;
			if(amt5 == "0.00"){
				amt5 = "0";
			}
			for(var d = 0; d < amt5.length; d++){
				amt5 = amt5.replace(".", "");
			}

			// 숙박비 표중액
			var amt6 = ds_sapData.get(0).SapItab.Amount6;
			if(amt6 == "0.00"){
				amt6 = "0";
			}
			for(var d = 0; d < amt6.length; d++){
				amt6 = amt6.replace(".", "");
			}

			//$("#tAmountTotal").text(gf_AmtFormat(amt1) + "원");
			// 일당비
			$("#dayAmAmt").text(gf_AmtFormat(amt2) + "원");
			// 숙박비
			$("#nightAmAmt").text(gf_AmtFormat(amt3) + "원");

			var dayAmText = "표준액 : " + gf_AmtFormat(amt5) + "원 ";
	  		var nightAmText = "표준액 : " + gf_AmtFormat(amt6) + "원 ";

	  		$("#dayAm").text(dayAmText);
	  		$("#nightAm").text(nightAmText);
			// 합계
//			$("#tTotal").text();
			// 교통비수령
	  		// 교통비 수령자가 존재한다면 표시한다 (임원 이상일 경우만 입력되어짐)
	  		if(ds_sapData.get(0).SapItab.RutLifnr != undefined){
	  			var rutLifnr = ds_sapData.get(0).SapItab.RutLifnr;
	  			for(var i = 0; i < rutLifnr.length; i++){
	  				rutLifnr = rutLifnr.replace("Z", "");
	  			}
	  			$("#receiptUser").text(rutLifnr + " " + ifParam.receiptUserNm);
	  			// 금액 / 계좌
	  			var rutValue = ds_sapData.get(0).SapItab.RutValue;
	  			for(var i = 0; i < rutValue.length; i++){
	  				rutValue = rutValue.replace(".", "");
	  			}
	  			$("#rAmountSpan").text(gf_AmtFormat(rutValue) + "원");
	  			// . 제거
	  			var rAccount = ds_sapData.get(0).SapItab.RutBankn;
	  			$("#rAccountSpan").text(rAccount);

	  		}

			v_EduBustrYn = ds_DocData.get(0).eduBustrYn;
			$("#eduTripChk").attr("disabled", "true");
			if(v_EduBustrYn == "Y"){
				$("#eduTripChk").attr("checked", "true");
				$("#eduTripAmt").text(gf_AmtFormat(amt2) + "원");
			}else if(v_EduBustrYn == "N"){
				$("#eduTripChk").removeAttr("checked");
			}

	  		// 갑지조회 추가
			$("#genAccomoDcnt").text(gf_AmtFormat(ds_DocData.get(0, "genAccomoDcnt"))+"일");	//알반숙박일수
	  		$("#accomoAmt").text(gf_AmtFormat(ds_DocData.get(0, "accomoAmt"))+"원");			//숙박금액
	  		$("#eexpAmt").text(gf_AmtFormat(ds_DocData.get(0, "eexpAmt"))+"원");				//식대금액
	  		$("#etcAmt").text(gf_AmtFormat(ds_DocData.get(0, "etcAmt"))+"원");					//기타금액
	  		$("#totAmt").text(gf_AmtFormat(ds_DocData.get(0, "totAmt"))+"원");					//총금액
	  		$("#rem").text(ds_DocData.get(0, "rem"));						//비고


			f_GetTotalAmount();

			f_GetLimitAccomoAmt();	//총한도 계산

			f_GetTotAccomoAmt()	//숙박비 합계 계산

			var isParam = {
	  				userId : ds_DocData.get(0, "userId"),
	  				orgCd : ds_DocData.get(0, "userOrgCd")
	  		};

			// 작성자
	  		var drafter = ds_DocData.get(0, "fnlEditUserNm") + " (" + ds_DocData.get(0, "fnlEditUserId") + ")";
	  		$("#drafter").text(drafter);

	  		//첨부파일
	  		gf_retrieveFileList(ds_DocData.get(0,"fileAtchId"));

	  		gf_Transaction("SELECT_IS_SPOT_MGMT", "/trip/innerTrip/retrieveIsSpotMgmt.xpl", isParam, {}, "f_Callback", true);

	  		//실비개발로 인한 신/구 조회 구분
	  		var yn_genAccomoDcnt = String(ds_DocData.get(0, "genAccomoDcnt"));
	  			//yn_genAccomoDcnt = String(yn_genAccomoDcnt);
	  			//alert('genV:'+yn_genAccomoDcnt);
	  		if(yn_genAccomoDcnt == ""){
				//alert('old');
				$("#viewOld1").show();	//일당비(구항목)
				$("#viewOld2").show();	//숙박비(구항목)
				$("#viewOld3").show();	//합계(구항목)
				$("#compCarText").show();	//회사차량 사용 여부선택(문구)
				$("#compCar").show();	//회사차량 사용 여부선택

				$("#viewNew1").hide();	//숙박 (신항목)
				$("#viewNew2").hide();	//숙박비 및 기타경비 (신항목)
				$("#viewNew3").hide();	//비고 (신항목)
				$("#viewNew4").hide();	//최종 시청금액 (신항목)
			}else{
				//alert('new');
				$("#viewOld1").hide();	//일당비(구항목)
				$("#viewOld2").hide();	//숙박비(구항목)
				$("#viewOld3").hide();	//합계(구항목)

				$("#compCarText").show();	//회사차량 사용 여부선택(문구)
				$("#compCar").show();	//회사차량 사용 여부선택
				$("#viewNew1").show();	//숙박 (신항목)
				$("#viewNew2").show();	//숙박비 및 기타경비 (신항목)
				$("#viewNew3").show();	//비고 (신항목)
				$("#viewNew4").show();	//최종 시청금액 (신항목)
			}




			break;
	  	case "SELECT_IS_SPOT_MGMT" :
	  		//현장 소속인지 결과를 셋팅한다.
	  		//현장 소속이라면 현장소장/팀장 협의 결재선이 추가된다.

	  		// 0명 이상이라면 현장소속
	  		//resultData.ds_IsSpot

	  		$(".ajax_overlay").remove();

	  		if(resultData.ds_IsSpot != null){
	  			// 현장소장 Y
	  		}else{
	  			// 현장소장 N
	  		}

	  		// 임원여부 검사
	  		if(resultData.ds_Officer != null){
//	  			v_IsOfficer = "Y"; // 임원여부 Y / N
	  			$("#excptNightChkSpan").show();
	  			$("#excptDayChkSpan").show();
	  			$("#eduTripChkSpan").hide();

	  			$("#recpTr").show();

	  		}else{
//	  			v_IsOfficer = "N";
	  			$("#excptNightChkSpan").hide();
	  			$("#excptDayChkSpan").hide();
	  			$("#eduTripChkSpan").show();

	  			$("#recpTr").hide();

	  		}
	  		break;
	  	case "SELECT_CO_USER_INFO" :

	  		break;
	  	case "SAVE_DELETE_CMAS_DOC" :
	  		// 문서가 성공적으로 처리되면 문서 리스트를 다시 조회하여 갱신하여 준다.
	  		if ( !gf_IsNull(v_CallbackFunc) ) {
	  			var openCallbackFunc = "opener."+v_CallbackFunc;
	  	    	eval(openCallbackFunc + "();");
	  	    }

	  		self.close();
	  		break;
	  	case "SAVE_CREATE_PDF_DOC" :
	  		$(".ajax_overlay").remove();
	  		break;
	  	case "SAVE_CONFIRM_SAP" :
	  		if(resultData.ds_Result[0].OErrMsg != undefined) alert(resultData.ds_Result[0].OErrMsg);
	  		$(".ajax_overlay").remove();
	  		break;
	  	case "SAVE_CONFIRM_ACCT" :
	  		$(".ajax_overlay").remove();
	  		break;
	  	default :
	  		break;
	  }
}

function f_callBackFuncDistSelect(obj) {

// v_BdgtType = obj.bdgtType;

	var tStart = obj.startDist + " " + obj.startCity;
	var tEnd = obj.endDist + " " + obj.endCity;

	$("#" + obj.targetId + " input[name='tStart']").val(tStart);
	$("#" + obj.targetId + " input[name='tEnd']").val(tEnd);

	  // 결과 셋팅

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

// ViewDoc 전용
function cf_AppendTranspDetail(obj){

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
	$("#transpDetail tr:last input[name='tCarUseQty']").val(obj.carUseQty);	//차량이용량
	$("#transpDetail tr:last input[name='tReqAmount']").val(obj.amt);	//청구금액

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

	// 차량사용량 활성화(시작)
	var tAmount = $("input[name='tReqAmount']");
	for(var i = 0; i < tAmount.size(); i++){
		var v_tAmtType_new = $(tAmount[i]).parent().parent().find("select[name='tType']").val();
		var v_tCarUseQty = $("input[name='tCarUseQty']");
		if(v_tAmtType_new == "8"){	//차량
			//alert('1차');
			$(v_tCarUseQty[i]).parent().parent().find("input[name='tCarUseQty']").removeAttr("disabled");	//차량사용량 활성화
		}else if(v_tAmtType_new != "8"){	//차량 외
			//alert('1차외');
			$(v_tCarUseQty[i]).parent().parent().find("input[name='tCarUseQty']").attr("disabled", "true");	//차량사용량 비활성화
		}
	}
	// 차량사용량 활성화(종료)


	return obj.amt;

}


 function cf_getTranspDetail(){

	 var dataSize = $("#transpDetail tr").size();
	 var idFlag = "tData";

	 var resultArr = new Array();

	 for(var i = 1; i < dataSize; i++){

		 var tData = new Object();

		 var dId = idFlag + i + " ";
		 var start = $("#" + dId + "input[name='tStart']").val();
		 var end = $("#" + dId + "input[name='tEnd']").val();
		 var type = $("#" + dId + "select[name='tType']").val();
		 var round = $("#" + dId + "select[name='tRound']").val();
		 var dist = $("#" + dId + "input[name='tDist']").val();
		 var amount = $("#" + dId + "input[name='tAmount']").val();

		 for(var j = 0; j < amount.length; j++){
			 amount = amount.replace(",", "");
		 }

		 tData.dptPlace = start;
		 tData.arrPlace = end;
		 tData.trafficCls = type;
		 tData.runtrpOneway = round;
		 tData.mvDist = dist;
		 tData.amt = amount;

		 resultArr.push(tData);

	 }

	 return resultArr;


//	 $("#tData1 input[name='tAmount']").val()


//	 // 출발지
//     input[name='tStart']
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


function gf_AmtFormat(val){
	var rslVal = val;
	var exp = new RegExp("(\\d)(?=(?:\\d{" + 3 + "})+(?!\\d))", "g");
	rslVal = rslVal.toString().replace(exp, '$1,');

	return rslVal;
}

function f_GetTotalAmount() {
	var t3 = $("#tAmountTotal").text();
	for(var j = 0; j < t3.length; j++){
		t3 = t3.replace(",", "");
	 }
	for(var j = 0; j < t3.length; j++){
		t3 = t3.replace("원", "");
	 }

	var t4 = $("#dayAmAmt").text();
	// 체크
	if(v_EduBustrYn == "Y"){
		t4 = $("#eduTripAmt").text();
		if(t4 == undefined || t4 == "") t4 = "0원";
	}
	for(var j = 0; j < t4.length; j++){
		t4 = t4.replace(",", "");
	 }
	for(var j = 0; j < t4.length; j++){
		t4 = t4.replace("원", "");
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


}

function cf_cmasDocDelete(){

	var deleteParams = {
  		docNo : v_CmasId,
  		refNo : v_RefNo,
  		userId : ds_DocData.get(0, "userId")
  	};

	gf_Transaction("SAVE_DELETE_CMAS_DOC", "/trip/innerTrip/deleteCmasDocDelete.xpl", deleteParams, {}, "f_Callback", true);


}

//총한도 계산
function f_GetLimitAccomoAmt() {

	//총한도 계산
	// A = (숙박일수 * 60,000원)
	// B = (출장일수 * 30,000원)
	// A+B = 총한도

	var v_genAccomoDcnt = $("#genAccomoDcnt").text();	//숙박일수
	if(v_genAccomoDcnt == undefined || v_genAccomoDcnt == "") v_genAccomoDcnt = 0;
	v_genAccomoDcnt = removeComma(v_genAccomoDcnt);
	for(var j = 0; j < v_genAccomoDcnt.length; j++){
		v_genAccomoDcnt = v_genAccomoDcnt.replace(",", "");
	}

	v_genAccomoDcnt = v_genAccomoDcnt * 60000;	// A (숙박일수 * 60,000원)

	//B (출장일수 * 30,000원)
	var v_betDay = 0;
	if($("#startDate").text() != "" && $("#endDate").text() != ""){
		var sDate = $("#startDate").text().split("-");
		var eDate = $("#endDate").text().split("-");

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


	var v_accomoAmt = $("#accomoAmt").text();	//숙박비
	if(v_accomoAmt == undefined || v_accomoAmt == "") v_accomoAmt = 0;
	v_accomoAmt = removeComma(v_accomoAmt);
	for(var j = 0; j < v_accomoAmt.length; j++){
		v_accomoAmt = v_accomoAmt.replace(",", "");
	}

	var v_eexpAmt = $("#eexpAmt").text();	//식비
	if(v_eexpAmt == undefined || v_eexpAmt == "") v_eexpAmt = 0;
	v_eexpAmt = removeComma(v_eexpAmt);
	for(var j = 0; j < v_eexpAmt.length; j++){
		v_eexpAmt = v_eexpAmt.replace(",", "");
	}

	var v_etcAmt = $("#etcAmt").text();	//잡비
	if(v_etcAmt == undefined || v_etcAmt == "") v_etcAmt = 0;
	v_etcAmt = removeComma(v_etcAmt);
	for(var j = 0; j < v_etcAmt.length; j++){
		v_etcAmt = v_etcAmt.replace(",", "");
	}

	var total = parseInt(v_accomoAmt) + parseInt(v_eexpAmt) + parseInt(v_etcAmt);

	$("#totAccomoAmt").text(gf_AmtFormat(total) + "원");

	//f_GetTotAmt();	//최종신청금액 계산
}

//콤마,원 제거
function removeComma(obj){
	for(var i = 0; i < obj.length; i++){
		obj = obj.replace(",", "");
	}
	for(var i = 0; i < obj.length; i++){
		obj = obj.replace("원", "");
	}
	for(var i = 0; i < obj.length; i++){
		obj = obj.replace("일", "");
	}
	for(var i = 0; i < obj.length; i++){
		obj = obj.replace("-", "");
	}
	return obj;
}