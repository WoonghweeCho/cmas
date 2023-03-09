
/*
 * 01. 업무구분 			: 공통
 * 02. 스크립트 설명  	: 공통 사용자 선택 팝업을 위한 자바 스크립트
 * 03. 작성자 			: 변형구
 * 04. 작성일 			: 2013.04.12
 */

/**************************************************************
 * 전역 변수 영역
 **************************************************************/
// 조직 트리용 Dataset
var ds_OrgList		= new DataSet();
// 사원목록 용 Dataset
var ds_UserList 	= new DataSet();
// 선택된 사용자 목록 용 Dataset
var ds_SelUserList 	= new DataSet();
// 이벤트처리를 위한 전역 orgcd 변수
var v_SelectedOrgCd;
//  이벤트 처리를 위한 전역 userID 변수
var v_SelectUser;
//  팝업이 닫힐때 리턴값을 전달할 callback 함수
var v_CallbackFunc;
// 팝업의 레벨
var v_FstLvlPop = true;
// modal pop 여부
var v_IsModal = false;

// zTree 를 위한 설정
var setting = {
		view: {
			dblClickExpand: false,
			showLine: true,
			selectedMulti: false
		},
		data: {
			simpleData: {
				enable:true,
				idKey: "orgCd",
				pIdKey: "hgrOrgCd",
				rootPId: ""
			},
			key: {
				name: "orgNm"
			}
		},
		callback: {
			// tree click 이벤트
			onClick: function(evt, treeId, treeNode, clickFlag) {
				v_SelectedOrgCd = treeNode.orgCd;
				f_RetrieveSignUserList(treeNode.orgCd);
			}
		}
	};


/**************************************************************
 * 화면별 필수정의 함수 영역
 **************************************************************/
/**
* @class 화면 로드 완료 시 필요한 초기 작업 수행.
*        1. 파라미터 초기화
*        2. 컴포넌트 초기화
*        3. Event Listener 초기화
*        4. 화면내 Form 객체 초기화
*        5. 다국어 처리
* @author 변형구
* @since 2013-04-12
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
* @author 변형구
* @since 2013-04-12
* @version 1.0
*/
function cf_InitParam()
{
	v_CallbackFunc = gf_IsNull(datas.callbackFunc) ? ""          : datas.callbackFunc;
	v_FstLvlPop    = gf_IsNull(datas.fstLvlPop)    ? v_FstLvlPop : eval(datas.fstLvlPop);
	v_IsModal      = gf_IsNull(datas.isModal)      ? v_IsModal   : eval(datas.isModal);
	// 부서 트리 목록을 조회한다.fstLvlPop isModal callBackFunc
	f_RetrieveOrgList();
}

/**
* @class Form Onload 시 컴포넌트 초기화
* @author 변형구
* @since 2013-04-12
* @version 1.0
*/
function cf_SetComponents()
{


	// 선택된 부서 목록 그리드

	$("#grdUserLst").jqGrid({
		datatype: "local",
	   	colNames:[gf_FindLang('이름'),gf_FindLang('직위'),gf_FindLang('직책/직무'),gf_FindLang('부서/현장')],
	   	colModel:[
	  	   		{name:'signUserNm',index:'signUserNm', width:70, align:'center'},
		   		{name:'apperPositNm',index:'apperPositNm', width:65, align:'center'},
		   		{name:'apperRpswrkNm',index:'apperRpswrkNm', width:65, align:'center'},
		   		{name:'apperOrgNm',index:'apperOrgNm', width:200, align:'left'}
	   	],
	   	width:433,
	   	height:110,
	   	sortname: 'orgNm',
	    viewrecords: true,
	    shrinkToFit : true,	// 그리드 크기에 컬럼을 비율로 맞출지 여부
	    sortorder: "asc",
	    ondblClickRow: f_MoveDown
	});

	// 선택된 부서 목록 그리드
	$("#grdSelUserLst").jqGrid({
		datatype: "local",
	   	colNames:[gf_FindLang('이름'),gf_FindLang('직위'),gf_FindLang('직책/직무'),gf_FindLang('부서/현장')],
	   	colModel:[
	  	   		{name:'signUserNm',index:'signUserNm', width:70, align:'center'},
		   		{name:'apperPositNm',index:'apperPositNm', width:65, align:'center'},
		   		{name:'apperRpswrkNm',index:'apperRpswrkNm', width:65, align:'center'},
		   		{name:'apperOrgNm',index:'apperOrgNm', width:200, align:'left'}
	   	],
	   	width:433,
	   	height:150,
	   	sortname: 'orgNm',
	    viewrecords: true,
	    shrinkToFit : true,	// 그리드 크기에 컬럼을 비율로 맞출지 여부
	    sortorder: "asc",
	    ondblClickRow: f_MoveUp
	});
}

/**
* @class Form Onload DataSet Binding 처리
* @author 변형구
* @since 2013-04-12
* @version 1.0
*/
function cf_SetBinding()
{
	//Grid Binding
	ds_UserList.bind($("#grdUserLst"));
	ds_SelUserList.bind($("#grdSelUserLst"));
}

/**
* @class Form Onload 시 Element/Component 인벤트 초기화
* @author 변형구
* @since 2013-04-12
* @version 1.0
*/
function cf_SetEventListener()
{
	$("#btnSearch").click(f_Search);
	$("#btnMoveDown").click(f_MoveDown);
	$("#btnMoveUp").click(f_MoveUp);
	$("#btnConfirm").click(f_Confirm);
	$("#btnClose").click(f_Close);

	$("#treeReset").click(function(){
		f_RetrieveOrgList();
	});


	$("input[name='txtSrchTxt']").bind('keypress', function(e) {
		if (e.which == 13) {
			f_Search();
		}
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
	ds_SelUserList.setAllData(datas.userDatas);
}

/**************************************************************
 * 각 화면 요소별 이벤트 정의 영역
 **************************************************************/
/**
* @class 확인버튼 클릭시 수행되는 이벤트 함수
* @author 변형구
* @since 2013-04-12
* @version 1.0
*/
function f_Confirm() {

		if ( !gf_IsNull(v_CallbackFunc) ) {
			var	openCallbackFunc = "opener."+v_CallbackFunc;
			eval(openCallbackFunc + "(ds_SelUserList);");
	    }
	self.close();
}

/**
* @class 닫기 버튼 클릭시 수행되는 이벤트 함수
* @author 변형구
* @since 2013-04-12
* @version 1.0
*/
function f_Close() {
	ds_SelUserList.reset();
	if(ds_SelUserList.size() > 0){
		if ( !gf_IsNull(v_CallbackFunc) ) {
			var	closeCallbackFunc = "opener."+v_CallbackFunc;
			eval(closeCallbackFunc + "(ds_SelUserList);");
	    }
	}
	self.close();
}

/**
* @class ▼ 버튼 클릭시 수행되는 이벤트 함수
* @author 이광우
* @since 2013-06-12
* @version 1.0
*/
function f_MoveDown() {

	// 선택수 제한
	if(v_CallbackFunc =="f_riskCsndSctUserPopCallback"
		|| v_CallbackFunc =="f_invtCsndSctUserPopCallback"
			||v_CallbackFunc =="f_invtCsndCmUserPopCallback"
				||v_CallbackFunc =="f_riskCsndCmUserPopCallback"
					||v_CallbackFunc =="f_msignInchrgUserPopCallback"
						||v_CallbackFunc =="f_ProxySignUserPopCallback"
						){
		var selRow = $("#grdUserLst").getGridParam( "selrow" );
		if(ds_SelUserList.size() < 1){
			var selUserId = $("#grdUserLst").data(selRow).get("signUserId");
			if ( !gf_IsNull(ds_SelUserList.findRow("signUserId", selUserId)) ) {
				gf_AlertMsg('이미 선택된 사용자 입니다.');
				return;
			}
			ds_SelUserList.add($("#grdUserLst").data(selRow).clone());
		}else{
			gf_AlertMsg('한명이상 선택하실수 없습니다.');
		}
	} else {
		var selRow = $("#grdUserLst").getGridParam( "selrow" );

		var selUserId = $("#grdUserLst").data(selRow).get("signUserId");
		if ( !gf_IsNull(ds_SelUserList.findRow("signUserId", selUserId)) ) {
			gf_AlertMsg('이미 선택된 사용자 입니다.');
			return;
		}
		ds_SelUserList.add($("#grdUserLst").data(selRow).clone());
	}
}

/**
* @class ▲ 버튼 클릭시 수행되는 이벤트 함수
* @author 변형구
* @since 2013-04-12
* @version 1.0
*/
function f_MoveUp() {

	var selRow = $("#grdSelUserLst").getGridParam( "selrow" );
	ds_SelUserList.remove($("#grdSelUserLst").data(selRow));
}

/**
* @class 조회 버튼 클릭시 수행되는 이벤트 함수
* @author 변형구
* @since 2013-04-12
* @version 1.0
*/
function f_Search() {
	var srchTxt = $("input[name='txtSrchTxt']").val();

	f_RetrieveOrg(srchTxt.toUpperCase());
	f_RetrieveUser(srchTxt.toUpperCase());
}


/**************************************************************
 * transaction 정의 및 기타 사용자 정의 함수 영역
 **************************************************************/

/**
* @class 부서조회 transaction 함수
* @author 변형구
* @since 2013-04-12
* @version 1.0
*/
function f_RetrieveOrgList(){
  	gf_Transaction("SELECT_ORG",
			"/co/common/user/retrieveSignOrgMapList.xpl",
			{},
			{},
			"f_Callback", true);
}

/**
* @class 사용자 조회  transaction 함수
* @param orgCd<String> 사용자를 조회할 조직코드
* @param userNm<String> 조회할 사용자명
* @return N/A
* @author 변형구
* @since 2013-04-12
* @version 1.0
*/
function f_RetrieveSignUserList(orgCd, userNm){
	if ( gf_IsNull(orgCd) ) {
		orgCd = "";
	}
	if ( gf_IsNull(userNm) ) {
		userNm = "";
	}

  	gf_Transaction("SELECT_USER",
			"/co/common/user/retrieveSignUserList.xpl",
			{"orgCd" : orgCd, "userKnm" : userNm },
			{},
			"f_Callback", false);
}

/**
* @class transaction callback 함수
* @param strSvcId<String> transaction 서비스 명
* @param obj<Object>
* @param resultData(JSON<Array>) transaction의 결과로 전달된 json array
* @author 변형구
* @since 2013-04-12
* @version 1.0
*/
function f_Callback(strSvcId, obj, resultData){

	  // transaction의 정상 처리 여부를 판단한다.
	  if (!gf_ChkTransaction(strSvcId, resultData, true )) {
		  return;
	  }

	  switch(strSvcId) {
	  	case "SELECT_ORG" :
	  		// 조회한 조직 데이터를 데이터셑으로 이동
	  		ds_OrgList.setAllData(resultData.ds_SignOrg);

	  		// tree display를 위한 데이터 생성
	  		zNodes = [];
	  		$.each(ds_OrgList.view(), function(i, row){
	  			zNodes.push(row.get());
	  		});

	  		// tree display & expand all 처리
	  		var t = $("#orgTree");
	  		t = $.fn.zTree.init(t, setting, zNodes);
	  		var treeObj = $.fn.zTree.getZTreeObj("orgTree");
	  		treeObj.expandAll(true);

			break;
	  	case "SELECT_USER" :
	  		ds_UserList.setAllData(resultData.ds_SignUser);
	  		gf_Trace('11111');
	  		ds_UserList.setPosition(0);
	  		gf_Trace('2222');
	  		break;
	  	default :
	  		break;
	  }
}

/**
* @class 검색어에 따른 조직도 tree 검색
* @param srchTxt<String> 검색어 (조직명)
* @return N/A
* @author 변형구
* @since 2013-04-12
* @version 1.0
*/
function f_RetrieveOrg(srchTxt) {
	if ( gf_IsNull(srchTxt) ) {
		// filter 해제
		ds_OrgList.filter(null);
	}
	else {
		ds_OrgList.filter(
			function (DataSetRow) {
				if ( DataSetRow.get("orgNm").indexOf(srchTxt) >= 0 ) {
					return true;
				}
				return false;
			}
		);
	}

	zNodes = [];
	$.each(ds_OrgList.view(), function(i, row){
		zNodes.push(row.get());
	});

	var t = $("#orgTree");
	t = $.fn.zTree.init(t, setting, zNodes);

	var treeObj = $.fn.zTree.getZTreeObj("orgTree");
	treeObj.expandAll(true);

	// filter 해제
	ds_OrgList.filter(null);
}

/**
* @class 검색어에 따른 사용자(사원) 검색
* @param srchTxt<String> 검색어 (사원명)
* @return N/A
* @author 변형구
* @since 2013-04-12
* @version 1.0
*/
function f_RetrieveUser(srchTxt) {

	f_RetrieveSignUserList("", srchTxt);
}