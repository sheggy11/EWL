<!DOCTYPE html>
<html>
<head>
    <#include "head.ftlh"/>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/static/css/patient.css">
    <title>Список пациентов</title>
</head>
<body>
<h1>Список пациентов</h1>
<form class="search-form">
    <label for="searchField">Поиск:</label>
    <input type="text" id="searchField" name="searchField">
    <button type="button" onclick="searchTable()">Искать</button>
</form>
<table>
    <tr>
        <th>#</th>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Отчество</th>
        <th>Номер телефона</th>
        <th>Дата рождения</th>
        <th>Дата создания</th>
        <th>Дата обновления</th>
        <th>Мед Центр</th>
    </tr>
    <#list patients as patient>
        <tr>
            <td>${patient_index + 1}</td>
            <td>${patient.lastName}</td>
            <td>${patient.firstName}</td>
            <td>${patient.patronymic}</td>
            <td>${patient.phoneNumber}</td>
            <td>${patient.dateOfBirth}</td>
            <td>${dateTimeFormatter.format(patient.createDate)}</td>
            <td>${dateTimeFormatter.format(patient.updateDate)}</td>
            <td>
                <#list medCenters as medCenter>
                    <#if medCenter.id == patient.medCenter.id>
                        ${medCenter.name}
                    </#if>
                </#list>
            </td>
            <td>
                <a href="/patients/${patient.id}/profile">Редактировать</a>
            </td>
        </tr>
    </#list>
</table>

<button onclick="window.location.href='/patients/addNewPatient'">Создать</button>

<script>
    function searchTable() {
        var input, filter, table, tr, td, i, j, txtValue;
        input = document.getElementById("searchField");
        filter = input.value.toLowerCase();
        table = document.querySelector("table");
        tr = table.getElementsByTagName("tr");

        for (i = 1; i < tr.length; i++) {
            tr[i].style.display = "none";
            for (j = 1; j < tr[i].cells.length; j++) {
                td = tr[i].cells[j];
                if (td) {
                    txtValue = td.textContent || td.innerText;
                    if (txtValue.toLowerCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                        break;
                    }
                }
            }
        }
    }
</script>
</body>
</html>