var ds_TechDataList = new DataSet();

var v_cls     = "";
var	v_regNo   = "";
var	v_subject = "";
var	v_sumry   = "";
//var	v_docSts  = "";
var v_userId  = "";
var v_userNm  = "";
var v_fstRegDt  = "";
var v_fileAtchId = "";

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
}

/**
* @class Form Onload 시 컴포넌트 초기화
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetComponents()
{
	//자료목록 목록 JQGrid
	var techDataList = {
		datatype: "local",
		colNames:[gf_FindLang('작성일'),gf_FindLang('제목'),gf_FindLang('작성자')],
		   	colModel:[
		   	          {name:'fstRegDt',index:'fstRegDt', width:50,align: "center"},
		   	          {name:'title',index:'title', width:200,align: "left"},
		   	          {name:'userNm',index:'userNm', width:50,align: "center"}
		   	],

	   	autowidth:true,
	   	height:380,
	   	sortname: 'fstRegDt',
	    viewrecords: true,
	    sortorder: 'desc',
	    pager : '#techDataListPager',
		rowNum :30,
		rowList : [30, 50, 70],
	    onSelectRow : function(rowid, status, e){
	    	grd_List_onrowclick(rowid);
	     },
	    ondblClickRow: function(rowid, iRow, iCol, boxid, e){
	    	grd_List_oncelldbclick(this, rowid, iRow, iCol);
	     }
	};

	$("#techDataList").jqGrid(techDataList);
	$("#techDataList").jqGrid('navGrid','#techDataListPager',{edit:false,add:false,del:false});

	/**
	 * Container 크기에 맞춰 Windows Resizing 될 때 Box Grid의 사이즈를 조절한다.
	 */
	$(window).bind("resize", function(){
	}).trigger('resize');
}

/**
* @class Form Onload DataSet Binding 처리
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetBinding()
{
	//양식목록 Grid Binding
	ds_TechDataList.bind($("#techDataList"));

}

/**
* @class Form Onload 시 Element/Component 인벤트 초기화
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetEventListener()
{
	// 신청리스트를 조회한다.
	$("#search").click(function(){

		ds_TechDataList.clear();
		cf_RetrieveTechDatList();

	});


    // 등록(작성)
	$("#techNoticeDraft").click(function(){
		var newWin = gf_PostOpen("/tech/techAppn/techNoticeDraft.jsp", null, "toolbar=no,scrollbars=yes,width=1024,height=700", null, true, true, "cf_RefreshList");
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
	f_SetBtn();
	cf_RetrieveTechDatList();
}

function f_SetBtn(){

	if(gv_AuthList.auth[0].code.match("RO_CMAS_CT_ADM") == "RO_CMAS_CT_ADM"
		|| gv_AuthList.auth[0].code.match("RO_CMAS_CT_BOOK") == "RO_CMAS_CT_BOOK") {
			$("#techNoticeDraft").show();
	}
}

function cf_RefreshList(){
	cf_RetrieveTechDatList();
}

function cf_RetrieveTechDatList(){

	v_subject = $("#subject").val();
	v_userNm  = $("#userNm").val();

	//해당 업무구분과 업무분류에 해당되는 양식명을 가져옴
	var params = {
			subject : v_subject,	//제목
			userNm  : v_userNm      //작성자
	};


	gf_Transaction("SELECT_TECH_DAT_LIST", "/tech/techAppn/retrieveTechNoticeList.xpl", params, {}, "f_Callback", true);

}

function cf_RefreshList(){
	cf_RetrieveTechDatList();
}

function grd_List_onrowclick(rowid)
{
 	var rowData = $("#techDataList").data(rowid);

 	cf_RetrieveTechDataList(rowData);
}

function grd_List_oncelldbclick(grid, rowid, iRow, iCol)
{
	var rowData = $("#techDataList").data(rowid);

	v_cls        = rowData.get("cls");
	v_regNo      = rowData.get("regNo");
	v_subject    = rowData.get("title");
	v_sumry      = rowData.get("sumry");
	v_userId     = rowData.get("userId");
	v_userNm     = rowData.get("userNm");
	v_fstRegDt   = rowData.get("fstRegDt");
	//v_docSts     = rowData.get("docSts");
	v_fileAtchId = rowData.get("atchFileId");

	var datas = {
			cls        : v_cls,
			regNo      : v_regNo,
			subject    : v_subject,
			sumry      : v_sumry,
			userId     : v_userId,
			userNm     : v_userNm,
			fstRegDt   : v_fstRegDt,
			//docSts   : v_docSts,
			fileAtchId : v_fileAtchId

	};

	var newWin = gf_PostOpen("/tech/techAppn/techNoticeViewDoc.jsp", null, "toolbar=no,scrollbars=yes,width=1024,height=700", datas, true, true, "cf_RefreshList");

}

/**
*
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
	  	case "SELECT_TECH_DAT_LIST" :
	  		ds_TechDataList.setAllData(resultData.ds_TechDataList);
	  		v_fileAtchId = ds_TechDataList.get(0, "atchFileId");

			break;
	   	default :
	  		break;
	  }
}


