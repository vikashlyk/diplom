<#macro template>
    <!doctype html>
    <html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<#--        <link rel="stylesheet" href="/css/bootstrap-reboot.min.css">-->

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
              integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
              crossorigin="anonymous">
        <#--стили сайта-->
        <link rel="stylesheet" href="/css/style.css">

        <title>Приложение к диплому</title>
    </head>
    <body>
    <#--меню-->
    <nav class="navBar">
        <div class="container">
            <div class="navbar-wrap">
                <ul class="navbar-menu">
                    <li><a href="/specialization">Специальности</a></li>
                    <li><a href="/group">Группы</a></li>
                    <li><a href="/faculty">Факультеты</a></li>
<#--                    <li><a href="/student">Студенты</a></li>-->
<#--                    <li><a href="/lesson">Предметы</a></li>-->
                    <li><a href="/diploma">Диплом</a></li>
                    <li><a href="/lessonName" >Создать предмет</a></li>
                    <li><a href="/lesson">Добавить предмет</a></li>
                    <li><a href="/lessonMark">Оценки</a></li>

                </ul>
                <a href="/student" class="create">Студенты </a>
            </div>
        </div>
    </nav>
    <div class="container mt-4">
        <#nested/>
    </div>

    </body>
    </html>
</#macro>