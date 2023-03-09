/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 김경태
* @since 2019-06-11
* @version 1.0
*/

var params = {};

var v_docNo = "";		//문서번호
var v_preDocSts = "";	//문서상태(직전)
var v_docSts = "";		//문서상태
var v_writer = "";		//작성자

//예산구분
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

var v_FileAtchId;		//첨부파일ID

//결재선
//임원 여부
var v_IsOfficer = "N";
// 팀장 여부
var v_IsOrgBoss = "N";

var v_RejectRes = "";
var fromReject = "";

var v_ErrMsg = "";
var v_PrgrMsg = "";

//발급예정일
var v_ScdDate = "";
var v_riskComm1 = "N";
var v_riskComm2 = "N";
var v_riskComm3 = "N";

/**
 * DataSet for 결재문서
 */

var ds_Signln = new DataSet();			//결재선정보
var ds_SignlnForExcluRegl = new DataSet();	//자동결재선정보

/* 신청서 정보 */
var ds_visaAppn = new DataSet(); // 상신용 DataSet

/* 협의자 정보*/
var ds_Assist = new DataSet();		//협의자 정보 (CMAS_OT_007, 필요시 추가 가능)

var tripUser; // 출장자
var tripUserNm;
var tripUserTeam; // 출장자팀
var tripUserTeamNm;
var tripUserPositCd;
var tripUserRpswrkCd;
var tripUserApptOrgCd; // 출장자 현발령

//국가 목록
var ds_NatList = new DataSet();
var v_natList = "";	//선택한 나라

//비자정보 목록
var ds_VisaInfoList = new DataSet();

var v_tSignUserCd; // 최종결재 ORG
var v_tSignUserId; // 최종결재 ID

var v_nmRiskYN;	//위험지역여부

// 일반정보,가족정보, 병역정보 조회
var ds_GeneralInfo = new DataSet();

//팝업이 닫힐때 리턴값을 전달할 callback 함수
var v_CallbackFunc;

// 최종결재자 임시저장
var v_tSign;

var ds_ChiefCd = new DataSet();

// 임시저장 확인용 Flag
var v_IsSavedDoc = "N";

// 결재선 지정 사용 여부
var v_isSignEdit = "N";

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

	v_docNo = gf_IsNull(datas.docNo) ? "" : datas.docNo;
	v_docSts = gf_IsNull(datas.docSts) ? "" : datas.docSts;

	v_CallbackFunc = gf_IsNull(datas.callbackFunc) ? "" : datas.callbackFunc;

	// 반려로부터 온 문서
	fromReject = gf_IsNull(datas.fromReject) ? "" : datas.fromReject;

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
	//출장자 검색 컴포넌트 (이거 extnlPerYn가 y이면 또 내용이 바뀜)

	gf_SetUserComponent($("#tripUserId"), function(data){
		var userInfo = data.orgNm + " " + data.userPositCd + " " + data.userKnm + "(" + data.userId + ")";

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

		var searchParams = {
				tripuserId : tripUser
		};

		//출장자 param (x) => 신청자(작성자) param(o)
/*		var isParam = {
  				userId : tripUser,
  				orgCd : tripUserTeam,
  				drafterId : tripUser,
  				drafterOrgCd : tripUserTeam
  		};
		*/

		var isParam = {
	  		userId : v_userId,
			orgCd : v_orgCd,
			drafterId : v_userId,
			drafterOrgCd : v_orgCd
		};

		if(isParam.userId == undefined){
		}
		else{

			// 일반정보 조회 (from C51 view)
			// 출장자에 대한 결재라인 조회.
			gf_Transaction("SELECT_GENERAL_INFO","/trip/visaAppn/retrieveGeneralInfo.xpl",searchParams,{},"f_Callback",true);
	  		gf_Transaction("SELECT_IS_SPOT_MGMT", "/trip/innerTrip/retrieveIsSpotMgmt.xpl", isParam, {}, "f_Callback", true);
		}

	});

	gf_SetUserComponent($("#tripUserId2"), function(data){
		var userInfo = data.orgNm + " " + data.userPositCd + " " + data.userKnm + "(" + data.userId + ")";

		tripUser = data.userId;
		tripUserNm = data.userKnm;
		tripUserTeam = data.orgCd;
		tripUserTeamNm = data.orgNm;
		tripUserPositCd = data.userPositCd;
		tripUserRpswrkCd = data.userRpswrkCd;
		tripUserApptOrgCd = data.apptOrgCd;

		//소속팀 셋팅
		var tripUserOrgNm = tripUserTeamNm + " (" + tripUserTeam + ")";
		$("#tripUserOrgNm2").text(tripUserOrgNm);

		var searchParams = {
				tripuserId : tripUser
		};

		if(tripUser == undefined){
		}
		else{

			// 일반정보 조회 (from C51 view)
			gf_Transaction("SELECT_GENERAL_INFO","/trip/visaAppn/retrieveGeneralInfo.xpl",searchParams,{},"f_Callback",true);
		}
	});

	//Attachment 컴포넌트 생성
	gf_InitFileUploadComponent();

	//출국예정일
	$( "input[name='departScdDd']").mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" , minDate : 0});

	//부,모,배우자 생일
	$( "input[name='fatherBirth']").mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
	$( "input[name='motherBirth']").mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
	$( "input[name='spouseBirth']").mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });

	//입대,전역일
	$( "input[name='enlistYmd']").mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
	$( "input[name='demobYmd']").mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
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
/*
   //generalInfo
   $("#extnlPerYn").change(function(){
        if($("#extnlPerYn").is(":checked")){
        	//ID칸 입력 불가 ,조회 버튼 클릭 불가
        	$("#tripUserId_id").attr('disabled','true');
        	$("#tripUserId_btn").hide();
        	$("#tripUserId_name").css({"width":"80px"});
        	//입력값들 초기화.
        	f_cleanVisaAppnInfo();
        	alert("generalinfo")
        }else{
        	$("#tripUserId_id").removeAttr('disabled');
        	$("#tripUserId_name").css({"width":"63px"});
        	$("#tripUserId_btn").show();
        }
    });

   //gInfo.
   $("#extnlPerYn2").change(function(){
       if($("#extnlPerYn2").is(":checked")){
       	//ID칸 입력 불가 ,조회 버튼 클릭 불가
			$("#tripUserId2_id").attr('disabled','true');
			$("#tripUserId2_btn").hide();
			//입력값 초기화
			f_cleanVisaAppnInfo();

       }else{
       		$("#tripUserId2_id").removeAttr('disabled');
       		$("#tripUserId2_btn").show();
       }
   });
*/
   //visitNat1,2,3이  CHANGE할때..가족정보, 병역정보 나타나고 사라지는 처리.

	//NAT1~NAT3, AREA1~AREA3
	$("input[name='visitNat1']").click(function(e){

		var distParams = {
			targetId : 'visitNat1',
			ds_NatList : ds_NatList.getAllData(),
		};

		gf_PostOpen("/trip/visaAppn/natSelectP.jsp", null,
				"toolbar=no,scrollbars=no,width=760,height=350", distParams,
				true, true, "f_callBackFuncNatSelect");

	});

	$("input[name='visitNat2']").click(function(e){
		var distParams = {
				targetId : 'visitNat2',
				ds_NatList : ds_NatList.getAllData(),
			};

			gf_PostOpen("/trip/visaAppn/natSelectP.jsp", null,
					"toolbar=no,scrollbars=no,width=760,height=350", distParams,
					true, true, "f_callBackFuncNatSelect");

	});

	$("input[name='visitNat3']").click(function(e){
		var distParams = {
				targetId : 'visitNat3',
				ds_NatList : ds_NatList.getAllData(),
			};

			gf_PostOpen("/trip/visaAppn/natSelectP.jsp", null,
					"toolbar=no,scrollbars=no,width=760,height=350", distParams,
					true, true, "f_callBackFuncNatSelect");

	});

	$("input[name='visitArea1']").click(function(e){
		var distParams = {
				targetId : 'visitNat1',
				ds_NatList : ds_NatList.getAllData(),
			};

			gf_PostOpen("/trip/visaAppn/natSelectP.jsp", null,
					"toolbar=no,scrollbars=no,width=620,height=350", distParams,
					true, true, "f_callBackFuncNatSelect");

	});

	$("input[name='visitArea2']").click(function(e){
		var distParams = {
				targetId : 'visitNat2',
				ds_NatList : ds_NatList.getAllData(),
			};

			gf_PostOpen("/trip/visaAppn/natSelectP.jsp", null,
					"toolbar=no,scrollbars=no,width=620,height=350", distParams,
					true, true, "f_callBackFuncNatSelect");
	});

	$("input[name='visitArea3']").click(function(e){
		var distParams = {
				targetId : 'visitNat3',
				ds_NatList : ds_NatList.getAllData(),
			};

			gf_PostOpen("/trip/visaAppn/natSelectP.jsp", null,
					"toolbar=no,scrollbars=no,width=620,height=350", distParams,
					true, true, "f_callBackFuncNatSelect");
	});



	$("#btnBdgtSelect").click(function(){

		var bdgtParams = {
				bdgtType : v_BdgtType,
				pType : "OT"
		};

		gf_PostOpen("/common/jsp/comp/budget/bdgtSelect.jsp", null,
				"toolbar=no,scrollbars=no,width=665,height=600", bdgtParams,
				true, true, "f_callBackFuncBdgtSelect");
		//예산 선택 후 callback에 주목

	});

	// 임시저장

	$('#btnSave').click(function(){
		var params = f_SetVisaAppnInfo();
		if(confirm("작성 중인 문서를 저장하시겠습니까?")){
	  		gf_onFileUpload();
		}
	});

	//협조의뢰
	$('#btnRequestForHelp').click(function(){

		v_preDocSts = v_docSts;
		v_docSts ="D60";

		var params = f_SetVisaAppnInfo();

		//입력값 Validation

		if(f_CheckValidationForDraft() == false){
			//rollback.
			v_docSts = v_preDocSts;
			return;
		}

		if(f_CheckValidationForSave(params) == false){
			//rollback.
			v_docSts = v_preDocSts;
			return;
		}

		//결재선 체크. D60 STATUS 에서는 결재선 바뀌면 안되니까.

		if(confirm("작성 중인 문서를 협조 의뢰 하시겠습니까?")){
	  		gf_onFileUpload();
		}
		else{
			//rollback
			v_docSts = v_preDocSts;
		}
	});

	// VISA 검토반려
	// 문서 반려 처리
	$('#btnVisaNotConfirm').click(function(){

		gf_PostOpen("/trip/visaAppn/rejectPop.jsp", null,
				"toolbar=no,scrollbars=yes,width=1000,height=150", {},
				true, true, "f_callBackFuncRejectPop");

	});



	$("#btnDelete").click(function(){
		if(confirm("이 문서를 삭제하시겠습니까?")){

			var param = {
					docNo : v_docNo
			};

			gf_Transaction("DELETE_VISA_APPN", "/trip/visaAppn/deleteVisaAppn.xpl", param, {}, "f_Callback", true);
		}

	});
	//결재선 지정 버튼.
	$("#btnSignln").click(function(){

		var datas = {
			signln : ds_Signln.getAll()
		};
		gf_PostOpen("/common/jsp/sign/signUserSelect.jsp", null, "toolbar=no,scrollbars=no,width=1020,height=590", datas, true, true);


	});

	 // 검토 완료 후 결재 상신.
	$("#btnVisaConfirm").click(function(){

		//아래 항목이 바뀔 수 있음.
		//orgCd,orgNm,userId 는 원작성자 정보를 가져와야 함.
/*
		docNo : v_docNo,
		orgCd : v_orgCd,
		orgNm : v_orgNm,
		userId : v_userId,
*/
		gf_EnableOverlay();

		//작성된 내용들
		var params = f_SetVisaAppnInfo();

		//저장 validation
		if(f_CheckValidationForSave(params) == false) {
			gf_DisableCurrentOverlay();
			return;
		}

		//상신 validation
		if(f_CheckValidationForDraft() == false) {
			gf_DisableCurrentOverlay();
			return;
		}

		if(ds_Signln.find("signTpCd", "T02") == -1) {
			gf_AlertMsg("결재자 지정을 해야 합니다.");
			gf_DisableCurrentOverlay();
			return;
		}

		// 최종 결재자가 협의로 끝나면 안됨
		for(var i = 0; i < ds_Signln.size(); i++){
			// 마지막 결재자 검사
			if(i == ds_Signln.size()-1){
				if(ds_Signln.get(ds_Signln.size()-1).signTpCd == "T03"){
					 gf_AlertMsg("최종 결재자를 지정해야 합니다.\n\n결재선을 지정해주세요.");
					 gf_DisableCurrentOverlay();
					 return;
				}
			}
		}

		//가족정보와 병역정보가 필요한 국가가 아니면 신청서에도 가족정보와 병역정보를 올리지 않음
		if(cf_GetFmlyInfoNcsYn($("input[name='visitNat1']").val())!='Y'
			&& cf_GetFmlyInfoNcsYn($("input[name='visitNat2']").val()) !='Y'
			&& cf_GetFmlyInfoNcsYn($("input[name='visitNat3']").val()) !='Y'){
			params.fatNm		="";
			params.fatBirymd	="";
			params.motNm		="";
			params.motBirymd	="";
			params.spouseNm		="";
			params.spouseBirymd	="";
		}
		if(cf_GetArmyInfoNcsYn($("input[name='visitNat1']").val()) !='Y'
			&& cf_GetArmyInfoNcsYn($("input[name='visitNat2']").val()) !='Y'
			&& cf_GetArmyInfoNcsYn($("input[name='visitNat3']").val()) !='Y'){
			params.amtye		="";
			params.ampos		="";
			params.enlmYmd		="";
			params.amdcgYmd		="";
		}

		//글로벌지원팀 담당자를 결재 협의로 넣지 않고, 해외출장의 사전검토 형태로 넣는 것으로 수정
		/*
		if(!gf_IsNull(ds_Assist.get(0,"signUserId"))){
			// RO_CMAS_OT_007 (비자신청,협의 담당자)
			addPerchrgId(ds_Assist.get(0,"signUserId"),ds_Assist.get(0,"apperOrgCd"));
		}
		*/

		// 결재선마다 현재 docNo 추가
		for(var i=0; i<ds_Signln.size(); i++){
  			ds_Signln.set(i, "docNo", v_docNo);		// 필수 : 문서번호
  			ds_Signln.set(i, "dutyCls", "05");		// 필수 : 업무구분 05 = VISA 신청
  		}

  		var dataSets = {
	  			signln : ds_Signln.getAllData(),
	  		};

		gf_Transaction("DRAFT_VISA_APPN", "/trip/visaAppn/draftVisaAppn.xpl", params, dataSets, "f_Callback", true);

	});

	//접수.
	$("#btnSubmit").click(function(){
		if(confirm("비자신청을 접수하고 발급예정일 선택하시겠습니까?")){
			gf_PostOpen("/trip/visaAppn/SelectScdDatePop.jsp", null,
					"toolbar=no,scrollbars=no,width=400,height=300", {},
					true, true, "f_callBackFuncSelectScdDatePop");

		}

	});

	//완료 처리 통보.
	$("#btnIssue").click(function(){
		if(confirm("비자발급 완료 통보하겠습니까?")){

			//완료일 자동 입력. 역시 원하는 양식으로 메일 발송.
			var param = {
					docNo : v_docNo
			};

			gf_Transaction("VISA_ISSUE_NOTIFY", "/trip/visaAppn/visaIssueNotify.xpl", param, {}, "f_Callback", true);
		}

	});

	 //기안창 종료 버튼
	$("#btnClose").click(function(){
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
	/**
	 * 문서 초기화, 새문서
	 */
	$("#fInfo").hide();
	$("#familyInfo").hide();
	$("#mInfo").hide();
	$("#militaryInfo").hide();

	f_SetDocInit(); 		//신규 작성 및 임시저장 문서에 대한 처리 분리
	f_SetFormReadOnly();
	f_SetBtn();				//USER 권한에 따라 Button 표시/삭제
	f_SetAppnInfoHide();
}

/**
* @class 신규 작성 및 임시저장 문서에 대한 처리
* @author 김경태
* @since 2019-06-11
* @version 1.0
*/
function f_SetDocInit(){


	//VISA 협의 결재자 (CMAS_OT_007)
	gf_Transaction("SELECT_COSIGN_LINE", "/trip/visaAppn/retrieveCoSignList.xpl", {}, {}, "f_Callback", false);
	//

	if(v_docNo != ""){
		$('#docNo').html(v_docNo);
		// 기존문서 불러오기
		var params = {
				docNo : v_docNo		//문서번호
		};

		gf_Transaction("SELECT_NAT_LIST", "/trip/outerTrip/retrieveNatList.xpl", {}, {}, "f_Callback", false);
  		gf_Transaction("SELECT_VISA_APPN_INFO", "/trip/visaAppn/retrieveSavedDocInfo.xpl", params, {}, "f_Callback", true);

	} else {
		// 신규작성 (v_userId = v_writer)
		v_writer = v_userId;
		$("#btnSignln").show();
	    var params = {
	      		userId : v_userId,
	    		orgCd : v_orgCd,
	      		orgNm : v_orgNm,
	      	};

		gf_Transaction("SELECT_NAT_LIST", "/trip/outerTrip/retrieveNatList.xpl", {}, {}, "f_Callback", false);
		gf_Transaction("SELECT_DOC_NO", "/trip/visaAppn/getDocNo.xpl", {}, {}, "f_Callback", true);
	}

}

function f_SetFormReadOnly(){

	if(gv_AuthList.auth[0].code.match("RO_CMAS_CO_TRIP_ADM") == "RO_CMAS_CO_TRIP_ADM") {
		//VISA 시스템도 출장관리 담당자가 관리.
	}
	if(v_userId == v_writer && (v_docSts == "D16" || v_docSts == "")){
		$('input').removeAttr('disabled');
		$('textarea').removeAttr('disabled');
		$('select').removeAttr('disabled');

		$("#btnBdgtSelect").show();
    	$("#tripUserId_btn").show();
    	$("#tripUserId2_btn").show();
	}
	else {
		$('input').attr('disabled', 'true');
		$('textarea').attr('disabled', 'true');
		$('select').attr('disabled', 'true');

		$("#btnBdgtSelect").hide();
    	$("#tripUserId_btn").hide();
       	$("#tripUserId2_btn").hide();
		//예산선택 버튼 , 출장자 선택 버튼 hide

	}
}

//출장신청자 개인정보 보이게 안보이게, 신청국가에 따라 불필요한 정보입력창 안보이게
function f_SetAppnInfoHide(){

	//관리자 및 VISA 담당자, 로그인한 사람 = 출장자,로그인=작성자 && 임시저장,작성중 && 출장자가 외부인
	if((gv_AuthList.auth[0].code.match("RO_CMAS_CO_TRIP_ADM") == "RO_CMAS_CO_TRIP_ADM")
		||(gv_AuthList.auth[0].code.match("RO_CMAS_OT_007") == "RO_CMAS_OT_007")
		||(v_userId == $("#tripUserId_id").val()) && v_userId == $("#tripUserId2_id").val()
		||(v_userId == v_writer && (v_docSts == "D16" || v_docSts == "") &&
			($("#extnlPerYn").is(":checked") || $("#extnlPerYn2").is(":checked"))))
	{
		//새문서일때 안탐.
		$("#gInfo").hide();
		$("#fInfo").hide();
		$("#mInfo").hide();

		//문구보이기
		if($("#extnlPerYn").is(":checked")){
			$("#gInfoComm").show();
			$("#gInfoComm1").show();
			$("#gInfoComm2").show();
		}else{
			$("#gInfoComm").hide();
			$("#gInfoComm1").hide();
			$("#gInfoComm2").hide();
		}

		if($('input:checkbox[id="extnlPerYn"]').is(":checked")){
			$("#tripUserId_id").attr('disabled','true');
		}else{
			$("#tripUserId_id").attr('disabled','false');
		}

		$("#generalInfo").show();

/*		$("#familyInfo").show();
		$("#militaryInfo").show();
*/
	}

	else{

		//개인정보 OFF
		//새문서일 경우 처리부분
		//alert("-->"+v_docSts)
		if (v_docSts == ""){
			//alert($("#tripUserId_id").val());
			if ($("#tripUserId_id").val() == ""){
				$("#generalInfo").show();
				$("#gInfo").hide();
			}else if(v_userId != $("#tripUserId_id").val()){
				$("#generalInfo").hide();
				$("#gInfo").show();
			}else{
				$("#generalInfo").show();
				$("#gInfo").hide();
			}
		}else{
			//조회시 테이블 보여주고/감추기
			//alert("vid-->"+v_userId);
			//alert($("#tripUserId_id").val());

			if(v_userId != $("#tripUserId_id").val()){
				//alert(gv_AuthList.auth[0].code.match("RO_CMAS_CO_TRIP_ADM"));
				//alert(gv_AuthList.auth[0].code.match("RO_CMAS_OT_007"));
				if((gv_AuthList.auth[0].code.match("RO_CMAS_CO_TRIP_ADM") == "RO_CMAS_CO_TRIP_ADM")
					|| (gv_AuthList.auth[0].code.match("RO_CMAS_OT_007") == "RO_CMAS_OT_007")
					|| ($("#extnlPerYn").is(":checked") || $("#extnlPerYn2").is(":checked")))
				{
					//alert("관리자이고 담당자 ,외부인");
					$("#generalInfo").show();
					$("#gInfo").hide();
					$("#fInfo").hide();
					$("#mInfo").hide();

					if(cf_GetFmlyInfoNcsYn($("input[name='visitNat1']").val())=='Y'
						||cf_GetFmlyInfoNcsYn($("input[name='visitNat2']").val()) =='Y'
						||cf_GetFmlyInfoNcsYn($("input[name='visitNat3']").val()) =='Y')
					{
						//alert("22222");
						$("#familyInfo").show();
						$("#fInfo").hide();
					}

					if(cf_GetArmyInfoNcsYn($("input[name='visitNat1']").val())=='Y'
						||cf_GetArmyInfoNcsYn($("input[name='visitNat2']").val()) =='Y'
						||cf_GetArmyInfoNcsYn($("input[name='visitNat3']").val()) =='Y')
					{
						$("#militaryInfo").show();
						$("#mInfo").hide();
					}

				}else{
					//alert("관리자이고 담당자 No~");
					if(v_userId != $("#tripUserId_id").val()){
						//alert("신청자와 출장자가 다름");
						if ($("#extnlPerYn").is(":checked") || $("#extnlPerYn2").is(":checked")){
							$("#fInfo").hide();
							$("#familyInfo").show();
							//alert("55");
						}else{
							$("#fInfo").show();
							$("#familyInfo").hide();
							//alert("66");
						}
					}

					$("#generalInfo").hide();
					$("#gInfo").show();

					if(cf_GetFmlyInfoNcsYn($("input[name='visitNat1']").val())=='Y'
						||cf_GetFmlyInfoNcsYn($("input[name='visitNat2']").val()) =='Y'
							||cf_GetFmlyInfoNcsYn($("input[name='visitNat3']").val()) =='Y')
					{
						$("#familyInfo").hide();
						$("#fInfo").show();
					}


					if(cf_GetArmyInfoNcsYn($("input[name='visitNat1']").val())=='Y'
						||cf_GetArmyInfoNcsYn($("input[name='visitNat2']").val()) =='Y'
						||cf_GetArmyInfoNcsYn($("input[name='visitNat3']").val()) =='Y')
					{
						$("#militaryInfo").hide();
						$("#mInfo").show();
					}
				}
			}else{

			}
		}
	//	$("#familyInfo").hide();
	//	$("#militaryInfo").hide();
		//조회


	}
}

function f_SetBtn(){

	//임시저장
	if(v_docSts == "D16" && (v_userId == v_writer)){		//임시저장
		$("#btnDelete").show();
		$("#btnSignln").show();
		$("#btnSave").show();
		$("#btnRequestForHelp").show();
	}

	//STATUS 더 세부적으로 쪼개야 함.
	if((v_docSts != "D16" && v_docSts != "")||(v_userId != v_writer)){
		$("#btnSignln").hide();
		$("#btnDelete").hide();
		$("#btnSave").hide();
		$("#btnRequestForHelp").hide();
	}

	// VISA 신청자 사전검토 STATUS 추가 : D60, D64(GHR 검토중, GHR반려)

	if(v_docSts == "D60"){

	//VISA 담당자에게 검토 버튼 활성화
		if(gv_AuthList.auth[0].code.match("RO_CMAS_OT_007") == "RO_CMAS_OT_007"){
			$("#btnVisaNotConfirm").show();
			$("#btnVisaConfirm").show();
			$("#btnSave").show();
		}

	//담당자 의견란
//		$("#ghrTr").show();

	//일반버튼 숨김
		$("#btnSignln").hide();
		$("#btnRequestForHelp").hide();
	}

	//반려받았을때 (결재반려, 사전검토 반려)
	if((v_docSts =="D04" || v_docSts =='D64') && v_userId == v_writer){
		$("#btnDelete").show();
	}

	else{

	}
	//관리자 권한 또는 VISA 담당자가 필요한 기능이 있을시 추가.
	if((gv_AuthList.auth[0].code.match("RO_CMAS_CO_TRIP_ADM") == "RO_CMAS_CO_TRIP_ADM")
	  ||(gv_AuthList.auth[0].code.match("RO_CMAS_OT_007") == "RO_CMAS_OT_007"))
	{
		//결재완료 시 비자접수 버튼 활성화
		if((v_docSts =="D03") && ds_visaAppn.get(0,"visaPrgrSts")==""){
			$("#btnSubmit").show();
		}
		//결재완료 + 접수완료
		if((v_docSts =="D03") && ds_visaAppn.get(0,"visaPrgrSts")=="S"){
			$("#btnSubmit").hide();
			$("#btnIssue").show();
		}
		//결재완료 + 접수 -> 발급완료
		if((v_docSts =="D03") && ds_visaAppn.get(0,"visaPrgrSts")=="I"){
			$("#btnSubmit").hide();
			$("#btnIssue").hide();
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

	  // transaction의 정상 처리 여부를 판단한다.
	  if (!gf_ChkTransaction(strSvcId, resultData, true )) {
		  return;
	  }

	  switch(strSvcId) {

	  	case "SELECT_VISA_MAIL":
		  self.close();
		  break;

	  	case "DRAFT_VISA_APPN" : //상신
	  		if ( !gf_IsNull(v_CallbackFunc) ) {
	  			var openCallbackFunc = "opener."+v_CallbackFunc;
	  	    	eval(openCallbackFunc + "();");
	  	    }
	  		gf_DisableCurrentOverlay();

	  		self.close();
			break;

	  	case "SAVE_VISA_APPN" :  //임시저장.
	  		if(v_docSts =="D60"){	//GHR 검토시.
	  			mailNotice(4);		//협조의뢰 받았을때 메일 (신청자에게 1개, 담당자에게 1개)
	  		}
	  		v_docSts = "D16";
	  		f_SetBtn();
	  		f_SetDocInit();
	  		self.close();
			break;

	  	case "REJECT_VISA_APPN" :

	  		// 문서가 성공적으로 처리되면 문서 리스트를 다시 조회하여 갱신하여 준다.
/*	  		if ( !gf_IsNull(v_CallbackFunc) ) {
	  			var openCallbackFunc = "opener."+v_CallbackFunc;
	  	    	eval(openCallbackFunc + "();");
	  	    }
*/
	  		//목록 새로고침
	  		opener.f_RefreshList();

	  		//GHR반려버튼 클릭시
	  		mailNotice(3);

	  		break;

	  	case "DELETE_VISA_APPN" :
	  		// 문서가 성공적으로 처리되면 문서 리스트를 다시 조회하여 갱신하여 준다.
	  		if ( !gf_IsNull(v_CallbackFunc) ) {
	  			var openCallbackFunc = "opener."+v_CallbackFunc;
	  	    	eval(openCallbackFunc + "();");
	  	    }

	  		self.close();
	  		break;

	  	case "SELECT_NAT_LIST" :
	  		ds_NatList.setAllData(resultData.ds_NatList);

	  		break;

	  	case "SELECT_CO_USER_INFO" :

	  		if(resultData.ds_coUser == null){
				return;
	  		}

	  		gf_DisableCurrentOverlay();

	  		break;

	  	case "SELECT_IS_SPOT_MGMT" :

  			// 일반적인 경우에는 대체로 최종결재자 ID가 만들어지지만
 			// 출장자가 임원일 경우 같은 조직에 팀장이 없으므로
  			// 따로 처리를 해줘야한다.

  			if(resultData.ds_Officer != null || resultData.ds_Officer == undefined){

  			}else{
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

	  		// 팀장 이상 여부 검사
	  		if(resultData.ds_IsOrgBoss != null){
	  			for(var i = 0; i < resultData.ds_IsOrgBoss.length; i++){
	  				if(ds_ChiefCd.find("code", resultData.ds_IsOrgBoss[i].userRpswrkCd) > -1){
	  					v_IsOrgBoss = "Y";
	  					$("#btnSignln").show();
	  				}
	  			}
	  		}else{
	  			v_IsOrgBoss = "N";
	  		}

	  		// 임원여부 검사
	  		if(resultData.ds_Officer != null){
	  			v_IsOfficer = "Y"; // 임원여부 Y / N
	  			$("#btnSignln").show();
	  		}else{
	  			if(v_userPositCd == "상무"){ // 호칭 조건을 추가하세요.
	  				v_IsOfficer = "Y"; // 임원여부 Y / N
		  			$("#btnSignln").show(); // 결재선 지정 팝업
	  			}else{
	  				v_IsOfficer = "N";
	  			}
	  		}
	  		cf_retrieveSignlnForExcluRegl();
	  		cf_getCouserInfo();

//	  		$(".ajax_overlay").remove();

	  		break;

	  	case "SELECT_GENERAL_INFO":	//일반,가족,병역
	  		ds_GeneralInfo.setAllData(resultData.ds_GeneralInfo);

	  		f_getGeneralInfo();
	  		f_SetAppnInfoHide();

	  		//선택 나라에 따라 가족사항, 병역 보이기/숨기기
	  		f_GetFmlyInfoNcsYN();

	  		break;

	  	case "SELECT_VISA_APPN_INFO" :

	  		ds_visaAppn.setAllData(resultData.ds_DocData);

	  		//f_getVisaAppnInfo(); //dataset으로 받아온 정보를 화면에 setting.

	  		v_IsSavedDoc = "Y";

	  		gf_SignlnInit(v_docNo);

	  		gf_AssembleSignln(ds_Signln);

	  		v_writer = ds_Signln.get(0, "signUserId");

	  		f_SetFormReadOnly();

	  		//2019-10-31 아래 함수  한번 더 호출
	  		f_getVisaAppnInfo();


	  		f_SetAppnInfoHide();

	  		f_SetBtn();


	  		// 에러내역 조회
	  		var params = {
	  				docNo : v_docNo
	  		};

	  		gf_Transaction("SELECT_ERR_MSG", "/trip/visaAppn/retrieveErrMsg.xpl", params, {}, "f_Callback", true);

	  		break;

	  	case "SELECT_DOC_NO" :

	  		v_docNo = resultData.ds_Result[0].docNo;
	  		v_writer = v_userId;
	  		//문서번호, 작성자, 소속팀 셋팅.
	  		$('#docNo').html(v_docNo);
	  		$('#drafter').html(gv_userNm+"("+gv_userId+")");
	  		$('#drafterOrgNm').html(gv_orgNm);

	  		cf_retrieveSignlnForExcluRegl();

	  		break;

	  	case "SELECT_COSIGN_LINE" :
	  		//do nothing
	  		ds_Assist.clear();
	  		ds_Assist.setAllData(resultData.ds_Assist);
	  		break;

	  	case "SELECT_SIGNLN_FOR_EXCLU_REGL" : // 전결규정 기반 결재선
	  		ds_SignlnForExcluRegl.setAllData(resultData.ds_SignlnForExcluRegl);

	  		//여기에다 innerTripDraft에 있는 결재선 반영한 로직을 넣는다 (19.07.19)

	  		cf_setSignlnInfo(resultData);

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

	  	case "VISA_SUBMIT_NOTIFY" :
	  		f_SetBtn();
	  		f_SetDocInit();

	  		//접수버튼 클릭시
	  		//
	  		break;

	  	case "VISA_ISSUE_NOTIFY" :
	  		f_SetBtn();
	  		f_SetDocInit();

	  		//발급완료 처리 통보.
	  		mailNotice(2);
	  		break;

	  	default :
	  		break;
	  }
}

function mailNotice(sunbun){
	// Mail로 Notice.

	var ds_Mail = new DataSet();
	var d = new Date();
	var today = d.getFullYear() + "년" + (d.getMonth()+1) + "월" + d.getDate() + "일";

	if(sunbun == 1){	//접수완료(현재 미사용)

		var params1 = {
				spec : "",
				bodyTemplate : "",
				fromMailId : "1DFES@daewooenc.com",
				fromMailName : "총무팀",
				mailId : v_writer + "@daewooenc.com", // 수신자
				mailSubject : "[총무팀-비자신청] 접수 안내" ,
				htmlBody : "("+ today + ") 신청하신 (" + v_natList + ") 비자 요청이 정상 접수되었으니 여권 및 필요 서류 총무팀 담당자 앞 전달해주시기 바랍니다.<br><br>"
							+ "● 담당자 : 총무팀 장준용 매니저(Tel : 1884)"
			};

		ds_Mail.add(params1);

	}else if(sunbun == 2){	//발급완료

		var params2 = {
				spec : "",
				bodyTemplate : "",
				fromMailId : "1DFES@daewooenc.com",
				fromMailName : "총무팀",
				mailId : v_writer + "@daewooenc.com", // 수신자
				mailSubject : "[총무팀-비자신청] 발급 안내" ,
				htmlBody :    "(" + v_natList + ") 비자가 발급 완료 되었으니 수령하시기 바랍니다.<br><br>"
							+ "● 담당자 : 총무팀 장준용 매니저(Tel : 1884)<br><br>"
			};

		ds_Mail.add(params2);

	}else if(sunbun == 3) {	//GHR반려

		var params3 = {
				spec : "",
				bodyTemplate : "",
				fromMailId : "1DFES@daewooenc.com",
				fromMailName : "총무팀",
				mailId : v_writer + "@daewooenc.com", // 수신자
				mailSubject : "[총무팀-비자신청] 반려 안내" ,
				htmlBody : "("+ today + ") 신청하신 (" + v_natList + ") 비자가 하기와 같은 사유로 반려되었으니 참고하시어 재상신하시기 바랍니다.<br><br>"
							+ "- 반려 사유 : " + v_RejectRes + "<br><br>"
							+ "● 담당자 : 총무팀 장준용 매니저(Tel : 1884)"
			};

		ds_Mail.add(params3);

	}else if(sunbun == 4) {
		//작성자에게 접수되었다고 알리는 메일
		var params1 = {
				spec : "",
				bodyTemplate : "",
				fromMailId : "1DFES@daewooenc.com",
				fromMailName : "총무팀",
				mailId : v_writer + "@daewooenc.com", // 수신자
				mailSubject : "[총무팀-비자신청] 접수 안내" ,
				htmlBody : "("+ today + ") 신청하신 (" + v_natList + ") 비자 요청이 정상 접수되었으니 여권 및 필요 서류 총무팀 담당자 앞 전달해주시기 바랍니다.<br><br>"
							+ "● 담당자 : 총무팀 장준용 매니저(Tel : 1884)"
			};

		ds_Mail.add(params1);

		//작성자가 접수자에게 협의요청하는 메일
		for(var i = 0; i < ds_Assist.size(); i++){
			if(!gf_IsNull(ds_Assist.get(i,"signUserId"))){
				var params4 = {
					spec : "",
					bodyTemplate : "",
					fromMailId : v_writer + "@daewooenc.com",
					fromMailName : "작성자협조의뢰",
					mailId : ds_Assist.get(i,"signUserId")+"@daewooenc.com", // 수신자
					mailSubject : "[비자 신청] 협조 의뢰 접수 ("+v_natList +")" ,
					htmlBody : "("+ v_writer + ") (" + v_natList + ") 비자 협조의뢰 접수<br><br>"
				};
				ds_Mail.add(params4);
			}
		}
	}

	//메일 목록들을 반영
	var dataSet = {
			input1 : ds_Mail.getAllData()
	};

	gf_Transaction("SELECT_VISA_MAIL", "/co/common/mail/sendMail.xpl", {}, dataSet, "f_Callback", true);

}

function cf_setSignlnInfo(resultData) {
	ds_Signln.clear();

	var cnt = 0;
	ds_Signln.insert(cnt, {
		signId : "",
		signSeq : cnt + 1,
		signTpCd : "T01",
		signUserId : v_userId, // 세션에서 받아온 값
		signUserNm : v_userNm, // 세션에서 받아온 값
		psignUserNm : "",
		apperPositCd : v_userPositCd,
		apperPositNm : v_userPositCd,
		perpsignPositNm : "",
		apperRpswrkCd : v_userRpswrkCd,
		apperRpswrkNm : v_userRpswrkCd,
		apperOrgCd : v_orgCd,
		apperOrgNm : v_orgNm,
		orgChrcCls : "D"
	});

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
			$("#btnSignln").show();
		}else{
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


function f_callBackFuncNatSelect(obj) {
	//alert("국가선택");
	$("#fInfo").hide();
	$("#familyInfo").hide();
	$("#mInfo").hide();
	$("#militaryInfo").hide();

	if(obj.type == "D"){		// 삭제하는 경우.

		if(obj.targetId =="visitNat1"){
	  		$("input[name='visitNat1']").val("");
	  		$("input[name='visitArea1']").val("");
		}
		if(obj.targetId =="visitNat2"){
	  		$("input[name='visitNat2']").val("");
	  		$("input[name='visitArea2']").val("");
		}
		if(obj.targetId =="visitNat3"){
	  		$("input[name='visitNat3']").val("");
	  		$("input[name='visitArea3']").val("");
		}

	}else{
		$("input[name='"+obj.targetId+"']").val(obj.natNm);

		if(obj.targetId =="visitNat1"){
			$("input[name='visitArea1']").val(obj.cityNm);
			if (obj.naRiskYn == 'Y'){
				v_riskComm1 = "Y"
			}else{
				v_riskComm1 = "N"
			}
		}
		if(obj.targetId =="visitNat2"){
			$("input[name='visitArea2']").val(obj.cityNm);
			if (obj.naRiskYn == 'Y'){
				v_riskComm2 = "Y"
			}else{
				v_riskComm2 = "N"
			}
		}
		if(obj.targetId =="visitNat3"){
			$("input[name='visitArea3']").val(obj.cityNm);
			if (obj.naRiskYn == 'Y'){
				v_riskComm3 = "Y"
			}else{
				v_riskComm3 = "N"
			}
		}

		//visitNat1,2,3 국가정보 확인 후 보여줄 column들 update
		if(cf_GetFmlyInfoNcsYn($("input[name='visitNat1']").val())=='Y'
			||cf_GetFmlyInfoNcsYn($("input[name='visitNat2']").val()) =='Y'
				||cf_GetFmlyInfoNcsYn($("input[name='visitNat3']").val()) =='Y')
		{
			if((gv_AuthList.auth[0].code.match("RO_CMAS_CO_TRIP_ADM") == "RO_CMAS_CO_TRIP_ADM")
					||(gv_AuthList.auth[0].code.match("RO_CMAS_OT_007") == "RO_CMAS_OT_007")
					||(v_userId == $("#tripUserId_id").val() || v_userId == $("#tripUserId2_id").val())
					||(v_userId == v_writer && (v_docSts == "D16" || v_docSts == "")
					&& ($("#extnlPerYn").is(":checked") || $("#extnlPerYn2").is(":checked"))))
			{
				$("#familyInfo").show();
			}else{
				//alert("작성시"+v_docSts);
				if((v_docSts == "") && ($("#tripUserId_id").val()=="")){
					//alert("새로작성");
					$("#familyInfo").show();
					$("#fInfo").hide();
				}else{
					$("#familyInfo").hide();
					$("#fInfo").show();
				}
			}
		}

		if(cf_GetArmyInfoNcsYn($("input[name='visitNat1']").val())=='Y'
		||cf_GetArmyInfoNcsYn($("input[name='visitNat2']").val()) =='Y'
		||cf_GetArmyInfoNcsYn($("input[name='visitNat3']").val()) =='Y'){
			if((gv_AuthList.auth[0].code.match("RO_CMAS_CO_TRIP_ADM") == "RO_CMAS_CO_TRIP_ADM")
					||(gv_AuthList.auth[0].code.match("RO_CMAS_OT_007") == "RO_CMAS_OT_007")
					||(v_userId == $("#tripUserId_id").val()) && v_userId == $("#tripUserId2_id").val()
					||(v_userId == v_writer && (v_docSts == "D16" || v_docSts == "") &&
						($("#extnlPerYn").is(":checked") || $("#extnlPerYn2").is(":checked"))))
			{
				$("#militaryInfo").show();
			}else{
				if((v_docSts == "") && ($("#tripUserId_id").val()=="")){
					$("#militaryInfo").show();
					$("#mInfo").hide();
				}else{
					$("#mInfo").show();
					$("#militaryInfo").hide();
				}
			}
		}

		//여행 금지 국가 입력시 안내 문구
		if (v_riskComm1 == 'N' && v_riskComm2 == 'N' && v_riskComm3 == 'N'){
			$("#visitNatRiskComm").hide();
		}else{
			$("#visitNatRiskComm").show();
		}
	}
}

function cf_retrieveSignlnForExcluRegl(){
	var params = {
			orgCd : v_orgCd, 		//팀코드
			orgCls : "팀/현장"		//조회조건 (팀/현장, 본부/실, PD/임원, 회장)
	};
	gf_Transaction("SELECT_SIGNLN_FOR_EXCLU_REGL", "/co/common/user/retrieveSignlnForExcluRegl.xpl", params, {}, "f_Callback", false);
}

/**
* @class 결재선 데이터 갱신
* 2015-01-22 최종 결재자 입력 추가
*
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

function delPerchrgId(perchrgId){
	if(ds_Signln.find("signUserId", perchrgId) > -1) {				// 결재선에 포함되어 있으면
		ds_Signln.remove(ds_Signln.find("signUserId", perchrgId));	// 삭제
		for(var k=0; k<ds_Signln.size();k++){
			if(ds_Signln.get(k, "signSeq") > "3" && ds_Signln.find("signSeq", "3") == -1){	// 결재순번이 3이 있는경우 shift하면 안됨
				ds_Signln.set(k, "signSeq", ds_Signln.get(k, "signSeq")-1);
			}
		}
		gf_AssembleSignln(ds_Signln);
	}
}

/* 협의자 추가 루틴.. 추후 필요할 수 있어서 남겨둠 */
function addPerchrgId(perchrgId, orgId){
	if(ds_Signln.find("signUserId", perchrgId) == -1) {	// 결재선에 포함되어 있지 않으면
		if(ds_Signln.size() > 2){
			gf_InsertSignUser(perchrgId, orgId, "T03", "3");
		} else {
			gf_AddSignln(perchrgId, orgId, "T03", "3");		// 추가
		}
		gf_AssembleSignln(ds_Signln);
	}
	return;
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


// validation.
function f_CheckValidationForSave(paramForSave){

	if(gf_IsNull(paramForSave.docNo)) { gf_AlertMsg("문서번호는 필수입니다."); return false; }
	if(gf_IsNull(paramForSave.orgCd)) { gf_AlertMsg("작성자 조직코드는 필수입니다."); return false; }
	if(gf_IsNull(paramForSave.orgNm)) { gf_AlertMsg("작성자 조직명은 필수입니다."); return false; }
	if(gf_IsNull(paramForSave.userId)) { gf_AlertMsg("작성자 사번은 필수입니다."); return false; }

	//예산
	if(gf_IsNull(paramForSave.expCls)) { gf_AlertMsg("경비구분은 필수입니다."); return false; }
	if(gf_IsNull(paramForSave.bdgtNo)) { gf_AlertMsg("예산번호는 필수입니다."); return false; }
//	if(gf_IsNull(paramForSave.expDutyCls)) { gf_AlertMsg("SAP예산 업무구분은 필수입니다."); return false; }

	//비자정보
	if(gf_IsNull(paramForSave.appnCls)) { gf_AlertMsg("출장 신청구분을 선택해주세요."); return false; }

	if(gf_IsNull(paramForSave.departScdDd)) { gf_AlertMsg("출국예정일을 입력해주세요"); return false; }

	if(gf_IsNull(paramForSave.visitNat1)&&
			gf_IsNull(paramForSave.visitNat2) &&
			gf_IsNull(paramForSave.visitNat3)) { gf_AlertMsg("출장국가를 입력해주세요"); return false; }

	if(gf_IsNull(paramForSave.visitNat1)&&
			gf_IsNull(paramForSave.visitNat2) &&
			gf_IsNull(paramForSave.visitNat3)) { gf_AlertMsg("출장도시를 입력해주세요"); return false; }

	//방문목적, 비고 (비고는 비어 있을 수 있음). 글자 수 제한은 jsp 파일의 maxlength에서 처리함.
	if(gf_IsNull(paramForSave.visitGl)) { gf_AlertMsg("방문목적을 입력해주세요"); return false; }

	//일반정보
	if(paramForSave.extnlPerYn =="N" && gf_IsNull(paramForSave.tripuserId)) { gf_AlertMsg("대상자 사번을 입력해주세요"); return false; }

	if(gf_IsNull(paramForSave.userKnm)) { gf_AlertMsg("대상자 성명을 입력해주세요"); return false; }
	if(gf_IsNull(paramForSave.userDeptNm)) { gf_AlertMsg("대상자 소속을 입력해주세요"); return false; }
	if(gf_IsNull(paramForSave.mphoneNo)) { gf_AlertMsg("대상자 휴대전화번호를 입력해주세요"); return false; }
	if(gf_IsNull(paramForSave.marrYn)) { gf_AlertMsg("대상자 결혼유무를 입력해주세요"); return false; }

	if(gf_IsNull(paramForSave.emailAddr)) { gf_AlertMsg("대상자 이메일주소를 입력해주세요"); return false; }
	if(gf_IsNull(paramForSave.domi)) { gf_AlertMsg("대상자 본적을 입력해주세요"); return false; }
	if(gf_IsNull(paramForSave.curAddr)) { gf_AlertMsg("대상자 현 주소를 입력해주세요"); return false; }

	//협력업체 입력시 개인정보 활용 동의서 첨부가 필요함.
	if(paramForSave.extnlPerYn == "Y" && gf_IsNull(paramForSave.fileAtchId)){ gf_AlertMsg("협력업체의 경우 개인정보 활용동의서 첨부가 필요합니다."); return false; }

	//여행금지국가 입력후 협조의뢰 시 첨부파일 필수 첨부기능 및 팝업창 생성
	if((paramForSave.nat1RiskYn == "Y" || paramForSave.nat2RiskYn == "Y" || paramForSave.nat3RiskYn == "Y") && gf_IsNull(paramForSave.fileAtchId)){ gf_AlertMsg("예외적 여권 사용허가 신청 서류 및 발급된 예외적 여권 사용허가서 첨부가 필요합니다."); return false; }

	//가족정보..는 없을 수 있으니 validation이 필요없음.

	//병역정보..도 없을 수 있으니 validation이 필요없음. max length만 jsp에서 검토..

}

/**
* 결재상신처리
*
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function f_CheckValidationForDraft(){
	/**
	 * 결재선 Validation 체크
	 * 상신일 경우만 결재선지정여부를 체크한다.
	 */
	if(ds_Signln.size() <= 1) {
		gf_AlertMsg('co.err.cantSignLine');
		return false;
	}
	var rowCnt = ds_Signln.size();
	for ( var i = 0; i< rowCnt; i++ ) {
		if(ds_Signln.get(i, "signTpCd" ) == ""){
			gf_AlertMsg('co.err.emptySignType');
			return false;
		}
	}

	// 결재자 갯수 제한
	if(gf_IsSignlnCount(ds_Signln) == false){
		gf_AlertMsg("co.err.maxSignLine");
		return false;
	}

	if(ds_Signln.find("signTpCd", "T02") == -1) {
		gf_AlertMsg("결재자 지정을 해야 합니다.");
		return false;
	}

	// 최종 결재자가 협의로 끝나면 안됨
	for(var i = 0; i < ds_Signln.size(); i++){
		// 마지막 결재자 검사
		if(i == ds_Signln.size()-1){
			if(ds_Signln.get(ds_Signln.size()-1).signTpCd == "T03"){
				 gf_AlertMsg("최종 결재자를 지정해야 합니다.\n\n결재선을 지정해주세요.");
				 return false;
			}
		}
	}

}

//국가리스트에서 나라이름으로 나라코드를 구한다.
function cf_GetNatCd(natNm){
	if(natNm =="") return "";
	//나라이름 공백일때
	return ds_NatList.get(ds_NatList.find("natNm", natNm), "natCd");
}

//비자 신청시 가족정보 필요 여부 RETURN
function cf_GetFmlyInfoNcsYn(natNm){
	if(natNm =="") return "";
	return ds_NatList.get(ds_NatList.find("natNm", natNm), "fmlyInfoNcsYn");
}

//비자신청시 병역정보 필요여부 RETURN
function cf_GetArmyInfoNcsYn(natNm){
	if(natNm =="") return "";
	return ds_NatList.get(ds_NatList.find("natNm", natNm), "armyInfoNcsYn");
}

function f_SetVisaAppnInfo(){

	var param =
	{
		//문서 정보. 출장신청시에 바뀌지 않도록 분리.
		docNo : v_docNo,
		orgCd : v_orgCd,
		orgNm : v_orgNm,
//		userId : v_userId,
		userId : v_writer,
		docSts : gf_IsNull(v_docSts) ? "D16": v_docSts,		// D16 : 임시저장
		dutyCls : "05",	// 05 : VISA신청
		programCode : "SGNS070009",		//VISA 신청양식

		//SAP예산정보
 		expCls			: v_BdgtType,
 		bdgtNo			: v_Aufnr,
 		bdgtItem		: v_KText,
// 		expDutyCls		: $("#cGubun").val(),
// 		bdgtDoTeam		: v_Kostv,
// 		bdgtDoTeamNm	: v_Kostvnm,
 		expTransCd		: $("#vTcode").val(),

		//비자정보
		appnCls		: $("select[name='appnCls']").val(),
		departScdDd	: $("input[name='departScdDd']").val().replace(/-/gi,''),
		visitNat1	: cf_GetNatCd($("input[name='visitNat1']").val()),
		visitNat2	: cf_GetNatCd($("input[name='visitNat2']").val()),
		visitNat3	: cf_GetNatCd($("input[name='visitNat3']").val()),
		visitArea1	: $("input[name='visitArea1']").val(),
		visitArea2	: $("input[name='visitArea2']").val(),
		visitArea3	: $("input[name='visitArea3']").val(),
		visitGl		: $("input[name='tPurp']").val(),
		rem			: $("input[name='tRemark']").val(),

		visitNatNmList  :
			($("input[name='visitNat1']").val()+","+
			$("input[name='visitNat2']").val()+","+
			$("input[name='visitNat3']").val()).replace(/(^,+)/, "").replace(/(,+$)/, ""),

		//일반정보
	    extnlPerYn	: $("#extnlPerYn").is(":checked")?"Y":"N",

	    //출장자ID
		tripuserId	: $("#tripUserId_id").val(),
		userKnm		: $("#tripUserId_name").val(),
		userDeptNm	: $("input[name='tripUserOrgNm']").val(),
		userSsno	: $("input[name='userSsno']").val(),

		marrYn		: $("select[name='marriType']").val(),
		mphoneNo	: $("input[name='mphoneNo']").val(),
		emailAddr	: $("input[name='email']").val(),
		domi		: $("input[name='domi']").val(),
		curAddr		: $("input[name='curAddr']").val(),

	// 가족정보
		fatNm			: $("input[name='fatherNm']").val(),
	    fatBirymd		: $("input[name='fatherBirth']").val().replace(/-/gi,''),
	    motNm			: $("input[name='motherNm']").val(),
	    motBirymd		: $("input[name='motherBirth']").val().replace(/-/gi,''),
	    spouseNm		: $("input[name='spouseNm']").val(),
	    spouseBirymd	: $("input[name='spouseBirth']").val().replace(/-/gi,''),
	// 병역정보
	    amtye		: $("input[name='armyType']").val(),
	    ampos		: $("input[name='grade']").val(),
		enlmYmd		: $("input[name='enlistYmd']").val().replace(/-/gi,''),
		amdcgYmd	: $("input[name='demobYmd']").val().replace(/-/gi,''),

		//파일첨부
		fileAtchId	: gf_IsNull(v_FileAtchId) ? "": v_FileAtchId
	};

	//협조의뢰 메일 발송을 위해 변수에 선택국가 값 저장
	if($("input[name='visitNat1']").val() !=""){
  		v_natList = $("input[name='visitNat1']").val();
  	}
  	if($("input[name='visitNat2']").val() !=""){
  		v_natList = v_natList + ", " + $("input[name='visitNat2']").val();
  	}
  	if($("input[name='visitNat3']").val() !=""){
  		v_natList = v_natList + ", " + $("input[name='visitNat3']").val();
  	}

	return param;
}

function f_cleanVisaAppnInfo(){

//generalInfo,familyInfo,militaryInfo

//일반정보
	$("#tripUserId_id").val("");
	$("#tripUserId_name").val("");

	$("input[name='tripUserOrgNm']").val("");

	$("input[name='userSsno']").val("");
	$("select[name='marriType']").val("");

	$("input[name='mphoneNo']").val("");
	$("input[name='email']").val("");
	$("input[name='domi']").val("");
	$("input[name='curAddr']").val("");
//가족정보
	$("input[name='fatherNm']").val("");
	$("input[name='fatherBirth']").val("");
	$("input[name='motherNm']").val("");
	$("input[name='motherBirth']").val("");
	$("input[name='spouseNm']").val("");
	$("input[name='spouseBirth']").val("");
//병역정보
	$("input[name='armyType']").val("");
	$("input[name='grade']").val("");
	$("input[name='enlistYmd']").val("");
	$("input[name='demobYmd']").val("");

//gInfo,fInfo,mInfo

//일반정보YN
	$("#tripUserId2_id").val("");
	$("#tripUserId2_name").val("");

	$("input[name='tripUserOrgNm2']").val("");

	$("input[name='userSsno2']").val("");
	$("select[name='marriType2']").val("");

	$("input[name='mphoneNo2']").val("");
	$("input[name='email2']").val("");
	$("input[name='domi2']").val("");
	$("input[name='curAddr2']").val("");

//가족정보YN
	$("input[name='fatherNm2']").val("");
	$("input[name='fatherBirth2']").val("");
	$("input[name='motherNm2']").val("");
	$("input[name='motherBirth2']").val("");
	$("input[name='spouseNm2']").val("");
	$("input[name='spouseBirth2']").val("");

//병역정보YN
	$("input[name='armyType2']").val("");
	$("input[name='grade2']").val("");
	$("input[name='enlistYmd2']").val("");
	$("input[name='demobYmd2']").val("");
}

function f_getVisaAppnInfo(){
// 문서정보  (문서번호, 작성자, 소속팀)
  		$('#docNo').text(v_docNo);
  		$("#drafter").text(ds_visaAppn.get(0,"drafterNm")+
  				"("+ds_visaAppn.get(0,"drafterId")+")");
  		$("#drafterOrgNm").text(ds_visaAppn.get(0,"drafterOrgNm"));

//예산집행 정보
  		v_BdgtType = ds_visaAppn.get(0,"expCls");		//경비구분
  		v_Aufnr = ds_visaAppn.get(0,"bdgtNo");			//예산번호
  		v_KText = ds_visaAppn.get(0,"bdgtItem");		//예산 내역
// 		$("#cGubun").val(ds_visaAppn.get(0,"expDutyCls"));	//업무구분
// 		v_Kostv = ds_visaAppn.get(0,"bdgtDoTeam");			//예산집행팀코드
// 		v_Kostvnm = ds_visaAppn.get(0,"bdgtDoTeamNm");		//예산집행팀명
/* 		v_Chief = data.chief;
  		v_Chiefnm = data.chiefNm;*/

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
  			$("#vTcode").val(ds_visaAppn.get(0,"expTransCd"));
  			$("#vTcode").attr("readonly", "false");
  		}else if(v_BdgtType == "O"){
  			// O 입찰경비 일 경우 경비 이체코드 입력창을 보인다.
  			$("#VTCodeSpan").show();
  			$("#vTcode").val(ds_visaAppn.get(0,"expTransCd"));
  			$("#vTcode").attr("readonly", "true");
  		}else if(v_BdgtType == "K"){
  			// K 본사집행현장원가 일 경우 경비 이체코드 입력창을 보인다.
  			$("#VTCodeSpan").show();
  			$("#vTcode").val(ds_visaAppn.get(0,"expTransCd"));
  			$("#vTcode").attr("readonly", "true");
  		}

//비자 정보
		$("select[name='appnCls']").val(ds_visaAppn.get(0,"appnCls"));
  		$("input[name='departScdDd']").val(ds_visaAppn.get(0,"departScdDd"));

  		//국가
  		$("input[name='visitNat1']").val(ds_visaAppn.get(0,"visitNatNm1"));
  		$("input[name='visitNat2']").val(ds_visaAppn.get(0,"visitNatNm2"));
  		$("input[name='visitNat3']").val(ds_visaAppn.get(0,"visitNatNm3"));

  		//선택국가
  		if($("input[name='visitNat1']").val() !=""){
  			v_natList = $("input[name='visitNat1']").val();
  		}
  		if($("input[name='visitNat2']").val() !=""){
  			v_natList = v_natList + ", " + $("input[name='visitNat2']").val();
  		}
  		if($("input[name='visitNat3']").val() !=""){
  			v_natList = v_natList + ", " + $("input[name='visitNat3']").val();
  		}

  		//도시,현장
  		$("input[name='visitArea1']").val(ds_visaAppn.get(0,"visitArea1"));
  		$("input[name='visitArea2']").val(ds_visaAppn.get(0,"visitArea2"));
  		$("input[name='visitArea3']").val(ds_visaAppn.get(0,"visitArea3"));

  		//위험지역 추가
  		var nm1RiskYN = (ds_visaAppn.get(0,"nat1RiskYn")== "Y")? ds_visaAppn.get(0,"nat1RiskYn") : "N";
  		var nm2RiskYN = (ds_visaAppn.get(0,"nat2RiskYn")== "Y")? ds_visaAppn.get(0,"nat2RiskYn") : "N";
  		var nm3RiskYN = (ds_visaAppn.get(0,"nat3RiskYn")== "Y")? ds_visaAppn.get(0,"nat3RiskYn") : "N";
  		v_nmRiskYN = nm1RiskYN + nm2RiskYN + nm3RiskYN;

  		if (v_nmRiskYN.indexOf("Y")>= 0){
  			$("#visitNatRiskComm").show()
  		}else{
  			$("#visitNatRiskComm").hide()
  		}

  		//방문 목적
  		$("input[name='tPurp']").val(ds_visaAppn.get(0,"visitGl"));
  		//비고
  		$("input[name='tRemark']").val(ds_visaAppn.get(0,"rem"));

  		//비자접수일
  		$("#submitYmd").text(ds_visaAppn.get(0,"embsRcvDd"));
  		//비자발급일
  		$("#issueYmd").text(ds_visaAppn.get(0,"visaIssueDd"));
  		//비자발급예정일
  		$("#preissueYmd").text(ds_visaAppn.get(0,"visaIssueScdDd"));

// 일반정보
  		if(ds_visaAppn.get(0,"extnlPerYn") =='Y'){
			$("input:checkbox[id='extnlPerYn']").prop("checked", true);
			$("#tripUserId_id").attr('disabled','true');
			$("#tripUserId_name").val(ds_visaAppn.get(0,"userKnm"));
			$("#tripUserId_btn").hide();
  		}

  		//사번,성명.
  		$("#tripUserId_id").val(ds_visaAppn.get(0,"userId"));
  		$("#tripUserId_name").val(ds_visaAppn.get(0,"userKnm"));

  		$("input[name='tripUserOrgNm']").val(ds_visaAppn.get(0,"userDeptNm"));

  		$("input[name='userSsno']").val(ds_visaAppn.get(0,"userSsno"));
  		$("select[name='marriType']").val(ds_visaAppn.get(0,"marrYn"));

  		$("input[name='mphoneNo']").val(ds_visaAppn.get(0,"mphoneNo"));
  		$("input[name='email']").val(ds_visaAppn.get(0,"emailAddr"));
  		$("input[name='domi']").val(ds_visaAppn.get(0,"domi"));
  		$("input[name='curAddr']").val(ds_visaAppn.get(0,"curAddr"));
// 가족정보
		$("input[name='fatherNm']").val(ds_visaAppn.get(0,"fatNm"));
		$("input[name='fatherBirth']").val(ds_visaAppn.get(0,"fatBirymd"));
		$("input[name='motherNm']").val(ds_visaAppn.get(0,"motNm"));
		$("input[name='motherBirth']").val(ds_visaAppn.get(0,"motBirymd"));
		$("input[name='spouseNm']").val(ds_visaAppn.get(0,"spouseNm"));
		$("input[name='spouseBirth']").val(ds_visaAppn.get(0,"spouseBirymd"));
// 병역정보
  		$("input[name='armyType']").val(ds_visaAppn.get(0,"amtye"));
  		$("input[name='grade']").val(ds_visaAppn.get(0,"ampos"));
  		$("input[name='enlistYmd']").val(ds_visaAppn.get(0,"enlmYmd"));
  		$("input[name='demobYmd']").val(ds_visaAppn.get(0,"amdcgYmd"));

// 일반정보YN
  		if(ds_visaAppn.get(0,"extnlPerYn") =='Y'){
			$("input:checkbox[id='extnlPerYn2']").prop("checked", true);
			$("#tripUserId2_id").attr('disabled','true');
			$("#tripUserId2_name").val(ds_visaAppn.get(0,"userKnm"));
			$("#tripUserId2_btn").hide();
  		}

  		$("#tripUserId2_id").val(ds_visaAppn.get(0,"userId"));
  		$("#tripUserId2_name").val(ds_visaAppn.get(0,"userKnm"));

  		$("input[name='tripUserOrgNm2']").val(ds_visaAppn.get(0,"userDeptNm"));
  		$("input[name='tripUserOrgNm2']").attr("disabled",true);

  		$("input[name='userSsno2']").val(gf_IsNull(ds_visaAppn.get(0,"userSsno")) ? "" : "입력됨");
  		//$("select[name='marriType2']").val(ds_visaAppn.get(0,"marrYn"));

  		if (!gf_IsNull(ds_visaAppn.get(0,"marrYn"))){	//존재
  			$("select[name='marriType2'] option:eq(1)").before("<option value='입력됨'>입력됨</option>");
  			$("select[name='marriType2'] option:eq(1)").attr("selected","selected");
  			$("select[name='marriType2']").attr('disabled',true);
  		}else{
  			if($("select[name='marriType2'] option:selected").text()== "입력됨"){
  				$("select[name='marriType2']").attr('disabled',false);
  				$("select[name='marriType2'] option:selected").remove();
  			}
  		}

  		$("input[name='mphoneNo2']").val(gf_IsNull(ds_visaAppn.get(0,"mphoneNo")) ? "" : "입력됨");
  		$("input[name='mphoneNo2']").attr("disabled",true);

  		$("input[name='email2']").val(gf_IsNull(ds_visaAppn.get(0,"emailAddr")) ? "" : "입력됨");
  		$("input[name='email2']").attr("disabled",true);

  		$("input[name='domi2']").val(gf_IsNull(ds_visaAppn.get(0,"domi")) ? "" : "입력됨");
  		$("input[name='domi2']").attr("disabled",true);

  		$("input[name='curAddr2']").val(gf_IsNull(ds_visaAppn.get(0,"curAddr")) ? "" : "입력됨");
  		$("input[name='curAddr2']").attr("disabled",true);

// 가족정보YN
		$("input[name='fatherNm2']").val(gf_IsNull(ds_visaAppn.get(0,"fatNm")) ? "" : "입력됨");
		$("input[name='fatherNm2']").attr("disabled",true);

		$("input[name='fatherBirth2']").val(gf_IsNull(ds_visaAppn.get(0,"fatBirymd")) ? "" : "입력됨");
		$("input[name='fatherBirth2']").attr("disabled",true);

		$("input[name='motherNm2']").val(gf_IsNull(ds_visaAppn.get(0,"motNm")) ? "" : "입력됨");
		$("input[name='motherNm2']").attr("disabled",true);

		$("input[name='motherBirth2']").val(gf_IsNull(ds_visaAppn.get(0,"motBirymd")) ? "" : "입력됨");
		$("input[name='motherBirth2']").attr("disabled",true);

		$("input[name='spouseNm2']").val(gf_IsNull(ds_visaAppn.get(0,"spouseNm")) ? "" : "입력됨");
		$("input[name='spouseNm2']").attr("disabled",true);

		$("input[name='spouseBirth2']").val(gf_IsNull(ds_visaAppn.get(0,"spouseBirymd")) ? "" : "입력됨");
		$("input[name='spouseBirth2']").attr("disabled",true);

// 병역정보YN
  		$("input[name='armyType2']").val(gf_IsNull(ds_visaAppn.get(0,"amtye")) ? "" : "입력됨");
  		$("input[name='armyType2']").attr("disabled",true);

  		$("input[name='grade2']").val(gf_IsNull(ds_visaAppn.get(0,"ampos")) ? "" : "입력됨");
  		$("input[name='grade2']").attr("disabled",true);

  		$("input[name='enlistYmd2']").val(gf_IsNull(ds_visaAppn.get(0,"enlmYmd")) ? "" : "입력됨");
  		$("input[name='enlistYmd2']").attr("disabled",true);

  		$("input[name='demobYmd2']").val(gf_IsNull(ds_visaAppn.get(0,"amdcgYmd")) ? "" : "입력됨");
  		$("input[name='demobYmd2']").attr("disabled",true);
//첨부파일
  		gf_retrieveFileList(ds_visaAppn.get(0,"fileAtchId"));

  		v_FileAtchId = ds_visaAppn.get(0,"fileAtchId");

//국가1,2,3 에 따라 입력창 활성화 되는 부분이 달라짐.

		if(ds_visaAppn.get(0,"fmlyInfoNcsYn1") =='Y'
		|| ds_visaAppn.get(0,"fmlyInfoNcsYn2") =='Y'
		|| ds_visaAppn.get(0,"fmlyInfoNcsYn3") =='Y'){
			$("#fInfo").show();
			$("#familyInfo").show();
		}
		if(ds_visaAppn.get(0,"armyInfoNcsYn1") =='Y'
		|| ds_visaAppn.get(0,"armyInfoNcsYn2") =='Y'
		|| ds_visaAppn.get(0,"armyInfoNcsYn3") =='Y'){
			$("#mInfo").show();
			$("#militaryInfo").show();
		}
}

function f_getGeneralInfo(){
	var jumin1 = ds_GeneralInfo.get(0,"ctzNo").substring(0,6);
	var jumin2 = ds_GeneralInfo.get(0,"ctzNo").substr(6,1)+'******';
//출장자 소속
	$("input[name='tripUserOrgNm']").val(ds_GeneralInfo.get(0,"userDeptNm"));
	$("input[name='userSsno']").val(jumin1+"-"+jumin2);

	$("select[name='marriType']").val(ds_GeneralInfo.get(0,"marriType"));
	$("input[name='mphoneNo']").val(ds_GeneralInfo.get(0,"hpNo"));
	$("input[name='email']").val(ds_GeneralInfo.get(0,"email"));
	$("input[name='domi']").val(ds_GeneralInfo.get(0,"regiAddr"));
	$("input[name='curAddr']").val(ds_GeneralInfo.get(0,"curAddr"));

// 가족 정보
	$("input[name='fatherNm']").val(ds_GeneralInfo.get(0,"fatherNm"));
	$("input[name='fatherBirth']").val(ds_GeneralInfo.get(0,"fatherBirthYmd"));
	$("input[name='motherNm']").val(ds_GeneralInfo.get(0,"motherNm"));
	$("input[name='motherBirth']").val(ds_GeneralInfo.get(0,"motherBirthYmd"));
	$("input[name='spouseNm']").val(ds_GeneralInfo.get(0,"spouseNm"));
	$("input[name='spouseBirth']").val(ds_GeneralInfo.get(0,"spouseBirthYmd"));

//병역정보
	$("input[name='armyType']").val(ds_GeneralInfo.get(0,"armyType"));
	$("input[name='grade']").val(ds_GeneralInfo.get(0,"gradeType"));
	$("input[name='enlistYmd']").val(ds_GeneralInfo.get(0,"enlistYmd"));
	$("input[name='demobYmd']").val(ds_GeneralInfo.get(0,"demobYmd"));

//출장자 소속 YN
	$("#tripUserId_id").val(ds_GeneralInfo.get(0,"empId"));
	$("#tripUserId_name").val(ds_GeneralInfo.get(0,"empNm"));

	$("#tripUserId2_id").val(ds_GeneralInfo.get(0,"empId"));
	$("#tripUserId2_name").val(ds_GeneralInfo.get(0,"empNm"));

	$("input[name='tripUserOrgNm2']").val(ds_GeneralInfo.get(0,"userDeptNm"));
	$("input[name='tripUserOrgNm2']").attr('disabled',true);

 	$("input[name='userSsno2']").val(gf_IsNull(ds_GeneralInfo.get(0,"ctzNo")) ? "" : "입력됨");
 	$("input[name='userSsno2']").attr('disabled',true);

 	$("input[name='mphoneNo2']").val(gf_IsNull(ds_GeneralInfo.get(0,"hpNo")) ? "" : "입력됨");
 	$("input[name='mphoneNo2']").attr('disabled',true);

	if (!gf_IsNull(ds_GeneralInfo.get(0,"marriType"))){	//존재
		$("select[name='marriType2'] option:eq(1)").before("<option value='입력됨'>입력됨</option>");
		$("select[name='marriType2'] option:eq(1)").attr("selected","selected");
		$("select[name='marriType2']").attr('disabled',true);
	}else{
		if($("select[name='marriType2'] option:selected").text()== "입력됨"){
			$("select[name='marriType2']").attr('disabled',false);
			$("select[name='marriType2'] option:selected").remove();
		}
	}
 	//$("select[name='marriType2']").val(gf_IsNull(ds_GeneralInfo.get(0,"marriType")) ? "" : "입력됨");

	$("input[name='email2']").val(gf_IsNull(ds_GeneralInfo.get(0,"email")) ? "" : "입력됨");
	$("input[name='email2']").attr('disabled','true');

	$("input[name='domi2']").val(gf_IsNull(ds_GeneralInfo.get(0,"regiAddr")) ? "" : "입력됨");
	$("input[name='domi2']").attr('disabled','true');

	$("input[name='curAddr2']").val(gf_IsNull(ds_GeneralInfo.get(0,"curAddr")) ? "" : "입력됨");
	$("input[name='curAddr2']").attr('disabled','true');

// 가족 정보 YN, 입력됨..이면 해당 컬럼을 Disable 처리.
	$("input[name='fatherNm2']").val(gf_IsNull(ds_GeneralInfo.get(0,"fatherNm")) ?"" : "입력됨");
	$("input[name='fatherNm2']").attr('disabled','true');

	$("input[name='fatherBirth2']").val(gf_IsNull(ds_GeneralInfo.get(0,"fatherBirthYmd")) ?"" : "입력됨");
	$("input[name='fatherBirth2']").attr('disabled','true');

	$("input[name='motherNm2']").val(gf_IsNull(ds_GeneralInfo.get(0,"motherNm")) ?"" : "입력됨");
	$("input[name='motherNm2']").attr('disabled','true');

	$("input[name='motherBirth2']").val(gf_IsNull(ds_GeneralInfo.get(0,"motherBirthYmd")) ?"" : "입력됨");
	$("input[name='motherBirth2']").attr('disabled','true');

	$("input[name='spouseNm2']").val(gf_IsNull(ds_GeneralInfo.get(0,"spouseNm")) ?"" : "입력됨");
	$("input[name='spouseNm2']").attr('disabled','true');

	$("input[name='spouseBirth2']").val(gf_IsNull(ds_GeneralInfo.get(0,"spouseBirthYmd")) ?"" : "입력됨");
	$("input[name='spouseBirth2']").attr('disabled','true');

//병역정보 YN
	$("input[name='armyType2']").val(gf_IsNull(ds_GeneralInfo.get(0,"armyType")) ?"" : "입력됨");
	$("input[name='armyType2']").attr('disabled','true');
	$("input[name='grade2']").val(gf_IsNull(ds_GeneralInfo.get(0,"gradeType")) ?"" : "입력됨");
	$("input[name='grade2']").attr('disabled','true');
	$("input[name='enlistYmd2']").val(gf_IsNull(ds_GeneralInfo.get(0,"enlistYmd")) ?"" : "입력됨");
	$("input[name='enlistYmd2']").attr('disabled','true');
	$("input[name='demobYmd2']").val(gf_IsNull(ds_GeneralInfo.get(0,"demobYmd")) ?"" : "입력됨");
	$("input[name='demobYmd2']").attr('disabled','true');
}

function fn_SetUploadCallback( fileAtchId ) {

	v_FileAtchId = fileAtchId;

	//첨부파일까지 확인하고 DB에 데이터 업데이트(FILE UPLOAD 의 Callback은 임시저장
	var params = f_SetVisaAppnInfo();

	// 결재선마다 현재 docNo 추가
	for(var i=0; i<ds_Signln.size(); i++){
			ds_Signln.set(i, "docNo", v_docNo);		// 필수 : 문서번호
			ds_Signln.set(i, "dutyCls", "05");		// 필수 : 업무구분 05 = VISA 신청
	}

	var dataSets = {
		signln : ds_Signln.getAllData(),
	};

	gf_Transaction("SAVE_VISA_APPN", "/trip/visaAppn/saveVisaAppn.xpl", params, dataSets, "f_Callback", true);
}

function f_callBackFuncSelectScdDatePop(obj){

	v_ScdDate = obj.IssueScdDd;
	//접수자, 접수일 자동 입력. 원하는 양식으로 메일 발송...은 CALLBACK에서.
	var param = {
			docNo : v_docNo,
			ScdDate : v_ScdDate.replace(/-/gi,'')
	};

	gf_Transaction("VISA_SUBMIT_NOTIFY", "/trip/visaAppn/visaSubmitNotify.xpl", param, {}, "f_Callback", true);

}

function f_callBackFuncRejectPop(obj){

	v_RejectRes = obj;
	f_rejectDoc();
}

//개발 필요. DB 컬럼 추가.
function f_rejectDoc(){

	//메일발송 필요하면 나중에 만들자.

	var docSts = "D16";	//임시저장

	if(v_docSts == "D60"){ //GHR 검토
		docSts = "D64";
	 }

	var params = {
		docNo : v_docNo,
		docStsCd : docSts,
		ghrComment : $("#ghrComment").val(),
		rejectRes : v_RejectRes
	};

	//updateRejectComment
	//반려후 처리.
	gf_Transaction("REJECT_VISA_APPN", "/trip/visaAppn/updateRejectComment.xpl", params, {}, "f_Callback", true);
}

//결혼상태값 변경
function marrSt() {
	var selTy = $("select[name=marriType2]").val();

	if (selTy == "Y"){
		$("#marriType option:eq(1)").prop("selected", true);
	}else if (selTy == "N") {
		$("#marriType option:eq(2)").prop("selected", true);
	}else{
		$("#marriType option:eq(0)").prop("selected", true);
	}
}

//외부인 체크, 사용자
function chkExtSt(obj) {
	var extchkStmp = $('input:checkbox[id="extnlPerYn"]').is(":checked");
	var extchkStmp2 = $('input:checkbox[id="extnlPerYn2"]').is(":checked");

	//alert(obj.id);
	if (obj.id == "extnlPerYn2"){
		if (extchkStmp2){	//클릭 ginfo
			//문구보이기
			$("#gInfoComm").show();
			$("#gInfoComm1").show();
			$("#gInfoComm2").show();

			$("#generalInfo").show();
			$("#gInfo").hide();

			$("input:checkbox[id='extnlPerYn']").prop("checked", true);

			if(cf_GetFmlyInfoNcsYn($("input[name='visitNat1']").val())=='Y'
				||cf_GetFmlyInfoNcsYn($("input[name='visitNat2']").val()) =='Y'
				||cf_GetFmlyInfoNcsYn($("input[name='visitNat3']").val()) =='Y')
			{
				$("#familyInfo").show();
				$("#fInfo").hide();
			}else{
				//국가값이 없륻때
				if($("input[name='visitNat1']").val()!= "" || $("input[name='visitNat2']").val()!= "" || $("input[name='visitNat3']").val() != ""){
					$("#fInfo").show();
					$("#familyInfo").hide();
				}
			}

			if(cf_GetArmyInfoNcsYn($("input[name='visitNat1']").val())=='Y'
				||cf_GetArmyInfoNcsYn($("input[name='visitNat2']").val()) =='Y'
				||cf_GetArmyInfoNcsYn($("input[name='visitNat3']").val()) =='Y')
			{
				$("#mInfo").hide();
				$("#militaryInfo").show();
			}else{
				//국가값이 없륻때
				if($("input[name='visitNat1']").val()!= "" || $("input[name='visitNat2']").val()!= "" || $("input[name='visitNat3']").val() != ""){
					$("#mInfo").show();
					$("#militaryInfo").hide();
				}
			}

	       	//ID칸 입력 불가 ,조회 버튼 클릭 불가
			$("#tripUserId2_id").attr('disabled','true');
			$("#tripUserId2_btn").hide();

			//ID칸 입력 불가 ,조회 버튼 클릭 불가
	        $("#tripUserId_id").attr('disabled','true');
	        $("#tripUserId_btn").hide();
	        $("#tripUserId_name").css({"width":"80px"});

			//입력값 초기화
			f_cleanVisaAppnInfo();
		}
	}

	if (obj.id == "extnlPerYn"){
		if(!extchkStmp){	//해제 generalinfo
			//문구 숨기기
		    //alert("해제22");

			$("#gInfoComm").hide();
			$("#gInfoComm1").hide();
			$("#gInfoComm2").hide();

			//$("#fInfo").hide();

			//새문서
			//alert(v_docSts);
			if(v_docSts == "" || v_docSts == "D16"){
				$("#generalInfo").show();
				$("#gInfo").hide();
			}else{
				$("#generalInfo").hide();
				$("#gInfo").show();
			}

			$("input:checkbox[id='extnlPerYn2']").prop("checked", false);

	        //입력값들 초기화.
	        f_cleanVisaAppnInfo();

		    $("#tripUserId2_id").removeAttr('disabled');
		    $("#tripUserId2_btn").show();

		    $("#tripUserId_id").removeAttr('disabled');
		    $("#tripUserId_btn").show();
		}else{
			//alert("선택22");

			$("#gInfoComm1").show();
			$("#gInfoComm2").show();

			//ID칸 입력 불가 ,조회 버튼 클릭 불가
			$("#tripUserId2_id").attr('disabled','true');
			$("#tripUserId2_btn").hide();
			$("#tripUserId2_name").css({"width":"63px"});

			//ID칸 입력 불가 ,조회 버튼 클릭 불가
	        $("#tripUserId_id").attr('disabled','true');
	        $("#tripUserId_btn").hide();
	        $("#tripUserId_name").css({"width":"63px"});

			//입력값 초기화
			f_cleanVisaAppnInfo();

		}
	}
}

function f_GetFmlyInfoNcsYN(){
//선택 나라에 따라 가족사항, 병역  보이기/숨기기
	//alert("출장자 선택");
	if(cf_GetFmlyInfoNcsYn($("input[name='visitNat1']").val())=='Y'
		||cf_GetFmlyInfoNcsYn($("input[name='visitNat2']").val()) =='Y'
		||cf_GetFmlyInfoNcsYn($("input[name='visitNat3']").val()) =='Y'){
		if((gv_AuthList.auth[0].code.match("RO_CMAS_CO_TRIP_ADM") == "RO_CMAS_CO_TRIP_ADM")
				||(gv_AuthList.auth[0].code.match("RO_CMAS_OT_007") == "RO_CMAS_OT_007")
				||(v_userId == $("#tripUserId_id").val()) && v_userId == $("#tripUserId2_id").val()
				||(v_userId == v_writer && (v_docSts == "D16" || v_docSts == "") &&
					($("#extnlPerYn").is(":checked") || $("#extnlPerYn2").is(":checked"))))
		{
				$("#familyInfo").show();
				$("#fInfo").hide()
				//alert("11");

		}else{
				$("#gInfo").show();
				$("#fInfo").show();
				$("#generalInfo").hide();
				$("#familyInfo").hide();
				//alert("22");
		}
	}


	if(cf_GetArmyInfoNcsYn($("input[name='visitNat1']").val())=='Y'
	||cf_GetArmyInfoNcsYn($("input[name='visitNat2']").val()) =='Y'
	||cf_GetArmyInfoNcsYn($("input[name='visitNat3']").val()) =='Y'){
		if((gv_AuthList.auth[0].code.match("RO_CMAS_CO_TRIP_ADM") == "RO_CMAS_CO_TRIP_ADM")
				||(gv_AuthList.auth[0].code.match("RO_CMAS_OT_007") == "RO_CMAS_OT_007")
				||(v_userId == $("#tripUserId_id").val()) && v_userId == $("#tripUserId2_id").val()
				||(v_userId == v_writer && (v_docSts == "D16" || v_docSts == "") &&
					($("#extnlPerYn").is(":checked") || $("#extnlPerYn2").is(":checked"))))
		{
				$("#militaryInfo").show();
				$("#mInfo").hide();
		}else{
			$("#mInfo").show();
			$("#militaryInfo").hide();
		}
	}

}