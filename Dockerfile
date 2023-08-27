FROM openjdk:18-jdk

ENV PARAMS="--spring.profiles.active=cloud"

WORKDIR /build

ADD ./demo-web/target/demo-web-0.0.1-SNAPSHOT.jar ./skywire.jar

ENTRYPOINT ["sh","-c","java -jar skywire.jar $PARAMS"]