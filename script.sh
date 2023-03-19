docker build -t finmaq-prueba-backend .
docker compose up -d
docker exec -ti finmaq-prueba-backend /bin/bash
docker compose stop server