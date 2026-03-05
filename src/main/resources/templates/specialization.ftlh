<#import "blocks/template.ftlh" as t>
<@t.template>


    <#--<h1>СПЕЦИАЛЬНОСТИ</h1><hr>-->
    <h4>Специальности</h4>

    <#--поиск-->
    <form action="/specialization" method="get">
        Поиск по названию специальности: <input type="text" name="specialtyName">
        <input type="submit" value="Найти"/>
    </form>
    <br>
    <#list specializations as specialization>
        <div>
            <p><b>${specialization.specialtyCode}</b> ${specialization.specialtyName} <a href="/specialization/${specialization.id}">Подробнее...</a> </p>
        </div>
    </#list>
    <hr>
    <h3>Создать новую специальность</h3>
    <form action="/specialization/create" method="post">
        Специальность: <input type="text" name="specialtyName"/><br><br>
        Код специальности: <input type="text" name="specialtyCode"/><br><br>
        Специализация/направление: <input type="text" name="directionName"/><br><br>
        Код специализации/направления: <input type="text" name="specialtyIndex"/><br><br>
        <input type="submit" value="Добавить"\>

    </form>
</@t.template>
<#--</body>-->
<#--</html>-->