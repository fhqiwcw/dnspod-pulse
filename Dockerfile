FROM java:8

VOLUME /tmp

COPY ./target/dnspod-pulse-1.0.0-RELEASE.jar app.jar

# RUN bash -c "touch /app.jar"

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=dev", "--server.port=8080", "> /log/app.log"]
