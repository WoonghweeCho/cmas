
/**
* @class
* @see   파일첨부 컴포넌트 스크립트 chrome, fire fox, sapari
* @author 변형구
* @since 2013-04-04
* @version 1.0
*/

// file_atch_id, 전역변수
var gv_FileAtchId;
// file upload 후 수행될 callback 함수 전역 변수
var gv_UploadCallFunc;
//upload, download, all 세가지의 모드로 구분된다.
var gv_Mode = "upload";
// file upload policy default is "default"
var gv_Policy = "default";
// enable multiple file upload ??
var gv_EnableMultipleUpload = true;

var gv_EcmIp = "172.20.2.189"; //개발
//var gv_EcmIp = "172.20.2.65"; //운영


// form string
// upload form string
var gv_UFormStr = "<form name=\"uploadForm\" id=\"uploadForm\" target=\"fileUploader\" method=\"post\" enctype=\"multipart/form-data\" action=\""+gContextPath+"/co/common/file/uploadEcmFile.xpl\"></form>";
// download form string
var gv_DFormStr = "<form name=\"downForm\" id=\"downForm\" target=\"fileUploader\" method=\"post\" action=\""+gContextPath+"/co/common/file/downloadEcmFile.xpl\"></form>";

// iframe String
// iframe string for IE Browser
var gv_IEIframe4UploadButton = "<iframe name=\"openBtnFrame\" src=\"/common/jsp/comp/fileupload/uploadButton.jsp\" width=\"200\" height=\"26\" frameborder=\"0\"></iframe>";
//iframe string for download
var gv_Iframe4DownTarget = "<iframe name=\"fileUploader\" style=\"display:none\"></iframe>";

// table string
// 파일 업로드 ui를 그리기 위한 변수들
var gv_FileListDnDInfo = "<tr><td class=\"dndRow\" colspan=\"7\" style=\"height:100px\"  align=\"center\"></td></tr>";
var gv_FileListEmptyRow = "<tr><td class=\"emptyRow\" colspan=\"7\"  align=\"center\">첨부된 파일이 없습니다.</td></tr>";
var gv_FileListTable = "<table id=\"fileTable\" name=\"fileTable\" border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"> \r\n" +
    "	<colgroup> 								\r\n" +
    "		<col width=\"41\"> 					\r\n" +
    "		<col width=\"\"> 					\r\n" +
    "		<col width=\"98\"> 					\r\n" +
    "		<col width=\"124\"> 				\r\n" +
    "		<col width=\"153\"> 				\r\n" +
    "		<col width=\"65\">					\r\n" +
    "		<col width=\"50\">					\r\n" +
    "	</colgroup> 							\r\n" +
    "  <tbody>									\r\n" +
    "		<tr> 								\r\n" +
    "			<th>순번</th>					\r\n" +
    "			<th>파일명</th> 					\r\n" +
    "			<th>파일크기</th> 				\r\n" +
    " 			<th>생성자</th> 					\r\n" +
    " 			<th>생성일</th> 					\r\n" +
    " 			<th>삭제</th> 					\r\n" +
    " 			<th>EcmNo</th> 					\r\n" +
    "		</tr> 								\r\n" +
    " 	</tbody>  								\r\n" +
    "</table>										";

// hidden string
// hidden field for file atch id
var gv_Hidden4FileAtchId = "<input type=\"hidden\" name=\"fileAtchId\" id=\"fileAtchId\">";
//hidden field for file policy
var gv_Hidden4FilePolicy = "<input type=\"hidden\" name=\"policy\" id=\"policy\">";
//hidden field for file atch id
var gv_Hidden4FileId = "<input type=\"hidden\" name=\"fileId\" id=\"fileId\">";


// input type file object for not IE style=\"display:none\"
var gv_InputFile = "<input type=\"file\" name=\"file\" id=\"file\" {0} onchange=\"gf_changeFile(this)\" style=\"display:none\" >";

// button string
// upload operation button string
var gv_OpenButton = "<a class=\"btn s4\" onclick=\"gf_onFileSelect()\" style=\"padding:0 0 0 5px;*zoom:1;overflow:hidden;\"><span style=\"margin:0;overflow:hidden;*zoom:1;\">파일열기</span></a>"; //"<input name=\"openBtn\" type=\"button\" value=\"열기\" onclick=\"gf_onFileSelect()\">";
var gv_InitButton = "<a class=\"btn s4\" onclick=\"gf_onFileInit()\" ><span>취소</span></a>"; //"<input name=\"initBtn\" type=\"button\" value=\"취소\" onclick=\"gf_onFileInit()\">";

// div string
var gv_DivStatus = "<div id=\"divStatus\" name=\"divStatus\" style=\"width:500px;height:30px\" border=\"1\"></div>";
var gv_DivDragNDrop = "<div id=\"dropbox\" name=\"dropbox\" sytle=\"position:absolute;width:100%;text-align:center;\"></div>";
var gv_DivButtonArea = "<div id=\"divBtnArea\" name=\"divBtnArea\"  class=\"btn-area f_r\" style\"width:100%;\" align=\"right\"></div>";

// 업로드를 수행하기 위해 선택한 파일 개체를 담는 전역 배열
var gv_Files = new Array();

// 파일목록이 데이터셑 형태로 필요할 가능성을 위해만든 파일 목록 데이터셑
var gds_FileList = new DataSet();


/*----------------------------------------------------------------------------------------------
 * 설명   	:
 * 파라미터 	: N/A
 * 리턴값   	: N/A
 * 작성자 	: 변형구
 * 작성일 	: 2013.03.20
 ----------------------------------------------------------------------------------------------*/
/**
* @constructor
* @param policy<String> 파일 업로드 policy null 일경우 default ( 현재 결재에서의 policy 는 default, excel 두가지 이다. )
*        enableMultipleUpload<boolean>  multiple file upload 가능 여부
* @see   fileupload.js를 <script src="/common/js/comp/fileupload.js"></script>를 이용하여 페이지에
 *            사용하면 화면 로드시 fileupload를 위한 UI를자동으로 생성하며 변수들을 초기화 한다.
* @return N/A
* @author 변형구
* @since 2013-03-20
* @version 1.0
*/
function gf_InitFileUploadComponent(policy, enableMultipleUpload) {

    var componentDiv = $("div[name='fileComponent']");

    if (componentDiv.length == 0) {
        gf_Trace(' file upload를 위한 div가 없기때문에 컴포넌트 UI를 생성하지 않는다. ');
        return;
    }

    // multiple file upload 설정
    gf_SetEnableMultipleUpload(enableMultipleUpload);

    // policy 설정
    gf_SetPolicy(policy);


    // 디자인 적용을 위한 div class 적용
    componentDiv.addClass("list_st5");

    var upForm = document.getElementById("uploadForm");
    // 이미 uploadForm 이라는 form이 있는지 검사 하여 없다면 새로 그린다.
    if (upForm == null || upForm == undefined) {
        // 파일업로드를 위한 폼 자동 생성
        // name이 fileComponent 인 div 를 찾아 form tag를 생성한다. 파일 업로드 이므로 multipart/form-data 로 생성
        $("div[name='fileComponent']").append(gv_UFormStr);
        // 금방 생성한 uploadForm에 파일 업로드를 위한 UI 를 생성한다.
        var fileForm = $("form[name='uploadForm']");
        // input type file 붙이기 non ie 에서만 동작해야 한다.
        if (gv_EnableMultipleUpload) { // multi file upload
            fileForm.append(gv_InputFile.simpleReplace("{0}", "multiple"));
        } else { // single file upload ( excel upload etc ... )
            fileForm.append(gv_InputFile.simpleReplace("{0}", ""));
        }
        fileForm.append(gv_DivButtonArea);
        fileForm.append(gv_DivDragNDrop);

        var btnDiv = $("div[name='divBtnArea']");
        btnDiv.append(gv_OpenButton);
        btnDiv.append(gv_InitButton);

        var dndDiv = $("div[name='dropbox']");
        // upload 된 파일의 목록을 나타낼 html table
        dndDiv.append(gv_FileListTable);

        var tbody = $("table[name='fileTable']").find("tbody");
        // table에 header 를 생성
        tbody.append(gv_FileListDnDInfo);
        tbody.append(gv_FileListEmptyRow);
        fileForm.append(gv_Hidden4FileAtchId);
        fileForm.append(gv_Hidden4FilePolicy);
        fileForm.append(gv_Hidden4FileId);
        fileForm.append(gv_DivStatus);

        // name이 fileComponent 인 div 를 찾아 form tag를 생성한다. 이 form은 파일 다운로드를 위해 사용한다.
        $("div[name='fileComponent']").append(gv_DFormStr);
        // download 폼을 위한 hidden 필드 생성
        var downForm = $("form[name='downForm']");
        downForm.append(gv_Hidden4FileAtchId);
        downForm.append(gv_Hidden4FilePolicy);
        downForm.append(gv_Hidden4FileId);

        // 파일 upload download시 form 의 target 이 될 iframe
        $("div[name='fileComponent']").append(gv_Iframe4DownTarget);

    }

    gf_setMode();

}


/**
* @function
* @param
* @see   파일 추가 버튼 클릭 시 이벤트 함수
* @return N/A
* @author 변형구
* @since 2013-03-20
* @version 1.0
*/
function gf_onFileSelect() {
    //var fileForm = $("form[name='uploadForm']");
    //var formData = new FormData(fileForm);
    var fileObj = document.getElementsByName("file");
    fileObj[0].click();
}


/**
* @function
* @param  obj<htmlObject> input type file 개체
* @see   input type file 개체의 onchange에 걸려 있는 이벤트 함수
* @return N/A
* @author 변형구
* @since 2013-03-20
* @version 1.0
*/
function gf_changeFile(obj) {

    if (obj.files.length == 0) return;

    if (gv_Files.length == 0) {
        gf_pushArray(obj);
    } else {
        if (gf_checkDupFile(obj.files)) {
            obj.value = "";
            gf_AlertMsg('co.warn.samefilename');
            return;
        } else {
            gf_pushArray(obj);
        }
    }
    // 파일 목록 테이블 생성
    gf_MakeFileTable(obj.files);
    // 파일 object 초기화
    obj.value = "";
}

/**
* @function
* @param  files<Object Array> 첨부된 파일 개체의 배열
* @see    첨부파일의 목록을 html로 그리는 함수
* @return N/A
* @author 변형구
* @since 2013-03-20
* @version 1.0
*/
function gf_MakeFileTable(files) {
    var tableObj = $("table[name=fileTable]").find("tbody");
    // 테이블의 마지막줄인 여기에 파일을 올려주세요 라인을 삭제 한다.
    var trObjs = $("table[name=fileTable]").find("tbody").find("tr");
    // 여기에 파일을 올려주세요 삭제
    $(trObjs[trObjs.length - 2]).remove();
    // 첨부된파일이 없습니다. 삭제
    $(trObjs[trObjs.length - 1]).remove();

    var totalSize = 0;
    for (var i = 0; i < files.length; i++) {
        // 파일 사이즈
        var fileSize = files[i].size;
        // 파일명
        var fileName = files[i].name;
        // 새로 추가된 tr의 넘버링을 위한 변수
        var trObj = $(tableObj).find("tr");
        totalSize += fileSize;

        // 각각 20메가 체크
        if ( fileSize > 838860800) {
        	gf_AlertMsg("첨부 가능한 각 파일 크기는 800메가 입니다.\r\n800메가가 넘는 파일은 첨부에서 제외합니다. ");
        	break;
        }

        // 전체 80메가 체크
        if ( totalSize > 838860800) {
        	gf_AlertMsg("첨부 가능한 전체 파일 크기는 800메가 입니다.\r\n800메가가 넘는 파일은 첨부에서 제외합니다. ");
        	break;
        }
        var fileTableRow = "<tr id='" + trObj.length + "' class=\"f1\" > 					\r\n " +
            "	<td class=\"din\" >" + trObj.length + "</td> 									\r\n " +
            "	<td class=\"din t\">" + fileName + "</td> 									\r\n " +
            "	<td class=\"din\" >" + Math.round((fileSize / 1024)) + "KB</td> 					\r\n " +
            "	<td class=\"din\" >" + gv_cmasComm.userNm + "</td> 								\r\n " +
            "	<td class=\"din\" >" + $.datepicker.formatDate('yy-mm-dd', new Date()) + "</td> \r\n " +
            "	<td class=\"din last\" > 													\r\n " +
            "		<div class=\"btn_area filedelete\" style=\"padding:0 10px;\"> 						\r\n " +
            "			<a class=\"btn s6\" onclick=\"gf_deleteFile(this)\" > 				\r\n " +
            "				<span>delete</span> 											\r\n " +
            "			</a> 																\r\n " +
            "		</div> 																	\r\n " +
            "	</td> 																		\r\n " +
            "	<td class=\"din\" >" + "" + "</td> \r\n " +
            "</tr> 																			\r\n ";

        tableObj.append(fileTableRow);
    }
    // table 재구성후 삭제한 여기에 파일을 올려주세요 라인을 다시 추가 한다.
    tableObj.append(gv_FileListDnDInfo);
    // table 재구성후 삭제한 첨부된 파일이 없습니다. 라인 추가
    tableObj.append(gv_FileListEmptyRow);


    //  mode 적용
    gf_setMode();
}

/**
* @function
* @param  obj<Html Object> 삭제 버튼 개체
*         fileId <number> 삭제할 파일 순번
* @see    특정 첨부파일을 삭제하는 함수
* @return N/A
* @author 변형구
* @since 2013-03-20
* @version 1.0
*/
function gf_deleteFile(obj, fileId) {

    var fileNm = $(obj).parent().parent().parent().find("td:eq(1)").text();

    if (!gf_IsNull(fileId)) {
        if (!confirm('파일도 함께 삭제됩니다. 계속 하시겠습니까?')) {
            return;
        }
    } else {
        // 아직 업로드된 파일이 아닌경우 전역 배열에서 삭제 처리 한다.
        for (var i = 0; i < gv_Files.length; i++) {
            if (fileNm == gv_Files[i].name) {
                gf_removeArray(i);
            }
        }
    }

    // 파일 목록 테이블 삭제
    $(obj).parent().parent().parent().remove();


    // 업로드된 파일이 삭제 처리 되는것이라면 실제 파일을 삭제하는 transaction 처리를 서버로 요청한다.
    if (!gf_IsNull(fileId)) {
        var fileInfo = {
            "fileAtchId": gv_FileAtchId,
            "fileId": fileId
        };

        // global file list dataset 에서 파일정보를 삭제 하기위해 dataset filtering
        gds_FileList.filter(
            function (DataSetRow) {
                if (DataSetRow.get("fileAtchId") == gv_FileAtchId && DataSetRow.get("fileId") == fileId) {
                    return true;
                }
                return false;
            }
        );

        for (var i = gds_FileList.size() - 1; i >= 0; i--) {
            gds_FileList.remove(i);
        }
        // filter 해제
        gds_FileList.filter(null);

        gf_Transaction("FILEDELETE", "/co/common/file/deleteFileInfo.xpl", fileInfo, {}, "gf_FileList_Callback", true);
    }


    var trObj = $("table[name=fileTable]").find("tr");
    // 헤더 제외, 마지막줄 제외 하고 순번에 넘버링을 수행한다.
    for (var i = 1; i < trObj.length - 1; i++) {
        $(trObj[i]).find("td:eq(0)").text(i);
    }
}

/**
* @function
* @param  fileAtchId <number> 첨부파일묶음번호
* @see    모든 첨부파일을 삭제하는 함수
* @return N/A
* @author 변형구
* @since 2013-03-20
* @version 1.0
*/
function gf_deleteAllFile(fileAtchId) {
    if (gf_IsNull(fileAtchId)) {
        fileAtchId = gv_FileAtchId;
    }

 // 첨부된 모든 파일이 삭제 됩니다. 계속 하시겠습니까?
	if ( !gf_ConfirmMsg('co.warn.allFileDelete') ) {
		return;
	}

    var fileInfo = {
        "fileAtchId": fileAtchId
    };

    gf_Transaction("FILEDELETEALL", "/co/common/file/deleteFileMasterInfo.xpl", fileInfo, {}, "gf_FileList_Callback", true);


}


/**
* @function
* @param  aryFiles<ArrayFileObj> 파일 개체
* @see    모든 첨부파일을 삭제하는 함수
* @return N/A
* @author 변형구
* @since 2013-03-20
* @version 1.0
*/
function gf_pushArray(obj) {
  var aryFiles = obj.files;
  var filePath = obj.value.split(",");
  for (var j = 0; j < filePath.length; j++) {
	  if(j==0){
		  continue
	  }
	  filePath[j] = filePath[j].substring(1);
  }
  for (var i = 0; i < aryFiles.length; i++) {
      gv_Files.push(aryFiles[i]);
      // dataset에 추가 하기 위해 json object 를 만든다.
      var jsonFileInfo = {
          "fileAtchId": gv_FileAtchId,
          "fileId": "",
          "sysCd": "",
          "filePath": filePath[i],
          "fileNm": aryFiles[i].name,
          "fileSize": aryFiles[i].size,
          "ecmNo": ""
      };
      gds_FileList.add(jsonFileInfo);
  }
}

/**
* @function
* @param  arrayIdx<number> 지우려는 배열의 index
* @see    첨부파일 배열에서 특정 파일을 삭제
* @return N/A
* @author 변형구
* @since 2013-03-20
* @version 1.0
*/
function gf_removeArray(arrayIdx) {

    var rmDSRow = gds_FileList.find("fileNm", gv_Files[arrayIdx].name);
    if (gf_IsNull(rmDSRow) || rmDSRow < 0) {
        gf_AlertMsg('co.warn.nodeletefileinfo');
        return;
    }
    gds_FileList.remove(rmDSRow);
    gv_Files.remove(arrayIdx);
    //gds_Test() ;
}

/**
* @function
* @param  aryFiles<ArrayFileObj> 파일 개체 배열
* @see    파일 개체내에 중복된 명칭이 있는지 확인
* @return N/A
* @author 변형구
* @since 2013-03-20
* @version 1.0
*/
function gf_checkDupFile(aryFiles) {

    for (var i = 0; i < aryFiles.length; i++) {
        for (var j = 0; j < gv_Files.length; j++) {
            if (gv_Files[j].name == aryFiles[i].name) {
                return true; // duplicate
            }
        }
    }
    return false; // non duplicate

}


/**
* @function
* @param  N/A
* @see   전역변수 / input type file 개체  초기화 함수
* @return N/A
* @author 변형구
* @since 2013-03-20
* @version 1.0
*/
function gf_onFileInit() {
    gv_Files = new Array();
    $("input[type='file'][name='file']").value = "";
}


/**
* @function
* @param  N/A
* @see    컴포넌트 reset 함수
* @return N/A
* @author 변형구
* @since 2013-03-20
* @version 1.0
*/
function gf_onResetComponnent() {
    var componentDiv = $("div[name='fileComponent']");
    componentDiv.empty();
    gf_InitFileUploadComponent();
}


/**
* @function
* @param  N/A
* @see    파일이 업로드 함수
* @return N/A
* @author 변형구
* @since 2013-03-20
* @version 1.0
*/
function gf_onFileUpload() {

    if (gv_Files.length == 0) {
        gf_Trace("업로드할 파일이 없습니다.");
        gf_UploadCallback(gv_FileAtchId);
        return false;
    }

    var msg = "";

    wrapWindowByMask();

	var nret = ESonicLinker.Connect(gv_EcmIp, "2104");

	if (nret > 0){

	}else {
		msg = ESonicLinker.GetErrorMessage();
		closeWindowByMask();
		alert(msg);
		return false;
	}

	for (var i = gds_FileList.size() - 1; i >= 0; i--) {
		//alert("fileNm : " + gds_FileList.get(i, "fileNm") + ", filePath : " + gds_FileList.get(i, "filePath"));

		if(gds_FileList.get(i, "ecmNo") != ""){
			continue;
		}

		var ecmNo = ESonicLinker.ContentUP(gds_FileList.get(i, "fileNm"),gds_FileList.get(i, "filePath"),"BASIC");

		if( ecmNo != ""){

		}else {
			msg = ESonicLinker.GetErrorMessage();
			ESonicLinker.DisConnect();
			closeWindowByMask();
			alert(msg);
			return false;

		}

		gds_FileList.set(i, "ecmNo", ecmNo);
		//alert("ecmNo : " + ecmNo);
	}

	ESonicLinker.DisConnect();

	closeWindowByMask();

	if (gf_IsNull(gv_FileAtchId)) {
    	var param = { fstRegUserId : gv_userId };
    } else {
    	var param = {fileAtchId : gv_FileAtchId,
	  			fstRegUserId : gv_userId
	  			};
    }
	var dataSets = {
  		input1 : gds_FileList.getAllData("U")
  	};
	gf_Transaction("RET_FILEUPLOAD", "/co/common/file/uploadEcmFileActiveX.xpl", param, dataSets, "gf_FileList_Callback", true);


}


/**
* @function
* @param  callBackFunc<String> callback 함수명
* @see    파일 업로드 실행후 수행될 business callback 함수 설정
* @return N/A
* @author 변형구
* @since 2013-03-20
* @version 1.0
*/
function gf_SetUploadCallback(callBackFunc) {
    gv_UploadCallFunc = callBackFunc;
}

/**
* @function
* @param mode<String> 파일 업로드 모드 upload, download, all
* @see   파일 첨부 컴포넌트의 mode 를 설정 하기 위한 함수
*        upload : 업로드 ui만 표출
*        download : 다운로드 ui만 표출
*        all : upload와 동일 이전에는 all 시 upload, download 모두 표출 되었으나
*              이후의 변경으로 인해 all 도 upload 로 유지
* @return N/A
* @author 변형구
* @since 2013-03-20
* @version 1.0
*/
function gf_setMode(mode) {

    if (!gf_IsNull(mode)) {
        gv_Mode = mode;
    }

    if (gv_Mode == "upload") {
        $("div[name='divBtnArea']").show();
        $(".filedelete").show();
        $(".dndRow").show();
        $(".emptyRow").hide();

    } else if (gv_Mode == "download") {
        $("div[name='divBtnArea']").hide();
        $(".filedelete").hide();
        $(".dndRow").hide();
        var trObjs = $("table[name=fileTable]").find("tbody").find("tr");
        if (trObjs.length == 3) {
            $(".emptyRow").show();
        } else {
            $(".emptyRow").hide();
        }

    } else if (gv_Mode == "all") {
        $("div[name='divBtnArea']").show();
        $(".filedelete").show();
        $(".dndRow").show();
        $(".emptyRow").hide();

    } else {
        gf_AlertMsg('co.warn.nofilemode');
    }

}

/**
* @function
* @param policy<String> file upload policy, "default", "excel"
* @see   파일 업로드 정책을 설정함 기본 정책은 default
*        defaut : 기본 결재 파일 업로드 policy
*        excel : excel 업로드시 사용하는 policy
*        이 policy 는 dispatcher-import.xml에 정의되어있다.
* @return N/A
* @author 변형구
* @since 2013-03-20
* @version 1.0
*/
function gf_SetPolicy(policy) {
    if (gf_IsNull(policy)) {
        policy = "default";
    }

    gv_Policy = policy;

}

/**
* @function
* @param enableMultipleUpload<boolean>
* @see   멀티 파일 첨부의 가능여부 설정 기본은 true 이다.
* @return N/A
* @author 변형구
* @since 2013-03-20
* @version 1.0
*/
function gf_SetEnableMultipleUpload(enableMultipleUpload) {
    if (gf_IsNull(enableMultipleUpload)) {
        enableMultipleUpload = true;
    }

    gv_EnableMultipleUpload = enableMultipleUpload;


}


/**
* @function
* @param  fileAtchId<String> file atch id
* @see   파일 업로드 실행후 수행되는 callback
* @return N/A
* @author 변형구
* @since 2013-03-20
* @version 1.0
*/
function gf_UploadCallback(fileAtchId) {

    // gf_SetUploadCallback 를 통해 설정된 callback function 있는지 검증
    if (!gf_IsNull(gv_UploadCallFunc)) {
        // 설정된 callback 이 함수 인지 검증
        var callFunction = eval(gv_UploadCallFunc);
        if (typeof callFunction != "function") {
            gf_AlertMsg('sg.inf.undefCallback', [callBackFunc]);
            return;
        }
        // callback 함수 수행 callback 으로 전달되는 인자는 file atch id 하나이다.
        eval(gv_UploadCallFunc + "('" + fileAtchId + "');");
    }
    // uploadform 에 fileatchid 설정
    $("form[name='uploadForm'] > input[name$='fileAtchId']")[0].value = fileAtchId;

    if (gf_IsNull(gv_FileAtchId)) {
        gv_FileAtchId = fileAtchId;
    }

    // 첨부 완료후 전역 파일 변수 초기화
    gv_Files = new Array();
    if (gf_IsNull(fileAtchId)) {
        return;
    }
    // 저장된 첨부 조회 수행
    //gf_retrieveFileList(fileAtchId);



}

/**
* @function
* @param fileAtchId<String>
* @see   파일 첨부 ID 를 설정한다.
* @return N/A
* @author 변형구
* @since 2013-03-20
* @version 1.0
*/
function gf_setFileAtchId(fleAtchId) {
    gf_retrieveFileList(fileAtchId);
}


/**
* @function
* @param fileAtchId<String> 파일 첨부 아이디
* @see   업로드된 파일 목록 조회 함수
* @return N/A
* @author 변형구
* @since 2013-03-20
* @version 1.0
*/
function gf_retrieveFileList(fileAtchId) {

    if (gf_IsNull(fileAtchId)) {
        fileAtchId = "";
        gv_FileAtchId = "";
    } else {
        gv_FileAtchId = fileAtchId;
    }
    // form의 hidden 필드에 fileAtchId 설정
    $("form[name='uploadForm'] > input[name$='fileAtchId']")[0].value = fileAtchId;

    // 조회 transaction 수행
    var fileInfo = {
        "fileAtchId": fileAtchId
    };
    gf_Transaction("SELECT_FILELIST", "/co/common/file/retrieveWebFileList.xpl", fileInfo, {}, "gf_FileList_Callback", true);
}


/**
* @function
* @param fileAtchId<String> 파일 첨부 아이디
*        fileId<String> 파일 순번
* @see   파일 다운로드 함수
* @return N/A
* @author 변형구
* @since 2013-03-20
* @version 1.0
*/
function gf_DownloadFile(rownum) {

	wrapWindowByMask();
	var idx = rownum - 1;
	var ecmNo = gds_FileList.get(idx, "ecmNo");
	var fileNm = gds_FileList.get(idx, "fileNm");

	//alert("fileNm : " + fileNm + ", ecmNo : " + ecmNo);

	var nret = ESonicLinker.Connect(gv_EcmIp, "2104");
	var nret = ESonicLinker.ContentDown(ecmNo, "C:\\Download\\"+fileNm);
	var msg = "";
	if( nret == 0){
	  msg = "C:\\Download 에 다운로드 완료 되었습니다."
	}else {
  	msg = ESonicLinker.GetErrorMessage();
	}
	closeWindowByMask();
	alert(msg);

	ESonicLinker.DisConnect();
}



/**
* @function
* @param  strSvcId<String> transaction 서비스 id
*         resultData<JsonObject> transaction 결과로 리턴된 jsonobject
* @see    파일업로드중 transaction 실행후 수행될 callback 함수
* @return N/A
* @author 변형구
* @since 2013-03-20
* @version 1.0
*/
function gf_FileList_Callback(strSvcId, obj, resultData) {

    // transaction의 정상 처리 여부를 판단한다.
    if (!gf_ChkTransaction(strSvcId, resultData, true)) {
        return;
    }

    // 조회 transaction 후 callback
    if (strSvcId == "SELECT_FILELIST") {
        var tbody = $("table[name='fileTable']").find("tbody");
        var tableTr = $("table[name='fileTable']").find("tbody").find("tr");

        // 이전에 유지되던 리스트 삭제
        for (var i = tableTr.length - 1; i > 0; i--) {
            $(tableTr[i]).remove();
        }
        // gloval file list dataset
        gds_FileList.setAllData(resultData["fileList"]);
        //gds_Test() ;
        // file list draw
        if (gds_FileList.size() > 0) {
            $.each(resultData, function (i, itemAry) {
                if (i == "fileList") {
                    $.each(itemAry, function (j, item) {
                        tbody.append("<tr class=\"f1\"> 			     																									\r\n" +
                            "	<td class=\"din\" >" + item.rownum + "</td>																								\r\n" +
                            "	<td class=\"din t\" > <a style=\"text-decoration:none;text-align:center\" href='#' onclick='gf_DownloadFile(" + item.rownum + ")'>" + item.fileNm + "</a></td>	\r\n" +
                            "  <td class=\"din\">" + Math.round((item.fileSize / 1024)) + "KB</td>																		\r\n" +
                            "	<td class=\"din\">" + item.fstRegUserId + "</td>																						\r\n" +
                            "  <td class=\"din\">" + item.fstRegDt + "</td>																							\r\n" +
                            "  <td class=\"din last\">																												\r\n" +
                            " 		<div class=\"btn_area filedelete\" style=\"padding:0 10px;\">																				\r\n" +
                            "			<a class=\"btn s6\" onclick='gf_deleteFile(this, " + item.fileId + ")'>															\r\n" +
                            "				<span>delete</span>																										\r\n" +
                            "			</a>																														\r\n" +
                            "		</div> 																															\r\n" +
                            "  <td class=\"din\">" + item.ecmNo + "</td>																							\r\n" +
                            "</tr>																																	\r\n ");
                    });
                    tbody.append(gv_FileListDnDInfo);
                    tbody.append(gv_FileListEmptyRow);
                    gf_setMode();
                    $(".emptyRow").hide();

                };
            });
        } else {
            tbody.append(gv_FileListDnDInfo);
            tbody.append(gv_FileListEmptyRow);
            gf_setMode();
        }

    } else if (strSvcId == "FILEDELETE") {
        // 삭제 transaction후 callback;
        gf_retrieveFileList(gv_FileAtchId);
    } else if (strSvcId == "FILEDELETEALL") {
        // 삭제 transaction후 callback;
        gv_FileAtchId = "";
        gf_retrieveFileList(gv_FileAtchId);
    } else if (strSvcId == "RET_FILEUPLOAD") {
		//alert("파일 업로드가 완료 되었습니다.");
		var fileAtchIdTmp = "";
		$.each(resultData, function(i, itemAry) {
			if ( i == "fileAtchId") {
				$.each(itemAry, function( j, item)  {
					fileAtchIdTmp = item.fileAtchId;
				} );
			};
		});
		gf_UploadCallback(fileAtchIdTmp);

    }


}


//추후에 activex 배포할지 여기에 있는 버젼을 수정하여야 한다.

var G_COM_URL = gContextPath+"/common/install/eSonicLinker.cab#version=5.0.0.10";    //OCX URL
var G_TOT_URL = gContextPath+"/common/install/eSonicLinker.exe";                           //통합 설치파일 URL
var G_COM_CAB = "CLSID:0874D2C2-E0DC-4F91-A32A-67A4F4608E36"; //OCX CLASSID
var G_SCAN_OCX = G_COM_CAB ;//"CLSID:B52D95B7-03A4-43C9-AA80-DDA50F9115FA";  //등록OCX CLASSID

$(document).ready(function() {

	//접속 컴퓨터에 이미지 등록 프로그램이 깔렸는지 검사 업을경우 설치 화면을 보여줌
	$('#btn_down').click(function() {
        location.href = G_TOT_URL;
    }).css('cursor', 'pointer');

    var $obj_EESCom = $('<OBJECT ID="comCab_check" CLASSID="'+ G_COM_CAB +'"  width="1" height="1" CODEBASE="' + G_COM_URL + '"></OBJECT>');
    $('body:last').append($obj_EESCom);

    if(!$obj_EESCom[0].object) {
        //$('#ocx_div').show();
        var intV = setInterval(function(){
            if($obj_EESCom[0].object) {
                clearInterval(intV);
                location.reload();
            }
        }, 1000);

        return;

    }

    // -- 접속 컴퓨터에 이미지 등록 프로그램이 깔렸는지 검사 업을경우 설치 화면을 보여줌

    var $obj_ESonicLinker = $("<object ID='ESonicLinker' CLASSID="+ G_SCAN_OCX+ "/>");
    $("#tb_ESonicLinker td:last").append($obj_ESonicLinker);

    fn_PageInit();

});

//OCX 셋팅
function fn_PageInit(){
	ESonicLinker.SvrID = "BASIC";
	ESonicLinker.UserID = "ROSIS";
	ESonicLinker.LocCD = "100";

}

function wrapWindowByMask() {
        //화면의 높이와 너비를 구한다.
        var maskHeight = $(document).height();
//      var maskWidth = $(document).width();
        var maskWidth = window.document.body.clientWidth;

        var mask = "<div id='mask' style='position:absolute; z-index:9000; background-color:#000000; display:none; left:0; top:0;'></div>";
        var loadingImg = '';

        loadingImg += "<div id='loadingImg' style='position:absolute; left:50%; top:40%; display:none; z-index:10000;'>";
        loadingImg += " <img src='"+gContextPath+"/common/images/loading.gif'/>";
        loadingImg += "</div>";

        //화면에 레이어 추가
        $('body')
            .append(mask)
            .append(loadingImg)

        //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
        $('#mask').css({
                'width' : maskWidth
                , 'height': maskHeight
                , 'opacity' : '0.3'
        });

        //마스크 표시
        $('#mask').show();

        //로딩중 이미지 표시
        $('#loadingImg').show();
    }

    function closeWindowByMask() {
        $('#mask, #loadingImg').hide();
        $('#mask, #loadingImg').remove();
    }
