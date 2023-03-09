/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/

var v_Tr = "<tr><td class=\"inpt\" style=\"width:10%; text-align:center\"><span name=\"Ticno\"></span></td>" +
		"<td class=\"inpt\" style=\"width:15%; text-align:center\"><span name=\"Empnm\"></span></td>" +
		"<td class=\"inpt\" style=\"width:10%; text-align:center\"><span name=\"Empno\"></span></td>" +
		"<td class=\"inpt\" style=\"width:15%; text-align:center\"><span name=\"Ticdt\"></span></td>" +
		"<td class=\"inpt\" style=\"width:10%; text-align:center\"><span name=\"PayMethod\"></span></td>" +
		"<td class=\"inpt\" style=\"width:10%; text-align:center\"><span name=\"Revifare\"></span></td>" +
		"<td class=\"inpt\" style=\"width:10%; text-align:center\"><span name=\"Prctr\"></span></td>" +
		"<td class=\"inpt\" style=\"width:20%; text-align:center\"><span name=\"Route\"></span></td></tr>";



var v_CallbackFunc;

var ds_normFareList = new DataSet();
var ds_refdFareList = new DataSet();
var ds_cancFareList = new DataSet();


/**
* @class 타블릿 화면 사용시 각페이지에서 생성되는 전역변수를 초기화시킴(팝업화면은 제외)
*        - 메모리 해제 관련 처리
* @author 권준호
* @since 2013-04-04
* @version 1.0
*/
function cf_GarbageCollector(){
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
	v_loclCd = gf_GetCookie("loclCd");		// 로케일 20140724

	var norm = gf_IsNull(datas.ds_normFareList) ? ""          : datas.ds_normFareList;
	var refd = gf_IsNull(datas.ds_refdFareList) ? ""          : datas.ds_refdFareList;
	var canc = gf_IsNull(datas.ds_cancFareList) ? ""          : datas.ds_cancFareList;

	ds_normFareList.setAllData(norm);
	ds_refdFareList.setAllData(refd);
	ds_cancFareList.setAllData(canc);

	var Belnr1 = gf_IsNull(datas.Belnr1) ? ""          : datas.Belnr1;
	var Belnr2 = gf_IsNull(datas.Belnr2) ? ""          : datas.Belnr2;
	var Belnr3 = gf_IsNull(datas.Belnr3) ? ""          : datas.Belnr3;
	var EBldat1 = gf_IsNull(datas.EBldat1) ? ""          : datas.EBldat1;
	var EBldat2 = gf_IsNull(datas.EBldat2) ? ""          : datas.EBldat2;
	var EBldat3 = gf_IsNull(datas.EBldat3) ? ""          : datas.EBldat3;
	var ENormalsum = gf_IsNull(datas.ENormalsum) ? ""          : datas.ENormalsum;
	var ERefundsum = gf_IsNull(datas.ERefundsum) ? ""          : datas.ERefundsum;
	var ECancelsum = gf_IsNull(datas.ECancelsum) ? ""          : datas.ECancelsum;

	$("#Belnr1").text(Belnr1);
	$("#Belnr2").text(Belnr2);
	$("#Belnr3").text(Belnr3);
	$("#EBldat1").text(EBldat1);
	$("#EBldat2").text(EBldat2);
	$("#EBldat3").text(EBldat3);
	$("#ENormalsum").text(ENormalsum);
	$("#ERefundsum").text(ERefundsum);
	$("#ECancelsum").text(ECancelsum);

	var tourC = gf_IsNull(datas.tourC) ? ""          : datas.tourC;
	if(tourC == "D"){
		tourC = "동서여행사";
	}else if(tourC == "D"){
		tourC = "현대드림투어";
	}else if(tourC == "J"){
		tourC = "주원항공여행사";
	}else if(tourC == "J"){
		tourC = "하나투어";
	}

	$("#tourC").text(tourC);
	var year = gf_IsNull(datas.year) ? ""          : datas.year;
	$("#year").text(year);
	var month = gf_IsNull(datas.month) ? ""          : datas.month;
	$("#month").text(month);

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

	$("#outerTripClose").click(function(){
		self.close();
	});

	$("#print").click(function(){

		$("#btnDiv").hide();
		window.print();
		$("#btnDiv").show();


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


//	ds_normFareList.setAllData(norm);
//	ds_refdFareList.setAllData(refd);
//	ds_cancFareList.setAllData(canc);

	// 동행인 영문이름 좌석등급 항공료
	for(var i = 0; i < ds_normFareList.size(); i++){

		cf_AppendNormFare(ds_normFareList.get(i));

	}

	// 동행인 영문이름 좌석등급 항공료
	for(var i = 0; i < ds_refdFareList.size(); i++){

		cf_AppendRefdFare(ds_refdFareList.get(i));

	}

	// 동행인 영문이름 좌석등급 항공료
	for(var i = 0; i < ds_cancFareList.size(); i++){

		cf_AppendCancFare(ds_cancFareList.get(i));

	}


	var nRcCount = "["+ds_normFareList.size()+"]";
	$("#normFareListCnt").text(nRcCount); 	//문서함 목록

	var cRcCount = "["+ds_cancFareList.size()+"]";
	$("#cancFareListCnt").text(cRcCount); 	//문서함 목록





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
	  	default :
	  		break;
	  }
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


function cf_AppendNormFare(obj){

	$("#norm").append(v_Tr);

	$("#norm tr:last span[name='Ticno']").text(obj.Ticno);
	$("#norm tr:last span[name='Empnm']").text(obj.Empnm);
	$("#norm tr:last span[name='Empno']").text(obj.Empno);
	$("#norm tr:last span[name='Ticdt']").text(obj.Ticdt);


	var PayMethod = "";
	if(obj.PayMethod == "1"){
		PayMethod = "현금";
	}else if(obj.PayMethod == "2"){
		PayMethod = "카드";
	}
	$("#norm tr:last span[name='PayMethod']").text(PayMethod);



	var Revifare = obj.Revifare;
	if(Revifare != undefined){
		temp = parseFloat(Revifare) * 100;
		Revifare = gf_AmtFormat(temp.toFixed(0));
	}else if(val != ""){
		temp = parseFloat(Revifare) * 100;
		Revifare = gf_AmtFormat(temp.toFixed(0));
	}
	$("#norm tr:last span[name='Revifare']").text(Revifare);
	$("#norm tr:last span[name='Prctr']").text(obj.Prctr);
	$("#norm tr:last span[name='Route']").text(obj.Route);


}

//ViewDoc 전용
function cf_AppendRefdFare(obj){

		$("#refd").append(v_Tr);

		$("#refd tr:last span[name='Ticno']").text(obj.Ticno);
		$("#refd tr:last span[name='Empnm']").text(obj.Empnm);
		$("#refd tr:last span[name='Empno']").text(obj.Empno);
		$("#refd tr:last span[name='Ticdt']").text(obj.Ticdt);


		var PayMethod = "";
		if(obj.PayMethod == "1"){
			PayMethod = "현금";
		}else if(obj.PayMethod == "2"){
			PayMethod = "카드";
		}
		$("#refd tr:last span[name='PayMethod']").text(PayMethod);



		var Revifare = obj.Revifare;
		if(Revifare != undefined){
			temp = parseFloat(Revifare) * 100;
			Revifare = gf_AmtFormat(temp.toFixed(0));
		}else if(val != ""){
			temp = parseFloat(Revifare) * 100;
			Revifare = gf_AmtFormat(temp.toFixed(0));
		}
		$("#refd tr:last span[name='Revifare']").text(Revifare);
		$("#refd tr:last span[name='Prctr']").text(obj.Prctr);
		$("#refd tr:last span[name='Route']").text(obj.Route);


}

function cf_AppendCancFare(obj){

	$("#canc").append(v_Tr);

	$("#canc tr:last span[name='Ticno']").text(obj.Ticno);
	$("#canc tr:last span[name='Empnm']").text(obj.Empnm);
	$("#canc tr:last span[name='Empno']").text(obj.Empno);
	$("#canc tr:last span[name='Ticdt']").text(obj.Ticdt);


	var PayMethod = "";
	if(obj.PayMethod == "1"){
		PayMethod = "현금";
	}else if(obj.PayMethod == "2"){
		PayMethod = "카드";
	}
	$("#canc tr:last span[name='PayMethod']").text(PayMethod);



	var Revifare = obj.Revifare;
	if(Revifare != undefined){
		temp = parseFloat(Revifare) * 100;
		Revifare = gf_AmtFormat(temp.toFixed(0));
	}else if(val != ""){
		temp = parseFloat(Revifare) * 100;
		Revifare = gf_AmtFormat(temp.toFixed(0));
	}
	$("#canc tr:last span[name='Revifare']").text(Revifare);
	$("#canc tr:last span[name='Prctr']").text(obj.Prctr);
	$("#canc tr:last span[name='Route']").text(obj.Route);


}



//기타비의 총합을 구한다
function totalEtcAmt(){

	var v1 = $("#loAirFare").text();
	v1 = removeComma(v1);
	if(v1 == "") v1 = 0;

	var v2 = $("#loTranFare").text();
	if(v2 == "") v2 = 0;
	v2 = removeComma(v2);

	var v3 = $("#visaFeeFare").text();
	if(v3 == "") v3 = 0;
	v3 = removeComma(v3);

	var v4 = $("#ovCharFare").text();
	if(v4 == "") v4 = 0;
	v4 = removeComma(v4);

	var v5 = $("#vocLeeFare").text();
	if(v5 == "") v5 = 0;
	v5 = removeComma(v5);

	var v6 = $("#hostFare").text();
	if(v6 == "") v6 = 0;
	v6 = removeComma(v6);

	var etcTotal = parseFloat(v1) + parseFloat(v2) + parseFloat(v3) + parseFloat(v4) + parseFloat(v5) + parseFloat(v6);

//	$("#etcTotalUSD").text(gf_AmtFormat(etcTotal));
	$("#etcTotalUSD").text(parseFloat(etcTotal.toFixed(3)));

	// 2015-09-02 계산 방식 변경
	// 기존 : 합계*환율, 변경 : 각각 금액*환율 하여 총합
//	var etcTotalEx = Math.round(etcTotal * v_USD);

	var etcTotalEx = Math.round(v1 * v_USD) + Math.round(v2 * v_USD) + Math.round(v3 * v_USD) + Math.round(v4 * v_USD) + Math.round(v5 * v_USD) + Math.round(v6 * v_USD);
	$("#etcTotalEx").text(gf_AmtFormat(etcTotalEx));

	return etcTotalEx;
}

function removeComma(obj){
	for(var i = 0; i < obj.length; i++){
		obj = obj.replace(",", "");
	}
	return obj;
}


function f_calcAirAmount(){
	var tAmount = $("span[name='tAirAmount']");
	var total = 0;
	for(var i = 0; i < tAmount.size(); i++){
		var tVal = tAmount[i].innerText;
		if(tVal == "") continue;
		for(var j = 0; j < tVal.length; j++){
			tVal = tVal.replace(",", "");
		}
		total = total + parseInt(tVal);
	}

	var tUserVal = $("#tripUserAirAmount").text();

	for(var j = 0; j < tUserVal.length; j++){
		tUserVal = tUserVal.replace(",", "");
	}

	total = total + parseInt(tUserVal);

	v_AirTotalAmt = total;
	$("#airTotalAmount").text(gf_AmtFormat(total));

	// 개인지급 합계 (체재비 + 항공료)
	v_TotalAmount = v_cTotalAmt + totalEtcAmt() + v_AirTotalAmt;

	$("#totalAmount").text(gf_AmtFormat(v_TotalAmount));
}