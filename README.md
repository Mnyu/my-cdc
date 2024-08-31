# CDC

## POSTGRES + KAFKA + DEBEZIUM

```shell
docker-compose --env-file .env up -d
```

```shell
curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" localhost:8083/connectors/ --data "@debezium.json"

curl -H "Accept:application/json" localhost:8083/connectors/

curl -i -X DELETE localhost:8083/connectors/pg-connector/
```

### DEBEZIUM TOPICS

1. Key Convertor
2. Value Convertor
3. Skip schema from key and value structs
4. Transformations
5. Debezium notifications