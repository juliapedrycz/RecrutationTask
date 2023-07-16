$env:COMPOSE_PROJECT_NAME = "empik"

docker-compose up -d postgres
sleep 5
docker-compose up -d pgadmin
Read-Host -Prompt 'Press Enter to exit.'
docker-compose down --remove-orphans --rmi local