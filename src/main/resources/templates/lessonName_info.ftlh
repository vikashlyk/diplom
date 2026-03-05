<#import "blocks/template.ftlh" as t>
<@t.template>
    <h1>Создание предмета</h1><hr>
    <h4>Создание предмета</h4>
    <b>Название: </b>${lessonNames.name}<br>
    <hr>
    <form action="/lessonName/delete/${lessonNames.id}" method="post">
        <input type="submit" value="Удалить"/>
    </form>
</@t.template>