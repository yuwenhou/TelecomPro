package kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import utils.PropertiesUtil;

import java.util.Arrays;

public class HBaseConsumer {

    public static void main(String[] args) {
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(PropertiesUtil.properties);
        kafkaConsumer.subscribe(Arrays.asList(PropertiesUtil.getProperty("kafka.topics")));
        while(true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);

            for (ConsumerRecord<String, String> cr : records) {
                String orivalue = cr.value();
                System.out.println(orivalue);


            }

        }
    }

}
