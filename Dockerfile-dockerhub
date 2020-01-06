FROM java:8

VOLUME /tmp

# COPY ./target/dnspod-pulse-1.0.0-RELEASE.jar ./target/classes/*.yml ./target/classes/*.xml ./

RUN bash -c "wget https://github.com/fhqiwcw/dnspod-pulse/releases/download/v0.0.6-alpha/dnspod-pulse-1.0.0-RELEASE.jar"

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "dnspod-pulse-1.0.0-RELEASE.jar", "--spring.profiles.active=dev", "--server.port=8080", "> /log/app.log"]
