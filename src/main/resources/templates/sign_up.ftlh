<!doctype html>
<#import "spring.ftl" as spring/>
<html lang="en">
<head>
    <title>Регистрация</title>
    <link rel="stylesheet" type="text/css" href="/static/css/signUp.css">
</head>
<body>
<h1>Регистрация</h1>

<form action="/signUp" method="post">
    <label for="lastName">Фамилия:</label>
    <input type="text" id="lastName" name="lastName" required>
    <br>
    <label for="firstName">Имя:</label>
    <input type="text" id="firstName" name="firstName" required>
    <br>
    <label for="patronymic">Отчество:</label>
    <input type="text" id="patronymic" name="patronymic" required>
    <br>
    <label for="email">Электронная почта:</label>
    <input type="text" id="email" name="email" required>
    <br>
    <label for="password">Пароль:</label>
    <div class="password-container">
        <input type="password" id="password" name="password" required>
        <button type="button" id="showPasswordToggle">&#128065;</button>
    </div>


    <label>Выберите медицинский центр:</label>
    <select name="medCenter" required>
        <#list medCenterList as medCenter>
            <option value="${medCenter.id}">${medCenter.name}</option>
        </#list>
    </select>
    <br>
    <button type="submit">Зарегистрироваться</button>

    <div class="signup_link">Есть аккаунт?
        <a href="/signIn">Войти</a>
    </div>
</form>

<script>
    const showPasswordToggle = document.getElementById('showPasswordToggle');
    const passwordField = document.getElementById('password');

    // Изначально устанавливаем тип поля ввода пароля как текст
    passwordField.setAttribute('type', 'text');
    showPasswordToggle.style.textDecoration = 'line-through'; // Текст значка будет перечеркнутым

    showPasswordToggle.addEventListener('click', function () {
        const type = passwordField.getAttribute('type') === 'password' ? 'text' : 'password';
        passwordField.setAttribute('type', type);

        if (type === 'password') {
            showPasswordToggle.style.textDecoration = 'line-through';
        } else {
            showPasswordToggle.style.textDecoration = 'none';
        }
    });

</script>

</body>
</html>