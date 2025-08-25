# Etapa 1
FROM gradle:8.14.3-jdk21 AS build
COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN gradle bootJar --no-daemon

# Etapa 2
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar platzi_play.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "platzi_play.jar"]