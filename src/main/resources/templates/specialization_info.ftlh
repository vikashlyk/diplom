<#import "blocks/template.ftlh" as t>
<@t.template>

    <h1>Специальность</h1><hr>

    <b>Название специальности: </b>${specialization.specialtyName}<br>
    <b>Код специальности: </b>${specialization.specialtyCode}<br>
    <b>Название специализации/направления: </b>${specialization.directionName}<br>
    <b>Код специализации/направления: </b>${specialization.specialtyIndex}<br>
    <hr>
    <form action="/specialization/delete/${specialization.id}" method="post">
        <input type="submit" value="Удалить"\>
    </form>

</@t.template>
