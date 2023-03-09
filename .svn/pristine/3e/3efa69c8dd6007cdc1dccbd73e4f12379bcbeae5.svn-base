/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/


var v_DocSts; // 상태
var v_AdjustDocSts; // 정산서 상태

//업로드를 수행하기 위해 선택한 파일 개체를 담는 전역 배열
var gv_Files = new Array();

var ds_OuterTripDoc = new DataSet();
var ds_ExtnlPer = new DataSet();
var ds_Expn = new DataSet();
var ds_Visa = new DataSet();
var ds_CityList = new DataSet();

var ds_SavedDoc = new DataSet();

var ds_HqRpswrkCd = new DataSet();

var v_IfParam = "";


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

var ds_Signln = new DataSet();

var ds_ChiefCd = new DataSet();

// 팀장 이상 여부
var v_IsCheif = "N";

//본부장 이상 여부
var v_IsHq = "N";

//일정 체제비 변동 여부
var v_IsPlanChg = "N";

//접대비 + 복리후생비 > 0 여부 or 접대비 + 복리후생비를 제외한 기타경비가 $200 이상 여부
var v_IsHostEtcOver = "N";

var v_SapResult;

var hostCheck = 0;

var hostCheck2 = 0;

var v_bossSignYn = "N";

var orgCls = "";

var v_FileAtchId;

var v_DraftSts;

var v_SapData;

var v_TripNat = "";

var ds_sapData = new DataSet();

var v_CancFlg = "";

var v_HQSign = "N";

var v_RejectRes = "";

var v_CmasList;

var fromReject = "";

var v_ErrMsg = "";
var v_PrgrMsg = "";

var ds_Cmas008 = new DataSet();		//협의자 (CMAS008)

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
	v_RefNo = gf_IsNull(datas.refNo) ? "" : datas.refNo;
	v_DocSts = gf_IsNull(datas.docSts) ? "" : datas.docSts;
	v_AdjustDocSts = gf_IsNull(datas.adjustDocSts) ? "" : datas.adjustDocSts;

	v_CmasList = gf_IsNull(datas.cmasList) ? "" : datas.cmasList;

	fromReject = gf_IsNull(datas.fromReject) ? "" : datas.fromReject;


	if(v_CmasList.length == 0){
		alert("출장 검토 담당자 정보를 가져올 수 없습니다.");
		self.close();
	}


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
	$("#rTripDateStart").mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
	$("#rTripDateEnd").mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
	$("#payDate").mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
	$("#payRutDate").mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });

	//증빙일자
	$("#oDate").mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
	$("#oDate").attr("readonly", "true");


	gf_SetUserComponent($("#payRutUser"), function(data){

	});

	//Attachment 컴포넌트 생성
	gf_InitFileUploadComponent();

	// upload 모드로 컴포넌트의 mode  설정
	// upload 모드로 컴포넌트의 mode  설정
	if(v_AdjustDocSts == "D61"){
		gf_setMode("download");
	}else if(v_AdjustDocSts == "D60"){
		gf_setMode("download");
	}else{
		gf_setMode("upload");
	}

    // 숫자만 입력
    f_numberOnly("usNightDay2");
    f_numberOnly("usDayDay2");
    f_numberOnly("eduDay2");
    f_numberOnly("spotDay2");
    f_numberOnly("euNightDay2");
    f_numberOnly("euDayDay2");
    f_numberOnly("enNightDay2");
    f_numberOnly("enDayDay2");
    f_numberOnly("jaNightDay2");
    f_numberOnly("jaDayDay2");

    f_numberOnly("s1");
    f_numberOnly("s2");
    f_numberOnly("s3");

    //기준액 수정 불가
    $("#usDayRef").attr("readonly", "readonly");
    $("#eduRef").attr("readonly", "readonly");
    $("#spotRef").attr("readonly", "readonly");
    $("#euDayRef").attr("readonly", "readonly");
    $("#enDayRef").attr("readonly", "readonly");
    $("#jaDayRef").attr("readonly", "readonly");

    // GHR 의 경우 기준액 수정 가능
    if(v_AdjustDocSts == "D60"){
    	$("#usDayRef").removeAttr("readonly");
    	$("#eduRef").removeAttr("readonly");
    	$("#spotRef").removeAttr("readonly");
    	$("#euDayRef").removeAttr("readonly");
    	$("#enDayRef").removeAttr("readonly");
    	$("#jaDayRef").removeAttr("readonly");

    	f_numberOnly("usDayRef");
    	f_numberOnly("eduRef");
    	f_numberOnly("spotRef");
    	f_numberOnly("euDayRef");
    	f_numberOnly("enDayRef");
    	f_numberOnly("jaDayRef");

    	f_numberOnly("s1");
    	f_numberOnly("s2");
    	f_numberOnly("s3");

    	// 신청서 의견 보기
    	$("#draftGhrCommentPop").show();

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

	$('#adjustDelete').click(function(){

		if(confirm("해당 문서를 삭제하시겠습니까?")){
			f_deleteDoc();
		}
	});

	$("#ghrBtn").click(function(){


		var params = {
				ghrComment : ds_OuterTripDoc.get(0, "perchrgRvwOpn2")
		}

		gf_PostOpen("/trip/outerTrip/ghrPop.jsp", null,
				"toolbar=no,scrollbars=yes,width=620,height=560", params,
				true, true, "f_callBackFuncSelNat");
	});


	$("#outerTripSign").click(function(){

//		v_SignSeq = gf_IsNull(datas.signSeq)? 0 : datas.signSeq;
//		v_IsDraft = gf_IsNull(datas.isDraft)? false: datas.isDraft;
//		v_IsMDraft = gf_IsNull(datas.isMDraft)? false: datas.isMDraft;
		var datas = {
			signln : ds_Signln.getAll()
		};
		gf_PostOpen("/common/jsp/sign/signUserSelect.jsp", null, "toolbar=no,scrollbars=no,width=1020,height=590", datas, true, true);
	});

	// 글로벌 HR팀 검토승인
	// SGNS DRAFT 시작
	// 임시저장 당시의 결재선이 통합결재 DRAFT시점에는 유효하지 않을수도 있음 (ex)팀장-실장-본부장 등의 교체. 따라서 결재선 재지정 필요.
	$('#outerTripHRConfirm').click(function(){

		// 이용일 수 체크
		var s1 = $("#s1").val();
		var s2 = $("#s2").val();
		var s3 = $("#s3").val();

		if(s1 == "" && s2 == "" && s3 == ""){
			alert("이용일 수에 공백을 입력할 수 없습니다.\n(무박일 시 최소 하나 이상의 항목에 0을 입력)");
			$("#s1").focus();
			return;
		}else{
			if(s1 == ""){
				$("#s1").val("0");
			}

			if(s2 == ""){
				$("#s2").val("0");
			}

			if(s3 == ""){
				$("#s3").val("0");
			}
		}


		if(f_docPatterChk() == false) return;

		if($("#jsGubun").val() == ""){
			alert('정산 구분을 선택해 주세요.');
			return;
		}else{
			var jsFlag = parseFloat(removeComma($("#cTotalAmt3").text()));

			if(jsFlag == 0 && $("#jsGubun").val() != "N"){
				if(v_CancFlg != "Y"){
					alert("정산 구분을 '일반'을 선택하세요.");
					return;
				}
			}else if(jsFlag < 0 && $("#jsGubun").val() != "R"){
				alert("정산 구분을 '반납'을 선택하세요.");
				return;
			}else if(jsFlag > 0 && $("#jsGubun").val() != "A"){
				alert("정산 구분을 '추가'를 선택하세요.");
				return;
			}
		}

		if($("#jsGubun").val() == "R"){

			if($("#culkum").val() == ""){
				alert("반납방법을 선택하세요.");
				return;
			}

			v_DraftSts = "D61";
			gf_onFileUpload();
		}else{

			if(confirm("문서를 검토완료 하시겠습니까?")){
				if(hostCheck2 > 0){
					v_DraftSts = "D70";
				}

				cf_settleOsBizTrip();
			}

		}


	});

	// 글로벌 HR팀 검토반려
	// 문서 반려 처리
	$('#outerTripSECNotConfirm').click(function(){

		alert("SEC팀 검토반려");
		self.close();

	});


	// 경영관리팀 검토승인
	$('#outerTripMANConfirm').click(function(){

		var assDtl = $("#assDtl").val();

		var dt = new Date();
		var month = dt.getMonth()+1;
		if(dt.getMonth()+1 < 10) month = "0" + (dt.getMonth()+1);
		var dayM = dt.getDate();
		if(dt.getDate() < 10) dayM = "0" + (dt.getDate());

		for(var i = 0; i < assDtl.length; i++){
			assDtl = assDtl.replace("\n", "##");
		}

		assDtl = assDtl + "##" + dt.getFullYear() + "-" + month + "-" + dayM + " " + gv_userId + " " + gv_userNm + " " + gv_orgNm + " 협조완료";

		//$("#assDtl").val(assDtl);

		v_DraftSts = "D60";
		gf_onFileUpload();

	});

	// 경영관리팀 검토반려
	$("#outerTripMANNotConfirm").click(function(){

		gf_PostOpen("/trip/outerTrip/rejectPop.jsp", null,
				"toolbar=no,scrollbars=yes,width=1000,height=150", {},
				true, true, "f_callBackFuncRejectPop");
	});


	// 문서 협조의뢰 한다.
	// 임시저장과 동일한 로직을 탄다.
	// 검토가 완료되고 통합결재 상신시에 데이터가 올바르게 저장된다.
	$("#adjustDraft").click(function(){

		if(($("#tPlanDtl").val().length+1) > 299){
			alert("세부일정 항목의 글자수가 공백포함 300자 이상 작성할 수 없습니다.\n장문 작성이 필요하신 경우는 별도의 파일로 첨부하시기 바랍니다.\n" + "(현재 글자수 : " + $("#tPlanDtl").val().length+1 + ")");
			return;
		}

		if(($("#tRptDtl").val().length+1) > 199){
			alert("업무보고내용 항목의 글자수가 공백포함 200자 이상 작성할 수 없습니다.\n장문 작성이 필요하신 경우는 별도의 파일로 첨부하시기 바랍니다.\n" + "(현재 글자수 : " + $("#tRptDtl").val().length+1 + ")");
			return;
		}

		if(($("#execDtl").val().length+1) > 199){
			alert("집행세부내역 항목의 글자수가 공백포함 200자 이상 작성할 수 없습니다.\n장문 작성이 필요하신 경우는 별도의 파일로 첨부하시기 바랍니다.\n" + "(현재 글자수 : " + $("#execDtl").val().length+1 + ")");
			return;
		}

		if(($("#rem").val().length+1) > 199){
			alert("비고 항목의 글자수가 공백포함 200자 이상 작성할 수 없습니다.\n장문 작성이 필요하신 경우는 별도의 파일로 첨부하시기 바랍니다.\n" + "(현재 글자수 : " + $("#rem").val().length+1 + ")");
			return;
		}

		if($("#oDate").val() == ""){
			alert("증빙일자를 입력하세요.");
			return;
		}

		if($("#rTripDateStart").val() == "" || $("#rTripDateEnd").val() == ""){
			alert("실사용 출장일자를 입력하세요.");
			return;
		}

		if($("#rTripDateStart").val() != "" && $("#rTripDateEnd").val() != ""){
			var sDate = $("#rTripDateStart").val().split("-");
			var eDate = $("#rTripDateEnd").val().split("-");

			var sDateTemp = sDate[0] + sDate[1] + sDate[2];
			var eDateTemp = eDate[0] + eDate[1] + eDate[2];

			if(sDateTemp > eDateTemp){
				alert("출장기간은 도착일이 출발일 보다 빠를 수 없습니다.");
				return;
			}
		}

		if(ds_Signln.find("signTpCd", "T02") == -1) {
			gf_AlertMsg("결재선을 지정하여 주세요.");
			return;
		}

		if(f_docPatterChk() == false) return;

		if($("#jsGubun").val() == ""){
			alert('정산 구분을 선택해 주세요.');
			return;
		}else{
			var jsFlag = parseFloat(removeComma($("#cTotalAmt3").text()));

			if(jsFlag == 0 && $("#jsGubun").val() != "N"){
				if(v_CancFlg != "Y"){
					alert("정산 구분을 '일반'을 선택하세요.");
					return;
				}else{
					alert("항공권 발권 취소의 경우 체제비 '반납' 입력만 가능합니다.");
					return;
				}
			}else if(jsFlag < 0 && $("#jsGubun").val() != "R"){
				alert("정산 구분을 '반납'을 선택하세요.");
				return;
			}else if(jsFlag > 0 && $("#jsGubun").val() != "A"){
				alert("정산 구분을 '추가'를 선택하세요.");
				return;
			}
		}

		if($("#jsGubun").val() == "R"){
			if($("#culkum").val() == ""){
				alert("반납은행을 선택하세요.");
				return;
			}
		}

		if($("#jsGubun").val() == "N"){

			var etcTotal = parseFloat(removeComma($("#etcTotal3").text()));
			if(etcTotal < 0){
				if($("#culkum").val() == ""){
					alert("기타경비 반납의 경우 반납은행을 선택하세요.");
					return;
				}

				if($("#payRutUser_id").val() == ""){
					alert("기타경비 반납의 경우 반납자를 입력하세요.");
					return;
				}

				if($("#payRutDate").val() == ""){
					alert("기타경비 반납의 경우 반납일을 선택하세요.");
					return;
				}

			}
		}

		// 이용일 수 체크
		var s1 = $("#s1").val();
		var s2 = $("#s2").val();
		var s3 = $("#s3").val();

		if(s1 == "" && s2 == "" && s3 == ""){
			alert("이용일 수에 공백을 입력할 수 없습니다.\n(무박일 시 최소 하나 이상의 항목에 0을 입력)");
			$("#s1").focus();
			return;
		}else{
			if(s1 == ""){
				$("#s1").val("0");
			}

			if(s2 == ""){
				$("#s2").val("0");
			}

			if(s3 == ""){
				$("#s3").val("0");
			}
		}

		//삭제금지_20200709_나중에 테스트후 적용필요(출장 중복신청 방지기능)
		//신청중복체크
		//f_CheckDraftDuplication();
		//return;

		var bdgtType = $("#bdgtType").text().substr(0,1);

		//출장일이 2018.06.01 이후 출장일부터 체크함
		if($("#rTripDateStart").val() != ""){
			var sDate = $("#rTripDateStart").val().split("-");
			var sDateTemp = sDate[0] + sDate[1] + sDate[2];

			// C. 특정경비-팀 || B. 특정경비-팀장 || A. 특정경비-임원 || I. 일반경비 일때만 숙박시설 이용시 경영관리팀 검토 그 외 글로벌HR팀 협조
				if((bdgtType=="C" || bdgtType=="B" || bdgtType=="A" || bdgtType=="I")&& parseInt(s1) > 0){
					if(sDateTemp >= 20180601){
						v_DraftSts = "D80";  // 경영관리팀 숙소검토
					}else{
						v_DraftSts = "D60";  // 글로벌HR팀 협조
					}
				}else{
					v_DraftSts = "D60";  // 글로벌HR팀 협조
				}
		}

		gf_onFileUpload();

	});


	$("#adjustSave").click(function(){

		if(f_docPatterChk() == false) return;

		if(($("#tPlanDtl").val().length+1) > 299){
			alert("세부일정 항목의 글자수가 공백포함 300자 이상 작성할 수 없습니다.\n장문 작성이 필요하신 경우는 별도의 파일로 첨부하시기 바랍니다.\n" + "(현재 글자수 : " + $("#tPlanDtl").val().length+1 + ")");
			return;
		}

		if(($("#tRptDtl").val().length+1) > 199){
			alert("업무보고내용 항목의 글자수가 공백포함 200자 이상 작성할 수 없습니다.\n장문 작성이 필요하신 경우는 별도의 파일로 첨부하시기 바랍니다.\n" + "(현재 글자수 : " + $("#tRptDtl").val().length+1 + ")");
			return;
		}

		gf_onFileUpload();
	});


	$("#jsExm").click(function(){

		var alertMsg = "체제비 변동무, 기타경비 추가 -> 일반\n" +
		"체제비 변동무, 기타경비 반납 -> 일반\n" +
		"\n" +
		"체제비 추가, 기타경비 추가 -> 추가\n" +
		"체제비 추가, 기타경비 일반 -> 추가\n" +
		"\n" +
		"체제비 반납, 기타경비 추가 -> 반납\n";

		alert(alertMsg);
	});


	$("#tripUserAddBtn").click(function(){

		cf_AppendTripUser();
	});

	$("#selNat").click(function(){
		var param = {
				ds_NatList : ds_NatList.getAllData(),
				natCd : $("#tripNat").val()
		};


		gf_PostOpen("/trip/outerTrip/selNat.jsp", null,
				"toolbar=no,scrollbars=yes,width=620,height=560", param,
				true, true, "f_callBackFuncSelNat");
	});

	$("#jsGubun").bind('change', function(e) {

		// 반납을 선택한 경우만 반납일 반납 방법을 보여줌
		if(e.currentTarget.value == "R"){
			$("#payRutDate").show();
			$("#culkum").show();
			$("#retCost").show();

			cf_retrieveSignLine(); // 전결규정 결재선 가져오기
			// GHR 검토중일경우
			if(v_AdjustDocSts == "D60") return;


		}else if(e.currentTarget.value == "N"){
			$("#payRutDate").show();
			$("#culkum").show();
			$("#retCost").show();

			cf_retrieveSignLine();

			if(v_AdjustDocSts == "D60") return;

		}else{

			$("#payRutDate").show();
			$("#culkum").show();
			$("#retCost").show();

			// GHR 검토중일경우
			cf_retrieveSignLine();
//			if(v_AdjustDocSts == "D60") return;
			if(e.currentTarget.value == "A"){
				// 추가 일 경우 결재라인을 본부장까지 생성
				// 본부장 이상 직책일 체크

				hostCheck2 = parseInt(f_notSpace(removeComma($("#vocLeeFare2").val()))) + parseInt(f_notSpace(removeComma($("#hostFare2").val())));

				if(hostCheck > 1400){
					if(hostCheck2 > hostCheck){
						v_bossSignYn = "Y";
						orgCls = "회장";

						// 화면 상단 결재선 셋팅
						// 현장소속의 경우 현장담당책임자를 가져오지않으므로 수동으로 중간협의자로 넣어줘야한다.
						var params = {
						  		orgCd : v_IfParam.tripUserOrgCd,
						  		orgCls : orgCls,
						  		userId : v_IfParam.tripUserId
						};

						// 상신시 Modal처리
						gf_InitSpinner(top.$('body'));

						gf_Transaction("SELECT_SIGN_LINE", "/co/common/user/retrieveSignlnForExcluRegl.xpl", params, {}, "f_Callback", true);

					}else{


						//정산 구분이 추가 일 경우 본부까지 결재가 올라감

						// 상신시 Modal처리
						gf_InitSpinner(top.$('body'));

						// 본부장 이상 직책일 체크
						if(ds_HqRpswrkCd.find("value", v_IfParam.tripUserRpswrkCd) != -1){
							orgCls = "회장";
						}else{
							orgCls = "본부/실";
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

				}else{

					if(hostCheck2 > 1400){
						v_bossSignYn = "Y";
						orgCls = "회장";

						// 화면 상단 결재선 셋팅
						// 현장소속의 경우 현장담당책임자를 가져오지않으므로 수동으로 중간협의자로 넣어줘야한다.
						var params = {
						  		orgCd : v_IfParam.tripUserOrgCd,
						  		orgCls : orgCls,
						  		userId : v_IfParam.tripUserId
						};

						// 상신시 Modal처리
						gf_InitSpinner(top.$('body'));

						gf_Transaction("SELECT_SIGN_LINE", "/co/common/user/retrieveSignlnForExcluRegl.xpl", params, {}, "f_Callback", true);

					}else{


						//정산 구분이 추가 일 경우 본부까지 결재가 올라감

						// 상신시 Modal처리
						gf_InitSpinner(top.$('body'));

						// 본부장 이상 직책일 체크
						if(ds_HqRpswrkCd.find("value", v_IfParam.tripUserRpswrkCd) != -1){
							orgCls = "회장";
						}else{
							orgCls = "본부/실";
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

				}

			}else{
				cf_retrieveSignLine();
			}
		}

	});


	$("#loAirFare2").bind('keyup', function(e) {

		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}

//		f_calcEtcAmt(amt, orgId, krId, diffId){
		f_calcEtcAmt(e.currentTarget.value, "loAirFare1", "loAirFareEx2", "loAirFare3");

//		e.currentTarget.value = gf_AmtFormat(targetVal);

	});

	$("#loAirFare2").focusout(function() {

		f_HQSignProcess();
	});

	$("#loTranFare2").bind('keyup', function(e) {

		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}

//		f_calcEtcAmt(amt, orgId, krId, diffId){
		f_calcEtcAmt(e.currentTarget.value, "loTranFare1", "loTranFareEx2", "loTranFare3");

//		e.currentTarget.value = gf_AmtFormat(targetVal);

	});

	$("#loTranFare2").focusout(function() {

		f_HQSignProcess();
	});

	$("#visaFeeFare2").bind('keyup', function(e) {

		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}

//		f_calcEtcAmt(amt, orgId, krId, diffId){
		f_calcEtcAmt(e.currentTarget.value, "visaFeeFare1", "visaFeeFareEx2", "visaFeeFare3");

//		e.currentTarget.value = gf_AmtFormat(targetVal);

	});

	$("#visaFeeFare2").focusout(function() {

		f_HQSignProcess();
	});

	$("#ovCharFare2").bind('keyup', function(e) {

		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}

//		f_calcEtcAmt(amt, orgId, krId, diffId){
		f_calcEtcAmt(e.currentTarget.value, "ovCharFare1", "ovCharFareEx2", "ovCharFare3");

//		e.currentTarget.value = gf_AmtFormat(targetVal);

	});

	$("#ovCharFare2").focusout(function() {

		f_HQSignProcess();
	});




	$("#vocLeeFare2").bind('keyup', function(e) {

		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}

//		f_calcEtcAmt(amt, orgId, krId, diffId){
		f_calcEtcAmt(e.currentTarget.value, "vocLeeFare1", "vocLeeFareEx2", "vocLeeFare3");

//		e.currentTarget.value = gf_AmtFormat(targetVal);

	});

	$("#vocLeeFare2").focusout(function() {

		f_bossSignProcess();
	});

	$("#hostFare2").bind('keyup', function(e) {

		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}

//		f_calcEtcAmt(amt, orgId, krId, diffId){
		f_calcEtcAmt(e.currentTarget.value, "hostFare1", "hostFareEx2", "hostFare3");

//		e.currentTarget.value = gf_AmtFormat(targetVal);

	});

	$("#hostFare2").focusout(function() {

		f_bossSignProcess();
	});

	$("#usNightDay2").bind('keyup', function(e) {

		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}

		f_calcExpnAmt(e.currentTarget.value, "usNightRef", "usNightDayAmt2", "usNightDayAmt", "usNight3");

		e.currentTarget.value = gf_AmtFormat(targetVal);

	});

	//기준액 수정
	$("#usDayRef").bind('keyup', function(e) {

		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}

		f_calcExpnAmt(removeComma($("#usDayDay2").val()), "usDayRef", "usDayDayAmt2", "usDayDayAmt", "usDay3");

		e.currentTarget.value = gf_AmtFormat(targetVal);

	});


	$("#usDayDay2").bind('keyup', function(e) {

		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}

		f_calcExpnAmt(e.currentTarget.value, "usDayRef", "usDayDayAmt2", "usDayDayAmt", "usDay3");

		e.currentTarget.value = gf_AmtFormat(targetVal);

	});

	// 기준액 수정
	$("#eduRef").bind('keyup', function(e) {

		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}

		f_calcExpnAmt(removeComma($("#eduDay2").val()), "eduRef", "eduDayAmt2", "eduDayAmt", "edu3");

		e.currentTarget.value = gf_AmtFormat(targetVal);

	});


	$("#eduDay2").bind('keyup', function(e) {

		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}

		f_calcExpnAmt(e.currentTarget.value, "eduRef", "eduDayAmt2", "eduDayAmt", "edu3");

		e.currentTarget.value = gf_AmtFormat(targetVal);

	});

	//기준액 수정
	$("#spotRef").bind('keyup', function(e) {

		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}

		f_calcExpnAmt(removeComma($("#spotDay2").val()), "spotRef", "spotDayAmt2", "spotDayAmt", "spot3");

		e.currentTarget.value = gf_AmtFormat(targetVal);

	});

	$("#spotDay2").bind('keyup', function(e) {

		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}

		f_calcExpnAmt(e.currentTarget.value, "spotRef", "spotDayAmt2", "spotDayAmt", "spot3");

		e.currentTarget.value = gf_AmtFormat(targetVal);

	});

	$("#euNightDay2").bind('keyup', function(e) {

		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}

		f_calcExpnAmt(e.currentTarget.value, "euNightRef", "euNightDayAmt2", "euNightDayAmt", "euNight3");

		e.currentTarget.value = gf_AmtFormat(targetVal);

	});

	// 기준액 수정
	$("#euDayRef").bind('keyup', function(e) {

		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}

		f_calcExpnAmt(removeComma($("#euDayDay2").val()), "euDayRef", "euDayDayAmt2", "euDayDayAmt", "euDay3");

		e.currentTarget.value = gf_AmtFormat(targetVal);

	});

	$("#euDayDay2").bind('keyup', function(e) {

		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}

		f_calcExpnAmt(e.currentTarget.value, "euDayRef", "euDayDayAmt2", "euDayDayAmt", "euDay3");

		e.currentTarget.value = gf_AmtFormat(targetVal);

	});

	$("#enNightDay2").bind('keyup', function(e) {

		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}

		f_calcExpnAmt(e.currentTarget.value, "enNightRef", "enNightDayAmt2", "enNightDayAmt", "enNight3");

		e.currentTarget.value = gf_AmtFormat(targetVal);

	});

	//기준액 수정
	$("#enDayRef").bind('keyup', function(e) {

		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}

		f_calcExpnAmt(removeComma($("#enDayDay2").val()), "enDayRef", "enDayDayAmt2", "enDayDayAmt", "enDay3");

		e.currentTarget.value = gf_AmtFormat(targetVal);

	});


	$("#enDayDay2").bind('keyup', function(e) {

		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}

		f_calcExpnAmt(e.currentTarget.value, "enDayRef", "enDayDayAmt2", "enDayDayAmt", "enDay3");

		e.currentTarget.value = gf_AmtFormat(targetVal);

	});

	$("#jaNightDay2").bind('keyup', function(e) {

		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}

		f_calcExpnAmt(e.currentTarget.value, "jaNightRef", "jaNightDayAmt2", "jaNightDayAmt", "jaNight3");

		e.currentTarget.value = gf_AmtFormat(targetVal);

	});

	//기준액 수정
	$("#jaDayRef").bind('keyup', function(e) {

		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}

		f_calcExpnAmt(removeComma($("#jaDayDay2").val()), "jaDayRef", "jaDayDayAmt2", "jaDayDayAmt", "jaDay3");

		e.currentTarget.value = gf_AmtFormat(targetVal);

	});

	$("#jaDayDay2").bind('keyup', function(e) {

		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}

		f_calcExpnAmt(e.currentTarget.value, "jaDayRef", "jaDayDayAmt2", "jaDayDayAmt", "jaDay3");

		e.currentTarget.value = gf_AmtFormat(targetVal);

	});

	$("#outerTripHRNotConfirm").click(function(){

		gf_PostOpen("/trip/outerTrip/rejectPop.jsp", null,
				"toolbar=no,scrollbars=yes,width=1000,height=150", {},
				true, true, "f_callBackFuncRejectPop");
	});


	$("#inputRet").click(function(){

		var param = {
				payRutUser_id : $("#payRutUser_id").val(),
				payRutUser_name : $("#payRutUser_name").val(),
				payRutDate : $("#payRutDate").val()
		};


		gf_PostOpen("/trip/outerTrip/retCost.jsp", null,
				"toolbar=no,scrollbars=yes,width=340,height=320", param,
				true, true, "f_callBackFuncInputRet");


	});

	$("#retComp").click(function(){

		if($("#payRutUser_id").val() == ""){
			alert("반납자를 입력하세요.");
			return;
		}else if($("#payRutDate").val() == ""){
			alert("반납일을 입력하세요.");
			return;
		}

		if(confirm("문서를 완료하시겠습니까?")){

			if(hostCheck2 > 0){
				v_DraftSts = "D70";
			}

			cf_settleOsBizTrip();
		}
	});

	//반납취소
	$("#retCancel").click(function(){

			if(confirm("반납취소 하시겠습니까?")){
				f_cancelDoc();
			}
	});


	// 출장일 From To Validation
	$("#rTripDateStart").bind('change', function() {

		if($("#rTripDateStart").val() != "" && $("#rTripDateEnd").val() != ""){
			var sDate = $("#rTripDateStart").val().split("-");
			var eDate = $("#rTripDateEnd").val().split("-");

			var sDateTemp = sDate[0] + sDate[1] + sDate[2];
			var eDateTemp = eDate[0] + eDate[1] + eDate[2];

			var startDate = $.datepicker.parseDate("yymmdd" , sDateTemp);
			var endDate = $.datepicker.parseDate("yymmdd" , eDateTemp);

			var betDay = (endDate - startDate)/1000/60/60/24;

			$("#rTripDate").text(betDay+1);

		}

	});

	// 출장일 From To Validation
	$("#rTripDateEnd").bind('change', function() {

//		if($("#tPassp").val() == ""){
//			$("#tPassp").datepicker("setDate", $("#fPassp").val());
//		}

		if($("#rTripDateStart").val() != "" && $("#rTripDateEnd").val() != ""){
			var sDate = $("#rTripDateStart").val().split("-");
			var eDate = $("#rTripDateEnd").val().split("-");

			var sDateTemp = sDate[0] + sDate[1] + sDate[2];
			var eDateTemp = eDate[0] + eDate[1] + eDate[2];

			var startDate = $.datepicker.parseDate("yymmdd" , sDateTemp);
			var endDate = $.datepicker.parseDate("yymmdd" , eDateTemp);

			var betDay = (endDate - startDate)/1000/60/60/24;

			$("#rTripDate").text(betDay+1);

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
	// 상태에 상관없이 관리자면 예산체크 테스트 버튼 생성
	if(gv_AuthList.auth[0].code.match("RO_CMAS_CO_TRIP_ADM") != null){
		$("#outerTripCheck").show();
	//	$("#adjustBack").show();
	}

	// 본부장 이상 직책 삽입
	ds_HqRpswrkCd.setAllData(gv_hqRpswrkCd);

	// 팀장 이상 직책 삽입
	ds_ChiefCd.setAllData(gv_ChiefCd);

	var params = {
			docNo : v_DocNo,
			refNo : v_RefNo
	};

	gf_Transaction("SELECT_OUTER_TRIP_VIEW_DOC", "/trip/outerTrip/retrieveOuterTripViewDoc.xpl", params, {}, "f_Callback", true);

	if(v_AdjustDocSts == "D16" || v_AdjustDocSts == "D54" || v_AdjustDocSts == "D64" || v_AdjustDocSts == "D84"){

		//임시저장일때 해출담당자 삭제버튼 활성화
		if(gv_AuthList.auth[0].code.match("RO_CMAS_CO_TRIP_ADM") != null){
			$("#adjustDelete").show();
		}

	}else if(v_AdjustDocSts == "D50"){

		//검토 버튼 활성화
		$("#outerTripSECConfirm").show();
		$("#outerTripSECNotConfirm").show();

		//일반버튼 숨김
		$("#adjustSave").hide();
		$("#adjustDraft").hide();
		$("#outerTripSign").hide();


		//협조내역 팝업
		$("#assistComment").removeAttr("readonly");
//		$("#assistComment").attr("readonly", "true");

	}else if(v_AdjustDocSts == "D60"){

		//검토 버튼 활성화
		$("#outerTripHRConfirm").show();
		$("#outerTripHRNotConfirm").show();
		$("#adjustSave").show();
		$("#outerTripSign").show();

		//일반버튼 숨김
		$("#adjustDraft").hide();

		//협조내역 팝업
		$("#assistComment").removeAttr("readonly");
//		$("#assistComment").attr("readonly", "true");

		// 검토의견 필드 팝업
		$("#ghrTr").show();
		$("#ghrComment").removeAttr("readonly");

	}else if(v_AdjustDocSts == "D61"){

		//일반버튼 숨김
		$("#outerTripSign").hide();
		$("#adjustSave").hide();
		$("#adjustDraft").hide();

		//반납 버튼 활성화
		$("#inputRet").show();
		$("#retComp").show();
		$("#retCancel").show();

		// 반납 확인중 문서 필들 팝업
		$("#retCost").show();

	}else if(v_AdjustDocSts == "D80"){

		//검토 버튼 활성화
		$("#outerTripMANConfirm").show();
		$("#outerTripMANNotConfirm").show();
		$("#adjustSave").show();
		$("#outerTripSign").show();

		//일반버튼 숨김
		$("#adjustDraft").hide();

		//협조내역 팝업
		$("#assistComment").removeAttr("readonly");

		// 검토의견 필드 팝업
		$("#ghrTr").show();
		$("#ghrComment").removeAttr("readonly");

	}


	// 에러내역 조회
	var params = {
			docNo : v_DocNo
	};

	gf_Transaction("SELECT_ERR_MSG", "/trip/outerTrip/retrieveErrMsgAdjust.xpl", params, {}, "f_Callback", true);

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
	  	case "SELECT_OUTER_TRIP_VIEW_DOC" :

	  		ds_sapData.setAllData(resultData.ds_sapData);

	  		ds_OuterTripDoc.setAllData(resultData.ds_OuterTripDoc);
			ds_ExtnlPer.setAllData(resultData.ds_ExtnlPer);
			ds_Expn.setAllData(resultData.ds_Expn);
			ds_Visa.setAllData(resultData.ds_Visa);

			if(ds_OuterTripDoc.get(0, "ifParam") != undefined) v_IfParam =  JSON.parse(ds_OuterTripDoc.get(0, "ifParam"));

			gf_Transaction("SELECT_NAT_LIST", "/trip/outerTrip/retrieveNatList.xpl", {}, {}, "f_Callback", true);

			break;
	  	case "SELECT_NAT_LIST" :
	  		ds_NatList.setAllData(resultData.ds_NatList);
	  		ds_RefList.setAllData(resultData.ds_RefList);
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

	  		$("#loAirFare1").text(ds_OuterTripDoc.get(0, "lclAfare"));
	  		$("#loTranFare1").text(ds_OuterTripDoc.get(0, "lclTrafficExpn"));
	  		$("#visaFeeFare1").text(ds_OuterTripDoc.get(0, "visaFee"));
	  		$("#ovCharFare1").text(ds_OuterTripDoc.get(0, "excsExpn"));
	  		$("#vocLeeFare1").text(ds_OuterTripDoc.get(0, "wef"));
	  		$("#hostFare1").text(ds_OuterTripDoc.get(0, "svcExpn"));

	  		// 환율 계산 데이터
	  		$("#loAirFareEx1").text(gf_AmtFormat(Math.round(parseFloat(removeComma(ds_OuterTripDoc.get(0, "lclAfare"))) * parseFloat(v_USD))));
	  		$("#loTranFareEx1").text(gf_AmtFormat(Math.round(parseFloat(removeComma(ds_OuterTripDoc.get(0, "lclTrafficExpn"))) * parseFloat(v_USD))));
	  		$("#visaFeeFareEx1").text(gf_AmtFormat(Math.round(parseFloat(removeComma(ds_OuterTripDoc.get(0, "visaFee"))) * parseFloat(v_USD))));
	  		$("#ovCharFareEx1").text(gf_AmtFormat(Math.round(parseFloat(removeComma(ds_OuterTripDoc.get(0, "excsExpn"))) * parseFloat(v_USD))));
	  		$("#vocLeeFareEx1").text(gf_AmtFormat(Math.round(parseFloat(removeComma(ds_OuterTripDoc.get(0, "wef"))) * parseFloat(v_USD))));
	  		$("#hostFareEx1").text(gf_AmtFormat(Math.round(parseFloat(removeComma(ds_OuterTripDoc.get(0, "svcExpn"))) * parseFloat(v_USD))));


	  		if(v_AdjustDocSts == ""){
	  			$("#loAirFare2").val(gf_AmtFormat(ds_OuterTripDoc.get(0, "lclAfare")));
		  		$("#loTranFare2").val(gf_AmtFormat(ds_OuterTripDoc.get(0, "lclTrafficExpn")));
		  		$("#visaFeeFare2").val(gf_AmtFormat(ds_OuterTripDoc.get(0, "visaFee")));
		  		$("#ovCharFare2").val(gf_AmtFormat(ds_OuterTripDoc.get(0, "excsExpn")));
		  		$("#vocLeeFare2").val(gf_AmtFormat(ds_OuterTripDoc.get(0, "wef")));
		  		$("#hostFare2").val(gf_AmtFormat(ds_OuterTripDoc.get(0, "svcExpn")));

		  		//계산
		  		f_calcEtcAmt(ds_OuterTripDoc.get(0, "lclAfare"), "loAirFare1", "loAirFareEx2", "loAirFare3");
		  		f_calcEtcAmt(ds_OuterTripDoc.get(0, "lclTrafficExpn"), "loTranFare1", "loTranFareEx2", "loTranFare3");
		  		f_calcEtcAmt(ds_OuterTripDoc.get(0, "visaFee"), "visaFeeFare1", "visaFeeFareEx2", "visaFeeFare3");
		  		f_calcEtcAmt(ds_OuterTripDoc.get(0, "excsExpn"), "ovCharFare1", "ovCharFareEx2", "ovCharFare3");
		  		f_calcEtcAmt(ds_OuterTripDoc.get(0, "wef"), "vocLeeFare1", "vocLeeFareEx2", "vocLeeFare3");
		  		f_calcEtcAmt(ds_OuterTripDoc.get(0, "svcExpn"), "hostFare1", "hostFareEx2", "hostFare3");
	  		}

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

	  		$("#rem").val(ds_OuterTripDoc.get(0, "rem"));

	  		hostCheck = parseInt(f_notSpace(removeComma(ds_OuterTripDoc.get(0, "wef")))) + parseInt(f_notSpace(removeComma(ds_OuterTripDoc.get(0, "svcExpn"))));

	  		cf_setAdjustDocInfo();

	  		break;
	  	case "SELECT_SAVED_ADJUST_DOC" :
	  		ds_SavedDoc.setAllData(resultData.ds_SavedDoc);

	  		cf_setSavedDocInfo();
	  		break;
	  	case "SELECT_SIGN_LINE" :
	  		cf_setSignlnInfo(resultData);
	  		break;
	  	case "SELECT_SETTLE_OS_BIZ_TIP" :
	  		// 일반의 경우 전표가 생성되지 않는다. . 만 리턴되어옴
	  		// 추가 반납의 경우 전표가 생성되니 결재 후 Confirm 처리 해야한다.
	  		if(resultData.output1[0].ErrMsg != null){
	  			gf_AlertMsg(resultData.output1[0].ErrMsg);

	  			gf_DisableCurrentOverlay();

	  			$(".ajax_overlay").remove();

	  			// 에러 메세지 기록
	  			var params = {
	  				docNo : v_DocNo,
	  				errMsg : resultData.output1[0].ErrMsg
	  			};

	  			v_ErrMsg = resultData.output1[0].ErrMsg;

	  			gf_Transaction("SELECT_ERR_MSG", "/trip/outerTrip/updateErrMsgAdjust.xpl", params, {}, "f_Callback", true);

	  		}else{
	  			//메일전송
	  			// 메일 전송을 위한 플래그 Y 일경우만 메일을 전송한다.
	  			var mailYn = "N";
	  			var ds_Mail = new DataSet();
	  			var mailParams;

	  			if(resultData.output1[0].sType == "Y"){
	  				mailYn = "Y";

	  				for(var i = 0; i < v_CmasList.length; i++){
	  					if(v_CmasList[i].privCd == "RO_CMAS_OT_005"){
//	  						alert(v_CmasList[i].userKnm);
	  						mailParams = {
	  		  						spec : "",
	  		  						bodyTemplate : "",
	  		  						fromMailId : "",
	  		  						fromMailName : "해외출장담당자",
	  		  						mailId : v_CmasList[i].userId + "@daewooenc.com", // 운영시에는 담당자로 변경할 것
	  		  						mailSubject : "[해외출장신청서](출장자: " + v_IfParam.tripUserKnm + " " + v_IfParam.tripUserId + ") 기타경비 확인바랍니다." ,
	  		  						htmlBody : "■ 출장자 : " + v_IfParam.tripUserKnm + " " + v_IfParam.tripUserPositCd + "<br>"
	  		  									+ "■ 문서상태 : 결재중" + "<br>"
	  		  									+ "■ 목적지 : " + $("#tripNat").val() + "<br>"
	  		  									+ "■ 출장기간 : " + $("#rTripDateStart").val() + " ~ " + $("#rTripDateEnd").val()
	  		  				};

	  						// mailParams 삽입
	  						ds_Mail.add(mailParams);

	  					}
	  				}


	  				// mailParams 삽입
	  				ds_Mail.add(mailParams);

//	  				제목 : [해외출장정산서](출장자: 홍길동)기타경비 확인바랍니다.
//	  				내용 :  ■ 작성자 : 홍길동 과장
//	  				          ■ 출장자 : 김철수 부장
//	  				          ■ 문서상태 : SEC 검토요청중
//	  				          ■ 목적지 : 나이지리아
//	  				          ■ 출장기간 : 20150611~20150614

	  			}


		  		var dataSetParam = {
		  				input1 : ds_Mail.getAllData()
				};

		  		// 국출 01 해출 02 시내 03 해출정산 04
		  		var updateParams = {
		  				mailYn : mailYn
		  		};

	  			gf_Transaction("SAVE_CMAS_DOC_UPDATE_SIGN_ID", "/trip/outerTrip/sendSTypeMail.xpl", updateParams, dataSetParam, "f_Callback", true);


	  		}
//	  			ORefkeyNo: ".", OPrctr: "3DFUR", OPrctrTxt: "IT운영팀", OAccountNo: "213211167098"

//	  			alert("전표 번호 : " + resultData.output1[0].ORefkeyNo);

//	  			if(resultData.output1[0].ORefkeyNo == undefined){
//	  				gf_AlertMsg("SAP 전표가 생성되지 않았습니다.");
//	  				return;
//
//	  				gf_DisableCurrentOverlay();
//
//	  				$(".ajax_overlay").remove();
//	  			}else{
//	  				v_SapResult = resultData.output1[0];
//		  			cf_sgnsRemoteDraft();
//
//	  			}
//
//	  		}

	  		break;
	  	case "SELECT_OUTER_TRIP_DRAFT" :

	  		// 에러 여부 체크
	  		if(resultData.output1[0].ErrMsg != null){
	  			alert("입력된 값이 초과 되었습니다.");
	  			$(".ajax_overlay").remove();
	  			return;
	  		}
			var drafterId = v_IfParam.drafterUserId + " " + v_IfParam.drafterUserKnm; // 작성자 ID
			var drafterOrg = v_DraftUserOrgCd + " " + v_DraftUserOrgNm; // 작성자 ORG

			var fileAtch = gf_IsNull(v_FileAtchId) ? ""          : v_FileAtchId;


			var mSignType = "N";
			if(v_DraftSts == "D70"){
				mSignType = "Y";
			}

			//20150925 추가 legacy Info
			var af1 = removeComma($("#af1").text());
			var af2 = removeComma($("#af2").text());
			var af3 = removeComma($("#af3").text());

			var usNightRef = removeComma($("#usNightRef").text());
			var usDayRef = removeComma($("#usDayRef").val());
			var eduRef = removeComma($("#eduRef").val());
			var spotRef = removeComma($("#spotRef").val());
			var euNightRef = removeComma($("#euNightRef").text());
			var euDayRef = removeComma($("#euDayRef").val());
			var enNightRef = removeComma($("#enNightRef").text());
			var enDayRef = removeComma($("#enDayRef").val());
			var jaNightRef = removeComma($("#jaNightRef").text());
			var jaDayRef = removeComma($("#jaDayRef").val());

			var usNightDay = removeComma($("#usNightDay").text());
			var usDayDay = removeComma($("#usDayDay").text());
			var eduDay = removeComma($("#eduDay").text());
			var spotDay = removeComma($("#spotDay").text());
			var euNightDay = removeComma($("#euNightDay").text());
			var euDayDay = removeComma($("#euDayDay").text());
			var enNightDay = removeComma($("#enNightDay").text());
			var enDayDay = removeComma($("#enDayDay").text());
			var jaNightDay = removeComma($("#jaNightDay").text());
			var jaDayDay = removeComma($("#jaDayDay").text());

			var usd = v_USD;
			var eur = v_EUR;
			var gbp = v_GBP;
			var jpy = v_JPY;


			var sType = "N";

			// 기타경비 0 이상 체크
			if(parseInt(removeComma($("#etcTotal1").text())) > 0){
				sType = "Y";
			}else if(parseInt(removeComma($("#etcTotal2").text())) > 0){
				sType = "Y";
			}

	  		// SGNS REMOTE DRAFT
	  		var sgnsParams = {
	  				dutySysCd : "SGNS", // DUTYSYSCD
	  		  		programCode : "SGNS070005", // 양식코드
	  		  		signDocTitle : "해외출장 보고서 및 정산서", // 양식 제목
	  				ordDate : $.datepicker.formatDate("yymmdd", new Date), // 기표일자
	  				ordNo : "", // 전표번호
	  				docNo : v_DocNo, // CMAS 문서번호
	  				drafterId : v_IfParam.drafterUserId + " " + v_IfParam.drafterUserKnm, // 작성자 ID
	  				drafter : drafterId,
	  				drafterOrg : v_DraftUserOrgCd + " " + v_DraftUserOrgNm, // 작성자 ORG
	  				drafterOrgNm : drafterOrg,
	  				tripUser : v_IfParam.tripUserId + " " + v_IfParam.tripUserKnm,
	  				tripUserId : v_IfParam.tripUserId + " " + v_IfParam.tripUserKnm,
	  				tripUserInfo : $("#tripUserInfo").text(),
	  				tripNat : $("#tripNat").val(),
	  				tripDate : "최초 : " + $("#fTripDate").text(),
	  				realTripDate : "실사용 : " + $("#rTripDateStart").val() + " ~ " + $("#rTripDateEnd").val() + " (총 " + $("#rTripDate").text() + "일)",
	  				payDate : $("#payDate").val(),
	  				tReq : $("#tReq").val(),
	  				tPurp : $("#tPurp").val(),
	  				tPlanDtl : $("#tPlanDtl").val(),
	  				tDataList : $("#tDataList").val(),
	  				s1 : $("#s1").val(),
	  				s2 : $("#s2").val(),
	  				s3 : $("#s3").val(),
	  				bdgtType : $("#bdgtType").text(),
	  				cGubun : $("#cGubun").text(),
	  				aufnr : $("#aufnr").text(),
	  				kostv : $("#kostv").text(),
	  				drafterInfo : $("#drafterInfo").text(),
	  				rem : $("#rem").val(),
	  				assDtl : $("#assDtl").val(),
	  				refNo : v_SapResult.ORefkeyNo,
	  				fileAtchId : fileAtch,
	  				mSignType : mSignType,
	  				af1 : af1,
					af2 : af2,
					af3 : af3,
					usNightRef : usNightRef,
					usDayRef : usDayRef,
					eduRef : eduRef,
					spotRef : spotRef,
					euNightRef : euNightRef,
					euDayRef : euDayRef,
					enNightRef : enNightRef,
					enDayRef : enDayRef,
					jaNightRef : jaNightRef,
					jaDayRef : jaDayRef,
					usNightDay : usNightDay,
					usDayDay : usDayDay,
					eduDay : eduDay,
					spotDay : spotDay,
					euNightDay : euNightDay,
					euDayDay : euDayDay,
					enNightDay : enNightDay,
					enDayDay : enDayDay,
					jaNightDay : jaNightDay,
					jaDayDay : jaDayDay,
					usd : usd,
					eur : eur,
					gbp : gbp,
					jpy : jpy,
					sType : sType
	  		};


	  		var draftDataSet = {
	  				ds_Signln : ds_Signln.getAllData()
	  		};

	  		gf_Transaction("SELECT_SGNS_REMOTE_DRAFT", "/trip/outerTrip/saveAdjustSgnsRemoteDraft.xpl", sgnsParams, draftDataSet, "f_Callback", true);
	  		break;
	  	case "SELECT_SGNS_REMOTE_DRAFT" :
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


	  		//메일전송
  			// 메일 전송을 위한 플래그 Y 일경우만 메일을 전송한다.
  			var mailYn = "N";
  			var ds_Mail = new DataSet();
  			var mailParams;

  			if(resultData.output1[0].sType == "Y"){
  				mailYn = "Y";

  				for(var i = 0; i < v_CmasList.length; i++){
  					if(v_CmasList[i].privCd == "RO_CMAS_OT_005"){
//  						alert(v_CmasList[i].userKnm);


  						mailParams = {
  		  						spec : "",
  		  						bodyTemplate : "",
  		  						fromMailId : "",
  		  						fromMailName : "해외출장담당자",
  		  						mailId : v_CmasList[i].userId + "@daewooenc.com", // 운영시에는 담당자로 변경할 것
  		  						mailSubject : "[해외출장신청서](출장자: " + v_IfParam.tripUserKnm + " " + v_IfParam.tripUserId + ") 기타경비 확인바랍니다." ,
  		  						htmlBody : "■ 출장자 : " + v_IfParam.tripUserKnm + " " + v_IfParam.tripUserPositCd + "<br>"
  		  									+ "■ 문서상태 : 결재중" + "<br>"
  		  									+ "■ 목적지 : " + $("#tripNat").val() + "<br>"
  		  									+ "■ 출장기간 : " + $("#rTripDateStart").val() + " ~ " + $("#rTripDateEnd").val()
  		  				};

  						// mailParams 삽입
  						ds_Mail.add(mailParams);


  					}
  				}


  				// mailParams 삽입
  				ds_Mail.add(mailParams);

//  				제목 : [해외출장정산서](출장자: 홍길동)기타경비 확인바랍니다.
//  				내용 :  ■ 작성자 : 홍길동 과장
//  				          ■ 출장자 : 김철수 부장
//  				          ■ 문서상태 : SEC 검토요청중
//  				          ■ 목적지 : 나이지리아
//  				          ■ 출장기간 : 20150611~20150614

  			}

	  		var dataSetParam = {
	  				ds_SignInfo : ds_SignInfo.getAllData("U"),
	  				input1 : ds_Mail.getAllData()
			};

	  		if(resultData.output1[0].type == "SUCCESS"){

		  		// 국출 01 해출 02 시내 03 해출정산 04
		  		var updateParams = {
		  				signId : signId,
		  				docNo : v_DocNo,
		  				dutyCls : '04',
		  				mailYn : mailYn
		  		};

	  			gf_Transaction("SAVE_CMAS_DOC_UPDATE_SIGN_ID", "/trip/outerTrip/saveCmasDocUpdateSignId.xpl", updateParams, dataSetParam, "f_Callback", true);
	  		}else{

	  			alert('결재선 등록실패');
	  			var updateParams = {
		  				docSts : "D99",
		  				docNo : v_DocNo,
		  				mailYn : mailYn
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
	  	case "SAVE_ADJUST_DOC" :
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

		  		var cancFlg = true;
		  		// 출장자 수만큼 데이터를 출력한다.
		  		for(var i = 0; i < user.length; i++){

		  			var tempIF = parseFloat(user[i].Initfare) * 100;
		  			Initfare = Initfare + parseInt(tempIF.toFixed(0));

		  			var tempCF = parseFloat(user[i].Confirmamt) * 100;
		  			Confirmamt = Confirmamt + parseInt(tempCF.toFixed(0));
		  			if(user[i].CancelDate != "" && user[i].CancelDate != undefined){
		  				cancFlg = false;
		  			}
		  		}

		  		if(cancFlg == false){
	  				//항공료 취소된 건
	  				alert("항공료 발권 취소의 경우 반납 정산서만 작성가능합니다.");
	  				$("#jsGubun").val("R");
	  				// 반납을 선택한 경우만 반납일 반납 방법을 보여줌
	  				$("#payRutDate").show();
	  				$("#culkum").show();
	  				cf_retrieveSignLine();
	  				// 정산 방법 반납에 고정
	  				$("#jsGubun").attr("disabled", "true");
	  				v_CancFlg = "Y";
		  		}

		  		var af3 = Confirmamt - Initfare;

		  		$("#af1").text(gf_AmtFormat(Initfare));
		  		$("#af2").text(gf_AmtFormat(Confirmamt));
		  		$("#af3").text(gf_AmtFormat(af3));

	  		}

//	  		$("#refTr").show();
  			$("#refDay").text(ds_OuterTripDoc.get(0, "fstRegDt").substr(0, 10));

	  		var savedYmd = ds_OuterTripDoc.get(0, "fstRegDt").substr(0, 10);

	  		var ymdParams = {
	  			Ymd : $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", savedYmd))
	  		};

			gf_Transaction("SELECT_EXRATE", "/trip/eai/getSendExrate.xpl", ymdParams, {}, "f_Callback", true);

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
	  	case "SAVE_ADJUST_REJECT_DOC" :
	  		// 문서가 성공적으로 처리되면 문서 리스트를 다시 조회하여 갱신하여 준다.
	  		if ( !gf_IsNull(v_CallbackFunc) ) {
	  			var openCallbackFunc = "opener."+v_CallbackFunc;
	  	    	eval(openCallbackFunc + "();");
	  	    }

	  		self.close();
	  		break;
	  	case "SELECT_ERR_MSG" :
	  		if(resultData.output1 != undefined){
	  			if(resultData.output1[0].errItem != ""){
	  				if(resultData.output1[0].errItem != null){
	  					v_ErrMsg = resultData.output1[0].errItem;
	  					prgrItem
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
	  	case "SELECT_CHECK_BIZ_TRIP" :

	  		var result = resultData.output1[0];

	  		// 예산 체크 후 에러가 있을 경우 (예산 부족)
	  		if(resultData.output1[0].ErrMsg != null){

	  			//GHR 로 가는 경우
	  			if(v_DraftSts == "D60"){
	  				// 예산 부족이니 가면 안됨
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
	  			}else if(v_DraftSts == "D61"){
	  				// 반납의 경우 예산 부족이니 진행되면 안됨
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
	  				// 예산 부족해도 문서 저장은 가능
	  				// 저장
	  				cf_adjustDocSave();
	  			}

	  			return;


	  		}else{

//	  			gf_AlertMsg("사용 가능 합니다.");

	  			if(v_DraftSts == "D60"){
	  				cf_adjustDraft(v_DraftSts);
	  			}else if(v_DraftSts == "D61"){
	  				// 저장
	  				cf_adjustDraft(v_DraftSts);
	  			}else if(v_DraftSts == "D80"){
	  				// 저장
	  				cf_adjustDraft(v_DraftSts);
	  			}else{
	  				// 저장
	  				cf_adjustDocSave();
	  			}

	  		}

	  		$(".ajax_overlay").remove();

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
	  	case "SELECT_COSIGN_LINE" :
	  		//do nothing
	  		ds_Cmas008.clear();
	  		ds_Cmas008.setAllData(resultData.ds_Cmas008);

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
	for(var i = 0; i < ds_Visa.size(); i++){
		if(ds_Visa.get(i, "visaOwnYn") != ""){
			try{
				v_TripNat = ds_Visa.get(0, "natCd");
			}catch(e){
			}
		}
	}

	if(v_TripNat == ""){
		alert("출장국가를 가져올 수 없습니다.");
		self.close();
		return;
	}

	$("#tripNat").val(cf_GetNatNm(v_TripNat));

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
	$("#payDate").val($.datepicker.formatDate("yy-mm-dd", new Date));

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
			cf_retrieveSignLine();
		}else{

			if(fromReject == "Y"){

				ds_SavedDoc.setAllData(datas.ds_SavedDoc);

				cf_setSavedAdjustDoc();


			}else{
				// 증빙일자 오늘 날짜로 셋팅
				$("#oDate").val($.datepicker.formatDate("yy-mm-dd", new Date()));

				cf_retrieveSignLine();

			}

			//default Setting
		}

	f_calcTotal();



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

function cf_AppendSavedExpn(obj){

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


	// 기준액 채우기

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


//	result[i].ddExpnDcnt

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



	// 개인지급 합계 (체재바 + 항공료)
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
	if(obj == "" || obj == undefined) obj = 0;

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

	ref = removeComma(ref);

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

	// 상신시 Modal처리
	gf_InitSpinner(top.$('body'));


	/***********************************************************************************
	 * SAP Parameter Init Start
	 */

	var v_BdgtType = v_IfParam.bdgtType; //경비구분(P:사업, I:일반, C:특정, R:기술연구원경비, Q:현장경비, Q1~5:PJ경비)
	var IBdgtType = v_BdgtType.substring(0,1); // 경비구분

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
	var ISendDate = $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", $("#oDate").val())); //증빙일
	var ITravId = v_IfParam.tripUserId; //출장자사번
	var ITravTeam = v_IfParam.tripUserOrgCd; //출장자팀
	var ITravFdate = $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", $("#rTripDateStart").val())); //출장시작일
	var ITravTdate = $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", $("#rTripDateEnd").val())); //출장종료일
	var ITravArea = v_TripNat; // 출장지역

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

	var IPaymDate = $("#payDate").val(); // 지불/반납예정일
	if(IPaymDate != ""){
		IPaymDate = $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", IPaymDate));
	}
	var IRetUsnam = ""; // 반납자
	var IRetDatum = ""; // 반납일
	var IRetMethod = ""; // 반납방법
	var IRetBank = ""; // 반납은행
	if(IClearType == "1"){
		// 만약 정산 구분이 반납이라면 값을 셋팅하여 넘긴다.
		IRetUsnam = $("#payRutUser_id").val(); //
		IRetDatum = $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", $("#payRutDate").val())); // 반납일
		IRetMethod = "은행"; // 반납방법
		IRetBank = $("#culkum").val().substr(0, 3); // 반납은행 "B200" 일경우 B20 만 보낸다. 맨 뒷자리는 값저장을 위한 일종의 플래그
	}else if(IClearType == "3"){
		// 만약 정산 구분이 반납이라면 값을 셋팅하여 넘긴다.
		IRetUsnam = $("#payRutUser_id").val(); //
		IRetDatum = $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", $("#payRutDate").val())); // 반납일
		IRetMethod = "은행"; // 반납방법
		IRetBank = $("#culkum").val().substr(0, 3); // 반납은행 "B200" 일경우 B20 만 보낸다. 맨 뒷자리는 값저장을 위한 일종의 플래그
	}


	var IAirFare = removeComma($("#af2").text()); // 신청 항공료
	var IDayAmt = v_cTotalAmt2; // 신청 체제비

	var IPosid = ""; // 작업 분석 구조 요소(WBS 요소)
	if(IBdgtType == "R"){
	// R 인 경우 예산번호
		IPosid = IBdgtNo;
	}else{
		IPosid = "";
	}

	var IBukrs = "1000"; // 회사코드
	var IGitaAmt = v_GitaAmt; // 기타비용(현지항공료+현지교통비+VISA FEE+Over charge)
	var IGitaAmt2 = v_GitaAmt2; // 기타비용 (복리후생비)
	var IGitaAmt3 = v_GitaAmt3; // 기타비용 (접대비)

	/**
	 * SAP Parameter Init End
	 */
	/**
	 * DOC Parameter Init Start
	 */
	var assDtl = $("#assDtl").val();
	if(v_AdjustDocSts == "D60"){
		var dt = new Date();
		var month = dt.getMonth()+1;
		if(dt.getMonth()+1 < 10) month = "0" + (dt.getMonth()+1);
		var dayM = dt.getDate();
		if(dt.getDate() < 10) dayM = "0" + (dt.getDate());

		for(var i = 0; i < assDtl.length; i++){
			assDtl = assDtl.replace("\n", "##");
		}

		assDtl = assDtl + "##" + dt.getFullYear() + "-" + month + "-" + dayM + " " + gv_userId + " " + gv_userNm + " " + gv_orgNm + " 협조완료";


	}else if(v_AdjustDocSts == "D61"){
		var dt = new Date();
		var month = dt.getMonth()+1;
		if(dt.getMonth()+1 < 10) month = "0" + (dt.getMonth()+1);
		var dayM = dt.getDate();
		if(dt.getDate() < 10) dayM = "0" + (dt.getDate());

		for(var i = 0; i < assDtl.length; i++){
			assDtl = assDtl.replace("\n", "##");
		}

		assDtl = assDtl + "##" + dt.getFullYear() + "-" + month + "-" + dayM + " " + gv_userId + " " + gv_userNm + " " + gv_orgNm + " 반납완료";
	}

	var ghrComment = $("#ghrComment").val();


	for(var i = 0; i < ds_Visa.size(); i++){

		if(ds_Visa.get(i, "visaOwnYn") == ""){
			ds_CityList.add(ds_Visa.get(i));
		}

	}

	/**
	 * DOC Parameter Init End
	 */
	/**
	 * SGNS Parameter Init Start
	 */
	var drafterId = v_IfParam.drafterUserId + " " + v_IfParam.drafterUserKnm; // 작성자 ID
	var drafterOrg = v_DraftUserOrgCd + " " + v_DraftUserOrgNm; // 작성자 ORG

	var fileAtch = gf_IsNull(v_FileAtchId) ? ""          : v_FileAtchId;


	var mSignType = "N";
	if(v_DraftSts == "D70"){
		mSignType = "Y";
	}

	//20150925 추가 legacy Info
	var af1 = removeComma($("#af1").text());
	var af2 = removeComma($("#af2").text());
	var af3 = removeComma($("#af3").text());

	var usNightRef = removeComma($("#usNightRef").text());
	var usDayRef = removeComma($("#usDayRef").val());
	var eduRef = removeComma($("#eduRef").val());
	var spotRef = removeComma($("#spotRef").val());
	var euNightRef = removeComma($("#euNightRef").text());
	var euDayRef = removeComma($("#euDayRef").val());
	var enNightRef = removeComma($("#enNightRef").text());
	var enDayRef = removeComma($("#enDayRef").val());
	var jaNightRef = removeComma($("#jaNightRef").text());
	var jaDayRef = removeComma($("#jaDayRef").val());

	var usNightDay = removeComma($("#usNightDay").text());
	var usDayDay = removeComma($("#usDayDay").text());
	var eduDay = removeComma($("#eduDay").text());
	var spotDay = removeComma($("#spotDay").text());
	var euNightDay = removeComma($("#euNightDay").text());
	var euDayDay = removeComma($("#euDayDay").text());
	var enNightDay = removeComma($("#enNightDay").text());
	var enDayDay = removeComma($("#enDayDay").text());
	var jaNightDay = removeComma($("#jaNightDay").text());
	var jaDayDay = removeComma($("#jaDayDay").text());

	var usd = v_USD;
	var eur = v_EUR;
	var gbp = v_GBP;
	var jpy = v_JPY;


	var sType = "N";

	// 기타경비 0 이상 체크
	if(parseInt(removeComma($("#etcTotal1").text())) > 0){
		sType = "Y";
	}else if(parseInt(removeComma($("#etcTotal2").text())) > 0){
		sType = "Y";
	}

	// JSON 개행 처리
	var itReq = $("#tReq").val();
	for(var i = 0; i < itReq.length; i++){
		itReq = itReq.replace("\n", "##");
	}
	var itPlanDtl = $("#tPlanDtl").val();
	for(var i = 0; i < itPlanDtl.length; i++){
		itPlanDtl = itPlanDtl.replace("\n", "##");
	}
	var itRptDtl = $("#tRptDtl").val();
	for(var i = 0; i < itRptDtl.length; i++){
		itRptDtl = itRptDtl.replace("\n", "##");
	}
	var iexecDtl = $("#execDtl").val();
	for(var i = 0; i < iexecDtl.length; i++){
		iexecDtl = iexecDtl.replace("\n", "##");
	}
	var irem = $("#rem").val();
	for(var i = 0; i < irem.length; i++){
		irem = irem.replace("\n", "##");
	}


	/**
	 * SGNS Parameter Init End
	 */

	var params = {
			// 예산체크 파라미터 시작
			AirAmt : removeComma($("#af2").text()), // 항공료 실사용분
			StayAmt : removeComma($("#cTotalAmt3").text()), // 체제비 차액
			EtcLairAmt : removeComma($("#loAirFareEx2").text()), // 현지항공료 실사용분
			EtcLtrAmt : removeComma($("#loTranFareEx2").text()), // 현지교통비 실사용분
			EtcVisaAmt : removeComma($("#visaFeeFareEx2").text()), // VISA FEE 실사용분
			EtcOverAmt : removeComma($("#ovCharFareEx2").text()), // OVER CHARGE 실사용분
			EtcWelAmt : removeComma($("#vocLeeFareEx2").text()), // 복리후생 실사용분
			EtcRcAmt : removeComma($("#hostFareEx2").text()), // 접대비 실사용분
			//ISendDateC : $.datepicker.formatDate("yymmdd", new Date()),
			ISendDateC : ISendDate,
			// 예산체크 파라미터 끝
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
			IGitaAmt3 : IGitaAmt3,
			// Doc
			docNo : v_DocNo,
//			refNo : v_SapResult.ORefkeyNo,
			stlCls : $("#jsGubun").val(),
			proofYmd : $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", $("#oDate").val())),
			bustrNatCd : ds_NatList.get(ds_NatList.find("natNm", $("#tripNat").val()), "natCd"),
			bustrStYmd : $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", $("#rTripDateStart").val())),
			bustrEdYmd : $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", $("#rTripDateEnd").val())),
			payScdYmd : $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", $("#payDate").val())),
			demItm : $("#tReq").val(),
			bustrGl : $("#tPurp").val(),
			dtlSch : $("#tPlanDtl").val(),
			dutyRptCont : $("#tRptDtl").val(),
			acqDat : $("#tDataList").val(),
			genAccomoDcnt : $("#s1").val(),
			siteAccomoDcnt : $("#s2").val(),
			rtrnMeth : "은행",
			rmtAccntBnk : $("#culkum option:selected").text(),
			rmtAcctno : v_IfParam.accountNo,
			lclAfare : removeComma($("#loAirFare2").val()),
			lclTrafficExpn : removeComma($("#loTranFare2").val()),
			visaFee : removeComma($("#visaFeeFare2").val()),
			excsExpn : removeComma($("#ovCharFare2").val()),
			wef : removeComma($("#vocLeeFare2").val()),
			svcExpn : removeComma($("#hostFare2").val()),
			doDtlItem : $("#execDtl").val(),
			rem : $("#rem").val(),
			fileAtchId : fileAtch,
			userId : v_IfParam.tripUserId,
//			ifParam : JSON.stringify(data),
			docSts : "D02",
			ghrComment : ghrComment,
			// SGNS
			dutySysCd : "SGNS", // DUTYSYSCD
	  		programCode : "SGNS070005", // 양식코드
	  		signDocTitle : "해외출장 보고서 및 정산서", // 양식 제목
			ordDate : $.datepicker.formatDate("yymmdd", new Date), // 기표일자
			ordNo : "", // 전표번호
			drafterId : v_IfParam.drafterUserId + " " + v_IfParam.drafterUserKnm, // 작성자 ID
			drafter : drafterId,
			drafterOrg : v_DraftUserOrgCd + " " + v_DraftUserOrgNm, // 작성자 ORG
			drafterOrgNm : drafterOrg,
			tripUser : v_IfParam.tripUserId + " " + v_IfParam.tripUserKnm,
			tripUserId : v_IfParam.tripUserId + " " + v_IfParam.tripUserKnm,
			tripUserInfo : $("#tripUserInfo").text(),
			tripNat : $("#tripNat").val(),
			tripDate : "최초 : " + $("#fTripDate").text(),
			realTripDate : "실사용 : " + $("#rTripDateStart").val() + " ~ " + $("#rTripDateEnd").val() + " (총 " + $("#rTripDate").text() + "일)",
			payDate : $("#payDate").val(),
			tReq : $("#tReq").val(),
			tPurp : $("#tPurp").val(),
			tPlanDtl : $("#tPlanDtl").val(),
			tDataList : $("#tDataList").val(),
			s1 : $("#s1").val(),
			s2 : $("#s2").val(),
			s3 : $("#s3").val(),
			bdgtType : $("#bdgtType").text(),
			cGubun : $("#cGubun").text(),
			aufnr : $("#aufnr").text(),
			kostv : $("#kostv").text(),
			drafterInfo : $("#drafterInfo").text(),
			assDtl : $("#assDtl").val(),
//			refNo : v_SapResult.ORefkeyNo,
			fileAtchId : fileAtch,
			mSignType : mSignType,
			af1 : af1,
			af2 : af2,
			af3 : af3,
			usNightRef : usNightRef,
			usDayRef : usDayRef,
			eduRef : eduRef,
			spotRef : spotRef,
			euNightRef : euNightRef,
			euDayRef : euDayRef,
			enNightRef : enNightRef,
			enDayRef : enDayRef,
			jaNightRef : jaNightRef,
			jaDayRef : jaDayRef,
			usNightDay : usNightDay,
			usDayDay : usDayDay,
			eduDay : eduDay,
			spotDay : spotDay,
			euNightDay : euNightDay,
			euDayDay : euDayDay,
			enNightDay : enNightDay,
			enDayDay : enDayDay,
			jaNightDay : jaNightDay,
			jaDayDay : jaDayDay,
			usd : usd,
			eur : eur,
			gbp : gbp,
			jpy : jpy,
			sType : sType,
			// ifParam
			jsGubun : $("#jsGubun").val(),
			tripNat : $("#tripNat").val(),
			rTripDateStart : $("#rTripDateStart").val(),
			rTripDateEnd : $("#rTripDateEnd").val(),
			tPurp : $("#tPurp").val(),
			tDataList : $("#tDataList").val(),
			Culkum : $("#culkum").val(),
			loAirFare2 : $("#loAirFare2").val(),
			loTranFare2 : $("#loTranFare2").val(),
			visaFeeFare2 : $("#visaFeeFare2").val(),
			ovCharFare2 : $("#ovCharFare2").val(),
			vocLeeFare2 : $("#vocLeeFare2").val(),
			hostFare2 : $("#hostFare2").val(),
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
			assDtl : assDtl,
			payRutUserId : $("#payRutUser_id").val(),
			payRutUserName : $("#payRutUser_name").val(),
			payRutDate : $("#payRutDate").val(),
			oDate : $("#oDate").val(),
			// 개행처리 param
			itReq : itReq,
			itPlanDtl : itPlanDtl,
			itRptDtl : itRptDtl,
			iexecDtl : iexecDtl,
			irem : irem,
			// 2016-04-21 최초 사용일 수 포함
			startDate : $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", v_IfParam.startDate)),
			endDate : $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", v_IfParam.endDate))
	};

	var draftDataSet = {
			ds_Signln : ds_Signln.getAllData()
	};

	gf_Transaction("SELECT_SETTLE_OS_BIZ_TIP", "/trip/eai/settleOsBizTrip.xpl", params, draftDataSet, "f_Callback", true);

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
			assDtl : $("#assDtl").val(),
			fileAtchId : v_FileAtchId,
			tripNat : v_TripNat,
			oDate : $("#oDate").val()

	};

	var ghrComment = $("#ghrComment").val();


	if(v_AdjustDocSts == ""){

		var params = {
				docNo : v_DocNo,
				docSts : "D16",
				ifParam : JSON.stringify(data),
				mailYn : "N"
		};

		var dataSet  = {
				ds_Sign : ds_Signln.getAllData("I")
		};

		if(fromReject == 'Y'){
			gf_Transaction("SAVE_ADJUST_DOC", "/trip/outerTrip/updateAdjustDoc.xpl", params, dataSet, "f_Callback", true);
		}else{
			gf_Transaction("SAVE_ADJUST_DOC", "/trip/outerTrip/saveAdjustDoc.xpl", params, dataSet, "f_Callback", true);
		}
	}else if(v_AdjustDocSts == "D16" || v_AdjustDocSts == "D64"){
		var params = {
				docNo : v_DocNo,
				docSts : "D16",
				ifParam : JSON.stringify(data),
				mailYn : "N"
		};

		var dataSet  = {
				ds_Sign : ds_Signln.getAllData("I")
		};


		gf_Transaction("SAVE_ADJUST_DOC", "/trip/outerTrip/updateAdjustDoc.xpl", params, dataSet, "f_Callback", true);

	}else if(v_AdjustDocSts == "D60"){

		var params = {
				docNo : v_DocNo,
				docSts : "D60",
				ifParam : JSON.stringify(data),
				mailYn : "N",
				ghrComment : ghrComment
		};

		var dataSet  = {
				ds_Sign : ds_Signln.getAllData("I")
		};

		gf_Transaction("SAVE_ADJUST_DOC", "/trip/outerTrip/updateAdjustDoc.xpl", params, dataSet, "f_Callback", true);
	}else{

		var params = {
				docNo : v_DocNo,
				docSts : "D16",
				ifParam : JSON.stringify(data),
				mailYn : "N"
		};

		var dataSet  = {
				ds_Sign : ds_Signln.getAllData("I")
		};

		gf_Transaction("SAVE_ADJUST_DOC", "/trip/outerTrip/saveAdjustDoc.xpl", params, dataSet, "f_Callback", true);
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
	}

	if(!(data.fileAtchId == "undefined" || data.fileAtchId == undefined)){

		v_FileAtchId = data.fileAtchId;

		// 첨부파일이 존재하는 경우
		gf_retrieveFileList(data.fileAtchId);
	}

	cf_AppendSavedExpn(ds_Expn.getAllData());

	// 증빙일자
	$("#oDate").val(data.oDate);


	$("#jsGubun").val(data.jsGubun);

	if($("#jsGubun").val() == "N"){
//		$("#payRutDate").show();
		$("#culkum").show();
		$("#retCost").hide();

		$("#payRutUser_id").val(data.payRutUserId);
		$("#payRutUser_name").val(data.payRutUserName);

		$("#payRutDate").val(data.payRutDate);

	}else if($("#jsGubun").val() == "R"){
		$("#culkum").show();
		$("#retCost").show();

		$("#payRutUser_id").val(data.payRutUserId);
		$("#payRutUser_name").val(data.payRutUserName);

		$("#payRutDate").val(data.payRutDate);

	}

//	$("#tripNat").val(data.tripNat);
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

	// 반려 재작성일 경우는 생략
	if(fromReject != "Y"){
		var assDtl = data.assDtl;
		assDtl = assDtl.replace("##", "\n");
		$("#assDtl").val(assDtl);
	}

	//출장국가
	v_TripNat = data.tripNat;

	if(v_TripNat != undefined){
		if(ds_NatList.find("natCd", v_TripNat) == -1){
			if(ds_NatList.find("natNm", v_TripNat) != -1){
				v_TripNat = ds_NatList.get(ds_NatList.find("natNm", v_TripNat), "natCd");
			}
		}
	}

	$("#tripNat").val(ds_NatList.get(ds_NatList.find("natCd", v_TripNat), "natNm"));

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

	if(fromReject == "Y"){
		// 결재선 생성
		ds_Signln.setAllData(datas.ds_Signln);

		for ( var i = 0; i < ds_Signln.size(); i++) {
				ds_Signln.set(i, "signDt", "");
				// 협의 제거
				if(ds_Signln.get(i, "signTpCd") == "T03"){
					ds_Signln.remove(i);
				}
  				if(ds_Signln.get(i, "signStsCd") == "S05"){
  					ds_Signln.set(i, "signStsCd", "S04");
  				}

				// seq 재정렬
				ds_Signln.set(i, "signSeq", i+1);
		}

		gf_AssembleSignln(ds_Signln);
	}else{
		//20150919 저장된 결재선을 불러온다.
		cf_RetrieveSignInfo();
	}

	f_calcTotal();

	f_calcExpnAmt();

	if(v_AdjustDocSts == "D61"){
		f_setDocReadOnly();
	}
}

function cf_adjustDraft(adjustDocSts){

	// 협조의뢰 전 숙박시설 이용 관련 모듈 Start
	//C. 특정경비-팀 || B. 특정경비-팀장 || A. 특정경비-임원 || I. 일반경비 일때만 숙박시설 유무 체크
	var fileAtch = gf_IsNull(v_FileAtchId) ? ""          : v_FileAtchId;
	var bdgtType = $("#bdgtType").text().substr(0,1);
	// 숙소이용일 수 체크
	var s1 = $("#s1").val();

	//alert("첨부 : "+ fileAtch);
	//alert("첨부 랭스 : "+gv_Files.length);
	//gv_FileAtchId

	//if((bdgtType=="Q" || bdgtType=="C" || bdgtType=="B" || bdgtType=="A" || bdgtType=="I") && parseInt(s1) > 0 && (gv_Files.length == 0 && fileAtchId == "")){
	//오픈전 bdgtType=="Q" 삭제


	//출장일이 2018.06.01 이후 출장일부터 체크함
	if($("#rTripDateStart").val() != ""){
		var sDate = $("#rTripDateStart").val().split("-");
		var sDateTemp = sDate[0] + sDate[1] + sDate[2];

		if(sDateTemp >= 20180601){
			// C. 특정경비-팀 || B. 특정경비-팀장 || A. 특정경비-임원 || I. 일반경비 일때만 숙박시설 이용 시 첨부파일 체크
			if((bdgtType=="C" || bdgtType=="B" || bdgtType=="A" || bdgtType=="I") && parseInt(s1) > 0 && fileAtch ==""){
				alert("해외출장기간 내 외부 숙박시설을 이용했을 경우,\n해당 영수증을 증빙자료로 첨부하여야 합니다.\n영수증 첨부파일이 존재하지 않을 경우\n정산서 검토단계로 넘어가지 않습니다.");
				return;
			}
		}
	}

	var confirmText = "";
	if(adjustDocSts == "D16"){
		confirmText = "문서를 협조의뢰 하시겠습니까?";
	}else if(adjustDocSts == "D50"){
		confirmText = "문서를 협조의뢰 하시겠습니까?";
	}else if(adjustDocSts == "D60"){
		confirmText = "문서를 협조의뢰 하시겠습니까?";
	}else if(adjustDocSts == "D80"){
		confirmText = "문서를 협조의뢰 하시겠습니까?";
	}else if(adjustDocSts == "D61"){
		confirmText = "문서를 검토완료 하시겠습니까?";
	}

	var assDtl = $("#assDtl").val();

	//메일전송
	// 메일 전송을 위한 플래그 Y 일경우만 메일을 전송한다.
	var mailYn = "N";
	var ds_Mail = new DataSet();
	var mailParams;

	if(adjustDocSts == "D61"){
		var dt = new Date();
		var month = dt.getMonth()+1;
		if(dt.getMonth()+1 < 10) month = "0" + (dt.getMonth()+1);
		var dayM = dt.getDate();
		if(dt.getDate() < 10) dayM = "0" + (dt.getDate());

		assDtl = assDtl + "##" + dt.getFullYear() + "-" + month + "-" + dayM + " " + gv_userId + " " + gv_userNm + " " + gv_orgNm + " 협조완료";

		// 반납 대상자에게 메일 발송
//		제목 : [해외출장정산서] 반납정보 입력을 요청합니다.
//		내용 : ■ 출장자 : 김철수 부장
//		      ■ 문서상태 : 반납확인중
//		      ■ 목적지 : 나이지리아
//		      ■ 출장기간 : 20150611~20150614

		mailYn = "Y";

		mailParams = {
				spec : "",
				bodyTemplate : "",
				fromMailId : "",
				fromMailName : "해외출장담당자",
				mailId : v_IfParam.tripUserId + "@daewooenc.com", // 운영시에는 담당자로 변경할 것
				mailSubject : "[해외출장정산서] 반납정보 입력을 요청합니다." ,
				htmlBody : "<BR>■ 출 장 자 : " + $("#tripUserInfo").text() + "<br>"
						 + "■ 문서상태 : 반납확인중" + "<br>"
						 + "■ 목 적 지 : " + $("#tripNat").val() + "<br>"
						 + "■ 출장기간 : " + $("#rTripDateStart").val() + " ~ " + $("#rTripDateEnd").val()
		};
	}else if(adjustDocSts == "D80"){

		mailYn = "Y";

		for(var i = 0; i < v_CmasList.length; i++){
				if(v_CmasList[i].privCd == "RO_CMAS_OT_004"){

					mailParams = {
							spec : "",
							bodyTemplate : "",
							fromMailId : "",
							fromMailName : "해외출장담당자",
							mailId : v_CmasList[i].userId + "@daewooenc.com", // 운영시에는 담당자로 변경할 것
							mailSubject : "[해외출장정산서](출장자: " + v_IfParam.tripUserKnm + " " + v_IfParam.tripUserId + ")협조를 의뢰합니다." ,
							htmlBody : "<BR>■ 출 장 자 : " + v_IfParam.tripUserKnm + " " + v_IfParam.tripUserPositCd + "<br>"
								     + "■ 문서상태 : 경영관리팀 검토요청" + "<br>"
									 + "■ 목 적 지 : " + $("#tripNat").val() + "<br>"
									 + "■ 출장기간 : " + $("#rTripDateStart").val() + " ~ " + $("#rTripDateEnd").val()
					};
			}
		}
	}


	var oDate = $("#oDate").val();

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
				assDtl : assDtl,
				fileAtchId : v_FileAtchId,
				tripNat : v_TripNat,
				oDate : oDate,
				payRutUserId : $("#payRutUser_id").val(),
				payRutUserName : $("#payRutUser_name").val(),
				payRutDate : $("#payRutDate").val(),
				//20151001 GHR 기준액 수정 저장
				usDayRef : $("#usDayRef").val(),
				eduRef : $("#eduRef").val(),
				spotRef : $("#spotRef").val(),
				euDayRef : $("#euDayRef").val(),
				enDayRef : $("#enDayRef").val(),
				jaDayRef : $("#jaDayRef").val()
		};

		// mailParams 삽입
		ds_Mail.add(mailParams);

		var ghrComment = $("#ghrComment").val();

		var params = {
				docNo : v_DocNo,
				docSts : adjustDocSts,
				ifParam : JSON.stringify(data),
				mailYn : mailYn,
				ghrComment : ghrComment
		};

		var dataSet  = {
				ds_Sign : ds_Signln.getAllData("I"),
				input1 : ds_Mail.getAllData()
		};

		if(v_AdjustDocSts == ""){
			if(fromReject == "Y"){
				gf_Transaction("SAVE_ADJUST_DOC", "/trip/outerTrip/updateAdjustDoc.xpl", params, dataSet, "f_Callback", true);
			}else{
				gf_Transaction("SAVE_ADJUST_DOC", "/trip/outerTrip/saveAdjustDoc.xpl", params, dataSet, "f_Callback", true);
			}
		}else{
			gf_Transaction("SAVE_ADJUST_DOC", "/trip/outerTrip/updateAdjustDoc.xpl", params, dataSet, "f_Callback", true);
		}
	}
}


function cf_retrieveSignLine(){


	// 기타경비로 인한 결재선 생성시에는 결재선을 조회하지 않는다.
	if(v_bossSignYn == "Y"){
		return;
	}else if(v_HQSign == "Y"){
		return;
	}

	// 상신시 Modal처리
	gf_InitSpinner(top.$('body'));

	// 팀장 이상 직책일 체크
	if(ds_ChiefCd.find("value", v_IfParam.tripUserRpswrkCd) != -1){
		v_IsCheif = "Y";
	}else{
		if(v_IfParam.tripUserPositCd == "상무"){
			v_IsCheif = "Y";
		}

		if(v_IfParam.tripUserPositCd == "전무"){
			v_IsCheif = "Y";
		}
	}


	// 본부장 이상 직책일 체크
	if(ds_HqRpswrkCd.find("value", v_IfParam.tripUserRpswrkCd) != -1){
		orgCls = "본부/실";
	}else{
		orgCls = "팀/현장";
	}

	if(v_IfParam.tripUserId == undefined){
		alert('출장자 정보를 가져올 수 없습니다.');
		self.close();
	}

	// 화면 상단 결재선 셋팅
	// 현장소속의 경우 현장담당책임자를 가져오지않으므로 수동으로 중간협의자로 넣어줘야한다.
	var params = {
  		orgCd : v_IfParam.tripUserOrgCd,
  		orgCls : orgCls,
  		userId : v_IfParam.tripUserId
	};
//
//	var params = {
//	  	orgCd : gv_orgCd,
//	  	orgCls : orgCls,
//	  	userId : gv_userId
//	};

	gf_Transaction("SELECT_COSIGN_LINE", "/trip/outerTrip/retrieveCoSignList.xpl", params, {}, "f_Callback", false);
	gf_Transaction("SELECT_SIGN_LINE", "/co/common/user/retrieveSignlnForExcluRegl.xpl", params, {}, "f_Callback", true);
}

function cf_setSignlnInfo(resultData) {
	ds_Signln.clear();

	var cnt = 0;

	ds_Signln.insert(cnt, {
		signId : "",
		signSeq : cnt + 1,
		signTpCd : "T01",
		signUserId : gv_userId, // 세션에서 받아온 값
		signUserNm : gv_userNm, // 세션에서 받아온 값
		psignUserNm : "",
		apperPositCd : gv_userPositCd,
		apperPositNm : gv_userPositCd,
		perpsignPositNm : "",
		apperRpswrkCd : "",
		apperRpswrkNm : "",
		apperOrgCd : gv_orgCd,
		apperOrgNm : gv_orgNm,
		orgChrcCls : "D"
	});

	//결재선에 있는지
	if( ds_Signln.find("signUserId",ds_Cmas008.get(0,"signUserId")) > -1 ){
		//alert("결재선 중복");
	}else{

		cnt++

		//협의자
		ds_Signln.insert(cnt, {
			signId : "",
			signSeq : cnt + 1,
			signTpCd : "T03",
			signUserId : ds_Cmas008.get(0,"signUserId"),
			signUserNm : ds_Cmas008.get(0,"signUserNm"),
			psignUserNm : "",
			apperPositCd : ds_Cmas008.get(0,"apperPositCd"),
			apperPositNm : ds_Cmas008.get(0,"apperPositNm"),
			perpsignPositNm : "",
			apperRpswrkCd : ds_Cmas008.get(0,"apperRpswrkCd"),
			apperRpswrkNm : ds_Cmas008.get(0,"apperRpswrkNm"),
			apperOrgCd : ds_Cmas008.get(0,"apperOrgCd"),
			apperOrgNm : ds_Cmas008.get(0,"apperOrgNm"),
			orgChrcCls : "D",
			canDelete : "N" // 결재선 삭제 불가능
		});

	}


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

		if(resultData.ds_SignlnForExcluRegl == undefined){

			// 전결상 가져오는 사람이 없어 undefined 일 경우 처리

			// 본부장 이상 직책일 체크
			// 본부장 이상일 시 운영서버에서 자신이 오지 않으므로 전결 처리를 강제로 해준다.
			if(ds_HqRpswrkCd.find("value", v_IfParam.tripUserRpswrkCd) != -1){
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
				// 팀장 이상의 경우는 일반이나 반납의 경우 자신 전결
				if(v_IsCheif == "Y"){
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

				}
			}

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

		}

	}

	gf_AssembleSignln(ds_Signln);

	gf_DisableCurrentOverlay();

	$(".ajax_overlay").remove();
}

function cf_sgnsRemoteDraft(){

	var assDtl = $("#assDtl").val();
	if(v_AdjustDocSts == "D60"){
		var dt = new Date();
		var month = dt.getMonth()+1;
		if(dt.getMonth()+1 < 10) month = "0" + (dt.getMonth()+1);
		var dayM = dt.getDate();
		if(dt.getDate() < 10) dayM = "0" + (dt.getDate());

		for(var i = 0; i < assDtl.length; i++){
			assDtl = assDtl.replace("\n", "##");
		}

		assDtl = assDtl + "##" + dt.getFullYear() + "-" + month + "-" + dayM + " " + gv_userId + " " + gv_userNm + " " + gv_orgNm + " 협조완료";

	}

	var ghrComment = $("#ghrComment").val();


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
			assDtl : assDtl,
			payRutUserId : $("#payRutUser_id").val(),
			payRutUserName : $("#payRutUser_name").val(),
			payRutDate : $("#payRutDate").val(),
			oDate : $("#oDate").val(),
			fileAtchId : v_FileAtchId,
			//20151001 GHR 기준액 수정 저장
			usDayRef : $("#usDayRef").val(),
			eduRef : $("#eduRef").val(),
			spotRef : $("#spotRef").val(),
			euDayRef : $("#euDayRef").val(),
			enDayRef : $("#enDayRef").val(),
			jaDayRef : $("#jaDayRef").val()
	};

	for(var i = 0; i < ds_Visa.size(); i++){

		if(ds_Visa.get(i, "visaOwnYn") == ""){
			ds_CityList.add(ds_Visa.get(i));
		}

	}
	var fileAtch = gf_IsNull(v_FileAtchId) ? ""          : v_FileAtchId;

	var draftParams = {
			docNo : v_DocNo,
			refNo : v_SapResult.ORefkeyNo,
			stlCls : $("#jsGubun").val(),
			proofYmd : $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", $("#oDate").val())),
			bustrNatCd : ds_NatList.get(ds_NatList.find("natNm", $("#tripNat").val()), "natCd"),
			bustrStYmd : $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", $("#rTripDateStart").val())),
			bustrEdYmd : $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", $("#rTripDateEnd").val())),
			payScdYmd : $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", $("#payDate").val())),
			demItm : $("#tReq").val(),
			bustrGl : $("#tPurp").val(),
			dtlSch : $("#tPlanDtl").val(),
			dutyRptCont : $("#tRptDtl").val(),
			acqDat : $("#tDataList").val(),
			genAccomoDcnt : $("#s1").val(),
			siteAccomoDcnt : $("#s2").val(),
			rtrnMeth : "은행",
			rmtAccntBnk : $("#culkum option:selected").text(),
			rmtAcctno : v_IfParam.accountNo,
			lclAfare : removeComma($("#loAirFare2").val()),
			lclTrafficExpn : removeComma($("#loTranFare2").val()),
			visaFee : removeComma($("#visaFeeFare2").val()),
			excsExpn : removeComma($("#ovCharFare2").val()),
			wef : removeComma($("#vocLeeFare2").val()),
			svcExpn : removeComma($("#hostFare2").val()),
			doDtlItem : $("#execDtl").val(),
			rem : $("#rem").val(),
			fileAtchId : fileAtch,
			userId : v_IfParam.tripUserId,
			ifParam : JSON.stringify(data),
			docSts : "D02",
			ghrComment : ghrComment

	};

	gf_Transaction("SELECT_OUTER_TRIP_DRAFT", "/trip/outerTrip/updateAdjustDraftDoc.xpl", draftParams, {}, "f_Callback", true);

}

function f_callBackFuncSelNat(obj){

	v_TripNat = obj.natCd;
	$("#tripNat").val(obj.natNm);

}

function f_notSpace(val){
	if(val == "" || val == undefined) val = 0;
	return val;
}

function f_bossSignProcess(){

	// GHR 검토중일경우
	if(v_AdjustDocSts == "D60") return;
	//접대비 + 복리후생 = 1400 이상 사장 전결

		hostCheck2 = parseInt(f_notSpace(removeComma($("#vocLeeFare2").val()))) + parseInt(f_notSpace(removeComma($("#hostFare2").val())));

		if(hostCheck > 1400){
			if(hostCheck2 > hostCheck){
				if(v_bossSignYn == "N"){
					if(confirm("복리후생과 접대비의 합계가 $1400 이상의 경우 사장 전결이 필요합니다. 진행하시겠습니까?")){
						v_bossSignYn = "Y";
						orgCls = "회장";

						// 화면 상단 결재선 셋팅
						// 현장소속의 경우 현장담당책임자를 가져오지않으므로 수동으로 중간협의자로 넣어줘야한다.
						var params = {
						  		orgCd : v_IfParam.tripUserOrgCd,
						  		orgCls : orgCls,
						  		userId : v_IfParam.tripUserId
						};

						// 상신시 Modal처리
						gf_InitSpinner(top.$('body'));

						gf_Transaction("SELECT_SIGN_LINE", "/co/common/user/retrieveSignlnForExcluRegl.xpl", params, {}, "f_Callback", true);
					}else{
						var exVal = 0;

						$("#vocLeeFare2").val(exVal);
						$("#hostFare2").val(exVal);

						f_calcEtcAmt(e.currentTarget.value, "vocLeeFare1", "vocLeeFareEx2", "vocLeeFare3");
						f_calcEtcAmt(e.currentTarget.value, "hostFare1", "hostFareEx2", "hostFare3");
					}
				}

			}else{
				if(v_bossSignYn == "Y"){
					alert("복리후생과 접대비의 합계가 $1400 이하의 경우 전결규정에 따른 결재선으로 원상 복구 합니다.");
					v_bossSignYn = "N";
					cf_retrieveSignLine();
				}
			}
		}else{
			if(hostCheck2 > 1400){
				if(v_bossSignYn == "N"){
					if(confirm("복리후생과 접대비의 합계가 $1400 이상의 경우 사장 전결이 필요합니다. 진행하시겠습니까?")){
						v_bossSignYn = "Y";
						orgCls = "회장";

						// 화면 상단 결재선 셋팅
						// 현장소속의 경우 현장담당책임자를 가져오지않으므로 수동으로 중간협의자로 넣어줘야한다.
						var params = {
						  		orgCd : v_IfParam.tripUserOrgCd,
						  		orgCls : orgCls,
						  		userId : v_IfParam.tripUserId
						};

						// 상신시 Modal처리
						gf_InitSpinner(top.$('body'));

						gf_Transaction("SELECT_SIGN_LINE", "/co/common/user/retrieveSignlnForExcluRegl.xpl", params, {}, "f_Callback", true);
					}else{
						var exVal = 0;

						$("#vocLeeFare2").val(exVal);
						$("#hostFare2").val(exVal);

						f_calcEtcAmt(e.currentTarget.value, "vocLeeFare1", "vocLeeFareEx2", "vocLeeFare3");
						f_calcEtcAmt(e.currentTarget.value, "hostFare1", "hostFareEx2", "hostFare3");
					}
				}

			}else{
				if(v_bossSignYn == "Y"){
					alert("복리후생과 접대비의 합계가 $1400 이하의 경우 전결규정에 따른 결재선으로 원상 복구 합니다.");
					v_bossSignYn = "N";
					if($("#jsGubun") == "A"){
						//정산 구분이 추가 일 경우 본부까지 결재가 올라감

						// 상신시 Modal처리
						gf_InitSpinner(top.$('body'));

						// 본부장 이상 직책일 체크
						if(ds_HqRpswrkCd.find("value", v_IfParam.tripUserRpswrkCd) != -1){
							orgCls = "회장";
						}else{
							orgCls = "본부/실";
						}

						// 화면 상단 결재선 셋팅
						// 현장소속의 경우 현장담당책임자를 가져오지않으므로 수동으로 중간협의자로 넣어줘야한다.
						var params = {
					  		orgCd : v_IfParam.tripUserOrgCd,
					  		orgCls : orgCls,
					  		userId : v_IfParam.tripUserId
						};

						gf_Transaction("SELECT_SIGN_LINE", "/co/common/user/retrieveSignlnForExcluRegl.xpl", params, {}, "f_Callback", true);

					}else{
						cf_retrieveSignLine();
					}
				}
			}
		}
}

function fn_SetUploadCallback( fileAtchId ) {

	v_FileAtchId = fileAtchId;

	// 예산 체크 후 상태 변경
	cf_checkOsBizTrip();

}

function f_calcTotal(){

	var usdTotal3;
	var euroTotal3;
	var gbpTotal3;
	var jaTotal3;
	var cTotalAmt3;
	var TotalAmt3;

	usdTotal3 = parseFloat(removeComma($("#usdTotal2").text())) - parseFloat(removeComma($("#usdTotal").text()));
	euroTotal3 = parseFloat(removeComma($("#euroTotal2").text())) - parseFloat(removeComma($("#euroTotal").text()));
	gbpTotal3 = parseFloat(removeComma($("#gbpTotal2").text())) - parseFloat(removeComma($("#gbpTotal").text()));
	jaTotal3 = parseFloat(removeComma($("#jaTotal2").text())) - parseFloat(removeComma($("#jaTotal").text()));
	cTotalAmt3 = parseFloat(removeComma($("#cTotalAmt2").text())) - parseFloat(removeComma($("#cTotalAmt").text()));
	TotalAmt3 = parseFloat(removeComma($("#TotalAmt2").text())) - parseFloat(removeComma($("#TotalAmt").text()));

	$("#usdTotal3").text(gf_AmtFormat(usdTotal3));
	$("#euroTotal3").text(gf_AmtFormat(euroTotal3));
	$("#gbpTotal3").text(gf_AmtFormat(gbpTotal3));
	$("#jaTotal3").text(gf_AmtFormat(jaTotal3));
	$("#cTotalAmt3").text(gf_AmtFormat(cTotalAmt3));
	$("#TotalAmt3").text(gf_AmtFormat(TotalAmt3));
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

			if(id == "tPurp"){
				id = "출장목적";
			}else if(id == "tDataList"){
				id = "입수자료목록";
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
			}else if(id == "tPlanDtl"){
				id = "세부일정";
			}else if(id == "tRptDtl"){
				id = "업무보고내용";
			}else if(id == "execDtl"){
				id = "집행세부내역";
			}else if(id == "rem"){
				id = "비고";
			}

			gf_AlertMsg(id + " 란에 특수문자를 입력하실 수 없습니다.");
			return false;
		}
	}
	return true;
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

function cf_RetrieveSignInfo(){

	//해당 업무구분과 업무분류에 해당되는 양식명을 가져옴
	var signParams = {
			docNo : v_DocNo
	};

	gf_Transaction("SELECT_SIGN_INFO", "/trip/outerTrip/retrieveSignInfo3.xpl", signParams, {}, "f_Callback", true);
}


function f_rejectDoc(){

	var ipDatas = JSON.parse(ds_SavedDoc.get(0, "ifParam"));

	var assDtl  = ipDatas.assDtl;

	var dt = new Date();
	var month = dt.getMonth()+1;
	if(dt.getMonth()+1 < 10) month = "0" + (dt.getMonth()+1);
	var dayM = dt.getDate();
	if(dt.getDate() < 10) dayM = "0" + (dt.getDate());

	assDtl = assDtl + "##" + dt.getFullYear() + "-" + month + "-" + dayM + " " + gv_userId + " " + gv_userNm + " " + gv_orgNm + " 반려";

	ipDatas.assDtl = assDtl;

	var docSts = "D16";


	// v_AdjustDocSts == "D60" GHR 반려
	// 메일 전송을 위한 플래그 Y 일경우만 메일을 전송한다.
	var mailYn = "N";
	var ds_Mail = new DataSet();
	var mailParams;

	if(v_AdjustDocSts == "D60"){

		mailYn = "Y";

		docSts = "D64";

		mailParams = {
				spec : "",
				bodyTemplate : "",
				fromMailId : "",
				fromMailName : "해외출장담당자",
				mailId : v_IfParam.drafterUserId + "@daewooenc.com", // 운영시에는 담당자로 변경할 것
				mailSubject : "[해외출장정산서](출장자: " + v_IfParam.tripUserKnm + " " + v_IfParam.tripUserId + ") 출장정산서 반려되었습니다." ,
				htmlBody : "■ 작 성 자 : " + v_IfParam.drafterUserKnm + " " + v_IfParam.drafterUserPositCd + "<br>"
						 + "■ 출 장 자 : " + v_IfParam.tripUserKnm + " " + v_IfParam.tripUserPositCd + "<br>"
						 + "■ 문서상태 : 반려" + "<br>"
						 + "■ 반려사유 : " + v_RejectRes + "<br>"
						 + "■ 목 적 지 : " + $("#tripNat").val() + "<br>"
						 + "■ 출장기간 : " + $("#rTripDateStart").val() + " ~ " + $("#rTripDateEnd").val()
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

	}else if(v_AdjustDocSts == "D80"){

		mailYn = "Y";

		var natNm = "";

		if(ds_CityList.size() > 0){
			natNm = ds_NatList.get(ds_NatList.find("natCd", ds_CityList.get(0, "natCd")), "natNm");
		}

		mailParams = {
				spec : "",
				bodyTemplate : "",
				fromMailId : "",
				fromMailName : "해외출장담당자",
				mailId : v_IfParam.drafterUserId + "@daewooenc.com", // 운영시에는 담당자로 변경할 것
				mailSubject : "[해외출장정산서](출장자: " + v_IfParam.tripUserKnm + " " + v_IfParam.tripUserId + ") 출장정산서 반려되었습니다." ,
				htmlBody : "■ 작 성 자 : " + v_IfParam.drafterUserKnm + " " + v_IfParam.drafterUserPositCd + "<br>"
						 + "■ 출 장 자 : " + v_IfParam.tripUserKnm + " " + v_IfParam.tripUserPositCd + "<br>"
						 + "■ 문서상태 : 반려" + "<br>"
						 + "■ 반려사유 : " + v_RejectRes + "<br>"
						 + "■ 목 적 지 : " + $("#tripNat").val() + "<br>"
						 + "■ 출장기간 : " + $("#rTripDateStart").val() + " ~ " + $("#rTripDateEnd").val()
		};

		// mailParams 삽입
		ds_Mail.add(mailParams);

		//경영관리팀 숙소검토 반려일 경우 메일전송
//		제목 : [해외출장신청서] (출장자 : 사번 이름) 출장정산서 반려되었습니다.
//		내용 : 반려사유 확인 후 수정하여 협조의뢰 해주시기 바랍니다.
//		 ■ 작성자 : 홍길동 과장
//		 ■ 출장자 : 홍길동 과장
//		 ■ 문서상태 : 반려
//		 ■ 반려사유: 재작성하세요
//		 ■ 목적지 : 나이지리아
//		 ■ 출장기간 : 20150611~20150614

		docSts = "D84";
	}

	var params = {
			docNo : v_DocNo,
			docSts : docSts,
			ifParam : JSON.stringify(ipDatas),
			mailYn : mailYn,
			rejectRes : v_RejectRes
	};

	var dataSet  = {
			ds_Sign : ds_Signln.getAllData("I"),
			input1 : ds_Mail.getAllData()
	};

	gf_Transaction("SAVE_ADJUST_REJECT_DOC", "/trip/outerTrip/updateAdjustDoc.xpl", params, dataSet, "f_Callback", true);

}


function f_cancelDoc(){

	var docSts = "D16";

	var params = {
			docNo : v_DocNo,
			docSts : docSts
	};

	gf_Transaction("SAVE_ADJUST_REJECT_DOC", "/trip/outerTrip/updateCancelAdjustDoc.xpl", params, {}, "f_Callback", true);

}


function f_setDocReadOnly(){
	// 수정불가 처리
	$("input").attr("readonly", "readonly");
	$("textarea").attr("readonly", "readonly");
	$("select").attr("disabled", "disabled");

	//hide
	$("#selNat").hide();

	//datepicker 해제
	$("#rTripDateStart").datepicker("destroy");
	$("#rTripDateStart").unmask();

	$("#rTripDateEnd").datepicker("destroy");
	$("#rTripDateEnd").unmask();

	$("#payDate").datepicker("destroy");
	$("#payDate").unmask();

	$("#oDate").datepicker("destroy");
	$("#oDate").unmask();
}


function f_callBackFuncInputRet(obj){

	$("#payRutUser_id").val(obj.payRutUser_id);
	$("#payRutUser_name").val(obj.payRutUser_name);
	$("#payRutDate").val(obj.payRutDate);
}

function f_HQSignProcess(){

	// GHR 검토중일경우
	if(v_AdjustDocSts == "D60") return;

	// 사장전결 진행중일경우
	if(v_bossSignYn == "Y") return;

	var etcCheck = parseInt(f_notSpace(removeComma($("#loAirFare2").val()))) + parseInt(f_notSpace(removeComma($("#loTranFare2").val()))) +
				   parseInt(f_notSpace(removeComma($("#visaFeeFare2").val()))) + parseInt(f_notSpace(removeComma($("#ovCharFare2").val())));

	if(etcCheck > 199){

		if(v_HQSign == "Y") return;


			if(confirm("복리후생과 접대비를 제외한 기타경비 합계가 $200 이상의 경우 본부장 전결이 필요합니다. 진행하시겠습니까?")){
				v_HQSign = "Y";
				orgCls = "본부/실";

				// 화면 상단 결재선 셋팅
				// 현장소속의 경우 현장담당책임자를 가져오지않으므로 수동으로 중간협의자로 넣어줘야한다.
				var params = {
				  		orgCd : v_IfParam.tripUserOrgCd,
				  		orgCls : orgCls,
				  		userId : v_IfParam.tripUserId
				};

				// 상신시 Modal처리
				gf_InitSpinner(top.$('body'));

				gf_Transaction("SELECT_SIGN_LINE", "/co/common/user/retrieveSignlnForExcluRegl.xpl", params, {}, "f_Callback", true);
			}else{
				var exVal = 0;

				$("#loAirFare2").val(exVal);
				$("#loTranFare2").val(exVal);
				$("#visaFeeFare2").val(exVal);
				$("#ovCharFare2").val(exVal);

				f_calcEtcAmt(e.currentTarget.value, "loAirFare1", "loAirFareEx2", "loAirFare3");
				f_calcEtcAmt(e.currentTarget.value, "loTranFare1", "loTranFareEx2", "loTranFare3");
				f_calcEtcAmt(e.currentTarget.value, "visaFeeFare1", "visaFeeFareEx2", "visaFeeFare3");
				f_calcEtcAmt(e.currentTarget.value, "ovCharFare1", "ovCharFareEx2", "ovCharFare3");
			}

	}else{
		alert("복리후생과 접대비를 제외한 기타경비 합계가 $200 이하의 경우 전결규정에 따른 결재선으로 원상 복구 합니다.");
		v_HQSign = "N";

		if($("#jsGubun").val() == "A"){

			hostCheck2 = parseInt(f_notSpace(removeComma($("#vocLeeFare2").val()))) + parseInt(f_notSpace(removeComma($("#hostFare2").val())));

			if(hostCheck > 1400){
				if(hostCheck2 > hostCheck){
					v_bossSignYn = "Y";
					orgCls = "회장";

					// 화면 상단 결재선 셋팅
					// 현장소속의 경우 현장담당책임자를 가져오지않으므로 수동으로 중간협의자로 넣어줘야한다.
					var params = {
					  		orgCd : v_IfParam.tripUserOrgCd,
					  		orgCls : orgCls,
					  		userId : v_IfParam.tripUserId
					};

					// 상신시 Modal처리
					gf_InitSpinner(top.$('body'));

					gf_Transaction("SELECT_SIGN_LINE", "/co/common/user/retrieveSignlnForExcluRegl.xpl", params, {}, "f_Callback", true);

				}else{

					//정산 구분이 추가 일 경우 본부까지 결재가 올라감

					// 상신시 Modal처리
					gf_InitSpinner(top.$('body'));

					// 본부장 이상 직책일 체크
					if(ds_HqRpswrkCd.find("value", v_IfParam.tripUserRpswrkCd) != -1){
						orgCls = "회장";
					}else{
						orgCls = "본부/실";
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

			}else{

				if(hostCheck2 > 1400){
					v_bossSignYn = "Y";
					orgCls = "회장";

					// 화면 상단 결재선 셋팅
					// 현장소속의 경우 현장담당책임자를 가져오지않으므로 수동으로 중간협의자로 넣어줘야한다.
					var params = {
					  		orgCd : v_IfParam.tripUserOrgCd,
					  		orgCls : orgCls,
					  		userId : v_IfParam.tripUserId
					};

					// 상신시 Modal처리
					gf_InitSpinner(top.$('body'));

					gf_Transaction("SELECT_SIGN_LINE", "/co/common/user/retrieveSignlnForExcluRegl.xpl", params, {}, "f_Callback", true);

				}else{

					//정산 구분이 추가 일 경우 본부까지 결재가 올라감
					// 상신시 Modal처리
					gf_InitSpinner(top.$('body'));

					// 본부장 이상 직책일 체크
					if(ds_HqRpswrkCd.find("value", v_IfParam.tripUserRpswrkCd) != -1){
						orgCls = "회장";
					}else{
						orgCls = "본부/실";
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

			}

		}else{
			cf_retrieveSignLine();
		}
	}

}


function f_numberOnly(id){

	$("#"+id).bind('keydown', function(e) {

		var keyID = (e.which) ? e.which : e.keyCode;
		if ((keyID >= 48 && keyID <= 57)||(keyID >=96 && keyID <= 105)|| keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ){
			return true;
		}else{
			return false;
		}
	});

}

function f_callBackFuncRejectPop(obj){
//	alert("반려사유 : " + obj.rejectRes);

	v_RejectRes = obj.rejectRes;

	f_rejectDoc();


}

function chkByte(str) {

	if(str == undefined) return 0;

    var strLen = str.length;
    var totalByte = 0;
    var oneChar = "";

    for (var i = 0; i < strLen; i++) {
        oneChar = str.charAt(i);
        if (escape(oneChar).length > 4) {
            totalByte += 2;
        } else {
            totalByte++;
        }
    }

    return totalByte;
}

function cf_checkOsBizTrip(){

	var v_BdgtType = v_IfParam.bdgtType;
	var IBdgtType = v_BdgtType.substring(0,1); // 경비구분
	var IBdgtNo = v_IfParam.aufnr; // 예산번호

	// 현장경비/PJ경비 예산의 경우는 예산번호를 입력하지 않는다.
	if(v_BdgtType == "Q" || v_BdgtType == "Q1" || v_BdgtType == "Q2" || v_BdgtType == "Q3" || v_BdgtType == "Q4" || v_BdgtType == "Q5"){
		IBdgtNo = "";
	}

	var ISendDate = $("#oDate").val();
	var IBdgtTeam = v_IfParam.kostV; // 집행팀
	var IRefkey = ds_OuterTripDoc.get(0).refNo; // 신청서 Ref No

	var params = {
			// 예산체크 params
			IBdgtType : IBdgtType, // 경비구분
			IBdgtNo : IBdgtNo, // 예산번호
			IBdgtTeam : IBdgtTeam, // 집행팀
			//ISendDateC : $.datepicker.formatDate("yymmdd", new Date()),
			ISendDateC : ISendDate, // 정산서의 증빙일자
			AirAmt : removeComma($("#af2").text()), // 항공료 실사용분
			StayAmt : removeComma($("#cTotalAmt3").text()), // 체제비 차액
			EtcLairAmt : removeComma($("#loAirFareEx2").text()), // 현지항공료 실사용분
			EtcLtrAmt : removeComma($("#loTranFareEx2").text()), // 현지교통비 실사용분
			EtcVisaAmt : removeComma($("#visaFeeFareEx2").text()), // VISA FEE 실사용분
			EtcOverAmt : removeComma($("#ovCharFareEx2").text()), // OVER CHARGE 실사용분
			EtcWelAmt : removeComma($("#vocLeeFareEx2").text()), // 복리후생 실사용분
			EtcRcAmt : removeComma($("#hostFareEx2").text()), // 접대비 실사용분
			IRefkey : IRefkey // 신청서 Ref No
	}

	gf_Transaction("SELECT_CHECK_BIZ_TRIP", "/trip/eai/submitOsBizTripTest.xpl", params, {}, "f_Callback", true);

}


function cf_checkOsBizTripTest(){

	var v_BdgtType = v_IfParam.bdgtType;
	var IBdgtType = v_BdgtType.substring(0,1); // 경비구분
	var IBdgtNo = v_IfParam.aufnr; // 예산번호

	// 현장경비/PJ경비 예산의 경우는 예산번호를 입력하지 않는다.
	if(v_BdgtType == "Q" || v_BdgtType == "Q1" || v_BdgtType == "Q2" || v_BdgtType == "Q3" || v_BdgtType == "Q4" || v_BdgtType == "Q5"){
		IBdgtNo = "";
	}

	//var IBdgtType = v_IfParam.bdgtType; // 경비구분
	//var IBdgtNo = v_IfParam.aufnr; // 예산번호

	var ISendDate = $("#oDate").val();
	var IBdgtTeam = v_IfParam.kostV; // 집행팀
	var IRefkey = ds_OuterTripDoc.get(0).refNo; // 신청서 Ref No

	var params = {
			// 예산체크 params
			IBdgtType : IBdgtType, // 경비구분
			IBdgtNo : IBdgtNo, // 예산번호
			IBdgtTeam : IBdgtTeam, // 집행팀
			//ISendDateC : $.datepicker.formatDate("yymmdd", new Date()),
			ISendDateC : ISendDate ,
			AirAmt : removeComma($("#af2").text()), // 항공료 실사용분
			StayAmt : removeComma($("#cTotalAmt3").text()), // 체제비 차액
			EtcLairAmt : removeComma($("#loAirFareEx2").text()), // 현지항공료 실사용분
			EtcLtrAmt : removeComma($("#loTranFareEx2").text()), // 현지교통비 실사용분
			EtcVisaAmt : removeComma($("#visaFeeFareEx2").text()), // VISA FEE 실사용분
			EtcOverAmt : removeComma($("#ovCharFareEx2").text()), // OVER CHARGE 실사용분
			EtcWelAmt : removeComma($("#vocLeeFareEx2").text()), // 복리후생 실사용분
			EtcRcAmt : removeComma($("#hostFareEx2").text()), // 접대비 실사용분
			IRefkey : IRefkey // 신청서 Ref No
	}

	gf_Transaction("SELECT_CHECK_BIZ_TRIP_TEST", "/trip/eai/submitOsBizTripTest.xpl", params, {}, "f_Callback", true);

}

function f_deleteDoc(){
	var params = {
			docNo : v_DocNo
	};

	gf_Transaction("SAVE_DELETE_DOC", "/trip/outerTrip/deleteOuterTripAdjustDoc.xpl", params, {}, "f_Callback", true);

}

//시내교통비/국내출장/해외출장 중복 신청 방지 체크
function f_CheckDraftDuplication(){

	if($("#rTripDateStart").val() != "" && $("#rTripDateEnd").val() != ""){
		var sDate = $("#rTripDateStart").val().split("-");
		var eDate = $("#rTripDateEnd").val().split("-");

		var startDate = sDate[0] + sDate[1] + sDate[2];
		var endDate = eDate[0] + eDate[1] + eDate[2];
	}

	var v_userId = v_IfParam.tripUserId;
	v_stYmd = startDate;
	v_edYmd = endDate;

	var params = {
			userId : v_userId,
			stYmd : v_stYmd,
			edYmd : v_edYmd,
			docNo : v_DocNo
	};

	gf_Transaction("CHECK_DRAFT_DUPLICATION", "/trip/cityTransp/retrieveCheckDraftDuplication.xpl", params, {}, "f_Callback", true);

}