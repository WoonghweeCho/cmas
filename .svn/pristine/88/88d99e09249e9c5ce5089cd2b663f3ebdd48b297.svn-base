/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
var ds_CityTranspList = new DataSet();	// 문서함 공지사항 목록 20140724

var ds_DocStsCd = new DataSet();
var ds_ExpCls = new DataSet();
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

	//기안일 검색 Date Component 초기화
	$( "input[name='startDate']" ).mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
	$( "input[name='endDate']" ).mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });

	//문서함 목록 JQGrid
	var cityTranspList = {
		datatype: "local",
	   	colNames:[gf_FindLang('일자'),gf_FindLang('소속팀'),gf_FindLang('신청자'),gf_FindLang('경비구분'),gf_FindLang('예산내역'),gf_FindLang('결재문서번호'),gf_FindLang('Ref No'),gf_FindLang('결재상태'),gf_FindLang('회계상태'),
	   	          gf_FindLang('신청자 이름'),gf_FindLang('신청자 아이디'),gf_FindLang('집행팀 이름'), gf_FindLang('집행팀 코드'), gf_FindLang('회계상태코드'), gf_FindLang('문서상태코드'),gf_FindLang('임시저장')
	   	          ],
	   	colModel:[
	  	   		{name:'fstRegDt',index:'fstRegDt', width:100,align: "center",
		  	   		formatter:function(val, gOpt, row){
		  	   			if(val.length > 19){
		  	   				val = val.substr(0, 19);
		  	   			}
	  	   	 			return val;
		  	   		}
	  	   		},
	  	   		{name:'execTeam',index:'execTeam', width:80,align: "center",
	  	   			formatter:function(val, gOpt, row){

	  	   	 			var rVal = row.doOrgNm;// + " (" + row.doOrgCd +")";
		  	   	 		if(row.doOrgNm == "" || row.doOrgCd == ""){
	  	   					rVal = "";
	  	   				}
		  	   	 		if(row.docSts == "D16"){
		  	   	 			var data = JSON.parse(row.ifParam);
		  	   	 			rVal = data.drafterOrgNm;// + " (" + data.drafterOrgCd +")";
		  	   	 		}
	  	   	 			return rVal;
	  	   			}
	  	   		},
		   		{name:'drafter',index:'drafter', width:80,align: "center",
	  	   			formatter:function(val, gOpt, row){

	  	   	 			var rVal = row.fnlEditUserNm + " (" + row.fnlEditUserId +")";
	  	   	 			return rVal;
	  	   			}
		   		},
		   		{name:'expCls',index:'expCls', width:100,align: "center",
		   			formatter:function(val, gOpt, row){

		   				if(ds_ExpCls.find("code", val) != -1){
		   					val = ds_ExpCls.get(ds_ExpCls.find("code", val)).value;
		   				}
		   				if(row.docSts == "D16"){
		   					var data = JSON.parse(row.ifParam);
		   					if(ds_ExpCls.find("code", data.bdgtType) != -1){
			   					val = ds_ExpCls.get(ds_ExpCls.find("code", data.bdgtType)).value;
			   				}
		   					if(val == "전체") val = "";
		   				}
	  	   				return val;
	  	   			}
		   		},
		   		{name:'bdgtItem',index:'bdgtItem', width:130,align: "center",
		   			formatter:function(val, gOpt, row){
		   				var val;
		   				if(row.docSts == "D16"){
		   					var data = JSON.parse(row.ifParam);
		   					val = data.kText;
		   				}
	  	   				return val;
	  	   			}
		   		},
		   		{name:'signId',index:'signId', width:80,align: "center"},
		   		{name:'refNo',index:'refNo', width:80,align: "center"},
		   		{name:'dSts',index:'dSts', width:50,align: "center",
		   			formatter:function(val, gOpt, row){

		   				var rVal;
		   				if(ds_DocStsCd.find("code", row.docSts) != -1){
		   					rVal = ds_DocStsCd.get(ds_DocStsCd.find("code", row.docSts)).value;
		   				}

	  	   				return rVal;
	  	   			}
		   		},
		   		{name:'aSts',index:'aSts', width:50,align: "center",
		   			formatter:function(val, gOpt, row){

		   				var rVal;
		   				if(ds_AcctStsCd.find("code", row.acctSts) != -1){
		   					rVal = ds_AcctStsCd.get(ds_AcctStsCd.find("code", row.acctSts)).value;
		   				}
	  	   				return rVal;
	  	   			}
		   		},
		   		{name:'fnlEditUserNm',index:'fnlEditUserNm', width:170,align: "center", hidden : true},
		   		{name:'fnlEditUserId',index:'fnlEditUserId', width:170,align: "center", hidden : true},
		   		{name:'doOrgNm',index:'doOrgNm', width:170,align: "center", hidden : true},
		   		{name:'doOrgCd',index:'doOrgCd', width:170,align: "center", hidden : true},
		   		{name:'acctSts',index:'acctSts', width:170,align: "center", hidden: true},
		   		{name:'docSts',index:'docSts', width:170,align: "center", hidden: true},
		   		{name:'ifParam',index:'ifParam', width:170,align: "center", hidden: true}
	   	],
	   	autowidth:true,
	   	height:450,
	   	pager : '#cityTranspListPager',
		rowNum : 20,
		rowList : [ 10, 20, 30 ],
//	   	sortname: 'fstRegDt',
	    viewrecords: true,
//	    sortorder: "desc",
	    ondblClickRow: function(rowid, iRow, iCol, boxid, e){
	    	grd_List_oncelldbclick(this, rowid, iRow, iCol, "1");
	     }
	};

	$("#cityTranspList").jqGrid(cityTranspList);
	$("#cityTranspList").jqGrid('navGrid','#cityTranspListPager',{search:false,edit:false,add:false,del:false});


	/**
	 * Container 크기에 맞춰 Windows Resizing 될 때 Box Grid의 사이즈를 조절한다.
	 */
	$(window).bind("resize", function(){
//		$("#innerTripList").setGridWidth($("#innerTripList").width());
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
	ds_CityTranspList.bind($("#cityTranspList"));

	ds_DocStsCd.bind($("#signStsCd")[0], {val: "code", text: "value"});
	ds_ExpCls.bind($("#expCls")[0], {val: "code", text: "value"});

}

/**
* @class Form Onload 시 Element/Component 인벤트 초기화
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetEventListener()
{
	$("#cityTranspDraft").click(function(){


//		gf_PostReplace("/trip/cityTransp/cityTranspDraft.jsp", {}, true, true, null);

//		gf_PostReplace(url, params, bFstLvlPop, bModal, callBackFunc){
//
//		gf_PostOpen("/common/jsp/comp/budget/bdgtSelect.jsp", null,
//				"toolbar=no,scrollbars=no,width=665,height=600", bdgtParams,
//				true, true, "f_callBackFuncBdgtSelect");

		gf_PostOpen("/trip/cityTransp/cityTranspDraft.jsp", null,
				"toolbar=no,scrollbars=yes,width=1024,height=700", {},
				true, true, "f_RefreshList");

	});

	$("#cityTranspSearch").click(function(){

		cf_RetrieveCityTranspList();

	});

	//기안일 검색 Select Box 선택
	$("select[name='simpleDtCd']").change(function(){
		simpleDtCd = gf_GetValue($("select[name='simpleDtCd']").val());

		if(simpleDtCd == 'DT'){ //기안일 상세검색
			// 기안일 검색 초기화 : 종료일을 내일날짜로 초기화
			$("#detailDt").show();
			$( "input[name='endDate']" ).datepicker( "setDate", gf_GetNextDate() );
		}else{
			$("#detailDt").hide();
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

	ds_DocStsCd.setAllData(gv_DocStsCd);
	ds_DocStsCd.insert(0, {code: '', value: '전체'});

//	<select id="bdgtType" style="width: 250px">
//	<option value="A">특정경비-임원</option>
//	<option value="B">특정경비-팀장</option>
//	<option value="C">특정경비-팀</option>
//	<option value="I">일반경비</option>
//	<option value="O">입찰경비</option>
//	<option value="P">사업경비</option>
//	<option value="R">기술연구원경비</option>
//	<option value="Q">현장경비</option>
//	<option value="K">본사집행현장원가</option>
//</select>

	var	expCls = [
	{ "code":"A","value":"특정경비-임원","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
	{ "code":"B","value":"특정경비-팀장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
	{ "code":"C","value":"특정경비-팀","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
	{ "code":"I","value":"일반경비","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
	{ "code":"O","value":"입찰경비","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
	{ "code":"P","value":"사업경비","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
	{ "code":"S","value":"사업경비-현장코드","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
	{ "code":"R","value":"기술연구원경비","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
	{ "code":"Q","value":"현장경비","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
	{ "code":"K","value":"본사집행현장원가","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" }
	 ];

	ds_ExpCls.setAllData(expCls);
	ds_ExpCls.insert(0, {code: '', value: '전체'});

	ds_AcctStsCd.setAllData(gv_AcctStsCd);
	ds_AcctStsCd.insert(0, {code: '', value: '연동전'});


	if(gv_AuthList.auth[0].code.match("RO_CMAS_TS_ADM") != null){
		// 전사출장조회권한
	}else{
		// 관리자가 아닌 경우
		cf_RetrieveCityTranspList();
	}

}

/**
* @class 업무시스템과 업무분류에 따른 양식문 Select Box를 구성한다.
* @author 방유성
* @since 2013-07-09
* @version 1.0
*/
function cf_RetrieveCityTranspList(){

	var fnlEditUserId = $("#empInput_id").val();
	var doOrgCd = $("#orgNmInput").val();
	var expCls = $("#expCls").val();
	var signStsCd = $("#signStsCd").val();

	var refNo = $("#refNoInput").val();

	var adminYn = "N";

	if(gv_AuthList.auth[0].code.match("RO_CMAS_TS_ADM") != null) adminYn = "Y";

	//해당 업무구분과 업무분류에 해당되는 양식명을 가져옴
	var params = {
			fnlEditUserId : fnlEditUserId,
			doOrgCd : doOrgCd,
			expCls : expCls,
			signStsCd : signStsCd,
			adminYn : adminYn,
			refNo : refNo,
			simpleDt : gf_GetValue($("select[name='simpleDtCd']").val()), 		//기안일 검색
			docStart : gf_GetValue($( "input[name='startDate']" ).val()), 		//기안일 검색 : 시작일
			docEnd : gf_GetValue($( "input[name='endDate']" ).val()) 			//기안일 검색 : 종료일
	};
	gf_Transaction("SELECT_CITY_TRANSP_LIST", "/trip/cityTransp/retrieveCityTranspList.xpl", params, {}, "f_Callback", true);
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
	  	case "SELECT_RC_SIGN_FORM_LIST" :
	  		ds_RcSignFormList.setAllData(resultData.ds_RcSignForm);

	  		for(var o = 0; ds_RcSignFormList.size() > o ;o++){

				if(ds_RcSignFormList.get(o,"msignYn") == "Y"){
					ds_RcSignFormList.set(o,"TempMsignYn","2단");

				}else{
					ds_RcSignFormList.set(o,"TempMsignYn","1단");
				}

				for(var i = 0; i < dsDutySubSys.size(); i++){
					if(dsDutySubSys.get(i, "code") == ds_RcSignFormList.get(o, "sysSubCd")){
						ds_RcSignFormList.set(o, "sysSubCd", dsDutySubSys.get(i, "value"));
					}
				}
			}

	  		/**
	  		 * 목록 조회 후 부가 정보 표출
	  		 */
	  		$("#rcSignbxNmNavi").text(v_rcsignbxNm); 	//문서함 네비게이션
	  		var rcCount = "["+ds_RcSignFormList.size()+"]";
	  		$("#rcListCnt").text(rcCount); 	//문서함 목록
			break;

	  	case "SELECT_SIGN_FORM_LIST" :
	  		for(var i = 0 ; i < resultData.ds_SignForm.length; i++){
	  			if($.trim(resultData.ds_SignForm[i].fstRegUserNm1 ) == "[ ]")
	  				resultData.ds_SignForm[i].fstRegUserNm1 = "";
	  		}

	  		ds_SignFormList.setAllData(resultData.ds_SignForm);

	  		for(var i = 0; ds_SignFormList.size() > i ;i++){

				if(ds_SignFormList.get(i,"msignYn") == "Y"){
					ds_SignFormList.set(i,"TempMsignYn","2단");

				}else{
					ds_SignFormList.set(i,"TempMsignYn","1단");
				}
				for(var p = 0; p < dsDutySubSys.size(); p++){
					if(dsDutySubSys.get(p, "code") == ds_SignFormList.get(i, "sysSubCd")){
						ds_SignFormList.set(i, "sysSubCd", dsDutySubSys.get(p, "value"));
					}
				}
			}
	  		/**
	  		 * 목록 조회 후 부가 정보 표출
	  		 */
	  		gf_SetBoxInfo(v_signbxNm, ds_SignFormList.size());
			break;
			/**
			 * 문서함 공지 보여주기 20140724
			 */
	  	case "SELECT_BOX_NOTICE" :
	  		ds_BoxNoticeList.setAllData(resultData.ds_BoxNoticelnDtl);
	  		for(var i=0; i<resultData.ds_BoxNoticelnDtl.length; i++){
	  			// 해당 문서함 찾기
	  			if(resultData.ds_BoxNoticelnDtl[i].signbxCd == v_signbxCd){
	  				v_BoxNoticeIdx = i;
	  				// 문서함 공지 사용 안하는 경우
	  				if(resultData.ds_BoxNoticelnDtl[i].useYn == "N"){
	  					$("#noticeDiv").css("display","none");			// 숨김
	  				}else{	// 문서함 공지 사용하는 경우
	  					// 한글인 경우
	  					if(v_loclCd=="ko_KR"){
		  					$("#noticeTitleSpan").html(resultData.ds_BoxNoticelnDtl[i].titleKr);	// ### 20140725
	  					}else{	// 영문인 경우
		  					$("#noticeTitleSpan").html(resultData.ds_BoxNoticelnDtl[i].titleEn);	// ### 20140725
	  					}
	  					$("#noticeDiv").css("display","table-cell");	// 보임
	  				}
	  				break;
	  			}
	  		}
	  		break;

	  	case "SELECT_CITY_TRANSP_LIST" :
	  		ds_CityTranspList.setAllData(resultData.ds_CityTranspList);

	  		/**
	  		 * 목록 조회 후 부가 정보 표출
	  		 */
	  		var rcCount = "["+ds_CityTranspList.size()+"]";
	  		$("#cityTranspListCnt").text(rcCount); 	//문서함 목록

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
function grd_List_oncelldbclick(grid, rowid, iRow, iCol, boxid)
{
	var rowData = $("#cityTranspList").data(rowid);

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

//	gf_PostReplace("/trip/cityTransp/cityTranspViewDoc.jsp", viewDocParam, true, true, null);


	// 임시저장일 경우
	if(docSts == "D16"){
		gf_PostOpen("/trip/cityTransp/cityTranspDraft.jsp", null,
				"toolbar=no,scrollbars=yes,width=1024,height=700", viewDocParam,
				true, true, "f_RefreshList");

	}else{
		gf_PostOpen("/trip/cityTransp/cityTranspViewDoc.jsp", null,
				"toolbar=no,scrollbars=yes,width=1024,height=700", viewDocParam,
				true, true, "f_RefreshList");

	}

}

function f_RefreshList(){
	cf_RetrieveCityTranspList();
}