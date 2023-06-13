FROM amazoncorretto:19
WORKDIR /street-drop-server/backend/street-drop-api
COPY backend/street-drop-api/build/libs/*.jar app.jar

ARG PROFILE=prod
ENV PROFILE=${PROFILE}
ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-Djava.security.edg=file:/dev/./urandom", "-jar", "/app.jar"]