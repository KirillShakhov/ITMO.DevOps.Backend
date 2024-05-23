FROM amazoncorretto:17-alpine
WORKDIR /service
COPY target/libs/eureka.jar /service/eureka.jar
CMD ["java", "-jar", "eureka.jar"]
