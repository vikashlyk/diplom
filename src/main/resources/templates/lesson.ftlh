<#import "blocks/template.ftlh" as t>
<@t.template>
    <h4>Предметы</h4>

<#--поиск-->
<#--    <form action="/group" method="get">-->
<#--        Поиск по названию предмета: <input type="text" name="lessonName"><br>-->
<#--        <input type="submit" value="Поиск"/>-->
<#--    </form>-->
    <#list lessons as lesson>
        <div>
            <p>
                <#if lesson.lessonName??>
                    ${lesson.lessonName.name}
                <#else>
                    ...
                </#if> -
                <b>${lesson.hours}</b>
                <b>${lesson.lessonType.name()}</b>
                <a href="/lesson/${lesson.id}">Подробнее...</a>
            </p>
        </div>
    </#list>

    <hr>

    <h3>Добавить предмет</h3>
    <form action="/lesson/create" method="post">
        Название предмета:
        <select name="lessonNameId" >
            <#list lessonNames as les>
                <option value="${les.id}"> ${les.name} </option>
            </#list>
        </select>
        <br><br>
        Часы:
        <input type="text" name="hours"/><br><br>
        <select name="lessonType" >
            <#list lessonTypes as les>
                <option value="${les.name()}"> ${les.name()} </option>
            </#list>
        </select><br><br>
        <input type="submit" value="Добавить"\>
    </form>
</@t.template>
<#--    </body>-->
<#--    </html>-->