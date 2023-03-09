/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/

var v_tripUserDetailTr = "<tr><td class=\"inpt\" style=\"width:40%; text-align:center\"><span name=\"tUserEnm\"></span></td>"
 + "<td class=\"inpt\" style=\"width:30%; text-align:center\"><span name=\"tGrade\"></span></td>"
 + "<td class=\"inpt\" style=\"width:30%; text-align:center\"><span name=\"tAirAmount\"></span>원</td></tr>";

var v_visaInfoDetailTr = "<tr><td class=\"inpt\" style=\"width:20%; text-align:center\"><span name=\"vNat\"></span></td><td class=\"inpt\" style=\"width:20%; text-align:center\"><span name=\"vVisaYn\"></span></td>"
 + "<td class=\"inpt\" style=\"width:60%; text-align:center\">	발급일 : <span name=\"vStartDate\" ></span> / 만료일 : <span name=\"vEndDate\"></span></td></tr>";

var v_cityDetailTr = "<tr><td class=\"inpt\" style=\"width:30%; text-align:center\"><span name=\"natNm\"></span> / <span name=\"cityNm\"></span></td>"
+ "<td class=\"inpt\" style=\"width:20%; text-align:center\"><input type=\"text\" name=\"stayStYmd\" style=\"width: 100px;border:0\" readonly=\"readonly\">부터<br><input type=\"text\" name=\"stayEdYmd\" style=\"width: 100px;border:0\" readonly=\"readonly\">까지</td>"
+ "<td class=\"inpt\" style=\"width:50%; text-align:center\"><span name=\"dutyCont\"></span></td></tr>";



var v_CallbackFunc;

var ds_Signln = new DataSet();

var ds_OuterTripDoc = new DataSet();
var ds_ExtnlPer = new DataSet();
var ds_Expn = new DataSet();
var ds_Visa = new DataSet();

var ds_sapData = new DataSet();

var ds_NatList = new DataSet();

var ds_ExpnList = new DataSet();

var v_DocSts = "";
var v_RefKey = "";
var v_SignId = "";
var v_DocNo = "";

var v_EUR;
var v_GBP;
var v_USD;
var v_JPY;

var v_AirTotalAmt = 0;

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

	var signln = gf_IsNull(datas.ds_Signln) ? ""          : datas.ds_Signln;
	v_DocSts = gf_IsNull(datas.v_DocSts) ? ""          : datas.v_DocSts;
	v_RefKey = gf_IsNull(datas.v_RefKey) ? ""          : datas.v_RefKey;
	v_SignId = gf_IsNull(datas.v_SignId) ? ""          : datas.v_SignId;
	v_DocNo = gf_IsNull(datas.v_DocNo) ? ""          : datas.v_DocNo;
	v_EUR = gf_IsNull(datas.v_EUR) ? ""          : datas.v_EUR;
	v_GBP = gf_IsNull(datas.v_GBP) ? ""          : datas.v_GBP;
	v_USD = gf_IsNull(datas.v_USD) ? ""          : datas.v_USD;
	v_JPY = gf_IsNull(datas.v_JPY) ? ""          : datas.v_JPY;

	ds_Signln.setAllData(signln);


	v_CallbackFunc = gf_IsNull(datas.callbackFunc) ? ""          : datas.callbackFunc;
	v_FstLvlPop    = gf_IsNull(datas.fstLvlPop)    ? v_FstLvlPop : eval(datas.fstLvlPop);
	v_IsModal      = gf_IsNull(datas.isModal)      ? v_IsModal   : eval(datas.isModal);
}

/**
* @class Form Onload 시 컴포넌트 초기화
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetComponents()
{





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

	$("#outerTripClose").click(function(){
		self.close();
	});

	$("#print").click(function(){

		$("#btnDiv").hide();
		window.print();
		$("#btnDiv").show();


	});


	// 팝업 close 이벤트 설정 및 정의
	// child window를 모두 close 처리 하고 modal 일경우 부모창을 비활성화 시킨 것을 다시 활성화 처리 한다.
	gf_SetPopCloseEvent(v_IsModal, v_FstLvlPop);

	// Modal 처리를 위한 공통 함수 호출
	gf_EnableOverlayOpener(false, v_FstLvlPop, v_IsModal);




}

/**
* @class Form Onload 시 객체 초기 값 설정
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_InitForm()
{

	// 결재선 생성
	gf_AssembleSignln(ds_Signln);

	ds_OuterTripDoc.setAllData(datas.ds_OuterTripDoc);
	ds_ExtnlPer.setAllData(datas.ds_ExtnlPer);
	ds_Expn.setAllData(datas.ds_Expn);
	ds_Visa.setAllData(datas.ds_Visa);

    ds_sapData.setAllData(datas.ds_sapData);

    ds_NatList.setAllData(datas.ds_NatList);



    // 전표번호
	if(v_DocSts == "D03"){
		//전표번호
		$("#tOrdNoTr").show();
		$("#tOrdNo").text(ds_sapData.get(0, "SapItab").Belnr);

		//지불예정일
		$("#payMDate").text(ds_sapData.get(0, "SapItab").Paydate);
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

	// 문서 번호
	var docNoText = v_DocNo.split("-");
	$("#docNo").text(docNoText[1] + "-" + docNoText[2]);

	var data = JSON.parse(ds_OuterTripDoc.get(0, "ifParam"));

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
	$("#tripUserEnm").text(ds_OuterTripDoc.get(0, "userEnm"));
//	$("#tripUserGrade").val(ds_OuterTripDoc.get(0, "seatGrade"));
	if(ds_OuterTripDoc.get(0, "seatGrade") == "F"){
		$("#tripUserGradeSpan").text("First");

	}else if(ds_OuterTripDoc.get(0, "seatGrade") == "B"){
		$("#tripUserGradeSpan").text("Business");

	}else if(ds_OuterTripDoc.get(0, "seatGrade") == "E"){
		$("#tripUserGradeSpan").text("Economy");

	}
	$("select[name='tripUserGrade']").val(ds_OuterTripDoc.get(0, "seatGrade"));
	$("#tripUserAirAmount").text(gf_AmtFormat(ds_OuterTripDoc.get(0, "afare")));
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
	$("#tPurp").text(ds_OuterTripDoc.get(0, "bustrGl"));
	// 요청사항
	var demItm = ds_OuterTripDoc.get(0, "demItm");
	for(var i = 0; i < demItm.length; i++){
		demItm = demItm.replace("\n", "<br>");
	}
	$("#tReq").html(demItm);

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
		case "K" :
			$("#bdgtType").text("K. 본사집행현장원가");
			break;
		default :
  		break;
	}


	$("#Aufnr").text(data.aufnr + " / 내역 : " + data.kText);
	$("#cGubun").val(data.cGugun);
	$("#bdgtTeam").text(data.kostVNm + " (" + data.kostV + ")");

	if(bdgtType == "I" || bdgtType == "O" ||bdgtType == "K"){
  		// I 일반경비 일 경우 경비 이체코드 입력창을 보인다.
  		$("#VTCodeSpan").show();
  		$("#vTcode").val(data.vTcode);
  	}
//	$("#bdgtTeam").text(data.kostVNm + " (" + data.kostV + ") / 집행팀장 : " + data.chiefNm + " (" + data.chief + ")");

	// 항공료

	// 특이사항
	var pecul = ds_OuterTripDoc.get(0, "pecul");
	for(var i = 0; i < pecul.length; i++){
		pecul = pecul.replace("\n", "<br>");
	}

	$("#pecul").html(pecul);
	// 기타경비
	// 현지항공료 현지교통비 VISAFEE OverCharge 복리후생 접대비 합계
	$("#loAirFare").text(ds_OuterTripDoc.get(0, "lclAfare"));

	$("#loTranFare").text(ds_OuterTripDoc.get(0, "lclTrafficExpn"));

	$("#visaFeeFare").text(ds_OuterTripDoc.get(0, "visaFee"));

	$("#ovCharFare").text(ds_OuterTripDoc.get(0, "excsExpn"));

	$("#vocLeeFare").text(ds_OuterTripDoc.get(0, "wef"));

	$("#hostFare").text(ds_OuterTripDoc.get(0, "svcExpn"));

	// 집행세부내역
	$("#loAirFareDtl").text(data.tAirAmtDtl);
	$("#loTranFareDtl").text(data.tTranspAmtDtl);
	$("#visaFeeFareDtl").text(data.visaFeeDtl);
	$("#ovCharFareDtl").text(data.overChargeDtl);
	$("#vocLeeFareDtl").text(data.benefitsDtl);
	$("#hostFareDtl").text(data.hostAmtDtl);

	// 송급계좌
	$("#retAccount").text(data.accountNo);

	//기타경비지불일자
	$("#etcPayDate").text(data.etcPayDate);






	// 체제비

	// 체제비 합계 단위별 환율조회(작성시 날짜)

	// 개인지급합계
	// 총계
	// 비고
	var rem = ds_OuterTripDoc.get(0, "rem");
	for(var i = 0; i < rem.length; i++){
		rem = rem.replace("\n", "<br>");
	}
	$("#rem").html(rem);
	// 협조내역
	var assistComment = data.assistComment;
	for(var i = 0; i < assistComment.length;i++){
		assistComment = assistComment.replace("##", "<br>");
	}
	$("#assistComment").html(assistComment);

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

	// 환율 및 체제비



		$("#euroRef").text(v_EUR);


		$("#gbpRef").text(v_GBP);


		$("#usdRef").text(v_USD);


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


		$("#tripUserDetail tr:last span[name='tUserEnm']").text(obj.userEnm);
		var seatGrade = "";
		if(obj.seatGrade == "F"){
			seatGrade = "First";
		}else if(obj.seatGrade == "B"){
			seatGrade = "Business";
		}else if(obj.seatGrade == "E"){
			seatGrade = "Economy";
		}

		$("#tripUserDetail tr:last span[name='tGrade']").text(seatGrade);
		$("#tripUserDetail tr:last span[name='tAirAmount']").text(gf_AmtFormat(obj.afare));

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

	$("#visaInfoDetail tr:last span[name='vNat']").text(cf_GetNatNm(obj.natCd));

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

	$("#visaInfoDetail tr:last span[name='vVisaYn']").text(visaOwnYn);
	$("#visaInfoDetail tr:last span[name='vStartDate']").text($.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", obj.visaIssueYmd)));
	$("#visaInfoDetail tr:last span[name='vEndDate']").text($.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", obj.visaExprtnYmd)));

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

	$("#cityDetail tr:last span[name='natNm']").text(cf_GetNatNm(obj.natCd));
	$("#cityDetail tr:last span[name='cityNm']").text(obj.cityNm);

	$("#cityDetail tr:last input[name='stayStYmd']").val($.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", obj.stayStYmd)));
	$("#cityDetail tr:last input[name='stayEdYmd']").val($.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", obj.stayEdYmd)));

	var dutyCont = obj.dutyCont;
	for(var i = 0; i < dutyCont.length; i++){
		dutyCont = dutyCont.replace("\n", "<br>");
	}
	$("#cityDetail tr:last span[name='dutyCont']").html(dutyCont);





}

//국가리스트에서 나라이름으로 나라코드를 구한다.
function cf_GetNatNm(natCd){
	return ds_NatList.get(ds_NatList.find("natCd", natCd), "natNm");
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

//기타비의 총합을 구한다
function totalEtcAmt(){

	var v1 = $("#loAirFare").text();
	v1 = removeComma(v1);
	if(v1 == "") v1 = 0;

	var v2 = $("#loTranFare").text();
	if(v2 == "") v2 = 0;
	v2 = removeComma(v2);

	var v3 = $("#visaFeeFare").text();
	if(v3 == "") v3 = 0;
	v3 = removeComma(v3);

	var v4 = $("#ovCharFare").text();
	if(v4 == "") v4 = 0;
	v4 = removeComma(v4);

	var v5 = $("#vocLeeFare").text();
	if(v5 == "") v5 = 0;
	v5 = removeComma(v5);

	var v6 = $("#hostFare").text();
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


function f_calcAirAmount(){
	var tAmount = $("span[name='tAirAmount']");
	var total = 0;
	for(var i = 0; i < tAmount.size(); i++){
		var tVal = tAmount[i].innerText;
		if(tVal == "") continue;
		for(var j = 0; j < tVal.length; j++){
			tVal = tVal.replace(",", "");
		}
		total = total + parseInt(tVal);
	}

	var tUserVal = $("#tripUserAirAmount").text();

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