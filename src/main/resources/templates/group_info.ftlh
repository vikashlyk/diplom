<#import "blocks/template.ftlh" as t>
<@t.template>

    <h1>Группа</h1><hr>
    <h4>Подробная информация о группе</h4>
    <b>Нормер группы: </b>
        ${group.number}
    <br>
    <b>Специальность: </b>
    <#if group.specialization??>
        ${group.specialization.specialtyName}
        <#else>
        -
    </#if>
    <br>
    <b>Факультет: </b>${group.faculty.name}<br>
<#--    <b>Список студентов  :</b>-->
    <hr>
    <form action="/group/delete/${group.id}" method="post">
        <input type="submit" value="Удалить"\>
    </form>
</@t.template>
<#--    </body>-->
<#--    </html>-->