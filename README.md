# CDC

## POSTGRES + KAFKA + DEBEZIUM

```shell
docker-compose --env-file .env up -d
```

### KAFKA

UI : http://localhost:8080/

### DEBEZIUM TOPICS

```shell
curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" localhost:8083/connectors/ --data "@debezium.json"

curl -H "Accept:application/json" localhost:8083/connectors/

curl -i -X DELETE localhost:8083/connectors/pg-connector/
```

1. Key Convertor
2. Value Convertor
3. Skip schema from key and value structs
4. Transformations
   - https://debezium.io/documentation/reference/2.7/transformations/index.html
5. Content based routing
   - https://debezium.io/documentation/reference/2.7/transformations/content-based-routing.html
   - https://github.com/yugabyte/cdc-examples/blob/main/content-based-routing/Dockerfile
   - https://github.com/yugabyte/cdc-examples/blob/main/content-based-routing/deploy-connector.sh

   Note : Added below 3 jars in ~/kafka/connect/debezium-connector-postgres/ and restarted the container debezium-cdc
   1. debezium-scripting-2.7.1.Final.jar 
      ```shell
      curl -so debezium-scripting-2.7.1.Final.jar https://repo1.maven.org/maven2/io/debezium/debezium-scripting/2.7.1.Final/debezium-scripting-2.7.1.Final.jar
      ```
   2. groovy-4.0.9.jar
      ```shell
      curl -so groovy-4.0.9.jar  https://repo1.maven.org/maven2/org/apache/groovy/groovy/4.0.9/groovy-4.0.9.jar
      ```
   3. groovy-jsr223-4.0.9.jar
      ```shell
      curl -so groovy-jsr223-4.0.9.jar  https://repo1.maven.org/maven2/org/apache/groovy/groovy-jsr223/4.0.9/groovy-jsr223-4.0.9.jar
      ```
   ```shell
    chmod 444 groovy-*
    chmod 444 debezium-scripting-2.7.1.Final.jar
   ```
   <br></br>
6. Debezium notifications