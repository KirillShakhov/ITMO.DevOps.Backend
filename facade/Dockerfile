FROM amazoncorretto:17-alpine
WORKDIR /service
COPY target/libs/facade.jar /service/facade.jar
CMD ["java", "-jar", "facade.jar"]
