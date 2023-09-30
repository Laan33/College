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
        <h1>Employee Found</h1>
        <table>
            <thead>
                <tr>
            <b>
                <td>ID</td><td>Name</td><td>Salary</td>
            </b>
        </tr>
    </thead>
    <tr>
        <td>${employeeFound.empid}</td><td>${employeeFound.name}</td><td>${employeeFound.salary}</td>
    </tr>    
</table>
<br><br><br>

<h2><a href="index.html">Home</a></h2>
</body>
</html>
