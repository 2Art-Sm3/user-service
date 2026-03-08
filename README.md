### Запуск PostgreSQL через Docker

```bash
docker run -d -p 5434:5432 \
  -e POSTGRES_DB=user_service_db \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  --name postgres-user-service \
  postgres:15