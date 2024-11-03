FROM maven:latest AS build

WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn dependency:go-offline

CMD ["mvn", "spring-boot:run"]
