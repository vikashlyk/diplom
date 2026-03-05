<#import "blocks/template.ftlh" as t>
<@t.template>

<#--<h1>ГРУППЫ</h1><hr>-->
    <h4>Диплом</h4>

<#--поиск-->
    <form action="/diploma" method="get">
        Поиск по студенту: <input type="text" name="topic">
        <input type="submit" value="Поиск"/>
    </form>
    <br>
    <#list diploms as diploma>
        <div>
            <p><b>${diploma.topic}</b> -
                <#if diploma.student??>
                    ${diploma.student.lastName}
                <#else>
                    ...
                </#if> -
<#--                ${diploma.hours}-->
                <a href="/diploma/${diploma.id}">Подробнее...</a>
            </p>
        </div>
    </#list>

    <hr>
    <h3>Добавить студенту тему диплома</h3>
    <form action="/diploma/create" method="post">
        Тема: <input type="text" name="topic"/><br><br>
        Часы: <input type="text" name="hours"/><br><br>
        Студент:
        <select name="studentId" >
            <#list students as spec>
                <option value="${spec.id}"> ${spec.lastName} ${spec.firstName} ${spec.middleName}
                    <#if spec.group??>
                        ${spec.group.number}
                    </#if>
                </option>
            </#list>
        </select>

        <br><br>
        <input type="submit" value="Добавить"\>

    </form>
</@t.template>
<#--    </body>-->
<#--    </html>-->