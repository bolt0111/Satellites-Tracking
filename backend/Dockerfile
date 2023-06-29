#define base docker image
FROM openjdk:19
LABEL maintainer = "satellites-tracking"
ENV PROJECT_DIR /usr/src/satellite_backend
WORKDIR ${PROJECT_DIR}
COPY target/satellites-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "satellites-0.0.1-SNAPSHOT.jar"]
