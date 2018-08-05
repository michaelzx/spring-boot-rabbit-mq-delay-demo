# SpringBoot RabbitMQ 延迟消息 队列DEMO

玩一玩 可以启动多个实例，然后使用一个不断发送消息
```
java -jar demo-0.0.1-SNAPSHOT.jar --name="Spring" --server.port=8881
java -jar demo-0.0.1-SNAPSHOT.jar --name="Spring" --server.port=8882
java -jar demo-0.0.1-SNAPSHOT.jar --name="Spring" --server.port=8883
```