# Start from official Java image
FROM openjdk:23-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the JAR file (you’ll build this using mvn package)
COPY target/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
