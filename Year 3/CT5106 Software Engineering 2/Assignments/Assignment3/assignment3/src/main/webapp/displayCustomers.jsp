<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" rel="stylesheet" type="text/css"/>
        <title>Employees</title>
    </head>
    <body>
        <h1>List of Employees</h1>
        <table>
            <thead>
                <tr>
            <b>
                <td>ID</td><td>Name</td><td>Salary</td>
            </b>
        </tr>
    </thead>
    <c:forEach var="emp" items="${employees}">
        <tr>
            <td>${emp.empid}</td><td>${emp.name}</td><td>${emp.salary}</td>
            <td>
                <form action="DeleteEmployee" method="post"> 
                    <input type="hidden" name="id" value="${emp.empid}"> 
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br><br><br>

<h2><a href="addEmployee.html">Add Employee</a></h2>


<h2><a href="index.html">Home</a></h2>

</body>
</html>
