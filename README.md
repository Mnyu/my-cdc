# CDC

## POSTGRES + KAFKA + DEBEZIUM

### STEPS TO RUN THE APP
1. 
   ```shell
   docker-compose --env-file .env up -d
   ```
   Verify if all the containers are running.<br>
   Apache Kafka UI : http://localhost:8080/ 
   <br>
2. ```shell
   docker exec -it debezium-cdc bash
   cd connect/debezium-connector-postgres/
   curl -so debezium-scripting-2.7.1.Final.jar https://repo1.maven.org/maven2/io/debezium/debezium-scripting/2.7.1.Final/debezium-scripting-2.7.1.Final.jar
   curl -so groovy-4.0.9.jar  https://repo1.maven.org/maven2/org/apache/groovy/groovy/4.0.9/groovy-4.0.9.jar
   curl -so groovy-jsr223-4.0.9.jar  https://repo1.maven.org/maven2/org/apache/groovy/groovy-jsr223/4.0.9/groovy-jsr223-4.0.9.ja
   chmod 444 groovy-jsr223-4.0.9.jar groovy-4.0.9.jar debezium-scripting-2.7.1.Final.jar
   exit
   docker stop debezium-cdc
   docker start debezium-cdc
   curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" localhost:8083/connectors/ --data "@debezium.json"
   ```
   <br>
3. Start the ```order-service```, ```shipment-service```, ```invoice-serice```.
   <br>
4. Create a new order by sending a POST Rest API request to http://localhost:8282/orders with body as
   ```js
   {
    "customerId": "1234567",
    "totalValue": 100,
    "lineItems": [{
            "item": "test1",
            "quantity" : 1,
            "totalPrice" : 100
        }]
   }
   ```
   <br>
5. Verify logs of ```shipment-service```, ```invoice-serice``` to see the received msgs.

<br>
<br>

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
    chmod 444 groovy-jsr223-4.0.9.jar groovy-4.0.9.jar debezium-scripting-2.7.1.Final.jar
   ```
   <br></br>
6. Debezium notifications