FROM amazoncorretto:19
ARG JAR_FILE=backend/streetdrop-api/build/libs/streetdrop-api.jar
COPY ${JAR_FILE} app.jar

ARG PROFILE=prod
ENV PROFILE=${PROFILE}
ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-Djava.security.edg=file:/dev/./urandom", "-jar", "/app.jar"]