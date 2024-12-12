# Etapa 1: Construcción del proyecto con Maven
FROM maven:3.9.0-eclipse-temurin-21 AS build

# Configurar el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar los archivos del proyecto al contenedor
COPY . .

# Ejecutar el comando de Maven para construir el proyecto
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final para ejecutar la aplicación con Java 21
FROM eclipse-temurin:21-jdk-slim

# Configurar el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo JAR generado desde la etapa de construcción
COPY --from=build /app/target/*.jar /app/app.jar

# Exponer el puerto en el que la aplicación Spring Boot escucha
EXPOSE 9082

# Comando para ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
