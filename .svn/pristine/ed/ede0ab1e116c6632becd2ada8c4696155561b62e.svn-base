/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/

var v_tripUserDetailTr = "<tr><td class=\"inpt\" style=\"width:40%; text-align:center\"><input type=\"text\" name=\"tUserEnm\" style=\"ime-mode:disabled;text-transform:uppercase\"></td>"
 + "<td class=\"inpt\" style=\"width:30%; text-align:center\"><select name=\"tGrade\"><option value=\"F\">First</option><option value=\"B\">Business</option><option value=\"E\" selected=\"selected\">Economy</option></select></td>"
 + "<td class=\"inpt\" style=\"width:30%; text-align:center\"><input type=\"text\" name=\"tAirAmount\" value=\"0\" style=\"text-align:right\">원</td></tr>";

var v_visaInfoDetailTr = "<tr><td class=\"inpt\" style=\"width:20%; text-align:center\"><input type=\"text\" name=\"vNat\"></td><td class=\"inpt\" style=\"width:20%; text-align:center\"><select name=\"vVisaYn\" id=\"vVisaYn\"><option value=\"Y\">소지</option><option value=\"N\">미소지</option><option value=\"V\">비자면제국가방문</option><option value=\"T\">현지공항비자발급</option></select></td>"
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
var ds_RejectDoc = new DataSet();


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

var ds_Cmas004 = new DataSet();		//'특수'협의결재선정보 (CMAS004,006)
var ds_Cmas006 = new DataSet();		//'특수'협의결재선정보 (CMAS004,006)

var v_CallbackFunc;

var v_SapResult;

var v_dSignType;   // 타 집행팀 여부
var v_dSignUserCd; // 타 집행팀장 ORG
var v_dSignUserId; // 타 집행티장 ID
var v_dSignUserNm;
var v_dSignOrgNm;  // resultData.ds_coUser[0].userId;
var v_dSignRpwrkNm;
var v_dSignRpswrkCd;
var v_dSignPositCd;
var v_dSignPositNm;

//타집행팀이 현장일 경우 현장관리책임자 협의가 추가된다.
var v_dHSignUserCd = "";
var v_dHSignUserId = "";
var v_dHSignUserNm = "";
var v_dHSignOrgNm = "";
var v_dHSignRpwrkNm = "";
var v_dHSignRpswrkCd = "";
var v_dHSignPositCd = "";
var v_dHSignPositNm = "";

//현장 소속일 경우 소장 저장용
var v_hSignType = "N";
var v_hSignUserCd = "";
var v_hSignUserId = "";
var v_hSignUserNm = "";
var v_hSignUserPositCd = "";
var v_hSignUserRpswrkCd = "";

var v_tSignUserCd = ""; // 최종결재 ORG
var v_tSignUserId;      // 최종결재 ID

var v_FileAtchId;

var v_isSaveEnable = "N"; // 상신중 여부

var hostCheck = 0;

var v_bossSignYn = "N";

var orgCls = "";
var v_secYn = "N";
var v_ghrYn = "N";

var v_TotalPer = 0;

var v_CmasList;

var v_RejectRes = "";

var fromReject = "";

var v_ErrMsg = "";
var v_PrgrMsg = "";

var v_ExStYmd = "";


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

	v_CmasList = gf_IsNull(datas.cmasList) ? "" : datas.cmasList;

	if(v_CmasList.length == 0){
		alert("출장 검토 담당자 정보를 가져올 수 없습니다.");
		self.close();
	}

	v_CallbackFunc = gf_IsNull(datas.callbackFunc) ? ""          : datas.callbackFunc;


	// 반려로부터 온 문서
	fromReject = gf_IsNull(datas.fromReject) ? "" : datas.fromReject;



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
	$("#fPassp").mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
	$("#tPassp").mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });

	$("#fPassp").attr("readonly", "true");
	$("#tPassp").attr("readonly", "true");

	//증빙일자
	$("#oDate").mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
	$("#oDate").attr("readonly", "true");

	$("#etcPayDate").mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });


	gf_SetUserComponent($("#tripUser"), function(data){
		// 출장자 정보
		v_TripUserId = data.userId;
		v_TripUserKnm = data.userKnm;
		v_TripUserEnm = data.userEnm;
		v_TripUserOrgCd = data.orgCd;
		v_TripUserOrgNm = data.orgNm;
		v_TripUserPositCd = data.userPositCd;
		v_TripUserRpswrkCd = data.userRpswrkCd;

		var tripUserDtl = v_TripUserOrgNm + "(" + v_TripUserOrgCd + ")" + " " + v_TripUserPositCd + " " + v_TripUserId + " " + v_TripUserKnm;
		$("#tripUserDtl").text(tripUserDtl);

		$("#tripUserEnm").val(data.userEnm.toUpperCase());

		v_TripUserEnm = $("#tripUserEnm").val();

		cf_retrieveSignLine();
		cf_getCouserInfo();
	});

	//Attachment 컴포넌트 생성
	gf_InitFileUploadComponent();

	// upload 모드로 컴포넌트의 mode  설정
	if(v_DocSts == "D50"){
		gf_setMode("download");
	}else{
		gf_setMode("upload");
	}

    // 숫자만 입력
    f_numberOnly("tripUserAirAmount");

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
	$("#outerTripSign").click(function(){

		var datas = {
			signln : ds_Signln.getAll()
		};
		//결재선 지정팝업
		gf_PostOpen("/common/jsp/sign/signUserSelect.jsp", null, "toolbar=no,scrollbars=no,width=1020,height=590", datas, true, true);
	});


	$("#tripUserAddBtn").click(function(){

		cf_AppendTripUser();

		//2015-08-31 동행인이 있을 경우 체재비는 동행인 수만큼 배수
		var pTU = $("#tripUserDetail tr").size();

		v_cTotalAmt = v_TotalPer*pTU;

		$("#cTotalAmt").text(gf_AmtFormat(v_cTotalAmt + totalEtcAmt()));

		// 개인지급 합계 (체재비 + 항공료)
		v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;
		$("#totalAmount").text(gf_AmtFormat(v_TotalAmount));

	});

	$("#tripUserDelBtn").click(function(){
		if($("#tripUserDetail tr").size() < 2){
			gf_AlertMsg("더이상 삭제할 수 없습니다");
		}else{
			$("#tripUserDetail tr:last").remove();
			$("#totalUser").text($("#tripUserDetail tr").size()-1);
		}

		//2015-08-31 동행인이 있을 경우 체제비는 동행인 수만큼 배수
		var pTU = $("#tripUserDetail tr").size();

		v_cTotalAmt = v_TotalPer*pTU;

		$("#cTotalAmt").text(gf_AmtFormat(v_cTotalAmt + totalEtcAmt()));


		// 개인지급 합계 (체재비 + 항공료)
		v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;
		$("#totalAmount").text(gf_AmtFormat(v_TotalAmount));
	});

	$("#visaAddBtn").click(function(){

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
			ds_VisaInfoList.remove(ds_VisaInfoList.size()-1);
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
			ds_CityList.remove(ds_CityList.size()-1);
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
				true, true, "null");
	});

	$("#inputAmt").click(function(){
//		$("#cityDetail").append(v_cityDetailTr);

		var param = {
				ds_NatList : ds_NatList.getAllData(),
				ds_ExpnList : ds_ExpnList.getAllData(),
				ds_RefList : ds_RefList.getAllData(),
				v_TripUserPositCd : v_TripUserPositCd,
				v_TripUserId : v_TripUserId
		};

		gf_PostOpen("/trip/outerTrip/inputAmt.jsp", null,
				"toolbar=no,scrollbars=yes,width=650,height=560", param,
				true, true, "f_callBackFuncInputAmt");

	});

	$("#bdgtBtn").click(function(){

		var bdgtParams = {
				bdgtType : v_BdgtType,
				pType : "OT"
		};

		gf_PostOpen("/common/jsp/comp/budget/bdgtSelect.jsp", null,
				"toolbar=no,scrollbars=no,width=665,height=600", bdgtParams,
				true, true, "f_callBackFuncBdgtSelect");
		//예산 선택 후 callback에 주목
	});

	$("#tripUserAirAmount").bind('keyup', function(e) {
		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}

		e.currentTarget.value = gf_AmtFormat(targetVal);

		if(parseInt(targetVal) > 100000000){
			e.currentTarget.value = 0;
			alert("항공료를 100,000,000원 이상 입력할 수 없습니다.");
		}

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

		if(tUserVal == "") tUserVal = 0;

		for(var j = 0; j < tUserVal.length; j++){
			tUserVal = tUserVal.replace(",", "");
		}

		total = total + parseInt(tUserVal);

		v_AirTotalAmt = total;
		$("#airTotalAmount").text(gf_AmtFormat(total));

		//총계 계산 포함

		// 개인지급 합계 (체재비 + 항공료)
		v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;
		$("#totalAmount").text(gf_AmtFormat(v_TotalAmount));

		$("#cTotalAmt").text(gf_AmtFormat(v_cTotalAmt + totalEtcAmt()));

	});

	$("#loAirFare").bind('keyup', function(e) {
		var targetVal = e.currentTarget.value;

		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}
//		e.currentTarget.value = gf_AmtFormat(targetVal);

		if(targetVal == "") targetVal = 0;

		var exVal = Math.round(parseFloat(targetVal) * parseFloat(v_USD));

		$("#loAirFareEx").text(gf_AmtFormat(exVal));

		v_TAirAmt = exVal;

		// 개인지급 합계 (체재비 + 항공료)
		v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;
		$("#totalAmount").text(gf_AmtFormat(v_TotalAmount));

		$("#cTotalAmt").text(gf_AmtFormat(v_cTotalAmt + totalEtcAmt()));

	});

	$("#loTranFare").bind('keyup', function(e) {

		var keyID = (e.which) ? e.which : e.keyCode;
		//0~9 || 0~9(keypad) || backspace || delete || arrow left || arrow right
		if ((keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ){

			var targetVal = e.currentTarget.value;
			for(var k = 0; k < targetVal.length; k++){
				targetVal = targetVal.replace(",", "");
			}
//			e.currentTarget.value = gf_AmtFormat(targetVal);

			if(targetVal == "") targetVal = 0;

			var exVal = Math.round(parseFloat(targetVal) * parseFloat(v_USD));

			$("#loTranFareEx").text(gf_AmtFormat(exVal));

			v_TTranspAmt = exVal;

			// 개인지급 합계 (체재비 + 항공료)
			v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;
			$("#totalAmount").text(gf_AmtFormat(v_TotalAmount));

			$("#cTotalAmt").text(gf_AmtFormat(v_cTotalAmt + totalEtcAmt()));

		}

	});

	$("#visaFeeFare").bind('keyup', function(e) {

		var keyID = (e.which) ? e.which : e.keyCode;
		//0~9 || 0~9(keypad) || backspace || delete || arrow left || arrow right
		if ((keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ){

			var targetVal = e.currentTarget.value;
			for(var k = 0; k < targetVal.length; k++){
				targetVal = targetVal.replace(",", "");
			}
//			e.currentTarget.value = gf_AmtFormat(targetVal);

			if(targetVal == "") targetVal = 0;

			var exVal = Math.round(parseFloat(targetVal) * parseFloat(v_USD));

			$("#visaFeeFareEx").text(gf_AmtFormat(exVal));

			v_VisaFee = exVal;

			// 개인지급 합계 (체재비 + 항공료)
			v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;
			$("#totalAmount").text(gf_AmtFormat(v_TotalAmount));

			$("#cTotalAmt").text(gf_AmtFormat(v_cTotalAmt + totalEtcAmt()));
		}

	});

	$("#ovCharFare").bind('keyup', function(e) {

		var keyID = (e.which) ? e.which : e.keyCode;
		//0~9 || 0~9(keypad) || backspace || delete || arrow left || arrow right
		if ((keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ){

			var targetVal = e.currentTarget.value;
			for(var k = 0; k < targetVal.length; k++){
				targetVal = targetVal.replace(",", "");
			}
//			e.currentTarget.value = gf_AmtFormat(targetVal);

			if(targetVal == "") targetVal = 0;

			var exVal = Math.round(parseFloat(targetVal) * parseFloat(v_USD));

			v_OverCharge = exVal;

			$("#ovCharFareEx").text(gf_AmtFormat(exVal));

			// 개인지급 합계 (체재비 + 항공료)
			v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;
			$("#totalAmount").text(gf_AmtFormat(v_TotalAmount));

			$("#cTotalAmt").text(gf_AmtFormat(v_cTotalAmt + totalEtcAmt()));

		}


	});

	$("#vocLeeFare").focusout(function() {
		//복리후생비+접대비
		f_bossSignProcess();
	});



	$("#vocLeeFare").bind('keyup', function(e) {

		var keyID = (e.which) ? e.which : e.keyCode;
		//0~9 || 0~9(keypad) || backspace || delete || arrow left || arrow right
		if ((keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ){


			var targetVal = e.currentTarget.value;
			for(var k = 0; k < targetVal.length; k++){
				targetVal = targetVal.replace(",", "");
			}
//			e.currentTarget.value = gf_AmtFormat(targetVal);

			if(targetVal == "") targetVal = 0;

			var exVal = Math.round(parseFloat(targetVal) * parseFloat(v_USD));

			v_Benefits = exVal;

			$("#vocLeeFareEx").text(gf_AmtFormat(exVal));

			// 개인지급 합계 (체재비 + 항공료)
			v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;
			$("#totalAmount").text(gf_AmtFormat(v_TotalAmount));

			$("#cTotalAmt").text(gf_AmtFormat(v_cTotalAmt + totalEtcAmt()));

		}

	});

	$("#hostFare").focusout(function() {
		//복리후생비+접대비
		f_bossSignProcess();
	});

	$("#hostFare").bind('keyup', function(e) {

		var keyID = (e.which) ? e.which : e.keyCode;
		//0~9 || 0~9(keypad) || backspace || delete || arrow left || arrow right
		if ((keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ){

			var targetVal = e.currentTarget.value;
			for(var k = 0; k < targetVal.length; k++){
				targetVal = targetVal.replace(",", "");
			}
//			e.currentTarget.value = gf_AmtFormat(targetVal);

			if(targetVal == "") targetVal = 0;

			var exVal = Math.round(parseFloat(targetVal) * parseFloat(v_USD));

			$("#hostFareEx").text(gf_AmtFormat(exVal));

			v_HostAmt = exVal;

			// 개인지급 합계 (체재비 + 항공료)
			v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;
			$("#totalAmount").text(gf_AmtFormat(v_TotalAmount));

			$("#cTotalAmt").text(gf_AmtFormat(v_cTotalAmt + totalEtcAmt()));

		}

	});

	// 문서 협조의뢰 한다.
	// 임시저장과 동일한 로직을 탄다.
	// 검토가 완료되고 통합결재 상신시에 데이터가 올바르게 저장된다.
	$("#outerTripDraft").click(function(){

		if(ds_Signln.size() < 2){
			gf_AlertMsg("결재선을 지정하여 주세요.");
			return;
		}

		//기타경비가 0이 아닐 경우 집행세부내역을 작성해야만 협조의뢰 가능함
		if($("#loAirFare").val()!="" && $("#loAirFare").val()!="0" && $("#loAirFareDtl").val()==""){
			alert("기타경비 현지항공료 집행세부내역이 입력되지 않았습니다.")
			return;
		}

		if($("#loTranFare").val()!="" && $("#loTranFare").val()!="0" && $("#loTranFareDtl").val()==""){
			alert("기타경비 현지교통비 집행세부내역이 입력되지 않았습니다.")
			return;
		}

		if($("#visaFeeFare").val()!="" && $("#visaFeeFare").val()!="0" && $("#visaFeeFareDtl").val()==""){
			alert("기타경비 VISA FEE 집행세부내역이 입력되지 않았습니다.")
			return;
		}

		if($("#ovCharFare").val()!="" && $("#ovCharFare").val()!="0" && $("#ovCharFareDtl").val()==""){
			alert("기타경비 Over Charge 집행세부내역이 입력되지 않았습니다.")
			return;
		}

		if($("#vocLeeFare").val()!="" && $("#vocLeeFare").val()!="0" && $("#vocLeeFareDtl").val()==""){
			alert("기타경비 복리후생 집행세부내역이 입력되지 않았습니다.")
			return;
		}

		if($("#hostFare").val()!="" && $("#hostFare").val()!="0" && $("#hostFareDtl").val()==""){
			alert("기타경비 접대비 집행세부내역이 입력되지 않았습니다.")
			return;
		}

		// 특수문자 체크
		if(f_docPatterChk() == false) return;

		if($("input[name='innType']:checked").val() == undefined){
			gf_AlertMsg("숙소 구분을 선택하세요.");
			return;
		}

		//비자정보 입력 validation 추가 필요
		// 1. 출장기간과 비자 발급/만료일 검증 루틴 추가 필요
		// 2. 출장 방문지 정보 + 비자 국가 정보가 일치해야 함 (미 입력시 협의단계로 안넘어감 - 아랫부분에 구현해 놓았음)
		// 3. 기타 다른 이슈 있을 수도 있는데..

		for(var i = 0; i < $("#visaInfoDetail tr td select[name='vVisaYn']").size(); i++){
			if($("#visaInfoDetail tr td select[name='vVisaYn']")[i].value == "Y"){
				if($("#visaInfoDetail tr td input[name='vStartDate']")[i].value == ""){
					gf_AlertMsg("소지 비자의 발급일을 입력하세요.");
					return;
				}else if($("#visaInfoDetail tr td input[name='vEndDate']")[i].value == ""){
					gf_AlertMsg("소지 비자의 만료일을 입력하세요.");
					return;
				}
			}
		}


		if($("#tPurp").val() == ""){
			gf_AlertMsg("출장목적이 입력되지 않았습니다.");
			return;
		}


		//alert($("#tPurp").val().length);

		if($("#tPurp").val().length > 38){
			gf_AlertMsg("출장목적은 38자 이내로 작성해 주세요.");
			return;
		}

		if($("#fPassp").val() == ""){
			gf_AlertMsg("여권 발급일이 입력되지 않았습니다.");
			return;
		}

		if($("#tPassp").val() == ""){
			gf_AlertMsg("여권 만료일이 입력되지 않았습니다.");
			return;
		}

		// 경비구분 확인
		if(v_BdgtType == ""){
			gf_AlertMsg("경비 구분이 선택되지 않았습니다.");
			return;
		}

		// 경비구분 확인
		if(ds_CityList.size() < 1){
			gf_AlertMsg("방문지를 입력해 주세요.");
			return;
		}else{
			if(ds_CityList.get(ds_CityList.size()-1, "natCd") != "KR"){
				gf_AlertMsg("최종 방문지는 한국을 지정하셔야 합니다.");
				return;
			}
		}

		// 경비구분 확인
		if(ds_CityList.size() < 2){
			gf_AlertMsg("방문지는 최소 2건 이상이어야 합니다.");
			return;
		}

		// 경비구분 확인
		if(ds_VisaInfoList.size() < 1){
			gf_AlertMsg("비자 정보를 입력해 주세요.");
			return;
		}

		if($("#tripUserEnm").val() == ""){
			gf_AlertMsg("영문 이름을 입력해 주세요.");
			return;
		}

		if($("#tripUserAirAmount").val() == ""){
			gf_AlertMsg("항공료를 입력해 주세요.");
			return;
		}

		// 동행인 입력여부 체크
		for(var i = 0; i < $("#tripUserDetail input[name=tUserEnm]").size(); i++){
			if($("#tripUserDetail input[name=tUserEnm]")[i].value == ""){
				alert("동행인 영문이름을 입력해주세요.");
				return;
			}
		}

		var natCds = new Array();

		// 비자필요국가가 방문지에 등록되어 있을시 비자정보 입력했는지를 검사
		for(var p = 0; p < ds_CityList.size(); p++){

			// 방문지의 국가가 비자 필요국가 인지를 검사
			if(f_visaYn(ds_CityList.get(p, "natCd")) == "Y"){
				natCds.push(ds_CityList.get(p, "natCd"));
			}
		}

		// 비자필요 국가가 존재하다면 비자정보를 입력했는지 검사한다.
		if(natCds.length > 0){
			for(var l = 0; l < natCds.length; l++){
				var natDtlFlg = false;
				for(var o = 0; o < ds_VisaInfoList.size(); o++){
					if(ds_VisaInfoList.get(o, "natCd") == natCds[l]) natDtlFlg = true;
				}
				if(natDtlFlg == false){
					alert(ds_NatList.get(ds_NatList.find("natCd", natCds[l]), "natNm") + "의 비자정보가 입력되지 않았습니다.");
					return;
				}
			}
		}

		//삭제금지_20200709_나중에 테스트후 적용필요(출장 중복신청 방지기능)
		//신청중복체크
		//f_CheckDraftDuplication();
		//return;

		// Validation Check 완료
		if(v_isSaveEnable == "N"){
			v_isSaveEnable = "Y";

			// 글로벌HR팀 협조
			v_DraftSts = "D60";

			// SECURITY팀 협조
			// 방문지 중에 위험국가가 있을시 SECURITY팀의 검토를 받는다.
			for(var i = 0; i < ds_CityList.size(); i++){
				if(cf_getRiskYnByNatCd(ds_CityList.get(i, "natCd")) == "Y") v_DraftSts = "D50";
			}

			gf_onFileUpload();

		}else{
			gf_AlertMsg("문서 상신중입니다. 잠시만 기다려주세요.");
			return;
		}

//		cf_saveOuterDraftDoc(docSts);

		// 예산 체크
//		cf_CheckOsBizTrip();


	});


	// 문서를 임시저장 처리 한다.
	// 이미 저장되어진 값을 JSON Object 형식으로 IF_PARAM 컬럼에 저장한다.
	$('#outerTripSave').click(function(){

		v_DraftSts = "D16";

		gf_onFileUpload();

		//cf_saveOuterDocSave(docSts);	// file upload의 callback 에서 호출함. 실제 저장은 DocSave에서 만들어짐.

	});

	// 글로벌 HR팀 검토승인
	// SGNS DRAFT 시작
	$('#outerTripHRConfirm').click(function(){

		// 특수문자 체크
		if(f_docPatterChk() == false) return;

		// 경비구분 확인
		if(v_BdgtType == ""){
			gf_AlertMsg("경비 구분이 선택되지 않았습니다.");
			return;
		}

		// 경비구분 확인
		if(ds_CityList.size() < 1){
			gf_AlertMsg("방문지를 입력해 주세요.");
			return;
		}

		// Validation Check 완료
		if(v_isSaveEnable == "N"){
			v_isSaveEnable = "Y";


			hostCheck = parseInt(f_notSpace(removeComma($("#hostFare").val()))) + parseInt(f_notSpace(removeComma($("#vocLeeFare").val())));

			//기타 경비 중 복리후생 + 접대비 0 원 이상이면 경영관리팀 검토
			if(hostCheck > 0){

				//입찰경비 O / 사업경비 P 인 경우 경영관리팀 검토 없음
				if(v_BdgtType == "O"){
					cf_submitOsBizTrip();
				}else if(v_BdgtType == "P"){
					cf_submitOsBizTrip();
				}else{
					// 경영관리팀 협조
					// 2015-09-15
					// 경영관리팀은 결재선 협조로 빠짐
					// GHR 에서 SGNS 로 문서를 상신한다.

					v_DraftSts = "D70";
					cf_submitOsBizTrip();
				}

			}else{

				cf_submitOsBizTrip();

			}

		}else{
			gf_AlertMsg("문서 상신중입니다. 잠시만 기다려주세요.");
			return;
		}

	});

	// 글로벌 HR팀 검토반려
	// 문서 반려 처리
	$('#outerTripHRNotConfirm').click(function(){

		gf_PostOpen("/trip/outerTrip/rejectPop.jsp", null,
				"toolbar=no,scrollbars=yes,width=1000,height=150", {},
				true, true, "f_callBackFuncRejectPop");


//		if(confirm("해당 문서를 반려하시겠습니까?")){
//			f_rejectDoc();
//		}


	});

	// 해외SEC팀 검토승인
	$('#outerTripSECConfirm').click(function(){

		// 경비구분 확인
		if(v_BdgtType == ""){
			gf_AlertMsg("경비 구분이 선택되지 않았습니다.");
			return;
		}

		// 경비구분 확인
		if(ds_CityList.size() < 1){
			gf_AlertMsg("방문지를 입력해 주세요.");
			return;
		}

		// Validation Check 완료
		if(v_isSaveEnable == "N"){
			v_isSaveEnable = "Y";

		// 글로벌HR팀 협조
			v_DraftSts = "D60";
			gf_onFileUpload();


		// SECURITY팀 협조시 출장자에게 위험국가 관련 메일 발송 2016.07.26 이은숙
			var tmpnatCd = ""
			var natCd = ""

			for(var i = 0; i < ds_CityList.size(); i++){
				if(cf_getRiskYnByNatCd(ds_CityList.get(i, "natCd")) == "Y"){
					natCd = ds_CityList.get(i, "natCd");
					tmpnatCd = gf_IsNull(tmpnatCd)? natCd : tmpnatCd +"||"+ natCd;
				}
			}

			var param = {
				natCd : tmpnatCd,
				tripUserId : v_TripUserId
			};

			gf_Transaction("SELECT_SPG_EMAIL_MGM", "/trip/outerTrip/retrieveSpgEmailMgm.xpl", param, {}, "f_Callback", true);

		}else{
			gf_AlertMsg("문서 상신중입니다. 잠시만 기다려주세요.");
			return;
		}

	});



	// 해외SEC팀 검토승인시 메일발송 모듈 별도
	$('#outerTripNatNm').click(function(){

		//alert('출장국가 구하기');

		//SECURITY팀 협조시 출장자에게 위험국가 관련 메일 발송 2016.07.26 이은숙

			var tmpnatCd = ""
			var natCd = ""

			for(var i = 0; i < ds_CityList.size(); i++){
				if(cf_getRiskYnByNatCd(ds_CityList.get(i, "natCd")) == "Y"){
					natCd = ds_CityList.get(i, "natCd");
					tmpnatCd = gf_IsNull(tmpnatCd)? natCd : tmpnatCd +"||"+ natCd;
					//alert(tmpnatCd);
				}
			}

			var param = {
				natCd : tmpnatCd,
				tripUserId : v_TripUserId
			};

			gf_Transaction("SELECT_SPG_EMAIL_MGM", "/trip/outerTrip/retrieveSpgEmailMgm.xpl", param, {}, "f_Callback", true);

	});



	// 해외SEC팀 검토반려
	// 문서 반려 처리
	$('#outerTripSECNotConfirm').click(function(){

		if(confirm("해당 문서를 반려하시겠습니까?")){
			f_rejectDoc();
		}


	});

	// 경영관리팀 검토승인 (원래 button 형태였으나 사라짐)
	// SGNS DRAFT 시작
	$('#outerTripMNGConfirm').click(function(){

		// 경비구분 확인
		if(v_BdgtType == ""){
			gf_AlertMsg("경비 구분이 선택되지 않았습니다.");
			return;
		}

		// 경비구분 확인
		if(ds_CityList.size() < 1){
			gf_AlertMsg("방문지를 입력해 주세요.");
			return;
		}

		// Validation Check 완료
		if(v_isSaveEnable == "N"){
			v_isSaveEnable = "Y";

			cf_submitOsBizTrip();

		}else{
			gf_AlertMsg("문서 상신중입니다. 잠시만 기다려주세요.");
			return;
		}

	});

	// 해외SEC팀 검토반려
	// 문서 반려 처리
	$('#outerTripMNGNotConfirm').click(function(){

		if(confirm("해당 문서를 반려하시겠습니까?")){
			f_rejectDoc();
		}

	});

	$('#outerTripDelete').click(function(){

		if(confirm("해당 문서를 삭제하시겠습니까?")){
			f_deleteDoc();
		}

	});


	// 여권발급일 From To Validation
	$("#fPassp").bind('change', function() {

//		if($("#tPassp").val() == ""){
//			$("#tPassp").datepicker("setDate", $("#fPassp").val());
//		}

		if($("#fPassp").val() != "" && $("#tPassp").val() != ""){
			var sDate = $("#fPassp").val().split("-");
			var eDate = $("#tPassp").val().split("-");

			var sDateTemp = sDate[0] + sDate[1] + sDate[2];
			var eDateTemp = eDate[0] + eDate[1] + eDate[2];

			var startDate = $.datepicker.parseDate("yymmdd" , sDateTemp);
			var endDate = $.datepicker.parseDate("yymmdd" , eDateTemp);

			var betDay = (endDate - startDate)/1000/60/60/24;

			if(betDay < 0){
				$("#tPassp").val("");
				return;
			}

		}

	});

	$("#tPassp").bind('change', function() {

//		if($("#fPassp").val() == ""){
//			$("#fPassp").datepicker("setDate", $("#tPassp").val());
//		}

		if($("#fPassp").val() != "" && $("#tPassp").val() != ""){
			var sDate = $("#fPassp").val().split("-");
			var eDate = $("#tPassp").val().split("-");

			var sDateTemp = sDate[0] + sDate[1] + sDate[2];
			var eDateTemp = eDate[0] + eDate[1] + eDate[2];

			var startDate = $.datepicker.parseDate("yymmdd" , sDateTemp);
			var endDate = $.datepicker.parseDate("yymmdd" , eDateTemp);

			var betDay = (endDate - startDate)/1000/60/60/24;

			if(betDay < 0){
				$("#fPassp").val("");
				return;
			}

		}

	});

	$('#outerTripCheck').click(function(){

		cf_checkOsBizTripTest();

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

	if(gv_AuthList.auth[0].code.match("RO_CMAS_CO_TRIP_ADM") != null){
		$("#outerTripCheck").show();
	}

	// 본부장 이상 직책 삽입
	ds_HqRpswrkCd.setAllData(gv_hqRpswrkCd);

	if(v_DocSts == "D54" || v_DocSts == "D64"){		//SEC반려, GHR반려이면 임시저장STATUS로 변경(신청서 작성자 입장에서)
		v_DocSts = "D16";
	}

	if(v_DocSts == "D16"){

		$("#outerTripDelete").show();

		v_DocNo = v_CmasId; // 이미 생성되어진 v_CmasId;
  		var docNoText = v_DocNo.split("-");
  		$("#docNo").text(docNoText[1] + "-" + docNoText[2]);

		gf_Transaction("SELECT_NAT_LIST_2", "/trip/outerTrip/retrieveNatList.xpl", {}, {}, "f_Callback", true);
		gf_Transaction("SELECT_REF_LIST", "/trip/outerTrip/retrieveTripRef.xpl", {}, {}, "f_Callback", true);

	}else if(v_DocSts == "D50"){

		//검토 버튼 활성화
		$("#outerTripSECConfirm").show();
		$("#outerTripSECNotConfirm").show();
		$("#outerTripSave").show();
		$("#secTr").show();

		//일반버튼 숨김
		$("#outerTripDraft").hide();
		$("#outerTripSign").hide();


		//협조내역 팝업
//		$("#assistComment").removeAttr("readonly");
//		$("#assistComment").attr("readonly", "true");

		// 글로벌 HR팀 사전검토
		v_DocNo = v_CmasId; // 이미 생성되어진 v_CmasId;
  		var docNoText = v_DocNo.split("-");
  		$("#docNo").text(docNoText[1] + "-" + docNoText[2]);

		gf_Transaction("SELECT_NAT_LIST_2", "/trip/outerTrip/retrieveNatList.xpl", {}, {}, "f_Callback", true);
		gf_Transaction("SELECT_REF_LIST", "/trip/outerTrip/retrieveTripRef.xpl", {}, {}, "f_Callback", true);


	}else if(v_DocSts == "D60"){

		//검토 버튼 활성화
		$("#outerTripHRConfirm").show();
		$("#outerTripHRNotConfirm").show();
		$("#outerTripSave").show();
		$("#ghrTr").show();

		//일반버튼 숨김
		$("#outerTripDraft").hide();
		$("#outerTripSign").hide();

		//협조내역 팝업
//		$("#assistComment").removeAttr("readonly");
//		$("#assistComment").attr("readonly", "true");

		// 글로벌 HR팀 사전검토
		v_DocNo = v_CmasId; // 이미 생성되어진 v_CmasId;
  		var docNoText = v_DocNo.split("-");
  		$("#docNo").text(docNoText[1] + "-" + docNoText[2]);

		gf_Transaction("SELECT_NAT_LIST_2", "/trip/outerTrip/retrieveNatList.xpl", {}, {}, "f_Callback", true);
		gf_Transaction("SELECT_REF_LIST", "/trip/outerTrip/retrieveTripRef.xpl", {}, {}, "f_Callback", true);

	}else if(v_DocSts == "D70"){		//

		//검토 버튼 활성화
		$("#outerTripMNGConfirm").show();
		$("#outerTripMNGNotConfirm").show();

		//일반버튼 숨김
		$("#outerTripSave").hide();
		$("#outerTripDraft").hide();

		//협조내역 팝업
//		$("#assistComment").removeAttr("readonly");
//		$("#assistComment").attr("readonly", "true");

		// 글로벌 HR팀 사전검토
		v_DocNo = v_CmasId; // 이미 생성되어진 v_CmasId;
  		var docNoText = v_DocNo.split("-");
  		$("#docNo").text(docNoText[1] + "-" + docNoText[2]);

		gf_Transaction("SELECT_NAT_LIST_2", "/trip/outerTrip/retrieveNatList.xpl", {}, {}, "f_Callback", true);
		gf_Transaction("SELECT_REF_LIST", "/trip/outerTrip/retrieveTripRef.xpl", {}, {}, "f_Callback", true);


	}else{
		if(fromReject == "Y"){

			// 결재선 생성
			ds_Signln.setAllData(datas.ds_Signln);

			//S04 결재완료, S05 반려
			for ( var i = 0; i < ds_Signln.size(); i++) {
  				ds_Signln.set(i, "signDt", "");
  				if(ds_Signln.get(i, "signStsCd") == "S05"){
  					ds_Signln.set(i, "signStsCd", "S04");
  				}
			}

			gf_AssembleSignln(ds_Signln);

			// 문서 셋팅
			ds_RejectDoc.setAllData(datas.docData);

			var rIfParam = JSON.parse(ds_RejectDoc.get(0).ifParam);

			//소속팀 셋팅
			var drafterOrgNm = rIfParam.drafterUserOrgNm + " (" + rIfParam.drafterUserOrgCd + ")";
			$("#drafterOrgNm").text(drafterOrgNm);

			var drafter =  rIfParam.drafterUserId + " " + rIfParam.drafterUserKnm;

			//소속팀 직급 이름 (사번)
			$("#drafter").text(drafter);

			// 작성자 셋팅
			v_DraftUserId = rIfParam.drafterUserId;
			v_DraftUserKnm = rIfParam.drafterUserKnm;
			v_DraftUserEnm = rIfParam.drafterUserEnm;
			v_DraftUserOrgCd = rIfParam.drafterUserOrgCd;
			v_DraftUserOrgNm = rIfParam.drafterUserOrgNm;
			v_DraftUserPositCd = rIfParam.drafterUserPositCd;
			v_DraftUserRpswrkCd = rIfParam.drafterUserRpswrkCd;


			v_TripUserId = rIfParam.tripUserId;
			v_TripUserKnm = rIfParam.tripUserKnm;
			v_TripUserEnm = rIfParam.tripUserEnm.toUpperCase();
			v_TripUserOrgCd = rIfParam.tripUserOrgCd;
			v_TripUserOrgNm = rIfParam.tripUserOrgNm;
			v_TripUserPositCd = rIfParam.tripUserPositCd;
			v_TripUserRpswrkCd = rIfParam.tripUserRpswrkCd;

			//출장자 셋팅
			$("#tripUser_id").val(v_TripUserId);
			$("#tripUser_name").val(v_TripUserKnm);
			$("#tripUserEnm").val(v_TripUserEnm);

			// 상신시 Modal처리
			gf_InitSpinner(top.$('body'));

			var params = {
				fstRegDt : ds_RejectDoc.get(0, "fstRegDt").substr(0, 10)
			};

			//DOC 번호 새로 따고
			gf_Transaction("SELECT_CMAS_ID", "/trip/outerTrip/getCmasId2.xpl", params, {}, "f_Callback", true);

			gf_Transaction("SELECT_NAT_LIST_3", "/trip/outerTrip/retrieveNatList.xpl", {}, {}, "f_Callback", true);
			gf_Transaction("SELECT_REF_LIST", "/trip/outerTrip/retrieveTripRef.xpl", {}, {}, "f_Callback", true);


			// 증빙일자 오늘 날짜로 셋팅
			$("#oDate").val($.datepicker.formatDate("yy-mm-dd", new Date()));

			// 문서 내용 셋팅

		}else{
			//소속팀 셋팅
			var drafterOrgNm = gv_orgNm + " (" + gv_orgCd + ")";
			$("#drafterOrgNm").text(drafterOrgNm);

//			var drafter = gv_orgNm + " (" + gv_orgCd + ") " + gv_userPositCd + " " + gv_userNm + " (" + gv_userId + ")";
	//
//			//소속팀 직급 이름 (사번)
//			$("#drafter").text(drafter);

			var drafter =  gv_userId + " " + gv_userNm;

			//소속팀 직급 이름 (사번)
			$("#drafter").text(drafter);

			// 작성자 셋팅
			v_DraftUserId = gv_userId;
			v_DraftUserKnm = gv_userNm;
			v_DraftUserEnm = gv_userEnm;
			v_DraftUserOrgCd = gv_orgCd;
			v_DraftUserOrgNm = gv_orgNm;
			v_DraftUserPositCd = gv_userPositCd;
			v_DraftUserRpswrkCd = gv_userRpswrkCd;


			v_TripUserId = gv_userId;
			v_TripUserKnm = gv_userNm;
			v_TripUserEnm = gv_userEnm.toUpperCase();
			v_TripUserOrgCd = gv_orgCd;
			v_TripUserOrgNm = gv_orgNm;
			v_TripUserPositCd = gv_userPositCd;
			v_TripUserRpswrkCd = gv_userRpswrkCd;

			//출장자 셋팅
			$("#tripUser_id").val(gv_userId);
			$("#tripUser_name").val(gv_userNm);
			$("#tripUserEnm").val(v_TripUserEnm);

			//출장자 상세
			// IT기획팀 (1DFIW) 과장 1202588 이은숙
			var tripUserDtl = v_TripUserOrgNm + "(" + v_TripUserOrgCd + ")" + " " + v_TripUserPositCd + " " + v_TripUserId + " " + v_TripUserKnm;
			$("#tripUserDtl").text(tripUserDtl);


//			alert("오늘 일자로 환율 조회");

			// 오늘 날짜 보냄 // 개발서버에 2015년 정보 없음 임시 하드코딩
	  		var ymd = $.datepicker.formatDate("yymmdd", new Date());

	  		// 환율 신청 일자 저장
	  		v_ExStYmd = ymd;

			var ymdParams = {
					Ymd : ymd
			};

			gf_Transaction("SELECT_CMAS_ID", "/trip/outerTrip/getCmasId.xpl", {}, {}, "f_Callback", true);

			gf_Transaction("SELECT_NAT_LIST", "/trip/outerTrip/retrieveNatList.xpl", {}, {}, "f_Callback", true);
			gf_Transaction("SELECT_REF_LIST", "/trip/outerTrip/retrieveTripRef.xpl", {}, {}, "f_Callback", true);
			gf_Transaction("SELECT_EXRATE", "/trip/eai/getSendExrate.xpl", ymdParams, {}, "f_Callback", true);

			// 출장자 수
			$("#totalUser").text($("#tripUserDetail tr").size()-1);

			// 증빙일자 오늘 날짜로 셋팅
			$("#oDate").val($.datepicker.formatDate("yy-mm-dd", new Date()));

			cf_retrieveSignLine();
			// 최초 1인 출장 동행인 추가
//			cf_AppendTripUser();
		}

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

	// 체크로직을 확인할 때에는 정상 메세지 나오지 않도록 한다
	if (strSvcId != null && strSvcId != "CHECK_DRAFT_DUPLICATION"){

	  // transaction의 정상 처리 여부를 판단한다.
	  if (!gf_ChkTransaction(strSvcId, resultData, true )) {
		  return;
	  }

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

//	  			var ymdParams = {
//			  		Ymd : $.datepicker.formatDate("yymmdd", new Date())
//			  	};
//
//	  			v_ExStYmd = $.datepicker.formatDate("yymmdd", new Date());

	  			var savedYmd = ds_SavedDoc.get(0, "fstRegDt").substr(0, 10);

		  		var ymdParams = {
		  			Ymd : $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", savedYmd))
		  		};

		  		v_ExStYmd = $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", savedYmd));

			  	gf_Transaction("SELECT_SAVED_EXRATE", "/trip/eai/getSendExrate.xpl", ymdParams, {}, "f_Callback", true);

	  		}else{
//	  			alert("저장된 일자로 환율 조회");
//	  			$("#refTr").show();
	  			$("#refDay").text(ds_SavedDoc.get(0, "fstRegDt").substr(0, 10));


	  			var savedYmd = ds_SavedDoc.get(0, "fstRegDt").substr(0, 10);

		  		var ymdParams = {
		  			Ymd : $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", savedYmd))
		  		};

		  		v_ExStYmd = $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", savedYmd));

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
	  	case "SELECT_NAT_LIST_3" :
	  		ds_NatList.setAllData(resultData.ds_NatList);

	  		var savedYmd = ds_RejectDoc.get(0, "fstRegDt").substr(0, 10);

			var ymdParams = {
					Ymd : $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", savedYmd))
			};

			v_ExStYmd = $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", savedYmd));

			gf_Transaction("SELECT_REJECTED_EXRATE", "/trip/eai/getSendExrate.xpl", ymdParams, {}, "f_Callback", true);

			break;

	  	case "SELECT_REF_LIST" :
	  		ds_RefList.setAllData(resultData.ds_RefList);
	  		break;
	  	case "SELECT_REF_LIST_REJ" :
	  		ds_RefList.setAllData(resultData.ds_RefList);
	  		break;
	  	case "SELECT_EXRATE" :
	  		ds_ExRList.setAllData(resultData.output1[0].SapItab);

	  		if(ds_ExRList.find("Currency", "EUR") == -1){
	  			alert("환율 정보를 가져올 수 없습니다.");
	  			self.close();
	  		}

	  		if(ds_ExRList.find("Currency", "GBP") == -1){
	  			alert("환율 정보를 가져올 수 없습니다.");
	  			self.close();
	  		}

	  		if(ds_ExRList.find("Currency", "USD") == -1){
	  			alert("환율 정보를 가져올 수 없습니다.");
	  			self.close();
	  		}

	  		if(ds_ExRList.find("Currency", "JPY") == -1){
	  			alert("환율 정보를 가져올 수 없습니다.");
	  			self.close();
	  		}

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

	  	case "SELECT_COSIGN_LINE" :
	  		//do nothing
	  		ds_Cmas004.clear();
	  		ds_Cmas006.clear();
	  		ds_Cmas004.setAllData(resultData.ds_Cmas004);
	  		ds_Cmas006.setAllData(resultData.ds_Cmas006);

	  		break;
	  	case "SAVE_OUTER_TRIP_DOC_SAVE" :

	  		// 문서가 성공적으로 처리되면 문서 리스트를 다시 조회하여 갱신하여 준다.
	  		if ( !gf_IsNull(v_CallbackFunc) ) {
	  			var openCallbackFunc = "opener."+v_CallbackFunc;
	  	    	eval(openCallbackFunc + "();");
	  	    }

	  		if(fromReject == "Y"){
	  			opener.cf_RetrieveOuterTripList();
	  		}

	  		self.close();
	  		break;

	  	case "SELECT_SEND_BIZ_TRIP" :	//협조완료 - SAP 상신 - 통합결재 넘어가는 과정

	  		var result = resultData.output1[0];

	  		if(resultData.output1[0].ErrMsg != null){
	  			// SAP 상신 실패시 Msg 출력
	  			gf_AlertMsg(result.ErrMsg);
	  			v_isSaveEnable = "N";

	  			if(v_DocSts == "D60"){
	  				$("#outerTripHRNotConfirm").show();
	  				$("#outerTripHRConfirm").show();
	  				$("#outerTripSave").show();

	  			}else if(v_DocSts == "D70"){
	  				$("#outerTripMNGConfirm").show();
	  				$("#outerTripMNGNotConfirm").show();
	  			}

	  			$(".ajax_overlay").remove();

	  			// 에러 메세지 기록
	  			var params = {
	  				docNo : v_DocNo,
	  				errMsg : result.ErrMsg
	  			};

	  			v_ErrMsg = result.ErrMsg;

	  			gf_Transaction("SELECT_ERR_MSG", "/trip/outerTrip/updateErrMsg.xpl", params, {}, "f_Callback", true);

	  			return;
	  		}else{

	  			gf_AlertMsg("정상 처리 되었습니다.");

	  			if ( !gf_IsNull(v_CallbackFunc) ) {
		  			var openCallbackFunc = "opener."+v_CallbackFunc;
		  	    	eval(openCallbackFunc + "();");
		  	    }

	  			self.close();

	  		}

	  		$(".ajax_overlay").remove();

	  		break;
	  	case "SELECT_CHECK_BIZ_TRIP" :

	  		var result = resultData.output1[0];

	  		// 에러메세지가 존재하는 경우 (예산 부족)
	  		if(resultData.output1[0].ErrMsg != null){

	  			// 임시저장할 문서의 경우, 예산이 부족해도 저장이 가능해야 한다.
	  			if(v_DraftSts == "D16"){
	  				cf_saveOuterDocSave(v_DraftSts);
	  			}else{
	  				// SAP 상신 실패시 Msg 출력
		  			gf_AlertMsg(result.ErrMsg);
		  			v_isSaveEnable = "N";

		  			if(v_DocSts == "D60"){
		  				$("#outerTripHRNotConfirm").show();
		  				$("#outerTripHRConfirm").show();
		  				$("#outerTripSave").show();

		  			}else if(v_DocSts == "D70"){
		  				$("#outerTripMNGConfirm").show();
		  				$("#outerTripMNGNotConfirm").show();
		  			}

		  			$(".ajax_overlay").remove();

		  			return;

	  			}

	  		}else{

//	  			gf_AlertMsg("사용 가능 합니다.");

	  			cf_saveOuterDocSave(v_DraftSts);

	  		}

	  		$(".ajax_overlay").remove();

	  		break;
	  	case "SELECT_CHECK_BIZ_TRIP_TEST" :

	  		var result = resultData.output1[0];

	  		if(resultData.output1[0].ErrMsg != null){
	  			// SAP 상신 실패시 Msg 출력
	  			gf_AlertMsg(result.ErrMsg);
	  			v_isSaveEnable = "N";

	  			if(v_DocSts == "D60"){
	  				$("#outerTripHRNotConfirm").show();
	  				$("#outerTripHRConfirm").show();
	  				$("#outerTripSave").show();

	  			}else if(v_DocSts == "D70"){
	  				$("#outerTripMNGConfirm").show();
	  				$("#outerTripMNGNotConfirm").show();
	  			}

	  			$(".ajax_overlay").remove();

	  			return;
	  		}else{

	  			gf_AlertMsg("사용 가능 합니다.");

//	  			cf_saveOuterDocSave(v_DraftSts);

	  		}

	  		$(".ajax_overlay").remove();

	  		break;
	  	case "SELECT_OUTER_TRIP_DRAFT" :

//	  		ORefkeyNo: ".", OPrctr: "3DFUR", OPrctrTxt: "IT운영팀", OAccountNo: "213211167098"
//	  		v_SapResult;

	  		var dutySysCd = "SGNS"; // DUTYSYSCD
	  		var programCode = "SGNS070003"; // 양식코드
	  		var signDocTitle = "해외출장신청서"; // 양식 제목

	  		var ordDate = $.datepicker.formatDate("yymmdd", new Date); // 기표일자
	  		var ordNo = ""; // 전표번호

	  		var docNo = v_DocNo; // CMAS 문서번호
	  		var drafterId = v_DraftUserId + " " + v_DraftUserKnm; // 작성자 ID
	  		var drafter = drafterId;
	  		var drafterOrg = v_DraftUserOrgCd + " " + v_DraftUserOrgNm; // 작성자 ORG
	  		var drafterOrgNm = drafterOrg;
	  		var tripUser = v_TripUserId + " " + v_TripUserKnm;
	  		var tripUserEnm = v_TripUserEnm;
	  		var tripUserGrade = $("select[name='tripUserGrade'] option:selected").text();
	  		var tripUserAirAmount = $("#tripUserAirAmount").val();
	  		var tripDate = $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", v_StartDate)) + " ~ " + $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", v_EndDate)) + " " + v_TripDate + "일";
	  		var tPurp = $("#tPurp").val();
	  		var tReq = $("#tReq").val();
	  		var fPassp = $("#fPassp").val();
	  		var tPassp = $("#tPassp").val();

	  		var bdgtType = $("#bdgtType").text();
	  		var aufnr = v_Aufnr + " / 내역 : " + v_KText;
	  		var cGubun = $("#cGubun option:selected").text();
	  		var bdgtTeam = v_Kostvnm + " " + v_Kostv;
	  		var airTotalAmount = $("#airTotalAmount").text();
	  		var pecul = $("#pecul").val();

	  		var loAirFare = "$ " + $("#loAirFare").val() + " / \ " + $("#loAirFareEx").text();
	  		var loTranFare = "$ " + $("#loTranFare").val() + " / \ " + $("#loTranFareEx").text();
	  		var visaFeeFare = "$ " + $("#visaFeeFare").val() + " / \ " + $("#visaFeeFareEx").text();
	  		var ovCharFare = "$ " + $("#ovCharFare").val() + " / \ " + $("#ovCharFareEx").text();
	  		var vocLeeFare = "$ " + $("#vocLeeFare").val() + " / \ " + $("#vocLeeFareEx").text();
	  		var hostFare = "$ " + $("#hostFare").val() + " / \ " + $("#hostFareEx").text();
	  		var etcTotal = "$ " + $("#etcTotal").val() + " / \ " + $("#etcTotalEx").text();

	  		var usNight = $("#usNight").text();
	  		var usDay = $("#usDay").text();
	  		var edu = $("#edu").text();
	  		var spot = $("#spot").text();
	  		var euNight = $("#euNight").text();
	  		var euDay = $("#euDay").text();
	  		var enNight = $("#enNight").text();
	  		var enDay = $("#enDay").text();
	  		var jaNight = $("#jaNight").text();
	  		var jaDay = $("#jaDay").text();

	  		var expnTotal = "USD " + $("#usdTotal").text() + "(" + $("#usdRef").text() + " / \ " + $("#usdTotalKr").text() + "), " +
	  		"EURO " + $("#euroTotal").text() + "(" + $("#euroRef").text() + " / \ " + $("#euroTotalKr").text() + "), " +
	  		"GBP " + $("#gbpTotal").text() + "(" + $("#gbpRef").text() + " / \ " + $("#gbpTotalKr").text() + "), " +
	  		"￥ " + $("#jaTotal").text() + "(" + $("#jaRef").text() +  " / \ " + $("#jaTotalKr").text() + ")";

	  		var cTotalAmt = $("#cTotalAmt").text() + "원 (체재비합계 X 인원 + 기타경비)";
	  		var totalAmount = $("#totalAmount").text() + "원 (개인지급 합계 + 항공료)";
	  		var rem = $("#rem").val();
	  		var assistComment = $("#assistComment").val();


	  		// SGNS REMOTE DRAFT
	  		var sgnsParams = {
	  				dutySysCd : dutySysCd,
	  				programCode : programCode,
	  				signDocTitle : signDocTitle,
	  				ordDate : ordDate,
	  				ordNo : ordNo,
	  				adjustYn : "N", // N 신청서 / Y 정산서
			  		docNo : docNo,
			  		drafterId : drafterId,
			  		drafter : drafter,
			  		drafterOrg : drafterOrg,
			  		drafterOrgNm : drafterOrgNm,
			  		tripUser : tripUser,
			  		tripUserEnm : tripUserEnm,
			  		tripUserGrade : tripUserGrade,
			  		tripUserAirAmount : tripUserAirAmount,
			  		tripDate : tripDate,
			  		tPurp : tPurp,
			  		tReq : tReq,
			  		fPassp : fPassp,
			  		tPassp : tPassp,
			  		bdgtType : bdgtType,
			  		aufnr : aufnr,
			  		cGubun : cGubun,
			  		bdgtTeam : bdgtTeam,
			  		airTotalAmount : airTotalAmount,
			  		pecul : pecul,
			  		loAirFare : loAirFare,
			  		loTranFare : loTranFare,
			  		visaFeeFare : visaFeeFare,
			  		ovCharFare : ovCharFare,
			  		vocLeeFare : vocLeeFare,
			  		hostFare : hostFare,
			  		etcTotal : etcTotal,
			  		usNight : usNight,
			  		usDay : usDay,
			  		edu : edu,
			  		spot : spot,
			  		euNight : euNight,
			  		euDay : euDay,
			  		enNight : enNight,
			  		enDay : enDay,
			  		jaNight : jaNight,
			  		jaDay : jaDay,
			  		expnTotal : expnTotal,
			  		cTotalAmt : cTotalAmt,
			  		totalAmount : totalAmount,
			  		rem : rem,
			  		assistComment : assistComment,
			  		refNo : v_SapResult.ORefkeyNo
	  		};

	  		var draftDataSet = {
	  				ds_Signln : ds_Signln.getAllData("U")
	  		};

	  		gf_Transaction("SELECT_SGNS_REMOTE_DRAFT", "/trip/outerTrip/saveSgnsRemoteDraft.xpl", sgnsParams, draftDataSet, "f_Callback", true);
	  		break;

	  	case "SELECT_CO_USER_INFO" :

	  		if(resultData.ds_coUser == null){

	  			//2015-06-09
//	  			gf_AlertMsg("최종 결재자가 지정되지 않았습니다. 결재자를 선택해주세요");
//				// 결재선을 지정할 수 있도록 버튼을 팝업한다.
//				$("#cityTranspSign").show();
				return;
	  		}

	  		// 출장자와 집행팀장의 CO 조직코드를 비교한다.
	  		if(v_TripUserOrgCd != resultData.ds_coUser[0].orgCd){

	  				gf_AlertMsg("타팀 예산 사용시 집행팀이 협의자로 지정됩니다.");

	  				//타팀 협의 일 경우 텍스트 팝업
	  				$("#dSignDiv").show();


		  			v_dSignType = "Y"; // 타 집행팀 여부

		  			v_dSignUserCd = resultData.ds_coUser[0].orgCd; // 타 집행팀장 ORG
		  			v_dSignUserId = resultData.ds_coUser[0].userId; // 타 집행티장 ID
		  			v_dSignUserNm = resultData.ds_coUser[0].userKnm;
//		  			v_dSignOrgNm = ''; // resultData.ds_coUser[0].userId;
		  			v_dSignOrgNm = resultData.ds_coUser[0].orgNm;
		  			v_dSignRpwrkNm = resultData.ds_coUser[0].userRpswrkCd;
		  			v_dSignRpswrkCd = resultData.ds_coUser[0].userRpswrkCd;
		  			v_dSignPositCd = resultData.ds_coUser[0].userPositCd;
		  			v_dSignPositNm = resultData.ds_coUser[0].userPositCd;

		  			// 해외출장도 Q. 현장경비 사용하도록 변경 2017.09.11
		  			// 타집행팀이면서 현장일 경우 현장관리 책임자도 협의 라인으로 추가한다.
		  			if(v_BdgtType == "Q"){

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

		  			if(v_dHSignUserId == "" && v_dSignUserId == "") v_dSignType = "N";
//		  			alert("타집행팀!");

	  		}else{
	  			v_dSignType = "N";
	  			//타팀 협의 일 경우 텍스트 팝업 숨기기
	  			$("#dSignDiv").hide();
	  		}

	  		//20150917 결재선 변경 고지
	  		alert('집행팀에 따라 결재선을 자동설정합니다. 결재선을 확인하세요.');

	  		cf_retrieveSignLine();

	  		gf_DisableCurrentOverlay();

	  		break;
	  	case "SAVE_REJECT_DOC" :

	  		// 문서가 성공적으로 처리되면 문서 리스트를 다시 조회하여 갱신하여 준다.
	  		if ( !gf_IsNull(v_CallbackFunc) ) {
	  			var openCallbackFunc = "opener."+v_CallbackFunc;
	  	    	eval(openCallbackFunc + "();");
	  	    }

	  		self.close();
	  		break;
	  	case "SAVE_DELETE_DOC" :
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
	  			v_FileAtchId = data.fileAtchId;
	  			// 첨부파일이 존재하는 경우
	  			gf_retrieveFileList(data.fileAtchId);

	  		}

	  		// SEC팀 여부
	  		if(!(data.secYn == "undefined" || data.secYn == undefined)){
	  			v_secYn = data.secYn;
		  		if(v_secYn == "Y"){
		  			$("#secTr").show();
		  			if(v_DocSts == "D50"){
		  				$("#secComment").removeAttr("readonly");
		  			}else if(v_DocSts == "D60"){
		  				$("#secComment").attr("readonly", "true");
		  			}
		  		}
	  		}

	  		// GHR팀 여부
	  		if(!(data.ghrYn == "undefined" || data.ghrYn == undefined)){
	  			v_ghrYn = data.ghrYn;
		  		if(v_ghrYn == "Y"){
//		  			$("#ghrTr").show();
		  			if(v_DocSts == "D60"){
		  				$("#ghrComment").removeAttr("readonly");
		  			}
		  		}
	  		}

	  		$("#secComment").val(ds_SavedDoc.get(0).perchrgRvwOpn1);
	  		$("#ghrComment").val(ds_SavedDoc.get(0).perchrgRvwOpn2);

	  		// 증빙일자
	  		$("#oDate").val(data.oDate);

	  		// 숙소구분
	  		if(data.innType == "H"){
//	  			alert("현장숙소");
	  			$("input[name='innType'][value='H']").attr("checked", "true");
	  		}else if(data.innType == "G"){
//	  			alert("외부숙소");
	  			$("input[name='innType'][value='G']").attr("checked", "true");
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
			  		case "Q1" :
			  			$("#bdgtType").text("Q1. PJ-현장경비");
			  			break;
			  		case "Q2" :
			  			$("#bdgtType").text("Q2. PJ-PM경비");
			  			break;
			  		case "Q3" :
			  			$("#bdgtType").text("Q3. PJ-PPM경비");
			  			break;
			  		case "Q4" :
			  			$("#bdgtType").text("Q4. PJ-EM경비");
			  			break;
			  		case "Q5" :
			  			$("#bdgtType").text("Q5. PJ-PCM경비");
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
	  			$("#vTcode").attr("readonly", "false");
	  		}else if(v_BdgtType == "O"){
	  			// O 입찰경비 일 경우 경비 이체코드 입력창을 보인다.
	  			$("#VTCodeSpan").show();
	  			$("#vTcode").val(data.vTcode);
	  			$("#vTcode").attr("readonly", "true");
	  		}else if(v_BdgtType == "K"){
	  			// K 본사집행현장원가 일 경우 경비 이체코드 입력창을 보인다.
	  			$("#VTCodeSpan").show();
	  			$("#vTcode").val(data.vTcode);
	  			$("#vTcode").attr("readonly", "true");
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

			var tripUserDtl = v_TripUserOrgNm + "(" + v_TripUserOrgCd + ")" + " " + v_TripUserPositCd + " " + v_TripUserId + " " + v_TripUserKnm;
			$("#tripUserDtl").text(tripUserDtl);

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
			for(var i = 0; i < data.assistComment.length; i++){
				data.assistComment = data.assistComment.replace("##", "\n");
			}
			$("#assistComment").val(data.assistComment);

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

			//저장된 결재선 불러옴
			cf_RetrieveSignInfo();

			// 복리후생비 + 접대비 계산
			hostCheck = parseInt(f_notSpace(removeComma($("#hostFare").val()))) + parseInt(f_notSpace(removeComma($("#vocLeeFare").val())));

//			if(data.signln == undefined || data.signln == "" || data.signln == null){
//				// 저장된 결재선이 존재하지 않으므로 다시 결재선을 조회하여 셋팅한다.
//				cf_retrieveSignLine();
//			}else{
//				// 결재선 셋팅
//				ds_Signln.setAllData(JSON.parse(data.signln));
//				gf_AssembleSignln(ds_Signln);
//			}

			// 합계 계산
			f_calcAirAmount();

			if(v_DocSts == "D50"){
				// 수정불가 처리
				f_setDocReadOnly();

				//SEC팀 협조의견 수정가능
				$("#secComment").removeAttr("readonly");
			}

	  		break;
	  	case "SELECT_REJECTED_EXRATE" :

	  		$(".ajax_overlay").remove();

	  		ds_ExRList.setAllData(resultData.output1[0].SapItab);

			ds_TripUserList.setAllData(datas.ds_ExtnlPer);
			ds_InputAmt.setAllData(datas.ds_Expn);
			ds_Visa.setAllData(datas.ds_Visa);


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

	  		var data = JSON.parse(ds_RejectDoc.get(0, "ifParam"));

	  		if(!(data.fileAtchId == "undefined" || data.fileAtchId == undefined)){
	  			v_FileAtchId = data.fileAtchId;
	  			// 첨부파일이 존재하는 경우
	  			gf_retrieveFileList(data.fileAtchId);

	  		}

	  		// SEC팀 여부
	  		if(!(data.secYn == "undefined" || data.secYn == undefined)){
	  			v_secYn = data.secYn;
		  		if(v_secYn == "Y"){
		  			$("#secTr").show();
		  			if(v_DocSts == "D50"){
		  				$("#secComment").removeAttr("readonly");
		  			}else if(v_DocSts == "D60"){
		  				$("#secComment").attr("readonly", "true");
		  			}
		  		}
	  		}

	  		// GHR팀 여부
	  		if(!(data.ghrYn == "undefined" || data.ghrYn == undefined)){
	  			v_ghrYn = data.ghrYn;
		  		if(v_ghrYn == "Y"){
//		  			$("#ghrTr").show();
		  			if(v_DocSts == "D60"){
		  				$("#ghrComment").removeAttr("readonly");
		  			}
		  		}
	  		}

	  		// 증빙일자
	  		$("#oDate").val(data.oDate);

	  		// 숙소구분
	  		if(data.innType == "H"){
//	  			alert("현장숙소");
	  			$("input[name='innType'][value='H']").attr("checked", "true");
	  		}else if(data.innType == "G"){
//	  			alert("외부숙소");
	  			$("input[name='innType'][value='G']").attr("checked", "true");
	  		}

			//좌석 등급
			$("select[name='tripUserGrade']").val(ds_RejectDoc.get(0, "seatGrade"));

			// 출장자 항공료
			$("#tripUserAirAmount").val(gf_AmtFormat(ds_RejectDoc.get(0, "afare")));

			// 출장 목적
			$("#tPurp").val(ds_RejectDoc.get(0, "bustrGl"));
			// 요청 사항
			$("#tReq").val(ds_RejectDoc.get(0, "demItm"));
			// 여권 발급일
			$("#fPassp").val($.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", ds_RejectDoc.get(0, "pportIssueYmd"))));

			// 여관 만료일
			$("#tPassp").val($.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", ds_RejectDoc.get(0, "pportExprtnYmd"))));

			// 항공료 요청사항
			$("#pecul").val(ds_RejectDoc.get(0, "pecul"));


			// 현지항공료
			var tAirAmt = ds_RejectDoc.get(0, "lclAfare");
			if(tAirAmt == undefined || tAirAmt == "") tAirAmt = 0;
			$("#loAirFare").val(tAirAmt);
			$("#loAirFareEx").text(gf_AmtFormat(Math.round(parseFloat(removeComma(tAirAmt)) * parseFloat(v_USD))));
			$("#loAirFareDtl").val(data.tAirAmtDtl);
			// 현지 교통비
			var tTranspAmt = ds_RejectDoc.get(0, "lclTrafficExpn");
			if(tTranspAmt == undefined || tTranspAmt == "") tTranspAmt = 0;
			$("#loTranFare").val(tTranspAmt);
			$("#loTranFareEx").text(gf_AmtFormat(Math.round(parseFloat(removeComma(tTranspAmt)) * parseFloat(v_USD))));
			$("#loTranFareDtl").val(data.tTranspAmtDtl);
			// Visa Fee
			var visaFee = ds_RejectDoc.get(0, "visaFee");
			if(visaFee == undefined || visaFee == "") visaFee = 0;
			$("#visaFeeFare").val(visaFee);
			$("#visaFeeFareEx").text(gf_AmtFormat(Math.round(parseFloat(removeComma(visaFee)) * parseFloat(v_USD))));
			$("#visaFeeFareDtl").val(data.visaFeeDtl);
			// Over Charge
			var overCharge = ds_RejectDoc.get(0, "excsExpn");
			if(overCharge == undefined || overCharge == "") overCharge = 0;
			$("#ovCharFare").val(overCharge);
			$("#ovCharFareEx").text(gf_AmtFormat(Math.round(parseFloat(removeComma(overCharge)) * parseFloat(v_USD))));
			$("#ovCharFareDtl").val(data.overChargeDtl);
			// 복리후생
			var benefits = ds_RejectDoc.get(0, "wef");
			if(benefits == undefined || benefits == "") benefits = 0;
			$("#vocLeeFare").val(benefits);
			$("#vocLeeFareEx").text(gf_AmtFormat(Math.round(parseFloat(removeComma(benefits)) * parseFloat(v_USD))));
			$("#vocLeeFareDtl").val(data.benefitsDtl);
			// 접대비
			var hostAmt = ds_RejectDoc.get(0, "svcExpn");
			if(hostAmt == undefined || hostAmt == "") hostAmt = 0;
			$("#hostFare").val(hostAmt);
			$("#hostFareEx").text(gf_AmtFormat(Math.round(parseFloat(removeComma(hostAmt)) * parseFloat(v_USD))));
			$("#hostFareDtl").val(data.hostAmtDtl);
			// 기타경비 송금예정일
			$("#etcPayDate").val(data.etcPayDate);



			// 비고
			$("#rem").val(ds_RejectDoc.get(0, "rem"));
//			//협조내역
//			for(var i = 0; i < data.assistComment.length; i++){
//				data.assistComment = data.assistComment.replace("##", "\n");
//			}
//			$("#assistComment").val(data.assistComment);

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

			// 복리후생비 + 접대비 계산
			hostCheck = parseInt(f_notSpace(removeComma($("#hostFare").val()))) + parseInt(f_notSpace(removeComma($("#vocLeeFare").val())));

			// 합계 계산
			f_calcAirAmount();

			if(v_DocSts == "D50"){
				// 수정불가 처리
				f_setDocReadOnly();

				//SEC팀 협조의견 수정가능
				$("#secComment").removeAttr("readonly");
			}
	  		break;
	  	case "CHECK_DRAFT_DUPLICATION" :

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
		  return;

	  		break;

	  	case "SELECT_ERR_MSG" :

	  		if(resultData.output1 != undefined){
	  			if(resultData.output1[0].errItem != ""){
	  				if(resultData.output1[0].errItem != null){
	  					v_ErrMsg = resultData.output1[0].errItem;
	  				}
	  			}

	  			if(resultData.output1[0].prgrItem != ""){
	  				if(resultData.output1[0].prgrItem != null){
	  					v_PrgrMsg = resultData.output1[0].prgrItem;
	  				}
	  			}
	  		}

	  		if(v_ErrMsg != ""){
	  			$("#errMsgTr").show();
	  			$("#errMsg").text(v_ErrMsg);
	  		}

	  		if(v_PrgrMsg != ""){
	  			$("#prtrMsgTr").show();
	  			$("#prtrMsg").text(v_PrgrMsg);
	  		}
	  		break;
	  	case "SELECT_SPG_EMAIL_MGM" :
	  		//self.close();
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

  			cf_getCouserInfo();
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

  			cf_getCouserInfo();
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

  			cf_getCouserInfo();
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
  			// 집행팀코스트 default 셋팅
  			$("#vTcode").val(v_Kostv);
  			$("#vTcode").removeAttr("readonly");

  			cf_getCouserInfo();
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

  			/**
  			 * 예산을 선택했을때의 정보가 obj 에 담겨있다.
  			 * Prctr 이 경비이체코드로 추가된 것이므로 경비이체코드 입력창에 강제로 삽입해준다.
  			 */
  			// 경우 경비 이체코드 입력창을 보인다.
  			$("#VTCodeSpan").show();
  			// 집행팀코스트 default 셋팅
  			$("#vTcode").val(obj.bdgtData.Prctr);
  			$("#vTcode").attr("readonly", "true");

  			cf_getCouserInfo();
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

  			// I 일반경비 일 경우 경비 이체코드 입력창을 보인다.
  			$("#VTCodeSpan").hide();

  			cf_getCouserInfo();
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

  			cf_getCouserInfo();
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

  			v_Chief = obj.bdgtData.orgChief.split(" ")[0]; // 집행팀장ID
  			v_Chiefnm = obj.bdgtData.orgChief.split(" ")[1]; // 집행팀장 이름
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);

  			// I 일반경비 일 경우 경비 이체코드 입력창을 보인다.
  			$("#VTCodeSpan").hide();

  			cf_getCouserInfo();
  			break;
 		case "Q1" :
  			$("#bdgtType").text("Q1. PJ-현장경비");

  			// 결과 셋팅
  			v_Aufnr = obj.bdgtData.orgId;
  			v_KText = obj.bdgtData.orgNm;
  			AufnrText = obj.bdgtData.orgId + " / 내역 : " + obj.bdgtData.orgNm;
  			$("#Aufnr").text(AufnrText);

  			v_Kostv = obj.bdgtData.orgId;
  			v_Kostvnm = obj.bdgtData.orgNm;

  			v_Chief = obj.bdgtData.orgChief.split(" ")[0]; // 집행팀장ID
  			v_Chiefnm = obj.bdgtData.orgChief.split(" ")[1]; // 집행팀장 이름
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);

  			// I 일반경비 일 경우 경비 이체코드 입력창을 보인다.
  			$("#VTCodeSpan").hide();

  			cf_getCouserInfo();
  			break;
 		case "Q2" :
  			$("#bdgtType").text("Q2. PJ-PM경비");

  			// 결과 셋팅
  			v_Aufnr = obj.bdgtData.orgId;
  			v_KText = obj.bdgtData.orgNm;
  			AufnrText = obj.bdgtData.orgId + " / 내역 : " + obj.bdgtData.orgNm;
  			$("#Aufnr").text(AufnrText);

  			v_Kostv = obj.bdgtData.orgId;
  			v_Kostvnm = obj.bdgtData.orgNm;

  			v_Chief = obj.bdgtData.orgChief.split(" ")[0]; // 집행팀장ID
  			v_Chiefnm = obj.bdgtData.orgChief.split(" ")[1]; // 집행팀장 이름
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);

  			// I 일반경비 일 경우 경비 이체코드 입력창을 보인다.
  			$("#VTCodeSpan").hide();

  			cf_getCouserInfo();
  			break;
		case "Q3" :
  			$("#bdgtType").text("Q3. PJ-PPM경비");

  			// 결과 셋팅
  			v_Aufnr = obj.bdgtData.orgId;
  			v_KText = obj.bdgtData.orgNm;
  			AufnrText = obj.bdgtData.orgId + " / 내역 : " + obj.bdgtData.orgNm;
  			$("#Aufnr").text(AufnrText);

  			v_Kostv = obj.bdgtData.orgId;
  			v_Kostvnm = obj.bdgtData.orgNm;

  			v_Chief = obj.bdgtData.orgChief.split(" ")[0]; // 집행팀장ID
  			v_Chiefnm = obj.bdgtData.orgChief.split(" ")[1]; // 집행팀장 이름
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);

  			// I 일반경비 일 경우 경비 이체코드 입력창을 보인다.
  			$("#VTCodeSpan").hide();

  			cf_getCouserInfo();
  			break;
		case "Q4" :
  			$("#bdgtType").text("Q4. PJ-EM경비");

  			// 결과 셋팅
  			v_Aufnr = obj.bdgtData.orgId;
  			v_KText = obj.bdgtData.orgNm;
  			AufnrText = obj.bdgtData.orgId + " / 내역 : " + obj.bdgtData.orgNm;
  			$("#Aufnr").text(AufnrText);

  			v_Kostv = obj.bdgtData.orgId;
  			v_Kostvnm = obj.bdgtData.orgNm;

  			v_Chief = obj.bdgtData.orgChief.split(" ")[0]; // 집행팀장ID
  			v_Chiefnm = obj.bdgtData.orgChief.split(" ")[1]; // 집행팀장 이름
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);

  			// I 일반경비 일 경우 경비 이체코드 입력창을 보인다.
  			$("#VTCodeSpan").hide();

  			cf_getCouserInfo();
  			break;
		case "Q5" :
  			$("#bdgtType").text("Q5. PJ-PCM경비");

  			// 결과 셋팅
  			v_Aufnr = obj.bdgtData.orgId;
  			v_KText = obj.bdgtData.orgNm;
  			AufnrText = obj.bdgtData.orgId + " / 내역 : " + obj.bdgtData.orgNm;
  			$("#Aufnr").text(AufnrText);

  			v_Kostv = obj.bdgtData.orgId;
  			v_Kostvnm = obj.bdgtData.orgNm;

  			v_Chief = obj.bdgtData.orgChief.split(" ")[0]; // 집행팀장ID
  			v_Chiefnm = obj.bdgtData.orgChief.split(" ")[1]; // 집행팀장 이름
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);

  			// I 일반경비 일 경우 경비 이체코드 입력창을 보인다.
  			$("#VTCodeSpan").hide();

  			cf_getCouserInfo();
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

  			/**
  			 * 예산을 선택했을때의 정보가 obj 에 담겨있다.
  			 * Prctr 이 경비이체코드로 추가된 것이므로 경비이체코드 입력창에 강제로 삽입해준다.
  			 */
  			// 경우 경비 이체코드 입력창을 보인다.
  			$("#VTCodeSpan").show();
  			// 집행팀코스트 default 셋팅
  			$("#vTcode").val(obj.bdgtData.Prctr);
  			$("#vTcode").attr("readonly", "true");

  			cf_getCouserInfo();
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
  			v_Chief = obj.bdgtData.Chief; // 집행팀장ID
  			v_Chiefnm = obj.bdgtData.Chiefnm; // 집행팀장 이름
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);

  			// I 일반경비 일 경우 경비 이체코드 입력창을 보인다.
  			$("#VTCodeSpan").hide();

  			cf_getCouserInfo();
  			break;

  		default :
	  		break;
	}
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

	if(obj.type == "D"){
		// 비자 정보를 삭제한다.
		ds_VisaInfoList.remove(obj.dsIndex);
		$("#" + obj.targetId).remove();

	}else{
		if(obj.dsIndex < 0){

			// 새로운 비자정보
			var visaInfo = {
					natCd : obj.natCd,
					visaOwnYn : obj.visaYn,
					visaIssueYmd : $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", obj.visaStartDate)),
					visaExprtnYmd : $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", obj.visaEndDate)),
					riskYn : obj.riskYn
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
//			$("#visaInfoDetail tr:last select[name='vVisaYn']").val(obj.visaYnDtl);
			$("#visaInfoDetail tr:last select[name='vVisaYn']").val(obj.visaYn);
			$("#visaInfoDetail tr:last input[name='vStartDate']").val(obj.visaStartDate);
			$("#visaInfoDetail tr:last input[name='vEndDate']").val(obj.visaEndDate);

			// readonly 처리
			$("#visaInfoDetail tr input[name='vNat']").attr("readonly", "true");
			if(obj.visaYn != "Y"){
				$("#visaInfoDetail tr input[name='vStartDate']").attr("disabled", "true");
				$("#visaInfoDetail tr input[name='vEndDate']").attr("disabled", "true");
			}
//			$("#visaInfoDetail tr input[name='vVisaYn']").attr("readonly", "true");
//			$("#visaInfoDetail tr input[name='vStartDate']").attr("readonly", "true");
//			$("#visaInfoDetail tr input[name='vEndDate']").attr("readonly", "true");

			// 클릭시 수정 팝업 이벤트 Rebind
			// event rebind
			$("input[name='vNat']").unbind("click");
			$("input[name='vNat']").click(function(e){
				cf_OpenVisaInfoPop(e);
			});
			// event rebind
//			$("input[name='vVisaYn']").unbind("click");
//			$("input[name='vVisaYn']").click(function(e){
//				cf_OpenVisaInfoPop(e);
//			});
			$("select[name='vVisaYn']").bind('change', function(e) {
				var targetId = $(e.target).parent().parent().attr("id");

				if($("#" + targetId + " select[name='vVisaYn']").val() != "Y"){
					$("#" + targetId + " input[name='vStartDate']").val("");
					$("#" + targetId + " input[name='vEndDate']").val("");
					$("#" + targetId + " input[name='vStartDate']").attr("disabled", "true");
					$("#" + targetId + " input[name='vEndDate']").attr("disabled", "true");
				} else {
					$("#" + targetId + " input[name='vStartDate']").removeAttr("disabled");
					$("#" + targetId + " input[name='vEndDate']").removeAttr("disabled");
					$("#" + targetId + " input[name='vStartDate']").attr("readonly", "false");
					$("#" + targetId + " input[name='vEndDate']").attr("readonly", "false");


				}

				// vData1 -- 0번째 DataSet parseInt 하여 -1 하면 Index가 나온다.
				var dId = parseInt(targetId.substr(5, 1))-1;
				var vVal = e.target.value;

				ds_VisaInfoList.set(dId, "visaOwnYn", vVal);



			});

			// event rebind
			$("input[name='vStartDate']").unbind("click");
			$("input[name='vStartDate']").mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
			$("input[name='vStartDate']").bind('change', function(e) {
				var targetId = $(e.target).parent().parent().attr("id");

				// vData1 -- 0번째 DataSet parseInt 하여 -1 하면 Index가 나온다.
				var dId = parseInt(targetId.substr(5, 1))-1;
				var vVal = e.target.value;

				ds_VisaInfoList.set(dId, "visaIssueYmd", $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", vVal)));


			});
//			$("input[name='vStartDate']").click(function(e){
//
//				//				cf_OpenVisaInfoPop(e);
//			});
			// event rebind
			$("input[name='vEndDate']").unbind("click");
			$("input[name='vEndDate']").mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
			$("input[name='vEndDate']").bind('change', function(e) {
				var targetId = $(e.target).parent().parent().attr("id");

				// vData1 -- 0번째 DataSet parseInt 하여 -1 하면 Index가 나온다.
				var dId = parseInt(targetId.substr(5, 1))-1;
				var vVal = e.target.value;

				ds_VisaInfoList.set(dId, "visaExprtnYmd", $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", vVal)));


			});

		}else{

			// 이미 존재하는 정보 수정
			var index = obj.dsIndex;
			ds_VisaInfoList.set(index, "natCd", obj.natCd);
			ds_VisaInfoList.set(index, "visaOwnYn", obj.visaYn);
			ds_VisaInfoList.set(index, "visaIssueYmd", $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", obj.visaStartDate)));
			ds_VisaInfoList.set(index, "visaExprtnYmd", $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", obj.visaEndDate)));
			ds_VisaInfoList.set(index, "riskYn", obj.riskYn);

			if(obj.visaYn != "Y"){
				$("#" + obj.targetId + " input[name='vStartDate']").val("");
				$("#" + obj.targetId + " input[name='vEndDate']").val("");
				$("#" + obj.targetId + " input[name='vStartDate']").attr("disabled", "true");
				$("#" + obj.targetId + " input[name='vEndDate']").attr("disabled", "true");
			}else{
				$("#" + obj.targetId + " input[name='vStartDate']").removeAttr("disabled");
				$("#" + obj.targetId + " input[name='vEndDate']").removeAttr("disabled");
				$("#" + obj.targetId + " input[name='vStartDate']").attr("readonly", "false");
				$("#" + obj.targetId + " input[name='vEndDate']").attr("readonly", "false");
			}


			$("#" + obj.targetId + " input[name='vNat']").val(obj.natNm);
			$("#" + obj.targetId + " select[name='vVisaYn']").val(obj.visaYn);
//			$("#" + obj.targetId + " select[name='visaYn']").val(obj.visaYnDtl);
			$("#" + obj.targetId + " input[name='vStartDate']").val(obj.visaStartDate);
			$("#" + obj.targetId + " input[name='vEndDate']").val(obj.visaEndDate);

		}
	}




}

function f_callBackFuncCityAddP(obj){

	if(obj.type == "D"){
		// 비자 정보를 삭제한다.
		ds_CityList.remove(obj.dsIndex);
		$("#" + obj.targetId).remove();

		// 출장시작, 종료, 기간 계산
		f_calcTripDate();

	}else{
		if(obj.dsIndex < 0){

			// 새로운 비자정보
			var cityInfo = {
					natCd : obj.natCd,
					cityNm : obj.cityNm,
					visaOwnYn : '',
					stayStYmd : $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", obj.stayStYmd)),
					stayEdYmd : $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", obj.stayEdYmd)),
					dutyCont : obj.dutyCont,
					riskYn : obj.riskYn
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
//			$("#cityDetail tr input[name='cityNm']").attr("readonly", "true");
//			$("#cityDetail tr input[name='stayStYmd']").attr("readonly", "true");
//			$("#cityDetail tr input[name='stayEdYmd']").attr("readonly", "true");
//			$("#cityDetail tr textarea[name='dutyCont']").attr("readonly", "true");

			// 클릭시 수정 팝업 이벤트 Rebind
			// event rebind
			$("input[name='natNm']").unbind("click");
			$("input[name='natNm']").click(function(e){
				cf_OpenCityInfoPop(e);
			});
			// event rebind
			$("input[name='cityNm']").unbind("click");
//			$("input[name='cityNm']").click(function(e){
//				cf_OpenCityInfoPop(e);
//			});

			$("input[name='cityNm']").bind('change', function(e) {
				var targetId = $(e.target).parent().parent().attr("id");

				// vData1 -- 0번째 DataSet parseInt 하여 -1 하면 Index가 나온다.
				var dId = parseInt(targetId.substr(5, 1))-1;
				var cVal = e.target.value;

				ds_CityList.set(dId, "cityNm", cVal);

			});

			// event rebind
			$("input[name='stayStYmd']").mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
//			$("input[name='stayStYmd']").unbind("click");
//			$("input[name='stayStYmd']").click(function(e){
//				cf_OpenCityInfoPop(e);
//			});

			$("input[name='stayStYmd']").bind('change', function(e) {
				var targetId = $(e.target).parent().parent().attr("id");

				// vData1 -- 0번째 DataSet parseInt 하여 -1 하면 Index가 나온다.
				var dId = parseInt(targetId.substr(5, 1))-1;
				var cVal = e.target.value;

				ds_CityList.set(dId, "stayStYmd", $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", cVal)));

				// 출장시작, 종료, 기간 계산
				f_calcTripDate();


			});

			// event rebind
			$("input[name='stayEdYmd']").mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
//			$("input[name='stayEdYmd']").unbind("click");
//			$("input[name='stayEdYmd']").click(function(e){
//				cf_OpenCityInfoPop(e);
//			});

			$("input[name='stayEdYmd']").bind('change', function(e) {
				var targetId = $(e.target).parent().parent().attr("id");

				// vData1 -- 0번째 DataSet parseInt 하여 -1 하면 Index가 나온다.
				var dId = parseInt(targetId.substr(5, 1))-1;
				var cVal = e.target.value;

				ds_CityList.set(dId, "stayEdYmd", $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", cVal)));

				// 출장시작, 종료, 기간 계산
				f_calcTripDate();

			});

			// event rebind
			$("textarea[name='dutyCont']").unbind("click");
//			$("textarea[name='dutyCont']").click(function(e){
//				cf_OpenCityInfoPop(e);
//			});

			$("textarea[name='dutyCont']").bind('change', function(e) {
				var targetId = $(e.target).parent().parent().attr("id");

				// vData1 -- 0번째 DataSet parseInt 하여 -1 하면 Index가 나온다.
				var dId = parseInt(targetId.substr(5, 1))-1;
				var cVal = e.target.value;

				ds_CityList.set(dId, "dutyCont", cVal);
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
			ds_CityList.set(index, "riskYn", obj.riskYn);

			$("#" + obj.targetId + " input[name='natNm']").val(obj.natNm);
			$("#" + obj.targetId + " input[name='cityNm']").val(obj.cityNm);
			$("#" + obj.targetId + " input[name='stayStYmd']").val(obj.stayStYmd);
			$("#" + obj.targetId + " input[name='stayEdYmd']").val(obj.stayEdYmd);
			$("#" + obj.targetId + " textarea[name='dutyCont']").val(obj.dutyCont);

		}

		// 출장시작, 종료, 기간 계산
		f_calcTripDate();
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

			if(tUserVal == "") tUserVal = 0;

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

			// 개인지급 합계 (체재비 + 항공료)
			v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;

			$("#totalAmount").text(gf_AmtFormat(v_TotalAmount));
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
		visaYn : $("#" + targetId + " select[name='vVisaYn']").val(),
		visaStartDate : $("#" + targetId + " input[name='vStartDate']").val(),
		visaEndDate : $("#" + targetId + " input[name='vEndDate']").val()
//		visaYn : ds_VisaInfoList.get(dsIndex, "visaOwnYn"),
//		visaStartDate : $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", ds_VisaInfoList.get(dsIndex, "visaIssueYmd"))),
//		visaEndDate : $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", ds_VisaInfoList.get(dsIndex, "visaExprtnYmd")))
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
		cityNm : $("#" + targetId + " input[name='cityNm']").val(),
		stayStYmd : $("#" + targetId + " input[name='stayStYmd']").val(),
		stayEdYmd : $("#" + targetId + " input[name='stayEdYmd']").val(),
		dutyCont : $("#" + targetId + " textarea[name='dutyCont']").val()
//		cityNm : ds_CityList.get(dsIndex, "cityNm"),
//		stayStYmd : $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", ds_CityList.get(dsIndex, "stayStYmd"))),
//		stayEdYmd : $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", ds_CityList.get(dsIndex, "stayEdYmd"))),
//		dutyCont : ds_CityList.get(dsIndex, "dutyCont")
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


	// 2015 10 07 기준액 수정 한뒤 저장해야됨
	// dd_expn_dcnt 필드에 수정된 기준액을 저장하고 그것을 사용하여 체제비를 계산한다.
	//  chk: true
	//	id: "jaDay"
	//	ref: "10"
	//	val: "1"

	for(var i = 0; i < result.length; i++){
		if(result[i].chk == true){
			var idTr = "#" + result[i].id + "Tr";
			$(idTr).show();
			//숙박비(＄) 	유럽숙박비(€)	영국숙박비(￡)	일본숙박비(￥)

			if(result[i].id == "usNight"){
				edtTxtVal = parseInt(result[i].val) + "일 /"
				  		+ " $" + gf_AmtFormat(parseInt(result[i].ref) * parseInt(result[i].val))
				  		+ " / ￦" + gf_AmtFormat(Math.round(v_USD * (parseInt(result[i].ref) * parseInt(result[i].val))));

				v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (parseInt(result[i].ref) * parseInt(result[i].val))));

				v_cTotUSD = v_cTotUSD + (parseInt(result[i].ref) * parseInt(result[i].val));

				var obj = new Object();
				obj.expnCls = result[i].id; // 구분코드
				obj.accomoCls = "일반숙박"; // 구분코드(한글)
				obj.accomoExpn = Math.round(v_USD * (parseInt(result[i].ref) * parseInt(result[i].val))); // KRW
				obj.accomoExpnDcnt = result[i].val; // 사용일수
				obj.ddExpnDcnt = parseInt(result[i].ref); // 기준액
				obj.ddExpn = (parseInt(result[i].ref) * parseInt(result[i].val)); // 기준액 * 일수

				ds_ExpnList.add(obj);


			}else if(result[i].id == "usDay"){
				edtTxtVal = parseInt(result[i].val) + "일 /"
		  		+ " $" + gf_AmtFormat(parseInt(result[i].ref) * parseInt(result[i].val))
		  		+ " / ￦" + gf_AmtFormat(Math.round(v_USD * (parseInt(result[i].ref) * parseInt(result[i].val))));

				v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (parseInt(result[i].ref) * parseInt(result[i].val))));

				v_cTotUSD = v_cTotUSD + (parseInt(result[i].ref) * parseInt(result[i].val));

				var obj = new Object();
				obj.expnCls = result[i].id; // 구분코드
				obj.accomoCls = "일반일당"; // 구분코드(한글)
				obj.accomoExpn = Math.round(v_USD * (parseInt(result[i].ref) * parseInt(result[i].val))); // KRW
				obj.accomoExpnDcnt = result[i].val; // 사용일수
				obj.ddExpnDcnt = parseInt(result[i].ref); // 기준액
				obj.ddExpn = (parseInt(result[i].ref) * parseInt(result[i].val)); // 기준액 * 일수

				ds_ExpnList.add(obj);


			}else if(result[i].id == "edu"){
				edtTxtVal = parseInt(result[i].val) + "일 /"
		  		+ " $" + gf_AmtFormat(parseInt(result[i].ref) * parseInt(result[i].val))
		  		+ " / ￦" + gf_AmtFormat(Math.round(v_USD * (parseInt(result[i].ref) * parseInt(result[i].val))));

				v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (parseInt(result[i].ref) * parseInt(result[i].val))));

				v_cTotUSD = v_cTotUSD + (parseInt(result[i].ref) * parseInt(result[i].val));

				var obj = new Object();
				obj.expnCls = result[i].id; // 구분코드
				obj.accomoCls = "연수경비(숙)"; // 구분코드(한글)
				obj.accomoExpn = Math.round(v_USD * (parseInt(result[i].ref) * parseInt(result[i].val))); // KRW
				obj.accomoExpnDcnt = result[i].val; // 사용일수
				obj.ddExpnDcnt = parseInt(result[i].ref); // 기준액
				obj.ddExpn = (parseInt(result[i].ref) * parseInt(result[i].val)); // 기준액 * 일수

				ds_ExpnList.add(obj);


			}else if(result[i].id == "spot"){
				edtTxtVal = parseInt(result[i].val) + "일 /"
		  		+ " $" + gf_AmtFormat(parseInt(result[i].ref) * parseInt(result[i].val))
		  		+ " / ￦" + gf_AmtFormat(Math.round(v_USD * (parseInt(result[i].ref) * parseInt(result[i].val))));

				v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (parseInt(result[i].ref) * parseInt(result[i].val))));

				v_cTotUSD = v_cTotUSD + (parseInt(result[i].ref) * parseInt(result[i].val));

				var obj = new Object();
				obj.expnCls = result[i].id; // 구분코드
				obj.accomoCls = "연수경비(숙식)"; // 구분코드(한글)
				obj.accomoExpn = Math.round(v_USD * (parseInt(result[i].ref) * parseInt(result[i].val))); // KRW
				obj.accomoExpnDcnt = result[i].val; // 사용일수
				obj.ddExpnDcnt = parseInt(result[i].ref); // 기준액
				obj.ddExpn = (parseInt(result[i].ref) * parseInt(result[i].val)); // 기준액 * 일수

				ds_ExpnList.add(obj);


			}else if(result[i].id == "euNight"){
				edtTxtVal = parseInt(result[i].val) + "일 /"
		  		+ " €" + gf_AmtFormat(parseInt(result[i].ref) * parseInt(result[i].val))
		  		+ " / ￦" + gf_AmtFormat(Math.round(v_EUR * (parseInt(result[i].ref) * parseInt(result[i].val))));

				v_cTotalAmt = v_cTotalAmt + (Math.round(v_EUR * (parseInt(result[i].ref) * parseInt(result[i].val))));

				v_cTotEUR = v_cTotEUR + (parseInt(result[i].ref) * parseInt(result[i].val));

				var obj = new Object();
				obj.expnCls = result[i].id; // 구분코드
				obj.accomoCls = "유럽숙박"; // 구분코드(한글)
				obj.accomoExpn = Math.round(v_USD * (parseInt(result[i].ref) * parseInt(result[i].val))); // KRW
				obj.accomoExpnDcnt = result[i].val; // 사용일수
				obj.ddExpnDcnt = parseInt(result[i].ref); // 기준액
				obj.ddExpn = (parseInt(result[i].ref) * parseInt(result[i].val)); // 기준액 * 일수

				ds_ExpnList.add(obj);

			}else if(result[i].id == "euDay"){
				edtTxtVal = parseInt(result[i].val) + "일 /"
		  		+ " €" + gf_AmtFormat(parseInt(result[i].ref) * parseInt(result[i].val))
		  		+ " / ￦" + gf_AmtFormat(Math.round(v_EUR * (parseInt(result[i].ref) * parseInt(result[i].val))));

				v_cTotalAmt = v_cTotalAmt + (Math.round(v_EUR * (parseInt(result[i].ref) * parseInt(result[i].val))));

				v_cTotEUR = v_cTotEUR + (parseInt(result[i].ref) * parseInt(result[i].val));

				var obj = new Object();
				obj.expnCls = result[i].id; // 구분코드
				obj.accomoCls = "유럽일당"; // 구분코드(한글)
				obj.accomoExpn = Math.round(v_USD * (parseInt(result[i].ref) * parseInt(result[i].val))); // KRW
				obj.accomoExpnDcnt = result[i].val; // 사용일수
				obj.ddExpnDcnt = parseInt(result[i].ref); // 기준액
				obj.ddExpn = (parseInt(result[i].ref) * parseInt(result[i].val)); // 기준액 * 일수

				ds_ExpnList.add(obj);

			}else if(result[i].id == "enNight"){
				edtTxtVal = parseInt(result[i].val) + "일 /"
		  		+ " ￡" + gf_AmtFormat(parseInt(result[i].ref) * parseInt(result[i].val))
		  		+ " / ￦" + gf_AmtFormat(Math.round(v_GBP * (parseInt(result[i].ref) * parseInt(result[i].val))));

				v_cTotalAmt = v_cTotalAmt + (Math.round(v_GBP * (parseInt(result[i].ref) * parseInt(result[i].val))));

				v_cTotGBP = v_cTotGBP + (parseInt(result[i].ref) * parseInt(result[i].val));

				var obj = new Object();
				obj.expnCls = result[i].id; // 구분코드
				obj.accomoCls = "영국숙박"; // 구분코드(한글)
				obj.accomoExpn = Math.round(v_USD * (parseInt(result[i].ref) * parseInt(result[i].val))); // KRW
				obj.accomoExpnDcnt = result[i].val; // 사용일수
				obj.ddExpnDcnt = parseInt(result[i].ref); // 기준액
				obj.ddExpn = (parseInt(result[i].ref) * parseInt(result[i].val)); // 기준액 * 일수

				ds_ExpnList.add(obj);

			}else if(result[i].id == "enDay"){
				edtTxtVal = parseInt(result[i].val) + "일 /"
		  		+ " ￡" + gf_AmtFormat(parseInt(result[i].ref) * parseInt(result[i].val))
		  		+ " / ￦" + gf_AmtFormat(Math.round(v_GBP * (parseInt(result[i].ref) * parseInt(result[i].val))));

				v_cTotalAmt = v_cTotalAmt + (Math.round(v_GBP * (parseInt(result[i].ref) * parseInt(result[i].val))));

				v_cTotGBP = v_cTotGBP + (parseInt(result[i].ref) * parseInt(result[i].val));

				var obj = new Object();
				obj.expnCls = result[i].id; // 구분코드
				obj.accomoCls = "영국일당"; // 구분코드(한글)
				obj.accomoExpn = Math.round(v_USD * (parseInt(result[i].ref) * parseInt(result[i].val))); // KRW
				obj.accomoExpnDcnt = result[i].val; // 사용일수
				obj.ddExpnDcnt = parseInt(result[i].ref); // 기준액
				obj.ddExpn = (parseInt(result[i].ref) * parseInt(result[i].val)); // 기준액 * 일수

				ds_ExpnList.add(obj);

			}else if(result[i].id == "jaNight"){
				edtTxtVal = parseInt(result[i].val) + "일 /"
		  		+ " ￥" + gf_AmtFormat(parseInt(result[i].ref) * parseInt(result[i].val))
		  		+ " / ￦" + gf_AmtFormat(Math.round(v_JPY * (parseInt(result[i].ref) * parseInt(result[i].val))));

				v_cTotalAmt = v_cTotalAmt + (Math.round(v_JPY * (parseInt(result[i].ref) * parseInt(result[i].val))));

				v_cTotJPY = v_cTotJPY + (parseInt(result[i].ref) * parseInt(result[i].val));

				var obj = new Object();
				obj.expnCls = result[i].id; // 구분코드
				obj.accomoCls = "일본숙박"; // 구분코드(한글)
				obj.accomoExpn = Math.round(v_USD * (parseInt(result[i].ref) * parseInt(result[i].val))); // KRW
				obj.accomoExpnDcnt = result[i].val; // 사용일수
				obj.ddExpnDcnt = parseInt(result[i].ref); // 기준액
				obj.ddExpn = (parseInt(result[i].ref) * parseInt(result[i].val)); // 기준액 * 일수

				ds_ExpnList.add(obj);

			}else if(result[i].id == "jaDay"){
				edtTxtVal = parseInt(result[i].val) + "일 /"
		  		+ " ￥" + gf_AmtFormat(parseInt(result[i].ref) * parseInt(result[i].val))
		  		+ " / ￦" + gf_AmtFormat(Math.round(v_JPY * (parseInt(result[i].ref) * parseInt(result[i].val))));

				v_cTotalAmt = v_cTotalAmt + (Math.round(v_JPY * (parseInt(result[i].ref) * parseInt(result[i].val))));

				v_cTotJPY = v_cTotJPY + (parseInt(result[i].ref) * parseInt(result[i].val));


				var obj = new Object();
				obj.expnCls = result[i].id; // 구분코드
				obj.accomoCls = "일본일당"; // 구분코드(한글)
				obj.accomoExpn = Math.round(v_USD * (parseInt(result[i].ref) * parseInt(result[i].val))); // KRW
				obj.accomoExpnDcnt = result[i].val; // 사용일수
				obj.ddExpnDcnt = parseInt(result[i].ref); // 기준액
				obj.ddExpn = (parseInt(result[i].ref) * parseInt(result[i].val)); // 기준액 * 일수

				ds_ExpnList.add(obj);

			}

			$("#" + result[i].id).text(edtTxtVal);
		}else{
			var idTr = "#" + result[i].id + "Tr";
			$(idTr).hide();
		}
	}

//	for(var i = 0; i < result.length; i++){
//		if(result[i].chk == true){
//			var idTr = "#" + result[i].id + "Tr";
//			$(idTr).show();
//			//숙박비(＄) 	유럽숙박비(€)	영국숙박비(￡)	일본숙박비(￥)
//
//			if(result[i].id == "usNight"){
//				edtTxtVal = parseInt(result[i].val) + "일 /"
//				  		+ " $" + gf_AmtFormat(checkRefVal(gv_userPositCd, "일반숙박") * parseInt(result[i].val))
//				  		+ " / ￦" + gf_AmtFormat(Math.round(v_USD * (checkRefVal(gv_userPositCd, "일반숙박") * parseInt(result[i].val))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (checkRefVal(gv_userPositCd, "일반숙박") * parseInt(result[i].val))));
//
//				v_cTotUSD = v_cTotUSD + (checkRefVal(gv_userPositCd, "일반숙박") * parseInt(result[i].val));
//
//				var obj = new Object();
//				obj.expnCls = result[i].id; // 구분코드
//				obj.accomoCls = "일반숙박"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "일반숙박") * parseInt(result[i].val))); // KRW
//				obj.accomoExpnDcnt = result[i].val; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "일반숙박"); // 기준액
//				obj.ddExpn = (checkRefVal(gv_userPositCd, "일반숙박") * parseInt(result[i].val)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//
//			}else if(result[i].id == "usDay"){
//				edtTxtVal = parseInt(result[i].val) + "일 /"
//		  		+ " $" + gf_AmtFormat(checkRefVal(gv_userPositCd, "일반일당") * parseInt(result[i].val))
//		  		+ " / ￦" + gf_AmtFormat(Math.round(v_USD * (checkRefVal(gv_userPositCd, "일반일당") * parseInt(result[i].val))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (checkRefVal(gv_userPositCd, "일반일당") * parseInt(result[i].val))));
//
//				v_cTotUSD = v_cTotUSD + (checkRefVal(gv_userPositCd, "일반일당") * parseInt(result[i].val));
//
//				var obj = new Object();
//				obj.expnCls = result[i].id; // 구분코드
//				obj.accomoCls = "일반일당"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "일반일당") * parseInt(result[i].val))); // KRW
//				obj.accomoExpnDcnt = result[i].val; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "일반일당"); // 기준액
//				obj.ddExpn = (checkRefVal(gv_userPositCd, "일반일당") * parseInt(result[i].val)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//
//			}else if(result[i].id == "edu"){
//				edtTxtVal = parseInt(result[i].val) + "일 /"
//		  		+ " $" + gf_AmtFormat(checkRefVal(gv_userPositCd, "연수경비(숙)") * parseInt(result[i].val))
//		  		+ " / ￦" + gf_AmtFormat(Math.round(v_USD * (checkRefVal(gv_userPositCd, "연수경비(숙)") * parseInt(result[i].val))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (checkRefVal(gv_userPositCd, "연수경비(숙)") * parseInt(result[i].val))));
//
//				v_cTotUSD = v_cTotUSD + (checkRefVal(gv_userPositCd, "연수경비(숙)") * parseInt(result[i].val));
//
//				var obj = new Object();
//				obj.expnCls = result[i].id; // 구분코드
//				obj.accomoCls = "연수경비(숙)"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "연수경비(숙)") * parseInt(result[i].val))); // KRW
//				obj.accomoExpnDcnt = result[i].val; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "연수경비(숙)"); // 기준액
//				obj.ddExpn = (checkRefVal(gv_userPositCd, "연수경비(숙)") * parseInt(result[i].val)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//
//			}else if(result[i].id == "spot"){
//				edtTxtVal = parseInt(result[i].val) + "일 /"
//		  		+ " $" + gf_AmtFormat(checkRefVal(gv_userPositCd, "연수경비(숙식)") * parseInt(result[i].val))
//		  		+ " / ￦" + gf_AmtFormat(Math.round(v_USD * (checkRefVal(gv_userPositCd, "연수경비(숙식)") * parseInt(result[i].val))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (checkRefVal(gv_userPositCd, "연수경비(숙식)") * parseInt(result[i].val))));
//
//				v_cTotUSD = v_cTotUSD + (checkRefVal(gv_userPositCd, "연수경비(숙식)") * parseInt(result[i].val));
//
//				var obj = new Object();
//				obj.expnCls = result[i].id; // 구분코드
//				obj.accomoCls = "연수경비(숙식)"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "연수경비(숙식)") * parseInt(result[i].val))); // KRW
//				obj.accomoExpnDcnt = result[i].val; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "연수경비(숙식)"); // 기준액
//				obj.ddExpn = (checkRefVal(gv_userPositCd, "연수경비(숙식)") * parseInt(result[i].val)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//
//			}else if(result[i].id == "euNight"){
//				edtTxtVal = parseInt(result[i].val) + "일 /"
//		  		+ " €" + gf_AmtFormat(checkRefVal(gv_userPositCd, "유럽숙박") * parseInt(result[i].val))
//		  		+ " / ￦" + gf_AmtFormat(Math.round(v_EUR * (checkRefVal(gv_userPositCd, "유럽숙박") * parseInt(result[i].val))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_EUR * (checkRefVal(gv_userPositCd, "유럽숙박") * parseInt(result[i].val))));
//
//				v_cTotEUR = v_cTotEUR + (checkRefVal(gv_userPositCd, "유럽숙박") * parseInt(result[i].val));
//
//				var obj = new Object();
//				obj.expnCls = result[i].id; // 구분코드
//				obj.accomoCls = "유럽숙박"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "유럽숙박") * parseInt(result[i].val))); // KRW
//				obj.accomoExpnDcnt = result[i].val; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "유럽숙박"); // 기준액
//				obj.ddExpn = (checkRefVal(gv_userPositCd, "유럽숙박") * parseInt(result[i].val)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//			}else if(result[i].id == "euDay"){
//				edtTxtVal = parseInt(result[i].val) + "일 /"
//		  		+ " €" + gf_AmtFormat(checkRefVal(gv_userPositCd, "유럽일당") * parseInt(result[i].val))
//		  		+ " / ￦" + gf_AmtFormat(Math.round(v_EUR * (checkRefVal(gv_userPositCd, "유럽일당") * parseInt(result[i].val))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_EUR * (checkRefVal(gv_userPositCd, "유럽일당") * parseInt(result[i].val))));
//
//				v_cTotEUR = v_cTotEUR + (checkRefVal(gv_userPositCd, "유럽일당") * parseInt(result[i].val));
//
//				var obj = new Object();
//				obj.expnCls = result[i].id; // 구분코드
//				obj.accomoCls = "유럽일당"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "유럽일당") * parseInt(result[i].val))); // KRW
//				obj.accomoExpnDcnt = result[i].val; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "유럽일당"); // 기준액
//				obj.ddExpn = (checkRefVal(gv_userPositCd, "유럽일당") * parseInt(result[i].val)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//			}else if(result[i].id == "enNight"){
//				edtTxtVal = parseInt(result[i].val) + "일 /"
//		  		+ " ￡" + gf_AmtFormat(checkRefVal(gv_userPositCd, "영국숙박") * parseInt(result[i].val))
//		  		+ " / ￦" + gf_AmtFormat(Math.round(v_GBP * (checkRefVal(gv_userPositCd, "영국숙박") * parseInt(result[i].val))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_GBP * (checkRefVal(gv_userPositCd, "영국숙박") * parseInt(result[i].val))));
//
//				v_cTotGBP = v_cTotGBP + (checkRefVal(gv_userPositCd, "영국숙박") * parseInt(result[i].val));
//
//				var obj = new Object();
//				obj.expnCls = result[i].id; // 구분코드
//				obj.accomoCls = "영국숙박"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "영국숙박") * parseInt(result[i].val))); // KRW
//				obj.accomoExpnDcnt = result[i].val; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "영국숙박"); // 기준액
//				obj.ddExpn = (checkRefVal(gv_userPositCd, "영국숙박") * parseInt(result[i].val)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//			}else if(result[i].id == "enDay"){
//				edtTxtVal = parseInt(result[i].val) + "일 /"
//		  		+ " ￡" + gf_AmtFormat(checkRefVal(gv_userPositCd, "영국일당") * parseInt(result[i].val))
//		  		+ " / ￦" + gf_AmtFormat(Math.round(v_GBP * (checkRefVal(gv_userPositCd, "영국일당") * parseInt(result[i].val))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_GBP * (checkRefVal(gv_userPositCd, "영국일당") * parseInt(result[i].val))));
//
//				v_cTotGBP = v_cTotGBP + (checkRefVal(gv_userPositCd, "영국일당") * parseInt(result[i].val));
//
//				var obj = new Object();
//				obj.expnCls = result[i].id; // 구분코드
//				obj.accomoCls = "영국일당"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "영국일당") * parseInt(result[i].val))); // KRW
//				obj.accomoExpnDcnt = result[i].val; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "영국일당"); // 기준액
//				obj.ddExpn = (checkRefVal(gv_userPositCd, "영국일당") * parseInt(result[i].val)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//			}else if(result[i].id == "jaNight"){
//				edtTxtVal = parseInt(result[i].val) + "일 /"
//		  		+ " ￥" + gf_AmtFormat(checkRefVal(gv_userPositCd, "일본숙박") * parseInt(result[i].val))
//		  		+ " / ￦" + gf_AmtFormat(Math.round(v_JPY * (checkRefVal(gv_userPositCd, "일본숙박") * parseInt(result[i].val))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_JPY * (checkRefVal(gv_userPositCd, "일본숙박") * parseInt(result[i].val))));
//
//				v_cTotJPY = v_cTotJPY + (checkRefVal(gv_userPositCd, "일본숙박") * parseInt(result[i].val));
//
//				var obj = new Object();
//				obj.expnCls = result[i].id; // 구분코드
//				obj.accomoCls = "일본숙박"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "일본숙박") * parseInt(result[i].val))); // KRW
//				obj.accomoExpnDcnt = result[i].val; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "일본숙박"); // 기준액
//				obj.ddExpn = (checkRefVal(gv_userPositCd, "일본숙박") * parseInt(result[i].val)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//			}else if(result[i].id == "jaDay"){
//				edtTxtVal = parseInt(result[i].val) + "일 /"
//		  		+ " ￥" + gf_AmtFormat(checkRefVal(gv_userPositCd, "일본일당") * parseInt(result[i].val))
//		  		+ " / ￦" + gf_AmtFormat(Math.round(v_JPY * (checkRefVal(gv_userPositCd, "일본일당") * parseInt(result[i].val))));
//
//				v_cTotalAmt = v_cTotalAmt + (Math.round(v_JPY * (checkRefVal(gv_userPositCd, "일본일당") * parseInt(result[i].val))));
//
//				v_cTotJPY = v_cTotJPY + (checkRefVal(gv_userPositCd, "일본일당") * parseInt(result[i].val));
//
//
//				var obj = new Object();
//				obj.expnCls = result[i].id; // 구분코드
//				obj.accomoCls = "일본일당"; // 구분코드(한글)
//				obj.accomoExpn = Math.round(v_USD * (checkRefVal(gv_userPositCd, "일본일당") * parseInt(result[i].val))); // KRW
//				obj.accomoExpnDcnt = result[i].val; // 사용일수
//				obj.ddExpnDcnt = checkRefVal(gv_userPositCd, "일본일당"); // 기준액
//				obj.ddExpn = (checkRefVal(gv_userPositCd, "일본일당") * parseInt(result[i].val)); // 기준액 * 일수
//
//				ds_ExpnList.add(obj);
//
//			}
//
//			$("#" + result[i].id).text(edtTxtVal);
//		}else{
//			var idTr = "#" + result[i].id + "Tr";
//			$(idTr).hide();
//		}
//	}

	$("#usdTotal").text(v_cTotUSD);
	$("#usdTotalKr").text(gf_AmtFormat(Math.round(v_cTotUSD * v_USD)));

	$("#euroTotal").text(v_cTotEUR);
	$("#euroTotalKr").text(gf_AmtFormat(Math.round(v_cTotEUR * v_EUR)));

	$("#gbpTotal").text(v_cTotGBP);
	$("#gbpTotalKr").text(gf_AmtFormat(Math.round(v_cTotGBP * v_GBP)));

	$("#jaTotal").text(v_cTotJPY);
	$("#jaTotalKr").text(gf_AmtFormat(Math.round(v_cTotJPY * v_JPY)));

	v_TotalPer = v_cTotalAmt;

	//2015-08-31 동행인이 있을 경우 체제비는 동행인 수만큼 배수
	var pTU = $("#tripUserDetail tr").size();

	v_cTotalAmt = v_TotalPer*pTU;

	$("#cTotalAmt").text(gf_AmtFormat(v_cTotalAmt + totalEtcAmt()));



	// 개인지급 합계 (체재비 + 항공료)
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

//	$("#etcTotalUSD").text(gf_AmtFormat(etcTotal));
	$("#etcTotalUSD").text(parseFloat(etcTotal.toFixed(3)));

	// 2015-09-02 계산 방식 변경
	// 기존 : 합계*환율, 변경 : 각각 금액*환율 하여 총합
//	var etcTotalEx = Math.round(etcTotal * v_USD);

	var etcTotalEx = Math.round(v1 * v_USD) + Math.round(v2 * v_USD) + Math.round(v3 * v_USD) + Math.round(v4 * v_USD) + Math.round(v5 * v_USD) + Math.round(v6 * v_USD);
	$("#etcTotalEx").text(gf_AmtFormat(etcTotalEx));

	/**
	 * 기타 경비가 0 이상일 시에 경비 정산 예정일 자동설정
	 * 귀국일 + 3일 뒤로 자동설정 토, 일 일 경우 월요일로 설정
	 */

	if(v_EndDate != undefined && etcTotal > 0){
		var etcPayDate = $.datepicker.parseDate("yymmdd", v_EndDate);
		// 3일 후로 설정
		etcPayDate.setDate(etcPayDate.getDate() + 3);

		//요일을 구한다.
		if(etcPayDate.getDay() == 6){
			// 토요일 이라면 2일 뒤
			etcPayDate.setDate(etcPayDate.getDate() + 2);

		}else if(etcPayDate.getDay() == 0){
			// 일요일 이라면 1일 뒤
			etcPayDate.setDate(etcPayDate.getDate() + 2);
		}

		$("#etcPayDate").val($.datepicker.formatDate("yy-mm-dd", etcPayDate));

	}


	return etcTotalEx;
}

function removeComma(obj){
	for(var i = 0; i < obj.length; i++){
		obj = obj.replace(",", "");
	}
	return obj;
}

// 예산 확인
function cf_CheckOsBizTrip2(){


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

		 tData.userEnm = userEnm.toUpperCase();
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
			startDate : v_StartDate,
			endDate : v_EndDate,
			drafterUserId : v_DraftUserId,
			drafterUserKnm : v_DraftUserKnm,
			drafterUserEnm : v_DraftUserEnm,
			drafterUserOrgCd : v_DraftUserOrgCd,
			drafterUserOrgNm : v_DraftUserOrgNm,
			drafterUserPositCd : v_DraftUserPositCd,
			drafterUserRpswrkCd : v_DraftUserRpswrkCd,
			v_dSignType : v_dSignType, // 타 집행팀 여부
			v_dSignUserCd : v_dSignUserCd, // 타 집행팀장 ORG
			v_dSignUserId : v_dSignUserId, // 타 집행티장 ID
			v_dSignUserNm : v_dSignUserNm,
			v_dSignOrgNm : v_dSignOrgNm, // resultData.ds_coUser[0].userId : ,
			v_dSignRpwrkNm : v_dSignRpwrkNm,
			v_dSignRpswrkCd : v_dSignRpswrkCd,
			v_dSignPositCd : v_dSignPositCd,
			v_dSignPositNm : v_dSignPositNm
	};

	var draftParams = {
			docNo : v_DocNo,
			refNo : "",
			userKnm : v_TripUserKnm,
			userEnm : v_TripUserEnm,
			seatGrade : $("select[name='tripUserGrade']").val(),
			afare : $("#tripUserAirAmount").val(),
			demItm : $("#tReq").val(),
			pportIssueYmd : pportIssueYmd,
			pportExprtnYmd : pportExprtnYmd,
			rem : $("#rem").val(),
			lclAfare : removeComma($("#loAirFareEx").text()),
			lclTrafficExpn : removeComma($("#loTranFareEx").text()),
			visaFee : removeComma($("#visaFeeFareEx").text()),
			excsExpn : removeComma($("#ovCharFareEx").text()),
			userId : v_DraftUserId,
			docSts : 'D50', // 결재전 글로벌HR 검토
			wef : removeComma($("#vocLeeFareEx").text()),
			svcExpn : removeComma($("#hostFareEx").text()),
			ifParam : JSON.stringify(ifParam),
			bustrGl : $("#tPurp").val(),
			pecul : $("#pecul").val()

	};

	var tripUserList = cf_getTripUserTrData();

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

	gf_Transaction("SELECT_OUTER_TRIP_DRAFT", "/trip/outerTrip/updateOuterTripDraft.xpl", draftParams, draftDataSet, "f_Callback", true);
}

function cf_sgnsRemoteDraft(){


//	ORefkeyNo: ".", OPrctr: "3DFUR", OPrctrTxt: "IT운영팀", OAccountNo: "213211167098"

	// 문서 업데이트

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
			userOrgCd : v_DraftUserOrgCd,
			accountNo : v_SapResult.OAccountNo,
			startDate : v_StartDate,
			endDate : v_EndDate,
			tripDate : v_TripDate,
			drafterUserId : v_DraftUserId,
			drafterUserKnm : v_DraftUserKnm,
			drafterUserEnm : v_DraftUserEnm,
			drafterUserOrgCd : v_DraftUserOrgCd,
			drafterUserOrgNm : v_DraftUserOrgNm,
			drafterUserPositCd : v_DraftUserPositCd,
			drafterUserRpswrkCd : v_DraftUserRpswrkCd,
			tripUserId : v_TripUserId,
			tripUserKnm : v_TripUserKnm,
			tripUserEnm : v_TripUserEnm,
			tripUserOrgCd : v_TripUserOrgCd,
			tripUserOrgNm : v_TripUserOrgNm,
			tripUserPositCd : v_TripUserPositCd,
			tripUserRpswrkCd : v_TripUserRpswrkCd,
			ordDate : $.datepicker.formatDate("yymmdd", new Date),
			etcPayDate : $("#etcPayDate").val()
	};

	var draftParams = {
			docNo : v_DocNo,
			refNo : v_SapResult.ORefkeyNo,
			userKnm : v_TripUserKnm,
			userEnm : v_TripUserEnm,
			seatGrade : $("select[name='tripUserGrade']").val(),
			afare : $("#tripUserAirAmount").val(),
			demItm : $("#tReq").val(),
			pportIssueYmd : pportIssueYmd,
			pportExprtnYmd : pportExprtnYmd,
			rem : $("#rem").val(),
			lclAfare : removeComma($("#loAirFare").val()),
			lclTrafficExpn : removeComma($("#loTranFare").val()),
			visaFee : removeComma($("#visaFeeFare").val()),
			excsExpn : removeComma($("#ovCharFare").val()),
			userId : v_DraftUserId,
			wef : removeComma($("#vocLeeFare").val()),
			svcExpn : removeComma($("#hostFare").val()),
			ifParam : JSON.stringify(ifParam),
			bustrGl : $("#tPurp").val(),
			pecul : $("#pecul").val(),
			startDate : v_StartDate,
			endDate : v_EndDate,
			tripDate : v_TripDate

	};

	var tripUserList = cf_getTripUserTrData();

//		!!

	// 초기화 후 추가 출장자 명단을 담는다.
	ds_TripUserList.clear();
	for(var i = 0; i < tripUserList.length; i++){
		ds_TripUserList.add(tripUserList[i]);
	}


	var updateDataSet = {
			ds_TripUserList : ds_TripUserList.getAllData("U"),
			ds_VisaInfoList : ds_VisaInfoList.getAllData("U"),
			ds_CityList : ds_CityList.getAllData("U"),
			ds_ExpnList : ds_ExpnList.getAllData("U"),
			ds_Signln : ds_Signln.getAllData("U")
	};

	gf_Transaction("SELECT_OUTER_TRIP_DRAFT", "/trip/outerTrip/updateOuterTripDraft.xpl", draftParams, updateDataSet, "f_Callback", true);



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

			// 개인지급 합계 (체재비 + 항공료)
			v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;
			$("#totalAmount").text(gf_AmtFormat(v_TotalAmount));

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
	$("#visaInfoDetail tr:last select[name='vVisaYn']").val(obj.visaOwnYn);

	$("#visaInfoDetail tr:last input[name='vStartDate']").val($.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", obj.visaIssueYmd)));
	$("#visaInfoDetail tr:last input[name='vEndDate']").val($.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", obj.visaExprtnYmd)));

	if(obj.visaOwnYn != "Y"){
		$("#visaInfoDetail tr:last input[name='vStartDate']").val("");
		$("#visaInfoDetail tr:last input[name='vEndDate']").val("");
		$("#visaInfoDetail tr:last input[name='vStartDate']").attr("disabled", "true");
		$("#visaInfoDetail tr:last input[name='vEndDate']").attr("disabled", "true");
	}else{
		$("#visaInfoDetail tr:last input[name='vStartDate']").removeAttr("disabled");
		$("#visaInfoDetail tr:last input[name='vEndDate']").removeAttr("disabled");
		$("#visaInfoDetail tr:last input[name='vStartDate']").attr("readonly", "false");
		$("#visaInfoDetail tr:last input[name='vEndDate']").attr("readonly", "false");
	}

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
//	$("input[name='vVisaYn']").unbind("click");
//	$("input[name='vVisaYn']").click(function(e){
//		cf_OpenVisaInfoPop(e);
//	});
	$("select[name='vVisaYn']").bind('change', function(e) {
		var targetId = $(e.target).parent().parent().attr("id");

		if($("#" + targetId + " select[name='vVisaYn']").val() != "Y"){
			$("#" + targetId + " input[name='vStartDate']").val("");
			$("#" + targetId + " input[name='vEndDate']").val("");
			$("#" + targetId + " input[name='vStartDate']").attr("disabled", "true");
			$("#" + targetId + " input[name='vEndDate']").attr("disabled", "true");
		} else {
			$("#" + targetId + " input[name='vStartDate']").removeAttr("disabled");
			$("#" + targetId + " input[name='vEndDate']").removeAttr("disabled");
			$("#" + targetId + " input[name='vStartDate']").attr("readonly", "false");
			$("#" + targetId + " input[name='vEndDate']").attr("readonly", "false");


		}

		// vData1 -- 0번째 DataSet parseInt 하여 -1 하면 Index가 나온다.
		var dId = parseInt(targetId.substr(5, 1))-1;
		var vVal = e.target.value;

		ds_VisaInfoList.set(dId, "visaOwnYn", vVal);



	});

	// event rebind
	$("input[name='vStartDate']").unbind("click");
	$("input[name='vStartDate']").mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
	$("input[name='vStartDate']").bind('change', function(e) {
		var targetId = $(e.target).parent().parent().attr("id");

		// vData1 -- 0번째 DataSet parseInt 하여 -1 하면 Index가 나온다.
		var dId = parseInt(targetId.substr(5, 1))-1;
		var vVal = e.target.value;

		ds_VisaInfoList.set(dId, "visaIssueYmd", $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", vVal)));


	});
//	$("input[name='vStartDate']").click(function(e){
//
//		//				cf_OpenVisaInfoPop(e);
//	});
	// event rebind
	$("input[name='vEndDate']").unbind("click");
	$("input[name='vEndDate']").mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
	$("input[name='vEndDate']").bind('change', function(e) {
		var targetId = $(e.target).parent().parent().attr("id");

		// vData1 -- 0번째 DataSet parseInt 하여 -1 하면 Index가 나온다.
		var dId = parseInt(targetId.substr(5, 1))-1;
		var vVal = e.target.value;

		ds_VisaInfoList.set(dId, "visaExprtnYmd", $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", vVal)));


	});

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
//	$("#cityDetail tr input[name='cityNm']").attr("readonly", "true");
	$("#cityDetail tr input[name='stayStYmd']").attr("readonly", "true");
	$("#cityDetail tr input[name='stayEdYmd']").attr("readonly", "true");
//	$("#cityDetail tr textarea[name='dutyCont']").attr("readonly", "true");

//	// 클릭시 수정 팝업 이벤트 Rebind
//	// event rebind
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

	// 클릭시 수정 팝업 이벤트 Rebind
	// event rebind
	$("input[name='natNm']").unbind("click");
	$("input[name='natNm']").click(function(e){
		cf_OpenCityInfoPop(e);
	});
	// event rebind
	$("input[name='cityNm']").unbind("click");
//	$("input[name='cityNm']").click(function(e){
//		cf_OpenCityInfoPop(e);
//	});

	$("input[name='cityNm']").bind('change', function(e) {
		var targetId = $(e.target).parent().parent().attr("id");

		// vData1 -- 0번째 DataSet parseInt 하여 -1 하면 Index가 나온다.
		var dId = parseInt(targetId.substr(5, 1))-1;
		var cVal = e.target.value;

		ds_CityList.set(dId, "cityNm", cVal);

	});

	// event rebind
	$("input[name='stayStYmd']").mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
//	$("input[name='stayStYmd']").unbind("click");
//	$("input[name='stayStYmd']").click(function(e){
//		cf_OpenCityInfoPop(e);
//	});

	$("input[name='stayStYmd']").bind('change', function(e) {
		var targetId = $(e.target).parent().parent().attr("id");

		// vData1 -- 0번째 DataSet parseInt 하여 -1 하면 Index가 나온다.
		var dId = parseInt(targetId.substr(5, 1))-1;
		var cVal = e.target.value;

		ds_CityList.set(dId, "stayStYmd", $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", cVal)));

		// 출장시작, 종료, 기간 계산
		f_calcTripDate();



	});

	// event rebind
	$("input[name='stayEdYmd']").mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
//	$("input[name='stayEdYmd']").unbind("click");
//	$("input[name='stayEdYmd']").click(function(e){
//		cf_OpenCityInfoPop(e);
//	});

	$("input[name='stayEdYmd']").bind('change', function(e) {
		var targetId = $(e.target).parent().parent().attr("id");

		// vData1 -- 0번째 DataSet parseInt 하여 -1 하면 Index가 나온다.
		var dId = parseInt(targetId.substr(5, 1))-1;
		var cVal = e.target.value;

		ds_CityList.set(dId, "stayEdYmd", $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", cVal)));

		// 출장시작, 종료, 기간 계산
		f_calcTripDate();
	});

	// event rebind
	$("textarea[name='dutyCont']").unbind("click");
//	$("textarea[name='dutyCont']").click(function(e){
//		cf_OpenCityInfoPop(e);
//	});

	$("textarea[name='dutyCont']").bind('change', function(e) {
		var targetId = $(e.target).parent().parent().attr("id");

		// vData1 -- 0번째 DataSet parseInt 하여 -1 하면 Index가 나온다.
		var dId = parseInt(targetId.substr(5, 1))-1;
		var cVal = e.target.value;

		ds_CityList.set(dId, "dutyCont", cVal);
	});

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
//		var idTr = "#" + result[i].expnCls + "Tr";
//		$(idTr).show();
//		//숙박비(＄) 	유럽숙박비(€)	영국숙박비(￡)	일본숙박비(￥)
//
//		if(result[i].expnCls == "usNight"){
//			edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//			  		+ " $" + gf_AmtFormat(checkRefVal(v_TripUserPositCd, "일반숙박") * parseInt(result[i].accomoExpnDcnt))
//			  		+ " / ￦" + gf_AmtFormat(Math.round(v_USD * (checkRefVal(v_TripUserPositCd, "일반숙박") * parseInt(result[i].accomoExpnDcnt))));
//
//			v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (checkRefVal(v_TripUserPositCd, "일반숙박") * parseInt(result[i].accomoExpnDcnt))));
//
//			v_cTotUSD = v_cTotUSD + (checkRefVal(v_TripUserPositCd, "일반숙박") * parseInt(result[i].accomoExpnDcnt));
//
//			var obj = new Object();
//			obj.expnCls = result[i].expnCls; // 구분코드
//			obj.accomoCls = "일반숙박"; // 구분코드(한글)
//			obj.accomoExpn = Math.round(v_USD * (checkRefVal(v_TripUserPositCd, "일반숙박") * parseInt(result[i].accomoExpnDcnt))); // KRW
//			obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//			obj.ddExpnDcnt = checkRefVal(v_TripUserPositCd, "일반숙박"); // 기준액
//			obj.ddExpn = (checkRefVal(v_TripUserPositCd, "일반숙박") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//			ds_ExpnList.add(obj);
//
//
//		}else if(result[i].expnCls == "usDay"){
//			edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//	  		+ " $" + gf_AmtFormat(checkRefVal(v_TripUserPositCd, "일반일당") * parseInt(result[i].accomoExpnDcnt))
//	  		+ " / ￦" + gf_AmtFormat(Math.round(v_USD * (checkRefVal(v_TripUserPositCd, "일반일당") * parseInt(result[i].accomoExpnDcnt))));
//
//			v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (checkRefVal(v_TripUserPositCd, "일반일당") * parseInt(result[i].accomoExpnDcnt))));
//
//			v_cTotUSD = v_cTotUSD + (checkRefVal(v_TripUserPositCd, "일반일당") * parseInt(result[i].accomoExpnDcnt));
//
//			var obj = new Object();
//			obj.expnCls = result[i].expnCls; // 구분코드
//			obj.accomoCls = "일반일당"; // 구분코드(한글)
//			obj.accomoExpn = Math.round(v_USD * (checkRefVal(v_TripUserPositCd, "일반일당") * parseInt(result[i].accomoExpnDcnt))); // KRW
//			obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//			obj.ddExpnDcnt = checkRefVal(v_TripUserPositCd, "일반일당"); // 기준액
//			obj.ddExpn = (checkRefVal(v_TripUserPositCd, "일반일당") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//			ds_ExpnList.add(obj);
//
//
//		}else if(result[i].expnCls == "edu"){
//			edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//	  		+ " $" + gf_AmtFormat(checkRefVal(v_TripUserPositCd, "연수경비(숙)") * parseInt(result[i].accomoExpnDcnt))
//	  		+ " / ￦" + gf_AmtFormat(Math.round(v_USD * (checkRefVal(v_TripUserPositCd, "연수경비(숙)") * parseInt(result[i].accomoExpnDcnt))));
//
//			v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (checkRefVal(v_TripUserPositCd, "연수경비(숙)") * parseInt(result[i].accomoExpnDcnt))));
//
//			v_cTotUSD = v_cTotUSD + (checkRefVal(v_TripUserPositCd, "연수경비(숙)") * parseInt(result[i].accomoExpnDcnt));
//
//			var obj = new Object();
//			obj.expnCls = result[i].expnCls; // 구분코드
//			obj.accomoCls = "연수경비(숙)"; // 구분코드(한글)
//			obj.accomoExpn = Math.round(v_USD * (checkRefVal(v_TripUserPositCd, "연수경비(숙)") * parseInt(result[i].accomoExpnDcnt))); // KRW
//			obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//			obj.ddExpnDcnt = checkRefVal(v_TripUserPositCd, "연수경비(숙)"); // 기준액
//			obj.ddExpn = (checkRefVal(v_TripUserPositCd, "연수경비(숙)") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//			ds_ExpnList.add(obj);
//
//
//		}else if(result[i].expnCls == "spot"){
//			edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//	  		+ " $" + gf_AmtFormat(checkRefVal(v_TripUserPositCd, "연수경비(숙식)") * parseInt(result[i].accomoExpnDcnt))
//	  		+ " / ￦" + gf_AmtFormat(Math.round(v_USD * (checkRefVal(v_TripUserPositCd, "연수경비(숙식)") * parseInt(result[i].accomoExpnDcnt))));
//
//			v_cTotalAmt = v_cTotalAmt + (Math.round(v_USD * (checkRefVal(v_TripUserPositCd, "연수경비(숙식)") * parseInt(result[i].accomoExpnDcnt))));
//
//			v_cTotUSD = v_cTotUSD + (checkRefVal(v_TripUserPositCd, "연수경비(숙식)") * parseInt(result[i].accomoExpnDcnt));
//
//			var obj = new Object();
//			obj.expnCls = result[i].expnCls; // 구분코드
//			obj.accomoCls = "연수경비(숙식)"; // 구분코드(한글)
//			obj.accomoExpn = Math.round(v_USD * (checkRefVal(v_TripUserPositCd, "연수경비(숙식)") * parseInt(result[i].accomoExpnDcnt))); // KRW
//			obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//			obj.ddExpnDcnt = checkRefVal(v_TripUserPositCd, "연수경비(숙식)"); // 기준액
//			obj.ddExpn = (checkRefVal(v_TripUserPositCd, "연수경비(숙식)") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//			ds_ExpnList.add(obj);
//
//
//		}else if(result[i].expnCls == "euNight"){
//			edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//	  		+ " €" + gf_AmtFormat(checkRefVal(v_TripUserPositCd, "유럽숙박") * parseInt(result[i].accomoExpnDcnt))
//	  		+ " / ￦" + gf_AmtFormat(Math.round(v_EUR * (checkRefVal(v_TripUserPositCd, "유럽숙박") * parseInt(result[i].accomoExpnDcnt))));
//
//			v_cTotalAmt = v_cTotalAmt + (Math.round(v_EUR * (checkRefVal(v_TripUserPositCd, "유럽숙박") * parseInt(result[i].accomoExpnDcnt))));
//
//			v_cTotEUR = v_cTotEUR + (checkRefVal(v_TripUserPositCd, "유럽숙박") * parseInt(result[i].accomoExpnDcnt));
//
//			var obj = new Object();
//			obj.expnCls = result[i].expnCls; // 구분코드
//			obj.accomoCls = "유럽숙박"; // 구분코드(한글)
//			obj.accomoExpn = Math.round(v_USD * (checkRefVal(v_TripUserPositCd, "유럽숙박") * parseInt(result[i].accomoExpnDcnt))); // KRW
//			obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//			obj.ddExpnDcnt = checkRefVal(v_TripUserPositCd, "유럽숙박"); // 기준액
//			obj.ddExpn = (checkRefVal(v_TripUserPositCd, "유럽숙박") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//			ds_ExpnList.add(obj);
//
//		}else if(result[i].expnCls == "euDay"){
//			edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//	  		+ " €" + gf_AmtFormat(checkRefVal(v_TripUserPositCd, "유럽일당") * parseInt(result[i].accomoExpnDcnt))
//	  		+ " / ￦" + gf_AmtFormat(Math.round(v_EUR * (checkRefVal(v_TripUserPositCd, "유럽일당") * parseInt(result[i].accomoExpnDcnt))));
//
//			v_cTotalAmt = v_cTotalAmt + (Math.round(v_EUR * (checkRefVal(v_TripUserPositCd, "유럽일당") * parseInt(result[i].accomoExpnDcnt))));
//
//			v_cTotEUR = v_cTotEUR + (checkRefVal(v_TripUserPositCd, "유럽일당") * parseInt(result[i].accomoExpnDcnt));
//
//			var obj = new Object();
//			obj.expnCls = result[i].expnCls; // 구분코드
//			obj.accomoCls = "유럽일당"; // 구분코드(한글)
//			obj.accomoExpn = Math.round(v_USD * (checkRefVal(v_TripUserPositCd, "유럽일당") * parseInt(result[i].accomoExpnDcnt))); // KRW
//			obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//			obj.ddExpnDcnt = checkRefVal(v_TripUserPositCd, "유럽일당"); // 기준액
//			obj.ddExpn = (checkRefVal(v_TripUserPositCd, "유럽일당") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//			ds_ExpnList.add(obj);
//
//		}else if(result[i].expnCls == "enNight"){
//			edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//	  		+ " ￡" + gf_AmtFormat(checkRefVal(v_TripUserPositCd, "영국숙박") * parseInt(result[i].accomoExpnDcnt))
//	  		+ " / ￦" + gf_AmtFormat(Math.round(v_GBP * (checkRefVal(v_TripUserPositCd, "영국숙박") * parseInt(result[i].accomoExpnDcnt))));
//
//			v_cTotalAmt = v_cTotalAmt + (Math.round(v_GBP * (checkRefVal(v_TripUserPositCd, "영국숙박") * parseInt(result[i].accomoExpnDcnt))));
//
//			v_cTotGBP = v_cTotGBP + (checkRefVal(v_TripUserPositCd, "영국숙박") * parseInt(result[i].accomoExpnDcnt));
//
//			var obj = new Object();
//			obj.expnCls = result[i].expnCls; // 구분코드
//			obj.accomoCls = "영국숙박"; // 구분코드(한글)
//			obj.accomoExpn = Math.round(v_USD * (checkRefVal(v_TripUserPositCd, "영국숙박") * parseInt(result[i].accomoExpnDcnt))); // KRW
//			obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//			obj.ddExpnDcnt = checkRefVal(v_TripUserPositCd, "영국숙박"); // 기준액
//			obj.ddExpn = (checkRefVal(v_TripUserPositCd, "영국숙박") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//			ds_ExpnList.add(obj);
//
//		}else if(result[i].expnCls == "enDay"){
//			edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//	  		+ " ￡" + gf_AmtFormat(checkRefVal(v_TripUserPositCd, "영국일당") * parseInt(result[i].accomoExpnDcnt))
//	  		+ " / ￦" + gf_AmtFormat(Math.round(v_GBP * (checkRefVal(v_TripUserPositCd, "영국일당") * parseInt(result[i].accomoExpnDcnt))));
//
//			v_cTotalAmt = v_cTotalAmt + (Math.round(v_GBP * (checkRefVal(v_TripUserPositCd, "영국일당") * parseInt(result[i].accomoExpnDcnt))));
//
//			v_cTotGBP = v_cTotGBP + (checkRefVal(v_TripUserPositCd, "영국일당") * parseInt(result[i].accomoExpnDcnt));
//
//			var obj = new Object();
//			obj.expnCls = result[i].expnCls; // 구분코드
//			obj.accomoCls = "영국일당"; // 구분코드(한글)
//			obj.accomoExpn = Math.round(v_USD * (checkRefVal(v_TripUserPositCd, "영국일당") * parseInt(result[i].accomoExpnDcnt))); // KRW
//			obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//			obj.ddExpnDcnt = checkRefVal(v_TripUserPositCd, "영국일당"); // 기준액
//			obj.ddExpn = (checkRefVal(v_TripUserPositCd, "영국일당") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//			ds_ExpnList.add(obj);
//
//		}else if(result[i].expnCls == "jaNight"){
//			edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//	  		+ " ￥" + gf_AmtFormat(checkRefVal(v_TripUserPositCd, "일본숙박") * parseInt(result[i].accomoExpnDcnt))
//	  		+ " / ￦" + gf_AmtFormat(Math.round(v_JPY * (checkRefVal(v_TripUserPositCd, "일본숙박") * parseInt(result[i].accomoExpnDcnt))));
//
//			v_cTotalAmt = v_cTotalAmt + (Math.round(v_JPY * (checkRefVal(v_TripUserPositCd, "일본숙박") * parseInt(result[i].accomoExpnDcnt))));
//
//			v_cTotJPY = v_cTotJPY + (checkRefVal(v_TripUserPositCd, "일본숙박") * parseInt(result[i].accomoExpnDcnt));
//
//			var obj = new Object();
//			obj.expnCls = result[i].expnCls; // 구분코드
//			obj.accomoCls = "일본숙박"; // 구분코드(한글)
//			obj.accomoExpn = Math.round(v_USD * (checkRefVal(v_TripUserPositCd, "일본숙박") * parseInt(result[i].accomoExpnDcnt))); // KRW
//			obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//			obj.ddExpnDcnt = checkRefVal(v_TripUserPositCd, "일본숙박"); // 기준액
//			obj.ddExpn = (checkRefVal(v_TripUserPositCd, "일본숙박") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//			ds_ExpnList.add(obj);
//
//		}else if(result[i].expnCls == "jaDay"){
//			edtTxtVal = parseInt(result[i].accomoExpnDcnt) + "일 /"
//	  		+ " ￥" + gf_AmtFormat(checkRefVal(v_TripUserPositCd, "일본일당") * parseInt(result[i].accomoExpnDcnt))
//	  		+ " / ￦" + gf_AmtFormat(Math.round(v_JPY * (checkRefVal(v_TripUserPositCd, "일본일당") * parseInt(result[i].accomoExpnDcnt))));
//
//			v_cTotalAmt = v_cTotalAmt + (Math.round(v_JPY * (checkRefVal(v_TripUserPositCd, "일본일당") * parseInt(result[i].accomoExpnDcnt))));
//
//			v_cTotJPY = v_cTotJPY + (checkRefVal(v_TripUserPositCd, "일본일당") * parseInt(result[i].accomoExpnDcnt));
//
//
//			var obj = new Object();
//			obj.expnCls = result[i].expnCls; // 구분코드
//			obj.accomoCls = "일본일당"; // 구분코드(한글)
//			obj.accomoExpn = Math.round(v_USD * (checkRefVal(v_TripUserPositCd, "일본일당") * parseInt(result[i].accomoExpnDcnt))); // KRW
//			obj.accomoExpnDcnt = result[i].accomoExpnDcnt; // 사용일수
//			obj.ddExpnDcnt = checkRefVal(v_TripUserPositCd, "일본일당"); // 기준액
//			obj.ddExpn = (checkRefVal(v_TripUserPositCd, "일본일당") * parseInt(result[i].accomoExpnDcnt)); // 기준액 * 일수
//
//			ds_ExpnList.add(obj);
//		}
//
//		$("#" + result[i].expnCls).text(edtTxtVal);
//
//}

	$("#usdTotal").text(v_cTotUSD);
	$("#usdTotalKr").text(gf_AmtFormat(Math.round(v_cTotUSD * v_USD)));

	$("#euroTotal").text(v_cTotEUR);
	$("#euroTotalKr").text(gf_AmtFormat(Math.round(v_cTotEUR * v_EUR)));

	$("#gbpTotal").text(v_cTotGBP);
	$("#gbpTotalKr").text(gf_AmtFormat(Math.round(v_cTotGBP * v_GBP)));

	$("#jaTotal").text(v_cTotJPY);
	$("#jaTotalKr").text(Math.round(v_cTotJPY * v_JPY));

	$("#cTotalAmt").text(gf_AmtFormat(v_cTotalAmt + totalEtcAmt()));

	v_TotalPer = v_cTotalAmt;

	//2015-08-31 동행인이 있을 경우 체제비는 동행인 수만큼 배수
	var pTU = $("#tripUserDetail tr").size();

	v_cTotalAmt = v_TotalPer*pTU;

	$("#cTotalAmt").text(gf_AmtFormat(v_cTotalAmt + totalEtcAmt()));


	// 개인지급 합계 (체재비 + 항공료)
	v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;
	$("#totalAmount").text(gf_AmtFormat(v_TotalAmount));

}



//저장 및 협조의뢰 시 호출하는 화면 (gf_FileUpload의 CallBack)
function cf_saveOuterDocSave(docSts){

	if(f_docPatterChk() == false) return;

	// 클릭 못하도록 버튼 숨김
	$(".btn").hide();


	// 상신시 Modal처리
	gf_InitSpinner(top.$('body'));

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
	var tripUserEnm = $("#tripUserEnm").val();
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
	//
	var secComment = $("#secComment").val();
	var ghrComment = $("#ghrComment").val();

	var confirmText = "";

	var isDraftDoc = "";

	// 메일 전송을 위한 플래그 Y 일경우만 메일을 전송한다.
	var mailYn = "N";
	var ds_Mail = new DataSet();
	var mailParams;



	// hostcheck > 0 이면 협의결재선 추가로 넣기. 이 부분을 적정한 위치에 넣기. 그리고 나중에 같은 결재라인에 이미 협조가 들어가 있지 않았을 경우에만..임.(2019.03.15)
	hostCheck = parseInt(f_notSpace(removeComma($("#hostFare").val()))) + parseInt(f_notSpace(removeComma($("#vocLeeFare").val())));

	if(hostCheck > 0 ){

		var params = {
		  		orgCd : v_TripUserOrgCd,
		  		orgCls : orgCls,
		  		userId : v_TripUserId
			};
		//경영관리팀 협의결재자(ds_Cmas004),해외출장 협의결재자(ds_Cmas006), sync mode
		gf_Transaction("SELECT_COSIGN_LINE", "/trip/outerTrip/retrieveCoSignList.xpl", params, {}, "f_Callback", false);

		//경영관리팀 담당자가 결재선에 있는지 && 협의결재인지
		if( ds_Signln.find("signUserId",ds_Cmas004.get(0,"signUserId")) > -1 &&
				ds_Signln.get(ds_Signln.find("signUserId",ds_Cmas004.get(0,"signUserId")),"signTpCd")== "T03"){
				//alert("결재선 중복");
		}
		else { //협의결재를 넣고 나머지 User를 shift함. 협의결재중 가장 빠른 사람이니 userSeq는 2
			for(var i=0; i<ds_Signln.size(); i++){		// 전체 결재선 루프
				var beforeSignSeq = ds_Signln.get(i, "signSeq");
				if(beforeSignSeq >= 2){
					ds_Signln.set(i, "signSeq", parseInt(ds_Signln.get(i, "signSeq")) + 1);
				}
			}
			ds_Signln.insert(1, {
				signId : "",
				signSeq : 2,
				signTpCd : "T03",
				signUserId : ds_Cmas004.get(0,"signUserId"),
				signUserNm : ds_Cmas004.get(0,"signUserNm"),
				psignUserNm : "",
				apperPositCd : ds_Cmas004.get(0,"apperPositCd"),
				apperPositNm : ds_Cmas004.get(0,"apperPositNm"),
				perpsignPositNm : "",
				apperRpswrkCd : ds_Cmas004.get(0,"apperRpswrkCd"),
				apperRpswrkNm : ds_Cmas004.get(0,"apperRpswrkNm"),
				apperOrgCd : ds_Cmas004.get(0,"apperOrgCd"),
				apperOrgNm : ds_Cmas004.get(0,"apperOrgNm"),
				orgChrcCls : "D",
				canDelete : "N" // 결재선 삭제 불가능
			});		//insert
		}		//else
	}	//end of hostcheck

	if(docSts == "D16"){		//임시저장
		confirmText = "작성 중인 문서를 저장하시겠습니까?";

		//SEC, GHR 저장 추가
		if(v_DocSts == "D50"){
			docSts = "D50";
		}else if(v_DocSts == "D60"){
			docSts = "D60";
		}

	}else if(docSts == "D50"){	//해외 SEC 검토
		confirmText = "협조의뢰하시겠습니까?";
		alert("해외출장시 소지여권의 잔여 유효기간(6개월 이상)과 훼손여부, 여유사증(4페이지 이상)과 방문예정국가의 VISA의 소지유무등을 확인하시기 바랍니다.");
		v_secYn = "Y";
		isDraftDoc = "Y";

		if(v_DocSts != "D50"){
			// SEC 담당자 임시저장이 아닐 경우는 협조의뢰 이므로 메일을 전송한다.
			// 메일 전송
			mailYn = "Y";

			var natNm = "";

			//목적지 전부 나오도록 처리 (2019.03.27)
			if(ds_CityList.size() > 0){
				for(var i=0;i<ds_CityList.size()-1;i++){		//맨 마지막은 한국이라서 삭제
					natNm = natNm + ", "+ds_NatList.get(ds_NatList.find("natCd", ds_CityList.get(i, "natCd")), "natNm");
				}
				//맨 처음 " ," 삭제 처리.
				natNm = natNm.substring(2);
			}

			for(var i = 0; i < v_CmasList.length; i++){
				if(v_CmasList[i].privCd == "RO_CMAS_OT_003"){		//01:GHR ROLE, 03:SECURITY ROLE, 06:협의결재용 ROLE
//					alert(v_CmasList[i].userKnm);

					mailParams = {
							spec : "",
							bodyTemplate : "",
							fromMailId : "cmastripadmin@daewooenc.com",
							fromMailName : "출장시스템관리자",
							mailId : v_CmasList[i].userId + "@daewooenc.com", // 운영시에는 담당자로 변경할 것
							mailSubject : "[해외출장신청서](출장자: " + v_TripUserKnm + " " + v_TripUserId + ")협조를 의뢰합니다." ,
							htmlBody : "■ 작성자 : " + v_DraftUserKnm + " " + v_DraftUserPositCd + "<br>"
										+ "■ 출장자 : " + v_TripUserKnm + " " + v_TripUserPositCd + "<br>"
										+ "■ 문서상태 : SEC 검토요청중" + "<br>"
										+ "■ 목적지 : " + natNm + "<br>"
										+ "■ 출장기간 : " + v_StartDate + " ~ " + v_EndDate
					};

					// mailParams 삽입
					ds_Mail.add(mailParams);

				}
			}
		}

	}else if(docSts == "D60"){			//GHR 검토(체제비 및 항공)
		v_ghrYn = "Y";
		if(v_DocSts == "D50"){
			confirmText = "문서를 검토완료 하시겠습니까?";
			var dt = new Date();
			var month = dt.getMonth()+1;
			if(dt.getMonth()+1 < 10) month = "0" + (dt.getMonth()+1);
			var dayM = dt.getDate();
			if(dt.getDate() < 10) dayM = "0" + (dt.getDate());

			assistComment = assistComment + "##" + dt.getFullYear() + "-" + month + "-" + dayM + " " + gv_userId + " " + gv_userNm + " " + gv_orgNm + " 협조완료";
		}else{
			confirmText = "협조의뢰하시겠습니까?";
			alert("해외출장시 소지여권의 잔여 유효기간(6개월 이상)과 훼손여부, 여유사증(4페이지 이상)과 방문예정국가의 VISA의 소지유무등을 확인하시기 바랍니다.");
			isDraftDoc = "Y";
		}
	}else if(docSts == "D70"){
		confirmText = "문서를 검토완료 하시겠습니까?";

		var dt = new Date();
		var month = dt.getMonth()+1;
		if(dt.getMonth()+1 < 10) month = "0" + (dt.getMonth()+1);
		var dayM = dt.getDate();
		if(dt.getDate() < 10) dayM = "0" + (dt.getDate());

		for(var i = 0; i < assistComment.length; i++){
			assistComment = assistComment.replace("\n", "##");
		}
		assistComment = assistComment + "##" + dt.getFullYear() + "-" + month + "-" + dayM + " " + gv_userId + " " + gv_userNm + " " + gv_orgNm + " 협조완료";
	}

	var fileAtch = gf_IsNull(v_FileAtchId) ? ""          : v_FileAtchId;

	var oDate = $("#oDate").val();

	var innType = $("input[name='innType']:checked").val();

	// 저장 시작
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
				tripReq : tripReq.replace(/%/gi,"%25"),
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
				airReq : airReq.replace(/%/gi,"%25"),
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
				rtcReq : rtcReq.replace(/%/gi,"%25"),
				assistComment : assistComment,
				v_dSignType : v_dSignType, // 타 집행팀 여부
				v_dSignUserCd : v_dSignUserCd, // 타 집행팀장 ORG
				v_dSignUserId : v_dSignUserId, // 타 집행티장 ID
				v_dSignUserNm : v_dSignUserNm,
				v_dSignOrgNm : v_dSignOrgNm, // resultData.ds_coUser[0].userId : ,
				v_dSignRpwrkNm : v_dSignRpwrkNm,
				v_dSignRpswrkCd : v_dSignRpswrkCd,
				v_dSignPositCd : v_dSignPositCd,
				v_dSignPositNm : v_dSignPositNm,
				fileAtchId : fileAtch,
				secYn : v_secYn,
//				secComment : secComment,
				ghrYn : v_ghrYn,
//				ghrComment : ghrComment,
				vTcode : $("#vTcode").val(),
				oDate : oDate,
				startDate : v_StartDate,
				endDate : v_EndDate,
				innType : innType
		};

		var exFlag = "N";

		if(ds_ExRList.get(0, "StYmd") != v_ExStYmd){
			if(ds_ExRList.get(0, "StYmd") != undefined){
				exFlag = "Y";
			}
		}


		var param = {
				docNo : v_DocNo,
				docSts : docSts,
				secComment : secComment,
				ghrComment : ghrComment,
				ifParam : JSON.stringify(data),
				mailYn : mailYn,
				userId : tripUserId,
				exFlag : exFlag,
				exStYmd : ds_ExRList.get(0, "StYmd")
		};

		var tripUserList = cf_getTripUserTrData();



		for(var i = 0; i < tripUserList.length; i++){
			ds_TripUserList.add(tripUserList[i]);
		}

		var draftDataSet = {
				ds_TripUserList : ds_TripUserList.getAllData("U"),
				ds_VisaInfoList : ds_VisaInfoList.getAllData("U"),
				ds_CityList : ds_CityList.getAllData("U"),
				ds_ExpnList : ds_ExpnList.getAllData("U"),
				ds_Sign : ds_Signln.getAllData("I"),
				input1 : ds_Mail.getAllData()
		};

		if(isDraftDoc == "Y"){

			if(v_DocSts == "D60"){
				gf_Transaction("SAVE_OUTER_TRIP_DOC_SAVE", "/trip/outerTrip/updateOuterTripDocSave.xpl", param, draftDataSet, "f_Callback", true);
			}else if(v_DocSts == "D50"){
				gf_Transaction("SAVE_OUTER_TRIP_DOC_SAVE", "/trip/outerTrip/updateOuterTripDocSave.xpl", param, draftDataSet, "f_Callback", true);
			}else{	//메일은 '첫 협조요청 프로세스에서만 적용되야 함'
				mailNotice();
				gf_Transaction("SAVE_OUTER_TRIP_DOC_SAVE", "/trip/outerTrip/updateOuterTripDocSave2.xpl", param, draftDataSet, "f_Callback", true);
			}
		}else{
			gf_Transaction("SAVE_OUTER_TRIP_DOC_SAVE", "/trip/outerTrip/updateOuterTripDocSave.xpl", param, draftDataSet, "f_Callback", true);
		}
	}else{
		//버튼 재생성
		v_isSaveEnable = "N";

		if(v_DocSts == ""){
			$("#outerTripSave").show();
			$("#outerTripDraft").show();
		}else if(v_DocSts == "D16"){
			$("#outerTripDelete").show();
			$("#outerTripSave").show();
			$("#outerTripDraft").show();
		}else if(v_DocSts == "D50"){
			$("#outerTripSECNotConfirm").show();
			$("#outerTripSECConfirm").show();
		}else if(v_DocSts == "D60"){
			$("#outerTripHRNotConfirm").show();
			$("#outerTripHRConfirm").show();
			$("#outerTripSave").show();
		}else if(v_DocSts == "D70"){
			$("#outerTripMNGConfirm").show();
			$("#outerTripMNGNotConfirm").show();
		}

		$(".ajax_overlay").remove();

	}

}

//해외출장 신청서 결재선 조회
function cf_retrieveSignLine(){


	// 상신시 Modal처리
	gf_InitSpinner(top.$('body'));

	// 출장자 정보
//	v_TripUserId;
//	v_TripUserOrgCd;
//	v_TripUserPositCd;

	//접대비 + 복리후생 = 1400 이상 사장 전결
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

			//경영관리팀 협의결재자(ds_Cmas004),해외출장 협의결재자(ds_Cmas006), sync call. no callback func
			gf_Transaction("SELECT_COSIGN_LINE", "/trip/outerTrip/retrieveCoSignList.xpl", params, {}, "f_Callback", true);
			//전결규정 결재선 조회
			gf_Transaction("SELECT_SIGN_LINE", "/co/common/user/retrieveSignlnForExcluRegl.xpl", params, {}, "f_Callback", true);

	}else{
		// 본부장 이상 직책일 체크
		// ds_HqRpswrkCd :: /common/js/comm/globalvariable.js 파일 내 본부장 이상 직책이 추가될 경우 대상 추가.
		// 2019.03.25 CFO 추가.
		if(ds_HqRpswrkCd.find("value", v_TripUserRpswrkCd) != -1){
			orgCls = "회장";
			//alert(v_TripUserRpswrkCd);
			/* 해외출장이 본부장 전결일 경우 수정해야 할 부분
			switch(v_TripUserRpswrkCd){
				case "그룹회장":
				case "부문부회장":
				case "대표이사사장":
				case "총괄사장":
				case "총괄부사장":
				case "사장":
					orgCls = "회장";
					break;
				case "수석부사장":
				case "해외영업총괄":
				case "부문장":
					orgCls = "총괄";
					break;
				default :
					orgCls = "본부/실";
					break;
			}
			*/
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

		//경영관리팀 협의결재자(ds_Cmas004),해외출장 협의결재자(ds_Cmas006), sync call. no callback func
		gf_Transaction("SELECT_COSIGN_LINE", "/trip/outerTrip/retrieveCoSignList.xpl", params, {}, "f_Callback", true);

		//전결규정 결재선 (true는 async, false는 sync)
		gf_Transaction("SELECT_SIGN_LINE", "/co/common/user/retrieveSignlnForExcluRegl.xpl", params, {}, "f_Callback", true);

	}

}

// 결재라인 세팅 (2019.03.13 update)
function cf_setSignlnInfo(resultData) {

	ds_Signln.clear();

	var cnt = 0;
	//기안자, 코드는 순서대로 기안/결재/협의/기안자전결/전결/협의
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
	//타 집행팀 협의 (집행팀장 != 출장자팀)
	if(v_dSignType == "Y" && v_dSignUserCd != v_TripUserOrgCd){
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
	}	//end of 타 집행팀 협의 (집행팀장 != 출장자팀)

	//타 집행팀 협의

/*	2019.03. 초도요청 항목이었으나 메일 기능만 넣는 것으로 변경되어 미사용 하는 코드
	//작성자..가 아니고 출장자 기준으로
	if(v_TripUserRpswrkCd =="본부장" || v_TripUserRpswrkCd =="실장"||
		v_TripUserRpswrkCd =="단장" || v_TripUserRpswrkCd =="CFO"||
		v_TripUserRpswrkCd =="대표이사사장"){

	}
	else { //협의결재 있음
		//해외출장협의결재(글로벌지원팀장 : CMAS_OT_006 ROLE
		cnt++;
		ds_Signln.insert(cnt, {
			signId : "",
			signSeq : cnt + 1,
			signTpCd : "T03",
			signUserId : ds_Cmas006.get(0,"signUserId"),
			signUserNm : ds_Cmas006.get(0,"signUserNm"),
			psignUserNm : "",
			apperPositCd : ds_Cmas006.get(0,"apperPositCd"),
			apperPositNm : ds_Cmas006.get(0,"apperPositNm"),
			perpsignPositNm : "",
			apperRpswrkCd : ds_Cmas006.get(0,"apperRpswrkCd"),
			apperRpswrkNm : ds_Cmas006.get(0,"apperRpswrkNm"),
			apperOrgCd : ds_Cmas006.get(0,"apperOrgCd"),
			apperOrgNm : ds_Cmas006.get(0,"apperOrgNm"),
			orgChrcCls : "D",
			canDelete : "N" // 결재선 삭제 불가능
		});

	}
	//end of GHR팀장 추가 루틴
*/
	//경영관리팀 협의 여부는 상신 시 validation check 부분에서.

	if(resultData.ds_SignlnForExcluRegl == undefined){

		// 전결상 가져오는 사람이 없어 undefined 일 경우 처리

	}else{
		for ( var i = 0; i < resultData.ds_SignlnForExcluRegl.length; i++) {

			// 기안자와 ID가 같다면 결재라인에서 빠진다.
			var temp = resultData.ds_SignlnForExcluRegl[i];

			if(v_DraftUserId != temp.signUserId){		//기안자가 아닌 결재자들
				/**
				 * 사장전결의 경우 수석부사장은 결재라인에서 빠짐
				 */
				if(orgCls == "회장" && temp.apperPositCd == "수석부사장"){ // 수석부사장 이 결재라인에서 빠지게 될 경우 조건을 삭제

				}else if(temp.signUserId == v_TripUserId){
					/**
					 * 출장자 본인이 결재선에 잡혀있을 경우에도 결재빠짐
					 */
				}else{
					//사장의 부재로 수정한 모듈
					//if(v_bossSignYn == "Y" && temp.apperPositCd == "사장"){
					if(v_bossSignYn == "Y" && ( temp.apperPositCd == "CEO" || temp.apperPositCd == "사장" ) ){ // 사장 또는 CEO 일 경우 처리 된다.
						cnt++;

						// 접대비, 복리후생 1400 이상 사장 결재 삭제 불가능
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
								orgChrcCls : "D",
								canDelete : "N" // 결재선 삭제 불가능
						});

					}else{	//안 빠지는 케이스들은 정상처리
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

					} // end of 안빠지는 케이스

				} //end of else
			} // end of 기안자가 아닌 결재자들


		} // end of 전결 for loop

		// 동일한 인물이 결재선에 중복될 경우 협의결재를 삭제

		for ( var i = 0; i < ds_Signln.size(); i++){
			for ( var j = 0; j < i; j++){
				if(ds_Signln.get(i,"signUserId") == ds_Signln.get(j,"signUserId")){
					if(ds_Signln.get(i,"signTpCd")== "T03"){ //협의결재
						ds_Signln.remove(i);
						break;
					}
					if(ds_Signln.get(j,"signTpCd")== "T03"){ //협의결재
						ds_Signln.remove(j);
						break;
					}
				}
			}
		}
	}

	gf_AssembleSignln(ds_Signln);

	$(".ajax_overlay").remove();

}

function fn_SetUploadCallback( fileAtchId ) {

	v_FileAtchId = fileAtchId;

	// 저장이면 예산체크 하지 않음(오류방지)
	if(v_DraftSts == "D16"){
		cf_saveOuterDocSave(v_DraftSts);
	}else{
		// 예산 체크 후 상태 변경
		cf_checkOsBizTrip();
	}

}

function f_calcTripDate(){

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

	if(v_StartDate != undefined && v_EndDate != undefined){
		var startDate = $.datepicker.parseDate("yymmdd" , v_StartDate);
		var endDate = $.datepicker.parseDate("yymmdd" , v_EndDate);

		v_TripDate = (endDate - startDate)/1000/60/60/24;

		var betDayText = v_TripDate + "박 " + (v_TripDate+1) + "일";

//		$("#tripDate").text(betDayText);

		var tripDate = $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", v_StartDate)) + " ~ " + $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", v_EndDate)) + " (기간 : " + (parseInt(v_TripDate)+1) + "일)";
		$("#tripDate").text(tripDate);
	}

	totalEtcAmt();

}

// 해외출장 전표 생성
function cf_submitOsBizTrip(){

	// 경비구분 확인
	if(v_BdgtType == ""){
		gf_AlertMsg("경비 구분이 선택되지 않았습니다.");
		return;
	}

	// 경비구분 확인
	if(ds_CityList.size() < 1){
		gf_AlertMsg("방문지를 입력해 주세요.");
		return;
	}

	// 경비구분 확인
	if(ds_VisaInfoList.size() < 1){
		gf_AlertMsg("비자정보를 입력해 주세요.");
		return;
	}

	/***********************************************************************************
	 * SAP Parameter Init Start
	 */


	// 클릭 못하도록 버튼 숨김
	$(".btn").hide();

	// 상신시 Modal처리
	gf_InitSpinner(top.$('body'));

	var IBdgtType = v_BdgtType.substring(0,1); // 경비구분
	var IDocType = "H"; // 전표구분
	var INotesdoc = v_DocNo; // 문서번호

	if(v_BdgtType == "C"){
		v_Aufnr = "";
	}
	var IBdgtNo = v_Aufnr; // 예산번호

	// 현장경비/PJ경비 예산의 경우는 예산번호를 입력하지 않는다.
	if(v_BdgtType == "Q" || v_BdgtType == "Q1" || v_BdgtType == "Q2" || v_BdgtType == "Q3" || v_BdgtType == "Q4" || v_BdgtType == "Q5"){
		IBdgtNo = "";
	}

	var IBdgtTeam = v_Kostv; // 집행팀
	var IPostTeam = ""; // 경비이체코드
	if(v_BdgtType == "I"){
		// I. 일반경비 일경우 경비이체 코드를 전달
		IPostTeam = $("#vTcode").val();
	}
	var ISenderId = v_DraftUserId; // 작성자사번
	var ISendDate =  $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", $("#oDate").val())); // 증빙일
	var ITravId = v_TripUserId; // 출장자사번
	var ITravTeam = v_TripUserOrgCd; // 출장자팀
	var ITravFdate = v_StartDate; // 출장시작일
	var ITravTdate = v_EndDate; // 출장종료일

	var ITravArea = ds_CityList.get(0).natCd; // 출장지역

	// 현장경비/PJ경비의 경우 sap의 적요에 목적+경비구분을 넣는다.
	var tmpIBdgtType = "";

	if(v_BdgtType == "Q"){
		tmpIBdgtType = "현장경비";
	}else if(v_BdgtType == "Q1"){
		tmpIBdgtType = "PJ-현장경비";
	}else if(v_BdgtType == "Q2"){
		tmpIBdgtType = "PJ-PM경비";
	}else if(v_BdgtType == "Q3"){
		tmpIBdgtType = "PJ-PPM경비";
	}else if(v_BdgtType == "Q4"){
		tmpIBdgtType = "PJ-EM경비";
	}else if(v_BdgtType == "Q5"){
		tmpIBdgtType = "PJ-PCM경비";
	}else{
	}

	//sap적요 : 해외출장목적+경비구분
	var ITravPurp ="";

	if(v_BdgtType == "Q" || v_BdgtType == "Q1" || v_BdgtType == "Q2" || v_BdgtType == "Q3" || v_BdgtType == "Q4" || v_BdgtType == "Q5"){
		ITravPurp = "(해출"+tmpIBdgtType+")" + $("#tPurp").val(); // 출장목적
	}else{
		ITravPurp = "(해출)" + $("#tPurp").val(); // 출장목적
	}

	var IPaymDate = ""; // 지불예정일
	var IEmpNm = $("#tripUserEnm").val(); // 출장직원영문이름
	var ISeatClass = $("select[name='tripUserGrade']").val(); // 좌석클래스
	var IAirFare = removeComma($("#tripUserAirAmount").val()); // 항공료
	var IDayAmt = v_cTotalAmt; // 체재비

	// KRW 기준으로 계산
	var loAirFare = removeComma($("#loAirFareEx").text());
	var loTranFare = removeComma($("#loTranFareEx").text());
//		$("#loTranFare").val();
	var visaFeeFare = removeComma($("#visaFeeFareEx").text());
//		$("#visaFeeFare").val();
	var ovCharFare = removeComma($("#ovCharFareEx").text());
//		$("#ovCharFare").val();
	var vocLeeFare = removeComma($("#vocLeeFareEx").text());
//		$("#vocLeeFare").val();

	if(loAirFare == "") loAirFare = 0;
	if(loTranFare == "") loTranFare = 0;
	if(visaFeeFare == "") visaFeeFare = 0;
	if(ovCharFare == "") ovCharFare = 0;
	if(vocLeeFare == "") vocLeeFare = 0;

	// 기타비용(현지항공료+현지교통비+VISA FEE+Over charge)
	var IGitaAmt = parseInt(loAirFare) + parseInt(loTranFare) + parseInt(visaFeeFare) + parseInt(ovCharFare);

	var IGitaAmt2 = vocLeeFare;
//		$("#vocLeeFare").val(); // 기타비용(복리후생비2)

	var hostFare = removeComma($("#hostFareEx").text());
//		$("#hostFare").val();
	if(hostFare == "") hostFare = 0;
	var IGitaAmt3 = hostFare; // 기타비용(접대비)

	var etcPayDate = $("#etcPayDate").val();
	if(etcPayDate != ""){
		etcPayDate = $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", etcPayDate));
	}
	var IGitaSettle = etcPayDate; // 기타비용 정산예정일


	var trUser = cf_getTripUserTrData();

	var IGuestNm1 = ""; // 동반인1 이름
	var ISeatClass1 = ""; // 동반인1 좌석클래스
	var IAirFare1 = ""; // 동반인1 항공료

	if(trUser[0] != undefined){
		IGuestNm1 = trUser[0].userEnm; // 동반인1 이름
		ISeatClass1 = trUser[0].seatGrade; // 동반인1 좌석클래스
		IAirFare1 = trUser[0].afare; // 동반인1 항공료
	}

	var IGuestNm2 = ""; // 동반인1 이름
	var ISeatClass2 = ""; // 동반인1 좌석클래스
	var IAirFare2 = ""; // 동반인1 항공료

	if(trUser[1] != undefined){
		IGuestNm2 = trUser[1].userEnm; // 동반인1 이름
		ISeatClass2 = trUser[1].seatGrade; // 동반인1 좌석클래스
		IAirFare2 = trUser[1].afare; // 동반인1 항공료
	}

	var IGuestNm3 = ""; // 동반인1 이름
	var ISeatClass3 = ""; // 동반인1 좌석클래스
	var IAirFare3 = ""; // 동반인1 항공료

	if(trUser[2] != undefined){
		IGuestNm3 = trUser[2].userEnm; // 동반인1 이름
		ISeatClass3 = trUser[2].seatGrade; // 동반인1 좌석클래스
		IAirFare3 = trUser[2].afare; // 동반인1 항공료
	}

	var IRutLifnr = ""; // 임원 대체수취 거래처
	var IRutValue = ""; // 임원 대체수취 거래처 지급액

	var IPosid = ""; // 작업 분석 구조 요소(WBS 요소)
	if(v_BdgtType == "R"){
		// R 인 경우 예산번호
		IPosid = v_Aufnr;
	}else{
		IPosid = "";
	}

	// 현장 예산의 경우는 예산번호를 입력하지 않는다.(2015.12.03 홍종석과장 협의하에 삭제)
	/*if(v_BdgtType == "Q"){
		IPosid = v_Kostv;
	}
	*/

	var IBukrs = "1000";


	/******************************************************************
	 * SAP Parameter Init End
	 */
	/******************************************************************
	 * DOC Parameter Init Start
	 */
	var pportIssueYmd = $("#fPassp").val().split("-")[0] + $("#fPassp").val().split("-")[1] + $("#fPassp").val().split("-")[2];
	var pportExprtnYmd = $("#tPassp").val().split("-")[0] + $("#tPassp").val().split("-")[1] + $("#tPassp").val().split("-")[2];



	/******************************************************************
	 * DOC Parameter Init End
	 */
	/******************************************************************
	 * SGNS Parameter Init Start
	 */
	var dutySysCd = "SGNS"; // DUTYSYSCD
		var programCode = "SGNS070003"; // 양식코드
		var signDocTitle = "해외출장신청서"; // 양식 제목

		var ordDate = $("#oDate").val(); // 증빙일자
		var ordNo = ""; // 전표번호

		var docNo = v_DocNo; // CMAS 문서번호
		var drafterId = v_DraftUserId + " " + v_DraftUserKnm; // 작성자 ID
		var drafter = drafterId;
		var drafterOrg = v_DraftUserOrgCd + " " + v_DraftUserOrgNm; // 작성자 ORG
		var drafterOrgNm = drafterOrg;
		var tripUser = v_TripUserId + " " + v_TripUserKnm;
		var tripUserEnm = v_TripUserEnm;
		var tripUserGrade = $("select[name='tripUserGrade'] option:selected").text();
		var tripUserAirAmount = $("#tripUserAirAmount").val();
		var tripDate = $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", v_StartDate)) + " ~ " + $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", v_EndDate)) + " " + v_TripDate + "일";
		var tPurp = $("#tPurp").val();
		var tReq = $("#tReq").val();
		var fPassp = $("#fPassp").val();
		var tPassp = $("#tPassp").val();

		var bdgtType = $("#bdgtType").text();
		var aufnr = v_Aufnr + " / 내역 : " + v_KText;
		var cGubun = $("#cGubun option:selected").text();
		var bdgtTeam = v_Kostvnm + " " + v_Kostv;
		var airTotalAmount = $("#airTotalAmount").text();
		var pecul = $("#pecul").val();

		var loAirFare = "$ " + $("#loAirFare").val() + " / \ " + $("#loAirFareEx").text();
		var loTranFare = "$ " + $("#loTranFare").val() + " / \ " + $("#loTranFareEx").text();
		var visaFeeFare = "$ " + $("#visaFeeFare").val() + " / \ " + $("#visaFeeFareEx").text();
		var ovCharFare = "$ " + $("#ovCharFare").val() + " / \ " + $("#ovCharFareEx").text();
		var vocLeeFare = "$ " + $("#vocLeeFare").val() + " / \ " + $("#vocLeeFareEx").text();
		var hostFare = "$ " + $("#hostFare").val() + " / \ " + $("#hostFareEx").text();
		var etcTotal = "$ " + $("#etcTotal").val() + " / \ " + $("#etcTotalEx").text();

		var usNight = $("#usNight").text();
		var usDay = $("#usDay").text();
		var edu = $("#edu").text();
		var spot = $("#spot").text();
		var euNight = $("#euNight").text();
		var euDay = $("#euDay").text();
		var enNight = $("#enNight").text();
		var enDay = $("#enDay").text();
		var jaNight = $("#jaNight").text();
		var jaDay = $("#jaDay").text();

		var expnTotal = "";

		var cTotalAmt = $("#cTotalAmt").text();
		var totalAmount = $("#totalAmount").text();
		var rem = $("#rem").val();
		var assistComment = $("#assistComment").val();

		//경영관리 일경우 협조완료 메세지
		if(v_DocSts == "D70" || v_DocSts == "D60"){
			var dt = new Date();
			var month = dt.getMonth()+1;
			if(dt.getMonth()+1 < 10) month = "0" + (dt.getMonth()+1);
			var dayM = dt.getDate();
			if(dt.getDate() < 10) dayM = "0" + (dt.getDate());

			for(var i = 0; i < assistComment.length; i++){
				assistComment = assistComment.replace("\n", "##");
			}
			assistComment = assistComment + "##" + dt.getFullYear() + "-" + month + "-" + dayM + " " + gv_userId + " " + gv_userNm + " " + gv_orgNm + " 협조완료";
		}

		var mSignType = "N";
		if(v_DraftSts == "D70"){
			mSignType = "Y";
		}

		var innType = $("input[name='innType']:checked").val();


	/******************************************************************
	 * SGNS Parameter Init End
	 */

		var params = {
				// 예산체크 params
				ISendDateC : $.datepicker.formatDate("yymmdd", new Date()),
				AirAmt : v_AirTotalAmt,
				StayAmt : v_cTotalAmt,
				EtcLairAmt : removeComma($("#loAirFareEx").text()),
				EtcLtrAmt : removeComma($("#loTranFareEx").text()),
				EtcVisaAmt : removeComma($("#visaFeeFareEx").text()),
				EtcOverAmt : removeComma($("#ovCharFareEx").text()),
				EtcWelAmt : removeComma($("#vocLeeFareEx").text()),
				EtcRcAmt : removeComma($("#hostFareEx").text()),
				IRefkey : "",
				// sap params
				IBdgtType : IBdgtType, // 경비구분
				IDocType : IDocType, // 전표구분
				INotesdoc : INotesdoc, // 문서번호
				IBdgtNo : IBdgtNo, // 예산버호
				IBdgtTeam : IBdgtTeam, // 집행팀
				IPostTeam : IPostTeam, // 경비이체코드
				ISenderId : ISenderId, // 작성자사번
				ISendDate : ISendDate, // 증빙일
				ITravId : ITravId, // 출장자사번
				ITravTeam : ITravTeam, // 출장자팀
				ITravFdate : ITravFdate, // 출장시작일
				ITravTdate : ITravTdate, // 출장종료일
				ITravArea : ITravArea, // 출장지역
				ITravPurp : ITravPurp, // 출장목적
				IPaymDate : IPaymDate, // 지불예정일
				IEmpNm : IEmpNm, // 출장직원영문이름
				ISeatClass : ISeatClass, // 좌석클래스
				IAirFare : IAirFare, // 항공료
				IDayAmt : IDayAmt, // 체재비
				IGitaAmt : IGitaAmt, // 기타비용(현지항공료+현지교통비+VISA FEE+Over charge)
				IGitaAmt2 : IGitaAmt2, // 기타비용(복리후생비2)
				IGitaAmt3 : IGitaAmt3, // 기타비용(접대비)
				IGitaSettle : IGitaSettle, // 기타비용 정산예정일
				IGuestNm1 : IGuestNm1, // 동반인1 이름
				ISeatClass1 : ISeatClass1, // 동반인1 좌석클래스
				IAirFare1 : IAirFare1, // 동반인1 항공료
				IGuestNm2 : IGuestNm2, // 동반인1 이름
				ISeatClass2 : ISeatClass2, // 동반인1 좌석클래스
				IAirFare2 : IAirFare2, // 동반인1 항공료
				IGuestNm3 : IGuestNm3, // 동반인1 이름
				ISeatClass3 : ISeatClass3, // 동반인1 좌석클래스
				IAirFare3 : IAirFare3, // 동반인1 항공료
				IRutLifnr : "", // 임원 대체수취 거래처
				IRutValue : "", // 임원 대체수취 거래처 지급액
				IPosid : IPosid, // 작업 분석 구조 요소(WBS 요소)
				IBukrs : "1000",
				// doc params
				docNo : v_DocNo,
				userKnm : v_TripUserKnm,
				userEnm : v_TripUserEnm,
				seatGrade : $("select[name='tripUserGrade']").val(),
				afare : removeComma($("#tripUserAirAmount").val()),
				demItm : $("#tReq").val(),
				pportIssueYmd : pportIssueYmd,
				pportExprtnYmd : pportExprtnYmd,
				rem : $("#rem").val(),
				lclAfare : removeComma($("#loAirFare").val()),
				lclTrafficExpn : removeComma($("#loTranFare").val()),
				visaFee : removeComma($("#visaFeeFare").val()),
				excsExpn : removeComma($("#ovCharFare").val()),
				userId : v_TripUserId,
				wef : removeComma($("#vocLeeFare").val()),
				svcExpn : removeComma($("#hostFare").val()),
				bustrGl : $("#tPurp").val(),
				pecul : $("#pecul").val(),
				startDate : v_StartDate,
				endDate : v_EndDate,
				tripDate : v_TripDate,
				// sgns params
				dutySysCd : dutySysCd,
				programCode : programCode,
				signDocTitle : signDocTitle,
				ordDate : ordDate,
				ordNo : ordNo,
				adjustYn : "N", // N 신청서 / Y 정산서
		  		drafterId : drafterId,
		  		drafter : drafter,
		  		drafterOrg : drafterOrg,
		  		drafterOrgNm : drafterOrgNm,
		  		tripUser : tripUser,
		  		tripUserId : tripUser,
		  		tripUserEnm : tripUserEnm,
		  		tripUserGrade : tripUserGrade,
		  		tripUserAirAmount : tripUserAirAmount,
		  		tripDate : tripDate,
		  		tPurp : tPurp,
		  		tReq : tReq,
		  		fPassp : fPassp,
		  		tPassp : tPassp,
		  		bdgtType : bdgtType,
		  		aufnr : aufnr,
		  		cGubun : cGubun,
		  		bdgtTeam : bdgtTeam,
		  		airTotalAmount : airTotalAmount,
		  		loAirFare : loAirFare,
		  		loTranFare : loTranFare,
		  		visaFeeFare : visaFeeFare,
		  		ovCharFare : ovCharFare,
		  		vocLeeFare : vocLeeFare,
		  		hostFare : hostFare,
		  		etcTotal : etcTotal,
		  		usNight : usNight,
		  		usDay : usDay,
		  		edu : edu,
		  		spot : spot,
		  		euNight : euNight,
		  		euDay : euDay,
		  		enNight : enNight,
		  		enDay : enDay,
		  		jaNight : jaNight,
		  		jaDay : jaDay,
		  		expnTotal : expnTotal,
		  		cTotalAmt : cTotalAmt,
		  		totalAmount : totalAmount,
		  		rem : rem,
		  		assistComment : assistComment,
		  		//ifParam
		  		bdgtType : v_BdgtType,
  				aufnr : v_Aufnr,
  				kText : v_KText,
  				kostV : v_Kostv,
  				kostVNm : v_Kostvnm,
  				chief : v_Chief,
  				chiefNm : v_Chiefnm,
  				cGugun : $("#cGubun").val(),
  				userOrgCd : v_DraftUserOrgCd,
//  				startDate : v_StartDate,
//  				endDate : v_EndDate,
  				iTripDate : v_TripDate,
  				drafterUserId : v_DraftUserId,
  				drafterUserKnm : v_DraftUserKnm,
  				drafterUserEnm : v_DraftUserEnm,
  				drafterUserOrgCd : v_DraftUserOrgCd,
  				drafterUserOrgNm : v_DraftUserOrgNm,
  				drafterUserPositCd : v_DraftUserPositCd,
  				drafterUserRpswrkCd : v_DraftUserRpswrkCd,
  				tripUserId : v_TripUserId,
  				tripUserKnm : v_TripUserKnm,
//  				tripUserEnm : v_TripUserEnm,
  				tripUserOrgCd : v_TripUserOrgCd,
  				tripUserOrgNm : v_TripUserOrgNm,
  				tripUserPositCd : v_TripUserPositCd,
  				tripUserRpswrkCd : v_TripUserRpswrkCd,
  				ordDate : $.datepicker.formatDate("yymmdd", new Date),
  				etcPayDate : $("#etcPayDate").val(),
  				fileAtchId : v_FileAtchId,
  				mSignType : mSignType,
  				usd : v_USD,
				eur : v_EUR,
				gbp : v_GBP,
				jpy : v_JPY,
				//2015-09-17 집행세부내역
				tAirAmtDtl : $("#loAirFareDtl").val(),
				tTranspAmtDtl : $("#loTranFareDtl").val(),
				visaFeeDtl : $("#visaFeeFareDtl").val(),
				overChargeDtl : $("#ovCharFareDtl").val(),
				benefitsDtl : $("#vocLeeFareDtl").val(),
				hostAmtDtl : $("#hostFareDtl").val(),
				vTcode : $("#vTcode").val(),
				oDate : $("#oDate").val(),
				secComment : $("#secComment").val(),
				ghrComment : $("#ghrComment").val(),
				innType : innType

		};


	var tripUserList = cf_getTripUserTrData();

	// 초기화 후 추가 출장자 명단을 담는다.
	ds_TripUserList.clear();
	for(var i = 0; i < tripUserList.length; i++){
		ds_TripUserList.add(tripUserList[i]);
	}

	var updateDataSet = {
			ds_TripUserList : ds_TripUserList.getAllData("U"),
			ds_VisaInfoList : ds_VisaInfoList.getAllData("U"),
			ds_CityList : ds_CityList.getAllData("U"),
			ds_ExpnList : ds_ExpnList.getAllData("U"),
			ds_Signln : ds_Signln.getAllData()
	};


	gf_Transaction("SELECT_SEND_BIZ_TRIP", "/trip/eai/submitOsBizTrip.xpl", params, updateDataSet, "f_Callback", true);

}

function cf_checkOsBizTrip(){

	var IBdgtType = v_BdgtType.substring(0,1); // 경비구분
	var IBdgtNo = v_Aufnr; // 예산번호

	// 현장경비/PJ경비 예산의 경우는 예산번호를 입력하지 않는다.
	if(v_BdgtType == "Q" || v_BdgtType == "Q1" || v_BdgtType == "Q2" || v_BdgtType == "Q3" || v_BdgtType == "Q4" || v_BdgtType == "Q5"){
		IBdgtNo = "";
	}

	var IBdgtTeam = v_Kostv; // 집행팀
	var IRefkey = "";  // 신청서 Ref No 공백처리

	var params = {
			// 예산체크 params
			IBdgtType : IBdgtType,   // 경비구분
			IBdgtNo : IBdgtNo,       // 예산번호
			IBdgtTeam : IBdgtTeam,   // 집행팀
			ISendDateC : $.datepicker.formatDate("yymmdd", new Date()),
			AirAmt : v_AirTotalAmt,
			StayAmt : v_cTotalAmt,
			EtcLairAmt : removeComma($("#loAirFareEx").text()),
			EtcLtrAmt : removeComma($("#loTranFareEx").text()),
			EtcVisaAmt : removeComma($("#visaFeeFareEx").text()),
			EtcOverAmt : removeComma($("#ovCharFareEx").text()),
			EtcWelAmt : removeComma($("#vocLeeFareEx").text()),
			EtcRcAmt : removeComma($("#hostFareEx").text()),
			IRefkey : IRefkey

	}

	gf_Transaction("SELECT_CHECK_BIZ_TRIP", "/trip/eai/submitOsBizTripTest.xpl", params, {}, "f_Callback", true);

}


function cf_checkOsBizTripTest(){

	// 예산번호 선택 확인
	if(v_BdgtType == ""){
		gf_AlertMsg("[예산번호]를 선택해야 예산체크를 할 수 있습니다");
		return;
	}

	var IBdgtType = v_BdgtType.substring(0,1); // 경비구분
	var IBdgtNo   = v_Aufnr;      // 예산번호

	// 현장경비/PJ경비 예산의 경우는 예산번호를 입력하지 않는다.
	if(v_BdgtType == "Q" || v_BdgtType == "Q1" || v_BdgtType == "Q2" || v_BdgtType == "Q3" || v_BdgtType == "Q4" || v_BdgtType == "Q5"){
		IBdgtNo = "";
	}

	// 현장경비/PJ경비의 경우 sap의 적요에 목적+경비구분을 넣는다.
	var tmpIBdgtType = "";

	if(v_BdgtType == "Q"){
		tmpIBdgtType = "현장경비";
	}else if(v_BdgtType == "Q1"){
		tmpIBdgtType = "PJ-현장경비";
	}else if(v_BdgtType == "Q2"){
		tmpIBdgtType = "PJ-PM경비";
	}else if(v_BdgtType == "Q3"){
		tmpIBdgtType = "PJ-PPM경비";
	}else if(v_BdgtType == "Q4"){
		tmpIBdgtType = "PJ-EM경비";
	}else if(v_BdgtType == "Q5"){
		tmpIBdgtType = "PJ-PCM경비";
	}else{
	}

	//sap적요 : 해외출장목적+경비구분
	//var ITravPurp = $("#tPurp").val();
	//var ITravPurp1 = "(해출:"+tmpIBdgtType+")" + $("#tPurp").val(); // 출장목적

	var ITravPurp ="";
	if(v_BdgtType == "Q" || v_BdgtType == "Q1" || v_BdgtType == "Q2" || v_BdgtType == "Q3" || v_BdgtType == "Q4" || v_BdgtType == "Q5"){
		ITravPurp = "(해출:"+tmpIBdgtType+")" + $("#tPurp").val(); // 출장목적
	}else{
		ITravPurp = "(해출)" + $("#tPurp").val(); // 출장목적
	}

	//alert("목적 : "+ITravPurp.length);
	//alert("추가목적 : "+ITravPurp1.length);

	//alert(ITravPurp);

	var IBdgtTeam = v_Kostv; // 집행팀
	var IRefkey = "";        // 신청서 Ref No 공백처리

	var params = {
			// 예산체크 params
			IBdgtType : IBdgtType, // 경비구분
			IBdgtNo : IBdgtNo,     // 예산번호
			IBdgtTeam : IBdgtTeam, // 집행팀
			ISendDateC : $.datepicker.formatDate("yymmdd", new Date()),
			AirAmt : v_AirTotalAmt,
			StayAmt : v_cTotalAmt,
			EtcLairAmt : removeComma($("#loAirFareEx").text()),
			EtcLtrAmt : removeComma($("#loTranFareEx").text()),
			EtcVisaAmt : removeComma($("#visaFeeFareEx").text()),
			EtcOverAmt : removeComma($("#ovCharFareEx").text()),
			EtcWelAmt : removeComma($("#vocLeeFareEx").text()),
			EtcRcAmt : removeComma($("#hostFareEx").text()),
			IRefkey : IRefkey
	}

	gf_Transaction("SELECT_CHECK_BIZ_TRIP_TEST", "/trip/eai/submitOsBizTripTest.xpl", params, {}, "f_Callback", true);

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

	// 개인지급 합계 (체재비 + 항공료)
	v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;

	$("#totalAmount").text(gf_AmtFormat(v_TotalAmount));

}

//SAP 에 등록된 집행팀의 ORG_CD 와 CO 에 등록된 ORG_CD 가 틀리기 때문에 같은 조직이 맞는지
//집행팀장의 ID 로 검증한다.
function cf_getCouserInfo(){

//	if(v_tSignUserCd != undefined && v_Chief != undefined){
	if(v_Chief != undefined){

		// 집행팀팀장ID
//		v_Chief

		// 예산타입이 현장경비라면 현장관리책임자도 가져온다.
		var coParams = {
				userId : v_Chief,
				bdgtType : v_BdgtType
		};


		gf_Transaction("SELECT_CO_USER_INFO", "/trip/innerTrip/retrieveCoUserInfo.xpl", coParams, {}, "f_Callback", true);
//	}else if(v_Chief == undefined){
//		gf_AlertMsg("집행팀이 입력되지 않았습니다.");
	}else if(v_tSignUserCd == undefined){
//		gf_AlertMsg("출장자가 입력되지 않았습니다.");
	}else{
		//console.log("====== 타집행팀 여부 검증안됨");
	}

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

function f_notSpace(val){
	if(val == "" || val == undefined) val = 0;
	return val;
}

/**
 * 복리후생과 접대비 1400 이상 결재선 변경,
 * 복리후생과 접대비 0보다 클 경우 경영관리팀 협의결재(
 */
function f_bossSignProcess(){
	//접대비 + 복리후생 = 1400 이상 사장 전결
	hostCheck = parseInt(f_notSpace(removeComma($("#hostFare").val()))) + parseInt(f_notSpace(removeComma($("#vocLeeFare").val())));

	if(hostCheck > 1400){
		if(v_bossSignYn == "N"){
			if(confirm("복리후생과 접대비의 합계가 $1400 이상의 경우 사장 전결이 필요합니다. 진행하시겠습니까?")){
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

				// 전결규정 기반 결재선 조회
				gf_Transaction("SELECT_SIGN_LINE", "/co/common/user/retrieveSignlnForExcluRegl.xpl", params, {}, "f_Callback", true);
			}else{
				var exVal = 0;

				v_Benefits = exVal;
				v_HostAmt = exVal;

				$("#hostFare").val(exVal);
				$("#vocLeeFare").val(exVal);

				$("#hostFareEx").text(gf_AmtFormat(exVal));
				$("#vocLeeFareEx").text(gf_AmtFormat(exVal));

				// 개인지급 합계 (체재비 + 항공료)
				v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;
				$("#totalAmount").text(gf_AmtFormat(v_TotalAmount));
			}
		}
	}else if(hostCheck < 1400){
		if(v_bossSignYn == "Y"){
			alert("복리후생과 접대비의 합계가 $1400 이하의 경우 전결규정에 따른 결재선으로 원상 복구 합니다.");
			v_bossSignYn = "N";
			cf_retrieveSignLine();
		}
	}
}


function f_rejectDoc(){

	// 반려 코멘트 처리
	var ipDatas = JSON.parse(ds_SavedDoc.get(0, "ifParam"));

	var assistComment = ipDatas.assistComment;

	var dt = new Date();
	var month = dt.getMonth()+1;
	if(dt.getMonth()+1 < 10) month = "0" + (dt.getMonth()+1);
	var dayM = dt.getDate();
	if(dt.getDate() < 10) dayM = "0" + (dt.getDate());

	assistComment = assistComment + "##" + dt.getFullYear() + "-" + month + "-" + dayM + " " + gv_userId + " " + gv_userNm + " " + gv_orgNm + " 반려";


	ipDatas.assistComment = assistComment;

	//메일전송
	// 메일 전송을 위한 플래그 Y 일경우만 메일을 전송한다.
	var mailYn = "N";
	var ds_Mail = new DataSet();
	var mailParams;

	var docSts = "D16";	//임시저장

	if(v_DocSts == "D60"){ //GHR 검토

		mailYn = "Y";

		var natNm = "";

		if(ds_CityList.size() > 0){
			for(var i=0;i<ds_CityList.size()-1;i++){		//맨 마지막은 한국이라서 삭제
				natNm = natNm + ", "+ds_NatList.get(ds_NatList.find("natCd", ds_CityList.get(i, "natCd")), "natNm");
			}
			//맨 처음 " ," 삭제 처리.
			natNm = natNm.substring(2);
		}

		mailParams = {
				spec : "",
				bodyTemplate : "",
				fromMailId : "cmastripadmin@daewooenc.com",	//GHR 해외출장 담당자도 별도의 변수로 뺄 것(변경가능)
				fromMailName : "출장시스템관리자",
				mailId : v_TripUserId + "@daewooenc.com", // 운영시에는 담당자로 변경할 것
				mailSubject : "[해외출장신청서](출장자: " + v_TripUserKnm + " " + v_TripUserId + ") 출장신청서 반려되었습니다." ,
				htmlBody : "■ 작성자 : " + v_DraftUserKnm + " " + v_DraftUserPositCd + "<br>"
							+ "■ 출장자 : " + v_TripUserKnm + " " + v_TripUserPositCd + "<br>"
							+ "■ 문서상태 : 반려" + "<br>"
							+ "■ 반려사유 : " + v_RejectRes + "<br>"
							+ "■ 목적지 : " + natNm + "<br>"
							+ "■ 출장기간 : " + v_StartDate + " ~ " + v_EndDate
		};

		// mailParams 삽입
		ds_Mail.add(mailParams);


		//GHR 반려일 경우 메일전송
//		제목 : [해외출장신청서] (출장자 : 사번 이름) 출장신청서 반려되었습니다.
//		내용 : 반려사유 확인 후 수정하여 협조의뢰 해주시기 바랍니다.
//		 ■ 작성자 : 홍길동 과장
//		 ■ 출장자 : 홍길동 과장
//		 ■ 문서상태 : 반려
//		 ■ 반려사유: 재작성하세요
//		 ■ 목적지 : 나이지리아
//		 ■ 출장기간 : 20150611~20150614

		docSts = "D64";

	 }else if(v_DocSts == "D50"){	//SEC 검토


			mailYn = "Y";

			var natNm = "";

			//목적지 전부 나오도록 처리(2019.03.27)
			if(ds_CityList.size() > 0){
				for(var i=0;i<ds_CityList.size()-1;i++){		//맨 마지막은 한국이라서 삭제
					natNm = natNm + ", "+ds_NatList.get(ds_NatList.find("natCd", ds_CityList.get(i, "natCd")), "natNm");
				}
				//맨 처음 " ," 삭제 처리.
				natNm = natNm.substring(2);
			}

			mailParams = {
					spec : "",
					bodyTemplate : "",
					fromMailId : "cmastripadmin@daewooenc.com",		  //SEC 담당자도 변수로 뺄 것
					fromMailName : "출장시스템관리자",
					mailId : v_TripUserId + "@daewooenc.com", // 운영시에는 담당자로 변경할 것
					mailSubject : "[해외출장신청서](출장자: " + v_TripUserKnm + " " + v_TripUserId + ") 출장신청서 반려되었습니다." ,
					htmlBody : "■ 작성자 : " + v_DraftUserKnm + " " + v_DraftUserPositCd + "<br>"
								+ "■ 출장자 : " + v_TripUserKnm + " " + v_TripUserPositCd + "<br>"
								+ "■ 문서상태 : 반려" + "<br>"
								+ "■ 반려사유 : " + v_RejectRes + "<br>"
								+ "■ 목적지 : " + natNm + "<br>"
								+ "■ 출장기간 : " + v_StartDate + " ~ " + v_EndDate
			};

			// mailParams 삽입
			ds_Mail.add(mailParams);

			//GHR 반려일 경우 메일전송
//			제목 : [해외출장신청서] (출장자 : 사번 이름) 출장신청서 반려되었습니다.
//			내용 : 반려사유 확인 후 수정하여 협조의뢰 해주시기 바랍니다.
//			 ■ 작성자 : 홍길동 과장
//			 ■ 출장자 : 홍길동 과장
//			 ■ 문서상태 : 반려
//			 ■ 반려사유: 재작성하세요
//			 ■ 목적지 : 나이지리아
//			 ■ 출장기간 : 20150611~20150614

			docSts = "D54";

		}

	var params = {
		docNo : v_DocNo,
		docStsCd : docSts,
		secComment : $("#secComment").val(),
		ghrComment : $("#ghrComment").val(),
		ifParam : JSON.stringify(ipDatas),
		mailYn : mailYn,
		rejectRes : v_RejectRes
	};

	var draftDataSet = {
			input1 : ds_Mail.getAllData()
	};

	//반려후
	gf_Transaction("SAVE_REJECT_DOC", "/trip/outerTrip/updateOuterTripInfo.xpl", params, draftDataSet, "f_Callback", true);
}

function cf_getRiskYnByNatCd(natCd){
	return ds_NatList.get(ds_NatList.find("natCd", natCd), "riskYn");
}


function f_deleteDoc(){
	var params = {
			docNo : v_DocNo
	};

	gf_Transaction("SAVE_DELETE_DOC", "/trip/outerTrip/deleteOuterTripDoc.xpl", params, {}, "f_Callback", true);

}

function f_visaYn(natCd){
	return ds_NatList.get(ds_NatList.find("natCd", natCd), "visaYn");
}


function f_callBackFuncRejectPop(obj){
//	alert("반려사유 : " + obj.rejectRes);

	v_RejectRes = obj.rejectRes;

	f_rejectDoc();

}

/**
* @class 결재선 데이터 갱신
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
	}

	gf_AssembleSignln(ds_Signln);

}

function f_setDocReadOnly(){
	// 수정불가 처리
	$("input").attr("readonly", "readonly");
	$("textarea").attr("readonly", "readonly");
	$("select").attr("disabled", "disabled");

	//hide
	$("#tripUser_btn").hide();
	$("#tripUserAddBtn").hide();
	$("#tripUserDelBtn").hide();
	$("#visaAddBtn").hide();
	$("#visaDelBtn").hide();
	$("#bdgtBtn").hide();
	$("#inputAmtTr").hide();
	$("#cityAddBtn").hide();
	$("#cityDelBtn").hide();

	// event rebind
	$("input[name='vNat']").unbind("click");
	// event rebind
	$("input[name='vVisaYn']").unbind("click");
	// event rebind
	$("input[name='vStartDate']").unbind("click");
	// event rebind
	$("input[name='vEndDate']").unbind("click");
	// event rebind
	$("input[name='natNm']").unbind("click");
	// event rebind
	$("input[name='cityNm']").unbind("click");
	// event rebind
	$("input[name='stayStYmd']").unbind("click");
	// event rebind
	$("input[name='stayEdYmd']").unbind("click");
	// event rebind
	$("textarea[name='dutyCont']").unbind("click");

	//datepicker 해제
	$("#fPassp").datepicker("destroy");
	$("#tPassp").datepicker("destroy");
	$("#etcPayDate").datepicker("destroy");
	$("#oDate").datepicker("destroy");


	$("#fPassp").unmask();
	$("#tPassp").unmask();
	$("#etcPayDate").unmask();
	$("#oDate").unmask();

}


function cf_RetrieveSignInfo(){

	//해당 업무구분과 업무분류에 해당되는 양식명을 가져옴
	var signParams = {
			docNo : v_DocNo
	};

	gf_Transaction("SELECT_SIGN_INFO", "/trip/outerTrip/retrieveSignInfo2.xpl", signParams, {}, "f_Callback", true);
}

function f_patternChk(txt){
	var special_pattern = /[`!@#$%^&|\\\'\";\?]/gi;
	if( special_pattern.test(txt) == true ){
	    return false;
	}else{
		return true;
	}

}

function f_docPatterChk(){
	for(var i = 0; i < $("input").size(); i++){
		if(f_patternChk($("input")[i].value) == false){

			var id = $("input")[i].id;
			if(id == ""){
				id = $("input")[i].name;
			}

			if(id == "tripUserEnm"){
				id = "출장자 영문이름";
			}else if(id == "tUserEnm"){
				id = "동행인 영문이름";
			}else if(id == "tAirAmount"){
				id = "동행인 항공료";
			}else if(id == "tPurp"){
				id = "출장목적";
			}else if(id == "cityNm"){
				id = "방문지 도시명";
			}else if(id == "vTcode"){
				id = "경비이체코드";
			}else if(id == "vTcode"){
				id = "경비이체코드";
			}else if(id == "loAirFareDtl"){
				id = "집행세부내역 현지항공료";
			}else if(id == "loTranFareDtl"){
				id = "집행세부내역 현지교통비";
			}else if(id == "visaFeeFareDtl"){
				id = "집행세부내역 VISA FEE";
			}else if(id == "ovCharFareDtl"){
				id = "집행세부내역 Over Charge";
			}else if(id == "vocLeeFareDtl"){
				id = "집행세부내역 복리후생";
			}else if(id == "hostFareDtl"){
				id = "집행세부내역 접대비";
			}

			gf_AlertMsg(id + " 란에 특수문자를 입력하실 수 없습니다.");
			return false;
		}
	}

	for(var i = 0; i < $("textarea").size(); i++){
		if(f_patternChk($("textarea")[i].value) == false){

			var id = $("textarea")[i].id;
			if(id == ""){
				id = $("textarea")[i].name;
			}

			if(id == "tReq"){
				id = "요청사항";
			}else if(id == "dutyCont"){
				id = "주요업무내용";
			}else if(id == "pecul"){
				id = "특이사항";
			}else if(id == "rem"){
				id = "비고";
			}

			gf_AlertMsg(id + " 란에 특수문자를 입력하실 수 없습니다.");
			return false;
		}
	}
	return true;
}

function mailTest(){

	var ds_Mail = new DataSet();

	var params = {
			spec : "",
			bodyTemplate : "",
			fromMailId : "cmastripadmin@daewooenc.com",
			fromMailName : "출장시스템관리자",
			mailId : "1501776" + "@daewooenc.com",
			mailSubject : "메일테스트",
			htmlBody : "<span>MAIL TEST</span>"
	};

	ds_Mail.add(params);

	var dataSet = {
			input1 : ds_Mail.getAllData()
	};

	gf_Transaction("SELECT_MAIL", "/co/common/mail/sendMail.xpl", {}, dataSet, "f_Callback", true);
}

function mailNotice(){

	// Mail로 Notice.

	var ds_Mail = new DataSet();

	//HR에서 PC-OFF 메시지 추가 요청 (수신자 : 출장자, 모든 출장 건에 대하여 공지)
	var params2 = {
			spec : "",
			bodyTemplate : "",
			fromMailId : "cmastripadmin@daewooenc.com",
			fromMailName : "출장시스템관리자",
			mailId : v_TripUserId + "@daewooenc.com", // 수신자: 출장자
//			mailId : "1501776@daewooenc.com", //
			mailSubject : "해외 출장 시 PC-OFF 및 CAD 관련  유의 사항" ,
			htmlBody :    "1.PC-OFF<br><br>"
						+ " - 해외출장 신청서 결재완료 후 승인된 출장기간 동안 PC-OFF제는 예외적용 처리됩니다.<br><br>"
						+ " - 상기 내용 반영을 위해 출장기간 중 최초 1회 가상접속시스템(VPN) https://vpn.daewooenc.com을 통해 사내망에 접속하시고,PC를 재부팅해주시기 바랍니다.단, PC-OFF제 예외적용 처리는 결재완료 후 익일부터 반영됩니다. (당일신청 당일적용 불가)<br><br>"
						+ " - 해외출장 신청서 상 사전승인되지 않은 기간은 예외적용 처리되지 않습니다. 따라서 출장기간이 연장되는 경우, 해당 기간만큼 개인 e-HR 시스템을 통해 해외출장 근태신청을 해주셔야 예외적용이 추가 반영됩니다.<br><br>"
						+ " ※ 문의 : 인사팀 과장 김용호(5612), 사원 문진희(5702) <br><br><br>"
						+ "2. AUTO-CAD<br><br>"
						+ " - 해외에서 CAD 프로그램 사용 예정 시 IT기획팀 김기철 대리에게 사전문의 바랍니다.<br><br>"
		};

	ds_Mail.add(params2);



	if(v_DraftUserId == v_TripUserId) {
		//기안자 = 출장자일 경우, 기안자는 메일을 받을 필요 없음 (중복제거.. 19.04.12. by g.KIM)

	}
	else{
		//HR에서 PC-OFF 메시지 추가 요청 (수신자 : 출장기안자, 모든 출장 건에 대하여 공지)
		var params3 = {
				spec : "",
				bodyTemplate : "",
				fromMailId : "cmastripadmin@daewooenc.com",
				fromMailName : "출장시스템관리자",
				mailId : v_DraftUserId + "@daewooenc.com", // 수신자 : 출장기안자
	//			mailId : "1501776@daewooenc.com", // GHR 팀장님. OT_006 ROLE
				mailSubject : "해외 출장 시 PC-OFF 및 CAD 관련  유의 사항" ,
				htmlBody :    "1.PC-OFF<br><br>"
							+ " - 해외출장 신청서 결재완료 후 승인된 출장기간 동안 PC-OFF제는 예외적용 처리됩니다.<br><br>"
							+ " - 상기 내용 반영을 위해 출장기간 중 최초 1회 가상접속시스템(VPN) https://vpn.daewooenc.com을 통해 사내망에 접속하시고,PC를 재부팅해주시기 바랍니다.단, PC-OFF제 예외적용 처리는 결재완료 후 익일부터 반영됩니다. (당일신청 당일적용 불가)<br><br>"
							+ " - 해외출장 신청서 상 사전승인되지 않은 기간은 예외적용 처리되지 않습니다. 따라서 출장기간이 연장되는 경우, 해당 기간만큼 개인 e-HR 시스템을 통해 해외출장 근태신청을 해주셔야 예외적용이 추가 반영됩니다.<br><br>"
							+ " ※ 문의 : 인사팀 과장 김용호(5612), 사원 문진희(5702) <br><br><br>"
							+ "2. AUTO-CAD<br><br>"
							+ " - 해외에서 CAD 프로그램 사용 예정 시 IT기획팀 김기철 대리에게 사전문의 바랍니다.<br><br>"
			};

		ds_Mail.add(params3);
	}

	var natNm = "";

	//목적지 여러곳 나오도록 처리 (2019.03.27)
	if(ds_CityList.size() > 0){
		for(var i=0;i<ds_CityList.size()-1;i++){		//맨 마지막은 한국이라서 삭제
			natNm = natNm + ", "+ds_NatList.get(ds_NatList.find("natCd", ds_CityList.get(i, "natCd")), "natNm");
		}
		//맨 처음 " ," 삭제 처리.
		natNm = natNm.substring(2);
	}


	//동행인 DATA
	var trUser = cf_getTripUserTrData();

	var IGuestNm = ""; // 동반인1 이름

	if(trUser[0] != undefined){
		IGuestNm = IGuestNm + trUser[0].userEnm; // 동반인1 이름
	}
	if(trUser[1] != undefined){
		IGuestNm = IGuestNm + ", "+trUser[1].userEnm; // 동반인2 이름
	}
	if(trUser[2] != undefined){
		IGuestNm = IGuestNm + ", "+trUser[2].userEnm; // 동반인3 이름
	}

	if(IGuestNm =="") IGuestNm ="없음";

	var IdSign = "";

	if(v_dSignOrgNm != null){
		IdSign = IdSign + v_dSignOrgNm;
	}
	if(v_dSignRpwrkNm != null){
		IdSign = IdSign +" "+ v_dSignRpwrkNm;
	}

	if(v_dSignUserNm != null){
		IdSign = IdSign +" "+ v_dSignUserNm;
	}

	if(IdSign =="") IdSign ="없음";

	//GHR팀장 출장신청 확인 요청 (수신자 : GHR팀장. 메일 발송 대상 : 출장자가 팀장 or 담당임원일때)
    //본부장 이상을 제외하고, 팀원을 제외하고..

	if(ds_HqRpswrkCd.find("value", v_TripUserRpswrkCd) != -1){
		//본부장 이상의 직책..일 경우 skip.
	}
	else{
		if(v_TripUserRpswrkCd.length > 0 ){			//직책자 ONLY...
			var params = {
				spec : "",
				bodyTemplate : "",
				fromMailId : "cmastripadmin@daewooenc.com",
				fromMailName : "출장시스템관리자",
//				mailId : "1501776@daewooenc.com", // GHR 팀장님. OT_006 ROLE
				mailId : "9521986@daewooenc.com", // GHR 팀장님. OT_006 ROLE
				mailSubject : "[해외출장신청서](출장자: " + v_TripUserKnm + " " + v_TripUserId + ")" ,
				//작성자,출장자,목적지(다중),소속팀,협의자(어느 팀 누구),동행인,출장기간,출장목적..
				htmlBody : "● 작성자 : " + v_DraftUserKnm + " " + v_DraftUserPositCd + "<br>"
							+ "● 출장자 : " + v_TripUserKnm + " " + v_TripUserPositCd + "<br>"
							+ "● 목적지 : " + natNm + "<br>"
							+ "● 소속팀 : " + v_TripUserOrgNm + "<br>"
							+ "● 협의자 : " + IdSign+ "<br>"
							+ "● 동행인 : " + IGuestNm+"<br>"
							+ "● 출장기간 : " + v_StartDate + " ~ " + v_EndDate+"<br>"
							+ "● 출장목적 : " +$("#tPurp").val()+"<br><br>"
			};
			//협의자와 동행인은 없을 수 있음.

			ds_Mail.add(params);

		} //end of if 직책자이면~
	}//end of else 본부장 이하의~

	//메일 목록들을 반영
	var dataSet = {
			input1 : ds_Mail.getAllData()
	};

	gf_Transaction("SELECT_MAIL", "/co/common/mail/sendMail.xpl", {}, dataSet, "f_Callback", true);

}

//시내교통비/국내출장/해외출장 중복 신청 방지 체크
function f_CheckDraftDuplication(){

	var v_userId = v_TripUserId;
	v_stYmd = v_StartDate;
	v_edYmd = v_EndDate;

	var params = {
			userId : v_userId,
			stYmd : v_stYmd,
			edYmd : v_edYmd
	};

	gf_Transaction("CHECK_DRAFT_DUPLICATION", "/trip/cityTransp/retrieveCheckDraftDuplication.xpl", params, {}, "f_Callback", true);

}