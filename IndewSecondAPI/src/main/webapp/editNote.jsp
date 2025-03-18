<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" %>
<%@ page import="in.indew.harshit.bean.Note" %>

<%
    Note note = (Note) request.getAttribute("note");
    if (note == null) {
        response.sendRedirect("dashboard.jsp?error=NoteNotFound");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Note</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            text-align: center;
        }
        .container {
            width: 50%;
            margin: auto;
            padding: 20px;
            background: white;
            border-radius: 10px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
        }
        input, textarea {
            width: 100%;
            padding: 8px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .btn {
            padding: 10px 15px;
            margin: 5px;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .btn-primary {
            background-color: #007bff;
            color: white;
        }
        .btn-secondary {
            background-color: #6c757d;
            color: white;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Edit Note</h2>
        <form action="UpdateNoteServlet" method="post">
            <input type="hidden" name="id" value="<%= note.getId() %>">
            <input type="text" name="title" value="<%= note.getTitle() %>" required>
            <textarea name="content" rows="5" required><%= note.getContent() %></textarea>
            <br>
            <button type="submit" class="btn btn-primary">Update Note</button>
            <a href="dashboard.jsp" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
</body>
</html>
