

/*
 * 01. 업무구분 			: 공통
 * 02. 스크립트 설명  	: 공통 component 관련 자바스크립트 라이브러리
 * 03. 작성자 				: 변형구
 * 04. 작성일 				: 2011.11.13
 */


/*----------------------------------------------------------------------------------
 * 설명   :  db 시퀀스 값 getting
 * 파라미터 : v_SequenceNm  -- db 시퀀스명
 * 리턴값   :
 * 작성자 : 변형구
 * 작성일 : 2011.11.13
 ----------------------------------------------------------------------------------------------*/

function gf_GetDBSequence( v_SequenceNm )
{
	var v_sSvcId		= "SEQNO";
	var v_sSvcAct		= "co/common/util/retrieveSequenceData.xpl";
	var v_sInDataset    = "";
	var v_sOutDataset	= "";
	var instcId = gf_getSValue("EtcInfo", "instcId");

	var v_sArgument		= "seqNm=" + gf_SetQuote(v_SequenceNm)
						+ " instcCd=" + instcId;
	var v_sCallback  = "";
	gf_Transaction(v_sSvcId, v_sSvcAct, v_sInDataset, v_sOutDataset, v_sArgument, v_sCallback, false);
	var returnVal = fv_GvSingleData;
	fv_GvSingleData = "";
	gf_Trace('gf_GetDBSequence->'+returnVal);
	return returnVal;
}

/*----------------------------------------------------------------------------------
 * 설명 		: param array를 param url로 변환
 *                gf_GetUrlWithParam( ['aaa=bbb,ccc=ddd&' ]);
 * 파라미터	: N/A
 * 리턴값 	    : 변환한 url
 * 작성자 		: 홍두희
 * 작성일 		: 2011.10
----------------------------------------------------------------------------------*/
function gf_GetUrlWithParam(params, aGubun) {
	var sGubun = '&';
	if (aGubun)
		sGubun = aGubun;
	var strRslt = "";
	for (var i in params) {
		strRslt += sGubun + i + "=" + gf_Hangle(params[i]);
	}
	if (strRslt.indexOf(sGubun) > -1)
		strRslt = strRslt.substring(sGubun.length, strRslt.length);
	return strRslt;
}

/*----------------------------------------------------------------------------------
 * 설명 		: 데이터셋에 공통코드를 등록한다. -- TODO: 향휴 comcomp.js 파일로 이전해야함
 * 작성자 		: 권준호
 * 작성일 		: 2013.03.05
----------------------------------------------------------------------------------*/
function gf_GetCommCds(CommGrpCd, dataSet, callback){
	gf_GetCommCdsFromCache(CommGrpCd, dataSet, callback);
	/*$.ajax({
		url : "/co/common/code/retrieveCommCdComboList.xpl",
		data: { queryCommGrpCd: CommGrpCd, sysCd : "cmas" },
		headers: {
	        Accept : "application/json+sua; charset=utf-8",
	        "Content-Type": "application/json+sua; charset=utf-8"
	    },
		async : false
	}).done(function(data) {
//		gf_Trace("data : " + JSON.stringify(data));
		dataSet.setAllData(data.output1);
		if(typeof(callback) != "undefined")
			callback(CommGrpCd, data);
	});*/

	/*var loclCd = gf_GetCookie("loclCd");

	if ( !gf_IsNull(gv_cmasComm.code[loclCd][CommGrpCd]) ) {
		dataSet.setAllData(gv_cmasComm.code[loclCd][CommGrpCd]);
		if(typeof(callback) != "undefined")
			callback(CommGrpCd, gv_cmasComm.code[loclCd][CommGrpCd]);
	}*/
}

/*----------------------------------------------------------------------------------
 * 설명 			: script 파일에 캐시된 공통코드를 데이터셑에 가져온다.
 * 작성자 		: 변형구
 * 파라미터		: CommGrpCd : 공통코드 그룹번호
 *       		  dataSet   : 데이터셑 개체
 *                callback  : callback 함수
 * 리턴값 	    : N/A
 * 작성일 		: 2013.03.13
----------------------------------------------------------------------------------*/
function gf_GetCommCdsFromCache(CommGrpCd, dataSet, callback){
	var loclCd = gf_GetCookie("loclCd");
	try {
		if ( !gf_IsNull(gv_cmasComm.code[loclCd][CommGrpCd]) ) {
			dataSet.setAllData(gv_cmasComm.code[loclCd][CommGrpCd]);
		}
		if(typeof(callback) != "undefined")
			callback(CommGrpCd, gv_cmasComm.code[loclCd][CommGrpCd]);
	}
	catch (e) {
		gf_Trace("common code getting error cause " + e.message);
	}

}

/*----------------------------------------------------------------------------------
 * 설명   :  공통코드 데이터셑 설정 함수
 * 파라미터 : v_sDsId      -- 데이터셑 아이디 ( 데이터셑 객체 아님 ),
			   v_sCommGrpCd -- 공통코드 그룹
 * 리턴값   :
 * 작성자 :
 * 작성일 : 2011.04.13
 ----------------------------------------------------------------------------------------------*/
function gf_SetCommCdDataset( v_sDsId, v_sCommGrpCd, ref1, ref2, ref3, ref4 )
{
	var refFilter = "";
	if ( !gf_IsNull(ref1) ) {
		if ( gf_IsNull(refFilter) ) {
			refFilter = "refCd1 == '"+ref1+"'";
		}
		else {
			refFilter = " && refCd1 == '"+ref1+"'";
		}
	}
	if ( !gf_IsNull(ref2) ) {
		if ( gf_IsNull(refFilter) ) {
			refFilter = "refCd2 == '"+ref2+"'";
		}
		else {
			refFilter = " && refCd2 == '"+ref2+"'";
		}
	}
	if ( !gf_IsNull(ref3) ) {
		if ( gf_IsNull(refFilter) ) {
			refFilter = "refCd3 == '"+ref3+"'";
		}
		else {
			refFilter = " && refCd3 == '"+ref3+"'";
		}
	}
	if ( !gf_IsNull(ref4) ) {
		if ( gf_IsNull(refFilter) ) {
			refFilter = "refCd4 == '"+ref4+"'";
		}
		else {
			refFilter = " && refCd4 == '"+ref4+"'";
		}
	}
	var commCdCacheXPlatformUseYn = false;
	if(gf_getSValue('EtcInfo', 'commCdCacheXPlatformUseYn') == 'true') {
		commCdCacheXPlatformUseYn = true;
	}
	if(commCdCacheXPlatformUseYn == true) {
		var filter = "commGrpCd==" + gf_SetQuote(v_sCommGrpCd);
		if (!gf_IsNull(refFilter)) {
			filter += " && " + refFilter;
		}
		gds_CommCd.filter(filter);
		var nRowCnt = eval(v_sDsId).copyData(gds_CommCd, true);
		v_sDsId = gds_CommCd;
		gds_CommCd.filter("");
	} else {
		var v_tmpDs         = new Dataset();
		this.addChild("v_tmpDs",v_tmpDs);
		var v_sSvcId		= "COMMCD";
		var v_sSvcAct		= "co/common/code/retrieveCommCdComboList.xpl";
		var v_sInDataset		= "";
		var v_sOutDataset	=  "v_tmpDs" + "=output1";
		var v_sArgument		= "queryCommGrpCd=" + gf_SetQuote(v_sCommGrpCd)
							    + " highLevelGroup="
							    + " highLevelCode="
							    + " refCd1=" + gf_SetQuote(ref1)
							    + " refCd2=" + gf_SetQuote(ref2)
							    + " refCd3=" + gf_SetQuote(ref3)
							    + " refCd4=" + gf_SetQuote(ref4);
		var v_sCallback  = "";
		gf_Transaction(v_sSvcId, v_sSvcAct, v_sInDataset, v_sOutDataset, v_sArgument, v_sCallback, false);
		var filter="";
		if (!gf_IsNull(refFilter)) {
			filter = refFilter;
		}
		v_tmpDs.filter(filter);
		var nRowCnt = eval(v_sDsId).copyData(v_tmpDs, true);
		v_tmpDs.filter("");
		this.removeChild("v_tmpDs");
	}
}


/*----------------------------------------------------------------------------------
 * 설명   : Dataset의 Row가 바뀔 때 Binding 되어 있는 Key 콤포넌트들의 Enable 여부를 결정한다.
 *           Key 콤포넌트는 Dataset의 rowType이 신규인 경우만 활성화 된다.
 * 파라미터  : ds Dataset
 * 파라미터  : e DSRowPosChangeEventInfo
 * 파라미터  : componentObj 콤포넌트 객체
 * 리턴값 : N/A
 * 작성자 :
----------------------------------------------------------------------------------*/
/*function gf_EnableKeyCompForRowChanged(ds:Dataset, e:DSRowPosChangeEventInfo, componentObj)
{
	if (ds.getRowType(e.newrow) ==  Dataset.ROWTYPE_INSERT)
	{
		componentObj.enable = true;
	}
	else
	{
		componentObj.enable = false;
	}
}
*/

/*----------------------------------------------------------------------------------
 * 설명   : Combo 객체에 '선택하세요' 항목을 추가해 준다.
 * 파라미터  : v_dsComboObj Combo 객체
 * 파라미터  : v_sValue 추가할 value(name)
 * 파라미터  : v_sCode 추가할 code
 * 파라미터  : v_nRow 추가할 Row Index
 * 파라미터  : v_sValueColumn 추가할 value의 컬럼명
 * 파라미터  : v_sCodeColumn 추가할 code의 컬럼명
 * 리턴값 : N/A
 * 작성자 :
----------------------------------------------------------------------------------*/
function gf_InsertDefaultRowForCombo(v_dsComboObj, v_sValue, v_sCode, v_nRow, v_sValueColumn, v_sCodeColumn) {
	var v_sValueColumnSel;
	var v_sCodeColumnSel;
	if (!gf_IsNull(v_nRow)) {
		v_dsComboObj.insertRow(v_nRow);
	} else {
		v_dsComboObj.insertRow(0);
	}
	if (gf_IsNull(v_sValueColumn)) {
		v_sValueColumnSel = 'value';
	} else {
		v_sValueColumnSel = v_sValueColumn;
	}
	if (gf_IsNull(v_sValueColumn)) {
		v_sCodeColumnSel = 'code';
	} else {
		v_sCodeColumnSel = v_sCodeColumn;
	}
	if (!gf_IsNull(v_sValue)) {
	    if ( v_sValue == '선택' ) {
			v_sValue == '선택하세요';
	    }
		v_dsComboObj.setColumn(0, v_sValueColumnSel, v_sValue);
	} else {
		v_dsComboObj.setColumn(0, v_sValueColumnSel, ' ');
	}
	if (!gf_IsNull(v_sCode)) {
		v_dsComboObj.setColumn(0, v_sCodeColumnSel, v_sCode);
	} else {
		v_dsComboObj.setColumn(0, v_sCodeColumnSel, '');
	}
}


/*----------------------------------------------------------------------------------
 * 설명   :  공통코드 데이터셑 쿼리 주입
 * 파라미터 : v_sDsId  -- 데이터셑 아이디 ( 데이터셑 객체 아님 ),
               v_sQuery -- 처리하고자 하는 쿼리string path 및 쿼리 Param (Get방식 표현식)
 * 리턴값   :
 * 작성자 :
 * 작성일 : 2011.04.13
 ----------------------------------------------------------------------------------------------*/
function gf_SetComboWithQuery( v_sDsId, v_sQuery )
{
	var v_sSvcId		= "COMMCD";
	var v_sSvcAct		= "co/common/util/retrieveSimpleData.xpl";
	var v_sInDataset		= "";
	var v_sOutDataset	=  v_sDsId + "=output";
	var v_sArgument		= "qUrlQuery=" + gf_SetQuote(v_sQuery);
	var v_sCallback  = "";
	var v_sUrl = v_sQuery.toLowerCase();
	gf_getAppUrl(v_sUrl);
	//gf_Trace('v_sUrl:' + v_sUrl +  ' / gv_CenterTx:' + gv_CenterTx);
	gf_Transaction(v_sSvcId, v_sSvcAct, v_sInDataset, v_sOutDataset, v_sArgument, v_sCallback, false, gv_CenterTx);
}

/*----------------------------------------------------------------------------------
* 설명 :    캘린더 컴포넌트 이벤트 주입
* 파라미터 :    calendar -
* 리턴값 :  none
* 작성자 : 변형구
* 작성일 : 2011.11.11
---------------------------------------------------------------------*/
/*function gf_CalendarFromToValidate(calSrce:Calendar, calComp:Calendar, sValidExpr) {
    calSrce._compObj = calComp;
    calSrce._validExpOri = sValidExpr;
    calComp._compObj = calSrce;
	calSrce.canchange.addHandler(
		function(obj:Calendar, e:ChangeEventInfo)
		{
			var calToValue = obj._compObj.value;
		    obj.validExp     = obj._validExpOri + calToValue;
		}
	);
	calComp.canchange.addHandler(
		function(obj:Calendar, e:ChangeEventInfo)
		{
			//var calToValue = objCalComp.value;
		    obj._compObj.validExp     = obj._compObj._validExpOri + e.postvalue;
		}
	);
}
*/

/*----------------------------------------------------------------------------------
 * 설명 		: span태그에 사용자검색 컴포넌트를 세팅한다. -- TODO: 향휴 comcomp.js 파일로 이전해야함
 * 작성자 		: 권준호
 * 작성일 		: 2013.03.05
----------------------------------------------------------------------------------*/
function gf_SetUserComponent(elm, callback){

	elm.css({overflow: "hidden", "vertical-align": "middle"})
				    .html("<input id=\""+elm.attr("id")+"_id\" type=\"text\" />"
						 +"<input id=\""+elm.attr("id")+"_name\" type=\"text\" />"
						 +"<span id=\""+elm.attr("id")+"_btn\" class=\"ui-icon ui-icon-search\"></span>");
	var userIdBox = elm.find("input[id$='_id']");
	var userNameBox = elm.find("input[id$='_name']");
	var userSearchBtn = elm.find("span[id$='_btn']");

	userIdBox.css({width : (elm.width()-34)/2, "float" : "left"});
	userNameBox.css({width : (elm.width()-24)/2, "float" : "left"});
	userSearchBtn.css({width:16, height:16, "float": "right", "display" : "inline-block"});


	userNameBox.keypress(function(event) {
		if (event.which == 13) {
			if(userNameBox.val() == "")
			{
				return;
			}
			$.ajax({
				url : gv_ContextPath + "/co/common/user/retrieveUserList.xpl",
				data: { searchVal: userNameBox.val() },
				headers: {
			        Accept : "application/json+sua; charset=utf-8",
			        "Content-Type": "application/json+sua; charset=utf-8"
			    }
			}).done(function(data) {
				var dataLength = (data == null) ? -1: data.length;
				switch (dataLength)
				{
					//검색 된 사용자가 1건 일 경우
					case 1 :
						//데이터 리턴
						userIdBox.val(data.userId);
						userNameBox.val(data.userKnm);
						if(typeof(callback) != "undefined")
							callback(data);
						break;
					default :
						userSearchBtn.trigger('click');
						break;
				}
			});
			event.stopPropagation();
		}
	});

	userSearchBtn.click(function(event){
		var callbackFunc = "callback_"+$.now();
		window[callbackFunc] = function(data){
			userIdBox.val(data.userId);
			userNameBox.val(data.userKnm);

			if(typeof(callback) != "undefined")
				callback(data);
		};

		var userNm = userNameBox.val();
		window.open(gv_ContextPath + "/common/jsp/comp/userSelect.jsp?userNm="+encodeURIComponent(userNm) + "&callbackFunc="+callbackFunc,"","toolbar=no,scrollbars=no,width=600,height=420");
	});
}


/*----------------------------------------------------------------------------------
 * 설명 		: Grid에 셀렉터박스를 삽입함
 * 작성자 		: 권준호
 * 작성일 		: 2013.03.05
----------------------------------------------------------------------------------*/
function gf_BindInnerSelect(curVal,opt, select) {
	var index = 0 ;
	var options = select.options;

	while(index < options.length){
		if(curVal == options.item(index).text){
			options.selectedIndex = index;
			$(select).trigger("change");
			break;
		}
		index++;
	}
	if(index == options.length){
		options.selectedIndex = 0;
		$(select).trigger("change");
	}

    return select;
}

/*----------------------------------------------------------------------------------
 * 설명 		: Grid에 해당 컬럼의 에디터모드가 종료되었을때... 해당 Cell에 데이터를 삽입함.
 * 작성자 		: 권준호
 * 작성일 		: 2013.03.05
----------------------------------------------------------------------------------*/
function gf_BindValue(elem, oper, value) {
    if(oper === 'get') {
       return $(elem).val();
    }
}

/*----------------------------------------------------------------------------------
 * 설명 		: Grid화면에 출력되는 포맷설정.
 * 				- 일반모드 :  해당 Row가 그려질때 출력될 Cell의 값을 설정
 * 				- 에디터모드 : 해당 Row가 에디터가 끝났을때 Cell의 데이터를 핸들링하는 경우
 * 작성자 		: 권준호
 * 작성일 		: 2013.03.05
----------------------------------------------------------------------------------*/
function gf_BindView(changVal, row, gOpt, opt){
	var dataSet = opt.dataSet;
	var curVal = opt.curVal;
	var val = opt.val;
	var text = opt.text;

	if(typeof(row.oper) == "undefined"){
		var grid = $("#"+gOpt.gid);
		var valRow = "";
        var rowId = gOpt.rowId;
        var dataSetRow = grid.data(rowId);
		try{
			valRow = dataSet.findRow(val, dataSetRow.get(curVal));
			return valRow.get(text);
			//grid.jqGrid("setCell", rowId, gOpt.colModel.name, changVal);
		}catch(e){
			return "";
		}
	}else if(row.oper == "edit"){
		var grid = $("#"+gOpt.gid);
		var rowId = gOpt.rowId;
		var name = gOpt.colModel.name;
		var dataSetRow = grid.data(rowId);
		var nameVal = dataSet.findRow(val, changVal).get(text);

		dataSetRow.set(curVal, changVal);
		if(dataSetRow.get(name) == undefined){
			//grid.jqGrid("setRowData", rowId, dataSetRow.get());
			grid.jqGrid("setCell", rowId, name, nameVal);
		}else{
			dataSetRow.set(name, nameVal);
			//grid.jqGrid("setRowData", rowId, dataSetRow.get());
		}

	}
}

/*----------------------------------------------------------------------------------
 * 설명 		: Grid화면에 출력되는 포맷설정.
 * 				- 일반모드 :  해당 Row가 그려질때 출력될 Cell의 값을 설정
 * 				- 에디터모드 : 해당 Row가 에디터가 끝났을때 Cell의 데이터를 핸들링하는 경우
 * 작성자 		: 권준호
 * 작성일 		: 2013.03.05
----------------------------------------------------------------------------------*/
function gf_ImageView(imgSrc, row, gOpt, opt){

	if(typeof(row.oper) == "undefined"){
		return "<img src=\""+ imgSrc +"\" >";
	}
}


/*----------------------------------------------------------------------------------
 * 설명 		: Grid화면에 출력되는 문서상태와 결재 상태 색설정.
 * 작성자 		: 이광우
 * 작성일 		: 2013.07.11
----------------------------------------------------------------------------------*/

function gf_StatusTextColor(TYPE,val, gOpt, row){
	 if(TYPE=="DOC"){
	 	var grid = $("#"+gOpt.gid);
        var rowId = gOpt.rowId;
        var dataSetRow = grid.data(rowId);
		var docStsCd = dataSetRow.get("docStsCd");
		var docStsNm = dataSetRow.get("docStsNm");
		if(typeof(row.oper) == "undefined"){
			if(docStsCd == "D04" || docStsCd == "D05")
				return "<span style=\"color:#FF0000;\">" + docStsNm + "</span>";
			else if(docStsCd == "D11" || docStsCd == "D12")
				return "<span style=\"color:#0000FF;\">" + docStsNm + "</span>";
			else if(docStsCd == "D16" )
				return "<span style=\"color:#32cd32;\">" + docStsNm + "</span>";
			else if(docStsCd == "D13" || docStsCd == "D14")
				return "<span style=\"color:#008b8b;\">" + docStsNm + "</span>";
			else
				return docStsNm;
		}

	} else if (TYPE=="SIGN"){

		var grid = $("#"+gOpt.gid);
        var rowId = gOpt.rowId;
        var dataSetRow = grid.data(rowId);
		var signStsCd = dataSetRow.get("signStsCd");
		var signStsNm = dataSetRow.get("signStsNm");
		if(typeof(row.oper) == "undefined" || row.oper == "edit"){
			if(signStsCd == "S05" || signStsCd == "S06")
				return "<span style=\"color:#FF0000;\">" + signStsNm + "</span>";
			else if(signStsCd == "S10")
				return "<span style=\"color:#0000FF;\">" + signStsNm + "</span>";
			else
				return signStsNm;
		}
	 }
}
