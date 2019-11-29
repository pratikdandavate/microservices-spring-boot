# Spring Boot, MYSQL, OAuth Resource Server API

Build Restful CRUD API for a simple Organ-Slide service application using Spring Boot, Mysql, JPA and Hibernate.

## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

3. Mysql - 5.x.x

## Steps to Setup

**1. Clone the application**

```bash
git clone git://aiicsv02/microservices-poc.git
```
**2. Go to the application**

```
cd slide-service
```

**3. Create Mysql database**

```bash
create database microservices
```

**4. Change mysql username and password as per your installation**

+ open `src/main/resources/application.yml`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**5. Build and run the app using maven**

```bash
mvn package
java -jar target/slide-service-1.0.0-SNAPSHOT.jar
```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8082>.

## Explore Rest APIs

For slide-service defines following CRUD APIs.

    GET /api/organs/{organId}/slides
    
    POST /api/organs{organId}/slides
    
    GET /api/organs/{organId}/{slideId}
    
    PUT /api/organs/{organId}/{slideId}
    
    DELETE /api/organs/{organId}/{slideId}

You can test them using postman or any other rest client.

