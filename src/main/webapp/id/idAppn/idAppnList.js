var ds_IdAppnList = new DataSet();
var ds_IdAppnDtlList = new DataSet();
var ds_Cls = new DataSet(); 		//구분 검색
var ds_SimpleDt = new DataSet(); 	//신청일 검색
var ds_SysCd = new DataSet(); 		//시스템 검색
var ds_DocSts = new DataSet();	//문서상태 검색

var ds_Auth = new DataSet();

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
	//특별ID 신청 목록 JQGrid
	var idAppnList = {
		datatype: "local",
	   	colNames:[gf_FindLang('구분'), gf_FindLang('신청번호'),
	   	       gf_FindLang('사번'),gf_FindLang('신청팀'), gf_FindLang('신청자'), gf_FindLang('문서상태'), gf_FindLang('최종수정일자')],
	   	colModel:[
	   	          {name:'clsNm',index:'clsNm', width:80,align: "center",
	   	        	formatter:function(val, gOpt, row){
	   	        		ds_Cls.filter(null);
		        		var result = gf_BindView(val, row, gOpt, {dataSet:ds_Cls, curVal:"cls", val: "code", text:"value"});
		        		if (result == '연장')
		        			return "<span style='color:blue'>"+ result + "</span>";
		        		else if (result == '기본정보변경') //// 20230112 추가
		        			return "<span style='color:blue'>"+ result + "</span>"; //// 20230112 추가
		        		else if (result == '해지')
		        			return "<span style='color:red'>"+ result + "</span>";
		        		return result;
		        	  }
	   	          },
	   	          {name:'docNo',index:'docNo', width:100,align: "center"},
	   	          {name:'userId',index:'userId', width:50,align: "center"},
	   	          {name:'orgNm',index:'orgNm', width:80,align: "center"},
	   	          {name:'userNm',index:'userNm', width:80,align: "center"},
	   	          {name:'docStsNm',index:'docStsNm', width:50,align: "center",
	   	        	formatter:function(val, gOpt, row){
	   	        		ds_DocSts.filter(null);
	   	        		var result = gf_BindView(val, row, gOpt, {dataSet:ds_DocSts, curVal:"docSts", val: "code", text:"value"});
		        		return result;
		        	  }
	   	          },
	   	          {name:'fnlEditDt',index:'fnlEditDt', width:80,align: "center"}
		],
	   	autowidth:true,
	   	height:315,
	   	sortname: 'docNo',
	    viewrecords: true,
	    sortorder: 'desc',
	    pager : '#idAppnListPager',
		rowNum :15,
		rowList : [15, 30, 60],
	    onSelectRow : function(rowid, status, e){
	    	grd_List_onrowclick(rowid);
	     },
	    ondblClickRow: function(rowid, iRow, iCol, boxid, e){
	    	grd_List_oncelldbclick(this, rowid, iRow, iCol);
	     }
	};

	$("#idAppnList").jqGrid(idAppnList);
	$("#idAppnList").jqGrid('navGrid','#idAppnListPager',{edit:false,add:false,del:false});


	//특별ID 신청 상세 목록 JQGrid
	var idAppnDtlList = {
		datatype: "local",
	   	colNames:[gf_FindLang('문서번호'), gf_FindLang('발급ID'),gf_FindLang('팀'),gf_FindLang('이름'), gf_FindLang('시작일자'),gf_FindLang('종료일자'),
	   	       gf_FindLang('바로넷'),gf_FindLang('메일'),gf_FindLang('바로미'),gf_FindLang('바로콘'),gf_FindLang('모바일'),gf_FindLang('SVPN'),
	   	         // -- DRM 외주인력보안  통합  20210701
	   	       gf_FindLang('분양관리'),gf_FindLang('스마티(안전)'),gf_FindLang('외주인력보안'),],
	   	colModel:[
	   	        {name:'docNo',index:'docNo', hidden:true },
	  	   		{name:'userId',index:'userId', width:80,align: "center"},
	  	   		{name:'orgNm',index:'orgNm', width:60,align: "center"},
	  	   	 	{name:'userNm',index:'userNm', width:40,align: "center"},
		   		{name:'stYmd',index:'stYmd', width:65,align: "center",},
		   		{name:'edYmd',index:'edYmd', width:65,align: "center",},
		   		{name:'system1',index:'system1', width:40,align: "center"},
		   		{name:'system4',index:'system4', width:40,align: "center"},
		   		{name:'system3',index:'system3', width:40,align: "center"},
		   		{name:'system2',index:'system2', width:40,align: "center"},
		   		{name:'system6',index:'system6', width:40,align: "center"},
		   		{name:'system5',index:'system5', width:40,align: "center"},
		   	//	{name:'system7',index:'system7', width:40,align: "center"}, -- DRM 외주인력보안  통합  20210701
		   		{name:'system8',index:'system8', width:40,align: "center"},
		   		{name:'system10',index:'system10', width:40,align: "center"},
		   		{name:'system9',index:'system9', width:40,align: "center"},
		],
	   	autowidth:true,
	   	height:105,
	   	sortname: 'userId',
	    viewrecords: true,
	    sortorder: "asc",
	    pager : '#idAppnDtlListPager',
	    rowNum :5,
		rowList : [5, 10, 20],
	};

	$("#idAppnList").jqGrid(idAppnList);
	$("#idAppnList").jqGrid('navGrid','#idAppnListPager',{edit:false,add:false,del:false});
	$("#idAppnDtlList").jqGrid(idAppnDtlList);
	$("#idAppnDtlList").jqGrid('navGrid','#idAppnDtlListPager',{edit:false,add:false,del:false});

	$("select[name='simpleDtCd']").trigger('change'); //신청일

	//신청일 검색 Date Component 초기화
	$( "input[name='startDate']" ).mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
	$( "input[name='endDate']" ).mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });

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
	ds_IdAppnList.bind($("#idAppnList"));
	ds_IdAppnDtlList.bind($("#idAppnDtlList"));

	//구분 검색 DataSet Binding
	ds_Cls.bind($("select[name='cls']")[0], {val: "code", text: "value"});

	//신청일 검색 DataSet Binding
	ds_SimpleDt.bind($("select[name='simpleDtCd']")[0], {val: "code", text: "value"});

	//문서상태 검색 DataSet Binding
	ds_DocSts.bind($("select[name='docSts']")[0], {val: "code", text: "value"});

	//소속 검색 DataSet Binding
	ds_Auth.bind($("select[name='orgCd']")[0], {val: "orgCd", text: "value"});
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
		ds_IdAppnList.clear();
		ds_IdAppnDtlList.clear();
		cf_RetrieveIdAppnList();
	});

	$("#idAppnDraft").click(function(){
		var params = {
		};
		var newWin = gf_PostOpen("/id/idAppn/idAppnDraft.jsp", null,	"toolbar=no,scrollbars=yes,width=1024,height=770", params, true, true, "cf_RefreshList");
	});

	$("#idAppnExtDraft").click(function(){
		var params = {
		};
		var newWin = gf_PostOpen("/id/idAppn/idAppnExtDraft.jsp", null,	"toolbar=no,scrollbars=yes,width=1024,height=770", params, true, true, "cf_RefreshList");
	});

	$("#idAppnExpDraft").click(function(){
		var params = {
		};
		var newWin = gf_PostOpen("/id/idAppn/idAppnExpDraft.jsp", null,	"toolbar=no,scrollbars=yes,width=1024,height=770", params, true, true, "cf_RefreshList");
	});

	$("#idAppnChgDraft").click(function(){
		var params = {
		};
		var newWin = gf_PostOpen("/id/idAppn/idAppnChgDraft.jsp", null,	"toolbar=no,scrollbars=yes,width=1024,height=770", params, true, true, "cf_RefreshList");
	});

	//// CWH 변경신청 추가 2023.01.11
	$("#idAppnEditDraft").click(function(){
		var params = {
		};
		var newWin = gf_PostOpen("/id/idAppn/idAppnEditDraft.jsp", null,	"toolbar=no,scrollbars=yes,width=1024,height=770", params, true, true, "cf_RefreshList");
	});
	////CWH 변경신청 추가 2023.01.11
	//신청일 검색 Select Box 선택
	$("select[name='simpleDtCd']").change(function(){
		simpleDtCd = gf_GetValue($("select[name='simpleDtCd']").val());

		if(simpleDtCd == 'DT'){ //신청일 상세검색
			// 신청일 검색 초기화 : 종료일을 내일날짜로 초기화
			$("#detailDt").show();
			$( "input[name='endDate']" ).datepicker( "setDate", gf_GetNextDate() );
		}else{
			$("#detailDt").hide();
		}
	});

	//기안문일경우 : 사전협의 notice
	$("#processInfo").click(function(){
		gf_PostOpen("/id/idAppn/info/processInfo.jsp", null,"toolbar=no,scrollbars=Yes,width=830,height=650", {},
				true, false, "");
	});

	//기안문일경우 : 사전협의 notice
	$("#passwordInfo").click(function(){
		gf_PostOpen("/id/idAppn/info/passwordInfo.jsp", null,"toolbar=no,scrollbars=Yes,width=830,height=650", {},
				true, false, "");
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
	//구분 검색 Select Box 처리
	gf_GetCommCds("ID001", ds_Cls, function(CommGrpCd, data){
		ds_Cls.insert(0, {code: 'ALL', value: '전체'})
	});

	//신청일 검색 Select Box 처리
	gf_GetCommCds("ID002", ds_SimpleDt, function(CommGrpCd, data){
	});

	//문서상태 검색 Select Box 처리
	ds_DocSts.setAllData(gv_IdDocStsCd);
	ds_DocSts.insert(0, {code: 'ALL', value: '전체'});

	if(!gf_IsNull(gv_AuthList)) {
		ds_Auth.setAllData(gv_AuthList.auth);
			ds_Auth.insert(0, {orgCd: '', value: '전체'});
	}

	cf_RetrieveIdAppnList();
}

function cf_RetrieveIdAppnList(){

	//최종수정일 365일 초과 체크
	if(f_CheckSearch() == false){
		return false;
	}

	//해당 업무구분과 업무분류에 해당되는 양식명을 가져옴
	var params = {
			cls : $("select[name='cls']").val(),								//구분 코드
			docSts : gf_GetValue($( "select[name='docSts']" ).val()),			//결재상태
			simpleDt : gf_GetValue($("select[name='simpleDtCd']").val()), 		//신청일 검색
			docStart : gf_GetValue($( "input[name='startDate']" ).val()), 		//신청일 검색 : 시작일
			docEnd : gf_GetValue($( "input[name='endDate']" ).val()), 			//신청일 검색 : 종료일
			orgCd : gf_GetValue($( "select[name='orgCd']" ).val()), 			//소속
			appnUser : gf_GetValue($( "input[name='appnUser']" ).val()), 		//신청자
			trgtUser : gf_GetValue($( "input[name='trgtUser']" ).val()), 		//대상자
			orgNm : gf_GetValue($( "input[name='orgNm']" ).val()) 				//조직명(신청자팀 & 대상자팀)
	};
	gf_Transaction("SELECT_ID_APPN_LIST", "/id/idAppn/retrieveIdAppnList.xpl", params, {}, "f_Callback", true);
}

function cf_RetrieveIdAppnDtlList(rowData){
	//해당 업무구분과 업무분류에 해당되는 양식명을 가져옴
	var params = {
			docNo : rowData.get('docNo') 		//문서번호
	};
	gf_Transaction("SELECT_ID_APPN_DTL_LIST", "/id/idAppn/retrieveIdAppnDtlList.xpl", params, {}, "f_Callback", true);
}

function cf_ConfirmIdAppnDtl(userId, docNo){
	var params = {
			userId : userId,
			docNo : docNo
	};
	gf_Transaction("CONFIRM_ID_APPN_DTL", "/id/idAppn/confirmIdAppnDtl.xpl", params, {}, "f_Callback", true);
}

function cf_RefreshList(){
	cf_RetrieveIdAppnList();
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
	  	case "SELECT_ID_APPN_LIST" :
	  		ds_IdAppnList.setAllData(resultData.ds_IdAppnList);
			break;
	  	case "SELECT_ID_APPN_DTL_LIST" :
	  		ds_IdAppnDtlList.setAllData(resultData.ds_IdAppnDtlList);
			break;
	  	case "CONFIRM_ID_APPN_DTL" :
			break;

	  	default :
	  		break;
	  }
}

function grd_List_onrowclick(rowid)
{
 	var rowData = $("#idAppnList").data(rowid);

 	cf_RetrieveIdAppnDtlList(rowData);
}

function grd_List_oncelldbclick(grid, rowid, iRow, iCol)
{
	var rowData = $("#idAppnList").data(rowid);

	var datas = {
			docNo : rowData.get("docNo"),
			docSts : rowData.get("docSts")
	};

	if(rowData.get("cls")=="01")
		var newWin = gf_PostOpen("/id/idAppn/idAppnDraft.jsp", null, "toolbar=no,scrollbars=yes,width=1024,height=770", datas, true, true, "cf_RefreshList");
	else if(rowData.get("cls")=="02")
		var newWin = gf_PostOpen("/id/idAppn/idAppnExtDraft.jsp", null, "toolbar=no,scrollbars=yes,width=1024,height=770", datas, true, true, "cf_RefreshList");
	else if(rowData.get("cls")=="03")
		var newWin = gf_PostOpen("/id/idAppn/idAppnExpDraft.jsp", null, "toolbar=no,scrollbars=yes,width=1024,height=770", datas, true, true, "cf_RefreshList");
	else if(rowData.get("cls")=="04")
		var newWin = gf_PostOpen("/id/idAppn/idAppnChgDraft.jsp", null, "toolbar=no,scrollbars=yes,width=1024,height=770", datas, true, true, "cf_RefreshList");
	else if(rowData.get("cls")=="05")
		var newWin = gf_PostOpen("/id/idAppn/idAppnEditDraft.jsp", null, "toolbar=no,scrollbars=yes,width=1024,height=770", datas, true, true, "cf_RefreshList");

}

// 검색조건 1년 제한(기안일 1년 초과시 기안일외 항목 1개 이상 필수 입력) 체크
function f_CheckSearch(){

    // 검색조건 1년 제한(기안일 1년 초과시 기안일외 항목 1개 이상 필수 입력) (시작)
	var d_appnUser = gf_GetValue($( "input[name='appnUser']" ).val());	//신청자
	var d_trgtUser = gf_GetValue($( "input[name='trgtUser']" ).val());	//대상자
	var d_simpleDtCd = gf_GetValue($("select[name='simpleDtCd']").val());		//최종수정일
    var d_startDate = gf_GetValue($( "input[name='startDate']" ).val());		//최종수정일(시작)
    var d_endDate = gf_GetValue($( "input[name='endDate']" ).val());			//최종수정일(종료)
    var d_msg = "■ 최종수정일 범위 1년 초과 검색 제한(시스템 과부하 방지) ■ \n\n ";
    d_msg += "최종수정일 범위를 1년 초과하여 검색 시, 아래 항목중 1개 이상 필수로 입력하세요 \n\n ";
    d_msg += "( 신청자, 대상자 )";

    var todate = fnToDate();	//오늘날짜 구하기(아래에 함수사용함)
    var strDate1 = d_startDate;
    var strDate2 = d_endDate;

    if (d_startDate == null || d_startDate == "") {
    	strDate1 = "1000-01-01";
    }
    if (d_endDate == null || d_endDate == "") {
    	strDate2 = todate;
    }
    var arr1 = strDate1.split('-');
    var arr2 = strDate2.split('-');
    var dat1 = new Date(arr1[0], arr1[1], arr1[2]);
    var dat2 = new Date(arr2[0], arr2[1], arr2[2]);

    var diff = dat2 - dat1;
    var currDay = 24 * 60 * 60 * 1000;// 시 * 분 * 초 * 밀리세컨

    var diff_day = parseInt(diff/currDay);

   if(d_simpleDtCd == "DT"){
        if (diff_day > 365) {
            if (d_appnUser == "" && d_trgtUser == "") {
		        alert(d_msg);
		        return false;
	        }
        }
    }
    // 검색조건 1년 제한(기안일 1년 초과시 기안일외 항목 1개 이상 필수 입력) (끝)
}

/*
현재날짜를 YYYYMMDD 형태로 리턴
*/
function fnToDate()
{
   var today = new Date(); // 날자 변수 선언
   var dateNow = fnLPAD(String(today.getDate()),"0",2); //일자를 구함
   var monthNow = fnLPAD(String((today.getMonth()+1)),"0",2); // 월(month)을 구함
   var yearNow = String(today.getYear()); //년(year)을 구함

   return yearNow + "-" + monthNow + "-" + dateNow;
}

/*
왼쪽에 원하는 텍스트 추가
오라클 LPAD 함수와 같음
val         원래 값
set         왼쪽에 추가하려는 값
cnt         set 갯수
*/
function fnLPAD(val,set,cnt)
{
   if( !set || !cnt || val.length >= cnt)
   {
        return val;
   }

   var max = (cnt - val.length)/set.length;

   for(var i = 0; i < max; i++)
   {
        val = set + val;
   }

   return val;
}