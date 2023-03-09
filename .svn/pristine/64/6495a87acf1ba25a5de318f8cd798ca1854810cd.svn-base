/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/

var v_GitaAmt3 = 0; // 기타비용 (접대비)



var v_CallbackFunc;

var ds_Signln = new DataSet();

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

	var jsGubun = gf_IsNull(datas.jsGubun) ? ""          : datas.jsGubun;
	// A  R  N
	if(jsGubun == "A"){
		$("#viewTitle").html("<h6>해외출장 보고서 및 정산서 (추가)</h6>")
	}else if(jsGubun == "R"){
		$("#viewTitle").html("<h6>해외출장 보고서 및 정산서 (반납)</h6>")
	}else if(jsGubun == "N"){
		$("#viewTitle").html("<h6>해외출장 보고서 및 정산서 (일반)</h6>")
	}

	// 출장정보
	var tripUserInfo = gf_IsNull(datas.tripUserInfo) ? ""          : datas.tripUserInfo;
	var tripUserTotal = gf_IsNull(datas.tripUserTotal) ? ""          : datas.tripUserTotal;
	var tripNat = gf_IsNull(datas.tripNat) ? ""          : datas.tripNat;
	var fTripDate = gf_IsNull(datas.fTripDate) ? ""          : datas.fTripDate;
	var rTripDateStart = gf_IsNull(datas.rTripDateStart) ? ""          : datas.rTripDateStart;
	var rTripDateEnd = gf_IsNull(datas.rTripDateEnd) ? ""          : datas.rTripDateEnd;
	var rTripDate = gf_IsNull(datas.rTripDate) ? ""          : datas.rTripDate;
	var payDate = gf_IsNull(datas.payDate) ? ""          : datas.payDate;
	var tReq = gf_IsNull(datas.tReq) ? ""          : datas.tReq;
	for(var i = 0; i < tReq.length; i++){
		tReq = tReq.replace("\n", "<br>");
	}

	$("#tripUserInfo").text(tripUserInfo);
	$("#tripUserTotal").text(tripUserTotal);
	$("#tripNat").text(tripNat);
	$("#fTripDate").text(fTripDate);

	$("#rTripDateStEd").text(rTripDateStart + " ~ " + rTripDateEnd);
	$("#rTripDate").text(rTripDate);

	$("#payDate").text(payDate);
	$("#tReq").html(tReq);


	// 출장 보고 내용

	var tPurp = gf_IsNull(datas.tPurp) ? ""          : datas.tPurp;
	var tPlanDtl = gf_IsNull(datas.tPlanDtl) ? ""          : datas.tPlanDtl;
	for(var i = 0; i < tPlanDtl.length; i++){
		tPlanDtl = tPlanDtl.replace("\n", "<br>");
	}

	var tRptDtl = gf_IsNull(datas.tRptDtl) ? ""          : datas.tRptDtl;
	for(var i = 0; i < tRptDtl.length; i++){
		tRptDtl = tRptDtl.replace("\n", "<br>");
	}

	var tDataList = gf_IsNull(datas.tDataList) ? ""          : datas.tDataList;

	$("#tPurp").text(tPurp);
	$("#tPlanDtl").html(tPlanDtl);
	$("#tRptDtl").html(tRptDtl);
	$("#tDataList").text(tDataList);

	// 이용 숙박
	var s1 = gf_IsNull(datas.s1) ? ""          : datas.s1;
	var s2 = gf_IsNull(datas.s2) ? ""          : datas.s2;
	var s3 = gf_IsNull(datas.s3) ? ""          : datas.s3;

	$("#s1").text(s1);
	$("#s2").text(s2);
	$("#s3").text(s3);

	//출장 경비
	var af1 = gf_IsNull(datas.af1) ? ""          : datas.af1;
	var af2 = gf_IsNull(datas.af2) ? ""          : datas.af2;
	var af3 = gf_IsNull(datas.af3) ? ""          : datas.af3;

	$("#af1").text(af1);
	$("#af2").text(af2);
	$("#af3").text(af3);

	var cTotalAmt = gf_IsNull(datas.cTotalAmt) ? ""          : datas.cTotalAmt;
	var cTotalAmt2 = gf_IsNull(datas.cTotalAmt2) ? ""          : datas.cTotalAmt2;
	var cTotalAmt3 = gf_IsNull(datas.cTotalAmt3) ? ""          : datas.cTotalAmt3;

	$("#cTotalAmt").text(cTotalAmt);
	$("#cTotalAmt2").text(cTotalAmt2);
	$("#cTotalAmt3").text(cTotalAmt3);

	var loAirFare1 = gf_IsNull(datas.loAirFare1) ? ""          : datas.loAirFare1;
	var loAirFareEx1 = gf_IsNull(datas.loAirFareEx1) ? ""          : datas.loAirFareEx1;
	var loAirFare2 = gf_IsNull(datas.loAirFare2) ? ""          : datas.loAirFare2;
	var loAirFareEx2 = gf_IsNull(datas.loAirFareEx2) ? ""          : datas.loAirFareEx2;
	var loAirFare3 = gf_IsNull(datas.loAirFare3) ? ""          : datas.loAirFare3;

	$("#loAirFare1").text(loAirFare1);
	$("#loAirFareEx1").text(loAirFareEx1);
	$("#loAirFare2").text(loAirFare2);
	$("#loAirFareEx2").text(loAirFareEx2);
	$("#loAirFare3").text(loAirFare3);

	var loTranFare1 = gf_IsNull(datas.loTranFare1) ? ""          : datas.loTranFare1;
	var loTranFareEx1 = gf_IsNull(datas.loTranFareEx1) ? ""          : datas.loTranFareEx1;
	var loTranFare2 = gf_IsNull(datas.loTranFare2) ? ""          : datas.loTranFare2;
	var loTranFareEx2 = gf_IsNull(datas.loTranFareEx2) ? ""          : datas.loTranFareEx2;
	var loTranFare3 = gf_IsNull(datas.loTranFare3) ? ""          : datas.loTranFare3;

	$("#loTranFare1").text(loTranFare1);
	$("#loTranFareEx1").text(loTranFareEx1);
	$("#loTranFare2").text(loTranFare2);
	$("#loTranFareEx2").text(loTranFareEx2);
	$("#loTranFare3 ").text(loTranFare3);

	var visaFeeFare1 = gf_IsNull(datas.visaFeeFare1) ? ""          : datas.visaFeeFare1;
	var visaFeeFareEx1 = gf_IsNull(datas.visaFeeFareEx1) ? ""          : datas.visaFeeFareEx1;
	var visaFeeFare2 = gf_IsNull(datas.visaFeeFare2) ? ""          : datas.visaFeeFare2;
	var visaFeeFareEx2 = gf_IsNull(datas.visaFeeFareEx2) ? ""          : datas.visaFeeFareEx2;
	var visaFeeFare3 = gf_IsNull(datas.visaFeeFare3) ? ""          : datas.visaFeeFare3;


	$("#visaFeeFare1").text(visaFeeFare1);
	$("#visaFeeFareEx1").text(visaFeeFareEx1);
	$("#visaFeeFare2").text(visaFeeFare2);
	$("#visaFeeFareEx2").text(visaFeeFareEx2);
	$("#visaFeeFare3").text(visaFeeFare3);

	var ovCharFare1 = gf_IsNull(datas.ovCharFare1) ? ""          : datas.ovCharFare1;
	var ovCharFareEx1 = gf_IsNull(datas.ovCharFareEx1) ? ""          : datas.ovCharFareEx1;
	var ovCharFare2 = gf_IsNull(datas.ovCharFare2) ? ""          : datas.ovCharFare2;
	var ovCharFareEx2 = gf_IsNull(datas.ovCharFareEx2) ? ""          : datas.ovCharFareEx2;
	var ovCharFare3 = gf_IsNull(datas.ovCharFare3) ? ""          : datas.ovCharFare3;

	$("#ovCharFare1").text(ovCharFare1);
	$("#ovCharFareEx1").text(ovCharFareEx1);
	$("#ovCharFare2").text(ovCharFare2);
	$("#ovCharFareEx2").text(ovCharFareEx2);
	$("#ovCharFare3").text(ovCharFare3);

	var vocLeeFare1 = gf_IsNull(datas.vocLeeFare1) ? ""          : datas.vocLeeFare1;
	var vocLeeFareEx1 = gf_IsNull(datas.vocLeeFareEx1) ? ""          : datas.vocLeeFareEx1;
	var vocLeeFare2 = gf_IsNull(datas.vocLeeFare2) ? ""          : datas.vocLeeFare2;
	var vocLeeFareEx2 = gf_IsNull(datas.vocLeeFareEx2) ? ""          : datas.vocLeeFareEx2;
	var vocLeeFare3 = gf_IsNull(datas.vocLeeFare3) ? ""          : datas.vocLeeFare3;

	$("#vocLeeFare1").text(vocLeeFare1);
	$("#vocLeeFareEx1").text(vocLeeFareEx1);
	$("#vocLeeFare2").text(vocLeeFare2);
	$("#vocLeeFareEx2").text(vocLeeFareEx2);
	$("#vocLeeFare3").text(vocLeeFare3);

	var hostFare1 = gf_IsNull(datas.hostFare1) ? ""          : datas.hostFare1;
	var hostFareEx1 = gf_IsNull(datas.hostFareEx1) ? ""          : datas.hostFareEx1;
	var hostFare2 = gf_IsNull(datas.hostFare2) ? ""          : datas.hostFare2;
	var hostFareEx2 = gf_IsNull(datas.hostFareEx2) ? ""          : datas.hostFareEx2;
	var hostFare3 = gf_IsNull(datas.hostFare3) ? ""          : datas.hostFare3;

	$("#hostFare1").text(hostFare1);
	$("#hostFareEx1").text(hostFareEx1);
	$("#hostFare2").text(hostFare2);
	$("#hostFareEx2").text(hostFareEx2);
	$("#hostFare3").text(hostFare3);

	var etcTotal1 = gf_IsNull(datas.etcTotal1) ? ""          : datas.etcTotal1;
	var etcTotalEx1 = gf_IsNull(datas.etcTotalEx1) ? ""          : datas.etcTotalEx1;
	var etcTotal2 = gf_IsNull(datas.etcTotal2) ? ""          : datas.etcTotal2;
	var etcTotalEx2 = gf_IsNull(datas.etcTotalEx2) ? ""          : datas.etcTotalEx2;
	var etcTotal3 = gf_IsNull(datas.etcTotal3) ? ""          : datas.etcTotal3;

	$("#etcTotal1").text(etcTotal1);
	$("#etcTotalEx1").text(etcTotalEx1);
	$("#etcTotal2").text(etcTotal2);
	$("#etcTotalEx2").text(etcTotalEx2);
	$("#etcTotal3").text(etcTotal3);




	var execDtl = gf_IsNull(datas.execDtl) ? ""          : datas.execDtl;
	for(var i = 0; i < execDtl.length; i++){
		execDtl = execDtl.replace("\n", "<br>");
	}

	var rem = gf_IsNull(datas.rem) ? ""          : datas.rem;
	for(var i = 0; i < rem.length; i++){
		rem = rem.replace("\n", "<br>");
	}


	$("#execDtl").html(execDtl);
	$("#rem").html(rem);




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