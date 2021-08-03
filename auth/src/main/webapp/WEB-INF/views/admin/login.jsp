<%@ page language="java" contentType="text/html; charset=EUC-KR"
		pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>	
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Mybury Admin</title>
		<link href="../static/css/bootstrap/bootstrap.min.css" rel="stylesheet">
		<link href="../static/css/bootstrap/signin.css" rel="stylesheet">
	</head>
	<body class="text-center">
		<div id="content" class="w-100">
			<form class="form-signin" action="/admin/otpCheck" method="post">
				<img class="mb-4" src="../static/img/tablet/app-icon@3x.png" alt="" width="72" height="72">
				
				<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
				
				<label for="otp" class="sr-only">T</label>
				<input type="text" id="userName" name="userName" class="form-control" placeholder="username" required autocomplete="off">
				
				<label for="userName" class="sr-only">T</label>
				<input type="text" id="otpValue" name="otpValue" class="form-control" placeholder="otp" required autocomplete="off">
				
				<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
				
				<p class="mt-5 mb-3 text-muted">&copy; mybury</p>
			</form>
		</div>
		
		<script src="../../static/js/vue/vue.js"></script>
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
		<script type="module" src="../../static/js/vue/vue_login.js"></script>
	</body>
</html>