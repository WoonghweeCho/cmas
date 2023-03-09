/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
var ds_OuterTripList = new DataSet();	// 문서함 공지사항 목록 20140724
var ds_CmasList = new DataSet();

var ds_ExpCls = new DataSet();

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
		//var userInfo = data.orgNm + " " + data.userPositCd + " " + data.userKnm + "(" + data.userId + ")";
//		$("#userInfo").val(userInfo);
	});

	//기안일 검색 Date Component 초기화
	$( "input[name='startDate']" ).mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
	$( "input[name='endDate']" ).mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });

	//문서함 목록 JQGrid
	var outerTripList = {
		datatype: "local",
	   	colNames:[gf_FindLang('출장시작일'),gf_FindLang('일자'),gf_FindLang('소속팀'),gf_FindLang('집행팀'),gf_FindLang('출장자'),gf_FindLang('목적지'),gf_FindLang('출장기간'),gf_FindLang('경비구분'),gf_FindLang('예산내역'),gf_FindLang('결재문서번호'),gf_FindLang('결재상태'),gf_FindLang('회계상태'),gf_FindLang('정산서'),gf_FindLang('정산구분'),gf_FindLang('REF NO'),
	   	          gf_FindLang('userId'), gf_FindLang('userNm'),gf_FindLang('docSts'),gf_FindLang('acctSts'),gf_FindLang('ifParam'),gf_FindLang('adjustDocSts'),gf_FindLang('inchrgAir'),gf_FindLang('afare')
	   	          ],
	   	colModel:[
	   	        {name:'stayStYmd',index:'stayStYmd', width:120,align: "center",
	   	        	formatter:function(val, gOpt, row){
		  	   			if(val != ""){
		  	   				val = $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", val));
		  	   			}
	  	   	 			return val;
		  	   		}
	   	        },
	  	   		{name:'fstRegDt',index:'fstRegDt', width:120,align: "center",
		  	   		formatter:function(val, gOpt, row){
		  	   			val = val.substr(0, 10);
	  	   	 			return val;
		  	   		}
	  	   		},
	  	   		{name:'orgNm',index:'orgNm', width:100,align: "center"},
	  	   		{name:'execDept',index:'execDept', width:100,align: "center", hidden : true,
		  	   		formatter:function(val, gOpt, row){

		  	   			if(row.ifParam == "") return "";

		  	   			var data = JSON.parse(row.ifParam);
	  	   	 			var rVal = data.kostVNm + " (" + data.kostV +")";
		  	   	 		if((data.kostVNm == "" || data.kostV == "") || (data.kostVNm == undefined || data.kostV == undefined)){
	  	   					rVal = "";
	  	   				}
		  	   	 		if(row.docSts == "D16"){
		  	   	 			if(data.kostvnm != undefined && data.kostv != undefined){
		  	   	 				rVal = data.kostvnm + " (" + data.kostv +")";
		  	   	 			}else{
		  	   	 				rVal = "";
		  	   	 			}
		  	   	 		}
		  	   	 		if(row.docSts == "D50"){
		  	   	 			rVal = data.kostvnm + " (" + data.kostv +")";
		  	   	 		}
			  	   	 	if(row.docSts == "D60"){
		  	   	 			rVal = data.kostvnm + " (" + data.kostv +")";
		  	   	 		}
				  	   	if(row.docSts == "D70"){
		  	   	 			rVal = data.kostvnm + " (" + data.kostv +")";
		  	   	 		}
	  	   	 			return rVal;
	  	   			}
	  	   		},
		   		{name:'userIdNm',index:'userIdNm', width:100,align: "center",
		  	   		formatter:function(val, gOpt, row){
	  	   	 			var rVal = row.userKnm + " (" + row.userId +")";
		  	   	 		if(row.userKnm == "" || row.userId == ""){
	  	   					rVal = "";
	  	   				}
		  	   	 		if(row.docSts == "D16" || row.docSts == "D50" || row.docSts == "D60" || row.docSts == "D70"){
		  	   	 			if(row.ifParam == "") return "";
		  	   	 			var data = JSON.parse(row.ifParam);
		  	   	 			rVal = data.tripUserKnm + " (" + data.tripUserId +")";

		  	   	 		}
	  	   	 			return rVal;
	  	   			}
		   		},
		   		{name:'natNm',index:'natNm', width:120,align: "center"},
		   		{name:'tripDate',index:'tripDate', width:160,align: "center",
		   			formatter:function(val, gOpt, row){
		   				var rVal = "";

		  	   			if(row.ifParam == "") return "";
		  	   			var data = JSON.parse(row.ifParam);
			  	 		var startDate = data.startDate;
				   		if(startDate != "" && startDate != undefined){
				   			startDate = $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", startDate));
				   		}else{
				   			return "";
				   		}
			  	   		var endDate = data.endDate;
			  	   		if(endDate != "" && endDate != undefined){
				  	   		endDate = $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", endDate));
				  	   	}else{
				  	   		return "";
				  	   	}

				  	   	// 출장기간
				  	   	var tripDate = startDate + " ~ " + endDate;
				  	   	rVal = tripDate;

	  	   	 			return rVal;
	  	   			}
		   		},
		   		{name:'expCls',index:'expCls', width:170,align: "center", hidden : true,
		   			formatter:function(val, gOpt, row){

		   				if(row.ifParam == "") return "";
		  	   			var data = JSON.parse(row.ifParam);

		   				if(row.docSts == "D16"){
		   					if(data.bdgtType != undefined){
		   						val = ds_ExpCls.get(ds_ExpCls.find("code", data.bdgtType)).value;
		   					}else{
		   						return "";
		   					}
		   				}else{
		   					if(ds_ExpCls.find("code", data.bdgtType) != -1){
			   					val = ds_ExpCls.get(ds_ExpCls.find("code", data.bdgtType)).value;
			   				}
		   				}
	  	   				return val;
	  	   			}
		   		},
		   		{name:'bdgtItem',index:'bdgtItem', width:170,align: "center", hidden : true,
		   			formatter:function(val, gOpt, row){

		   				if(row.ifParam == "") return "";

		  	   			var data = JSON.parse(row.ifParam);
		   				val = data.kText;
		   				if(row.docSts == "D16"){
		   					if(data.kText != undefined){
		   						val = data.kText;
		   					}else{
		   						val = "";
		   					}
		   				}
	  	   				return val;
	  	   			}
		   		},
		   		{name:'signId',index:'signId', width:120,align: "center", hidden : true},
		   		{name:'dSts',index:'dSts', width:100,align: "center",
		   			formatter:function(val, gOpt, row){

		   				var rVal = "";
		   				if(ds_DocStsCd.find("code", row.docSts) != -1){
		   					rVal = ds_DocStsCd.get(ds_DocStsCd.find("code", row.docSts)).value;
		   				}
		   				if(row.docSts.substr(0, 1) == "A"){
		   					rVal = "결재완료";
		   				}
	  	   				return rVal;
	  	   			}
		   		},
		   		{name:'aSts',index:'aSts', width:80,align: "center",
		   			formatter:function(val, gOpt, row){

		   				var rVal;
		   				if(ds_AcctStsCd.find("code", row.acctSts) != -1){
		   					rVal = ds_AcctStsCd.get(ds_AcctStsCd.find("code", row.acctSts)).value;
		   				}
	  	   				return rVal;
	  	   			}
		   		},
		   		{name:'adjust',index:'adjust', width:100,align: "center",
		   			formatter:function(val, gOpt, row){
		   				var spanCss = {width : "100%", height: "100%", backgroundColor:"#ffcccc"};
		   				var rVal = "";
		   				if(row.docSts == "D03"){
		   					if(row.adjustDocSts == "" && row.inchrgAir != ""){
		   						rVal = "정산서작성";
		   					}else if(row.adjustDocSts == "" && row.inchrgAir == ""){
		   						if(row.afare == "0"){
		   							rVal = "정산서작성";
		   						}else{
		   							rVal = "항공료입력전";

		   						}

		   					}else{
		   						rVal = ds_DocStsCd.get(ds_DocStsCd.find("code", row.adjustDocSts)).value;
		   					}
		   					return $("<span>").append($("<div>").text(rVal).css(spanCss)).html();
		   				}
		   				return rVal;
	  	   			}
		   		},
		   		{name:'stlCls',index:'stlCls', width:40,align: "center",
		   			formatter:function(val, gOpt, row){
		   				if(val == "N"){
		   					val = "일반";
		   				}else if(val == "R"){
		   					val = "반납";
		   				}else if(val == "A"){
		   					val = "추가";
		   				}

	  	   				return val;
	  	   			}
		   		},
		   		{name:'refNo',index:'refNo', width:100,align: "center"},
		   		{name:'userId',index:'userId', width:170,align: "center", hidden : true},
		   		{name:'userKnm',index:'userKnm', width:170,align: "center", hidden : true},
		   		{name:'docSts',index:'docSts', width:170,align: "center", hidden : true},
		   		{name:'acctSts',index:'acctSts', width:170,align: "center", hidden : true},
		   		{name:'ifParam',index:'ifParam', width:170,align: "center", hidden : true},
		   		{name:'adjustDocSts',index:'adjustDocSts', width:170,align: "center", hidden : true},
		   		{name:'inchrgAir',index:'inchrgAir', width:170,align: "center", hidden : true},
		   		{name:'afare',index:'afare', width:170,align: "center", hidden : true}
	   	],
	   	autowidth:true,
	   	height:450,
	   	pager: "outerTripListPager",
	   	rowNum :20,
		rowList : [10, 20, 30],
//	   	sortname: 'fstRegDt',
	    viewrecords: true,
//	    sortorder: "desc",
	    sortable: true,
	    ondblClickRow: function(rowid, iRow, iCol, boxid, e){
	    	if(iCol != 12){
	    		grd_List_oncelldbclick(this, rowid, iRow, iCol, "1");
	    	}else if(iCol == 12){
	    		grd_List_oncelldbclick2(this, rowid, iRow, iCol, "1");
	    	}
	     }
	};

	$("#outerTripList").jqGrid(outerTripList);
	$("#outerTripList").jqGrid('navGrid','#outerTripListPager',{search:false,edit:false,add:false,del:false});


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
	ds_OuterTripList.bind($("#outerTripList"));

	ds_DocStsCd.bind($("#signStsCd")[0], {val: "code", text: "value"});

	ds_DocStsCd.bind($("#adjustStsCd")[0], {val: "code", text: "value"});

}

/**
* @class Form Onload 시 Element/Component 인벤트 초기화
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetEventListener()
{
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

	$("#outerTripDraft").click(function(){


//		gf_PostReplace("/trip/outerTrip/outerTripDraft.jsp", {}, true, true, null);

//		gf_PostReplace(url, params, bFstLvlPop, bModal, callBackFunc){
//
//		gf_PostOpen("/common/jsp/comp/budget/bdgtSelect.jsp", null,
//				"toolbar=no,scrollbars=no,width=665,height=600", bdgtParams,
//				true, true, "f_callBackFuncBdgtSelect");


		var params = {
				cmasList : ds_CmasList.getAllData()
		}

		gf_PostOpen("/trip/outerTrip/outerTripDraft.jsp", null,
				"toolbar=no,scrollbars=yes,width=1024,height=700", params,
				true, true, "f_RefreshList");

	});

	$("#outerTripSearch").click(function(){
		cf_RetrieveOuterTripList();
	});

	//닫기버튼 클릭
	$("#closeBtn").click(function(){
		self.close();
	});

	$("#jBtn").click(function(){

		ds_OuterTripList.filter(
				function (DataSetRow) {
					if ( DataSetRow.get("adjustDocSts") == "" && DataSetRow.get("inchrgAir") != "" ) {
						return true;
					}



					return false;
				}
			);

	});

	$('#notiClose').click(function(){
		$('#emmisionLayerDiv').fadeOut();
		return false;
	});

	$("#outerTripError").click(function(){

		gf_PostOpen("/trip/outerTrip/outerTripError.jsp", null,
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

	var	expCls = [
	   	   	{ "code":"A","value":"특정경비-임원","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
	   	   	{ "code":"B","value":"특정경비-팀장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
	   	   	{ "code":"C","value":"특정경비-팀","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
	   	   	{ "code":"I","value":"일반경비","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
	   	   	{ "code":"O","value":"입찰경비","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
	   	   	{ "code":"P","value":"사업경비","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
	   	   	{ "code":"R","value":"기술연구원경비","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
	   	   	{ "code":"Q","value":"현장경비","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
	   	   	{ "code":"K","value":"본사집행현장원가","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" }
	   	   	 ];

	ds_ExpCls.setAllData(expCls);
	ds_ExpCls.insert(0, {code: '', value: '전체'});

	ds_DocStsCd.setAllData(gv_DocStsCd);
	ds_DocStsCd.insert(0, {code: '', value: '전체'});

	ds_AcctStsCd.setAllData(gv_AcctStsCd);
	ds_AcctStsCd.insert(0, {code: '', value: '연동전'});

	cf_RetrieveOuterTripList();

	// 공지사항 팝업
//	var top = (($(window).width() - $("#emmisionLayerDiv").outerWidth()) / 2) + $(window).scrollLeft();
//	var left = (($(window).height() - $("#emmisionLayerDiv").outerHeight()) / 2) + $(window).scrollTop();

	var top = 0;
	var left = 0;

//	$("#emmisionLayerDiv").css({position:"absolute", top : (offset.top + $(e.target).height()), left:(offset.left + $("#emmisionLayerDiv").width())});
	$("#emmisionLayerDiv").css({position:"absolute", top : top, left : left});


	$("#emmisionLayerDiv").show();

	if(gv_AuthList.auth[0].code.match("RO_CMAS_CO_TRIP_ADM") != null){

		//에러 확인 페이지 오픈
		$("#outerTripError").show();
	}else{
		// 관리자가 아닌 경우
		cf_RetrieveOuterTripList();
	}


}

/**
 * 해외출장 리스트 조회
 */
function cf_RetrieveOuterTripList(){

	var userNm = $("#empNm").val();
	var orgNm = $("#orgNmInput").val();
	var refNo = $("#refNo").val();
	var signStsCd = $("#signStsCd").val();
	var adjustStsCd = $("#adjustStsCd").val();

	var adminYn = "N";

	if(gv_AuthList.auth[0].code.match("RO_CMAS_CO_TRIP_ADM") != null){	//에러는 관리자만
		adminYn = "Y";
/*	}else if(gv_AuthList.auth[0].code.match("RO_CMAS_OT_003")){
		adminYn = "Y";
	}else if(gv_AuthList.auth[0].code.match("RO_CMAS_OT_001")){
		adminYn = "Y";
*/
	}

	//해당 업무구분과 업무분류에 해당되는 양식명을 가져옴
	var params = {
			userNm : userNm,
			orgNm : orgNm,
			refNo : refNo,
			signStsCd : signStsCd,
			adjustStsCd : adjustStsCd,
			adminYn : adminYn,
			simpleDt : gf_GetValue($("select[name='simpleDtCd']").val()), 		//기안일 검색
			docStart : gf_GetValue($( "input[name='startDate']" ).val()), 		//기안일 검색 : 시작일
			docEnd : gf_GetValue($( "input[name='endDate']" ).val()) 			//기안일 검색 : 종료일
	};
	gf_Transaction("SELECT_OUTER_TRIP_LIST", "/trip/outerTrip/retrieveOuterTripError.xpl", params, {}, "f_Callback", true);

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
	  	case "SELECT_OUTER_TRIP_LIST" :
	  		ds_OuterTripList.setAllData(resultData.ds_OuterTripList);
	  		ds_CmasList.setAllData(resultData.ds_CmasList);

	  		if(gv_AuthList.auth[0].code.match("RO_CMAS_TS_ADM") == null){
	  			// filter
	  			ds_OuterTripList.filter(
						function (DataSetRow) {
							if ( DataSetRow.get("fstRegUserId") != gv_userId && DataSetRow.get("docSts") == "D16" ) {
								return false;
							}
							return true;
						}
				);
	  		}

	  		var rcCount = "["+ds_OuterTripList.size()+"]";
	  		$("#outerTripListCnt").text(rcCount); 	//문서함 목록
			break;

	  	default :
	  		break;
	  }
}







/**
* @class 조건에 맞는 결재양식 목록을 조회한다.
* @author 고준석
* @since 2013-04-04
* @version 1.0
*/
function cf_RetrieveSignFormList(){

	var selectParams = $("#searchForm").serializeObject();


	//양식명이 Select Box 인지 Input Text 인지 검사하여 해당 되는 값을 파라미터에 Set 한다.
    var signFormNmParam;
//    if($("select[name='signFormNmSelect']").css("display") == "none"){
//
//    	signFormNmParam = gf_GetValue($("input[name='signFormNmInput']").val());
//
//    }else{
//        signFormNmParam = gf_GetValue($("select[name='signFormNmSelect']").val());
//
//        //양식명의 전체일 경우 공란을 셋팅 한다.
//        if(signFormNmParam == "ALL"){
//        	signFormNmParam = "";
//        }
//    }

    signFormNmParam = gf_GetValue($("input[name='signFormNmInput']").val());

    /**
     * 문서함 목록 검색
     *
     * [검색조건]
     *  1. 업무구분
     *  2. 양식분류
     *  3. 문서번호
     *  4. 양식명
     *  5. 문서제목
     *  6. 기안자
     *  7. 기안일
     */
    $.extend(selectParams, {

//		addSysCd : "DWEP030007",

		sysCd : gf_GetValue($("select[name='sysCd']").val()), //업무구분(업무시스템)
		sysSubCd : gf_GetValue($("select[name='sysSubCd']").val()), //업무분류(업무시스템)
		signFormNm : signFormNmParam
//		sysSubCd : gf_GetValue($("select[name='sysSubCd']").val()),			//업무분류
//		signFormTpCd : gf_GetValue($("select[name='signFormTpCd']").val()), //양식유형
//		signFormNm : gf_GetValue($( "input[name='signFormNm']" ).val())		//양식명
    });

    gf_Transaction("SELECT_SIGN_FORM_LIST", "/sn/sign/retrieveSignFormList.xpl", selectParams, {}, "f_Callback", true);
}

/**
* @class 조건에 맞는 결재양식 목록을 조회한다.
* @author 고준석
* @since 2013-04-04
* @version 1.0
*/
function cf_RetrieveRcSignFormList(){

	var selectParams = $("#searchForm").serializeObject();

	//양식명이 Select Box 인지 Input Text 인지 검사하여 해당 되는 값을 파라미터에 Set 한다.
    var signFormNmParam;
//    if($("select[name='signFormNmSelect']").css("display") == "none"){
//
//    	signFormNmParam = gf_GetValue($("input[name='signFormNmInput']").val());
//
//    }else{
//        signFormNmParam = gf_GetValue($("select[name='signFormNmSelect']").val());
//
//        //양식명의 전체일 경우 공란을 셋팅 한다.
//        if(signFormNmParam == "ALL"){
//        	signFormNmParam = "";
//        }
//    }

	signFormNmParam = gf_GetValue($("input[name='signFormNmInput']").val());


    /**
     * 문서함 목록 검색
     *
     * [검색조건]
     *  1. 업무구분
     *  2. 양식분류
     *  3. 문서번호
     *  4. 양식명
     *  5. 문서제목
     *  6. 기안자
     *  7. 기안일
     */
    $.extend(selectParams, {
		sysCd : gf_GetValue($("select[name='sysCd']").val()), //업무구분(업무시스템)
		sysSubCd : gf_GetValue($("select[name='sysSubCd']").val()), //업무분류(업무시스템)
		signFormNm : signFormNmParam
//		sysSubCd : gf_GetValue($("select[name='sysSubCd']").val()),			//업무분류
//		signFormTpCd : gf_GetValue($("select[name='signFormTpCd']").val()), //양식유형
//		signFormNm : gf_GetValue($( "input[name='signFormNm']" ).val())		//양식명
//		drftUserId : $("#drftUserId input[id$='_id']").val() 				//기안자
    });

    gf_Transaction("SELECT_RC_SIGN_FORM_LIST", "/sn/sign/retrieveRcSignFormList.xpl", selectParams, {}, "f_Callback", true);
}


/**
* @class 작성함의 양식목록 더블클릭시 기안작성화면 오픈
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function grd_List_oncelldbclick(grid, rowid, iRow, iCol, boxid)
{
	var rowData = $("#outerTripList").data(rowid);


//	var dutyLnkCd = rowData.get("dutyLnkCd");
//	var dutySysCd = rowData.get("sysCd");		//TODO : 문서번호 체계에 따라 변경
//	var signDocfile = rowData.get("signFormDat");
//	var signDocTitle = rowData.get("signFormNm");
//	var signFormTpCd = rowData.get("signFormTpCd");

	var cmasId = rowData.get("docNo");
	var acctSts = rowData.get("acctSts");
	var retResn = rowData.get("retResn");
	var docSts = rowData.get("docSts");
	var adjustDocSts = rowData.get("adjustDocSts");
	var refNo = rowData.get("refNo");
	var signId = rowData.get("signId");

	var viewDocParam = {
			fromList : cmasId,
			acctSts : acctSts,
			retResn : retResn,
			docSts : docSts,
			adjustDocSts : adjustDocSts,
			refNo : refNo,
			signId : signId,
			cmasList : ds_CmasList.getAllData()

	};



//	gf_PostReplace("/trip/innerTrip/innerTripViewDoc.jsp", viewDocParam, true, true, null);

	// 임시저장일 경우
	if(docSts == "D16" || docSts == "D54" || docSts == "D64"){
		gf_PostOpen("/trip/outerTrip/outerTripDraft.jsp", null,
				"toolbar=no,scrollbars=yes,width=1024,height=700", viewDocParam,
				true, true, "f_RefreshList");

	}else if(gv_AuthList.auth[0].code.match("RO_CMAS_OT_003") != null && docSts == "D50"){
		// 해외 SECURITY팀 검토
		gf_PostOpen("/trip/outerTrip/outerTripDraft.jsp", null,
				"toolbar=no,scrollbars=yes,width=1024,height=700", viewDocParam,
				true, true, "f_RefreshList");

	}else if(gv_AuthList.auth[0].code.match("RO_CMAS_OT_001") != null && docSts == "D60"){
		// 글로벌 HR팀 검토
		gf_PostOpen("/trip/outerTrip/outerTripDraft.jsp", null,
				"toolbar=no,scrollbars=yes,width=1024,height=700", viewDocParam,
				true, true, "f_RefreshList");
	}else if(gv_AuthList.auth[0].code.match("RO_CMAS_OT_004") != null && docSts == "D70"){
		// 경영관리팀 검토
		gf_PostOpen("/trip/outerTrip/outerTripDraft.jsp", null,
				"toolbar=no,scrollbars=yes,width=1024,height=700", viewDocParam,
				true, true, "f_RefreshList");

	}else if(docSts == "D50" || docSts == "D60" || docSts == "D70"){
		// 겸토중일때의 사용자 View
		gf_PostOpen("/trip/outerTrip/outerTripDraftReadOnly.jsp", null,
				"toolbar=no,scrollbars=yes,width=1024,height=700", viewDocParam,
				true, true, "f_RefreshList");

	}else{
		gf_PostOpen("/trip/outerTrip/outerTripViewDoc.jsp", null,
				"toolbar=no,scrollbars=yes,width=1024,height=700", viewDocParam,
				true, true, "f_RefreshList");
	}
}

/**
* @class 작성함의 양식목록 더블클릭시 기안작성화면 오픈
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function grd_List_oncelldbclick2(grid, rowid, iRow, iCol, boxid)
{
	var rowData = $("#outerTripList").data(rowid);


//	var dutyLnkCd = rowData.get("dutyLnkCd");
//	var dutySysCd = rowData.get("sysCd");		//TODO : 문서번호 체계에 따라 변경
//	var signDocfile = rowData.get("signFormDat");
//	var signDocTitle = rowData.get("signFormNm");
//	var signFormTpCd = rowData.get("signFormTpCd");

	var cmasId = rowData.get("docNo");
	var acctSts = rowData.get("acctSts");
	var retResn = rowData.get("retResn");
	var docSts = rowData.get("docSts");
	var adjustDocSts = rowData.get("adjustDocSts");
	var refNo = rowData.get("refNo");
	var signId = rowData.get("signId");
	var inchrgAir = rowData.get("inchrgAir");
	var afare = rowData.get("afare");

	var viewDocParam = {
			fromList : cmasId,
			acctSts : acctSts,
			retResn : retResn,
			docSts : docSts,
			adjustDocSts : adjustDocSts,
			refNo : refNo,
			signId : signId,
			cmasList : ds_CmasList.getAllData()
	};

	if(docSts.substr(0, 1) == "A" || docSts == "D03"){
		// 임시저장일 경우
		if(adjustDocSts == "D16" || adjustDocSts == "D64"){
			gf_PostOpen("/trip/outerTrip/outerTripAdjust.jsp", null,
					"toolbar=no,scrollbars=yes,width=1024,height=700", viewDocParam,
					true, true, "f_RefreshList");

		}else if(adjustDocSts == "D61"){
			// 반납 확인중
			gf_PostOpen("/trip/outerTrip/outerTripAdjust.jsp", null,
					"toolbar=no,scrollbars=yes,width=1024,height=700", viewDocParam,
					true, true, "f_RefreshList");
		}else if(docSts =="D03" && adjustDocSts == "" && inchrgAir != ""){
			gf_PostOpen("/trip/outerTrip/outerTripAdjust.jsp", null,
					"toolbar=no,scrollbars=yes,width=1024,height=700", viewDocParam,
					true, true, "f_RefreshList");
		}else if(docSts =="D03" && adjustDocSts == "" && inchrgAir == ""){
			if(afare == "0"){
				gf_PostOpen("/trip/outerTrip/outerTripAdjust.jsp", null,
						"toolbar=no,scrollbars=yes,width=1024,height=700", viewDocParam,
						true, true, "f_RefreshList");
			}else{
				alert('여행사에서 항공료 확정 이후 정산서 작성이 가능합니다.');
			}
		}else if(gv_AuthList.auth[0].code.match("RO_CMAS_OT_003") != null && adjustDocSts == "D50"){
			// 해외 SECURITY팀 검토
			gf_PostOpen("/trip/outerTrip/outerTripAdjust.jsp", null,
					"toolbar=no,scrollbars=yes,width=1024,height=700", viewDocParam,
					true, true, "f_RefreshList");

		}else if(gv_AuthList.auth[0].code.match("RO_CMAS_OT_001") != null && adjustDocSts == "D60"){
			// 글로벌 HR팀 검토
			gf_PostOpen("/trip/outerTrip/outerTripAdjust.jsp", null,
					"toolbar=no,scrollbars=yes,width=1024,height=700", viewDocParam,
					true, true, "f_RefreshList");

		}else if(adjustDocSts == "D50" || adjustDocSts == "D60"){
			// 겸토중일때의 사용자 View
			gf_PostOpen("/trip/outerTrip/outerTripAdjustReadOnly.jsp", null,
					"toolbar=no,scrollbars=yes,width=1024,height=700", viewDocParam,
					true, true, "f_RefreshList");

		}else{
			gf_PostOpen("/trip/outerTrip/outerTripAdjustReadOnly.jsp", null,
					"toolbar=no,scrollbars=yes,width=1024,height=700", viewDocParam,
					true, true, "f_RefreshList");
//			gf_PostOpen("/trip/outerTrip/outerTripAdjustViewDoc.jsp", null,
//					"toolbar=no,scrollbars=yes,width=1024,height=700", viewDocParam,
//					true, true, "f_RefreshList");
		}
	}
}

function f_RefreshList(){
	cf_RetrieveOuterTripList();
}

