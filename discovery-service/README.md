# Discovery Service

This project starts a discovery server built using Spring Cloud Netflix Eureka project.

## Building the project
You can build the project using maven. Run the following command in command line.

```
$ mvn clean install
```
This should create the required executables files.

## Starting eureka server

```
$ java -jar ./target/discovery-service-0.0.1-SNAPSHOT.jar
```

This will start Eureka server on port 8761. You can access the Eureka dashboard by opening below url in browser.

`http://localhost:8761`


 
