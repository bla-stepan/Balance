# Balance
Описание функциональности к проекту Balance

Шаг 1. Спроектировать базу данных 
Создан класс сущности Balance c параметрами userId (первичный ключ), currentBalance

Шаг 2. Создать класс по работе с базой данных
Создан класс BalanseServise с методами: Seve, getBalance, putMoneу, takeMoney.

* Создан файл application.properties с настройками подключения к базе данных (название БД, IP-адрес, логин и пароль).

Шаг 3. Создайте Rest API
Создан интерфейс BalanceRepository
Создан BalanceController реализующий Rest API для операции save, getBalance, putMoney и takeMoney.
