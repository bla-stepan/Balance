# API для Интернет-банка
Rest API по работе с банковским счетом. Это API будет использовать банкомат, веб-приложение или мобильное приложение Интернет-банка. 
<h3>Будут доступны следующие операции:</h3>

Узнать баланс по ID пользователя;

Снятие заданной суммы с баланса пользователя;

Пополнение баланса на заданную сумму;

Отобразить список операций за выбранный период;

Перевести заданную сумму другому пользователю.(не реализовано)

Ответ выдается в виде JSON.

# Описание работ к проекту Balance
<h2>Этап 1</h2>
<h3>Шаг 1. Спроектировать базу данных.</h3>

Создан класс сущности Balance c параметрами userId (первичный ключ), currentBalance.

<h3>Шаг 2. Создать класс по работе с базой данных.</h3>

Создан класс BalanseServise с методами: Seve, getBalance, putMoneу, takeMoney.

* Создан файл application.properties с настройками подключения к базе данных (название БД, IP-адрес, логин и пароль).</p>

<h3>Шаг 3. Rest API</h3>
Создан интерфейс BalanceRepository. Создан BalanceController реализующий Rest API для операции save, getBalance, putMoney и takeMoney.
<h2>Этап 2</h2>
<h3>Шаг 1. Создание таблицы с операциями</h3>
Создан класс сущности Operation с полями id (int), userId (int), operationType (int), operationAmount (int), operationDate (LocalDate).
<h3>Шаг 2. Добавление класса по работе с базой данных</h3>
Доработаны методы putMoney и takeMoney в классе BalanseController - добавлен функционал сохранения информации об операции. создан класс OperationService с методами setOperation, getOperationById, getAllOperation, getOperationList. создан интерфейс OperationRepository. 
getOperationList принимает ID пользователя и 2 даты и возвращает список операций за выбранный диапазон времени.
В интерфейсе OperationRepository добавлены методы spring findAllByOperationDateBetweenAndAndUserId, принимающий две даты и идентификатор пользлователя, findAllByUserId, принимающий идентификатор пользователя.
Предусмотрена возможность - одно или оба значения диапазона дат могут быть пустыми. В этом случае выдаются все операции без фильтрации по дате.
<h3>Шаг 3. Rest API</h3>
Создан OperationController реализующий Rest API для операции getOperationById, getAllOperation, getOperationList.
<h2>Этап 3</h2>
<h3>Шаг 1. Добавление таблицы с трансферами</h3>
Создан класс сущности Trancfer, с полями id, senderId, recipientId, transferDate, transferAmount. Создан интерфейс TransferRepository.
<h3>Шаг 2. Доработка класс по работе с базой данных</h3>
Создан класс TransferService с методами setTransfer, getTransferById, getAllTransfer.
transferMoney, которая принимает ID первого и второго пользователя, а также сумму для перевода. Предусмотрена ситуация недостатка средств на счете пользователя.
<h3>Шаг 3. Добавление операции transferMoney в Rest API</h3>
Создан класс TransferController сметодом /transferMone, yкоторый принимает ID первого и второго пользователя, а также сумму для перевода. Предусмотрена ситуация недостатка средств на счете пользователя.
