Тестовое задание для "21 Век"
==============

Описание задания:
--------------

1. Сделать 3 сущности «Заказ» (Order), «Строка Заказа» (OrderLine) и «Товар» (Goods) которые сохраняются в базу данных
------

2. Реализовать методы:

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
  
------  
3. Приложение должно быть разделено по слоям DAO, Service, Controller

- для DAO используется JUnit, Spring, Spring Data JPA и база данных H2
- для Service используется JUnit, Spring, Mockito (не обращается к базе, использует Mock объекты)
- для Controller используется JUnit, Spring (не обращается к Service, использует MockMVC объекты)


4. Реализовать вывод на клиента:
  
  - журнала заказов и справочника товаров в двух вкладках (сделать переключения между журналами через вкладки)
  - возможность добавления, изменения, удаления и редактирования заказа и справочника товаров

------
Запуск проекта
--------------

1. Inrelij IDEA -> Settings -> Build, Execution, Deployment -> Compiler -> Annotation Processors -> ***Enabled Annotation Processing***
2. Inrelij IDEA -> Settings -> Plugins -> ***Lomboc*** *to establish*
3. Запуск проекта: com/tradeservice/Main
4. API Calls -> [Trade Service](https://documenter.getpostman.com/view/8128788/SVYusHqh) (Installed PostMan required)


*In the process...*
------
Запуск UI: 

    cd webapp/angular-app
    ng serve --open
