/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/

var ds_NatList = new DataSet();

// DataSet index
var v_TargetId;

var v_NatCd = "";
var v_NatNm = "";
var v_CityNm = "";

var v_RiskYn = "";
var v_pportUseApprNcsYn = "";
var v_ArmyInfoNcsYn = "";
var v_FmlyInfoNcsYn = "";


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

	v_TargetId = gf_IsNull(datas.targetId) ? ""          : datas.targetId;

	v_NatCd = gf_IsNull(datas.natCd) ? ""          : datas.natCd;
	v_NatNm = gf_IsNull(datas.natNm) ? ""          : datas.natNm;
	v_CityNm = gf_IsNull(datas.cityNm) ? ""          : datas.cityNm;

	v_CityNm = gf_IsNull(datas.ar) ? ""          : datas.cityNm;
	v_CityNm = gf_IsNull(datas.cityNm) ? ""          : datas.cityNm;


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
	   	colNames:[gf_FindLang('국가명'),gf_FindLang('비자필요유무'),gf_FindLang('비자발급소요기간'),gf_FindLang('예외적여권사용허가진행여부'),gf_FindLang('위험여부'),gf_FindLang('가족정보필요유무'),gf_FindLang('병역정보필요유무')
	   	          ],
	   	colModel:[
	  	   		{name:'natNm',index:'natNm', width:100,align: "center"},
	  	   		{name:'visaYn',index:'visaYn', width:60,align: "center"},
	  	   		{name:'issueNeedPeriod',index:'issueNeedPeriod', width:170,align: "center"},
	  	   		{name:'pportUseApprNcsYn',index:'pportUseApprNcsYn', width:130,align: "center"},
	  	   		{name:'riskYn',index:'riskYn', width:100,align: "center", hidden : true},
				{name:'fmlyInfoNcsYn',index:'fmlyInfoNcsYn', width:50,align: "center", hidden : true},
				{name:'armyInfoNcsYn',index:'armyInfoNcsYn', width:50,align: "center", hidden : true}
	  	   		],
	   	autowidth:true,
	   	height:100,
	   	sortname: 'natNm',
	    viewrecords: true,
	    sortorder: "desc",
	    ondblClickRow: function(rowid, iRow, iCol, boxid, e){
	    	grd_List_oncelldbclick(this, rowid, iRow, iCol);
	     }
	};

	$("#natList").jqGrid(natList);
	$("#natList").jqGrid('navGrid','#natListPager',{edit:false,add:false,del:false});


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
		f_Delete();
	});

	$("#visaInfo").click(function(){

		var param = {
				ds_NatList : ds_NatList.getAllData(),
				dsIndex : -1
		};

		gf_PostOpen("/trip/visaAppn/visaInfoDoc.jsp", null,
				"toolbar=no,scrollbars=yes,width=810,height=820", param,
				true, true, "null");
	});

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

		$("#cityName").val(v_CityNm);
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

	if(v_NatCd == ""){
		gf_AlertMsg("국가를 입력해주세요.");
		return;
	}
	if($("#cityName").val() == ""){
		gf_AlertMsg("방문 도시를 입력해주세요.");
		return;
	}

	// 방문지 공백 체크 할 것

	// "" == 0 결과가 true 로 나옴

	//alert(v_RiskYn);
	//alert(v_pportUseApprNcsYn);

	var callbackObj = {
			natCd : v_NatCd,
			natNm : v_NatNm,
			cityNm : $("#cityName").val(),
			targetId : v_TargetId,
			fmlyInfoNcsYn : v_FmlyInfoNcsYn,
			armyInfoNcsYn : v_ArmyInfoNcsYn,
			naRiskYn : v_pportUseApprNcsYn
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

function grd_List_oncelldbclick(grid, rowid, iRow, iCol)
{
	var rowData = $("#natList").data(rowid);

	var natCd = rowData.get("natCd");
	var natNm = rowData.get("natNm");
	var riskYn = rowData.get("riskYn");
	var pportUseApprNcsYn = rowData.get("pportUseApprNcsYn")

	//가족정보 필요유무
	var fmlyInfoNcsYn = rowData.get("fmlyInfoNcsYn");
	//병역정보 필요유무
	var armyInfoNcsYn = rowData.get("armyInfoNcsYn");

	v_NatCd = natCd;
	v_NatNm = natNm;
	v_RiskYn = riskYn;
	v_pportUseApprNcsYn = pportUseApprNcsYn;

	v_ArmyInfoNcsYn		 = armyInfoNcsYn;
	v_FmlyInfoNcsYn		 = fmlyInfoNcsYn;

	$("#selectedNat").text(natNm);
	if(natCd == "KR"){ // 방문국가가 한국이면
		$("#cityName").val("인천");
	}

	$("#natListTr").hide();
	$("#selNatTr").show();
}

function f_Delete() {

	var callbackObj = {
			targetId : v_TargetId,	//호출했던 곳의 이름.
			type : "D"
	};

	if ( !gf_IsNull(v_CallbackFunc) ) {
		var openCallbackFunc = "opener."+v_CallbackFunc;
    	eval(openCallbackFunc + "(callbackObj);");
    }

	self.close();
}