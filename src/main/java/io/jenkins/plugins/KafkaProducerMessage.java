package io.jenkins.plugins;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerMessage {

    private String topic;
    private String broker;

    public KafkaProducerMessage(String broker, String topic){
        this.broker = broker;
        this.topic = topic;
    }

    public void produce(String message){

        // create instance for properties to access producer configs
        Properties props = new Properties();

        //Assign localhost id
        props.put("bootstrap.servers", broker);
        //Set acknowledgements for producer requests.
        props.put("acks", "all");
        //Specify buffer size in config
        props.put("batch.size", 16384);
        //Reduce the no of requests less than 0
        props.put("linger.ms", 1);
        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        producer.send(new ProducerRecord<String, String>(topic, message));
        producer.close();
    }
}
