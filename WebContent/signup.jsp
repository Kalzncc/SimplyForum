<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <!-- 
  Kalzn 18软件02 马明皓  
  用户注册界面
   -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>注册</title>
<style type="text/css">
	@import url("css/style.css");
</style>
<script>
	function check() {
		var name = document.getElementById("name").value;
		var regx = /^[a-zA-Z_]{6,18}$/
		if (name=="") {
			alert('用户名不可为空!');
			return false;
		}
		else if (!regx.test(name)) {			
			alert('用户名仅由8~16位字母,下划线组成');
			return false;
		}
		
		var text =  document.getElementById("pwd").value;
		var regx = /^(?=.*\d)(?=.*[a-zA-Z])[\da-zA-Z]{6,}$/;
		if (text=="") {
			alert('密码不可为空!');
			return false;
		}
		else if (!regx.test(text)) {
			alert('密码必须包含8~16位大小写字母和数字!');
			return false;
		}
		
		var text2 = document.getElementById("ipwd").value;
		if (text != text2) {
			alert('两次输入的密码不一致');
			return false;
		}
		return true;
	}
	function createRequest(url) {
		http_request=false;
		http_request=new XMLHttpRequest();
		if(!http_request) {
			alert('链接异常！');
			return false;
		}
		http_request.onreadystatechange=getResult;
		http_request.open('GET', url, true);
		http_request.send(null);
	}
	function getResult() {
		if (http_request.readyState==4) {
			if (http_request.status==200) {
				document.getElementById("check_div").innerHTML=http_request.responseText;
				
			} else {
				alert('请求失败!');
			}
		}
	}
	function Dycheck() {
		var name = document.getElementById("name").value;
		var regx = /^[a-zA-Z_]{6,18}$/
		if (name=="") {
			alert('用户名不可为空!');
			return false;
		}
		else if (!regx.test(name)) {			
			alert('用户名仅由8~16位字母,下划线组成');
			return false;
		}
		createRequest('Admin/CheckDeal?username='+name);
	}
 
</script>
</head>
<body class="main_body">
	<form action="Admin/SignupDeal" method="post" onSubmit = "return check()">
		<table class="main_table">
			<tr>
				<td colspan=2>
					<div id="check_div"></div>
				</td>
			</tr>
			<tr>
				<td>
					管理用户名：
				</td>
				<td>
					<input id="name" type="text" name="adminname">
				</td>
				<td>
					<input type="button" value="检查用户名" onclick="Dycheck()")>
				</td>
			</tr>
			<tr>
				<td>
					密码：
				</td>
				<td>
					<input id="pwd" type="password" name="password">
				</td>
			</tr>
			<tr>
				<td>
					确认密码：
				</td>
				<td>
					<input id="ipwd" type="password" name="ipassword">
				</td>
			</tr>
			<tr>
				<td colspan=2>
					<input type="submit" value="注册">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>