/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/

var ds_AuthList = new DataSet();	// 문서함 공지사항 목록 20140724

var v_OrgCd;
var v_AuthList;

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


	v_OrgCd = gf_IsNull(datas.orgCd) ? ""          : datas.orgCd;
	v_AuthList = gf_IsNull(datas.authList) ? ""          : datas.authList;

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



}

/**
* @class Form Onload DataSet Binding 처리
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetBinding()
{

	ds_AuthList.bind($("#orgList")[0], {val: "orgCd", text: "sText"});

//	"[" + ds_AuthList.get(0).apptCls + "] " + ds_AuthList.get(0).orgNm + " (" + ds_AuthList.get(0).orgCd + ")"

}

/**
* @class Form Onload 시 Element/Component 인벤트 초기화
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetEventListener()
{

	$("#btnConfirm").click(f_Confirm);
	$("#btnClose").click(f_Close);

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
	//v_AuthList.bind($("#orgList"));

	ds_AuthList.setAllData(v_AuthList);

	// 데이터 셋팅
	for(var i = 0; i < ds_AuthList.size(); i++){
		var apptCls = ds_AuthList.get(i).apptCls;
		if(apptCls == '01'){
			apptCls = "현직";
		}else{
			apptCls = "겸직";
		}
		var sText = "[" + apptCls + "] " + ds_AuthList.get(i).orgNm + " (" + ds_AuthList.get(i).orgCd + ")";

		ds_AuthList.set(i, "sText", sText);
	}

	$("#orgList").val(ds_AuthList.get(ds_AuthList.find("orgCd", v_OrgCd)));


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

	var callbackObj = ds_AuthList.get(ds_AuthList.find("orgCd", $("#orgList").val()))

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