<!DOCTYPE html>
<html>

<head>
    <#include "head.ftlh"/>
    <link rel="stylesheet" type="text/css" href="/static/css/rolesSettings.css">
    <title>Управление ролями</title>
</head>

<body>
<h1>Управление ролями</h1>
<form action="/rolesSettings/add" method="post">
    <h2>Добавить роль</h2>
    <label for="assignUserId">Выберите пользователя:</label>
    <select name="userId" id="assignUserId">
        <#list accounts as user>
            <option value="${user.id}">${user.email}</option>
        </#list>
    </select>
    <br>
    <label for="assignRoleId">Выберите роль:</label>
    <select name="roleId" id="assignRoleId">
        <#list roles as role>
            <option value="${role.id}">${role.roleName}</option>
        </#list>
    </select>
    <br>
    <button type="submit">Назначить</button>
</form>
<form action="/rolesSettings/remove" method="post">
    <h2>Удалить роль</h2>
    <label for="removeUserId">Выберите пользователя:</label>
    <select name="userId" id="removeUserId">
        <#list accounts as user>
            <option value="${user.id}">${user.email}</option>
        </#list>
    </select>
    <br>
    <label for="removeRoleId">Выберите роль:</label>
    <select name="roleId" id="removeRoleId">
        <#list roles as role>
            <option value="${role.id}">${role.roleName}</option>
        </#list>
    </select>
    <br>
    <button type="submit">Удалить</button>
</form>


</body>
</html>