FROM amazoncorretto:19
COPY backend/streetdrop-api/build/libs/streetdrop-api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Djava.security.edg=file:/dev/./urandom", "-jar", "/app.jar"]