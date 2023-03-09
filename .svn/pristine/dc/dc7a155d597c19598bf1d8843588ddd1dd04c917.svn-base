/**
 * 공통 코드처리 DataSet
 */

var v_url = '';

// 팝업이 닫힐때 리턴값을 전달할 callback 함수
var v_CallbackFunc;
/**
 * @class 화면 로드 완료 시 필요한 초기 작업 수행. 1. 파라미터 초기화 2. 컴포넌트 초기화 3. Event Listener 초기화
 *        4. 바인딩 초기화 5. 화면내 Form 객체 초기화 6. 다국어 처리
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
function cf_InitParam() {
	v_url = gf_IsNull(datas.url) ? "" : datas.url;
	//alert(">>>"+v_url);
}

function cf_SetComponents()
{

}

/**
 * @class Element, Compoment Event 처리
 * @author 권준호
 * @since 2013-04-04
 * @version 1.0
 */
function cf_SetEventListener() {

	// 닫기 버튼
	$("#btnCancle").click(function() {
		self.close();
	});

    // 인쇄
	//$("#btnPrint").click(function(){
		//var newWin=gf_PostOpen("/RexServer30/viewer.jsp?rex_rptname=tech/serge&connectname=oracle1&isOpenWindow=true&option=1&issueRegNo="+v_issueRegNo+"&rex_db=oracle1");
		//$("#infoDiv").fnBtnEvent('print')
	//});

}

/**
 * @class Form Onload DataSet Binding 처리
 * @author 권준호
 * @since 2013-04-04
 * @version 1.0
 */
function cf_SetBinding() {

}

/**
 * @class Form Elemenet, Data 초기화
 * @author 권준호
 * @since 2013-04-04
 * @version 1.0
 */
function cf_InitForm() {

	var hiddenLoadFunc = function(type, current, total){
	};

	var hiddenLoadFuncNm = "hiddenLoadFunc" + $.now();
	window[hiddenLoadFuncNm] = hiddenLoadFunc;
	$("#rexpert").attr("hiddenLoadFunc", hiddenLoadFuncNm);

	//ActiveX 생성
	$("#rexpert").attr("src", gv_ServerUrl+v_url);

}

/**
 * @class Transaction 처리 후 수행되는 Callback 함수
 *
 * @author 권준호
 * @since 2013-04-04
 * @version 1.0
 */
function f_Callback(strSvcId, obj, resultData) {
	if (!gf_ChkTransaction(strSvcId, resultData, true)) {
		return;
	}

	switch (strSvcId) {
	case "DRAFT_TECH_DATA": // 등록
		self.close();
		break;
	case "SAVE_TECH_DATA": // 저장
		self.close();
		break;
	case "DELETE_TECH_DATA": // 삭제
		self.close();
		break;
	case "SELECT_VIEW_DOC_INFO":
		ds_DocData.setAllData(resultData.ds_DocData);
	default:
		break;
	}
}

