<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" rel="stylesheet" type="text/css"/>
        <title>Customers</title>
    </head>
    <body>
        <h1>List of Customers</h1>
        <table>
            <thead>
                <tr>
            <b>
                <td>ID</td><td>Name</td><td>Address</td><td>Phone</td><td>Email</td><td>Country</td><td>Post Code</td><td>Credit Limit</td>
            </b>
        </tr>
    </thead>
    <c:forEach var="cust" items="${customers}">
        <tr>
            <td>${cust.id}</td><td>${cust.name}</td><td>${cust.address}</td><td>${cust.phone}</td><td>${cust.email}</td><td>${cust.country}</td><td>${cust.postCode}</td><td>${cust.creditLimit}</td>
            <td>
                <form action="DeleteCustomer" method="POST">
                    <input type="hidden" name="id" value="${cust.id}" />
                    <input type="submit" value="Delete" />
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br><br><br>

<h2><a href="addCustomer.html">Add Customers</a></h2>


<h2><a href="index.html">Go home</a></h2>

</body>
</html>
