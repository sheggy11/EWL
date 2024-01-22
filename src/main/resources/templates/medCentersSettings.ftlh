<!DOCTYPE html>
<html>
<head>
    <#include "head.ftlh"/>
    <link rel="stylesheet" type="text/css" href="/static/css/medCenterSettings.css">
    <title>Med Centers Settings</title>
</head>
<body>
<h1>Список всех мед центров</h1>
<table>
    <tr>
        <th>#</th>
        <th>Название</th>
        <th>Статус</th>
        <th>Список услуг</th>
    </tr>
    <#list medCenters as medCenter>
        <tr>
            <td>${medCenter_index + 1}</td>
            <td>${medCenter.name}</td>
            <td>
                <form method="post" action="/medCentersSettings/edit/${medCenter.id}">
                    <label>
                        <input type="checkbox" name="statusCheckbox" <#if medCenter.status == 'active'>checked</#if> onchange="updateStatus(this)">
                        <input type="hidden" name="status" value="inactive">
                    </label>

                </form>
            </td>
            <td><a href="/medCentersSettings/medServices/${medCenter.id}">Услуги</a></td>
        </tr>
    </#list>
</table>

<h1>Добавить новый мед центр</h1>
<form method="post" action="/medCentersSettings/add">
    <label for="name">Название:</label>
    <input type="text" id="name" name="name" required><br><br>
    <label for="status">Статус:</label>
    <select id="status" name="status" required>
        <option value="active">Активно</option>
        <option value="inactive">Неактивно</option>
    </select><br><br>
    <button type="submit">Добавить</button>
</form>

<script>
    function updateStatus(checkbox) {
        const hiddenInput = checkbox.nextSibling.nextSibling;
        if (checkbox.checked) {
            hiddenInput.value = "active";
        } else {
            hiddenInput.value = "inactive";
        }

        checkbox.closest('form').submit();
    }
</script>

</body>
</html>