# Base image with JDK 17
FROM openjdk:17-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the packaged jar from target folder
COPY target/student-management-0.0.1-SNAPSHOT.jar app.jar

# Expose application port
EXPOSE 8080

# Command to run the jar
ENTRYPOINT ["java","-jar","app.jar"]