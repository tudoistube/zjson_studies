<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String pagefile=request.getParameter("page");
	if (pagefile==null){pagefile="ch6_1_newitem";}
%>
<html>
<head>
<title>Template Test</title>
</head>
<body>
<table width="960" border="1" color="gray" align="center">
	<tr>
		<td height="43" colspan=3 align=left>
			<jsp:include page="ch6_1_top.jsp"/>
		</td>
	</tr>
	<tr>
		<td width="15%" align=right valign=top><br>
			<jsp:include page="ch6_1_left.jsp"/>
		</td>
		<td colspan=2 align=center>
			<jsp:include page='<%=pagefile+".jsp" %>'/>
		</td>
	</tr>
	<tr>
		<td width="100%" height="40" colspan="3">
			<jsp:include page="ch6_1_bottom.jsp"/>
		</td>
	</tr>
</table>
</body>
</html>
