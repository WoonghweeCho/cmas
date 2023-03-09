
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

var ds_IdAppnSysDtl = new DataSet();		// 시스템 담당자

//팝업이 닫힐때 리턴값을 전달할 callback 함수
var v_CallbackFunc;

//2014-07-30 추가 팀변경 위한 작성자 정보
var ds_Auth = new DataSet();

var v_ori_edYmd = [];	//원래 종료일자 비교용(나중에 수정)

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
	   	height:150,
	   	sortname: 'userId',
	    viewrecords: true,
	    sortorder: "desc",
	    rowNum:10000,
	    onSelectRow: function(rowid, status, e) {
	    	f_SetFormReadonly(false);
		}
	};
	$("#idAppnDtlList").jqGrid(idAppnDtlList);

//	$( "input[name='stYmd']" ).mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
	$( "input[name='edYmd']" ).mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
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


		/* 연장신청 시, 인사팀 담당자 협의라인에 추가기능 (시작) */
		var perchrgId = "";
		var orgId = "";

		while(ds_IdAppnSysDtl.find("sysCd", "20") != -1){
  			perchrgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "20"), "perchrgId");
  			orgId = ds_IdAppnSysDtl.get(ds_IdAppnSysDtl.find("sysCd", "20"), "orgId");
  			ds_IdAppnSysDtl.remove(ds_IdAppnSysDtl.find("sysCd", "20"));
  			addPerchrgId(perchrgId, orgId);
  		}

		ds_IdAppnSysDtl.reset();
		/* 연장신청 시, 인사팀 담당자 협의라인에 추가기능 (끝) */


		var params = f_SetDocInfo();

		// 결재선마다 현재 docNo 추가
		for(var i=0; i<ds_Signln.size(); i++){
  			ds_Signln.set(i, "docNo", v_docNo);		// 필수 : 문서번호
  			ds_Signln.set(i, "dutyCls", "04");		// 필수 : 업무구분 04 = 특별ID신청
  		}

  		var dataSets = {
  			signln : ds_Signln.getAllData(),
  			idAppnDtlList : ds_IdAppnDtlList.getAllData("U"),
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
		var newWin = gf_PostOpen("/id/idAppn/userIdSelect.jsp", null, "toolbar=no,scrollbars=no,width=420,height=400, resizable=0", params, true, true, "");
	});

	// 행삭제 버튼
	$("#delBtn").click(function(){
		if(ds_IdAppnDtlList.getPosition() == -1)
			return;
		var del_userId = ds_IdAppnDtlList.get(ds_IdAppnDtlList.getPosition(), "userId");

		ds_IdAppnDtlList.remove(ds_IdAppnDtlList.getPosition());
		f_SetFormReadonly(true);

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
	$("input[name='privCd']").click(function(){
		return false;
	});
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
	      		cls : "02",		// 02 : 연장
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

		var v_compMail = ds_IdAppnDtlList.get(i, "compMail");
		var compMail_length = ds_IdAppnDtlList.get(i, "compMail").length;
		var v_compMail_right10 = v_compMail.substring(compMail_length-10);
		if(v_compMail_right10 == "@dwenc.com"){
			gf_AlertMsg("[dwenc.com] 메일 사용중입니다. \n\n 메일 사용이 필요하시면  \n\n 특별ID[daewooenc 메일 사용]으로 신규 신청하시기 바랍니다." +
						"\n\n 문의 : 디지털개발팀 이진혁대리(02-2288-3464) ");
			if (!gf_ConfirmMsg("그래도 진행하시겠습니까?")){
				return false;
			}
		} ////



		if(ds_IdAppnDtlList.get(i, "mphoneNo" ) == ""){
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("휴대폰번호 입력은 필수입니다.");
			return false;
		}
		if(ds_IdAppnDtlList.get(i, "userId" ) == ""){
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("대상자 발급ID는 필수입니다.");
			return false;
		} else if(ds_IdAppnDtlList.get(i, "edYmd" ) == "") {
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("종료일자는 필수입니다.");
			return false;

		} else if(ds_IdAppnDtlList.get(i, "edYmd" ) == v_ori_edYmd[i]) {
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("종료일자를 연장하실 일자로 지정하시기 바랍니다. \n\n (연장 이전의 종료일자와 같습니다. 종료일자를 변경하세요)  \n\n *ID별로 종료일자를 지정하시기 바랍니다");
			return false;

		} else if(ds_IdAppnDtlList.get(i, "grd" ) == "") {
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("직급은 필수입니다.");
			return false;
		} else {
		}

		var enYmd = ds_IdAppnDtlList.get(i, "edYmd" ).replace(/-/gi,'');
		var date = new Date();
  		date.setMonth(date.getMonth()+12);
  		var afterOneYear = date.format('YYYYMMDD');	// 1년후

  		var date2 = new Date();
  		date2.setDate(date2.getDate()+1);
  		var afterOneDate = date2.format('YYYYMMDD');	// 1일후
  		date2.setMonth(date2.getMonth()+1);
  		var afterOneMonth = date2.format('YYYYMMDD');	// 1달후
  		date2.setDate(date2.getDate()+0);
  		var sameDate = date2.format('YYYYMMDD'); //  입력당일
  		var enYmdFormat = ds_IdAppnDtlList.get(i, "edYmd" );	// YYYY-MM-DD

  		if (enYmd > afterOneYear){
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("보안정책상 사용기간은 1년이내로 지정 가능합니다.\n(종료 전 연장신청 필요)");
			return false;
		}

  		if(afterOneDate > enYmd){
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("종료일자는 내일날짜 이후로 선택해야 합니다.");
			return false;
		}
//  		if(){
//			ds_IdAppnDtlList.setPosition(i, true);
//			gf_AlertMsg("종료일자 변경되지 않았습니다. 1년 이내로 연장 종료 일자를 변경해주시길 바랍니다.");
//			return false;
//		}

  		if(afterOneMonth > enYmd){
			ds_IdAppnDtlList.setPosition(i, true);
			if (!gf_ConfirmMsg("사용가능 일자가 30일 이내 입니다. \n *종료일자 : "+enYmdFormat+" \n\n 이대로 진행하시겠습니까?")){
				return false;
			}
		}




		// 직급 길이 제한(20 byte)
		if(getByteLength(ds_IdAppnDtlList.get(i, "grd" )) > 20){
			ds_IdAppnDtlList.setPosition(i, true);
			gf_AlertMsg("직급의 최대길이는 [한글6자 또는 영문20자] 이하 입니다.");
			return false;
		}

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
	  		ds_IdAppnSysDtl.setAllData(resultData.ds_IdAppnSysDtl);
			break;
	  	case "SELECT_ID_APPN_DTL_SYS_LIST" :
	  		v_writer = ds_Signln.get(0, "signUserId");
	  		ds_IdAppnSysDtl.setAllData(resultData.ds_IdAppnSysDtl);
/* (나중에 수정)*/
	  		//원래 종료일자와 비교용
	  		for(var i = 0 ; i < ds_IdAppnDtlList.size(); i++){
	  			v_ori_edYmd[i] = ds_IdAppnDtlList.get(i, "edYmd");
	  		}

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
	if(gv_AuthList.auth[0].code.match("RO_CMAS_CO_ADM") == "RO_CMAS_CO_ADM"
		|| gv_AuthList.auth[0].code.match("RO_CMAS_CO_ID_APPN") == "RO_CMAS_CO_ID_APPN") {
			$('input').removeAttr('disabled');
			$('textarea').removeAttr('disabled');
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
	  			cls : "02",		// 02 : 연장
	  			docSts : gf_IsNull(v_docSts) ? "D16": v_docSts,		// D16 : 임시저장
	  			dutyCls : "04",	// 04 : 특별ID
	  			programCode : "SGNS070006"
	  			};
	return param;
}

function f_SetDocInfoForAdm(){
	var param = {docNo : v_docNo,
	  			orgCd : "",
	  			orgNm : "",
	  			userId : "",
	  			cls : "02",		// 02 : 연장
	  			docSts : gf_IsNull(v_docSts) ? "D16": v_docSts,		// D16 : 임시저장
	  			dutyCls : "04",	// 04 : 특별ID
	  			programCode : "SGNS070006"
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

function f_addIdAppnSys(userId){
	/**
	 * 시스템코드
	 * 바로넷 : 01
	 * 바로콘 : 02
	 * 바로미 : 03
	 * 메일 : 04
	 * SVPN : 05
	 * 모바일 : 06
	 */
}

function setIdAppnDtlList(idAppnDtlList){
	for(var i = 0 ; i < idAppnDtlList.size(); i++){
		if(ds_IdAppnDtlList.find("userId", idAppnDtlList.get(i, "userId")) == -1) {
			idAppnDtlList.set(i, "docNo", v_docNo);
			ds_IdAppnDtlList.add(idAppnDtlList.get(i));
//			ds_IdAppnDtlList.set(i, "docNo", v_docNo);
		}
	}
	var dataSets = {
			idAppnDtlList : ds_IdAppnDtlList.getAllData()
  		};
	gf_Transaction("SELECT_ID_APPN_DTL_SYS_LIST", "/id/idAppn/retrieveUserIdSysList.xpl", params, dataSets, "f_Callback", true);
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
