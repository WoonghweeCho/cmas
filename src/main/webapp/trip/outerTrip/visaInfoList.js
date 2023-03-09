/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/

var ds_VisaInfoList = new DataSet();	// 문서함 공지사항 목록 20140724

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
	ds_VisaInfoList.setAllData(natData);

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
	//문서함 목록 JQGrid
	var visaInfoList = {
		datatype: "local",
	   	colNames:[gf_FindLang('국가명'),gf_FindLang('국내비자진행여부'),gf_FindLang('발급소요기간'),gf_FindLang('예외적여권사용허가진행여부'),gf_FindLang('Security 협의여부'),
	   	          ],
	   	colModel:[
	  	   		{name:'natNm',index:'natNm', width:80,align: "center"},
	  	   		{name:'visaYn',index:'visaYn', width:60,align: "center"},
	  	   		{name:'issueNeedPeriod',index:'visaYn', width:120,align: "center"},
	  	   		{name:'pportUseApprNcsYn',index:'pportUseApprNcsYn', width:130,align: "center"},
	  	   		{name:'riskYn',index:'riskYn', width:80,align: "center"}
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
//				<span id="visaFee"></span>
	    	$("#visaFee").val(rowData.get("visaFee"));
	    	$("#visaFee").height(1);
	    	$("#visaFee").height($("#visaFee").prop('scrollHeight')+12);
//				<textarea id="visaInfo" style="width:95%"></textarea>
	    	$("#visaInfo").val(rowData.get("visaInfo"));
//				<textarea id="ncsDoc" style="width:95%"></textarea>
	    	$("#ncsDoc").val(rowData.get("ncsDoc"));
	    	$("#ncsDoc").height(1);
	    	$("#ncsDoc").height($("#ncsDoc").prop('scrollHeight')+12);

//				<textarea id="pecul" style="width:95%"></textarea>
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


	$("#visaInfoClose").click(f_Close);


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
	ds_VisaInfoList.bind($("#visaInfoList"));

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
	  	case "SELECT_RC_SIGN_FORM_LIST" :
	  		ds_RcSignFormList.setAllData(resultData.ds_RcSignForm);

	  		for(var o = 0; ds_RcSignFormList.size() > o ;o++){

				if(ds_RcSignFormList.get(o,"msignYn") == "Y"){
					ds_RcSignFormList.set(o,"TempMsignYn","2단");

				}else{
					ds_RcSignFormList.set(o,"TempMsignYn","1단");
				}

				for(var i = 0; i < dsDutySubSys.size(); i++){
					if(dsDutySubSys.get(i, "code") == ds_RcSignFormList.get(o, "sysSubCd")){
						ds_RcSignFormList.set(o, "sysSubCd", dsDutySubSys.get(i, "value"));
					}
				}
			}

	  		/**
	  		 * 목록 조회 후 부가 정보 표출
	  		 */
	  		$("#rcSignbxNmNavi").text(v_rcsignbxNm); 	//문서함 네비게이션
	  		var rcCount = "["+ds_RcSignFormList.size()+"]";
	  		$("#rcListCnt").text(rcCount); 	//문서함 목록
			break;

	  	case "SELECT_SIGN_FORM_LIST" :
	  		for(var i = 0 ; i < resultData.ds_SignForm.length; i++){
	  			if($.trim(resultData.ds_SignForm[i].fstRegUserNm1 ) == "[ ]")
	  				resultData.ds_SignForm[i].fstRegUserNm1 = "";
	  		}

	  		ds_SignFormList.setAllData(resultData.ds_SignForm);

	  		for(var i = 0; ds_SignFormList.size() > i ;i++){

				if(ds_SignFormList.get(i,"msignYn") == "Y"){
					ds_SignFormList.set(i,"TempMsignYn","2단");

				}else{
					ds_SignFormList.set(i,"TempMsignYn","1단");
				}
				for(var p = 0; p < dsDutySubSys.size(); p++){
					if(dsDutySubSys.get(p, "code") == ds_SignFormList.get(i, "sysSubCd")){
						ds_SignFormList.set(i, "sysSubCd", dsDutySubSys.get(p, "value"));
					}
				}
			}
	  		/**
	  		 * 목록 조회 후 부가 정보 표출
	  		 */
	  		gf_SetBoxInfo(v_signbxNm, ds_SignFormList.size());
			break;
			/**
			 * 문서함 공지 보여주기 20140724
			 */
	  	case "SELECT_BOX_NOTICE" :
	  		ds_BoxNoticeList.setAllData(resultData.ds_BoxNoticelnDtl);
	  		for(var i=0; i<resultData.ds_BoxNoticelnDtl.length; i++){
	  			// 해당 문서함 찾기
	  			if(resultData.ds_BoxNoticelnDtl[i].signbxCd == v_signbxCd){
	  				v_BoxNoticeIdx = i;
	  				// 문서함 공지 사용 안하는 경우
	  				if(resultData.ds_BoxNoticelnDtl[i].useYn == "N"){
	  					$("#noticeDiv").css("display","none");			// 숨김
	  				}else{	// 문서함 공지 사용하는 경우
	  					// 한글인 경우
	  					if(v_loclCd=="ko_KR"){
		  					$("#noticeTitleSpan").html(resultData.ds_BoxNoticelnDtl[i].titleKr);	// ### 20140725
	  					}else{	// 영문인 경우
		  					$("#noticeTitleSpan").html(resultData.ds_BoxNoticelnDtl[i].titleEn);	// ### 20140725
	  					}
	  					$("#noticeDiv").css("display","table-cell");	// 보임
	  				}
	  				break;
	  			}
	  		}
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