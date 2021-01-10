# Online store (Spring Boot)

## Description
Online store. The store provides a list of products that a registered customer can add to the cart, and then place an order. An unregistered customer does not have this option, but can view the products.

Administrator has the rights to:
- adding / deleting goods, changing product information;
- user lock / unlock / delete;
- transfer of the order from the status 'waiting approval' to 'approved' or cancel oreder

## Описание

Онлайн магазин. Магазин надає список товарів, які зареєстрований клієнт може додати в кошик, після чого оформити замовлення. Незареєстрований клієнт такої можливості не має, але може переглядати товари.

Адміністратор системи володіє правами:
- додавання/видалення товарів, зміни інформації про товар;
- блокування/розблокування користувача;
- переведення замовлення зі статусу 'зареєстрований' до 'оплачений' або 'скасований'

# Installation and running

## Requirements

* JDK 1.8
* Apache Tomcat
* Apache Maven
* MySQL

## Running the project

Clone project to your local repository

Run script from /resources/db/ folder to create database and tables
and to insert data (sql.sql)

Edit file application.properties from resources. (Replace with your credentials)
- spring.datasource.username=(your username)
- spring.datasource.password=(your password)

Run OnlineStoreSpringBootApplication main method.

Use http://localhost:8082/ to view website

# Default users

#### ADMIN
- Email: admin
- Password: admin

#### USER
- Email: user
- Password: user
