FROM alpine:latest

ENV JAVA_HOME="/usr/lib/jvm/default-jvm/"
RUN apk add openjdk17

ENV PATH=$PATH:${JAVA_HOME}/bin

CMD ["jshell"]

COPY target/legal_guardian_soa_canete_2023-0.0.1-SNAPSHOT.jar /legal_guardian_soa_canete.jar

ENTRYPOINT ["java", "-jar", "/legal_guardian_soa_canete.jar"]

EXPOSE 8084