FROM maven:3.8.7-eclipse-temurin-17-alpine AS build
WORKDIR /app


RUN mvn dependency:go-offline -B


COPY src ./src
RUN mvn clean package -DskipTests


FROM eclipse-temurin:17-jre-alpine
WORKDIR /app


RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring


COPY --from=build --chown=spring:spring /app/target/*.jar app.jar


RUN mkdir -p /data && chown -R spring:spring /data
VOLUME /data


EXPOSE 8081


ENTRYPOINT ["java", "-jar", "app.jar"]