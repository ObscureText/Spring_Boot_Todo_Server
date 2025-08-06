# --- Stage 1: Build the app ---
FROM gradle:8.4.0-jdk17-alpine AS build

# Copy project files
COPY --chown=gradle:gradle . /home/gradle/project
WORKDIR /home/gradle/project

# Build the fat JAR
RUN gradle bootJar

# --- Stage 2: Run the app ---
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy only the built JAR from the previous stage
COPY --from=build /home/gradle/project/build/libs/*.jar app.jar

# Expose port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
