/**
* @class 화면에서 사용될 전역변수를 아래에 기술함.
* @author 고준석
* @since 2013-04-04
* @version 1.0
*/

var params = {};						//파라미터 처리 변수
var v_userId = "";
var v_signId;
var v_signUserId;
var v_formType;

var v_SiteLocList;

var marker, i;
var markerList = [];
var locations;
var map;
var stylez;
var infowindow;



var v_IsSave = false;

var ds_SiteLocList = new DataSet();		//목록 DataSet

/**
* @class 화면 로드 완료 시 필요한 초기 작업 수행.
*        1. 파라미터 초기화
*        2. 컴포넌트 초기화
*        3. Event Listener 초기화
*        4. 화면내 Form 객체 초기화
*        5. 다국어 처리
* @author 고준석
* @since 2013-04-04
* @version 1.0
*/
$(function() {
	/* 컴포넌트 처리 */
	cf_InitParam();
	cf_SetComponents();
//	cf_SetEventListener();
	cf_SetBinding();
	cf_InitForm();
	fn_MlangSet();

});

/**
* @class 파라미터 초기화
* @author 고준석
* @since 2013-04-04
* @version 1.0
*/
function cf_InitParam()
{
	/**
	 * 파라미터
	 */
}

/**
* @class Form Onload 시 컴포넌트 초기화
* @author 고준석
* @since 2013-04-04
* @version 1.0
*/
function cf_SetComponents()
{
	clsCdSelect = $("<select>");

	//목록 JQGrid
	var siteLocList = {
		datatype: "local",
	   	colNames:[
	   	          gf_FindLang('현장명')
	   	          , gf_FindLang('현장코드')
	   	          , gf_FindLang('현장전화')
	   	          , gf_FindLang('현장소장')
	   	          ],
	   	colModel:[

		   		// 현장명
		   		{name:'siteNm',index:'siteNm', width:150, editable:false, fixed: true},

		   		// 현장코드
	  	   		{name:'siteCd',index:'siteCd', width:60, editable:false, fixed: true, align: "center"},

	  	   		// 현장전화
		   		{name:'tel',index:'siteNm', width:100, editable:false, fixed: true},

		   		// 현장소장
		   		{name:'siteSvNm',index:'siteSvNm', width:150, editable:false, fixed: true}


		   		],
	   	height:270,
	   	width:500,
	   	sortname: 'id',
	    viewrecords: true,
	    sortorder: "desc",
	    ondblClickRow : function(row){
	    	f_gMapMove(ds_SiteLocList.get(row, "sortNo")-2);
	    },

	    rowNum:10000

	};



	/**
	 * 화면에 JQGrid 연결
	 */
	$("#siteLocList").jqGrid(siteLocList);

	/**
	 * Container 크기에 맞춰 Windows Resizing 될 때 Box Grid의 사이즈를 조절한다.
	 */
}

/**
* @class Form Onload DataSet Binding 처리
* @author 고준석
* @since 2013-04-04
* @version 1.0
*/
function cf_SetBinding()
{
	//목록 Grid Binding
	ds_SiteLocList.bind($("#siteLocList"));
}

/**
* @class Form Onload 시 Element/Component 인벤트 초기화
* @author 고준석
* @since 2013-04-04
* @version 1.0
*/
function cf_SetEventListener()
{

}


/**
* @class Form Onload 시 객체 초기 값 설정
* @author 고준석
* @since 2013-04-04
* @version 1.0
*/
function cf_InitForm()
{
	/**
	 * 양식 목록 조회
	 */
	cf_RetrieveSiteLocList();
	var loc = document.location;
	var domain = loc.protocol + "//" + loc.host;
}


/**
* @class 조건에 맞는 결재양식 목록을 조회한다.
* @author 고준석
* @since 2013-04-04
* @version 1.0
*/
function cf_RetrieveSiteLocList(){
	/**
	 * Map<String, Object> 형태 파라미터 처리
	 */
	var datas= {
//		signId	: v_signId
	};

    gf_Transaction("SELECT_CVA0201", "/info/siteLocList.xpl", datas, {}, "f_Callback", true);

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
	  	case "SELECT_CVA0201" :
	  		ds_SiteLocList.setAllData(resultData.output);
	  		ds_SiteLocList.setPosition(0);

	  		f_gMapInit("37.5704432","126.9725806", ds_SiteLocList);

	  		map.setCenter(new google.maps.LatLng("37.5704432","126.9725806"));
	  		infowindow.setContent("본사");
			break;
	  	default :
	  		break;
	  }
}

function f_AddRow(){

	ds_SiteLocListList.add({
		siteCd: "",
		siteNm: "",
		zipCd: "",
		addr: "",
		tel: "",
		latitude: "",
		longitude: "",
		conOutline: "",
		conNm: "",
		constDate: "",
		conendDate: "",
		constYmd: "",
		siteSvNm: "",
		orderPl: "",
		gubun: "",

		fstRegDt: "",
		fstRegUserId: "",
		fnlEditDt: "",
		fnlEditUserId: "",
		signId: v_signId
	});

}

function cf_Save(){

	v_IsSave = false;
	/**
	 * Map<String, Object> 형태 파라미터 처리
	 */

	var datas= {
		signId : v_signId
	};

	/**
	 * DataSet 파라미터 처리
	 */
	var dataSets = {
//		input1 : ds_FloorList.getAllData("U")
	};
}

function cf_LinkForm(args){

	f_InitForm();

}

function f_InitForm(){

}

/**
 * 화면 열람 형태에 따라 결재시스템의 이벤트 발생(상신, 재기안, 결재, 반려)시
 * 서버 Transaction을 처리한다.
 *
 * D01 : 기안작성           - 저장
 * D16 : 임시저장 후 재기안  - 수정
 * 그외 : 열람
 *
 * @returns {Boolean}
 */
function cf_SignCheck(){


}

function f_gMapInit(lat, lon, ds_SiteLocList){


//	alert(ds_SiteLocList.size());
	/**
	 * 구글맵 불러오기
	 */
	locations = [ds_SiteLocList.size()];
    for(var s=0; s< ds_SiteLocList.size(); s++){
    	locations[s] = [ds_SiteLocList.get(s, "siteNm") + "<br>현장소장 : " + ds_SiteLocList.get(s, "siteSvNm") + "<br>공사기간 : " + ds_SiteLocList.get(s, "conDate") + "<br>공사개요 : " + ds_SiteLocList.get(s, "conOutline") + "<br>주소 : " + ds_SiteLocList.get(s, "addr"), ds_SiteLocList.get(s, "latitude"), ds_SiteLocList.get(s, "longitude"),ds_SiteLocList.get(s, "sortNo")];
    }

	map = new google.maps.Map(document.getElementById('siteLocMap'), {
		zoom: 15,
		center: new google.maps.LatLng(lat, lon),
		mapTypeId: google.maps.MapTypeId.ROADMAP
	});


	stylez = [{
		featureType: "all",
		elementType: "all",
		stylers: [
		]
	}];
		var mapType = new google.maps.StyledMapType(stylez, { name:"Grayscale" });
		map.mapTypes.set('tehgrayz', mapType);
		map.setMapTypeId('tehgrayz');

//	}



	infowindow = new google.maps.InfoWindow(

	);


	for (i = 0; i < locations.length; i++) {
		marker = new google.maps.Marker({
		position: new google.maps.LatLng(locations[i][1], locations[i][2]),
		map: map
		});

		google.maps.event.addListener(marker, 'click', (function(marker, i) {
			return function() {
				infowindow.setContent(locations[i][0]);
				infowindow.open(map, marker);
			};
		})(marker, i));

		markerList.push(marker);
	}
}

function f_gMapMove(i){
	map.setCenter(new google.maps.LatLng(locations[i][1], locations[i][2]));
	infowindow.setContent(locations[i][0]);
	infowindow.open(map, markerList[i]);
}
