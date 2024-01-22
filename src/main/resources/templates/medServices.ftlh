<!DOCTYPE html>
<html>
<head>
    <title>Услуги медицинского центра</title>
</head>
<body>
<h1>Услуги медицинского центра "${medCenter.name}"</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Название</th>
        <th>Статус</th>
    </tr>
    <#list services as service>
        <tr>
            <td>${service.id}</td>
            <td>${service.name}</td>

            <td>
                <form method="post" action="/medCentersSettings/medServices/edit/${medCenter.id}/${service.id}">
                    <select name="status">
                        <option value="active" <#if service.status == 'active'>selected</#if>>Активно</option>
                        <option value="inactive" <#if service.status == 'inactive'>selected</#if>>Неактивно</option>
                    </select>
                    <button type="submit">Save</button>
                </form>
            </td>
        </tr>
    </#list>
</table>

    <h2>Добавить новую услугу</h2>

<form method="post" action="/medCentersSettings/medServices/${medCenter.id}/add">
    <label for="name">Название услуги:</label>
    <input type="text" id="name" name="name" required>
    <label for="status">Status:</label>
    <select id="status" name="status" required>
        <option value="active">Активно</option>
        <option value="inactive">Неактивно</option>
    </select><br><br>
    <button type="submit">Добавить услугу</button>
</form>


<a href="/medCentersSettings">Назад к списку медицинских центров</a>
</body>
</html>




