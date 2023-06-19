FROM amazoncorretto:19
ARG JAR_FILE=backend/streetdrop-api/build/libs/streetdrop-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ARG PROFILE=dev
ENTRYPOINT ["java","-Dspring.profiles.active=${PROFILE}", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]