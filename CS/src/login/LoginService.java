package login;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import core.CouponSystem;
import exception.CouponSystemException;
import exception.PasswordDoesntMatchException;
import facade.ClientFacade;
import facade.ClientType;

@Path("/LoginService")
public class LoginService {
	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	// http://localhost:8080/CS/rest/LoginService/login?username=david&password=123&ClientType=CUSTOMER
	// http://localhost:8080/CS/rest/LoginService/login?username=admin&password=1234&ClientType=ADMIN
	@GET
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response Login2() {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String clientType = request.getParameter("ClientType");
		System.out.println("username: " + username + " || password: " + password + " || ClientType: " + clientType);

		Response resp = new Response();
		if (username.equals(null) || password.equals(null) || clientType.equals(null)) {
			// if form is incorrect , sending response with name = null
			return resp;
		}

		// Setting facade on session and checking login
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(300); // 5min
		ClientFacade facade;
		try {
			facade = CouponSystem.getInstance().login(username, password, ClientType.valueOf(clientType));
			session.setAttribute("facade", facade);
			System.out.println(facade);
		} catch (CouponSystemException | PasswordDoesntMatchException e1) {
			facade = null;
		}
		if (facade != null) {
			// if we successfully logged in we got facade, if login is incorrect its null
			resp.setName(username);
			resp.setLoggedAs(clientType);
		}

		System.out.println("Response to client : " + resp);
		System.out.println("session: " + session.getId());
		return resp;
	}

	@GET
	@Path("logOut")
	public Void logOut() throws IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		// response.sendRedirect("../../index.html");
		System.out.println("USER LOGGED OUT");
		return null;
	}

}
