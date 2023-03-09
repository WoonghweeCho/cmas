
/**
* @class 화면에서 사용할 전역변수를 아래에 선엄함
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
var v_BaseWindowHeight = 700;

var params = {};
var v_docNo = "";		//문서번호
var v_docSts = "";		//문서상태
var v_writer = "";		//작성자

/**
 * DataSet for 결재문서
 */
var ds_UserEtcInfo = new DataSet();		// 추가 유저정보
var ds_IdAppnDtlList = new DataSet();	// 상세정보
var ds_IdAppnSysList = new DataSet();	// 시스템 권한정보
var ds_Signln = new DataSet();			//결재선정보
var ds_Readtn = new DataSet();			//결재선정보
var ds_SignlnForExcluRegl = new DataSet();	//자동결재선정보

var ds_Baronet = new DataSet(); // 시스템,권한 리스트
var ds_Mail = new DataSet(); // 시스템,권한 선택 리스트
var ds_Baromi = new DataSet(); // 시스템,권한 선택 리스트
var ds_Barocon = new DataSet(); // 시스템,권한 선택 리스트
var ds_SVPN = new DataSet(); // 시스템,권한 선택 리스트
var ds_Mobile = new DataSet(); // 시스템,권한 선택 리스트
var ds_Safety = new DataSet(); // 시스템,권한 선택 리스트 ////CWH 스마트 세이프티 권한 추가중 220729
var ds_DRM = new DataSet(); // 시스템,권한 선택 리스트
var ds_POMS = new DataSet(); // 시스템,권한 선택 리스트
var ds_ITS = new DataSet(); // 시스템,권한 선택 리스트

var ds_IdAppnSysBaronet = new DataSet();	// 시스템 권한입력
var ds_IdAppnSysMail = new DataSet();	// 시스템 권한입력
var ds_IdAppnSysBaromi = new DataSet();	// 시스템 권한입력
var ds_IdAppnSysBarocon = new DataSet();	// 시스템 권한입력
var ds_IdAppnSysSVPN = new DataSet();	// 시스템 권한입력
var ds_IdAppnSysMobile = new DataSet();	// 시스템 권한입력
var ds_IdAppnSysSafety = new DataSet(); // 시스템 권한입력 ////CWH 스마트 세이프티 권한 추가중 220729*/
var ds_IdAppnSysDRM = new DataSet();	// 시스템 권한입력  // DRM 과 외주인력보안 테이블 통합  20210630 되살림0 20210812 - svpn 담당자 메일 발신 오류
var ds_IdAppnSysPOMS = new DataSet();	// 시스템 권한입력
var ds_IdAppnSysITS = new DataSet();	// 시스템 권한입력

var ds_IdAppnSysDtl = new DataSet();		// 시스템 담당자

//팝업이 닫힐때 리턴값을 전달할 callback 함수
var v_CallbackFunc;

//2014-07-30 추가 팀변경 위한 작성자 정보
var ds_Auth = new DataSet();

/**
* @class 화면 로드 완료 시 필요한 초기 작업 수행.
*        1. 파라미터 초기화
*        2. 컴포넌트 초기화
*        3. Event Listener 초기화
*        4. 바인딩 초기화
*        5. 화면내 Form 객체 초기화
*        6. 다국어 처리
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
$(function() {
	cf_SetComponents();
	cf_InitParam();
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
	v_docNo = gf_IsNull(datas)? "" : gf_IsNull(datas.docNo) ? "": datas.docNo;
	v_docSts = gf_IsNull(datas)? "" : gf_IsNull(datas.docSts) ? "": datas.docSts;
	v_CallbackFunc = gf_IsNull(datas.callbackFunc) ? "" : datas.callbackFunc;
}

/**
* @class 컴포넌트 생성
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetComponents()
{
	//특별ID 신청 상세 목록 JQGrid
	var idAppnDtlList = {
		datatype: "local",
	   	colNames:[gf_FindLang('발급ID'),gf_FindLang('팀'),gf_FindLang('직급'),gf_FindLang('이름'),],
	   	colModel:[
	  	   		{name:'userId',index:'userId', width:80,align: "center"},
	  	   		{name:'orgNm',index:'orgNm', width:60,align: "center"},
	  	   		{name:'grd',index:'grd', width:40,align: "center"},
	  	   	 	{name:'userNm',index:'userNm', width:40,align: "center"},
		],
		celledit: true,
	   	autowidth:true,
	   	height:70,
	   	sortname: 'userId',
	    viewrecords: true,
	    sortorder: "desc",
	    rowNum:10000,
	    onSelectRow: function(rowid, status, e) {
	    	f_SetFormReadonly(false);

	    	/**
	    	 * 시스템 신청내역 처리
	    	 */
	    	var rowData = $("#idAppnDtlList").data(rowid);
	    	var userId = rowData.get('userId');
	    	f_addIdAppnSys(userId);
		}
	};
	$("#idAppnDtlList").jqGrid(idAppnDtlList);

//	$( "input[name='stYmd']" ).mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
	$( "input[name='edYmd']" ).mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });

	// 바로넷 사용유무 선택 부분 구현
	gf_GetCommCds("ID004", ds_Baronet, function(CommGrpCd, data){});
	for(var i=0; i<ds_Baronet.size(); i++){
		$("#baronet").html($("#baronet").html() +
		"<input name='privCd' id='privCd' type='radio' value='" + ds_Baronet.get(i, 'code') + "' /><span>" + ds_Baronet.get(i, 'value') + "</span><br>");
	}



	// 바로미 사용 선택 부분 구현
	gf_GetCommCds("ID007", ds_Baromi, function(CommGrpCd, data){});
	for(var i=0; i<ds_Baromi.size(); i++){
		$("#baromi").html($("#baromi").html() +
		"<input name='privCd' id='privCd' type='radio' value='" + ds_Baromi.get(i, 'code') + "' /><span>" + ds_Baromi.get(i, 'value') + "</span><br>");
	}
	// 메일 사용 선택 부분 구현
	gf_GetCommCds("ID005", ds_Mail, function(CommGrpCd, data){});
	for(var i=0; i<ds_Mail.size(); i++){
		$("#mail").html($("#mail").html() +
		"<input name='privCd' id='privCd' type='radio' value='" + ds_Mail.get(i, 'code') + "' /><span>" + ds_Mail.get(i, 'value') + "</span><br>");
	}


	// 바로콘 사용 선택 부분 구현
	gf_GetCommCds("ID006", ds_Barocon, function(CommGrpCd, data){});
	for(var i=0; i<ds_Barocon.size(); i++){
		$("#barocon").html($("#barocon").html() +
		"<input name='privCd' id='privCd' type='checkbox' value='" + ds_Barocon.get(i, 'code') + "' /><span>" + ds_Barocon.get(i, 'value') + "</span><br>");
	}

	// 모바일 사용 선택 부분 구현
	gf_GetCommCds("ID009", ds_Mobile, function(CommGrpCd, data){});
	for(var i=0; i<ds_Mobile.size(); i++){
		$("#mobile").html($("#mobile").html() +
		"<input name='privCd' id='privCd' type='checkbox' value='" + ds_Mobile.get(i, 'code') + "' /><span>" + ds_Mobile.get(i, 'value') + "</span><br>");
	}

/*	// 똑바로(안전 사용 선택 부분 구현 ////CWH 스마트 세이프티 권한 추가중 220729 //// 014 테스트용 생성 하여 radio 로 테스트중
	gf_GetCommCds("ID013", ds_Safety, function(CommGrpCd, data){});
	for(var i=0; i<ds_Safety.size(); i++){
		$("#safety").html($("#safety").html() +
		"<input name='privCd' id='privCd' type='radio' value='" + ds_Safety.get(i, 'code') + "' /><span>" + ds_Safety.get(i, 'value') + "</span><br>");
	}*/ ////20221025 특별ID 운영 배포 건으로 개발에만 적용중인 똑바로 안전 관련 내용 주석처리


	// SVPN 사용 선택 부분 구현
	gf_GetCommCds("ID008", ds_SVPN, function(CommGrpCd, data){});
	for(var i=0; i<ds_SVPN.size(); i++){
		$("#SVPN").html($("#SVPN").html() +
		"<input name='privCd' id='privCd' type='radio' value='" + ds_SVPN.get(i, 'code') + "' /><span>" + ds_SVPN.get(i, 'value') + "</span><br>");
	}

	// DRM 사용 선택 부분 구현
//	gf_GetCommCds("ID010", ds_DRM, function(CommGrpCd, data){});
//	for(var i=0; i<ds_DRM.size(); i++){
//		$("#DRM").html($("#DRM").html() +
//		"<input name='privCd' id='privCd' type='radio' value='" + ds_DRM.get(i, 'code') + "' /><span>" + ds_DRM.get(i, 'value') + "</span><br>");
//	} 20210813 OK

	// 분양관리 사용 선택 부분 구현
	gf_GetCommCds("ID011", ds_POMS, function(CommGrpCd, data){});
	for(var i=0; i<ds_POMS.size(); i++){
		$("#POMS").html($("#POMS").html() +
		"<input name='privCd' id='privCd' type='radio' value='" + ds_POMS.get(i, 'code') + "' /><span>" + ds_POMS.get(i, 'value') + "</span><br>");
	}

	// 프로젝트 외주인력 보안 사용 선택 부분 구현
	gf_GetCommCds("ID012", ds_ITS, function(CommGrpCd, data){});
	for(var i=0; i<ds_ITS.size(); i++){
		$("#ITS").html($("#ITS").html() +
		"<input name='privCd' id='privCd' type='radio' value='" + ds_ITS.get(i, 'code') + "' /><span>" + ds_ITS.get(i, 'value') + "</span><br>");
	}

}

/**
* @class Element, Compoment Event 처리
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetEventListener()
{
	// 저장 버튼
	$("#btnSave").click(function(){
		if(f_CheckValidationForSave() == false){
			return;
		}

		var params = f_SetDocInfo();

		if(gv_AuthList.auth[0].code.match("RO_CMAS_CO_ADM") == "RO_CMAS_CO_ADM"
		|| gv_AuthList.auth[0].code.match("RO_CMAS_CO_ID_APPN") == "RO_CMAS_CO_ID_APPN") {
			if(v_userId != v_writer){
				ds_Signln.clear();
				params = f_SetDocInfoForAdm();
			}
		}

		// 결재선마다 현재 docNo 추가
		for(var i=0; i<ds_Signln.size(); i++){
  			ds_Signln.set(i, "docNo", v_docNo);		// 필수 : 문서번호
  			ds_Signln.set(i, "dutyCls", "04");		// 필수 : 업무구분 04 = 특별ID신청
  		}
  		var dataSets = {
  			signln : ds_Signln.getAllData(),
  			idAppnDtlList : ds_IdAppnDtlList.getAllData("U"),
  			idAppnSysBaronet : ds_IdAppnSysBaronet.getAllData("U"),
  			idAppnSysMail : ds_IdAppnSysMail.getAllData("U"),
  			idAppnSysBaromi : ds_IdAppnSysBaromi.getAllData("U"),
  			idAppnSysBarocon : ds_IdAppnSysBarocon.getAllData("U"),
  			idAppnSysSVPN : ds_IdAppnSysSVPN.getAllData("U"),
  			idAppnSysMobile : ds_IdAppnSysMobile.getAllData("U"),
  			idAppnSysSafety : ds_IdAppnSysSafety.getAllData("U"),////CWH 스마트 세이프티 권한 추가중 220729
//  			idAppnSysDRM : ds_IdAppnSysDRM.getAllData("U"), // DRM 과 외주인력보안 테이블 통합  20210630, 20210813 메일오류 확인 OK
  			idAppnSysPOMS : ds_IdAppnSysPOMS.getAllData("U"),
  			idAppnSysITS : ds_IdAppnSysITS.getAllData("U")
  		};
		gf_Transaction("SAVE_ID_APPN", "/id/idAppn/saveIdAppn.xpl", params, dataSets, "f_Callback", true);
	});

	// 삭제 버튼
	$("#btnDelete").click(function(){
		var params = f_SetDocInfo();
		params.docSts = "D06";			// 삭제 코드

		// 결재선마다 현재 docNo 추가
		for(var i=0; i<ds_Signln.size(); i++){
  			ds_Signln.set(i, "docNo", v_docNo);				// 필수 : 문서번호
  			ds_Signln.set(i, "dutyCls", "04");				// 필수 : 업무구분 04 = 특별ID신청
  			ds_Signln.set(i, "signStsCd", "S07");			// 필수 : 결재상태코드 S07 = 삭제
  		}
  		var dataSets = {
  			signln : ds_Signln.getAllData(),
  			idAppnDtlList : ds_IdAppnDtlList.getAllData("U"),
  			idAppnSysBaronet : ds_IdAppnSysBaronet.getAllData("U"),
  			idAppnSysMail : ds_IdAppnSysMail.getAllData("U"),
  			idAppnSysBaromi : ds_IdAppnSysBaromi.getAllData("U"),
  			idAppnSysBarocon : ds_IdAppnSysBarocon.getAllData("U"),
  			idAppnSysSVPN : ds_IdAppnSysSVPN.getAllData("U"),
  			idAppnSysMobile : ds_IdAppnSysMobile.getAllData("U"),
  			idAppnSysSafety : ds_IdAppnSysSafety.getAllData("U"),////CWH 스마트 세이프티 권한 추가중 220729
// 			idAppnSysDRM : ds_IdAppnSysDRM.getAllData("U"),// DRM 과 외주인력보안 테이블 통합  20210630, 20210813 메일 오류 확인OK
  			idAppnSysPOMS : ds_IdAppnSysPOMS.getAllData("U"),
  			idAppnSysITS : ds_IdAppnSysITS.getAllData("U")
  		};
		gf_Transaction("DELETE_ID_APPN", "/id/idAppn/saveIdAppn.xpl", params, dataSets, "f_Callback", true);
	});

	// 결재선등록 버튼
	$("#btnSignln").click(function(){
		var datas = {
			signId : ''
			, isDraft : true
			, signln : ds_Signln.getAll()
		};
		var newWin = gf_PostOpen("/common/jsp/sign/signUserSelect.jsp", null, "toolbar=no,scrollbars=no,width=1020,height=590", datas, true, true, null);
	});

	 // 결재 상신 버튼
	$("#btnDraft").click(function(){
		gf_EnableOverlay();
		if(ds_IdAppnDtlList.size() < 1 ){
			alert("특별ID를 1개이상 신청하셔야 합니다.");
			gf_DisableCurrentOverlay();
			return;
		}
		if(f_CheckValidationForSave() == false) {
			gf_DisableCurrentOverlay();
			return;
		}
		if(f_CheckValidationForDraft() == false) {
			gf_DisableCurrentOverlay();
			return;
		}
		if(ds_Signln.find("signTpCd", "T02") == -1) {
			alert("결재자 지정을 해야 합니다.");
			gf_DisableCurrentOverlay();
			return
		}

		/* IT운영팀 담당자, 인사팀 담당자, 모바일담당자, SVPN담당자, 메일(dwenc.com), DRM, 분양관리, 프로젝트 외주인력 보안 담당자 협의라인에 추가기능 시작 */
		var perchrgId = "";
		var orgId = "";

		while(ds_IdAppnSysDtl.find("sysCd", "00") != -1){
  			perchrgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "00"), "perchrgId");
  			orgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "00"), "orgId");
  			ds_IdAppnSysDtl.remove(ds_IdAppnSysDtl.find("sysCd", "00"));
  			addPerchrgId(perchrgId, orgId);
  		}
		for(var i=0; i<ds_IdAppnSysMobile.size(); i++){		// 특별ID 중에 모바일 사용이 하나라도 있으면 return
			//선택한 특별ID외에 모바일 사용 신청이 되어 있나 체크
			if(ds_IdAppnSysMobile.get(i, "privCd") != "0" && ds_IdAppnSysDtl.find("sysCd", "06") != -1){
	  			perchrgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "06"), "perchrgId");
	  			orgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "06"), "orgId");
	  			ds_IdAppnSysDtl.remove(ds_IdAppnSysDtl.find("sysCd", "06"));
	  			addPerchrgId(perchrgId, orgId);
	  			break;
			}
  		}
		for(var i=0; i<ds_IdAppnSysSafety.size(); i++){		//// 특별ID 중에  똑바로(안전) 사용이 하나라도 있으면 return
			//선택한 특별ID외에 똑바로(안전) 사용 신청이 되어 있나 체크
			if(ds_IdAppnSysSafety.get(i, "privCd") != "0" && ds_IdAppnSysDtl.find("sysCd", "16") != -1){
	  			perchrgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "16"), "perchrgId");
	  			orgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "16"), "orgId");
	  			ds_IdAppnSysDtl.remove(ds_IdAppnSysDtl.find("sysCd", "16"));
	  			addPerchrgId(perchrgId, orgId);
	  			break;
			}
		}
		for(var i=0; i<ds_IdAppnSysSVPN.size(); i++){		// 특별ID 중에 SVPN 사용이 하나라도 있으면 return
			//선택한 특별ID외에 SVPN 사용 신청이 되어 있나 체크
			if( (ds_IdAppnSysSVPN.get(i, "privCd") == "1" || ds_IdAppnSysSVPN.get(i, "privCd") == "2") && ds_IdAppnSysDtl.find("sysCd", "05") != -1){
				perchrgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "05"), "perchrgId");
	  			orgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "05"), "orgId");
	  			ds_IdAppnSysDtl.remove(ds_IdAppnSysDtl.find("sysCd", "05"));
	  			addPerchrgId(perchrgId, orgId);
				break;
			}
		}
		for(var i=0; i<ds_IdAppnSysMail.size(); i++){		// 특별ID 중에 메일(daewooenc.com) 사용이 하나라도 있으면 return
			//선택한 특별ID외에 메일(daewooenc.com) 사용 신청이 되어 있나 체크
			if(ds_IdAppnSysMail.get(i, "privCd") == "1" && ds_IdAppnSysDtl.find("sysCd", "08") != -1){
				perchrgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "08"), "perchrgId");
	  			orgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "08"), "orgId");
	  			ds_IdAppnSysDtl.remove(ds_IdAppnSysDtl.find("sysCd", "08"));
	  			addPerchrgId(perchrgId, orgId);
				break;
			}
		}
/*		for(var i=0; i<ds_IdAppnSysMail.size(); i++){		// 특별ID 중에 메일(dwenc.com) 사용이 하나라도 있으면 return
			//선택한 특별ID외에 메일(dwenc.com) 사용 신청이 되어 있나 체크
			if(ds_IdAppnSysMail.get(i, "privCd") == "3" && ds_IdAppnSysDtl.find("sysCd", "07") != -1){
				perchrgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "07"), "perchrgId");
	  			orgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "07"), "orgId");
	  			ds_IdAppnSysDtl.remove(ds_IdAppnSysDtl.find("sysCd", "07"));
	  			addPerchrgId(perchrgId, orgId);
				break;
			}
		}*/ //// dwenc.com 사용종료

//		for(var i=0; i<ds_IdAppnSysDRM.size(); i++){		// 특별ID 중에 DRM 사용이 하나라도 있으면 return
//			//선택한 특별ID외에 DRM 사용 신청이 되어 있나 체크
//			if(ds_IdAppnSysDRM.get(i, "privCd") == "1" && ds_IdAppnSysDtl.find("sysCd", "10") != -1){
//				perchrgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "10"), "perchrgId");
//	  			orgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "10"), "orgId");
//	  			ds_IdAppnSysDtl.remove(ds_IdAppnSysDtl.find("sysCd", "10"));
//	  			addPerchrgId(perchrgId, orgId);
//				break;
//			}
//		}  // DRM 과 외주인력보안 테이블 통합  20210630
		for(var i=0; i<ds_IdAppnSysPOMS.size(); i++){		// 특별ID 중에 분양관리 사용이 하나라도 있으면 return
			//선택한 특별ID외에 분양관리 사용 신청이 되어 있나 체크
			while(ds_IdAppnSysPOMS.get(i, "privCd") != "0" && ds_IdAppnSysDtl.find("sysCd", "11") != -1){
				perchrgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "11"), "perchrgId");
	  			orgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "11"), "orgId");
	  			ds_IdAppnSysDtl.remove(ds_IdAppnSysDtl.find("sysCd", "11"));
	  			addPerchrgId(perchrgId, orgId);
			}
		}
		for(var i=0; i<ds_IdAppnSysBaromi.size(); i++){		// 특별ID 중에 바로미 사용이 하나라도 있으면 return
			//선택한 특별ID외에 바로미 사용 신청이 되어 있나 체크
			while(ds_IdAppnSysBaromi.get(i, "privCd") != "0" && ds_IdAppnSysDtl.find("sysCd", "12") != -1){
				perchrgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "12"), "perchrgId");
	  			orgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "12"), "orgId");
	  			ds_IdAppnSysDtl.remove(ds_IdAppnSysDtl.find("sysCd", "12"));
	  			addPerchrgId(perchrgId, orgId);
			}
		}
//		for(var i=0; i<ds_IdAppnSysBarocon.size(); i++){		// 특별ID 중에 바로콘 사용이 하나라도 있으면 return
//			//선택한 특별ID외에 바로콘 사용 신청이 되어 있나 체크
//			while(ds_IdAppnSysBarocon.get(i, "privCd") != "0" && ds_IdAppnSysDtl.find("sysCd", "13") != -1){
//				perchrgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "13"), "perchrgId");
//  			orgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "13"), "orgId");
//	  			ds_IdAppnSysDtl.remove(ds_IdAppnSysDtl.find("sysCd", "13"));
//	  			addPerchrgId(perchrgId, orgId);
//			}
//		} 20220325 바로콘 협의자 제거

		for(var i=0; i<ds_IdAppnSysITS.size(); i++){		// 특별ID 중에 프로젝트 외주인력 보안 사용이 하나라도 있으면 return
			//선택한 특별ID외에 프로젝트 외주인력 보안 사용 신청이 되어 있나 체크
			while(ds_IdAppnSysITS.get(i, "privCd") != "0" && ds_IdAppnSysDtl.find("sysCd", "14") != -1){
				perchrgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "14"), "perchrgId");
	  			orgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "14"), "orgId");
	  			ds_IdAppnSysDtl.remove(ds_IdAppnSysDtl.find("sysCd", "14"));
	  			addPerchrgId(perchrgId, orgId);
			}
		}
		ds_IdAppnSysDtl.reset();
		/* IT운영팀 담당자, 인사팀 담당자, 모바일담당자, SVPN담당자, 메일(dwenc.com), DRM, 분양관리, 프로젝트 외주인력 보안 담당자 협의라인에 추가기능 끝 */

		var params = f_SetDocInfo();

		// 결재선마다 현재 docNo 추가
		for(var i=0; i<ds_Signln.size(); i++){
  			ds_Signln.set(i, "docNo", v_docNo);		// 필수 : 문서번호
  			ds_Signln.set(i, "dutyCls", "04");		// 필수 : 업무구분 04 = 특별ID신청
  		}

  		var dataSets = {
  			signln : ds_Signln.getAllData(),
  			idAppnDtlList : ds_IdAppnDtlList.getAllData("U"),
  			idAppnSysBaronet : ds_IdAppnSysBaronet.getAllData("U"),
  			idAppnSysMail : ds_IdAppnSysMail.getAllData("U"),
  			idAppnSysBaromi : ds_IdAppnSysBaromi.getAllData("U"),
  			idAppnSysBarocon : ds_IdAppnSysBarocon.getAllData("U"),
  			idAppnSysSVPN : ds_IdAppnSysSVPN.getAllData("U"),
  			idAppnSysMobile : ds_IdAppnSysMobile.getAllData("U"),
  			idAppnSysSafety : ds_IdAppnSysSafety.getAllData("U"),////CWH 스마트 세이프티 권한 추가중 220729
  			idAppnSysDRM : ds_IdAppnSysDRM.getAllData("U"),  // DRM 과 외주인력보안 테이블 통합  20210630 되살림1 20210812 - svpn 담당자 메일 발신 오류
  			idAppnSysPOMS : ds_IdAppnSysPOMS.getAllData("U"),
  			idAppnSysITS : ds_IdAppnSysITS.getAllData("U"),
  			oriIdAppnDtlList : ds_IdAppnDtlList.getAllData("N")
  		};
		gf_Transaction("DRAFT_ID_APPN", "/id/idAppn/draftIdAppn.xpl", params, dataSets, "f_Callback", true);
	});

	 //기안창 종료 버튼
	$("#btnCancle").click(function(){
		self.close();
	});

	// 행추가 버튼
	$("#addBtn").click(function(){
		gf_Transaction("SELECT_USER_ID", "/id/idAppn/getUserId.xpl", params, {}, "f_Callback", true);
	});

	// 행삭제 버튼
	$("#delBtn").click(function(){
		if(ds_IdAppnDtlList.getPosition() == -1)
			return;
		var del_userId = ds_IdAppnDtlList.get(ds_IdAppnDtlList.getPosition(), "userId");

		ds_IdAppnDtlList.remove(ds_IdAppnDtlList.getPosition());
		ds_IdAppnSysBaronet.remove(ds_IdAppnSysBaronet.find("userId", del_userId));
		ds_IdAppnSysMail.remove(ds_IdAppnSysMail.find("userId", del_userId));
		ds_IdAppnSysBaromi.remove(ds_IdAppnSysBaromi.find("userId", del_userId));
		ds_IdAppnSysBarocon.remove(ds_IdAppnSysBarocon.find("userId", del_userId));
		ds_IdAppnSysSVPN.remove(ds_IdAppnSysSVPN.find("userId", del_userId));
		ds_IdAppnSysMobile.remove(ds_IdAppnSysMobile.find("userId", del_userId));
		ds_IdAppnSysSafety.remove(ds_IdAppnSysSafety.find("userId", del_userId)); ////CWH 스마트 세이프티 권한 추가중 220729
		ds_IdAppnSysDRM.remove(ds_IdAppnSysDRM.find("userId", del_userId)); // DRM 과 외주인력보안 테이블 통합  20210630 되살림 20210812 - svpn 담당자 메일 발신 오류
		ds_IdAppnSysPOMS.remove(ds_IdAppnSysPOMS.find("userId", del_userId));
		ds_IdAppnSysITS.remove(ds_IdAppnSysITS.find("userId", del_userId));
		f_SetFormReadonly(true);

		/* 협의라인 추가 기능, 로직 변경으로 주석처리
		var perchrgId = "";
		var cnt = 0;
		perchrgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "05"), "perchrgId");
		for(var i=0; i<ds_IdAppnSysSVPN.size(); i++){		// 특별ID 중에 SVPN 사용이 하나라도 있으면 삭제 안함
			if(ds_IdAppnSysSVPN.get(i, "privCd") == "1"){
				cnt++;
			}
		}
		if (cnt == 0) delPerchrgId(perchrgId);

		cnt = 0;
		perchrgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "06"), "perchrgId");
		for(var i=0; i<ds_IdAppnSysMobile.size(); i++){		// 특별ID 중에 Mobile 사용이 하나라도 있으면 삭제 안함
			if(ds_IdAppnSysMobile.get(i, "privCd") != "0"){
				cnt++;
			}
		}
		if (cnt == 0) delPerchrgId(perchrgId);
		*/
	});

	//신규생성시 팀 입력
	$("#orgNmBtn").click(function() {
		if(ds_IdAppnDtlList.getPosition() == -1){
			gf_AlertMsg("먼저, 행추가 또는 행선택 후 진행해 주십시오.");
			return;
		}
		gf_OpenOrgPop(true, true, "f_orgPopCallback");
	});

	//2014-07-30 권한 Select Box 선택
	$("select[name='authCd']").change(function(){
		var orgCd = gf_GetValue($("select[name='authCd'] option:selected").val());
		var orgNm  = gf_GetValue($("select[name='authCd'] option:selected").text());

		ds_Auth.filter(
			function (DataSetRow) {
				if ( DataSetRow.get("value") == orgNm && DataSetRow.get("orgCd") == orgCd ) {
					return true;
				}
				return false;
			}
		);

		if ( ds_Auth.size() > 1 ) {
			gf_AlertMsg('error ');
		}

		var strPrivCd = ds_Auth.get(0, "code");
		var strSiteCd = ds_Auth.get(0, "siteCd");
		var strOrgCd = ds_Auth.get(0, "orgCd");
		var strOrgNm = ds_Auth.get(0, "orgNm");

		//2014-07-30 결재선에 바뀐 조직코드, 조직명 셋팅
		ds_Signln.set(0, "apperOrgCd", strOrgCd);
		ds_Signln.set(0, "apperOrgNm", strOrgNm);

		v_orgCd = strOrgCd;
		v_orgNm = strOrgNm;

		// filter 해제
		ds_Auth.filter(null);
		gf_ChangePrivDraft(strPrivCd, strSiteCd, strOrgCd);
		$("select[name='authCd']").val(orgCd);

		gf_EnableOverlay();
		alert("팀변경 시, 결재선은 초기화됩니다.");
		cf_retrieveSignlnForExcluRegl();
	} );

	$("#baronet input[name='privCd']").click(function() {
		if ($(this).attr("checked") == "checked"){
			//chkUsingBaronetMail();
		}
	});
//cwh 20211202 분양관리 메시지 용 추가
	$("#POMS input[name='privCd']").click(function() {
		if ($(this).attr("checked") == "checked"){
			if ($(this).val() !== "0"){
			gf_AlertMsg(" 분양관리 선택시  휴대폰 번호 입력 및 \n\n [회사메일] 항목에 개인 이메일 주소를 입력바랍니다. \n\n (메일 미사용선택).");
			}
		}
	});
//cwh 20211202 분양관리 메시지 용 추가

	$("#mail input[name='privCd']").click(function() {
		if ($(this).attr("checked") == "checked"){
			var tempUserId = ds_IdAppnSysMail.get(ds_IdAppnSysMail.getPosition(), "userId");
			var tempMail = ds_IdAppnDtlList.get(ds_IdAppnDtlList.find("userId", tempUserId), "compMail");
			var v_tempMail_left2 = tempMail.substring(0,2);   // 20210827 ZA 시작 하는 이메일 주소 구분
			//alert(v_tempMail_left2);
			if($(this).val() == "0"){
				ds_IdAppnDtlList.set(ds_IdAppnDtlList.find("userId", tempUserId), "compMail", "");
			} else {
				//CWH 20210827 이메일 주소 입력값이 null 이거나 ZA 로 시작하는 경우에만 자동 입력
				if(($(this).val() == "1" && tempMail =="") || ($(this).val() == "1" && v_tempMail_left2 == "ZA") || ($(this).val() == "1" && v_tempMail_left2 == "za")){
						ds_IdAppnDtlList.set(ds_IdAppnDtlList.find("userId", tempUserId), "compMail", tempUserId + "@daewooenc.com");
						gf_AlertMsg("[daewooenc.com] 메일을 선택하셨습니다. \n\n [회사메일] 항목에 [특별ID@daewooenc.com]로 기본설정 \n 되었습니다. \n\n 변경 원할시 [회사메일] 항목을 변경하시기 바랍니다.");
				}

				//CWH 20210827 이메일 주소 입력값이 null 이거나 ZA 로 시작하는 경우에만 자동 입력
				if($(this).val() == "2"){
					alert("PJ팀에서 공용으로 사용하는 이메일인 경우에만,\nPJ 공용메일로 신청해주시기 바랍니다.");
				}
			}

			//chkUsingBaronetMail();
		}
	});
	$("#barocon input[name='privCd']").click(function() {
		if ($(this).attr("checked") == "checked"){
			if($(this).val() == "0"){
				$("#barocon input[name=privCd]").prop("checked", false);
				$("#barocon input[name=privCd][value='0']").prop("checked", true);
				// prop 변경만으로 체크박스가 정상적으로 처리 안됨. 아래 로직 필요
				ds_IdAppnSysBarocon.set(ds_IdAppnSysBarocon.getPosition(), "privCd", "0");
			} else {
				$("#barocon input[name=privCd][value='0']").prop("checked", false);
				// prop 변경만으로 체크박스가 정상적으로 처리 안됨. 아래 로직 필요
				var str_privCd = ds_IdAppnSysBarocon.get(ds_IdAppnSysBarocon.getPosition(), "privCd");
				if(str_privCd.indexOf("0,") == 0){
					var len_privCd = str_privCd.length;
					ds_IdAppnSysBarocon.set(ds_IdAppnSysBarocon.getPosition(), "privCd", str_privCd.substr(2, len_privCd));
				}
								//문서관리 바로콘 로그인용 권한 체크박스 선택시 타 바로콘 권한 선택 초기화 추가  CWH 20211112//
					if($(this).val() == "ROLE_ICMS_DM_058"){
					$("#barocon input[name=privCd]").prop("checked", false);
					$("#barocon input[name=privCd][value='ROLE_ICMS_DM_058']").prop("checked", true);
					// prop 변경만으로 체크박스가 정상적으로 처리 안됨. 아래 로직 필요
					ds_IdAppnSysBarocon.set(ds_IdAppnSysBarocon.getPosition(), "privCd", "ROLE_ICMS_DM_058");
				} else {
					$("#barocon input[name=privCd][value='ROLE_ICMS_DM_058']").prop("checked", false);
					// prop 변경만으로 체크박스가 정상적으로 처리 안됨. 아래 로직 필요
					var str_privCd = ds_IdAppnSysBarocon.get(ds_IdAppnSysBarocon.getPosition(), "privCd");
					if(str_privCd.indexOf("ROLE_ICMS_DM_058,") == 0){
						var len_privCd = str_privCd.length;
						ds_IdAppnSysBarocon.set(ds_IdAppnSysBarocon.getPosition(), "privCd", str_privCd.substr(17, len_privCd));
					}
				}				//문서관리 바로콘 로그인용 권한 체크박스 선택시 타 바로콘 권한 선택 초기화 추가  CWH 20211112//
			}
		}
	});
	$("#SVPN input[name='privCd']").change(function() {
		/* 담당자 협의라인 추가 기능 로직 변경으로 주석처리
		//svpn 담당자 조회
		if(ds_IdAppnSysDtl.find("sysCd", "05") > -1){
			var perchrgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "05"), "perchrgId");
			var orgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "05"), "orgId");

			var selectedUserId = ds_IdAppnDtlList.get(ds_IdAppnDtlList.getPosition(), "userId");
			var isExist = false;

			//선택
			if ($(this).attr("checked") == "checked"){
				if($(this).val() == "0"){		// 미사용 선택시
					for(var i=0; i<ds_IdAppnSysSVPN.size(); i++){		// 특별ID 중에 SVPN 사용이 하나라도 있으면 return
						//선택한 특별ID외에 SVPN 사용 신청이 되어 있나 체크
						if(ds_IdAppnSysSVPN.get(i, "privCd") == "1" && ds_IdAppnSysSVPN.get(i, "userId") != selectedUserId){
							isExist = true;
						}
					}
					if(ds_Signln.find("signUserId", perchrgId) > -1 && !isExist) {				// 결재선에 포함되어 있으면
						ds_Signln.remove(ds_Signln.find("signUserId", perchrgId));	// 삭제
						for(var k=0; k<ds_Signln.size();k++){
							if(ds_Signln.get(k, "signSeq") > "3" && ds_Signln.find("signSeq", "3") == -1){	// 결재순번이 3이 있는경우 shift하면 안됨
								ds_Signln.set(k, "signSeq", ds_Signln.get(k, "signSeq")-1);
							}
						}
						gf_AssembleSignln(ds_Signln);
					}
				} else {		// 사용 선택시
					if(ds_Signln.find("signUserId", perchrgId) == -1 && !gf_IsNull(perchrgId)) {				// 결재선에 없으면
						if(ds_Signln.size() > 2){
							gf_InsertSignUser(perchrgId, orgId, "T03", "3");
						} else {
							gf_AddSignln(perchrgId, orgId, "T03", "3");		// 추가
						}
						gf_AssembleSignln(ds_Signln);
					}
				}
			}
		}
		*/
	});

	$("#mobile input[name='privCd']").change(function() {
		/* 담당자 협의라인 추가 기능 로직 변경으로 주석처리
		//모바일 담당자 조회
		if(ds_IdAppnSysDtl.find("sysCd", "06") > -1){
			var perchrgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "06"), "perchrgId");
			var orgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "06"), "orgId");

			var selectedUserId = ds_IdAppnDtlList.get(ds_IdAppnDtlList.getPosition(), "userId");
			var isExist = false;

			//선택
			if ($(this).attr("checked") == "checked"){
				if($(this).val() == "0"){		// 미사용 선택시
					for(var i=0; i<ds_IdAppnSysMobile.size(); i++){		// 특별ID 중에 SVPN 사용이 하나라도 있으면 return
						//선택한 특별ID외에 SVPN 사용 신청이 되어 있나 체크
						if(ds_IdAppnSysMobile.get(i, "privCd") == "1" && ds_IdAppnSysMobile.get(i, "userId") != selectedUserId){
							isExist = true;
						}
					}
					if(ds_Signln.find("signUserId", perchrgId) > -1 && !isExist) {				// 결재선에 포함되어 있으면
						ds_Signln.remove(ds_Signln.find("signUserId", perchrgId));	// 삭제
						for(var k=0; k<ds_Signln.size();k++){
							if(ds_Signln.get(k, "signSeq") > "3" && ds_Signln.find("signSeq", "3") == -1){	// 결재순번이 3이 있는경우 shift하면 안됨
								ds_Signln.set(k, "signSeq", ds_Signln.get(k, "signSeq")-1);
							}
						}
						gf_AssembleSignln(ds_Signln);
					}
				} else {		// 사용 선택시
					if(ds_Signln.find("signUserId", perchrgId) == -1 && !gf_IsNull(perchrgId)) {				// 결재선에 없으면
						if(ds_Signln.size() > 2){
							gf_InsertSignUser(perchrgId, orgId, "T03", "3");
						} else {
							gf_AddSignln(perchrgId, orgId, "T03", "3");		// 추가
						}
						gf_AssembleSignln(ds_Signln);
					}
				}
			}
		}
		*/
	});

	$("#mobile input[name='privCd']").click(function() {
		if ($(this).attr("checked") == "checked"){
			if($(this).val() == "0"){
				$("#mobile input[name=privCd]").prop("checked", false);
				$("#mobile input[name=privCd][value='0']").prop("checked", true);
				// prop 변경만으로 체크박스가 정상적으로 처리 안됨. 아래 로직 필요
				ds_IdAppnSysMobile.set(ds_IdAppnSysMobile.getPosition(), "privCd", "0");
			} else {
				$("#mobile input[name=privCd][value='0']").prop("checked", false);
				// prop 변경만으로 체크박스가 정상적으로 처리 안됨. 아래 로직 필요
				var str_privCd = ds_IdAppnSysMobile.get(ds_IdAppnSysMobile.getPosition(), "privCd");
				if(str_privCd.indexOf("0,") == 0){
					var len_privCd = str_privCd.length;
					ds_IdAppnSysMobile.set(ds_IdAppnSysMobile.getPosition(), "privCd", str_privCd.substr(2, len_privCd));
				}
			}
		}
	});

////CWH 스마트 세이프티 권한 추가중 220729
	$("#safety input[name='privCd']").click(function() {
		if ($(this).attr("checked") == "checked"){
			if($(this).val() == "0"){
				$("#safety input[name=privCd]").prop("checked", false);
				$("#safety input[name=privCd][value='0']").prop("checked", true);
				// prop 변경만으로 체크박스가 정상적으로 처리 안됨. 아래 로직 필요
				ds_IdAppnSysSafety.set(ds_IdAppnSysSafety.getPosition(), "privCd", "0");
			} else if($(this).val() != "0") {
			//	$("#safety input[name=privCd]").prop("checked", true);
				$("#safety input[name=privCd][value='0']").prop("checked", false);
				// prop 변경만으로 체크박스가 정상적으로 처리 안됨. 아래 로직 필요
				var str_privCd = ds_IdAppnSysSafety.get(ds_IdAppnSysSafety.getPosition(), "privCd");
				if(str_privCd.indexOf("0,") == 0){
					var len_privCd = str_privCd.length;
					ds_IdAppnSysSafety.set(ds_IdAppnSysSafety.getPosition(), "privCd", str_privCd.substr(2, len_privCd));
				}
			}
		}
	});

////CWH 스마트 세이프티 권한 추가중 220729
}



/**
* @class Form Onload DataSet Binding 처리
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetBinding()
{
	ds_IdAppnDtlList.bind($("#idAppnDtlList"));
	ds_IdAppnDtlList.bind($("#idAppnDtlForm")[0]);

	ds_IdAppnSysBaronet.bind($("#idAppnSysBaronet")[0]);
	ds_IdAppnSysMail.bind($("#idAppnSysMail")[0]);
	ds_IdAppnSysBaromi.bind($("#idAppnSysBaromi")[0]);
	ds_IdAppnSysBarocon.bind($("#idAppnSysBarocon")[0]);
	ds_IdAppnSysSVPN.bind($("#idAppnSysSVPN")[0]);
	ds_IdAppnSysMobile.bind($("#idAppnSysMobile")[0]);
//	ds_IdAppnSysSafety.bind($("#idAppnSysSafety")[0]);  ////CWH 스마트 세이프티 권한 추가중 220729 221025 특별ID 운영 배포 건으로 개발에만 적용중인 똑바로 안전 관련 내용 주석처리
//	ds_IdAppnSysDRM.bind($("#idAppnSysDRM")[0]);        // DRM 과 외주인력보안 테이블 통합  20210630 화면 뭉개짐 원인.
	ds_IdAppnSysPOMS.bind($("#idAppnSysPOMS")[0]);
	ds_IdAppnSysITS.bind($("#idAppnSysITS")[0]);

	//2014-07-30 추가 팀 변경
	//권한  DataSet Binding
	if ( gf_GetCookie("loclCd") == "ko_KR") {
		ds_Auth.bind($("select[name='authCd']")[0], {val: "orgCd", text: "value"});
		$("#title").removeClass("en");
		$("#settingBtn").removeClass("en");
	}
	else {
		ds_Auth.bind($("select[name='authCd']")[0], {val: "code", text: "orgNmEn"});
		$("#title").addClass("en");
		$("#settingBtn").addClass("en");
	}
}

/**
* @class Form Elemenet, Data 초기화
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_InitForm()
{
	/**
	 * 결재선 초기화
	 */
	gf_SignlnInit(v_docNo);
	gf_AssembleSignln(ds_Signln);

	/**
	 * 문서 초기화
	 */
	f_SetDocInit();
	f_SetFormReadonly(true);
	f_SetBtn();
	f_SetFirstRow();
}

/**
* @class 해당 결재양식 정보를 통해 결재문서 정보를 초기화 한다.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function f_SetDocInit(){
	if(v_docNo != ""){
		$('#docNo').html(v_docNo);
		// 기존문서 불러오기*
		var params = {
				docNo : v_docNo		//문서번호
		};
		gf_Transaction("SELECT_ID_APPN_DTL_LIST", "/id/idAppn/retrieveIdAppnDtlList.xpl", params, {}, "f_Callback", false);
	} else {
		// 새문서번호 불러오기
	    var params = {
	      		userId : v_userId,
	    		orgCd : v_orgCd,
	      		orgNm : v_orgNm,
	      		cls : "01",		// 01 : 신규
	      	};
		gf_Transaction("SELECT_DOC_NO", "/id/idAppn/getDocNo.xpl", params, {}, "f_Callback", false);
	}

	//2014-07-30 작성팀변경 추가
	if(!gf_IsNull(gv_AuthList)) {
		ds_Auth.setAllData(gv_AuthList.auth);

		if(gv_AuthList.auth.length <= 1) {
			$("#authCd").hide();
		} else {
			$("select[name='authCd'] option:eq(0)").before("<option value='팀 변경'>팀 변경</option>");
			$("select[name='authCd']").val("팀 변경");
		}
	}
}

function f_CheckValidationForSave(){
	if(gf_IsNull(v_docNo)) { gf_AlertMsg("문서번호는 필수입니다."); return; }
	if(gf_IsNull(v_orgCd)) { gf_AlertMsg("작성자 조직코드는 필수입니다."); return; }
	if(gf_IsNull(v_orgNm)) { gf_AlertMsg("작성자 조직명은 필수입니다."); return; }
	if(gf_IsNull(v_userId)) { gf_AlertMsg("작성자 사번은 필수입니다."); return; }

	for ( var i=0; i<ds_IdAppnDtlList.size(); i++ ) {
		if(ds_IdAppnDtlList.get(i, "userId" ) == ""){
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("대상자 발급ID는 필수입니다.");
			return false;
		} else if(ds_IdAppnDtlList.get(i, "userNm" ) == "") {
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("대상자 이름은 필수입니다.");
			return false;
		} else if(ds_IdAppnDtlList.get(i, "userNmEn" ) == "") {
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("대상자 영문이름은 필수입니다.");
			return false;
		} else if( isKorean(ds_IdAppnDtlList.get(i, "userNmEn" )) == false ) {
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("대상자 영문이름은 한글로 입력할 수 없습니다. 영문으로 입력하세요");
			return false;
		} else if(ds_IdAppnDtlList.get(i, "grd" ) == "") {
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("대상자 직급은 필수입니다.");
			return false;
		} else if(ds_IdAppnDtlList.get(i, "orgCd" ) == "") {
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("대상자 조직코드는 필수입니다.");
			return false;
		} else if(ds_IdAppnDtlList.get(i, "orgNm" ) == "") {
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("대상자 조직명은 필수입니다.");
			return false;
		} else if(ds_IdAppnDtlList.get(i, "stYmd" ) == "") {
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("시작일자는 필수입니다.");
			return false;
		} else if(ds_IdAppnDtlList.get(i, "edYmd" ) == "") {
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("종료일자는 필수입니다.");
			return false;
		} else if(ds_IdAppnDtlList.get(i, "memo" ) == "") {
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("사용목적(메모)는 필수입니다.");
			return false;
			//20220117 CWH 필수 입력값 추가 TEST start//
		} else if(ds_IdAppnDtlList.get(i, "compMail" ) == "") {
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("이메일 주소 입력은 필수입니다. \n 사내 메일 미사용 체크시 개인 이메일 주소 입력 바랍니다. ");
			return false;
		} else if(ds_IdAppnDtlList.get(i, "mphoneNo" ) == "") {
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("휴대폰번호 입력은 필수입니다.");
			return false;
			//20220117 CWH 필수 입력값 추가 TEST end//



		} else {
		}

		var tempUserId = ds_IdAppnDtlList.get(i, "userId" );
		if(ds_IdAppnSysMail.find("userId", tempUserId) != -1){
			var useYn = ds_IdAppnSysMail.get(ds_IdAppnSysMail.find("userId", tempUserId), "privCd");
			if(useYn != "0"){
				if(ds_IdAppnDtlList.get(i, "compMail") == ""){
					gf_AlertMsg("메일 사용 신청 시, 메일주소 입력은 필수입니다.");
					return false;
				}
			}
		}

		var stYmd = ds_IdAppnDtlList.get(i, "stYmd" ).replace(/-/gi,'');
		var enYmd = ds_IdAppnDtlList.get(i, "edYmd" ).replace(/-/gi,'');
		var date = new Date(stYmd.substring(0,4), stYmd.substring(4,6)-1, stYmd.substring(6,8));
  		date.setMonth(date.getMonth()+12);
  		var afterOneYear = date.format('YYYYMMDD');	// 1년후

  		var date2 = new Date();
  		date2.setDate(date2.getDate()+1);
  		var afterOneDate = date2.format('YYYYMMDD');	// 1일후
  		date2.setMonth(date2.getMonth()+1);
  		var afterOneMonth = date2.format('YYYYMMDD');	// 1달후

  		var enYmdFormat = ds_IdAppnDtlList.get(i, "edYmd" );	// YYYY-MM-DD

  		if(stYmd > enYmd){
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("시작일자는 종료일자보다 클 수 없습니다.");
			return false;
		}

  		if (enYmd > afterOneYear && v_userId != "1204594" && v_userId != "1202588" && v_userId != "1202540" && v_userId != "1202491" ){ // 관리자 기간체크 해제.
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("보안정책상 사용기간은 1년이내로 지정 가능합니다.\n(종료 전 연장신청 필요)");
			return false;
		}

  		if(afterOneDate > enYmd){
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("종료일자는 내일날짜 이후로 선택해야 합니다.");
			return false;
		}

  		if(afterOneMonth > enYmd){
			ds_IdAppnDtlList.setPosition(i, true);
			if (!gf_ConfirmMsg("사용가능 일자가 30일 이내 입니다. \n *종료일자 : "+enYmdFormat+" \n\n 이대로 진행하시겠습니까?")){
				return false;
			}
		}

		if(ds_IdAppnSysBaronet.size() < 1){
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("바로넷 입력은 필수입니다.");
			return false;
		} else if(ds_IdAppnSysBarocon.size() < 1){
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("바로콘 입력은 필수입니다.");
			return false;
		} else if(ds_IdAppnSysBaromi.size() < 1) {
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("바로미 입력은 필수입니다.");
			return false;
		} else if(ds_IdAppnSysMail.size() < 1) {
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("메일 입력은 필수입니다.");
			return false;
		} else if(ds_IdAppnSysSVPN.size() < 1) {
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("SVPN 입력은 필수입니다.");
			return false;
		} else if(ds_IdAppnSysMobile.size() < 1) {
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("모바일 입력은 필수입니다.");
			return false;
//		} else if(ds_IdAppnSysSafety.size() < 1) {
//			ds_IdAppnDtlList.setPosition(i, true);
//			gf_AlertMsg("공공도급사 관리자 입력은 필수입니다.");////CWH 스마트 세이프티 권한 추가중 220729
//			return false; ////
//		} else if(ds_IdAppnSysDRM.size() < 1) {
//			ds_IdAppnDtlList.setPosition(i, true);
//			gf_AlertMsg("DRM(문서보안) 입력은 필수입니다.");
//			return false;                              // DRM 과 외주인력보안 테이블 통합  20210630
		} else if(ds_IdAppnSysPOMS.size() < 1) {
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("분양관리 입력은 필수입니다.");
			return false;
		} else if(ds_IdAppnSysITS.size() < 1) {
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("프로젝트 외주인력 보안 입력은 필수입니다.");
			return false;
		}

		if(ds_IdAppnSysBarocon.get(i, "privCd") == ""){
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("바로콘 입력은 필수입니다.");
			return false;
		}

		//바로넷 사용 선택시에는 특별ID@daewooenc.com 만 사용가능
		if(ds_IdAppnSysBaronet.get(i, "privCd") == "1" && ds_IdAppnSysMail.get(i, "privCd") != "1") {
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("바로넷 사용 시, \n\n [daewooenc.com] 메일을 필수로 사용 신청해야 합니다.");
			return false;
		}

		//분양관리 사용 선택시에는 회사 메일주소 (메일 미사용 선택 후 개인 이메일 주소) 및 핸드폰 번호 필수 입력 필요  - CWH 20211201

		if(ds_IdAppnSysPOMS.get(i, "privCd") !== "0"  && ds_IdAppnDtlList.get(i, "compMail") == "") {
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("분양관리 사용 시, \n\n 메일 입력은 필수입니다. \n추가 인증시 사용되니 이메일 주소를  \n정확히 입력하시기 바랍니다.");
			return false;
		}

		if(ds_IdAppnSysPOMS.get(i, "privCd") !== "0"  && ds_IdAppnDtlList.get(i, "mphoneNo" ) == "") {
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("분양관리 사용 시, \n\n 휴대폰번호 입력은 필수입니다. \n추가 인증시 사용되니 휴대폰 번호를  \n정확히 입력하시기 바랍니다.");
			return false;
		}


		// - CWH 20211123





		//---------- 메일추가에 따른 체크(시작)(2017.03.28) ----------

		// 관리자는 메일체크 해제
		if (v_userId != "1204594" && v_userId != "1202588" && v_userId != "1202540" && v_userId != "1202491" ){

			//daewooenc.com 사용 선택시, 메일 입력칸에 @daewooenc.com 이 있는지 확인(오타로 수정하는 경우 방지)
			if(ds_IdAppnSysMail.get(i, "privCd") == "1") {
				var v_compMail = ds_IdAppnDtlList.get(i, "compMail");
				var compMail_length = ds_IdAppnDtlList.get(i, "compMail").length;
				var v_compMail_right14 = v_compMail.substring(compMail_length-14);
				if(v_compMail_right14 != "@daewooenc.com"){
					gf_AlertMsg("[daewooenc.com] 메일 사용 시, \n\n 회사메일 항목에 [@daewooenc.com]가 있어야 합니다.");
					return false;
				}
			}

/*			//dwenc.com 사용 선택시, 메일 입력칸에 @dwenc.com 이 있는지 확인(오타로 수정하는 경우 방지)
			if(ds_IdAppnSysMail.get(i, "privCd") == "3") {
				var v_compMail = ds_IdAppnDtlList.get(i, "compMail");
				var compMail_length = ds_IdAppnDtlList.get(i, "compMail").length;
				var v_compMail_right10 = v_compMail.substring(compMail_length-10);
				if(v_compMail_right10 != "@dwenc.com"){
					gf_AlertMsg("[dwenc.com] 메일 사용 시, \n\n 회사메일 항목에 [@dwenc.com]가 있어야 합니다.");
					return false;
				}
			}
*/
			if(ds_IdAppnSysMail.get(i, "privCd") == "3") {
					gf_AlertMsg("[dwenc.com] 메일은 사용 불가 입니다 .");
					return false;
				}


			//daewooenc.com OR dwenc.com 사용 선택시, 메일 입력칸에 특수문자 및 공백 이 있는지 확인, 불가(%&^#!${}[]~*) 가능((@._)
			if(ds_IdAppnSysMail.get(i, "privCd") == "0" || ds_IdAppnSysMail.get(i, "privCd") == "1" || ds_IdAppnSysMail.get(i, "privCd") == "3") {
				var v_compMail = ds_IdAppnDtlList.get(i, "compMail");

				//특수문자 체크
				if(f_patternSpecialChk(v_compMail) == false){
					gf_AlertMsg("[회사메일] 항목에 사용불가 특수문자를 입력할 수 없습니다 \n\n -사용불가 특수문자 : % & ^ # ! $ { } [ ] ~ * \n\n -사용가능 특수문자 : -(마이너스) .(점) ＿(언더바)");
					return false;
				}

				//공백 체크
				if(f_patternGapChk(v_compMail) == false){
					gf_AlertMsg("[회사메일] 항목에 공백을 입력할 수 없습니다");
					return false;
				}

				//메일유효성 체크
				if(f_patternEmailChk(v_compMail) == false){
					gf_AlertMsg("[회사메일] 항목에 이메일 규칙에 맞게 입력해 주세요");
					return false;
				}

			}

			//메일 미사용 선택시, 입력불가 메일 확인(@daewooenc.com, @dwenc.com)
			if(ds_IdAppnSysMail.get(i, "privCd") == "0") {
				var v_compMail = ds_IdAppnDtlList.get(i, "compMail");
				if(v_compMail.match("daewooenc.com") || v_compMail.match("dwenc.com")) {
					gf_AlertMsg("메일 미사용 시, 회사메일 항목에 회사메일계정으로 입력할 수 없습니다. \n\n (*회사메일계정 : daewooenc.com, dwenc.com)");
					return false;
				}
			}

		}

		// 직급 길이 제한(20 byte)
		if(getByteLength(ds_IdAppnDtlList.get(i, "grd" )) > 20){
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("직급의 최대길이는 [한글6자 또는 영문20자] 이하 입니다.");
			return false;
		}

		//---------- 메일추가에 따른 체크(끝)(2017.03.28) ----------


	}
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
}

function f_Close(){
}


/**
* @class Transaction 처리 후 수행되는 Callback 함수
*
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function f_Callback(strSvcId, obj, resultData){
	  if (!gf_ChkTransaction(strSvcId, resultData, true )) {
		  return;
	  }

	  switch(strSvcId) {
	  	case "SELECT_USER_ID" :
	  		var date = new Date();
	  		var today = date.format('YYYY-MM-DD');	// 오늘
	  		date.setMonth(date.getMonth()+1);
	  		var afterOneMonth = date.format('YYYY-MM-DD');	// 1달후

			ds_IdAppnDtlList.add(
					{
						docNo:v_docNo,
						userId:resultData.ds_Result[0].userId,
						userNm:"",
						userNmEn:"",
						grd:"",
						orgCd:ds_Signln.get(0, "apperOrgCd"),
						orgNm:ds_Signln.get(0, "apperOrgNm"),
						stYmd:today,
						edYmd:"",
						compMail:"",
						mphoneNo:"",
						compTelno:"",
						faxno:"",
						memo:"",
						empmtNo:"",
					});
			f_addIdAppnSys(resultData.ds_Result[0].userId);
			break;
	  	case "SELECT_DOC_NO" :
	  		v_docNo = resultData.ds_Result[0].docNo;
	  		v_writer = v_userId;
	  		$('#docNo').html(v_docNo);
	  		ds_IdAppnSysDtl.setAllData(resultData.ds_IdAppnSysDtl);

	  		gf_EnableOverlay();
	  		cf_retrieveSignlnForExcluRegl();
	  		break;
	  	case "SELECT_ID_APPN_DTL_LIST" : //기안작성 기본 정보 조회
	  		v_writer = ds_Signln.get(0, "signUserId");
	  		ds_IdAppnDtlList.setAllData(resultData.ds_IdAppnDtlList);
	  		ds_IdAppnSysBaronet.setAllData(resultData.ds_IdAppnSysBaronet);
	  		ds_IdAppnSysMail.setAllData(resultData.ds_IdAppnSysMail);
	  		ds_IdAppnSysBaromi.setAllData(resultData.ds_IdAppnSysBaromi);
	  		ds_IdAppnSysBarocon.setAllData(resultData.ds_IdAppnSysBarocon);
	  		ds_IdAppnSysSVPN.setAllData(resultData.ds_IdAppnSysSVPN);
	  		ds_IdAppnSysMobile.setAllData(resultData.ds_IdAppnSysMobile);
	  		ds_IdAppnSysSafety.setAllData(resultData.ds_IdAppnSysSafety);////CWH 스마트 세이프티 권한 추가중 220729
//	  		ds_IdAppnSysDRM.setAllData(resultData.ds_IdAppnSysDRM); // DRM 과 외주인력보안 테이블 통합  20210630, 20210812 메일오류 확인 OK
	  		ds_IdAppnSysPOMS.setAllData(resultData.ds_IdAppnSysPOMS);
	  		ds_IdAppnSysITS.setAllData(resultData.ds_IdAppnSysITS);
	  		ds_IdAppnSysDtl.setAllData(resultData.ds_IdAppnSysDtl);
			break;
	  	case "SELECT_SIGNLN_FOR_EXCLU_REGL" : // 전결규정 기반 결재선
	  		ds_SignlnForExcluRegl.setAllData(resultData.ds_SignlnForExcluRegl);

	  		ds_Signln.clear();
	  		ds_Signln.insert(0,{
	  			signSeq: "1",
	  			signTpCd: "T01",
	  			signUserId: v_userId,	// 세션에서 받아온 값
	  			signUserNm: v_userNm,	// 세션에서 받아온 값
	  			psignUserNm : "",
	  			apperPositCd: v_userPositCd,
	  			apperPositNm: v_userPositCd,
	  			perpsignPositNm : "",
	  			apperRpswrkCd: v_userRpswrkCd,
	  			apperRpswrkNm: v_userRpswrkCd,
	  			apperOrgCd: v_orgCd,
	  			apperOrgNm: v_orgNm,
	  			orgChrcCls: "D"
	  		});

	  		var size = ds_SignlnForExcluRegl.size();
	  		if(size == 0){
	  			$("#btnSignln").show();
	  		}
	  		for(var i = 0; i < size; i++){
	  			ds_Signln.insert(i+1,{
	  				signSeq: i+2,
	  				signTpCd: "T02",
	  				signUserId: ds_SignlnForExcluRegl.get(i, "signUserId"),	// 세션에서 받아온 값
	  				signUserNm: ds_SignlnForExcluRegl.get(i, "signUserNm"),	// 세션에서 받아온 값
	  				psignUserNm : "",
	  				apperPositCd: ds_SignlnForExcluRegl.get(i, "apperPositCd"),
	  				apperPositNm: ds_SignlnForExcluRegl.get(i, "apperPositNm"),
	  				perpsignPositNm : "",
	  				apperRpswrkCd: ds_SignlnForExcluRegl.get(i, "apperRpswrkCd"),
	  				apperRpswrkNm: ds_SignlnForExcluRegl.get(i, "apperRpswrkNm"),
	  				apperOrgCd: ds_SignlnForExcluRegl.get(i, "apperOrgCd"),
	  				apperOrgNm: ds_SignlnForExcluRegl.get(i, "apperOrgNm"),
	  				orgChrcCls: "D"
	  			});
	  		}

	  		gf_AssembleSignln(ds_Signln);
	  		gf_DisableCurrentOverlay();
	  		break;
	  	case "DRAFT_ID_APPN" : //상신
	  		if ( !gf_IsNull(v_CallbackFunc) ) {
	  			var openCallbackFunc = "opener."+v_CallbackFunc;
	  	    	eval(openCallbackFunc + "();");
	  	    }
	  		gf_DisableCurrentOverlay();
	  		self.close();
			break;
	  	case "SAVE_ID_APPN" :  //저장
	  		v_docSts = "D16";
	  		f_SetBtn();
	  		f_SetDocInit();
			break;
	  	case "DELETE_ID_APPN" :  //삭제
	  		if ( !gf_IsNull(v_CallbackFunc) ) {
	  			var openCallbackFunc = "opener."+v_CallbackFunc;
	  	    	eval(openCallbackFunc + "();");
	  	    }
	  		self.close();
			break;
	  	default :
	  		break;
	  }
}

function cf_retrieveSignlnForExcluRegl(){
	var params = {
			orgCd : v_orgCd, 		//팀코드
			orgCls : "팀/현장"		//조회조건 (팀/현장, 본부/실, PD/임원, 회장)
	};
	gf_Transaction("SELECT_SIGNLN_FOR_EXCLU_REGL", "/co/common/user/retrieveSignlnForExcluRegl.xpl", params, {}, "f_Callback", false);
}

function f_SetFormReadonly(readonly){
	if(gv_AuthList.auth[0].code.match("RO_CMAS_CO_ADM") == "RO_CMAS_CO_ADM"
	|| gv_AuthList.auth[0].code.match("RO_CMAS_CO_ID_APPN") == "RO_CMAS_CO_ID_APPN") {
		$('input').removeAttr('disabled');
		$('textarea').removeAttr('disabled');
	}
	switch(readonly) {
		case true :
			$('input').attr('disabled', 'true');
			$('textarea').attr('disabled', 'true');
			break;
		case false :
			if(v_userId == v_writer && (v_docSts == "D16" || v_docSts == "")){
				$('input').removeAttr('disabled');
				$('textarea').removeAttr('disabled');
			}
			break;
		default :
			break;
	}
}

function f_SetBtn(){
	if(v_docSts == "D16"){
		$("#btnDelete").show();
	}

	if(v_docSts != "D16" && v_docSts != "" || v_userId != v_writer){
		$("#btnSave").hide();
		$("#btnDelete").hide();
		$("#btnDraft").hide();
		$("#addBtn").hide();
		$("#delBtn").hide();
		$("#addBtn").hide();
		$("#authCd").hide();
	}

	if(gv_AuthList.auth[0].code.match("RO_CMAS_CO_ADM") == "RO_CMAS_CO_ADM"
	|| gv_AuthList.auth[0].code.match("RO_CMAS_CO_ID_APPN") == "RO_CMAS_CO_ID_APPN") {
		$("#btnSave").show();
		$("#btnDelete").show();
		if(v_docSts != "D16" && v_docSts != ""){
			$("#btnDelete").hide();
		}
	}
}

function f_SetFirstRow(){
	if(ds_IdAppnDtlList.size() >= 1){
		ds_IdAppnDtlList.setPosition(0, true);
	}
}

function f_SetDocInfo(){
	var param = {docNo : v_docNo,
	  			orgCd : v_orgCd,
	  			orgNm : v_orgNm,
	  			userId : v_userId,
	  			cls : "01",		// 01 : 신규
	  			docSts : gf_IsNull(v_docSts) ? "D16": v_docSts,		// D16 : 임시저장
	  			dutyCls : "04",	// 04 : 특별ID
	  			programCode : "SGNS070004"
	  			};
	return param;
}

function f_SetDocInfoForAdm(){
	var param = {docNo : v_docNo,
	  			orgCd : "",
	  			orgNm : "",
	  			userId : "",
	  			cls : "01",		// 01 : 신규
	  			docSts : gf_IsNull(v_docSts) ? "D16": v_docSts,		// D16 : 임시저장
	  			dutyCls : "04",	// 04 : 특별ID
	  			programCode : "SGNS070004"
	  			};
	return param;
}

function f_orgPopCallback(obj) {
	if(obj.size() > 0){
		var orgNm = obj.get(0, "orgNm");
		var orgCd = obj.get(0, "orgCd");
		ds_IdAppnDtlList.set(ds_IdAppnDtlList.getPosition(), "orgNm", orgNm);
		ds_IdAppnDtlList.set(ds_IdAppnDtlList.getPosition(), "orgCd", orgCd);
	}
}

function f_addIdAppnSys(userId){
	/**
	 * 시스템코드
	 * 바로넷 : 01
	 * 바로콘 : 02
	 * 바로미 : 03
	 * 메일 : 04
	 * SVPN : 05
	 * 모바일 : 06
	 * DRM : 07
	 * 분양관리 : 08
	 * 프로젝트 외주인력 보안 : 09
	 * 똑바로(안전) : 10
	 */

	if(ds_IdAppnSysBaronet.find("userId", userId) < 0)	ds_IdAppnSysBaronet.add({userId:userId, sysCd:"01", privCd:"0",});
	else ds_IdAppnSysBaronet.setPosition(ds_IdAppnSysBaronet.find("userId", userId), false);
	if(ds_IdAppnSysMail.find("userId", userId) < 0) ds_IdAppnSysMail.add({userId:userId, sysCd:"04", privCd:"0",});
	else ds_IdAppnSysMail.setPosition(ds_IdAppnSysMail.find("userId", userId), false);
	if(ds_IdAppnSysBaromi.find("userId", userId) < 0) ds_IdAppnSysBaromi.add({userId:userId, sysCd:"03", privCd:"0",});
	else ds_IdAppnSysBaromi.setPosition(ds_IdAppnSysBaromi.find("userId", userId), false);
	if(ds_IdAppnSysBarocon.find("userId", userId) < 0) ds_IdAppnSysBarocon.add({userId:userId, sysCd:"02", privCd:"0",});
	else ds_IdAppnSysBarocon.setPosition(ds_IdAppnSysBarocon.find("userId", userId), false);
	if(ds_IdAppnSysSVPN.find("userId", userId) < 0) ds_IdAppnSysSVPN.add({userId:userId, sysCd:"05", privCd:"0",});
	else ds_IdAppnSysSVPN.setPosition(ds_IdAppnSysSVPN.find("userId", userId), false);
	if(ds_IdAppnSysMobile.find("userId", userId) < 0) ds_IdAppnSysMobile.add({userId:userId, sysCd:"06", privCd:"0",});
	else ds_IdAppnSysMobile.setPosition(ds_IdAppnSysMobile.find("userId", userId), false);
	if(ds_IdAppnSysDRM.find("userId", userId) < 0) ds_IdAppnSysDRM.add({userId:userId, sysCd:"07", privCd:"0",});
	else ds_IdAppnSysDRM.setPosition(ds_IdAppnSysDRM.find("userId", userId), false);  // DRM 과 외주인력보안 테이블 통합  20210630 되살림 20210812 - svpn 담당자 메일 발신 오류
	if(ds_IdAppnSysPOMS.find("userId", userId) < 0) ds_IdAppnSysPOMS.add({userId:userId, sysCd:"08", privCd:"0",});
	else ds_IdAppnSysPOMS.setPosition(ds_IdAppnSysPOMS.find("userId", userId), false);
	if(ds_IdAppnSysITS.find("userId", userId) < 0) ds_IdAppnSysITS.add({userId:userId, sysCd:"09", privCd:"0",});
	else ds_IdAppnSysITS.setPosition(ds_IdAppnSysITS.find("userId", userId), false);
////CWH 스마트 세이프티 권한 추가중 220729
	if(ds_IdAppnSysSafety.find("userId", userId) < 0) ds_IdAppnSysSafety.add({userId:userId, sysCd:"10", privCd:"0",});
	else ds_IdAppnSysSafety.setPosition(ds_IdAppnSysSafety.find("userId", userId), false);
////CWH 스마트 세이프티 권한 추가중 220729

}

function setSignln(selSignln){
	//양식에 표현하기 위한 결재자 DataSet(ds_SignList)에 추가된 결재자를 삭제한다.
	ds_Signln.clear();

	//양식에 표현하기 위한 결재자 DataSet(ds_SignList)에 결재선을 추가한다.
	for(var i = 0 ; i < selSignln.size(); i++){
		ds_Signln.add(selSignln.get(i));
	}

	//지정된 결재자를 문서에 표시한다.
	gf_AssembleSignln(ds_Signln);
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

/*
function addPerchrgIdSVPN(perchrgId, orgId){
	for(var i=0; i<ds_IdAppnSysSVPN.size(); i++){		// 특별ID 중에 SVPN 사용이 하나라도 있으면 return
		if(ds_IdAppnSysSVPN.get(i, "privCd") == "1"){
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
	}
}
*/
/*
function addPerchrgIdMobile(perchrgId, orgId){
	for(var i=0; i<ds_IdAppnSysMobile.size(); i++){		// 특별ID 중에 모바일 사용이 하나라도 있으면
		if(ds_IdAppnSysMobile.get(i, "privCd") != "0"){
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
	}
}*/
function chkUsingBaronetMail(){
	var baronetPrivCd = $('#baronet').find(':radio[name="privCd"]:checked').val();
	var mailPrivCd = $('#mail').find(':radio[name="privCd"]:checked').val();
	var SVPNPrivCd = $('#SVPN').find(':radio[name="privCd"]:checked').val();

	if(baronetPrivCd == "1" || mailPrivCd == "1") {
		if(SVPNPrivCd == "1") {
			gf_AlertMsg("SVPN 신청상태가 미사용으로 변경되었습니다.\n(바로넷 또는 메일 신청 시, 불필요)");
		}
		$('#SVPN').find(':radio[name="privCd"]').attr('disabled', 'true');
		ds_IdAppnSysSVPN.set(ds_IdAppnSysSVPN.getPosition(), "privCd", "0");
		$("#SVPN").trigger('change');
	}

	if(baronetPrivCd == "0" && mailPrivCd == "0") {
		$('#SVPN').find(':radio[name="privCd"]').removeAttr('disabled');
		$("#SVPN").trigger('change');
	}
}

//길이제한체크(byte)
function getByteLength( str ) {

	var len = 0;

	if(str.length == 0) {
		return 0;
	}

	for(var i=0; i < str.length; i++) {
		var ch = escape(str.charAt(i));
		if( ch.length == 1 ) len++;
		else if( ch.indexOf("%u") != -1 ) len += 3;//DB가 한글을 3byte로 인식
		else if( ch.indexOf("%") != -1 ) len += ch.length/3;
	}

	return len;
}

/*
 * 입력값의 한글여부 체크
 * Parameter : value
 * Return : true(한글 미포함) / false(한글 포함)
 */
function isKorean(objValue)
{
    var ch = "\0";
	var flag = true;

	for (var i = 0, ch = objValue.charAt(i); (i<objValue.length) && (flag); ch = objValue.charAt(++i))
	{
        if ((ch >= '\uAc00') && (ch <= '\uD7A3')) flag = false;
    }

	return flag;
}

//특수문자 체크(메일용)
function f_patternSpecialChk(txt){
	var special_pattern = /[%&^#!${}~*\[\]]/;
	if( special_pattern.test(txt) == true ){
	    return false;
	}else{
		return true;
	}
}

//공백 체크(메일용)
function f_patternGapChk(txt){
	var special_pattern = / /;
	if( special_pattern.test(txt) == true ){
	    return false;
	}else{
		return true;
	}
}

//허용범위 체크(메일용)
function f_patternEmailChk(txt){
	var special_pattern1 = /(@.*@)|(\.\.)|(@\.)|(\.@)|(^\.)/;
	var special_pattern2 = /^[a-zA-Z0-9\-\.\_]+\@/;

	if( !special_pattern1.test(txt) && special_pattern2.test(txt) ){
	    return true;
	}else{
		return false;
	}

}
