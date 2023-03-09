var ds_TechAppnList = new DataSet();

var v_regNo   = "";
var v_issueRegNo = "";
var v_docSts  = "";
var v_subject = "";
var v_appnDd  = "";
var v_docNo   = "";
var v_cls     = "";
var v_orgCd   = "";
var v_orgNm   = "";
var v_userId  = "";
var v_userNm  = "";
var v_grd     = "";
var v_atchFileId = "";
var v_admin ="";
var v_fnlEditDt = "";


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
	var techAppnList = {
		datatype: "local",
	   	colNames:[gf_FindLang('신청일'), gf_FindLang('신청자사번'), gf_FindLang('신청자명'), gf_FindLang('제목'), gf_FindLang('소속'), gf_FindLang('상태')],
	   	colModel:[
	   	          {name:'appnDd',index:'appnDd', width:50,align: "center"},
	   	          {name:'userId',index:'userId', width:50,align: "center"},
	   	          {name:'userNm',index:'userNm', width:40,align: "center"},
	   	          {name:'title',index:'title', width:150,align: "center"},
	   	          {name:'orgNm',index:'orgNm', width:70,align: "center"},
	   	          {name:'docSts',index:'docSts', width:50,align: "center",
	   	        	  formatter:function(val, gOpt, row){
	   	        		  var rVal = "";
	   	        		  	if(row.docSts == "D51"){
	   	        		  		rVal = "처리완료";
	   	        		  	}else if(row.docSts == "D31"){
	   	        		  		rVal = "열람신청";
	   	        		  	}else if(row.docSts == "D16"){
	   	        		  		rVal = "임시저장";
	   	        		  	}
	   	        		  	return rVal;
	   	        	  }
	   	          },
	   	],

	   	autowidth:true,
	   	height:315,
	   	sortname: 'title',
	    viewrecords: true,
	    sortorder: 'desc',
	    pager : '#techAppnListPager',
		rowNum :15,
		rowList : [15, 30, 60],
	    onSelectRow : function(rowid, status, e){
	    	grd_List_onrowclick(rowid);
	     },
	    ondblClickRow: function(rowid, iRow, iCol, boxid, e){
	    	grd_List_oncelldbclick(this, rowid, iRow, iCol);
	     }
	};

	$("#techAppnList").jqGrid(techAppnList);
	$("#techAppnList").jqGrid('navGrid','#techAppnListPager',{edit:false,add:false,del:false});

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
	ds_TechAppnList.bind($("#techAppnList"));

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
		ds_TechAppnList.clear();
		cf_RetrieveTechAppnList();
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
	cf_RetrieveTechAppnList();
}

function cf_RetrieveTechAppnList(){

	var v_cls = $("select[name='cls'] option:selected").val();
	if(v_cls == "ALL"){
		v_cls = "";
	}

	var v_userNm = $("#userNm").val();
	var v_subject = $("#subject").val();
	var v_docSts = $("select[name='docSts'] option:selected").val();
	if(v_docSts == "ALL"){
		v_docSts = "";
	}

	if(v_admin == 'Y'){
		v_userId = '';
	}else{
		v_userId = gv_userId;
	}

	//해당 업무구분과 업무분류에 해당되는 양식명을 가져옴
	var params = {
			cls     : v_cls,			//구분 코드
			userNm  : v_userNm,         //신청자
			subject : v_subject,		//제목
			docSts  : v_docSts,			//결재상태
			userId  : v_userId
	};

	gf_Transaction("SELECT_TECH_APPN_LIST", "/tech/publication/retrieveTechPubAppnList.xpl", params, {}, "f_Callback", true);

}

function cf_RefreshList(){
	cf_RetrieveTechAppnList();
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
	  	case "SELECT_TECH_APPN_LIST" :
	  		ds_TechAppnList.setAllData(resultData.ds_TechAppnList);
			break;
	  default :
	  		break;
	  }
}

function grd_List_onrowclick(rowid)
{
 	var rowData = $("#techAppnList").data(rowid);

 	cf_RetrieveTechAppnList(rowData);
}

function grd_List_oncelldbclick(grid, rowid, iRow, iCol)
{
	var rowData = $("#techAppnList").data(rowid);

	v_regNo   = rowData.get("regNo");
	v_docSts  = rowData.get("docSts");
	v_subject = rowData.get("title");
	v_appnDd  = rowData.get("appnDd");
	v_docNo   = rowData.get("docNo");
	v_telno   = rowData.get("telno");
	v_cls     = rowData.get("cls");
	v_orgCd   = rowData.get("orgCd");
	v_orgNm   = rowData.get("orgNm");
	v_userId  = rowData.get("userId");
	v_userNm  = rowData.get("userNm");
	v_grd     = rowData.get("grd");
	v_atchFileId = rowData.get("atchFileId");
	v_issueRegNo = rowData.get("issueRegNo");
	v_fnlEditDt  = rowData.get("fnlEditDt");

	var datas = {
			regNo   : v_regNo,
			docSts  : v_docSts,
			subject : v_subject,
			appnDd  : v_appnDd,
			docNo   : v_docNo,
			telno   : v_telno,
			cls     : v_cls,
			orgCd   : v_orgCd,
	        orgNm   : v_orgNm,
	        userId  : v_userId,
	        userNm  : v_userNm,
	        grd     : v_grd,
	        atchFileId : v_atchFileId,
	        issueRegNo : v_issueRegNo,
			fnlEditDt  : v_fnlEditDt
	};
		var newWin = gf_PostOpen("/tech/publication/pubAppnViewDoc.jsp", null, "toolbar=no,scrollbars=yes,width=1020,height=600", datas, true, true, "cf_RefreshList");

}
