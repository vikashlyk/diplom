<#import "blocks/template.ftlh" as t>
<@t.template>

    <h4>Факультеты</h4>

    <#list faculties as faculty>
        <div>
            <p><b>${faculty.dean}</b> ${faculty.secretary} ${faculty.rector} ${faculty.name} <a href="/faculty/${faculty.id}">Подробнее...</a> </p>
        </div>
    </#list>
    <hr>
    <h3>Создать новый факультет</h3>
    <form action="/faculty/create" method="post">
        Абривиатура факультета: <input type="text" name="name"/><br><br>
        И.О. Фамилия декана: <input type="text" name="dean"/><br><br>
        И.О. Фамилия секреторя факультета: <input type="text" name="secretary"/><br><br>
        И.О. Фамилия руководителя учреждения: <input type="text" name="rector"/><br><br>
        <input type="submit" value="Добавить"\>

    </form>
</@t.template>
<#--</body>-->
<#--</html>-->