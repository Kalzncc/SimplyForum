package com.kalzn.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kalzn.service.impl.AdminServiceImpl;
import com.kalzn.service.impl.UserServiceImpl;

/**
 * Servlet implementation class CheckDeal
 */
@WebServlet("/CheckDeal")
public class CheckDeal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckDeal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		try {
			if ( !(new AdminServiceImpl()).checkUsername(request.getParameter("username"))) {
				response.getWriter().append("<span style=\"color:red\">用户名已被注册</span>");
			} else {
				response.getWriter().append("<span style=\"color:green\">用户名可以使用</span>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
