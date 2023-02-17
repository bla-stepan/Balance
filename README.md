# Balance
# Описание функциональности к проекту Balance

<h3>Шаг 1. Спроектировать базу данных.</h3>

<p>Создан класс сущности Balance c параметрами userId (первичный ключ), currentBalance.</p>

<h3>Шаг 2. Создать класс по работе с базой данных.</h3>

<p>Создан класс BalanseServise с методами: Seve, getBalance, putMoneу, takeMoney.

* Создан файл application.properties с настройками подключения к базе данных (название БД, IP-адрес, логин и пароль).</p>

<h3>Шаг 3. Создайте Rest API</h3>

<p>Создан интерфейс BalanceRepository. Создан BalanceController реализующий Rest API для операции save, getBalance, putMoney и takeMoney.</p>
