# MyWarehouse

Программа, реализующая тестовую задачу, заключающуюся в разработке серверной части для учета товаров на складе.
Код написан с использованием технологий Spring Framework, Swagger, Postgres.

## Запуск

Для запуска и использования необходимо создать базу данных в Postgres и в файле `src/main/resources/hibernate.properties` изменить данные:

* `hibernate.connection.url` - JDBC URL
* `hibernate.connection.username` - имя пользователя
* `hibernate.connection.password` - пароль

После запуска можно использовать Swagger UI для отправки документов на сервер и получения ответов:
http://localhost:8080/swagger-ui.html

## Входные/выходные данные
**Команды, обрабатываемые сервером**
<table>
<tr>
<td> Название конечной точки </td> <td> Название команды </td> <td> Метод запроса </td> <td> URL-адрес запроса</td> <td> Пример входного JSON-документа </td>
</tr>
<tr>
<td colspan="2"> Поступление </td>
<td> POST </td>
<td> /api/admission </td>
<td>

```json
{
  "number": "1",
  "warehouse": {
    "name": "Склад 1"
  },
  "products": [
    {
      "article": "7A1FCTG44",
      "name": "Холодильник",
      "priceLastPurchase": "50000",
      "priceLastSale": "50000"
    }
  ]
}
```
</td>
</tr>
<tr>
<td colspan="2"> Продажа </td>
<td> DELETE </td>
<td> /api/sale </td>
<td>

```json
{
  "number": "2",
  "warehouse": {
    "name": "Склад 1"
  },
  "products": [
    {
      "article": "7A1FCTG44",
      "name": "Холодильник",
      "priceLastPurchase": "50000",
      "priceLastSale": "50000"
    }
  ]
}
```
</td>
</tr>
<tr>
<td colspan="2"> Перемещение </td>
<td> PUT </td>
<td> /api/moving </td>
<td>

```json
{
  "number": "3",
  "warehouseOne": {
    "name": "Склад 1"
  },
  "warehouseTwo": {
    "name": "Склад 2"
  },
  "products": [
    {
      "article": "7A1FCTG44",
      "name": "Холодильник",
      "priceLastPurchase": "50000",
      "priceLastSale": "50000"
    }
  ]
}
```
</td>
</tr>
<tr>
<td rowspan="4"> Склад </td>
<td> Добавление данных нового склада </td>
<td> POST </td>
<td> /api/warehouse </td>
<td>

```json
{
  "name": "Склад 1"
}
```
</td>
</tr>
<tr>
<td> Изменение данных склада </td>
<td> PUT </td>
<td> /api/warehouse/{id} </td>
<td>

```json
{
  "name": "Склад 2"
}
```
</td>
</tr>
<tr>
<td> Удаление данных склада </td>
<td> DELETE </td>
<td> /api/warehouse/{id} </td>
<td>
</td>
</tr>
<tr>
<td> Просмотр данных склада </td>
<td> GET </td>
<td> /api/warehouse/{id} </td>
<td>
</td>
</tr>
<tr>
<td rowspan="4"> Товар </td>
<td> Добавление нового товара </td>
<td> POST </td>
<td> /api/product </td>
<td>

```json
{
  "nameWarehouse": "Склад 1",
  "productDTO": {
    "article": "7A1FCTG44",
    "name": "Холодильник",
    "priceLastPurchase": "50000",
    "priceLastSale": "50000"
  }
}
```
</td>
</tr>
<tr>
<td> Изменение данных товара </td>
<td> PUT </td>
<td> /api/product/{id} </td>
<td>

```json
{
  "nameWarehouse": "Склад 1",
  "productDTO": {
    "article": "7A1FCTG44",
    "name": "Холодильник",
    "priceLastPurchase": "100000",
    "priceLastSale": "100000"
  }
}
```
</td>
</tr>
<tr>
<td> Удаление товара </td>
<td> DELETE </td>
<td> /api/product/{id} </td>
<td>
</td>
</tr>
<tr>
<td> Просмотр товара </td>
<td> GET </td>
<td> /api/product/{id} </td>
<td>
</td>
</tr>
<tr>
<td rowspan="2"> Отчеты </td>
<td> Общий список товаров </td>
<td> GET </td>
<td> /api/report/generalListProducts </td>
<td>
Возвращает список всех товаров со всех складов. В качестве параметра можно передать название склада, для которого вернется список всех товаров.
</td>
</tr>
<tr>
<td> Остатки товаров на складе </td>
<td> GET </td>
<td> /api/report/remnantsGoodsInWarehouses </td>
<td>
Возвращает количество каждого товара на всех складах. В качестве параметра можно передать название товара, для которого вернется его количество на всех складах.
</td>
</tr>

</table>

**Результаты выполнения команд сервером**

| Код ответа | Название    | Описание                                                                                                                                          |
|------------|-------------|---------------------------------------------------------------------------------------------------------------------------------------------------|
| _200_        | OK          | Команда успешно выполнена.                                                                                                                        |
| _400_        | Bad Request | Пользователь допустил ошибку в отправленном документе: не ввел данные или заполнил их пустым значением. В овете будет возвращено описание ошибки. |
| _404_        | Not Found   | Пользователем введен запрос на работу с данными, которые отсутствуют в базе данных. В овете будет возвращено описание ошибки.                     |
| _500_        | Internal Server Error   | Произошла ошибка на стороне сервера. В овете будет возвращено описание ошибки.                                                                    |

