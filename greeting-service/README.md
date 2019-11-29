# Greeting Service

This project exposes a greeting service on HTTP GET, POST and PUT methods.

## Building the project
You can build the project using maven. Run the following command in command line.

```
$ mvn clean install
```
This should create the required executables files.

## Starting Greeting service

```
$ java -jar ./target/greeting-service-<SERVICE_VERSION>.jar
```

OR

You can start the service using Spring Boot maven plugin by running below command. You can append `-Dserver.port=9000` argument to start the service on different port.

```
mvn spring-boot:run
```

The service tries to register it self with eureka server running `http://localhost:8761/eureka/`. 

## Service Endpoints

```
GET
http://<hostname>:<port>/greeting?name=Foo

POST
http://<hostname>:<port>/greeting
Request:
{"content":"Foo!"}

PUT
http://<hostname>:<port>/greeting
Request:
{"content":"Foo!"}
```





 


 
