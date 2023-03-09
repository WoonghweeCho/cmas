/**
 * Modal Popup 처리를 위한 필수 변수
 */
var v_CallbackFunc; 	//팝업이 닫힐때 리턴값을 전달할 callback 함수
var v_FstLvlPop = true; //팝업 레벨
var v_IsModal = false; 	//Modal Popup 여부

var ds_IdAppnDtlList = new DataSet();	// 상세정보
/**
* @class 화면 로드 완료 시 필요한 초기 작업 수행.
*        1. 파라미터 초기화
*        2. 컴포넌트 초기화
*        3. Event Listener 초기화
*        4. 바인딩 초기화
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
	/**
	 * Modal Popup 처리를 위한 변수 초기화
	 */
	v_CallbackFunc = gf_IsNull(datas.callbackFunc) ? "" : datas.callbackFunc;
	v_FstLvlPop = gf_IsNull(datas.fstLvlPop) ? v_FstLvlPop : eval(datas.fstLvlPop);
	v_IsModal = gf_IsNull(datas.isModal) ? v_IsModal : eval(datas.isModal);
}

/**
* @class 컴포넌트 생성
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetComponents()
{
	// 부서를 선택했을시 부서내의 사원목록을 출력함.
	$("#users").jqGrid({
		datatype: "local",
	   	colNames:[gf_FindLang('특별ID'),gf_FindLang('이름'),gf_FindLang('종료일')],
	   	colModel:[
	  	   		{name:'userId',index:'userId', width:55},
	  	   		{name:'userNm',index:'userNm', width:55},
		   		{name:'edYmd',index:'edYmd', width:60}
	   	],
	   	width:290,
	   	height:250,
	   	rowNum:10000,
	   	sortname: 'userId',
	    viewrecords: true,
	    multiselect: true,
	    sortorder: "asc"
	});
	$("#users").jqGrid('navGrid','#usersPager',{edit:false,add:false,del:false});

	/**
	 * Container 크기에 맞춰 Windows Resizing 될 때 Box Grid의 사이즈를 조절한다.
	 */
	$(window).bind("resize", function(){

		var parentDiv = $("#users").parents(".list_st2");
		$("#users").setGridWidth(parentDiv.width());
	}).trigger('resize');

	$("#users").attr('unselectable', 'on')
				    .css('user-select', 'none')
				    .on('selectstart', false);
}

/**
* @class Form Onload DataSet Binding 처리
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetBinding()
{
	// 사원목록 데이터셋을 그리드에 바인드
	ds_IdAppnDtlList.bind($("#users"));
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

/**
* @class Element, Compoment Event 처리
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetEventListener(){
	/**
	 * 부서, 사용자 조회
	 */
	$("#btnSearch").click(function(){
		f_Search();
	});

	// 현재 지정된 결재선을 결재문서에 사용함.ㅣ
	$("#btnConf").click(function(){
		var ds_Selected = new DataSet();
		var selarr = $("#users").getGridParam("selarrrow");
		var pos = ds_Selected.getPosition() < 0 ? ds_Selected.size()-1: ds_Selected.getPosition();

		for(var i=0; i <selarr.length; i++){
			var dataRow = $("#users").data(selarr[i]);
			var cloneRow = dataRow.clone();
			ds_Selected.insert((pos++) + 1 , cloneRow);
		}
		opener.setIdAppnDtlList(ds_Selected);
		self.close();
	});

	// 결재선 선택을 취소함
	$("#btnClose").click(function(){
		self.close();
	});

	$("#table_user_search").bind('keypress', function(e) {
	    if (e.which == 13) {
	    	f_Search();
	    }
	});


	// child window를 모두 close 처리 하고 modal 일경우 부모창을 비활성화 시킨 것을 다시 활성화 처리 한다.
	gf_SetPopCloseEvent(v_IsModal, v_FstLvlPop);
	// Modal 처리를 위한 공통 함수 호출
	gf_EnableOverlayOpener(false, v_FstLvlPop, v_IsModal);
}

/**
* @class 조회 버튼 클릭시 수행되는 이벤트 함수
* @author 변형구
* @since 2013-04-12
* @version 1.0
*/
function f_Search() {
	var srchTxt = $("input[name='txtSrchTxt']").val();

	if ( gf_IsNull(srchTxt) ) {
		srchTxt = "";
	}
	f_RetrieveUser(srchTxt.toLowerCase());
}

function f_RetrieveUser(srchTxt) {
	var params = {
			srchTxt : srchTxt		//문서번호
	};
	gf_Transaction("SELECT_USER_ID_LIST", "/id/idAppn/retrieveUserIdList.xpl", params, {}, "f_Callback", true);
}

/**
* @class Transaction Callback 처리
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
	  	case "SELECT_USER_ID_LIST" :	// 지정된 부서에 해당되는 직원목록을 출력함
	  		ds_IdAppnDtlList.setAllData(resultData.ds_IdAppnDtlList);
			break;
	  	default :
	  		break;
	  }
}

