<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp" %>
<html>
<head>
	<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
		<jsp:param value="사용자지정" name="title"/>
		<jsp:param value="popup" name="type"/>
	</jsp:include>
	<ut:script src="${contextPath}/common/jsp/comp/userNorg/orgUserSelect.js"></ut:script>
<script type="text/javascript">
var gContextPath = "${contextPath}";
$.jgrid.no_legacy_api = true;
$.jgrid.useJSON = true;
var datas = ${param.datas};	// 파라메터로 인코딩 된 데이터
</script>
</head>
<body id="popup_body">
<h3>${param.title }</h3>

<div id="popup_wrap" >
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

				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<col width="110px"/>
					<col width=""/>
					<tr>
						<td class="tit">
							<span>부서</span>/<span>사원명</span>
						</td>
						<td class="inpt"><input name="txtSrchTxt" id="txtSrchTxt"  type="text" style="width:250px" class="int_s1"/></td>
					</tr>
				</table>

			</div>
			<div class="bg_B"><span class="left"></span><span class="right"></span></div>
		</div>
		<!--//검색설정-->

		<br>

		<div class="box">

			<div class="half f_l" style="height:320px;">
				<h3>부서목록</h3>
					<div class="btn-area f_r">
						<a id="treeReset" class="btn s4"><span>초기화</span></a>
					</div>
				<div class="area_line" style="height:330px;overflow-x:hidden;overflow-y:scroll;display:block;min-height:115px;padding:10px;">
					<!-- grid 용 table  -->
					<ul id="orgTree" class="ztree"></ul>
				</div>

			</div>
			<div class="half f_r" style="height:160px;">
				<h3>직원목록</h3>

				<!--리스트-->
				<div class="list_st2" style="min-height:110px;">
					<!-- grid 용 table  -->
					<table id="grdUserLst" style="width:100%;"></table>
				</div>
				<!--//리스트-->

			</div>
			<div class="half f_r" style="height:200px;">
				<h3>지정된사용자</h3>
				<div class="btn-area" style="text-align:center;">
					<a class="btn s4" id="btnMoveDown" ><span>▼</span></a>
					<a class="btn s4" id="btnMoveUp" ><span>▲</span></a>
				</div>

				<!--리스트-->
				<div class="list_st2" style="min-height:180px;">
					<!-- grid 용 table  -->
					<table id="grdSelUserLst" style="width:100%"></table>
				</div>
				<!--//리스트-->

			</div>
			<div class="clear"></div>
		</div> <!--//box-->

	</div><!--//contents-->
</div>

</body>
</html>