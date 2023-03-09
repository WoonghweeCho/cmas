var ds_TechAppnList = new DataSet();

var v_regNo   = "";
var v_docSts  = "";
var v_subject = "";
var v_appnDd  = "";
var v_rentDd  = "";
var v_rtrnDd  = "";
var v_docNo   = "";
var v_telno   = "";
var v_cls     = "";
var v_orgCd   = "";
var v_orgNm   = "";
var v_userId  = "";
var v_userNm  = "";
var v_grd     = "";
var v_gubun   = "";


//문서상태 : docSts
//D16 : 임시저장, D17 : 취소 ,D31 : 대출신청, D50 : 대출중, D51 : 반납

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
	var techAppnList = {
		datatype: "local",
	   	colNames:[gf_FindLang('신청일'), gf_FindLang('신청자사번'), gf_FindLang('신청자명'),
	   	       gf_FindLang('대여일'),gf_FindLang('반납일'), gf_FindLang('제목'), gf_FindLang('소속'), gf_FindLang('구분'), gf_FindLang('상태')],
	   	colModel:[
	   	          {name:'appnDd',index:'appnDd', width:50,align: "center"},
	   	          {name:'userId',index:'userId', width:50,align: "center"},
	   	          {name:'userNm',index:'userNm', width:40,align: "center"},
	   	          {name:'rentDd',index:'rentDd', width:60,align: "center"},
	   	          {name:'rtrnDd',index:'rtrnDd', width:60,align: "center"},
	   	          {name:'title',index:'title', width:150,align: "left"},
	   	          {name:'orgNm',index:'orgNm', width:70,align: "center"},
	   	          {name:'cls',index:'cls', width:60,align: "center",
		   	           formatter:function(val, gOpt, row){
		   	        	   	var rVal = row.cls;
		   	        	   		if(rVal == "BK"){
		   	        	   			rVal = "구매도서";

		   	        	   		}else if(rVal == "DV"){
		   	        	   			rVal = "DVD";
		   	        	   		}
		   	        	   		return rVal;
		   	           	}
		   	      },

	   	          {name:'docSts',index:'docSts', width:50,align: "center",
	   	        	  formatter:function(val, gOpt, row){
	   	        		  var rVal = "";
	   	        		  	if(row.docSts == "D50"){
	   	        		  		rVal = "대출중";
	   	        		  	}else if(row.docSts == "D51"){
	   	        		  		rVal = "반납";
	   	        		  	}else if(row.docSts == "D31"){
	   	        		  		rVal = "대출신청";
	   	        		  	}else if(row.docSts == "D16"){
	   	        		  		rVal = "임시저장";
	   	        		  	}else if(row.docSts == "D17"){
	   	        		  		rVal = "취소";
	   	        		  	}
	   	        		  	return rVal;
	   	        	  }
	   	          },
	   	],

	   	autowidth:true,
	   	height:380,
	   	sortname: 'appnDd',
	    viewrecords: true,
	    sortorder: 'desc',
	    pager : '#techAppnListPager',
		rowNum :20,
		rowList : [30, 50, 70],
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

	var v_cls = gv_initMenuCd; //$("select[name='cls'] option:selected").val();
	var v_userNm = $("#userNm").val();
	var v_subject = $("#subject").val();
	var v_docSts = $("select[name='docSts'] option:selected").val();
	if(v_docSts == "ALL"){
		v_docSts = "";
	}

	if (v_cls == "DV"){
		$("#dvNotice").show();
	}

	//해당 업무구분과 업무분류에 해당되는 양식명을 가져옴
	var params = {
			cls : v_cls,				//구분 코드
			userNm : v_userNm,          //신청자
			subject : v_subject,		//제목
			docSts : v_docSts			//결재상태
	};

	gf_Transaction("SELECT_TECH_APPN_LIST", "/tech/techAppn/retrieveTechAppnList.xpl", params, {}, "f_Callback", true);

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
	v_rentDd  = rowData.get("rentDd");
	v_rtrnDd  = rowData.get("rtrnDd");
	v_docNo   = rowData.get("docNo");
	v_telno   = rowData.get("telno");
	v_cls     = rowData.get("cls");
	v_orgCd   = rowData.get("orgCd");
	v_orgNm   = rowData.get("orgNm");
	v_userId  = rowData.get("userId");
	v_userNm  = rowData.get("userNm");
	v_grd     = rowData.get("grd");

	var datas = {
			regNo   : v_regNo,
			docSts  : v_docSts,
			subject : v_subject,
			appnDd  : v_appnDd,
			rentDd  : v_rentDd,
			rtrnDd  : v_rtrnDd,
			docNo   : v_docNo,
			telno   : v_telno,
			cls     : v_cls,
			orgCd   : v_orgCd,
	        orgNm   : v_orgNm,
	        userId  : v_userId,
	        userNm  : v_userNm,
	        grd     : v_grd
	};

	var newWin = gf_PostOpen("/tech/techAppn/techAppnViewDoc.jsp", null, "toolbar=no,scrollbars=yes,width=1020,height=600", datas, true, true, "cf_RefreshList");

}
