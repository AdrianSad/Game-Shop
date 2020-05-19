# Game-Shop [![CircleCI](https://circleci.com/gh/AdrianSad/Game-Shop.svg?style=svg&circle-token=4dd285f47055803028fc8f1f90f70cb50c89820c)](<LINK>)

Simple game shop application where u can do CRUD operations on games and its companies.

## Running Game Shop
```
git clone https://github.com/AdrianSad/Game-Shop.git
cd game-shop
./mvnw package
java -jar target/*.jar
```
## Get Started

You can access game shop here: http://localhost:8080/

In application.properties set the profile and database : 
```
spring.profiles.active=h2
database=h2
```

or

```
spring.profiles.active=mysql
database=mysql
```

For mysql profile you will need MySQL Database which you can download here : https://dev.mysql.com/downloads/

1. Download and install the MySQL database
2. Set up application.properties-mysql settings
3. Run the app with `spring.profiles.active=mysql`

## Technologies : 

**Controller :** Spring MVC

**Repository :** Spring Data JPA

**ORM :** Hibernate


**Web layer :**

- Thymeleaf
- Bootstrap
- Webjars


**Databases :**

- H2 in memory database
- MySQL


**Tests :** 
- Mockito 
- JUnit 
