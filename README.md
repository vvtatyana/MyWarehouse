# MyWarehouse

Программа, реализующая тестовую задачу, заключающуюся в разработке серверной части для учета товаров на складе.
Код написан с использованием технологий Spring Framework, Swagger, Postgres.

## Запуск

Для запуска и использования необходимо в файле `src/main/resources/hibernate.properties` изменить данные

* `hibernate.connection.url` - JDBC URL
* `hibernate.connection.username` - имя пользователя
* `hibernate.connection.password` - пароль


## Входные/выходные данные
**Команды, обрабатываемые сервером**

| Название команды| URL-адрес      | Пример входного JSON-документа                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
|-----------------|----------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| _Поступление_     | `/api/admission` | <code> {<br>&nbsp;&nbsp;"number": "1", <br>&nbsp;&nbsp;"warehouse": { <br>&nbsp;&nbsp;&nbsp;&nbsp;"name": "Склад 1" <br>&nbsp;&nbsp;}, <br>&nbsp;&nbsp;"products": [ <br>&nbsp;&nbsp;{ <br>&nbsp;&nbsp;&nbsp;&nbsp;"article": "7A1FCTG44", <br>&nbsp;&nbsp;&nbsp;&nbsp;"name": "Холодильник", <br>&nbsp;&nbsp;&nbsp;&nbsp;"priceLastPurchase": "50000", <br>&nbsp;&nbsp;&nbsp;&nbsp;"priceLastSale": "50000" <br>&nbsp;&nbsp;&nbsp;&nbsp;}<br>&nbsp;&nbsp;]<br>}<code>                                                                                                       |
| _Продажа_         | `/api/sale`      | <code> {<br>&nbsp;&nbsp;"number": "1", <br>&nbsp;&nbsp;"warehouse": { <br>&nbsp;&nbsp;&nbsp;&nbsp;"name": "Склад 1" <br>&nbsp;&nbsp;}, <br>&nbsp;&nbsp;"products": [ <br>&nbsp;&nbsp;{ <br>&nbsp;&nbsp;&nbsp;&nbsp;"article": "7A1FCTG44", <br>&nbsp;&nbsp;&nbsp;&nbsp;"name": "Холодильник", <br>&nbsp;&nbsp;&nbsp;&nbsp;"priceLastPurchase": "50000", <br>&nbsp;&nbsp;&nbsp;&nbsp;"priceLastSale": "50000" <br>&nbsp;&nbsp;&nbsp;&nbsp;}<br>&nbsp;&nbsp;]<br>}<code>                                                                                                       |
| _Перемещение_     | `/api/moving`    | <code> {<br>&nbsp;&nbsp;"number": "1", <br>&nbsp;&nbsp;"warehouseOne": { <br>&nbsp;&nbsp;&nbsp;&nbsp;"name": "Склад 1" <br>&nbsp;&nbsp;}, <br>&nbsp;&nbsp;"warehouseTwo": { <br>&nbsp;&nbsp;&nbsp;&nbsp;"name": "Склад 2" <br>&nbsp;&nbsp;}, <br>&nbsp;&nbsp;"products": [ <br>&nbsp;&nbsp;{ <br>&nbsp;&nbsp;&nbsp;&nbsp;"article": "7A1FCTG44", <br>&nbsp;&nbsp;&nbsp;&nbsp;"name": "Холодильник", <br>&nbsp;&nbsp;&nbsp;&nbsp;"priceLastPurchase": "50000", <br>&nbsp;&nbsp;&nbsp;&nbsp;"priceLastSale": "50000" <br>&nbsp;&nbsp;&nbsp;&nbsp;}<br>&nbsp;&nbsp;]<br>}<code> |

**Результаты выполнения команд сервером**

| Код ответа | Название    | Описание                                                                                                                                             |
|------------|-------------|------------------------------------------------------------------------------------------------------------------------------------------------------|
| _200_        | OK          | Команда успешно выполнена. Сервер вернет список товаров на складе, на котором выполнялась команда.                                                   |
| _404_        | Not Found   | Пользователем введен запрос на работу с данными, которые отсутствуют в базе данных. В овете будет возвращено описание ошибки.                        |
| _400_        | Bad Request | Пользователь допустил ошибку в отправленном документе: не ввел данные или заполнил их пустым значением. В овете будет возвращено описание ошибки.    |