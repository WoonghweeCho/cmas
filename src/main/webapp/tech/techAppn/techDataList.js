var ds_TechDataList = new DataSet();

var v_regNo   = "";
var v_docSts  = "";
var v_subject = "";
var v_issuePl = "";
var v_issueYr = "";
var v_ath     = "";
var v_cls     = "";
var v_interClscd = "";
var v_cont    = "";

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
	   	colNames:[gf_FindLang('제목'), gf_FindLang('저자'),
	   	       gf_FindLang('발행년도'),gf_FindLang('발행처'), gf_FindLang('등록번호'), gf_FindLang('상태')],
	   	colModel:[
	   	          {name:'title',index:'title', width:180,align: "left"},
	   	          {name:'ath',index:'ath', width:100,align: "left"},
	   	          {name:'issueYr',index:'issueYr', width:40,align: "center"},
	   	          {name:'issuePl',index:'issuePl', width:90,align: "center"},
	   	          {name:'disregno',index:'disregno', width:50,align: "center"},
	   	          {name:'docSts',index:'docSts', width:50,align: "center",
	   	        	  formatter:function(val, gOpt, row){
	   	        		  var rVal = "대출가능";
	   	        		  	if(row.docSts == "D31"){
	   	        		  		rVal = "대출신청";
	   	        		  	}else if(row.docSts == "D50"){
	   	        		  		rVal = "대출중";
	   	        		  	}else if(row.docSts == "D16"){
	   	        		  		rVal = "임시저장";
	   	        		  	}
	   	        		  	return rVal;
	   	        	  }
	   	          },
	   	],

	   	autowidth:true,
	   	height:380,
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
		ds_TechDataList.clear();
		cf_RetrieveTechDatList();
	});

    // 도서/간행물 자료 작성
	$("#techDataDraft").click(function(){

//		var subject = $("#subject").val();
		var params = {

		};
		var newWin = gf_PostOpen("/tech/techAppn/techDataDraft.jsp", null,	"toolbar=no,scrollbars=yes,width=1024,height=650", params, true, true, "cf_RefreshList");

	});

	// DVD자료 작성
	$("#techDvdDataDraft").click(function(){
		var params = {
		};
		var newWin = gf_PostOpen("/tech/techAppn/techDvdDataDraft.jsp", null,	"toolbar=no,scrollbars=yes,width=1024,height=770", params, true, true, "cf_RefreshList");
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
	//cf_RetrieveTechDatList();
}

function f_SetBtn(){
	if(gv_AuthList.auth[0].code.match("RO_CMAS_CT_ADM") == "RO_CMAS_CT_ADM" || gv_AuthList.auth[0].code.match("RO_CMAS_CT_BOOK") == "RO_CMAS_CT_BOOK")
		$("#techDataDraft").show();
}


function cf_RefreshList(){
	cf_RetrieveTechDatList();
}

function cf_RetrieveTechDatList(){

	v_subject = $("#subject").val();
	v_ath     = $("#ath").val();
	v_issueYr = $("#issueYr").val();
	v_issuePl = $("#issuePl").val();
	v_interClscd = $("#interClscd").val();
	v_docSts = $("select[name='docSts'] option:selected").val();
	if(v_docSts == "ALL"){
		v_docSts = "";
	}

	//해당 업무구분과 업무분류에 해당되는 양식명을 가져옴
	var params = {
			subject : v_subject, 			//제목
			ath     : v_ath,                //저자
			issueYr : v_issueYr,            //발행년도
			issuePl : v_issuePl,            //발행처
			interClscd : v_interClscd,      //udcCode
			docSts  : v_docSts 		    	//결재상태
	};

	gf_Transaction("SELECT_TECH_DAT_LIST", "/tech/techAppn/retrieveTechDatList.xpl", params, {}, "f_Callback", true);

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

	v_regNo   = rowData.get("regNo");
	v_docSts  = rowData.get("docSts");
	v_subject = rowData.get("title");
	v_issuePl = rowData.get("issuePl");
	v_issueYr = rowData.get("issueYr");
	v_ath     = rowData.get("ath");
	v_cls     = rowData.get("cls");
	v_interClscd = rowData.get("interClscd");
	v_cont    = rowData.get("cont");

	var datas = {
			regNo      : v_regNo,
			docSts     : v_docSts,
			subject    : v_subject,
			issuePl    : v_issuePl,
			issueYr    : v_issueYr,
			ath        : v_ath,
			cls        : v_cls,
			interClscd : v_interClscd,
			cont       : v_cont
	};

	//if(v_cls =="BK")
		var newWin = gf_PostOpen("/tech/techAppn/techDataViewDoc.jsp", null, "toolbar=no,scrollbars=yes,width=1024,height=650", datas, true, true, "cf_RefreshList");
	//else if(v_cls =="DV")
	//	var newWin = gf_PostOpen("/tech/techAppn/techDvdDataViewDoc.jsp", null, "toolbar=no,scrollbars=yes,width=1024,height=770", datas, true, true, "cf_RefreshList");
}
