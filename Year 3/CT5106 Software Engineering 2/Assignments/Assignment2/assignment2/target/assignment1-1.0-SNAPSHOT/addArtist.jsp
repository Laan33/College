<!DOCTYPE html>
<html>

<head>
    <title>Add Artist </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        body {

            color: rgb(134, 235, 255);
            background-color: rgb(73, 73, 73);
        }
    </style>
</head>

<body>
    <h2>Add Artist form</h2>
    <form action="AddArtistServlet" method="post">
        <fieldset>
            <label>First Name</label>
            <input type="text" name="firstName">
            <label>Surname</label>
            <input type="text" name="surname">
            <label>Nationality</label>
            <input type="text" name="nationality">
            <label>Year of Birth</label>
            <input type="number" name="yearOfBirth">
            <label>Year of Death</label>
            <input type="number" name="yearOfDeath">
            <br><br>
            <label>Biography</label>
            <textarea name="biography" rows="4" cols="50"></textarea>
            <br><br>
            <label>Artist Image URL</label>
            <input type="url" name="artistImage">

            <input type="submit" value="Submit">
        </fieldset>
    </form>
    <br><br>
    <!-- Link back to displayArtists.jsp-->
    <a href="displayArtists.jsp" style="color: rgb(134, 235, 255);">Back to Artists</a>
</body>

</html>