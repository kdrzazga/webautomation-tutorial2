FROM openjdk:12-alpine

COPY target/webautomation-tutorial*with-dependencies.jar  /webautomation-tutorial2.jar

CMD ["java", "-jar", "webautomation-tutorial2.jar"]

FROM openjdk:12-alpine

COPY target/test-classes/microservices/authors-service-1.0.jar  /authors-service.jar

CMD ["java", "-jar", "webautomation-tutorial2.jar"]