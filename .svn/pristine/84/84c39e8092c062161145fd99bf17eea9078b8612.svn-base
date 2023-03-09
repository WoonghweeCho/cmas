/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/


var v_TbData = "<table><tr><td class=\"tit\" style=\"width:20%\">방문도시명</td><td class=\"inpt\" style=\"width:80%\" colspan=\"3\">	<input name=\"vCity\" type=\"text\"></td></tr>"
	+ "<tr><td class=\"tit\" style=\"width:20%\">체류시작일</td><td class=\"inpt\" style=\"width:30%\"><input name=\"vStartDate\" type=\"text\"></td>"
	+ "<td class=\"tit\" style=\"width:20%\">체류종료일</td><td class=\"inpt\" style=\"width:30%\"><input name=\"vEndDate\" type=\"text\"></td></tr>"
	+ "<tr><td class=\"tit\" style=\"width:20%\">주요업무내용</td><td class=\"inpt\" style=\"width:80%\" colspan=\"3\"><textarea name=\"tWorkDtl\" style=\"width:97%\"></textarea></td></tr></table>";


// 국가목록
var ds_NatList = new DataSet();

// DataSet index
var v_TargetId;
var v_DsIndex;

var v_NatCd = "";
var v_NatNm = "";
var v_VisaYn;
var v_VisaStartDate;
var v_VisaEndDate;
var v_RiskYn;

//팝업이 닫힐때 리턴값을 전달할 callback 함수
var v_CallbackFunc;
// 팝업의 레벨
var v_FstLvlPop = true;
// modal pop 여부
var v_IsModal = true;
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

	var natData = gf_IsNull(datas.ds_NatList) ? ""          : datas.ds_NatList;
	ds_NatList.setAllData(natData);
	v_DsIndex = gf_IsNull(datas.dsIndex) ? ""          : datas.dsIndex;

	v_TargetId = gf_IsNull(datas.targetId) ? ""          : datas.targetId;

	v_NatCd = gf_IsNull(datas.natCd) ? ""          : datas.natCd;
	v_NatNm = gf_IsNull(datas.natNm) ? ""          : datas.natNm;
	v_VisaYn = gf_IsNull(datas.visaYn) ? ""          : datas.visaYn;
	v_VisaStartDate = gf_IsNull(datas.visaStartDate) ? ""          : datas.visaStartDate;
	v_VisaEndDate = gf_IsNull(datas.visaEndDate) ? ""          : datas.visaEndDate;


	v_CallbackFunc = gf_IsNull(datas.callbackFunc) ? ""          : datas.callbackFunc;
	v_FstLvlPop    = gf_IsNull(datas.fstLvlPop)    ? v_FstLvlPop : eval(datas.fstLvlPop);
	v_IsModal      = gf_IsNull(datas.isModal)      ? v_IsModal   : eval(datas.isModal);
}

/**
* @class Form Onload 시 컴포넌트 초기화
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetComponents()
{



	//문서함 목록 JQGrid
	var natList = {
		datatype: "local",
	   	colNames:[gf_FindLang('국가명'),gf_FindLang('비자필요유무'),gf_FindLang('기타'), gf_FindLang('위험여부')
	   	          ],
	   	colModel:[
	  	   		{name:'natNm',index:'natNm', width:100,align: "center"},
	  	   		{name:'visaYn',index:'visaYn', width:60,align: "center"},
	  	   		{name:'visaInfo',index:'visaInfo', width:100,align: "center"},
	  	   		{name:'riskYn',index:'riskYn', width:100,align: "center", hidden : true}
	  	   		],
	   	autowidth:true,
	   	height:150,
	   	sortname: 'natNm',
	    viewrecords: true,
	    sortorder: "desc",
	    ondblClickRow: function(rowid, iRow, iCol, boxid, e){
	    	grd_List_oncelldbclick(this, rowid, iRow, iCol);
	     }
	};

	$("#natList").jqGrid(natList);
	$("#natList").jqGrid('navGrid','#natListPager',{edit:false,add:false,del:false});


	$( "input[name='visaStartDate']" ).mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
	$( "input[name='visaEndDate']" ).mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });

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
	ds_NatList.bind($("#natList"));

}

/**
* @class Form Onload 시 Element/Component 인벤트 초기화
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetEventListener()
{

	$("#confirmBtn").click(f_Confirm);
	$("#closeBtn").click(f_Close);


	$("input[name='txtSrchTxt']").bind('keypress', function(e) {
		if (e.which == 13) {
			ds_NatList.filter(
					function (DataSetRow) {
						if ( DataSetRow.get("natNm").match(e.target.value) != null ) {
							return true;
						}
						return false;
					}
				);
		}
	});

	$("#searchBtn").click(function(){
		ds_NatList.filter(
				function (DataSetRow) {
					if ( DataSetRow.get("natNm").match(e.target.value) != null ) {
						return true;
					}
					return false;
				}
			);
	});

	$("#filterNull").click(function(){
		ds_NatList.filter(null);
	});

	$("#reSearch").click(function(){
		$("#natListTr").show();
		$("#selNatTr").hide();
	});

	$("#deleteBtn").click(function(){
		if(v_DsIndex == -1){
			f_Close();
		}else{
			f_Delete();
		}
	});

	// 비자유무
	$("select[name='visaYn']").bind('change', function() {
		if($("select[name='visaYn']").val() != "Y"){
			$("input[name='visaStartDate']").val("");
			$("input[name='visaEndDate']").val("");
			$("input[name='visaStartDate']").attr("disabled", "true");
			$("input[name='visaEndDate']").attr("disabled", "true");
			$("input[name='visaStartDate']").attr("readonly", "true");
			$("input[name='visaEndDate']").attr("readonly", "true");
		} else {
			$("input[name='visaStartDate']").removeAttr("disabled");
			$("input[name='visaEndDate']").removeAttr("disabled");
			$("input[name='visaStartDate']").attr("readonly", "false");
			$("input[name='visaEndDate']").attr("readonly", "false");


		}
	});


	// 여권발급일 From To Validation
	$("input[name='visaStartDate']").bind('change', function() {

//		if($("input[name='visaEndDate']").val() == ""){
//			$("input[name='visaEndDate']").datepicker("setDate", $("input[name='visaStartDate']").val());
//		}

		if($("input[name='visaStartDate']").val() != "" && $("input[name='visaEndDate']").val() != ""){
			var sDate = $("input[name='visaStartDate']").val().split("-");
			var eDate = $("input[name='visaEndDate']").val().split("-");

			var sDateTemp = sDate[0] + sDate[1] + sDate[2];
			var eDateTemp = eDate[0] + eDate[1] + eDate[2];

			var startDate = $.datepicker.parseDate("yymmdd" , sDateTemp);
			var endDate = $.datepicker.parseDate("yymmdd" , eDateTemp);

			var betDay = (endDate - startDate)/1000/60/60/24;

			if(betDay < 0){
				$("input[name='visaEndDate']").val("");
				return;
			}

		}

	});

	// 여권발급일 From To Validation
	$("input[name='visaEndDate']").bind('change', function() {

//		if($("input[name='visaStartDate']").val() == ""){
//			$("input[name='visaStartDate']").datepicker("setDate", $("input[name='visaEndDate']").val());
//		}

		if($("input[name='visaStartDate']").val() != "" && $("input[name='visaEndDate']").val() != ""){
			var sDate = $("input[name='visaStartDate']").val().split("-");
			var eDate = $("input[name='visaEndDate']").val().split("-");

			var sDateTemp = sDate[0] + sDate[1] + sDate[2];
			var eDateTemp = eDate[0] + eDate[1] + eDate[2];

			var startDate = $.datepicker.parseDate("yymmdd" , sDateTemp);
			var endDate = $.datepicker.parseDate("yymmdd" , eDateTemp);

			var betDay = (endDate - startDate)/1000/60/60/24;

			if(betDay < 0){
				$("input[name='visaStartDate']").val("");
				return;
			}

		}

	});




//	$("#addCityBtn").click(function(){
//		$("#cityArea").append(v_TbData);
//
//		// last 에 ID 부여
//		var idFlag = "tData";
//		var indexF = "" + $("#cityArea table:last").index();
//
//		idFlag = idFlag + indexF;
//		$("#cityArea table:last").attr("id", idFlag);
//
//		$( "input[name='vStartDate']" ).mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
//		$( "input[name='vEndDate']" ).mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
//
//
//	});
//
//	$("#delCityBtn").click(function(){
//		$("#cityArea table:last").remove();
//	});










	// 팝업 close 이벤트 설정 및 정의
	// child window를 모두 close 처리 하고 modal 일경우 부모창을 비활성화 시킨 것을 다시 활성화 처리 한다.
	gf_SetPopCloseEvent(v_IsModal, v_FstLvlPop);

	// Modal 처리를 위한 공통 함수 호출
	gf_EnableOverlayOpener(false, v_FstLvlPop, v_IsModal);


}

/**
* @class Form Onload 시 객체 초기 값 설정
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_InitForm()
{

	if(v_NatCd == ""){
		// ADD
	}else{
		// EDIT

		$("#visaYn").val(v_VisaYn);

		if(v_VisaYn != "Y"){
			$("input[name='visaStartDate']").val("");
			$("input[name='visaEndDate']").val("");
			$("input[name='visaStartDate']").attr("disabled", "true");
			$("input[name='visaEndDate']").attr("disabled", "true");
		}else{
			$("input[name='visaStartDate']").removeAttr("disabled");
			$("input[name='visaEndDate']").removeAttr("disabled");
			$("input[name='visaStartDate']").attr("readonly", "false");
			$("input[name='visaEndDate']").attr("readonly", "false");
		}

		$("input[name='visaStartDate']").val(v_VisaStartDate);
		$("input[name='visaEndDate']").val(v_VisaEndDate);
		$("#selectedNat").text(v_NatNm);

		$("#natListTr").hide();
		$("#selNatTr").show();


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
	  	default :
	  		break;
	  }
}

/**************************************************************
 * 각 화면 요소별 이벤트 정의 영역
 **************************************************************/
/**
* @class 확인버튼 클릭시 수행되는 이벤트 함수
* @author 변형구
* @since 2013-04-12
* @version 1.0
*/
function f_Confirm() {


	// 출발지 도착지 목적지 교통수단 왕복여부


	if(v_NatCd == ""){
		gf_AlertMsg("국가를 입력해주세요.");
		return;
	}
	if($("select[name='visaYn']").val() == "Y"){
		if($("input[name='visaStartDate']").val() == ""){
			gf_AlertMsg("비자 발급일을 입력해주세요.");
			return;
		}
		if($("input[name='visaEndDate']").val() == ""){
			gf_AlertMsg("비자 만료일을 입력해주세요.");
			return;
		}
	}

	// 방문지 공백 체크 할 것

	// "" == 0 결과가 true 로 나옴

	var callbackObj = {
			natCd : v_NatCd,
			natNm : v_NatNm,
			visaYn : $("#visaYn").val(),
			visaYnDtl : $("#visaYn option:selected").text(),
			visaStartDate : $("input[name='visaStartDate']").val(),
			visaEndDate : $("input[name='visaEndDate']").val(),
			riskYn : v_RiskYn,
			dsIndex : v_DsIndex,
			targetId : v_TargetId
	};

	if ( !gf_IsNull(v_CallbackFunc) ) {
		var openCallbackFunc = "opener."+v_CallbackFunc;
    	eval(openCallbackFunc + "(callbackObj);");
    }

	self.close();
}

function f_Delete() {

	var callbackObj = {
			dsIndex : v_DsIndex,
			targetId : v_TargetId,
			type : "D"
	};

	if ( !gf_IsNull(v_CallbackFunc) ) {
		var openCallbackFunc = "opener."+v_CallbackFunc;
    	eval(openCallbackFunc + "(callbackObj);");
    }

	self.close();


}


/**
* @class 닫기 버튼 클릭시 수행되는 이벤트 함수
* @author 변형구
* @since 2013-04-12
* @version 1.0
*/
function f_Close() {

	self.close();
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

function grd_List_oncelldbclick(grid, rowid, iRow, iCol)
{
	var rowData = $("#natList").data(rowid);

	var natCd = rowData.get("natCd");
	var natNm = rowData.get("natNm");
	var riskYn = rowData.get("riskYn");

	v_NatCd = natCd;
	v_NatNm = natNm;
	v_RiskYn = riskYn;

	$("#selectedNat").text(natNm);

	$("#natListTr").hide();
	$("#selNatTr").show();



}