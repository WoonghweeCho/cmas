
/**
* @class 화면에서 사용할 전역변수를 아래에 선엄함
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
var v_BaseWindowHeight = 700;

var params = {};

var v_docNo = "";		//문서번호
var v_docSts = "";		//문서상태
var v_regNo = "";       //자료등록번호
var v_cls    = "";      //자료등록구분
var fnlEditUserId = "";

//팝업이 닫힐때 리턴값을 전달할 callback 함수
var v_CallbackFunc;


//문서상태 : docSts
//D16 : 임시저장, D17 : 취소 ,D31 : 대출신청, D50 : 대출중, D51 : 반납

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
	v_regNo = gf_IsNull(datas)? "" : gf_IsNull(datas.regNo) ? "": datas.regNo;
	v_docSts = gf_IsNull(datas)? "" : gf_IsNull(datas.docSts) ? "": datas.docSts;
	v_docNo = gf_IsNull(datas)? "" : gf_IsNull(datas.docNo) ? "": datas.docNo;
	v_cls = gf_IsNull(datas)? "" : gf_IsNull(datas.cls) ? "": datas.cls;
//	alert(datas.gubun);
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
    //대출 버튼
	$("#btnRent").click(function(){
		var v_docSts = 'D50';     // D50 : 대출중
		var v_fnlEditUserId = gv_userId;

		var params = {
						docNo  : v_docNo,
						docSts : v_docSts,
						regNo  : v_regNo,
						cls    : v_cls,
						fnlEditUserId : v_fnlEditUserId
					};

		gf_Transaction("UPDATE_TECH_APPN_BAS", "/tech/techAppn/updateTechAppnBas.xpl", params, {}, "f_Callback", true);
	});

    //반납 버튼
	$("#btnReturn").click(function(){
		var v_docSts = 'D51';    // D51 : 반납완료
		var v_fnlEditUserId = gv_userId;

		var params = {
						docNo  : v_docNo,
						docSts : v_docSts,
						regNo  : v_regNo,
						cls    : v_cls,
						fnlEditUserId : v_fnlEditUserId
					};

		gf_Transaction("UPDATE_TECH_APPN_BAS", "/tech/techAppn/updateTechAppnBas.xpl", params, {}, "f_Callback", true);
	});

	//삭제 버튼
	$("#btnDelete").click(function(){
		var params = {
				docNo  : v_docNo,
				docSts : v_docSts,
				regNo  : v_regNo,
				cls    : v_cls
			};

		gf_Transaction("DELETE_TECH_APPN_BAS", "/tech/techAppn/deleteTechAppnBas.xpl", params, {}, "f_Callback", true);
	});

	//닫기 버튼
	$("#btnCancle").click(function(){
		self.close();
	});

	//취소 버튼
	$("#btnBackCancle").click(function(){
		var v_docSts = 'D17';     // D17 : 취소
		var v_fnlEditUserId = gv_userId;

		var params = {
						docNo  : v_docNo,
						docSts : v_docSts,
						regNo  : v_regNo,
						cls    : v_cls,
						fnlEditUserId : v_fnlEditUserId
					};

		gf_Transaction("UPDATE_TECH_APPN_BAS", "/tech/techAppn/updateTechAppnBas.xpl", params, {}, "f_Callback", true);
	});

	// 반납메일요청 버튼
	$("#btnMailSend").click(function(){
		//메일전송
		// 메일 전송을 위한 플래그 Y 일경우만 메일을 전송한다.
		var	mailYn = "N";
		var ds_Mail = new DataSet();
		var mailParams;

		mailParams = {
					spec : "",
					bodyTemplate : "",
					fromMailId : "cmastechadmin@daewooenc.com",
					fromMailName : "건설기술정보센터관리자",
					mailId : v_CmasList[i].userId + "@daewooenc.com", // 운영시에는 담당자로 변경할 것
					mailSubject : "[건설기술정보센터] 반납요청 바랍니다." ,
					htmlBody : "■ 신청자 : " + v_IfParam.tripUserKnm + " " + v_IfParam.tripUserPositCd + "<br>"
								+ "■ 문서상태 : 대출중" + "<br>"
								+ "■ 대여기간 : " + $("#rTripDateStart").val() + " ~ " + $("#rTripDateEnd").val()
			};

			// mailParams 삽입
			ds_Mail.add(mailParams);

	  		var dataSetParam = {
	  				input1 : ds_Mail.getAllData()
			};

	  		// 국출 01 해출 02 시내 03 해출정산 04
	  		var updateParams = {
	  				mailYn : mailYn
	  		};

				gf_Transaction("MAIL_SEND", "/trip/outerTrip/sendSTypeMail.xpl", updateParams, dataSetParam, "f_Callback", true);
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

	f_SetBtn();

	var drafterText = datas.userId + " " + datas.userNm;
	$("#drafter").text(drafterText);

	//소속팀 셋팅
	var drafterOrgNm = datas.orgNm + " (" + datas.orgCd + ")";
	$("#drafterOrgNm").text(drafterOrgNm);

	$("#subject").text(datas.subject);
	$("#regNo").text(datas.cls+datas.regNo);
	$("#docNo").text(datas.docNo);

	$("#appnDd").text(datas.appnDd);
	$("#rentDd").text(datas.rentDd);
	$("#rtrnDd").text(datas.rtrnDd);
}

function f_SetBtn(){

	if(gv_AuthList.auth[0].code.match("RO_CMAS_CT_ADM") == "RO_CMAS_CT_ADM"
		|| gv_AuthList.auth[0].code.match("RO_CMAS_CT_BOOK") == "RO_CMAS_CT_BOOK"
		|| gv_AuthList.auth[0].code.match("RO_CMAS_CT_DVD") == "RO_CMAS_CT_DVD") {

		if(v_docSts == "D31" && v_docSts != ""){
			if(v_cls == 'DV'){
				$("#btnBackCancle").show();
			}
			$("#btnRent").show();
		}

		if(v_docSts == "D50" && v_docSts != ""){
			$("#btnReturn").show();
		}
	}

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
	  		self.close();
			break;
	  	case "SAVE_TECH_DATA" :  //저장
	  		self.close();
			break;
		case "UPDATE_TECH_APPN_BAS" :  //신청 Bas 저장
			self.close();
			break;
		case "UPDATE_TECH_APPN_DAT" :  //자료신청 저장
			self.close();
			break;
		case "SAVE_TECH_APPN_DAT" :  //신청 후 자료 Dat 저장
	  		if ( !gf_IsNull(v_CallbackFunc) ) {
	  			var openCallbackFunc = "opener."+v_CallbackFunc;
	  	    	eval(openCallbackFunc + "();");
	  	    }
			break;

		case "DELETE_TECH_DATA" :  //삭제
	  		if ( !gf_IsNull(v_CallbackFunc) ) {
	  			var openCallbackFunc = "opener."+v_CallbackFunc;
	  	    	eval(openCallbackFunc + "();");
	  	    }

			break;
		case "MAIL_SEND" :  //반낭요청메일 보내기
			break;
	  	default :
	  		break;
	  }
}

