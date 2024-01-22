<!DOCTYPE html>
<html>
<head>
    <#include "head.ftlh"/>
    <title>Информация о звонке</title>
</head>
<body>
<h2>Карточка звонка</h2>
    <p>ФИО: ${appointment.patient.lastName} ${appointment.patient.firstName} ${appointment.patient.patronymic}</p>
    <p>Номер телефона: ${appointment.patient.phoneNumber}</p>
    <p>Услуга: ${appointment.getMedService().name}</p>
<h3>Список звонков по этой услуге</h3>
<table>
    <tr>
        <th>Дата звонка</th>
        <th>Статус звонка</th>
        <th>Комментарий</th>
    </tr>
    <#list callLogs as callLogs>
        <tr>
            <td>${callLogs.callDate}</td>
            <td>${callLogs.callStatus.name}</td>
            <td>${callLogs.comment}</td>

    </#list>
</table>

<#if appointment.appointmentStatus.getId() != 3 && appointment.appointmentStatus.getId() != 5>
<h3>Форма для заполнения</h3>

<form action="/callLogs/callCard/${appointment.id}/add" method="post">

    <input type="hidden" name="appointment_id" value="${appointment.id}">

    <label for="callStatusSelect">Статус звонка:</label>
    <select id="call_status_id" name="call_status_id">
        <option value=""></option>
        <option value="1">Дозвонились</option>
        <option value="2">Неверный номер</option>
        <option value="3">Недоступен</option>
    </select>

    <label for="comment">Комментарий:</label>
    <textarea id="comment" name="comment"></textarea>
    <br>

    <label for="callResultSelect" id="callResultLabel" style="display: none;">Результат звонка:</label>
    <select id="call_result_id" name="call_result_id" style="display: none;">
        <option value=""></option>
        <option value="1">Записан</option>
        <option value="2">Отказ пациента</option>
        <option value="3">Предложить другую дату</option>
    </select>
    <input type="submit" value="Сохранить">
</form>
</#if>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const callStatusSelect = document.getElementById('call_status_id');
        const callResultLabel = document.getElementById('callResultLabel');
        const callResultSelect = document.getElementById('call_result_id');

        callStatusSelect.addEventListener('change', function() {
            if (callStatusSelect.value === '1') {
                callResultLabel.style.display = 'block';
                callResultSelect.style.display = 'block';
            } else {
                callResultLabel.style.display = 'none';
                callResultSelect.style.display = 'none';
            }
        });
    });
</script>

<#if appointment.appointmentStatus.getId() != 3 && appointment.appointmentStatus.getId() != 5>
<a href="/callLogs">Вернуться</a>
    <#else> <a href="/callLogs/archive">Вернуться</a>

</#if>

</body>
</html>
