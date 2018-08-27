package core;

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

import beans.Coupon;
import exception.CouponSystemException;
import facade.CompanyFacade;

@Path("/Company")
public class CompanyService {
	private CompanyFacade company;
	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	// http://localhost:8080/CS/rest/Company/createCoupon
	@POST
	@Path("createCoupon")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Void createCoupon(Coupon coupon) {
		HttpSession session = request.getSession(false);
		company = (CompanyFacade) session.getAttribute("facade");
		try {
			company.createCoupon(coupon);
			System.out.println(coupon.toString());
		} catch (CouponSystemException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@POST
	@Path("deleteCoupon")
	@Consumes(MediaType.APPLICATION_JSON)
	public Void deleteCoupon(Coupon coupon) {
		HttpSession session = request.getSession(false);
		company = (CompanyFacade) session.getAttribute("facade");
		try {
			company.deleteCoupon(coupon);
			System.out.println(coupon.toString() + " has been deleted Successfully");
		} catch (CouponSystemException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@PUT
	@Path("updateCoupon")
	@Consumes(MediaType.APPLICATION_JSON)
	public Void updateCoupon(Coupon coupon) {
		HttpSession session = request.getSession(false);
		company = (CompanyFacade) session.getAttribute("facade");
		try {
			company.updateCoupon(coupon);
			System.out.println(coupon.toString());
		} catch (CouponSystemException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@POST
	@Path("readCoupon")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Coupon readCoupon(Coupon coupon) {
		HttpSession session = request.getSession(false);
		company = (CompanyFacade) session.getAttribute("facade");
		try {
			coupon = company.readCoupon(coupon.getId());
		} catch (CouponSystemException e) {
			System.out.println("failed to read + " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		System.out.println(coupon);
		return coupon;
	}

	@POST
	@Path("readCouponByType")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Coupon> readCouponByType(Coupon coupon) {
		List<Coupon> list = new ArrayList<>();
		HttpSession session = request.getSession(false);
		company = (CompanyFacade) session.getAttribute("facade");
		try {
			list = company.readAllCouponsByType(coupon.getType());
		} catch (CouponSystemException e) {
			System.out.println("failed to read + " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println(list);
		return list;
	}

	@POST
	@Path("readCouponByDate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Coupon> readCouponByDate(int year, int month, int day) {
		List<Coupon> list = new ArrayList<>();
		HttpSession session = request.getSession(false);
		company = (CompanyFacade) session.getAttribute("facade");
		try {
			list = company.readAllCouponsByDate(year, month, day);
		} catch (CouponSystemException e) {
			System.out.println("failed to read + " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println(list);
		return list;
	}

	@POST
	@Path("readCouponByPrice")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Coupon> readCouponByPrice(Coupon coupon) {
		List<Coupon> list = new ArrayList<>();
		HttpSession session = request.getSession(false);
		company = (CompanyFacade) session.getAttribute("facade");
		try {
			list = company.readAllCouponsByPrice(coupon.getPrice());
		} catch (CouponSystemException e) {
			System.out.println("failed to read + " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println(list);
		return list;
	}

	@GET
	@Path("readAllCoupon")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Coupon> readAllCoupon() {
		List<Coupon> list = new ArrayList<>();
		HttpSession session = request.getSession(false);
		company = (CompanyFacade) session.getAttribute("facade");
		try {
			list = company.readAllCoupons();
		} catch (CouponSystemException e) {
			System.out.println("failed to read + " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println(list);
		return list;
	}

}
