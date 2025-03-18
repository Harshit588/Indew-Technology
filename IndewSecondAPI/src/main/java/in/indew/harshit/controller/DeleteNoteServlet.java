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

@WebServlet("/DeleteNoteServlet")
public class DeleteNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// ✅ Check if user is logged in
		if (user == null) {
			response.sendRedirect("index.jsp");
			return;
		}

		// ✅ Get note ID from request
		String noteIdStr = request.getParameter("id");
		if (noteIdStr == null || noteIdStr.trim().isEmpty()) {
			session.setAttribute("errorMessage", "Invalid note ID.");
			response.sendRedirect("dashboard.jsp");
			return;
		}

		int noteId = Integer.parseInt(noteIdStr);
		String userEmail = user.getEmail();

		// ✅ Delete the note
		NoteDao noteDao = new NoteDao();
		boolean isDeleted = noteDao.deleteNoteById(noteId, userEmail);

		if (isDeleted) {
			// ✅ Fetch updated notes list and update session
			List<Note> updatedNotes = noteDao.getNotesByEmail(userEmail);
			session.setAttribute("notes", updatedNotes);

			// ✅ Redirect back to dashboard
			response.sendRedirect("dashboard.jsp");
		} else {
			session.setAttribute("errorMessage", "Failed to delete note. Try again.");
			response.sendRedirect("dashboard.jsp");
		}
	}
}
