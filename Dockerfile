FROM eclipse-temurin:25-jdk-jammy


WORKDIR /app


COPY build/libs/*-SNAPSHOT.jar app.jar


EXPOSE 8081


ENTRYPOINT ["java", "-jar", "app.jar"]