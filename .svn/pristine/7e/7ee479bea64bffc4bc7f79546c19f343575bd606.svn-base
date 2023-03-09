
/**
* @class 통합결재 시스템에서 전역으로 사용될 메세지, 코드, 메뉴 클래스
* @constructor
* @see   .msg, .code, .menu 세가지의 시스템 리소스를 가진다.
* @author 변형구
* @since 2013-04-04
* @version 1.0
*/
var cmas_comm = function() {

};


/**
* @see    메세지 데이터를 Locale 별로 가지는 변수
* @author 변형구
* @since 2013-04-04
* @version 1.0
*/
cmas_comm.prototype.msg 	= {};
/**
* @see    공통코드 데이터를 Locale 별로 가지는 변수
* @author 변형구
* @since 2013-04-04
* @version 1.0
*/
cmas_comm.prototype.code 	= {};
/**
* @see    권한별 메뉴 및 메뉴별 버튼권한등의 데이터 가지는 변수
* @author 변형구
* @since 2013-04-04
* @version 1.0
*/
cmas_comm.prototype.menu 			= {};

cmas_comm.prototype.auth 			= {};

cmas_comm.prototype.baroNetUrl  	= "";

cmas_comm.prototype.baroConUrl  	= "";

cmas_comm.prototype.a06Url      	= "";

cmas_comm.prototype.h01xUrl      	= "";

cmas_comm.prototype.dominoUrl   	= "";

cmas_comm.prototype.baroNetDomain  	= "";

cmas_comm.prototype.baroConDomain  	= "";

cmas_comm.prototype.a06Domain      	= "";

cmas_comm.prototype.drmdownUrl     	= "";

cmas_comm.prototype.cmasUrl     	= "";

cmas_comm.prototype.userId         	= "";

cmas_comm.prototype.userNm			= "";

cmas_comm.prototype.orgCd			= "";



// 전역으로 사용될 cmas_common 클래스 생성
if(typeof(gv_cmasComm) == "undefined")
	gv_cmasComm = new cmas_comm();

var gf_openSign;

var gf_ChildWindow = new Array();

(function(){
	f_setGlobal();
})();

function f_setGlobal() {
	try {
		var opner = opener;
		if (opner && !gv_IsProxy) {
			while ( true ) {
				if(opner.gv_IsProxy)
					break;

				if ( typeof(opner.opener) != "undefined" && typeof(opner.opener) != "unknown" && opner.opener != null) {
					opner = opner.opener;
				}
				else {
					break;
				}
			}
		}

		var valuePath;

		if(gv_IsProxy){
			valuePath = window;
		}else if ( !opner ) {
			valuePath = window.top == window ? window : top;
		}
		else {
				valuePath = opner.window.top == opner.window ? opner.window : opner.top;
		}

		//valuePath.f_onPopupPage();

		gf_openSign = valuePath.gf_BaroconOpenSign;

		cmas_comm.prototype.msg.en_US 		= valuePath.gv_Message_en_US;
		cmas_comm.prototype.msg.ko_KR 		= valuePath.gv_Message_ko_KR;
		cmas_comm.prototype.code.en_US 		= valuePath.gv_CommCd_en_US;
		cmas_comm.prototype.code.ko_KR 		= valuePath.gv_CommCd_ko_KR;
		cmas_comm.prototype.menu.PRIV_MENU 	= valuePath.gv_MenuPrive;
		cmas_comm.prototype.auth.AUTH_LIST  = valuePath.gv_AuthList;

		cmas_comm.prototype.baroNetUrl  	= valuePath.gv_BaroNetUrl;
		cmas_comm.prototype.baroConUrl  	= valuePath.gv_BaroConUrl;
		cmas_comm.prototype.a06Url      	= valuePath.gv_A06Url;
		cmas_comm.prototype.h01xUrl      	= valuePath.gv_H01xUrl;
		cmas_comm.prototype.dominoUrl   	= valuePath.gv_DominoUrl;
		cmas_comm.prototype.baroNetDomain  	= valuePath.gv_BaroNetDomain;
		cmas_comm.prototype.baroConDomain  	= valuePath.gv_BaroConDomain;
		cmas_comm.prototype.a06Domain      	= valuePath.gv_A06Domain;
		cmas_comm.prototype.drmdownUrl     	= valuePath.gv_DrmDownUrl;
		cmas_comm.prototype.cmasUrl    		= valuePath.gv_cmasUrl;

		cmas_comm.prototype.userId         	= valuePath.gv_userId;
		cmas_comm.prototype.userNm			= valuePath.gv_userNm;
		cmas_comm.prototype.orgCd			= valuePath.gv_orgCd;

		jquery_lang_js.prototype.lang.en_US = valuePath.gv_mlang_en_US;
		jquery_lang_js.prototype.lang.ko_KR = valuePath.gv_mlang_ko_KR;

	} catch (e) {
		if (console && console.log) console.log(e);
	}
}

var gv_DocStsCd = [
{ "code":"D02","value":"결재중","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"D03","value":"결재완료","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"D04","value":"반려","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
//{ "code":"D06","value":"삭제","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
//{ "code":"D07","value":"결재요청취소","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
//{ "code":"D08","value":"상신완료","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
//{ "code":"D09","value":"이단결재대기","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"D54","value":"SEC반려","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"D64","value":"총무팀반려","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"D84","value":"경영관리팀반려","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
//{ "code":"D05","value":"회수","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
//{ "code":"D11","value":"협의요청","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"","refCd2":"","refCd3":"","refCd4":"" },
//{ "code":"D12","value":"협의완료","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"D16","value":"임시저장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"D50","value":"SEC검토중","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"D60","value":"총무팀검토중","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"D61","value":"반납확인중","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"","refCd2":"","refCd3":"","refCd4":"" },
//{ "code":"D70","value":"경영관리팀검토중","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"D80","value":"경영관리팀검토중","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"D99","value":"상신실패","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"","refCd2":"","refCd3":"","refCd4":"" }
];

var gv_AdjstStsCd = [
{ "code":"A02","value":"결재중","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"A03","value":"결재완료","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"A04","value":"반려","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"A05","value":"회수","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"A11","value":"협의요청","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"A16","value":"임시저장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"A50","value":"검토중","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"A99","value":"상신실패","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"A00","value":"작성필요","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"","refCd2":"","refCd3":"","refCd4":"" }
];


var gv_AcctStsCd = [
{ "code":"P","value":"회계승인","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"완료","value":"회계승인","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"R","value":"회계반려","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"반려","value":"회계반려","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" }
];


var gv_hqRpswrkCd = [
{ "code":"그룹회장","value":"그룹회장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"부문부회장","value":"부문부회장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"대표이사사장","value":"대표이사사장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"총괄사장","value":"총괄사장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"총괄부사장","value":"총괄부사장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"사장","value":"사장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"업무총괄","value":"업무총괄","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"사업총괄","value":"사업총괄","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"수석부사장","value":"수석부사장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"부문장","value":"부문장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"원장","value":"원장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"해외영업총괄","value":"해외영업총괄","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"본부장","value":"본부장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"실장","value":"실장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"CFO","value":"CFO","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
];

var gv_ChiefCd = [
{ "code":"법인대표","value":"법인대표","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"그룹회장","value":"그룹회장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"부문부회장","value":"부문부회장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"대표이사사장","value":"대표이사사장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"총괄사장","value":"총괄사장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"총괄부사장","value":"총괄부사장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"사장","value":"사장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"부문장","value":"부문장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"수석부사장","value":"수석부사장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"해외영업총괄","value":"해외영업총괄","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"본부장","value":"본부장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"원장","value":"원장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"부원장","value":"부원장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"실장","value":"실장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"단장","value":"단장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"주재임원","value":"주재임원","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"PM","value":"PM","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"PM(시공)","value":"PM(시공)","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"부서장","value":"부서장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"현장소장","value":"현장소장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"PCM","value":"PCM","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"PPM","value":"PPM","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"EM","value":"EM","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"기전부문소장","value":"기전부문소장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"부문소장","value":"부문소장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"시공사업단장","value":"시공사업단장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"운영사업소장","value":"운영사업소장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"지사장","value":"지사장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"공장장","value":"공장장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"팀장","value":"팀장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"PJ팀장","value":"PJ팀장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"대대장","value":"대대장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"과책임자","value":"과책임자","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"현장팀장","value":"현장팀장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" }, // 여기까지 보직코드
{ "code":"회장","value":"회장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"부회장","value":"부회장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"부사장","value":"부사장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"전무","value":"전무","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"상무","value":"상무","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"이사","value":"이사","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"고문","value":"고문","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"상담역","value":"상담역","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"관리역","value":"관리역","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"자문역","value":"자문역","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"상무보대우","value":"상무보대우","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"수석전문위원","value":"수석전문위원","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"수석연구위원","value":"수석연구위원","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"전문위원","value":"전문위원","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"연구위원","value":"연구위원","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"본부장직무대리","value":"본부장직무대리","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"원장직무대리","value":"원장직무대리","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"실장직무대리","value":"실장직무대리","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" }// 여기까지 보직코드에 포함된 거 제외한 호칭코드
];

var gv_IdDocStsCd = [
{ "code":"D02","value":"결재중","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"D03","value":"결재완료","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"D04","value":"반려","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"D06","value":"삭제","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
//{ "code":"D07","value":"결재요청취소","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
//{ "code":"D08","value":"상신완료","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
//{ "code":"D09","value":"이단결재대기","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
//{ "code":"D54","value":"SEC반려","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
//{ "code":"D64","value":"GHR반려","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"D05","value":"회수","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"D11","value":"협의요청","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"D12","value":"협의완료","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
{ "code":"D16","value":"임시저장","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"","refCd2":"","refCd3":"","refCd4":"" }
//{ "code":"D50","value":"SEC검토중","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"","refCd2":"","refCd3":"","refCd4":"" },
//{ "code":"D60","value":"GHR검토중","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"","refCd2":"","refCd3":"","refCd4":"" },
//{ "code":"D61","value":"반납확인중","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"","refCd2":"","refCd3":"","refCd4":"" },
//{ "code":"D70","value":"경영관리팀검토중","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"","refCd2":"","refCd3":"","refCd4":"" },
//{ "code":"D99","value":"상신실패","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"","refCd2":"","refCd3":"","refCd4":"" }
];

//비자 진행상황 코드
var gv_visaPrgrCd = [
	{ "code":"S","value":"접수완료","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
	{ "code":"I","value":"발급완료","hgrCommGrpCd":"","hgrCommCd":"","refCd1":"Y","refCd2":"","refCd3":"","refCd4":"" },
];
