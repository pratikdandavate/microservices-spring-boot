FROM openjdk:8-jdk-alpine
ADD ["target/config-server-0.0.1-SNAPSHOT.jar", "app.jar"]
EXPOSE 8888 8787
ENV JAVA_OPTS="-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8787,suspend=n"
HEALTHCHECK --interval=30s --timeout=30s CMD curl -f http://localhost:8888/actuator/health || exit 1
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=native,docker -jar /app.jar" ]
 