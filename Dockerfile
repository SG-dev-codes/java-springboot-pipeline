# Usa una imagen base de Java
FROM eclipse-temurin:17-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR al contenedor
COPY target/*.jar app.jar

# Expone el puerto (cámbialo si tu app usa otro)
EXPOSE 8087

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
