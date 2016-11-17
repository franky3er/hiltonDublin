package com.hiltondublin.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hiltondublin.db.HiltonDublinDBConnection;
import com.hiltondublin.helper.Helper;
import com.hiltondublin.users.User;

/**
 * Servlet implementation class RegisterEmployeeServlet
 */
@WebServlet("/RegisterEmployeeServlet")
public class RegisterEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		String firstName = Helper.setNullIfEmptyString(request.getParameter("firstName"));
		String lastName = Helper.setNullIfEmptyString(request.getParameter("lastName"));
		String phone = Helper.setNullIfEmptyString(request.getParameter("phone"));
		String email = Helper.setNullIfEmptyString(request.getParameter("email"));
		String username = Helper.setNullIfEmptyString(request.getParameter("username"));
		String password = Helper.setNullIfEmptyString(request.getParameter("password"));
		String passwordRepeat = Helper.setNullIfEmptyString(request.getParameter("passwordRepeat"));
		String isAdmin = request.getParameter("isAdmin");
		
		request.setAttribute("firstName", firstName);
		request.setAttribute("lastName", lastName);
		request.setAttribute("phone", phone);
		request.setAttribute("email", email);
		request.setAttribute("username", username);
		if(isAdmin != null){
			if(isAdmin.equals("true")){
				request.setAttribute("isAdmin", "checked");
			}
		}
		
		
		
		boolean registerEmployee = true;
		
		if(firstName == null){
			request.setAttribute("registerEmployeeErrorFirstName", "1");
			registerEmployee = false;
		}
		
		if(lastName == null){
			request.setAttribute("registerEmployeeErrorLastName", "1");
			registerEmployee = false;
		}
		
		if(email != null){
			if(!Helper.validateEmail(email)){
				request.setAttribute("registerEmployeeErrorEmail", "2");
				registerEmployee = false;
			}
		} else {
			request.setAttribute("registerEmployeeErrorEmail", "1");
			registerEmployee = false;
		}
		
		if(username != null){
			if(!dbConnection.getEmployees(username, null, null, null, null, null, null, null).isEmpty()){
				request.setAttribute("registerEmployeeErrorUsername", "2");
				registerEmployee = false;
			}
		} else {
			request.setAttribute("registerEmployeeErrorUsername", "1");
			registerEmployee = false;
		}
		
		if(password != null){
			if(password.length() < 8){
				request.setAttribute("registerEmployeeErrorPassword", "2");
				registerEmployee = false;
			}
		} else {
			request.setAttribute("registerEmployeeErrorPassword", "1");
			registerEmployee = false;
		}
		
		if(passwordRepeat != null){
			if(passwordRepeat.length() < 8){
				request.setAttribute("registerEmployeeErrorPassword", "2");
				registerEmployee = false;
			}
		} else {
			request.setAttribute("registerEmployeeErrorPassword", "1");
			registerEmployee = false;
		}
		
		if(passwordRepeat != null && password != null){
			if(!password.equals(passwordRepeat)){
				request.setAttribute("registerEmployeeErrorPassword", "3");
				registerEmployee = false;
			}
		}
		
		if(isAdmin == null){
			isAdmin = "false";
		}
		
		if(registerEmployee){
			
			ResultSet rs = dbConnection.insertEmployee(username, firstName, lastName, password, isAdmin, email, phone);
			request.setAttribute("registerEmployeeSuccessful", "1");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
