<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link href="style.css" rel="stylesheet" type="text/css" />
            <title>Customers</title>
        </head>

        <body>
            <h1>Customer Found</h1>
            <table>
                <thead>
                    <tr>
                        <b>
                            <td>ID</td>
                            <td>Name</td>
                            <td>Address</td>
                            <td>Phone</td>
                            <td>Email</td>
                            <td>Country</td>
                            <td>Post Code</td>
                            <td>Credit Limit</td>
                        </b>

                    </tr>
                </thead>
                <tr>
                    <td>${customer.id}</td>
                    <td>${customer.name}</td>
                    <td>${customer.address}</td>
                    <td>${customer.phone}</td>
                    <td>${customer.email}</td>
                    <td>${customer.country}</td>
                    <td>${customer.postCode}</td>
                    <td>${customer.creditLimit}</td>
                </tr>
            </table>
            <br><br><br>

            <h2><a href="index.html">Go home</a></h2>
        </body>

        </html>