/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/

var v_DocData;
var v_SapData;
var v_UserTotal;

var v_CallCount = 0;
var v_SucessCount = 0;

//팝업이 닫힐때 리턴값을 전달할 callback 함수
var v_CallbackFunc;



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

	v_DocData = gf_IsNull(datas.docData) ? ""          : datas.docData;

	v_CallbackFunc = gf_IsNull(datas.callbackFunc) ? ""          : datas.callbackFunc;
}


/**
* @class Form Onload 시 컴포넌트 초기화
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_SetComponents()
{

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

	$("#drafteBtn").click(function(){

		var user;
		var paramArr = new Array();

		if(f_docPatterChk() == false) return;

		if($("#user0 input[name='user0tourC']:checked").val() == undefined){
			alert("여행사를 선택하여 주세요.");
			return;
		}

  		if(v_SapData.SapItab == undefined){
  			alert('입력이 불가능합니다.');
  			return;
  		}

  		if(v_DocData.docNo == "" || v_DocData.docNo == undefined){
  			alert('문서번호가 존재하지 않습니다.');
  			return;
  		}


  		// 결과가 하나일 경우 오브젝트로 전달
  		if(v_SapData.SapItab.constructor == Object){
//  			alert("오브젝트임");
  			user = new Array();
  			user[0] = v_SapData.SapItab;
  		}else if(v_SapData.SapItab.constructor == Array){
  			user = v_SapData.SapItab;

  		}else{
  			alert('항공료 입력 데이터가 올바르지 않습니다.');
  			return;
  		}

  		v_CallCount = 0;
  		v_SucessCount = 0;

  		// 출장자 수만큼 데이터를 전송한다.
  		for(var i = 0; i < user.length; i++){
  			var tagId = "user" + i;
  			$("#" + tagId).show();

  			var IMode = "U"; // UPDATE MODE(발권취소일에 값있으면:C, else U)
  			var IBdocno = v_DocData.docNo; // 문서번호
  			var IRefkey = v_DocData.refNo; // 전표


  			var IEmpnm = $("#" + tagId + " span[name='userEnm']").text();
  			var ISerno = "1"; // (공백)
  			var ITicdt = $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", $("#" + tagId + " input[name='issDate']").val())); // 발권일
  			var ticNo = $("#" + tagId + " input[name='issNo1']").val() +  " " + $("#" + tagId + " input[name='issNo2']").val();
  			var ITicno = ticNo; // 발권번호
  			var IAirfare = removeComma($("#" + tagId + " input[name='fare']").val()); // 항공료
  			var IInsure = removeComma($("#" + tagId + " input[name='warIns']").val()); // 전쟁보험료
  			var IAirptFee1 = removeComma($("#" + tagId + " input[name='airPort1']").val()); // 공항이용료1
  			var IAirptFee2 = removeComma($("#" + tagId + " input[name='airPort2']").val()); // 공항이용료2
  			var IDcamt = removeComma($("#" + tagId + " input[name='disC']").val()); // 할인금액
  			var IRoute = $("#" + tagId + " input[name='jourN']").val(); // 여정
  			if(IRoute == ""){
  				gf_AlertMsg("여정을 입력하세요.");
  				return;
  			}
  			var payWay = $("#" + tagId + " select[name='payWay']").val();
  			if(payWay == "P"){
  				payWay = "1";
  			}else if(payWay == "C"){
  				payWay = "2";
  			}else{
  				gf_AlertMsg("현금 / 카드 구분을 선택해주세요.");
  				return;
  			}
  			var IPayMethod = payWay; // 지불방법 1-현금, 2-카드
  			var ICancelDate = $("#" + tagId + " input[name='cancDate']").val(); // 발권취소일
  			if(ICancelDate != ""){
  				IMode = "C"; // UPDATE MODE(발권취소일에 값있으면:C, else U)
  				ICancelDate = $.datepicker.formatDate("yymmdd", $.datepicker.parseDate("yy-mm-dd", ICancelDate));
  			}
  			var IUsnam = $("#user0 input[name='user0tourC']:checked").val(); // 여행사 담당자 사번
  			 if(IUsnam == "H"){
  				IUsnam = "ZA47613";
//  				C1DFLJ6 남주희 - 현대드림투어
// 					ZA35689 하지선	- 현대드림투어, (2021.03.09 사번변경 : ZA35689 -> ZA47613)
  			}else if(IUsnam == "N"){
  				IUsnam = "ZA47614";
//  				ZA41509 신희철 - 하나투어, (2021.03.09 사번변경 : ZA41509 -> ZA47614)
  			}else{
  				gf_AlertMsg("여행사를 선택하세요.");
  				return;
  			}
  			var ITicketFee = removeComma($("#" + tagId + " input[name='issComm']").val()); // 발권수수료
  			var ICash = removeComma($("#" + tagId + " input[name='amtDiv']").val()); // 항공분할입력분

  			if(ICash == 0) ICash = "";


  			var params = {
  				IMode : IMode,
  				IBdocno : IBdocno,
  				IRefkey : IRefkey,
  				IEmpnm : IEmpnm,
  				ISerno : ISerno,
  				ITicno : ITicno,
  				ITicdt : ITicdt,
  				IAirfare : IAirfare,
  				IInsure : IInsure,
  				IAirptFee1 : IAirptFee1,
  				IAirptFee2 : IAirptFee2,
  				IDcamt : IDcamt,
  				IRoute : IRoute,
  				IPayMethod : IPayMethod,
  				ICancelDate : ICancelDate,
  				IUsnam : IUsnam,
  				ITicketFee : ITicketFee,
  				ICash : ICash
  			};

  			paramArr.push(params);

//  			gf_Transaction("SELECT_UPDATE_AIR_FARE", "/trip/eai/updateAirFareN.xpl", params, {}, "f_Callback", true);

  			// 호출 할때 마다 CallCount 를 늘린다.
  			v_CallCount++;

  		}

  		for(var o = 0; o < paramArr.length; o++){
  			gf_Transaction("SELECT_UPDATE_AIR_FARE", "/trip/eai/updateAirFareN.xpl", paramArr[o], {}, "f_Callback", true);

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

	var ifParam = JSON.parse(v_DocData.ifParam);

	var fDate = ifParam.startDate;
	var tDate = ifParam.endDate;

	var fDate2 = $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", ifParam.startDate));
	var tDate2 = $.datepicker.formatDate("yy-mm-dd", $.datepicker.parseDate("yymmdd", ifParam.endDate));

	$("#startDate").text(fDate2);
	$("#endDate").text(tDate2);

	var startDate = $.datepicker.parseDate("yymmdd" , fDate);
	var endDate = $.datepicker.parseDate("yymmdd" , tDate);

	var betDay = (endDate - startDate)/1000/60/60/24;

	//숙박비, 일당비 계산
	var tripDate = betDay+1; // 기준일 저장

	$("#tripDate").text(tripDate);

	$("#docNo").text(v_DocData.docNo);
	$("#refNo").text(v_DocData.refNo);

//	$("#userEnm").text(v_DocData.userEnm);
//	$("#sGrade").text(v_DocData.seatGrade);
//	$("#fAirFare").text(v_DocData.afare);


	var refNo   = v_DocData.refNo;
	var IBdocno = v_DocData.docNo; // 문서번호

	var params = {
		Refkey   : refNo,
		Fdate    : fDate,
		Tdate    : tDate,
		Notesdoc : IBdocno
	};

	gf_Transaction("SELECT_AIRFARE_INVOICE", "/trip/eai/getAirfareInvoiceDay.xpl", params, {}, "f_Callback", true);


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
	  	case "SELECT_UPDATE_AIR_FARE" :
	  		if(resultData.output1[0].ErrMsg != undefined){
	  			v_SucessCount = 0;
	  			alert(resultData.output1[0].ErrMsg);
	  		}else{

	  			v_SucessCount++;

	  			if(v_SucessCount == v_CallCount){

	  				var params = {
	  					docNo : v_DocData.docNo,
	  					inchrgAir : $("#user0 input[name='user0tourC']:checked").val()
	  				};

	  				gf_Transaction("SAVE_AIR_CONF", "/trip/outerTrip/updateOuterTripAirConf.xpl", params, {}, "f_Callback", true);


//	  				alert("정상적으로 처리되었습니다.");
//	  				self.close();
	  			}
	  		}

//	  		self.close();
	  		break;
	  	case "SAVE_AIR_CONF" :
	  		// 문서가 성공적으로 처리되면 문서 리스트를 다시 조회하여 갱신하여 준다.
	  		if ( !gf_IsNull(v_CallbackFunc) ) {
	  			var openCallbackFunc = "opener."+v_CallbackFunc;
	  	    	eval(openCallbackFunc + "();");
	  	    }

	  		self.close();
	  		break;
	  	case "SELECT_AIRFARE_INVOICE" :
	  		v_SapData = resultData.output1[0];

	  		var user;

	  		if(v_SapData.SapItab == undefined){
	  			alert('항공료 데이터를 가져올 수 없습니다.');
	  			return;
	  		}


	  		// 결과가 하나일 경우 오브젝트로 전달
	  		if(v_SapData.SapItab.constructor == Object){
//	  			alert("오브젝트임");
	  			user = new Array();
	  			user[0] = v_SapData.SapItab;

	  			$("#orderDate").text(user[0].Bldat);
	  			$("#ordNo").text(user[0].Belnr);
	  		}else if(v_SapData.SapItab.constructor == Array){
	  			user = v_SapData.SapItab;

	  			$("#orderDate").text(user[0].Bldat);
	  			$("#ordNo").text(user[0].Belnr);
	  		}

	  		// 출장자 수만큼 데이터를 출력한다.
	  		for(var i = 0; i < user.length; i++){
	  			var tagId = "user" + i;
	  			$("#" + tagId).show();

	  			// 영문이름
	  			$("#" + tagId + " span[name='userEnm']").text(user[i].Empnm);
	  			// 좌석등급

	  			var sType = user[i].Seattype;
//	  			E -> Economy, B -> Business, F -> First
	  			if(sType == "E"){
	  				sType = "Economy";
	  			}else if(sType == "B"){
	  				sType = "Business";
	  			}else if(sType == "F"){
	  				sType = "First";
	  			}

	  			$("#" + tagId + " span[name='sGrade']").text(sType);
	  			// 최초항공료
	  			var Initfare = parseFloat(user[i].Initfare) * 100;
	  			$("#" + tagId + " span[name='fAirFare']").text(gf_AmtFormat(Initfare.toFixed(0)));
	  			// 발권일
	  			$("#" + tagId + " input[name='issDate']").val(user[i].Ticdt);
	  			// 발권번호
	  			var ticNo = user[i].Ticno;
	  			if(ticNo != undefined){
	  				var ticNo =ticNo.split(" ");
	  				$("#" + tagId + " input[name='issNo1']").val(ticNo[0]);
		  			$("#" + tagId + " input[name='issNo2']").val(ticNo[1]);
	  			}

	  			// 발권수수료
	  			var TicketFee = parseFloat(user[i].TicketFee) * 100;
	  			$("#" + tagId + " input[name='issComm']").val(gf_AmtFormat(TicketFee.toFixed(0)));
	  			// FARE
	  			var Airfare = parseFloat(user[i].Airfare) * 100;
	  			$("#" + tagId + " input[name='fare']").val(gf_AmtFormat(Airfare.toFixed(0)));
	  			// 전쟁보험료
	  			var Insure = parseFloat(user[i].Insure) * 100;
	  			$("#" + tagId + " input[name='warIns']").val(gf_AmtFormat(Insure.toFixed(0)));
	  			// 공항이욜료
	  			var AirptFee1 = parseFloat(user[i].AirptFee1) * 100;
	  			$("#" + tagId + " input[name='airPort1']").val(gf_AmtFormat(AirptFee1.toFixed(0)));
	  			// 현지공항세
	  			var AirptFee2 = parseFloat(user[i].AirptFee2) * 100;
	  			$("#" + tagId + " input[name='airPort2']").val(gf_AmtFormat(AirptFee2.toFixed(0)));
	  			// D/C
	  			var Dcamt = parseFloat(user[i].Dcamt) * 100;
	  			$("#" + tagId + " input[name='disC']").val(gf_AmtFormat(Dcamt.toFixed(0)));
	  			// 현금분할입력
	  			var Cashamt = parseFloat(user[i].Cashamt) * 100;
	  			$("#" + tagId + " input[name='amtDiv']").val(gf_AmtFormat(Cashamt.toFixed(0)));
	  			// 지불방법
	  			// 지불방법 1-현금 P, 2-카드 C
	  			var payM;
	  			if(user[i].PayMethod == "1"){
	  				payM = "P";
	  			}else if(user[i].PayMethod == "2"){
	  				payM = "C";
	  			}
	  			$("#" + tagId + " select[name='payWay']").val(payM);
	  			// 발권취소일
	  			$("#" + tagId + " input[name='cancDate']").val(user[i].CancelDate);
	  			// 일련번호
	  			// 확정항공료
	  			var Confirmamt = parseFloat(user[i].Confirmamt) * 100;
	  			$("#" + tagId + " span[name='confAmt']").text(gf_AmtFormat(Confirmamt.toFixed(0)));
	  			// 여정
	  			$("#" + tagId + " input[name='jourN']").val(user[i].Route);

	  			if(i == 0){
	  				// 여행사 (1:동서여행사,2:현대드림투어 3:주원항공 4:하나투어)
		  			var comp = user[i].Jcompany;
		  			if(user[i].Jcompany == "1"){
		  				$("input[name='" + tagId + "tourC'][value='D']").attr("checked", "true");
		  			}else if(user[i].Jcompany == "2"){
		  				$("input[name='" + tagId + "tourC'][value='H']").attr("checked", "true");
		  			}
		  			if(user[i].Jcompany == "3"){
		  				$("input[name='" + tagId + "tourC'][value='J']").attr("checked", "true");
		  			}else if(user[i].Jcompany == "4"){
		  				$("input[name='" + tagId + "tourC'][value='N']").attr("checked", "true");
		  			}
	  			}
	  		}

	  		$("input[name='issDate']").mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });
	  		$("input[name='cancDate']").mask("9999-99-99").datepicker({ dateFormat: "yy-mm-dd" });

	  		break;
	  	default :
	  		break;
	  }
}


function removeComma(obj){
	for(var i = 0; i < obj.length; i++){
		obj = obj.replace(",", "");
	}
	return obj;
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

function f_patternChk(txt){
	var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
	if( special_pattern.test(txt) == true ){
	    return false;
	}else{
		return true;
	}

}


function f_docPatterChk(){
	for(var i = 0; i < $("input").size(); i++){
		if(f_patternChk($("input")[i].value) == false){

			var id = $("input")[i].id;
			if(id == ""){
				id = $("input")[i].name;
			}

			if(id == "issNo1"){
				id = "발권번호";
			}else if(id == "issNo2"){
				id = "발권번호";
			}else if(id == "issComm"){
				id = "발권수수료";
			}else if(id == "fare"){
				id = "FARE";
			}else if(id == "warIns"){
				id = "전쟁보험료";
			}else if(id == "airPort1"){
				id = "공항이용료";
			}else if(id == "airPort2"){
				id = "현지공항세";
			}else if(id == "disC"){
				id = "D/C";
			}else if(id == "amtDiv"){
				id = "현금분할입력";
			}else if(id == "issNo2"){
				id = "발권번호";
			}else if(id == "jourN"){
				//여정란은 사용가능
				return true;
			}
			gf_AlertMsg(id + " 란에 특수문자를 입력하실 수 없습니다.");
			return false;
		}
	}
	return true;
}