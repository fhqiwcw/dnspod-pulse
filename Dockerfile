FROM java:8

VOLUME /tmp

COPY ./target/dnspod-pulse-1.0.0-RELEASE.jar ./target/classes/*.yml ./target/classes/*.xml ./

# RUN bash -c "touch /app.jar"

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "dnspod-pulse-1.0.0-RELEASE.jar", "--spring.profiles.active=dev", "--server.port=8080", "> /log/app.log"]
