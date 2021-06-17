package com.kalzn.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kalzn.service.impl.UserServiceImpl;

/**
 * Servlet implementation class ReplyDeal
 */
/**
 * 响应了对用户发布回复的请求
 * @author Kalzn 18软件02 马明皓
 *
 */
@WebServlet("/ReplyDeal")
public class ReplyDeal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyDeal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("gb18030");
		try {
			(new UserServiceImpl()).replyMessage(Integer.parseInt(request.getParameter("messageid")), request.getParameter("pulisher"), (String)request.getAttribute("context"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.getWriter().append("<script>alert('回复失败！')</script>");
			response.setHeader("refresh", "0;URL=../index.jsp?page=1");
			
			return;
		}
		response.getWriter().append("<script>alert('回复成功！')</script>");
		response.setHeader("refresh", "0;URL=../message.jsp?id="+request.getParameter("messageid"));
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
