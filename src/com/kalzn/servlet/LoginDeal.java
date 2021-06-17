package com.kalzn.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kalzn.dbcon.impl.MysqlServerCon;
import com.kalzn.service.impl.AdminServiceImpl;
import com.kalzn.service.impl.UserServiceImpl;
import com.mysql.cj.Session;

/**
 * Servlet implementation class LoginDeal
 */
/**
 * 响应了对管理员登陆的请求
 * @author Kalzn 18软件02 马明皓
 *
 */
@WebServlet("/LoginDeal")
public class LoginDeal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginDeal() {
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
			if ( (new AdminServiceImpl()).loginIn(request.getParameter("adminname"), request.getParameter("password")) ) {
				request.getSession().setAttribute("admin", request.getParameter("adminname"));
				response.setHeader("refresh", "0;URL=../index.jsp?page=1");
			} else {
				response.getWriter().append("<script>alert('密码或者用户名错误！')</script>");
				response.setHeader("refresh", "0;URL=../login.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setHeader("refresh", "0;URL=../index.jsp?page=1");
			return;
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
