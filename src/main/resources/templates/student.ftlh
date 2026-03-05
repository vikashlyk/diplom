<#import "blocks/template.ftlh" as t>
<@t.template>
<#--<h1>ГРУППЫ</h1><hr>-->
    <h4>Студенты</h4>

<#--поиск-->
    <form action="/student" method="get">
        Поиск по фамилии: <input type="lastName" name="lastName">
        <input type="submit" value="Поиск"/>
    </form>
    <br>
    <#list students as student>
        <div>
            <p><b>${student.lastName}</b> -
                <b>${student.firstName}</b> -
                <#if student.group??>
                    ${student.group.number}
                <#else>
                    (нет номера группы)
                </#if> -
                <#if student.diploma??>
                    ${student.diploma.topic}
                <#else>
                    (нет темы диплома)
                </#if> -
                <a href="/student/${student.id}">Сделать приложение</a>
            </p>
        </div>
    </#list>

    <hr>
    <h3>Создать студента</h3>
    <form action="/student/create" method="post">
        Имя : <input type="text" name="firstName"/><br><br>
        Фамилия : <input type="text" name="lastName"/><br><br>
        Отчество : <input type="text" name="middleName"/><br><br>
        Номер группы:
        <select name="groupId" >
            <#list groups as gr>
                <option value="${gr.id}"> ${gr.number} </option>
            </#list>
        </select>
        <br><br>
        <input type="submit" value="Добавить"\>

    </form>
</@t.template>
<#--    </body>-->
<#--    </html>-->