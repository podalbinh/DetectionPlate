<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.LicensePlate" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách biển số xe chưa đăng ký</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        h2 {
            color: #333;
        }
        .container {
            background: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            max-width: 800px;
            margin: auto;
        }
        .button {
            padding: 10px 15px;
            background: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background 0.3s;
            margin-right: 10px;
        }
        .button:hover {
            background: #0056b3;
        }
        .delete-button {
            background: #dc3545;
        }
        .delete-button:hover {
            background: #c82333;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            margin: 10px 0;
            padding: 10px;
            background: #f9f9f9;
            border-radius: 5px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        img {
            max-width: 100px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Danh sách biển số xe chưa đăng ký:</h2>

        <%
            // Lấy session
        
            // Lấy danh sách biển số từ session
            List<LicensePlate> unregisteredPlates = (List<LicensePlate>) session.getAttribute("unregisteredPlates");

            if (unregisteredPlates != null && !unregisteredPlates.isEmpty()) {
        %>
            <ul id="plateList">
                <%
                    for (LicensePlate plate : unregisteredPlates) {
                %>
                    <li id="plate_<%= plate.getId() %>">
                        <div>
                            Name: <%= plate.getName() %>
                            <br/>
                            <img src="<%= plate.getImageUrl() %>" alt="Image of <%= plate.getName() %>"/>
                        </div>
                        <div>
                            <button class="button delete-button" onclick="deletePlate('<%= plate.getId() %>')">Xóa</button>
                        </div>
                    </li>
                <%
                    }
                %>
            </ul>
            <button class="button" onclick="document.getElementById('savePlatesForm').submit();">Lưu vào CSDL</button>
            <form id="savePlatesForm" action="savePlates" method="post" style="display:none;">
                <input type="hidden" name="unregisteredPlates" value="<%= unregisteredPlates %>"/>
            </form>
        <%
            } else {
        %>
            <p>Không có biển số xe chưa đăng ký nào được tìm thấy.</p>
        <%
            }
        %>
        <button class="button" onclick="window.history.back()">Quay lại</button>
    </div>

    <script>
        function deletePlate(plateId) {
            // Gửi yêu cầu xóa đến server
            fetch('deletePlate', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: 'plateId=' + plateId
            })
            .then(response => {
                if (response.ok) {
                    // Xóa biển số khỏi giao diện
                    var plateElement = document.getElementById('plate_' + plateId);
                    if (plateElement) {
                        plateElement.remove();
                    }
                } else {
                    alert('Có lỗi xảy ra khi xóa biển số.');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Có lỗi xảy ra khi xóa biển số.');
            });
        }
    </script>
</body>
</html>