<!DOCTYPE html>
<html>
<head>
    <title>Edit Account</title>
</head>
<body>
<h2>Edit Account</h2>
<form action="/profile/update" method="post">
    <label for="firstName">First Name:</label><br>
    <input type="text" id="firstName" name="firstName" value="${accountDto.firstName}"><br>
    <label for="lastName">Last Name:</label><br>
    <input type="text" id="lastName" name="lastName" value="${accountDto.lastName}"><br>
    <label for="patronymic">Patronymic:</label><br>
    <input type="text" id="patronymic" name="patronymic" value="${accountDto.patronymic}"><br>
    <label for="medCenter">Med Center:</label><br>

    <label for="password">Password:</label><br>
    <input type="password" id="password" name="password"><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
