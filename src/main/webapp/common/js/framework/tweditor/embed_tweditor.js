var oEmbed = document.createElement('<OBJECT id=twe codeBase="'+gv_ContextPath+'/common/install/download/tweditor.cab#version=3,7,1,1014" classid="CLSID:976A7D6C-B14C-4e50-A5C3-B43D8C49D8C8" width="640" height="480" VIEWASTEXT></OBJECT>');

var oParam1 = document.createElement('<PARAM name=DesignMode value=html>');	//에디터 모드 설정
var oParam2 = document.createElement('<PARAM name=ReadOnly value=false>');		//읽기 모드 설정
var oParam3 = document.createElement('<PARAM name=ShowToolbar value=true>');	//툴바 표시 여부

var oParam4 = document.createElement('<param name=CharSet value=utf-8>');		//Character Set
var oParam7 = document.createElement('<param name=Settings value="http://' + g_sHostName + '/common/js/framework/tweditor/env.xml">');	//환경 설정 파일
var oParam8 = document.createElement('<param name="AddFileAttach" value="' + g_sDownFileList + '">');	//첨부 파일 목록
var oParam12 = document.createElement('<param name=ShowSymbolbar value=true>');	//기호 막대 표시 여부
var oParam13 = document.createElement('<param name=ShowEncodeCbo value=false>');	//인코딩 콤보 박스 활성화 여부

oEmbed.appendChild(oParam1);
oEmbed.appendChild(oParam2);
oEmbed.appendChild(oParam3);
oEmbed.appendChild(oParam4);
oEmbed.appendChild(oParam7);
oEmbed.appendChild(oParam8);
oEmbed.appendChild(oParam12);
oEmbed.appendChild(oParam13);

Contents.appendChild(oEmbed);
