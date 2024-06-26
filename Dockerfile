FROM maven AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-jammy
COPY --from=build /target/journey_genie-0.0.1-SNAPSHOT.jar journey_genie.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","journey_genie.jar"]