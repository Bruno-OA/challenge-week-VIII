FROM amazoncorretto:17-alpine3.17-jdk
WORKDIR /app
COPY .mvn/ ./mvn
COPY mvnw pom.xml ./
COPY src ./src
COPY ./target/b-0.0.1-SNAPSHOT.jar b-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","b-0.0.1-SNAPSHOT.jar"]
