FROM amazoncorretto:17-alpine3.17-jdk
WORKDIR /app
COPY .mvn/ ./mvn
COPY mvnw pom.xml ./
COPY src ./src
COPY ./target/e-0.0.1-SNAPSHOT.jar e-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","e-0.0.1-SNAPSHOT.jar"]
