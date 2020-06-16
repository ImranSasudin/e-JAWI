<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<% String email = (String) session.getAttribute("currentSessionUser"); %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Notes</title>
        <!-- Make sure the path to CKEditor is correct. -->
        <script src="https://cdn.ckeditor.com/4.14.0/standard/ckeditor.js"></script>
    </head>
    <body>
    <form action="../NotesController" method="post">  
		 Title: <input type="text" name="title" required>  
		 <input type="submit" value="Add" name="action">  
		<br><br>  
		 Content:<br><textarea name="content" placeholder="Enter text here..." required></textarea>   
	</form>
        <script>
                        CKEDITOR.replace( 'content' );
                </script>
    </body>
</html>