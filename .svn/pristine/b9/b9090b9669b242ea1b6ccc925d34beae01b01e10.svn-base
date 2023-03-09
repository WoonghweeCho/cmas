<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp" %>
<html>
<head>
	<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
		<jsp:param value="공통업무시스템" name="title"/>
	</jsp:include>
	<script type="text/javascript">
	// 파라미터로 전달되는 메뉴 로딩시 처음 선택될 메뉴 코드
	var gv_initMenuCd = "${cookie.menuCd.value}";
	var gv_viewMenu   = "${cookie.viewMenu.value}";
	// 메뉴 로딩시 처음 선택될 메뉴 아이디
	var v_initMenuId = "";
	// 샘플 매뉴 아이디
	var v_sampleMenuID = "";
	// 결재관리 메뉴 아이디
	var v_manageMenuID = "";
	var v_fixMenu = "";
	/**
	* @function
	* @param isPageLoad<boolean> 페이지 로드시에 는 true 그외 의 경우 false. null 일경우는 true로 처리한다.
	* @see   왼쪽 메뉴를 구성한다. 전역 메뉴변수를 읽어 메뉴를 구성
	*        결재관리, 샘플 메뉴는 하위메뉴를 보이거나 안보이게 접고 펴기 가능
	*        그외의 메뉴는 하위메뉴가 있어도 무조건 펼침
	* @author 변형구
	* @since 2013-04-04
	* @version 1.0
	*/
	function loadMenu(isPageLoad) {
		var v_hideMenu = "";

		isPageLoad = typeof(isPageLoad) == "undefined" ? true: isPageLoad ;

		var lvlNMenuStr = "<ul name=\"{0}\" depth=\"{1}\" parentId=\"{2}\" class=\"depth{3}\"></ul>";
		// 클릭가능한 하위 메뉴가 없는 중간 메뉴
		var liString     = "<li id=\"{2}\" class=\"death{3} {5}\"><a href=\"#0\" onclick=\"f_Menustr('{2}', '{0}', false, '{6}', '{7}'); return false;\"><span>{1}</span></a></li>" ;
		// 하위 메뉴가 잇으면서 url 이 없는 메뉴
		var liString2     = "<li id=\"{2}\" class=\"death{3} {5}\"><a href=\"#0\" onclick=\" f_ExpendMenu('{2}'); return false;\"><span>{1}</span></a></li>"

		var privParentId = "0";
		var privDepth = "1";
		var conTextUrl = "${contextPath}";
		var initScreen = "";
		var initMenuId = "";

		$(".death1").empty();

		$.each(gv_cmasComm.menu['PRIV_MENU'], function(i, menuAry) {
			var menuUseUnaResn = menuAry.menuUseUnaResn;
			var popStr = "";
			var tabletView = menuUseUnaResn == "tablet=N"? false : true; // TODO: 레거시INFO 형태로 변경해서 조건 처리

			if(gv_IsTablet && !tabletView)
				return;

			lvlNMenuStr = "<ul name=\"{0}\" depth=\"{1}\" parentId=\"{2}\" class=\"depth{3}\" style=\"display:none\"></ul>";
			var menuVar = gf_GetValue(menuAry.menuVar.substring(1));
			if ( !gf_IsNull(gv_initMenuCd) ) {
				if ( gv_initMenuCd == menuAry.menuCD ) {
					v_initMenuId = i;
					initScreen = conTextUrl+menuAry.menuUrl+menuAry.menuVar;
				}
			}else if( menuVar.indexOf("INITMENU") !== -1 ){
				initScreen = conTextUrl+menuAry.menuUrl+menuAry.menuVar;
				v_initMenuId = i;
			}

			if( menuVar.indexOf("FIX") !== -1 ){
				v_fixMenu = v_fixMenu + "|" + i;
			}
			if( menuVar.indexOf("POPUP") !== -1 ){
				popStr = "POPUP";
			}else if( menuVar.indexOf("MAIN") !== -1 ){
				popStr = "MAIN";
			}

			if( gv_viewMenu != "-" && menuAry.menuDepth == "2" && gv_viewMenu.indexOf(menuAry.menuCD) == -1 ){	// menuCD 가 viewMenu 에 포함 되어 있지 않을 경우. 숨길 메뉴.
				v_hideMenu = v_hideMenu + "|" + i;
			}
			// 1depth menu 는 화면에 보이지 않는다.
			if ( menuAry.menuDepth == "1"){ }
			else {
				// 본격적인 메뉴의 출력
				// 이미 출력된 메뉴중 동일한 depth, 동일한 부모 노드를 가진 것이 있는지 확인
				var lvlNMenu = $("ul[depth='"+menuAry.menuDepth+"'][parentId='"+menuAry.parentId+"']");
				var li = null;
				var newLi = '';
					// 이미 출력된 메뉴중 동일한 depth, 동일한 부모 노드를 가진 것이 없다면
					if ( lvlNMenu.length == 0 ) {
						// 현재 메뉴의 parent menu id 로 출력된 메뉴를 찾는다.
						li = $("li[id='"+menuAry.parentId+"']");
						// parent menu 아래에 ul tag를 붙인다.
						li.append( lvlNMenuStr.simpleReplace('{0}','depth'+menuAry.menuDepth).simpleReplace('{1}', menuAry.menuDepth).simpleReplace('{2}', menuAry.parentId).simpleReplace('{3}', (menuAry.menuDepth-1) ) );
						// 이전줄에서 붙인 ul tag 개체를 찾는다.
						var subMenu = $("ul[depth='"+menuAry.menuDepth+"'][parentId='"+menuAry.parentId+"']");
						// ul tag 아래에 sub menu 를 붙이기 시작한다.
						if ( !gf_IsNull(menuAry.menuUrl) && menuAry.lastMenu == "Y" ) {
							// url이 있으면서 마지막 메뉴 인경우
							// menudepth 가 3 이상인경우
							if( menuVar.indexOf("MAIN") !== -1 || menuVar.indexOf("POPUP") !== -1 ){ // 팝업설정인 경우 MAIN : 메인화면에, POPUP : 새창으로
								newLi = liString.simpleReplace("{0}", menuAry.menuUrl)
										.simpleReplace("{1}", menuAry.menuNm)
										.simpleReplace("{2}", i)
										.simpleReplace("{3}", (menuAry.menuDepth-1))
										.simpleReplace("{5}", "plus2")
										.simpleReplace("{6}", menuAry.menuCd)
										.simpleReplace("{7}", popStr);
							}else{
								newLi = liString.simpleReplace("{0}", conTextUrl+menuAry.menuUrl+menuAry.menuVar)
									.simpleReplace("{1}", menuAry.menuNm)
									.simpleReplace("{2}", i)
									.simpleReplace("{3}", (menuAry.menuDepth-1))
									.simpleReplace("{5}", "plus2")
									.simpleReplace("{6}", menuAry.menuCd)
									.simpleReplace("{7}", menuVar);
							}
						}
						else if ( gf_IsNull(menuAry.menuUrl) && menuAry.lastMenu == "N" ) {
							newLi = liString2.simpleReplace("{1}", menuAry.menuNm)
											.simpleReplace("{2}", i)
											.simpleReplace("{3}", (menuAry.menuDepth-1))
											.simpleReplace("{5}", "plus");
						}
						subMenu.append(newLi);
					}
					else {
						if ( !gf_IsNull(menuAry.menuUrl) && menuAry.lastMenu == "Y" ) {
							if ( menuAry.menuDepth == 2) {
							// menu depth 가 2 인경우
								if( menuVar.indexOf("MAIN") !== -1 || menuVar.indexOf("POPUP") !== -1 ){ // 팝업설정인 경우 MAIN : 메인화면에, POPUP : 새창으로
									newLi = liString.simpleReplace("{0}", menuAry.menuUrl)
											.simpleReplace("{1}", menuAry.menuNm)
											.simpleReplace("{2}", i)
											.simpleReplace("{3}", (menuAry.menuDepth-1))
											.simpleReplace("{5}", "plus2")
											.simpleReplace("{6}", menuAry.menuCD)
											.simpleReplace("{7}", popStr);
								}else{
									newLi = liString.simpleReplace("{0}", conTextUrl+menuAry.menuUrl+menuAry.menuVar)
											.simpleReplace("{1}", menuAry.menuNm)
											.simpleReplace("{2}", i)
											.simpleReplace("{3}", (menuAry.menuDepth-1))
											.simpleReplace("{5}", "plus2")
											.simpleReplace("{6}", menuAry.menuCD)
											.simpleReplace("{7}", menuVar);
								}
							}
							else {
								// menudepth 가 3 이상인경우
								if( menuVar.indexOf("MAIN") !== -1 || menuVar.indexOf("POPUP") !== -1 ){ // 팝업설정인 경우 MAIN : 메인화면에, POPUP : 새창으로
									newLi = liString.simpleReplace("{0}", menuAry.menuUrl)
											.simpleReplace("{1}", menuAry.menuNm)
											.simpleReplace("{2}", i)
											.simpleReplace("{3}", (menuAry.menuDepth-1))
											.simpleReplace("{5}", "plus2")
											.simpleReplace("{6}", menuAry.menuCd)
											.simpleReplace("{7}", popStr);
								}else{
									newLi = liString.simpleReplace("{0}", conTextUrl+menuAry.menuUrl+menuAry.menuVar)
											.simpleReplace("{1}", menuAry.menuNm)
											.simpleReplace("{2}", i)
											.simpleReplace("{3}", (menuAry.menuDepth-1))
											.simpleReplace("{5}", "plus2")
											.simpleReplace("{6}", menuAry.menuCD)
											.simpleReplace("{7}", menuVar);
								}
							}
						}
						else if ( !gf_IsNull(menuAry.menuUrl) && menuAry.lastMenu == "N" ) {
							if( menuVar.indexOf("MAIN") !== -1 || menuVar.indexOf("POPUP") !== -1 ){ // 팝업설정인 경우 MAIN : 메인화면에, POPUP : 새창으로
								newLi = liString.simpleReplace("{0}", menuAry.menuUrl)
												.simpleReplace("{1}", menuAry.menuNm)
												.simpleReplace("{2}", i)
												.simpleReplace("{3}", (menuAry.menuDepth-1))
												.simpleReplace("{5}", "minus on")
												.simpleReplace("{6}", menuAry.menuCD)
												.simpleReplace("{7}", popStr);
							}else{
								newLi = liString.simpleReplace("{0}", conTextUrl+menuAry.menuUrl+menuAry.menuVar)
												.simpleReplace("{1}", menuAry.menuNm)
												.simpleReplace("{2}", i)
												.simpleReplace("{3}", (menuAry.menuDepth-1))
												.simpleReplace("{5}", "minus on")
												.simpleReplace("{6}", menuAry.menuCD)
												.simpleReplace("{7}", menuVar);
							}
						}
						else if ( gf_IsNull(menuAry.menuUrl) && menuAry.lastMenu == "N" ) {
							newLi = liString2.simpleReplace("{1}", menuAry.menuNm)
											.simpleReplace("{2}", i)
											.simpleReplace("{3}", (menuAry.menuDepth-1))
											.simpleReplace("{5}", "plus");
						}
						lvlNMenu.append( newLi );
					}
				}

			privParentId = menuAry.parentId;
			privDepth = menuAry.menuDepth;
		});

		if(isPageLoad){
			f_Menustr(v_initMenuId, initScreen, true, false);
		}
		v_fixMenu = v_fixMenu.substring(1);
		v_hideMenu = v_hideMenu.substring(1);
		// - 마킹.
		f_SetExpendMark(v_fixMenu);
		// 메뉴 한영변환 처리
		//fn_MlangSet();
		// - 마킹 된 메뉴 열기.
		f_ExpendMenus(f_GetExpendMenu());
		// 메뉴 숨기기
		f_HideMenus(v_hideMenu);
	}

	function f_SetExpendMark(markStr){
		var v_menuArr = markStr.split("|");
		for(var i = 0; i < v_menuArr.length; i++){
			$("li[id='"+v_menuArr[i]+"']").removeClass("plus");
			$("li[id='"+v_menuArr[i]+"']").addClass("minus on");
		}
	}
	function f_HideMenus(hideStr){
		var v_menuArr = hideStr.split("|");
		for(var i = 0; i < v_menuArr.length; i++){
			$("li[id='"+v_menuArr[i]+"']").hide();
		}
	}
	function f_GetExpendMenu(){
		var expendMenuIds = new Array();
		$("li.minus.on").each(function(i,e){
			expendMenuIds[expendMenuIds.length] = $(e).attr("id");
		});

		return expendMenuIds;
	}
	function f_ExpendMenus(expendMenuIds){
		for(var i = 0 ; i < expendMenuIds.length ; i++){
			f_ExpendMenu(expendMenuIds[i]);
		}
	}
	/**
	* @function
	* @param menuId<String> 메뉴 id
	*        initYn<boolean> 초기화 여부 default false
	* @see   하위메뉴를 펼치거나 접는다.
	* @author 변형구
	* @since 2013-04-04
	* @version 1.0
	*/
	function f_ExpendMenu(menuId, initYn) {
		if ( gf_IsNull(initYn) ) {
			initYn = false;
		}

		if ( !initYn ) {
			if ( menuId == v_initMenuId ) {
				return;
			}
		}

		var childElement = $("ul[parentId='"+menuId+"']").toggle("fast", function () {
			if ( $("ul[parentId='"+menuId+"']").css("display") == "block" ) {
				// child menu show
				$("li[id='"+menuId+"']").removeClass("plus");
				$("li[id='"+menuId+"']").addClass("minus on");
			}
			else {
				// child menu hide
				$("li[id='"+menuId+"']").removeClass("minus on");
				$("li[id='"+menuId+"']").addClass("plus");
			}
		});

		var allUlElements = $("ul");
		for ( var i = 0; i < allUlElements.length; i++ ) {
			if ( $(allUlElements[i]).attr("parentId") == menuId || $(allUlElements[i]).attr("parentId") == "0" ) {
				continue;
			}
			else {
				var mId = $(allUlElements[i]).attr("parentId") ;
				if ( $(allUlElements[i]).css("display") == "block" ) {

					// child menu show
					$("li[id='"+mId+"']").removeClass("plus");
					$("li[id='"+mId+"']").addClass("minus on");
				}
				else {
					// child menu hide
					$("li[id='"+mId+"']").removeClass("minus on");
					$("li[id='"+mId+"']").addClass("plus");
				}
			}
		}
	}

	/**
	* @function
	* @param menuId<String> 메뉴 id
	*        menuUrl<String> 메뉴 url
	*        initYn<boolean> 초기호출 여부 default false
	* @see   url이 있는 메뉴를 클릭할때 수행되는 함수로서 main 프레임에 메뉴 화면 redirect
	*        부가기능으로 건수가 있는 메뉴가 클릭될때 건수 데이터를 재조회하여 메뉴에 redraw
	* @author 변형구
	* @since 2013-04-04
	* @version 1.0
	*/
	function f_Menustr(menuId, menuUrl, initYn, menuCd, popupStr)  {

		if ( gf_IsNull(initYn) ) {
			initYn = false;
		}
		var valuePath = window.top == window ? window : top;
		// child menu show
		$("li[id='"+menuId+"']").addClass("selected");

		var liCollection = $("li");
		for ( var i = 0; i < liCollection.length; i++ ) {
			if ( $(liCollection[i]).attr("id") == menuId ) {
				continue;
			}
			$(liCollection[i]).removeClass("selected");
		}

		if(window.top != window){
			if( popupStr == "POPUP" ){ // 팝업설정인 경우 MAIN : 메인화면에, POPUP : 새창으로
				window.open(menuUrl, "_blank");
			}else{
				valuePath.frames["main"].location.href = menuUrl;
			}
			if ( initYn )
				f_ExpendMenu(menuId, initYn);
		}
		else{
			if(typeof(cf_GarbageCollector) != "undefined"){
				cf_GarbageCollector();
				delete cf_GarbageCollector;
			}
			$("#main").load(menuUrl, function(){
				gv_IsTablet = true;
				sizeCenterPane();
			});
		}
	}

	/**
	* @function transaction callback 함수
	* @param strSvcId<string> transaction service id
	*        obj, resultData
	* @author 변형구
	* @since 2013-04-12
	* @version 1.0
	*/
	function f_LeftMenuCallback(strSvcId, obj, resultData){

		  // transaction의 정상 처리 여부를 판단한다.
		  if (!gf_ChkTransaction(strSvcId, resultData, true )) {
			  return;
		  }

		  switch(strSvcId) {
		  	default :
		  		break;
		  }
	}

	function f_MoveWrite(){

	}

	$(function(){
		loadMenu();

	});
	</script>
</head>
<body>


<!--left-->
<div id="left_wrap">
	<div id="menu" >
		<h2>
			<span class="left"></span>
			<span class="right"></span>
			<div class="title">메뉴</div>
		</h2>

		<!--MENU-->
		<div id="tree">
			<div class="bg_rs">
				<ul class="death1" depth="2" parentId="0" >

				</ul>
			</div>
		</div>
		<div class="bottom_bg"><span class="left"></span><span class="right"></span></div>
	</div>
</div>

</body>
</html>