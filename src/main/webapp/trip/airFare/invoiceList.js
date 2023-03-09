/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
var ds_normFareList = new DataSet();	// 문서함 공지사항 목록 20140724
var ds_refdFareList = new DataSet();
var ds_cancFareList = new DataSet();
var ds_belList = new DataSet();


var ds_TourSel = new DataSet();
var ds_Years = new DataSet();
var ds_Months = new DataSet();
var ds_FareCls = new DataSet();

var v_TourC1 = "ZA29990"; // - 동서여행사 (플랜트 : 김진아 대리. 현재는 없음)
var v_TourC2 = "ZA47613"; // - 현대드림투어 (토목 : 하지선 매니저), (2021.03.09 사번변경 : ZA35689 -> ZA47613)
var v_TourC3 = "ZA29771"; // - 주원항공여행사 (기타 : 김지연 대리. 현재는 없음)
var v_TourC4 = "ZA47614"; // - 하나투어 (기타 : 신희철 매니저), (2021.03.09 사번변경 : ZA41509 -> ZA47614)

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
	// 정상전표
	var normFareList = {
		datatype: "local",
	   	colNames:[gf_FindLang('발권번호'),gf_FindLang('이름'),gf_FindLang('사번'),gf_FindLang('발권일'),gf_FindLang('지불방법'),gf_FindLang('실발권금액'),gf_FindLang('손익센터'),gf_FindLang('여정')],
	   	colModel:[
	   	        {name:'Ticno',index:'Ticno', width:80,align: "center"},
	  	   		{name:'Empnm',index:'Empnm', width:80,align: "center"},
	  	   		{name:'Empno',index:'Empno', width:60,align: "center"},
	  	   		{name:'Ticdt',index:'Ticdt', width:50,align: "center"},
	  	   	 	{name:'PayMethod',index:'PayMethod', width:40,align: "center",
		   			formatter:function(val, gOpt, row){
		   				if(val == '1'){
		   					val = '현금';
		   				}else if(val == '2'){
		   					val = '카드';
		   				}
	  	   				return val;
	  	   			}
	  	   	 	},
		   		{name:'Revifare',index:'Revifare', width:80,align: "center",
		   			formatter:function(val, gOpt, row){
		   				if(val != undefined){
		   					val = parseFloat(val) * 100;
		   		  			val = gf_AmtFormat(val.toFixed(0));
		   				}else if(val != ""){
		   					val = parseFloat(val) * 100;
		   		  			val = gf_AmtFormat(val.toFixed(0));
		   				}
	  	   				return val;
	  	   			}
		   		},
		   		{name:'Prctr',index:'Prctr', width:40,align: "center"},
		   		{name:'Route',index:'Route', width:170,align: "center",
		   			formatter:function(val, gOpt, row){
		   				if(val == undefined){
		   					val = '';
		   				}
	  	   				return val;
	  	   			}
		   		}

	   	],
	   	autowidth:true,
	   	height:200,
	   	sortname: 'Ticdt',
	    viewrecords: true,
	    sortorder: "desc",
	    rowNum:10000
	};

	// 리펀딩전표
	var refdFareList = {
		datatype: "local",
		colNames:[gf_FindLang('발권번호'),gf_FindLang('이름'),gf_FindLang('사번'),gf_FindLang('발권일'),gf_FindLang('지불방법'),gf_FindLang('실발권금액'),gf_FindLang('손익센터'),gf_FindLang('여정')],
	   	colModel:[
	   	        {name:'Ticno',index:'Ticno', width:80,align: "center"},
	  	   		{name:'Empnm',index:'Empnm', width:80,align: "center"},
	  	   		{name:'Empno',index:'Empno', width:60,align: "center"},
	  	   		{name:'Ticdt',index:'Ticdt', width:50,align: "center"},
	  	   	 	{name:'PayMethod',index:'PayMethod', width:40,align: "center",
		   			formatter:function(val, gOpt, row){
		   				if(val == '1'){
		   					val = '현금';
		   				}else if(val == '2'){
		   					val = '카드';
		   				}
	  	   				return val;
	  	   			}
	  	   	 	},
	  	   	 	{name:'Revifare',index:'Revifare', width:80,align: "center",
		   			formatter:function(val, gOpt, row){
		   				if(val != undefined){
		   					val = parseFloat(val) * 100;
		   		  			val = gf_AmtFormat(val.toFixed(0));
		   				}else if(val != ""){
		   					val = parseFloat(val) * 100;
		   		  			val = gf_AmtFormat(val.toFixed(0));
		   				}
	  	   				return val;
	  	   			}
		   		},
		   		{name:'Prctr',index:'Prctr', width:40,align: "center"},
		   		{name:'Route',index:'Route', width:170,align: "center",
		   			formatter:function(val, gOpt, row){
		   				if(val == undefined){
		   					val = '';
		   				}
	  	   				return val;
	  	   			}
		   		}

	   	],
	   	autowidth:true,
	   	height:200,
	   	sortname: 'Ticdt',
	    viewrecords: true,
	    sortorder: "desc",
	    rowNum:10000
	};

	// 취소전표
	var cancFareList = {
		datatype: "local",
		colNames:[gf_FindLang('발권번호'),gf_FindLang('이름'),gf_FindLang('사번'),gf_FindLang('발권일'),gf_FindLang('지불방법'),gf_FindLang('실발권금액'),gf_FindLang('손익센터'),gf_FindLang('여정')],
	   	colModel:[
	   	        {name:'Ticno',index:'Ticno', width:80,align: "center"},
	  	   		{name:'Empnm',index:'Empnm', width:80,align: "center"},
	  	   		{name:'Empno',index:'Empno', width:60,align: "center"},
	  	   		{name:'Ticdt',index:'Ticdt', width:50,align: "center"},
	  	   	 	{name:'PayMethod',index:'PayMethod', width:40,align: "center",
		   			formatter:function(val, gOpt, row){
		   				if(val == '1'){
		   					val = '현금';
		   				}else if(val == '2'){
		   					val = '카드';
		   				}
	  	   				return val;
	  	   			}
	  	   	 	},
	  	   	 	{name:'Revifare',index:'Revifare', width:80,align: "center",
		   			formatter:function(val, gOpt, row){
		   				if(val != undefined){
		   					val = parseFloat(val) * 100;
		   		  			val = gf_AmtFormat(val.toFixed(0));
		   				}else if(val != ""){
		   					val = parseFloat(val) * 100;
		   		  			val = gf_AmtFormat(val.toFixed(0));
		   				}
	  	   				return val;
	  	   			}
		   		},
		   		{name:'Prctr',index:'Prctr', width:40,align: "center"},
		   		{name:'Route',index:'Route', width:170,align: "center",
		   			formatter:function(val, gOpt, row){
		   				if(val == undefined){
		   					val = '';
		   				}
	  	   				return val;
	  	   			}
		   		}

	   	],
	   	autowidth:true,
	   	height:200,
	   	sortname: 'Ticdt',
	    viewrecords: true,
	    sortorder: "desc",
	    rowNum:10000
	};


	// 전표리스트
	var belList = {
		datatype: "local",
		colNames:[gf_FindLang('전표번호'),gf_FindLang('증빙일'),gf_FindLang('적요'),gf_FindLang('반려여부')],
	   	colModel:[
	   	        {name:'Belnr',index:'Belnr', width:130,align: "center"},
	  	   		{name:'Bldat',index:'Bldat', width:100,align: "center"},
	  	   		{name:'Sgtxt',index:'Sgtxt', width:350,align: "center"},
	  	   		{name:'Ticdt',index:'Ticdt', width:60,align: "center"}
	   	],
	   	autowidth:true,
	   	height:200,
	   	sortname: 'Belnr',
	    viewrecords: true,
	    sortorder: "desc",
	    rowNum:10000,
	    ondblClickRow: function(rowid, iRow, iCol, boxid, e){
	    	grd_List_oncelldbclick(this, rowid, iRow, iCol);
	     }
	};

	$("#normFareList").jqGrid(normFareList);
	$("#normFareList").jqGrid('navGrid','#normFareListPager',{edit:false,add:false,del:false});

	$("#refdFareList").jqGrid(refdFareList);
	$("#refdFareList").jqGrid('navGrid','#refdFareListPager',{edit:false,add:false,del:false});

	$("#cancFareList").jqGrid(cancFareList);
	$("#cancFareList").jqGrid('navGrid','#cancFareListPager',{edit:false,add:false,del:false});

	$("#belList").jqGrid(belList);
	$("#belList").jqGrid('navGrid','#belListPager',{edit:false,add:false,del:false});



	/**
	 * Container 크기에 맞춰 Windows Resizing 될 때 Box Grid의 사이즈를 조절한다.
	 */
	$(window).bind("resize", function(){
//		$("#innerTripList").setGridWidth($("#innerTripList").width());
	}).trigger('resize');

	$("input[name='timeYm']" ).monthpicker({dateFormat: 'yy-mm'});
	$("input[name='timeYm']").val($.monthpicker.formatDate("yy-mm", new Date()));
}

/**
* @class Form Onload DataSet Binding 처리
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetBinding()
{
	ds_normFareList.bind($("#normFareList"));
	ds_refdFareList.bind($("#refdFareList"));
	ds_cancFareList.bind($("#cancFareList"));
	ds_belList.bind($("#belList"));


	ds_TourSel.bind($("#tourC")[0], {val: "code", text: "value"});
	ds_FareCls.bind($("#fareCls")[0], {val: "code", text: "value"});

}

/**
* @class Form Onload 시 Element/Component 인벤트 초기화
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetEventListener()
{
	$("#invoiceSearch").click(function(){

		cf_RetrieveInvoiceList();

	});


	$('#layer_close2').click(function(){
		$('#emmisionLayerDiv').fadeOut();
		return false;
	});

	$("#print").click(function(){

		var params = {
				ds_normFareList : ds_normFareList.getAllData(),
				ds_refdFareList : ds_refdFareList.getAllData(),
				ds_cancFareList : ds_cancFareList.getAllData(),
				Belnr1 : $("#Belnr1").text(),
				Belnr2 : $("#Belnr2").text(),
				Belnr3 : $("#Belnr3").text(),
				EBldat1 : $("#EBldat1").text(),
				EBldat2 : $("#EBldat2").text(),
				EBldat3 : $("#EBldat3").text(),
				ENormalsum : $("#ENormalsum").text(),
				ERefundsum : $("#ERefundsum").text(),
				ECancelsum : $("#ECancelsum").text(),
				year : $("input[name='timeYm']").val().split("-")[0],
				month : $("input[name='timeYm']").val().split("-")[1],
				tourC : $("select[name='tourC']").val()
		};


		gf_PostOpen("/trip/airFare/invoicePrint.jsp", null,
				"toolbar=no,scrollbars=yes,width=800,height=700", params,
				true, true, null);


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

	var	tourSel = [{ "code":"","value":""},{ "code":"D","value":"동서여행사"},{ "code":"H","value":"현대드림투어"},{ "code":"J","value":"주원항공여행사"},{ "code":"N","value":"하나투어"}];
	var	fareSel = [{ "code":"NA","value":"미정산 항공료"},{ "code":"FL","value":"전표리스트"}];

	ds_TourSel.setAllData(tourSel);
	ds_FareCls.setAllData(fareSel);

}

/**
* @class 업무시스템과 업무분류에 따른 양식문 Select Box를 구성한다.
* @author 방유성
* @since 2013-07-09
* @version 1.0
*/
function cf_RetrieveInvoiceList(){

	//getAirfareInvoice

//	IGjahr
//	IMonat
//	IBelnr
//	IUpnam
//	I_GJAHR	NUMC	 4 	회계연도
//	I_MONAT	NUMC	 2 	조회월
//	I_BELNR	CHAR	30
//	I_UPNAM	CHAR	12	여행사담당자 사번


	//getAirfareInvoiceList 전표 리스트 조회
//	I_GJAHR	NUMC	 4 	회계연도
//	I_MONAT	NUMC	 2 	조회월
//	I_UPNAM	CHAR	12	여행사담당자 사번


//	IGjahr
//	IMonat
//	IUpnam

	var tourC;

	if($("#tourC").val() == ""){
		alert("여행사를 선택하세요");
		return;
	}else if($("#tourC").val() == "D"){
		tourC = v_TourC1;
	}else if($("#tourC").val() == "H"){
		tourC = v_TourC2;
	}else if($("#tourC").val() == "J"){
		tourC = v_TourC3;
	}else if($("#tourC").val() == "N"){
		tourC = v_TourC4;
	}

	// 정산인지 미정산인지에 따라 eai 가 갈림
	// NA 미정산, FL 전표리스트
	if($("#fareCls").val() == "NA"){

		$("#Belnr1").text("");
		$("#Belnr2").text("");
		$("#Belnr3").text("");

		$("#EBldat1").text("");
		$("#EBldat2").text("");
		$("#EBldat3").text("");



		var params = {
				IGjahr : $("input[name='timeYm']").val().split("-")[0],
				IMonat : $("input[name='timeYm']").val().split("-")[1],
				IBelnr : "",
				IUpnam : tourC
		};

		gf_Transaction("SELECT_NA_LIST", "/trip/eai/getAirfareInvoice.xpl", params, {}, "f_Callback", true);
	}else if($("#fareCls").val() == "FL"){

		var params = {
				IGjahr : $("input[name='timeYm']").val().split("-")[0],
				IMonat : $("input[name='timeYm']").val().split("-")[1],
				IUpnam : tourC
		};

		gf_Transaction("SELECT_FL_LIST", "/trip/eai/getAirfareInvoiceList.xpl", params, {}, "f_Callback", true);
	}
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
	  	case "SELECT_NA_LIST" :

	  		$('#emmisionLayerDiv').fadeOut();

	  		//증빙일
	  		if(resultData.output1[0].EBldat != undefined){

	  			$("#EBldat1").text(resultData.output1[0].EBldat);
	  			$("#EBldat2").text(resultData.output1[0].EBldat);
	  			$("#EBldat3").text(resultData.output1[0].EBldat);

	  		}


//	  		ECancelsum: "3123"
//	  		ENormalsum: "3600"
//	  		ERefundsum: "0"

	  		if(resultData.output1[0].ENormalsum != undefined){
	  			$("#ENormalsum").text(gf_AmtFormat(resultData.output1[0].ENormalsum));
	  		}

	  		if(resultData.output1[0].ERefundsum != undefined){
	  			$("#ERefundsum").text(gf_AmtFormat(resultData.output1[0].ERefundsum));
	  		}
	  		if(resultData.output1[0].ECancelsum != undefined){
	  			$("#ECancelsum").text(gf_AmtFormat(resultData.output1[0].ECancelsum));
	  		}



	  		ds_normFareList.clear();
	  		if(resultData.output1[0].SapNormaltab != undefined){

	  			// 결과가 하나일 경우 오브젝트로 전달
		  		if(resultData.output1[0].SapNormaltab.constructor == Object){
		  			ds_normFareList.add(resultData.output1[0].SapNormaltab);
		  		}else if(resultData.output1[0].SapNormaltab.constructor == Array){
		  			ds_normFareList.setAllData(resultData.output1[0].SapNormaltab);
		  		}

	  		}

	  		var nRcCount = "["+ds_normFareList.size()+"]";
	  		$("#normFareListCnt").text(nRcCount); 	//문서함 목록


	  		ds_cancFareList.clear();
	  		if(resultData.output1[0].SapCanceltab != undefined){

	  			// 결과가 하나일 경우 오브젝트로 전달
		  		if(resultData.output1[0].SapCanceltab.constructor == Object){
		  			ds_cancFareList.add(resultData.output1[0].SapCanceltab);
		  		}else if(resultData.output1[0].SapCanceltab.constructor == Array){
		  			ds_cancFareList.setAllData(resultData.output1[0].SapCanceltab);
		  		}
	  		}

	  		var cRcCount = "["+ds_cancFareList.size()+"]";
	  		$("#cancFareListCnt").text(cRcCount); 	//문서함 목록


	  		// 미정산 항공료
	  		//resultData.output1[0].SapCanceltab 테이블 목록
//	  		Airfare: "0"AirptFee1: "0"AirptFee2: "0"Aufnr: "AA010072"Bldat: "2015-02-27"CancelDate: "2015-03-03"Dcamt: "0"Empnm: "Sang-Real Kim"Empno: "ZA010072"Erdat: "2015-02-27"Ernam: "RFCUSR"Ertim: "Thu Jan 01 13:19:52 KST 1970"Fdate: "2015-03-04"Gjahr: "2015"Initfare: "8166120.00"Insure: "0"Kunnr: "ZA010072"Mandt: "100"Monat: "02"Notesdoc: "42FFD9132CDF9D0849257DF900095EC6"PayMethod: "2"Prctr: "1DFGW"Refkey: "AA20151DFGW0004"Revifare: "0"Route: "ICN/IAH/ICN (2/27)"Seattype: "BIZ"Serno: "01"Sgtxt: "(해출)벡텔사와 미팅 (MOU 체결 및 오만 PJ 협의)"Tdate: "2015-03-07"Ticdt: "2015-03-01"TicketFee: "0"Ticno: "1809210131525"Twaer: "KRW"Updat: "2015-03-03"Upnam: "ZA29990"Uptim: "Thu Jan 01 09:20:06 KST 1970"Zmemo: "동서여행사"
	  		// 데이터형

	  		// 전표리스트
	  		//resultData.output1[0].SapBeltab
//	  		Belnr: "SRK20150006"Bldat: "2015-03-31"Crdat: "2015-03-31"Crnam: "Z0802784"Crtim: "Thu Jan 01 14:10:23 KST 1970"Gjahr: "2015"Mandt: "100"Monat: "03"Sgtxt: "3월 해외출장 항공료(동서)정산"Upnam: "ZA29990"
	  		// 데이터형

			break;
	  	case "SELECT_FL_LIST" :

	  		ds_belList.clear();

	  		if(resultData.output1[0].SapBeltab != undefined){

	  			// 결과가 하나일 경우 오브젝트로 전달
		  		if(resultData.output1[0].SapBeltab.constructor == Object){
		  			ds_belList.add(resultData.output1[0].SapBeltab);
		  		}else if(resultData.output1[0].SapBeltab.constructor == Array){
		  			ds_belList.setAllData(resultData.output1[0].SapBeltab);
		  		}
	  		}

	  		var bRcCount = "["+ds_belList.size()+"]";
	  		$("#belListCnt").text(bRcCount); 	//문서함 목록

  			var top = 50;
  			var left = (($(window).width() - $("#emmisionLayerDiv").outerWidth()) / 2) + $(window).scrollLeft();

  			$("#emmisionLayerDiv").css({position:"absolute", top : top, left : left});


  			$("#emmisionLayerDiv").show();
  			return false;

	  		break;

	  	default :
	  		break;
	  }
}





/**
* @class 조건에 맞는 결재양식 목록을 조회한다.
* * @since 2013-04-04
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
	var rowData = $("#belList").data(rowid);

	var Belnr = rowData.get("Belnr");


	$("#Belnr1").text(Belnr);
	$("#Belnr2").text(Belnr);
	$("#Belnr3").text(Belnr);



	var tourC;

	if($("#tourC").val() == ""){
		alert("여행사를 선택하세요");
		return;
	}else if($("#tourC").val() == "D"){
		tourC = v_TourC1;
	}else if($("#tourC").val() == "H"){
		tourC = v_TourC2;
	}else if($("#tourC").val() == "J"){
		tourC = v_TourC3;
	}else if($("#tourC").val() == "N"){
		tourC = v_TourC4;
	}

	var params = {
			IGjahr : $("input[name='timeYm']").val().split("-")[0],
			IMonat : $("input[name='timeYm']").val().split("-")[1],
			IBelnr : Belnr,
			IUpnam : tourC
	};

	gf_Transaction("SELECT_NA_LIST", "/trip/eai/getAirfareInvoice.xpl", params, {}, "f_Callback", true);



}

/**
 * 금액 포맷에 맞춤
 * @param val
 * @returns
 */
function gf_AmtFormat(val){
	var rslVal = val;
	var exp = new RegExp("(\\d)(?=(?:\\d{" + 3 + "})+(?!\\d))", "g");
	rslVal = rslVal.toString().replace(exp, '$1,');

	return rslVal;
}
