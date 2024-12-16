# JDK 
FROM openjdk:17-jdk-slim
# Working dir
WORKDIR /app
# Copy the contents into the workdir
COPY . /app
# build the application with maven wrapper so no need to install maven, not sure why clean is necessary here
RUN ./mvnw clean package
# Expose port
EXPOSE 8080
# Commands to run in cmd, JAR filename as defined in pom.xml
CMD ["java", "-jar", "target/cardeditor-0.0.1-SNAPSHOT.jar"]