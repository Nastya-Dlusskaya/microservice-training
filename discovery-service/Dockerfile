# Build stage
FROM gradle:7.6.4-jdk17-alpine AS build
WORKDIR /app
COPY build.gradle ./
RUN gradle dependencies --no-daemon --build-cache

COPY src ./src
RUN gradle assemble --no-daemon --build-cache -x test

# Runtime stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8761
CMD ["java", "-jar", "app.jar"]