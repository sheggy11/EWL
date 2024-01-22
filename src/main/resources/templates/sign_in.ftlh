<!doctype html>
<html lang="en">

<head>
    <title>Страница входа</title>
    <link rel="stylesheet" type="text/css" href="/static/css/signIn.css">
</head>

<body>

<div class="center">
    <h1>Вход</h1>
    <form action="/signIn" method="post">


        <div class="txt_field">
            <input type="email" name="email" placeholder="Почта">
        </div>

        <div class="txt_field">
            <input type="password" name="password" placeholder="Пароль">
        </div>

        <div class="signup_link">Запомнить меня?
            <input type="checkbox" name="rememberMe">
        </div>

        <input type="submit" value="войти">
    </form>

    <div class="signup_link">Нет аккаунта? <a href="/signUp">Регистрация</a></div>

    <#if errorMessage??>
        <p class="error-message">${errorMessage}</p>
    </#if>
</div>
</body>
</html>