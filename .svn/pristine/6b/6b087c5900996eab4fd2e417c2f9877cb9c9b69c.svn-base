<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String userNm = request.getParameter("userNm");
String callbackFunc = request.getParameter("callbackFunc");
%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp" %>
<html>
<head>
	<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
		<jsp:param value="사용자지정" name="title"/>
		<jsp:param value="popup" name="type"/>
	</jsp:include>
<script type="text/javascript">
$.jgrid.no_legacy_api = true;
$.jgrid.no_legacy_api = true;
$.jgrid.useJSON = true;
var output1 = new DataSet();
var callbackFunc = "<%=callbackFunc%>";

$(document).ready(function() {
	$("#btnSearch").click(function(){
		f_Search();
	});

	$("#btnConfirm").click(function(){
		//$("#userList").trigger("jqGridDblClickRow");
		if ( gf_IsNull($( "#userList" ).getGridParam( "selrow" ))) {
			gf_AlertMsg("sg.inf.notSelectUser");
			return;
		}
		$("#userList").jqGrid("getGridParam", "ondblClickRow").call($("#userList")[0], $( "#userList" ).getGridParam( "selrow" ));
		//self.close();
	});
	$("#btnClose").click(function(){
		self.close();
	});

	$("#userList").jqGrid({
		datatype: "local",
		height: 250,
	   	colNames:[gf_FindLang('순번'),gf_FindLang('사용자ID'), gf_FindLang('사용자명'), gf_FindLang("직위"),gf_FindLang('소속조직'),gf_FindLang('소속조직명'),gf_FindLang('사용여부'), gf_FindLang('직책'), gf_FindLang('userEnm'),gf_FindLang('현발령')],
	   	colModel:[
	  	   	{name:'seq',index:'seq', width:30, sorttype:"int"},
	   		{name:'userId',index:'userId', width:55},
	   		{name:'userKnm',index:'userKnm', width:80,align:"center"},
	   		{name:'userPositCd',index:'userPositCd', width:80},
	   		{name:'orgCd',index:'orgCd', width:70},
	   		{name:'orgNm',index:'orgNm', width:150},
	   		{name:'useYn',index:'useYn', width:50,align:"center"},
	   		{name:'userRpswrkCd',index:'userRpswrkCd', width:50,align:"center", hidden : true},
	   		{name:'userEnm',index:'userEnm', width:50,align:"center", hidden : true},
	   		{name:'apptOrgCd',index:'apptOrgCd', width:50,align:"center", hidden : true}
	   	],
	   	width:570,
	   	height:230,
	   	sortname: 'seq',
	    //viewrecords: false,
	    //autowidth : false,
	    shrinkToFit : false,	// 그리드 크기에 컬럼을 비율로 맞출지 여부
	    sortorder: "asc",
	   	multiselect: false,		// 멀티ROW셀렉터 사용 여무
//	   	caption: "사용자 목록",
	   	ondblClickRow : function(rowid,iRow,iCol,e){

	   		var row = $(this).getRowData(rowid);
	   		opener.window[callbackFunc](row);
	   		window.close();
	   	}
	});
	$("#userList").jqGrid('navGrid','#userListPager',{edit:false,add:false,del:false});
	$("#userList").data("nextVal", 1);

	fn_MlangSet();

	/**
	 * Enter Key Event 처리
	 * - 검색영억에 Focus가 가 있을 때만 Enter Key Event를 처리한다.
	 */
	$("#table_sign_search").bind('keypress', function(e) {
	    if (e.which == 13) {
	    	f_Search();
	    }
	});

	function f_Search(){
		var searchVal = $("input[name='userNm']").val();
		// 검색조건 입력하지 않았을시에 조회를 막는다.
		if(searchVal == "") return;

		$.ajax({
			url : gv_ContextPath + "/co/common/user/retrieveUserList.xpl",
			data: { searchVal: $("input[name='userNm']").val(), useYn : "Y" },
			headers: {
		        Accept : "application/json+sua; charset=utf-8",
		        "Content-Type": "application/json+sua; charset=utf-8"
		    }
		}).done(function(data) {
			output1.setAllData(data.output1);
			$("#userList").clearGridData();
			$("#userList").data("nextVal", 1);
			var viewList = output1.view();
			for(var i=0;i<viewList.length;i++){
				viewList[i].seq = i+1;
				$("#userList").jqGrid('addRowData',i+1,viewList[i].get());
			}
			$("#userList").data("nextVal", viewList.length + 1);
		});
	}

	f_Search();
});
</script>
</head>

<body id="popup_body">
<div id="popup_wrap_mini">
	<div class="top_bar">
    	<span class="title">사용자지정</span>
    </div>
    <div id="content">
		<!--BTN-->
		<div class="btn-area">
			<a class="btn s1" id="btnSearch"><span>조회</span></a>
			<a class="btn s1" id="btnConfirm"><span>확인</span></a>
			<a class="btn s5" id="btnClose"><span>닫기</span></a>
		</div>
		<!--검색설정-->
		<div class="int_box">
			<div class="bg_T"><span class="left"></span><span class="right"></span></div>
			<div class="content">

				<table id="table_sign_search" width="100%" border="0" cellspacing="0" cellpadding="0">
					<col width="60px"/>
					<col width=""/>
					<tr>
						<td class="tit">성명</td>
						<td class="inpt"><input type="text" name="userNm" id="userNm" value="<%=userNm%>" style="width:150px" class="int_s1"/></td>
					</tr>
				</table>

			</div>
			<div class="bg_B"><span class="left"></span><span class="right"></span></div>
		</div>
		<!--//검색설정-->
		<br>
		<div class="box">
		<!--리스트-->
			<div class="list_st2" style="min-height:150px;">
				<table id="userList" style="width: 320px;"></table>
				<div id="userListPager"></div>
			</div>
			<div class="clear"></div>

		</div>

	</div><!--//contents-->
</div>
</body>
</html>