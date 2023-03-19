## BUILD EXECUTABLE stage 1 ##
FROM maven:3.8.7-eclipse-temurin-19 AS builder
WORKDIR /backend/build
COPY . .
RUN mvn -f pom.xml clean package
RUN mvn package spring-boot:repackage

## EXECUTE APPLICATION stage 2 ##
FROM maven:3.8.7-eclipse-temurin-19 AS runtime
WORKDIR /backend/app
COPY --from=builder /backend/build/target/FinMaqPruebaServer-0.0.1-SNAPSHOT.jar finmaq_prueba_backend.jar
ENTRYPOINT ["java","-jar","finmaq_prueba_backend.jar"]
CMD ["-start"]