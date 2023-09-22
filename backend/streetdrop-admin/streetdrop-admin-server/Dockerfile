FROM openjdk:19-jdk

WORKDIR /app

CMD ["../../gradlew", "streetdrop-admin:streetdrop-admin-server:build"]

ARG JAR_FILE=./build/libs/streetdrop-admin-server-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar
ENV	USE_PROFILE prod

ENTRYPOINT ["java","-Dspring.profiles.active=${USE_PROFILE}", "-jar","app.jar"]
