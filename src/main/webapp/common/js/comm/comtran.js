/**********************************************************************************
* Transaction 관련 공통 라이브러리
***********************************************************************************/

/**
* @class
* @param strSvcID(String) transaction 을 구분할 ID
* @param strSvcAct(String) transaction URL
* @param parameters(JSONObject) controller에서 getParam 으로 전달받을 인자값
* @param jsonParameter(JSONObjectArray) controller에서 request.getMapList로 전달받을 data
* @param strCallbackFunc(String) transaction 수행후 실행될 callback 함수
* @param bAsync(bool) 비동기 처리 여부 true(비동기), false(동기_
* @return
* @see   서버의 controller 를 호출하기 위한 함수
* @author 변형구
* @since  2013-02-27
* @version 1.0
*/

function gf_Transaction(strSvcID, strSvcAct, paramerters, jsonParameter, strCallbackFunc, bAsync )
{
	var targetID = $(this);
	var callbacks = $.Callbacks();
    var callFunction = null;
    var ajaxUrl = gContextPath+strSvcAct;
    var tranRslt = true;
    var parameter = {};

    if ( ajaxUrl.indexOf("?") >= 0 ) {
    	ajaxUrl += "&" + $.param(paramerters);
    }
    else {
    	ajaxUrl += "?" + $.param(paramerters);
    }

    if ( gf_IsNull(jsonParameter) ) {
    	parameter = jsonParameter;
    }

    var dataSetCnt = 0;

    var dataSetCnt = 0;
    for(var key in jsonParameter){
    	if(jsonParameter[key].length > 0){
    		parameter[key] = jsonParameter[key];
    		dataSetCnt++;
    	}
    }
    if(dataSetCnt == 0)
    	parameter["dummyObject"] = [{"a":"a"}]; // JsonReader의 오류 때문에 빈 Array를 실어보냄.

	parameter = JSON.stringify(parameter);
	/*$.each(jsonParameter, function(key, val){
		parameter[key] = JSON.stringify(val);
	});*/

    if ( !gf_IsNull(strCallbackFunc) ) {
    	callFunction = eval(strCallbackFunc);

    	if ( typeof callFunction != "function" ) {
          	gf_AlertMsg("call back function "+ strCallbackFunc + "is not defined!!");
          	return;
        }
    }

    // ajax 호출
    $.ajax({
    	type: "post",
    	url: ajaxUrl,
    	data: parameter,
		datatype: "json",
		headers: {
	        Accept : "application/json+sua; charset=utf-8",
	        "Content-Type": "application/json+sua; charset=utf-8"
	    },
		success: function(data){
					/*var tableString = "";
					var err = false;
					// 서버 실행 결과가 exception throw 일 경우의 처리
					if (data.exception != undefined) {
						err = true;
						var errMsg = item.split("//DETAIL//");
						msgAlert(errMsg[0]);
						// consol log 에 상세 exception 메세지 출력
						gf_Trace("%s: %o", errMsg[1], this);
					}
					// 에러 이면 리턴
					if ( err ) {
						tranRslt = false;
						return;
					}*/
					//  call callback function
					if ( !gf_IsNull(callFunction) ) {
						callbacks.add(callFunction);
		                callbacks.fire(strSvcID, targetID, data);
					}
		    	},
		error: function (xhr, ajaxOptions, thrownError) {
			if(xhr.status != 0){
				gf_AlertMsg(xhr.status);
				gf_AlertMsg(thrownError);
				gf_DisableCurrentOverlay();
			}
	    		},
	    async : bAsync
	});


}



/**
* @class
* @param strSvcID(String) transaction 을 구분할 ID
* @param resultData(JSONObject) json 형태의 ajax response 데이터
* @param bDispMsg(bool) message alert여부  true(alert), false(don't alert)
* @return true / false ( transaction 성공 여부 )
* @see Transaction 호출 후 정리 처리등의 메세지 처리.
* @author 변형구
* @since  2013-02-27
* @version 1.0
*/
function gf_ChkTransaction(strSvcId, resultData, bDispMsg )
{
	var tranRslt = true;
	// 서버 실행 결과가 exception throw 일 경우의 처리
	if (resultData.exception != undefined) {
		tranRslt = false;
		var errMsg = resultData.exception.split("//DETAIL//");
		if ( bDispMsg ) {
			gf_AlertMsg(errMsg[0]);
		}
		else {
			// consol log 에 exception 사용자 메세지 출력
			gf_Trace("%s: %o", errMsg[0], this);
		}
		// consol log 에 상세 exception 메세지 출력
		gf_Trace("%s: %o", errMsg[1], this);
	}
	else {
		if ( strSvcId.startsWith("SEL_") ||  strSvcId.startsWith("SELECT_") || strSvcId.startsWith("RETRIEVE_") || strSvcId.startsWith("RET_") ) {

		}
		else {
			gf_Trace("success message alert service id is " + strSvcId );
			gf_AlertMsg("co.suc.work", [" "]);
		}
	}
	return tranRslt;

}


