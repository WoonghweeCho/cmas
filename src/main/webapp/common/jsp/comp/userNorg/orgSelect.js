
/*
 * 01. 업무구분 			: 공통
 * 02. 스크립트 설명  	: 공통 사용자 선택 팝업을 위한 자바 스크립트
 * 03. 작성자 			: 변형구
 * 04. 작성일 			: 2013.04.12
 */

/**************************************************************
 * 전역 변수 영역
 **************************************************************/
// 부서목록 데이터셑
var ds_OrgList		= new DataSet();
// 공통그룹 목록 데이터셑
var ds_CommGrouplnDtl = new DataSet();
// 선택 부서목록 데이터셑
var ds_SelOrgList 	= new DataSet();
// 트리에서 선택한 부서코드를 가지는 전역 변수
var v_SelectedOrgCd;
var v_SelectedLeaf;
//팝업이 닫힐때 리턴값을 전달할 callback 함수
var v_CallbackFunc;
// 팝업의 레벨
var v_FstLvlPop = true;
// modal pop 여부
var v_IsModal = true;

var v_SelTabNm = "treeOrgTab";

var v_SelectType = "";

var param={};

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
				v_SelectedLeaf = treeNode.isParent;
				/*var zTree = $.fn.zTree.getZTreeObj("tree");
				if (treeNode.isParent) {
					zTree.expandNode(treeNode);
					f_RetrieveSignUserList(treeNode.orgCd);
					return false;
				} else {
					f_RetrieveSignUserList(treeNode.orgCd);
					return true;
				}*/
			},
			// tree double click event
			onDblClick: function ( evt, treeId, treeNode) {
				f_MoveRight(v_SelectedOrgCd, v_SelectedLeaf);
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
	v_SelectType = gf_IsNull(datas.selectType) ? "" : datas.selectType;

	v_CallbackFunc = gf_IsNull(datas.callbackFunc) ? ""          : datas.callbackFunc;
	v_FstLvlPop    = gf_IsNull(datas.fstLvlPop)    ? v_FstLvlPop : eval(datas.fstLvlPop);
	v_IsModal      = gf_IsNull(datas.isModal)      ? v_IsModal   : eval(datas.isModal);
	// 부서 트리 목록을 조회한다.
	f_RetrieveOrgList();
	// 공통 그룹 목록을 조회한다.
//	f_RetrieveCommGrpList();
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
	   	colNames:[gf_FindLang('부서코드'),gf_FindLang('부서/현장명')],
	   	colModel:[
	  	   		{name:'orgCd',index:'orgCd', width:70},
		   		{name:'orgNm',index:'orgNm', width:230}
	   	],
	   	width:295,
	   	height:315,
	   	sortname: 'orgNm',
	    viewrecords: true,
	    shrinkToFit : true,	// 그리드 크기에 컬럼을 비율로 맞출지 여부
	    sortorder: "asc",
	    ondblClickRow: f_MoveLeft
	});

	// 선택된 부서 목록 그리드

	$("#grdGrp").jqGrid({
		datatype: "local",
	   	colNames:[gf_FindLang('순번'),gf_FindLang('그룹명')],
	   	colModel:[
	  	   		{name:'rn',index:'rn', width:70, align:'center'},
		   		{name:'recvPlNm',index:'recvPlNm', width:200, align:'left'}
	   	],
	   	width:295,
	   	height:315,
	   	sortname: 'recvPlNm',
	    viewrecords: true,
	    shrinkToFit : true,	// 그리드 크기에 컬럼을 비율로 맞출지 여부
	    sortorder: "asc",
	    ondblClickRow: function(rowid, iRow, iCol, e){
	     	var rowData = $("#grdGrp").data(rowid);
	    	f_MoveRight(rowData.get("cuseRecvPlId"), false);
	    }
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
	ds_SelOrgList.bind($("#grdUserLst"));

	//Grid Binding
	ds_CommGrouplnDtl.bind($("#grdGrp"));
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
	$("#btnMoveRight").click(f_MoveRight);
	$("#btnMoveLeft").click(f_MoveLeft);
	$("#btnConfirm").click(f_Confirm);
	$("#btnClose").click(f_Close);

	$("#treeReset").click(function(){
		f_RetrieveOrgList();
	});

	//파일첨부 Tab
	$("#treeOrgTab").click(function(){
		f_DisplayList("treeOrgTab");
	});

	//문서첨부 Tab
	$("#grdCommGrpTab").click(function(){
		f_DisplayList("grdCommGrpTab");
	});

	//파일첨부 Tab
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
	ds_SelOrgList.setAllData(datas.orgDatas);

	f_CheckFilter()

	//수신처일때만 공통그룹표시
	if(v_CallbackFunc =="f_orgReadtnCallback"){
		$("#grdCommGrpTab").show();
	}
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
		var openCallbackFunc = "opener."+v_CallbackFunc;
    	eval(openCallbackFunc + "(ds_SelOrgList);");
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

	self.close();
}

/**
* @class ▶ 버튼 클릭시 수행되는 이벤트 함수
* @author 변형구
* @since 2013-04-12
* @version 1.0
*/
function f_MoveRight( selectedOrgCd, isLeaf ) {
	if ( typeof(selectedOrgCd) != "string" || gf_IsNull(selectedOrgCd) ) {
		selectedOrgCd = v_SelectedOrgCd;
	}
	if(typeof(isLeaf) == "undefined")
		isLeaf = v_SelectedLeaf;

	if(isLeaf == false){

		if(v_CallbackFunc =="f_orgPopCallback"){
			if(ds_SelOrgList.size() < 1){
				selectProcess(selectedOrgCd);
			}else{
				// 한명이상 선택 할 수 없습니다.
				gf_AlertMsg('co.warn.notSelectOverDept');
			}
		}else{
			selectProcess(selectedOrgCd);
		}
	}
}

function selectProcess(selectedOrgCd){

	ds_SelOrgList.filter(null);

	if ( v_SelTabNm == "grdCommGrpTab") {
		var pos = ds_CommGrouplnDtl.getPosition();
		if(pos <0){
			// 선택된 row가 없습니다.
			gf_AlertMsg("co.err.moreLarge", [gf_FindLang('행')]);
			return;
		}

		if ( ds_SelOrgList.find("orgCd", ds_CommGrouplnDtl.get(pos, "cuseRecvPlId")) > -1 ) {
			// 이미 선택한 부서입니다.
			f_CheckFilter();
			gf_AlertMsg("co.err.dupDepart");
			return false;
		}

		var row = {
				orgCd : ds_CommGrouplnDtl.get(pos, "cuseRecvPlId"),
				orgNm : ds_CommGrouplnDtl.get(pos, "recvPlNm"),
				gubn  : ds_CommGrouplnDtl.get(pos, "gubn")
			};
		if(v_SelectType == "readtn")
			row.type = "G";

		ds_SelOrgList.add(row);
		//ds_SelOrgList.remove($("#grdGrp").data(selRow));

	}
	else {
		if ( ds_SelOrgList.find("orgCd", selectedOrgCd) > -1 ) {
			// 이미 선택한 부서입니다.
			f_CheckFilter();
			gf_AlertMsg("co.err.dupDepart");
			return false;
		}
		/**/

		var selRowObj = ds_OrgList.findRow("orgCd", selectedOrgCd);

		var row = {
				orgCd : selRowObj.get("orgCd"),
				orgNm : selRowObj.get("orgNm"),
				gubn  : selRowObj.get("gubn")
			};

		if(v_SelectType == "readtn")
			row.type = "G";

		ds_SelOrgList.add(row);
	}
	f_CheckFilter();

}
/**
* @class ◀ 버튼 클릭시 수행되는 이벤트 함수
* @author 변형구
* @since 2013-04-12
* @version 1.0
*/
function f_MoveLeft() {

	var selRow = $("#grdUserLst").getGridParam( "selrow" );
	ds_SelOrgList.remove($("#grdUserLst").data(selRow));

}

/**
* @class 조회 버튼 클릭시 수행되는 이벤트 함수
* @author 변형구
* @since 2013-04-12
* @version 1.0
*/
function f_Search() {

	var srchtext = $("input[name='txtSrchTxt']").val();
	var srchCond = srchtext.toUpperCase();

	if ( v_SelTabNm == "grdCommGrpTab") {
		f_RetrieveCommGrpList(srchCond);
	}else {
		if ( gf_IsNull(srchCond) ) {
			// filter 해제
			ds_OrgList.filter(null);
		}
		else {
			ds_OrgList.filter(
				function (DataSetRow) {
					if ( DataSetRow.get("orgNm").indexOf(srchCond) >= 0 ) {
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

		var t = $("#orgtree");
		t = $.fn.zTree.init(t, setting, zNodes);

		var treeObj = $.fn.zTree.getZTreeObj("orgtree");
		treeObj.expandAll(true);

		// filter 해제
		ds_OrgList.filter(null);
	}
}

/**
* @class 부서, 공통 그룹 탭 클릭시 수행되는 이벤트 함수
* @author 변형구
* @since 2013-04-12
* @version 1.0
*/
function f_DisplayList(tabNm){
	v_SelTabNm = tabNm;
	 if(tabNm =="grdCommGrpTab"){
		 $("input[name='txtSrchTxt']").val("");
		 $("#grdCommGrpTab").removeClass("tab off").addClass("tab on");
		 $("#treeOrgTab").removeClass("tab on").addClass("tab off");
		 $("#treeOrg").hide();//.attr("style", "display:none"); //문서첨부 컴포넌트 : Hide
		 $("#grdCommGrp").show();
	 }else{
		 $("input[name='txtSrchTxt']").val("");
		 $("#treeOrgTab").removeClass("tab off").addClass("tab on");
		 $("#grdCommGrpTab").removeClass("tab on").addClass("tab off");
		 $("#grdCommGrp").hide(); //.attr("style", "display:none"); //파일첨부 컴포넌트 : Hide
		 $("#treeOrg").show();
	 }
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
* @class 공통그룹 transaction 함수
* @author 변형구
* @since 2013-04-12
* @version 1.0
*/
function f_RetrieveCommGrpList(srchCond){
	if ( gf_IsNull(srchCond)) {
		srchCond = "";
	}
	var datass = {};
	datass.useYn 		= "Y";					// 사용여부
	datass.recvPlNm       = srchCond.toUpperCase();
  	gf_Transaction("SELECT_COMMGRP",
			"/co/common/user/retrieveCommGroup.xpl",
			datass,
			{},
			"f_Callback", true);
}

/**
* @class transaction callback 함수
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
	  		ds_OrgList.setAllData(resultData.ds_SignOrg);
	  		zNodes = [];
	  		$.each(ds_OrgList.view(), function(i, row){
	  			zNodes.push(row.get());
	  		});

	  		var t = $("#orgtree");

	  		start = $.now();

	  		t = $.fn.zTree.init(t, setting, zNodes);

	  		var treeObj = $.fn.zTree.getZTreeObj("orgtree");
	  		treeObj.expandAll(true);

			break;
	  	case "SELECT_COMMGRP" :
	  		ds_CommGrouplnDtl.setAllData(resultData.ds_CommGrouplnDtl);
			break;
	  	default :
	  		break;
	  }
}

function f_CheckFilter(){
	if(v_SelectType == "readtn"){
		ds_SelOrgList.filter(function(row){
			if(row.get("type") == "G")
				return true;
			return false;
		});
	}
}
