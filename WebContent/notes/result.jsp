<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="result">
<h3>${requestScope["message"]}</h3> <br>
</div>
File name  :  ${requestScope["name"]} <br>
File size  :  ${requestScope["size"]} <br>
File type  : ${requestScope["type"]}

<!-- <a href="DownloadServlet">download the jsp file</a>  -->
</body>
</html>