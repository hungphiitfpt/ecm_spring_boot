FROM openjdk:21-ea-slim-bullseye

ADD target/Eccomer-Spring-Boot-0.1.jar Eccomer-Spring-Boot-0.1.jar

ENTRYPOINT [ "java", "-jar", "/Eccomer-Spring-Boot-0.1.jar" ]