package com.kalzn.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kalzn.service.impl.AdminServiceImpl;

/**
 * Servlet implementation class DeleteReplyDeal
 */
/**
 * 响应了对管理员删除回复的请求
 * @author Kalzn 18软件02 马明皓
 *
 */
@WebServlet("/DeleteReplyDeal")
public class DeleteReplyDeal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteReplyDeal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("gb18030");
		request.setCharacterEncoding("utf-8");
		if (request.getSession().getAttribute("admin") == null) {
			response.setHeader("refresh", "0;URL=../index.jsp?page=1");
			return;
		}
		try {
			(new AdminServiceImpl()).deleteReply(Integer.parseInt(request.getParameter("replyid")));
		} catch (Exception e) {
			response.getWriter().append("<script>alert('删除失败！')</script>");
			return;
		} finally {
			response.setHeader("refresh", "0;URL=../message.jsp?id="+request.getParameter("messageid"));
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
