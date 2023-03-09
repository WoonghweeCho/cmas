/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 고준석
* @since 2013-04-04
* @version 1.0
*/

var params = {};						//파라미터 처리 변수
var v_signId = "";
var v_formType = "VIEW";				//VIEW,DRAFT,REDRAFT
var signClientMsg = null;
var v_IsSave = false;

/**
 * 데이터 처리용 DataSet
 */
var ds_CarApp = new DataSet();		//배차신청 DataSet

/**
 * 공통 코드처리 DataSet
 */
var ds_UseCd = new DataSet();		//배차신청 '용도'공통 코드
var ds_DriveType = new DataSet();		//배차신청 '용도'공통 코드

/**
* @class 화면 로드 완료 시 필요한 초기 작업 수행.
*        1. 파라미터 초기화
*        2. 컴포넌트 초기화
*        3. Event Listener 초기화
*        4. 화면내 Form 객체 초기화
*        5. 다국어 처리
* @author 고준석
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
* @author 고준석
* @since 2013-04-04
* @version 1.0
*/
function cf_InitParam()
{

}

/**
* @class Form Onload 시 컴포넌트 초기화
* @author 고준석
* @since 2013-04-04
* @version 1.0
*/
function cf_SetComponents()
{
	//시작일/종료일 검색 Date Component 초기화
	$( "input[name='rncrStDt']" ).mask("9999-99-99").datepicker({
		dateFormat: "yy-mm-dd",
		onSelect : function(text, obj){
			$( "input[name='rncrStDt']" ).trigger("change");
		}
	});
	$( "input[name='rncrEdDt']" ).mask("9999-99-99").datepicker({
		dateFormat: "yy-mm-dd",
		onSelect : function(text, obj){
			$( "input[name='rncrEdDt']" ).trigger("change");
		}
	});
}

/**
* @class Form Onload DataSet Binding 처리
* @author 고준석
* @since 2013-04-04
* @version 1.0
*/
function cf_SetBinding()
{
	ds_CarApp.bind($("#editForm")[0]);

	//'용도' DataSet Binding
	ds_UseCd.bind($("select[name='carAppnClscd']")[0], {val: "code", text: "value"});

	//'용도' DataSet Binding
	ds_DriveType.bind($("select[name='drvClscd']")[0], {val: "code", text: "value"});
}

/**
* @class Form Onload 시 Element/Component 인벤트 초기화
* @author 고준석
* @since 2013-04-04
* @version 1.0
*/
function cf_SetEventListener()
{
	$("#save").click(function(){
		cf_InsertCarApp();
	});
}

/**
* @class Form Onload 시 객체 초기 값 설정
* @author 고준석
* @since 2013-04-04
* @version 1.0
*/
function cf_InitForm()
{
	//용도 Select Box 처리
	gf_GetCommCds("EA001", ds_UseCd);
	ds_UseCd.insert(0, {code: 'ALL', value: ''});

	//운전구분 Select Box 처리
	gf_GetCommCds("EA002", ds_DriveType);
	ds_DriveType.insert(0, {code: 'ALL', value: ''});

	//페이지 로드시에 post Msg를 연결함.
	try{
		var loc = document.location;
		var domain = loc.protocol + "//" + loc.host;
		var signClientMsg = new SignClientMsg(domain);
		signClientMsg.setMsg();
		signClientMsg.connect();
	}catch(e){
		signClientMsg = null;
		f_InitForm();
	}

}

function cf_LinkForm(args){
	var docSysCd = args.docSysCd;
	v_signId = args.signId;

	if(docSysCd == "D01")
		v_formType = "DRAFT";
	else if(docSysCd == "D16")
		v_formType = "REDRAFT";
	else
		v_formType = "VIEW";

	$("#save").hide();
	f_InitForm();
}

function f_InitForm(){

	if(v_formType == "DRAFT"){ //열람
		ds_CarApp.insert(0,{ //작성
			signId : v_signId,
			rncrStDt : "",
			rncrStTm : "",
			rncrEdDt : "",
			rncrEdTm : "",
			carAppnClscd : "",
			appnResn : "",
			destNm : "",
			userNm : "",
			drvClscd : "",
			carKindExpl : ""
		});
	}else{
		cf_RetrieveCarApp();
		if(v_formType != "REDRAFT")
			$("#editForm").find("input, select").attr("disabled", "disabled");
	}
}

/**
 * 화면 열람 형태에 따라 결재시스템의 이벤트 발생(상신, 재기안, 결재, 반려)시
 * 서버 Transaction을 처리한다.
 *
 * D01 : 기안작성           - 저장
 * D16 : 임시저장 후 재기안  - 수정
 * 그외 : 열람
 *
 * @returns {Boolean}
 */
function cf_SignCheck(){

//	var rncrStDt = gf_GetValue($("input[name='rncrStDt']").val());
		//gf_AlertMsg(rncrStDt);

	var rncrStDt = gf_GetValue($("input[name='rncrStDt']").val());	//시작일 체크
	if(gf_IsNull(rncrStDt)){
		gf_AlertMsg('co.info.contractFrom');
		return false;
	}

	var rncrEdDt = gf_GetValue($("input[name='rncrEdDt']").val());	//종료일 체크

	if(gf_IsNull(rncrEdDt)){
		gf_AlertMsg('co.info.contractTo');
		return flase;
	}

	var carAppnClscd = gf_GetValue($("select[name='carAppnClscd']").val());//용도 체크

	if(carAppnClscd == 'ALL'){
		gf_AlertMsg('co.info.Clscd');
		return false;
	}

	var appnResn = gf_GetValue($("input[name='appnResn']").val());	//사유 체크
	if(gf_IsNull(appnResn)){
		gf_AlertMsg('co.info.Resn');
		return false;
	}

	var destNm = gf_GetValue($("input[name='destNm']").val());	//행선지 체크
	if(gf_IsNull(destNm)){
			gf_AlertMsg('co.info.destNm');
			return false;
	}

	var userNm = gf_GetValue($("input[name='userNm']").val());	//탑승자 체크
	if(gf_IsNull(userNm)){
		gf_AlertMsg('co.info.userNm');
		return false;
	}

	var drvClscd = gf_GetValue($("select[name='drvClscd']").val());	//운전구분 체크
	if(drvClscd == 'ALL'){
		gf_AlertMsg('co.info.drvClscd');
		return false;
	}

	var carKindExpl = gf_GetValue($("input[name='carKindExpl']").val());	//차종 체크
	if(gf_IsNull(carKindExpl)){
		gf_AlertMsg('co.info.carKindExpl');
		return false;
	}

	if(v_formType == "DRAFT")
		gf_Transaction("SELECT_INSERT_CARAPP", "/eaps/insertCarApp.xpl", ds_CarApp.get(0), {}, "f_Callback", false);
	else if(v_formType == "REDRAFT")
		gf_Transaction("SELECT_UPDATE_CARAPP", "/eaps/upateCarApp.xpl", ds_CarApp.get(0), {}, "f_Callback", false);
    return true;

}

/**
* @class 조건에 맞는 결재양식 목록을 조회한다.
* @author 고준석
* @since 2013-04-04
* @version 1.0
*/
function cf_RetrieveCarApp(){
	/**
	 * Map<String, Object> 형태 파라미터 처리
	 */
	var datas= {
		signId	: v_signId
	};

    gf_Transaction("SELECT_CARAPP", "/eaps/selectCarApp.xpl", datas, {}, "f_Callback", true);
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
	  	case "SELECT_INSERT_CARAPP" :
	  		v_IsSave = true;
			break;
	  	case "SELECT_UPDATE_CARAPP" :
	  		v_IsSave = true;
			break;
	  	case "SELECT_CARAPP" :
	  		ds_CarApp.setAllData(resultData.output1);
	  		ds_CarApp.setPosition(0);
			break;
	  	default :
	  		break;
	  }
}
