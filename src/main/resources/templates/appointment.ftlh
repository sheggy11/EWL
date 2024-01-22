<!DOCTYPE html>
<html>
<head>
    <#include "head.ftlh"/>
    <link rel="stylesheet" type="text/css" href="/static/css/appointment.css">

    <title>Добавление записи</title>
</head>
<body>
<h2>Добавить новую запись</h2>


<form action="/appointment" method="post">
    <label for="patientSelect">Выберите пациента:</label>
    <select id="patientSelect" name="patient_id">
        <option value="">Выберите пациента</option>
        <#list patients as patient>
            <option value="${patient.id}">${patient.lastName}</option>
        </#list>
    </select><br><br>

    <label for="medServiceSelect">Выберите услугу:</label>
    <select id="medServiceSelect" name="med_service_id">
        <option value="">Выберите услугу</option>
        <#list medServices as medService>
            <option value="${medService.id}">${medService.name}</option>
        </#list>
    </select>

    <label for="appointment_date">Дата назначения:</label>
    <input type="date" id="appointment_date" name="appointment_date" required>

    <label for="appointment_note">Обоснование</label>
    <input type="text" id="appointment_note" name="appointment_note"><br><br>

    <label for="diagnosis">Диагноз:</label>
    <input type="text" id="diagnosis" name="diagnosis" required><br><br>

    <label for="urgencyNoteSelect">Выберите отметку о срочности записи:</label>
    <select id="urgency_note_id" name="urgency_note_id">
        <option value="">Выберите отметку о срочности записи</option>
        <option value="1">Планово</option>
        <option value="2">Cito</option>
        <option value="3">МСЭК</option>
        <option value="4">Госпитализация</option>
    </select>

    <label for="deadline">До какого числа записать пациента:</label>
    <input type="date" id="deadline" name="deadline" required><br><br>


    <label for="appointmentStatusSelect">Выберите статус:</label>
    <select id="appointment_status_id" name="appointment_status_id">
        <option value="">Выберите статус</option>
        <option value="1">Активна</option>
        <option value="2">Неактивна</option>
        <option value="3">Выполнена</option>
        <option value="4">Аннулирована</option>
    </select>

    <button type="submit">Добавить запись</button>


    <a href="/patients/addNewPatient">Создать нового пациента</a>

</form>
</body>
</html>