<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp"%>
<html>
<head>
<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
	<jsp:param value="proxy" name="popupType"/>
	<jsp:param value="시내교통비" name="title" />
	<jsp:param value="popup" name="type" />
</jsp:include>
<ut:script src="${contextPath}/trip/cityTransp/cityTranspAddP.js"></ut:script>

<script type="text/javascript">
	var gContextPath = "${contextPath}";
	$.jgrid.no_legacy_api = true;
	$.jgrid.useJSON = true;
	var datas = ${param.datas}; // 파라메터로 인코딩 된 데이터
</script>
</head>

<body id="popup_body">
<!--
	Class Name 	: excluReglSelectP.jsp
	Description : 전결규정 조회
 -->
 <!--right-->
	<!-- POPUP_WRAP -->
	<div id="popup_wrap">


	<!-- TOP BAR -->
	<div id="popup_wrap">
		<div class="top_bar">
			<span class="title">시내교통비 입력</span>
		</div>
		<!-- CONTENT -->
		<div id="content">
			<!-- Button -->
			<div class="btn-area">
				<a class="btn s1" id="confirmBtn"><span>확인</span></a>
				<a class="btn s5" id="closeBtn"><span>닫기</span></a>
			</div>

			<div class="list_st1">

				<table id="docInfo" >
						<tr>
							<td class="tit" style="width:20%">소속팀/팀코드</td>
							<td class="inpt" style="width:80%"><span id="tripUserOrgCd"></span></td>
						</tr>
						<tr>
							<td class="tit" style="width:20%">신청자</td>
							<td class="inpt" style="width:80%">
								<span id="tripUser"></span> <a href="javascript:;" class="button-light" id="reSearch">신청자검색</a>
							</td>
						</tr>
						<tr id="empListTr">
							<td class="tit" style="width:20%">신청자 검색</td>
							<td class="inpt" style="width:80%">
								<div>* 소속팀 인원만 조회됩니다.</div>
								<input type="text" name="txtSrchTxt" style="width: 120px" /><span id="searchBtn" class="ui-icon ui-icon-search" lang="kr" style="width: 16px; height: 16px; display: inline-block;"></span>
								<a href="javascript:;" class="button-light" id="filterNull" style="float: right;">전체조회</a><a href="javascript:;" class="button-light" id="otherTUser" style="float: right;">타팀검색</a>
								<!--리스트-->
								<div class="list_st2" style="min-height:150px;">
									<table id="empList" style="width: 100%;"></table>
									<div id="empListPager"></div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:20%">사용일</td>
							<td class="inpt" style="width:80%">
								<input type="text" name="useDate" style="width:90px" class="int_s1 f_l">
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:20%">출발지</td>
							<td class="inpt" style="width:80%"><input type="text" id="t1"> 출발, 도착, 목적지는 구체적으로 표현</td>
						</tr>
						<tr>
							<td class="tit" style="width:20%">도착지</td>
							<td class="inpt" style="width:80%"><input type="text" id="t2"> (예:본사, 논현동, 당산동 등)</td>
						</tr>
						<tr>
							<td class="tit" style="width:20%">목적지</td>
							<td class="inpt" style="width:80%"><input type="text" id="t3"> (예:서울시청, OO현장, OO회사 등)</td>
						</tr>
						<tr>
							<td class="tit" style="width:20%">교통수단</td>
							<td class="inpt" style="width:80%">
								<select id="tType">
									<option value="B">버스</option>
									<option value="S">선박</option>
									<option value="M">지하철</option>
									<option value="T">택시</option>
									<option value="1">항공</option>
									<option value="2">고속철도</option>
									<option value="3">개인차량</option>
								</select> &nbsp;&nbsp;
								<select id="tRound">
									<option value="R" selected="selected">왕복</option>
									<option value="D">편도</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:20%">사용목적</td>
							<td class="inpt" style="width:80%"><input type="text" id="tPurp"> ※ 사적 사용금지</td>
						</tr>
						<tr>
							<td class="tit" style="width:20%">금액</td>
							<td class="inpt" style="width:80%"><input type="text" id="tAmount"> 원</td>
						</tr>

						<tr>
							<td colspan="2">
							<span>
								<b>
								<font color="blue">
								<br>
								[ 현장경비 예산 선택시 추가 입력란(필수) ]
								</font>

								</b>
							</span>
							 </td>
						</tr>

						<tr>
							<td class="tit" style="width:20%">현장경비종류</td>
							<td class="inpt" style="width:80%">
								<select id="tSiteExpKind">
									<option value="0">미선택</option>
									<option value="1">시내업무교통비</option>
									<option value="2">귀가여비</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="tit" style="width:20%">이동거리</td>
							<td class="inpt" style="width:80%">
								<input type="text" id="tMvDist" style="width:120px"> km
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="http://map.naver.com/" class="button-light" id="searchDist" target="_blank">이동거리 조회(지도)</a>
							</td>
						</tr>

						<tr>
							<td colspan="2">
							<span>
								<b>
								<font color="red">
								 -현장경비 예산 선택시 유의사항- <br>
								 ※ 소요거리 작성시 직행 기준 거리 입력 <br>
								 ※ 시내업무교통비(한도금액) : 2만원(증빙서류 없을시 5천원 / 지하철,버스) <br>
								 ※ 귀가여비(개인차량 한도금액) : KTX기준 총소요금액 한도 내 200원/km 기준 <br>
								 ※ 대중교통 이용 정기출퇴근처리 : 귀가여비 선택 및 사용목적 대중교통출퇴근 명시, 내부품의를 증빙으로 첨부<br>
								 &nbsp;&nbsp;(내부품의 시 비용 산출근거 명시, 개인차량유지비(유류비) 중복 지급 불가 내용 반영)<br>
 								</font>
								 ※ 현장경비 담당자 : 예산관리팀 이선호 과장 (02-2288-2836)<br>
								</b>
							</span>
							 </td>
						</tr>



				</table>
			</div>

		</div>
		<!--//CONTENTS-->
		<div class="bottom_bg"><span class="left"></span><span class="right"></span></div>
	</div>
	<!--//right-->

</body>
</html>