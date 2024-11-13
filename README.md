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
```

Start the application:

```shell
mvn spring-boot:run
```

## Try it out

```shell
curl http://localhost:8088/sample/aloha
```
