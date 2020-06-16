<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<html>
<head>
		<meta charset="UTF-8">
	 	<title>Delete Notes</title>
</head>
<body>
          <div class="row">
			<div class="mx-auto" style="width: 400px;">
			<form action="NotesController" method="post">
				
				<br>ID:<input type="text" name="notesID" value="<c:out value="${notes.notesID}" />"  readonly/>	<br>
				<br>Title:<input type="text" name="title" value="<c:out value="${notes.notesTitle}" />" readonly/>	<br>
				<br>Content:<input type="text" name="content" value="<c:out value="${notes.notesContent}" />" readonly/>	<br>
				
				
				
				<input type = "submit" name="action" value="Delete" class="btn btn-primary" onclick="return confirm('Are you sure want to delete this data? \nYou can\'t recover the data if u delete it')">
				
				
			</form>
	
</body>	
</html>