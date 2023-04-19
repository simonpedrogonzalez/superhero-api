FROM maven:3.8.2-eclipse-temurin-17

WORKDIR /app

COPY . .

ENV PORT=${PORT}
ENV DB_URL=${DB_URL}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}

RUN mvn dependency:go-offline
RUN mvn clean package

CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar", "-Xms512M","-Xmx1024M"]