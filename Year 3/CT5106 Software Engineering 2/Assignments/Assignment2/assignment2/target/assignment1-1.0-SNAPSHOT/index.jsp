<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            body {
                font-size: 40px;
                text-align: center;
                color: rgb(134, 235, 255);
                background-color: rgb(73, 73, 73);
            }
        </style>
    </head>

    <body>        
        <a href="displayArtists.jsp" style="color: rgb(134, 235, 255);">Display Artists</a>
        <br><br>
        <!-- As explained in GetAristsServlet, this serlvet exists just to demonstrate the web app easier, 
            it is not needed for based function-->
        <a href="GetArtistsServlet" style="color: rgb(134, 235, 255);"> Create and display starting sample Artists</a>
    </body>

    </html>
        
    </body>

    </html>