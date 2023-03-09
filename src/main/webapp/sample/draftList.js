var ds_DraftList = new DataSet();
var ds_DocSts = new DataSet();	//문서상태 검색

//var sgnsUrl = "http://localsgns.daewooenc.com:8080/sgns/jsp/sign/proxyDialogSSO.jsp";
//var callbackUrl = "process=http://localsgns.daewooenc.com:9090/cmas/sample/SignCallBack.xpl";
var sgnsUrl = "http://test.daewooenc.com/sgns/jsp/sign/proxyDialogSSO.jsp";
var callbackUrl = "process=http://test.daewooenc.com/cmas/sample/SignCallBack.xpl";

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
	ds_DocSts.setAllData(gv_DocStsCd);
}

/**
* @class Form Onload 시 컴포넌트 초기화
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetComponents()
{
	// 리스트 JQGrid
	var draftList = {
		datatype: "local",
	   	colNames:[gf_FindLang('signId'), gf_FindLang('docStsCd'), gf_FindLang('docStsNm'), gf_FindLang('fnlEditDt')],
	   	colModel:[
	   	          {name:'signId',index:'signId', width:100,align: "center"},
	   	          {name:'docStsCd',index:'docStsCd', width:100,align: "center"},
	   	          {name:'docStsNm',index:'docStsNm', width:100,align: "center",
		   	        	formatter:function(val, gOpt, row){
		   	        		ds_DocSts.filter(null);
		   	        		var result = gf_BindView(val, row, gOpt, {dataSet:ds_DocSts, curVal:"docStsCd", val: "code", text:"value"});
			        		return result;
			        	  }
		   	          	},
	   	          {name:'fnlEditDt',index:'fnlEditDt', width:100,align: "center"}
		],
	   	autowidth:true,
	   	height:550,
	   	sortname: 'signId',
	    viewrecords: true,
	    sortorder: 'desc',
	    pager : '#draftListPager',
		rowNum :25,
		rowList : [25, 50, 100],
	    ondblClickRow: function(rowid, iRow, iCol, boxid, e){
	    	grd_List_oncelldbclick(this, rowid, iRow, iCol);
	     }
	};

	$("#draftList").jqGrid(draftList);
	$("#draftList").jqGrid('navGrid','#draftListPager',{edit:false,add:false,del:false});

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
	ds_DraftList.bind($("#draftList"));
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
		ds_DraftList.clear();
		cf_RetrieveDraftList();
	});

	$("#new").click(function(){
		// http://localsgns.daewooenc.com:8080/sgns/jsp/sign/proxyDialogSSO.jsp?dutySysCd=SGNS&proxyDialogType=DRFT&programCode=SGNS010007&process=http://localsgns.daewooenc.com:9090/cmas/sample/SignCallBack.xpl
		var url = sgnsUrl + "?dutySysCd=SGNS&proxyDialogType=DRFT&programCode=SGNS010007&" + callbackUrl;
		window.open(url, "test","menubar=yes,location=yes,resizable=yes,scrollbars=yes,status=yes");
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
	cf_RetrieveDraftList();
}

function cf_RetrieveDraftList(){
	//해당 업무구분과 업무분류에 해당되는 양식명을 가져옴
	var params = {};
	gf_Transaction("SELECT_DRAFT_LIST", "/sample/retrieveDraftList.xpl", params, {}, "f_Callback", true);
}

function cf_RefreshList(){
	cf_RetrieveDraftList();
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
	  	case "SELECT_DRAFT_LIST" :
	  		ds_DraftList.setAllData(resultData.ds_DraftList);
			break;

	  	default :
	  		break;
	  }
}

function grd_List_oncelldbclick(grid, rowid, iRow, iCol)
{
	var rowData = $("#draftList").data(rowid);
	var docStsCd = rowData.get("docStsCd");
	var signId = rowData.get("signId");
	var url = "";

	if(iCol == 2 && docStsCd != "D07" && docStsCd != "D06"){	// docStsNm 필드 클릭시
		url = sgnsUrl + "?proxyDialogType=VIEW_SIGN" + "&signId=" + signId + "&sysCd=SGNS";
		window.open(url, "test","toolbar=no,scrollbars=no,width=920,height=550");
		return;
	}

	if(docStsCd == "D07" || docStsCd == "D06"){	// 결재요청취소 또는 삭제
		alert("저장된 결재문서가 없습니다.");
		return;
	} else if(docStsCd == "D16" || docStsCd == "D04" || docStsCd == "D05") {
		url = sgnsUrl + "?dutySysCd=SGNS&proxyDialogType=REDRFT&programCode=SGNS010007&" + callbackUrl + "&signId=" + signId;
	} else {
		alert("외부에서는 보안상\n임시저장/회수/반려 문서외에는 조회되면 안 됨.");
		return;
	}
	window.open(url, "test","toolbar=no,scrollbars=yes,width=1024,height=700");
}
