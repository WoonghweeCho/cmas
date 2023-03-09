<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/jsp/comm/include/baseSetting.jsp" %>
<!DOCTYPE html>
<head>
	<jsp:include page="/common/jsp/comm/include/clientLib.jsp">
		<jsp:param value="공통업무시스템-iframe proxy" name="title"/>
	</jsp:include>
</head>
<script type="text/javascript">

      // This event is fired when you change my window size
      window.onresize = function(){

        // Retrieve the hash of my url, ex: #color:red
        var hash = document.location.hash;
        var valuePath = window.top == window ? window : top;

        if(hash != ''){

          // Split # to extract "color:red"
          var hash = hash.split('#');

          // Split : to extract "color" and "red"
          var params = hash[1].split(':');

          switch(params[0]){

            case 'refresh':
              // Change my parent body background color
              valuePath.f_refreshList();
              break;

            case 'nochange':
              // Call No Change
              valuePath.f_noChange();
              break;

            default:
              break;
          }
        }
      };
    </script>
<body>
</body>
</html>