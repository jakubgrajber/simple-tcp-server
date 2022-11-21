FROM amazoncorretto:17.0.4-al2

ADD target/server-1.0-SNAPSHOT-jar-with-dependencies.jar server.jar
ADD src/main/resources/config.properties src/main/resources/config.properties

ENTRYPOINT ["java", "-jar", "server.jar"]