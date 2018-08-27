package core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Company;
import beans.Customer;
import exception.CouponSystemException;
import facade.AdminFacade;

@Path("Admin")
public class AdminService {
	private AdminFacade admin;
	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	@POST
	@Path("createCompany")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Void createCompany(Company comp) throws IOException {
		HttpSession session = request.getSession(false);
		admin = (AdminFacade) session.getAttribute("facade");
		try {
			admin.createCompany(comp);
			System.out.println("Company [" + comp.getCompName() + "] sent to database...");
		} catch (CouponSystemException e) {
			response.sendError(403, e.getMessage());
			System.out.println(e.getMessage());
		}
		return null;
	}

	@POST
	@Path("createCustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Void createCustomer(Customer cust) throws IOException {
		HttpSession session = request.getSession(false);
		admin = (AdminFacade) session.getAttribute("facade");
		try {
			admin.createCustomer(cust);
			System.out.println(cust.toString());
		} catch (CouponSystemException e) {
			response.sendError(403, e.getMessage());
			System.out.println(e.getMessage());
		}
		return null;
	}

	@PUT
	@Path("updateCompany")
	@Consumes(MediaType.APPLICATION_JSON)
	public Void updateCompany(Company comp) throws IOException {
		HttpSession session = request.getSession(false);
		admin = (AdminFacade) session.getAttribute("facade");
		try {
			admin.updateCompany(comp);
			System.out.println(comp.toString() + " Has been updated");
		} catch (CouponSystemException e) {
			response.sendError(403);
			System.out.println(e.getMessage());
		}
		return null;
	}

	@PUT
	@Path("updateCustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	public Void updateCustomer(Customer cust) {
		System.out.println(cust);
		HttpSession session = request.getSession(false);
		admin = (AdminFacade) session.getAttribute("facade");
		try {
			admin.updateCustomer(cust);
			System.out.println(cust.toString() + " Has been updated");
		} catch (CouponSystemException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@POST
	@Path("readCompany")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Company readCompanyJSON(Company comp) {
		// reads by: JSON id field, returns JSON object
		HttpSession session = request.getSession(false);
		admin = (AdminFacade) session.getAttribute("facade");
		try {
			comp = admin.readCompany(comp);
		} catch (CouponSystemException e) {
			System.out.println("failed to read + " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		System.out.println(comp);
		return comp;
	}

	@POST
	@Path("readCustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Customer readCustomerJSON(Customer cust) {
		// reads by: JSON id field, returns JSON object
		HttpSession session = request.getSession(false);
		admin = (AdminFacade) session.getAttribute("facade");
		try {
			cust = admin.readCustomer(cust.getId());
		} catch (CouponSystemException e) {
			System.out.println("failed to read + " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		System.out.println(cust);
		return cust;
	}

	// http://localhost:8080/CS/rest/Admin/readAllCompanies
	@GET
	@Path("readAllCompanies")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Company> readAllCompanies() {
		List<Company> list = new ArrayList<>();
		HttpSession session = request.getSession(false);
		admin = (AdminFacade) session.getAttribute("facade");
		if (admin == null) {
			System.out.println("facade: " + admin);
			return null;
		}
		try {
			list = admin.readAllCompany();
		} catch (CouponSystemException e) {
			System.out.println("failed to read + " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		System.out.println(list);
		return list;
	}

	@GET
	@Path("readAllCustomers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> readAllCustomers() {
		List<Customer> list = new ArrayList<>();
		HttpSession session = request.getSession(false);
		admin = (AdminFacade) session.getAttribute("facade");
		if (admin == null) {
			System.out.println("facade: " + admin);
			return null;
		}
		try {
			list = admin.readAllCustomers();
		} catch (CouponSystemException e) {
			System.out.println("failed to read + " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		System.out.println(list);
		return list;
	}

	@POST
	@Path("deleteCompany")
	@Consumes(MediaType.APPLICATION_JSON)
	public Void deleteCompany(Company comp) {
		System.out.println(comp);
		HttpSession session = request.getSession(false);
		admin = (AdminFacade) session.getAttribute("facade");
		try {
			admin.deleteCompany(comp);
			System.out.println(comp.getId() + ":ID Has been deleted");
		} catch (CouponSystemException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@POST
	@Path("deleteCustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	public Void deleteCustomer(Customer cust) {
		System.out.println(cust);
		HttpSession session = request.getSession(false);
		admin = (AdminFacade) session.getAttribute("facade");
		try {
			admin.deleteCustomer(cust);
			System.out.println(cust.getId() + ":ID Has been deleted");
		} catch (CouponSystemException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
