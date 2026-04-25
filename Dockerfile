FROM eclipse-temurin:17-jdk-slim


WORKDIR /app


COPY build/libs/*-SNAPSHOT.jar app.jar


EXPOSE 8081


ENTRYPOINT ["java", "-jar", "app.jar"]