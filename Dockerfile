FROM openjdk:17-alpine3.14 as build

COPY . .

RUN ./gradlew build -x test

FROM openjdk:17-alpine3.14

COPY --from=build build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]