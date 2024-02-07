# Используем базовый образ с Java и Maven
    FROM eclipse-temurin:20-jdk AS build

# Копируем исходный код и файлы pom.xml в контейнер
COPY src /app/src
COPY pom.xml /app/

# Устанавливаем рабочую директорию
WORKDIR /app

RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*
# Собираем приложение с помощью Maven
RUN mvn clean package

# Используем базовый образ с JRE
FROM eclipse-temurin:20-jdk

# Копируем собранные файлы из предыдущего этапа
COPY --from=build /app/target/*.jar /app/app.jar

# Определяем переменные окружения
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/carsharing_db
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=228322

# Открываем порт, на котором будет работать приложение
EXPOSE 8080

# Команда, которая будет выполнена при старте контейнера
CMD ["java", "-jar", "/app/app.jar"]