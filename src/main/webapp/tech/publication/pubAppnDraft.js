
/**
* @class 화면에서 사용할 전역변수를 아래에 선엄함
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
var v_BaseWindowHeight = 700;
var params = {};

//팝업이 닫힐때 리턴값을 전달할 callback 함수
var v_CallbackFunc;
var v_docNo;
var v_docSts;
var v_cls;
var v_orgCd;
var v_orgNm;
var v_userId;
var v_userNm;
var v_userPositCd;
var v_appnDd;
var v_regNo;
var v_subject;
var v_issueRegNo;
var fstRegUserId;
var fnlEditUserId;

var d = new Date();
var curr_year = d.getFullYear();
var curr_month = d.getMonth();
var curr_date = d.getDate();
var curdd = curr_year + "-" + (curr_month+1) + "-" +curr_date ;


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
	v_docNo = gf_IsNull(datas)? "" : gf_IsNull(datas.docNo) ? "": datas.docNo;
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
	// 신청 버튼
	$("#btnAppn").click(function(){
		if(f_CheckValidationForSave() == false){
			return;
		}

		v_cls = datas.cls;
		v_docSts = 'D31';
		v_orgCd = gv_orgCd;
		v_orgNm = gv_orgNm;
		v_userId = gv_userId;
		v_userNm = gv_userNm;
		v_userPositCd = gv_userPositCd;
		v_appnDd = curdd;
		v_regNo = datas.regNo;
		v_issueRegNo = datas.issueRegNo;
		v_subject = datas.subject;
		fstRegUserId = gv_userId;
		fnlEditUserId = gv_userId;


		//해당 업무구분과 업무분류에 해당되는 양식명을 가져옴
		var params = {
						cls    : v_cls,
						docSts : v_docSts,
						orgCd  : v_orgCd,
						orgNm  : v_orgNm,
						userId : v_userId,
						userNm : v_userNm,
						userPositCd : v_userPositCd,
						appnDd : v_appnDd,
						regNo  : v_regNo,
						issueRegNo : v_issueRegNo,
						subject : v_subject,
						fstRegUserId : fstRegUserId,
						fnlEditUserId : fnlEditUserId
		};

		gf_Transaction("DRAFT_TECH_APPN", "/tech/publication/insertPubAppnBas.xpl", params, {}, "f_Callback", false);
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

}

/**
* @class Form Elemenet, Data 초기화
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_InitForm()
{

	/**
	 * 문서 초기화
	 */

	var drafterText = gv_userId + " " + gv_userNm;
	$("#drafter").text(drafterText);

	//소속팀 셋팅
	var drafterOrgNm = gv_orgNm + " (" + gv_orgCd + ")";
	$("#drafterOrgNm").text(drafterOrgNm);
	$("#subject").text(datas.subject);
	//$("#regNo").text(datas.cls + datas.regNo);
	$("#issueRegNo").text(datas.issueRegNo);
	$("#appnDd").text(curdd);
}

/**
* @class 해당 결재양식 정보를 통해 결재문서 정보를 초기화 한다.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function f_SetDocInit(){

}


function f_CheckValidationForSave(){

}

function f_Close(){
	self.close();
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
  	  	case "DRAFT_TECH_APPN" : //신청
  	  		opener.f_Close();
  	  		self.close();
			break;
  	    case "DRAFT_TECH_APPN_DATA" : //신청
  	  		self.close();
  			break;
	  	case "SAVE_TECH_DATA" :  //저장
	  		self.close();
			break;
		case "SAVE_TECH_APPN" :  //신청 Bas 저장
			break;
		case "SAVE_TECH_APPN_DAT" :  //신청 후 자료 Dat 저장
			self.close();
			break;
		case "DELETE_TECH_DATA" :  //삭제
	  		if ( !gf_IsNull(v_CallbackFunc) ) {
	  			var openCallbackFunc = "opener."+v_CallbackFunc;
	  	    	eval(openCallbackFunc + "();");
	  	    }
			break;
	  	default :
	  		break;
	  }
}
