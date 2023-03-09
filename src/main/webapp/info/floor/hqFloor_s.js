/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 고준석
* @since 2013-04-04
* @version 1.0
*/

var params = {};						//파라미터 처리 변수
var v_userId = "";
var v_signId;
var v_signUserId;
var v_formType;
var v_drtxBas;


var v_IsSave = false;

var ds_FloorList = new DataSet();		//목록 DataSet


// 가상셀렉터 박스
var floorSelect;	// 그리드 셀에 사용될 가상 셀렉터박스(ds_ClsCd에 바인드됨)
var bonbooSelect;	// 그리드 셀에 사용될 가상 셀렉터박스(ds_ClsCd에 바인드됨)


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
//	cf_SetEventListener();
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
	/**
	 * 파라미터
	 */
}

/**
* @class Form Onload 시 컴포넌트 초기화
* @author 고준석
* @since 2013-04-04
* @version 1.0
*/
function cf_SetComponents()
{
	clsCdSelect = $("<select>");

	//목록 JQGrid
	var floorList = {
		datatype: "local",
	   	colNames:[
	   	          gf_FindLang('층'), gf_FindLang('본부'), gf_FindLang('팀')
	   	          ],
	   	colModel:[
	   	        // 층
	  	   		{name:'flr',index:'flr', width:40, editable:false, fixed: true, align: "center", sortable : false},

		   		// 본부
		   		{name:'orgNm',index:'orgNm', width:100, editable:false, fixed: true},

		   		// 팀
		   		{name:'dtlInfo',index:'dtlInfo', width:850, editable:false, fixed: true, sortable : false}
	   	],
	   	height:200,
	   	sortname: 'id',
	    viewrecords: true,
	    sortorder: "desc",
	    rowNum:100

	};



	/**
	 * 화면에 JQGrid 연결
	 */
	$("#floorList").jqGrid(floorList);

	/**
	 * Container 크기에 맞춰 Windows Resizing 될 때 Box Grid의 사이즈를 조절한다.
	 */
	$(window).bind("resize", function(){
		$("#floorList").setGridWidth($("#container").width()-34);
	}).trigger('resize');
}

/**
* @class Form Onload DataSet Binding 처리
* @author 고준석
* @since 2013-04-04
* @version 1.0
*/
function cf_SetBinding()
{
	//목록 Grid Binding
	ds_FloorList.bind($("#floorList"));
}

/**
* @class Form Onload 시 Element/Component 인벤트 초기화
* @author 고준석
* @since 2013-04-04
* @version 1.0
*/
function cf_SetEventListener()
{
}


/**
* @class Form Onload 시 객체 초기 값 설정
* @author 고준석
* @since 2013-04-04
* @version 1.0
*/
function cf_InitForm()
{
	/**
	 * 양식 목록 조회
	 */
	cf_RetrieveFloorList();
	var loc = document.location;
	var domain = loc.protocol + "//" + loc.host;
}


/**
* @class 조건에 맞는 결재양식 목록을 조회한다.
* @author 고준석
* @since 2013-04-04
* @version 1.0
*/
function cf_RetrieveFloorList(){
	/**
	 * Map<String, Object> 형태 파라미터 처리
	 */
	var datas= {
	};

    gf_Transaction("SELECT_FLOOR_LIST", "/info/floorList.xpl", datas, {}, "f_Callback", true);
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
	  	case "SELECT_FLOOR_LIST" :
	  		ds_FloorList.setAllData(resultData.output);
	  		ds_FloorList.setPosition(0);
			break;
	  	default :
	  		break;
	  }
}

function f_AddRow(){

	ds_FloorList.add({
		flr: "",
		orgNm: "",
		dtlInfo: "",
		priority: "",
		fstRegDt: "",
		fstRegUserId: "",
		fnlEditDt: "",
		fnlEditUserId: "",
		signId: v_signId
	});

}

function cf_Save(){

	v_IsSave = false;
	/**
	 * Map<String, Object> 형태 파라미터 처리
	 */

	var datas= {
		signId : v_signId
	};

	/**
	 * DataSet 파라미터 처리
	 */
	var dataSets = {
	};
}

function cf_LinkForm(args){

	f_InitForm();

}

function f_InitForm(){

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


}