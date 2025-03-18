package in.indew.harshit.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.indew.harshit.bean.User;
import in.indew.harshit.dao.UserDao;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		System.out.println("Email :: " + email);
		System.out.println("Name :: " + name);
		System.out.println("Password :: " + password);

		// ✅ Use only one User object
		User user = new User(name, email, password);

		UserDao userDao = new UserDao();

		// ✅ Save user in the database
		if (userDao.registerUser(user)) {
			response.sendRedirect("index.jsp?message=Registration Successful! Please login.");
		} else {
			request.setAttribute("errorMessage", "Registration failed. Try again!");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
}
