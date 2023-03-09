var ds_TechDataList = new DataSet();


// 문서상태  D16 : 임시저장, D17 : 등록요청  , D18 : 등록완료

var params = {};

var v_cls = "";
var	v_regNo   = "";
var	v_subject = "";
var	v_sumry = "";
var	v_keywd = "";
var	v_issueTeam = "";
var	v_issueEr = "";
var	v_issueMgr = "";
var	v_issueDd = "";
var	v_edtPerchrg = "";
var	v_distrCls = "";
var	v_prfrd   = "";
var	v_issueCpys = "";
var	v_docSts  = "";
var v_fileAtchId = "";
var v_imgAtchFileId = "";
var v_issueRegNo = "";
var v_admin   ="";
var v_appnDd  = "";

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

	if(gv_AuthList.auth[0].code.match("RO_CMAS_CT_ADM") == "RO_CMAS_CT_ADM"){
		v_admin = 'Y';
	}else{
		v_admin = 'N';
	}
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
		colNames:[gf_FindLang('서지등록번호'),gf_FindLang('신청일'),gf_FindLang('표제'), gf_FindLang('발행팀')
		          ,gf_FindLang('편집담당자'),gf_FindLang('상태')
		          ],
		   	colModel:[
		   	          {name:'issueRegNo',index:'issueRegNo', width:50,align: "center"},
		   	          {name:'fstRegDt',index:'fstRegDt', width:50,align: "center"},
		   	          {name:'title',index:'title', width:150,align: "left"},
		   	          {name:'issueTeam',index:'issueTeam', width:50,align: "center"},
		   	          {name:'edtPerchrg',index:'edtPerchrg', width:50,align: "center"},
		  	          {name:'disdocsts',index:'disdocsts', width:50,align: "center",
		   	        	  formatter:function(val, gOpt, row){
		   	        		  	if(row.disdocsts == "D16"){
		   	        		  		rVal = "임시저장";
		   	        		  	}else if(row.disdocsts == "D17"){
		   	        		  		rVal = "등록신청";
		   	        		  	}else if(row.disdocsts == "D18"){
		   	        		  		rVal = "등록완료";
		   	        		  	}
		   	        		  	return rVal;
		   	        	  }
		   	          }
		   	],

	   	autowidth:true,
	   	height:380,
	   	sortname: 'issueRegNo',
	    viewrecords: true,
	    sortorder: 'desc',
	    pager : '#techDataListPager',
		rowNum :15,
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

    // 간행물 등록신청
	$("#publicationDataDraft").click(function(){
		var newWin = gf_PostOpen("/tech/publication/publicationDataDraft.jsp", null, "toolbar=no,scrollbars=yes,width=1024,height=700", null, true, true, "cf_RefreshList");
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
//	if(gv_AuthList.auth[0].code.match("RO_CMAS_CT_DVD") == "RO_CMAS_CT_PUB")
//		$("#techDataDraft").show();
}

function cf_RefreshList(){
	cf_RetrieveTechDatList();
}

function cf_RetrieveTechDatList(){

	v_subject = $("#subject").val();
	v_docSts = $("select[name='docSts'] option:selected").val();
	if(v_docSts == "ALL"){
		v_docSts = "";
	}

	v_issueRegNo = $("#issueRegNo").val();

	if(v_admin == 'Y'){
		v_userId = '';
	}else{
		v_userId = gv_userId;
	}

	//해당 업무구분과 업무분류에 해당되는 양식명을 가져옴
	var params = {
			subject : v_subject,		 //제목
			docSts  : v_docSts,			 //결재상태
			//userId  : v_userId,          //열람권한
			issueRegNo : v_issueRegNo    //등록번호
	};

	gf_Transaction("SELECT_TECH_DAT_LIST", "/tech/publication/retrieveTechPubDatList1.xpl", params, {}, "f_Callback", true);

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
	v_keywd      = rowData.get("keywd");
	v_issueTeam  = rowData.get("issueTeam");
	v_issueEr    = rowData.get("issueEr");
	v_issueMgr   = rowData.get("issueMgr");
	v_issueDd    = rowData.get("issueDd");
	v_edtPerchrg = rowData.get("edtPerchrg");
	v_distrCls   = rowData.get("distrCls");
	v_prfrd      = rowData.get("prfrd");
	v_issueCpys  = rowData.get("issueCpys");
	v_docSts     = rowData.get("docSts");
	v_issueRegNo = rowData.get("issueRegNo");
	v_fileAtchId = rowData.get("atchFileId");
	v_imgAtchFileId = rowData.get("imgAtchFileId");

	var datas = {
			cls        : v_cls,
			regNo      : v_regNo,
			subject    : v_subject,
			sumry      : v_sumry,
			keywd      : v_keywd,
			issueTeam  : v_issueTeam,
			issueEr    : v_issueEr,
			issueMgr   : v_issueMgr,
			issueDd    : v_issueDd,
			edtPerchrg : v_edtPerchrg,
			distrCls   : v_distrCls,
			prfrd      : v_prfrd,
			issueCpys  : v_issueCpys,
			docSts     : v_docSts,
			issueRegNo : v_issueRegNo,
			fileAtchId : v_fileAtchId,
			imgAtchFileId : v_imgAtchFileId
	};

	var newWin = gf_PostOpen("/tech/publication/publicationDataViewDoc.jsp", null, "toolbar=no,scrollbars=yes,width=1024,height=700", datas, true, true, "cf_RefreshList");

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
