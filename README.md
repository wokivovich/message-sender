It is a simple application represents microservices interaction. 
I implement two ways of communication - Spring Cloud Feign Client and Apache Kafka.
You can switch ways by configuring producer's `application.yml` by `sending.method`. In addition to it, you can change
period of message sending, by `sending.timeout` value. 

### Instructions
You can run Apache Kafka locally, or use my docker-compose, it creates zookeeper and kafka containers.
But don't forget to change kafka and zookeeper ports in `application.yml` in producer and consumer.

Don't forget to create database and user for your database. Check that database name, username and password
compare with `application.yml` in consumer.

Run `ConsumerApplication` and `ProducerApplication`. When they will start working, you can check
your database and see, that messages are sending and receiving.
