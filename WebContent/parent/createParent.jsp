<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Form</title>
</head>
<body>
<form action="../ParentController" method="post">
		<div class="">
			Email: <br>
			<input class="form-control" type="text" placeholder="Enter Email"
				name=email required><br>
		</div>
		<div class="">
			Password: <input class="form-control" type="password"
				placeholder="Enter Password" name=password required><br>
		</div>
		<div class="">
			Name: <br>
			<input class="form-control" type="text" placeholder="Enter Name"
				name=name required><br>
		</div>
		<div class="">
			Address: <br>
			<input class="form-control" type="text" placeholder="Enter Address"
				name=address required><br>
		</div>
		<div class="">
			Phone: <input class="form-control" type="text"
				placeholder="Enter Phone" name=phone
				pattern="^[0][1-9]\d{8,9}$|^[1-9]\$"
				title="Put Number Only (min 10, max 11)" required><br>
		</div>
		 
		<br> <br> <button type="submit" value="Register" name="action"
			class="btn btn-primary">Submit</button>
	</form>
</body>
</html>