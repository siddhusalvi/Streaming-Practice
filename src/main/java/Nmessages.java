import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.json.JSONObject;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class Nmessages {
    public static void main(String[] args){

        List<JSONObject> messagesFromKafka = new ArrayList<>();

        messagesFromKafka = consumeMessages("test");



    }
    public static List<JSONObject> consumeMessages(String kafkaTopicName) {

        boolean flag = true;
        List<JSONObject> messagesFromKafka = new ArrayList<>();
        int recordCount = 0;
        int i = 0;
        int maxMessagesToReturn = 20;


        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "project.group.id");
        props.put("max.partition.fetch.bytes", "1048576000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("test"));

        consumer.poll(10);

        consumer.assignment().forEach(System.out::println);

        AtomicLong maxTimestamp = new AtomicLong();
        AtomicReference<ConsumerRecord<String, String>> latestRecord = new AtomicReference<>();



        //        LOGGER.info("Subscribed to topic " + kafkaConsumer.listTopics());
//        while (flag) {
//            // will consume all the messages and store in records
//            ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
//            kafkaConsumer.seekToEnd(Collections.singleton(topicPartition));
//
//
//
//            // getting total records count
//            recordCount = records.count();
////            LOGGER.info("recordCount " + recordCount);
//            for (ConsumerRecord<String, String> record : records) {
//                if(record.value() != null) {
//                    if (i >= recordCount - maxMessagesToReturn) {
//                        // adding last 20 messages to messagesFromKafka
////                        LOGGER.info("kafkaMessage "+record.value());
//                        System.out.println(record.value());
//                    }
//                    i++;
//                }
//            }
//            if (recordCount > 0) {
//                flag = false;
//            }
//        }
//        kafkaConsumer.close();
        return messagesFromKafka;
    }
}
