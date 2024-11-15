# sample-kafka

A sample Kafka application using spring boot.

Generate the sources for the Avro schema:

```shell
mvn generate-sources
```

## Start local Kafka

Start the local Kafka cluster:

```shell
cd development
docker-compose up

# For background mode
docker-compose up -d
docker-compose down
```

Start the application:

```shell
mvn spring-boot:run
```

## Try it out

```shell
# Send a message
curl http://localhost:8088/sample/key/aloha

# Send a lot of messages with key a, b and c
curl http://localhost:8088/stress/a,b,c/aloha

# Send 100000 messages for each of a, b and c keys
curl http://localhost:8088/stress/a,b,c/aloha?amount=100000
```

## Ports

```shell
# Kafka UI
http://localhost:8082

# Schema Registry UI
http://localhost:8081

# Kafka bootstrap server
localhost:9092

# Application REST API
http://localhost:8088


```