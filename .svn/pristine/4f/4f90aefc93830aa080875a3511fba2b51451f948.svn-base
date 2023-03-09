/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
var ds_InnerTripList = new DataSet();	// 문서함 공지사항 목록 20140724

var ds_DocStsCd = new DataSet();
var ds_AcctStsCd = new DataSet();

/**
* @class 타블릿 화면 사용시 각페이지에서 생성되는 전역변수를 초기화시킴(팝업화면은 제외)
*        - 메모리 해제 관련 처리
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_GarbageCollector(){
//	delete v_signbxNm;

}

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
	gv_AuthList = gf_IsNull(datas.gv_AuthList) ? "" : datas.gv_AuthList;
}

/**
* @class Form Onload 시 컴포넌트 초기화
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetComponents()
{

	gf_SetUserComponent($("#empInput"), function(data){
		var userInfo = data.orgNm + " " + data.userPositCd + " " + data.userKnm + "(" + data.userId + ")";
//		$("#userInfo").val(userInfo);
	});

	gf_SetUserComponent($("#tripUserInput"), function(data){
		var userInfo = data.orgNm + " " + data.userPositCd + " " + data.userKnm + "(" + data.userId + ")";
//		$("#userInfo").val(userInfo);
	});

	//문서함 목록 JQGrid
	var innerTripList = {
		datatype: "local",
	   	colNames:[gf_FindLang('작성일자'),gf_FindLang('작성자 소속팀'),gf_FindLang('작성자'),gf_FindLang('출장자 소속팀'), gf_FindLang('출장자'), gf_FindLang('출장기간'),gf_FindLang('결재문서번호'),gf_FindLang('Ref No'),gf_FindLang('결재상태'), gf_FindLang('회계상태'),
	   	          gf_FindLang("소속팀이름"), gf_FindLang("소속팀코드"), gf_FindLang("출장시작일"), gf_FindLang("출장종료일"), gf_FindLang('작성자 아이디'), gf_FindLang('작성자 이름'), gf_FindLang('출장자 아이디'), gf_FindLang('출장자 이름'), gf_FindLang('출장자 소속팀 코드'), gf_FindLang('출장자 소속팀 이름'), gf_FindLang('결재상태 코드 '), gf_FindLang('회계상태 코드'),
	   	          gf_FindLang('임시저장')],
	   	colModel:[
	  	   		{name:'fnlEditDt',index:'fnlEditDt', width:80,align: "center",
		  	   		formatter:function(val, gOpt, row){
		  	   			if(val.length > 19){
		  	   				val = val.substr(0, 19);
		  	   			}
	  	   	 			return val;
		  	   		}
	  	   		},
	  	   		{name:'execDept',index:'execDept', width:60,align: "center",
	  	   			formatter:function(val, gOpt, row){

	  	   	 			var rVal = row.dutyAgncOrgNm;// + " (" + row.dutyAgncOrgCd +")";
	  	   				if(row.dutyAgncOrgNm == "" || row.dutyAgncOrgCd == ""){
	  	   					rVal = "";
	  	   				}
	  	   	 			return rVal;
	  	   			}, hidden : true
	  	   		},
	  	   		{name:'drafter',index:'drafter', width:60,align: "center",
	  	   			formatter:function(val, gOpt, row){

	  	   	 			var rVal = row.fnlEditUserNm + " (" + row.fnlEditUserId +")";
		  	   	 		if(row.fnlEditUserNm == "" || row.fnlEditUserId == ""){
	  	   					rVal = "";
	  	   				}
	  	   	 			return rVal;
	  	   			}
	  	   		},
	  	   		{name:'draftUserOrg',index:'draftUserOrg', width:100,align: "center",
	  	   			formatter:function(val, gOpt, row){

	  	   	 			var rVal = row.userOrgNm;// + " (" + row.userOrgCd +")";
		  	   	 		if(row.userOrgNm == "" || row.userOrgCd == ""){
	  	   					rVal = "";
	  	   				}
			  	   	 	if(row.docSts == "D16"){
			  	   	 		if(row.ifParam == "") return "";
		  	   	 			var data = JSON.parse(row.ifParam);
		  	   	 			rVal = data.tripUserTeamNm;// + " (" + data.tripUserTeam +")";
		  	   	 		}
	  	   	 			return rVal;
	  	   			}
	  	   		},
	  	   	 	{name:'tripUser',index:'tripUser', width:60,align: "center",
	  	   			formatter:function(val, gOpt, row){

	  	   	 			var rVal = row.userNm + " (" + row.userId +")";
		  	   	 		if(row.userNm == "" || row.userId == ""){
	  	   					rVal = "";
	  	   				}
		  	   	 	if(row.docSts == "D16"){
		  	   	 		if(row.ifParam == "") return "";
	  	   	 			var data = JSON.parse(row.ifParam);
	  	   	 			rVal = data.tripUserNm + " (" + data.tripUser +")";
	  	   	 		}
	  	   	 			return rVal;
	  	   			}
	  	   		},
		   		{name:'tripYMD',index:'tripYMD', width:100,align: "center",
	  	   	 		formatter:function(val, gOpt, row){

	  	   	 			var ymdVal = $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd" , row.stYmd)) + " ~ " +
	  	   	 			$.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd" , row.edYmd));
		  	   	 		if(row.stYmd == "" || row.edYmd == ""){
		  	   	 			ymdVal = "";
	  	   				}
			  	   	 	if(row.docSts == "D16"){
			  	   	 		if(row.ifParam == "") return "";
		  	   	 			var data = JSON.parse(row.ifParam);
		  	   	 			ymdVal = data.startDate + " ~ " + data.endDate;
			  	   	 		if(data.startDate == "" || data.endDate == ""){
			  	   	 			ymdVal = "";
		  	   				}
		  	   	 		}
	  	   	 			return ymdVal;
	  	   	 		}
	  	   	 	},
	  	   	 	{name:'signId',index:'signId', width:60,align: "center"},
	  	   	 	{name:'refNo',index:'refNo', width:60,align: "center"},
		   		{name:'dSts',index:'dSts', width:50,align: "center",
		   			formatter:function(val, gOpt, row){

		   				var rVal;
		   				if(ds_DocStsCd.find("code", row.docSts) != -1){
		   					rVal = ds_DocStsCd.get(ds_DocStsCd.find("code", row.docSts)).value;
		   				}else{
		   					rVal = "";
		   				}

	  	   				return rVal;
	  	   			}
		   		},
	  	   	 	{name:'aSts',index:'aSts', width:50,align: "center",
		   			formatter:function(val, gOpt, row){

		   				var rVal;
		   				if(ds_AcctStsCd.find("code", row.acctSts) != -1){
		   					rVal = ds_AcctStsCd.get(ds_AcctStsCd.find("code", row.acctSts)).value;
		   				}else{
		   					rVal = "";
		   				}
	  	   				return rVal;
	  	   			}
		   		},
		   		{name:'dutyAgncOrgNm',index:'dutyAgncOrgNm', width:170,align: "center", hidden: true},
		   		{name:'dutyAgncOrgCd',index:'dutyAgncOrgCd', width:170,align: "center", hidden: true},
		   		{name:'stYmd',index:'stYmd', width:170,align: "center", hidden: true},
		   		{name:'edYmd',index:'edYmd', width:170,align: "center", hidden: true},
		   		{name:'fnlEditUserId',index:'fnlEditUserId', width:170,align: "center", hidden: true},
		   		{name:'fnlEditUserNm',index:'fnlEditUserNm', width:170,align: "center", hidden: true},
		   		{name:'userId',index:'userId', width:170,align: "center", hidden: true},
		   		{name:'userNm',index:'userNm', width:170,align: "center", hidden: true},
		   		{name:'userOrgCd',index:'userOrgCd', width:170,align: "center", hidden: true},
		   		{name:'userOrgNm',index:'userOrgNm', width:170,align: "center", hidden: true},
		   		{name:'docSts',index:'docSts', width:80,align: "center", hidden: true},
		   		{name:'acctSts',index:'acctSts', width:170,align: "center", hidden: true},
		   		{name:'ifParam',index:'ifParam', width:170,align: "center", hidden: true}
	   	],
	   	autowidth:true,
	   	height:450,
	   	pager : '#innerTripListPager',
		rowNum :20,
		rowList : [10, 20, 30],
//	   	sortname: 'fstRegDt',
	    viewrecords: true,
//	    sortorder: "desc",
	    ondblClickRow: function(rowid, iRow, iCol, boxid, e){
	    	grd_List_oncelldbclick(this, rowid, iRow, iCol, '1');
	     }
	};

	$("#innerTripList").jqGrid(innerTripList);
	$("#innerTripList").jqGrid('navGrid','#innerTripListPager',{search:false,edit:false,add:false,del:false});


	/**
	 * Container 크기에 맞춰 Windows Resizing 될 때 Box Grid의 사이즈를 조절한다.
	 */
	$(window).bind("resize", function(){
//		$("#innerTripList").setGridWidth($("#innerTripList").width());
	}).trigger('resize');

	$( "#stDateInput" ).mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
	$( "#edDateInput" ).mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });


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
	ds_InnerTripList.bind($("#innerTripList"));

	ds_DocStsCd.bind($("#signStsCd")[0], {val: "code", text: "value"});

}

/**
* @class Form Onload 시 Element/Component 인벤트 초기화
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetEventListener()
{

	$("#closeBtn").click(function(){
		self.close();
	});


	$("#innerTripDraft").click(function(){


//		gf_PostReplace("/trip/innerTrip/innerTripDraft.jsp", {}, true, true, null);

//		gf_PostReplace(url, params, bFstLvlPop, bModal, callBackFunc){
//
//		gf_PostOpen("/common/jsp/comp/budget/bdgtSelect.jsp", null,
//				"toolbar=no,scrollbars=no,width=665,height=600", bdgtParams,
//				true, true, "f_callBackFuncBdgtSelect");

		gf_PostOpen("/trip/innerTrip/innerTripDraft.jsp", null,
				"toolbar=no,scrollbars=yes,width=1024,height=700", {},
				true, true, "f_RefreshList");
	});

	$("#innerTripSearch").click(function(){

		cf_RetrieveInnerTripList();

	});

	$("#innerTripError").click(function(){

		gf_PostOpen("/trip/innerTrip/innerTripError.jsp", null,
				"toolbar=no,scrollbars=yes,width=1024,height=700", {},
				false, false, "f_RefreshList");

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
	ds_DocStsCd.setAllData(gv_DocStsCd);
	ds_DocStsCd.insert(0, {code: '', value: '전체'});

	ds_AcctStsCd.setAllData(gv_AcctStsCd);
	ds_AcctStsCd.insert(0, {code: '', value: '연동전'});

	// 관리자가 아닌 경우
	cf_RetrieveInnerTripList();
}

/**
* @class 업무시스템과 업무분류에 따른 양식문 Select Box를 구성한다.
* @author 방유성
* @since 2013-07-09
* @version 1.0
*/
function cf_RetrieveInnerTripList(){

	var userId = $("#empInput_id").val();
	var orgNm = $("#orgNmInput").val();
	var signStsCd = $("#signStsCd").val();

	//20150724 검색조건 추가
	var refNo = $("#refNoInput").val();
	var tripUser = $("#tripUserInput_id").val();
	var stDate = $("#stDateInput").val();
	var edDate = $("#edDateInput").val();

	// 출장기간 시작일과 종료일 중 하나만 입력되어 있다면 나머지 날짜를 대체한다.
	if(stDate != "" && edDate == ""){
		edDate = stDate;
	}else if(edDate != "" && stDate == ""){
		stDate = edDate;
	}

	var adminYn = "Y";



	//해당 업무구분과 업무분류에 해당되는 양식명을 가져옴
	var params = {
			userId : userId,
			orgNm : orgNm,
			signStsCd : signStsCd,
			adminYn : adminYn,
			refNo : refNo,
			tripUser : tripUser,
			stDate : stDate,
			edDate : edDate
	};
	gf_Transaction("SELECT_INNER_TRIP_LIST", "/trip/innerTrip/retrieveInnerTripError.xpl", params, {}, "f_Callback", true);
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
	  	case "SELECT_INNER_TRIP_LIST" :
	  		ds_InnerTripList.setAllData(resultData.ds_InnerTripList);

	  		if(gv_AuthList.auth[0].code.match("RO_CMAS_TS_ADM") == null){
	  			// filter
		  		ds_InnerTripList.filter(
						function (DataSetRow) {
							if ( DataSetRow.get("fstRegUserId") != gv_userId && DataSetRow.get("docSts") == "D16" ) {
								return false;
							}
							return true;
						}
				);
	  		}

	  		var rcCount = "["+ds_InnerTripList.size()+"]";
	  		$("#innerTripListCnt").text(rcCount); 	//문서함 목록
			break;

	  	default :
	  		break;
	  }
}


/**
* @class 작성함의 양식목록 더블클릭시 기안작성화면 오픈
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function grd_List_oncelldbclick(grid, rowid, iRow, iCol)
{
	var rowData = $("#innerTripList").data(rowid);


//	var dutyLnkCd = rowData.get("dutyLnkCd");
//	var dutySysCd = rowData.get("sysCd");		//TODO : 문서번호 체계에 따라 변경
//	var signDocfile = rowData.get("signFormDat");
//	var signDocTitle = rowData.get("signFormNm");
//	var signFormTpCd = rowData.get("signFormTpCd");

	var cmasId = rowData.get("docNo");
	var acctSts = rowData.get("acctSts");
	var retResn = rowData.get("retResn");
	var docSts = rowData.get("docSts");

	var viewDocParam = {
			fromList : cmasId,
			acctSts : acctSts,
			retResn : retResn,
			docSts : docSts

	};



//	gf_PostReplace("/trip/innerTrip/innerTripViewDoc.jsp", viewDocParam, true, true, null);

	// 임시저장일 경우
	if(docSts == "D16"){
		gf_PostOpen("/trip/innerTrip/innerTripDraft.jsp", null,
				"toolbar=no,scrollbars=yes,width=1024,height=700", viewDocParam,
				true, true, "f_RefreshList");

	}else{
		gf_PostOpen("/trip/innerTrip/innerTripViewDoc.jsp", null,
				"toolbar=no,scrollbars=yes,width=1024,height=700", viewDocParam,
				true, true, "f_RefreshList");

	}


}

function f_RefreshList(){
	cf_RetrieveInnerTripList();
}