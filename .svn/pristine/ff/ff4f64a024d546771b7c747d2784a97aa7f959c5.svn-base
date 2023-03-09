/**
 * @class 화면에서 사용할 전역변수를 아래에 선엄함
 * @author 권준호
 * @since 2013-04-04
 * @version 1.0
 */

var v_cls = "";
var	v_regNo   = "";
var	v_subject = "";
var	v_sumry = "";
var	v_keywd = "";
var	v_issueTeam = "";
var	v_issueEr = "";
var	v_issueMgr = "";
var	v_issueDd = "";
var	v_edtPerchrg = "";
var	v_distrCls = "";
var	v_prfrd = "";
var	v_issueCpys = "";
var	v_docSts = "";
var	v_fileAtchId = "";
var	v_imgAtchFileId = "";
var v_issueRegNo = "";
var v_admin = "";


/**
 * 공통 코드처리 DataSet
 */


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
	v_docSts = gf_IsNull(datas.docSts) ? "" : datas.docSts;
	v_issueRegNo = gf_IsNull(datas.issueRegNo) ? "" : datas.issueRegNo;
	v_fileAtchId =  gf_IsNull(datas.fileAtchId) ? "" : datas.fileAtchId;
	v_imgAtchFileId = gf_IsNull(datas.imgAtchFileId) ? "" : datas.imgAtchFileId;
}


function cf_SetComponents()
{
	//Attachment 컴포넌트 생성
	gf_InitFileUploadComponent();
	gf_setMode("download");

}

/**
 * @class Element, Compoment Event 처리
 * @author 권준호
 * @since 2013-04-04
 * @version 1.0
 */
function cf_SetEventListener() {

	// 등록완료 버튼
	$("#btnComplete").click(
			function() {
				v_regNo = datas.regNo;
				v_cls = datas.cls;
				v_docSts = 'D18';

				var params = {
					regNo : v_regNo,
					cls   : v_cls,
					docSts : v_docSts
				};

				gf_Transaction("SAVE_TECH_DATA", "/tech/publication/updateTechPubDatComp.xpl", params, {}, "f_Callback", true);
			});

	// 대출신청 버튼
	$("#btnDraft").click(
			function() {
				var newWin = gf_PostOpen("/tech/publication/pubAppnDraft.jsp", null, "toolbar=no,scrollbars=yes,width=1024,height=650", datas, true, true);
			});

	// 편집 버튼
	$("#btnEdit").click(
			function() {
				v_cls        = datas.cls;
				v_regNo      = datas.regNo;
				v_subject    = datas.subject;
				v_sumry      = datas.sumry;
				v_keywd      = datas.keywd;
				v_issueTeam  = datas.issueTeam;
				v_issueEr    = datas.issueEr;
				v_issueMgr   = datas.issueMgr;
				v_issueDd    = datas.issueDd;
				v_edtPerchrg = datas.edtPerchrg;
				v_distrCls   = datas.distrCls;
				v_prfrd      = datas.prfrd;
				v_issueCpys  = datas.issueCpys;
				v_fileAtchId = v_fileAtchId;
				v_imgAtchFileId  = v_imgAtchFileId;
				v_issueRegNo = v_issueRegNo;

				var params = {
						cls        : v_cls,
						regNo      : v_regNo,
						subject    : v_subject,
						sumry      : v_sumry,
						keywd      : v_keywd,
						issueTeam  : v_issueTeam,
						issueEr    : v_issueEr,
						issueMgr   : v_issueMgr,
						issueDd    : v_issueDd,
						edtPerchrg : v_edtPerchrg,
						distrCls   : v_distrCls,
						prfrd      : v_prfrd,
						issueCpys  : v_issueCpys,
						fileAtchId : v_fileAtchId,
						imgAtchFileId : v_imgAtchFileId,
						docSts     : v_docSts,
						issueRegNo : v_issueRegNo
				};
				var newWin = gf_PostOpen("/tech/publication/publicationDataDraft.jsp",	null, "toolbar=no,scrollbars=yes,width=1024,height=700", params, true, true);
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

				if (confirm("정말로 삭제하시겠습니까?")){
					gf_Transaction("DELETE_TECH_DATA","/tech/techAppn/deleteTechDat.xpl", params, {},"f_Callback", true);
				}else{

				}
			});

	// 닫기 버튼
	$("#btnCancle").click(function() {
		self.close();
	});

    // 서지사항 인쇄
	$("#btnPrint").click(function(){


		var params = {
				url : "RexServer30/viewer.jsp?rex_rptname=tech/serge&connectname=oracle1&isOpenWindow=true&option=1&direct_print=true&issueRegNo="+v_issueRegNo+"&rex_db=oracle1"
		};
		var newWin = gf_PostOpen("/tech/publication/printViewDoc.jsp",null, "toolbar=no,scrollbars=yes,width=1024,height=700", params, true, true);

		//	var newWin=gf_PostOpen("/RexServer30/viewer.jsp?rex_rptname=tech/serge&connectname=oracle1&isOpenWindow=true&option=1&issueRegNo="+v_issueRegNo+"&rex_db=oracle1");
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

	$("#cls").text(datas.cls);
	$("#regNo").text(datas.regNo);
	$("#subject").text(datas.subject);
	$("#issueTeam").text(datas.issueTeam);
	$("#issueEr").text(datas.issueEr);
	$("#issueMgr").text(datas.issueMgr);
	$("#issueDd").text(datas.issueDd);
	$("#edtPerchrg").text(datas.edtPerchrg);
	$("#distrCls").text(datas.distrCls);
	$("#prfrd").text(datas.prfrd);
	$("#issueCpys").text(datas.issueCpys);
	$("#issueRegNo").text(datas.issueRegNo);

	for ( var i = 0; i < datas.sumry.length; i++) {
		datas.sumry = datas.sumry.replace("##", "\n");
	}
	$("#sumry").text(datas.sumry);

	for ( var i = 0; i < datas.keywd.length; i++) {
		datas.keywd = datas.keywd.replace("##", "\n");
	}
	$("#keywd").text(datas.keywd);

	v_fileAtchId = datas.fileAtchId;
	v_imgAtchFileId = datas.imgAtchFileId;

	gf_retrieveFileList(v_fileAtchId); //첨부파일 리스트 조회

	var image = document.getElementById('img');
	//image.src = gContextPath + "/co/common/file/imageFile.xpl?fileAtchId="+"3339123";
	image.src = gContextPath + "/co/common/file/imageFile.xpl?fileAtchId="+v_imgAtchFileId;

}

function f_CheckValidationForSave() {

	var subject = gf_GetValue($("input[name='subject']").val()); // 제목

	if (gf_IsNull(subject)) {
		gf_AlertMsg("제목은 필수입니다.");
		return false;
	}

}

function f_Close() {
	opener.cf_RetrieveTechDatList();
	self.close();
}

function f_SetBtn() {

	if (v_docSts == "D18" || v_docSts == "등록완료") {
		//$("#btnDraft").show();
		$("#btnPrint").show();
	}

	if (v_docSts == "D16" || v_docSts == "" || v_docSts == "임시저장") {
		$("#btnAppn").show();
		$("#btnEdit").show();
	}

	if (gv_AuthList.auth[0].code.match("RO_CMAS_CT_ADM") == "RO_CMAS_CT_ADM"
			|| gv_AuthList.auth[0].code.match("RO_CMAS_CT_BOOK") == "RO_CMAS_CT_BOOK") {

		if (v_docSts == "D17" || v_docSts == "등록신청") {
			$("#btnComplete").show();
		}

		$("#btnDelete").show();
		$("#btnEdit").show();

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

