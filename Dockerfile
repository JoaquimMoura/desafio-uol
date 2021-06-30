FROM openjdk

WORKDIR /app

COPY target/uol-0.0.1-SNAPSHOT.jar /app/uol.jar

ENTRYPOINT ["java", "-jar", "uol.jar"]