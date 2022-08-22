FROM gradle:jdk17 as builder

WORKDIR /builder

COPY build.gradle .

COPY settings.gradle .

COPY src src

RUN gradle build --no-daemon

FROM openjdk:17-slim-bullseye

RUN apt-get update && apt-get upgrade -y

COPY --from=builder /builder/build/libs/reactive.learning-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
