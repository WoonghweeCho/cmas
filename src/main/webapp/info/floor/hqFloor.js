var ds_FloorList = new DataSet();		//목록 DataSet

var v_flr     = "";
var	v_orgNm   = "";
var	v_dtlInfo = "";
var v_sts     = "";
var v_admin   = "";

/**
* @class 화면 로드 완료 시 필요한 초기 작업 수행.
*        1. 파라미터 초기화
*        2. 컴포넌트 초기화
*        3. Event Listener 초기화
*        4. 화면내 Form 객체 초기화
*        5. 다국어 처리
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
* @version 1.0
*/
function cf_InitParam()
{
	v_loclCd = gf_GetCookie("loclCd");		// 로케일 20140724
	if(gv_AuthList.auth[0].code.match("RO_CMAS_Fl_ADM") == "RO_CMAS_Fl_ADM"){
		v_admin = 'Y';
	}else{
		v_admin = 'N';
	}
}

/**
* @class Form Onload 시 컴포넌트 초기화
* @version 1.0
*/
function cf_SetComponents()
{
	//자료목록 목록 JQGrid
	var floorList = {
		datatype: "local",
		colNames:[gf_FindLang('층'),gf_FindLang('본부'),gf_FindLang('팀')],
		   	colModel:[
		   	          {name:'flr',index:'flr', width:40,align: "center", sortable : false, sorttype : "integer"},
		   	          {name:'orgNm',index:'orgNm', width:180,align: "left", sortable : false},
		   	          {name:'dtlInfo',index:'dtlInfo', width:820,sortable : false}
		   	],

	   	autowidth:true,
	   	height:550,
	   	sortname: 'flr',
	    viewrecords: true,
	    sortorder: 'desc',
	    pager : '#floorListPager',
		rowNum :50,
		rowList : [30, 50, 70],
	    onSelectRow : function(rowid, status, e){
	    	grd_List_onrowclick(rowid);
	     },
	    ondblClickRow: function(rowid, iRow, iCol, boxid, e){
	    	grd_List_oncelldbclick(this, rowid, iRow, iCol);
	     }
	};

	$("#floorList").jqGrid(floorList);
	$("#floorList").jqGrid('navGrid','#floorListPager',{edit:false,add:false,del:false});

	/**
	 * Container 크기에 맞춰 Windows Resizing 될 때 Box Grid의 사이즈를 조절한다.
	 */
	$(window).bind("resize", function(){
	}).trigger('resize');
}

/**
* @class Form Onload DataSet Binding 처리
* @version 1.0
*/
function cf_SetBinding()
{
	//양식목록 Grid Binding
	ds_FloorList.bind($("#floorList"));

}

/**
* @class Form Onload 시 Element/Component 인벤트 초기화
* @version 1.0
*/
function cf_SetEventListener()
{
	// 신청리스트를 조회한다.
	$("#search").click(function(){

		ds_FloorList.clear();
		cf_RetrieveFloorList();

	});

	 //작성
	$("#btnDraft").click(function(){
		var newWin = gf_PostOpen("/info/floor/hqFloorDraft.jsp", null, "toolbar=no,scrollbars=yes,width=850,height=450", null, true, true, "f_Callback");
	});

}

/**
* @class Form Onload 시 객체 초기 값 설정
* @version 1.0
*/
function cf_InitForm()
{
	/**
	 * 양식 목록 조회
	 */
	f_SetBtn();
	cf_RetrieveFloorList();
}

function f_SetBtn(){

	if(v_admin == "Y") {
			$("#btnDraft").show();
	}
}

function cf_RefreshList(){
	cf_RetrieveFloorList();
}

function cf_RetrieveFloorList(){

	var datas = {
	};

	gf_Transaction("SELECT_FLOOR_LIST", "/info/floorList.xpl", datas, {}, "f_Callback", true);

}

function grd_List_onrowclick(rowid)
{
 	var rowData = $("#floorList").data(rowid);

 	cf_RetrieveFloorList(rowData);
}

function grd_List_oncelldbclick(grid, rowid, iRow, iCol)
{
	var rowData = $("#floorList").data(rowid);

		v_flr     = rowData.get("flr");
		v_orgNm   = rowData.get("orgNm");
		v_dtlInfo = rowData.get("dtlInfo");
		v_sts     = "편집";

	var datas = {
			flr     : v_flr,
			orgNm   : v_orgNm,
			dtlInfo : v_dtlInfo,
			sts     : v_sts

	};

	var newWin = gf_PostOpen("/info/floor/hqFloorDraft.jsp", null, "toolbar=no,scrollbars=yes,width=850,height=450", datas, true, true, "cf_RefreshList");

}

/**
*
* @class Transaction 처리 후 수행되는 Callback 함수
* @version 1.0
*/
function f_Callback(strSvcId, obj, resultData){

	  // transaction의 정상 처리 여부를 판단한다.
	  if (!gf_ChkTransaction(strSvcId, resultData, true )) {
		  return;
	  }

	  switch(strSvcId) {
	  	case "SELECT_FLOOR_LIST" :
	  		ds_FloorList.setAllData(resultData.output);
			break;
	  	default :
	  		break;
	  }
}


