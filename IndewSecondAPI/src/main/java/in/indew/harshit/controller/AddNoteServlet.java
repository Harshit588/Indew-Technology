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

@WebServlet("/AddNoteServlet")
public class AddNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// ✅ Check if user is logged in
		if (user == null) {
			response.sendRedirect("index.jsp");
			return;
		}

		// ✅ Retrieve form data
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String userEmail = user.getEmail();

		// ✅ Validate input fields
		if (title == null || title.trim().isEmpty() || content == null || content.trim().isEmpty()) {
			session.setAttribute("errorMessage", "Title and content cannot be empty!");
			response.sendRedirect("addNote.jsp");
			return;
		}

		// ✅ Create Note object and save to database
		Note note = new Note(title, content, userEmail);
		NoteDao noteDao = new NoteDao();
		boolean isSaved = noteDao.saveNote(note);

		if (isSaved) {
			// ✅ Fetch updated notes list and store in session
			List<Note> updatedNotes = noteDao.getNotesByEmail(userEmail);
			session.setAttribute("notes", updatedNotes);

			// ✅ Redirect to dashboard
			response.sendRedirect("dashboard.jsp");
		} else {
			session.setAttribute("errorMessage", "Failed to save note. Try again.");
			response.sendRedirect("addNote.jsp");
		}
	}
}
