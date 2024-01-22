<!DOCTYPE html>
<html>
<head>
    <#include "head.ftlh"/>
    <link rel="stylesheet" type="text/css" href="/static/css/call_logs.css">

    <title>Журнал вызовов</title>
</head>
<body>
<h2>Список всех записей</h2>


<table>
    <tr>
        <th>#</th>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Отчество</th>
        <th>Номер телефона</th>
        <th>Услуга</th>
        <th>Клиника</th>
        <th>Количество звонков</th>
        <th>Последний звонок</th>
        <th>Дата создания</th>
    </tr>
    <#list appointments as appointment>
        <#if callLogsCounts[appointment_index] < 3 && appointment.appointmentStatus.getId() != 3>
            <tr>
                <td>${appointment.id}</td>
                <td>${appointment.patient.lastName}</td>
                <td>${appointment.patient.firstName}</td>
                <td>${appointment.patient.patronymic}</td>
                <td>${appointment.patient.phoneNumber}</td>
                <td>${appointment.getMedService().name}</td>
                <td>${appointment.patient.medCenter.name}</td>
                <td>${callLogsCounts[appointment_index]}</td>
                <td>${lastCallDates[appointment_index]}</td>
                <td>${dateTimeFormatter.format(appointment.appointment_create_date)}</td>
                <td>
                    <a href="/callLogs/callCard/${appointment.id}">Звонок</a>
                </td>
            </tr>
        </#if>
    </#list>
</table>

<a href="/callLogs/archive">Архив вызовов</a>

</body>
</html>