package in.indew.harshit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.indew.harshit.bean.Note;
import in.indew.harshit.dao.NoteDao;

@WebServlet("/EditNoteServlet")
public class EditNoteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        int noteId = Integer.parseInt(request.getParameter("id"));
        NoteDao noteDao = new NoteDao();
        Note note = noteDao.getNoteById(noteId);

        if (note != null) {
            request.setAttribute("note", note);
            request.getRequestDispatcher("editNote.jsp").forward(request, response);
        } else {
            response.sendRedirect("dashboard.jsp?error=NoteNotFound");
        }
    }
}

