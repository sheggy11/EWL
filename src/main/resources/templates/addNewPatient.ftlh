<!DOCTYPE html>
<html>
<head>
    <#include "head.ftlh"/>
    <link rel="stylesheet" type="text/css" href="/static/css/add_new_patient.css">

    <title>Создать запись о пользователе</title>
</head>
<body>
<h1>Создать запись о пользователе</h1>

<form method="post" action="/patients/addNewPatient">
    <label for="firstName">Имя</label>
    <input type="text" id="firstName" name="firstName" required><br><br>

    <label for="lastName">Фамилия</label>
    <input type="text" id="lastName" name="lastName" required><br><br>

    <label for="patronymic">Отчество</label>
    <input type="text" id="patronymic" name="patronymic" required><br><br>

    <label for="dateOfBirth">Дата рождения</label>
    <input type="date" id="dateOfBirth" name="dateOfBirth"><br><br>

    <label for="phoneNumber">Номер телефона</label>
    <input type="text" id="phoneNumber" name="phoneNumber" required><br><br>

    <label for="medCenter">Выберите мед центр:</label>
    <select id="medCenter" name="medCenterId">
        <#list medCenters as medCenter>
            <option value="${medCenter.id}">${medCenter.name}</option>
        </#list>
    </select><br><br>

    <button type="submit">Добавить пользователя</button>
</form>
</body>
</html>
