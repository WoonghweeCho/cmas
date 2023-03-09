alert("tweditor.js");

//#tip create editor
if(true)
{
	//운영용 라이센스
//	var clsid =  "976A7D6C-B14C-4e50-A5C3-B43D8C49D8C8";
//	var version = "3,7,1,1006";
//	var key = "WMt/6sdX77X2Pq7Tk8mxYrteLYKHZGnpAviPYXxsyg6KUJhafUthXe/hIgafcjG698Vv5Zg5/Spni04MhQHvPgS74NAXndZFlyW3GO6JmldTtARExVQ9UlYHaN5DEd4C5BVlL4WCBxFSYUq4BxsMd2HLbKzRdRujJh8/Jo7ft4OS7J/CZu5nDsKwiNbQs8p8vvdB6qX64Ywuo/c+efwzUSXXy9Ww6AjXQvtAZDUDmlzjXXVGURjhRpn3btVob1VWYxFvS+Az48pgmDKuWBT8oiFPqXvGTFK8b74yPxB+3zWNVHRvYgx+SwJClG7HrmGmKNvsR6UNjVeZyTHmAM7RVrZ09HiDn8C94Gz1fBfROzXvWT5xBloRiu0+ptqOR2xBrzIKKAIrDfM54RIX7OVN75gEzbPuw27PMXOpLP1voNCASwbMrQ7sGp6IJBjCV78n6XLL1v2HBYKjTkQgi9W3LOb7DImML1T/iPaz6oIIZfg0Wes2odwQu9LmoE9aXFQw323I6v+1RATr2aG+WAse2jL/nK2hgjlzyLms2LsM+IwvXuW2CvaToiBcxOjTZhjHAb9E9saBmS6vWkL3q4gqioIQuFCCJeagp8lPoFRJZAohCQZM05gSiEah3kQHwq//uq/Yth2bQMUyBCZk6c0/ks2G1aUfur3V2leXyIh7FqtMN1HiFa9G8CR1GRvIiDeN4PsdhUtDaAJJ83qlNy21D7hK6KqKBkACS6wcaGJeakgar7tYhZfj/TrEOwcHFkKi2F2TM0z0gdQyt+MCAM0f8w==";

	// 개발용 라이센스
	var clsid =  "976A7D6C-B14C-4e50-A5C3-B43D8C49D8C8";
	var version = "3,7,1,1014";
	var key = "rK7Zm5P+LSHfubsKavCS2VbAbgJeD8oFqUUdJ/nbw0p2r1aksszLEfzx0Gj2gcbJbbPHUSWeUPScws18rp9Dk+e+7RjvRiTIgF7jo5Cq2n041gea6vPfKx8KmYcmVYv0Gq+7WIWX4/06xDsHBxZCothdkzNM9IHUMrfjAgDNH/M=";

	var cab = "tweditor.cab";
	var env = "env.xml";
	var id = "twe";
	var applyinitdata = 1;//apply:1
	var editmode = 0;//edit:0

	document.write('<object ID="'+id+'" width="100%" height="100%" CLASSID="CLSID:'+clsid+'" CODEBASE="'+cab+'#version='+version+'">');
	document.write('<PARAM name="InitFile" value="'+env+'"/>');
	document.write('<PARAM name="ApplyInitData" VALUE="'+applyinitdata+'"/>');
	document.write('<PARAM name="Mode" VALUE="'+editmode+'"/>');
	document.write('<PARAM name="LicenseKey" value="'+key+'">');
	document.write('</object>');
}

//#tip p->br, br->p
if(false)
{
	document.write('<script language="JScript" FOR="'+id+'" EVENT="OnKeyDown(event)">');
	document.write('	if (!event.shiftKey && event.keyCode == 13)');
	document.write('	{');
	document.write('		document.'+id+'.InsertHtml("<br>");');
	document.write('		event.returnValue = true;');
	document.write('	}');
	document.write('	if (event.shiftKey && event.keyCode == 13)');
	document.write('	{');
	document.write('		document.'+id+'.InsertHtml("<p>");');
	document.write('		event.returnValue = true;');
	document.write('	}');
	document.write('</script>');
}

//#tip protected backspace
if(false)
{
	document.write('<script language="JavaScript">');
	document.write('document.onkeydown = twekeyhandler;');
	document.write('function twekeyhandler() {');
	document.write('    if (window.event && window.event.keyCode == 8) {');
	document.write('        alert("protected backspace key");');
	document.write('        return false;');
	document.write('    }');
	document.write('}');
	document.write('</script>');
}

//#tip activex redesign ref)http://www.tagfree.com/ver2/Support_Center/faq/faq_view.asp?supno=11
if(false)
{
	if(editmode != 1)
	{
		document.write('<script language="JScript" FOR="'+id+'" EVENT="BeforeTabChange">');
		document.write('	var view = document.'+id+'.ActiveTab;');
		document.write('	if(view == 1 || view == 3)');
		document.write('	{');
		document.write('		document.'+id+'.ObjectToScript("object", "object.asp","tag","tweobject");');
		document.write('		document.'+id+'.ObjectToScript("embed", "object.asp","tag","tweobject");');
		document.write('	}');
		document.write('</script>');
		document.write('<script language="JScript" FOR="'+id+'" EVENT="OnLoadComplete">');
 		document.write('	var view = document.'+id+'.ActiveTab;');
 		document.write('	if(view == 1 || view == 3) document.'+id+'.ScriptToObject("tag","tweobject");');
		document.write('</script>');
	}
}

//#tip custom popup menu
if(false)
{
	document.write('<OBJECT id="ContextMenu" width="0" height="0" classid="CLSID:7823A620-9DD9-11CF-A662-00AA00C066D2" CODEBASE="http://activex.microsoft.com/controls/iexplorer/x86/iemenu.cab#version=4"></OBJECT>');
	document.write('<script language="JScript" FOR="twe" EVENT="OnContextMenu(event)">');
	document.write('	ContextMenu.Clear();');
	document.write('	ContextMenu.AddItem ("1", 1);');
	document.write('	ContextMenu.AddItem ("2", 2);');
	document.write('	ContextMenu.PopUp();');
	document.write('	event.returnValue = true;');
	document.write('</script>');
	document.write('<SCRIPT LANGUAGE="VBscript">');
	document.write('Sub ContextMenu_Click(ByVal x)');
	document.write('	alert(x)');
	document.write('End Sub');
	document.write('</SCRIPT>');
}