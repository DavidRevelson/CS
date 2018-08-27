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
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Coupon;
import exception.CouponSystemException;
import facade.CustomerFacade;

@Path("/Customer")
public class CustomerService {
	private CustomerFacade customer;
	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	@POST
	@Path("PurchesedCoupon")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Void PurchasedCoupon(Coupon coupon) throws IOException {
		HttpSession session = request.getSession(false);
		customer = (CustomerFacade) session.getAttribute("facade");
		try {
			customer.purchaseCoupon(coupon);
			System.out.println(coupon.toString() + " has been purchased");
		} catch (CouponSystemException e) {
			System.out.println("failed to purchase coupon " + e.getMessage());
			response.sendError(403);
		}
		return null;
	}

	// http://localhost:8080/CS/rest/Customer/readAllPurchesedCoupons
	@GET
	@Path("readAllPurchesedCoupons")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Coupon> readAllPurchasedCoupons() {
		List<Coupon> list = new ArrayList<>();
		HttpSession session = request.getSession(false);
		customer = (CustomerFacade) session.getAttribute("facade");
		try {
			list = customer.readAllPurchesedCoupons();
		} catch (CouponSystemException e) {
			System.out.println("failed to read + " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println(list);
		return list;
	}

	@POST
	@Path("readAllPurchesedCouponsByType")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Coupon> readAllPurchesedCouponsByType(Coupon coupon) {
		List<Coupon> list = new ArrayList<>();
		HttpSession session = request.getSession(false);
		customer = (CustomerFacade) session.getAttribute("facade");
		try {
			list = customer.readAllPurchesedCouponsByType(coupon.getType());
		} catch (CouponSystemException e) {
			System.out.println("failed to read + " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println(list);
		return list;
	}

	@POST
	@Path("readAllPurchesedCouponsByPrice")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Coupon> readAllPurchesedCouponsByPrice(Coupon coupon) {
		List<Coupon> list = new ArrayList<>();
		HttpSession session = request.getSession(false);
		customer = (CustomerFacade) session.getAttribute("facade");
		try {
			list = customer.readAllPurchesedCouponsByPrice(coupon.getPrice());
		} catch (CouponSystemException e) {
			System.out.println("failed to read + " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println(list);
		return list;
	}

	@GET
	@Path("readAllCoupons")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Coupon> readAllCoupons() {
		List<Coupon> list = new ArrayList<>();
		HttpSession session = request.getSession(false);
		customer = (CustomerFacade) session.getAttribute("facade");
		try {
			list = customer.readAllCoupons();
		} catch (CouponSystemException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(list);
		return list;
	}

}
