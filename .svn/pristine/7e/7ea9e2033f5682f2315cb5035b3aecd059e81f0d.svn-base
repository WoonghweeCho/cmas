/**
* @class 우편번호 검색
* @author 이진혁
* @since 2013-10-28
* @version 1.0
*/

var params = {};						//파라미터 처리 변수


/**
 * 데이터 처리용 DataSet
 */
var ds_PstSrch = new DataSet();			//조회용 DataSet
var ds_PstSrchList = new DataSet();		//목록 DataSet

/**
* @class 화면 로드 완료 시 필요한 초기 작업 수행.
*        1. 파라미터 초기화
*        2. 컴포넌트 초기화
*        3. Event Listener 초기화
*        4. 화면내 Form 객체 초기화
*        5. 다국어 처리
* @author 이진혁
* @since 2013-11-04
* @version 1.0
*/
$(function() {
	/* 컴포넌트 처리 */
	cf_InitParam();
	cf_SetComponents();
	cf_SetEventListener();
	cf_SetBinding();
	cf_InitForm();
	fn_MlangSet();
});

/**
* @class 파라미터 초기화
* @author 이진혁
* @since 2013-11-04
* @version 1.0
*/
function cf_InitParam()
{

}

/**
* @class Form Onload 시 컴포넌트 초기화
* @author 이진혁
* @since 2013-11-04
* @version 1.0
*/
function cf_SetComponents()
{
	//목록 JQGrid
	var pstSrchList = {
		datatype: "local",
	   	colNames:[gf_FindLang('우편번호'),gf_FindLang('주소')],
	   	colModel:[
	  	   		{name:'postcd',index:'postcd', width:20},
		   		{name:'postArea4',index:'postArea4', width:30}

	   	],
//	   	autowidth:true,
	   	width:"150",
	   	height:"250",
	   	sortname: 'id',
	    viewrecords: true,
	    sortorder: "desc",
	    rowNum:10000,
	    resizable:true,
	    ondblClickRow: function(rowid, iRow, iCol, e){
	    	grd_List_oncelldbclick(this, rowid, iRow, iCol);
	     }
	};

	/**
	 * 화면에 JQGrid 연결
	 */
	$("#outputPstSrchList").jqGrid(pstSrchList);

	//Select Box change Event 생성
	$("select[name='addsCd']").trigger('change'); //주소구분


	/**
	 * 페이징 처리
	 */
	$("#outputPstSrchList").jqGrid('navGrid','#outputPstSrchListPager',{edit:false,add:false,del:false});

	/**
	 * Container 크기에 맞춰 Windows Resizing 될 때 Box Grid의 사이즈를 조절한다.
	 */
	$(window).bind("resize", function(){
		$("#outputPstSrchList").setGridWidth($("#container").width()-34);
	}).trigger('resize');

}

/**
* @class Form Onload DataSet Binding 처리
* @author 이진혁
* @since 2013-11-04
* @version 1.0
*/
function cf_SetBinding()
{
	//목록 Grid Binding
	ds_PstSrchList.bind($("#outputPstSrchList"));
}

/**
* @class Form Onload 시 Element/Component 인벤트 초기화
* @author 이진혁
* @since 2013-11-04
* @version 1.0
*/
function cf_SetEventListener()
{
	//우편번호 찾기 눌렀을 경우, 우편번호와 주소 등 조회
	$("#search").click(function()
	{
		cf_RetrievePstSrchList();
	});

	/**
	 * Enter Key Event 처리
	 * - 검색영억에 Focus가 가 있을 때만 Enter Key Event를 처리한다.
*/
	$( "input[name='pstSrch']" ).live('keypress', function(e) {
	    if (e.which == 13) {
	    	cf_RetrievePstSrchList();
	    	return false;
	    }
	});
}

/**
* @class Form Onload 시 객체 초기 값 설정
* @author 이진혁
* @since 2013-11-04
* @version 1.0
*/
function cf_InitForm()
{
}

/**
 *
 */
function cf_RetrievePstSrchList()
{
	if(!gf_IsNull(gf_GetValue($("input[name='pstSrch']").val())))
	{

		//구주소 조회
		if($("select[name='addsCd']").val() == "P")
		{
			/**
			 * Map<String, Object> 형태 파라미터 처리
			 */
			var datas =
			{
				postArea4 : gf_GetValue($("input[name='pstSrch']" ).val())
			};

		    gf_Transaction("SELECT_POST", "/sn/eaps/retrievePostCd.xpl", datas, {}, "f_Callback", true);
		}
		//신주소 조회
		else
		{
			/**
			 * Map<String, Object> 형태 파라미터 처리
			 */
			var datas =
			{
				postArea4 : gf_GetValue($("input[name='pstSrch']" ).val())
			};

		    gf_Transaction("SELECT_POST", "/sn/eaps/retrieveRoadPostCd.xpl", datas, {}, "f_Callback", true);
		}
	}

}



/**
* @class Transaction 처리 후 수행되는 Callback 함수
* @author 이진혁
* @since 2013-11-04
* @version 1.0
*/
function f_Callback(strSvcId, obj, resultData){

	  // transaction의 정상 처리 여부를 판단한다.
	  if (!gf_ChkTransaction(strSvcId, resultData, true )) {
		  alert("처리 오류");
		  return;
	  }

	  switch(strSvcId) {
	  	case "SELECT_POST" :
	  		ds_PstSrchList.setAllData(resultData.output1);
	  		ds_PstSrchList.setPosition(0);

	  		if(resultData.output1 == null)
	  		{
	  			gf_AlertMsg("조회된 데이터가 없습니다.");
	  		}

			break;

	  	default :
	  		break;
	  }
}



/**
* @class 더블클릭시, 데이터 부모창에 전송
* @author 이진혁
* @since 2013-11-05
* @version 1.0
*/
function grd_List_oncelldbclick(grid, rowid, iRow, iCol)
{
 	var rowData = $("#outputPstSrchList").data(rowid);

 	var postcd1 = rowData.get('postcd');
 	var postcd = String(postcd1);
 	var postarea  = rowData.get('postArea4');

 	$(opener.document).find("#zipcd").val(postcd);
 	$(opener.document).find("#addr1").val(postarea);

	this.close();

}
