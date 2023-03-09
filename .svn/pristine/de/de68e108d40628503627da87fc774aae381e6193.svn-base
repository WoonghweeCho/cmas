
/**
* @class 화면에서 사용할 전역변수를 아래에 선엄함
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
var v_BaseWindowHeight = 700;

var params = {};
var v_loclCd;

var v_regNo = "";
var v_subject = "";
var v_issuePl = "";
var v_issueYr = "";
var v_ath = "";
var v_cls = "";
var v_interClscd = "";
var v_cont = "";
var v_docNo = "";
var v_docSts = "";

var ds_TechDatList = new DataSet();	// 상세정보

/**
 * 공통 코드처리 DataSet
 */
var ds_Cls = new DataSet();		// 분류 공통코드


//팝업이 닫힐때 리턴값을 전달할 callback 함수
var v_CallbackFunc;
/**
* @class 화면 로드 완료 시 필요한 초기 작업 수행.
*        1. 파라미터 초기화
*        2. 컴포넌트 초기화
*        3. Event Listener 초기화
*        4. 바인딩 초기화
*        5. 화면내 Form 객체 초기화
*        6. 다국어 처리
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
$(function() {
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
	v_regNo = gf_IsNull(datas)? "" : gf_IsNull(datas.regNo) ? "": datas.regNo;
	v_docSts = gf_IsNull(datas)? "" : gf_IsNull(datas.docSts) ? "": datas.docSts;
	v_CallbackFunc = gf_IsNull(datas.callbackFunc) ? "" : datas.callbackFunc;

}

/**
* @class 컴포넌트 생성
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetComponents()
{

}

/**
* @class Element, Compoment Event 처리
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetEventListener()
{
	// 저장 버튼
	$("#btnSave").click(function(){
		if(f_CheckValidationForSave() == false){
			return;
		}

	var icont = $("#cont").val();
		for(var i = 0; i < icont.length; i++){
			icont = icont.replace("\n", "##");
		}

	v_regNo   = $("#regNo").val();
	v_subject = $("#subject").val();
	v_issuePl = $("#issuePl").val();
	v_issueYr = $("#issueYr").val();
	v_ath     = $("#ath").val();
	v_cls     = $("select[name='cls'] option:selected").val();
	v_interClscd = $("#interClscd").val();
	v_cont    = icont;
	v_docSts  = "D30";

	var params = {
			regNo   : v_regNo,
			subject : v_subject,
			issuePl : v_issuePl,
			issueYr : v_issueYr,
			ath     : v_ath,
			cls     : v_cls,
			interClscd : v_interClscd,
			cont    : v_cont,
			docSts  : v_docSts
	};


	gf_Transaction("SAVE_TECH_DATA", "/tech/techAppn/insertTechDat.xpl", params, {}, "f_Callback", true);

	});

	 //닫기 버튼
	$("#btnCancle").click(function(){
		self.close();
	});

}

/**
* @class Form Onload DataSet Binding 처리
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetBinding()
{
	ds_TechDatList.bind($("#techDataDtlForm")[0]);

}

/**
* @class Form Elemenet, Data 초기화
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_InitForm()
{


}


function f_CheckValidationForSave(){

	var subject = gf_GetValue($("input[name='subject']").val());	//제목
	var issuePl= gf_GetValue($("input[name='issuePl']").val());	//발행처
	var issueYr= gf_GetValue($("input[name='issueYr']").val());	//발행년도

	if(gf_IsNull(subject)){ gf_AlertMsg("제목은 필수입니다."); return false; }
	if(gf_IsNull(issuePl)){ gf_AlertMsg("발행처는 필수입니다."); return false; }
	if(issueYr.length>4){ gf_AlertMsg("발행년도 : YYYY 형식입니다."); return false; }

//	if(gf_IsNull(datas.title)) { gf_AlertMsg("제목은 필수 항목입니다."); return; }

}

function f_Close(){
}


/**
* @class Transaction 처리 후 수행되는 Callback 함수
*
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function f_Callback(strSvcId, obj, resultData){
	  if (!gf_ChkTransaction(strSvcId, resultData, true )) {
		  return;
	  }

	  switch(strSvcId) {
  	  	case "DRAFT_TECH_DATA" : //등록
	  		if ( !gf_IsNull(v_CallbackFunc) ) {
	  			var openCallbackFunc = "opener."+v_CallbackFunc;
	  	    	eval(openCallbackFunc + "();");
	  	    }
	  		gf_DisableCurrentOverlay();
	  		self.close();
			break;
	  	case "SAVE_TECH_DATA" :  //저장
	  		self.close();
			break;
	  	case "DELETE_TECH_DATA" :  //삭제
	  		if ( !gf_IsNull(v_CallbackFunc) ) {
	  			var openCallbackFunc = "opener."+v_CallbackFunc;
	  	    	eval(openCallbackFunc + "();");
	  	    }
	  		self.close();
			break;
	  	default :
	  		break;
	  }
}
