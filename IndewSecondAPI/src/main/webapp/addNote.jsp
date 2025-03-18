<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Note</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <h2>Add a New Note</h2>
        <form action="AddNoteServlet" method="post">
            <input type="text" name="title" placeholder="Note Title" required>
            <textarea name="content" placeholder="Write your note here..." required></textarea>
            <button type="submit">Save Note</button>
        </form>
        <a href="dashboard.jsp">Back to Dashboard</a>
    </div>
</body>
</html>
