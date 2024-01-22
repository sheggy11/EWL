<!DOCTYPE html>
<html>
<head>
      <#include "head.ftlh"/>
    <link rel="stylesheet" type="text/css" href="/static/css/accounts.css">
    <meta charset="UTF-8">
      <title>Список пользователей</title>
    <style>
        .search-form {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<h1>Список пользователей</h1>
<form class="search-form">
    <label for="searchField">Поиск:</label>
    <input type="text" id="searchField" name="searchField">
    <button type="button" onclick="searchTable()">Искать</button>
</form>
<table>
    <tr>
        <th>ID</th>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Отчество</th>
        <th>Медицинский центр</th>
        <th>Электронная почта</th>
    </tr>
    <#list accounts as account>
        <tr>
            <td>${account.id}</td>
            <td>${account.lastName}</td>
            <td>${account.firstName}</td>
            <td>${account.patronymic}</td>
            <td>
                <#list medCenters as medCenter>
                    <#if medCenter.id == account.medCenter.id>
                        ${medCenter.name}
                    </#if>
                </#list>
            </td>
            <td>${account.email}</td>
        </tr>
    </#list>
</table>
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