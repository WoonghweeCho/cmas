/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/

var ds_EmpList = new DataSet();	// 문서함 공지사항 목록 20140724

var v_TripUserId;
var v_TripUserNm;
var v_TripOrgCd;
var v_TripOrgNm;


var v_targetId;
var v_tName;
var v_tNameText;
var v_tOrg;
var v_tOrgText;
var v_tType;
var v_tPurp;
var v_tAmount;
var v_tUseYmd;

var v_tSiteExpKind;
var v_tMvDist;

var v_Amt;
var v_AppantId;
var v_AppantNm;
var v_AppantTeamCd;
var v_AppantTeamNm;
var v_ArrPlace;
var v_Cls;
var v_DocNo;
var v_GlPlace;
var v_DptPlace;
var v_TrafficCls;
var v_UseGl;
var v_UseYmd;

var v_SiteExpKind;
var v_MvDist;

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

//	amt: "1232"appantId: "1202735"appantNm: "강동균"appantTeamCd: "1DFUR"appantTeamNm: "IT운영팀"arrPlace: ""callbackFunc: "f_callBackFuncCityTranspAddP"cls: ""docNo: "CMAS-2015-000090"dptPlace: "qwe#we#qwe#B#R"fstLvlPop: trueglPlace: ""isModal: trueorgCd: "1DFUR"targetId: "tData1"trafficCls: ""useGl: "qwe"useYmd: "20150625"


	v_targetId = gf_IsNull(datas.targetId) ? ""          : datas.targetId;
	v_OrgCd = gf_IsNull(datas.orgCd) ? ""          : datas.orgCd;

	v_Amt = gf_IsNull(datas.amt) ? ""          : datas.amt;
	v_AppantId = gf_IsNull(datas.appantId) ? ""          : datas.appantId;
	v_AppantNm = gf_IsNull(datas.appantNm) ? ""          : datas.appantNm;
	v_AppantTeamCd = gf_IsNull(datas.appantTeamCd) ? ""          : datas.appantTeamCd;
	v_AppantTeamNm = gf_IsNull(datas.appantTeamNm) ? ""          : datas.appantTeamNm;
	v_ArrPlace = gf_IsNull(datas.arrPlace) ? ""          : datas.arrPlace;
	v_Cls = gf_IsNull(datas.cls) ? ""          : datas.cls;
	v_DocNo = gf_IsNull(datas.docNo) ? ""          : datas.docNo;
	v_GlPlace = gf_IsNull(datas.glPlace) ? ""          : datas.glPlace;
	v_DptPlace = gf_IsNull(datas.dptPlace) ? ""          : datas.dptPlace;
	v_TrafficCls = gf_IsNull(datas.trafficCls) ? ""          : datas.trafficCls;
	v_UseGl = gf_IsNull(datas.useGl) ? ""          : datas.useGl;
	v_UseYmd = gf_IsNull(datas.pUseYmd) ? ""          : datas.pUseYmd;
	v_SiteExpKind = gf_IsNull(datas.siteExpKind) ? ""          : datas.siteExpKind;
	v_MvDist = gf_IsNull(datas.mvDist) ? ""          : datas.mvDist;


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

	//기안자 검색 컴포넌트 초기화
//	gf_SetUserComponent($("#tripUserId"), function(data){
//
//		v_TripUserId = data.userId;
//		v_TripUserNm = data.userKnm;
//		v_TripUserPositCd = data.userPositCd;
//		v_TripOrgCd = data.orgCd;
//		v_TripOrgNm = data.orgNm;
//
//		var userInfo = v_TripUserPositCd + " " + v_TripUserNm + " (" + v_TripUserId + ")";
//		var orgInfo = v_TripOrgNm + " (" + v_TripOrgCd + ")";
//
//		v_tName = v_TripUserId + " "  + v_TripUserNm;
//		v_tOrg = v_TripOrgCd + " " + v_TripOrgNm;
//
//		$("#tripUserOrgCd").text(v_TripOrgCd + " " + v_TripOrgNm);
//		$("#tripUser").text(v_TripUserId + " " + v_TripUserNm);
//
//	});



	//문서함 목록 JQGrid
	var empList = {
		datatype: "local",
	   	colNames:[gf_FindLang('사번'),gf_FindLang('이름'),gf_FindLang('직위'),gf_FindLang('직책')
	   	          ],
	   	colModel:[
	  	   		{name:'userId',index:'userId', width:100,align: "center"},
	  	   		{name:'userKnm',index:'userKnm', width:100,align: "center"},
	  	   		{name:'userPositCd',index:'userPositCd', width:70,align: "center"},
	  	   		{name:'userRpswrkCd',index:'userRpswrkCd', width:70,align: "center"}
	  	   		],
	   	autowidth:true,
	   	height:150,
	   	sortname: 'userKnm',
	    viewrecords: true,
	    sortorder: "desc",
	    ondblClickRow: function(rowid, iRow, iCol, boxid, e){
	    	grd_List_oncelldbclick(this, rowid, iRow, iCol);
	     }
	};

	$("#empList").jqGrid(empList);
	$("#empList").jqGrid('navGrid','#empListPager',{edit:false,add:false,del:false});


	/**
	 * Container 크기에 맞춰 Windows Resizing 될 때 Box Grid의 사이즈를 조절한다.
	 */
	$(window).bind("resize", function(){
//		$("#innerTripList").setGridWidth($("#innerTripList").width());
	}).trigger('resize');

	//기안일 검색 Date Component 초기화
	$( "input[name='useDate']" ).mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });

	$("input[name='useDate']").attr("readonly", "true");

	f_numberOnly("tAmount");	//숫자만(금액)
	f_numberOnly("tMvDist");	//숫자만(이동거리)

}

/**
* @class Form Onload DataSet Binding 처리
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetBinding()
{
	ds_EmpList.bind($("#empList"));


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

	$("#addBtn").click(function(){

		gf_PostOpen("/trip/cityTranspAddP.jsp", null,
				"toolbar=no,scrollbars=no,width=500,height=310", {},
				true, true, "f_callBackFuncCityTranspAddP");

	});

	$("#tAmount").bind('keyup', function(e) {
		var targetVal = e.currentTarget.value;
		for(var k = 0; k < targetVal.length; k++){
			targetVal = targetVal.replace(",", "");
		}
		e.currentTarget.value = gf_AmtFormat(targetVal);
	});

	$("input[name='txtSrchTxt']").bind('keypress', function(e) {
		if (e.which == 13) {
			ds_EmpList.filter(
					function (DataSetRow) {
						if ( DataSetRow.get("userKnm").match(e.target.value) != null ) {
							return true;
						}
						return false;
					}
				);
		}
	});

	$("#searchBtn").click(function(){
		ds_EmpList.filter(
				function (DataSetRow) {
					if ( DataSetRow.get("userKnm").match($("input[name='txtSrchTxt']").val()) != null ) {
						return true;
					}
					return false;
				}
			);
	});

	$("#filterNull").click(function(){
		ds_EmpList.filter(null);
	});

	$("#reSearch").click(function(){
		$("#empListTr").show();

	});

	$("#otherTUser").click(function(){
		var callbackFunc = "f_SelectOtherOrgUser";
		window.open(gv_ContextPath + "/common/jsp/comp/userSelect.jsp?userNm=" + "&callbackFunc="+callbackFunc,"","toolbar=no,scrollbars=no,width=600,height=420");
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

	v_TripUserId = v_AppantId;
	v_TripUserNm = v_AppantNm;
	v_TripOrgCd = v_AppantTeamCd;
	v_TripOrgNm = v_AppantTeamNm;

	// 팀코드
	if(v_AppantTeamCd == ""){
		$("#tripUserOrgCd").text("");
	}else{
		$("#tripUserOrgCd").text(v_AppantTeamNm + " (" + v_AppantTeamCd + ")");
	}

	// 신청자
	if(v_AppantId == ""){
		$("#tripUser").text("");
	}else{
		$("#tripUser").text(v_AppantNm + " (" + v_AppantId + ")");
	}




	// 출발 도작 목적 수단 왕복
	$("#t1").val(v_DptPlace);
	$("#t2").val(v_ArrPlace);
	$("#t3").val(v_GlPlace);
	$("input[name='useDate']").val(v_UseYmd);
	$("#tType").val(v_TrafficCls);
	$("#tRound").val(v_Cls);

	// 목적
	$("#tPurp").val(v_UseGl);

	// 금액
	$("#tAmount").val(v_Amt);

	$("#tSiteExpKind").val(v_SiteExpKind);	//현장경비구분
	$("#tMvDist").val(v_MvDist);	//이동거리

	var param = {
		orgCd : v_OrgCd
	};

	gf_Transaction("SELECT_EMP_LIST", "/trip/cityTransp/retrieveEmpListByOrgCd.xpl", param, {}, "f_Callback", true);

	//("#empListTr").hide()
	(v_AppantId != "" ? $("#empListTr").hide():"");
	//$("#empListTr").hide() == true?$("#reSearch").hide():$("#reSearch").show();


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
	  	case "SELECT_EMP_LIST" :
	  		ds_EmpList.setAllData(resultData.ds_EmpList);
	  		break;

	  	default :
	  		break;
	  }
}

function f_SelectOtherOrgUser(obj){

	v_TripUserId = obj.userId;
	v_TripUserNm = obj.userKnm;
	v_TripUserPositCd = obj.userPositCd;
	v_TripOrgCd = obj.orgCd;
	v_TripOrgNm = obj.orgNm;

	var userInfo = v_TripUserPositCd + " " + v_TripUserNm + " (" + v_TripUserId + ")";
	var orgInfo = v_TripOrgNm + " (" + v_TripOrgCd + ")";

	v_tName = v_TripUserId + " "  + v_TripUserNm;
	v_tOrg = v_TripOrgCd + " " + v_TripOrgNm;


	$("#empListTr").hide();

	$("#tripUserOrgCd").text(v_TripOrgNm + " (" + v_TripOrgCd+")");
	$("#tripUser").text(v_TripUserId + " " + v_TripUserNm);
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
	if($("#t1").val() == ""){
		gf_AlertMsg("출발지를 입력해주세요.");
		return;
	}
	if($("#t2").val() == ""){
		gf_AlertMsg("도착지를 입력해주세요.");
		return;
	}
	if($("#t3").val() == ""){
		gf_AlertMsg("목적지를 입력해주세요.");
		return;
	}
	if($("#tPurp").val() == ""){
		gf_AlertMsg("사용목적을 입력해주세요.");
		return;
	}
	if($("#tAmount").val() == ""){
		gf_AlertMsg("금액을 입력해주세요.");
		return;
	}
	if($("input[name='useDate']").val() == ""){
		gf_AlertMsg("사용일을 입력해주세요.");
		return;
	}
	if(v_TripUserId == ""){
		gf_AlertMsg("신청자를 입력해주세요.");
		return;
	}

	/*
	if($("#tSiteExpKind").val() == "" || $("#tSiteExpKind").val() == "0"){
		gf_AlertMsg("현장경비종류를 선택해주세요.");
		return;
	}
	if(tMvDist == ""){
		gf_AlertMsg("이동거리를 입력해주세요.");
		return;
	}
	 */

	//length 체크
	if($("#t1").val().length > 14){
		gf_AlertMsg("출발지는 14자 이내로 작성해주세요.");
		return;
	}
	if($("#t2").val().length > 14){
		gf_AlertMsg("도착지는 14자 이내로 작성해주세요.");
		return;
	}
	if($("#t3").val().length > 14){
		gf_AlertMsg("목적지는 14자 이내로 작성해주세요.");
		return;
	}

	if($("#tPurp").val().length > 40){
		gf_AlertMsg("사용 목적은 40자 이내로 작성해주세요.");
		return;
	}

	if($("#tMvDist").val().length > 10){
		gf_AlertMsg("이동거리는 10자 이내로 작성해주세요.");
		return;
	}



	//특수문자 체크(출발지)
	if(f_patternChk($("#t1").val()) == false){
		gf_AlertMsg("[출발지]에 특수문자를 입력할 수 없습니다");
		return;
	}

	//특수문자 체크(도착지)
	if(f_patternChk($("#t2").val()) == false){
		gf_AlertMsg("[도착지]에 특수문자를 입력할 수 없습니다");
		return;
	}

	//특수문자 체크(목적지)
	if(f_patternChk($("#t3").val()) == false){
		gf_AlertMsg("[목적지]에 특수문자를 입력할 수 없습니다");
		return;
	}

	//특수문자 체크(출장목적)
	if(f_patternChk($("#tPurp").val()) == false){
		gf_AlertMsg("[사용목적]에 특수문자를 입력할 수 없습니다");
		return;
	}



//	v_tType = $("#t1").val() + "#" + $("#t2").val() + "#" + $("#t3").val() + "#" + $("#tType").val() + "#" + $("#tRound").val();
//
//	v_tPurp = $("#tPurp").val();
//	v_tAmount = $("#tAmount").val();
//	v_UseYmd = $("input[name='useDate']").val();


//	v_Amt = gf_IsNull(datas.amt) ? ""          : datas.amt;
//	v_AppantId = gf_IsNull(datas.appantId) ? ""          : datas.appantId;
//	v_AppantNm = gf_IsNull(datas.appantNm) ? ""          : datas.appantNm;
//	v_AppantTeamCd = gf_IsNull(datas.appantTeamCd) ? ""          : datas.appantTeamCd;
//	v_AppantTeamNm = gf_IsNull(datas.appantTeamNm) ? ""          : datas.appantTeamNm;
//	v_ArrPlace = gf_IsNull(datas.arrPlace) ? ""          : datas.arrPlace;
//	v_Cls = gf_IsNull(datas.cls) ? ""          : datas.cls;
//	v_DocNo = gf_IsNull(datas.docNo) ? ""          : datas.docNo;
//	v_GlPlace = gf_IsNull(datas.glPlace) ? ""          : datas.glPlace;
//	v_DptPlace = gf_IsNull(datas.dptPlace) ? ""          : datas.dptPlace;
//	v_TrafficCls = gf_IsNull(datas.trafficCls) ? ""          : datas.trafficCls;
//	v_UseGl = gf_IsNull(datas.useGl) ? ""          : datas.useGl;
//	v_UseYmd = gf_IsNull(datas.useYmd) ? ""          : datas.useYmd;

//	v_TripUserId = rowData.get("userId");
//	v_TripUserNm = rowData.get("userKnm");
//	v_TripUserPositCd = rowData.get("userPositCd");
//	v_TripOrgCd = rowData.get("orgCd");
//	v_TripOrgNm = rowData.get("orgNm");

	var callbackObj = {
			targetId : v_targetId,
			amt : $("#tAmount").val(),
			appantId : v_TripUserId,
			appantNm : v_TripUserNm,
			appantTeamCd : v_TripOrgCd,
			appantTeamNm : v_TripOrgNm,
			arrPlace : $("#t2").val(),
			cls : $("#tRound").val(),
			docNo : v_DocNo,
			glPlace : $("#t3").val(),
			dptPlace : $("#t1").val(),
			trafficCls : $("#tType").val(),
			useGl : $("#tPurp").val(),
			useYmd : $("input[name='useDate']").val(),
			siteExpKind : $("#tSiteExpKind").val(),
			mvDist : $("#tMvDist").val()
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

function grd_List_oncelldbclick(grid, rowid, iRow, iCol){
	var rowData = $("#empList").data(rowid);

	v_TripUserId = rowData.get("userId");
	v_TripUserNm = rowData.get("userKnm");
	v_TripUserPositCd = rowData.get("userPositCd");
	v_TripOrgCd = rowData.get("orgCd");
	v_TripOrgNm = rowData.get("orgNm");

	var userInfo = v_TripUserPositCd + " " + v_TripUserNm + " (" + v_TripUserId + ")";
	var orgInfo = v_TripOrgNm + " (" + v_TripOrgCd + ")";

	v_tName = v_TripUserId + " "  + v_TripUserNm;
	v_tOrg = v_TripOrgCd + " " + v_TripOrgNm;


	$("#empListTr").hide();

	$("#tripUserOrgCd").text(v_TripOrgNm + " (" + v_TripOrgCd+")");
	$("#tripUser").text(v_TripUserId + " " + v_TripUserNm);


//	v_TripUserId = data.userId;
//	v_TripUserNm = data.userKnm;
//	v_TripUserPositCd = data.userPositCd;
//	v_TripOrgCd = data.orgCd;
//	v_TripOrgNm = data.orgNm;
//
//	var userInfo = v_TripUserPositCd + " " + v_TripUserNm + " (" + v_TripUserId + ")";
//	var orgInfo = v_TripOrgNm + " (" + v_TripOrgCd + ")";
//
//	v_tName = v_TripUserId + " "  + v_TripUserNm;
//	v_tOrg = v_TripOrgCd + " " + v_TripOrgNm;
//
//	$("#tripUserOrgCd").text(v_TripOrgCd + " " + v_TripOrgNm);
//	$("#tripUser").text(v_TripUserId + " " + v_TripUserNm);



}

//특수문자 체크
function f_patternChk(txt){
	var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
	if( special_pattern.test(txt) == true ){
	    return false;
	}else{
		return true;
	}
}

//숫자 체크
function f_numberChk(txt){
	var special_pattern = /123456789/;
	if( special_pattern.test(txt) == true ){
	    return true;
	}else{
		return false;
	}
}

//숫자확인
function f_numberOnly(id){

	$("#"+id).bind('keydown', function(e) {
	var keyID = (e.which) ? e.which : e.keyCode;
	if ((keyID >= 48 && keyID <= 57)||(keyID >=96 && keyID <= 105)|| keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ){
		return true;
	}else{
		alert("숫자만 입력가능합니다.");
		return false;
	}
});

}