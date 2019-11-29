# Gateway Service

This project starts a discovery server built using Spring Cloud Gateway project. Features implemented in Gateway service is as follows.
- Routing services based on base url.
- Hystrix fallback command for all routes.
- Global CORS configuration for all routes.
- Global Auth filter for all requests sent to gateway.   



## Building the project
You can build the project using maven. Run the following command in command line.

```
$ mvn clean install
```
This should create the required executables files.

## Starting gateway service

```
$ java -jar ./target/gateway-0.0.1-SNAPSHOT.jar
```

This will start gateway service on port 8070. The service tries to register it self with eureka server running `http://localhost:8761/eureka/`. 

This project already has some dummy routes configured. You can add additional routes by updating the `/resources/application.yml` file. The `routes` entry is a list and new route can be added as follow.

`src/main/resources/application.yml`

```yml
      routes:
      - id: httpbin-service
        uri: ${user.httpBinUri}
        predicates:
        - Path=/get**
        filters:
        - AddRequestHeader=Hello, World
        - name: Hystrix
          args:
            name: httpbin-service
            fallbackUri: forward:/fallback  
            
      - id: httpbin-delay-service
        uri: http://httpbin.org
        predicates:
        - Path=/delay/**
        filters:
        - name: Hystrix
          args:
            name: httpBin-service-fallback
            fallbackUri: forward:/fallback  
```

Read more about configuring routes in application.yml files @ `https://cloud.spring.io/spring-cloud-gateway/reference/html/#gateway-how-it-works`

## Testing the service
Call the http bin service by calling using below url.

```
$ http://localhost:8070/get
Response:
{
  "args": {}, 
  "headers": {
    "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3", 
    "Accept-Encoding": "gzip, deflate, br", 
    "Accept-Language": "en-US,en;q=0.9", 
    "Cache-Control": "max-age=0", 
    "Cookie": "JSESSIONID=4B03BA521574D60E64C762B5F448F06C", 
    "Forwarded": "proto=http;host=\"localhost:8070\";for=\"0:0:0:0:0:0:0:1:52902\"", 
    "Host": "httpbin.org", 
    "Sec-Fetch-Mode": "navigate", 
    "Sec-Fetch-Site": "none", 
    "Sec-Fetch-User": "?1", 
    "Upgrade-Insecure-Requests": "1", 
    "User-Agent": "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36", 
    "X-Forwarded-Host": "localhost:8070"
  }, 
  "origin": "0:0:0:0:0:0:0:1, 49.248.151.170, ::1", 
  "url": "https://localhost:8070/get"
}
```

Call the http bin delay service to test out the hystrix fallback implementation. The call to delay service fallbacks to the hystrix command and returns a fallback response.

```
$ http://localhost:8070/delay/3
Response:
fallback
```


 


 
