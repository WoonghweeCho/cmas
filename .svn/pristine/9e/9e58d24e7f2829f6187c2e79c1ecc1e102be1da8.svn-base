
/**
* @class 화면에서 사용할 전역변수를 아래에 선엄함
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
var v_BaseWindowHeight = 700;

var d = new Date();
var curr_year = d.getFullYear();
var curr_month = d.getMonth();
var curr_date = d.getDate();
var curdd = curr_year + "-" + (curr_month+1) + "-" +curr_date ;

// 문서상태 : docSts
// D16 : 임시저장, D18 : 등록완료

var params = {};
var v_loclCd;
var v_cls;
var	v_regNo   = "";
var	v_subject = "";
var	v_sumry = "";
var	v_docSts = "";
var v_userNm = gv_userNm;
var v_fstRegDt = curdd;
var fstRegUserId;
var fnlEditUserId;
var v_fileAtchId;
var v_saveDv = "Y";
var v_editor = null;

var ds_TechDatList = new DataSet();	// 상세정보


//팝업이 닫힐때 리턴값을 전달할 callback 함수
var v_CallbackFunc;
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
	v_loclCd = gf_GetCookie("loclCd");		// 로케일 20140724
	v_regNo = gf_IsNull(datas)? "" : gf_IsNull(datas.regNo) ? "": datas.regNo;
	v_docSts = gf_IsNull(datas)? "" : gf_IsNull(datas.docSts) ? "": datas.docSts;
	v_CallbackFunc = gf_IsNull(datas.callbackFunc) ? "" : datas.callbackFunc;
	v_fileAtchId = gf_IsNull(datas)? "" : gf_IsNull(datas.fileAtchId) ? "": datas.fileAtchId;

	//파일 업로드 콜백 함수 Initialize
	gf_SetUploadCallback("fn_SetUploadCallback");

}

/**
* @class 컴포넌트 생성
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetComponents()
{
	//Attachment 컴포넌트 생성
	gf_InitFileUploadComponent();
	gf_setMode("upload");

}

/**
* @class Element, Compoment Event 처리
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetEventListener()
{

	// 저장 버튼
	$("#btnSave").click(function(){
		v_docSts = 'D18';
		gf_onFileUpload();
	});

	//닫기 버튼
	$("#btnCancle").click(function(){

		if(v_regNo!=""){
			opener.f_Close();
		}
	  	self.close();
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

	if(v_regNo !=""){
		$("#doregNo").text(datas.cls+datas.regNo);
		$("#subject").val(datas.subject);

	    for(var i = 0; i < datas.sumry.length; i++){
			datas.sumry = datas.sumry.replace("##", "\n");
		}
	    v_sumry = datas.sumry;

		var drafterText = datas.userId + " " + datas.userNm;
		$("#drafter").text(drafterText);

		$("#fstRegDt").text(datas.fstRegDt);

	}else{
		var drafterText = gv_userId + " " + gv_userNm;
		$("#drafter").text(drafterText);
		$("#fstRegDt").text(v_fstRegDt);
	}

	f_editorSet();

	if(v_fileAtchId !=""){
		gf_retrieveFileList(v_fileAtchId);  //첨부파일 리스트 조회
	}else{
		f_retrieveImgFileAtchId();
	}

}

function f_CheckValidationForSave(){

	var subject = gf_GetValue($("input[name='subject']").val());	//제목

	if(gf_IsNull(subject)){ gf_AlertMsg("제목은 필수입니다."); return false; }
}

function f_Close(){
}

function f_Save(){
	//최초 자료문서 저장
	if(v_regNo ==""){
		if(f_CheckValidationForSave() == false){
			return;
		}
		v_sumry = CKEDITOR.instances.editor.getData();
		v_cls     = 'NO';
		v_subject = $("#subject").val();
		fstRegUserId = gv_userId;
		fnlEditUserId = gv_userId;
		v_fileAtchId  = v_fileAtchId;

		var params = {
				regNo         : v_regNo,
				cls           : v_cls,
				subject       : v_subject,
				sumry         : v_sumry,
				docSts        : v_docSts,
				fstRegUserId  : fstRegUserId,
				fnlEditUserId : fnlEditUserId,
				fileAtchId    : v_fileAtchId
				};

			gf_Transaction("DRAFT_TECH_DATA", "/tech/techAppn/insertTechNotice.xpl", params, {}, "f_Callback", true);
	}else{
		//편집 후 저장
		if(f_CheckValidationForSave() == false){
			return;
		}
		v_sumry = CKEDITOR.instances.editor.getData();

		v_cls     = 'NO';
		v_subject = $("#subject").val();

		fstRegUserId = datas.userId;
		fnlEditUserId = gv_userId;
		v_fileAtchId  = v_fileAtchId;

		var params = {
				regNo      : v_regNo,
				cls        : v_cls,
				subject    : v_subject,
				sumry      : v_sumry,
				//docSts     : v_docSts,
				fstRegUserId  : fstRegUserId,
				fnlEditUserId : fnlEditUserId,
				fileAtchId    : v_fileAtchId
		};

			gf_Transaction("SAVE_TECH_DATA", "/tech/techAppn/updateTechNotice.xpl", params, {}, "f_Callback", true);

	}
}

function f_SetBtn() {

	if (gv_AuthList.auth[0].code.match("RO_CMAS_CT_ADM") == "RO_CMAS_CT_ADM"
			|| gv_AuthList.auth[0].code.match("RO_CMAS_CT_BOOK") == "RO_CMAS_CT_BOOK") {

	}
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
  	  	case "DRAFT_TECH_DATA" : //등록
  	   		self.close();
			break;
	  	case "SAVE_TECH_DATA" :  //저장
	  		opener.f_Close();
  	  		self.close();
			break;
	  	case "SELECT_FILEATCHID" :
			var fileAtchIdTmp = "";
			$.each(resultData, function(i, itemAry) {
				if ( i == "fileAtchId") {
					$.each(itemAry, function( j, item)  {
						fileAtchIdTmp = item.fileAtchId;
					} );
				};
			});
			break;
	  	case "DELETE_TECH_DATA" :  //삭제
	  		self.close();
			break;
	  	default :
	  		break;
	  }
}

function fn_SetUploadCallback( fileAtchId ) {

	v_fileAtchId = fileAtchId;
	if(v_fileAtchId == "undefined"){
		v_fileAtchId = '' ;
	}

//	f_onImageUpload();
	f_Save();
}

/**
* @function
* @param  N/A
* @see    file upload를 위한 xmlhttprequest 생성 함수 & 프로그래스를 위한 이벤트 등록 함수
* @return N/A
* @author 변형구
* @since 2013-03-20
* @version 1.0
*/
function f_sendXHRequest(formData, uri) {
    // Get an XMLHttpRequest instance
    var xhr = new XMLHttpRequest();

    // Set up request
    xhr.open('POST', uri, true);

    // Fire!
    xhr.send(formData);
}

function f_retrieveImgFileAtchId() {
		gf_Transaction("SELECT_FILEATCHID", "/co/common/file/retrieveFileAtchId.xpl?saveYn=Y", {}, {}, "f_Callback", false);
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
