<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tải lên Video</title>
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
        .upload-container {
            background: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            margin: auto;
        }
        input[type="file"] {
            display: none; /* Ẩn nút chọn file */
        }
        .file-label {
            display: inline-block;
            padding: 10px 20px;
            background: #007bff;
            color: white;
            border-radius: 5px;
            cursor: pointer;
            transition: background 0.3s;
        }
        .file-label:hover {
            background: #0056b3;
        }
        video {
            margin-top: 20px;
            max-width: 100%;
            border-radius: 5px;
        }
    </style>
    <script>
        function handleFileSelect(event) {
            const file = event.target.files[0];
            const videoElement = document.getElementById('videoPreview');
            if (file) {
                const fileURL = URL.createObjectURL(file);
                videoElement.src = fileURL;
                videoElement.style.display = 'block'; // Hiển thị video
                document.getElementById('uploadButton').disabled = false; // Kích hoạt nút upload
            }
        }

      
    </script>
</head>
<body>
    <div class="upload-container">
        <h2>Tải lên Video</h2>
        <form action="video-upload" method="post" enctype="multipart/form-data">
            <label class="file-label" for="fileInput">Chọn Video</label>
            <input type="file" id="fileInput" name="file" accept="video/*" onchange="handleFileSelect(event)">
            <button id="uploadButton" type="submit" disabled >Upload</button>
        </form>
        <video id="videoPreview" controls style="display:none;"></video>
    </div>
</body>
</html>