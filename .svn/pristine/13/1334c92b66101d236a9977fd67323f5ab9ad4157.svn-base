
/**
* @class 화면에서 사용할 전역변수를 아래에 선엄함
* @version 1.0
*/
var v_BaseWindowHeight = 700;

var params = {};
var v_loclCd;

var	v_flr      = "";
var	v_orgNm    = "";
var	v_dtlInfo  = "";
var v_sts      = "";
var v_bcont    = "";
var v_tcont    = "";
var v_admin    = "";

var fstRegUserId;
var fnlEditUserId;

var ds_DatList = new DataSet();	// 상세정보


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
* * @version 1.0
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
* @version 1.0
*/
function cf_InitParam()
{
	v_loclCd = gf_GetCookie("loclCd");		// 로케일 20140724
	v_CallbackFunc = gf_IsNull(datas.callbackFunc) ? "" : datas.callbackFunc;
	v_sts = gf_IsNull(datas.sts) ? "" : datas.sts;
	if(gv_AuthList.auth[0].code.match("RO_CMAS_Fl_ADM") == "RO_CMAS_Fl_ADM"){
		v_admin = 'Y';
	}else{
		v_admin = 'N';
	}
}

/**
* @class 컴포넌트 생성
* @version 1.0
*/
function cf_SetComponents()
{

}

/**
* @class Element, Compoment Event 처리
* @version 1.0
*/
function cf_SetEventListener()
{
	// 저장 버튼
	$("#btnSave").click(function(){

			//문서 저장

			if(f_CheckValidationForSave() == false){
				return;
			}

			var bcont = $("#bonbu").val();
			for(var i = 0; i < bcont.length; i++){
				bcont = bcont.replace("\n", "<br>&nbsp;");
			}

			var icont = $("#team").val();
			for(var i = 0; i < icont.length; i++){
				icont = icont.replace("\n", "<br>&nbsp;");
			}

			v_flr     = $("#floor").val()+"0";
			v_orgNm   = bcont;
			v_dtlInfo = icont;
			//fstRegUserId = gv_userId;
			//fnlEditUserId = gv_userId;

			var params = {
					flr        : v_flr,
					orgNm      : v_orgNm,
					dtlInfo    : v_dtlInfo
					//fstRegUserId : fstRegUserId,
					//fnlEditUserId : fnlEditUserId
			};

			if(v_sts == "편집"){
				gf_Transaction("SAVE_DATA", "/info/updateFloor.xpl", params, {}, "f_Callback", true);
			}else{
				gf_Transaction("SAVE_DATA", "/info/insertFloor.xpl", params, {}, "f_Callback", true);
			}

		});

	 //닫기 버튼
	$("#btnCancle").click(function(){
		self.close();
	});

	 //삭제 버튼
	$("#btnDelete").click(function(){
		v_flr     = $("#floor").val()+"0";

		var params = {
				flr : v_flr
		};

		gf_Transaction("DELETE_DATA", "/info/deleteFloor.xpl", params, {}, "f_Callback", true);
	});

}

/**
* @class Form Onload DataSet Binding 처리
* @version 1.0
*/
function cf_SetBinding()
{

}

/**
* @class Form Elemenet, Data 초기화
* @version 1.0
*/
function cf_InitForm()
{

	f_SetBtn();

	if(v_sts == "편집"){

		$("#floor").val(datas.flr);

		// 본부
		for(var i = 0; i < datas.orgNm.length; i++){
			datas.orgNm = datas.orgNm.replace("<br>&nbsp;", "\n");
		}
		$("#bonbu").text(datas.orgNm);

		//팀
		for(var i = 0; i < datas.dtlInfo.length; i++){
			datas.dtlInfo = datas.dtlInfo.replace("<br>&nbsp;", "\n");
		}

		$("#team").text(datas.dtlInfo);
	}
}


function f_SetBtn(){

	if(v_admin == "Y") {
		$("#btnSave").show();
		$("#btnDelete").show();
	}
}


function f_CheckValidationForSave(){

	var floor = gf_GetValue($("input[name='floor']").val());	//본사층

	if(gf_IsNull(floor)){ gf_AlertMsg("본사층은 필수입니다."); return false; }

}


/**
* @class Transaction 처리 후 수행되는 Callback 함수
* @version 1.0
*/
function f_Callback(strSvcId, obj, resultData){
	  if (!gf_ChkTransaction(strSvcId, resultData, true )) {
		  return;
	  }
	  switch(strSvcId) {
	  	case "SAVE_DATA" :  //저장
  	  		self.close();
			break;
	  	case "DELETE_DATA" :  //삭제
	  		self.close();
			break;
	  	default :
	  		break;
	  }
}

