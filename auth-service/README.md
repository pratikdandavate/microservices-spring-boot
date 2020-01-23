# Authentication/Authorization Service

This project provides service for authenticating and authorizing a user. It exposes a signup service which registers a user. It provides a login service that authenticates a user and provides a token in response for authorizing further requests.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Maven and the JDK8 build.

Be sure that your JAVA_HOME environment variable points to the jdk1.8.0 folder extracted from the JDK download.

### Installing

Running below command will start the application on port 8080.

```
$ java -jar ./target/auth-service-<SERVICE_VERSION>.jar
```

OR

```
mvn spring-boot:run
```

## Running the tests


```
mvn test
```


## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Acknowledgments

* https://www.callicoder.com/spring-boot-spring-security-jwt-mysql-react-app-part-1/
* https://docs.spring.io/spring-security/site/docs/current/reference/html5/
