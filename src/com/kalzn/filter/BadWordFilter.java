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
 * 鏁忔劅璇嶈繃婊ゅ櫒銆�
 * 杩囨护鍣ㄤ粠鏁版嵁搴撶殑鏁忔劅璇嶈〃涓幏鍙栨暟鎹紝鐒跺悗鍖归厤妫�绱㈢敤鎴疯瘯鍥惧彂甯冪殑鍐呭锛屽鏋�
 * 鍙戠幇灞忚斀璇嶏紝鍒欏皢鍏舵浛鎹负*
 * 
 * @author Kalzn 18杞欢02 椹槑鐨�
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
