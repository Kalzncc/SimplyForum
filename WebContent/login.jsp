<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <!-- 
  Kalzn 18软件02 马明皓  
  admin登陆界面，用于管理员登陆
   -->
<!DOCTYPE html>
<html>
<style type="text/css">
	@import url("css/style.css");
</style>
<head>
<meta charset="utf-8">
<title>登陆</title>
</head>
<body class="main_body">
	<form action="Admin/LoginDeal" method="post">
		<table class="main_table">
			<tr>
				<td>
					管理用户名：
				</td>
				<td>
					<input type="text" name="adminname">
				</td>
			</tr>
			<tr>
				<td>
					密码：
				</td>
				<td>
					<input type="password" name="password">
				</td>
			</tr>
			<tr>
				<td colspan=2>
					<a href="signup.jsp"> 没有账号？&nbsp;点击注册 </a>
					&nbsp;&nbsp;&nbsp;
					<input type="submit" value="登陆">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>