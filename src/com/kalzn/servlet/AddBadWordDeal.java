package com.kalzn.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kalzn.service.impl.AdminServiceImpl;

/**
 * Servlet implementation class AddBadWordDeal
 */
/**
 * 响应了对管理员添加屏蔽词的请求
 * @author Kalzn 18软件02 马明皓
 *
 */
@WebServlet("/AddBadWordDeal")
public class AddBadWordDeal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBadWordDeal() {
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
			(new AdminServiceImpl()).addBadWord(request.getParameter("word"));
		} catch (Exception e) {
			response.getWriter().append("<script>alert('添加失败！')</script>");
			return;
		} finally {
			response.setHeader("refresh", "0;URL=../admin.jsp?page=1");
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
