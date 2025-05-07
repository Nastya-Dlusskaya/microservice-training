# Build stage
FROM gradle:7.6.4-jdk17-alpine AS build
WORKDIR /app
COPY build.gradle ./
RUN gradle dependencies --no-daemon --build-cache

COPY src ./src
RUN gradle assemble --build-cache --no-daemon -x test

# Runtime stage
FROM eclipse-temurin:17
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]