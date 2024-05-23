FROM amazoncorretto:17-alpine
WORKDIR /service
COPY target/libs/auth.jar /service/auth.jar
CMD ["java", "-jar", "auth.jar"]
