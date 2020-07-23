<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<% String email = (String) session.getAttribute("currentSessionUser"); %>
	<% String title = (String) session.getAttribute("currentSessionUser"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
</head>
<body>
Welcome <%=session.getAttribute("parentName") %><br><br>

<a href="parentController?action=ViewAccount&email=<%=email%>">View Parent Account</a>
<a href="NotesController?action=ViewNotes&title=<%=title%>">View Notes</a>

</body>
</html>