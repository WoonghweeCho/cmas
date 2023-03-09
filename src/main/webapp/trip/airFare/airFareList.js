/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
var ds_AirFareList = new DataSet();	// 문서함 공지사항 목록 20140724

var ds_DocStsCd = new DataSet();	// 문서함 공지사항 목록 20140724

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

	//출장일 검색 Date Component 초기화
	$( "input[name='startDate']" ).mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
	$( "input[name='endDate']" ).mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });

	//문서함 목록 JQGrid
	var airFareList = {
		datatype: "local",
	   	colNames:[gf_FindLang('출장시작일'),gf_FindLang('출장종료일'),gf_FindLang('출장자사번'),gf_FindLang('출장자이름'),gf_FindLang('영문이름'),gf_FindLang('좌석등급'),gf_FindLang('항공료'),gf_FindLang('문서상태'),gf_FindLang('여행사'),gf_FindLang('문서번호'),gf_FindLang('REF NO'),
	   	       gf_FindLang('출장시작일'),gf_FindLang('출장종료일'),gf_FindLang('좌석코드'), gf_FindLang('ifParam'), gf_FindLang("iAir")
	   	          ],
	   	colModel:[
				{name:'startDate',index:'startDate', width:80,align: "center",
					formatter:function(val, gOpt, row){
	   	        		var data = JSON.parse(row.ifParam);
	   	        		var rVal = data.startDate;
	   	        		if(data.startDate == undefined) rVal = "";
	  	   	 			return rVal;
	  	   			}
				},
				{name:'endDate',index:'endDate', width:80,align: "center",
					formatter:function(val, gOpt, row){
	   	        		var data = JSON.parse(row.ifParam);
	   	        		var rVal = data.endDate;
	   	        		if(data.endDate == undefined) rVal = "";
	  	   	 			return rVal;
	  	   			}
				},

//				{name:'tDate',index:'tDate', width:120,align: "center",
//					formatter:function(val, gOpt, row){
//						// 기본적으로 Sorting 은 기본 옵션
//	   	        		var data = JSON.parse(row.ifParam);
//	   	        		// startDate 와 endDate 를 조합해서 한칸으로 만드는 부분
//	   	        		// sorting 이 되지 않음
//	  	   	 			var rVal = data.startDate + " ~ " + data.endDate;
//	  	   	 			// startDate 로 한칸
//	  	   	 			// endDate 로 한칸 나눠줘야됩니다
//	  	   	 			if(data.startDate == undefined || data.endDate == undefined) rVal = "";
//	  	   				return rVal;
//	  	   			}
//				},
				{name:'userId',index:'userId', width:70,align: "center"},
				{name:'userKnm',index:'userKnm', width:70,align: "center"},
	  	   		{name:'userEnm',index:'userEnm', width:150,align: "center"},
	  	   		{name:'sGrade',index:'sGrade', width:60,align: "center",
		  	   		formatter:function(val, gOpt, row){
	   	        		var rVal = row.seatGrade;
	   	        		if(rVal == "F"){
	   	        			rVal = "First";
	   	        		}else if(rVal == "B"){
	   	        			rVal = "Business";
	   	        		}else if(rVal == "E"){
	   	        			rVal = "Economy";
	   	        		}
	  	   				return rVal;
	  	   			}
	  	   		},
	  	   		{name:'afare',index:'afare', width:50,align: "center"},
	  	   	 	{name:'dSts',index:'dSts', width:50,align: "center",
		   			formatter:function(val, gOpt, row){

		   				var rVal = "입력전";
		   				if(row.inchrgAir == "D"){
		   					rVal = "입력완료";
		   				}else if(row.inchrgAir == "H"){
		   					rVal = "입력완료";
		   				}else if(row.inchrgAir == "J"){
		   					rVal = "입력완료";
		   				}else if(row.inchrgAir == "N"){
	   					rVal = "입력완료";
		   				}
	  	   				return rVal;
	  	   			}
		   		},
		   		{name:'iAir',index:'iAir', width:100,align: "center",
		   			formatter:function(val, gOpt, row){

		   				var rVal = "";
		   				if(row.inchrgAir == "D"){
		   					rVal = "동서여행사";
		   				}else if(row.inchrgAir == "H"){
		   					rVal = "현대드림투어";
		   				}else if(row.inchrgAir == "J"){
		   					rVal = "주원항공여행사";
		   				}else if(row.inchrgAir == "N"){
		   					rVal = "하나투어";
		   				}
	  	   				return rVal;
	  	   			}
		   		},
		   		{name:'docNo',index:'docNo', width:80,align: "center"},
				{name:'refNo',index:'refNo', width:80,align: "center"},
		   		{name:'pportExprtnYmd',index:'pportExprtnYmd', width:170,align: "center", hidden: true},
		   		{name:'pportIssueYmd',index:'pportIssueYmd', width:170,align: "center", hidden: true},
		   		{name:'seatGrade',index:'seatGrade', width:170,align: "center", hidden: true},
		   		{name:'ifParam',index:'ifParam', width:170,align: "center", hidden: true},
		   		{name:'inchrgAir',index:'inchrgAir', width:170,align: "center", hidden: true},

	   	],
	   	autowidth:true,
	   	height:450,
	   	sortname: 'fstRegDt',
	    viewrecords: true,
	    sortorder: "desc",
	    rowNum:10000,
	    ondblClickRow: function(rowid, iRow, iCol, boxid, e){
	    	grd_List_oncelldbclick(this, rowid, iRow, iCol);
	     }
	};

	$("#airFareList").jqGrid(airFareList);
	$("#airFareList").jqGrid('navGrid','#airFareListPager',{edit:false,add:false,del:false});


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
	ds_AirFareList.bind($("#airFareList"));

}

/**
* @class Form Onload 시 Element/Component 인벤트 초기화
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetEventListener()
{


	$("#signSearch").click(function(){
		cf_RetrieveAirFareList();
	});

	/**
	 * Enter Key Event 처리
	 * - 검색영억에 Focus가 가 있을 때만 Enter Key Event를 처리한다.
	 */
	$("#table_sign_search").bind('keypress', function(e) {
	    if (e.which == 13) {
	    	cf_RetrieveAirFareList();
	    }
	});

	//기안일 검색 Select Box 선택
	$("select[name='simpleDt']").change(function(){
		simpleDtCd = gf_GetValue($("select[name='simpleDt']").val());

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

	//cf_RetrieveAirFareList();		(2019.03.29. 화면 로그인시 자동 FETCH하지 않도록)
}

/**
* @class 업무시스템과 업무분류에 따른 양식문 Select Box를 구성한다.
* @author 방유성
* @since 2013-07-09
* @version 1.0
*/
function cf_RetrieveAirFareList(){


	var selectParams = $("#searchForm").serializeObject();

	gf_Transaction("SELECT_AIR_FARE_LIST", "/trip/outerTrip/retrieveAirFareList.xpl", selectParams, {}, "f_Callback", true);
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
	  	case "SELECT_AIR_FARE_LIST" :
	  		ds_AirFareList.setAllData(resultData.ds_AirFareList);

	  		var rcCount = "["+ds_AirFareList.size()+"]";
	  		$("#airFareListCnt").text(rcCount); 	//문서함 목록
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
function grd_List_oncelldbclick(grid, rowid, iRow, iCol)
{
	var pos = ds_AirFareList.getPosition();

	var param = {
			docData : ds_AirFareList.get(pos)
	};

	gf_PostOpen("/trip/airFare/airFareDraft.jsp", null,
			"toolbar=no,scrollbars=yes,width=1024,height=700", param,
			true, true, "f_RefreshList");



}

function f_RefreshList(){
	cf_RetrieveAirFareList();
}