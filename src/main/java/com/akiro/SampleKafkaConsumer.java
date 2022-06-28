package com.akiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Properties;
import java.util.Random;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class SampleKafkaConsumer {

  private Consumer<String, String> createConsumer(KafkaConsumerConfig config) throws IOException {

    String groupId = "kafka-consumer-" + new Random().nextInt() + config.getTopic();

    Properties properties = new Properties();
    properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getBootstrapservers());
    properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        StringDeserializer.class.getName());
    properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
        StringDeserializer.class.getName());
    properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, config.getAutoResetConfig());

    if (config.isKerberosEnabled()) {
      System.out.println("Kafka security enabled");
      properties.put("sasl.mechanism", config.getSaslMechanism());
      properties.put("security.protocol", config.getSecurityProtocol());
      properties.put("sasl.kerberos.service.name", config.getKerberosServiceName());
      properties.put("sasl.jaas.config", config.getJaasConfig());
    }
    // Create the consumer using props.
    final Consumer<String, String> consumer = new KafkaConsumer<>(properties);

    // Subscribe to the topic.
    consumer.subscribe(Collections.singletonList(config.getTopic()));
    return consumer;
  }

  public static void main(String[] args) throws IOException {

    ObjectMapper objectMapper = new ObjectMapper();

    String content = new String(Files.readAllBytes(Paths.get(args[0])));

    KafkaConsumerConfig config = objectMapper.readValue(content, KafkaConsumerConfig.class);

    SampleKafkaConsumer sampleKafkaConsumer = new SampleKafkaConsumer();

    System.out.println("Creating the kafka consumer");
    Consumer<String, String> consumer = sampleKafkaConsumer.createConsumer(config);
    System.out.println("Kafka consumer created successfully");

    System.out.println("Consuming the records from " + config.getTopic());
    final long pollInterval = config.getPollInterval();
    Thread t = new Thread(() -> {
      while (true) {
        final ConsumerRecords<String, String> consumerRecords = consumer.poll(pollInterval);
        if (consumerRecords.count() > 0) {
          consumerRecords.forEach(consumerRecord -> {
            try {
              System.out.println(consumerRecord.value());
            } catch (Exception e) {
              e.printStackTrace();
            }
          });
        }
      }
    });
    t.start();
  }
}
