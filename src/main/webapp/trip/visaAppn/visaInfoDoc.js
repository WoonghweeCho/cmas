/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/

var ds_VisaInfoList = new DataSet();

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

}

/**
* @class Form Onload 시 컴포넌트 초기화
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetComponents()
{
	//문서함 목록 JQGrid
	var visaInfoList = {
		datatype: "local",
	   	colNames:[gf_FindLang('국가명'),gf_FindLang('국내비자진행여부'),gf_FindLang('발급소요기간'),gf_FindLang('예외적여권사용허가진행여부'),gf_FindLang('위험여부'),
	   	          ],
	   	colModel:[
	  	   		{name:'natNm',index:'natNm', width:80,align: "center"},
	  	   		{name:'visaYn',index:'visaYn', width:60,align: "center"},
	  	   		{name:'issueNeedPeriod',index:'visaYn', width:120,align: "center"},
	  	   		{name:'pportUseApprNcsYn',index:'pportUseApprNcsYn', width:130,align: "center"},
	  	   		{name:'riskYn',index:'riskYn', width:60,align: "center",hidden:true}
	  	   		],
	   	autowidth:true,
	   	height:100,
	   	sortname: 'natNm',
	    viewrecords: true,
	    sortorder: "desc",
		onSelectRow: function(rowId, status, e){

	    	var rowData = $("#visaInfoList").data(rowId);

	    	var natCd = rowData.get("natCd");
	    	var natNm = rowData.get("natNm");

	    	$("#natNm").text(natNm);
	    	$("#natCd").text(natCd);
	    	$("#visaYn").val(rowData.get("visaYn"));
	    	$("#riskYn").val(rowData.get("riskYn"));

	    	$("#stayAblePeriod").text(rowData.get("stayAblePeriod"));
	    	$("#stayAblePeriod").height(1);
	    	$("#stayAblePeriod").height($("#stayAblePeriod").prop('scrollHeight')+12);

	    	$("#validPeriod").text(rowData.get("validPeriod"));
	    	$("#validPeriod").height(1);
	    	$("#validPeriod").height($("#validPeriod").prop('scrollHeight')+12);

	    	$("#visaPrgrStage").text(rowData.get("visaPrgrStage"));
	    	$("#visaPrgrStage").height(1);
	    	$("#visaPrgrStage").height($("#visaPrgrStage").prop('scrollHeight')+12);

	    	$("#issueNeedPeriod").text(rowData.get("issueNeedPeriod"));
	    	$("#issueNeedPeriod").height(1);
	    	$("#issueNeedPeriod").height($("#issueNeedPeriod").prop('scrollHeight')+12);
	    	$("#visaFee").val(rowData.get("visaFee"));
	    	$("#visaFee").height(1);
	    	$("#visaFee").height($("#visaFee").prop('scrollHeight')+12);

	    	$("#visaInfo").val(rowData.get("visaInfo"));

	    	$("#ncsDoc").val(rowData.get("ncsDoc"));
	    	$("#ncsDoc").height(1);
	    	$("#ncsDoc").height($("#ncsDoc").prop('scrollHeight')+12);
	    	$("#pecul").val(rowData.get("pecul"));
	    	$("#pecul").height(1);
	    	$("#pecul").height($("#pecul").prop('scrollHeight')+12);

	    }
	};

	$("#visaInfoList").jqGrid(visaInfoList);
	$("#visaInfoList").jqGrid('navGrid','#visaInfoListPager',{edit:false,add:false,del:false});

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

	$("#natNmInput").bind('keypress', function(e) {
		if (e.which == 13) {
			ds_VisaInfoList.filter(
					function (DataSetRow) {
						if ( DataSetRow.get("natNm").match(e.target.value) != null ) {
							return true;
						}
						return false;
					}
				);
		}
	});
}

/**
* @class Form Onload 시 객체 초기 값 설정
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_InitForm()
{
	//여기 어딘가에서 ds_NatList를 받아와야 함.
	gf_Transaction("SELECT_NAT_LIST", "/trip/outerTrip/retrieveNatList.xpl", {}, {}, "f_Callback", false);

	$("select[id='riskYn']").attr('disabled',true);
	$("select[id='visaYn']").attr('disabled',true);
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

	  	case "SELECT_NAT_LIST" :
	  		ds_VisaInfoList.setAllData(resultData.ds_NatList);
	  		ds_VisaInfoList.bind($("#visaInfoList"));
	  		break;

	  	default :
	  		break;
	  }
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

function grd_List_oncelldbclick(grid, rowid, iRow, iCol)
{
	var rowData = $("#visaInfoList").data(rowid);

	var natCd = rowData.get("natCd");
	var natNm = rowData.get("natNm");
}