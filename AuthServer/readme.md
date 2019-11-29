# Spring Boot Authorization Server API

Build Restful Authorization Server with JDBC as a Datasource.

## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

3. Mysql - 5.x.x

4. Spring-cloud - Greenwich.RELEASE

## Steps to Setup

**1. Clone the application**

```bash
git clone git://aiicsv02/microservices-poc.git
```
**2. Go to the application**

```
cd AuthServer
```

**2. Create Mysql database**

```bash
create database oauthDB
```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.yml`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Change database configuration**

+ rename `src/main/resources/data.txt` to `src/main/resources/data.txt` to insert user with 
  specified roles to the database
  
+ Note :- Once user added to the Database rename `src/main/resources/data.txt` to avoid Duplication of Same Record.


**4. Run the app using maven**

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:9191>.

## Explore Rest APIs

The Auth-server defines following APIs.

    POST /oauth/token 
    
    GET /oauth/check_token 
    
You can test them using postman or any other rest client.

