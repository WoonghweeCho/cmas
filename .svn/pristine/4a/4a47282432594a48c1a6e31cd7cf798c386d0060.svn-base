// 지역 리스트
var ds_StartDist 	= new DataSet();
var ds_StartCity 	= new DataSet();
var ds_EndDist 	= new DataSet();
var ds_EndCity 	= new DataSet();

//팝업이 닫힐때 리턴값을 전달할 callback 함수
var v_CallbackFunc;
// 팝업의 레벨
var v_FstLvlPop = true;
// modal pop 여부
var v_IsModal = true;

var v_TargetId;
var v_tStart;
var v_tEnd;

var param={};

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
	v_TargetId = gf_IsNull(datas.targetId) ? "" : datas.targetId;
	tStart = gf_IsNull(datas.tStart) ? "" : datas.tStart;
	tEnd = gf_IsNull(datas.tEnd) ? "" : datas.tEnd;

	v_tStart = tStart.split(" ");
	v_tEnd = tEnd.split(" ");


	v_CallbackFunc = gf_IsNull(datas.callbackFunc) ? ""          : datas.callbackFunc;
	v_FstLvlPop    = gf_IsNull(datas.fstLvlPop)    ? v_FstLvlPop : eval(datas.fstLvlPop);
	v_IsModal      = gf_IsNull(datas.isModal)      ? v_IsModal   : eval(datas.isModal);
	// 부서 트리 목록을 조회한다.
//	f_RetrieveOrgList();
//	// 공통 그룹 목록을 조회한다.
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

}

/**
* @class Form Onload DataSet Binding 처리
* @author 변형구
* @since 2013-04-12
* @version 1.0
*/
function cf_SetBinding()
{

	ds_StartDist.bind($("#startDist")[0], {val: "key", text: "val"});
	ds_StartCity.bind($("#startCity")[0], {val: "key", text: "val"});
	ds_EndDist.bind($("#endDist")[0], {val: "key", text: "val"});
	ds_EndCity.bind($("#endCity")[0], {val: "key", text: "val"});
}

/**
* @class Form Onload 시 Element/Component 인벤트 초기화
* @author 변형구
* @since 2013-04-12
* @version 1.0
*/
function cf_SetEventListener()
{
	$("#btnConfirm").click(f_Confirm);
	$("#btnClose").click(f_Close);

	//파일첨부 Tab
	$("input[name='txtSrchTxt']").bind('keypress', function(e) {
		if (e.which == 13) {
			f_Search();
		}
	});

	$("#startDist").bind('change', function(e) {

		ds_StartCity.setAllData(eval($("#startDist").val()));

	});

	$("#endDist").bind('change', function(e) {

		ds_EndCity.setAllData(eval($("#endDist").val()));

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
	ds_StartDist.setAllData(Start);
	ds_EndDist.setAllData(Start);

	$("#startDist").val(ds_StartDist.get(ds_StartDist.find("val", v_tStart[0])).key);
	$("#endDist").val(ds_EndDist.get(ds_EndDist.find("val", v_tEnd[0])).key);

	ds_StartCity.setAllData(eval($("#startDist").val()));
	ds_EndCity.setAllData(eval($("#endDist").val()));

	$("#startCity").val(ds_StartCity.get(ds_StartCity.find("val", v_tStart[1])).key);
	$("#endCity").val(ds_EndCity.get(ds_EndCity.find("val", v_tEnd[1])).key);
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

	var callbackObj = {
			startDist : $("#startDist option:selected").text(),		// 구분
			startCity : $("#startCity option:selected").text(),
			endDist : $("#endDist option:selected").text(),
			endCity : $("#endCity option:selected").text(),
			targetId : v_TargetId
	};

	if ( !gf_IsNull(v_CallbackFunc) ) {
		var openCallbackFunc = "opener."+v_CallbackFunc;
    	eval(openCallbackFunc + "(callbackObj);");
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
