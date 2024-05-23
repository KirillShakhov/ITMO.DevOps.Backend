FROM amazoncorretto:17-alpine
WORKDIR /service
COPY target/libs/chat.jar /service/chat.jar
CMD ["java", "-jar", "chat.jar"]
