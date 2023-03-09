/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/

var v_TrData = "<tr><td class=\"inpt\" style=\"text-align:center\"><input type=\"text\" name=\"tName\" style=\"width:95%\"></td>" +
			 "<td class=\"inpt\" style=\"text-align:center\"><input type=\"text\" name=\"tUseYmd\" style=\"width:95%\"></td>" +
			 "<td class=\"inpt\" style=\"text-align:center\"><input type=\"text\" name=\"tType\" style=\"width:95%\"><input type=\"hidden\" name=\"tTypeVal\"></td>" +
			 "<td class=\"inpt\" style=\"text-align:center\"><input type=\"text\" name=\"tPurp\" style=\"width:95%\"></td>" +
			 "<td class=\"inpt\" style=\"text-align:center\"><input type=\"text\" name=\"tSiteExpKind\" style=\"width:95%\"><input type=\"hidden\" name=\"tSiteExpKindVal\"></td>" +
			 "<td class=\"inpt\" style=\"text-align:center\"><input type=\"text\" name=\"tMvDist\" style=\"width:70%\">km</td>" +
"<td class=\"inpt\" style=\"text-align:center\"><input type=\"text\" name=\"tAmount\" style=\"width:80%\">원</td></tr>";


var v_BdgtType = "";

var v_Aufnr; // 예산
var v_Kostv; // 집행팀
var v_Kostvnm; // 집행팀 이름

var v_isSaveEnable = "N";

var v_DocNo; // 문서번호

//DataSet
var ds_DocData = new DataSet();
var ds_DetailData = new DataSet();
var ds_SapData = new DataSet();

var ds_Signln = new DataSet();			//결재선정보

var v_DocSts = "";

var v_FileAtchId;		//첨부파일ID

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

	v_DocNo = gf_IsNull(datas.fromList) ? "" : datas.fromList;
	v_DocSts = gf_IsNull(datas.docSts) ? "" : datas.docSts;

}

/**
* @class Form Onload 시 컴포넌트 초기화
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetComponents()
{

	//Attachment 컴포넌트 생성
	gf_InitFileUploadComponent();

	// upload 모드로 컴포넌트의 mode  설정
    gf_setMode("download");

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

	$("#closeBtn").click(function(){
		self.close();
	});

	$("#docDelBtn").click(function(){

		if(confirm("신청서를 삭제하시겠습니까?") == true){
			cf_cmasDocDelete();
		};


	});

	$("#confirmAcct").click(function(){

		if(confirm("회계승인 없이 문서를 '결재완료+회계승인+결재선완료'상태로 변경하시겠습니까?")){
			// 수동으로 승인으로 상태값 변경
			gf_InitSpinner(top.$('body'));

			if(v_DocNo == "" || v_DocNo == undefined){
				alert("문서번호가 존재하지 않습니다.");
				return;
			}else{
				var params = {
						acctSts : '완료',
						docNo : v_DocNo,
						docSts : 'D03',
						retResn : '',
						signId : ds_DocData.get(0).signId
				};

				gf_Transaction("SAVE_CONFIRM_ACCT", "/trip/cityTransp/updateSgnsReject2.xpl", params, {}, "f_Callback", true);
			}

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

	//상신 실패로 넘어오지 않는 케이스도 있음.
	//잘못 올렸다가 지워야 할 수도 있으므로 특정 유저에게는
	if(v_DocSts == "D99"){
		$("#docDelBtn").show();
	}

	//D99도 안찍히고 결재만 안 넘어가는 케이스도 있음.

	if(gv_AuthList.auth[0].code.match("RO_CMAS_CO_TRIP_ADM") != null){

		//통합결재로 제대로 넘어가지 않았을 경우에는 신청서 삭제..가 필요할 수도 있다. (회계반려 + 신청서 삭제 )
		$("#docDelBtn").show();

		// 결재완료 문서의 경우 회계승인 버튼 생성
		if(v_DocSts == "D02"){
			$("#confirmAcct").show();
		}

	}else{
		// 관리자가 아닌 경우
	}

	// 로딩시 Modal 처리
	gf_InitSpinner(top.$('body'));

	var docNoText = v_DocNo.split("-");
	$("#docNo").text(docNoText[1] + "-" + docNoText[2]);

	//해당 업무구분과 업무분류에 해당되는 양식명을 가져옴
	var docParams = {
			cmasId : v_DocNo
	};

	var acctSts = gf_IsNull(datas.acctSts) ? "" : datas.acctSts;
	var retResn = gf_IsNull(datas.retResn) ? "" : datas.retResn;
	var refNo = gf_IsNull(datas.refNo) ? "" : datas.refNo;
	// P 회계승인 ; R 반려
	if(acctSts == "R" || acctSts == "반려"){
		var overlay = $("<div>") ;
		overlay.addClass("ui-widget-overlay ui-front").appendTo(  $('body') );
		alert("회계반려 된 문서입니다.\nRef No : " + refNo + "\n\n사유 : " + retResn);
		//self.close();
	}else if(v_DocSts == "D04"){
		var overlay = $("<div>") ;
		overlay.addClass("ui-widget-overlay ui-front").appendTo(  $('body') );
		alert("Ref No : " + refNo + "\n\n사유 : " + retResn);
		self.close();
	}else{
		gf_Transaction("SELECT_VIEW_DOC_INFO", "/trip/cityTransp/retrieveViewDocInfo.xpl", docParams, {}, "f_Callback", true);
		cf_RetrieveSignInfo();
	}



}

function cf_RetrieveSignInfo(){

	//해당 업무구분과 업무분류에 해당되는 양식명을 가져옴
	var signParams = {
			docNo : v_DocNo
	};

	gf_Transaction("SELECT_SIGN_INFO", "/trip/cityTransp/retrieveSignInfo.xpl", signParams, {}, "f_Callback", true);
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
	  	case "SELECT_SIGN_INFO" :
	  		ds_Signln.clear();
	  		ds_Signln.setAllData(resultData.ds_Sign);

	  		for ( var i = 0; i < ds_Signln.size(); i++) {

	  			if(i > 0){
	  				if(ds_Signln.get(i, "apperRpswrkCd") == ""){
	  					ds_Signln.set(i, "apperRpswrkCd", ds_Signln.get(i, "apperPositCd"));
	  				}
	  				ds_Signln.set(i, "apperPositCd", ds_Signln.get(i, "apperRpswrkCd"));
	  			}
	  			// 결재 시각을 자른다. 일자만 보임
  				if(ds_Signln.get(i, "signDt") != ""){
  					var signDt = ds_Signln.get(i, "signDt");
  					signDt = signDt.substr(0, 10);
  					ds_Signln.set(i, "signDt", signDt);
  				}
			}

	  		gf_AssembleSignln(ds_Signln);

	  		break;
	  	case "SELECT_VIEW_DOC_INFO" :

	  		ds_DocData.setAllData(resultData.ds_DocData);

	  		ds_DetailData.setAllData(resultData.ds_DetailData);

	  		var totalAmt = 0;

	  		for(var i = 0; ds_DetailData.size() > i; i++){

	  			var amt = cf_AppendTranspDetail(ds_DetailData.get(i));

	  			totalAmt = totalAmt + parseInt(amt);


	  			// readonly 처리
	  			$("#transpDetail tr input[name='tStart']").attr("readonly", "true");
	  			$("#transpDetail tr input[name='tEnd']").attr("readonly", "true");
	  			$("#transpDetail tr select[name='tType']").attr("disabled", "true");
	  			$("#transpDetail tr select[name='tRound']").attr("disabled", "true");
	  			$("#transpDetail tr input[name='tDist']").attr("readonly", "true");
	  			$("#transpDetail tr input[name='tAmount']").attr("readonly", "true");

			}

	  		$("#tAmountTotal").text(gf_AmtFormat(totalAmt));

	  		// (시스템)관리자일 경우 CMAS 문서번호 팝업
	  		if(gv_AuthList.auth[0].code.match("RO_CMAS_CO_TRIP_ADM") != null){
	  			$("#docNoTr").show();
	  		}else{
	  			$("#docNoTr").hide();
	  		}

	  		// 일반 사용자에게 결재문서번호를 문서번호에 보여줌
	  		$("#signId").text(ds_DocData.get(0, "signId"));

	  		// 조회된 RefKey 로 시내교통비 정보 조회
	  		var param = {
	  				Refkey : ds_DocData.get(0).refNo
	  		}
	  		// 최종수정자
			$("#tDrafter").text(ds_DocData.get(0).fnlEditUserId + " " + ds_DocData.get(0).fnlEditUserNm);
	  		// 소속팀
			$("#tDrafterOrgNm").text(ds_DocData.get(0).bdgtItem + " (" + ds_DocData.get(0).doOrgCd + ")");

	  		//첨부파일
	  		gf_retrieveFileList(ds_DocData.get(0,"fileAtchId"));

	  		gf_Transaction("SELECT_SAP_DATA", "/trip/eai/getTrafficExpenseSearch.xpl", param, {}, "f_Callback", true);


			break;
	  	case "SELECT_SAP_DATA" :

	  		//SAP DATA 조회
	  		ds_SapData.setAllData(resultData.output1);


	  		if(ds_SapData.get(0).SapItab == undefined){
	  			alert("ESB 서버가 작동하지 않습니다.");
	  			self.close();
	  		}
	  		// 경비구분
	  		var gubun = "";
			var gubun4 = ""; // 전표구분 H본사, G지역


	  		if(ds_SapData.get(0).SapItab.constructor == Array){
	  			$("#tRefNo").text(ds_SapData.get(0).SapItab[0].Refkey);
	  			gubun = ds_SapData.get(0).SapItab[0].BdgtType;
	  			gubun4 = ds_SapData.get(0).SapItab[0].DocType;

				// 전표번호
				$("#tBelnr").text(ds_SapData.get(0).SapItab[0].Belnr);

				$("#tOrdDate").text(ds_SapData.get(0).SapItab[0].Date1);
				$("#tOrdDate2").text(ds_SapData.get(0).SapItab[0].Date1);
				// 예산번호
				$("#tAufnr").text(ds_SapData.get(0).SapItab[0].Aufnr);

				// 집행팀
				$("#tExecTeam").text(ds_SapData.get(0).SapItab[0].Kostl1);
	  		}else{
	  			$("#tRefNo").text(ds_SapData.get(0).SapItab.Refkey);
	  			gubun = ds_SapData.get(0).SapItab.BdgtType;
	  			gubun4 = ds_SapData.get(0).SapItab.DocType;

				// 전표번호
				$("#tBelnr").text(ds_SapData.get(0).SapItab.Belnr);

				$("#tOrdDate").text(ds_SapData.get(0).SapItab.Date1);
				$("#tOrdDate2").text(ds_SapData.get(0).SapItab.Date1);
				// 예산번호
				$("#tAufnr").text(ds_SapData.get(0).SapItab.Aufnr);

				// 집행팀
				$("#tExecTeam").text(ds_SapData.get(0).SapItab.Kostl1);
	  		}

			var gText = "";
			switch(gubun) {
		  		case "A" :
		  			gText = "A. 특정경비-임원";
		  			break;
		  		case "B" :
		  			gText = "B. 특정경비-팀장";
		  			break;
		  		case "C" :
		  			gText = "C. 특정경비-팀";
		  			break;
		  		case "I" :
		  			gText = "I. 일반경비";
		  			break;
		  		case "O" :
		  			gText = "O. 입찰경비";
		  			break;
		  		case "P" :
		  			gText = "P. 사업경비";
		  			break;
		  		case "R" :
		  			gText = "R. 기술연구원경비";
		  			break;
		  		case "Q" :
		  			gText = "Q. 현장경비";
		  			break;
		  		case "K" :
		  			gText = "K. 본사집행현장원가";
		  			break;
		  		case "S" :
		  			gText = "S. 사업경비-현장코드";
		  			break;
		  		default :
			  		break;
			}



			if(gubun4 == "H"){
				gText = gText + " (본사)";
			}else if(gubun4 == "G"){
				gText = gText + " (지역)";
			}
			$("#bdgtType").text(gText);

			// 로딩 완료
			$(".ajax_overlay").remove();



	  		break;
	  	case "SAVE_DELETE_CMAS_DOC" :
	  		// 문서가 성공적으로 처리되면 문서 리스트를 다시 조회하여 갱신하여 준다.
	  		if ( !gf_IsNull(v_CallbackFunc) ) {
	  			var openCallbackFunc = "opener."+v_CallbackFunc;
	  	    	eval(openCallbackFunc + "();");
	  	    }

	  		self.close();
	  		break;

	  	case "SAVE_CONFIRM_ACCT" :
	  		$(".ajax_overlay").remove();
	  		self.close();
	  		break;

	  	default :
	  		break;
	  }
}

/**
* @class 문서함 공지 목록을 조회한다. ###
* @author 최대일
* @since 2014-07-24
* @version 1.0
*/
function cf_RetrieveBoxNoticeList(){
    gf_Transaction("SELECT_BOX_NOTICE", "/sn/sign/selectBoxNotice.xpl", {}, {}, "f_Callback", true);
}



function f_callBackFuncBdgtSelect(obj){

	v_BdgtType = obj.bdgtType;

	switch(v_BdgtType) {
  		case "A" :
  			$("#bdgtType").text("A. 특정경비-임원");
  		    // 결과 셋팅
  			v_Aufnr = obj.bdgtData.Aufnr;
  			AufnrText = obj.bdgtData.Aufnr + " / 내역 : " + obj.bdgtData.Ktext;
  			$("#Aufnr").text(AufnrText);

  			v_Kostv = obj.bdgtData.Kostv;
  			v_Kostvnm = obj.bdgtData.Kostvnm;
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);

  			break;
  		case "B" :
  			$("#bdgtType").text("B. 특정경비-팀장");
  			// 결과 셋팅
  			v_Aufnr = obj.bdgtData.Aufnr;
  			AufnrText = obj.bdgtData.Aufnr + " / 내역 : " + obj.bdgtData.Ktext;
  			$("#Aufnr").text(AufnrText);

  			v_Kostv = obj.bdgtData.Kostv;
  			v_Kostvnm = obj.bdgtData.Kostvnm;
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);
  			break;
  		case "C" :
  			$("#bdgtType").text("C. 특정경비-팀");
  			// 결과 셋팅
  			v_Aufnr = obj.bdgtData.Kostv;
  			AufnrText = obj.bdgtData.Kostv + " / 내역 : " + obj.bdgtData.Kostvnm;
  			$("#Aufnr").text(AufnrText);

  			v_Kostv = obj.bdgtData.Kostv;
  			v_Kostvnm = obj.bdgtData.Kostvnm;
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);
  			break;
  		case "I" :
  			$("#bdgtType").text("I. 일반경비");
  			// 결과 셋팅
  			v_Aufnr = obj.bdgtData.Aufnr;
  			AufnrText = obj.bdgtData.Aufnr + " / 내역 : " + obj.bdgtData.Ktext;
  			$("#Aufnr").text(AufnrText);

  			v_Kostv = obj.bdgtData.Kostv;
  			v_Kostvnm = obj.bdgtData.Kostvnm;
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);
  			break;
  		case "O" :
  			$("#bdgtType").text("O. 입찰경비");
  			// 결과 셋팅
  			v_Aufnr = obj.bdgtData.Aufnr;
  			AufnrText = obj.bdgtData.Aufnr + " / 내역 : " + obj.bdgtData.Ktext;
  			$("#Aufnr").text(AufnrText);

  			v_Kostv = obj.bdgtData.Kostv;
  			v_Kostvnm = obj.bdgtData.Kostvnm;
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);
  			break;
  		case "P" :
  			$("#bdgtType").text("P. 사업경비");
  			// 결과 셋팅
  			v_Aufnr = obj.bdgtData.Orderno;
  			AufnrText = obj.bdgtData.Orderno + " / 내역 : " + obj.bdgtData.Ktext;
  			$("#Aufnr").text(AufnrText);

  			v_Kostv = obj.bdgtData.Kostv;
  			v_Kostvnm = obj.bdgtData.Kostvnm;
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);
  			break;
  		case "R" :
  			$("#bdgtType").text("R. 기술연구원경비");
  			// 결과 셋팅
  			v_Aufnr = obj.bdgtData.Aufnr;
  			AufnrText = obj.bdgtData.Aufnr + " / 내역 : " + obj.bdgtData.Ktext;
  			$("#Aufnr").text(AufnrText);

  			v_Kostv = obj.bdgtData.Kostv;
  			v_Kostvnm = obj.bdgtData.Kostvnm;
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);
  			break;
  		case "Q" :
  			$("#bdgtType").text("Q. 현장경비");
  			// 결과 셋팅
  			v_Aufnr = obj.bdgtData.Sitprt;
  			AufnrText = obj.bdgtData.Sitprt + " / 내역 : " + obj.bdgtData.Ktext;
  			$("#Aufnr").text(AufnrText);

  			v_Kostv = obj.bdgtData.Sitprt;
  			v_Kostvnm = obj.bdgtData.Ktext;
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);
  			break;
  		case "K" :
  			$("#bdgtType").text("K. 본사집행현장원가");
  			// 결과 셋팅
  			v_Aufnr = obj.bdgtData.Aufnr;
  			AufnrText = obj.bdgtData.Aufnr + " / 내역 : " + obj.bdgtData.Ktext;
  			$("#Aufnr").text(AufnrText);

  			v_Kostv = obj.bdgtData.Kostv;
  			v_Kostvnm = obj.bdgtData.Kostvnm;
  			KostvText = v_Kostvnm + " (" + v_Kostv + ")";
  			$("#bdgtTeam").text(KostvText);
  			break;

  		default :
	  		break;
	}


//	<select id="bdgtType" style="width: 250px">
//		<option value="A">특정경비-임원</option>
//		<option value="B">특정경비-팀장</option>
//		<option value="C">특정경비-팀</option>
//		<option value="I">일반경비</option>
//		<option value="O">입찰경비</option>
//		<option value="P">사업경비</option>
//		<option value="R">기술연구원경비</option>
//		<option value="Q">현장경비</option>
//		<option value="K">본사집행현장원가</option>
//	</select>

}

//ViewDoc 전용
function cf_AppendTranspDetail(obj){

	$("#transpInfo").append(v_TrData);


	// last 에 ID 부여
	var idFlag = "tData";
	var indexF = "" + $("#transpInfo tr:last").index();

	idFlag = idFlag + indexF;
	$("#transpInfo tr:last").attr("id", idFlag);

//	amt: "2823"
//	appantId: "1202429"
//	appantNm: "강혜성"
//	appantTeamCd: "1DFUR"
//	arrPlace: "도착"
//	cls: "R"
//	docNo: "CMAS-2015-000001"
//	dptPlace: "출발"
//	fnlEditDt: "2015-04-23 13:46:09"
//	fnlEditUserId: "1202429"
//	fstRegDt: "2015-04-23 13:46:09"
//	fstRegUser_id: ""
//	glPlace: "목적"
//	seq: "0"
//	trafficCls: "B"
//	useGl: "사적"
//	useYmd: "20150423"

//	var tName = obj.appantNm + " (" + obj.appantId + ")";
	var tName = obj.appantId + " " + obj.appantNm;
	var tOrg = obj.appantTeamCd + " " + obj.appantTeamNm;

	var tUseYmd = obj.useYmd;

	var tTypeTextSel1;

	if(obj.trafficCls == "B"){
		tTypeTextSel1 = "버스";
	}else if(obj.trafficCls == "S"){
		tTypeTextSel1 = "선박";
	}else if(obj.trafficCls == "M"){
		tTypeTextSel1 = "지하철";
	}else if(obj.trafficCls == "T"){
		tTypeTextSel1 = "택시";
	}else if(obj.trafficCls == "1"){
		tTypeTextSel1 = "항공";
	}else if(obj.trafficCls == "2"){
		tTypeTextSel1 = "고속철도";
	}else if(obj.trafficCls == "3"){
		tTypeTextSel1 = "개인차량";
	}else{
		tTypeTextSel1 = "";
	}

	// 왕복 편도
	var tTypeTextSel2;
	if(obj.cls == "R"){
		tTypeTextSel2 = "왕복";
	}else if(obj.cls == "D"){
		tTypeTextSel2 = "편도";
	}else{
		tTypeTextSel2 = "";
	}


	var tType = obj.dptPlace + " - " + obj.arrPlace + " - " + obj.glPlace + " / " + tTypeTextSel1 + " (" + tTypeTextSel2 + ")";
	var tPurp = obj.useGl;
	var tAmount = obj.amt;

	//현장경비종류
	var tSiteExpKind;
	if(obj.siteExpKind == "1"){
		tSiteExpKind = "시내업무교통비";
	}else if(obj.siteExpKind == "2"){
		tSiteExpKind = "귀가여비";
	}else{
		tSiteExpKind = "";
	}

	var tSiteExpKindVal = obj.siteExpKind;
	var tMvDist = obj.mvDist;

	$("#transpInfo tr:last input[name='tName']").val(tName);
//	$("#transpInfo tr:last input[name='tOrg']").val(tOrg);
	$("#transpInfo tr:last input[name='tUseYmd']").val($.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", tUseYmd)));
	$("#transpInfo tr:last input[name='tType']").val(tType);
	$("#transpInfo tr:last input[name='tPurp']").val(tPurp);
	$("#transpInfo tr:last input[name='tAmount']").val(gf_AmtFormat(tAmount));
	$("#transpInfo tr:last input[name='tSiteExpKind']").val(tSiteExpKind);
	$("#transpInfo tr:last input[name='tSiteExpKindVal']").val(tSiteExpKindVal);
	$("#transpInfo tr:last input[name='tMvDist']").val(gf_AmtFormat(tMvDist));

	// readonly 처리
	$("#transpInfo tr input[name='tName']").attr("readonly", "true");
//	$("#transpInfo tr input[name='tOrg']").attr("readonly", "true");
	$("#transpInfo tr input[name='tUseYmd']").attr("readonly", "true");
	$("#transpInfo tr input[name='tType']").attr("readonly", "true");
	$("#transpInfo tr input[name='tPurp']").attr("readonly", "true");
	$("#transpInfo tr input[name='tAmount']").attr("readonly", "true");
	$("#transpInfo tr input[name='tSiteExpKind']").attr("readonly", "true");
	$("#transpInfo tr input[name='tMvDist']").attr("readonly", "true");

	return obj.amt;


}

function cf_OpenUserSelectPop(e){

	var targetId = $(e.target).parent().parent().attr("id");

	var trParams = {
		targetId : targetId,
		tName : $("#" + targetId + " input[name='tName']").val(),
		tOrg : $("#" + targetId + " input[name='tOrg']").val(),
		tType : $("#" + targetId + " input[name='tTypeVal']").val(),
		tPurp : $("#" + targetId + " input[name='tPurp']").val(),
		tAmount : $("#" + targetId + " input[name='tAmount']").val(),
		tSiteExpKind : $("#" + targetId + " input[name='tSiteExpKind']").val(),
		tMvDist : $("#" + targetId + " input[name='tMvDist']").val()
	};

	gf_PostOpen("/trip/cityTransp/cityTranspAddP.jsp", null,
	"toolbar=no,scrollbars=no,width=620,height=490", trParams,
	true, true, "f_callBackFuncCityTranspAddP");

}

function f_callBackFuncCityTranspAddP(obj) {

//	  v_BdgtType = obj.bdgtType;

	var tName = obj.tName;
	var tOrg = obj.tOrg;
	var tType = obj.tType;
	var tPurp = obj.tPurp;
	var tAmount = obj.tAmount;

	//현장경비종류
	var tSiteExpKind;
	if(obj.siteExpKind == "1"){
		tSiteExpKind = "시내업무교통비";
	}else if(obj.siteExpKind == "2"){
		tSiteExpKind = "귀가여비";
	}else{
		tSiteExpKind = "";
	}

	var tSiteExpKindVal = obj.siteExpKind;
	var tMvDist = obj.mvDist;

	var tTypeText = tType.split(" ");
	// 수단
	var tTypeTextSel1;

	if(tTypeText[3] == "B"){
		tTypeTextSel1 = "버스";
	}else if(tTypeText[3] == "S"){
		tTypeTextSel1 = "선박";
	}else if(tTypeText[3] == "M"){
		tTypeTextSel1 = "지하철";
	}else if(tTypeText[3] == "T"){
		tTypeTextSel1 = "택시";
	}else if(tTypeText[3] == "1"){
		tTypeTextSel1 = "항공";
	}else if(tTypeText[3] == "2"){
		tTypeTextSel1 = "고속철도";
	}else if(tTypeText[3] == "3"){
		tTypeTextSel1 = "개인차량";
	}else{
		tTypeTextSel1 = "";
	}

	// 왕복 편도
	var tTypeTextSel2;
	if(tTypeText[4] == "R"){
		tTypeTextSel2 = "왕복";
	}else if(tTypeText[4] == "D"){
		tTypeTextSel2 = "편도";
	}else{
		tTypeTextSel2 = "";
	}

	$("#" + obj.targetId + " input[name='tName']").val(tName);
//	$("#" + obj.targetId + " input[name='tOrg']").val(tOrg);
	$("#" + obj.targetId + " input[name='tOrg']").val(tOrg);
	$("#" + obj.targetId + " input[name='tType']").val(
			tTypeText[0] + " - " + tTypeText[1] + " - " + tTypeText[2] + " / " + tTypeTextSel1 + " (" + tTypeTextSel2 + ")"
	);
	$("#" + obj.targetId + " input[name='tTypeVal']").val(tType);
	$("#" + obj.targetId + " input[name='tPurp']").val(tPurp);
	$("#" + obj.targetId + " input[name='tAmount']").val(tAmount);

	$("#" + obj.targetId + " input[name='tSiteExpKind']").val(tSiteExpKind);
	$("#" + obj.targetId + " input[name='tMvDist']").val(tMvDist);

	var tAmount = $("input[name='tAmount']");
	var total = 0;
	for(var i = 0; i < tAmount.size(); i++){
		var tVal = tAmount[i].value;
		if(tVal == "") continue;
		for(var j = 0; j < tVal.length; j++){
			tVal = tVal.replace(",", "");
		}
		total = total + parseInt(tVal);
	}

	$("#tAmountTotal").text(gf_AmtFormat(total));


}

/**
 * 금액 포맷에 맞춤
 * @param val
 * @returns
 */
function gf_AmtFormat(val){
	var rslVal = val;
	var exp = new RegExp("(\\d)(?=(?:\\d{" + 3 + "})+(?!\\d))", "g");
	rslVal = rslVal.toString().replace(exp, '$1,');

	return rslVal;
}


/**
 * EAI 상신
 */
function cf_GetTrafficCost(){

	// SAP 상신 전에 Validation Check 실시할 것

	// Parameter Init
	var IBukrs = "1000"; // 회사코드 1000 상수
	var Mandt = null; // NULL
	var Bukrs = "1000"; // 회사코드
	var Refkey = ""; // SAP 에서 생성 후 Return
	var BdocNo = ""; // CMAS 문서번호
	var Seqno = ""; // 순번
	var Kostl1 = ""; // 집행팀코드
	var Kostxt = null; // NULL
	var Sabun1 = ""; // 작성자
	var Date1 = ""; // 다수 증빙일 경우 사용일이 가장 높은 날짜
	var Sabun2 = ""; // 신청자 사번
	var Lfatxt = null; // NULL
	var Kostl2 = ""; // 신청팀코드
	var Bankno = null; // 은행계좌 NULL
	var Paydate = null; // 레코드 생성일 NULL
	var Waers = "KRW"; // 통화 키
	var Amount = ""; // 사용금액
	var Area = ""; // 지역
	var Descript = ""; // 사용목적
	var Chkflag = null; // 예산반영 NULL
	var Sdate = null; // 레코드 생성일 NULL
	var Sflag = null; // 예산반영 NULL
	var BdgtType = ""; // 예산구분 경비구분
	var DocType = ""; // 전표구분 H G
	var Aufnr = ""; // 예산번호
	var Posid = ""; // R 예산번호, Q 원가실귀속코드
	var Kblnr = ""; // 특정 사용 자금의 전표 번호 NULL
	var Kblpos = ""; // 특정 사용자금 전표 항목 NULL
	var Usedt = ""; // 업무사용일
	var Rfund = ""; // 자금


	var draftParams = {
			IBukrs : IBukrs,
			Mandt : Mandt,
			Bukrs : Bukrs,
			Refkey : Refkey,
			BdocNo : BdocNo,
			Seqno : Seqno,
			Kostl1 : Kostl1,
			Kostxt : Kostxt,
			Sabun1 : Sabun1,
			Date1 : Date1,
			Sabun2 : Sabun2,
			Lfatxt : Lfatxt,
			Kostl2 : Kostl2,
			Bankno : Bankno,
			Paydate : Paydate,
			Waers : Waers,
			Amount : Amount,
			Area : Area,
			Descript : Descript,
			Chkflag : Chkflag,
			Sdate : Sdate,
			Sflag : Sflag,
			BdgtType : BdgtType,
			DocType : DocType,
			Aufnr : Aufnr,
			Posid : Posid,
			Kblnr : Kblnr,
			Kblpos : Kblpos,
			Usedt : Usedt,
			Rfund : Rfund
	};


	gf_Transaction("SAVE_SEND_TRAFFIC_COST", "/trip/eai/getSendTrafficCost.xpl", draftParams, {}, "f_Callback", true);
}

function cf_cmasDocDelete(){

	var v_userId = '';
	if (ds_Signln.size() == 0){
		v_userId = ds_DocData.get(0,'fstRegUserId');
	}else{
		v_userId = ds_Signln.get(0,'signUserId');
	}

	//신청서 삭제시 상신자 요청으로 삭제하는 것
	var deleteParams = {
  		docNo : ds_DocData.get(0,'docNo'),
  		refNo : ds_DocData.get(0,'refNo'),
 		userId : v_userId
  	};

	gf_Transaction("SAVE_DELETE_CMAS_DOC", "/trip/cityTransp/deleteCmasDocDelete.xpl", deleteParams, {}, "f_Callback", true);
}