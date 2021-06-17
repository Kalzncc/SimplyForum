<%@page import="com.kalzn.bean.PageData"%>
<%@page import="com.kalzn.bean.Reply"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.kalzn.bean.Message"%>
<%@page import="com.kalzn.dbcon.Dbcon"%>
<%@page import="com.kalzn.service.impl.UserServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <!-- 
  Kalzn 18软件02 马明皓  
  单个信息的显示界面。
   -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>信息</title>
<style type="text/css">
	@import url("css/style.css");
</style>
<script>
	function check() {
		var d = document.getElementById("post_pulisher").value;
		var c = document.getElementById("post_context").value;
		if (d == "" || c == "") {
			alert("发表人、标题以及内容不能为空");
			return false;
		}
	}
	function check_delete() {
		return confirm('你确定要删除此留言吗？');
	}
</script>
</head>
<body class="main_body">

	<%
		PageData<Message> data = new PageData<Message>();
		if (request.getParameter("id") == null) {
			response.setHeader("refresh", "0;URL=index.jsp?page=1");
			return;
		}
		try{
			data = (new UserServiceImpl()).getPageData(Integer.parseInt(request.getParameter("id")), Dbcon.ALL_TITLE, Dbcon.ALL_PULISHER);
		} catch (Exception e){
			e.printStackTrace();
			response.setHeader("refresh", "0;URL=index.jsp?page=1");
			return;
		}
		if (data.getPageNum() == 0) {
			response.setHeader("refresh", "0;URL=index.jsp?page=1");
			return;
		}
		Message ms = data.iterator().next().iterator().next();
	%>

	<div class="head_div">
		<div style="float:left;magin:5px;padding:5px">
			<a href="index.jsp">首页</a>
		</div>
		<div style="float:right;magin:5px;padding:5px">
			<%
				if (session.getAttribute("admin") == null) {
					out.append("<a href=\"login.jsp\">登陆</a>");
					out.append("&nbsp;&nbsp;");
					out.append("<a href=\"signup.jsp\">注册</a>");
				} else {
					out.append("欢迎你！管理员："+session.getAttribute("admin"));
					out.append("&nbsp;&nbsp;");
					
					out.append("<a href=\"admin.jsp\">管理</a>");
					out.append("&nbsp;&nbsp;");
					out.append("<a href=\"Admin/LogoutDeal\">登出</a>");
				}
			
			%>
		</div>
	</div>
	<table class="message_table">
		<tr>
			<th colspan=2 class="main_title">
				<h3>
					<%=ms.getTitle() %>
				</h3>
				<%
						if (session.getAttribute("admin") != null) {
							out.append("<div style=\"float:right\">"+
							"<form action=\"Admin/DeleteMessageDeal\" method=\"post\" onSubmit=\"return check_delete()\">"+
							"<input type=\"hidden\" name=\"messageid\" value=\""+ ms.getDbid() +"\">"+
							"<input type=\"submit\" value=\"删除留言\">"+
							"</form>");
						}
				%>
			</th>
		</tr>
		<tr>
			<th>
				发布时间：<%=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ).format(ms.getComtime()) %>
			</th>
			<th>
				发布者:<%=ms.getPulisher() %>
			</th>
		</tr>
		<tr>
			<td colspan=2 style="text-align:left">
				<%=ms.getContext() %>
			</td>
		</tr>
		
		
	</table>
	
	
	<table class="message_table" style="padding:20px">
	<tr>
			<th colspan=2>
				<h3>回复</h3>
			</th>
		</tr>
		<%
			for (Reply re : ms.getReplies()) {
				
				
				out.append("<tr>");
				out.append("<td style=\"width:20%\">");
				out.append("回复人："+re.getPulisher());
				out.append("</td>");
				out.append("<td rowspan=\"2\" style=\"text-align:left\">");
				out.append(re.getContext());
				out.append("</td>");
				out.append("</tr>");
				
				
				out.append("<tr>");
				out.append("<td style=\"width:20%\">");
				out.append("回复时间："+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ).format(re.getComtime()));
				if (session.getAttribute("admin") != null) {
					out.append("<div style=\"float:right\">"+
					"<form action=\"Admin/DeleteReplyDeal\" method=\"post\" onSubmit=\"return check_delete()\">"+
					"<input type=\"hidden\" name=\"messageid\" value=\""+ ms.getDbid() +"\">"+
					"<input type=\"hidden\" name=\"replyid\" value=\""+ re.getDbid() +"\">"+
					"<input type=\"submit\" value=\"删除回复\">"+
					"</form>");
				}
				out.append("</td>");
				out.append("</tr>");
			}
		%>
	</table>
	
	<form action="Submit/ReplyDeal" method="post" onSubmit="return check()">
	<input type="hidden" name="messageid" value="<%=ms.getDbid()%>">
    <table class="main_table" style="padding:50px">
    	<tr>
    		<td>
    			发表人：<input type="text" name="pulisher" id="post_pulisher">
    		</td>
    	</tr>
    	<tr>
    		<td colspan="2">
    			<textarea rows="10" cols="50" name="context" id="post_context"></textarea>
    		</td>
    	</tr>
    	<tr>
    		<td colspan=2>
    		<input type="reset" value="清空">
    		&nbsp;&nbsp;
    		<input type="submit" value="提交">
    		</td>
    	</tr>
    
    </table>
    </form>
</body>
</html>