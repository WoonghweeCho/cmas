var ds_TechDataList = new DataSet();

var v_regNo      = "";
var v_subject    = "";
var v_cls        = "";
var v_issueYr    = "";
var v_interClscd = "";
var v_osCls      = "";

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
	   	colNames:[gf_FindLang('구분'),gf_FindLang('규격번호'),gf_FindLang('연도'),gf_FindLang('신청'),gf_FindLang('등록번호')],
	   	colModel:[
	   	          {name:'cls',index:'cls', width:100,align: "left"},
	   	          {name:'title',index:'title', width:400,align: "left"},
	   	          {name:'issueYr',index:'issueYr', width:80,align: "center"},
	   	          {name:'appn',index:'appn', width:50,align: "center", hidden :"true",
			   			formatter:function(val, gOpt, row){
			   				var spanCss = {width : "100%", height: "100%", backgroundColor:"#D4F4FA"}; //#ffcccc
			   				var rVal = "";
			   				rVal = "신청";
		   					return $("<span>").append($("<div>").text(rVal).css(spanCss)).html();
		  	   			}
			   		},
			   	  {name:'disregno',index:'disregno', width:100,align: "center",hidden :"true"},
	   	],

	   	autowidth:true,
	   	height:400,
	   	sortname: 'title',
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

		v_osCls = $("select[name='osCls'] option:selected").val();
		v_subject = $("#subject").val().toUpperCase();


//		if(v_osCls =='BSI' && (v_subject =='BS'|| v_subject =='EN'|| v_subject =='ISO')){
//			alert('검색 시 BS/EN/ISO으로 시작하는 검색은 가능하지 않습니다.\n정확한 규격번호를 입력하거나 다른 검색어를 입력하세요.');
//			return;
//		}

		if(v_subject ==''){
			alert('검색 시 규격번호를 입력하십시오.');
			return;
		}else{
			ds_TechDataList.clear();
			cf_RetrieveTechDatList();
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
	//f_SetBtn();
	cf_RetrieveTechDatList();
}

function f_SetBtn(){

}


function cf_RefreshList(){
	cf_RetrieveTechDatList();
}

function cf_RetrieveTechDatList(){
	v_osCls = $("select[name='osCls'] option:selected").val();
	v_subject = $("#subject").val().toUpperCase();


	//해당 업무구분과 업무분류에 해당되는 양식명을 가져옴
	var params = {

			osCls : v_osCls,            //분류
			subject : v_subject			//제목
	};

	gf_Transaction("SELECT_TECH_DAT_LIST", "/tech/techAppn/retrieveTechOsDatList.xpl", params, {}, "f_Callback", true);

}

function cf_RefreshList(){
	cf_RetrieveTechDatList();
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
	  	case "SELECT_TECH_DAT_LIST" :
	  		ds_TechDataList.setAllData(resultData.ds_TechDataList);
			break;
	  	case "SELECT_ID_APPN_DTL_LIST" :
	  		ds_IdAppnDtlList.setAllData(resultData.ds_IdAppnDtlList);
			break;
	   	default :
	  		break;
	  }
}

function grd_List_onrowclick(rowid)
{
 	var rowData = $("#techDataList").data(rowid);

 	cf_RetrieveTechDataList(rowData);
}

function grd_List_oncelldbclick(grid, rowid, iRow, iCol)
{
	var rowData = $("#techDataList").data(rowid);

	var v_cls   = rowData.get("cls");
	var v_regNo = rowData.get("regNo");
	var v_subject = rowData.get("title");
	var v_issueYr = rowData.get("issueYr");

	var datas = {
			cls     : v_cls,
			regNo   : v_regNo,
			subject : v_subject,
			issueYr : v_issueYr
	};

		var newWin = gf_PostOpen("/tech/techAppn/techOsAppnDraft.jsp", null, "toolbar=no,scrollbars=yes,width=1024,height=500", datas, true, true, "cf_RefreshList");
}
