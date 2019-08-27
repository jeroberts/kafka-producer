# Kafka Producer using Spring Boot
The purpose of this repo is to help me learn how to create and use a simple Kafka Producer.

## Running Producer
These instructions assume ~/kafka
### Start Zookeeper  
* ```~/kafka/bin/zookeeper-server-start.sh ~/kafka/config/zookeeper.properties```

### Start Kafka  
* ```~/kafka/bin/kafka-server-start.sh ~/kafka/config/server.properties```

### Create Kafka Topic
Person_Topic will be used for this example.
* ```~/kafka/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic Person_Topic```

### Run Spring Boot Producer locally
* ```mvn spring-boot:run```

### POST data to Producer to send to Kafka Server
* Incoming details

