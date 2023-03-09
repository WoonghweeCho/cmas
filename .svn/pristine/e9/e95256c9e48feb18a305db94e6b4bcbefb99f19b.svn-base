/**
 * @class 화면에서 사용할 전역변수를 아래에 선엄함
 * @author 권준호
 * @since 2013-04-04
 * @version 1.0
 */

var v_cls     = "";
var	v_regNo   = "";
var	v_subject = "";
var	v_sumry   = "";
//var	v_docSts  = "";
var v_userId  = "";
var v_userNm  = "";
var v_fstRegDt  = "";
var v_fileAtchId = "";

var v_saveDv = "N";
var v_editor = null;

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
	v_cls =  gf_IsNull(datas.cls) ? "" : datas.cls;
	//v_docSts = gf_IsNull(datas.docSts) ? "" : datas.docSts;
	v_regNo = gf_IsNull(datas.regNo) ? "" : datas.regNo;
	v_fileAtchId =  gf_IsNull(datas.fileAtchId) ? "" : datas.fileAtchId;
	v_sumry = gf_IsNull(datas.sumry) ? "" : datas.sumry;
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

	// 편집 버튼
	$("#btnEdit").click(
			function() {
				v_cls        = datas.cls;
				v_regNo      = datas.regNo;
				v_subject    = datas.subject;
				v_sumry      = datas.sumry;
				v_fileAtchId = v_fileAtchId;
				v_userId     = datas.userId;
				v_userNm     = datas.userNm;
				v_fstRegDt   = datas.fstRegDt;

				var params = {
						cls        : v_cls,
						regNo      : v_regNo,
						subject    : v_subject,
						sumry      : v_sumry,
						userId     : v_userId,
						userNm     : v_userNm,
						fstRegDt   : v_fstRegDt,
						fileAtchId : v_fileAtchId
				};
				var newWin = gf_PostOpen("/tech/techAppn/techNoticeDraft.jsp",	null, "toolbar=no,scrollbars=yes,width=1024,height=700", params, true, true);
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

	var drafterText = datas.userId + " " + datas.userNm;
	$("#drafter").text(drafterText);

	$("#fstRegDt").text(datas.fstRegDt);
	$("#cls").text(datas.cls);
	$("#regNo").text(datas.regNo);
	$("#subject").text(datas.subject);

	f_editorSet();
	v_fileAtchId = datas.fileAtchId;

	gf_retrieveFileList(v_fileAtchId); //첨부파일 리스트 조회

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

	if (gv_AuthList.auth[0].code.match("RO_CMAS_CT_ADM") == "RO_CMAS_CT_ADM"
			|| gv_AuthList.auth[0].code.match("RO_CMAS_CT_BOOK") == "RO_CMAS_CT_BOOK") {
		$("#btnEdit").show();
		$("#btnDelete").show();
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

/**
* @class 에디터 설정
* @author 고준석
* @since 2013-04-04
* @version 1.0
*/
function f_editorSet()
{	//editor 처리
	var editBox = $("#editBox");
	if(v_saveDv == "Y"){
		editBox.html("<textarea id=\"editor\"></textarea>");
		CKEDITOR.replace( 'editor', {
			filebrowserUploadUrl: gContextPath+'/co/common/file/uploadWebFile.xpl?type=editor',
			on: {
				'instanceReady': function(e){
					v_editor = e.editor;
					v_editor.setData(v_sumry);
			    }
			}
		});
	}else{
		editBox.html("<div id=\"editorView\"></div>");
		var editorView = $("#editorView");
		editorView.css("padding", "20px 20px 10px 20px");
		editorView.html(v_sumry);
	}
}
