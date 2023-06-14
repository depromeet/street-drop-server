FROM amazoncorretto:19
ARG JAR_FILE=./backend/streetdrop-api/build/libs/streetdrop-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Djava.security.edg=file:/dev/./urandom", "-jar", "/app.jar"]