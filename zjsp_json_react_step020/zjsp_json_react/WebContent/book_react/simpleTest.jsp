<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
  <head>
    <meta charset="utf-8">
    <title>1st React Component in Servlet&JSP.</title>
  </head>

  <body>
    <h1>Simple Test to use React in JSP&Servlet... <h1>

    <script>
     	let zmessage = '${zmessage}';
     	if (zmessage == 'Simple Test') {
         	alert(zmessage);
     	}
     </script>

    <div id="zroot"></div>
    <script src="/zjsp_json_react/resources/built/bundle.js" type="text/javascript"></script>
    
  </body>
</html>