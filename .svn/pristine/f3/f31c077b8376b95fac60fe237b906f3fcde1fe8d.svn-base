
/**
* @class 화면에서 사용할 전역변수를 아래에 선엄함
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
var v_BaseWindowHeight = 700;

var imgFile;

// 문서상태 : docSts
// D16 : 임시저장, D17 : 등록요청  , D18 : 등록완료

var params = {};
var v_loclCd;
var v_cls;
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
var fstRegUserId;
var fnlEditUserId;
var v_fileAtchId;
var v_imgAtchFileId;
var v_admin = "";
var v_issueRegNo = "";

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
	v_issueRegNo = gf_IsNull(datas)? "" : gf_IsNull(datas.issueRegNo) ? "": datas.issueRegNo;
	v_docSts = gf_IsNull(datas)? "" : gf_IsNull(datas.docSts) ? "": datas.docSts;
	v_CallbackFunc = gf_IsNull(datas.callbackFunc) ? "" : datas.callbackFunc;
	v_fileAtchId = gf_IsNull(datas)? "" : gf_IsNull(datas.fileAtchId) ? "": datas.fileAtchId;
	v_imgAtchFileId = gf_IsNull(datas)? "" : gf_IsNull(datas.imgAtchFileId) ? "": datas.imgAtchFileId;

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
	// 등록신청 버튼
	$("#btnAppn").click(function(){
		v_docSts = 'D17';
		gf_onFileUpload();
	});

	// 저장 버튼
	$("#btnSave").click(function(){
		if(v_docSts == ''){
			v_docSts = 'D16';
		}
		gf_onFileUpload();
	});

	//닫기 버튼
	$("#btnCancle").click(function(){
		if(v_regNo!=""){
			opener.f_Close();
		}
	  	self.close();
	});

	// 등록완료 버튼
	$("#btnComplete").click(function() {
		v_admin = 'Y'
		v_docSts = 'D18';

		gf_onFileUpload();

	});


	 //img등록 버튼
	$("#btnUpload").click(function(){
		f_onImageUpload();
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
	//ds_TechDatList.bind($("#techDataDtlForm")[0]);
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
		$("#issueTeam").val(datas.issueTeam);
		$("#issueEr").val(datas.issueEr);
		$("#issueMgr").val(datas.issueMgr);
		$("#issueDd").val(datas.issueDd);
		$("#edtPerchrg").val(datas.edtPerchrg);
	    $("#distrCls").val(datas.distrCls);
	    $("#prfrd").val(datas.prfrd);
	    $("#issueCpys").val(datas.issueCpys);

	    for(var i = 0; i < datas.sumry.length; i++){
			datas.sumry = datas.sumry.replace("##", "\n");
		}
		$("#sumry").val(datas.sumry);

		for(var i = 0; i < datas.keywd.length; i++){
			datas.keywd = datas.keywd.replace("##", "\n");
		}
		$("#keywd").val(datas.keywd);
	}

	if(v_fileAtchId !=""){
		gf_retrieveFileList(v_fileAtchId);  //첨부파일 리스트 조회
	}

	if(v_imgAtchFileId !=""){
		$("#findName").text('변경');
	}else{
		$("#findName").text(' 찾기');
		f_retrieveImgFileAtchId();
	}

}

function f_CheckValidationForSave(){

	var subject = gf_GetValue($("input[name='subject']").val());	//제목
	var issueDd = gf_GetValue($("input[name='issueDd']").val());    //발행일

	if(gf_IsNull(subject)){ gf_AlertMsg("제목(표제)은 필수입니다."); return false; }
	if(issueDd.length>8){ gf_AlertMsg("발행일 : YYYYMMDD 형식입니다."); return false; }
}

function f_Close(){
}

function f_Save(){
	//최초 자료문서 저장
	if(v_regNo ==""){
		if(f_CheckValidationForSave() == false){
			return;
		}

		var isumry = $("#sumry").val();
		for(var i = 0; i < isumry.length; i++){
			isumry = isumry.replace("\n", "##");
		}

		var ikeywd = $("#keywd").val();
		for(var i = 0; i < ikeywd.length; i++){
			ikeywd = ikeywd.replace("\n", "##");
		}

		v_cls     = 'GR';
		v_subject = $("#subject").val();
		v_sumry   = isumry;
		v_keywd   = ikeywd;
		v_issueTeam = $("#issueTeam").val();
		v_issueEr = $("#issueEr").val();
		v_issueMgr = $("#issueMgr").val();
		v_issueDd = $("#issueDd").val();
		v_edtPerchrg = $("#edtPerchrg").val();
		v_distrCls = $("#distrCls").val();
		v_prfrd = $("#prfrd").val();
		v_issueCpys = $("#issueCpys").val();
		fstRegUserId = gv_userId;
		fnlEditUserId = gv_userId;
		v_fileAtchId  = v_fileAtchId;
		v_imgAtchFileId = v_imgAtchFileId;

		var params = {
				regNo      : v_regNo,
				cls        : v_cls,
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
				docSts     : v_docSts,
				fstRegUserId  : fstRegUserId,
				fnlEditUserId : fnlEditUserId,
				fileAtchId    : v_fileAtchId,
				imgAtchFileId : v_imgAtchFileId
				};

		if(v_docSts == 'D18'){
			gf_Transaction("DRAFT_TECH_DATA", "/tech/publication/insertTechPubDatComp.xpl", params, {}, "f_Callback", true);
		}else{
			gf_Transaction("DRAFT_TECH_DATA", "/tech/publication/insertTechPubDat.xpl", params, {}, "f_Callback", true);
		}
	}else{
		//편집 후 저장
		if(f_CheckValidationForSave() == false){
			return;
		}

		var isumry = $("#sumry").val();
		for(var i = 0; i < isumry.length; i++){
			isumry = isumry.replace("\n", "##");
		}

		var ikeywd = $("#keywd").val();
		for(var i = 0; i < ikeywd.length; i++){
			ikeywd = ikeywd.replace("\n", "##");
		}

		v_cls     = 'GR';
		v_subject = $("#subject").val();
		v_sumry   = isumry;
		v_keywd   = ikeywd;
		v_issueTeam = $("#issueTeam").val();
		v_issueEr = $("#issueEr").val();
		v_issueMgr = $("#issueMgr").val();
		v_issueDd = $("#issueDd").val();
		v_edtPerchrg = $("#edtPerchrg").val();
		v_distrCls = $("#distrCls").val();
		v_prfrd = $("#prfrd").val();
		v_issueCpys = $("#issueCpys").val();
		fstRegUserId = gv_userId;
		fnlEditUserId = gv_userId;
		v_fileAtchId  = v_fileAtchId;
		v_imgAtchFileId = v_imgAtchFileId;

		var params = {
				regNo      : v_regNo,
				cls        : v_cls,
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
				docSts     : v_docSts,
				fstRegUserId  : fstRegUserId,
				fnlEditUserId : fnlEditUserId,
				fileAtchId    : v_fileAtchId,
				imgAtchFileId : v_imgAtchFileId
		};

		if(v_docSts == 'D18' && v_issueRegNo == ""){
			gf_Transaction("SAVE_TECH_DATA", "/tech/publication/updateTechPubDatComp.xpl", params, {}, "f_Callback", true);
		}else{
			gf_Transaction("SAVE_TECH_DATA", "/tech/publication/updateTechPubDat.xpl", params, {}, "f_Callback", true);
		}

	}
}

function f_SetBtn() {

	if (v_docSts == "D16" || v_docSts == "" || v_docSts == "임시저장") {
		$("#btnAppn").show();
	}

	if (gv_AuthList.auth[0].code.match("RO_CMAS_CT_ADM") == "RO_CMAS_CT_ADM"
			|| gv_AuthList.auth[0].code.match("RO_CMAS_CT_BOOK") == "RO_CMAS_CT_BOOK") {
		//	$("#btnComplete").show();
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
			v_imgAtchFileId = fileAtchIdTmp;
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

	f_onImageUpload();
	f_Save();
}

/**
* @function
* @param  obj<htmlObject> input type file 개체
* @see   input type file 개체의 onchange에 걸려 있는 이벤트 함수
* @return N/A
* @author 변형구
* @since 2013-03-20
* @version 1.0
*/
function f_changeImg(obj) {

	 var tmpExtension = (obj.value).split('.');

	    for ( var i in tmpExtension ) {
	    	if( i == (tmpExtension.length)-1){
	    		var extension = tmpExtension[i].toLowerCase();

	    	  	if (extension != 'jpg' && extension != 'bmp' && extension != 'gif'){
	    	  		alert('확장자가 gif, jpg, bmp의 그림파일만 첨부 가능합니다.');
	    	  		break;
	    	  	}else{
	    	  		document.getElementById('fileName').value = obj.value
	    	  	    if (obj.files.length == 0) return;
	    	  	    imgFile = obj.files[0];
	    	  	}
	    	 }
	      }

}

/**
* @function
* @param  N/A
* @see    파일이 업로드 함수
* @return N/A
* @author 변형구
* @since 2013-03-20
* @version 1.0
*/
function f_onImageUpload() {

    var formData = new FormData();
    formData.append("file", imgFile);
    formData.append("fileAtchId", v_imgAtchFileId);
    // Since this is the file only, we send it to a specific location
    var action = gContextPath + '/co/common/file/uploadWebFile.xpl';

    formData.append("policy", "default");
    f_sendXHRequest(formData, action);
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
