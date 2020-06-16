<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>	
		<% String email = (String) session.getAttribute("currentSessionUser"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Notes</title>
</head>
<body>
        <table id="myTable" class="display" border="1" cellpadding="5" width="100px">
           <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                 <th>Update</th>
                 <th>Delete</th>
              
            </tr>
            </thead>
            <tbody>
        <c:forEach  var="notes" items="${notes}">
                <tr>
                    <td><c:out value="${notes.notesID}" /></td>
                    <td><c:out value="${notes.notesTitle}" /></td>
                    <td><a href="NotesController?action=updateNotes&notesID=<c:out value="${notes.notesID}" />" class="btn btn-primary"><b>Update</b></a></td>
    				<td><a href="NotesController?action=deleteNotes&notesID=<c:out value="${notes.notesID}" />" class="btn btn-primary"><b>Delete</b></a></td>
			</tr>
            </c:forEach>
          </tbody>
        </table>
    </body>
</html>