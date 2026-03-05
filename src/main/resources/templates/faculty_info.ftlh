<#import "blocks/template.ftlh" as t>
<@t.template>
<#--    <!DOCTYPE html>-->
<#--    <html>-->
<#--    <head>-->
<#--        <title>Факультет</title>-->
<#--    </head>-->
<#--    <body>-->
    <h1>Факультет</h1><hr>
    <h4>Подробная информация о факультете</h4>
    <b>Название факультета: </b>${faculty.name}<br>
    <b>Декан: </b>${faculty.dean}<br>
    <b>Секретарь: </b>${faculty.secretary}<br>
    <b>Ректор: </b>${faculty.rector}<br>
    <hr>
    <form action="/faculty/delete/${faculty.id}" method="post">
        <input type="submit" value="Удалить"/>
    </form>
</@t.template>
<#--    </body>-->
<#--    </html>-->