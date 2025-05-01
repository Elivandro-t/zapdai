FROM ubuntu:latest as build

RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven

WORKDIR /dr7

COPY pom.xml .

COPY . .

RUN mvn clean package

# Estágio de produção
FROM openjdk:17-jdk-slim

WORKDIR /dr7

COPY --from=build /dr7/target/dr7-0.0.1-SNAPSHOT.jar dr7.jar
EXPOSE 8080
ENV DATA_DIR=/var/lib/data
CMD ["java", "-jar", "dr7.jar"]
