<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Account</title>
</head>
<body>
	<form method="post"  action="StudentController">
       		
			<br>Email:<input type="email" class="form-control" name="email" value=" <c:out value="${student.studentEmail}" />" required/>
			<!-- <br>Password: <a href="/e-JAWI/student/updatePass.jsp" class="btn btn-info">Update Password</a><br> -->
			<br>Name:<input type="text" class="form-control" name="name"  pattern="^[_A-z]*((-|\s)*[_A-z])*$" title="Name cannot contain any special character or number" value="<c:out value="${student.studentName}" />" required/>		
			<br>Address:<input type="text" class="form-control" name="address"  value="<c:out value="${student.studentAddress}" />"required/>	
			<br>Phone:<input type="text" class="form-control" name="phone" pattern="^[0][1-9]\d{8,9}$|^[1-9]\$" title="Put Number Only (min 10, max 11)" value="<c:out value="${student.studentPhone}" />"required/>	
						
			
			<br><br><input type = "submit" name="action" class="btn btn-primary" value = "UpdateStudent">

		</form>
</body>
</html>