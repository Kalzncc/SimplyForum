<%@page import="com.kalzn.service.impl.AdminServiceImpl"%>
<%@page import="com.kalzn.bean.BadWord"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
 <!-- 
  Kalzn 18软件02 马明皓  
  admin管理页面，用于管理员对屏蔽词进行查改
   -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>管理</title>
<style type="text/css">
	@import url("css/style.css");
</style>
<script>
	function deleteDb(id) {
		if (confirm('你确定要删除此屏蔽词吗？')) {
			
			document.getElementById("wordid").value=id;
			document.getElementById("form1").submit();
		}
	}
	function check() {
		if (document.getElementById("word").value=="") {
			alert('屏蔽词不可为空');
			return false;
		}
		return true;
	}
</script>
</head>
<body class="main_body">
	<form id="form1" action="Admin/DeleteBadWordDeal" method="post">
		<input id="wordid" type="hidden" name="wordid" value="">
	</form>
		<div class="head_div">
		<div style="float:left;magin:5px;padding:5px">
		<a href="index.jsp">首页</a>
		</div>
		<div style="float:right;magin:5px;padding:5px">
			<%
				if (session.getAttribute("admin") == null) {
					response.setHeader("refresh", "0;URL=index.jsp");
					return;
				} else {
					out.append("欢迎你！管理员："+session.getAttribute("admin"));
					out.append("&nbsp;&nbsp;");
					
					out.append("<a href=\"index.jsp\">前台</a>");
					out.append("&nbsp;&nbsp;");
					out.append("<a href=\"Admin/LogoutDeal\">登出</a>");
				}
			
			%>
		</div>
	</div>
	<table class="main_table">
		<tr>
			<th colspan=2>
				<h3>
					屏蔽词列表
				</h3>
			</th>
		</tr>
		<%
			Vector<BadWord> set = (new AdminServiceImpl()).getBadWord();
			for (BadWord x:set) {
				out.append("<tr>");
				out.append("<td>");
				
				out.append(x.getWord());
				out.append("</td>");
				out.append("<td>");
				out.append("<input type=\"button\" value=\"删除\" onclick=\"deleteDb("+ x.getDbid()  +")\">");
				out.append("</td>");
				out.append("</tr>");
			}
		%>
	</table>
	<form action="Admin/AddBadWordDeal" method="post" onSubmit="return check()">
	<table class="main_table" style="padding:10px">
		<tr>
			<td>
				添加屏蔽词：
			</td>
			<td>
				<input type="text" id="word" name="word">
			</td>
			<td>
				<input type="submit" value="添加">
			</td>
		</tr>
		
	</table>
	</form>
</body>
</html>