package in.indew.harshit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.indew.harshit.dao.NoteDao;

@WebServlet("/UpdateNoteServlet")
public class UpdateNoteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        int noteId = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        NoteDao noteDao = new NoteDao();
        boolean updated = noteDao.updateNote(noteId, title, content);

        if (updated) {
            response.sendRedirect("dashboard.jsp?success=NoteUpdated");
        } else {
            response.sendRedirect("dashboard.jsp?error=UpdateFailed");
        }
    }
}
