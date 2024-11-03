# The Open Notebook

## ============= ENVIRONMENT =========

Load environment variables from the `.env` file:
```bash
export $(grep -v '^#' .env | xargs)

echo $DB_HOST
echo $DB_PORT
echo $DB_NAME
echo $DB_USER
echo $DB_PASSWORD
docker exec -it NotebookDB bash   || docker compose exec -it NotebookDB bash




