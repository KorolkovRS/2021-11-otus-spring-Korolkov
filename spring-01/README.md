# Приложение для тестирования студентов
### Цель
Создать приложение с помощью Spring IoC, чтобы познакомиться с основной функциональностью IoC, на которой строится весь Spring.
### Результат 
Простое приложение, сконфигурированное XML-контекстом.

### Описание задания

В ресурсах хранятся вопросы и различные ответы к ним в виде CSV файла (5 вопросов).
Вопросы могут быть с выбором из нескольких вариантов или со свободным ответом - на Ваше желание и усмотрение.
Приложение должна просто вывести вопросы теста из CSV-файла с возможными вариантами ответа.

### Выполненые требования:
- В приложении должна присутствовать объектная модель (отдаём предпочтение объектам и классам, а не строчкам и массивам/спискам строчек)  ✅
- Все классы в приложении должны решать строго определённую задачу  ✅
- Контекст описывается XML-файлом ✅
- Все зависимости должны быть настроены в IoC контейнере  ✅
- Имя ресурса с вопросами (CSV-файла) необходимо захардкодить строчкой в XML-файле с контекстом ✅
- CSV с вопросами читается именно как ресурс, а не как файл ✅
- Весь ввод-вывод осуществляется на английском языке  ✅
- Написать юнит-тест какого-нибудь сервиса (оцениваться будет только попытка написать тест) ✅
- Приложение должно корректно запускаться с помощью "java -jar" ✅