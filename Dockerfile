FROM openjdk:11-jdk-slim-buster
VOLUME /tmp
COPY target/Pjoias-*.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]