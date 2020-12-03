FROM maven:3.6.3-jdk-11 AS builder

WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /build/src/
RUN mvn package -DskipTests



#RUN mvn clean package -DskipTests dependency:go-offline

FROM openjdk:11
COPY --from=builder /build/target/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar", "-Ddebug", "-Xmx128m"]