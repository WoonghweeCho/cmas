<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="사용자지정" name="title" />
	<jsp:param value="popup" name="type" />
</jsp:include>

<jsp:include page="/common/jsp/comp/fileupload/uploadComp.jsp" />

<ut:script src="${contextPath}/common/jsp/comp/excel/uploadExcel.js"></ut:script>

<script type="text/javascript">
	var gContextPath = "${contextPath}";
	$.jgrid.no_legacy_api = true;
	$.jgrid.useJSON = true;
	var datas = ${param.datas}; // 파라메터로 인코딩 된 데이터

	var ds_ExcelParseData = new DataSet();

	var v_FstLvlPop = true; //팝업 레벨
	var v_IsModal = true; 	//Modal Popup 여부
	var v_Callback = "";


	// 화면이 로드될시 수행되는 함수
	$(function(){
		v_FstLvlPop = gf_IsNull(datas.fstLvlPop) ? v_FstLvlPop : eval(datas.fstLvlPop);
		v_IsModal = gf_IsNull(datas.isModal) ? v_IsModal : eval(datas.isModal);
		v_Callback = gf_IsNull(datas.callbackFunc) ? "" : datas.callbackFunc;
		// 파일 업로드를 excel upload 모드, singlefile upload만 되도록 처리
		gf_InitFileUploadComponent("excel", false);
		gf_SetUploadCallback("f_ExcelUploadProcess");
		gf_setMode("upload");

		// 업로드 버튼 이벤트 등록
		$("#btnUpload").click(function(){
			gf_onFileUpload();
		});

		// 닫기 버튼 이벤트 등록
		$("#btnClose").click(function(){
			self.close();
		});


		// child window를 모두 close 처리 하고 modal 일경우 부모창을 비활성화 시킨 것을 다시 활성화 처리 한다.
		gf_SetPopCloseEvent(v_IsModal, v_FstLvlPop);
		// Modal 처리를 위한 공통 함수 호출
		gf_EnableOverlayOpener(false, v_FstLvlPop, v_IsModal);

	} );

	// 첨부된 엑셀 파일이 업로드된후 수행된다.
	// 실제 엑셀 파일내에서 데이터 추출이 일어나는 Controller 호출
	function f_ExcelUploadProcess(fileAtchId) {

		// 여기가 업로드된 엑셀 파일을 처리하는 Controller 호출 하는 부분이다.
		// 업로드된 엑셀 파일을 읽어 데이터를 추출하기위해 controller로 보내야할 인자를 설정한다.
		// fileAtchId 는 업로드된 엑셀파일의 fileAtchId 이며
		// sheetName 은 특정 시트의 데이터를 읽으려 할경우 해당 시트의 명칭이다.
		// mappedData 는 엑셀의 헤더 값과 데이터 필드의 매핑 정의 이다.
		var param = { "fileAtchId":fileAtchId, "sheetName":datas.sheetNm, "mappedData":datas.mapperData };

		gf_Transaction("SELECT_UPLOADEXCEL", "/co/common/excel/importCommExcel.xpl", param, {}, "f_ExcelUploadCallback", false);
	}

	// 엑셀 데이터 추출후 opener 의 함수를 호출하여 추출한 데이터를 저장하는 함수
	function f_ExcelUploadCallback(strSvcId, obj, resultData){

		  // transaction의 정상 처리 여부를 판단한다.
		  if (!gf_ChkTransaction(strSvcId, resultData, true )) {
			  return;
		  }

		  if ( strSvcId == 'SELECT_UPLOADEXCEL' ) {

			  ds_ExcelParseData.setAllData(resultData["importData"]);
			  // 추출한 엑셀 데이터셑 저장 하는 controller 호출
			  gf_Transaction("EXCEL_SAVE", datas.saveControllerPath, {}, {input : ds_ExcelParseData.getAllData()}, "", false);
			  if (!gf_IsNull(v_Callback)) {
				  var saveFuncNm = "opener."+v_Callback;
				  //alert(saveFuncNm);
				  eval(saveFuncNm+"()");
				  self.close();
			  }

		  }
	}

</script>
</head>
<body id="popup_body">
	<h3>${param.title }</h3>
	<div id="popup_wrap_mini">
		<div class="top_bar">
			<span class="title">엑셀업로드</span>
		</div>
		<div id="content">
			<!--BTN-->
			<div class="btn-area">
				<a class="btn s1" id="btnUpload"><span>업로드</span></a>
				<a class="btn s2" id="btnClose"><span>닫기</span></a>
			</div>
			<br>

			<div class="box">
				<div name="fileComponent" class="upload"></div>
			</div>
			<!--//box-->

		</div>
		<!--//contents-->
	</div>


</body>
</html>
