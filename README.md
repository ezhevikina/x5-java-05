# java-05
##Домашнее задание

Реализовать DAO паттерн для сущности Account и операций снятия, внесения наличных, 
перевода денежных средств и проверки баланса. В качестве источника хранения данных 
использовать JSON и базу данных.

Для запуска БД atm выполнить
```
docker run --name atmdb -it --rm -p 15432:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=atm postgres:11
```