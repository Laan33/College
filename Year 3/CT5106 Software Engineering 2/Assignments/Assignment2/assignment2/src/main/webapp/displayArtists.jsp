<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Artists</title>
            <style>
                body {
                    text-align: center;
                    color: rgb(134, 235, 255);
                    background-color: rgb(73, 73, 73);
                }
            </style>
        </head>

        <body>
            <h1>Summary of Artists Information</h1>
            <table>
                <thead>
                    <tr>
                        <b>
                            <th>Surname</th>
                            <th>First Name</th>
                            <th>Nationality</th>
                            <th>Birth Year</th>
                            <th>Death Year <br> optional :&#41;</th>
                            <th>Further Details</th>
                        </b>
                    </tr>
                </thead>
                <c:forEach var="c" items="${artistsInfo}">
                    <tr>
                        <td>${c.surname}</td>
                        <td>${c.firstName}</td>
                        <td>${c.nationality}</td>
                        <td>${c.yearOfBirth}</td>
                        <td>${c.yearOfDeath}</td>
                        <td>
                            <form action="<c:url value='viewArtist.jsp'/>" method="post">
                                <input type="hidden" name="artistID" value="${c.artistID}">
                                <input type="hidden" name="firstName" value="${c.firstName}">
                                <input type="hidden" name="surname" value="${c.surname}">
                                <input type="submit" name="submit" value="Details">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
                <br><br><b><a href="addArtist.jsp" style="color: rgb(134, 235, 255);">Add an Artist</a></b>
            </body>

        </html>