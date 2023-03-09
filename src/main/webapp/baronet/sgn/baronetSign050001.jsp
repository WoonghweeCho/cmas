<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Properties" %>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@taglib prefix="c" uri="/c_rt" %>
<%@taglib prefix="fmt" uri="/fmt" %>
<%@taglib prefix="fn" uri="/fn" %>
<%@taglib prefix="ut" uri="/ut" %>
<%
	WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
	Properties prop = (Properties) context.getBean("appProperties");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!-- HTTP Request POST Data 처리 -->
<script type="text/javascript">
	var gContextPath = "${contextPath}";
	var datas = "${param.datas}";

	var c_contentLink = "";

	$(document).ready(function() {
		getData();
	});

	//http://dwdev.dwconst.co.kr/gw/app/bult/dplaza.nsf/wAgtMobileApp?OpenAgent&unid=${param.unid}
	var getData = function() {
		var parameter = {};
		parameter["param"] = [{
		            "username": "${param.username}",
		            "password": "${param.password}",
		            "goURL": "/gw/app/bult/dplaza.nsf/wAgtMobileApp?OpenAgent&unid=${param.unid}"
		    	}];
		$.ajax({
			type: "post",
		    url: "<c:url value="/baronet/sgn/baronetDocViewer.baro" />",
		    data : JSON.stringify(parameter),
		    datatype: "json",
		    contentType: "application/json+sua; charset=utf-8",
		    success: function(data){
		    	//서버에서 data.result[0].* 로 넘어온다.(JSONArray 형태로 넘어옴.)
		    	if(data.result != undefined) {
		    		var result = data.result[0];

		    		if(result != undefined && result.result == "dataexist" ) {
		    			$("#c_Subject").text(result.c_Subject);
		    			$("#c_authorName").text(result.c_authorName + "/" + result.c_authorOrgName + "/" + result.c_authorGradeName);
		    			$("#c_AuthorOfficeTelNo").text(result.c_AuthorOfficeTelNo);
		    			$("#c_Categoies").text(result.c_Categoies);
		    			$("#c_Type").text(result.c_Type);
		    			$("#c_RequiredRead").text(result.c_RequiredRead == "0" ? "없음" : "필독");
		    			$("#c_dtmsConfirm").text(result.c_dtmsConfirm);

		    			//결재 URL 세팅
		    			$("#signURL").attr("data-url", result.signurl);
		    			$("#rejectURL").attr("data-url", result.rejecturl);

		    			//업무공지 내용
		    			//getContents(result.c_contentLink);
		    			//$("#notice").attr("src", login + result.c_contentLink);
		    			//console.log(login + result.c_contentLink);

		    			c_contentLink = result.c_contentLink;
		    		}
		    		else {
		    			fn_alert(result.result);
		    		}
		    	}
		    	else {
		    		fn_alert("데이터가 없습니다.");
		    	}
		    }
		});
	};

	var getContents = function(contentLink) {
		var parameter = {};
		parameter["param"] = [{
		            "username": "${param.username}",
		            "password": "${param.password}",
		            "goURL": contentLink
		    	}];
		$.ajax({
			type: "post",
		    url: "<c:url value="/baronet/sgn/baronetDocContents.baro" />",
		    data : JSON.stringify(parameter),
		    datatype: "json",
		    contentType: "application/json+sua; charset=utf-8",
		    success: function(data){
		    	//서버에서 data.result[0].* 로 넘어온다.(JSONArray 형태로 넘어옴.)
		    	if(data.result != undefined) {
		    		var result = data.result[0];

		    		if(result != undefined ) {
		    			$("#notice").html(result.contents);
		    			$("#notice").find("img").each(function(idx) {
		    				var src = $(this).attr("src");
		    				$(this).attr("src", "<%=prop.getProperty("dwe.baronetgw.domain") %>" + src);
		    			});
		    		}
		    	}
		    }
		});
	};
</script>

	<!--
		Class Name 	: baronetDocViewer.jsp
		Description : 결재문서열람 - 업무공지
	 -->
	<p class="pageTit">업무공지</p>

	<table class="tb">
		<tbody>
			<tr>
				<th>제목</th>
				<td id="c_Subject"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td id="c_authorName"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td id="c_AuthorOfficeTelNo"></td>
			</tr>
			<tr>
				<th>분류</th>
				<td id="c_Categoies"></td>
			</tr>
			<tr>
				<th>언어</th>
				<td id="c_Type"></td>
			</tr>
			<tr>
				<th>필독구분</th>
				<td id="c_RequiredRead"></td>
			</tr>
			<tr>
				<th>DTMS 제/개정</th>
				<td id="c_dtmsConfirm"></td>
			</tr>
		</tbody>
	</table>

	<%-- <p style="width:100%; text-align:center;"><button id="viewContent" name="viewContent" class="btn gray" onclick="showLayer();" >내용보기</button></p>

	<div id="contLay" style="background:rgba(255,255,255,0.9); position:absolute; top:52px; display:none; width:100%; height:80%; z-index:1005; margin:auto;">
		<p style="float:right;"><a href="javascript:closeLayer();"><img src="${contextPath }/common/images/top/btn_close.png" /></a></p>
		<div id="contFrame" style="height:100%; text-align:center;">
			<iframe id="notice" name="notice" src="about:blank" border="0" frameborder="0" scrolling="auto" style="width:100%; height:100%;"></iframe>
		</div>
	</div>

	<form id="frm" name="frm" method="get" action="baronetDummy.jsp" target="notice">
		<input type="hidden" name="username" value="MTIwMjQ1NA==" /><!-- 1202454 -->
		<input type="hidden" name="password" value="YjEyMDI0NTQ=" /><!-- b1202454 -->
		<input type="hidden" name="contentLink" value="/gw/app/bult/dplaza.nsf/0/EBE27827EA67D9C949257F1F002148D1/Body/M1.1?OpenElement" />
	</form>

	<form id="frm1" name="frm1" method="post" action="http://m.dwconst.co.kr/names.nsf?Login" target="notice">
		<input type="hidden" name="username" value="1202454" /><!-- 1202454 -->
		<input type="hidden" name="password" value="b1202454" /><!-- b1202454 -->
		<input type="hidden" name="RedirectTo" value="/gw/app/bult/dplaza.nsf/0/EBE27827EA67D9C949257F1F002148D1/Body/M1.1?OpenElement" />
	</form> --%>

	<script type="text/javascript">
		/* function showLayer() {
			if (navigator.platform == 'iPad' || navigator.platform == 'iPhone' || navigator.platform == 'iPod' || navigator.platform == 'Linux armv6l') {
				//$("#contFrame").html("아이폰에서 준비중입니다.");
				$("#frm1").submit();
			}
			else {
				//$("#notice").attr("src", login + encodeURIComponent(c_contentLink));
				$("#frm1").submit();
			}
			$("#mask").css("display", "inline-block");
			$("#contLay").css("display", "inline-block");
		}

		function closeLayer() {
			$("#mask").css("display", "none");
			$("#contLay").css("display", "none");
		} */
	</script>
