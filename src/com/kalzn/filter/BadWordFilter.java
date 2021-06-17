package com.kalzn.filter;

import java.io.File;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.kalzn.bean.BadWord;
import com.kalzn.dbcon.impl.MysqlServerCon;

import java.util.*;

/**
 * 
 * Servlet Filter implementation class BadWordFilter
 */
/**
 * 敏感词过滤器。
 * 过滤器从数据库的敏感词表中获取数据，然后匹配检索用户试图发布的内容，如果
 * 发现屏蔽词，则将其替换为*
 * 
 * @author Kalzn 18软件02 马明皓
 *
 */
@WebFilter("/BadWordFilter")
public class BadWordFilter implements Filter {
    /**
     * Default constructor. 
     */
	String path;
    public BadWordFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		String context = request.getParameter("context");
		if (context != null) {
			MysqlServerCon con = new MysqlServerCon();
			Vector<BadWord> set = null;
			try {
				set = con.getBadWord();
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (BadWord x : set) {
				context = context.replace(x.getWord(), "*");
			}
			request.setAttribute("context", context);
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
