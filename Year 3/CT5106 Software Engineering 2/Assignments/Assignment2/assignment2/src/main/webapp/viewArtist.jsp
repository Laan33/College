<a href="viewArtist.jsp"></a>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${param.artistID} - ${param.firstName} ${param.surname} </title>
        <style>
            body {
                font-size: 20px;
                color: rgb(0, 202, 242);
                background-color: rgb(73, 73, 73);
            }
        </style>

    </head>

    <body>
        <div id="details">
            <h2>Artist Details</h1>
                <h2>${param.firstName} ${param.surname}</h2>
                <c:forEach var="c" items="${artistsInfo}">
                    <c:if test="${c.artistID == param.artistID}">
                        <table>
                            <tr>
                                <td>
                                    <c:if test="${c.yearOfDeath != 0}">
                                        <h3>Born: ${c.yearOfBirth} - Died: ${c.yearOfDeath} </h3>
                                    </c:if>
                                    <c:if test="${c.yearOfDeath == 0}">
                                        <h3>Born: ${c.yearOfBirth} - Current</h3>
                                    </c:if>
                                </td>

                            </tr>
                            <tr>
                                <td><img src="${c.artistPhoto}" alt="${param.firstName} ${param.surname}"
                                        style="max-width: 320px; max-height: 320px;"></td>
                            </tr>
                            <tr>
                                <td>
                                    <h3>Biography</h3>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <p>${c.biography}</p>
                                </td>
                            </tr>
                        </table>
                    </c:if>
                </c:forEach>
        </div>
        <br><br>
        <!-- Link back to displayArtists.jsp-->
        <a href="displayArtists.jsp" style="color: rgb(134, 235, 255);">Back to Artists</a>
    </body>

</html>