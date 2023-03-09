
/***********************************************************************************************
/*----------------------------------------------------------------------------------------------
 * 설명   : 화면에서 사용될 전역변수를 아래에 기술함.
 * 작성자 : 권준호
 * 작성일 : 2013.3.05
 ----------------------------------------------------------------------------------------------*/

var setting = {
	view: {
		dblClickExpand: false,
		showLine: true,
		selectedMulti: false
	},
	data: {
		simpleData: {
			enable:true,
			idKey: "orgCd",
			pIdKey: "hgrOrgCd",
			rootPId: ""
		},
		key: {
			name: "orgNm"
		}
	},
	callback: {
		beforeClick: function(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("orgTree");
			if(v_oldOrgCd != treeNode.orgCd){
				f_RetrieveSignUserList(treeNode.orgCd);
				v_oldOrgCd = treeNode.orgCd;
			}

			if (treeNode.isParent) {
				zTree.expandNode(treeNode);
				return false;
			} else {
				return true;
			}
		}
	}
};
var signId = "";	// 결재문서 ID
var v_SignSeq = 0;
var v_Csnd = "";	// 결재문서 ID
var v_IsDraft = false;
var v_IsMDraft = false;
var params = {};	// params로 요청이 들어온 추가 정보를 받음.

var v_oldOrgCd = null;

var ds_SignOrg = new DataSet();	// 부서 목록
var ds_Signln = new DataSet();	// 부서에서 선택된 해당부서의 직원 목록
var ds_SelSignln = new DataSet();	// 결재자로 선택된 직원 목록
var ds_SignType = new DataSet();	// 결재유형 코드목록

var zNodes = [];	//트리를 형성할 배열

var signTypeSelect;	// 그리드 셀에 사용될 가상 셀렉터박스(ds_SignType에 바인드됨)

/**
 * Modal Popup 처리를 위한 필수 변수
 */
var v_CallbackFunc; 	//팝업이 닫힐때 리턴값을 전달할 callback 함수
var v_FstLvlPop = true; //팝업 레벨
var v_IsModal = false; 	//Modal Popup 여부

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
	signId = datas.signId;
	v_SignSeq = gf_IsNull(datas.signSeq)? 0 : datas.signSeq;
	v_IsDraft = gf_IsNull(datas.isDraft)? false: datas.isDraft;
	v_IsMDraft = gf_IsNull(datas.isMDraft)? false: datas.isMDraft;
	params.signId = signId;

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
	// 결재자를 선택하는 그리드에 결재유형 셀렉터박스를 설정함.
	signTypeSelect = $("<select>");

	// 부서를 선택했을시 부서내의 사원목록을 출력함.
	$("#signln").jqGrid({
		datatype: "local",
	   	colNames:[gf_FindLang('사번'),gf_FindLang('이름'),gf_FindLang('직위'), gf_FindLang('직책/직무'), gf_FindLang('부서/현장')],
	   	colModel:[
	  	   		{name:'signUserId',index:'signUserId', width:55},
	  	   		{name:'signUserNm',index:'signUserNm', width:55},
		   		{name:'apperPositNm',index:'apperPositNm', width:60},
		   		{name:'apperRpswrkCd',index:'apperRpswrkCd', width:80},
		   		{name:'apperOrgNm',index:'apperOrgNm', width:80}
	   	],
	   	width:300,
	   	height:150,
	   	rowNum:10000,
	   	sortname: 'signUserNm',
	    viewrecords: true,
	    multiselect: true,
	    sortorder: "asc",
	    ondblClickRow:function(rowid, iRow, iCol, e){
	    	f_AddUser("T02");
	    }
	});
	$("#signln").jqGrid('navGrid','#signlnPager',{edit:false,add:false,del:false});

	// 선택된 결재선을 출력하는 그리드
	$("#selSignln").jqGrid({
		datatype: "local",
	   	colNames:['', '',gf_FindLang('결재방법'),gf_FindLang('사번'),gf_FindLang('이름'), gf_FindLang('직위'),
	   	          gf_FindLang('직책/직무'),gf_FindLang('부서/현장')],
	   	colModel:[
	  	   		{name:'upBt',index:'upBt', width:20, sortable: false, align:"center",
	  	   			formatter:function(val, gOpt, row){
	  	   				return gf_ImageView(gv_ContextPath + "/common/images/btn_ShowPopUpN.png", row, gOpt);
	  	   			}
	  	   		},
	  	   		{name:'downBt',index:'downBt', width:20, sortable: false, align:"center",
	  	   			formatter:function(val, gOpt, row){
	  	   				return gf_ImageView(gv_ContextPath + "/common/images/btn_ShowPopDnP.png", row, gOpt);
	  	   			}
	  	   		},
	  	   		{name:'signTpNm',index:'signTpNm', width:60, sortable: false,
	  	   			editable:false, edittype:'custom',
	  	   			editoptions:{
	  	   				custom_element: function(curVal,opt){
	  	   					var selrow = opt.id.split("_")[0];//$("#selSignln").jqGrid("getGridParam","selrow");
	  	   					var result = "";
	  	   					if(selrow == "1"){
	  	   						ds_SignType.filter(null);
		  	   					result = curVal;
	  	   					}else{
	  	   						ds_SignType.filter(function(row){
	  	   							if(row.get("refCd1") == "D" && row.get("code") != "T01")
	  	   								return true;
	  	   							return false;
	  	   						});
	  	   						result = signTypeSelect.clone();
		  	   					result = gf_BindInnerSelect(curVal,opt, result[0]);
	  	   					}
	  	   				    return result;
	  	   				},
	  	   				custom_value: function(elem, oper, value){
		  	   				var result = gf_BindValue(elem, oper, value);
		  	   				result = typeof(result) == "undefined"? "" : result;
		  	   				return result;
	  	   				}
	  	   			},
	  	   			formatter:function(val, gOpt, row){
	  	   				ds_SignType.filter(null);
	  	   				var result = gf_BindView(val, row, gOpt, {dataSet:ds_SignType, curVal:"signTpCd", val: "code", text:"value"});
	  	   				return result;
	  	   			}
	  	   		},
		   		{name:'signUserId',index:'signUserId', width:60, sortable: false, align:"center"},
		   		{name:'signUserNm',index:'signUserNm', width:60, sortable: false, align:"center"},
		   		{name:'apperPositCd',index:'apperPositCd', width:50, sortable: false, align:"center"},
		   		{name:'apperRpswrkCd',index:'apperRpswrkCd', width:70, sortable: false, align:"center"},
		   		{name:'apperOrgNm',index:'apperOrgNm', width:100, sortable: false, align:"center"}
	   	],
	   	width:300,
	   	height:150,
	    viewrecords: true,
	    multiselect: false,
	    onCellSelect : function(rowid, iCol, cellcontent, e){
	    	var grid = $("#selSignln");
	   	    var dataSetRow = grid.data(rowid);
	   	    // 기안자가 등록되어 있는 행과 고정사용자로 등록되어 있는 행은 상태를 변경할수 없습니다.
	    	if(rowid == "1" || dataSetRow.get("canDelete") == "N"){
	    		grid.jqGrid('restoreRow',rowid);
	    		return;
	    	}

	    	// 상신창이 아닐 경우 이미 결재가 진행된 결재선은 수정할수 없습니다.
	    	if(!v_IsDraft && (dataSetRow.get("signSeq") != null && v_SignSeq >= dataSetRow.get("signSeq"))){
	    		grid.jqGrid('restoreRow',rowid);
	    		return;
	    	}


	    	var colName = grid.getGridParam("colModel")[iCol].name;
	    	if(colName == "upBt" || colName == "downBt" )
	    		f_RowChange(grid, rowid, iCol, cellcontent, e);

	    },
	    ondblClickRow: function(rowid, iRow, iCol, e){
	    	if(rowid == "1"){
	    		$("#selSignln").jqGrid('restoreRow',rowid);
	    	}
	    }
	});
	$("#selSignln").jqGrid('navGrid','#selSignlnPager',{edit:false,add:false,del:false});


	/**
	 * Container 크기에 맞춰 Windows Resizing 될 때 Box Grid의 사이즈를 조절한다.
	 */
	$(window).bind("resize", function(){

		var parentDiv = $("#signln").parents(".list_st2");
		$("#signln").setGridWidth(parentDiv.width());

		parentDiv = $("#selSignln").parents(".list_st2");
		$("#selSignln").setGridWidth(parentDiv.width());
	}).trigger('resize');

	$("#selSignln").attr('unselectable', 'on')
				    .css('user-select', 'none')
				    .on('selectstart', false);

	$("#signln").attr('unselectable', 'on')
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
	ds_Signln.bind($("#signln"));

	// 사원목록 데이터셋을 그리드에 바인드
	ds_SelSignln.bind($("#selSignln"), {editType:"oneClick"});

	// 사원목록 데이터셋을 그리드에 바인드
	ds_SignType.bind(signTypeSelect[0], {val: "code", text:"value"});
}

/**
* @class Form Elemenet, Data 초기화
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_InitForm()
{
	f_RetrieveSignOrgList();	// 부서목록을 조회함.

	// 공통코드가 없어서 하드코딩으로 긁어와서 셋팅
	var signType = '[{"code":"T01","value":"기안","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"D","refCd2":"","refCd3":"","refCd4":"","rowStatus":"NORMAL"},{"code":"T02","value":"결재","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"D","refCd2":"","refCd3":"","refCd4":"","rowStatus":"NORMAL"},{"code":"T03","value":"협의","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"D","refCd2":"","refCd3":"","refCd4":"","rowStatus":"NORMAL"},{"code":"T04","value":"기안자전결","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"E","refCd2":"","refCd3":"","refCd4":"","rowStatus":"NORMAL"},{"code":"T05","value":"전결","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"E","refCd2":"","refCd3":"","refCd4":"","rowStatus":"NORMAL"},{"code":"T06","value":"협의","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"E","refCd2":"","refCd3":"","refCd4":"","rowStatus":"NORMAL"}]';
	ds_SignType.setAllData(JSON.parse(signType));

//	gf_GetCommCds("CO403",  ds_SignType);// , "D"); 	// 결재유형 공통코드를 초기화함

	ds_SelSignln.setAllData(datas.signln);	// 파라메터로 요청받은 결재선 데이터를 세팅함.

	// 이단결재 여부를 체크하기 위해서 기안자를 필터하고 기안자가 2명일경우 이단결재 여부를 true로 세팅함
	ds_SelSignln.filter(function(row){
		if(row.get("signTpCd") == "T01")
			return true;
		return false;
	});
	if(ds_SelSignln.size() > 1)
		v_IsMDraft = true;
	ds_SelSignln.filter(null);

	f_DisablePSign();

}

function f_DisablePSign(){
	if(v_IsDraft == false){
		// 현재 지정된 결재선을 개인결재목록에 추가함.
		$("#addPsignln").css("display", "none");

		// 선택된 개인결재선을 저장함.
		$("#savePsignln").css("display", "none");

		$("#delPsignln").css("display", "none");
	}
}

/**
* @class Element, Compoment Event 처리
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetEventListener(){
	$("#selSignln").focusout(function(e){
		//alert(1);
	});
	/**
	 * 부서, 사용자 조회
	 */
	$("#btnSearch").click(function(){
		f_Search();
	});
	$("#treeReset").click(function(){
		f_RetrieveSignOrgList();	// 부서목록을 조회함.
	});


	// 현재 지정된 결재선을 결재문서에 사용함.ㅣ
	$("#btnConf").click(function(){
		ds_SelSignln._binder.saveRows();
		f_SignlnConf();
	});

	// 결재선 선택을 취소함
	$("#btnClose").click(function(){
		opener.gf_DisableCurrentOverlay();
		self.close();
	});

	// 직원목록에 선택된 사용자를 결재선에 추가함.
	$("#addUser1").click(function(){
		f_AddUser("T02");
	});

	$("#addUser2").click(function(){
		f_AddUser("T03");
	});

	// 결재선에 추가된 직원을 삭제함
	$("#delUser").click(function(){
		f_DelUser();
	});

	/**
	 * Enter Key Event 처리
	 * - 검색영억에 Focus가 가 있을 때만 Enter Key Event를 처리한다.
	 */
	$("#table_sign_search").bind('keypress', function(e) {
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

	if(gf_IsNull(srchTxt)){
		gf_AlertMsg("co.inf.insertUserDept");
		return;
	}

	f_RetrieveOrg(srchTxt.toUpperCase());
	f_RetrieveUser(srchTxt.toUpperCase());
}

/**
* @class 검색어에 따른 조직도 tree 검색
* @param srchTxt<String> 검색어 (조직명)
* @return N/A
* @author 변형구
* @since 2013-04-12
* @version 1.0
*/
function f_RetrieveOrg(srchTxt) {
	if ( gf_IsNull(srchTxt) ) {
		// filter 해제
		ds_SignOrg.filter(null);
	}
	else {
		ds_SignOrg.filter(
			function (DataSetRow) {
				if ( DataSetRow.get("orgNm").indexOf(srchTxt) >= 0 ) {
					return true;
				}
				return false;
			}
		);
	}

	zNodes = [];
	$.each(ds_SignOrg.view(), function(i, row){
		zNodes.push(row.get());
	});

	var t = $("#orgTree");
	t = $.fn.zTree.init(t, setting, zNodes);

	var treeObj = $.fn.zTree.getZTreeObj("orgTree");
	treeObj.expandAll(true);

	// filter 해제
	ds_SignOrg.filter(null);
}

/**
* @class 검색어에 따른 사용자(사원) 검색
* @param srchTxt<String> 검색어 (사원명)
* @return N/A
* @author 변형구
* @since 2013-04-12
* @version 1.0
*/
function f_RetrieveUser(srchTxt) {

	f_RetrieveSignUserList("", srchTxt);
}

/**
* @class 부서목록 DataSet에 값 지정
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function f_RetrieveSignOrgList(){
  	gf_Transaction("SELECT_INIT",
			"/co/common/user/retrieveSignOrgMapList.xpl",
			{},
			{},
			"f_Callback", true);
}

/**
* @class 선택된 부서에 속한 직원목록을 DataSet에 값 지정
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function f_RetrieveSignUserList(orgCd, userNm){
	if ( gf_IsNull(orgCd) ) {
		orgCd = "";
	}
	if ( gf_IsNull(userNm) ) {
		userNm = "";
	}
  	gf_Transaction("SELECT_USER",
			"/co/common/user/retrieveSignUserList.xpl",
			{"orgCd" : orgCd, "userKnm" : userNm },
			{},
			"f_Callback", false);
}

/**
* @function 로그인한 사용자부서의 tree node 개체를 찾기위한 filter 함수
* @author 권준호
* @since 2013-04-04
* @version 1.0
*  - 20140730 겸직 업데이트로 treeFilter2 로 대체.
*/
function treeFilter(node) {
	return ( node.orgCd == gv_SgnsComm.orgCd ) ;
}
/**
* @function 사용자가 선택한 현재 조직의 tree node 개체를 찾기위한 filter 함수
* @since 2014-07-30
* @version 1.0
*/
function treeFilter2(node) {
	return ( node.orgCd == ds_SelSignln._rows[0]._data.apperOrgCd ) ;
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
	  	case "SELECT_INIT" :	// 부서목록을 DataSet에 입력한후 트리를 작성함
	  		var start = $.now();
	  		ds_SignOrg.setAllData(resultData.ds_SignOrg);
	  		gf_Trace("add time : " + ($.now()-start));


	  		zNodes = [];
	  		$.each(ds_SignOrg.view(), function(i, row){
	  			zNodes.push(row.get());
	  		});

	  		var t = $("#orgTree");

	  		start = $.now();

	  		t = $.fn.zTree.init(t, setting, zNodes);
	  		gf_Trace("tree init time : " + ($.now()-start));

	  		// 현재 로그인 사용자의 부서를 찾아 펼쳐주고 부서의 사원목록을 조회한다.
	  		var treeObj = $.fn.zTree.getZTreeObj("orgTree");
//	  		var node = treeObj.getNodesByFilter(treeFilter, true); // search only one node	 ### 20140730 org
	  		var node = treeObj.getNodesByFilter(treeFilter2, true); // search only one node   ### 20140730 new	겸직 업뎃과 함께 현재 선택된 조직을 펼쳐지도록 함
	  		//treeObj.expandNode(node, true, true, true);
	  		treeObj.selectNode(node);
	  		// 사원목록 조회
	  		f_RetrieveSignUserList(node.orgCd);

			break;
	  	case "SELECT_USER" :	// 지정된 부서에 해당되는 직원목록을 출력함
	  		var start = $.now();
	  		ds_Signln.setAllData(resultData.ds_SignUser);
	  		gf_Trace("user Grid init time : " + ($.now()-start));

			break;
	  	default :
	  		break;
	  }
}

/**
* @class 선택된 결재선을 결재문서에 세팅함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function f_SignlnConf(){

	//결재선 지정 여부 체크
	if(ds_SelSignln.size() == 1){
		gf_AlertMsg('co.err.cantSignLine');
		return;
	}

	//결재유형 지정 여부 체크
	var rowCnt = ds_SelSignln.size();
	var apSeq = 1;//일반결재 순번 : 결재 <- 향후 다른 결재 유형이 추가될 수 있음
	var asSeq = 1;//협조결재 순번 : 협조

	var v_signCnt = 0;
	var v_assistCnt = 0;
	var v_clsChangeCnt = 0;

	var isAsType = false;

	//이단결재 양식 일 경우 현장에서 본사 협의자 지정 가능 : jsko : 0712
	for ( var i = 0; i< rowCnt; i++ ) {

		if(ds_SelSignln.get(i, "signTpCd" ) == ""){
			gf_AlertMsg('co.err.emptySignType');
			return;
		}

//		//마지막 결재자가 협의자 일수 없음. 마지막 결재자의 결재형식을 체크함.
//		// -- 일반결재일 경우 마지막 결재자가 협의자일수 있음.
//		if((rowCnt == i + 1)){
//			if(ds_SelSignln.get(i, "signTpCd" ) == 'T03'){
//				gf_AlertMsg('co.err.wrongLastSignln');
//				return;
//			}
//		}

		//결재자 부가 정보 처리 :  signSeq
		if(ds_SelSignln.get(i, "signTpCd" ) != 'T03'){
			//협조 다음 결재자일 경우 순번을 1 증가시킨다.
			if(isAsType){
				apSeq = apSeq + 1;
				asSeq = asSeq + 1;
			}

			ds_SelSignln.set(i,'signSeq', apSeq);
			apSeq = apSeq + 1;
			asSeq = asSeq + 1;

			isAsType = false;
			v_signCnt++;
		}else{
			ds_SelSignln.set(i,'signSeq', asSeq);
			isAsType = true;
			v_assistCnt++;
		}
		ds_SelSignln.set(i, 'signId', signId);
	}

	// 결재자 갯수 제한
	if(gf_IsSignlnCount(ds_SelSignln) == false){
		alert("co.err.maxSignLine");
		return;
	}

	// 일반결재시 협의자 지정 규칙체크
//	if(!v_IsMDraft){
//		for(var i = 0; i < ds_SelSignln.size() ; i++){
//			if(ds_SelSignln.get(i, "signTpCd") == "T03" && ds_SelSignln.get(i, "signSeq") != "3"){
//				gf_AlertMsg("협의자는 1차결재자 다음에만 지정가능 합니다.");
//				return;
//			}
//	 	}
//	}

	opener.setSignln(ds_SelSignln);

	opener.gf_DisableCurrentOverlay();

	self.close();
}

/**
* @class 선택된 직원을 결재선목록에 추가함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function f_AddUser(signTpCd){
	var selarr = $("#signln").getGridParam("selarrrow");
	if(selarr.length <= 0)
		return;

	var pos = ds_SelSignln.getPosition() < 0 ? ds_SelSignln.size()-1: ds_SelSignln.getPosition();
	//pos = pos < 1 ?  0 : pos;
	var posSignSeq = ds_SelSignln.get(pos, "signSeq");
	var posSignTpCd = ds_SelSignln.get(pos, "signTpCd");

	if(ds_SelSignln.size() > (pos+1) && ds_SelSignln.get(pos, "canDelete") == "N" && ds_SelSignln.get(pos+1, "canDelete") == "N"){
		gf_AlertMsg("고정결재(협의)자 사이에는 결재선을 삽입할수 없습니다.");
		return;
	}

	// 상신이후 결재선을 변경할경우 결재가 이미 진행된 결재선은 변경할 수 없습니다.
	if(!v_IsDraft){
		if(pos < ds_SelSignln.size()){
			if(v_SignSeq > posSignSeq){
				gf_AlertMsg("co.err.cantUpdateSignLine");
				return;
			}
		}

		 if(posSignTpCd == "T03" && signTpCd == "T03" && posSignSeq == v_SignSeq){
				gf_AlertMsg("co.err.cantUpdateSignLine");
				return;
		}
	}

	for(var i = 0 ; i < selarr.length ; i++){
		var selrow = selarr[i];
		var dataRow = $("#signln").data(selrow);
//		if(ds_SelSignln.find("signUserId", dataRow.get("signUserId")) > -1){
//			continue;
//		}
		var cloneRow = dataRow.clone();

		if(signTpCd == "T02"){	// 결재가 추가 될결우에는 계속 SignSeq를 증가 시킴.
			posSignSeq = Number(posSignSeq) + 1;
			cloneRow.signSeq = posSignSeq;
		}else if(posSignTpCd != "T03" && signTpCd == "T03"){	// 이전 결재가 협의가 아닐경우에는 SignSeq를 한번만 증가시킴
			cloneRow.signSeq = Number(posSignSeq) + 1;
		}else if(posSignTpCd == "T03" && signTpCd == "T03"){	// 이전 결재가 협의이고 현재 결재도 협의일 경우 SignSeq를 증가시키지 않음
			cloneRow.signSeq = Number(posSignSeq);
		}

		cloneRow.signTpCd = signTpCd;
		cloneRow.check = "N"; // 기본 체크박스 false
		ds_SelSignln.insert((pos++) + 1 , cloneRow); //
	}
}

/**
* @class 선택된 결재선목록의 직원을 결재선목록에서 제거함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function f_DelUser(){
	var pos = ds_SelSignln.getPosition();
	if(pos < 0){
		gf_AlertMsg("선택된 Row가 존재하지 않습니다.");
		return;
	}

	var canDelete = ds_SelSignln.get(pos, "canDelete");
	var signTpCd = ds_SelSignln.get(pos, "signTpCd");

	if(canDelete == "N"){
		gf_AlertMsg("co.err.cantChangeFixUser");
		return;
	}
	if(signTpCd == "T01"){
		gf_AlertMsg("기안자는 변경할수 없습니다.");
		return;
	}

	ds_SelSignln.remove(pos);
}

function f_SelUserRowChecked(grid, rowid, iCol, cellcontent, e){
	var dataRow = grid.data(rowid);
	var curPos = ds_SelSignln.view().indexOf(dataRow);
	if(curPos <= 0)
		return;

	var val = ds_SelSignln.get(curPos, "check");
	if(val  == "Y")
		ds_SelSignln.set(curPos, "check", "N");
	else
		ds_SelSignln.set(curPos, "check", "Y");

}

/**
* @class 선택된 직원을 결재선목록에서 순서를 변경함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function f_RowChange(grid, rowid, iCol, cellcontent, e){
	if(iCol < 0 )
		return;

	var dataRow = grid.data(rowid);
	var curPos = ds_SelSignln.view().indexOf(dataRow);
	var colNm = grid.getGridParam("colModel")[iCol].name;
	var targetPos = 0;

	if(colNm != "upBt" && colNm != "downBt")
		return;

	if(colNm == "upBt"){
		targetPos = curPos - 1;
	}else if(colNm == "downBt"){
		targetPos = curPos + 1;
	}

	if(curPos == 0 || targetPos == 0){
		gf_AlertMsg("co.warn.changedrafter");
		return;
	}else if(ds_SelSignln.get(curPos, "canDelete") == "N"){
		gf_AlertMsg("co.err.cantChangeFixUser");
		return;

	}else if(ds_SelSignln.get(targetPos, "canDelete") == "N"){
		if(curPos < targetPos){
			while(targetPos < ds_SelSignln.size()){
				if(ds_SelSignln.get(targetPos, "canDelete") != "N")
					break;

				ds_SelSignln.move(curPos, targetPos);
				curPos = targetPos;
				targetPos++;
			}
		}else{
			while(targetPos >= 1){
				if(ds_SelSignln.get(targetPos, "canDelete") != "N")
					break;

				ds_SelSignln.move(curPos, targetPos);
				curPos = targetPos;
				targetPos--;
			}
		}
		return;
	}else if(!v_IsDraft &&  (ds_SelSignln.get(targetPos,"signSeq") != null && v_SignSeq >= ds_SelSignln.get(curPos,"signSeq"))){
		gf_AlertMsg("co.err.cantEditSignln");
		return;

	}else if(!v_IsDraft &&  (ds_SelSignln.get(targetPos,"signSeq") != null && v_SignSeq >= ds_SelSignln.get(targetPos,"signSeq"))){
		gf_AlertMsg("co.err.cantEditSignln");
		return;

	}else if(targetPos >= ds_SelSignln.size()){

		return;
	}

	if(colNm == "upBt" || colNm == "downBt"){
		ds_SelSignln.move(curPos, targetPos);
	}

}
