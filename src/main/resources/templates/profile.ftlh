<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Личная страница</title>
    <div class="head-container">
        <#include "head.ftlh"/>
    </div>
    <link rel="stylesheet" type="text/css" href="/static/css/profile.css">
</head>
<body>

<#if account.hasRole("ADMIN")>
    <div class="six">
        <a href="/patients">Пациенты</a>
    </div>
    <div class="six">
        <a href="/rolesSettings">Управление ролями</a>
    </div>
    <div class="six">
        <a href="/medCentersSettings">Список мед центров</a>
    </div>
    <div class="six">
        <a href="/accounts">Список всех пользователей</a>
    </div>
    <div class="six">
        <a href="/callLogs">Журнал вызовов</a>
    </div>
    <div class="six">
        <a href="/appointment">Создать запись</a>
    </div>
</#if>

<#if account.hasRole("MED_OFFICER")>
    <h1>Ваша роль: Мед сотрудник</h1>

    <div class="six">
        <a href="/patients">Пациенты</a>
    </div>
    <div class="six">
        <a href="/medCentersSettings">Список мед центров</a>
    </div>
    <div class="six">
        <a href="/appointment">Создать запись</a>
    </div>
</#if>

<#if account.hasRole("OPERATOR")>
    <h1>Ваша роль: Оператор</h1>
    <div class="six">
        <a href="/callLogs">Журнал вызовов</a>
    </div>
</#if>

<br>
<div class="center">
    <br>
    <p class="lead">Добро пожаловать, ${account.firstName} ${account.lastName}!</p>
    <p>Ваш медицинский центр: ${account.medCenter.name} </p>
    <a href="/logout">Выход</a>
    <br>
</div>
</body>
</html>