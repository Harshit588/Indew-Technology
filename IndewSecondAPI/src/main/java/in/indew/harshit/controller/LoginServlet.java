package in.indew.harshit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.indew.harshit.bean.Note;
import in.indew.harshit.bean.User;
import in.indew.harshit.dao.NoteDao;
import in.indew.harshit.dao.UserDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    
	    String email = request.getParameter("email").trim().toLowerCase();
	    String password = request.getParameter("password");

	    UserDao userDao = new UserDao();
	    boolean isValidUser = userDao.validateUser(email, password);

	    if (isValidUser) {
	        User user = userDao.getUserByEmail(email);
	        HttpSession session = request.getSession();
	        session.setAttribute("user", user);

	        // 🔹 Fetch user notes from DB and store them in the session
	        NoteDao noteDao = new NoteDao();
	        List<Note> notes = noteDao.getNotesByEmail(email);
	        session.setAttribute("notes", notes); // Store notes in session

	        response.sendRedirect("dashboard.jsp"); // Redirect to notes page
	    } else {
	        request.setAttribute("errorMessage", "Invalid email or password!");
	        request.getRequestDispatcher("index.jsp").forward(request, response);
	    }
	}

}
