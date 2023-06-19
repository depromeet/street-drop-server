FROM amazoncorretto:19
ARG JAR_FILE=backend/streetdrop-api/build/libs/streetdrop-api-0.0.1-SNAPSHOT.jar
ARG PROFILE=prod
ARG DB_URL
ARG DB_USERNAME
ARG DB_PASSWORD

COPY ${JAR_FILE} app.jar

ENV PROFILE=${PROFILE}
ENV DB_URL=${DB_URL}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}

ENTRYPOINT ["java","-Dspring.profiles.active=${PROFILE}", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]