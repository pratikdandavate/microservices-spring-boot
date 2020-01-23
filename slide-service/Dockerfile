# # Start with a base image containing Java runtime
# FROM openjdk:8-jdk-alpine

# # Add Maintainer Info
# LABEL maintainer="pratik.dandavate@gmail.com"

# # Add a volume pointing to /tmp
# VOLUME /tmp

# # Make port 8080 available to the world outside this container
# # EXPOSE 8080

# # The application's jar file
# ARG JAR_FILE=/target/slide-service-0.0.1-SNAPSHOT.jar

# # Add the application's jar to the container
# ADD ${JAR_FILE} /slide-service-0.0.1-SNAPSHOT.jar

# # Run the jar file 
# ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/slide-service-0.0.1-SNAPSHOT.jar"]
FROM openjdk:8-jdk-alpine
ADD ["target/slide-service-0.0.1-SNAPSHOT.jar", "app.jar"]
EXPOSE 8082 8787
ENV JAVA_OPTS="-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8787,suspend=n"
# HEALTHCHECK --interval=30s --timeout=30s CMD curl -f http://localhost:8888/actuator/health || exit 1
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -jar /app.jar" ]
 