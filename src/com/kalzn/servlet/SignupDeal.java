package com.kalzn.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kalzn.service.impl.AdminServiceImpl;

/**
 * Servlet implementation class SignupDeal
 */
/**
 * 响应了对管理员注册的请求
 * @author Kalzn 18软件02 马明皓
 *
 */
@WebServlet("/SignupDeal")
public class SignupDeal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupDeal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("gb18030");
		request.setCharacterEncoding("utf-8");
		try {
			(new AdminServiceImpl()).signUp(request.getParameter("adminname"), request.getParameter("password"));
		} catch (Exception e) {
			response.getWriter().append("<script>alert('注册失败！可能是用户名已被注册')</script>");
			response.setHeader("refresh", "0;URL=../signup.jsp");
			return;
		}
		response.getWriter().append("<script>alert('注册成功！请登陆')</script>");
		response.setHeader("refresh", "0;URL=../login.jsp");
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
