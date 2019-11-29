#1. Installing Zipkin
Run the first docker command to pull the Zipkin image from `hub.docker.com` and then the next docker command to start it on port `9411`.

```
$ docker pull openzipkin/zipkin
$ docker run -d -p 9411:9411 openzipkin/zipkin
```

Validate the setup by accessing the Zipkin web interface on the url: <http://localhost:9411/zipkin/>


#2. Installing ELK Stack

Execute the below docker commands to build the image with tag `local-elk` and start all three components.

```
$ docker build . --tag local-elk
$ docker run --net=host -p 5601:5601 -p 9200:9200 -p 5044:5044 -it --name elk local-elk
```

The `docker run`, command starts up Kibana on port `5601`, ElasticSearch on port `9200` and LogStash on port `5044`.
Validate the kibana setup by accessing the web console on url <http://localhost:5601>



**To enable Sleuth, Zipkin and ELK stack, we need to make the below changes on all microservices.**

```
 <!-- Dependencies for Zipkin and Sleuth -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-zipkin</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-sleuth</artifactId>
    </dependency>
       
   <!-- Dependencies for LogStash -->
   <dependency>
        <groupId>net.logstash.logback</groupId>
        <artifactId>logstash-logback-encoder</artifactId>
        <version>5.3</version>
    </dependency>
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-core</artifactId>
    </dependency>
    
```

**The second change is to add the URL, in the application.properties for spring to publish data to Zipkin.**

```
# Zipkin info
spring.zipkin.base-url=http://localhost:9411/
spring.sleuth.sampler.probability=1
```

The final change is the `logback.xml`, to publish the logs to LogStash. The appender publishes all the logs to Logstash running on port 5000, using an Async TCP Appender
