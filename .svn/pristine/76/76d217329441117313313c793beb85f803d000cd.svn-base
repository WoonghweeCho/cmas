var gv_sEnovType = "HtmCtrl";	//편집모드
var gv_sCharSet  = "utf-8";	//Character Set
var gv_sHostName = location.host;	//Host Name

var tweditor_callback = null;

var html = null;

var tweditor = function(elm, downFileList, callback){
	this.editor = null;
	this.init(elm, downFileList, callback);
};
tweditor.prototype = {
		init : function(elm, downFileList, callback){

			var clsid =  "";
			var version = "";
			var key = "";
			var cab = "";
			var actPage = "";

			if(gv_cmasComm.cmasUrl.indexOf("iworks") > -1 || gv_cmasComm.cmasUrl.indexOf("icmsdev") > -1){
				//운영용 라이센스
				clsid =  "976A7D6C-B14C-4e50-A5C3-B43D8C49D8C8";
				version = "3,7,1,1006";
				key = "WMt/6sdX77X2Pq7Tk8mxYrteLYKHZGnpAviPYXxsyg6KUJhafUthXe/hIgafcjG698Vv5Zg5/Spni04MhQHvPgS74NAXndZFlyW3GO6JmldTtARExVQ9UlYHaN5DEd4C5BVlL4WCBxFSYUq4BxsMd2HLbKzRdRujJh8/Jo7ft4OS7J/CZu5nDsKwiNbQs8p8vvdB6qX64Ywuo/c+efwzUSXXy9Ww6AjXQvtAZDUDmlzjXXVGURjhRpn3btVob1VWYxFvS+Az48pgmDKuWBT8oiFPqXvGTFK8b74yPxB+3zWNVHRvYgx+SwJClG7HrmGmKNvsR6UNjVeZyTHmAM7RVrZ09HiDn8C94Gz1fBfROzXvWT5xBloRiu0+ptqOR2xBrzIKKAIrDfM54RIX7OVN75gEzbPuw27PMXOpLP1voNCASwbMrQ7sGp6IJBjCV78n6XLL1v2HBYKjTkQgi9W3LOb7DImML1T/iPaz6oIIZfg0Wes2odwQu9LmoE9aXFQw323I6v+1RATr2aG+WAse2jL/nK2hgjlzyLms2LsM+IwvXuW2CvaToiBcxOjTZhjHAb9E9saBmS6vWkL3q4gqioIQuFCCJeagp8lPoFRJZAohCQZM05gSiEah3kQHwq//uq/Yth2bQMUyBCZk6c0/ks2G1aUfur3V2leXyIh7FqtMN1HiFa9G8CR1GRvIiDeN4PsdhUtDaAJJ83qlNy21D7hK6KqKBkACS6wcaGJeakgar7tYhZfj/TrEOwcHFkKi2F2TM0z0gdQyt+MCAM0f8w==";
				cab = "tweditor.cab";
				actPage = "/cmas/co/common/file/uploadWebFile.xpl?type=tweditor";
			} else {
				// 개발용 라이센스
				clsid =  "976A7D6C-B14C-4e50-A5C3-B43D8C49D8C8";
				version = "3,7,1,1014";
				key = "jsSQsLdWFozfzCBaTdR/Ep0SWpdqzN/jbrG+eXYGJ8x2r1aksszLEfzx0Gj2gcbJbbPHUSWeUPScws18rp9DkxkPYvw3mX5UrY50wvBLWPNvjdSgHB2iN/Gli1H7iCB+Gq+7WIWX4/06xDsHBxZCothdkzNM9IHUMrfjAgDNH/M=";
				cab = "tweditor_3_7_1_1014.cab";
				actPage = "/co/common/file/uploadWebFile.xpl?type=tweditor";
			}

			var id = "twe";
			var applyinitdata = 1;//apply:1
			var editmode = 0;//edit:0

			tweditor_callback = callback;

			eventScript = document.createElement('<script language="JavaScript" FOR="'+id+'" EVENT="OnControlInit()"></script>');
			eventScript.text  = "tweditor_callback();";
			document.getElementsByTagName("head")[0].appendChild(eventScript);

			eventCancelScript = document.createElement('<script language="JavaScript" FOR="'+id+'" EVENT="OnUploadCancel()"></script>');
			eventCancelScript.text  =  "$(\"#uploadRst\").val(-1)";
			eventCancelScript.text  =  "alert(\"파일 전송에 실패하였습니다.\")";
			eventCancelScript.text  =  "return;";
			document.getElementsByTagName("head")[0].appendChild(eventCancelScript);

			eventDoneScript = document.createElement('<script language="JavaScript" FOR="'+id+'" EVENT="OnUploadDone()"></script>');
			eventDoneScript.text  = "$(\"#uploadRst\").val(1)";
			document.getElementsByTagName("head")[0].appendChild(eventDoneScript);

			/* */
			var params = [
						  {name: "InitFile", value: gv_cmasComm.cmasUrl+"/common/js/framework/tweditor/env.xml"},
						  {name: "ApplyInitData", value: applyinitdata},
						  {name: "Mode", value: editmode},
						  {name: "ActionPage", value: actPage},
						  {name: "LicenseKey", value: key},
						  {name: "BaseUrl", value: gv_cmasComm.cmasUrl}

			];
			var editor = document.createElement("object");

			for(var i = 0 ; i < params.length ; i++){
				var paramObj = document.createElement("param");
				var param = params[i];
				for(var key in param){
					paramObj.setAttribute(key, param[key]);
				}
				editor.appendChild(paramObj);
			}

//			if(typeof(downFileList) != "undefined" && downFileList != null){
//				var paramObj = document.createElement("param");
//				paramObj.setAttribute("name", "AddFileAttach");
//				paramObj.setAttribute("value", downFileList);
//				editor.appendChild(paramObj);
//			}

			editor.setAttribute("id", "twe");
			editor.setAttribute("codeBase", gv_cmasComm.cmasUrl+"/common/install/download/"+cab+"#version="+version);
			editor.setAttribute("classid", "CLSID:"+clsid);
			editor.setAttribute("width", "100%");
			editor.setAttribute("height", "100%");

			var _that = this;
			setTimeout(function(){
				elm[0].appendChild(editor);
				_that.editor = document.twe;
			}, 1);


			//<!-- 태그프리 관련 설정 -->
			$("form").append("<input type=\"hidden\" name=\"upType\" id=\"upType\" value=\"OnlyUpload\">");
			$("form").append("<input type=\"hidden\" name=\"htmlValue\" id=\"htmlValue\">");
			$("form").append("<input type=\"hidden\" name=\"mime_contents\" id=\"mime_contents\">");
			$("form").append("<input type=\"hidden\" name=\"html\" id=\"html\">");

			$("form").append("<input type=\"hidden\" name=\"uploadRst\" id=\"uploadRst\" value=\"false\">"); // 업로드 결과
			//<!-- //태그프리 관련 설정 -->
		},
		setHTML : function(html){
			this.editor.HtmlValue = html;//this.editor.Document.body.innerHTML = html;
		},
		setData : function(html){
			this.setHTML(html);
		},
		getHTML : function(){
			return this.editor.HtmlValue;//this.editor.Document.body.innerHTML;//this.editorBody.innerHTML;
		},
		getData : function(){
			return this.editor.HtmlValue;//this.editor.Document.body.innerHTML;//this.editorBody.innerHTML;
		},
		AddFiles : function(){
			var files = document.twe.GetLocalFiles();

			// 에디터 내부에 파일이 포함되어 있을 경우 GetLocalFiles API 를 이용해 파일경로 리스트들을 가져옵니다.
			// 얻어온 파일경로들은 ; 로 분리구분되어 있기때문에 분리 해서 AddFile 로 추가해주는 작업을 처리합니다.
			// 에디터 내부에 파일이 포함되어 있을 경우 경로를 분리해 AddFile 로 추가해주고 true 를 반환하고
			// 파일이 포함되어 있지 않을 경우 false 를 반환하여 MIME 처리를 해줍니다.


			if(files != "")
			{
				var filesList = files.split(";");

				for(var i=0; i<filesList.length;i++)
				{
					if(filesList[i].substr(0,2)!="\\\\")
					{
						document.twe.AddFile(filesList[i]);
					}

				}

				return true;

			}
			else
			{
				return false;
			}

		},
		AddParam : function(){
			var form = document.frm_write;
			var coll = document.all.tags("input");

			// input 태그의 Name 과 Value 를 AddParameter 에 추가합니다.
			if(coll != null)
			{
				// 폼에 <input type="hidden" name="html"> 이 존재해야함.
				form.html.value = form.twe.GetHtml2("302BDF9B-314C-4e34-9C75-9E18C566C25C/");
				// 현재 에디터 내부의 HTML 값을 얻어온다.
				// 이때 HTML 값의 이미지 경로들은 GetHtml2 메소드에
				// 설정한 파라미터의 값으로 변환돼서 반환된다.
				// EX. <IMG src = “c:\test\test.gif”>
				//    =>  <IMG src = “302BDF9B-314C-4e34-9C75-9E18C566C25C/test.gif”>

				for (var i=0; i < coll.length; i++)
				{
					var strName = coll[i].name;
					var strValue = coll[i].value;

					form.twe.AddParameter(strName, strValue);
					// input 태그의 데이터들을 AddParameter 메소드를 이용해 추가한다.
					// 이 값들은 서버 사이드에서 변수 형태로 받아서 사용할 수 있다.

				}



				return true;
			}
			else
			{
				return false;
			}
		}
};

