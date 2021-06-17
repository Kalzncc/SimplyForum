package com.kalzn.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kalzn.bean.Message;
import com.kalzn.dbcon.impl.MysqlServerCon;

/**
 * Servlet implementation class MessageDeal
 */
/**
 * 响应了对用户发布留言的请求
 * @author Kalzn 18软件02 马明皓
 *
 */
@WebServlet("/MessageDeal")
public class MessageDeal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageDeal() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("gb18030");
		request.setCharacterEncoding("utf-8");
		Message ms = new Message();
		ms.setComtime(new Date());
		ms.setContext((String)request.getAttribute("context"));
		ms.setTitle(request.getParameter("title"));
		ms.setPulisher(request.getParameter("pulisher"));
		try {
			(new MysqlServerCon()).addMessage(ms);
		} catch(Exception e) {
			response.getWriter().append("<script>alert('数据库异常！发布失败！')</script>");
			response.setHeader("refresh", "0;URL=../index.jsp?page=1");
			return;
		}
		response.getWriter().append("<script>alert('发布成功')</script>");
		response.setHeader("refresh", "0;URL=../index.jsp?page=1");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
