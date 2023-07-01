# Define la imagen base de Java que se utilizará
FROM adoptopenjdk/openjdk11:latest

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado por Spring Boot en la carpeta /app del contenedor
COPY target/tu-aplicacion.jar app.jar

# Expone el puerto que utilizará tu API (asegúrate de que coincida con el puerto configurado en tu aplicación Spring Boot)
EXPOSE 8080

# Comando para ejecutar tu aplicación cuando el contenedor se inicie
CMD ["java", "-jar", "app.jar"]
