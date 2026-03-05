<#import "blocks/template.ftlh" as t>
<@t.template>
<#--<h1>ГРУППЫ</h1><hr>-->
<#--    <form action="/lessonName" method="post">-->
<#--        <input type="submit" value="Создать предмет"\>-->
<#--    </form>-->
<#--    <form action="/lesson" method="post">-->
<#--        <input type="submit" value="Добавить предмет"\>-->
<#--    </form>-->

    <h4>Оценки студентов:</h4>

<#--    <form action="/lessonName" method="get">-->
<#--        Поиск по номеру группы: <input type="number" name="number"><br>-->
<#--        <input type="submit" value="Поиск"/>-->
<#--    </form>-->
    <#list lessonMarks as lessonMark>
        <div>
                <#if lessonMark.student??>
                    ${lessonMark.student.lastName}
                    ${lessonMark.student.firstName}
                <#else>
                    -
                </#if> -
                ${lessonMark.lesson.lessonName.name} -
                ${lessonMark.lesson.lessonType} -
                ${lessonMark.mark}
                <a href="/lessonMark/${lessonMark.id}">Подробнее...</a>
<#--            </p>-->
        </div>
    </#list>
    <br>

    <hr>
    <h3>Поставить отметку</h3>
    <form action="/lessonMark/create" method="post">
        Предмет:
        <select name="lessonId" >
            <#list lessons as les>
                <option value="${les.id}"> ${les.lessonName.name} ${les.lessonType} - ${les.hours}ч. </option>
            </#list>
        </select>
        <br><br>
        Студент:
        <select name="studentId" >
            <#list students as stu>
                <option value="${stu.id}"> ${stu.lastName}  ${stu.firstName} ${stu.middleName}
                    <#if stu.group??>
                        ${stu.group.number}
                    </#if></option>
            </#list>
        </select>
        <br><br>
        Отметка: <input type="mark" name="mark"/><br><br>
        <input type="submit" value="Добавить"\>


    </form>
</@t.template>
