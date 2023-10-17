FROM openjdk:17-jdk-alpine

EXPOSE 8080

ADD target/StorageCloud-0.0.1-SNAPSHOT.jar springbootapp.jar

CMD ["java","-jar","springbootapp.jar"]