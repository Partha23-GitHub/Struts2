<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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