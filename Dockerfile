FROM openjdk:11 as build
VOLUME /tmp
WORKDIR /workspace/app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN chmod +x mvnw && ./mvnw install -DskipTests

FROM openjdk:11
COPY --from=build /workspace/app/target/*.jar /
ENTRYPOINT ["java", "-jar", "/marvel-api.jar"]