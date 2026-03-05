<#import "blocks/template.ftlh" as t>
<@t.template>
    <h1>Формирование приложения</h1><hr>
<#--    <h4>Информация о студенте</h4>-->
    <b>Фамилия: </b>${student.lastName}<br>
    <b>Имя: </b>${student.firstName}<br>
    <b>Отчество: </b>${student.middleName}<br>
    <b>Группа: </b>
    <#if student.group??>
        ${student.group.number}
    <#else>
        -
    </#if>
    <br>
    <b>Тема диплома: </b>
    <#if student.diploma??>
        ${student.diploma.topic}
    <#else>
        (нет темы)
    </#if>
    <br><br>

    <form action="/student/${student.id}/extract" method="post">
        Номер выписки:
        <input style="width:150px; height: 20px" type="text" name="extractNumber" value="" placeholder="13039302"><br>
        Срок обучения:
        <input style="width:320px; height: 20px" type="text" name="educationTerm" value="01 сентября 2020 г. по 28 июня 2024 года"><br>
        Дата выдачи:
        <input style="width:150px; height: 20px" type="text" name="date" value="18 мая 2024 года"><br>
        Регистрационный номер:
        <input style="width:150px; height: 20px" type="text" name="registrationNumber" value="0001-233"><br><br>

        <input type="submit" value="Создать выписку"/>
    </form>

    <hr>
    <form action="/student/delete/${student.id}" method="post">
        <input type="submit" value="Удалить"\>
    </form>
</@t.template>