FROM adoptopenjdk/maven-openjdk11
COPY ./ /app
WORKDIR /app
RUN mvn clean package