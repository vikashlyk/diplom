<#import "blocks/template.ftlh" as t>
<@t.template>

    <h4>Названия предметов</h4>

    <#list lessonNames as lessonName>
        <div>
            <p><b>${lessonName.name}</b>
                <a href="/lessonName/${lessonName.id}">Подробнее...</a> </p>
        </div>
    </#list>
    <hr>
    <h3>Создать новый предмет</h3>
    <form action="/lessonName/create" method="post">
         Название предмета: <input type="text" name="name"/><br><br>
        <input type="submit" value="Добавить"\>

    </form>
</@t.template>