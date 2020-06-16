<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
if((String)session.getAttribute("currentSessionUser") == null)
	response.sendRedirect("/e-JAWI/loginParent.jsp");
%>
    

<!DOCTYPE html>
<html lang="en">
  <head>
		<title>Home</title>
</head>

<body id="page-top">
	
   <jsp:include page="/parent/header.jsp" />

       <!-- Page Top Navigation  -->
			<jsp:include page="navigation.jsp" />  
			
          <!-- Page Heading -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Home</h1>
          </div>
          <div class="row">
			<div class="mx-auto">
			 <br><br><h1>Welcome ^_^</h1><br><br>
			 </div>



   </body>

</html>

