<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" %>
<%@ page import="java.util.List, java.util.ArrayList, in.indew.harshit.bean.Note" %>
<%@ page session="true" %>

<%
    // Get notes from session
    List<Note> notes = (List<Note>) session.getAttribute("notes");

    // If no notes are found, set an empty list to avoid errors
    if (notes == null) {
        notes = new ArrayList<>();
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Notes</title>
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            text-align: center;
        }
        .container {
            width: 60%;
            margin: auto;
            padding: 20px;
            background: white;
            border-radius: 10px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #333;
        }
        .btn {
            padding: 10px 15px;
            margin: 5px;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            display: inline-flex;
            align-items: center;
            gap: 5px;
        }
        .btn-success {
            background-color: #28a745;
            color: white;
        }
        .btn-danger {
            background-color: #dc3545;
            color: white;
        }
        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
            text-align: left;
        }
        th, td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        .action-links a {
            margin-right: 10px;
            text-decoration: none;
            font-weight: bold;
        }
        .edit {
            color: #ffc107;
        }
        .delete {
            color: #dc3545;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Your Notes</h2>

        <button class="btn btn-success" onclick="location.href='addNote.jsp'">
            <i class="fa fa-plus"></i> Add New Note
        </button>

        <button class="btn btn-danger" onclick="location.href='index.jsp'">
            <i class="fa fa-sign-out-alt"></i> Logout
        </button>

        <table>
            <tr>
                <th>Title</th>
                <th>Content</th>
                <th>Actions</th>
            </tr>
            <% if (notes.isEmpty()) { %>
                <tr>
                    <td colspan="3" style="text-align:center; color: gray;">No notes available</td>
                </tr>
            <% } else { %>
                <% for (Note note : notes) { %>
                    <tr>
                        <td><%= note.getTitle() %></td>
                        <td><%= note.getContent() %></td>
                        <td class="action-links">
                            <a class="edit" href="EditNoteServlet?id=<%= note.getId() %>">
                                <i class="fa fa-edit"></i> Edit
                            </a>
                            <a class="delete" href="DeleteNoteServlet?id=<%= note.getId() %>"
                               onclick="return confirm('Are you sure you want to delete this note?');">
                                <i class="fa fa-trash"></i> Delete
                            </a>
                        </td>
                    </tr>
                <% } %>
            <% } %>
        </table>
    </div>
</body>
</html>
