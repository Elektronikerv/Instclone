
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="stylesheets/uploadImage.css" rel="stylesheet">
    <title>Upload image</title>
</head>
<body>
<div class="center">
    <div class="container">
        <h3>Upload image</h3>
        <form method="post" action="/uploadImage"
              encType="multipart/form-data">
            Description: <textarea  name="description"></textarea>
            <br>
          <input type="file" name="file" value="select image..." accept="image/*"/>
          <input class="upload" type="submit" value="start upload"/>
        </form>
    </div>
</div>
</body>
</html>
