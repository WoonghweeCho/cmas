


/*----------------------------------------------------------------------------------
 * 설명 		: Cookie에 name-value 쌍으로 값을 저장
 * 파라미터 	: name : 쿠키 명칭
 *            value : 쿠키값
 * 리턴값	: N/A
 * 작성자 	: 변형구
 * 작성일 	: 2013.03.04
----------------------------------------------------------------------------------*/
function gf_SetCookie(name, value) {
	var expires = new Date();
    expires.setTime(expires.getTime() + 1000*60*60*24*30); //30일간 유지
    document.cookie = name + '=' + escape(value) + '; path=/; expires='+ expires.toGMTString()+'; ';
}

/*----------------------------------------------------------------------------------
 * 설명 		: 쿠키정보를 리턴한다.
 * 파라미터 	: name : 값을 리턴받을 쿠키 명칭
 * 리턴값	: name 에 해당하는 쿠키값
 * 작성자 	: 변형구
 * 작성일 	: 2013.03.04
----------------------------------------------------------------------------------*/
function gf_GetCookie(name){
	var nameOfCookie = name + "=";
	var x=0;
	while(x<= document.cookie.length){
		var y= (x+nameOfCookie.length);
		if(document.cookie.substring(x,y) == nameOfCookie){
			if((endOfCookie= document.cookie.indexOf(";",y)) == -1)
				endOfCookie = document.cookie.length;
			return unescape(document.cookie.substring(y,endOfCookie));
		}
		x= document.cookie.indexOf(" ",x)+1;
		if(x==0)
		break;
	}

	if ( name == "loclCd") {
		gf_SetCookie("loclCd", "ko_KR");
		return "ko_KR";
	}
	else {
		return "";
	}
}





/*----------------------------------------------------------------------------------
= 기능 : 문자열을 추적 로그를 남긴다.
= 인수 : sValue
= Return :
----------------------------------------------------------------------------------*/
function gf_Trace(sValue)
{
	if(typeof(window.console) != "undefined"){
	console.log(sValue);

/*----------------------------------------------------------------------------------
    for (var i=0; i<arguments.length; i++) {
        trace(arguments[i]);
    }
----------------------------------------------------------------------------------*/
	}
}

/*----------------------------------------------------------------------------------
 * 설명 		: UTF-8에서 한글 처리
 * 파라미터 	: 한글값
 * 리턴값	: URI ENCODE 된 한글값
 * 작성자 	: 변형구
 * 작성일 	: 2013.03.04
----------------------------------------------------------------------------------*/
function gf_Hangle(val){
	return encodeURIComponent(val);
}


/**
* @class 통합결재 시스템의 UI 입력 및 버튼 개체의 enable disable 컨트롤 클래스
* @constructor
* @see   Barocon 의 gf_Enable 의 역할을 수행한다.
* @author 변형구
* @since 2013-04-04
* @version 1.0
*/
var objControl = function () {

};

/**
* @function
* @param obj(Object) collection 개체 인지를 확인할 개체
* @see   인자로 넘긴 object 가 collection 인지 확인한다.
*        이 함수는 enable 함수에서 사용하기 위해 만든것이므로 화면단의 스크립트에서는 사용하지 않도록 한다.
* @return true(collection yes) / false (collection no )
* @author 변형구
* @since 2013-04-04
* @version 1.0
*/
objControl.prototype.isCollection = function ( obj ) {

	if ( gf_IsNull( obj.length ) ) {
		return false;
	}
	else {
		return true;
	}
}

/**
* @function
* @param obj(Object) enable 처리할 개체 또는 개체 collection
* @see   인자로 넘긴 object 또는 object collection 내의 모든 object 를 enable 처리 한다.<br>
*        <color='red'>이 함수는 enable 함수에서 사용하기 위해 만든것이므로 화면단의 스크립트에서는 사용하지 않도록 한다.</color>
* @author 변형구
* @since 2013-04-04
* @version 1.0
*/
objControl.prototype.enableObj = function ( obj ) {

	if ( typeof(obj) == "string") {
		obj = document.getElementsByName(obj);
	}

	if ( this.isCollection( obj ) ) {
		for ( var j = 0; j < obj.length; j++ ) {
			obj[j].disabled = false;
		}
	}
	else {
		obj.disabled = false;
	}
}

/**
* @function
* @param obj(Object) disable 처리할 개체 또는 개체 collection
* @see   인자로 넘긴 object 또는 object collection 내의 모든 object 를 disable 처리 한다.<br>
*        이 함수는 enable 함수에서 사용하기 위해 만든것이므로 화면단의 스크립트에서는 사용하지 않도록 한다.
* @author 변형구
* @since 2013-04-04
* @version 1.0
*/
objControl.prototype.disableObj = function ( obj ) {

	if ( typeof(obj) == "string") {
		obj = document.getElementsByName(obj);
	}

	if ( this.isCollection( obj ) ) {
		for ( var j = 0; j < obj.length; j++ ) {
			obj[j].disabled = true;
		}
	}
	else {
		obj.disabled = true;
	}
}

/**
* @function
* @param obj(String) enable 또는 disable 처리할 개체명, 여러개의 개체를 이용하려 할경우 개체 명을 ','로 구분한다.
* @param enable(boolean) true (enable) / false(disable)
* @see   실제 화면단의 스크립트에서 사용할 enable 처리 함수
* @author 변형구
* @since 2013-04-04
* @version 1.0
*/
objControl.prototype.enable = function ( obj, enable ) {

	if ( $.type(obj) == "string" ) {
		if ( obj.indexOf(',') <= -1 ) {
			if ( enable ) {
				this.enableObj(document.getElementsByName(obj));
			}
			else {
				this.disableObj(document.getElementsByName(obj));
			}
		}
		else {
			var ob = obj.split(',');
			for (var i = 0; i < ob.length; i++ ) {
				var objNode = document.getElementsByName(ob[i]);
				if ( enable ) {
					this.enableObj(objNode);
				}
				else {
					this.disableObj(objNode);
				}
			}
		}
	}
};

var gf_Objs = new objControl();

/*----------------------------------------------------------------------------------
 * Array prototype 함수
 ----------------------------------------------------------------------------------*/
/**
* @function
* @param idx(int) 배열에서 삭제할 index
* @see   배열의 특정 위치의 데이터를 삭제 한다.
* @author 변형구
* @since 2013-04-04
* @version 1.0
*/
Array.prototype.remove = function(idx){
	   var temp = new Array();
	   var i = this.length;

	   while(i > idx){
	       var kk = this.pop();
	       temp.push(kk);

	       i--;
	   }

	   for(var i=temp.length - 2; i>=0; i--){
	       this.push(temp[i]);
	   }
	}


/*----------------------------------------------------------------------------------
 * Date Prototype 함수
----------------------------------------------------------------------------------*/
/*----------------------------------------------------------------------------------
 * 설명 			: 자바스크립트의 내장 객체인 String 객체에 simpleReplace 메소드를 추가한다.
 *            	  simpleReplace 메소드는 스트링 내에 있는 특정 스트링을 다른 스트링으로 모두 변환한다.
 *       	      String 객체의 replace 메소드와 동일한 기능을 하지만 간단한 스트링의 치환시에
 *                보다 유용하게 사용할 수 있다.
 *                ex ) var str = 'abcde';
 *                     str = str.simpleReplace('cd', 'xx');
 *                     위의 예에서 str는 "abxxe"가 된다.
 * 파라미터 		: oldStr required 바뀌어야 될 기존의 스트링
 *                newStr required 바뀌어질 새로운 스트링
 * 리턴값		: replaced String.
 * 작성자 		: 변형구
 * 작성일 		: 2013.04.01
----------------------------------------------------------------------------------*/
String.prototype.simpleReplace = function(oldStr, newStr) {
	var rStr = oldStr;
	rStr = rStr.replace(/\\/g, "\\\\");
	rStr = rStr.replace(/\^/g, "\\^");
	rStr = rStr.replace(/\$/g, "\\$");
	rStr = rStr.replace(/\*/g, "\\*");
	rStr = rStr.replace(/\+/g, "\\+");
	rStr = rStr.replace(/\?/g, "\\?");
	rStr = rStr.replace(/\./g, "\\.");
	rStr = rStr.replace(/\(/g, "\\(");
	rStr = rStr.replace(/\)/g, "\\)");
	rStr = rStr.replace(/\|/g, "\\|");
	rStr = rStr.replace(/\,/g, "\\,");
	rStr = rStr.replace(/\{/g, "\\{");
	rStr = rStr.replace(/\}/g, "\\}");
	rStr = rStr.replace(/\[/g, "\\[");
	rStr = rStr.replace(/\]/g, "\\]");
	rStr = rStr.replace(/\-/g, "\\-");
	var re = new RegExp(rStr, "g");
	return this.replace(re, newStr);
}
/*----------------------------------------------------------------------------------
 * 설명 		: 자바스크립트의 내장 객체인 String 객체에 trim 메소드를 추가한다.
 *            	  trim 메소드는 스트링의 앞과 뒤에 있는 white space 를 제거한다.
 *                ex ) var str = '  abede    '
 *                     str = str.trim();
 *                     위의 예에서 str는 "abcde"가 된다.
 * 파라미터 	: N/A
 * 리턴값	: trimed String.
 * 작성자 	: 변형구
 * 작성일 	: 2013.03.04
----------------------------------------------------------------------------------*/
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
/*----------------------------------------------------------------------------------
 * 설명 		: 자바스크립트의 내장 객체인 String 객체에 removeNewLine 메소드를 추가한다.
 *            문자열에서 newline 을 제거한다.
 * 파라미터 	: N/A
 * 리턴값	: newline 제거된 문자열
 * 작성자 	: 변형구
 * 작성일 	: 2013.03.04
----------------------------------------------------------------------------------*/
String.prototype.removeNewLine = function() {
	return this.replace(/(\r\n|\n|\r)/gm,"");
};
/*----------------------------------------------------------------------------------
 * 설명 		: 자바스크립트의 내장 객체인 String 객체에 startsWith 메소드를 추가한다.
 *            문자열이 인자로 전달된 str 문자열로 시작하는지 판단.
 * 파라미터 	: str<String> : 검사할 문자열
 * 리턴값	: 인자로 전달된 str로 시작되는 문자열이면 true 아니면 false
 * 작성자 	: 변형구
 * 작성일 	: 2013.05.27
----------------------------------------------------------------------------------*/
String.prototype.startsWith = function(str) {
	return this.slice(0, str.length) == str;
};
/*----------------------------------------------------------------------------------
 * 설명 		: 자바스크립트의 내장 객체인 String 객체에 endsWith 메소드를 추가한다.
 *            문자열이 인자로 전달된 str 문자열로 종료되는지 판단.
 * 파라미터 	: str<String> : 검사할 문자열
 * 리턴값	: 인자로 전달된 str로 종료되는 문자열이면 true 아니면 false
 * 작성자 	: 변형구
 * 작성일 	: 2013.05.27
----------------------------------------------------------------------------------*/
String.prototype.endsWith = function(str) {
	return this.slice(-str.length) == str;
};




/*----------------------------------------------------------------------------------
 * Date Prototype 함수
----------------------------------------------------------------------------------*/
/*----------------------------------------------------------------------------------
 * 설명 		: 자바스크립트의 내장 객체인 Date 객체에 format 메소드를 추가한다.
 *         		   format 메소드는 Date 객체가 가진 날짜를 지정된 포멧의 스트링으로 변환한다.
 *         		var date = new Date();
 *         		var dateStr = date.format('YYYYMMDD');
 *         		참고 : Date 오브젝트 생성자들
 *            		- dateObj = new Date()
 *            		- dateObj = new Date(dateVal)
 *            		- dateObj = new Date(year, month, date[, hours[, minutes[, seconds[,ms]]]])
 *         		위의 예에서 오늘날짜가 2002년 3월 5일이라면 dateStr의 값은 "20020305"가 된다.
 *         		default pattern은 "YYYYMMDD"이다.
 * 파라미터 : pattern optional 변환하고자 하는 패턴 스트링. (default : YYYYMMDD)
 *     # syntex
 *       YYYY : hour in am/pm (1&tilde;12)
 *       MM   : month in year(number)
 *       MON  : month in year(text)  예) 'January'
 *       mon  : short month in year(text)  예) 'Jan'
 *       DD   : day in month
 *       DAY  : day in week  예) 'Sunday'
 *       day  : short day in week  예) 'Sun'
 *       hh   : hour in am/pm (1&tilde;12)
 *       HH   : hour in day (0&tilde;23)
 *       mm   : minute in hour
 *       ss   : second in minute
 *       SS   : millisecond in second
 *       a    : am/pm  예) 'AM'
 * 리턴값 		: Date를 표현하는 변환된 String.
 * 작성자 	: 변형구
 * 작성일 	: 2013.03.04
----------------------------------------------------------------------------------*/
Date.prototype.format = function(pattern) {
	var year = this.getFullYear();
	var month = this.getMonth() + 1;
	var day = this.getDate();
	var dayInWeek = this.getDay();
	var hour24 = this.getHours();
	var ampm = (hour24 < 12) ? "AM" : "PM";
	var hour12 = (hour24 > 12) ? (hour24 - 12) : hour24;
	var min = this.getMinutes();
	var sec = this.getSeconds();
	var YYYY = "" + year;
	var YY = YYYY.substr(2);
	var MM = (("" + month).length == 1) ? "0" + month : "" + month;
//	var MON = GLB_MONTH_IN_YEAR[month - 1];
//	var mon = GLB_SHORT_MONTH_IN_YEAR[month - 1];
	var DD = (("" + day).length == 1) ? "0" + day : "" + day;
//	var DAY = GLB_DAY_IN_WEEK[dayInWeek];
//	var day = GLB_SHORT_DAY_IN_WEEK[dayInWeek];
	var HH = (("" + hour24).length == 1) ? "0" + hour24 : "" + hour24;
	var hh = (("" + hour12).length == 1) ? "0" + hour12 : "" + hour12;
	var mm = (("" + min).length == 1) ? "0" + min : "" + min;
	var ss = (("" + sec).length == 1) ? "0" + sec : "" + sec;
	var SS = "" + this.getMilliseconds();
	var dateStr;
	var index = -1;
	if (typeof (pattern) == "undefined") {
		dateStr = "YYYYMMDD";
	} else {
		dateStr = pattern;
	}
	dateStr = dateStr.replace(/YYYY/g, YYYY);
	dateStr = dateStr.replace(/YY/g, YY);
	dateStr = dateStr.replace(/MM/g, MM);
//	dateStr = dateStr.replace(/MON/g, MON);
//	dateStr = dateStr.replace(/mon/g, mon);
	dateStr = dateStr.replace(/DD/g, DD);
//	dateStr = dateStr.replace(/DAY/g, DAY);
//	dateStr = dateStr.replace(/day/g, day);
	dateStr = dateStr.replace(/hh/g, hh);
	dateStr = dateStr.replace(/HH/g, HH);
	dateStr = dateStr.replace(/mm/g, mm);
	dateStr = dateStr.replace(/ss/g, ss);
	dateStr = dateStr.replace(/SS/g, SS);
	dateStr = dateStr.replace(/(\s+)a/g, "$1" + ampm);
	return dateStr;
};

/*----------------------------------------------------------------------------------
 * 설명 		: 현재 Date 객체의 날짜보다 이후날짜를 가진 Date 객체를 리턴한다.
 *            	  예를 들어 내일 날짜를 얻으려면 다음과 같이 하면 된다.
 *                var date = new Date();
 *                var oneDayAfter = date.after(0, 0, 1);
 * 파라미터 	: years optional 이후 년수
 *                months optional 이후 월수
 *                dates optional 이후 일수
 *                hours optional 이후 시간수
 *                minutes optional 이후 분수
 *                seconds optional 이후 초수
 *                mss optional 이후 밀리초수
 * 리턴값	: 이후날짜를 표현하는 Date 객체
 * 작성자 	: 변형구
 * 작성일 	: 2013.03.04
----------------------------------------------------------------------------------*/
Date.prototype.after = function(years, months, dates, hours, miniutes, seconds, mss) {
	if (years == null)
		years = 0;
	if (months == null)
		months = 0;
	if (dates == null)
		dates = 0;
	if (hours == null)
		hours = 0;
	if (miniutes == null)
		miniutes = 0;
	if (seconds == null)
		seconds = 0;
	if (mss == null)
		mss = 0;
	if (years != 0)
		this.addYear(years);
	if (months != 0)
		this.addMonth(months);
	if (dates != 0)
		this.addDate(dates);
	if (hours != 0)
		this.addHours(hours);
	if (miniutes != 0)
		this.addMinutes(miniutes);
	if (seconds != 0)
		this.addSeconds(seconds);
	if (mss != 0)
		this.addMilliseconds(mss);
	return this;
};

/*----------------------------------------------------------------------------------
 * 설명 		: 현재 Date 객체의 날짜보다 이전날짜를 가진 Date 객체를 리턴한다.
 *            	  예를 들어 어제 날짜를 얻으려면 다음과 같이 하면 된다.
 *                var date = new Date();
 *                var oneDayBefore = date.before(0, 0, 1);
 * 파라미터 	: years optional 이전으로 돌아갈 년수
 *                months optional 이전으로 돌아갈 월수
 *                dates optional 이전으로 돌아갈 일수
 *                hours optional 이전으로 돌아갈 시간수
 *                minutes optional 이전으로 돌아갈 분수
 *                seconds optional 이전으로 돌아갈 초수
 *                mss optional 이전으로 돌아갈 밀리초수
 * 리턴값	: 이전날짜를 표현하는 Date 객체
 * 작성자 	: 변형구
 * 작성일 	: 2013.03.04
----------------------------------------------------------------------------------*/
Date.prototype.before = function(years, months, dates, hours, miniutes, seconds, mss) {
	if (years == null)
		years = 0;
	if (months == null)
		months = 0;
	if (dates == null)
		dates = 0;
	if (hours == null)
		hours = 0;
	if (miniutes == null)
		miniutes = 0;
	if (seconds == null)
		seconds = 0;
	if (mss == null)
		mss = 0;
	if (years != 0)
		this.addYear(years * -1);
	if (months != 0)
		this.addMonth(months * -1);
	if (dates != 0)
		this.addDate(dates * -1);
	if (hours != 0)
		this.addHours(hours * -1);
	if (miniutes != 0)
		this.addMinutes(miniutes * -1);
	if (seconds != 0)
		this.addSeconds(seconds * -1);
	if (mss != 0)
		this.addMilliseconds(mss * -1);
	return this;
};


/**
* @class 화면 전체에 반투명의 div를 덮어 화면 요소의 클릭을 막는다.
* @param bClickDisableOverlay<bool> 덮인 div를 클릭하면 div를 없앨것인지 여부 true( click 시 div 사라짐 ) / false ( 클릭해도 사라지지 않음 )
* @param bFstLvlPop<bool> true 메인페이지에서 열리는 팝업 / false : 팝업의 팝업
* @author 변형구
* @since 2013-04-16
* @version 1.0
*/
function gf_EnableOverlayOpener( bClickDisableOverlay, bFstLvlPop, v_IsModal ) {

	if ( gf_IsNull(v_IsModal) ) {
		v_IsModal = true;
	}

	if (v_IsModal == false ) {
		return;
	}

	if ( gf_IsNull(bClickDisableOverlay)) {
		bClickDisableOverlay = false;
	}

	if ( gf_IsNull( bFstLvlPop ) ) {
		bFstLvlPop = true;
	}

	var bodyHeight = 0;

	if (bFstLvlPop) {
		try {
			if ( opener ) {
				bodyHeight = opener.top.$('body').prop("scrollHeight") + "px";

			}
		}
		catch (e) {
			//alert("error : " + e.message);
		}
	}
	else {
		try {
			if ( opener ) {
				bodyHeight = opener.$('body').prop("scrollHeight") + "px";
			}
		}
		catch (e) {
			//alert("error : " + e.message);
		}
	}


	if ( bodyHeight == 0) {
		bodyHeight = "100%";
	}


	/*var overlay = "<div style=\"width: 100%; height: "+bodyHeight+"\" class=\"ui-widget-overlay ui-front\"> " +
	              "<div class=\"ui-dialog-content  ui-widget-content\"> " +
	              //"<div class=\"btn-area\" >" +
	              //"<a class=\"btn s1\" ><span>팝업으로 인해 사용 할 수 없습니다.</span></a> " +
	              "팝업으로 인해 사용 할 수 없습니다." +
	              //"</div>"  +
	              "</div>" +
	              "</div>";*/

	var overlay = 	"<div style=\"width: 100%; height: "+bodyHeight+"\" class=\"ui-widget-overlay ui-front\"> " +
				    "</div>";
	var overlayMsg = "";
	/*var overlayMsg = "	<div id=\"dialog-message\" title=\"사용할 수 없습니다.\"> " +
			"<p> " +
			" 	<span class=\"ui-icon ui-icon-circle-check\" style=\"float: left; margin: 0 7px 50px 0;\"></span>" +
			" 		팝업이 열려있어 사용할 수 없는 상태 입니다. " +
			"</p> " +
			"<p>" +
			"	열여 있는 팝업이 없으나 이 창이 보인다면 닫기 버튼을 눌러 주세요" +
			"</p>" +
			"</div>"*/ ;
    // popup의 팝업
	if ( opener.top == opener ) {
		bFstLvlPop = false;
	}
	else {
		// 첫번째 팝업
		bFstLvlPop = true;
	}

	// 정책 변경으로 인해 첫번째 팝업은 모달 레이어를 깔지 않는다.

	if (bFstLvlPop) {

		try {
			if ( opener ) {
				// modal layer 삽입
				opener.$('body').append(overlay);

				/*if ( opener.top.$("#dialog-message" ).length == 0)  {
					// dialog layer 삽입
					opener.top.$('body').append(overlayMsg);
				}*/

				// dialog layer 띄우기 / 이벤트 설정
				/*opener.top.$( "#dialog-message" ).dialog({
					minWidth : 320,
					buttons: [ { text: "닫기", click: function() {
																	if ( opener ) {
																		gf_AlertMsg('팝업이 열려 있어 닫을 수 없습니다.');
																		return false;
																	}

																	opener.top.$( this ).dialog( "close" );
																 }
					            }
					         ],
					close: function( event, ui ) {

						var l_overlay;
						if ( opener ) {
							l_overlay = opener.top.$(".ui-widget-overlay");   	// modal layer 개체
						}
						else {
							l_overlay = top.$(".ui-widget-overlay");   	// modal layer 개체
						}

						$(l_overlay).detach();										// modal layer 개체 제거
					},
					beforeClose: function (event, ui) {
						// 열려있었을지도 모르는 다이얼로그의 X 버튼을 다시 활성화
						opener.top.$('.ui-dialog-titlebar-close').show();
					}
				});

				// 다이얼로그의 X 버튼을 없앤다.
				opener.top.$('.ui-dialog-titlebar-close').hide();*/
			}
		}
		catch (e) {
			//alert("error : " + e.message);
		}
	}
	else {
		try {
			if ( opener ) {
				// modal layer 삽입
				opener.$('body').append(overlay);

				/*if ( opener.$("#dialog-message" ).length == 0 ){					// dialog layer 삽입
					opener.$('body').append(overlayMsg);
				}

				// dialog layer 띄우기 / 이벤트 설정
				opener.$( "#dialog-message" ).dialog({
					minWidth : 320,
					buttons: [ { text: "닫기", click: function() {
																	if ( opener ) {
																		gf_AlertMsg('팝업이 열려 있어 닫을 수 없습니다.');
																		return false;
																	}

																	opener.$( this ).dialog( "close" );
																 }
					           }
					         ],
				    close: function( event, ui ) {
				    	if ( opener ) {
							gf_AlertMsg('팝업이 열려 있어 닫을 수 없습니다.');
							return false;
						}
				    	opener.$('.ui-dialog-titlebar-close').show();
				    	var l_overlay = opener.$(".ui-widget-overlay");   	// modal layer 개체
				    	//var l_dialog  = opener.$("#dialog-message");   	// dialog 개체
				    	$(l_overlay).detach();								// modal layer 개체 제거
				    },
				    beforeClose: function (event, ui) {
				    	// 열려있었을지도 모르는 다이얼로그의 X 버튼을 다시 활성화
						opener.$('.ui-dialog-titlebar-close').show();
					}

				});
				opener.$(this).$('.ui-dialog-titlebar-close').hide();
				// 다이얼로그의 X 버튼을 없앤다.
				//opener.$('.ui-dialog-titlebar-close').hide();*/
			}
		}
		catch (e) {
			//alert("error : " + e.message);
		}
	}

	if ( bClickDisableOverlay ) {
		overlay.click( function () {
			gf_DisableOverlayOpener(bFstLvlPop);
			}
		);
	}

	window.focus();
}





/**
* @class 화면 전체에 반투명의 div를 덮어 화면 요소의 클릭을 막는다.
* @param bClickDisableOverlay<bool> 덮인 div를 클릭하면 div를 없앨것인지 여부 true( click 시 div 사라짐 ) / false ( 클릭해도 사라지지 않음 )
* @param bFstLvlPop<bool> true 메인페이지에서 열리는 팝업 / false : 팝업의 팝업
* @author 변형구
* @since 2013-04-16
* @version 1.0
*/
function gf_EnableOverlay( bClickDisableOverlay, bFstLvlPop ) {


	if ( gf_IsNull(bClickDisableOverlay)) {
		bClickDisableOverlay = false;
	}

	if ( gf_IsNull( bFstLvlPop ) ) {
		bFstLvlPop = true;
	}

	var overlay = $("<div><span><img src='" + gv_ServerUrl + "/common/images/spinner-small.gif'> 처리중입니다. (절대 창을 닫지 마세요)</span></div>") ;
	if (bFstLvlPop) {
		overlay.addClass("ui-widget-overlay ui-front").appendTo(  top.$('body') ); //$('#root') );
	}
	else {
		overlay.addClass("ui-widget-overlay ui-front").appendTo(  $('body') ); //$('#root') );
	}

	if ( bClickDisableOverlay ) {
		overlay.click( function () {
			gf_DisableOverlay(bFstLvlPop);
			}
		);
	}
}





/**
* @class gf_EnableOverlay로 인해 활성화된 전체 화면을 덮은 div를 제거 한다.
* @param
* @author 변형구
* @since 2013-04-16
* @version 1.0
*/
function gf_DisableOverlay() {
	/*if ( gf_IsNull( bFstLvlPop ) ) {
		bFstLvlPop = true;
	}*/

	var overlay;
	var dial;

	/*if ( bFstLvlPop ) {
		if ( opener ) {
			overlay = opener.top.$(".ui-widget-overlay");
		}
	}
	else {
		if ( opener ) {
			overlay = opener.$(".ui-widget-overlay") ;
		}
	}*/

	try{
		if ( opener ) {
			if ( opener.top ) {
				overlay = opener.top.$(".ui-widget-overlay");		// modal layer 개체
				//opener.top.$("#dialog-message").dialog( "close" );
//				overlay = opener.$(".ui-widget-overlay");
				dial  = opener.top.$("#dialog-message");   		// dialog 개체

			}
			else {
				overlay = opener.$(".ui-widget-overlay");			// modal layer 개체
				dial = opener.$("#dialog-message");
			}
		}

		if ( opener ) {
			$(overlay).detach();
			dial.dialog("close");
			//dialog.dialog("close");

		}
	}catch(e){

	}
	//$(window).focus();
    //$(overlay[1]).detach();
}

//
function gf_DisableCurrentOverlay(){
	$(".ui-widget-overlay").detach();
}


/**
* @class gf_EnableOverlayOpener로 인해 활성화된 전체 화면을 덮은 div를 제거 한다.
* @param bFstLvlPop<bool> true 메인페이지에서 열리는 팝업 / false : 팝업의 팝업
* @author 변형구
* @since 2013-04-16
* @version 1.0
*/
function gf_DisableOverlayOpener(bFstLvlPop) {

	alert("gf_DisableOverlayOpener : " + bFstLvlPop);

	if ( gf_IsNull( bFstLvlPop ) ) {
		bFstLvlPop = true;
	}
	var overlay;
	if ( bFstLvlPop ) {
		if ( opener ) {
			overlay = opener.top.$(".ui-widget-overlay");

//			overlay = opener.$(".ui-widget-overlay");
		}
	}
	else {
		if ( opener ) {
			overlay = opener.$(".ui-widget-overlay") ;
		}
	}
	$(overlay).detach();

}



/**
* @class 입력값이 null 또는 wihte space로만 이루어져 있는지 확인 하는 함수
* @param sValue<Object>
* @return true / false ( null 여부)
* @author 변형구
* @since 2013-03-27
* @version 1.0
*/
function gf_IsNull(sValue) {

	if (new String(sValue).valueOf() == "undefined")
		return true;
	if (sValue == null)
		return true;
	var v_ChkStr = new String(sValue);
	if (v_ChkStr == null)
		return true;
	if (v_ChkStr.toString().length == 0)
		return true;
	return false;

};

/**
* @class 입력된 값이 null 인 경우 주어진 값으로 셋팅
* @param strCompType<Object, strSetVal<String>
* @return String
* @author 권준호
* @since 2013-03-27
* @version 1.0
*/
function gf_setNullInit(sVal, strSetVal)
{
	if(gf_IsNull(sVal))
	{
		return strSetVal;
	} else {
		return sVal;
	}
}


/*----------------------------------------------------------------------------------
 * 설명     	: 입력된 key에 해당하는 다국어를 찾는 함수
 * 파라미터 	: strKey : 다국어 key값,
 *            loclCd : 자바 로케일 코드 if loclCd is null then using session loclCd
 * 작성자   	: 변형구
 * 작성일    	: 2013.03.13
 *---------------------------------------------------------------------------------*/
function gf_FindLang(strKey, loclCd) {
	if ( gf_IsNull(loclCd) ) {
		loclCd = gf_GetCookie("loclCd");
	}
	if ( gf_IsNull(loclCd) )
		loclCd = "ko_KR";

	var findLang = "";
	try {
		findLang = jquery_lang_js.prototype.lang[loclCd][strKey];
	}
	catch (e) {
		gf_Trace(e.message);
	}

	if ( gf_IsNull(findLang) ) {
		findLang = strKey;
	}
	return findLang;
}

/*----------------------------------------------------------------------------------
 * 설명 			: 공통메세지에 정의된 메세지를 alert box로 보여준 후 리턴한다.
 * 파라미터		: msgId  메세지 데이터 베이스에 정의한 메세지 코드
 *       		  paramArray  메세지에서 '@' 문자와 치환될 데이터 Array.
 *                Array의 index와 메세지 내의 '@' 문자의 순서가 일치한다.
 *                치환될 데이터는 [] 사이에 ,콤마를 구분자로 하여 기술하면 Array 로 인식된다.
 * 리턴값 	    : 치환된 메세지 스트링
 * 작성자 		: 변형구
 * 작성일 		: 2013.03.13
----------------------------------------------------------------------------------*/
function gf_AlertMsg(msgId, paramArray) {
	if (gf_IsNull(msgId)) {
		alert("메시지 코드가 입력되지 않았습니다.");
		return null;
	}

	var msg = gMsg.getMsg(ezMessage(msgId), paramArray);
	alert(msg, gf_FindLang('알림'));
	return msg;
}



/*----------------------------------------------------------------------------------
 * 설명 			: 공통메세지에 정의된 메세지를 confirm box로 보여준 후 리턴한다.
 *                gf_GetMsg 참조.
 * 파라미터		: msgId  메세지 데이터 베이스에 정의한 메세지 코드
 *       		  paramArray  메세지에서 '@' 문자와 치환될 데이터 Array.
 *                Array의 index와 메세지 내의 '@' 문자의 순서가 일치한다.
 *                치환될 데이터는 [] 사이에 ,콤마를 구분자로 하여 기술하면 Array 로 인식된다.
 * 리턴값 	    : confirm 결과 true / false
 * 작성자 		: 변형구
 * 작성일 		: 2013.03.13
----------------------------------------------------------------------------------*/
function gf_ConfirmMsg(msgId, paramArray) {
	if (gf_IsNull(msgId)) {
		alert("메시지 코드가 입력되지 않았습니다.");
		return null;
	}
	return confirm(gMsg.getMsg(ezMessage(msgId), paramArray), gf_FindLang('확인'));
}


/*----------------------------------------------------------------------------------
 * 설명 			: 메세지 코드에 따른 메세지 리턴
 * 파라미터		: code : 메세지 코드
 * 리턴값 		: 코드에 해당하는 메시지 , 없다면 코드를 그대로 리턴
 * 작성자 		: 변형구
 * 작성일 		: 2011.10
----------------------------------------------------------------------------------*/
function ezMessage(code) {
	// json 변수에서 메세지 코드값찾는다
	// 코드값 업승면 꽝
	var loclCd = gf_GetCookie("loclCd");
	try{

		code = code.simpleReplace(".", "_");

		if ( gf_IsNull( gv_cmasComm.msg[loclCd][code] )) {

			return code.simpleReplace("_", ".");;
		}
		var msgValue = gv_cmasComm.msg[loclCd][code].message;
		if (gf_IsNull(msgValue)) {
			return code;
		}
	}catch(e){
		msgValue = code;
	}
	return msgValue;
}


///////////////////////////// coMessage 개체 정의  /////////////////////////////
var gMsg = new coMessage();

/*----------------------------------------------------------------------------------
 * 설명 			: 메세지를 관리하는 객체이다.
 * 파라미터		: N/A
 * 리턴값 		: N/A
 * 작성자 		: 변형구
 * 작성일 		: 2013.03.13
----------------------------------------------------------------------------------*/
function coMessage() {
	this.getMsg = coMessage_getMsg;
}

/*----------------------------------------------------------------------------------
 * 설명 			: 공통메세지에 정의된 메세지를 치환하여 알려준다.
 * 파라미터		: message     공통 메세지 영역에 선언된 메세지 ID
 *       		  paramArray  메세지에서 '@' 문자와 치환될 스트링 Array.
 *                (Array의 index와 메세지 내의 '@' 문자의 순서가 일치한다.)
 * 리턴값 	    : 치환된 메세지 스트링
 * 작성자 		: 변형구
 * 작성일 		: 2013.03.13
----------------------------------------------------------------------------------*/
function coMessage_getMsg(message, paramArray) {
	try {
		if (message == null || message == '') {
			return null;
		}
		var index = 0;
		var re = /@/g;
		var count = 0;
		if (paramArray == null) {
			return message;
		}
		for ( var i = 0; i < paramArray.length ; i++) {
			if ( !gf_IsNull(paramArray[i]) ) {
				paramArray[i] = paramArray[i].toString().replace("'", "‘");
			}
		}

		while ((index = message.indexOf("@", index)) != -1) {
			if (paramArray[count] == null) {
				paramArray[count] = "";
			}

			var paramIndex = message.substring(index+1, index+2);
			var value = paramIndex.match(/[^0-9]/g);

			// @ 다음 숫자가 나온경우
			if(value == null) {
				paramIndex = parseInt(paramIndex) -1 ;
				//전달된 메세지 파라미터의 순서를 의미 한다고 판단한다.
				message = message.substr(0, index) + String(gf_FindLang(paramArray[paramIndex]))
						+ message.substring(index + 2, message.length);
			} else {
				message = message.substr(0, index) + String( gf_FindLang(paramArray[count])  )
						+ message.substring(index + 1, message.length);
			}
			index = index + String(gf_FindLang(paramArray[count++])).length;
		}
	} catch (e) {
		gf_Trace('Error occurred!!! : coMessage_getMsg' + e.message);
		return 'Error occurred!!! : coMessage_getMsg';
	}
	return message;
}

/**
* @class 팝업 오픈 공통 함수
* @param url<String> 팝업으로 오픈될 페이지 url
* @param name<String> 팝업 윈도우명
* @param option<String> 윈도우 오픈 option
* @param params<JSONObject> 팝업윈도우에 전달될 인자값
* @param bFstLvlPop<bool> true 메인페이지에서 열리는 팝업 / false : 팝업의 팝업 (default true)
* @param bModal<bool> true modal / false : modaless (default false)
* @param callBackFunc<String> popup이 close 된후 수행될 opener의 함수 ( default null)
* @see   호출되는 팝업에서는 반드시 fstLvlPop isModal callbackFunc 인자값을 받아 해당 값에 적절한 코딩을 구현해야 한다.
* @author 권준호
* @since 2013-04-16
* @version 1.0
*/
function gf_PostOpen(url, name, option, params, bFstLvlPop, bModal, callBackFunc, parentClose){
	parentClose = gf_IsNull(parentClose) ? true: parentClose;
	name = name ? name : "win_"+$.now();

	params = gf_IsNull(params) ? {} : params;

	if ( gf_IsNull(bFstLvlPop) ) {
		bFstLvlPop = true;
	}

	if ( gf_IsNull(bModal) ) {
		bModal = false;
	}

	if ( gf_IsNull(callBackFunc) ) {
		callBackFunc = "";
	}

	params["fstLvlPop"] = bFstLvlPop;
	params["isModal"] = bModal;
	params["callbackFunc"] = callBackFunc;

	var newWin = window.open("",name, option);

	url = gv_ContextPath + url;

	var form = $("<form action=\""+url+"\" method=\"post\" target=\""+name+"\"></form>");
	$("body").append(form);

	$("<input type=\"hidden\" name=\"datas\" />")
		.appendTo(form)
		.val(JSON.stringify(params));

	// 전역 배열 변수에 팝업 개체 삽입
	if(parentClose == true)
		gf_ChildWindow.push(newWin);

	//  모달일경우 부모창을 비활성화 처리 한다.
//	if (bModal) {
//		// 부모창 비활성화 시도
//		gf_EnableOverlay(true, false);
//	}

	newWin.focus();
	form.submit();
	form.detach();

	return newWin;
}

/**
* @class Post document.location.replace 공통 함수
* @param url<String> 전환될 url
* @param params<JSONObject> 전달될 인자값
* @param bFstLvlPop<bool> true 메인페이지에서 열리는 팝업 / false : 팝업의 팝업 (default true)
* @param bModal<bool> true modal / false : modaless (default false)
* @param callBackFunc<String> popup이 close 된후 수행될 opener의 함수 ( default null)
* @see   호출되는 팝업에서는 반드시 fstLvlPop isModal callbackFunc 인자값을 받아 해당 값에 적절한 코딩을 구현해야 한다.
* @author 고준석
* @since 2013-04-16
* @version 1.0
*/
function gf_PostReplace(url, params, bFstLvlPop, bModal, callBackFunc){


	params = gf_IsNull(params) ? {} : params;

	params["fstLvlPop"] = bFstLvlPop;
	params["isModal"] = bModal;
	params["callbackFunc"] = callBackFunc;

	if ( gf_IsNull(bFstLvlPop) ) {
		bFstLvlPop = true;
	}

	if ( gf_IsNull(bModal) ) {
		bModal = false;
	}

	if ( gf_IsNull(callBackFunc) ) {
		callBackFunc = "";
	}

	url = gv_ContextPath + url;

	var form = $("<form action=\""+url+"\" method=\"post\" ></form>");
	$("body").append(form);

	$("<input type=\"hidden\" name=\"datas\" />")
		.appendTo(form)
		.val(JSON.stringify(params));

	// Modal창 일경우
	// 현재 열린창에서의 replace, redirection 이기때문에 replace, redirection 이 일어나기전에 Modal을 유지하기 위해
	// 이전에 설정된 window.unload 이벤트를 unbind 처리 한다.
	// unload가 unbind 되기때문에 새로 replace, redirection 되는 페이지에서는
	// 반드시 gf_SetPopCloseEvent 함수를 페이지 로드시 호출하여 window.unload 이벤트를 다시 bind 해주어야 한다.
	if (bModal) {
		// 부모창 비활성화 시도
		$(window).unbind('unload');
	}
	//  모달일경우 부모창을 비활성화 처리 한다.
	/*if (bModal) {
		// 부모창 비활성화 시도
		gf_EnableOverlay(false, bFstLvlPop);
	}*/

	form.submit();
	form.detach();
}

/**
* @class 팝업 Close event 설정  공통 함수
* @param bModal<bool> true modal / false : modaless (default false)
* @param bFstLvlPop<bool> true 메인페이지에서 열리는 팝업 / false : 팝업의 팝업 (default true)
* @see   구현한 팝업페이지의 로드시 이 함수를 호출해 주어야 close 이벤트가 설정된다.
* @author 변형구
* @since 2013-04-16
* @version 1.0
*/
function gf_SetPopCloseEvent(bModal, bFstLvlPop) {

	bModal		= gf_IsNull(bModal)   ? false : bModal;
//	bFstLvlPop 	= gf_IsNull(bFstLvlPop) ? true  : bFstLvlPop;

	// popup의 팝업
//	try{
//		if (opener.top == opener ) {
//			bFstLvlPop = false;
//		}
//		else {
//			// 첫번째 팝업
//			bFstLvlPop = true;
//		}
//	}catch(e){
//		bFstLvlPop = true;
//	}



	$(window).bind('unload', function() {
		for(var index in gf_ChildWindow){
			try{
			if(gf_ChildWindow[index].window)
				if ( !gf_ChildWindow[index].closed )
					gf_ChildWindow[index].close();
			}catch(e){}
		}
		if ( bFstLvlPop ) {

			gf_DisableOverlay(true);
		}
	});


}

/**
* @class 사용자 선택 팝업
* @param bFstLvlPop<bool> true 메인페이지에서 열리는 팝업 / false : 팝업의 팝업 (default true)
* @param bModal<bool> true 모달 popup / false modaless popup
* @param callBackFunc<String> popup이 close 된후 수행될 opener의 함수 ( default null)
* @author 변형구
* @since 2013-04-16
* @version 1.0 480
*/
function gf_OpenUserPop(bFstLvlPop, bModal, callBackFunc){
	var newWin = gf_PostOpen("/common/jsp/comp/userNorg/orgUserSelect.jsp", null, "toolbar=no,scrollbars=no,width=910,height=501", {}, bFstLvlPop, bModal, callBackFunc);
}


/**
* @class 부서선택팝업
* @param bFstLvlPop<bool> true 메인페이지에서 열리는 팝업 / false : 팝업의 팝업 (default true)
* @param bModal<bool> true 모달 popup / false modaless popup
* @param callBackFunc<String> popup이 close 된후 수행될 opener의 함수 ( default null)
* @author 변형구
* @since 2013-04-17
* @version 1.0
*/
function gf_OpenOrgPop(bFstLvlPop, bModal, callBackFunc){

	var newWin = gf_PostOpen("/common/jsp/comp/userNorg/orgSelect.jsp", null, "toolbar=no,scrollbars=no,width=700,height=500", {}, bFstLvlPop, bModal, callBackFunc);

}

/**
 * @class 사용자 선택 팝업
 * @param bFstLvlPop<bool> true 메인페이지에서 열리는 팝업 / false : 팝업의 팝업 (default true)
 * @param bModal<bool> true 모달 popup / false modaless popup
 * @param callBackFunc<String> popup이 close 된후 수행될 opener의 함수 ( default null)
 * @author 변형구
 * @since 2013-04-16
 * @version 1.0 480
 */
function gf_OpenParamUserPop(bFstLvlPop, bModal, params, callBackFunc){
	var newWin = gf_PostOpen("/common/jsp/comp/userNorg/orgUserSelect.jsp", null, "toolbar=no,scrollbars=no,width=910,height=501", params, bFstLvlPop, bModal, callBackFunc);
}


/**
 * @class 부서선택팝업
 * @param bFstLvlPop<bool> true 메인페이지에서 열리는 팝업 / false : 팝업의 팝업 (default true)
 * @param bModal<bool> true 모달 popup / false modaless popup
 * @param callBackFunc<String> popup이 close 된후 수행될 opener의 함수 ( default null)
 * @author 변형구
 * @since 2013-04-17
 * @version 1.0
 */
function gf_OpenParamOrgPop(bFstLvlPop, bModal, params,callBackFunc){
	var newWin = gf_PostOpen("/common/jsp/comp/userNorg/orgSelect.jsp", null, "toolbar=no,scrollbars=no,width=700,height=500", params, bFstLvlPop, bModal, callBackFunc);
}
/**
 * @class 부서선택팝업
 * @param bFstLvlPop<bool> true 메인페이지에서 열리는 팝업 / false : 팝업의 팝업 (default true)
 * @param bModal<bool> true 모달 popup / false modaless popup
 * @param callBackFunc<String> popup이 close 된후 수행될 opener의 함수 ( default null)
 * @author 변형구
 * @since 2013-04-17
 * @version 1.0
 */
function gf_OpenUserOrgPop(bFstLvlPop, bModal, params,callBackFunc){
	var newWin = gf_PostOpen("/common/jsp/sign/comm/selectUserOrg.jsp", null, "toolbar=no,scrollbars=no,width=700,height=500", params, bFstLvlPop, bModal, callBackFunc);
}

/**
* @class 입력값이 undefined, null일 경우 Null String 으로 변환한다.
* @author 고준석
* @since 2013-04-04
* @version 1.0
*/
function gf_GetValue(sValue) {
	if(gf_IsNull(sValue)) return '';
	return sValue;
};

/**
* @class 내일 날짜를 반환한다
*        형식 : YYYY-MM-DD
* @author 고준석
* @since 2013-04-04
* @version 1.0
*/
function gf_GetNextDate(){
	var v_CurDate = new Date();
	v_CurDate.setDate(v_CurDate.getDate()+1);
	return v_CurDate;
}


/**
 * @type : function
 * @access : public
 * @desc : hidden frame 생성 및 리턴
 */
function gf_GetPoppyFrame(doc, objCnt) {
	var aObjCnt = '';
	if(objCnt) aObjCnt = objCnt;
	var poppy = doc.getElementById("poppy");
	if ( poppy == null )  {
		if ($.browser.msie == true)  {
		//if (/MSIE (\d+\.\d+);/.test(navigator.userAgent)) {
			doc.body.insertAdjacentHTML("beforeEnd", "<div id='poppy'/></div>");
		} else {
			alert('poppy div not found!!!');
			return null;
		}
	}
	doc.getElementById("poppy").innerHTML = "<iframe id='poppyFrame"+aObjCnt+"' name='poppyFrame"+aObjCnt+"' src='about:blank' style='width:0;height:0;border:0;frameborder:0;padding:0;margin:0' scrolling=no></iframe>";
	poppy = doc.getElementById("poppyFrame" + aObjCnt);
	return poppy  ;
}

function gf_ModialPopupDiv( divId, postTitle, content, divCnt ) {
	var tempDivId = divId;
	if ( gf_IsNull(divCnt) ) {
		divCnt = 0;
	}
	else {
		divCnt = divCnt * 20;
	}

	if ( $("#"+divId).length == 0 ) {

		$("#"+divId).append(content);
		$("body").append("<div id=\""+divId+"\"></div>");
	}
	else {
		var cnt = 0;
		while(true) {
			divId = divId+""+cnt;
			if ( $("#"+divId).length == 0 ) {
				break;
			}
		}

		$("#"+divId).append(content);
		$("body").append("<div id=\""+divId+"\"></div>");
	}

	$("#"+divId).append(content);
	if(tempDivId=="atchFileView"){
//		$("#"+divId+":ui-dialog" ).dialog("destroy");
		$( "#"+divId ).dialog({
			maxHeight : 100,
			width : 500,
			title : postTitle,
			position : { my: "left+"+divCnt+" top+"+divCnt, at: "left top", of: "body" },
			modal : true,
			open: function( event, ui ) {
				$(event.target).parent().css("zIndex", "1000");
			}

		});
	}else if(tempDivId=="atchOpnView"){
//		$("#"+divId+":ui-dialog" ).dialog("destroy");
			$( "#"+divId ).dialog({
				maxHeight : 300,
				width : 300,
				title : postTitle,
				position : { my: "left+"+divCnt+" top+"+divCnt, at: "left top", of: "body" },
				modal : true,
				open: function( event, ui ) {
					$(event.target).parent().css("zIndex", "1000");
				}
			});
	}else{
		$( "#"+divId ).dialog( {
			maxHeight : 100,
			title : postTitle,
			position : { my: "left+"+divCnt+" top+"+divCnt, at: "left top", of: "body" }
		});
	}
}


function gf_SelectedValue(elm, value){
	var options = elm.options;
	var size = options.length;
	var i = 0;
	while(i < size){
		if(options[i].value == value){
			elm.selectedIndex = i;
			return;
		}
		i++;
	}
}

if (!Array.prototype.indexOf)
{
  Array.prototype.indexOf = function(elt /*, from*/)
  {
    var len = this.length;

    var from = Number(arguments[1]) || 0;
    from = (from < 0)
         ? Math.ceil(from)
         : Math.floor(from);
    if (from < 0)
      from += len;

    for (; from < len; from++)
    {
      if (from in this &&
          this[from] === elt)
        return from;
    }
    return -1;
  };
}

//글자수 제한 체크
function inputLimit(limit, inputId){
	$("#"+inputId).live('keypress', function(){
		var str;
		str = $("#"+inputId).val();
		if(str.length >= limit){
			gf_AlertMsg('입력범위를 초과 하였습니다.');
			str = str.substring(0,limit);
			return false;
		}else{
			return true;
		}
	});
}


/**
 * @function
 * @param  : fileName <String> 		: 엑셀 파일명
 *           sheetName<String>      : 다운로드 받을 엑셀파일내의 시트 명 default sheet1
 *           mappedData<String>     : Dataset의 필드명과 엑셀 헤더 명칭을 매핑 한다. 엑셀 헤더 명칭은 사용자가 보기 원하는 이름으로 정한다.
 *                                    'fieldNm:Type(length):headerNm,......' 의 형식으로 구성한다.
 *                                    ex ) var mappedData = "excluCls:STRING(50):구분," +
 *															 "dutyNm1:STRING(300):직무명1," +
 *															 "dutyNm2:STRING(300):직무명2," +
 *															 "dutyNm3:STRING(300):직무명3," +
 *															 "dutyNm4:STRING(300):직무명4," +
 *															 "excluCd:STRING(6):전결코드," +
 *															 "rpswrkNm:STRING(100):전결자";
 *
 * 			 ds_ExportData<String>  : 엑셀로 내려받을 데이터를 가지는 데이터셑
 * @access : public
 * @see    :
 *
 * @desc   : 엑셀 업로드
 */
function gf_ExportExcel(fileName, sheetName, mappedData, ds_ExportData) {

	if ( gf_IsNull(mappedData) ) {
		gf_AlertMsg("컬럼 매핑 데이터가 없습니다. 다운로드 할수 없습니다.");
		return;
	}

	if ( ds_ExportData.size() == 0) {
		gf_AlertMsg("다운로드할 데이터가 없습니다.");
		return;
	}

	if ( gf_IsNull(fileName) ) {
		fileName = "execlDown.xls";
	}

	if ( gf_IsNull(sheetName) ) {
		sheetName = "sheet1";
	}
	/**
	 * Map<String, Object> 형태 파라미터 처리
	 */
	var datas= {
		fileNm : fileName,
		sheetNm : sheetName,
		mapperData : mappedData
	};

	/**
	 * DataSet 파라미터 처리
	 */
	var dataSets = {
		excelData : ds_ExportData.getAllData()
	};

	gf_Transaction("SELECT_DOWNLOAD", //전결 : EXCLU
			"/co/common/excel/exportCommExcel.xpl",
			datas,
			dataSets,
			"gf_ExcelCallback", true);
}


/**
 * @function
 * @param  : saveControllerPath <String> : 업로드된 엑셀 데이터를 저장할때 사용할 Controller 의 경로
 *                                      controller 로 전달되는 인자는 input 이라는 이름의 데이터셑 한개 이다. 반드시 지켜져야 한다.
 *           mappedData<String>     : 엑셀 파일의 header 와 database query에서 사용한 인자명과의 매핑 데이터
 *                                    구분:gubn,직무명1:dutyNm1,직무명2:dutyNm2 의 형태로 전송한다.
 *           sheetName<String>      : 엑셀 파일에서 업로드할 sheet name 값이 없으면 첫번째 sheet 만을
 *                                    값이 잇으면 해당 하는 sheet name을 가진 sheet 만을 업로드 한다.
 * 			 popupCallback<String>  : 이 엑셀 업로드는 팝업을 띄워 사용한다. 그러므로 데이터 업로드가 완료된후
 *                                    자동으로 팝업을 닫게 되는에 이때 실행될 업무 화면 단의 스크립트 함수이다.
 *                                    값이 없으면 팝업만 닫고 끝난다.
 * @access : public
 * @see    : 1) 이 함수의 실행 구조는
 *              Excel 파일 서버 업로드 -> 엑셀 내용을 Dataset 에 담아 client 로 전송 -> 함수의 인자로 전달된 saveController 에
 *              Excel 에서 추출한 데이터셑 전송 하여 Call -> saveControllerPath 수행후 팝업 닫힘 -> 업무화면에 정의된  popupCallback 에 해당하는
 *              함수 호출
 *           2) 제약 사항
 *              2-1) 반드시 excel header 와 데이터 베이스 컬럼의 매핑을 정의한 파라미터를 전달 해주어야 함
 *              2-2) 복수개의 sheet 에대한 업로드 수행 불가
 *
 * @desc   : 엑셀 업로드
 */
function gf_ImportExcel( saveControllerPath, mappedData, sheetName, popupCallback ) {

	if ( gf_IsNull(mappedData) ) {
		gf_AlertMsg("컬럼 매핑 데이터가 없습니다. 업로드 할수 없습니다.");
		return;
	}

	if ( gf_IsNull(sheetName) ) {
		gf_AlertMsg('첫번째 EXCEL SHEET만을 업로드 합니다.');
	}

	if ( gf_IsNull( popupCallback ) ) {
		popupCallback = "";
	}

	/**
	 * Map<String, Object> 형태 파라미터 처리
	 */
	var datas= {
		saveControllerPath : saveControllerPath,
		sheetNm : sheetName,
		mapperData : mappedData
	};

	return gf_PostOpen("/common/jsp/comp/excel/uploadExcel.jsp", "excelUpload", "toolbar=no,scrollbars=no,width=560,height=300", datas, true, true, popupCallback, true) ;


}



/**
* @function excel upload download Transaction 처리 후 수행되는 Callback 함수
*
* @author 변형구
* @since 2013-07-06
* @version 1.0
*/
function gf_ExcelCallback(strSvcId, obj, resultData){

	  // transaction의 정상 처리 여부를 판단한다.
	  if (!gf_ChkTransaction(strSvcId, resultData, true )) {
		  return;
	  }

	  switch(strSvcId) {
	  	case "SELECT_DOWNLOAD" :
	  		 $.each(resultData, function (i, itemAry) {
	                if (i == "downResult") {
	                    $.each(itemAry, function (j, item) {
	                    	$("form[name='downForm'] > input[name$='filePath']")[0].value = item.filePath;
	                    	$("form[name='downForm'] > input[name$='fileNm']")[0].value = item.fileNm;
	                    	$("form[name='downForm'] > input[name$='policy']")[0].value = item.policy;
	                    });
	                };
	            });
	  		 $("form[name='downForm']").submit();
			break;

	  	case "SELECT_UPLOADEXCEL" :
	  		break;
	  	default :
	  		break;
	  }
}

function gf_InitSpinner(el, options) {
	// Becomes this.options
	var defaults = {
		bgColor 		: '#fff',
		duration		: 800,
		opacity			: 0.7,
		classOveride 	: false
	};
	this.options 	= jQuery.extend(defaults, options);
	this.container 	= $(el);

	this.init = function() {
		var container = this.container;
		// Delete any other loaders
		this.remove();
		// Create the overlay
		var overlay = $('<div></div>').css({
				'background-color': this.options.bgColor,
				'opacity':this.options.opacity,
				'width':container.width(),
				'height':container.height(),
				'position':'fixed',
				'top':'0px',
				'left':'0px',
				'z-index':99999
		}).addClass('ajax_overlay');
		// add an overiding class name to set new loader style
		if (this.options.classOveride) {
			overlay.addClass(this.options.classOveride);
		}
		// insert overlay and loader into DOM
		container.append(
			overlay.append(
				$('<div></div>').addClass('ajax_loader')
			).fadeIn(this.options.duration)
		);
    };

	this.remove = function(){
		var overlay = this.container.children(".ajax_overlay");
		if (overlay.length) {
			overlay.fadeOut(this.options.classOveride, function() {
				overlay.remove();
			});
		}
	}

    this.init();
}
