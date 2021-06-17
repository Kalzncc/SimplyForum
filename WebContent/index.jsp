<%@page import="com.kalzn.bean.PageData"%>
<%@page import="com.kalzn.dbcon.Dbcon"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.kalzn.bean.Message"%>
<%@page import="com.kalzn.bean.PageSet"%>
<%@page import="com.kalzn.dbcon.impl.MysqlServerCon"%>
<%@page import="com.kalzn.service.impl.UserServiceImpl"%>
<%@page import="com.kalzn.service.UserService"%>
<%@page import="com.kalzn.util.PageDataDBCreater"%>
<%@page import="com.kalzn.util.MD5"%>
<%@page import="com.mysql.cj.Session"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
 <!-- 
  Kalzn 18软件02 马明皓  
  留言板主页，可以看到所有信息分页后的结果，以及自己的登陆状态。
   -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>留言版</title>
<style type="text/css">
	@import url("css/style.css");
</style>

<script>
	function check() {
		var s = document.getElementById("post_title").value;
		var d = document.getElementById("post_pulisher").value;
		var c = document.getElementById("post_context").value;
		if (s == "" || d == "" || c == "") {
			alert("发表人、标题以及内容不能为空");
			return false;
		}
	}
</script>
</head>
<body class="main_body">
	
	<%
			String tval = null, pval = null;
				if (request.getParameter("page") == null) {
			response.setHeader("refresh", "0;URL=index.jsp?page=1");
			return;
				}
				if (request.getParameter("pulisher") != null) {
			if(request.getParameter("pulisher").equals("")) {
				session.removeAttribute("pulisher");
			} else {
				session.setAttribute("pulisher", request.getParameter("pulisher"));
			}
			
				}
				if (request.getParameter("title") != null) {
			if(request.getParameter("title").equals("")) {
				session.removeAttribute("title");
			} else {
				session.setAttribute("title", request.getParameter("title"));
			}
				}
				tval = (String)session.getAttribute("title");
				pval = (String)session.getAttribute("pulisher");
		%>
	<%
		PageData<Message> data = new PageData<Message>(); 
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
	
	<form id="post_form" action="index.jsp">	
		<input type="hidden" name="page" value="1">
	    <table class="main_table">
	        <tr>
	            <th colspan="4" class="main_title">
	                <h2>
	                    留言版
	                </h2>
	            </th>
	        </tr>
	        <tr>
	            <th>
	                时间
	            </th>
	            <th>
	                标题
	                <input type="text" name="title" value="<%=tval==null?"":tval%>">
	            </th>
	            <th>
	                发表人
	                <input type="text" name="pulisher" value="<%=pval==null?"":pval%>" style="width:100px">
	            </th>
	            <th>
	            	<input type="submit"  value="搜索">
	            </th>

	            
	        </tr>
			<%
				data = (new UserServiceImpl()).getPageData(Dbcon.ALL_ID,  (String)session.getAttribute("pulisher"),  (String)session.getAttribute("title"));
					if (data.getPageNum() != 0) {
						PageSet<Message> set = null;
						try{
							set = data.getPage(Integer.parseInt(request.getParameter("page"))-1 );
						} catch (Exception e) {
							response.setHeader("refresh", "0;URL=index.jsp?page=1");
							return;
						}
						for (Message ss : set) {
							out.append("<tr>");
							
							
							out.append("<td class=\"time\">" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ).format(ss.getComtime())  + "</td>");
							out.append("<td class=\"title\"><a href = message.jsp?id="+  ss.getDbid()  +">" + ss.getTitle()+" &nbsp; ("+ss.getReplynum() +"个回复)</a></td>");
							out.append("<td class=\"pulisher\" colspan=2>");
							out.append(ss.getPulisher() );
							out.append("</td>");
							
							out.append("</tr>");
						}
					} else {
						out.append("<tr>");
						
						
						out.append("<td colspan=4>No result.");
						out.append("</td>");
						
						out.append("</tr>");
					}
			%>
			<tr><td colspan="4">
			<%
				out.append("<a href=index.jsp?page=1> 首页 </a>" );
			out.append("&nbsp;" );out.append("&nbsp;" );out.append("&nbsp;" );
				out.append("<a href=index.jsp?page=" + (data.getPrePageNum()+1) +"> 上一页 </a>" );
				out.append("&nbsp;" );out.append("&nbsp;" );out.append("&nbsp;" );
				out.append("第"+(data.getCurNum()+1)+"页/共"+data.getPageNum()+"页");
				out.append("&nbsp;" );out.append("&nbsp;" );out.append("&nbsp;" );
				out.append("<a href=index.jsp?page=" + (data.getNextPageNum()+1) +"> 下一页 </a>" );
				out.append("&nbsp;" );out.append("&nbsp;" );out.append("&nbsp;" );
				out.append("<a href=index.jsp?page=" + (data.getPageNum()) +"> 末页 </a>" );
			%>
			</td>
			</tr>
	    </table>
    </form>
    <form action="Submit/MessageDeal" method="post" onSubmit="return check()">
    <table class="main_table" style="padding:50px">
    	<tr>
    		<td>
    			发表人：<input type="text" name="pulisher" id="post_pulisher">
    		</td>
    		<td>
    			标题：<input type="text" name="title" id="post_title">
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