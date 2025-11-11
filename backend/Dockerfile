FROM azul/zulu-openjdk:17 AS build

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN chmod +x mvnw

RUN ./mvnw dependency:go-offline --no-transfer-progress

COPY src src

RUN cd src/main/resources && \
    openssl genrsa 2048 > app.key && \
    openssl rsa -in app.key -pubout -out app.pub

RUN ./mvnw package -DskipTests --no-transfer-progress

FROM azul/zulu-openjdk:17 AS runner

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar


EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]