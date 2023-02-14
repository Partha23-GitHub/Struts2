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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello click on the submit button to put data</h1>
        <form action="ApiFetch" method="Post" id="myForm">
            <label for="floatingInput">URL</label>
            <input type="text" name="url" class="form-control" id="floatingInput" style="width: 300px">
            
            <button class="w-100 btn btn-lg btn-primary" type="submit">Submit</button>
        </form>

    </body>
</html>
