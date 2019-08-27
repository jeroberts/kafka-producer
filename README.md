# Kafka Producer using Spring Boot
The purpose of this repo is to help me learn how to create and use a simple Kafka Producer.  Currently it's only able to support a single kafka server.

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
* ```cd kafka-producer```  
```mvn spring-boot:run```  

### POST data to Producer to send to Kafka Server
* ```Incoming details```

### Option 1: Spin up Consumer in console
* ```~/kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic Person_Topic --from-beginning```

### Option 2: Run Consumer locally
* ```Incoming details```