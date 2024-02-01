FROM eclipse-temurin:17-jdk-alpine
RUN mkdir /maera
COPY target/maera-1.0-SNAPSHOT.jar /maera/maera-1.0-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "/maera/maera-1.0-SNAPSHOT.jar"]
