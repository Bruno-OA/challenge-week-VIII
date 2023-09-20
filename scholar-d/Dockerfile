FROM amazoncorretto:17-alpine3.17-jdk
WORKDIR /app
COPY .mvn/ ./mvn
COPY mvnw pom.xml ./
COPY src ./src
COPY ./target/ms-scholar-d-0.0.1-SNAPSHOT.jar ms-scholar-d-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","scholar-d-0.0.1-SNAPSHOT.jar"]
