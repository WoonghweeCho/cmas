var ds_IdAppnSysList = new DataSet();
var ds_Sys = new DataSet();
var v_Sys = {};

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
	v_loclCd = gf_GetCookie("loclCd");		// 로케일 20140724
}

/**
* @class Form Onload 시 컴포넌트 초기화
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetComponents()
{
	//구분 검색 Select Box 처리
	gf_GetCommCds("ID003", ds_Sys, function(CommGrpCd, data){
		for(var i=0; i<ds_Sys.size(); i++){
			var key = ds_Sys.get(i, "code");
			var val = ds_Sys.get(i, "value");
			v_Sys[key] = val;
		}
	});

	//특별ID 시스템 목록 JQGrid
	var idAppnSysList = {
		datatype: "local",
	   	colNames:[gf_FindLang('시스템'), gf_FindLang('모듈명'), gf_FindLang('담당자사번'), gf_FindLang('담당자명')],
	   	colModel:[
	   	          {name:'sysNm',index:'sysNm', width:30, align:"center",
	   	        	formatter:function(val, gOpt, row){
	   	        		ds_Sys.filter(null);
		        		var result = gf_BindView(val, row, gOpt, {dataSet:ds_Sys, curVal:"sysCd", val: "code", text:"value"});
		        		return result;
		        	}
	   	          },
	   	          {name:'moduleNm',index:'moduleNm', width:120,align: "center"},
	   	          {name:'perchrgId',index:'perchrgId', width:60,align: "center"},
	   	          {name:'perchrgNm',index:'perchrgNm', width:80,align: "center"},
		],
	   	autowidth:true,
	   	height:470,
	   	sortname: 'fnlEditDt',
	    viewrecords: true,
	    sortorder: 'desc',
	    rowNum:10000,
	    onSelectRow: function(rowid, status, e) {
	    	f_SetFormReadonly(false);
	    	if(ds_IdAppnSysList.getStatus(ds_IdAppnSysList.getPosition()) != "INSERT") {
	    		$('#sysCd').attr('disabled', 'true');
	    		$('#moduleNm').attr('disabled', 'true');
	    	}
		}
	};

	$("#idAppnSysList").jqGrid(idAppnSysList);

	/**
	 * Container 크기에 맞춰 Windows Resizing 될 때 Box Grid의 사이즈를 조절한다.
	 */
	$(window).bind("resize", function(){
	}).trigger('resize');
}

/**
* @class Form Onload DataSet Binding 처리
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetBinding()
{
	//양식목록 Grid Binding
	ds_IdAppnSysList.bind($("#idAppnSysList"));
	ds_IdAppnSysList.bind($("#idAppnSysForm")[0]);

	//문서상태 검색 DataSet Binding
	ds_Sys.bind($("select[name='sysCd']")[0], {val: "code", text: "value"});
}

/**
* @class Form Onload 시 Element/Component 인벤트 초기화
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetEventListener()
{
	// 시스템 목록을 조회한다.
	$("#search").click(function(){
		ds_IdAppnSysList.clear();
		cf_RetrieveIdAppnSysDtlList();
	});

	$("#sysCdBtn").click(function(){
		var callbackFunc = "f_SelectUsr";
		window.open(gv_ContextPath + "/common/jsp/comp/userSelect.jsp?userNm=" + "&callbackFunc="+callbackFunc,"","toolbar=no,scrollbars=no,width=600,height=420");
	});

	// 행추가 버튼
	$("#addBtn").click(function(){
		ds_IdAppnSysList.add(
				{
					sysCd:"",
					moduleNm:"",
					perchrgId:"",
				});
		$("#sysCd").val("01");		// 시스템 기본값 01 = 바로넷
		$("#idAppnSysList").trigger("reloadGrid");
	});

	// 행삭제 버튼
	$("#delBtn").click(function(){
		if(ds_IdAppnSysList.getPosition() == -1){
			alert("선택된 행이 없습니다.");
			return;
		}
		ds_IdAppnSysList.remove(ds_IdAppnSysList.getPosition());
		f_SetFormReadonly(true);
	});

	// 저장 버튼
	$("#btnSave").click(function(){
		if(f_CheckValidationForSave() == false){
			return;
		}
		var dataSets = {
  			idAppnSysDtlList : ds_IdAppnSysList.getAllData("U")
  		};
		gf_Transaction("SAVE_ID_APPN_SYS_DTL", "/id/idAppn/saveIdAppnSysDtl.xpl", {}, dataSets, "f_Callback", true);
	});

	//시스템 선택 시
	$("#sysCd").mouseup(function(e){
		e.preventDefault();
	});
}

/**
* @class Form Onload 시 객체 초기 값 설정
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_InitForm(){
	f_SetFormReadonly(true);
}

function cf_RetrieveIdAppnSysDtlList(){
	gf_Transaction("SELECT_ID_APPN_SYS_LIST", "/id/idAppn/retrieveIdAppnSysDtlList.xpl", {}, {}, "f_Callback", true);
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
	  	case "SELECT_ID_APPN_SYS_LIST" :
	  		ds_IdAppnSysList.setAllData(resultData.ds_IdAppnSysDtlList);
	  		f_SetFormReadonly(true);
			break;
	  	default :
	  		break;
	  }
}

function f_SetFormReadonly(readonly){
	switch(readonly) {
		case true :
			$('#idAppnSysForm input').attr('disabled', 'true');
			$('#idAppnSysForm select').attr('disabled', 'true');
			$('#idAppnSysForm input #perchrgId').attr('disabled', 'true');
			break;
		case false :
			$('#idAppnSysForm input').removeAttr('disabled');
			$('#idAppnSysForm select').removeAttr('disabled');
			$('#perchrgId').attr('disabled', 'true');
			break;
		default :
			break;
	}
}

function f_SelectUsr(obj){
	ds_IdAppnSysList.set(ds_IdAppnSysList.getPosition(), "perchrgId", obj.userId);
	ds_IdAppnSysList.set(ds_IdAppnSysList.getPosition(), "perchrgNm", obj.userKnm);
}

function f_CheckValidationForSave(){
	// 수정된 데이터가 없다면
	if ( !ds_IdAppnSysList.isUpdate() && ds_IdAppnSysList._deleteRow.length == 0){
		gf_AlertMsg('co.err.noChange1');  // Msg : 변경된 사항이 없습니다.
		return false;
	}

	for ( var i=0; i<ds_IdAppnSysList.size(); i++ ) {
		if(ds_IdAppnSysList.get(i, "sysCd" ) == ""){
			gf_AlertMsg("시스템코드는 필수입니다.");
			ds_IdAppnSysList.setPosition(i, true);
			return false;
		} else if(ds_IdAppnSysList.get(i, "moduleNm" ) == "") {
			gf_AlertMsg("모듈명은 필수입니다.");
			ds_IdAppnSysList.setPosition(i, true);
			return false;
		} else if(ds_IdAppnSysList.get(i, "perchrgId" ) == "") {
			gf_AlertMsg("담당자는 필수입니다.");
			ds_IdAppnSysList.setPosition(i, true);
			return false;
		} else {
		}
	}
}