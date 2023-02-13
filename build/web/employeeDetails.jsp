<%-- 
    Document   : landingPage
    Created on : 22-Dec-2022, 12:00:33 PM
    Author     : Avijit Chattopadhyay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.exavalu.services.EmployeeService"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--        <link href="css/header.css" rel="stylesheet">-->
        <link rel="stylesheet" href="css/all.min.css">
        <link rel="stylesheet" href="css/reset-min.css">
        <link rel="stylesheet" href="css/algolia-min.css">
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/docs.min.css">
        <link rel="stylesheet" href="css/index.css">
        <title>Employee Management</title>
    </head>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <!<!-- I want to check my session before showing any content to the user -->


    <body>

        <jsp:include page="menu.jsp"></jsp:include>

            <div id="example">
                <script>
                    init({
                        title: 'Large data',
                        desc: 'Use `virtualScroll` to enable the virtual scroll to play with large data sets (10000 rows).',
                        links: ['bootstrap-table.min.css'],
                        scripts: ['bootstrap-table.min.js']
                    })
                </script>



                <div class="bootstrap-table bootstrap5">
                    <div class="fixed-table-toolbar"><div class="bs-bars float-left">

                        </div><div class="columns columns-right btn-group float-right"><div class="keep-open btn-group" title="Columns">
                                <div class="dropdown-menu dropdown-menu-right" style=""><label class="dropdown-item dropdown-item-marker"><input type="checkbox" data-field="id" value="0" checked="checked"> <span>ID</span></label><label class="dropdown-item dropdown-item-marker"><input type="checkbox" data-field="name" value="1" checked="checked"> <span>Item Name</span></label><label class="dropdown-item dropdown-item-marker"><input type="checkbox" data-field="price" value="2" checked="checked"> <span>Item Price</span></label></div></div></div></div>

                    <div class="fixed-table-container fixed-height" style="height: 542px; padding-bottom: 50.5px;">

                        <div class="fixed-table-body">
                            <table id="table" data-height="400" data-virtual-scroll="true" class="table table-bordered table-hover" style="margin-top: -9.5px;">
                                <table id="example" class="table table-striped border">
                                    <thead>
                                        <tr class="backgroud_color">
                                            <th>
                                                Employee Id
                                            </th>
                                            <th>
                                                First Name
                                            </th>
                                            <th>
                                                Last Name
                                            </th>
                                            <th>
                                                Phone Number
                                            </th>
                                            <th>
                                                Gender
                                            </th>
                                            <th>
                                                Age
                                            </th>
                                            <th>
                                                Department Name
                                            </th>
                                            <th>
                                                Role Name
                                            </th>
                                            <th>
                                                Basic Salary
                                            </th>
                                            <th>
                                                Allowance
                                            </th>

                                            <th>
                                                Action
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                    <c:forEach items="${EmpList}" var="emp">
                                        <tr>
                                            <td>

                                                ${emp.getEmployeeId()}
                                            </td>
                                            <th scope="row">
                                                ${emp.getFirstName()}                    </th>
                                            <th scope="row">
                                                ${emp.getLastName()}                    </th>
                                            <td>
                                                ${emp.getPhone()}                    </td>
                                            <td>
                                                ${emp.getGender()}                    </td>
                                            <td>
                                                ${emp.getAge()}                    </td>
                                            <td>
                                                ${emp.getDepName()}                    </td>
                                            <td>
                                                ${emp.getRoleName()}                    </td>
                                            <td>
                                                ${emp.getSalary()}                    </td>
                                            <td>
                                                ${emp.getAllowance()}                    </td>
                                            <td> 
                                                <a href="Edit?employeeId=${emp.getEmployeeId()}">Edit</a>
                                                <a href="Delete?employeeId=${emp.getEmployeeId()}">Delete</a>
                                                </td>                                
                                        </tr>

                                    </c:forEach>
                                </tbody>
                            </table>

                            <div class="fixed-table-footer" style="display: none;"></div>
                    </div>
                    <div class="fixed-table-pagination" style="display: none;"></div>
                </div>
                </body>
                </html>
