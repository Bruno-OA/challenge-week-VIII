FROM amazoncorretto:17-alpine3.17-jdk
WORKDIR /app
COPY .mvn/ ./mvn
COPY mvnw pom.xml ./
COPY src ./src
COPY ./target/auth-and-auto-0.0.1-SNAPSHOT.jar auth-and-auto-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","auth-and-auto-0.0.1-SNAPSHOT.jar"]
