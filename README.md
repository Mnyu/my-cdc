# CDC

## POSTGRES + KAFKA + DEBEZIUM

```shell
docker-compose --env-file .env up -d
```

```shell
curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" localhost:8083/connectors/ --data "@debezium.json"

curl -H "Accept:application/json" localhost:8083/connectors/
```