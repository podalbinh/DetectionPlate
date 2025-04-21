<%-- 
    Document   : notice
    Created on : Mar 28, 2025, 1:09:26 PM
    Author     : Admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Thông báo</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 20px;
            }
            .container {
                background: #fff;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                max-width: 600px;
                margin: auto;
            }
            h2 {
                color: #333;
            }
            p {
                color: #555;
            }
            .button {
                padding: 10px 15px;
                background: #007bff;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                transition: background 0.3s;
            }
            .button:hover {
                background: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Thông báo</h2>
            <p><%= request.getAttribute("message") %></p>
            <button class="button" onclick="window.location.href='upload.jsp'">Quay lại</button>
        </div>
    </body>
</html>
