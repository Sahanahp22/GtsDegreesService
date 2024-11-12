FROM openjdk:11
WORKDIR /GTSDegreesService
ENV PORT 4702
EXPOSE 4702
COPY target/GTSDegreesService-1.0.0-SNAPSHOT.jar /GTSDegreesService/
CMD ["java", "-jar", "GTSDegreesService-1.0.0-SNAPSHOT.jar"]