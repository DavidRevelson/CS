package core;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import facade.ClientFacade;

public class LoginFilter implements Filter {

	public LoginFilter() {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		ServletContext context = request.getServletContext();
		HttpSession session = req.getSession(false);
		if (session == null) {
			resp.sendRedirect(context.getContextPath() + "/index.html");
			return;
		}
		ClientFacade cf = (ClientFacade) session.getAttribute("facade");
		if (cf == null) {
			return;
		}
		chain.doFilter(request, response);
		System.out.println("request passed trough service");
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
