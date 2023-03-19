# Prueba Técnica FinMaq - Componente Lógico Stateless de Backend

## Requisitos para ejecutar la aplicación:

- Tener instalada la versión 17 de Java

## Pasos

- Instalar maven y añadir el binario como variable de entorno
- En la carpeta raíz del backend:
- `mvn clean`
- `mvn package`
- `mvn package spring-boot:repackage`
- `docker build -t finmaq-prueba-backend .`
- `docker compose up -d`
- `docker exec -ti <container name> /bin/bash`
- En la cli, para ejecutar los tests: `mvn -f pom.xml test`
- `docker compose stop finmaq-prueba-backend`