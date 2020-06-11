# Game-Shop [![CircleCI](https://circleci.com/gh/AdrianSad/Game-Shop.svg?style=svg&circle-token=4dd285f47055803028fc8f1f90f70cb50c89820c)](<LINK>)

Simple game shop where u can do CRUD operations on games and its companies. Application is secured by Spring Security, for example you need to log in as Admin to delete a product.

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

The h2 console is automatically exposed at http://localhost:8080/h2-console and it is possible to inspect the content of the database using the jdbc:h2:mem:testdb url.


or

```
spring.profiles.active=mysql
database=mysql
```

For mysql profile you will need MySQL Database which you can download here : https://dev.mysql.com/downloads/

1. Download and install the MySQL database
2. Set up application.properties-mysql settings
3. Run mysql-configure.sql inside MySQL workbench
4. Run the app with `spring.profiles.active=mysql`

### Admin

login : 
`admin@example.com`

password :
`password`

Permissions : 

- View all games and companies
- Add games and companies
- Remove all games and companies
- Update all games and companies

### Customer

login : 
`jan@example.com`

password :
`password`

Permissions : 

- View game and company
- Add games
- Remove own game
- Update own game

## Technologies : 

**Controller :** Spring MVC

**Repository :** Spring Data JPA

**ORM :** Hibernate

**Access-control  :** Spring Security

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

## Functionality overview

`http://localhost:8080/`
![Game List](/img/mainPage.png)

`http://localhost:8080/games`

![Game List](/img/login.png)

`http://localhost:8080/login`

![Game List](/img/register.png)

`http://localhost:8080/register`

![Game List](/img/games.png)

`http://localhost:8080/games/1/show`

![Game detail](/img/games_1_show.png)

`http://localhost:8080/games/new`

![Game detail](/img/games_new.png)

`http://localhost:8080/companies`

![Company List](/img/companies.png)

`http://localhost:8080/companies/3/show`

![Company games detail](/img/companies_3_show.png)




