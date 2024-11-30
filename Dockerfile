FROM openjdk:17-jdk-alpine

LABEL authors="Nick Mendel"

# add a volume to /tmp
VOLUME /tmp

WORKDIR /rapidtogo-backend

copy build/libs/*.jar rapidtogo-backend.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "rapidtogo-backend.jar"]