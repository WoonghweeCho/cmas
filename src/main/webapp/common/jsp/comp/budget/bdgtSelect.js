
// 예산조회리스트
var ds_BdgtList 	= new DataSet();

//팝업이 닫힐때 리턴값을 전달할 callback 함수
var v_CallbackFunc;
// 팝업의 레벨
var v_FstLvlPop = true;
// modal pop 여부
var v_IsModal = true;

var v_BdgtType;

//Type별 Grid 옵션

var v_gOptA = {
		datatype: "local",
	   	colNames:[gf_FindLang('임원코드'),gf_FindLang('임원명'),gf_FindLang('작업시작'),gf_FindLang('작업종료'),gf_FindLang('책임코스트')
	   	       ,gf_FindLang('팀명'),gf_FindLang('집행팀장')],
	   	colModel:[
	  	   		{name:'Aufnr',index:'Aufnr', width:50},
		   		{name:'Ktext',index:'Ktext', width:50},
		   		{name:'User7',index:'User7', width:50},
		   		{name:'User8',index:'User8', width:50},
		   		{name:'Kostv',index:'Kostv', width:50},
		   		{name:'Kostvnm',index:'Kostvnm', width:50},
		   		{name:'Chiefnm',index:'Chiefnm', width:50,
		   			formatter:function(val, gOpt, row){
		   				var rowData = $("#bdgtList").data(gOpt.rowId);
		   				var nm = gf_IsNull(val) ? "" : val;
		   				var no = gf_IsNull(rowData.get("Chief")) ? "" : rowData.get("Chief");
		   				var rsVal = no + " " + nm;
	  	   				return rsVal;
		   			}
  	   			}
	   	],
	   	width:635,
	   	height:360,
	   	sortname: 'User7',
	    viewrecords: true,
	    shrinkToFit : true,	// 그리드 크기에 컬럼을 비율로 맞출지 여부
	    sortorder: "desc",
	    ondblClickRow: function(rowid, iRow, iCol, boxid, e){
	    	grd_List_oncelldbclick(this, rowid, iRow, iCol);
	     }
	};

var v_gOptB = {
		datatype: "local",
		colNames:[gf_FindLang('팀장코드'),gf_FindLang('팀장명'),gf_FindLang('작업시작'),gf_FindLang('작업종료'),gf_FindLang('책임코스트')
		   	       ,gf_FindLang('팀명'),gf_FindLang('집행팀장')],
		colModel:[
		  	   		{name:'Aufnr',index:'Aufnr', width:50},
			   		{name:'Ktext',index:'Ktext', width:50},
			   		{name:'User7',index:'User7', width:50},
			   		{name:'User8',index:'User8', width:50},
			   		{name:'Kostv',index:'Kostv', width:50},
			   		{name:'Kostvnm',index:'Kostvnm', width:50},
			   		{name:'Chiefnm',index:'Chiefnm', width:50,
			   			formatter:function(val, gOpt, row){
			   				var rowData = $("#bdgtList").data(gOpt.rowId);
			   				var nm = gf_IsNull(val) ? "" : val;
			   				var no = gf_IsNull(rowData.get("Chief")) ? "" : rowData.get("Chief");
			   				var rsVal = no + " " + nm;
		  	   				return rsVal;
			   			}
	  	   			}
	   	],
	   	width:635,
	   	height:360,
	   	sortname: 'User7',
	    viewrecords: true,
	    shrinkToFit : true,	// 그리드 크기에 컬럼을 비율로 맞출지 여부
	    sortorder: "desc",
	    ondblClickRow: function(rowid, iRow, iCol, boxid, e){
	    	grd_List_oncelldbclick(this, rowid, iRow, iCol);
	     }
	};

var v_gOptC = {
		datatype: "local",
		colNames:[gf_FindLang('책임코스트')
		   	       ,gf_FindLang('팀명'),gf_FindLang('집행팀장')],
		colModel:[
		  	   		{name:'Kostv',index:'Kostv', width:50},
			   		{name:'Kostvnm',index:'Kostvnm', width:50},
			   		{name:'Chiefnm',index:'Chiefnm', width:50,
			   			formatter:function(val, gOpt, row){
			   				var rowData = $("#bdgtList").data(gOpt.rowId);
			   				var nm = gf_IsNull(val) ? "" : val;
			   				var no = gf_IsNull(rowData.get("Chief")) ? "" : rowData.get("Chief");
			   				var rsVal = no + " " + nm;
		  	   				return rsVal;
			   			}
	  	   			}
	   	],
	   	width:635,
	   	height:360,
	   	sortname: 'Kostv',
	    viewrecords: true,
	    shrinkToFit : true,	// 그리드 크기에 컬럼을 비율로 맞출지 여부
	    sortorder: "asc",
	    ondblClickRow: function(rowid, iRow, iCol, boxid, e){
	    	grd_List_oncelldbclick(this, rowid, iRow, iCol);
	     }
	};

var v_gOptI = {
		datatype: "local",
		colNames:[gf_FindLang('예산번호'),gf_FindLang('내역'),gf_FindLang('작업시작'),gf_FindLang('작업종료'),gf_FindLang('책임코스트')
		   	       ,gf_FindLang('팀명'),gf_FindLang('집행팀장')],
		colModel:[
		  	   		{name:'Aufnr',index:'Aufnr', width:50},
			   		{name:'Ktext',index:'Ktext', width:50},
			   		{name:'User7',index:'User7', width:50},
			   		{name:'User8',index:'User8', width:50},
			   		{name:'Kostv',index:'Kostv', width:50},
			   		{name:'Kostvnm',index:'Kostvnm', width:50},
			   		{name:'Chiefnm',index:'Chiefnm', width:50,
			   			formatter:function(val, gOpt, row){
			   				var rowData = $("#bdgtList").data(gOpt.rowId);
			   				var nm = gf_IsNull(val) ? "" : val;
			   				var no = gf_IsNull(rowData.get("Chief")) ? "" : rowData.get("Chief");
			   				var rsVal = no + " " + nm;
		  	   				return rsVal;
			   			}
	  	   			}
	   	],
	   	width:635,
	   	height:360,
	   	sortname: 'User7',
	    viewrecords: true,
	    shrinkToFit : true,	// 그리드 크기에 컬럼을 비율로 맞출지 여부
	    sortorder: "desc",
	    ondblClickRow: function(rowid, iRow, iCol, boxid, e){
	    	grd_List_oncelldbclick(this, rowid, iRow, iCol);
	     }
	};

var v_gOptO = {
		datatype: "local",
		colNames:[gf_FindLang('예산번호'),gf_FindLang('내역'),gf_FindLang('작업시작'),gf_FindLang('작업종료'),gf_FindLang('책임코스트')
		   	       ,gf_FindLang('팀명'),gf_FindLang('집행팀장')],
		colModel:[
		  	   		{name:'Aufnr',index:'Aufnr', width:50},
			   		{name:'Ktext',index:'Ktext', width:50},
			   		{name:'User7',index:'User7', width:50},
			   		{name:'User8',index:'User8', width:50},
			   		{name:'Kostv',index:'Kostv', width:50},
			   		{name:'Kostvnm',index:'Kostvnm', width:50},
			   		{name:'Chiefnm',index:'Chiefnm', width:50,
			   			formatter:function(val, gOpt, row){
			   				var rowData = $("#bdgtList").data(gOpt.rowId);
			   				var nm = gf_IsNull(val) ? "" : val;
			   				var no = gf_IsNull(rowData.get("Chief")) ? "" : rowData.get("Chief");
			   				var rsVal = no + " " + nm;
		  	   				return rsVal;
			   			}
	  	   			}
	   	],
	   	width:635,
	   	height:360,
	   	sortname: 'User7',
	    viewrecords: true,
	    shrinkToFit : true,	// 그리드 크기에 컬럼을 비율로 맞출지 여부
	    sortorder: "desc",
	    ondblClickRow: function(rowid, iRow, iCol, boxid, e){
	    	grd_List_oncelldbclick(this, rowid, iRow, iCol);
	     }
	};

var v_gOptP = {
		datatype: "local",
		colNames:[gf_FindLang('예산번호'),gf_FindLang('내역'),gf_FindLang('작업시작'),gf_FindLang('작업종료'),gf_FindLang('책임코스트')
		   	       ,gf_FindLang('팀명'),gf_FindLang('집행팀장')],
		colModel:[
		  	   		{name:'Aufnr',index:'Aufnr', width:50},
			   		{name:'Ktext',index:'Ktext', width:50},
			   		{name:'User7',index:'User7', width:50},
			   		{name:'User8',index:'User8', width:50},
			   		{name:'Kostv',index:'Kostv', width:50},
			   		{name:'Kostvnm',index:'Kostvnm', width:50},
			   		{name:'Chiefnm',index:'Chiefnm', width:50,
			   			formatter:function(val, gOpt, row){
			   				var rowData = $("#bdgtList").data(gOpt.rowId);
			   				var nm = gf_IsNull(val) ? "" : val;
			   				var no = gf_IsNull(rowData.get("Chief")) ? "" : rowData.get("Chief");
			   				var rsVal = no + " " + nm;
		  	   				return rsVal;
			   			}
	  	   			}
	   	],
	   	width:635,
	   	height:360,
	   	sortname: 'User7',
	    viewrecords: true,
	    shrinkToFit : true,	// 그리드 크기에 컬럼을 비율로 맞출지 여부
	    sortorder: "desc",
	    ondblClickRow: function(rowid, iRow, iCol, boxid, e){
	    	grd_List_oncelldbclick(this, rowid, iRow, iCol);
	     }
	};

var v_gOptR = {
		datatype: "local",
		colNames:[gf_FindLang('예산번호'),gf_FindLang('내역'),gf_FindLang('책임코스트')
		   	       ,gf_FindLang('팀명'),gf_FindLang('집행팀장')],
		colModel:[
		  	   		{name:'Aufnr',index:'Aufnr', width:50},
			   		{name:'Ktext',index:'Ktext', width:50},
			   		{name:'Kostv',index:'Kostv', width:50},
			   		{name:'Kostvnm',index:'Kostvnm', width:50},
			   		{name:'Chiefnm',index:'Chiefnm', width:50,
			   			formatter:function(val, gOpt, row){
			   				var rowData = $("#bdgtList").data(gOpt.rowId);
			   				var nm = gf_IsNull(val) ? "" : val;
			   				var no = gf_IsNull(rowData.get("Chief")) ? "" : rowData.get("Chief");
			   				var rsVal = no + " " + nm;
		  	   				return rsVal;
			   			}
	  	   			}
	   	],
	   	width:635,
	   	height:360,
	   	sortname: 'Kostv',
	    viewrecords: true,
	    shrinkToFit : true,	// 그리드 크기에 컬럼을 비율로 맞출지 여부
	    sortorder: "asc",
	    ondblClickRow: function(rowid, iRow, iCol, boxid, e){
	    	grd_List_oncelldbclick(this, rowid, iRow, iCol);
	     }
	};

var v_gOptK = {
		datatype: "local",
		colNames:[gf_FindLang('예산번호'),gf_FindLang('내역'),gf_FindLang('작업시작'),gf_FindLang('작업종료'),gf_FindLang('책임코스트')
		   	       ,gf_FindLang('팀명'),gf_FindLang('집행팀장')],
		colModel:[
		  	   		{name:'Aufnr',index:'Aufnr', width:50},
			   		{name:'Ktext',index:'Ktext', width:50},
			   		{name:'User7',index:'User7', width:50},
			   		{name:'User8',index:'User8', width:50},
			   		{name:'Kostv',index:'Kostv', width:50},
			   		{name:'Kostvnm',index:'Kostvnm', width:50},
			   		{name:'Chiefnm',index:'Chiefnm', width:50,
			   			formatter:function(val, gOpt, row){
			   				var rowData = $("#bdgtList").data(gOpt.rowId);
			   				var nm = gf_IsNull(val) ? "" : val;
			   				var no = gf_IsNull(rowData.get("Chief")) ? "" : rowData.get("Chief");
			   				var rsVal = no + " " + nm;
		  	   				return rsVal;
			   			}
	  	   			}
	   	],
	   	width:635,
	   	height:360,
	   	sortname: 'Kostv',
	    viewrecords: true,
	    shrinkToFit : true,	// 그리드 크기에 컬럼을 비율로 맞출지 여부
	    sortorder: "asc",
	    ondblClickRow: function(rowid, iRow, iCol, boxid, e){
	    	grd_List_oncelldbclick(this, rowid, iRow, iCol);
	     }
	};

var v_gOptS = {
		datatype: "local",
		colNames:[gf_FindLang('예산번호'),gf_FindLang('내역'),gf_FindLang('작업시작'),gf_FindLang('작업종료'),gf_FindLang('책임코스트')
		   	       ,gf_FindLang('팀명'),gf_FindLang('집행팀장')],
		colModel:[
		  	   		{name:'Aufnr',index:'Aufnr', width:50},
			   		{name:'Ktext',index:'Ktext', width:50},
			   		{name:'User7',index:'User7', width:50},
			   		{name:'User8',index:'User8', width:50},
			   		{name:'Kostv',index:'Kostv', width:50},
			   		{name:'Kostvnm',index:'Kostvnm', width:50},
			   		{name:'Chiefnm',index:'Chiefnm', width:50,
			   			formatter:function(val, gOpt, row){
			   				var rowData = $("#bdgtList").data(gOpt.rowId);
			   				var nm = gf_IsNull(val) ? "" : val;
			   				var no = gf_IsNull(rowData.get("Chief")) ? "" : rowData.get("Chief");
			   				var rsVal = no + " " + nm;
		  	   				return rsVal;
			   			}
	  	   			}
	   	],
	   	width:635,
	   	height:360,
	   	sortname: 'Kostv',
	    viewrecords: true,
	    shrinkToFit : true,	// 그리드 크기에 컬럼을 비율로 맞출지 여부
	    sortorder: "asc",
	    ondblClickRow: function(rowid, iRow, iCol, boxid, e){
	    	grd_List_oncelldbclick(this, rowid, iRow, iCol);
	     }
	};

var v_gOptQ = {
		datatype: "local",
		colNames:[gf_FindLang('현장코드')
		   	       ,gf_FindLang('현장명'),gf_FindLang('현장소장')],
		colModel:[
		  	   		{name:'orgId',index:'orgId', width:50},
			   		{name:'orgNm',index:'orgNm', width:50},
			   		{name:'orgChief',index:'orgChief', width:50,
			   			formatter:function(val, gOpt, row){
			   				var rowData = $("#bdgtList").data(gOpt.rowId);
			   				var rsVal = rowData.get("orgChief");

			   				if(rowData.get("orgChief") == " "){
			   					rsVal = "9999999999" + " " + "미등록";
			   				}

		  	   				return rsVal;
		  	   			}
			   		}
	   	],
	   	width:635,
	   	height:360,
	   	sortname: 'a1',
	    viewrecords: true,
	    shrinkToFit : true,	// 그리드 크기에 컬럼을 비율로 맞출지 여부
	    sortorder: "asc",
	    ondblClickRow: function(rowid, iRow, iCol, boxid, e){
	    	grd_List_oncelldbclick(this, rowid, iRow, iCol);
	     }
	};


var param={};

var pType = "";

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
	v_BdgtType = gf_IsNull(datas.bdgtType) ? "" : datas.bdgtType;
	pType = gf_IsNull(datas.pType) ? "" : datas.pType;

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
	if(v_BdgtType == "A" || v_BdgtType == ""){

		$("#bdgtList").jqGrid(v_gOptA);

	}else if(v_BdgtType == "B"){

		$("#bdgtList").jqGrid(v_gOptB);


	}else if(v_BdgtType == "C"){

		$("#bdgtList").jqGrid(v_gOptC);


	}else if(v_BdgtType == "I"){

		$("#bdgtList").jqGrid(v_gOptI);


	}else if(v_BdgtType == "O"){

		$("#bdgtList").jqGrid(v_gOptO);

	}else if(v_BdgtType == "P"){

		$("#bdgtList").jqGrid(v_gOptP);

	}else if(v_BdgtType == "R"){

		$("#bdgtList").jqGrid(v_gOptR);

	}else if(v_BdgtType == "Q" || v_BdgtType == "Q1" || v_BdgtType == "Q2" || v_BdgtType == "Q3" || v_BdgtType == "Q4" || v_BdgtType == "Q5"){

		$("#bdgtList").jqGrid(v_gOptQ);

	}else if(v_BdgtType == "K"){

		$("#bdgtList").jqGrid(v_gOptK);

	}else if(v_BdgtType == "S"){

		$("#bdgtList").jqGrid(v_gOptS);

	}else{

		gf_AlertMsg("올바르지 않은 경비구분 코드 입니다.");

		self.close();

	}
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
	ds_BdgtList.bind($("#bdgtList"));
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


	$("#btnSearch").click(function(e){
		f_Search();

	});


	//파일첨부 Tab
	$("input[name='txtSrchTxt']").bind('keypress', function(e) {
		if (e.which == 13) {
			f_Search();
		}
	});

	$("#bdgtType").bind('change', function(e) {

		// Grid 를 Reset
		f_resetGridComp($("#bdgtType").val());

		var sFormTr = $("#bdgtSearchForm tr");

		var trParam = "bdgtType" + $("#bdgtType").val();

		for(var i = 1; i < sFormTr.size();i++){

			if(sFormTr[i].id == trParam){
				$(sFormTr[i]).css("display", "block");
			}else{
				$(sFormTr[i]).css("display", "none");
			}
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

	$("#bdgtType").val(v_BdgtType);

	var sFormTr = $("#bdgtSearchForm tr");

	var trParam = "bdgtType" + $("#bdgtType").val();

	for(var i = 1; i < sFormTr.size();i++){

		if(sFormTr[i].id == trParam){
			$(sFormTr[i]).css("display", "block");
		}else{
			$(sFormTr[i]).css("display", "none");
		}
	}

	//alert("pType : " + pType);
	// 해외출장의 경우는 현장경비를 사용하지 않는다.(2017.08.29 사용한다.)
	//if(pType == "OT") $("#bdgtType option[value='Q']").remove();

	//  해외출장을 제외한 국내출장, 시내교통비의 경우는 PJ현장경비를 사용하지 않는다.(2017.09.05 사용하지 않는다)
	if(pType != "OT"){
		$("#bdgtType option[value='Q1']").remove();
		$("#bdgtType option[value='Q2']").remove();
		$("#bdgtType option[value='Q3']").remove();
		$("#bdgtType option[value='Q4']").remove();
		$("#bdgtType option[value='Q5']").remove();
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

	if(ds_BdgtList.getPosition() == -1){
		gf_AlertMsg("예산을 선택하세요.");
		return;
	}

	var callbackObj = {
			bdgtType : $("#bdgtType").val(),
			bdgtData : ds_BdgtList.get(ds_BdgtList.getPosition())

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

function f_resetGridComp(typeParam){

	$("#bdgtList").GridUnload();

	if(typeParam == "A" || typeParam == ""){

		$("#bdgtList").jqGrid(v_gOptA);

	}else if(typeParam == "B"){

		$("#bdgtList").jqGrid(v_gOptB);


	}else if(typeParam == "C"){

		$("#bdgtList").jqGrid(v_gOptC);


	}else if(typeParam == "I"){

		$("#bdgtList").jqGrid(v_gOptI);


	}else if(typeParam == "O"){

		$("#bdgtList").jqGrid(v_gOptO);


	}else if(typeParam == "P"){

		$("#bdgtList").jqGrid(v_gOptP);


	}else if(typeParam == "R"){

		$("#bdgtList").jqGrid(v_gOptR);

	}else if(typeParam == "Q" || typeParam == "Q1" || typeParam == "Q2" || typeParam == "Q3" || typeParam == "Q4" || typeParam == "Q5"){

		$("#bdgtList").jqGrid(v_gOptQ);

	}else if(typeParam == "K"){

		$("#bdgtList").jqGrid(v_gOptK);

	}else if(typeParam == "S"){

		$("#bdgtList").jqGrid(v_gOptS);

	}else{

		gf_AlertMsg("올바르지 않은 경비구분 코드 입니다.");

		self.close();

	}

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

	  	case "SELECT_BDGT_LIST" :

	  		ds_BdgtList = new DataSet();

	  		//Grid Binding
	  		ds_BdgtList.bind($("#bdgtList"));

	  		if(!gf_IsNull(resultData.output1[0].Poerror)){
	  			gf_AlertMsg(resultData.output1[0].Poerror);
	  			return;
	  		}

	  		if(resultData.output1[0].SapPoIono == undefined){
	  			gf_AlertMsg("조회된 결과가 없습니다.")
	  			return;
	  		}

	  		// 결과가 여러개 일 경우 배열로 전달
	  		if(resultData.output1[0].SapPoIono.constructor == Array){
//	  			alert("배열임");
	  			ds_BdgtList.setAllData(resultData.output1[0].SapPoIono);
	  		}

	  		// 결과가 하나일 경우 오브젝트로 전달
	  		if(resultData.output1[0].SapPoIono.constructor == Object){
//	  			alert("오브젝트임");
	  			ds_BdgtList.add(resultData.output1[0].SapPoIono);
	  		}

//	  		var rcCount = "["+ds_InnerTripList.size()+"]";
//	  		$("#innerTripListCnt").text(rcCount); 	//문서함 목록


	  		break;

	  	case "SELECT_PRCTR_LIST" :

	  		ds_BdgtList = new DataSet();

	  		//Grid Binding
	  		ds_BdgtList.bind($("#bdgtList"));

//	  		if(!gf_IsNull(resultData.output1[0].RtnCd)){
//	  			gf_AlertMsg(resultData.output1[0].RtnCd);
//	  		}
//
//	  		// 결과가 여러개 일 경우 배열로 전달
//	  		if(resultData.output1[0].SapSoTab.constructor == Array){
////	  			alert("배열임");
//	  			ds_BdgtList.setAllData(resultData.output1[0].SapSoTab);
//	  		}
//
//	  		// 결과가 하나일 경우 오브젝트로 전달
//	  		if(resultData.output1[0].SapSoTab.constructor == Object){
////	  			alert("오브젝트임");
//	  			ds_BdgtList.add(resultData.output1[0].SapSoTab);
//	  		}

	  		if(resultData.prctrList == null){
	  			gf_AlertMsg("조회된 결과가 없습니다.");
	  		}

	  		// 현장경비 쿼리로 조회로 변경
	  		ds_BdgtList.setAllData(resultData.prctrList);

	  		break;
	  	case "SELECT_PRCTR_LIST1" :

	  		ds_BdgtList = new DataSet();

	  		//Grid Binding
	  		ds_BdgtList.bind($("#bdgtList"));

	  		if(resultData.prctrList1 == null){
	  			gf_AlertMsg("조회된 결과가 없습니다.");
	  		}

	  		// PJ경비 쿼리로 조회로 변경
	  		ds_BdgtList.setAllData(resultData.prctrList1);

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


/**
* @class 작성함의 양식목록 더블클릭시 기안작성화면 오픈
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function grd_List_oncelldbclick(grid, rowid, iRow, iCol)
{
	f_Confirm();

}


function f_Search(){

	// 파라미터 초기화
	var Picgubun = $("#bdgtType").val();
	var Piexecteam = "";
	var Piteamnm = "";
	var Piexenm = "";
	var Piiokey = "";
	var Piaufnr = "";
	var Measure = "";
	var Bukrs = "";
	var Prctrnm = "";

	// bdgtType 에 따라 다른 파라미터를 전송한다.
	var reqType = $("#bdgtType").val();

	if(reqType == "A"){

		Piexenm = $("#APiexenm").val();
		Piteamnm = "";

	}else if(reqType == "B"){

		Piexenm = $("#BPiexenm").val();
		Piteamnm = "";

	}else if(reqType == "C"){

		Piexenm = "";
		Piteamnm = $("#CPiteamnm").val();

	}else if(reqType == "I"){

		Piexenm = $("#IPiexenm").val();
		Piteamnm = $("#IPiteamnm").val();

	}else if(reqType == "O"){

		Piexecteam = $("#OPiexecteam").val();
		Piiokey = $("#OPiiokey").val();

	}else if(reqType == "P"){

		Piexecteam = $("#PPiexecteam").val();
		Piiokey = $("#PPiiokey").val();

	}else if(reqType == "R"){

		Piexecteam = $("#RPiexecteam").val();
		Piiokey = $("#RPiiokey").val();

	}else if(reqType == "Q"){

		Bukrs = '1000';
		Prctrnm =  $("#QPrctrnm").val();

	}else if(reqType == "Q1"){

			Bukrs = '1000';
			Prctrnm =  $("#Q1Prctrnm").val();

	}else if(reqType == "Q2"){

		Bukrs = '1000';
		Prctrnm =  $("#Q2Prctrnm").val();

	}else if(reqType == "Q3"){

		Bukrs = '1000';
		Prctrnm =  $("#Q3Prctrnm").val();

	}else if(reqType == "Q4"){

		Bukrs = '1000';
		Prctrnm =  $("#Q4Prctrnm").val();

	}else if(reqType == "Q5"){

		Bukrs = '1000';
		Prctrnm =  $("#Q5Prctrnm").val();

	}else if(reqType == "K"){

		Piexecteam = $("#KPiexecteam").val();
		Piiokey = $("#KPiiokey").val();

	}else if(reqType == "S"){

		Piexecteam = $("#SPiexecteam").val();
		Piiokey = $("#SPiiokey").val();

	}else{
		gf_AlertMsg("올바르지 않은 경비구분 코드 입니다.");
		return;

	}

	if(Piexecteam.length > 5){
		gf_AlertMsg("PJ코드는 5자리 이상일 수 없습니다.");
		return;
	}

	//2020.01.13 (전체검색을 못하도록 검색조건 추가..근데 순서상 여기 로직을 안타네. why..
	if(	Piexenm.length == 0 && Piexecteam.length == 0 &&
			Piteamnm.length == 0 && Piiokey.length == 0 && Prctrnm.length == 0){
		gf_AlertMsg("검색조건을 입력해주세요");
		return;
	}

	// ZN_BDGT_NO
	if(reqType == "A" || reqType == "B" || reqType == "C" || reqType == "O" || reqType == "P" || reqType == "R" || reqType == "K" || reqType == "S" || reqType == "I"){

		var searchParams = {
				Picgubun   : Picgubun,
				Piexecteam : Piexecteam,
				Piteamnm   : Piteamnm,
				Piexenm    : Piexenm,
				Piiokey    : Piiokey,
				Piaufnr    : Piaufnr
		};

		gf_Transaction("SELECT_BDGT_LIST", "/trip/eai/getBdgtNoList.xpl", searchParams, {}, "f_Callback", true);

	}else if(reqType == "Q"){

		// 현장경비의 경우 쿼리 조회로 변경
		var searchParams = {
				Prctrnm : Prctrnm
		};

		gf_Transaction("SELECT_PRCTR_LIST", "/trip/bdgt/getPrctrList.xpl", searchParams, {}, "f_Callback", true);

	}else if(reqType == "Q1" || reqType == "Q2" || reqType == "Q3" || reqType == "Q4" || reqType == "Q5"){

		// PJ경비의 경우 쿼리 조회로 변경
		var searchParams = {
				Prctrnm : Prctrnm
		};

		gf_Transaction("SELECT_PRCTR_LIST1", "/trip/bdgt/getPrctrList1.xpl", searchParams, {}, "f_Callback", true);

	}else{
		alert("경비구분이 선택되지 않았습니다.");
		return;
	}

}