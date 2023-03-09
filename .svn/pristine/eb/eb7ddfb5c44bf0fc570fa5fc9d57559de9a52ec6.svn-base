/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
var ds_VisaAppnList = new DataSet();
var ds_DocStsCd = new DataSet();
var ds_visaPrgrCd = new DataSet();

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
* 변수 선언 하는 부분
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_InitParam()
{
	v_loclCd = gf_GetCookie("loclCd");
}

/**
* @class Form Onload 시 컴포넌트 초기화
* 특별한 기능 있는 부분을 정의하는 부분
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetComponents()
{

	// 직원 검색기 달기
	gf_SetUserComponent($("#empInput"), function(data){

		// 검색기가 닫힌 후 수행되는 로직들
		var userInfo = data.orgNm + " " + data.userPositCd + " " + data.userKnm + "(" + data.userId + ")";
	});

	gf_SetUserComponent($("#visaUserInput"), function(data){
		var userInfo = data.orgNm + " " + data.userPositCd + " " + data.userKnm + "(" + data.userId + ")";
//		$("#userInfo").val(userInfo);
	});

	//기안일 검색 Date Component
	$( "input[name='startDate']" ).mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
	$( "input[name='endDate']" ).mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });

	//비자신청 목록 JQGrid
	var visaAppnList = {
		datatype: "local",
	   	colNames:[gf_FindLang('작성일자'),gf_FindLang('작성자'),gf_FindLang('발급대상자 소속'), gf_FindLang('발급 대상자'), gf_FindLang('출국 예정일'),gf_FindLang('결재문서번호'),gf_FindLang('결재상태'),gf_FindLang('처리상태'),
	   	          gf_FindLang("소속팀이름"), gf_FindLang("소속팀코드"), gf_FindLang("출국일"), gf_FindLang('작성자 아이디'), gf_FindLang('작성자 이름'), gf_FindLang('출장자 아이디'), gf_FindLang('출장자 이름'),
	   	          gf_FindLang('발급대상자 소속 이름'), gf_FindLang('결재상태 코드 '),gf_FindLang('처리상태 코드')],
	   	colModel:[
	  	   		{name:'fstRegDt',index:'fstRegDt', width:80,align: "center",
		  	   		formatter:function(val, gOpt, row){
		  	   			if(val.length > 19){
		  	   				val = val.substr(0, 19);
		  	   			}
	  	   	 			return val;
		  	   		}
	  	   		},
	  	   		{name:'drafter',index:'drafter', width:60,align: "center",
	  	   			formatter:function(val, gOpt, row){

	  	   				// val : 원래 표현되는 값
	  	   	 			var rVal = row.fstRegUserNm + " (" + row.fstRegUserId +")";
		  	   	 		if(row.fstRegUserNm == "" || row.fstRegUserId == ""){
	  	   					rVal = "";
	  	   				}
		  	   	 		// return val : 최종적으로 표현될 값
	  	   	 			return rVal;
	  	   			}
	  	   		},
	  	   		{name:'draftUserOrg',index:'draftUserOrg', width:100,align: "center",
	  	   			formatter:function(val, gOpt, row){

	  	   	 			var rVal = row.userDeptNm;
	  	   	 			return rVal;
	  	   			}
	  	   		},
	  	   	 	{name:'visaUser',index:'visaUser', width:60,align: "center",
	  	   			formatter:function(val, gOpt, row){

	  	   	 			var rVal = row.userKnm + " (" + row.userId +")";
	  	   	 			return rVal;
	  	   			}
	  	   		},
		   		{name:'tripYMD',index:'tripYMD', width:80,align: "center",
	  	   	 		formatter:function(val, gOpt, row){

	  	   	 			var ymdVal = $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd" , row.departScdDd));

		  	   	 		if(row.departScdDd == ""){
		  	   	 			ymdVal = "";
	  	   				}
	  	   	 			return ymdVal;
	  	   	 		}
	  	   	 	},
	  	   	 	//결재문서 번호
	  	   	 	{name:'signId',index:'signId', width:80,align: "center"},
	  	   	 	//문서상태
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
		   		//비자처리상태
	  	   	 	{name:'pSts',index:'pSts', width:50,align: "center",
		   			formatter:function(val, gOpt, row){

		   				var rVal;
		   				if(ds_visaPrgrCd.find("code", row.visaPrgrSts) != -1){
		   					rVal = ds_visaPrgrCd.get(ds_visaPrgrCd.find("code", row.visaPrgrSts)).value;
		   				}else{
		   					rVal = "";
		   				}
	  	   				return rVal;
	  	   			}
		   		},
		   		{name:'drafterOrgNm',index:'drafterOrgNm', width:170,align: "center", hidden: true},
		   		{name:'drafterOrgCd',index:'drafterOrgCd', width:170,align: "center", hidden: true},
		   		{name:'departScdDd',index:'departScdDd', width:170,align: "center", hidden: true},
		   		{name:'fstRegUserId',index:'fstRegUserId', width:170,align: "center", hidden: true},
		   		{name:'fstRegUserNm',index:'fstRegUserNm', width:170,align: "center", hidden: true},
		   		{name:'userId',index:'userId', width:170,align: "center", hidden: true},
		   		{name:'userDeptNm',index:'userDeptNm', width:170,align: "center", hidden: true},
		   		{name:'userKnm',index:'userKnm', width:170,align: "center", hidden: true},
		   		{name:'docSts',index:'docSts', width:80,align: "center", hidden: true},
		   		{name:'visaPrgrSts',index:'visaPrgrSts', width:80,align: "center", hidden: true}
	   	],
	   	autowidth:true,
	   	height:450,
	   	pager : '#visaAppnListPager',
		rowNum :20,
		rowList : [10, 20, 30],
//	   	sortname: 'fstRegDt',
	    viewrecords: true,
//	    sortorder: "desc",
	    // 행을 클릭했을 때 발생하는 이벤트
	    ondblClickRow: function(rowid, iRow, iCol, boxid, e){
	    	grd_List_oncelldbclick(this, rowid, iRow, iCol, '1');
	     }
	};

	$("#visaAppnList").jqGrid(visaAppnList);
	$("#visaAppnList").jqGrid('navGrid','#visaAppnListPager',{search:false,edit:false,add:false,del:false});


	/**
	 * Container 크기에 맞춰 Windows Resizing 될 때 Box Grid의 사이즈를 조절한다.
	 */
	$(window).bind("resize", function(){
//		$("#visaAppnList").setGridWidth($("#visaAppnList").width());
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
	ds_VisaAppnList.bind($("#visaAppnList"));

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
	$("#visaAppnDraft").click(function(){

		gf_PostOpen("/trip/visaAppn/visaAppnDraft.jsp", null,
				"toolbar=no,scrollbars=yes,width=1024,height=700", {},
				true, true, "f_RefreshList");
	});

	$("#visaAppnSearch").click(function(){

		cf_RetrieveVisaAppnList();

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

	$('#notiClose').click(function(){
		$('#emmisionLayerDiv').fadeOut();
		return false;
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

	ds_visaPrgrCd.setAllData(gv_visaPrgrCd);
	ds_visaPrgrCd.insert(0, {code: '', value: ''});

	cf_RetrieveVisaAppnList();

	// 공지사항 팝업
	var top = 0;
	var left = 0;

	$("#emmisionLayerDiv").css({position:"absolute", top : top, left : left});
	$("#emmisionLayerDiv").show();
}

/**
* @class 업무시스템과 업무분류에 따른 양식문 Select Box를 구성한다.
* @author 방유성
* @since 2013-07-09
* @version 1.0
*/
function cf_RetrieveVisaAppnList(){

	var userId = $("#empInput_id").val();
	var orgNm = $("#orgNmInput").val();
	var signStsCd = $("#signStsCd").val();

	var visaUser = $("#visaUserInput_id").val();
	var stDate = $("#stDateInput").val();
	var edDate = $("#edDateInput").val();

	// 출장기간 시작일과 종료일 중 하나만 입력되어 있다면 나머지 날짜를 대체한다.

	var adminYn = "N";

	if(gv_AuthList.auth[0].code.match("RO_CMAS_TS_VISA") != null) adminYn = "Y";		//출장전체 조회권한

	//해당 업무구분과 업무분류에 해당되는 양식명을 가져옴
	var params = {
			userId : userId,
			orgNm : orgNm,
			signStsCd : signStsCd,
			adminYn : adminYn,
			visaUser : visaUser,
			stDate : stDate,
			edDate : edDate,
			simpleDt : gf_GetValue($("select[name='simpleDtCd']").val()), 		//기안일 검색
			docStart : gf_GetValue($( "input[name='startDate']" ).val()), 		//기안일 검색 : 시작일
			docEnd : gf_GetValue($( "input[name='endDate']" ).val()) 			//기안일 검색 : 종료일
	};
	gf_Transaction("SELECT_VISA_APPN_LIST", "/trip/visaAppn/retrieveVisaAppnList.xpl", params, {}, "f_Callback", true);
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
	  	case "SELECT_VISA_APPN_LIST" :
	  		ds_VisaAppnList.setAllData(resultData.ds_VisaAppnList);

	  		if(gv_AuthList.auth[0].code.match("RO_CMAS_TS_VISA") == null){
	  			// filter
		  		ds_VisaAppnList.filter(
						function (DataSetRow) {
							if ( DataSetRow.get("fstRegUserId") != gv_userId && DataSetRow.get("docSts") == "D16" ) {
								return false;
							}
							return true;
						}
				);
	  		}

	  		var rcCount = "["+ds_VisaAppnList.size()+"]";
	  		$("#visaAppnListCnt").text(rcCount); 	//문서함 목록
			break;

	  	default :
	  		break;
	  }
}

/**
* @class 리스트를 더블클릭하면 발생하는 기능
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function grd_List_oncelldbclick(grid, rowid, iRow, iCol)
{
	var rowData = $("#visaAppnList").data(rowid);

	// 행에서 데이터를 뽑음
	var viewDocParam = {
			docNo : rowData.get("docNo"),
			retResn : rowData.get("retResn"),
			docSts : rowData.get("docSts")
	};

	gf_PostOpen("/trip/visaAppn/visaAppnDraft.jsp", null,
				"toolbar=no,scrollbars=yes,width=1024,height=700", viewDocParam,
				true, true, "f_RefreshList");

}


function f_RefreshList(){
	cf_RetrieveVisaAppnList();
}