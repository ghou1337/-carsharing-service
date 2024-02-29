# Używajemy podstawowego obrazu z Javą i Maven
FROM eclipse-temurin:20-jdk AS build

# Kopiujemy kod źródłowy i pliki pom.xml do kontenera
COPY src /app/src
COPY pom.xml /app/

# Ustawiamy katalog roboczy
WORKDIR /app

RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*
# Budowanie aplikacji za pomocą Maven
RUN mvn clean package

# Używamy podstawowego obrazu z JRE
FROM eclipse-temurin:20-jdk

# Kopiujemy zebrane pliki z poprzedniego etapu
COPY --from=build /app/target/*.jar /app/app.jar

# Definiujemy zmienne środowiskowe
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/carsharing_db
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=228322

# Otwórzenie portu, na którym będzie działać aplikacja
EXPOSE 8080

# Komenda do wykonania po uruchomieniu kontenera
CMD ["java", "-jar", "/app/app.jar"]