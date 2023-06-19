FROM amazoncorretto:19
ARG JAR_FILE=backend/streetdrop-api/build/libs/streetdrop-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ARG PROFILE=dev
ARG DB_HOST
ARG DB_NAME
ARG DB_USERNAME
ARG DB_PASSWORD
ENV PROFILE=${PROFILE}
ENV DB_HOST=${DB_HOST}
ENV DB_NAME=${DB_NAME}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}
ENTRYPOINT ["java","-Dspring.profiles.active=${PROFILE}", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]