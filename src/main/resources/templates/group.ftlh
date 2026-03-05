<#import "blocks/template.ftlh" as t>
<@t.template>

    <#--<h1>ГРУППЫ</h1><hr>-->
    <h4>Группы</h4>

    <#--поиск-->
    <form action="/group" method="get">
        Поиск по номеру группы: <input type="number" name="number">
        <input type="submit" value="Поиск"/>
    </form>
    <br>
    <#list groups as group>
        <div>
            <p><b>${group.number}</b> -
                <#if group.specialization??>
                    ${group.specialization.specialtyName}
                <#else>
                    ...
                </#if> -
                <b>${group.faculty.name}</b>
                <a href="/group/${group.id}">Подробнее...</a>
            </p>
        </div>
    </#list>

    <hr>
    <h3>Создать новую группу</h3>
    <form action="/group/create" method="post">
        Номер группы: <input type="number" name="number"/><br><br>
        Специальность:
            <select name="specializationId" >
                <#list specializations as spec>
                <option value="${spec.id}"> ${spec.specialtyName} </option>
                </#list>
            </select>
    <#--    <input type="text" name="specialization"/><br><br>-->
        <br><br>
        Факультет:
            <select name="facultyId" >
                <#list faculties as spec>
                    <option value="${spec.id}"> ${spec.name}<br> </option>
                </#list>
            </select>
        <br><br>
        <input type="submit" value="Добавить"\><br>

    </form>
</@t.template>
<#--    </body>-->
<#--    </html>-->