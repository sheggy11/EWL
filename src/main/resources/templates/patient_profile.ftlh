<!DOCTYPE html>
<html>
<head>
    <title>Профиль пациента</title>
    <#include "head.ftlh"/>
</head>
<body>

<h1>Профиль пациента</h1>
<form method="post" action="/patients/${patient.id}/update">

    <label for="firstName">Имя:</label>
    <input type="text" id="firstName" name="firstName" value="${patient.firstName}"><br><br>

    <label for="lastName">Фамилия:</label>
    <input type="text" id="lastName" name="lastName" value="${patient.lastName}"><br><br>

    <label for="patronymic">Отчество:</label>
    <input type="text" id="patronymic" name="patronymic" value="${patient.patronymic}"><br><br>

    <label for="dateOfBirth">Дата рождения</label>
    <input type="date" id="dateOfBirth" name="dateOfBirth" value="${patient.dateOfBirth}"><br><br>

    <label for="phoneNumber">Номер телефона</label>
    <input type="text" id="phoneNumber" name="phoneNumber" value="${patient.phoneNumber}"><br><br>


    <button type="submit">Сохранить</button>
</form>
<h2>Мед центр пациента</h2>
<form method="post" action="/patients/${patient.id}/updateMedCenter">
    <#if patient.medCenter??>
        <label for="medCenter">Медицинский центр:</label>
        <input type="text" id="medCenter" name="medCenter" value="${patient.medCenter.name}" readonly><br><br>
    </#if>

    <label for="medCenterId">Выберите новый медицинский центр:</label>
    <select id="medCenterId" name="medCenterId">
        <#list medCenters as medCenter>
            <option value="${medCenter.id}">${medCenter.name}</option>
        </#list>
    </select><br><br>

    <button type="submit">Сохранить</button><br><br>

    <a href="/patients">Вернуться к списку всех пациентов</a>

</body>
</html>
