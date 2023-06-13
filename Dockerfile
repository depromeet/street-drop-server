FROM amazoncorretto:19
ARG JAR_FILE=./backend/streetdrop-api/build/libs/*.jar
COPY ${JAR_FILE} streetdrop.jar
ENTRYPOINT ["java", "-Djava.security.edg=file:/dev/./urandom", "-jar", "streetdrop.jar"]