package com.hiltondublin.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hiltondublin.classes.Reservation;
import com.hiltondublin.db.HiltonDublinDBConnection;
import com.hiltondublin.helper.Helper;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = Helper.setNullIfEmptyString(request.getParameter("url"));
		
		String bookingNumber = Helper.setNullIfEmptyString(request.getParameter("bookingNumber"));

		String cardHolder = Helper.setNullIfEmptyString(request.getParameter("cardHolder"));
		String creditCard = Helper.setNullIfEmptyString(request.getParameter("credit-card"));
		String cardNumber = Helper.setNullIfEmptyString(request.getParameter("cardNumber"));
		String cvcNumber = Helper.setNullIfEmptyString(request.getParameter("cvcNumber"));
		String expirationYear = Helper.setNullIfEmptyString(request.getParameter("expirationYear"));
		String expirationMonth = Helper.setNullIfEmptyString(request.getParameter("expirationMonth"));
		
		request.setAttribute("bookingNumber", bookingNumber);
		
		request.setAttribute("cardHolder", cardHolder);
		request.setAttribute("creditCard", creditCard);
		request.setAttribute("cardNumber", cardNumber);
		request.setAttribute("cvvCode", cvcNumber);
		request.setAttribute("expirationYear", expirationYear);
		request.setAttribute("expirationMonth", expirationMonth);
		
		boolean payed = true;

		if(cardHolder == null){
			request.setAttribute("paymentErrorCardHolder", "1");
			payed = false;
		}
		
		if(creditCard != null){
			if(cardNumber != null){
				if(creditCard.equals("visa")){
					if(!Helper.checkIfVisa(cardNumber)){
						request.setAttribute("paymentErrorCardNumber", "2");
						payed = false;
					}
				} else if(creditCard.equals("mastercard")){
					if(!Helper.checkIfMaster(cardNumber)){
						request.setAttribute("paymentErrorCardNumber", "2");
						payed = false;
					}
				} else {
					request.setAttribute("paymentErrorCreditCard", "2");
					payed = false;
				}
			} else {
				request.setAttribute("paymentErrorCardNumber", "1");
				payed = false;
			}
		} else {
			request.setAttribute("paymentErrorCreditCard", "1");
			payed = false;
		}
		
		if(cvcNumber != null){
			if(!Helper.checkCvvCode(cvcNumber)){
				request.setAttribute("paymentErrorCvvCode", "2");
				payed = false;
			}
		} else {
			request.setAttribute("paymentErrorCvvCode", "1");
			payed = false;
		}
		
		if(expirationYear != null){
			if(expirationYear.equals("Year")){
				request.setAttribute("paymentErrorExpiration", "1");
				payed = false;
			}
		} else {
			payed = false;
		}
		
		if(expirationMonth != null){
			if(expirationMonth.equals("Month")){
				request.setAttribute("paymentErrorExpiration", "2");
				payed = false;
			}
		} else {
			payed = false;
		}
		
		if(payed){
			List<Reservation> reservations = dbConnection.getReservations(bookingNumber, null, null, null, null, null);
			
			if(reservations != null){
				if(!reservations.isEmpty()){
					request.setAttribute("payedReservation", reservations.get(0));
				} else {
					request.setAttribute("paymentSystemError", "1");
				}
			} else {
				request.setAttribute("paymentSystemError", "1");
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
