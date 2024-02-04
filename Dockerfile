FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy your application files
COPY build/libs/*SNAPSHOT.jar app.jar

# Expose the port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]