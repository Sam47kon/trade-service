Тестовое задание для "21 Век"
==============

Описание задания:
--------------

<h4>1. Сделать 3 сущности «Заказ» (Order), «Строка Заказа» (OrderLine) и «Товар» (Goods) которые сохраняются в базу данных</h4>

<h4>2. Реализовать методы:</h4>

- добавление нового заказа
- изменение существующего заказа
- удаление заказа
- получение всех заказов
- получение определенного заказа по id
- добавление нового товара
- изменение существующего товара
- удаление товара
- получение всех товаров
- получение определенного товара по id
  
<h4>3. Приложение должно быть разделено по слоям DAO, Service, Controller:</h4>

- для DAO используется JUnit, Spring, Spring Data JPA и база данных H2
- для Service используется JUnit, Spring, Mockito (не обращается к базе, использует Mock объекты)
- для Controller используется JUnit, Spring (не обращается к Service, использует MockMVC объекты)

<h4>4. Реализовать вывод на клиента:</h4>
  
- журнала заказов и справочника товаров в двух вкладках (сделать переключения между журналами через вкладки)
- возможность добавления, изменения, удаления и редактирования заказа и справочника товаров

Запуск проекта
--------------

1. Включить обработку аннотаций:  Inrelij IDEA -> Settings -> Build, Execution, Deployment -> Compiler -> Annotation Processors -> ***Enabled Annotation Processing***
2. Установить Lomboc плагин: Inrelij IDEA -> Settings -> Plugins -> ***Lomboc*** *Установить*
3. Запуск проекта: Gradle -> Tasks -> application -> bootRun
4. API Calls -> [Примеры запросов к REST API](https://documenter.getpostman.com/view/8128788/SVYusHqh) (Необходим PostMan)
5. Запуск UI: 

    cd webapp/angular-app
    npm install
    npm install -g "@angular/cli"
    ng serve -o --base-href /trade-service/
