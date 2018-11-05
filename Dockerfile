#FROM openjdk:10
FROM openjdk:10-jre-slim
MAINTAINER David Stevenson <david.35472@gmail.com>

ARG JAR_FILE=target/basic-microservice-0.0.1-SNAPSHOT-jar-with-dependencies.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Xms16m", "-Xmx32m","-cp", "/app.jar", "com.stevenson.basicmicroservice.Application"]
CMD ["alphabet.*", "A"]
