<#import "blocks/template.ftlh" as t>
<@t.template>

    <h1>Оценка студентов</h1><hr>
    <b>Имя студента: </b>
    <#if lessonMark.student??>
        ${lessonMark.student.lastName}
        ${lessonMark.student.firstName}
        ${lessonMark.student.middleName}
        ${lessonMark.student.group.number} <br>
    <#else>
        - <br>
    </#if>
    <b>Номер группы: </b>
    <#if lessonMark.student??>
        ${lessonMark.student.group.number} <br>
    <#else>
        - <br>
    </#if>
    <b>Название предмета: </b>
    <#if lessonMark.lesson??>
        ${lessonMark.lesson.lessonName.name} <br>
    <#else>
        - <br>
    </#if>
    <b>Отметка: </b>
    ${lessonMark.mark} <br>
    <hr>
    <form action="/lessonMark/delete/${lessonMark.id}" method="post">
        <input type="submit" value="Удалить"\>
    </form>
</@t.template>