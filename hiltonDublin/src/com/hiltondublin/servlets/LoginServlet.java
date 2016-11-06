package com.hiltondublin.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hiltondublin.db.HiltonDublinDBConnection;
import com.hiltondublin.users.Employee;
import com.hiltondublin.users.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String url = request.getParameter("url");
		
		//verify login
		List<User> users = dbConnection.getEmployees(username, null, null, null, null, null, null, null);
		if(username.isEmpty() || users == null || users.size()==0){
			String loginError = "1";
			request.setAttribute("loginError", loginError);
		}
		else{
			Employee employee = (Employee) users.get(0);
			if(employee.getPassword().equals(password)){
				request.getSession().setAttribute("user", users.get(0));
			}
			else{
				String loginError = "2";
				request.setAttribute("loginError", loginError);
			}
			
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
