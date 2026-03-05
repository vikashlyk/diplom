<#import "blocks/template.ftlh" as t>
<@t.template>
    <h1>Диплом</h1><hr>
    <h4>
        <#if diploma.student??>
            ${diploma.student.lastName}
            ${diploma.student.firstName}
            ${diploma.student.group.number}<br>
        <#else>
            -
        </#if>
    </h4>
    <b>Тема диплома: </b>${diploma.topic}<br>
    <b>Часы: </b>${diploma.hours}<br>
    <hr>
    <form action="/diploma/delete/${diploma.id}" method="post">
        <input type="submit" value="Удалить"\>
    </form>
</@t.template>