/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/

var v_TrData = "<tr><td class=\"inpt\" style=\"text-align:center\"><input type=\"text\" name=\"tName\" style=\"width:95%\"></td>" +
			 "<td class=\"inpt\" style=\"text-align:center\"><input type=\"text\" name=\"tUseYmd\" style=\"width:95%\"></td>" +
			 "<td class=\"inpt\" style=\"text-align:center\"><input type=\"text\" name=\"tType\" style=\"width:95%\"><input type=\"hidden\" name=\"tTypeVal\"></td>" +
			 "<td class=\"inpt\" style=\"text-align:center\"><input type=\"text\" name=\"tPurp\" style=\"width:95%\"></td>" +
			 "<td class=\"inpt\" style=\"text-align:center\"><input type=\"text\" name=\"tSiteExpKind\" style=\"width:95%\"><input type=\"hidden\" name=\"tSiteExpKindVal\"></td>" +
			 "<td class=\"inpt\" style=\"text-align:center\"><input type=\"text\" name=\"tMvDist\" style=\"width:70%\">km</td>" +
			 "<td class=\"inpt\" style=\"text-align:center\"><input type=\"text\" name=\"tAmount\" style=\"width:80%\">원</td>" +
			 "<td class=\"inpt\" style=\"width:3%; text-align:center\"><img name=\"delDtl\" src=\"../../common/images/popup/icon_close.png\"></td></tr>";


var v_BdgtType = "";

var v_Aufnr = ""; 		// 예산
var v_KText = ""; 		// 예산내역
var v_Kostv = ""; 		// 집행팀
var v_Kostvnm = ""; 	// 집행팀 이름
var v_Chief = ""; 		// 집행팀장ID
var v_Chiefnm = ""; 	// 집행팀장 이름

var v_DrafterId = "";	// 기안자 사번
var v_DrafterNm = "";	// 기안자 이름
var v_DrafterOrgCd = "";	// 기안자 소속팀코드
var v_DrafterOrgNm = "";	// 기안자 소속팀명
var v_DrafterPositCd = "";	// 기안자 직급
var v_DrafterRpswrkCd = "";	// 기안자 직책

var v_hSignType;

var v_hSignUserId = "";
var v_hSignUserNm = "";
var v_hSignUserPositCd = "";
var v_hSignUserRpswrkCd = "";
var v_hSignUserCd = "";


var v_TotalAmount = "";

var v_isSaveEnable = "N";

var v_DocSts = ""; 	// 문서 상태값
var v_DocNo = ""; 	// 문서번호

var ds_DetailData = new DataSet();

var ds_SavedDoc = new DataSet();

var v_RefNo; // RefNo
var v_SapDoc;

//팝업이 닫힐때 리턴값을 전달할 callback 함수
var v_CallbackFunc;

var ds_Signln = new DataSet();			//결재선정보

// 결재선상 상태값
var v_dSignType = "N"; // 타 집행팀 여부
var v_dSignUserCd = "";
var v_dSignUserId = "";
var v_dSignUserNm = "";
var v_dSignOrgNm = "";
var v_dSignRpwrkNm = "";
var v_dSignRpswrkCd = "";
var v_dSignPositCd = "";
var v_dSignPositNm = "";

var v_dHSignUserCd = "";
var v_dHSignUserId = "";
var v_dHSignUserNm = "";
var v_dHSignOrgNm = "";
var v_dHSignRpwrkNm = "";
var v_dHSignRpswrkCd = "";
var v_dHSignPositCd = "";
var v_dHSignPositNm = "";

var v_IsOrgBoss = "";
var v_IsOfficer = "N";

var ds_ChiefCd = new DataSet();

// 기존 문서의 경우는 최초 한번 결재선을 불러온다.
// 수정된 결재선 저장 문서의 경우는 저장된 if_param 에서 결재선을 가져온다.
var v_IsSavedDoc = "N";

// 최종 결재자
var v_tSignUserCd;
var v_tSignUserId;

var v_isSignEdit = "N";  // 결재선 수정여부

var v_FileAtchId;		//첨부파일ID
var v_CoFileYn = "N";	//첨부파일 DB에 등록여부 체크

var valNoticeName = "\n\n ※ 현장경비 담당자: 예산관리팀 이선호 과장 (02-2288-2836)";	//현장경비 담당자 안내
var valNoticeHQName = "\n\n ※ 본사경비 담당자: 경영관리팀  이상민 차장 (02-2288-3101), \n                                          강해연 사원 (02-2288-4603)";	//본사경비 담당자 안내

/**
* @class 타블릿 화면 사용시 각페이지에서 생성되는 전역변수를 초기화시킴(팝업화면은 제외)
*        - 메모리 해제 관련 처리
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_GarbageCollector(){
//	delete v_signbxNm;

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

	v_DocNo = gf_IsNull(datas.fromList) ? "" : datas.fromList;
	v_DocSts = gf_IsNull(datas.docSts) ? "" : datas.docSts;

	v_CallbackFunc = gf_IsNull(datas.callbackFunc) ? ""          : datas.callbackFunc;

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

	$("#cityTranspSign").click(function(){
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


	$("#delBtn").click(function(){
		if($("#transpInfo tr").size() < 3){
			gf_AlertMsg("더이상 삭제할 수 없습니다");
		}else{
			$("#transpInfo tr:last").remove();
			ds_DetailData.remove(ds_DetailData.size()-1);

		}

	});

	$("#addBtn").click(function(){

		cf_AppendTranspDetail();

	});

	$("#cityTranspNumChange").click(function(){

		var bdgtParams = {
				bdgtType : v_BdgtType
		};

		gf_PostOpen("/common/jsp/comp/budget/bdgtSelect.jsp", null,
				"toolbar=no,scrollbars=no,width=665,height=600", bdgtParams,
				true, true, "f_callBackFuncBdgtSelect");

	});

	// 문서 상신
	$("#cityTranspDraft").click(function(){

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

		if(ds_DetailData.size() == 0){
			gf_AlertMsg("선청자를 입력해주세요.");
			return;
		}

  		if(v_tSignUserId == "" || v_tSignUserId == undefined){
  			gf_AlertMsg("최종 결재자가 지정되지 않았습니다.");
  			$(".btn").show();
  			return;
  		}

		if(v_Chief == undefined){
			gf_AlertMsg("집행팀장이 등록되지 않는 예산의 경우는 상신이 불가합니다.");
			return;
		}

		//현장경비인 경우[현장경비종류]와 [이동거리] 입력체크
		if(v_BdgtType == "Q"){	//Q현장경비
			var tSiteExpKind = $("input[name='tSiteExpKind']");	//현장경비종류
			var tMvDist = $("input[name='tMvDist']");	//이동거리
			var tSiteExpKindVal = "";
			var tMvDistVal = "";
			var valCheck = "Y";
			for(var i = 0; i < tMvDist.size(); i++){
				tSiteExpKindVal = tSiteExpKind[i].value;
				tMvDistVal = tMvDist[i].value;
				if(tSiteExpKindVal == "" || tSiteExpKindVal == "0" || tMvDistVal == ""){
					valCheck = "N";
				}
			}

			if(valCheck == "N"){
				gf_AlertMsg("현장경비 예산을 사용할 경우 \n\n [현장경비종류]와 [이동거리]는 필수로 입력해야 합니다\n\n"+valNoticeName);
				return;
			}

		}

		//삭제금지_20200709_나중에 테스트후 적용필요(출장 중복신청 방지기능)
		//신청중복체크
		//f_CheckDraftDuplication();
		//return;

		if(confirm("상신 하시겠습니까?")){
			v_CanSaveDraft = '2';	//첨부파일 콜백 작업시 '저장' 구분 (1:저장, 2:상신)
			gf_onFileUpload();
		}

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

	// 문서를 임시저장 처리 한다.
	// 이미 적상되어진 같을 JSON Object 형식으로 IF_PARAM 컬럼에 저장한다.
	$('#cityTranspSave').click(function(){

		if(confirm("작성 중인 문서를 저장하시겠습니까?")){
			v_CanSaveDraft = '1';	//첨부파일 콜백 작업시 '저장' 구분 (1:저장, 2:상신)
			gf_onFileUpload();
		}

	});

	$("#cityTranspDelete").click(function(){
		if(confirm("이 문서를 삭제하시겠습니까?")){

			var param = {
					docNo : v_DocNo
			};

			gf_Transaction("DELETE_CITY_TRANSP_DOC", "/trip/cityTransp/deleteCityTranspSavedDoc.xpl", param, {}, "f_Callback", true);
		}

	});

	$("#orgChgBtn").click(function(){
		if(confirm("소속팀을 변경하시겠습니까?\n(작성중인 내용이 초기화됩니다.)")){

			var distParams = {
				orgCd : v_DrafterOrgCd,
				authList : gv_AuthList.auth
			};

			gf_PostOpen("/trip/cityTransp/orgChgP.jsp", null,
					"toolbar=no,scrollbars=no,width=305,height=175", distParams,
					true, true, "f_callBackFuncOrgSelect");
		}
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
	 * 결재선 초기화
	 */
	//gf_SignlnInit(v_DocNo);
	//gf_AddSignln("1202564", "1DFUR", "T02", 2);

	//ds_Signln.setAllData(resultData.ds_SignlnForExcluRegl);

	//gf_AssembleSignln(ds_Signln);


	ds_ChiefCd.setAllData(gv_ChiefCd);


	// 로딩 Modal
	gf_InitSpinner(top.$('body'));
	// 임시저장
	if(v_DocSts == "D16"){

		$("#cityTranspDelete").show();

		var docNoText = v_DocNo.split("-");
  		$("#docNo").text(docNoText[1] + "-" + docNoText[2]);

  		var searchParams = {
  				docNo : v_DocNo
  		};

//  	var drafterText = gv_userId + " " + gv_userNm;
//		var drafterOrgNmText = gv_orgNm + "("+gv_orgCd+")";
//		$("#writer").text(drafterText);
//		$("#drafter").text(drafterText);
//		$("#drafterOrgNm").text(drafterOrgNmText);


  		// IF_PARAM 을 조회하여 문서에 셋팅한다.
  		gf_Transaction("SELECT_SAVED_DOC_INFO", "/trip/cityTransp/retrieveSavedDocInfo.xpl", searchParams, {}, "f_Callback", true);

		// 문서 조회해오기
//		gf_Transaction("SELECT_CMAS_ID", "/trip/cityTransp/getCmasId.xpl", {}, {}, "f_Callback", true);

	}else{

		// 겸직일 경우 소속팀 변경 버튼 팝업
		if(gv_AuthList.auth.length > 1){
			$("#orgChgBtn").show();
		}else{
			$("#orgChgBtn").hide();
		}

		// 최초작성
		v_DrafterId = gv_userId;
		v_DrafterNm = gv_userNm;
		v_DrafterOrgCd = gv_orgCd;
		v_DrafterOrgNm = gv_orgNm;
		v_DrafterPositCd = gv_userPositCd;
		v_DrafterRpswrkCd = gv_userRpswrkCd;


		var drafterText = gv_userId + " " + gv_userNm;
		var drafterOrgNmText = gv_orgNm + "("+gv_orgCd+")";
		$("#writer").text(drafterText);
		$("#drafter").text(drafterText);
		$("#drafterOrgNm").text(drafterOrgNmText);

		gf_Transaction("SELECT_CMAS_ID", "/trip/cityTransp/getCmasId.xpl", {}, {}, "f_Callback", true);

		// 팀장 이상 여부 초기화
		v_IsOrgBoss = "N";

		var isParam = {
  				userId : v_DrafterId,
  				orgCd : v_DrafterOrgCd,
  				drafterId : v_DrafterId,
  				drafterOrgCd : v_DrafterOrgCd
  		};

  		gf_Transaction("SELECT_IS_SPOT_MGMT", "/trip/innerTrip/retrieveIsSpotMgmt.xpl", isParam, {}, "f_Callback", true);

		cf_AppendTranspDetail();
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
	if (strSvcId != null && strSvcId != "CHECK_DRAFT_DUPLICATION" && strSvcId != "CHECK_CO_FILE_YN"){

	  // transaction의 정상 처리 여부를 판단한다.
	  if (!gf_ChkTransaction(strSvcId, resultData, true )) {
		  return;
	  }

	}

	  switch(strSvcId) {
	  	case "DELETE_CITY_TRANSP_DOC" :
	  		// 문서가 성공적으로 처리되면 문서 리스트를 다시 조회하여 갱신하여 준다.
	  		if ( !gf_IsNull(v_CallbackFunc) ) {
	  			var openCallbackFunc = "opener."+v_CallbackFunc;
	  	    	eval(openCallbackFunc + "();");
	  	    }
	  		self.close();
	  		break;

	  	case "SAVE_CITY_TRANSP_DOC_SAVE" :
	  		// 문서가 성공적으로 처리되면 문서 리스트를 다시 조회하여 갱신하여 준다.

	  		if(resultData.ds_DocData == undefined){
	  			if ( !gf_IsNull(v_CallbackFunc) ) {
		  			var openCallbackFunc = "opener."+v_CallbackFunc;
		  	    	eval(openCallbackFunc + "();");
		  	    }
		  		self.close();
	  		}else{
	  			gf_AlertMsg("저장할 수 없습니다. (이미 진행중인 문서거나 삭제된 문서입니다.");
	  			$(".ajax_overlay").remove();
	  			return;
	  		}
		  break;
	  	case "SELECT_SEND_TRAFFIC_COST" :
	  		if(resultData.output1[0].SapDoctab != undefined){
	  			if(resultData.output1[0].SapErrtab != undefined){
	  				if(resultData.output1[0].SapErrtab.constructor == Object){
	  					gf_AlertMsg(resultData.output1[0].SapErrtab.Message);
	  				}

	  				if(resultData.output1[0].SapErrtab.constructor == Array){
	  					for(var i = 0; i < resultData.output1[0].SapErrtab.length; i++){
	  						gf_AlertMsg(resultData.output1[0].SapErrtab[i].Message);
	  					}
	  				}



	  				v_isSaveEnable = "N";
	  				$(".btn").show();
	  				$(".ajax_overlay").remove();

	  				return;
	  			}else{
	  				cf_SaveCityTranspDraft(resultData.output1[0].SapDoctab);
	  			}

	  		}else{
	  			gf_AlertMsg("SAP 상신 중 에러가 발생했습니다.");
	  			v_isSaveEnable = "N";
	  			$(".btn").show();
	  			$(".ajax_overlay").remove();
	  			return;
	  		}
	  		break;
	  	case "SELECT_CMAS_ID" :

	  		v_DocNo = resultData.ds_Result[0].docNo;
	  		var docNoText = v_DocNo.split("-");
	  		$("#docNo").text(docNoText[1] + "-" + docNoText[2]);

	  		break;
	  	case "SELECT_SAVED_DOC_INFO" :

	  		//저장된 데이터 이므로 세션이 아닌 출장자정보에서 셋팅해야한다.
	  		ds_SavedDoc.setAllData(resultData.ds_DocData);

	  		v_IsSavedDoc = "Y";

	  		var data = JSON.parse(ds_SavedDoc.get(0, "ifParam"));

	  		//작성자 관련
	  		v_DrafterId = data.drafterId;
			v_DrafterNm = data.drafterNm;
			v_DrafterOrgCd = data.drafterOrgCd;
			v_DrafterOrgNm = data.drafterOrgNm;
			v_DrafterPositCd = data.drafterPositCd;
			v_DrafterRpswrkCd = data.drafterRpswrkCd;

			// 겸직일 경우 소속팀 변경 버튼 팝업
			if(gv_AuthList.auth.length > 1 && v_DrafterId == gv_userId){
				$("#orgChgBtn").show();
			}else{
				$("#orgChgBtn").hide();
			}

			$("#writer").text(v_DrafterId + " " + v_DrafterNm);


			var drafterText = gv_userId + " " + gv_userNm;
			var drafterOrgNmText = gv_orgNm + "("+gv_orgCd+")";

			$("#drafter").text(drafterText);
			$("#drafterOrgNm").text(drafterOrgNmText);


			if(data.signln == undefined || data.signln == "" || data.signln == null){
				// 저장된 결재선이 존재하지 않으므로 다시 결재선을 조회하여 셋팅한다.
				v_IsSavedDoc = "N";
			}else{
				// 결재선 셋팅
				ds_Signln.setAllData(JSON.parse(data.signln));

				// 마지막 결재선이 최종 결재자
				v_tSignUserCd = ds_Signln.get(ds_Signln.size()-1).signUserId;
				v_tSignUserId = ds_Signln.get(ds_Signln.size()-1).apperOrgCd;


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
				v_dHSignUserCd = data.v_dHSignUserCd;
				v_dHSignUserId = data.v_dHSignUserId;
				v_dHSignUserNm = data.v_dHSignUserNm;
				v_dHSignOrgNm = data.v_dHSignOrgNm;
				v_dHSignRpwrkNm = data.v_dHSignRpwrkNm;
				v_dHSignRpswrkCd = data.v_dHSignRpswrkCd;
				v_dHSignPositCd = data.v_dHSignPositCd;
				v_dHSignPositNm = data.v_dHSignPositNm;
				v_IsOrgBoss = data.v_IsOrgBoss;
				v_IsOfficer = data.v_IsOfficer;

			}

			if(v_IsSavedDoc == "Y"){

	  			//alert("결재선 생성해야됨");


	  			gf_AssembleSignln(ds_Signln);
	  			// 이후부터는 다시 결재선 재조회
	  			//v_IsSavedDoc = "N";
	  			$(".ajax_overlay").remove();

	  			v_isSignEdit = "Y";  // 결재선 수정여부


	  		}else{

	  			// 기존 저장 문서의 경우는 결재선을 전결규정에서 가져온다.

	  			var params = {
		  				orgCd : v_DrafterOrgCd,
		  				orgCls : "팀/현장",
		  				userId : v_DrafterId
		  		};

		  		gf_Transaction("SELECT_SIGN_LINE", "/co/common/user/retrieveSignlnForExcluRegl.xpl", params, {}, "f_Callback", true);

	  		}




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

	  		//
	  		ds_DetailData.setAllData(resultData.ds_DetailData);

	  		var totalAmt = 0;

	  		for(var i = 0; ds_DetailData.size() > i; i++){

	  			var amt = cf_SavedAppendTranspDetail(ds_DetailData.get(i));

	  			totalAmt = totalAmt + parseInt(amt);


	  			// readonly 처리
	  			$("#transpDetail tr input[name='tStart']").attr("readonly", "true");
	  			$("#transpDetail tr input[name='tEnd']").attr("readonly", "true");
	  			$("#transpDetail tr select[name='tType']").attr("disabled", "true");
	  			$("#transpDetail tr select[name='tRound']").attr("disabled", "true");
	  			$("#transpDetail tr input[name='tDist']").attr("readonly", "true");
	  			$("#transpDetail tr input[name='tAmount']").attr("readonly", "true");

			}

	  		$("#tAmountTotal").text(gf_AmtFormat(totalAmt));

	  		// 팀장 이상 여부 초기화
			v_IsOrgBoss = "N";

			//첨부파일
	  		gf_retrieveFileList(ds_SavedDoc.get(0,"fileAtchId"));
	  		v_FileAtchId = ds_SavedDoc.get(0,"fileAtchId");

			if(v_IsSavedDoc == "N"){

	  			// 기존 저장 문서의 경우는 결재선을 전결규정에서 가져온다.

				var isParam = {
		  				userId : v_DrafterId,
		  				orgCd : v_DrafterOrgCd,
		  				drafterId : v_DrafterId,
		  				drafterOrgCd : v_DrafterOrgCd
		  		};

		  		gf_Transaction("SELECT_IS_SPOT_MGMT", "/trip/innerTrip/retrieveIsSpotMgmt.xpl", isParam, {}, "f_Callback", true);

	  		}

	  		break;
	  	case "SELECT_CITY_TRANSP_DRAFT" :

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


	  		var dataSetParam = {
	  				ds_SignInfo : ds_SignInfo.getAllData("U")
			};

	  		if(resultData.output1[0].type == "SUCCESS"){

	  			// 국출 01 해출 02 시내 03
		  		var updateParams = {
		  				signId : signId,
		  				docNo : v_DocNo,
		  				dutyCls : '03'
		  		};

	  			gf_Transaction("SAVE_CMAS_DOC_UPDATE_SIGN_ID", "/trip/cityTransp/saveCmasDocUpdateSignId.xpl", updateParams, dataSetParam, "f_Callback", true);
	  		}else{

	  			var updateParams = {
		  				docSts : "D99",
		  				docNo : v_DocNo
		  		};

	  			gf_Transaction("SAVE_CMAS_DOC_UPDATE_SIGN_ID", "/trip/cityTransp/saveCmasDocUpdateFail.xpl", updateParams, {}, "f_Callback", true);

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
	  			if(v_hSignUserId == v_DrafterId) v_hSignType = "N";

	  			if(resultData.ds_IsSpotBoss != null){
		  			v_tSignUserCd = resultData.ds_IsSpotBoss[0].orgCd; // 현장소장 ORG
		  			v_tSignUserId = resultData.ds_IsSpotBoss[0].userId; // 현장소장 ID
	  			} else {

	  				// 2016-05-02
	  				// 현장 조직이나 현장소장이 등록되어 있지 않은 경우
	  				if(resultData.ds_DrafterOrgBoss == null){
	  					gf_AlertMsg("팀장/현장소장이 존재하지 않습니다. 최종결재자를 지정하세요.");

	  				}else{
	  					v_tSignUserCd = resultData.ds_DrafterOrgBoss[0].orgCd; // 최종 결재자 ORG
			  			v_tSignUserId = resultData.ds_DrafterOrgBoss[0].userId; // 최종결재자 ID
	  				}

	  			}
	  		}else{

	  			// 일반적인 경우에는 대체로 최종결재자 ID가 만들어지지만
	  			// 출장자가 임원일 경우 같은 조직에 팀장이 없으므로
	  			// 따로 처리를 해줘야한다.
	  			if(resultData.ds_Officer != null){
		  			// 임원일 경우
//	  				v_tSignUserCd = resultData.ds_DrafterOrgBoss[0].orgCd; // 최종 결재자 ORG
//		  			v_tSignUserId = resultData.ds_DrafterOrgBoss[0].userId; // 최종결재자 ID
	  				// 본인 전결
//	  				본인^본인
//	  	            (기안자^결재자)
	  				// 타집행팀 일 경우 다른 팀 예산 사용 못하도록 맞기
	  				// 본인 전결일 경우
	  				// 전결로 이 문서를 상신할 수 없습니다. 다른 작성자로 상신하세요.
	  				v_tSignUserCd = v_DrafterOrgCd;
	  				v_tSignUserId = v_DrafterId;
		  		}else{
		  			// 임원이 아닐 경우
//		  			v_tSignUserCd = resultData.ds_DrafterOrgBoss[0].orgCd; // 최종 결재자 ORG
//		  			v_tSignUserId = resultData.ds_DrafterOrgBoss[0].userId; // 최종결재자 ID

		  			if(resultData.ds_DrafterOrgBoss == null){
	  					gf_AlertMsg("팀장/현장소장이 존재하지 않습니다. 최종결재자를 지정하세요.");

//	  					var callbackFunc = "f_SelectSignInfo";
//	  					window.open(gv_ContextPath + "/common/jsp/comp/userSelect.jsp?userNm=" + "&callbackFunc="+callbackFunc,"","toolbar=no,scrollbars=no,width=600,height=420");
	  				}else{
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
	  				}
	  			}
	  		}else{
	  			v_IsOrgBoss = "N";

	  		}

	  		var params = {
	  				orgCd : v_DrafterOrgCd,
	  				orgCls : "팀/현장",
	  				userId : v_DrafterId
	  		};

	  		gf_Transaction("SELECT_SIGN_LINE", "/co/common/user/retrieveSignlnForExcluRegl.xpl", params, {}, "f_Callback", true);

//	  		cf_getCouserInfo();



	  		break;
	  	case "SELECT_SIGN_LINE" :
//	  		ds_Signln.setAllData(resultData.ds_SignlnForExcluRegl);
//	  		gf_AssembleSignln(ds_Signln);

	  		cf_setSignlnInfo(resultData);

	  		break;
	  	case "SELECT_CO_USER_INFO" :

	  		if(resultData.ds_coUser == null){

	  			//2015-06-09
	  			gf_AlertMsg("최종 결재자가 지정되지 않았습니다. 결재자를 선택해주세요");
				// 결재선을 지정할 수 있도록 버튼을 팝업한다.
				$("#cityTranspSign").show();
				return;
	  		}

	  		if(v_tSignUserCd == undefined || v_tSignUserId == undefined){
	  			v_tSignUserCd = resultData.ds_coUser[0].orgCd;
	  			v_tSignUserId = v_Chief;
	  		}

	  		// 최종결재자와 집행팀장의 CO 조직코드를 비교한다.
	  		if(v_tSignUserCd != resultData.ds_coUser[0].orgCd && v_Chief != v_tSignUserId){


	  			if(v_BdgtType == "Q" && v_DrafterOrgCd == v_Kostv){
	  				v_dSignType = "N";
	  			}else{
//	  				gf_AlertMsg("타팀 예산 사용시 집행팀이 협의자로 지정됩니다.");
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

	  			}


	  		// 2015 08 17 추가 팀장 아닌 사람이 집행팀장으로 등록될 경우가 있음
	  		// 별도 처리
	  		}else if(v_tSignUserCd == resultData.ds_coUser[0].orgCd && v_Chief != v_tSignUserId){
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
	  		}else{
	  			v_dSignType = "N";
	  		}

	  		// 결재선을 다시 그린다.
	  		var params = {
	  				orgCd : v_DrafterOrgCd,
	  				orgCls : "팀/현장",
	  				userId : v_DrafterId
	  		};

	  		gf_Transaction("SELECT_SIGN_LINE", "/co/common/user/retrieveSignlnForExcluRegl.xpl", params, {}, "f_Callback", true);

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
				if (v_hSignUserId == v_DrafterId)
					v_hSignType = "N";

			}

			ds_Signln.clear();

			var cnt = 0;

			// ##
			//
			// var tripUser; // 출장자
			// var tripUserNm;
			// var tripUserTeam; // 출장자팀
			// var tripUserTeamNm;
			// var tripUserPositCd;
			// var tripUserRpswrkCd;


			if(v_IsOrgBoss == "Y"){
				ds_Signln.insert(cnt, {
					signId : "",
					signSeq : cnt + 1,
					signTpCd : "T01",
					signUserId : v_DrafterId, // 세션에서 받아온 값
					signUserNm : v_DrafterNm, // 세션에서 받아온 값
					psignUserNm : "",
					apperPositCd : v_DrafterPositCd,
					apperPositNm : v_DrafterPositCd,
					perpsignPositNm : "",
					apperRpswrkCd : v_DrafterRpswrkCd,
					apperRpswrkNm : v_DrafterRpswrkCd,
					apperOrgCd : v_DrafterOrgCd,
					apperOrgNm : v_DrafterOrgNm,
					orgChrcCls : "D"
				});

			}else{
				ds_Signln.insert(cnt, {
					signId : "",
					signSeq : cnt + 1,
					signTpCd : "T01",
					signUserId : v_DrafterId, // 세션에서 받아온 값
					signUserNm : v_DrafterNm, // 세션에서 받아온 값
					psignUserNm : "",
					apperPositCd : v_DrafterPositCd,
					apperPositNm : v_DrafterPositCd,
					perpsignPositNm : "",
					apperRpswrkCd : v_DrafterRpswrkCd,
					apperRpswrkNm : v_DrafterRpswrkCd,
					apperOrgCd : v_DrafterOrgCd,
					apperOrgNm : v_DrafterOrgNm,
					orgChrcCls : "D"
				});

				/**
				 * 2015 07 03 전결규정 통한 결재선 생성 처리 삭제
				 * 집행팀장이 최종 결재자가 됨 (현장일 경우 현장관리 책임자 포함
				 */

				// 현장소장 협의
				// 현장 담당자 추가되어야함
				if (v_hSignType == "Y" && v_dSignType != "Y") {
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

//			// 임원이라면 전결처리
//			if (v_IsOrgBoss == "Y" || v_IsOfficer == "Y") {
	//
//				ds_Signln.insert(cnt, {
//					signId : "",
//					signSeq : cnt + 1,
//					signTpCd : "T02",
//					signUserId : tripUser, // 세션에서 받아온 값
//					signUserNm : tripUserNm, // 세션에서 받아온 값
//					psignUserNm : "",
//					apperPositCd : tripUserPositCd,
//					apperPositNm : tripUserPositCd,
//					perpsignPositNm : "",
//					apperRpswrkCd : tripUserRpswrkCd,
//					apperRpswrkNm : tripUserRpswrkCd,
//					apperOrgCd : tripUserTeam,
//					apperOrgNm : tripUserTeamNm,
//					orgChrcCls : "D"
//				});
	//
//				v_tSignUserCd = tripUserTeam;
//				v_tSignUserId = tripUser;
	//
//				gf_AssembleSignln(ds_Signln);
//			} else {
	//
//				cnt++;
	//
//				ds_Signln.insert(cnt, {
//					signId : "",
//					signSeq : cnt + 1,
//					signTpCd : "T02",
//					signUserId : v_tSign.userId, // 세션에서 받아온 값
//					signUserNm : v_tSign.userKnm, // 세션에서 받아온 값
//					psignUserNm : "",
//					apperPositCd : v_tSign.userPositCd,
//					apperPositNm : v_tSign.userIdPositNm,
//					perpsignPositNm : "",
//					apperRpswrkCd : v_tSign.userRpswrkCd,
//					apperRpswrkNm : v_tSign.userRpswrkNm,
//					apperOrgCd : v_tSign.orgCd,
//					apperOrgNm : v_tSign.orgNm,
//					orgChrcCls : "D"
//				});
	//
//				gf_AssembleSignln(ds_Signln);
//			}

			$(".ajax_overlay").remove();

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


function f_callBackFuncBdgtSelect(obj){

	v_BdgtType = obj.bdgtType;



	switch(v_BdgtType) {
  		case "A" :
  			$("#bdgtType").text("A. 특정경비-임원");
  		    // 결과 셋팅
  			if(v_Aufnr == obj.bdgtData.Aufnr) return;

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

  			cf_getCouserInfo();

  			break;
  		case "B" :
  			if(v_Aufnr == obj.bdgtData.Aufnr) return;
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

  			cf_getCouserInfo();

  			break;
  		case "C" :
  			if(v_Aufnr == obj.bdgtData.Aufnr) return;
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

  			cf_getCouserInfo();

  			break;
  		case "I" :
  			if(v_Aufnr == obj.bdgtData.Aufnr) return;
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

  			cf_getCouserInfo();

  			break;
  		case "O" :
  			if(v_Aufnr == obj.bdgtData.Aufnr) return;
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

  			cf_getCouserInfo();

  			break;
  		case "P" :
  			if(v_Aufnr == obj.bdgtData.Aufnr) return;
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

  			cf_getCouserInfo();

  			break;
  		case "R" :
  			if(v_Aufnr == obj.bdgtData.Aufnr) return;
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

  			cf_getCouserInfo();

  			break;
  		case "Q" :
  			if(v_Aufnr == obj.bdgtData.Aufnr) return;
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

  			cf_getCouserInfo();

  			break;
  		case "K" :
  			if(v_Aufnr == obj.bdgtData.Aufnr) return;
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

  			cf_getCouserInfo();

  			break;
  		case "S" :
  			if(v_Aufnr == obj.bdgtData.Aufnr) return;
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

  			cf_getCouserInfo();

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

function cf_SavedAppendTranspDetail(obj){

	$("#transpInfo").append(v_TrData);


	// last 에 ID 부여
	var idFlag = "tData";
	var indexF = "" + $("#transpInfo tr:last").index();

	idFlag = idFlag + indexF;
	$("#transpInfo tr:last").attr("id", idFlag);

//	amt: "2823"
//	appantId: "1202429"
//	appantNm: "강혜성"
//	appantTeamCd: "1DFUR"
//	arrPlace: "도착"
//	cls: "R"
//	docNo: "CMAS-2015-000001"
//	dptPlace: "출발"
//	fnlEditDt: "2015-04-23 13:46:09"
//	fnlEditUserId: "1202429"
//	fstRegDt: "2015-04-23 13:46:09"
//	fstRegUser_id: ""
//	glPlace: "목적"
//	seq: "0"
//	trafficCls: "B"
//	useGl: "사적"
//	useYmd: "20150423"

//	var tName = obj.appantNm + " (" + obj.appantId + ")";
	var tName = obj.appantId + " " + obj.appantNm;
	var tOrg = obj.appantTeamCd + " " + obj.appantTeamNm;

	var tTypeTextSel1;

	if(obj.trafficCls == "B"){
		tTypeTextSel1 = "버스";
	}else if(obj.trafficCls == "S"){
		tTypeTextSel1 = "선박";
	}else if(obj.trafficCls == "M"){
		tTypeTextSel1 = "지하철";
	}else if(obj.trafficCls == "T"){
		tTypeTextSel1 = "택시";
	}else if(obj.trafficCls == "1"){
		tTypeTextSel1 = "항공";
	}else if(obj.trafficCls == "2"){
		tTypeTextSel1 = "고속철도";
	}else if(obj.trafficCls == "3"){
		tTypeTextSel1 = "개인차량";
	}else{
		tTypeTextSel1 = "";
	}

	// 왕복 편도
	var tTypeTextSel2;
	if(obj.cls == "R"){
		tTypeTextSel2 = "왕복";
	}else if(obj.cls == "D"){
		tTypeTextSel2 = "편도";
	}else{
		tTypeTextSel2 = "";
	}

	var tUseYmd = obj.useYmd;
//	tUseYmd = $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd",tUseYmd));
	var tType = obj.dptPlace + " - " + obj.arrPlace + " - " + obj.glPlace + " / " + tTypeTextSel1 + " (" + tTypeTextSel2 + ")";
	var tTypeVal = obj.trafficCls;
	var tPurp = obj.useGl;
	var tAmount = obj.amt;

	//현장경비종류
	var tSiteExpKind;
	if(obj.siteExpKind == "1"){
		tSiteExpKind = "시내업무교통비";
	}else if(obj.siteExpKind == "2"){
		tSiteExpKind = "귀가여비";
	}else{
		tSiteExpKind = "";
	}

	var tSiteExpKindVal = obj.siteExpKind;
	var tMvDist = obj.mvDist;

	$("#transpInfo tr:last input[name='tName']").val(tName);
//	$("#transpInfo tr:last input[name='tOrg']").val(tOrg);
	$("#transpInfo tr:last input[name='tUseYmd']").val($.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", tUseYmd)));
	$("#transpInfo tr:last input[name='tType']").val(tType);
	$("#transpInfo tr:last input[name='tTypeVal']").val(tTypeVal);
	$("#transpInfo tr:last input[name='tPurp']").val(tPurp);
	$("#transpInfo tr:last input[name='tAmount']").val(gf_AmtFormat(tAmount));
	$("#transpInfo tr:last input[name='tSiteExpKind']").val(tSiteExpKind);
	$("#transpInfo tr:last input[name='tSiteExpKindVal']").val(tSiteExpKindVal);
	$("#transpInfo tr:last input[name='tMvDist']").val(gf_AmtFormat(tMvDist));

	// readonly 처리
	$("#transpInfo tr input[name='tName']").attr("readonly", "true");
//	$("#transpInfo tr input[name='tOrg']").attr("readonly", "true");
	$("#transpInfo tr input[name='tUseYmd']").attr("readonly", "true");
	$("#transpInfo tr input[name='tType']").attr("readonly", "true");
	$("#transpInfo tr input[name='tPurp']").attr("readonly", "true");
	$("#transpInfo tr input[name='tAmount']").attr("readonly", "true");
	$("#transpInfo tr input[name='tSiteExpKind']").attr("readonly", "true");
	$("#transpInfo tr input[name='tMvDist']").attr("readonly", "true");

	// event rebind
	$("input[name='tName']").unbind("click");
	$("input[name='tName']").click(function(e){

		cf_OpenUserSelectPop(e);

	});

//	// event rebind
//	$("input[name='tOrg']").unbind("click");
//	$("input[name='tOrg']").click(function(e){
//
//		cf_OpenUserSelectPop(e);
//
//	});

	// event rebind
	$("input[name='tUseYmd']").unbind("click");
	$("input[name='tUseYmd']").click(function(e){

		cf_OpenUserSelectPop(e);

	});

	// event rebind
	$("input[name='tType']").unbind("click");
	$("input[name='tType']").click(function(e){

		cf_OpenUserSelectPop(e);

	});

	// event rebind
	$("input[name='tPurp']").unbind("click");
	$("input[name='tPurp']").click(function(e){

		cf_OpenUserSelectPop(e);

	});

	// event rebind
	$("input[name='tAmount']").unbind("click");
	$("input[name='tAmount']").click(function(e){

		cf_OpenUserSelectPop(e);

	});

	// event rebind
	$("input[name='tSiteExpKind']").unbind("click");
	$("input[name='tSiteExpKind']").click(function(e){

		cf_OpenUserSelectPop(e);

	});

	// event rebind
	$("input[name='tMvDist']").unbind("click");
	$("input[name='tMvDist']").click(function(e){

		cf_OpenUserSelectPop(e);

	});

	$("img[name='delDtl']").unbind("click");
	$("img[name='delDtl']").click(function(e){

		if($("#transpInfo tr").size() < 3){
			gf_AlertMsg("더이상 삭제할 수 없습니다");
		}else{
			if(e.target.parentNode.parentNode.id == ""){
				alert('');
			}else{
				if(ds_DetailData.find("targetId", e.target.parentNode.parentNode.id) == -1){

				}else{
					ds_DetailData.remove(ds_DetailData.find("targetId", e.target.parentNode.parentNode.id));
				}

				// 삭제
				$("#" + e.target.parentNode.parentNode.id).remove();
//				ds_DetailData.remove(ds_DetailData.size()-1);
			}



		}

	});

	return obj.amt;


}

function cf_AppendTranspDetail(){



	// last 에 ID 부여
	var idFlag = "tData";
//	var indexF = "" + $("#transpInfo tr:last").index();

	var lastId = $("#transpInfo tr:last").attr("id");
	if(lastId == undefined){
		lastId = "1";
	}else{
		lastId = parseInt($("#transpInfo tr:last").attr("id").substr(5))+1;
	}

	// lastId 를 구한후 삽입
	$("#transpInfo").append(v_TrData);

	idFlag = idFlag + lastId;
	$("#transpInfo tr:last").attr("id", idFlag);

	// readonly 처리
	$("#transpInfo tr input[name='tName']").attr("readonly", "true");
//	$("#transpInfo tr input[name='tOrg']").attr("readonly", "true");
	$("#transpInfo tr input[name='tUseYmd']").attr("readonly", "true");
	$("#transpInfo tr input[name='tType']").attr("readonly", "true");
	$("#transpInfo tr input[name='tPurp']").attr("readonly", "true");
	$("#transpInfo tr input[name='tAmount']").attr("readonly", "true");
	$("#transpInfo tr input[name='tSiteExpKind']").attr("readonly", "true");
	$("#transpInfo tr input[name='tMvDist']").attr("readonly", "true");

	// event rebind
	$("input[name='tName']").unbind("click");
	$("input[name='tName']").click(function(e){

		cf_OpenUserSelectPop(e);

	});

//	// event rebind
//	$("input[name='tOrg']").unbind("click");
//	$("input[name='tOrg']").click(function(e){
//
//		cf_OpenUserSelectPop(e);
//
//	});

	// event rebind
	$("input[name='tUseYmd']").unbind("click");
	$("input[name='tUseYmd']").click(function(e){

		cf_OpenUserSelectPop(e);

	});

	// event rebind
	$("input[name='tType']").unbind("click");
	$("input[name='tType']").click(function(e){

		cf_OpenUserSelectPop(e);

	});

	// event rebind
	$("input[name='tPurp']").unbind("click");
	$("input[name='tPurp']").click(function(e){

		cf_OpenUserSelectPop(e);

	});

	// event rebind
	$("input[name='tAmount']").unbind("click");
	$("input[name='tAmount']").click(function(e){

		cf_OpenUserSelectPop(e);

	});

	// event rebind
	$("input[name='tSiteExpKind']").unbind("click");
	$("input[name='tSiteExpKind']").click(function(e){

		cf_OpenUserSelectPop(e);

	});

	// event rebind
	$("input[name='tMvDist']").unbind("click");
	$("input[name='tMvDist']").click(function(e){

		cf_OpenUserSelectPop(e);

	});

	$("img[name='delDtl']").unbind("click");
	$("img[name='delDtl']").click(function(e){

		if($("#transpInfo tr").size() < 3){
			gf_AlertMsg("더이상 삭제할 수 없습니다");
		}else{
			if(e.target.parentNode.parentNode.id == ""){
				alert('');
			}else{
				if(ds_DetailData.find("targetId", e.target.parentNode.parentNode.id) == -1){

				}else{
					ds_DetailData.remove(ds_DetailData.find("targetId", e.target.parentNode.parentNode.id));
				}

				// 삭제
				$("#" + e.target.parentNode.parentNode.id).remove();
//				ds_DetailData.remove(ds_DetailData.size()-1);
			}



		}

	});

}

function cf_OpenUserSelectPop(e){

	var targetId = $(e.target).parent().parent().attr("id");

//	var tName;
//	var tOrg;
//	var tType;
//	var tPurp;
//	var tAmount;
//	var tUseYmd;
//
//	if(ds_DetailData.find("targetId", targetId) > -1){
//
//		tName = ds_DetailData.get(ds_DetailData.find("targetId", targetId), "appantId") + " " + ds_DetailData.get(ds_DetailData.find("targetId", targetId), "appantNm");
//
//
//		tOrg = ds_DetailData.get(ds_DetailData.find("targetId", targetId), "appantTeamNm") + " (" +ds_DetailData.get(ds_DetailData.find("targetId", targetId), "appantTeamCd") + ")";
//		// 출발 도작 목적 수단 왕복
//
//		tType = ds_DetailData.get(ds_DetailData.find("targetId", targetId), "dptPlace") + " " + ds_DetailData.get(ds_DetailData.find("targetId", targetId), "arrPlace") + " " +
//		ds_DetailData.get(ds_DetailData.find("targetId", targetId), "glPlace") + " " + ds_DetailData.get(ds_DetailData.find("targetId", targetId), "trafficCls") + " " + ds_DetailData.get(ds_DetailData.find("targetId", targetId), "cls");
//
//		tPurp = ds_DetailData.get(ds_DetailData.find("targetId", targetId), "useGl");
//		tAmount = ds_DetailData.get(ds_DetailData.find("targetId", targetId), "amt");
//		tUseYmd = $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", ds_DetailData.get(ds_DetailData.find("targetId", targetId), "useYmd")));
//
////		tName = ds_DetailData.get(ds_DetailData.find("targetId", targetId), "tName");
////		tOrg = ds_DetailData.get(ds_DetailData.find("targetId", targetId), "tOrg");
////		tType = ds_DetailData.get(ds_DetailData.find("targetId", targetId), "tType");
////		tPurp = ds_DetailData.get(ds_DetailData.find("targetId", targetId), "tPurp");
////		tAmount = ds_DetailData.get(ds_DetailData.find("targetId", targetId), "tAmount");
////		tUseYmd = ds_DetailData.get(ds_DetailData.find("targetId", targetId), "tUseYmd");
//
//
//
//
//	}else{
//		tName = "";
//		tOrg = "";
//		tType = "";
//		tPurp = "";
//		tAmount = "";
//		tUseYmd = "";
//	}
//
//	var trParams = {
//		targetId : targetId,
//		tName : tName,
//		tOrg : tOrg,
//		tType : tType,
//		tPurp : tPurp,
//		tAmount : tAmount,
//		tUseYmd : tUseYmd,
//		orgCd : gv_orgCd
//	};

	var trParams;

	// split 때문에 공백이 안써지므로 다시 작성
	if(ds_DetailData.find("targetId", targetId) > -1){

		trParams = ds_DetailData.get(ds_DetailData.find("targetId", targetId));
		var useYmd = ds_DetailData.get(ds_DetailData.find("targetId", targetId), "useYmd");
		trParams.pUseYmd = $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", useYmd));
		trParams.orgCd = gv_orgCd;

	}else{
		 trParams = {
				targetId : targetId,
				orgCd : v_DrafterOrgCd
		 };
	}

	gf_PostOpen("/trip/cityTransp/cityTranspAddP.jsp", null,
	"toolbar=no,scrollbars=no,width=700,height=850", trParams,
	true, true, "f_callBackFuncCityTranspAddP");

}

function f_callBackFuncCityTranspAddP(obj) {

//	  v_BdgtType = obj.bdgtType;

	// 있을경우
	if(ds_DetailData.find("targetId", obj.targetId) != -1){


//		var tempData = {
//		docNo : v_DocNo,// 문서 번호
//		appantId : user[0], // 사용자 ID
//		appantNm : user[1], // 사용자 이름
//		appantTeamCd : org[0], // 사용자조직
//		appantTeamNm : org[1], // 사용자조직
//		useYmd : useYmd, // 사용일
//		dptPlace : tType[0], // 출발지
//		arrPlace : tType[1], // 도작치
//		glPlace : tType[2], // 목적지
//		trafficCls : tType[3], // 교통수단
//		useGl : tPurp, // 사용목적
//		amt : tAmount, // 금액
//		cls : tType[4], // 왕복여부
//		targetId : obj.targetId
//};

		for(var j = 0; j < obj.amt.length; j++){
			obj.amt = obj.amt.replace(",", "");
		}

		obj.useYmd = $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", obj.useYmd));

		ds_DetailData.set(ds_DetailData.find("targetId", obj.targetId), "appantId", obj.appantId);
		ds_DetailData.set(ds_DetailData.find("targetId", obj.targetId), "appantNm", obj.appantNm);
		ds_DetailData.set(ds_DetailData.find("targetId", obj.targetId), "appantTeamCd", obj.appantTeamCd);
		ds_DetailData.set(ds_DetailData.find("targetId", obj.targetId), "appantTeamNm", obj.appantTeamNm);

		ds_DetailData.set(ds_DetailData.find("targetId", obj.targetId), "useYmd", obj.useYmd);
		ds_DetailData.set(ds_DetailData.find("targetId", obj.targetId), "dptPlace", obj.dptPlace);
		ds_DetailData.set(ds_DetailData.find("targetId", obj.targetId), "arrPlace", obj.arrPlace);
		ds_DetailData.set(ds_DetailData.find("targetId", obj.targetId), "glPlace", obj.glPlace);
		ds_DetailData.set(ds_DetailData.find("targetId", obj.targetId), "trafficCls", obj.trafficCls);
		ds_DetailData.set(ds_DetailData.find("targetId", obj.targetId), "useGl", obj.useGl);
		ds_DetailData.set(ds_DetailData.find("targetId", obj.targetId), "amt", obj.amt);
		ds_DetailData.set(ds_DetailData.find("targetId", obj.targetId), "cls", obj.cls);
		ds_DetailData.set(ds_DetailData.find("targetId", obj.targetId), "siteExpKind", obj.siteExpKind);
		ds_DetailData.set(ds_DetailData.find("targetId", obj.targetId), "mvDist", obj.mvDist);


	}else if(ds_DetailData.find("targetId", obj.targetId) == -1){
		// 없음
//		var user = obj.tName.split(" ");
//		var org = obj.tOrg.split(" ");
//		var tUseYmd = obj.tUseYmd.split("-");
//		var useYmd = tUseYmd[0] + tUseYmd[1] + tUseYmd[2];
//		var tType = obj.tType.split(" ");
//		var tAmount = obj.tAmount;
//		for(var j = 0; j < tAmount.length; j++){
//			tAmount = tAmount.replace(",", "");
//		}
//
//		var tPurp = obj.tPurp;
//
//		var tempData = {
//				docNo : v_DocNo,// 문서 번호
//				appantId : user[0], // 사용자 ID
//				appantNm : user[1], // 사용자 이름
//				appantTeamCd : org[0], // 사용자조직
//				appantTeamNm : org[1], // 사용자조직
//				useYmd : useYmd, // 사용일
//				dptPlace : tType[0], // 출발지
//				arrPlace : tType[1], // 도작치
//				glPlace : tType[2], // 목적지
//				trafficCls : tType[3], // 교통수단
//				useGl : tPurp, // 사용목적
//				amt : tAmount, // 금액
//				cls : tType[4], // 왕복여부
//				targetId : obj.targetId
//		};

		for(var j = 0; j < obj.amt.length; j++){
			obj.amt = obj.amt.replace(",", "");
		}

		obj.useYmd = $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", obj.useYmd));


		ds_DetailData.add(obj);
	}

//	var tName = obj.appantNm + " (" + obj.appantId + ")";
	var tName = obj.appantId + " " + obj.appantNm;
//	var tOrg = obj.appantTeamNm + " (" + obj.appantTeamCd;
	var tUseYmd = obj.useYmd;
	var tType = obj.trafficCls;
	var tPurp = obj.useGl;
	var tAmount = obj.amt;

	//현장경비종류
	var tSiteExpKind;
	if(obj.siteExpKind == "1"){
		tSiteExpKind = "시내업무교통비";
	}else if(obj.siteExpKind == "2"){
		tSiteExpKind = "귀가여비";
	}else{
		tSiteExpKind = "";
	}

	var tSiteExpKindVal = obj.siteExpKind;
	var tMvDist = obj.mvDist;

	// 수단
	var tTypeTextSel1;

	if(obj.trafficCls == "B"){
		tTypeTextSel1 = "버스";
	}else if(obj.trafficCls == "S"){
		tTypeTextSel1 = "선박";
	}else if(obj.trafficCls == "M"){
		tTypeTextSel1 = "지하철";
	}else if(obj.trafficCls == "T"){
		tTypeTextSel1 = "택시";
	}else if(obj.trafficCls == "1"){
		tTypeTextSel1 = "항공";
	}else if(obj.trafficCls == "2"){
		tTypeTextSel1 = "고속철도";
	}else if(obj.trafficCls == "3"){
		tTypeTextSel1 = "개인차량";
	}else{
		tTypeTextSel1 = "";
	}

	// 왕복 편도
	var tTypeTextSel2;
	if(obj.cls == "R"){
		tTypeTextSel2 = "왕복";
	}else if(obj.cls == "D"){
		tTypeTextSel2 = "편도";
	}else{
		tTypeTextSel2 = "";
	}


//	targetId : v_targetId,
//	amt : $("#tAmount").val(),
//	appantId : v_TripUserId,
//	appantNm : v_TripUserNm,
//	appantTeamCd : v_TripOrgCd,
//	appantTeamNm : v_TripOrgNm,
//	arrPlace : $("#t2").val(),
//	cls : $("#tRound").val(),
//	docNo : v_DocNo,
//	glPlace : $("#t3").val(),
//	dptPlace : $("#t1").val(),
//	trafficCls : $("#tType").val(),
//	useGl : $("#tPurp").val(),
//	useYmd : $("input[name='useDate']").val()

	$("#" + obj.targetId + " input[name='tName']").val(tName);
//	$("#" + obj.targetId + " input[name='tOrg']").val(tOrg);
	$("#" + obj.targetId + " input[name='tUseYmd']").val($.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", tUseYmd)));
	$("#" + obj.targetId + " input[name='tType']").val(
			obj.dptPlace + " - " + obj.arrPlace + " - " + obj.glPlace + " / " + tTypeTextSel1 + " (" + tTypeTextSel2 + ")"
	);
	$("#" + obj.targetId + " input[name='tTypeVal']").val(tType);
	$("#" + obj.targetId + " input[name='tPurp']").val(tPurp);
	$("#" + obj.targetId + " input[name='tAmount']").val(gf_AmtFormat(tAmount));

	$("#" + obj.targetId + " input[name='tSiteExpKind']").val(tSiteExpKind);
	$("#" + obj.targetId + " input[name='tSiteExpKindVal']").val(tSiteExpKindVal);
	$("#" + obj.targetId + " input[name='tMvDist']").val(gf_AmtFormat(tMvDist));

	var tAmount = $("input[name='tAmount']");
	var total = 0;
	for(var i = 0; i < tAmount.size(); i++){
		var tVal = tAmount[i].value;
		if(tVal == "") continue;
		for(var j = 0; j < tVal.length; j++){
			tVal = tVal.replace(",", "");
		}
		total = total + parseInt(tVal);
	}

	v_TotalAmount = total;
	$("#tAmountTotal").text(gf_AmtFormat(total));


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
 */
function cf_GetTrafficCost(){

	// SAP 상신 전에 Validation Check 실시할 것


	// Parameter Init
	var IBukrs = "1000"; // 회사코드 1000 상수

	// 문서번호
	var docNo = v_DocNo;
	// signId SGNS 에서 차후 업데이트
	var signId = "";
	// signStsCd 결재상태
	var signStsCd = "D02";
	// 출장자 조직코드
	var doOrgCd = gv_orgCd;
	// 결비구분
	var expCls = v_BdgtType;
	// 예산내역
	var bdgtItem = v_KText;
	// 회계반려 여부
	var acctSts = "";
	// 회계 반려 사유
	var retResn = "";

	/************************
	 * SGNS REMOTE DRAFT PARAMS INIT
	 ************************/

	var tSignUserCd = v_tSignUserCd; // 최종결재 ORG
	var tSignUserId = v_tSignUserId; // 최종결재 ID

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

	var dutySysCd = "SGNS"; // DUTYSYSCD
	var programCode = "SGNS070001"; // 양식코드
	var signDocTitle = "시내교통비"; // 양식 제목

	var drafterId = gv_userId + " " + gv_userNm; // 작성자 ID
	var drafterOrg = gv_orgCd + " " + gv_orgNm.replace(/&/gi,"＆"); // 작성자 팀명에 특수문자 변환(&)

	//vReceiveMan = vReceiveMan.replace(/&/gi,"＆");

	var bdgtType = $("#bdgtType").text(); // 경비구분
	var aufnrNo = v_Aufnr; // 예산번호
	var docNo = v_DocNo; // CMAS 문서번호
	var ordDate = ""; // 기표일자
	var ordNo = ""; // 전표번호

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

	var draftParams = {
			IBukrs : IBukrs,
			docNo : docNo,
			signId : signId,
			docSts : signStsCd,
			doOrgCd : doOrgCd,
			expCls : expCls,
			bdgtItem : bdgtItem,
			acctSts : acctSts,
			retResn : retResn,
			//sgns param
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
			drafterId : drafterId,
			drafterOrg : drafterOrg,
			bdgtType : bdgtType,
			aufnrNo : aufnrNo,
			ordDate : ordDate,
			ordNo : ordNo,
			bdgtCd : v_BdgtType,
			isOrgBoss : v_IsOrgBoss,
			isOfficer : "N",
			drUser : v_DrafterId,
			drUserCd : v_DrafterOrgCd,
			isSignEdit : v_isSignEdit,
			fileAtchId	: gf_IsNull(v_FileAtchId) ? "": v_FileAtchId
	};

	var ds_TUserList = new DataSet();

	// 각각 출장자 별로 Array
	for(var i = 0; i < ds_DetailData.size(); i++){

		// inner SapDoctab
		var Bukrs = "1000"; // 회사코드
		var Refkey = ""; // SAP 에서 생성 후 Return
		var Bdocno = v_DocNo; // CMAS 문서번호
		var Seqno = i+1; // 순번
		//console.log("Seqno === " + Seqno);
		var Kostl1 = v_Kostv; // 집행팀코드
		var Kostxt = ""; // 집행팀 : 최초NULL
		var Sabun1 = v_DrafterId; // 작성자 최초작성자 유지

		var Date1;
		var maxDate = cf_MaxDateInDetail(); // 다수 증빙일 경우 사용일이 가장 높은 날짜
		if(maxDate == ""){
			gf_AlertMsg("증빙일이 올바르지 않습니다.");

			v_isSaveEnable = "N";
			$(".btn").show();

			$(".ajax_overlay").remove();

			return;
		}else{
			Date1 = maxDate;
		}


		var Sabun2 = ds_DetailData.get(i).appantId; // 신청자 사번
		var Lfatxt = ""; // NULL
		var Kostl2 = ds_DetailData.get(i).appantTeamCd; // 신청팀코드
		var Bankno = ""; // 은행계좌 NULL
		var Paydate = ""; // 레코드 생성일 NULL
		var Waers = "KRW"; // 통화 키

		var amt = ds_DetailData.get(i).amt;
		for(var j = 0; j < amt.length; j++){
			amt = amt.replace(",", "");
		}
		var Amount = amt; // 사용금액

		// (교통수단) 출발 도착 목적
		var trafficCls = ds_DetailData.get(i, "trafficCls");

		// 교통수단
		if(trafficCls == "B"){
			trafficCls = "버스";
		}else if(trafficCls == "S"){
			trafficCls = "선박";
		}else if(trafficCls == "M"){
			trafficCls = "지하철";
		}else if(trafficCls == "T"){
			trafficCls = "택시";
		}else if(trafficCls == "1"){
			trafficCls = "항공";
		}else if(trafficCls == "2"){
			trafficCls = "고속철도";
		}else if(trafficCls == "3"){
			trafficCls = "개인차량";
		}else{
			trafficCls = "";
		}
		// (교통수단) 출발 도착 목적


		var Area = "(" + trafficCls + ") " + ds_DetailData.get(i, "dptPlace") + " " + ds_DetailData.get(i, "arrPlace") + " " + ds_DetailData.get(i, "glPlace"); // 지역

		// 20201202 사용목적 앞에 현장경비종류에 따른 문구 추가 ( 1시내업무교통비 -> (시내), 2귀가여비 -> (귀가) )    (시작)
		var v_siteExpKind = ds_DetailData.get(i).siteExpKind;
		var v_addSiteExpKind = "";

		if(v_siteExpKind == "1"){
			v_addSiteExpKind = "(시내)";
		}else if(v_siteExpKind == "2"){
			v_addSiteExpKind = "(귀가)";
		}
		var Descript = ds_DetailData.get(i).useGl; // 사용목적
		Descript = v_addSiteExpKind + Descript;
		// 20201202 사용목적 앞에 현장경비종류에 따른 문구 추가 ( 1시내업무교통비 -> [시내], 2귀가여비 -> [귀가] )    (종료)


		var Chkflag = ""; // 예산반영 NULL
		var Sdate = ""; // 레코드 생성일 NULL
		var Sdflag = ""; // 예산반영 NULL
		var BdgtType = v_BdgtType; // 예산구분 경비구분

		if($("input[name='docType']:checked").val() == undefined){
			gf_AlertMsg("본사 / 지역 여부가 선택되지 않았습니다.");
			v_isSaveEnable = "N";
			$(".btn").show();
			$(".ajax_overlay").remove();
			return;
		}
		var DocType = $("input[name='docType']:checked").val(); // 전표구분 H G

		var Aufnr = v_Aufnr; // 예산번호
		if(v_BdgtType == "C" || v_BdgtType == "Q") Aufnr = "";


		var Posid = ""; // R 예산번호, Q 원가실귀속코드
		if(v_BdgtType == "R"){
			Posid = v_Aufnr;
		}else if(v_BdgtType == "Q"){
			Posid = v_Kostv;
		}

		var Kblnr = ""; // 특정 사용 자금의 전표 번호 NULL
		var Kblpos = ""; // 특정 사용자금 전표 항목 NULL


		var Usedt = ds_DetailData.get(i, "useYmd"); // 업무사용일
		var Rfund = ""; // 자금

		var tripUser = {
				Bukrs : Bukrs,
				Refkey : Refkey,
				Bdocno : Bdocno,
				Seqno : Seqno,
				Kostl1 : Kostl1,
				Kostxt : Kostxt,
				Sabun1 : Sabun1,
				Date1 : Date1,
				Sabun2 : Sabun2,
				Lfatxt : Lfatxt,
				Kostl2 : Kostl2,
				Bankno : Bankno,
				Paydate : Paydate,
				Waers : Waers,
				Amount : Amount,
				Area : Area,
				Descript : Descript,
				Chkflag : Chkflag,
				Sdate : Sdate,
				Sdflag : Sdflag,
				BdgtType : BdgtType,
				DocType : DocType,
				Aufnr : Aufnr,
				Posid : Posid,
				Kblnr : Kblnr,
				Kblpos : Kblpos,
				Usedt : Usedt,
				Rfund : Rfund
		};

		ds_TUserList.add(tripUser);

	}

	var ds_DraftDtl = new DataSet();

	for(var i = 0; i < ds_DetailData.size();i++){

		ds_DraftDtl.add(ds_DetailData.get(i));
	}

	var draftDataSet = {
			ds_TUserList : ds_TUserList.getAllData("U"),
			ds_DraftDtl : ds_DraftDtl.getAllData("U"),
			ds_Signln : ds_Signln.getAllData("A")
	};

	gf_Transaction("SELECT_CITY_TRANSP_DRAFT", "/trip/eai/getSendTrafficCost.xpl", draftParams, draftDataSet, "f_Callback", true);

}

function cf_SaveCityTranspDraft(obj){

//	doc_no,
//	ref_no,
//	sign_id,
//	sign_sts_cd,
//	fst_reg_dt,
//	fst_reg_user_id,
//	fnl_edit_dt,
//	fnl_edit_user_id,
//	do_org_cd,
//	exp_cls,
//	bdgt_item,
//	acct_sts,
//	ret_resn

	v_SapDoc = obj;

	// 문서번호
	var docNo = v_DocNo;
	// refNo SAP 통신 후 상신되어 온 값
	v_RefNo = obj.Refkey;

	// Result 가 Object 인지 검사
	// 출장자가 2이상일 경우는 배열로 온다.
	if(obj.constructor == Array) v_RefNo = obj[0].Refkey;
	// signId SGNS 에서 차후 업데이트
	var signId = "";
	// signStsCd 결재상태
	var signStsCd = "D02";
	// 출장자 조직코드
	var doOrgCd = gv_orgCd;
	// 결비구분
	var expCls = v_BdgtType;
	// 예산내역
	var bdgtItem = v_KText;
	// 회계반려 여부
	var acctSts = "";
	// 회계 반려 사유
	var retResn = "";

	var ds_DraftDtl = new DataSet();

	for(var i = 0; i < ds_DetailData.size();i++){

		ds_DraftDtl.add(ds_DetailData.get(i));
	}

	var draftParams = {
			docNo : docNo,
			refNo : v_RefNo,
			signId : signId,
			docSts : signStsCd,
			doOrgCd : doOrgCd,
			expCls : expCls,
			bdgtItem : bdgtItem,
			acctSts : acctSts,
			retResn : retResn
	};

	var draftDataSet = {
			ds_DraftDtl : ds_DraftDtl.getAllData("U")
	};

	gf_Transaction("SELECT_CITY_TRANSP_DRAFT", "/trip/cityTransp/updateCityTranspDraftDoc.xpl", draftParams, draftDataSet, "f_Callback", true);
}

/**
 * detail Data 에서 가장 높은 증빙일을 찾아 리턴한다.
 * data가 없으면 ""
 * @returns
 */
function cf_MaxDateInDetail(){
	var result;
	if(ds_DetailData.size() == 0){
		result = "";
	}else if(ds_DetailData.size() == 1){
		result = ds_DetailData.get(0, "useYmd");
	}else{
		// 높은 날짜를 구함
		result = ds_DetailData.get(0, "useYmd");
		for(var i = 1; i < ds_DetailData.size(); i++){
			var chk = $.datepicker.parseDate("yymmdd", result) - $.datepicker.parseDate("yymmdd", ds_DetailData.get(i, "useYmd"));
			if(chk < 0) result = ds_DetailData.get(i, "useYmd");
		}
	}
	return result;
}

function cf_TranspSgnsRemoteDraft(){

}

function cf_setSignlnInfo(resultData) {
	ds_Signln.clear();

	var cnt = 0;

	//	var tripUser; // 출장자
	//	var tripUserNm;
	//	var tripUserTeam; // 출장자팀
	//	var tripUserTeamNm;
	//	var tripUserPositCd;
	//	var tripUserRpswrkCd;

	if(v_IsOrgBoss == "Y"){
		ds_Signln.insert(cnt, {
			signId : "",
			signSeq : cnt + 1,
			signTpCd : "T01",
			signUserId : v_DrafterId, // 세션에서 받아온 값
			signUserNm : v_DrafterNm, // 세션에서 받아온 값
			psignUserNm : "",
			apperPositCd : v_DrafterPositCd,
			apperPositNm : v_DrafterPositCd,
			perpsignPositNm : "",
			apperRpswrkCd : v_DrafterRpswrkCd,
			apperRpswrkNm : v_DrafterRpswrkCd,
			apperOrgCd : v_DrafterOrgCd,
			apperOrgNm : v_DrafterOrgNm,
			orgChrcCls : "D"
		});

	}else{
		ds_Signln.insert(cnt, {
			signId : "",
			signSeq : cnt + 1,
			signTpCd : "T01",
			signUserId : v_DrafterId, // 세션에서 받아온 값
			signUserNm : v_DrafterNm, // 세션에서 받아온 값
			psignUserNm : "",
			apperPositCd : v_DrafterPositCd,
			apperPositNm : v_DrafterPositCd,
			perpsignPositNm : "",
			apperRpswrkCd : v_DrafterRpswrkCd,
			apperRpswrkNm : v_DrafterRpswrkCd,
			apperOrgCd : v_DrafterOrgCd,
			apperOrgNm : v_DrafterOrgNm,
			orgChrcCls : "D"
		});

		/**
		 * 2015 07 03 전결규정 통한 결재선 생성 처리 삭제
		 * 집행팀장이 최종 결재자가 됨 (현장일 경우 현장관리 책임자 포함
		 */

		// 현장소장 협의
		// 현장 담당자 추가되어야함
		if (v_hSignType == "Y" && v_dSignType != "Y") {
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
	}



	// 팀/소장 전결 추가
	if (v_IsOrgBoss == "Y") {

		// 2016-05-16 Javascript 이상 증상?
		// 디버그 상에서 cnt 가 업데이트 되지 않는 버그
		// 전결의 마지막 추가과정이므로 변수를 바꾸어서 처리

		cnt_temp = cnt;
		cnt_temp = cnt_temp + 1;

		ds_Signln.insert(cnt_temp, {
			signId : "",
			signSeq : cnt_temp + 1,
			signTpCd : "T02",
			signUserId : v_DrafterId, // 세션에서 받아온 값
			signUserNm : v_DrafterNm, // 세션에서 받아온 값
			psignUserNm : "",
			apperPositCd : v_DrafterRpswrkCd,
			apperPositNm : v_DrafterRpswrkCd,
			perpsignPositNm : "",
			apperRpswrkCd : v_DrafterRpswrkCd,
			apperRpswrkNm : v_DrafterRpswrkCd,
			apperOrgCd : v_DrafterOrgCd,
			apperOrgNm : v_DrafterOrgNm,
			orgChrcCls : "D"
		});

		v_tSignUserCd = v_DrafterOrgCd;
		v_tSignUserId = v_DrafterId;

		gf_AssembleSignln(ds_Signln);
	}else{


		if (v_dSignType == "Y" && v_Chief != v_tSignUserId) {

			if(v_BdgtType == "Q"){

				// 타집행팀에 현장일 경우 현장관리책임자 협의가 추가된다.

				if(v_dHSignUserId != ""){
					cnt++;

					if(v_dHSignRpswrkCd == ""){
						ds_Signln.insert(cnt, {
							signId : "",
							signSeq : cnt + 1,
							signTpCd : "T02",
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
					}else{
						ds_Signln.insert(cnt, {
							signId : "",
							signSeq : cnt + 1,
							signTpCd : "T02",
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

				v_tSignUserCd = v_dHSignUserCd;
				v_tSignUserId = v_dHSignUserId;

			}


			if(v_dSignUserId != ""){

				cnt++;

				if(v_dSignRpswrkCd == ""){
					ds_Signln.insert(cnt, {
						signId : "",
						signSeq : cnt + 1,
						signTpCd : "T02",
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
						signTpCd : "T02",
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

				v_tSignUserCd = v_dSignUserCd;
				v_tSignUserId = v_dSignUserId;
			}
		}else{


			/**
			 * 2015 07 03 전결규정 통한 결재선 생성 처리 삭제
			 * 집행팀장이 최종 결재자가 됨 (현장일 경우 현장관리 책임자 포함
			 */
			if (resultData.ds_SignlnForExcluRegl == null) {
				gf_AlertMsg("최종 결재자가 지정되지 않았습니다. 결재자를 선택해주세요");
				// 결재선을 지정할 수 있도록 버튼을 팝업한다.
				$("#cityTranspSign").show();
			} else {
//				$("#cityTranspSign").hide();
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

		}


		gf_AssembleSignln(ds_Signln);

	}

	$(".ajax_overlay").remove();

	v_isSignEdit = "N";
}

function f_callBackFuncOrgSelect(obj){

	v_DrafterOrgCd = obj.orgCd;
	v_DrafterOrgNm = obj.orgNm;

	var drafterOrgNmText = v_DrafterOrgNm + "("+v_DrafterOrgCd+")";
	$("#drafterOrgNm").text(drafterOrgNmText);

	// 정보 삭제
	var tf = $("#transpInfo tr").size()-1;
	for(var i = 0; i < tf; i++){
		$("#transpInfo tr:last").remove();
	}
	ds_DetailData.clear();

	$("#addBtn").trigger("click");

	// 예산번호 집행팀 경비구분
	v_BdgtType = "";
	$("#bdgtType").text("");

	v_Aufnr = ""; // 예산
	v_KText = ""; // 예산내역
	$("#Aufnr").text("");

	v_Kostv = ""; // 집행팀
	v_Kostvnm = ""; // 집행팀 이름
	v_Chief = ""; // 집행팀장ID
	v_Chiefnm = ""; // 집행팀장 이름
	$("#bdgtTeam").text("");

	// 팀장 이상 여부 초기화
	v_IsOrgBoss = "N";

	var isParam = {
			userId : v_DrafterId,
			orgCd : v_DrafterOrgCd,
			drafterId : v_DrafterId,
			drafterOrgCd : v_DrafterOrgCd
	};

	gf_Transaction("SELECT_IS_SPOT_MGMT", "/trip/innerTrip/retrieveIsSpotMgmt.xpl", isParam, {}, "f_Callback", true);



}

function f_NotExistOrgBossSelect(obj){
	// 임원이 아닐 경우
	v_tSignUserCd = obj.orgCd; // 최종 결재자 ORG
	v_tSignUserId = obj.userId; // 최종결재자 ID
}

function f_SelectSignInfo(obj){

	v_hSignType = "N";
	v_dSignType = "N";

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

//시내교통비/국내출장/해외출장 중복 신청 방지 체크
function f_CheckDraftDuplication(){

	var v_userId = '';
	var v_stYmd = '';
	var v_edYmd = '';

	for(var i = 0; i < ds_DetailData.size();i++){
		v_userId = ds_DetailData.get(i, "appantId");
		v_stYmd = ds_DetailData.get(i, "useYmd");
		v_edYmd = ds_DetailData.get(i, "useYmd");

		var params = {
				userId : v_userId,
				stYmd : v_stYmd,
				edYmd : v_edYmd
		};

		gf_Transaction("CHECK_DRAFT_DUPLICATION", "/trip/cityTransp/retrieveCheckDraftDuplication.xpl", params, {}, "f_Callback", false);
	}
}

//분기작업
//저장,상신 (첨부파일로 인한 저장/상신 분기작업)
function fn_SetUploadCallback( fileAtchId ) {

	v_FileAtchId = fileAtchId;


	if(v_CanSaveDraft == "1"){
		f_CityTranspSave();	//저장
	}else if(v_CanSaveDraft == "2"){
		f_CityTranspDraft();	//상신
	}

}

//저장저장저장
//저장 (파일첨부 처리후 저장작업 진행)
function f_CityTranspSave() {

	// 클릭 못하도록 버튼 숨김
	$(".btn").hide();


	// 상신시 Modal처리
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
			drafterId : v_DrafterId,
			drafterNm : v_DrafterNm,
			drafterOrgCd : v_DrafterOrgCd,
			drafterOrgNm : v_DrafterOrgNm,
			drafterPositCd : v_DrafterPositCd,
			drafterRpswrkCd : v_DrafterRpswrkCd,
			docType : docType,
			signln : JSON.stringify(ds_Signln.getAllData()), // 20150721 전결라인 저장
			v_dSignType : v_dSignType, // 타 집행팀 여부
			v_dSignUserCd : v_dSignUserCd,
			v_dSignUserId : v_dSignUserId,
			v_dSignUserNm : v_dSignUserNm,
			v_dSignOrgNm : v_dSignOrgNm,
			v_dSignRpwrkNm : v_dSignRpwrkNm,
			v_dSignRpswrkCd : v_dSignRpswrkCd,
			v_dSignPositCd : v_dSignPositCd,
			v_dSignPositNm : v_dSignPositNm,
			v_dHSignUserCd : v_dHSignUserCd,
			v_dHSignUserId : v_dHSignUserId,
			v_dHSignUserNm : v_dHSignUserNm,
			v_dHSignOrgNm : v_dHSignOrgNm,
			v_dHSignRpwrkNm : v_dHSignRpwrkNm,
			v_dHSignRpswrkCd : v_dHSignRpswrkCd,
			v_dHSignPositCd : v_dHSignPositCd,
			v_dHSignPositNm : v_dHSignPositNm,
			v_IsOrgBoss : v_IsOrgBoss,
			v_IsOfficer : v_IsOfficer
	};


	var ds_DraftDtl = new DataSet();

	for(var i = 0; i < ds_DetailData.size();i++){

		ds_DraftDtl.add(ds_DetailData.get(i));
	}

	var draftDataSet = {
			ds_DraftDtl : ds_DraftDtl.getAllData("U")
	};

	var param = {
			docNo : v_DocNo,
			docSts : docSts,
			ifParam : JSON.stringify(data),
			fileAtchId	: gf_IsNull(v_FileAtchId) ? "": v_FileAtchId,
	};

//	if(v_DocSts == "D16"){
//		gf_Transaction("SAVE_CITY_TRANSP_DOC_SAVE", "/trip/cityTransp/updateCityTranspDocSave.xpl", param, draftDataSet, "f_Callback", true);
//	}else{
//		gf_Transaction("SAVE_CITY_TRANSP_DOC_SAVE", "/trip/cityTransp/saveCityTranspDocSave.xpl", param, draftDataSet, "f_Callback", true);
//	}

	gf_Transaction("SAVE_CITY_TRANSP_DOC_SAVE", "/trip/cityTransp/updateCityTranspDocSave.xpl", param, draftDataSet, "f_Callback", true);


}

//상신상신상신
//상신 (첨부파일 작업후 상신진행)
function f_CityTranspDraft() {

	// 첨부파일 체크(시작)

	if(v_BdgtType == "Q"){	//Q현장경비
		var tSiteExpKind = $("input[name='tSiteExpKindVal']");	//현장경비종류
		var tMvDist = $("input[name='tMvDist']");	//이동거리
		var tType = $("input[name='tTypeVal']");	//교통수단
		var tAmount = $("input[name='tAmount']");	//금액
		var tName = $("input[name='tName']");		//신청자
		var tSiteExpKindVal = "";
		var tMvDistVal = "";
		var tAmountVal = "0";
		var tTypeVal = "";
		var tNameVal = "";
		var valCheck1 = "Y";	//(조건1)2만원 한도 (현장경비예산 & 현장경비종류[시내업무교통비])
		var valCheck2 = "Y";	//(조건2)5천원 한도 (현장경비예산 & 현장경비종류[시내업무교통비] & 첨부파일 미첨부)
		var valCheck3 = "Y";	//(조건3)귀가여비 미첨부시 상신불가 (현장경비예산 & 현장경비종류[귀가여비] & 첨부파일 미첨부)
		var valCheck4 = "Y";	//(조건4)개인차량 200원/km 한도 (현장경비예산 & 현장경비종류[귀가여비])
		var valCheck4_limit = "";	//조건4에 대한 한도금액 안내용
		var valCheckName = "";	//신청자(메시지 표시용)

		for(var i = 0; i < tMvDist.size(); i++){
			tSiteExpKindVal = tSiteExpKind[i].value;
			tMvDistVal = tMvDist[i].value;
			tAmountVal = tAmount[i].value;
			tTypeVal = tType[i].value;
			tNameVal = tName[i].value;

//alert("tSiteExpKindVal:"+tSiteExpKindVal);
//alert("tTypeVal:"+tTypeVal);

			if(tSiteExpKindVal == "1" && parseInt(removeComma(tAmountVal)) > "20000"){	//현장경비종류[시내업무교통비]
				valCheck1 = "N";
				valCheckName = tNameVal;
			}
//alert("tSiteExpKindVal:"+tSiteExpKindVal);
//alert("2removeComma(tAmountVal):"+removeComma(tAmountVal));
			if(tSiteExpKindVal == "1" && parseInt(removeComma(tAmountVal)) > "5000"){	//현장경비종류[시내업무교통비]
				valCheck2 = "N";
				valCheckName = tNameVal;
			}
			if(tSiteExpKindVal == "2"){	//현장경비종류[귀가여비]
				valCheck3 = "N";
				valCheckName = tNameVal;
			}
			if(tSiteExpKindVal == "2" && tTypeVal == "3"){	//현장경비종류[귀가여비] & 교통수단[개인차량]
				var int_tAmountVal = parseInt(removeComma(tAmountVal));
				var int_tMvDistVal200 = parseInt(removeComma(tMvDistVal)) * 200;
//alert("int_tAmountVal:"+int_tAmountVal);
//alert("int_tMvDistVal200:"+int_tMvDistVal200);
				if (int_tAmountVal > int_tMvDistVal200){
					valCheck4_limit = int_tMvDistVal200;
					valCheck4 = "N";
					valCheckName = tNameVal;
				}
			}
		}

		//2만원 한도 (현장경비예산 & 현장경비종류[시내업무교통비])
		if(valCheck1 == "N"){
			gf_AlertMsg(valCheckName+"\n\n"+"현장경비예산 선택시, 현장경비종류가 [시내업무교통비]이면 2만원을 초과할 수 없습니다\n\n"+valNoticeName);
			return;
		}

		//5천원 한도 (현장경비예산 & 현장경비종류[시내업무교통비] & 첨부파일 미첨부)
		if(valCheck2 == "N"){

			f_CheckCoFileYn();
			//v_CoFileYn = "Y"	//나중에 막으세요
			var v_FileMsg2 = valCheckName+"\n\n"+"금액이 한도 5천원을 초과할 수 없습니다 아래(경우2)에 해당\n\n";
			v_FileMsg2 += "(경우1) (현장경비예산) AND (현장경비종류[시내업무교통비]) AND (첨부파일 첨부) = 한도 2만원\n\n";
			v_FileMsg2 += "(경우2) (현장경비예산) AND (현장경비종류[시내업무교통비]) AND (첨부파일 미첨부) = 한도 5천원\n\n";
			v_FileMsg2 += valNoticeName;
				if (v_CoFileYn != "Y") {
					 gf_AlertMsg(v_FileMsg2);
					 return;
				}

		}

		//귀가여비 미첨부시 상신불가 (현장경비예산 & 현장경비종류[귀가여비] & 첨부파일 미첨부)
		if(valCheck3 == "N"){

			f_CheckCoFileYn();
			//v_CoFileYn = "Y"	//나중에 막으세요
			var v_FileMsg3 = valCheckName+"\n\n"+"현장경비예산 선택시, 현장경비종류가 [귀가여비]이면 영수증 첨부는 필수입니다 \n\n"+valNoticeName;
				if (v_CoFileYn != "Y") {
					 gf_AlertMsg(v_FileMsg3);
					 return;
				}

		}

		//(조건4)개인차량 200원/km 한도 (현장경비예산 & 현장경비종류[귀가여비])
		if(valCheck4 == "N"){
			gf_AlertMsg(valCheckName+"\n\n"+ "현장경비예산 선택시, 현장경비종류가 [귀가여비]이면" + "\n\n 개인차량의 경우 [금액]이 한도를 초과 할 수 없습니다  \n\n ( 개인차량 한도: "+valCheck4_limit+"원 ) \n\n (개인차량 한도 공식: 이동거리 x 200원)\n\n"+valNoticeName);
			return;
		}

	}

//alert("현장경비 체크완료");
//return;

	// 첨부파일 체크(종료)






/*
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
*/



	// 본사 시내교통비 3만원 초과 필수 증빙첨부 메세지 추가 (시작)
	if(v_BdgtType != "Q"){	// 본사경비 (Q현장경비 이외)
		var tMvDist = $("input[name='tMvDist']");	//이동거리
		var tAmount = $("input[name='tAmount']");	//금액
		var tName = $("input[name='tName']");		//신청자
		var tAmountVal = "0";
		var tNameVal = "";
		var valCheckName = "";	//신청자(메시지 표시용)

		for(var i = 0; i < tMvDist.size(); i++){
			tAmountVal = tAmount[i].value;
			tNameVal = tName[i].value;

			if(parseInt(removeComma(tAmountVal)) > "30000"){	// 3만원 초과
				valCheck1 = "N";
				valCheckName = tNameVal;
			}

		}

		if(valCheck1 == "N"){
			f_CheckCoFileYn();
			var v_FileMsgHQ = valCheckName+"\n\n"+" 본사경비 예산 선택시, \n 3만원을 초과하면 영수증 첨부는 필수입니다\n\n"+valNoticeHQName;
			if (v_CoFileYn != "Y") {
				 gf_AlertMsg(v_FileMsgHQ);
				 return;
			}
		}

	}
	// 본사 시내교통비 3만원 초과 필수 증빙첨부 메세지 추가 (종료)


	if(v_isSaveEnable == "N"){

		//버튼 숨김
		$(".btn").hide();
		// 로딩 Modal
		gf_InitSpinner(top.$('body'));

		// 중복 상신 불가
		v_isSaveEnable = "Y";

		//SAP 상신
		cf_GetTrafficCost();

	}else{
		gf_AlertMsg("문서 상신중입니다. 잠시만 기다려주세요.");
		return;
	}

}

//첨부파일 DB에 등록여부 체크
function f_CheckCoFileYn(){

	var fileAtchId = gf_IsNull(v_FileAtchId) ? ""          : v_FileAtchId;

	var params = {
			fileAtchId : fileAtchId
	};

	gf_Transaction("CHECK_CO_FILE_YN", "/trip/innerTrip/retrieveCheckCoFileYn.xpl", params, {}, "f_Callback", false);

}

//콤마,원 제거
function removeComma(obj){
	for(var i = 0; i < obj.length; i++){
		obj = obj.replace(",", "");
	}
	for(var i = 0; i < obj.length; i++){
		obj = obj.replace("원", "");
	}
	return obj;
}