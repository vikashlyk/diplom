<#import "blocks/template.ftlh" as t>
<@t.template>
    <h1>Предмет</h1><hr>
<#--    <h4>Подробная информация о группе</h4>-->

    <b>Название предмета: </b>
    <#if lessons.lessonName??>
        ${lessons.lessonName.name}
    <#else>
        -
    </#if>
    <br>
    <b>Кол-во часов: </b>${lessons.hours}<br>
    <hr>
    <form action="/lesson/delete/${lessons.id}" method="post">
        <input type="submit" value="Удалить"\>
    </form>
</@t.template>