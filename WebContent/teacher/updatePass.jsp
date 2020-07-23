<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

 <% if((String)session.getAttribute("currentSessionUser") == null )
      response.sendRedirect("/e-JAWI/loginTeacher.jsp"); %>
<!DOCTYPE html>

<html lang="en">
<%	String teacherEmail = (String)session.getAttribute("currentSessionUser");%>

	<head>
		<meta charset="UTF-8">
	    
		<title>Update Password</title>
</head>


<body>
			<form method="post"  action="/e-JAWI/PasswordController">
       		
			<br>Current Password:<input type="password" class="form-control" name="currentPassword" />
			<br>New Password:<input type="password" class="form-control" name="newPassword"/>		
			
			<input type="email" hidden class="form-control" name="email" value="<c:out value="<%=teacherEmail%>" />" />
			<br><br><input type = "submit" name="action" class="btn btn-primary" value = "Submit">

		</form>
	
</html>
