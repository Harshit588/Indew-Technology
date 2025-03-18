<%@ page import="java.util.List" %>
<%@ page import="in.indew.harshit.bean.User" %>
<%@ page import="in.indew.harshit.dao.NoteDao" %>
<%@ page import="in.indew.harshit.bean.Note" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%
    HttpSession sessionObj = request.getSession(false);
    String userEmail = (String) sessionObj.getAttribute("userEmail");
    if (userEmail == null) {
        response.sendRedirect("index.html");
        return;
    }
    NoteDao noteDao = new NoteDao();
    List<Note> notes = noteDao.getNotesByEmail(userEmail);
%>

<html>
<head>
    <title>Notes</title>
</head>
<body>
    <h2>Welcome, <%= userEmail %></h2>
    <h3>Your Notes</h3>
    <form action="AddNoteServlet" method="post">
        <textarea name="content" placeholder="Write your note here..."></textarea>
        <button type="submit">Add Note</button>
    </form>
    
    <ul>
        <% for (Note note : notes) { %>
            <li>
                <form action="EditNoteServlet" method="post">
                    <input type="hidden" name="id" value="<%= note.getId() %>">
                    <textarea name="content"><%= note.getContent() %></textarea>
                    <button type="submit">Update</button>
                </form>
                <form action="DeleteNoteServlet" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="<%= note.getId() %>">
                    <button type="submit">Delete</button>
                </form>
            </li>
        <% } %>
    </ul>
</body>
</html>
