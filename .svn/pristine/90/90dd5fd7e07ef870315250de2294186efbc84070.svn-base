var ds_TechDataList = new DataSet();


//var ds_Cls = new DataSet(); 	  //구분 검색
//var ds_Subject = new DataSet();   //제목 검색

//var ds_Auth = new DataSet();


var	v_regNo   = "";
var	v_subject = "";
var	v_actorNm = "";
var	v_spv = "";
var	v_playTime = "";
var	v_dbng = "";
var	v_sbtl = "";
var	v_dvdCls = "";
var	v_cls = "";
var	v_mkco = "";
var	v_recTp = "";
var	v_scrn = "";
var	v_cont = "";
var	v_docSts = "";
var v_disregno = "";

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
		colNames:[gf_FindLang('타이틀'), gf_FindLang('주연배우'),gf_FindLang('분류'),
		   	       gf_FindLang('등록번호'),gf_FindLang('상태'),gf_FindLang('hh')],
		   	colModel:[
		   	          {name:'title',index:'title', width:150,align: "left"},
		   	          {name:'actorNm',index:'actorNm', width:100,align: "left"},
		   	          {name:'dvdCls_disp',index:'dvdCls', width:70,align: "center"
		   	        	  ,
			   	        	formatter:function(val, gOpt, row){
			   	        		  var rVal = "기타";
			   	        		  	if(row.dvdCls == "D"){
			   	        		  		rVal = "드라마/코믹";
			   	        		  	}else if(row.dvdCls == "A"){
			   	        		  		rVal = "액션/SF";
			   	        		  	}else if(row.dvdCls == "H"){
			   	        		  		rVal = "공포/스릴";
		        		  			}else if(row.dvdCls == "C"){
			   	        		  		rVal = "어린이/가족/만화";
		   	        		  		}else if(row.dvdCls == "P"){
			   	        		  		rVal = "Playstation";
		   	        		  		}else{
			   	        		  		rVal = "기타";
		   	        		  		}
			   	        		  	return rVal;
			   	        	  }
		   	          },
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
		   	       {name:'dvdCls',index:'dvdCls', width:70,align: "center", hidden :"true"},
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

		//var subject = $("#subject").val();
		var params = {
				//subject : subject
		};
		var newWin = gf_PostOpen("/tech/techAppn/techDataDraft.jsp", null,	"toolbar=no,scrollbars=yes,width=1024,height=650", params, true, true, "cf_RefreshList");
	});

	// DVD자료 작성
	$("#techDvdDataDraft").click(function(){
		var params = {
		};
		var newWin = gf_PostOpen("/tech/techAppn/techDvdDataDraft.jsp", null,	"toolbar=no,scrollbars=yes,width=1024,height=700", params, true, true, "cf_RefreshList");
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
	if(gv_AuthList.auth[0].code.match("RO_CMAS_CT_DVD") == "RO_CMAS_CT_DVD")
		$("#techDvdDataDraft").show();
}

function cf_RefreshList(){
	cf_RetrieveTechDatList();
}

function cf_RetrieveTechDatList(){

	v_dvdCls = $("select[name='dvdCls'] option:selected").val();
	if(v_dvdCls == "ALL"){
		v_dvdCls = "";
	}
	v_subject = $("#subject").val();
	v_docSts = $("select[name='docSts'] option:selected").val();
	if(v_docSts == "ALL"){
		v_docSts = "";
	}
	v_spv = $("#spv").val();
	v_actorNm = $("#actorNm").val();
	v_mkco = $("#mkco").val();

	//해당 업무구분과 업무분류에 해당되는 양식명을 가져옴
	var params = {
			dvdCls  : v_dvdCls,			//구분 코드
			subject : v_subject,		//제목
			docSts  : v_docSts,			//결재상태
			spv     : v_spv,            //감독
			actorNm : v_actorNm,        //배우명
			mkco    : v_mkco            //제작사
	};

	gf_Transaction("SELECT_TECH_DAT_LIST", "/tech/techAppn/retrieveTechDvdDatList.xpl", params, {}, "f_Callback", true);

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

	v_regNo    = rowData.get("regNo");
	v_subject  = rowData.get("title");
	v_actorNm  = rowData.get("actorNm");
	v_spv      = rowData.get("spv");
	v_playTime = rowData.get("playTime");
	v_dbng     = rowData.get("dbng");
	v_sbtl     = rowData.get("sbtl");
	v_dvdCls   = rowData.get("dvdCls");
	v_cls      = rowData.get("cls");
	v_mkco     = rowData.get("mkco");
	v_recTp    = rowData.get("recTp");
	v_scrn     = rowData.get("scrn");
	v_cont     = rowData.get("cont");
	v_docSts   = rowData.get("docSts");
	v_disregno = rowData.get("disregno");

	var datas = {
			regNo    :  v_regNo,
			subject  : 	v_subject,
			actorNm  : 	v_actorNm,
			spv      :	v_spv,
			playTime :	v_playTime,
			dbng     :	v_dbng,
			sbtl     :	v_sbtl,
			dvdCls   :	v_dvdCls,
			cls      :	v_cls,
			mkco     :  v_mkco,
			recTp    :  v_recTp,
			scrn     :  v_scrn,
			cont     :	v_cont,
			docSts   : 	v_docSts,
			disregno :	v_disregno
	};

		var newWin = gf_PostOpen("/tech/techAppn/techDvdDataViewDoc.jsp", null, "toolbar=no,scrollbars=yes,width=1024,height=700", datas, true, true, "cf_RefreshList");
}
