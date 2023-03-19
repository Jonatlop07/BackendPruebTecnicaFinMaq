# Prueba Técnica FinMaq - Componente Lógico Stateless de Backend

## Requisitos para ejecutar la aplicación:

- **Ejecución con Docker Compose (Recomendada)**: Tener instalado Docker, Docker Compose y el programa necesario para ejecutar Docker (DockerDesktop, Minikube, entre otros).
- **Ejecución con Maven**: Tener una versión eciente del plugin de Maven y Java JDK versión 19 (como versión por defecto de Java).

##  Pasos para ejecutar la aplicación (Docker Compose)
La imagen del servidor se encuentra pública en DockerHub:
- Crear y ejecutar un contenedor de la aplicación: `docker compose up -d`
- Ejecutar una terminal en el contenedor (si se requiere) `docker exec -ti finmaq-prueba-backend /bin/bash`
- Detener el contenedor: `docker compose stop server`
- Eliminar el contenedor: `docker compose rm server`

##  Pasos para ejecutar la aplicación (Maven)
- Verificar que el binario de Maven esté como variable de entorno (para usar el comando `mvn`)
- En la carpeta raíz del backend, ejecutar:
- `mvn -f pom.xml clean package`
- `mvn -f pom.xml package spring-boot:repackage`
- Finalmente, ejecutar el .jar generado: `java -jar target/FinMaqPruebaServer-0.0.1-SNAPSHOT.jar`
- Para ejecutar los tests: `mvn -f pom.xml test`

## Para hacer uso de la API, use las siguientes URIs:
- Para las peticiones: `http://localhost:8090/api/v1/user`
- Para ver la información de la API con Swagger: `http://localhost:8090/api/v1/swagger-ui.html`