/**
 * @class 화면에서 사용할 전역변수를 아래에 선엄함
 * @author 권준호
 * @since 2013-04-04
 * @version 1.0
 */
var v_BaseWindowHeight = 700;
// var ds_DocData = new DataSet();
var v_DocSts = "";
var v_regNo = "";
var v_cls = "";
var v_subject = "";
var v_actorNm = "";
var v_spv = "";
var v_playTime = "";
var v_dbng = "";
var v_sbtl = "";
var v_dvdCls = "";
var v_cls = "";
var	v_mkco = "";
var	v_recTp = "";
var	v_scrn = "";
var v_cont = "";

/**
 * 공통 코드처리 DataSet
 */
var ds_Cls = new DataSet(); // 분류 공통코드

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
	v_DocSts = gf_IsNull(datas.docSts) ? "" : datas.docSts;
}

/**
 * @class 컴포넌트 생성
 * @author 권준호
 * @since 2013-04-04
 * @version 1.0
 */
function cf_SetComponents() {

}

/**
 * @class Element, Compoment Event 처리
 * @author 권준호
 * @since 2013-04-04
 * @version 1.0
 */
function cf_SetEventListener() {
	// 대출신청 버튼
	$("#btnDraft").click(
			function() {
				var newWin = gf_PostOpen("/tech/techAppn/techAppnDraft.jsp", null, "toolbar=no,scrollbars=yes,width=1024,height=650", datas, true, true);
			});

	// 편집 버튼
	$("#btnEdit").click(
			function() {
				v_regNo = datas.regNo;
				v_cls = datas.cls;
				v_subject = datas.subject;
				v_actorNm = datas.actorNm;
				v_spv = datas.spv;
				v_playTime = datas.playTime;
				v_dbng = datas.dbng;
				v_sbtl = datas.sbtl;
				v_dvdCls = datas.dvdCls;
				v_cls = datas.cls;
				v_mkco  = datas.mkco;
				v_recTp = datas.recTp;
				v_scrn  = datas.scrn;
				v_cont = datas.cont;

				var params = {
					regNo : v_regNo,
					cls : v_cls,
					subject : v_subject,
					actorNm : v_actorNm,
					spv : v_spv,
					playTime : v_playTime,
					dbng : v_dbng,
					sbtl : v_sbtl,
					dvdCls : v_dvdCls,
					mkco : v_mkco,
					recTp :v_recTp,
					scrn : v_scrn,
					cont : v_cont
				};
				var newWin = gf_PostOpen("/tech/techAppn/techDvdDataDraft.jsp",	null, "toolbar=no,scrollbars=yes,width=1024,height=700", params, true, true);
			});

	// 삭제 버튼
	$("#btnDelete").click(
			function() {
				v_regNo = datas.regNo;
				v_cls = datas.cls;

				var params = {
					regNo : v_regNo,
					cls   : v_cls
				};

				gf_Transaction("DELETE_TECH_DATA","/tech/techAppn/deleteTechDat.xpl", params, {},"f_Callback", true);
			});

	// 닫기 버튼
	$("#btnCancle").click(function() {
		self.close();
	});

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
	f_SetBtn();

	$("#regNo").text(datas.disregno);
	$("#subject").text(datas.subject);
	$("#actorNm").text(datas.actorNm);
	$("#spv").text(datas.spv);
	$("#playTime").text(datas.playTime);
	$("#dbng").text(datas.dbng);
	$("#sbtl").text(datas.sbtl);

	var v_dvdCls = datas.dvdCls;
	if (v_dvdCls == "D") {
		v_dvdCls = "드라마/코믹";
	} else if (v_dvdCls == "A") {
		v_dvdCls = "액션/SF";
	} else if (v_dvdCls == "H") {
		v_dvdCls = "공포/스릴러";
	} else if (v_dvdCls == "C") {
		v_dvdCls = "어린이/가족/만화";
	} else if (v_dvdCls == "P") {
		v_dvdCls = "Playstation2";
	} else {
		v_dvdCls = "기타";
	}

	$("#dvdCls_disp").text(v_dvdCls);
	$("#mkco").text(datas.mkco);
	$("#recTp").text(datas.recTp);
	$("#scrn").text(datas.scrn);

	// $("#cont").text(datas.cont);

	for ( var i = 0; i < datas.cont.length; i++) {
		datas.cont = datas.cont.replace("##", "\n");
	}
	$("#cont").text(datas.cont);

}

function f_CheckValidationForSave() {

	var subject = gf_GetValue($("input[name='subject']").val()); // 제목
	var issuePl = gf_GetValue($("input[name='issuePl']").val()); // 발행처

	if (gf_IsNull(subject)) {
		gf_AlertMsg("제목은 필수입니다.");
		return false;
	}
	if (gf_IsNull(issuePl)) {
		gf_AlertMsg("발행처는 필수입니다.");
		return false;
	}
}

function f_Close() {
	opener.cf_RetrieveTechDatList();
	self.close();
}

function f_SetBtn() {

	var d = new Date();
	var curr_hours = d.getHours();

	// 대출가능 문서의 경우 대출신청 버튼 생성
	if ((v_DocSts == "D30" || v_DocSts == "D51" ||v_DocSts == "D17" || v_DocSts == "대출가능" || v_DocSts == "반납" || v_DocSts == "취소")
			&& curr_hours < 12 ) {
		$("#btnDraft").show();
	}

	if (gv_AuthList.auth[0].code.match("RO_CMAS_CT_ADM") == "RO_CMAS_CT_ADM"
			|| gv_AuthList.auth[0].code.match("RO_CMAS_CT_BOOK") == "RO_CMAS_CT_BOOK"
			|| gv_AuthList.auth[0].code.match("RO_CMAS_CT_DVD") == "RO_CMAS_CT_DVD") {

		if (v_DocSts == "D30" || v_DocSts == "D51" || v_DocSts == "D17" || v_DocSts == "대출가능"
				|| v_DocSts == "반납" || v_DocSts == "취소") {
			$("#btnEdit").show();
			$("#btnDelete").show();
		}
	}
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
