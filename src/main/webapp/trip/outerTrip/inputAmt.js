/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/

var ds_NatList = new DataSet();
var ds_ExpnList = new DataSet();
var ds_RefList = new DataSet();

var v_TripUserPositCd;
var v_TripUserId;

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
	var expnData = gf_IsNull(datas.ds_ExpnList) ? ""          : datas.ds_ExpnList;
	var refData = gf_IsNull(datas.ds_RefList) ? ""          : datas.ds_RefList;

	v_TripUserPositCd = gf_IsNull(datas.v_TripUserPositCd) ? ""          : datas.v_TripUserPositCd;
	v_TripUserId = gf_IsNull(datas.v_TripUserId) ? ""          : datas.v_TripUserId;


	ds_NatList.setAllData(natData);
	ds_ExpnList.setAllData(expnData);
	ds_RefList.setAllData(refData);

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

	$("#usNight").bind('change', function(e) {
		cf_showHideSpanById("usNight");
	});
	$("#usDay").bind('change', function(e) {
		cf_showHideSpanById("usDay");
	});
	$("#edu").bind('change', function(e) {
		cf_showHideSpanById("edu");
	});
	$("#spot").bind('change', function(e) {
		cf_showHideSpanById("spot");
	});
	$("#euNight").bind('change', function(e) {
		cf_showHideSpanById("euNight");
	});
	$("#euDay").bind('change', function(e) {
		cf_showHideSpanById("euDay");
	});
	$("#enNight").bind('change', function(e) {
		cf_showHideSpanById("enNight");
	});
	$("#enDay").bind('change', function(e) {
		cf_showHideSpanById("enDay");
	});
	$("#jaNight").bind('change', function(e) {
		cf_showHideSpanById("jaNight");
	});
	$("#jaDay").bind('change', function(e) {
		cf_showHideSpanById("jaDay");
	});

	f_numberOnly("usDayI");
	f_numberOnly("usNightI");
	f_numberOnly("eduI");
	f_numberOnly("spotI");
	f_numberOnly("euNightI");
	f_numberOnly("euDayI");
	f_numberOnly("enNightTr");
	f_numberOnly("euDayI");
	f_numberOnly("enNightI");
	f_numberOnly("enDayI");
	f_numberOnly("jaNightI");
	f_numberOnly("jaDayI");

	f_numberOnly("usNightRef");
	f_numberOnly("usDayRef");
	f_numberOnly("eduRef");
	f_numberOnly("spotRef");
	f_numberOnly("euNightRef");
	f_numberOnly("euDayRef");
	f_numberOnly("enNightRef");
	f_numberOnly("enDayRef");
	f_numberOnly("jaNightRef");
	f_numberOnly("jaDayRef");











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

	if(ds_ExpnList.size() > 0){
		for(var i = 0; i < ds_ExpnList.size(); i++){
			var id = ds_ExpnList.get(i, "expnCls");
			$("#" + id).attr("checked", "true");
			$("#" + id + "I").val(ds_ExpnList.get(i, "accomoExpnDcnt"));
			cf_showHideSpanById(id);
			$("#" + id + "Ref").val(ds_ExpnList.get(i, "ddExpnDcnt"));

		}
	}

	var params = {
			userId : v_TripUserId
	};

	gf_Transaction("SELECT_REAL_POSITCD", "/trip/outerTrip/getUserRealPositCd.xpl", params, {}, "f_Callback", true);




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
	  	case "SELECT_REAL_POSITCD" :

	  		if(resultData.output1.length > 0){
	  			var rPcd = resultData.output1[0].userRealPositCd;

	  			if(rPcd == ""){

	  			}else if(rPcd == undefined){

	  			}else{
	  				v_TripUserPositCd = rPcd;

	  				if(v_TripUserPositCd == '전문위원'){
	  						v_TripUserPositCd = '상무';
	  				}

	  				if(v_TripUserPositCd == '수석연구원'){
  						v_TripUserPositCd = '부장';
	  				}

	  				if(v_TripUserPositCd == '책임연구원'){
  						v_TripUserPositCd = '차장';
	  				}

	  				if(v_TripUserPositCd == '선임연구원'){
  						v_TripUserPositCd = '과장';
	  				}

	  				if(v_TripUserPositCd == '전임연구원'){
  						v_TripUserPositCd = '대리';
	  				}

	  				if(v_TripUserPositCd == '연구원'){
  						v_TripUserPositCd = '사원';
	  				}

	  			}

	  		}else{



	  		}

	  		// 입력받은 체제비가 없다면 기준표에서 셋팅
  		  	var usNightRef = checkRefVal(v_TripUserPositCd, "일반숙박");
  		  	var usDayRef = checkRefVal(v_TripUserPositCd, "일반일당");
  		  	var eduRef = checkRefVal(v_TripUserPositCd, "연수경비(숙)");
  		  	var spotRef = checkRefVal(v_TripUserPositCd, "연수경비(숙식)");
  		  	var euNightRef = checkRefVal(v_TripUserPositCd, "유럽숙박");
  		  	var euDayRef = checkRefVal(v_TripUserPositCd, "유럽일당");
  		  	var enNightRef = checkRefVal(v_TripUserPositCd, "영국숙박");
  		  	var enDayRef = checkRefVal(v_TripUserPositCd, "영국일당");
  		  	var jaNightRef = checkRefVal(v_TripUserPositCd, "일본숙박");
  		  	var jaDayRef = checkRefVal(v_TripUserPositCd, "일본일당");

	  		if($("#usNightRef").val() == ""){
	  			$("#usNightRef").val(usNightRef);
	  		}
	  		if($("#usDayRef").val() == ""){
	  			$("#usDayRef").val(usDayRef);
	  		}
	  		if($("#eduRef").val() == ""){
	  			$("#eduRef").val(eduRef);
	  		}
	  		if($("#spotRef").val() == ""){
	  			$("#spotRef").val(spotRef);
	  		}
	  		if($("#euNightRef").val() == ""){
	  			$("#euNightRef").val(euNightRef);
	  		}
	  		if($("#euDayRef").val() == ""){
	  			$("#euDayRef").val(euDayRef);
	  		}
	  		if($("#enNightRef").val() == ""){
	  			$("#enNightRef").val(enNightRef);
	  		}
	  		if($("#enDayRef").val() == ""){
	  			$("#enDayRef").val(enDayRef);
	  		}
	  		if($("#jaNightRef").val() == ""){
	  			$("#jaNightRef").val(jaNightRef);
	  		}
	  		if($("#jaDayRef").val() == ""){
	  			$("#jaDayRef").val(jaDayRef);
	  		}





		  break;
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


	var spcFlg = true;
	var refFlg = true;
	for(var i = 0; i < $("#natListTr input").size(); i++){
		if($("#natListTr input")[i].checked == true){
			if($("#" + $("#natListTr input")[i].id + "I").val() == "") spcFlg = false;
			if($("#" + $("#natListTr input")[i].id + "Ref").val() == "") refFlg = false;
		}
	}

	if(refFlg == false){
		alert("기준액을 입력하세요.");
		return;
	}

	if(spcFlg == false){
		alert("일수를 입력하세요.");
		return;
	}



	var ds_AmtData = new DataSet();

	for(var i = 0; i < $("#natListTr input").size(); i++){
		ds_AmtData.add({
			id : $("#natListTr input")[i].id,
			chk : $("#natListTr input")[i].checked,
			val : $("#" + $("#natListTr input")[i].id + "I").val(),
			ref : $("#" + $("#natListTr input")[i].id + "Ref").val()
		});
	}


	var callbackObj = {
			result : ds_AmtData.getAllData()
	};

//	var callbackObj = {
//			result : ds_AmtData.getAllData(),
//			usNightRef : $("#usNightRef").val(),
//			usDayRef : $("#usDayRef").val(),
//			eduRef : $("#eduRef").val(),
//			spotRef : $("#spotRef").val(),
//			euNightRef : $("#euNightRef").val(),
//			euDayRef : $("#euDayRef").val(),
//			enNightRef : $("#enNightRef").val(),
//			enDayRef : $("#enDayRef").val(),
//			jaNightRef : $("#jaNightRef").val(),
//			jaDayRef : $("#jaDayRef").val()
//	};

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

function cf_showHideSpanById(idName){

	var selector = "#" + idName;
	var idName = selector + ":checked";
	var idTr = selector + "Tr";
	if($(idName).size() < 1){
		//안체크
		$(idTr).hide();
	}else{
		// 체크
		$(idTr).show();
	}

}

function f_numberOnly(id){


	$("#"+id).bind('keypress', function(e) {

		var keyID = (e.which) ? e.which : e.keyCode;
//		if ((keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ){
		if ((keyID >= 48 && keyID <= 57) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ){
			return true;
		}else{
			return false;
		}

	});

}

function checkRefVal(positNm, cls){
	for(var i = 0; i < ds_RefList.size(); i++){
		if(ds_RefList.get(i, "positNm") == positNm && ds_RefList.get(i, "cls") == cls){
			return ds_RefList.get(i, "val");
		};
	}
	return -1;
}