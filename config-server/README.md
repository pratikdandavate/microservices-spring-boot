# Configuration Server

This project starts a configuration server built using Spring Cloud project. Features implemented in service is as follows.
- Centralized mechanism (service) to maintain and manage application related properties for all services. 
- If there is any property change, only the centralized server (or service) will update the change 
  and all the related service will received the updated property without restarting themselves.


## Building the project
You can build the project using maven. Run the following command in command line.

```
$ mvn clean install
```
This should create the required executables files.

## Starting Configuration server

```
$ java -jar ./target/config-server-0.0.1-SNAPSHOT.jar
```

This will start config server on port 8888 `http://localhost:8888`. 

## Creating Configuration client

The following dependencies should be added when creating the Config-client service.

```
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-config</artifactId>
</dependency>

<dependency>
   <groupId>org.springframework.cloud</groupId>
   <artifactId>spring-cloud-starter-bus-amqp</artifactId>
</dependency>

<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

#Create a bootstrap.properties file and add the following configurations

```
spring.application.name={application-name}
spring.cloud.config.uri=http://localhost:8888
```

# Spring Cloud Config Server exposes the following REST endpoints:

```
GET /{application}/{profile}[/{label}]
GET /{application}-{profile}.yml
GET /{label}/{application}-{profile}.yml
GET /{application}-{profile}.properties
GET /{label}/{application}-{profile}.properties
```

The refresh event is triggered by invoking the endpoint  `/actuator/bus-refresh` on any of config client and it will broadcast event to all connected clients.



 


 
