<%-- 
    Document   : api
    Created on : Feb 13, 2023, 2:45:39 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>API Page</title>
<!--        <link rel="stylesheet" href="css/all.min.css">
        <link rel="stylesheet" href="css/reset-min.css">
        <link rel="stylesheet" href="css/algolia-min.css">
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/docs.min.css">
        <link rel="stylesheet" href="css/index.css">-->
    </head>
    <body>
        <div>
            <jsp:include page="menu.jsp"></jsp:include>
            <h1>Hello click on the submit button to put data</h1>
            <form action="ApiFetch" method="Post" id="myForm">
                <label for="floatingInput">URL</label>
                <input type="text" name="url" class="form-control" id="floatingInput" style="width: 300px">

                <button type="submit" style="width:100px">Submit</button>
            </form>
        </div>
    </body>
</html>
