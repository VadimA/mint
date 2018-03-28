FROM openjdk:8-jre-alpine
COPY ./target/mint-0.0.1-SNAPSHOT.jar /usr/src/mint/
WORKDIR /usr/src/mint
EXPOSE 8080
CMD ["java", "-jar", "mint-0.0.1-SNAPSHOT.jar"]