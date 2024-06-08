FROM amazoncorretto:17-alpine
WORKDIR /service
COPY auth/build/libs/auth.jar /service/auth.jar
CMD ["java", "-jar", "auth.jar"]
