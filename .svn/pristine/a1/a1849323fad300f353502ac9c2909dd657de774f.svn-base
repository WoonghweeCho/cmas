
/**
* @class 화면에서 사용할 전역변수를 아래에 선엄함
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
var v_BaseWindowHeight = 700;

var params = {};
var v_loclCd;

var	v_regNo   = "";
var	v_subject = "";
var	v_actorNm = "";
var	v_spv = "";
var	v_playTime = "";
var	v_dbng = "";
var	v_sbtl = "";
var	v_dvdCls = "";
var	v_cls = "";
var	v_cont = "";
var	v_mkco = "";
var	v_recTp = "";
var	v_scrn = "";
var	v_docSts = "";
var fstRegUserId;
var fnlEditUserId;


var ds_TechDatList = new DataSet();	// 상세정보

/**
 * 공통 코드처리 DataSet
 */
var ds_Cls = new DataSet();		// 분류 공통코드


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
	// 저장 버튼
	$("#btnSave").click(function(){
		//최소 자료문서 저장
		if(v_regNo ==""){
			if(f_CheckValidationForSave() == false){
				return;
			}

			var icont = $("#cont").val();
			for(var i = 0; i < icont.length; i++){
				icont = icont.replace("\n", "##");
			}

			v_regNo  = $("#regNo").val();
			v_subject = $("#subject").val();
			v_actorNm = $("#actorNm").val();
			v_spv = $("#spv").val();
			v_playTime = $("#playTime").val();
			v_dbng = $("#dbng").val();
			v_sbtl = $("#sbtl").val();
			v_dvdCls = $("select[name='dvdCls'] option:selected").val();
			v_cls = "DV";
			v_mkco = $("#mkco").val();
			v_recTp = $("#recTp").val();
			v_scrn = $("#scrn").val();
			v_cont = icont;
			v_docSts = "D30";
			fstRegUserId = gv_userId;
			fnlEditUserId = gv_userId;

			var params = {
					regNo     : v_regNo,
					subject   : v_subject,
					actorNm   : v_actorNm,
					spv       : v_spv,
					playTime  : v_playTime,
					dbng      : v_dbng,
					sbtl      : v_sbtl,
					dvdCls    : v_dvdCls,
					cls       : v_cls,
					mkco      : v_mkco,
					recTp     : v_recTp,
					scrn      : v_scrn,
					cont      : v_cont,
					docSts    : v_docSts,
					fstRegUserId : fstRegUserId,
					fnlEditUserId : fnlEditUserId
			};

			gf_Transaction("DRAFT_TECH_DATA", "/tech/techAppn/insertTechDvdDat.xpl", params, {}, "f_Callback", true);
		}else{
			//편집 후 저장
			if(f_CheckValidationForSave() == false){
				return;
			}

			var icont = $("#cont").val();
			for(var i = 0; i < icont.length; i++){
				icont = icont.replace("\n", "##");
			}

			v_regNo  = datas.regNo;
			v_subject = $("#subject").val();
			v_actorNm = $("#actorNm").val();
			v_spv = $("#spv").val();
			v_playTime = $("#playTime").val();
			v_dbng = $("#dbng").val();
			v_sbtl = $("#sbtl").val();
			v_dvdCls = $("select[name='dvdCls'] option:selected").val();
			v_cls = "DV";
			v_mkco = $("#mkco").val();
			v_recTp = $("#recTp").val();
			v_scrn = $("#scrn").val();
			v_cont = icont;
			fnlEditUserId = gv_userId;

			var params = {
					regNo     : v_regNo,
					subject   : v_subject,
					actorNm   : v_actorNm,
					spv       : v_spv,
					playTime  : v_playTime,
					dbng      : v_dbng,
					sbtl      : v_sbtl,
					dvdCls    : v_dvdCls,
					cls       : v_cls,
					mkco      : v_mkco,
					recTp     : v_recTp,
					scrn      : v_scrn,
					cont      : v_cont,
					fnlEditUserId : fnlEditUserId
			};

			gf_Transaction("SAVE_TECH_DATA", "/tech/techAppn/updateTechDvdDat.xpl", params, {}, "f_Callback", true);
		}

	});

	 //닫기 버튼
	$("#btnCancle").click(function(){
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
	if(v_regNo !=""){
		$("#doregNo").text(datas.cls+datas.regNo);
		$("#subject").val(datas.subject);
		$("#actorNm").val(datas.actorNm);
		$("#spv").val(datas.spv);
		$("#playTime").val(datas.playTime);
		$("#dbng").val(datas.dbng);
		$("#sbtl").val(datas.sbtl);

		v_dvdCls = datas.dvdCls;
		$('[name=dvdCls]').val(v_dvdCls);

		$("#mkco").val(datas.mkco);
		$("#recTp").val(datas.recTp);
	    $("#scrn").val(datas.scrn);

		for(var i = 0; i < datas.cont.length; i++){
			datas.cont = datas.cont.replace("##", "\n");
		}

		$("#cont").val(datas.cont);
	}

}



function f_CheckValidationForSave(){

	var subject = gf_GetValue($("input[name='subject']").val());	//제목
	var issuePl= gf_GetValue($("select[name='dvdCls'] option:selected").val());	//분류(장르)

	if(gf_IsNull(subject)){ gf_AlertMsg("제목(타이틀)은 필수입니다."); return false; }
	if(gf_IsNull(issuePl)){ gf_AlertMsg("분류(장르)는 필수입니다."); return false; }

//	if(gf_IsNull(datas.title)) { gf_AlertMsg("제목은 필수 항목입니다."); return; }

}

function f_Close(){
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
	  	case "DELETE_TECH_DATA" :  //삭제
	  		self.close();
			break;
	  	default :
	  		break;
	  }
}

