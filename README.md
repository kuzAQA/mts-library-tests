# Проект Сервиса библиотеки

### Описание проекта
Данный проект позволяет работать с несколькими связанными таблицами:
- таблица с данными о пользователях библиотеки,
- таблица с информацией об авторах,
- таблица с информацией о книгах, которые имеются в библиотеке.
Каждая книга связана с автором, также содержится информация о пользователе, который в данный момент читает выбранную книгу. 
  
С помощью данного сервиса Вы можете получить списки с информацией, которая хранится в базе данных через get запросы с использованием параметров:
- информация о книгах, которые читает выбранный пользователь;
- информация обо всех книгах автора, которые записаны в базе данных библиотеки;
- информация обо всех пользователях библиотеки, которые читают книги выбранного автора.
  
Имеется возможность отправить по сети и записать данные в БД через post запросы:
- информация о новых авторах;
- информация о новых книгах;
- информация о новых пользователях библиотеки.

### Используемые технологии

  - Maven
  - Spring 
  - MySql
  - JUnit
  ```
  Вы можете скопировать базу данных для тестовой проверки сервиса с помощью файлов 
  *.sql в папке "sqlDunp" для работы с базой данных MySql
  ```

### Описание работы с проектом

Пример post запроса для сохранения новой книги в базе данных:
```
POST http://localhost:8080/library/books/save
Content-Type: application/json

{
  "bookTitle": "Два гусара",
  "author": {
    "id": 2
  }
}
```

Пример post запроса для сохранения нового автора в базе данных:
```
POST http://localhost:8080/library/authors/save
Content-Type: application/json

{
  "firstName": "Николай",
  "secondName": "Гумилев"
}
```
Пример post запроса для сохранения нового пользователя в базе данных:
```
POST http://localhost:8080/library/users/save
Content-Type: application/json

{
  "firstName": "Андрей",
  "secondName": "Пухов"
}
```
Пример post запроса для закрепления книги за пользователем:
```
POST http://localhost:8080/library/users/1/getBooks
Content-Type: application/json

"{"booksId": [1]}"
```
Пример post запроса для сдачи книги:
```
POST http://localhost:8080/library/books/return
Content-Type: application/json

"{"booksId": [1]}"
```
Пример get запроса для получения всех книг, которые читает выбранный пользователь 
```
GET http://localhost:8080/library/users/1/books
```

Пример get запроса для получения всех книг автора
```
GET http://localhost:8080/library/authors/2/books
```

Пример get запроса для получения пользователей, которые читают выбранного автора
```
GET http://localhost:8080/library/authors/2/users"
```


## Автор проекта
Кряжова Светлана Юрьевна
